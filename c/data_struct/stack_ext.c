#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include "stack_ext.h"

stack *create_stack()
{
	stack *stck;
	stck=(stack *)malloc(sizeof(struct stack));
	if(stck==NULL)
		return  NULL;
	stck->top=NULL;
	return stck;
}
int is_empty(stack *stck)
{
	if(stck==NULL)
		return 0;
	return stck->top==NULL;
}
int stack_is_empty(stack *stck)
{
	return is_empty(stck);
}
void push(stack *stck,void *data)
{
	node *new_node;

	if(stck==NULL){
		printf("the stack is illegal!\n");
		return;
	}
	new_node=malloc(sizeof(struct node));
	if(new_node==NULL){
		printf("out of memory space!\n");
		return;
	}
	new_node->element=data;
	
	new_node->next=stck->top;
	stck->top=new_node;
}
void *pop(stack *stck)
{
	node *ntemp;
	void *data;
	if(stck==NULL){
                printf("the stack is illegal!\n");
                return NULL;
        }

	if(stck->top==NULL){
		printf("the stack is empty!\n");
		return NULL;
	}
	else{
		data=stck->top->element;
		ntemp=stck->top;
		stck->top=ntemp->next;
		free(ntemp);
		return data;
	}

}
void apply_stack(stack *stck,void (*fn)(void *data,void *arg),void *arg)
{
	node *stck_node;

	if(stck==NULL){
                printf("the stack is illegal!\n");
                return;
        }

	for(stck_node=stck->top; stck_node!=NULL; stck_node=stck_node->next)
		(*fn)(stck_node->element,arg);
}

void print_string_data(void *data,void *arg)
{
	char *p_string;
	p_string=(char *)data;
	printf("%s,",p_string);
}

/*int main()
{
	stack *stck;
	char *p_string;

	stck=create_stack();
	push(stck,(void *)"ab");
	push(stck,(void *)"c");
	push(stck,(void *)"df");
	apply_stack(stck,print_string_data,(void *)0);

	p_string=pop(stck);
	apply_stack(stck,print_string_data,(void *)0);
	printf("\n");
	return 0;
}*/
