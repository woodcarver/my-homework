#include<stdio.h>
#include<stdlib.h>
#include<string.h>

enum{
	SUM=3,
	TYPE=3,
	MAX_COUNT=99999
};

int price[]={1,2,5};
int tab_count[TYPE+1][SUM+1];

int change_money(int n,int sum)
{
        int k,temp;

        if(tab_count[n][sum]>0){
                printf("build on tab_count[%d][%d]:%d\n",n,sum,tab_count[n][sum]);
                return tab_count[n][sum];
        }
	
	if(n<=0)
		if(sum>0)
			return MAX_COUNT;
		else
			return 0;

        tab_count[n][sum]=change_money(n-1,sum);
        for(k=1;k<=sum/price[n-1];k++){
                temp=change_money(n-1,sum-k*price[n-1])+k;
                if(tab_count[n][sum]>temp)
                        tab_count[n][sum]=temp;
                printf("i:%d k:%d sum:%d tab_count:%d\n",n,k,sum,tab_count[n][sum]);
        }   
        return tab_count[n][sum];
}

int main(int argc, char **argv)
{
	int sum;
	if(argc!=2)
		exit(-1);
	else
		sum=atoi(argv[1]);
        memset(tab_count,0,sizeof(tab_count));
        printf("change_money the optimal solution is:%d\n",change_money(TYPE,sum));
	return 0;
}
