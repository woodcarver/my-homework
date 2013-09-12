#include<stdio.h>

int main()
{
	int *p;
	int a[2]={1,2};
	p=a;
	printf("%d,%d\n",*(p+1),*(p+10));
	printf("%d,%d\n",1/5,1%5);
	printf("%d,%d\n",-1/5,-1%5);
	printf("%d,%d\n",-13/5,-13%5);
	p=p+10;
}
