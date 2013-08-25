#include<stdio.h>

int rand7()
{
	return rand()%7+1;
}

int rand10()
{
	int px,py;
	do{
		px=rand7()-1;
		py=rand7()-1;
		printf("%d %d\n",px,py);	
	}while(px*7+py>40);
	return (px*7+py)/4+1;
}

void main()
{
	printf("final:%d\n",rand10());
}
