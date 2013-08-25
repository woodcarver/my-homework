#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>

#define NUMBER_OF_THEADS 10

void *print_hello_world(void *tid)
{
	printf("hello world.Greetings from thread %d\n",(int *)tid);
	pthread_exit(NULL);
}
int main(int argc,char **argv)
{
	int i,status;
	pthread_t threads[NUMBER_OF_THEADS];
	
	for(i=0; i<NUMBER_OF_THEADS; i++){
		printf("Main here.Creating thread %d\n",i);
		status=pthread_create(&threads[i],NULL,print_hello_world,(void *)i);

		if(status!=0){
			printf("Oops.pthread_create returned error code %d\n",status);
			exit(-1);
		}
	}
	sleep(1);//keep the last thread be excuted and printed.
	exit(0);
}
