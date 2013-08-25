#include<stdio.h>
#include<string.h>
#define N 5
#define W 11
#define MIN_WEIGHT -1e9

int weight[N]={2,3,8,1,10};
int price[N]={10,1,8,2,10};
int tprice[N+1][W+1];
int vector[N+1][W+1];

int max(int num1,int num2)
{
	if(num1>=num2)
		return num1;
	else
		return num2;
}

int knapsack(int n,int cap)
{
	int put,notput;
	printf("passed n:%d,cap:%d\n",n,cap);
	if(cap<0)
		return MIN_WEIGHT;//0;
	else if(n<=0)
		return 0;
	else if(tprice[n][cap]>0){
		printf("build on tprice[%d][%d]:%d\n",n,cap,tprice[n][cap]);
		return tprice[n][cap];
	}
	else{
		notput=knapsack(n-1,cap);
		put=knapsack(n-1,cap-weight[n-1])+price[n-1];
		tprice[n][cap]=max(put,notput);
		printf("calculate n:%d cap:%d notput:%d put:%d tprice:%d\n",n,cap,notput,put,tprice[n][cap]);
		if(tprice[n][cap]==put)
			vector[n][cap]=1;//vector[n-1]=1;
		else
			vector[n][cap]=0;//vector[n-1]=0;
		printf("n:%d cap:%d vector:%d\n\n",n,cap,vector[n][cap]);	
		return tprice[n][cap];
		/*return tprice[n][cap]=max(knapsack(n-1,cap),knapsack(n-1,cap-weight[n-1])+price[n-1]);*/
	}
}
int knapsack2(int n,int cap)
{
	int i,j;
	memset(tprice,0,sizeof(tprice));
	for(i=0; i<n; i++)
		for(j=0; j<=cap; j++){
			if(j<weight[i])
				tprice[i+1][j]=tprice[i][j];
			else
				tprice[i+1][j]=max(tprice[i][j],tprice[i][j-weight[i]]+price[i]);
		}
	return tprice[n][cap];
}
int unbounded_knapsack(int n,int cap)
{
	int k,temp;

	if(n<=0)
		return 0;
	if(tprice[n][cap]>0){
		printf("build on tprice[%d][%d]:%d\n",n,cap,tprice[n][cap]);
		return tprice[n][cap];
	}

	tprice[n][cap]=unbounded_knapsack(n-1,cap);
	for(k=1;k<=cap/weight[n-1];k++){
		temp=unbounded_knapsack(n-1,cap-k*weight[n-1])+k*price[n-1];
		if(tprice[n][cap]<temp)
			tprice[n][cap]=temp;
		printf("i:%d k:%d tprice:%d\n",n,k,tprice[n][cap]);
	}
	return tprice[n][cap];
}
int main()
{
	int i,j,temp_cap;
/*	weight[N]={2,3,8,1,10};
	price[W]={10,1,8,2,10};*/
	memset(tprice,0,sizeof(tprice));
	memset(vector,0,sizeof(vector));
	printf("the optimal solution is:%d\n",knapsack(N,W));
/*	for(i=0; i<N; i++)
		for(j=0; j<W; j++)

*/
	for(i=1; i<=N; i++){
		for(j=0; j<=W; j++)
			printf("%2d ",tprice[i][j]);
		printf("\n");
	}
	printf("\n");

	for(i=1; i<=N; i++){
		for(j=0; j<=W; j++)
			printf("%2d ",vector[i][j]);
		printf("\n");
	}
	printf("\n");

	for(i=N,temp_cap=W; i>0; i--){
		printf("%d ",vector[i][temp_cap]);
		if(vector[i][temp_cap])
			temp_cap-=weight[i-1];
	}
	printf("\n");

	memset(tprice,0,sizeof(tprice));
	printf("knapsack2 the optimal solution is:%d\n",knapsack2(N,W));
	memset(tprice,0,sizeof(tprice));
	printf("unbound knapsack the optimal solution is:%d\n",unbounded_knapsack(N,W));
	return 0;
} 
