#include<stdio.h>
int main()
{
	srand(10);
	printf("rand:%d\n",rand());
	printf("result:%d\n",system("date"));
	return 0;
}
