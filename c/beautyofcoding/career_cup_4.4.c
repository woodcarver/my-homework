#include "../data_struct/list_ext.c"
#include "../data_struct/tree_ext_gen.c"

list *tree_list[100];
int pos;

void tree_gen_list(tree *root)
{
	int is_end;
	queue que;
	tree *pt;
	que=create_queue();
	
	en_queue(que,(void *)root);
	en_queue(que,NULL);
	printf("\nstart to generate\n");
	while(!queue_is_empty(que)){
		is_end=1;
		//tree_list[pos]=malloc(sizeof(list *));
		while((pt=(tree *)de_queue(que))!=NULL){
			list_insert(&tree_list[pos],pt->element);
			if(pt->left){
				is_end=0;
				en_queue(que,(void *)pt->left);
			}
			if(pt->right){
				is_end=0;
				en_queue(que,(void *)pt->right);
			}
		}
		pos++;
		printf("the level:%d\n\n",pos);
		if(!is_end)
			en_queue(que,NULL);
	}
}

int main(int argc,char *argv[])
{
	int i;
	tree *t=NULL;
	if(argc!=2)
		exit(1);

	gen_tree_from_argument(&t,argv[1]);
	tree_gen_list(t);
	
	for(i=0; i<pos; i++)
		print_list(tree_list[i]);

	return 0;
}
