#include<stdio.h>
int bubble_sort(int a[],int n)
{
	int i,j,temp,count;
	count=0;
	for(i=0; i<n; ++i){
		for(j=0; j<n-i-1;++j)
			if(a[j]>a[j+1]){
				temp=a[j];
				a[j]=a[j+1];
				a[j+1]=temp;
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
        printf("%d\n",bubble_sort(a,8));
        print_array(a,8);
}
