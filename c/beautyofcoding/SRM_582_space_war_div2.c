#include<stdio.h>
#include<stdlib.h>

int *fatig;/*fatigue vector*/
int *g_stren;
int *ene_stren;
int *ene_count;
int N;/*number of girls*/
int M;/*number of enemies*/

void min_fatig(int *g_stren,int *ene_stren,int *ene_count,int n,int i);

int min_fatig_driver(int *g_stren,int *ene_stren,int *ene_count,int n)
{
	int i,max_f;
	if(n<0)
		return -1;
	if(g_stren[n-1]<ene_stren[n-1])
		return -1;

	fatig=calloc(n,sizeof(int));
	for(i=0; i<n; i++)
		fatig[i]=0;

	min_fatig(g_stren,ene_stren,ene_count,n,n-1);
	
	printf("fatigue vector:");
	max_f=fatig[0];
	for(i=1; i<n; i++){
		printf(" %d ",fatig[i]);
		if(max_f<fatig[i])
			max_f=fatig[i];
	}
	printf("\n");

	return max_f;
}

void min_fatig(int *g_stren,int *ene_stren,int *ene_count,int n,int i)
{
	int j,k,ene_sum,avg,rest_ene_count;
	if(i<0)
		return;
	if(g_stren[i]<ene_stren[0])
		return;
	
	ene_sum=0;
	for(j=0; j<n; j++)
		ene_sum+=ene_count[j];
	avg=ene_sum/(n-i);

	rest_ene_count=ene_count[0];
	for(j=i,k=0; j<n; j++){
		if(g_stren[j]<ene_stren[k])
			break;
		while(fatig[j]<avg){
			if(g_stren[j]<ene_stren[k])
				break;
			if(rest_ene_count>avg-fatig[j]){
				fatig[j]+=avg-fatig[j];
				fatig[j+1]-=avg-fatig[j];
				rest_ene_count-=avg-fatig[j];
			}
			else{
				fatig[j]+=ene_count[k];
				fatig[j+i]-=ene_count[k];
				k++;
				rest_ene_count=ene_count[k];
			}
		}
	}
	min_fatig(g_stren,ene_stren,ene_count,n,i-1);
}

void get_data_from_file(char *fname)
{
	int i;
	FILE *file;
	if(!(file=fopen(fname,"r"))){
		fprintf(stderr,"can not open %s\n",fname);
		exit(1);
	}
	fscanf(file,"%d",&N);
	fscanf(file,"%d",&M);
	g_stren=(int *)calloc(N,sizeof(int));
	ene_stren=(int *)calloc(M,sizeof(int));
	ene_count=(int *)calloc(M,sizeof(int));

	for(i=0; i<N; i++)fscanf(file,"%d",g_stren+i);
	for(i=0; i<M; i++)fscanf(file,"%d",ene_stren+i);
	for(i=0; i<M; i++)fscanf(file,"%d",ene_count+i);
	
	fclose(file);

}

void get_data_from_keyboard()
{
	int i;
	printf("\nPlease enter the number of girl: ");
	scanf("%d",&N);
	printf("\n");
	printf("\nPlease enter the number of enemy: ");
	scanf("%d",&M);
	printf("\n");

	g_stren=(int *)calloc(N,sizeof(int));
        ene_stren=(int *)calloc(M,sizeof(int));
        ene_count=(int *)calloc(M,sizeof(int));

	printf("Please enter the girl strength: \n   ");
	for (i=0;i<N;i++) scanf("%d",g_stren+i);
	printf("Please enter the enemy strength: \n   ");
        for(i=0; i<M; i++)scanf("%d",ene_stren+i);
	printf("Please enter the enemy count: \n   ");
        for(i=0; i<M; i++)scanf("%d",ene_count+i);

}

int main(int argc,char *argv[])
{
	int min_f;

	if(argc==1)get_data_from_keyboard();
	else if(argc==2)get_data_from_file(argv[1]);
	else{
		fprintf(stderr,"Usage: %s [filename]",argv[0]);
		exit(1);
	}
	min_f=min_fatig_driver(g_stren,ene_stren,ene_count,N);
	printf("the minimal fatigue is:%d\n",min_f);

	return 0;
}
