#include<stdio.h>
#include<assert.h>
void print_array(int [],int);

/*recursive*/
int binarySearch1(int a[],int needle,int start,int end)
{
	int middle = (start+end)/2;

	if(start > end)
		return -1;//not 0,becuase the needle maybe at the 0th element
	else if(needle < a[middle]) 
		return binarySearch1(a,needle,start,middle-1);
	else if(needle > a[middle])
		return binarySearch1(a,needle,middle+1,end);
	else if(needle == a[middle])
		return middle;
}
/*simple loop*/
int binarySearch2(int a[],int needle,int n)
{
	print_array(a,n);
	printf("needle:%d\n",needle);
	int start = 0;
	int end = n-1;
	int middle;
	while(start <= end)
	{
		
		middle = (start+end)/2;
		printf("start:%d\n",start);
		printf("end:%d\n",end);
		printf("middle:%d\n",middle);
		if(needle < a[middle])
			end = middle-1;
		else if(needle > a[middle])
			start = middle+1;
		else if(needle == a[middle])
			return middle;
	}
	return -1;
}
#define MAXN 20
int main()
{
	/*int a[5]={1,3,6,9,11};
	int needle = 10;
	printf("1:%d:%d\n",needle,binarySearch1(a,needle,0,5));
	printf("2:%d:%d\n",needle,binarySearch2(a,needle,5));*/

	int n,t,i;
	int x[MAXN];
	while(scanf("%d%d",&n,&t) != EOF){
		assert(n);
		for(i=0; i<n; ++i)
			x[i] = 10*i;
		printf("result:%d\n",binarySearch2(x,t,n));
	}
	return 0;
}
void print_array(int a[],int n)
{
        int i;
        for(i=0; i<n; ++i)
                printf("%d ",a[i]);
        printf("\n");
}

