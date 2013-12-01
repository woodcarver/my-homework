#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<netinet/in.h>

#define MAXLINE 100
#define SERVER_PORT 12345

void fatal(char *msg)
{
	printf("%s\n",msg);
	exit(1);
}
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
