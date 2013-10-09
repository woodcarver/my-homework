#include<stdio.h>
#include<stdlib.h>

#define MAX_LEN 30

struct stack_muti{
	int data[MAX_LEN];
	int top[3];
	int separate;
};
typedef stack_muti mstack;

void mstack_create(int separate)
{
	mstack *sck;
	sck=malloc(sizeof(struct stack_muti));
	if(sck==NULL)
		return;
	sck->top[0]=0;
	sck->top[1]=sck->top[2]=separate;
}

int mstack_pop(mstack *sck,int sck_num)
{
	if(sck_num==1 && sck->top[0]>=0)
		return sck->data[sck->top[0]--];
	else if(sck_num==2 && sck->top[1]<=sck->separate)
		return sck->data[sck->top[1]++];
	else if(sck_num==3 && sck->top[2]>separate)
		return sck->data[sck->top[2]--];
	else{
		fprintf(stderr,"stack is empty!\n");
		return -1;
	}
}

int mstack_push(mstack *sck,int sck_num,int value)
{
	if(sck_num==1 && sck->top[0]<sck->top[1]-1)
		sck->data[++sck->top[0]]=value;
	else if(sck_num==2 && sck->top[1]>sck->top[0])
		sck->data[--sck->top[1]]=value;
	else if(sck_num==3 && sck->top[2]<MAX_LEN-1)
		sck->data[--sck->top[2]]=value;
	else{
		fprintf(stderr,"stack is full!\n");
		return 0;
	}
	return 1;
}

int mstack_is_empty(mstack *sck,int sck_num)
{
	//return sck->top;
}

print_array(int *arr,int n)
{
	int i;
	for(i=0; i<n; i++)
		printf("%3d",arr[i]);
	printf("\n");
}

int main(int argc,char *argv[])
{
	int i;
	mstack *sck;
	if(argc!=4)
		exit(1);

	sck=mstack_create(atoi(argv[1]));
//	for(i=0; i<strlen(argv[2]); i++)
//		mstack_push();
//should use getc
}
