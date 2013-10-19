#include<stdio.h>
#include<stdlib.h>
#include "../common.c"

int n;
int *arr;
int *tmp_arr;

void merge(int *arr,int *tmp_arr,int left,int right,int middle)
{
	int i,left_p,right_p;
	printf("[merg] left:%d right:%d middle:%d\n",left,right,middle);
	left_p=left;
	right_p=middle+1;
	for(i=0; left_p<=middle && right_p<=right; i++){
		printf("[inner merg]left_p:%d right_p:%d\n",left_p,right_p);
		if(arr[left_p]<arr[right_p])
			tmp_arr[left+i]=arr[left_p++];
		else
			tmp_arr[left+i]=arr[right_p++];
	}
	while(left_p<=middle){
		tmp_arr[left+i]=arr[left_p];
		left_p++;
		i++;
	}
	while(right_p<=right){
		tmp_arr[left+i]=arr[right_p];
		right_p++;
		i++;
	}
	for(i=0; i<right-left+1; i++)
		arr[left+i]=tmp_arr[left+i];
}
void merge_sort(int *arr,int *tmp_arr,int left,int right)
{
	int middle;
	printf("[partition] left:%d right:%d\n",left,right);
	if(left>=right)
		return;
	middle=left+(right-left)/2;
	merge_sort(arr,tmp_arr,left,middle);
	merge_sort(arr,tmp_arr,middle+1,right);
	merge(arr,tmp_arr,left,right,middle);
}

int main()
{
	int i;
	printf("enter the n:\n");
	scanf("%d",&n);
	arr=malloc(n*(sizeof(int)));
	tmp_arr=malloc(n*(sizeof(int)));
	if(arr==NULL || tmp_arr==NULL)
		exit(1);
	for(i=0; i<n; i++)
		scanf("%d",arr+i);

	print_array(arr,n);
	merge_sort(arr,tmp_arr,0,n-1);
	print_array(arr,n);

	return 0;
}
