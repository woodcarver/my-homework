网络编程中的服务器模型
=====================
网络编程中的服务器模型根据接受请求和处理请求的方法来分，大方向分为阻塞模型和非阻塞模型。像redis这种服务使用的是非阻塞模型。而根据服务器对请求派生的进程或者线程方式，模型又分为单进程或线程和多进程或线程。下面依次讲解这几种经典模型：

单进程的阻塞模型
---------------
这是服务器模型中最为简单的模型，当然处理客户端请有的效率也是最低的。该模型是启动一个socket进程，每来一个请求就处理一个，如果同时有多个请求，只处理最先到达的请求，其他的请求处于阻塞状态直到前一个请求处理完成。尽管这个模型很简单，但是理解其他网络服务器模型的基石。其参考代码如下(代码下载：)：

下面的例子的功能是把服务端的时间传送给客户端：

    服务端程序：

	int main(int argc,char **argv)
	{
		int sockfd,bin,connfd,lisfd;
		char buff[MAXLINE];
		struct sockaddr_in sevr_addr;
		time_t ticks;
	
		memset(&sevr_addr,0,sizeof(sevr_addr));
		sevr_addr.sin_family=AF_INET;
		sevr_addr.sin_addr.s_addr=htonl(INADDR_ANY);
		sevr_addr.sin_port=htons(SERVER_PORT);
	
		sockfd=socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
		if(sockfd<0)
			fatal("socket failed");
		bin=bind(sockfd,(struct sockaddr *)&sevr_addr,sizeof(sevr_addr));
		if(bin<0)
			fatal("bind failed");
	
		lisfd=listen(sockfd,QUEUE_SIZE);
		if(lisfd<0)
			fatal("listen failed");
	
		while(1){
			connfd=accept(sockfd,NULL,NULL);
			ticks=time(NULL);
			printf("send time:%d\n",ticks);
			snprintf(buff,sizeof(buff),"%.24s\r\n",ctime(&ticks));
			write(connfd,buff,strlen(buff));
			close(connfd);
		}
		exit(0);
	}

    该例子中，只启用了一个accept来接受客户端的请求连接，每次只处理一个请求。其他的请求则按照FIFO的原则排入队列中，队列的大小是作为listen函数的第二个参数通知系统的。关于socket编程，参考《unix网络编程》。

    客户端程序：

	int main(int argc,char **argv)
	{
	        int sockfd,n;
	        char buff[MAXLINE+1];
	        struct sockaddr_in sevr_addr;
	
	        if((sockfd=socket(AF_INET,SOCK_STREAM,0))<0)
	                fatal("socket error");
	        memset(&sevr_addr,0,sizeof(sevr_addr));
	        sevr_addr.sin_family=AF_INET;
	        sevr_addr.sin_port=htons(SERVER_PORT);
	        if(inet_pton(AF_INET,argv[1],&sevr_addr.sin_addr)<=0)
	                fatal("can not reach the address");
	        if(connect(sockfd,(struct sockaddr *)&sevr_addr,sizeof(sevr_addr))<0)
	                fatal("can not connect");
	        while((n=read(sockfd,buff,MAXLINE))>0){
	                buff[n]=0;
	                if(fputs(buff,stdout)==EOF)
	                        fatal("fputs error");
	        }
	        if(n<0)
	                fatal("read error");
	        exit(0);
	}

一个进程或者线程一个请求模型
---------------------------
这种模型是在单进程模型的基础上，对每个请求派生一个专属的进程。由于是多进程或者线程，这种模型是可以同时处理多个请求。但是这种模型存在派生进程过多，导致服务器崩溃的危险情况。同时由于大量的进程或者线程的生成和销毁，会耗费大量的cpu资源。而其他高级模型就是解决这种情况而演变出来的。其编程的核心是在单进程模型上利用fork函数。
