#include<stdio.h>

int count_ones1(int n)
{
	int count;
	count=0;
	while(n){
		printf("version1:n:%d\n",n);
		if(n&1)
			count++;
		n=n>>1;
	}
	return count;
}

int count_ones2(int n)
{
	int count;
	count=0;
	while(n){
		count++;
		n&=n-1;
	}
	return count;
}

int main(int argc,char *argv[])
{
	int count;
	printf("count_ones1:%d\n",count_ones1(atoi(argv[1])));
	count=count_ones2(atoi(argv[1]));
	printf("count_ones2:%d\n",count);

	printf("the smallest:%x\n",(1<<count)-1);
	printf("the largest:%x\n",((~(unsigned int)0)>>count) ^ ~(unsigned int)0);
	return 0;
}
