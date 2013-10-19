#include<string.h>
#include "../data_struct/tree_ext.c"

void build_binary_tree(tree **root,char *arr,int left,int right)
{
	tree *new_node;
	printf("left:%d right:%d middle:%d\n",left,right,arr[(left+right)/2]-'0');
	int middle;
	/*if(*root==NULL)
		return;*/
	if(left>right)
		return;
	
	middle=left+(right-left)/2;
	new_node=malloc(sizeof(struct tree_node));
	if(new_node==NULL)
		return;
	new_node->element=arr[middle]-'0';
	new_node->left=new_node->right=NULL;
	
	*root=new_node;
	build_binary_tree(&((*root)->left),arr,left,middle-1);
	build_binary_tree(&((*root)->right),arr,middle+1,right);
}

int main(int argc,char *argv[])
{
	tree *t;
	build_binary_tree(&t,argv[1],0,strlen(argv[1])-1);
	print_tree(t,"root");

	return 0;
}
