# Job Intention

### Data Engineer

# Employment

## 2013.11-now        Domob inc             Software Engineer

### Domob Data Platform (2013/11 - now)

* Built high performance ETL data pipelines for advertisement user's tracking streams on **Storm Realtime Computation Framework**(in **Java**) which processed 3T-per-day data and the latency is only 3s.
* Took **Redis** as verification hub to build data anti and information completing services. The verification rate was up to 98%.
* Designed Domob data warehouse which is based on **Hive** by receiving streams from **Kafka** and translating it to **Rcfile**. Solved the tough time window problem.
* Developed raw-data-backup service in **Python**, copyed nignx access logs scattered on machines to HDFS and designed retry-when-failed feature using task queue.

### Domob OLAP Platform (2014/5 - now)

* Imported and drived **Saiku and Mondrian open source OLAP** to upgrade the original simple version, provided friendly user interaction meanwhile reduced developers' workload.
* Improved 10 times response time for large query by replaced MySQL with **Infobright** and **Vertica** and developed multiple dimension data model ETL module.
* Developed a sqlproxy tool which implements JDBC interface that made single Saiku cube can use multiple data engines.
* Designed OLAP data metadata manager system in Python. Implemented defining table schema once and fit multiple databases. And used hook design pattern to build dimension data collection.
* Redeveloped mondrian source code to implement special requirements.

### Advertisment Event Notification Platform (2015/10 - now)

* Using producer-consumer model and Python to build a high performance remote notification system with over 1400 requests per second which uses Kafka as the task queue. And it's monitor module is based on Redis.
* Optimized message task queuing policy, significantly reducing latency under lots of unusual circumstances. Added monitor in Python thread pool to prevent thread leak.

## 2011/12-2013/7     Chu Kong tech of Beijing,inc      Software Engineer

### Real-time Gamer Data Statistic System

* Parsed, stored and manipulated 3G raw data per day in the most run-time and memory efficient manner using **PHP**.
* Used Redis as the calculating cache and made the final result persistent in MySQL.

### Train Ticket app(火车票™) (2011/12 – 2013/1)

* It provides tickets information searching and booking. Data cache was designed by Redis to support 2.3 million users and 10+ requests per second on single machine.
* Designed transit suggestion service. Using Dijstra algorithm to calculate all the shortest paths and restored the 9,000,000 recodes into MySQL by implementing horizontal distributed data tables.

# Education

## University of Science and Technology of China(UESTC)

* 2006.9-2010.7   Bachelor     Management Information Systems

# Programming Languages and Softwares

* Java(expert),PHP,Python,C,Shell
* Linux,Hive,Hadoop,Kafka,Storm,Mysql,Infobright,Vertica,Redis,OLAP,Thrift
