#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<netinet/in.h>

#define MAXLINE 100
#define SERVER_PORT 12345
#define QUEUE_SIZE 1

void fatal(char *msg)
{
	printf("%s\n",msg);
	exit(1);
}
int main(int argc,char **argv)
{
	int sockfd,bin,connfd,lisfd;
	char buff[MAXLINE];
	struct sockaddr_in sevr_addr;
	time_t ticks;
	pid_t pid;

	memset(&sevr_addr,0,sizeof(sevr_addr));
	sevr_addr.sin_family=AF_INET;
	sevr_addr.sin_addr.s_addr=htonl(INADDR_ANY);
	sevr_addr.sin_port=htons(SERVER_PORT);

	sockfd=socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
	if(sockfd<0)
		fatal("socket failed");
	//setsockopt(sockfd,SOL_SOCKET,SO_REUSEADDR,(char *)&on,sizeof(on));
	bin=bind(sockfd,(struct sockaddr *)&sevr_addr,sizeof(sevr_addr));
	if(bin<0)
		fatal("bind failed");

	lisfd=listen(sockfd,QUEUE_SIZE);
	if(lisfd<0)
		fatal("listen failed");

	while(1){
		connfd=accept(sockfd,NULL,NULL);
		if((pid=fork())==0){
			close(lisfd);/*why?*/

			ticks=time(NULL);
			printf("send time:%d\n",ticks);
			snprintf(buff,sizeof(buff),"%.24s\r\n",ctime(&ticks));
			write(connfd,buff,strlen(buff));

			printf("sleep for 10 seconds...\n");
			sleep(10);
			printf("the connect is over.\n");
			close(connfd);
			exit(0);
		}
		close(connfd);
	}
}
