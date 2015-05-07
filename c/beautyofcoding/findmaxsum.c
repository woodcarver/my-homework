#include<stdio.h>
/*the complex is O(n*n*n)*/
int find_max_sum(int a[],int n)
{
	int i,j,k,max_sum,sum;
	max_sum = 0;
	sum = 0;
	for(i = 0; i <= n; ++i)
		for(j = 0; j<= n-i; ++j){
			//printf("i:%d j:%d\n",i,j);
			sum = 0;
			for(k = j; k<j+i; ++k)
				sum += a[k];
			if(sum > max_sum){
				printf("i:%d j:%d sum:%d\n",i,j,sum);
				max_sum = sum;
			}
		}
	return max_sum;
}
/*2013-8-2 add a new method*/
int find_max_sum2(int a[],int n)
{
	int max_sum,suffix_sum,i;
	max_sum = suffix_sum = 0;
	for(i = 0; i < n; i++){
		if(suffix_sum < -a[i])
			suffix_sum = 0;
		else
			suffix_sum += a[i];
		if(suffix_sum > max_sum)
			max_sum = suffix_sum;
	}
	return max_sum;
}
/*has recorded the subsequence*/
typedef struct sub_seq{
	int *seq;
	int sum;
	int len;
};
void find_max_sum3(int *a,int n, sub_seq *global_max,sub_seq *suffix_max)
{
	if(n == 1){
		if(a[n-1] >= 0)
	}
		
}
int main()
{
	int a[7]={31,-41,59,26,-53,58,97};
	printf("maxSum:%d\n",find_max_sum(a,7));
	return 0;
}
