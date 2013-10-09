#include<stdio.h>
#include<stdlib.h>
#include<string.h>
 
char *simulate(int *prefer1,int *prefer2,int n)
{
	int i,j,count;
	char *res=calloc(n,sizeof(char));
	memset(res,'0',n);
	
	i=j=0;
	for(count=(n+1)/2; count>0; count--){
		printf("count:%d i:%d j:%d\n",count,i,j);
		while(i<n)
			if(res[prefer1[i]-1]!='0'){
				i++;
				continue;
			}
			else{
				res[prefer1[i]-1]='1';
				i++;
				break;
			}
		while(j<n)
			if(res[prefer2[j]-1]!='0'){
				j++;
				continue;
			}
			else{
				res[prefer2[j]-1]='2';
				j++;
				break;
			}
	}
	return res;
}

int main()
{
	int *prefer1,*prefer2,n;
	int i;
	printf("enter the number n:\n");
	scanf("%d",&n);

	prefer1=calloc(n,sizeof(n));
	prefer2=calloc(n,sizeof(n));	

	printf("enter the prefer1:\n");
	for(i=0; i<n; i++)
		scanf("%d",prefer1+i);
	printf("enter the prefer2:\n");
	for(i=0; i<n; i++)
		scanf("%d",prefer2+i);


	printf("\nthe result:%s\n",simulate(prefer1,prefer2,n));

	return 0;
}
