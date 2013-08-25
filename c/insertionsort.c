#include<stdio.h>
int insertion_sort(int a[],int n)
{
	int i,j,next,count;
	printf("%d\n",j);
	count=0;
	for(i=0; i<n; ++i){
		next=a[i+1];
		for(j=i; j>=0; --j){
			//if next > a[j],next should be insert at j+1 position
			if(next>a[j])
				break;
			//move the elements of sequentail part to make room for new one
			a[j+1]=a[j];
			++count;
		}
		printf("%d\n",j);
		//this is j+1 not j,because it was unexpected less one at the last time in for loop 
		a[j+1]=next;
	}
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
        printf("count:%d\n",insertion_sort(a,8));
        print_array(a,8);
}

