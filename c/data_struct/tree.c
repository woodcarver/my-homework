#include<stdio.h>
#include<malloc.h>
#include "queue_ext.c"
#include "tree.h"

queue que;

tree insert_tree1(tree t, int x)
{
	if(t == NULL){
		t = malloc(sizeof(struct tree_node));
		if(t == NULL){
			printf("out of space!\n");
			return NULL;
		}
		t->element = x;
		t->left = t->right = NULL;
	}
	else if(t->element > x)
		t->left = insert_tree1(t->left, x);
	else if(t->element < x)
		t->right = insert_tree1(t->right, x);
	/*do nothing for tree->element == x*/
	return t;
}

tree insert_tree2(tree *t, int x)
{
	if(*t == NULL){
		*t = malloc(sizeof(struct tree_node));
		if(*t == NULL)
			printf("out of space!\n");
		(*t)->element = x;
		(*t)->left = (*t)->right = NULL;
	}
	else if((*t)->element > x)
		insert_tree2(&((*t)->left), x);
	else if((*t)->element < x)
		insert_tree2(&((*t)->right), x);

	return *t;
}

tree insert_tree3(tree *t, int x)
{
	while(*t)
		if((*t)->element > x)
			t = &((*t)->left);
		else if((*t)->element < x)
			t = &((*t)->right);
		else
			break;
	if(*t == NULL){
		*t = malloc(sizeof(struct tree_node));
		if(*t == NULL)
			printf("out of space!\n");
		(*t)->element = x;
		(*t)->left = (*t)->right = NULL;
	}
	return *t;
}

tree delete_tree1(tree t,int x)
{
	tree_node temp_node, temp_min;
	if(t == NULL){
		printf("can not find the element.\n");
		return NULL;
	}
	else if(t->element > x)
		t->left = delete_tree1(t->left,x);
	else if(t->element < x)
		t->right = delete_tree1(t->right,x);
	else{
		if(t->left == NULL && t->right == NULL){
			//printf("the free one:%x\n",t);
			free(t);
			//printf("the free one:%x\n",t);
			t = NULL;
		}
		else if(t->right == NULL){
			//version 2
			temp_node = t;
			t = t->left;
			free(temp_node);

			//version 1
			//replace_node(&t,t->left);
			//free(t->left);	
		}
		else if(t->left == NULL){
			//replace_node(&t,t->right);
			//free(t->right);

			//version 2
			temp_node = t;
			t = t->right;
			free(temp_node);
		}
		else{
			temp_min = find_min(t->right);
			t->element = temp_min->element;
			t->right = delete_tree1(temp_min,t->element);
		}
	}
	return t;
}

void delete_tree2(tree *t,int x)
{
	tree_node temp_node,temp_min;

	if(t == NULL){
		printf("can not find the element!\n");
		return;
	}
	else if((*t)->element > x)
		delete_tree2(&((*t)->left),x);
 	else if((*t)->element < x)
		delete_tree2(&((*t)->right),x);
	else{
		temp_node = *t;
		if((*t)->left == NULL && (*t)->right == NULL){
			*t = NULL;
			free(temp_node);
		}
		else if((*t)->right == NULL){
			*t = (*t)->left;
			free(temp_node);
		}
		else if((*t)->left == NULL){
			*t = (*t)->right;
			free(temp_node);
		}
		else{
			temp_min = find_min((*t)->right);
			(*t)->element = temp_min->element;
			delete_tree2(&((*t)->right),temp_min->element);
		}
	}
}

void replace_node(tree_node *t, tree_node snode)
{
	(*t)->element = snode->element;
	(*t)->left = snode->left;
	(*t)->right = snode->right;
}

tree_node find_min(tree_node t)
{
	if(t == NULL){
		printf("the tree is empty!\n");
		return NULL;
	}
	while(t->left)
		t = t->left;
	return t;
}
void dispose_tree(tree t)
{
	if(t == NULL)
		printf("dispose:end the subtree!\n");
	else{
		dispose_tree(t->left);
		dispose_tree(t->right);
		printf("free %d\n",t->element);
		free(t);
	}
}
void print_tree(tree t, char * descript)
{
	if(t == NULL)
		//printf("\n%s:end the subtree!\n",descript);
		return;
	else{
		//printf("(%s,%d) ",descript,t->element);
		printf("(");
		print_tree(t->left,"left");
		printf(" %d ",t->element);
		print_tree(t->right,"right");
		printf(")");
	}
}
void print_tree_node(void *vt,void *arg)
{
	tree pt;
	if(vt==NULL)
		return;
	pt=(tree)vt;
	printf("%d ",pt->element);
}

void print_tree_level(tree t)
{
	tree pt;
	if(t==NULL)
		return;
	printf("%d ",t->element);

	if(t->left)/*must check the node or it will break the reccusive*/
		en_queue(que,(void *)(t->left));
	if(t->right)
		en_queue(que,(void *)(t->right));

	printf("the queue:");
	apply_queue(que,print_tree_node,(void *)0);
	printf("\n");

	pt=(tree)de_queue(que);
	print_tree_level(pt);
}

int max(int a,int b)
{
	return a>b?a:b;
}

int find_max_dist(tree t,int *global_dist)
{
	int local_dist,left_depth,right_depth,depth;
	if(t==NULL)
		return 0;
	printf("t:%d,global_dist:%d\n",t->element,*global_dist);

	/*left_depth=find_max_dist(t->left,global_dist)+1;
	right_depth=find_max_dist(t->right,global_dist)+1;*/
	left_depth=find_max_dist(t->left,global_dist);
	right_depth=find_max_dist(t->right,global_dist);
	printf("the left depth:%d ,right depth:%d\n",left_depth,right_depth);

	//local_dist=left_depth+right_depth-1;//the root node is caculate twice.
	local_dist=left_depth+right_depth+1;
	if(*global_dist<local_dist){
		printf("update the global:%d, local:%d\n",*global_dist,local_dist);
		*global_dist=local_dist;
	}
	printf("\n");
	depth=max(left_depth,right_depth)+1;
	return depth;
}
/*to do list:how to hidden the depth in the argument instead of keeping by return value.*/
int find_max_dist2(tree t,int *depth,int global_dist)
{
	int local_dist,left_depth,right_depth;
	left_depth=right_depth=*depth;
	if(t==NULL){
		*depth=0;
		return 0;
	}
	printf("t:%d,depth:%d\n",t->element,*depth);

	find_max_dist2(t->left,&left_depth,global_dist);
	find_max_dist2(t->right,&right_depth,global_dist);
	printf("the left depth:%d ,right depth:%d\n",left_depth,right_depth);

	//local_dist=left_depth+right_depth-1;//the root node is caculate twice.
	local_dist=left_depth+right_depth+1;
	if(global_dist<local_dist){
		//printf("update the global:%d, local:%d\n",*global_dist,local_dist);
		global_dist=local_dist;
	}
	printf("\n");
	*depth=max(left_depth,right_depth)+1;
	return global_dist;
}

void rebuild(char *preorder,char *inorder,int left,int right,tree *root)
{
	tree new_node;
	int i;
	if(preorder==NULL || inorder==NULL)
		return;

	new_node=malloc(sizeof(struct tree_node));
	if(new_node==NULL){
		fprintf(stderr,"out of space for tree node!\n");
		return;
	}
	new_node->element=*preorder;
	new_node->left=new_node->right=NULL;

	*root=new_node;

	if(right-left<=0)
		return;
	
	for(i=left; i<=right; i++)
		if(*preorder==inorder[i])
			break;

	if(i-left>0)
		rebuild(preorder+1,inorder,left,i-1,&(new_node->left));
	if(right-i>0)
		rebuild(preorder+(i-left)+1,inorder,i+1,right,&(new_node->right));
}

int main()
{
	tree t = NULL;
	int c,max_dist,depth;
	max_dist=depth=0;
	/*while((c = getchar()) != EOF){
		if(c != '\n'){
			//t = insert_tree1(t,c-'0');
			//insert_tree2(&t,c-'0');
			insert_tree3(&t,c-'0');
		}
		else
			print_tree(t,"root");
	}*/
	insert_tree3(&t,3);
	insert_tree3(&t,1);
	insert_tree3(&t,2);
	insert_tree3(&t,4);
	insert_tree3(&t,6);
	insert_tree3(&t,5);
	insert_tree3(&t,9);
	insert_tree3(&t,7);
	print_tree(t,"root");

	//printf("\n----star to delete----\n");
	//t = delete_tree1(t,6);
	//delete_tree2(&t,2);
	//delete_tree2(&t,1);
	//delete_tree2(&t,6);
	//print_tree(t,"root");

	printf("\n----star to print  the tree by level order----\n");
	que=create_queue();
	print_tree_level(t);

	printf("\n----star to caculate the max distance of the tree----\n");
	find_max_dist(t,&max_dist);
	printf("the max distance:%d\n",max_dist);
	//printf("max dist2:",find_max_dist2(t,&depth,0));

	printf("\n----star to rebuild the tree throught preorder and inorder----\n");
	char preorder[6]={'a','b','d','c','e','f'};
	char inorder[6]={'d','b','a','e','c','f'};
	tree proot=NULL;
	
	rebuild(preorder,inorder,0,5,&proot);
//	print_tree_level(proot);
	print_tree(proot,"root");

	dispose_queue(que);
//	printf("\n----star to free the tree----\n");
//	dispose_tree(t);
	return 0;
}
