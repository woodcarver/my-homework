#include<stdio.h>
int subset[3];
int pos;
void subset(int space,int digit)
{
	if(digit<=0){
		printf("\n");
		return;
	}
	if(space){
		printf("1");
		subset[pos]=subset[pos]^(1<<digit);
		pos++;
		subset(space-1,digit-1);
	}
	printf("0");
	//subset[digit]=subset[digit]^(1<<digit);
	pos++
	subset(space,digit-1);
}
int main()
{
	int n=3;
	subset(3,3);
}
