#include<stdio.h>
#include<stdlib.h>

#define true 1;
#define false 0;

int *x;
int node;
int count;

void permutation(int level,int n)
{
	int i,elem,j,in_set;
	node++;
	if(level==n){
		count++;
		for(i=0; i<n; i++)
			printf(" %d ",x[i]);
		printf("\n");
	}
	else
		for(elem=1; elem<=n; elem++){
			in_set=false;
			for(j=0; j<=level; j++)
				if(elem==x[j]){
					in_set=true;
				}
			if(in_set)
				continue;

			x[level]=elem;
			permutation(level+1,n);
		}
}

int main(int argc,char *argv[])
{
	if(argc!=2)
		exit(-1);
	int n=atoi(argv[1]);
	x=calloc(n,sizeof(int));
	permutation(0,n);
	printf("total node:%d count:%d\n",node,count);
	return 0;
}
