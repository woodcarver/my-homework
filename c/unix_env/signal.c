#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>

void kill_test()
{
    pid_t pid;
    if((pid=fork())==0){
        pause();
        printf("control should never reach here!\n");
        exit(0);
    }
    sleep(3);
    kill(pid,SIGKILL);
}
void alarm_handler(int sig)
{
    static int beeps=0;
    printf("BEEP\n");
    if(++beeps<5)
        alarm(1);
    else{
        printf("BOOM\n");
        exit(0);
    }
}
void alarm_test()
{
    signal(SIGALRM,alarm_handler);
    alarm(1);
    while(1){
        ;
    }
    exit(0);
}
int main()
{
    //kill_test();
    alarm_test();
    exit(0);
}
