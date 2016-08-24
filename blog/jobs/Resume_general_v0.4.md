# § 工作经历

## 2013.11-now        多盟无限科技有限公司     系统研发工程师

### 数据平台设计与开发（2013/11 － now）

* 使用**Storm**流式计算框架编写Java程序，搭建了广告用户事件流的实时ETL处理管道，处理每日3T数据的清洗、转换、过滤工作，平均消息延迟3s
* 使用Redis作为核对仓库，构建了数据流的基本anti服务和数据匹配补全服务，使得核对率达到98%
* 构建了以Hive为基础的数据仓库，设计了其中Kafka流式消息转成Rcfile逻辑并且处理了其中较难的时间窗口问题
* 开发了原始日志文件备份服务，使用Python实现了收集散落在各个机器上的nginx日志到HDFS上并利用任务队列的方式设计了备份失败重试特性

### OLAP系统（2014/5 - now）

* 引进和推动saiku整套OLAP解决方法，替换了原来简陋的数据查询系统，向各种层次业务人员提供友好的系统，同时减轻了技术开发工作量
* 探索面向分析的列式引擎，从Mysql改为面向分析型数据库（infobright、vertica），可行查询数据容量扩大了至少10倍，并且实现了整个多维度数据制作模块。
* 开发了一款sqlproxy工具，实现了在saiku现有的框架下一个cube可以使用多个数据引擎功能，从而能发挥出各种引擎的特点
* 对mondrian修改并新加了诸多features，以满足了广告大维度数据的展现。比如：1. 加入like、limit语意 2. 增加log4j新接口，支持模糊查询维度列表需求
* 设计了元数据管理系统。利用python构建了整个程序，实现了各种数据库的schema自动生成，同时搭配钩子设计实现了维度表的数据搜集

### 广告事件消息通知系统（2015/10 - now）

* 采用生产者消费模型和线程池技术用python构建了qps达1400的消息发送系统，以kafka做输入源和中间任务队列，以redis和mysql分层作为统计服务储存
* 优化了消息任务排队策略，大幅度降低了大量消息发送异常情况下的延迟
* 在此模块的python线程池加入监控，防止线程池线程泄露导致服务性能下降

## 2011/6-2013/07     北京触控科技有限公司     软件工程师

### 实时用户数据统计系统（2013/1 – 2013/7）

* 使用php构建系统，每日处理的日志量达到3G。数据层完全利用redis做为db，以达到最快速度的数据统计处理速度。
* Parsed, stored and manipulated 3G-per-day raw data in the most run-time and memory efficient manner using **PHP**.
* Used Redis as the calculating cache and make the final result persistent in MySql.

### 火车票移动应用（2011/6 – 2013/1）

* 提供准确、实时的火车站点、线路、票务查询和订购业务。使用Redis建立数据缓存，仅用一台机器支撑了2300,000用户，10+ qps。
* 利用Dijstra算法一次性产生所有线路最短路，并通过定时更新策略提供中转查询服务。其中通过水平分表方法存放9,000,000多条线路记录在mysql中

# 教育背景
# Education
2006.9 – 2010.7     电子科技大学     信息管理与信息系统

# 业余项目

### 科大学生贷款还款系统 （2008 - 2010）
一个网络桌面软件，使用了C#网络框架和事件驱动并发模型开发，提供全校学生使用

# 技能特长、编程语言和软件
# Programming Languages and Softwares

* Java(expert),PHP,Python,C,Shell
* Linux,Hive,Hadoop,Kafka,Storm,Mysql,Infobright,Vertica,Redis,OLAP,Thrift
