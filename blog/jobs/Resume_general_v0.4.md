# § 工作经历

## 2013.11-now        多盟无限科技有限公司     系统研发工程师
## 2013.11-now        Domob inc             Software Developer

### 数据平台设计与开发（2013.11 － now）
### Domob Data Platform (2013.11 - now)

``keyword: Java, storm,shell, python, kafka, hive, vertica, infobright``

* 使用Storm流式计算框架编写Java程序，搭建了广告5条用户事件流的实时ETL处理管道，处理每日3T数据的清洗、转换、过滤工作，平均消息延迟3s
* Built high performance ETL data pipelines for ``5 advertisement user tracking streams`` on **Storm realtime computation framework**(in **Java**). Processing 3T data everyday only in 3s delay per message average.
* 使用Redis作为核对仓库，构建了数据流的基本anti服务和数据匹配补全服务，使得核对率达到98%
* Used **Redis** as searching reservoir to architecture data anti and infomation completing service.
* 通过kafka的消息分发中心，部署了离线批量日志储存服务。设计了Kafka流式消息到批量Rcfile的转化逻辑，处理了其中较难的时间窗口问题。完成了储存和备份整个过程，构建了以为Hive为基础的数据仓库
* Designed Domob data warehouse which is base on **Hive** by receiving streams from **Kafka** and translating it to **Rcfile**. Solved the tougth time window problem
* 开发了原始日志文件备份服务，使用Python实现了收集散落在各个机器上的nginx日志到HDFS上，利用任务队列的方式设计了备份失败重试特性
* Developed raw data backup service in **Python**, copying nignx access logs which are scattered on machines to HDFS. Using task queue to implement failure retry feature.

### OLAP系统（2014.5 - now）
### Domob OLAP Platform (2014.5 - now)

``keyword: Java, shell, mondrian, saiku``

* 引进和推动saiku整套OLAP解决方法，替换了原来简陋的数据查询系统，向各种层次业务人员提供友好的系统，同时减轻了技术开发工作量
* ``Imported and drived **Saiku and Mondrian Open sourc OLAP**`` which replaced the original simple version, providing friendly user interaction mean while reduced the workload of developer.
* 探索面向分析的列式引擎，从Mysql改为面向分析型数据库（infobright、vertica），可行查询数据容量扩大了至少10倍，并且实现了整个多维度数据制作模块。
* Improved 10 times response time by replaced MySql with **Infobright** and **Vertica** and developed multiple dimension data model ETL module.
* 针对不断各种异构的数据引擎，开发了借助于mondrian的聚集表路由功能的隐藏数据分层开发了**sqlproxy**工具。遵循JDBC接口，提供面向java的一个通用接口。其中通过mondrian用户的查询sql，自动解析出表,再根据表库配置，自动实现信息自动匹配跳转。从而可以实现面对业务人员透明超细粒度查询cube。
* Inorder to extend saiku to use different databases in once cube, designed sqlproxy tool through developing a JDBC application.
* 设计了元数据管理系统。利用python构建了整个程序，实现了一次table schema定义跨数据引擎同步，同时搭配钩子设计实现了事实表、维度表的结构同步和维度表的数据搜集。
* Designed OLAP data metadata manager system in Python. Implemented defining table schema once and fit multiple databases. And used hook design pattern to build dimension data collection.
* 对mondrian修改并新加了诸多featrue，以满足了广告大维度数据的展现。比如：1. 加入like、limit语意 2. 增加log4j新接口，支持模糊查询维度列表需求
* Changed mondrian source code to adptive special requirements - added like senmatics and 模糊 searching.

### 广告事件消息通知系统（2015.10 - now）
``keyword: python, php, kafka, redis, thrift``
- Responsibilty on domob event notification system.

主导了通知点击事件给广告主和第三方数据监测平台，整个系统分为event collector 、sender and monitor和统计后台三个部分。

#### event collector & sender
* 采用生产者消费模型和线程池技术用python构建了qps达1400的远程消息发送系统，以kafka做输入源和中间任务队列，以redis和mysql分层作为统计服务储存
* 重新设计了消息发送机制，采用回归式队列代替原来低效的线性队列形式。有效防治了个别通知目标服务器宕机时候阻塞整个系统的吞吐量
* 重新设计了python线程池，加入线程检测，防止线程池线程泄露导致服务性能下降
* 增加了原来缺失的单元测试模块，并且简化原来集成测试的流程，大大降低测试代码的耗时并且增强代码的可靠性

#### click monitor
* 重新设计了更符合业务需求的数据监测点位，简化了系统状态监测和问题排查速度。其中思路借鉴于fb303，使得统计形成一个中心存取方式，从而使得数据和应用分离，形成各种展现集中统计可视化平台

## 2011.6-2013.07     北京触控科技有限公司     软件工程师
### 实时用户数据统计系统（2013.1 – 2013.7）

触控统一账户用户平台的子项目，该平台目前接入了包括《我叫MT》在内的十余款游戏，开发的api有用户注册、登录、好友关系、支付、数据、日志统计和报表（对内业务）。

* 每日处理的日志量达到3G。数据层完全利用redis做为db，以达到最快速度的数据统计处理速度。系统架构是：采集器->分发器->处理器->后续处理器->回收器——日志采集API直接将日志送入待处理队列，然后通过日志分发守护进程分发到各个到各个redis db，然后由计算处理程序来处理，并汇总去重。最后直接回收redis中日志记录，转移到磁盘上存档。

### 火车票数据服务项目（2011.6 – 2013.1）                                                                                                                     
keywords: php,redis,mysql

一个拥有200+万加用户的手机火车票服务软件，提供准确、实时的火车站点、线路、票务查询和订购业务。

* 建立了数据源层、数据库（mysql）层、Nosql(redis)内存缓存层、客户端层四层数据模式，仅用两台服务器来承受900,000次日（最高峰是1000,000次）点击，2300,000用户的压力。并且可是实现平滑火车时刻表数据更新
* 利用Dijstra算法一次性产生所有线路最短路，并通过定时更新策略提供中转查询服务。其中通过水平分表方法存放9,000,000多条线路记录在mysql中
* 通过webservice方法支持ios、android和website三个客户端的数据访问，保持客户端之间的独立，方便控制流量并防止同时崩溃。
* 通过redis排队和多进程、定时脚本措施可支撑每日百万级别用户的定时推送功能

# § 教育背景
2006.9 – 2010.7     电子科技大学     信息管理与信息系统

# § 业余项目

### 科大学生贷款还款系统 （2008 - 2010）
一个网络桌面软件，使用了C#网络框架和事件驱动并发模型开发，提供全校每年8000人使用。

# § 技能特长、编程语言和软件

* Java(expert),PHP(expert),python,Linux C,Shell
* Linux,hive,hadoop,kafka,storm,Mysql,infobright,vertica,Redis
