#include<stdio.h>
#include<malloc.h>
#include "tree.h"

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
		printf("\n%s:end the subtree!\n",descript);
	else{
		printf("(%s,%d) ",descript,t->element);
		print_tree(t->left,"left");
		print_tree(t->right,"right");
	}
}
int main()
{
	tree t = NULL;
	int c;
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

	printf("\n----star to delete----\n");
	//t = delete_tree1(t,6);
	//delete_tree2(&t,2);
	//delete_tree2(&t,1);
	delete_tree2(&t,6);
	print_tree(t,"root");

	printf("\n----star to free the tree----\n");
	dispose_tree(t);
	return 0;
}
