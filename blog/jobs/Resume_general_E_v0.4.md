# Employment

## 2013.11-now        Domob inc             Software Engineer

### Domob Data Platform (2013/11 - now)

* Built high performance ETL data pipelines for advertisement user's tracking streams on **Storm Realtime Computation Framework**(in **Java**) which processed 3T-per-day data and the latency is only 3s.
* Took **Redis** as verification hub to build data anti and information completing services. The verification rate was up to 98%.
* Designed Domob data warehouse which is base on **Hive** by receiving streams from **Kafka** and translating it to **Rcfile**. Solved the tough time window problem.
* Developed raw-data-backup service in **Python**, copyed nignx access logs scattered on machines to HDFS and designed retry-when-failed feature using task queue.

### Domob OLAP Platform (2014/5 - now)

* Introduced and improved **Saiku and Mondrian open source OLAP** which replaced the original simple version, providing friendly user interaction mean while reduced the workload of developer.
* Improved 10 times response time by replaced MySql with **Infobright** and **Vertica** and developed multiple dimension data model ETL module.
* Inorder to extend saiku to use different databases in once cube, designed sqlproxy tool through developing a JDBC application.
* Designed OLAP data metadata manager system in Python. Implemented defining table schema once and fit multiple databases. And used hook design pattern to build dimension data collection.
* Redeveloped mondrian source code to implement special requirements: added like,limit semantics and range query etc.

### User Event Notification Platform (2015/10 - now)

* Using producer-consumer model and Python to build a high performance remote notification system with over 1400 requests per second which uses Kafka as the task queue. And it's monitor module is based on Redis.
* Optimized task send strategy to fix high delay when a lot of failure tasks happened problem.
* Added monitor in Python thread pool to prevent thread leak.

## 2011/10-2013/7     Chu Kong tech of Beijing,inc      Software Engineer

### Real-time Gamer Data Statistic System

* Parsed, stored and manipulated 3G of raw data everyday in the most run-time and memory efficient manner using **PHP**.
* Used Redis as the calculating cache and make the final result persistent in MySql.

### Train Ticket app(火车票™) (2011/6 – 2013/1)

* It provides precise and  trains  and tickets info,also supports booking service. Designed data cache by Redis to support 2.3 million users and 10+ requests per second on single machine.
* Designed transit suggestion service. Using Dijstra algorithm to calculate all the shortest paths and restored the 9,000,000 recodes into MySql by implementing horizontal distributed data tables.

# Education
2006.9-2010.7    University of Science and Technology of China(UESTC)    Management Information Systems

# Programming Languages and Softwares

* Java(expert),PHP,Python,C,Shell
* Linux,Hive,Hadoop,Kafka,Storm,Mysql,Infobright,Vertica,Redis,OLAP,Thrift
