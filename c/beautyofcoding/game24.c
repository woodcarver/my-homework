#include<stdio.h>

#define UNKNOW -1
#define N 24
#define TRUE 1
#define FALSE 0

int set[1<<N];

int game24_driver(int *a,int n)
{
	int i,j,count;
	memset(set,UNKNOW,sizeof(set));
	
	for(i=0; i<n; i++)
		set[1<<i]=a[i];

	for(i=0; i<(1<<N)-1; i++)
		game24(i);
	
	for(i=0; i < 1<<N-1; i++)
		if(set[i]==N)
			return TRUE;
	return FALSE;
}

void game24(int subset)
{
	int i;
	if(set[subset]!=UNKNOW)
		return set[subset];
	for(i=1; i<subset; i++)
		if(subset&i == subset){
			game24(i);
			game24(subset-i);
			set_merge(i,sunset-i);
		}
}

void set_merget(int sub1,int sub2)
{
	if(set[sub1]==-1 || set[sub2]==-1)
		return;
	
}
