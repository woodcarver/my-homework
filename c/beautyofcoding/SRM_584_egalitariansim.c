#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "../data_struct/queue_ext.c"

enum{
	false=0,
	true=1
};

char **is_friend;
int *visited;
int *money;/*for the diffence of every citizen*/
int N;
int d;
int *vertex;

void dfs(char **graph,int v)
{
	int i;
	visited[v]=true;
	printf("dfs visited vertex:%d\n",v);
	for(i=0; i<N; i++)
		if(graph[v][i]=='Y' && !visited[i])
			dfs(graph,i);
}

int bfs(char **graph,int v,int d)
{
	int i,max_diff;
	int *pv=vertex+v;

	queue que;
	que=create_queue();
	en_queue(que,(void *)pv);
	memset(visited,false,N*sizeof(int));
	memset(money,0,N*sizeof(int));
	while(!is_empty(que)){
		pv=(int *)de_queue(que);
		visited[*pv]=true;
		printf("bfs visited the vertex:%d\n",*pv);
		for(i=0; i<N; i++)
			if(graph[*pv][i]=='Y' && !visited[i]){
				max_diff=money[i]=money[*pv]+d;
				pv=vertex+i;
				en_queue(que,(void *)pv);
			}
	}
	return max_diff;
}

int max_diff(int d)
{
	int i,opt_diff,cur_diff;
	if(!is_friend)
		return -1;
	dfs(is_friend,0);
	for(i=0; i<N; i++)
		if(!visited[i]){
			printf("the graph is not connected!\n");
			return -1;
		}

	opt_diff=0;
	for(i=0; i<N; i++){
		cur_diff=bfs(is_friend,i,d);
		if(cur_diff>opt_diff)
			opt_diff=cur_diff;
		printf("vertext:%d cur_diff:%d opt_diff:%d\n",i,cur_diff,opt_diff);
	}
	return opt_diff;
}

void get_data_from_file(char *fname)
{
	int i,j;
	FILE *file;
	if(!(file=fopen(fname,"r"))){
		fprintf(stderr,"can not open the file!\n");
		exit(1);
	}
	
	fscanf(file,"%d",&N);
	fscanf(file,"%d",&d);

	visited=calloc(N,sizeof(int));
	money=calloc(N,sizeof(int));
	
	vertex=calloc(N,sizeof(int));
	for(i=0; i<N; i++)
		vertex[i]=i;

	is_friend=(char **)calloc(N,sizeof(char *));
	for(i=0; i<N; i++){
		is_friend[i]=(char *)calloc(N,sizeof(char));
		//for(j=0; j<N; j++)
			fscanf(file,"%s",is_friend[i]);
	}
}
void print_graph()
{
	int i,j;
	printf("\n\n");
	for(i=0; i<N; i++){
		for(j=0; j<N; j++)
			printf("%d ",is_friend[i][j]);
		printf("\n");
	}
	printf("\n\n");
}
int main(int argc,char *argv[])
{
	char *fname;
	if(argc!=2)
		fname="is_friend";
	else
		fname=argv[1];

	get_data_from_file(fname);
	print_graph();

	printf("\nthe max diff:%d\n",max_diff(d));
	
	return 0;	
}
