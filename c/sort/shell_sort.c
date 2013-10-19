#include<stdio.h>
void shellsort(int v[],int n)
{
	int gap,i,j,temp;
	for(gap = n/2; gap>0; gap /=2)
		for(i=gap; i<n; ++i)
			for(j=i-gap; j>=0 && v[j] >v[j+gap]; j-=gap)
			{
				temp = v[j];
				v[j] = v[j+gap];
				v[j+gap] = temp;
			}
}

void ccout(int s[],int n)
{
	int i;
	for(i=0; i<n; ++i)
		printf("%d\t",s[i]);
	printf("\n");
}

int main()
{
	int s[5]={4,1,3,9,5};
	ccout(s,5);
	shellsort(s,5);
	ccout(s,5);
	return 0;
}
