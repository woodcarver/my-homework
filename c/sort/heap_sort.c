#include<stdio.h>

#define left_child(i) (2*(i)+1)
#define swap(x,y) do{int tmp;tmp=(x);(x)=(y);(y)=tmp;}while(0)
perc_down(int a[],int i,int n)
{
	int child;
	int tmp;
	for(tmp=a[i]; left_child(i)<n; i=child){
		child=left_child(i);
		if(child!=n-1 &&a[child+1]>a[child])
			child++;
		if(tmp<a[child])
			a[i]=a[child];
		else
			break;
	}
	a[i]=tmp;
}

void heap_sort(int a[],int n)
{
	int i;
	for(i=n/2;i>=0;i--)
		perc_down(a,i,n);
	for(i=n-1;i>0;i--){
		swap(a[0],a[i]);
		perc_down(a,0,i);
	}
}

int main(void)
{
	int i;
	int a[10]={3,1,5,7,9,2,4,6,0};
	heap_sort(a,10);
	for(i=0; i<10; i++)
		printf("%d ",a[i]);
}
