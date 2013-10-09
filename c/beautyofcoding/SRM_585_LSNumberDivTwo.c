#include<stdio.h>
#include<stdlib.h>

int calculate(int *seq,int n)
{
	int i,count;
	count=1;
	for(i=0; i<n-1; i++)
		if(seq[i]>=seq[i+1])
			count++;
	return count;
}

int main()
{
	int *seq,n,i;
	printf("please enter the number of sequence:\n");
	scanf("%d",&n);
	seq=calloc(n,sizeof(int));
	printf("please enter the sequence:\n");
	for(i=0; i<n; i++)
		scanf("%d", seq+i);

	printf("\nthe LISNumber is:%d\n",calculate(seq,n));
	return 0;
}
