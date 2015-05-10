nginx反向代理配置
===============
什么是代理
--------
代理在普通生活中的意义就是本来应该你做的事情，你让别人代你做了，那么那个帮你做的人就是你的代理。而在计算机网络中代理的概念差不多，就是本来要客户端要做的网络访问，现在移交给另外一个机器做，那么那个机器就被称为代理服务器，代理服务器帮你来访问。过程如下：

正常情况：
client ---(send request)---> server

代理情况：
client ---(send request)---> clinet proxy --(send request)---> server
什么又是反向代理
--------------
那什么又是反向代理呢？反向代理可不是说本来代理你事务的人，反过来代理别人。反向代理在计算机网络中是指这么一个过程。一般来说正向代理是客户机找人来代理把自己的请求转发给服务端，但是如果反向代理，找代理的人不再是客户机，而是服务器这边把自己接受的请求转发给背后的其他机器。其主要区别：

- 正向代理中代理的过程是客户端，代理机器是作为一个访问客户的身份的；而在反向代理中代理机器是作为服务身份。
- 正向代理中代理的过程是服务端，服务端对代理的存在无感知；而在反向代理中客户机对代理的存在无感知。

反向代理情况：
clinet --(send request)--> server proxy --(send request)-->other
server

先让我们看看一个简单示例
-----------------
```
#① part start
user www;
worker_process 1;
error_log /var/log/nginx/error.log
pid /var/run/nginx.pid;

events{
	use epoll;
	worker_connections 1024;
}

http{
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;
    access_log  /var/log/nginx/access.log  main;
    #
    sendfile        on;
    #
    keepalive_timeout  65;
    gzip  on;
   
    index   index.html index.htm;
    include /etc/nginx/conf.d/*.conf;
    include /etc/nginx/sites-enabled/*;
    #② part start
   	# 定义上游服务器列表组
    upstream web1 {
        server 127.0.0.1:111 weight=1;
        server 127.0.0.1:222 weight=1;
    }
    upstream web2 {
        server 127.0.0.2:111 weight=1;
        server 127.0.0.2:222 weight=6;
        server 127.0.0.2:333 weight=7;
    }
    #定义一个服务器，其监听80端口，配置的域名是www.company.com
    server{
    	listen 80;
    	# using www  domain to access the main website
    	server_name www.company.com;
    	access_log  /var/log/nginx/www.log
    	
    	location / {
    		root /home/website_root;
    		
    	}
    }
    #③ part start
    #定义第二个服务器，其同样监听80端口，但是匹配域名是web.company.com
    server{
    	listen 80;
    	# using web sub domain to access
    	server_name web.company.com;
    	access_log  /var/log/nginx/web_access.log
    	
    	location / {
    		root /home/web2_root;
    		proxy_pass http://127.0.0.1:8080/web/;
    		proxy_read_timeout 300;
    		proxy_connect_timeout 300;
    		proxy_redirect     off;

    		proxy_set_header   X-Forwarded-Proto $scheme;
    		proxy_set_header   Host              $http_host;
    		proxy_set_header   X-Real-IP         $remote_addr;
    	}
    }
    #定义第三个服务器，其同样监听80端口，但是匹配域名是web1.company.com，并把请求转发到web1上游服务
    server{
    	listen 80;
    	# using web1 sub domain to access
    	server_name web1.company.com;
    	access_log  /var/log/nginx/web1_access.log
    	
    	location / {
    		root /home/web1_root;
    		proxy_pass http://web1;
    		proxy_read_timeout 300;
    		proxy_connect_timeout 300;
    		proxy_redirect     off;

    		proxy_set_header   X-Forwarded-Proto $scheme;
    		proxy_set_header   Host              $http_host;
    		proxy_set_header   X-Real-IP         $remote_addr;
    	}
    }
        #定义第三个服务器，其同样监听80端口，但是匹配域名是web2.company.com，并把请求转发到web2上游服务
    server{
    	listen 80;
    	# using web2 sub domain to access
    	server_name web2.company.com;
    	access_log  /var/log/nginx/web2_access.log
    	
    	location / {
    		root /home/web2_root;
    		proxy_pass http://web2;
    		proxy_read_timeout 300;
    		proxy_connect_timeout 300;
    		proxy_redirect     off;

    		proxy_set_header   X-Forwarded-Proto $scheme;
    		proxy_set_header   Host              $http_host;
    		proxy_set_header   X-Real-IP         $remote_addr;
    	}
    }
}
```

### 这个示例都做了什么
1. 第①部分，定义nginx通用规则，包括运行账户，处理进程个数等
2. 第②部分，开始定义上游服务器组
3. 第③部分，定义server，并指定怎么使用第②部分定义的upstream

总体来说就是这个提供了4个服务，www，web，web1，web2 4个网站。这个例子很适合只有一台机器，但是有想避免url中携带端口号，统一使用域名的方式访问。4个网站都监听80端口，但是分配不同的二级域名既可以。这就需要nginx的反向代理，把接到的请求转发给背后不同的服务。


为什么需要方向代理
---------------
为什么要反向代理？作用服务端的代理，自然就是一台服务器处理不过来了，需要转发、分散请求给其他服务器做。下面罗列些适用场景：

- 负载均衡（例子）
- 一个域名，多个网站。在这里反向代理倒不是为了负责存在，而是为了域名和服务的统一部署。例如一个公司的内部网站需要搭建很多服务——代码管理服、wiki服务、oa……，但是只要一个域名。这时候就可以用反向代理把不同的子域名转发到不同的服务上。下面是一个例子：

当然反向代理的另一大用处就是隐藏后面的实际服务，以此来达到一定的安全性。


仔细讲解每个模块
-------------
- user
user 设置nginx是以什么用户来运行的，这个非常重要，确保运行nginx的用户能有权限访问读写网站的文件。

- events
- upstream
upstream 直接翻译就是上游，即上游服务，其封装一组服务器列表，这些服务器可以别proxy_pass,fastcgi_pass,uwsgi_pass,scgi_pass和 memcached_pass引用，把接到的请求转发给这些服务器组。
引用方法就是加行http://[upstream module name]

```
The ngx_http_upstream_module module is used to define groups of servers that can be referenced by the proxy_pass, fastcgi_pass, uwsgi_pass, scgi_pass, and memcached_pass directives.
```
例子：

```
upstream  backend {  #①
    server backend1.example.com       weight=5;
    server backend2.example.com:8080;
    server unix:/tmp/backend3;

    server backup1.example.com:8080   backup;
    server backup2.example.com:8080   backup;
}

server {
    location / {
        proxy_pass http://backend; #②
    }
}

```
注意有①，和②行的写法。要引用**backend**模块，只需把它制定成**http://backend**就行。

- http 意义和配置
- service 配置和匹配规则
- location 配置和匹配规则

参考资料
-------
- [nginx upstream模块介绍文档1](http://nginx.org/cn/docs/http/ngx_http_upstream_module.html)
- [nginx upstream模块介绍文档2](http://tengine.taobao.org/book/chapter_05.html)
- [nginx配置例子](http://www.cnblogs.com/xiaogangqq123/archive/2011/03/02/1969006.html)