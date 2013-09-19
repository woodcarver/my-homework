#include<stdio.h>

enum{
	FALSE=0,
	TRUE=1
};

char *check(int n)
{
	int i,j;
	if(n<1)
		return "No";
	if(n==1)
		return "Yes";
	
	for(i=1; i<n; i++)
		for(j=i+1; j<n; j++)
			if(i*j*j==n){
				printf("found:a:%d b:%d\n",i,j);
				return "Yes";
			}
			else if(i*j*j>n)
				break;
	return "No";
}

int main(int argc,char *argv[])
{
	if(argc!=2)
		return 1;

	printf("Is it a semi_perfect square? %s\n",check(atoi(argv[1])));
	return 0;
}
