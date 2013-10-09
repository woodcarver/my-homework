#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "../data_struct/graph_matrix.c"

int *visited;

int is_connect_dfs(int **graph,int vertex,int target)
{
	int j;
	if(vertex==target)
		return 1;
	visited[vertex]=1;
	for(j=0; j<H; j++)
		if(graph[vertex][j]==1 && visited[j]!=1)
			if(is_connect_dfs(graph,j,target))
				return 1;
	return 0;
}

int main(int argc,char *argv[])
{
	char *fname;
	if(argc!=4)
		fname="careecup4.2";
	else
		fname=argv[3];
	build_graph_from_file(fname);
	print_graph();
	visited=calloc(H,sizeof(int));
	printf("\nresult:%d\n",is_connect_dfs(matrix,atoi(argv[1]),atoi(argv[2])));
	return 0;
}
