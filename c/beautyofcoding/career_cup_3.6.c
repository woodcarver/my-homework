#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include "../data_struct/stack_ext.c"

#define LEN 100

void print_array(int *arr,int n);
int intcmp(const void *p1,const void *p2)
{
	return *(int *)p1 > *(int *)p2?1:0;
}
void stack_sort(stack *sck)
{
	int data[LEN],i,j;
	int *pdata;
	i=0;
	while(!stack_is_empty(sck)){
		pdata=(int *)pop(sck);
		printf("pop:%d\n",*pdata);
		data[i++]=*pdata;
	}
	qsort(data,i,sizeof(int),intcmp);
	print_array(data,i);
	for(j=i-1; j>=0; j--)
		push(sck,(void *)(data+j));
}
void print_array(int *arr,int n)
{
	int i;
	printf("the arr:");
	for(i=0; i<n; i++)
		printf("%d ",arr[i]);
	printf("\n");
}
void print_int_data(void *data, void *arg)
{
        int *p_int;
        p_int=(int *)data;
        printf("%d ",*p_int);
}

void stack_gen(stack *sck,char *str)
{
	int i,*data;
	for(i=0; i<strlen(str); i++){
		printf("push %c\n",str[i]);
		data=malloc(sizeof(int));
		*data=str[i]-'0';
		push(sck,(void *)data);
	}
	printf("push data over!\n");
}
int main(int argc,char *argv[])
{
	stack *sck;
	sck=create_stack();

	stack_gen(sck,argv[1]);
	apply_stack(sck,print_int_data,(void *)0);
	printf("\n");

	printf("start to sort the stack:\n");
	stack_sort(sck);
	apply_stack(sck,print_int_data,(void *)0);
	printf("\n");

	return 0;
}
