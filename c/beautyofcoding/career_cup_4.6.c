#include "../data_struct/stack_ext.c"
#include "../data_struct/tree_ext_gen.c"

int second_node;
int node1;
int node2;
stack *stck;


tree *find_common_parent(tree *t)
{
	tree *parent,*ans;
	if(t==NULL)
		return NULL;
	if(t->element==node1 || t->element==node2){
		printf("find the first node.parent:%d\n",parent->element);
		if(t->element==node1)
			second_node=node2;
		else
			second_node=node1;

		if(find_node_tree(t->left,second_node) || find_node_tree(t->right,second_node))
			return t;

		while(stack_is_empty(stck)){
			parent=(tree *)pop(stck);
			if(find_node_tree(parent->right,second_node))
				return parent;
		}
	}
	push(stck,(void *)t);
	if(!(ans=find_common_parent(t->left)))
		ans= find_common_parent(t->right);
}
int main(int argc,char *argv[])
{
	int i;
	tree *t=NULL;
	tree *ans;
	if(argc!=4)
		exit(1);

	node1=atoi(argv[2]);
	node2=atoi(argv[3]);

	gen_tree_from_argument(&t,argv[1]);
	ans=find_common_parent(t);
	printf("the ans:%d\n",ans->element);
	return 0;
}
