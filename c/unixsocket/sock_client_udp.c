#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<netdb.h>

#define SERVER_PORT 12345
#define MAX_LINE 10

void fatal(char *string);
void dg_cli(FILE *,int,struct sockaddr *,socklen_t);

int main(int argc, char ** argv)
{
	int sock_fd;
	struct sockaddr_in serv_addr;
	if(argc!=2)
		fatal("arg is error");
	memset(&serv_addr,0,sizeof(serv_addr));
	serv_addr.sin_family=AF_INET;
	serv_addr.sin_port=htons(SERVER_PORT);

	inet_pton(AF_INET,argv[1],&serv_addr.sin_addr);
	sock_fd=socket(AF_INET,SOCK_DGRAM,0);

	dg_cli(stdin,sock_fd,(struct sockaddr *)&serv_addr,sizeof(serv_addr));
}

void dg_cli(FILE *fd,int sock_fd,struct sockaddr *pserv_addr,socklen_t len)
{
	int n;
	char sendline[MAX_LINE],recvline[MAX_LINE+1];//why +1
	while(fgets(sendline,MAX_LINE,fd)!=NULL){
		sendto(sock_fd,sendline,strlen(sendline),0,pserv_addr,len);
		n=recvfrom(sock_fd,recvline,MAX_LINE,0,NULL,NULL);
		recvline[n]='\0';//for terminal character slot
		fputs(recvline,stdout);
	}
}

void fatal(char *string)
{
	printf("%s\n",string);
	exit(1);
}
