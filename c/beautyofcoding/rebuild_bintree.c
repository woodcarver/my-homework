#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct tnode{
	char data;
	struct tnode *left;
	struct tnode *right;
};
struct pivot{
	int mid_idx;
	int left_idx;
	int right_idx;
	struct *tnode p_node;
};
typedef struct tnode tnode;
typedef struct pivot pivot;

/*#define TREE_LEN 6
#define LEFT 0;
#define RIGHT 1;*/
enum{
//	TREE_LEN==6,
	LEFT=0,
	RIGHT=1,
	STACK_LEN=10
};

struct stack{
	int top;
	pivot stack[STACK_LEN];
};

typedef struct stack stack;

stack piv_stack;

tnode *create_tnode(char data);
pivot create_pivot(int mid_idx,int left_idx,int right_idx,tnode *p_node);

/*operations for stack*/
int is_empty(stack *stack);
pivot *pop(stack *stack);
void push(stack *stack,pivot new_pit);

void print_tree(tnode *root)
{
	if(root==NULL)
		return;
	printf("(");
	print_tree(root->left);
	printf(")");
	printf("%c",root->data);
	printf("(");
	print_tree(root->right);
	print(")");
}

void rebuild_bintree(const char *preorder,const char *inorder,int treelen,tnode **root)
{
	int i,j;
	tnode *new_tnode,*parent;
	pivot *ppivot;
	
	for(i=0;i<treelen;i++){
		new_tnode=create_tnode(preorder[i]);

		while(!is_empty(piv_stack)){
			ppivot=pop(piv_stack);
			/*find in the left subtree set*/
			for(j=ppivot->left_idx;j<=ppivoit->mid_idx-1;j++)
				if(inorder[j]==preorder[i]){
					parent=ppivot->p_node;
					position=LEFT;
					push(piv_stack,creat_pivot(j,ppivot->left_idx,ppivot->mid_idx-1,new_tnode));
					break;
				}
			/*check if it finds the node*/
			if(j<ppivot->mid_idx-1)
				break;
			/*find in the right subtree set*/
			for(j=ppivot->mid_idx+1;j<=ppivoit->right_idx-1;j++)
				if(inorder[j]==preorder[i]){
					parent=ppivot->p_node;
					position=RIGHT;
					push(stack,creat_pivot(j,ppivot->mid_idx+1,ppivot->right_idx,new_tnode));
					break;
				}
		}
		/*append the new node to tree*/
		if(root==NULL)
			*root=p;
		else if(position==LEFT)
			parent->left=new_tnode;
		else if(position==RIGHT)
			parent->right=new_tnode;
	}
}


tnode *create_tnode(char data)
{
	tnode *new_node;
	new_node=realloc(sizeof(struct tnode));
	new_node->data=data;
	new_node->left=new_node->right=NULL;
	return new_node;
}
pivot create_pivot(int mid_idx,int left_idx,int right_idx,tnode *p_node)
{
	pivot new_pivot;
	new_pivot.mid_idx=mid_idx;
	new_pivot.left_idx=left_idx;
	new_pivot.right=right_idx;
	new_pivot.p_node=p_node;
	return new_piovt;
}

/*operations for stack*/
int is_empty(pivot *stack)
{
	if(!top_stack)
		return false;
	return true;
}
pivot *pop(pivot *stack)
{
	if(top_stack)
		return stack+top_stack--;
	return NULL;
}
void push(pivot *stack,pivot new_pit)
{
	if(top_stack<STACK_LEN-1)
		stack[++top_stack]=new_pit;
}


int main(int argc,char **argv)
{
	int tree_len;
	tnode **root;
	if(argc!=3){
		const char *pre_order="abdcef";
		const char *in_order="dbaecf";
	}
	tree_len=strlen(pre_order);

	rebuild(pre_order,in_order,tree_len,root);

	return 0;
}
