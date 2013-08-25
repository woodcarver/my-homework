#include<stdio.h>
#define N 9

int rotate(int a[],int n,int p);
int reverse(int start,int end,int a[]);

void print_array(int a[],int n)
{
        int i;
        for(i=0; i<n; ++i)
                printf("%d\t",a[i]);
        printf("\n");
}
int main()
{
        int a[]={1,2,3,4,5,6,7,8,9};
        print_array(a,N);
        printf("count:%d\n",rotate(a,N,3));
        print_array(a,N);
}

int rotate(int a[],int n,int p)
{
	reverse(0,p,a);
	reverse(p+1,n-1,a);
	reverse(0,n-1,a);
	return 1;
}

int reverse(int start,int end,int a[])
{
	int i,temp,middle;
	//middle = (end-start-1)/2+start;
	middle = (end+start)/2;
	for(i=start; i<=middle; ++i){
		temp=a[i];
		a[i]=a[end-i+start];
		a[end-i+start]=temp;
	}
        print_array(a,8);
}
