#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
int snooze(int time){
    int rest;
    rest=sleep(time);
    printf("Sleep %d of %d\n",time-rest,time);
    return rest;
}
int main()
{
    int i,pid,status;
    for(i=0;i<2;i++)
        if((pid=fork())==0){
            printf("ppid:%d,i:%d\n",getppid(),i);
            snooze(3);
            exit(100+i);
        }
    if((pid=waitpid(-1,&status,0))>0){
        printf("waitpid:%d,%d\n",pid,WIFEXITED(status));
    }
    printf("I am parent:pid:%d,child_pid:%d\n",getpid(),pid);
    return 0;
}
