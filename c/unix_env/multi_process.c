#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/wait.h>

#define MAXLINE 1000

int main()
{
	char buf[MAXLINE];
	pid_t pid;
	int status;
	
	printf("%% ");
	while(fgets(buf, MAXLINE, stdin) != NULL){
		if(buf[strlen(buf)-1] == '\n')
			buf[strlen(buf)-1]=0;

		if((pid = fork()) < 0){
			printf("fork error!\n");
		}
		else if(pid == 0){
			execlp(buf, buf, (char *)0);
			printf("could't execute: %s\n",buf);
			exit(127);
		}
	}
	if((pid = waitpid(pid, &status, 0)) < 0)
		printf("waitpid error\n");
	else
		printf("waitpid is:%d",pid);

	printf("%%");
	exit(0);
}
