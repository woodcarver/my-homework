#include "../data_struct/list_ext.c"
#include "../data_struct/tree_ext_gen.c"

tree *path[100];

void print_path(int *cur_path,int start,int end);
void find_sum(tree *t,int sum,int level)
{
	int i,path_sum;
	int cur_path[100];
	tree *node;
	
	if(t==NULL)
		return;

	path_sum=0;
	printf("level:%d\n",level);
	for(i=level; i>=0; i--){
		node=path[i];
		//printf("%d ",node->element);
		cur_path[i]=node->element;
		path_sum+=node->element;
		if(path_sum==sum)
			print_path(cur_path,i,level);
	}
	printf("\n");

	path[level+1]=t->left;
	find_sum(t->left,sum,level+1);

	path[level+1]=t->right;
	find_sum(t->right,sum,level+1);
}

void print_path(int *cur_path,int start,int end)
{
	int i;
	for(i=start; i<=end; i++)
		printf("%d ",cur_path[i]);
	printf("\n");
}

int main(int argc,char *argv[])
{
	int i;
	tree *node,*ans;
	tree *t=NULL;
	if(argc!=3)
		exit(1);

	gen_tree_from_argument(&t,argv[1]);
	path[0]=t;
	find_sum(t,atoi(argv[2]),0);
	return 0;
}
