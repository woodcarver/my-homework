#include<stdio.h>

#define N 100

int **subset[N];

int binomail(int n,int r)
{
	int i,per_n,per_r;
	per_r=1;//need the production to keep the precision.
	per_n=1;
	
	if(n==0)
		return 1;

	for(i=n; i>n-r; i--){
		per_n*=i;
		per_r*=i-n+r;
	}

	if(!per_r)
		return 0;
	else
		return per_n/per_r;
}
void ini_subset()
{
	/*malloc for subset*/
	int i,set_num;
	for(i=0; i<N; i++){
		/*calculate the number of i-subsets*/
		set_num=binomail(n,i);
		subset[i]=malloc(set_num*i*sizeof(int));
	}
}
void combinator(int n,int m,size_t pos,int *a)
{
	int i,j;
	if(n==0)
		return;
	else if(m==0)
		return;

	combinator(n-1,m-1,0,a);

	for(i=0; i<binomail(n-1,m-1); i++){
		for(j=0; j<m-1; j++)
			subset[m][++pos][j]=subset[m-1][i][j];
		subset[m][pos][j+1]=a[n];
	}
	combinator(n-1,m,pos,a);
}
