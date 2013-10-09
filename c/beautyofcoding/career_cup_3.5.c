#include<stdio.h>
#include<stdlib.h>
#include "../data_struct/stack_ext.c"

#define true 1
#define false 0

struct my_queue{
	stack *s1;
	stack *s2;
};
typedef struct my_queue mqueue;

mqueue *mqueue_create()
{
	mqueue *mq=malloc(sizeof(struct my_queue));
	if(mq==NULL){
		fprintf(stderr,"mq out of space!\n");
		return NULL;
	}
	mq->s1=malloc(sizeof(struct stack));
	mq->s2=malloc(sizeof(struct stack));
	
	if(mq->s1==NULL || mq->s2==NULL){
		fprintf(stderr,"s1 or s2 out of space!\n");
		return NULL;
	}
	
	return mq;
}
void enqueue(mqueue *mq,void *data)
{
	push(mq->s1,data);	
}

void *dequeue(mqueue *mq)
{
	void *data;
	if(stack_is_empty(mq->s2))
		while(!stack_is_empty(mq->s1))
			push(mq->s2,pop(mq->s1));
	if(!stack_is_empty(mq->s2))
		return pop(mq->s2);
	return NULL;
}
int mqueue_is_empty(mqueue *mq)
{
	if(stack_is_empty(mq->s1) && stack_is_empty(mq->s2))
		return true;
	return false;
}
void mqueue_apply(mqueue *mq,void(*fn)(void *data,void *arg),void *arg)
{
	printf("stack1:");
	apply_stack(mq->s1,fn,arg);
	printf(" stack2:");
	apply_stack(mq->s2,fn,arg);
	printf("\n");
}

void print_int_data(void *data, void *arg)
{
	int *p_int;
	p_int=(int *)data;
	printf("%d ",*p_int);
}

int main(int argc,char *argv[])
{
	/*if(argc!=2)
		exit(1);*/

	char c,*buff;
	mqueue *mq;
	void *pdata;
	mq=mqueue_create();

	while(EOF!=(c=getchar())){
		if(c=='e'){
			printf("enqueue:\n");
			buff=malloc(100);
			if(buff==NULL)
				exit(1);
			scanf("%s",buff);
			enqueue(mq,(void *)buff);
			mqueue_apply(mq,print_string_data,(void *)0);
		}
		else if(c=='d'){
			pdata=dequeue(mq);
			printf("dequeue:%s\n",(char *)pdata);
			mqueue_apply(mq,print_string_data,(void *)0);
		}
	}
}
