#include<stdio.h>
#include "list.h"

int is_empty(list l)
{
	return l->next == NULL;
}

int is_last(list l, position p)
{
	return p->next == NULL;
}

position find_p(list l, char x)
{
	l = l->next;
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

void insert_node(list l, char x,position p)
{
	if(p == NULL){
		printf("the position is null!\n");
		return;
	}
	else{
		ptr_to_node new_node =  malloc(sizeof(struct node));
		if(new_node == NULL){
			printf("out of space!!");
			return;
		}
		new_node->element = x;

		new_node->next = p->next;
		p->next = new_node;
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
}

void print_list(list l)
{
	l = l->next;
	while(l){
		printf("%c ",l->element);
		l = l->next;
	}
	printf("\n");
}

int main()
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
	print_list(l);

	delete_node(l,'f');	
	delete_node(l,'c');	
//	insert_node(l,'d',NULL);
	print_list(l);

	delete_list(l);
	print_list(l);
	return 0;	
}
