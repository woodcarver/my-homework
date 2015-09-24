# mondrian设计与原理
## 多维数据模型概念
- 成员(member)：成员是代表维度中一次或多次数据出现的项。度量值也可以算作一个维度，因此一个具体度量值项也可以作为一个成员。

- 元组(tuple)：是向量，用于定义来自多维数据集的数据切片；它由来自一个或多个维度的单个成员的有序集合组成。元组用于标识来自多维数据集的特定多维数据块；由来自多维数据集中各个维度的一个成员组成的元组完全描述单元值。换言之，元组是一种成员向量。
例如：(时间.[下半年], 路线.非陆地.航空)，由单个成员组成的元组也可括在圆括号内，但这不是必需的。

- 单元（cell）：多维成员的交集创建单元，可以是单个单元或单元块。元组唯一标识多维数据集中的一部分；它不必指某个特定单元，也不必包括多维数据集中的所有维度。

- 集合(set)：集合是零个、一个或多个元组的有序集合。集合最常用于在 MDX 查询中定义轴维度和切片器维度，并且同样可能只具有单个元组或可能在某些情况下为空。下面的示例显示具有两个元组的集合：{ (时间.[上半年], 路线.非陆地.航空), (时间.[下半年], 路线.非陆地.海路) }

## 程序员视角下的olap4j
olap4j是类似于jdbc的一个针对OLAP的API，也是一个数据库驱动。规定了基本的数据模型框架和提供方法指导。是作为后端OLAP引擎和前端使用者的一个统一接口。而合olap4j经常一起搭配使用的后端引擎是mondrian，一款ROLAP engine，其具体实现了各种操作的逻辑。关于olap4j的发展历史或者更多的资料，[参看Olap4j document](http://olap4j.org/olap4j_fs.html)，或者更简短的Olap4j Introduction - An end-user perspective.
![olap4j](./img/olap4j_architecture.png =500x300)

## mondrian设计与实现

## 案例介绍

## 一个获取层次成员接口的整体实现
在mondrian和olap4j都是不支持模糊查询一个level的members。比如加入我有一时间维度date，其有一个层次，下面有一个level。假如要选择一天或者几天是没问题，但是要来选择7月呢，选择7月到12月呢？难道我一直手写上180个筛选条件？

```
[date].[date].[day]
```
对于日期这种还算好，但是碰上很大的维度就更没辙了。因为这种情况就会牵扯到搜索，不可能通过列表展示肉眼选择了。（貌似saiku和mondrian都不考虑这种情况，saiku的level筛选把全部的level的members全取下来，一般对于超过一万的维度，呵呵，页面奔溃了。mondrian因为不支持模糊查询所以更是没有办法少量传递数据给saiku，同时占用大量服务器内存。我开始的时候用了一种很山寨的方法，就是mondrian侧的逻辑不变，就把全部的数据传给saiku，而saiku层做改变。把展现和搜索变成异步的，限制展现条数同时搜索从前端js移到java数据控制层，在java中过滤（用的正则过滤）后再传递给前端页面。但是这种有个严重的问题——那就是性能，想想对一万个字符串做正则比对多么耗时。后期这种方法其本也是处于不可用状态）。

既然没有，那我就想办法加一个模糊搜索的方法，步骤如下：

- 在olap4j中的Meta模块下的Level接口中增加lookupMembers方法——List<Member> lookupMembers(String keyword) throws OlapException;
- 在mondrian的MondrianOlap4jLevel类对lookupMembers的实现
- 在saiku中增加getLevelMembersByKeyword方法，其中调用Level.lookupMembers()方法

下面分步骤描述：
#### olap4j
- 首先下载olap4j源码[官网](http://olap4j.org/olap4j_fs.html)
- 打开org.olap4j.metadat.Level接口，添加方法：

```
List<Member> lookupMembers(String keyword) throws OlapException;
```

- 打开org.olap4j.driver.xmla.XmlaOlap4jLevel类，增加对lookupMembers的实现
- 修改完后，进行编译。然后把jar包分别考入到mondrian和saiku的工程下。

#### mondrian
- 下载mondrian的源码，[github](https://github.com/pentaho/mondrian)
- 在mondrian.olap4j.MondrianOlap4jLevel类中增加一个lookupMembers的实现：

```
	/**
     * @author xiedandan
     * very ugly impelementation
     */
    public List<Member> lookupMembers(String childName) throws OlapException {
        final MondrianOlap4jConnection olap4jConnection =
            olap4jSchema.olap4jCatalog.olap4jDatabaseMetaData.olap4jConnection;
        final RolapConnection mondrianConnection =
            olap4jConnection.getMondrianConnection();
        final Id.Segment id=Util.convert(IdentifierNode.ofNames(childName).getSegmentList().get(0));
        return Locus.execute(
            mondrianConnection,
            "Reading members of level",
            new Locus.Action<List<Member>>() {
                public List<Member> execute() {
                    final mondrian.olap.SchemaReader schemaReader =
                        mondrianConnection.getSchemaReader().withLocus();
                    final List<mondrian.olap.Member> levelMembers =
                        schemaReader.lookupMembersChildByName(level.getHierarchy().getDefaultMember(), id);
                    return new AbstractList<Member>() {
                        public Member get(int index) {
                            return olap4jConnection.toOlap4j(
                                levelMembers.get(index));
                        }

                        public int size() {
                            return levelMembers.size();
                        }
                    };
                }
            });
    }
```
- 在mondrian.rolap.RolapSchemaReader类中增加一个根据传入的Id返回相关列表的方法。

```
    /**
     * Finds members by a given name
     * @author xiedandan
     * @param parent
     * @param childName
     * @return
     */
    public List<Member> lookupMembersChildByName(
            Member parent, Id.Segment childName)
        {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                    "looking for children \"" + childName + "\" of " + parent);
            }
            List<Member> list=new ArrayList<Member>();
            assert !(parent instanceof RolapHierarchy.LimitedRollupMember);
            try {
                MemberChildrenConstraint constraint;
                if (childName instanceof Id.NameSegment)
                {
                    constraint = sqlConstraintFactory.getChildByNameConstraint(
                        (RolapMember) parent, (Id.NameSegment) childName);
                } else {
                    constraint =
                        sqlConstraintFactory.getMemberChildrenConstraint(null);
                }
                List<RolapMember> children =
                    internalGetMemberChildren(parent, constraint);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(
                        "children size \"" + children.size());
                }
                if (children.size() > 0) {
                    for(Member member : children){
                    	list.add(member);
                    }
                    return list;
                }
            } catch (NumberFormatException e) {
                // this was thrown in SqlQuery#quote(boolean numeric, Object
                // value). This happens when Mondrian searches for unqualified Olap
                // Elements like [Month], because it tries to look up a member with
                // that name in all dimensions. Then it generates for example
                // "select .. from time where year = Month" which will result in a
                // NFE because "Month" can not be parsed as a number. The real bug
                // is probably, that Mondrian looks at members at all.
                //
                // @see RolapCube#lookupChild()
                LOGGER.debug(
                    "NumberFormatException in lookupMembersChildByName "
                    + "for parent = \"" + parent
                    + "\", childName=\"" + childName
                    + "\", exception: " + e.getMessage());
            }
            return null;
        }
```
- 增加like语义，因为这仅仅是个实验，所以我非常山寨的直接修改的constrain的方法。修改mondrian.rolap.SqlConstraintUtils.constrainLevel方法：把columnValue前后加上％，同时把AND改成like。

```
        String constraint;
        columnValue = "%" + columnValue + "%";//add by xiedandan,for constructing like constraint
        if (RolapUtil.mdxNullLiteral().equalsIgnoreCase(columnValue)) {
            constraint = columnString + " is " + RolapUtil.sqlNullLiteral;
        } else {
            if (datatype.isNumeric()) {
                // make sure it can be parsed
                Double.valueOf(columnValue);
            }
            final StringBuilder buf = new StringBuilder();
            query.getDialect().quote(buf, columnValue, datatype);
            String value = buf.toString();
            if (caseSensitive && datatype == Dialect.Datatype.String) {
                // Some databases (like DB2) compare case-sensitive. We convert
                // the value to upper-case in the DBMS (e.g. UPPER('Foo'))
                // rather than in Java (e.g. 'FOO') in case the DBMS is running
                // a different locale.
                if (!MondrianProperties.instance().CaseSensitive.get()) {
                    columnString = query.getDialect().toUpper(columnString);
                    value = query.getDialect().toUpper(value);
                }
            }

            constraint = columnString + " like " + value;//add by xiedandan,for constructing like constraint, change = to like
```
### saiku
