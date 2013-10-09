#include<stdio.h>
#include<stdlib.h>

long long min_cars(int height)
{
	int i;
	long long sum,nleaves;
	sum=nleaves=1<<(height-1);
	
	while(nleaves=nleaves>>2){
		printf("nleaves:%lld\n",nleaves);
		sum+=nleaves;
	}
	if(height%2==0)sum++;
	return sum;
}

int main(int argc,char *argv[])
{
	if(argc!=2)
		exit(1);
	int height;
	height=atoi(argv[1]);
	printf("the min cars:%lld\n",min_cars(height));
	return 0;
}
