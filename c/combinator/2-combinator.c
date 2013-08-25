#include<stdio.h>

int main(void)
{
	int i,j,count;
	for(i=0,count=0; i<10; i++)
		for(j=i+1; j<10; j++){
			printf("%d %d\n",i,j);
			count++;
		}
	printf("the total space is %d\n",count);
}
