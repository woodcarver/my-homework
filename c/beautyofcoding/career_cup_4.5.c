#include "../data_struct/list_ext.c"
#include "../data_struct/tree_ext_gen.c"

tree *tree_inorder_successor(tree *node)
{
	tree *successor;
	if(node=NULL)
		return NULL;

	if(node->parent==NULL || node->right)
		return find_min(node->right);
	else{
		successor=node->parent;
		while(successor && successor->element <node->element)
			successor=successor->element;
		return successor;
	}
}

int main(int argc,char *argv[])
{
	int i;
	tree *node,*ans;
	tree *t=NULL;
	if(argc!=3)
		exit(1);

	gen_tree_from_argument(&t,argv[1]);
	node=find_node_tree(t,atoi(argv[2]));
	ans=tree_inorder_successor(node);
	
	printf("the result:%d\n",ans->element);
	return 0;
}
