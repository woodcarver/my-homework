#include<stdio.h>

int sub_number(int n,int m,int i,int j)
{
	int mod;
	mod=(~0<<(j+1))^(~(~0<<i));
	printf("mod:%d\n",mod);
	return n&mod|(m<<i);
}

int main(int argc,char *argv[])
{
	printf("%d\n",sub_number(atoi(argv[1]),atoi(argv[2]),atoi(argv[3]),atoi(argv[4])));
}
