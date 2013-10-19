#include "../data_struct/list_ext.c"
#include "../data_struct/tree_ext_gen.c"

int subtree(tree *t1,tree *t2)
{
	if(t1==NULL)
		return 0;

	if(t1->element==t2->element)
		if(match_tree(t1,t2))
			return 1;
	return subtree(t1->left,t2)||subtree(t1->right,t2);
}
int match_tree(tree *t1,tree *t2)
{
	if(t1==NULL && t2==NULL)
		return 1;
	if(t1==NULL || t2==NULL)
		return 0;
	if(t1->element==t2->element)
		return match_tree(t1->left,t2->left)&&match_tree(t1->right,t2->right);
}

int main(int argc,char *argv[])
{
	int i,ans;
	tree *t1,*t2;
	t1=t2=NULL;
	if(argc!=3)
		exit(1);

	gen_tree_from_argument(&t1,argv[1]);
	gen_tree_from_argument(&t2,argv[2]);
	if(t2==NULL)
		ans=1;
	else
		ans=subtree(t1,t2);
	
	printf("the result:%d\n",ans);
	return 0;
}
