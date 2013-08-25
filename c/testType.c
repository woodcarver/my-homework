#include<stdio.h>
void getnum(int * a)
{
	printf("%d\n",*a);
}

int main()
{
	//getnum(10);
	int *a = "10";
	char *s = "sssssss";
	printf("%d\n",*a);
	printf("%s\n",s);
	return 0;
}
