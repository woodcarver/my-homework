#include<stdio.h>
int main()
{
	int i;
	for(; ;){
		for(i=1;i<960000000;i++)
			printf("loop forever!\n");
		usleep(10);
	}
}
