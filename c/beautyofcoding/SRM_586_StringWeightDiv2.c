#include<stdio.h>
#include<stdlib.h>

#define MOD 1000000009
#define LETTER_SIZE 26

int bin_coef_mod(int n,int r)
{
	long long coeffi;
	int i;
	printf("binomial_coeffient n:%d r:%d\n",n,r);
	if(r<0 || n<r)
		return 0;
	if(2*r>n)
		r=n-r;

	coeffi=1;
	if(r!=0)
		for(i=0; i<=r-1;i++){
			coeffi=((coeffi*n-i)/(i+1))%MOD;
			printf("i:%d bin_coeffi:%lld\n",i,coeffi);
		}
	return coeffi;
}
int permutation_mod(int n,int r)
{
	long long permu;
	int i;
	if(n<=0)return 0;
	printf("permutation n:%d r:%d\n",n,r);
	permu=1;
	for(i=n; i>n-r; i--){
		//permu%=MOD;
		//permu*=i%MOD;
		permu=(permu*i)%MOD;
		printf("i:%d permu:%lld\n",i,permu);
	}
	return permu;
}

long long count_min(int len)
{
	long long diff,combin_coeff,permu;
	if(len<=LETTER_SIZE){
		printf("every word only occuer once!\n");
		return permutation_mod(LETTER_SIZE,len);
	}

	diff=len-LETTER_SIZE;
	combin_coeff=bin_coef_mod(diff+LETTER_SIZE-1,diff);
	permu=permutation_mod(LETTER_SIZE,LETTER_SIZE);
	
	return (combin_coeff*permu)%MOD;
}

int main(int argc,char *argv[])
{
	printf("the res:%lld\n",count_min(atoi(argv[1])));
	return 0;
}
