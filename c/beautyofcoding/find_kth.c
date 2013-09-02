#include<stdio.h>

void swap(int *a,int idx_a,int idx_b)
{
/*
this method can fail when swap the element with itself. the element has been change to zero and can not restored.
	printf("a:%d,b:%d\n",a[idx_a],a[idx_b]);
	a[idx_a]^=a[idx_b];
	printf("a:%d,b:%d\n",a[idx_a],a[idx_b]);
	a[idx_b]^=a[idx_a];
	printf("a:%d,b:%d\n",a[idx_a],a[idx_b]);
	a[idx_a]^=a[idx_b];
	printf("a:%d,b:%d\n",a[idx_a],a[idx_b]);
*/
	int temp;
	temp=a[idx_a];
	a[idx_a]=a[idx_b];
	a[idx_b]=temp;
}
int median3(int *a,int idx_left,int idx_right)
{
	int idx_center=(idx_left+idx_right)/2;
	//printf("left:%d,center:%d,right:%d\n",a[idx_left],a[idx_center],a[idx_right]);
	if(a[idx_left]>a[idx_center])
		swap(a,idx_left,idx_center);
	if(a[idx_center]>a[idx_right])
		swap(a,idx_center,idx_right);
	if(a[idx_left]>a[idx_center])
		swap(a,idx_left,idx_center);
	//printf("left:%d,center:%d,right:%d\n",a[idx_left],a[idx_center],a[idx_right]);
	return idx_center;
}
void quick_sort(int *a,int k,int left,int right)
{
	int i,m,pivot;
	if(left>=right)
		return;
	
	pivot=median3(a,left,right);
	swap(a,left,pivot);

	printf("pivot:%d,left:%d,right:%d\n",a[left],left,right);
	printf("\n");

	m=left;
	for(i=left+1;i<=right;i++)
		if(a[i]<a[left]){
			printf("swap i:%d,m:%d\n",i,m);
			swap(a,++m,i);
		}
	swap(a,left,m);

	if(k<=m)
		quick_sort(a,k,left,m-1);
	else
		quick_sort(a,k,m+1,right);
}

int main()
{
	int i;
	int a[10]={9,0,2,1,3,6,8,7,5,4};
	quick_sort(a,3,0,9);
	for(i=0;i<10;i++)
		printf("%d,",a[i]);
	printf("\n");
	return 0;
}
