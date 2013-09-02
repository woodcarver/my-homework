#include<stdio.h>

int majority(int *a,int n)
{
	int candi,majori,mtimes,i,count;
	count=0;
	candi=a[0];
	mtimes=1;
	for(i=1;i<n;i++){
		if(mtimes==0){
			candi=a[i];
			mtimes=1;
		}
		else
			if(candi==a[i])
				mtimes++;
			else
				mtimes--;
	}
	printf("the final candidate is:%d\n",candi);
	/*second scan to guarantee the candi is a majority number*/
	for(i=0;i<n;i++)
		if(a[i]==candi)
			count++;
	if(count>n/2)
		return candi;
	return -1;
}

int main(void)
{
	int a[8]={1,2,5,5,6,5,5,5};
	printf("the majority is:%d\n",majority(a,8));
	return 0;
}
