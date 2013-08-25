#include<stdio.h>
#include<malloc.h>
#include "queue.h"

int is_empty(queue q)
{
	return q->front->next == NULL;
}

queue create_queue(void)
{
	queue q = (queue)malloc(sizeof(struct queue));
	if(q == NULL){
		printf("malloc queue error:out of space!\n");
		return NULL;
	}
	
	q->front = malloc(sizeof(struct node));
	if(q->front == NULL){
		free(q);
		printf("malloc list error:out of space!");
		return NULL;
	}
	q->front->element = 'T';
	q->front->next = NULL;
	
	q->rear = q->front;//not q->list->next
}

int dispose_queue(queue q)
{
	while(q->front->next)
		 de_queue(q);

	free(q->front);
	free(q);

	return 1;
}

int en_queue(queue q, char x)
{
	node new_node = malloc(sizeof(struct node));
	new_node->element = x;
	new_node->next = NULL;
	
	q->rear->next = new_node;
	q->rear = new_node;
	
	return 1;
}

char de_queue(queue q)
{
	if(q->front->next == NULL){
		printf("the queue is empty!\n");
		return '0';
	}
	node temp_node;
	char element;

	temp_node = q->front->next;

	element = temp_node->element;
	printf("de_queue:%c\n",element);
	
	q->front->next = temp_node->next;
	free(temp_node);
	
	return element;
}

void print_queue(queue q)
{
	node temp = q->front;
	while(temp){
		printf("%c ",temp->element);
		temp = temp->next;
	}
	printf("\n");
}

int main()
{
	queue q;
	q = create_queue();
	//print_queue(q);
	//en_queue(q,'a');
	//print_queue(q);

	char c;
	while((c = getchar()) != EOF ){
		if(c == '\n')
			print_queue(q);
		else
			en_queue(q,c);
	}
	
	de_queue(q);
	print_queue(q);

	de_queue(q);
	print_queue(q);

	de_queue(q);
	print_queue(q);
	/*free the memory*/
	dispose_queue(q);
	return 0;
}
