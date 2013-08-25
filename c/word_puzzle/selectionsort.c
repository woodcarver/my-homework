#include<stdio.h>
int selection_sort(int a[],int n)
{
	int i,j,min,temp,count;
	count=0;
	for(i=0; i<n; ++i){
		min=i;
		for(j=i+1; j<n; ++j)
			if(a[min]>a[j])
				min=j;
		if(min!=i){
			temp=a[i];
			a[i]=a[min];
			a[min]=temp;

			++count;
		}
	}
	return count;
}
void print_array(int a[],int n)
{
	int i;
	for(i=0; i<n; ++i)
		printf("%d",a[i]);
	printf("\n");
}
int main()
{
	int a[8]={3,7,5,2,1,9,5,4};
	print_array(a,8);
	printf("%d\n",selection_sort(a,8));
	print_array(a,8);
}
