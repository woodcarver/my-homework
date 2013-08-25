#include<stdio.h>
int main()
{
	//printf("\o");
	char *p = 0;
	int *i = 0;
	if(p == i)
		printf("match:%d\n",(int *)0);
	else
		printf("not match!\n");

	return 0;
}
