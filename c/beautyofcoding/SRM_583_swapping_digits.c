#include<stdio.h>
#include<stdlib.h>

int N;
char *input;

void swap(char *a,int pos1,int pos2)
{
	int temp;
	temp=a[pos1];
	a[pos1]=a[pos2];
	a[pos2]=temp;
}

int median3(char *a,int left,int right)
{
	int median;
	median=left+(right-left)/2;
	if(a[left]>a[median])
		swap(a,left,median);
	if(a[median]>a[right])
		swap(a,median,right);
	if(a[left]>a[median])
		swap(a,left,median);
	return median;
}

void quick_sort(char *a,int left,int right)
{
	int m,i,piovt;

	if(left>=right)
		return;

	piovt=median3(a,left,right);
	swap(a,left,piovt);

	for(i=left+1,m=left; i<=right; i++)
		if(a[i]<a[left])
			swap(a,++m,i);
	
	swap(a,m,left);
	quick_sort(a,left,m-1);
	quick_sort(a,m+1,right);	
}

char *swap_digits(char *num,int n)
{
	int i;
	printf("before quick sort:%s\n",num);
	quick_sort(num,0,n-1);
	printf("after quick sort:%s\n",num);
	i=0;
	while(num[i]=='0')
		i++;
	swap(num,0,i);
	return num;
}

int find_min(char *a,int left,int right)
{
	int i,min,sec_min,offset;
	offset=left-1;
	min=left;
	for(i=left+1; i<=right; i++)
		if(offset==0){
			//printf("the min start from 1:min:%d a[min]:%d a[i]:%d\n",min,a[min],a[i]);
			if(a[min]=='0' && a[i]!='0')
				min=i;
			else if(a[i]>'0' && a[min]>a[i])
				min=i;
		}
		else
			if(a[min]>=a[i])
				min=i;
	return min;
}

char *min_num(char *num,int n)
{
	int i,min;
	for(i=0; i<n-1; i++){
		min=find_min(num,i+1,n-1);
		printf("min:%d num[min]:%c i:%d\n",min,num[min],i);
		if(num[min]=='0' && i==0)
			continue;
		if(num[min]<num[i]){
			swap(num,min,i);
			break;
		}
	}
	return num;
}

void get_data_from_keyboard()
{
	int i;
	printf("please enter the number of the input string:\n");
	scanf("%d",&N);
	
	input=calloc(N+1,sizeof(char));
	printf("please enter the input string:\n");
	//for(i=0; i<N; i++)
		//scanf("%s", input+i);
	//input[N]='\0';
	scanf("%s",input);
	printf("\n\n");
}

int main()
{
	get_data_from_keyboard();
	//printf("the result:%s\n",swap_digits(input,N));
	printf("the min swap pair result:%s\n",min_num(input,N));
	return 0;	
}
