Thrift的机制
============
thrift是什么？
-------------
官网上介绍thrift是一个可伸缩的跨语言网络服务程序开发框架（``The Apache Thrift software framework, for scalable cross-language services development``）。这个介绍非常笼统，其实到现在我都没理解可伸缩（``scalable``）指的是什么特性。不过重点词应该放在 **网络服务** 和 **跨语言** 上。这两个次直接描述thrift提供的两大功能——网络协议和compiler。thrift约定了一般网络程序的3大组建——1.服务端的事件处理模型；2.传输模式的层次的选择；3.传输数据的格式。并提供了相应的各种语言的实现（实现都放在源码的lib文件下）。而跨语言是利用thrift提供的一个编译器实现的。thrift约定了一套开发服务端和客户端api开发统一中间语言，然后利用其编译器来生产各个具体目标语言，例如python、php等。

我们可以从一个例子全程跟踪讲起，这个例子来源与github.._thrift example:https://github.com/yuxel/thrift-examples。

* 安装thrfit：参考：http://thrift.apache.org/docs/install/
* 下载例子的代码：
* 目录结构：
    - client
    - gen-php
    - gen-py
    - lib
    - server
    - Example.thrift
    - README.md

    其中gen-php和gen-py是thrift compiler根据Example.thrift生产的。怎么产生这两个文件参看例子的README.md的介绍。

    lib文件夹中放入了thrift提供的网络服务程序组件的实现。这些实现可以从thrift源码的lib文件夹中获得。

    client，server和Example.thrift是需要开发者自己编写的部分。怎么编写thrift文件按，参考：http://thrift.apache.org/docs/idl/。这个例子使用的目标语言是python和php。
* 运行例子。
    我们这里选择使用python作为服务端，php的socket方式作为客户端。因为这样就不用使用apche或其他web服务器了。如果选用http传输模式必须安装一个web服务器。
    cd server && python PythonServer.py
    cd client && php -f PhpClientSocket.php

这个例子是简单的把服务器的时间传输给客户端。其中服务模型选择的是单线程服务模型；数据协议使用的二进制格式；传输协议使用的是socket。接下来主要讲解由thrift生成的processor，即放在gen-<target-language>文件下的<thrift-file-name>.<target-language-extension-name>例如：Example.py。

processor的结构如下：
    - import files。
        导入必要的文件。
    - Example.thrift中定义的接口声明。
        根据IDL定义的api生产具体的目标语言的接口声明。
    - client类声明（针对客户端）。
        封装了各个api的send和receivce网络传输方法，用于客户端来调用api。
    - processor类声明（针对服务端）。
        封装了服务端api接口的网络服务部分。todo:消息队列是怎么建立的？
    - 其他接口帮助类声明。
        各个接口的参数和结果类。类中封装了基本的read、write方法。
