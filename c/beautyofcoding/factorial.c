#include<stdio.h>
#include<stdlib.h>

int lowest_one(int n)
{
	int count=0;
	while(n)
	{
		n>>=1;
		count+=n;
	}
	count++;
	printf("the lowest_one:%d\n",count);
	return count;
}
int zero_count(int n)
{
	int count=0;
	while(n){
		count+=n/5;
		n/=5;
		/*
		n/=5;
		count+=n;
		*/
	}
	printf("the zero count:%d\n",count);
	return count;
}
int main(int argc,char **argv)
{
	int i,num;
	long unsigned factorial;

	if(argc!=2){
		printf("please input a integer!");
		exit(1);
	}
	factorial=1;
	num=atoi(argv[1]);
	for(i=1;i<=num;i++)
		factorial*=i;
	printf("the factorial:%lu\n",factorial);
	lowest_one(num);
	zero_count(num);
	return 0;
}
