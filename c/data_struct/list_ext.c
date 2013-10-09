#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include "list_ext.h"

int is_empty(list l)
{
	return l->next == NULL;
}

int is_last(list l, position p)
{
	return p->next == NULL;
}

node *find_p(list l, char x)
{
	while(l != NULL && l->element != x)
		l = l->next;
	return l;
}

void delete_node(list l, char x)
{
	//position previous;
	position temp;

	while(l->next && l->next->element != x)
		l = l->next;
	printf("delete prevoius position: %c\n",l->element);
	/*previous = l;
	
	if(previous->next != NULL){
		previous->next = previous->next->next;
		free(l->next);
	}*/
	temp = l->next;
	if(temp != NULL){
                l->next = temp->next;
                free(temp);
        }
}

void list_insert(node **l, char x)
{
	if(l == NULL){
		printf("the list is illege!\n");
		return;
	}
	else{
		node *new_node =  malloc(sizeof(struct node));
		if(new_node == NULL){
			printf("out of space!!");
			return;
		}
		new_node->element = x;
		new_node->next = *l;
		*l = new_node;
	}
}

void delete_list(list l)
{
	ptr_to_node temp;
	while(l->next){
		temp = l->next;
		l->next = temp->next;
		free(temp);
	}
	free(l);
}

void print_list(list l)
{
	if(l==NULL)
		printf("this is a empty list!\n");

	while(l){
		printf("%c ",l->element);
		l = l->next;
	}
	printf("\n");
}

void list_delete_without_previous(list p)
{
	list q;
	if(p==NULL){
		printf("the given pointer is illegal!\n");
		return;
	}
	q=p->next;
	if(q==NULL){
		free(p);
		p=NULL;
	}
	else{
		p->element=q->element;
		p->next=q->next;
		free(q);
	}
}
void list_reverse(list l)
{
	list prev,p;
	if(l==NULL){
		printf("the given pointer is illegal!\n");
		return;
	}

	prev=l->next;
	p=prev->next;

	for(;p!=NULL;p=prev->next){
		/*soft delete p and excute front insert*/
		prev->next=p->next;
		p->next=l->next;
		l->next=p;
	}
}

/*int main()
{
	struct node header;
	ptr_to_node specific_node;
	list l = &header;
	
	l->element = 'h';
	l->next = NULL;

	insert_node(l,'a', l);
	insert_node(l,'b',l);
	specific_node = find_p(l,'a');
	insert_node(l,'c',specific_node);
	insert_node(l,'d',specific_node);
	insert_node(l,'e', l);
	insert_node(l,'f',l);
	print_list(l);

	delete_node(l,'f');	
	delete_node(l,'c');	
//	insert_node(l,'d',NULL);
	print_list(l);

	delete_list(l);
	print_list(l);

	sleep(1);
	specific_node = find_p(l,'a');
	list_delete_without_previous(specific_node);
	print_list(l);

	sleep(1);
	list_reverse(l);
	print_list(l);

	delete_list(l);
	return 0;	
}*/
