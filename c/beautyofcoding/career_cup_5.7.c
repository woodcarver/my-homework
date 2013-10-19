#include<stdio.h>
#include<stdlib.h>
int bit_fetch(int num,int pos)
{
	if(num&(1<<pos))
		return 1;
	else
		return 0;
}

int find_missing(int *arr,int n)
{
	int i;
	for(i=0; i<n; i++){
		printf("bit:%d\n",bit_fetch(arr[i],0));
		if((i&1)^bit_fetch(arr[i],0))
			return i;
	}
	return n;
}

int main(int argc,char *argv[])
{
	int *arr,n,i;
	n=atoi(argv[1]);
	arr=malloc(n*sizeof(int));
	for(i=0; i<n; i++)
		scanf("%d",arr+i);
	
	printf("the ans:%d\n",find_missing(arr,n));
}
