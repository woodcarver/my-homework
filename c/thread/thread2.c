#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>

pthread_t ntid1,ntid2;

printids(const char *s)
{
	pid_t pid;
	pthread_t tid;
	pid=getpid();
	tid=pthread_self();
	printf("%s pid %u tid %u (0x%x)\n",s,(unsigned int)pid,(unsigned int)tid,(unsigned int )tid);
}

void *thr_fn(void *arg)
{
	printids("new thread:");
	return((void *)0);
}
void *thr_fn2(void *arg)
{
	int foo=10000;
	printids("thread 2:");
	printf("thread 2:%d\n",foo);
	pthread_exit((void *)&foo);
}
int main(void)
{
	//printf("the size of pthread_t:%u",sizeof(pthread_t));
	int err,*ret;
	
	err=pthread_create(&ntid1,NULL,thr_fn,NULL);
	if(err!=0){
		fprintf(stderr,"can't create thread1:%d\n",err);
		exit(-1);
	}
/*	err=pthread_join(ntid1,&ret);
	if(err!=0){
                fprintf(stderr,"can't join with thread1:%d\n",err);
                exit(-1);
        }*/
	sleep(1);

	err=pthread_create(&ntid2,NULL,thr_fn2,NULL);
        if(err!=0){
                fprintf(stderr,"can't create thread2:%d\n",err);
                exit(-1);
        }
        err=pthread_join(ntid2,(void *)&ret);
        if(err!=0){
                fprintf(stderr,"can't join with thread2:%d\n",err);
                exit(-1);
        }
        sleep(1);
	
	printids("main thread:");
	printf("main thread foo:%d\n",*ret);
	sleep(1);
	exit(0);
}
