#include<string.h>
#include "../data_struct/tree_ext.c"

void gen_tree_from_argument(tree **t,char *arr)
{
        int i;
        for(i=0; i<strlen(arr); i++){
		printf("insert:%c\n",arr[i]);
                insert_tree3(t,arr[i]-'0');
	}
	printf("the tree is:\n");
        print_tree(*t,"root");
	printf("\n");
}

void gen_tree_from_keyboard(tree **t)
{
	int c;
        while((c = getchar()) != EOF){
                if(c != '\n'){
                        //t = insert_tree1(t,c-'0');
                        //insert_tree2(&t,c-'0');
                        insert_tree3(t,c-'0');
                }
                else
                        print_tree(*t,"root");
        }

}

void build_binary_tree(tree **root,char *arr,int left,int right)
{
/*
	//usage
        tree *t;
        build_binary_tree(&t,argv[1],0,strlen(argv[1])-1);
        print_tree(t,"root");
*/
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

