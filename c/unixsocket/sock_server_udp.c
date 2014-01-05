#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/fcntl.h>
#include<sys/socket.h>
#include<netinet/in.h>

#define SERVER_PORT 12345
#define MAX_LINE 10

void fatal(char *string);
void dg_echo(int,struct sockaddr *,socklen_t);

int main(int argc,char **argv)
{
	int sock_fd;
	struct sockaddr_in serv_addr,cli_addr;

	memset(&serv_addr,0,sizeof(serv_addr));
	serv_addr.sin_family=AF_INET;
	serv_addr.sin_addr.s_addr=htonl(INADDR_ANY);
	serv_addr.sin_port=htons(SERVER_PORT);

	sock_fd=socket(AF_INET,SOCK_DGRAM,0);
	if(sock_fd<0)fatal("socket failed");
	if(bind(sock_fd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)))
		fatal("bind failed");
	dg_echo(sock_fd,(struct sockaddr *)&cli_addr,sizeof(cli_addr));
}

void dg_echo(int sock_fd,struct sockaddr *pcli_addr,socklen_t len)
{
	int n;
	
	char msg[MAX_LINE];

	while(1){
		n=recvfrom(sock_fd,msg,MAX_LINE,0,pcli_addr,&len);
		printf("%s\n",msg);
		sendto(sock_fd,msg,n,0,pcli_addr,len);
	}
}

void fatal(char *string)
{
	printf("%s\n",string);
	exit(1);
}
