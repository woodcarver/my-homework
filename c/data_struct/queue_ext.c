#include<stdio.h>
#include<malloc.h>
#include "queue_ext.h"

int queue_is_empty(queue q)
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
	q->front->element = NULL;
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

int en_queue(queue q, void *element)
{
	node *new_node = malloc(sizeof(struct node));
	new_node->element = element;
	new_node->next = NULL;
	
	q->rear->next = new_node;
	q->rear = new_node;
	
	return 1;
}

void *de_queue(queue q)
{
	if(q->front->next == NULL){
		printf("the queue is empty!\n");
		return NULL;
	}
	node *temp_node;
	void *element;

	temp_node = q->front->next;

	element = temp_node->element;
	//printf("de_queue:%c\n",element);
	
	q->front->next = temp_node->next;
	free(temp_node);

	if(q->front->next==NULL)/*check the last element,it must update rear*/
		q->rear=q->front;
	
	return element;
}

void apply_queue(queue q,void (*fn)(void *,void *),void *arg)
{
	node *listp;
	for(listp=q->front->next; listp!=NULL; listp=listp->next)
		(*fn)(listp->element,arg);
}

/*void print_element(void *element,void *arg)
{
	
}
*/
/*int main()
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
	//free the memory
	dispose_queue(q);
	return 0;
}*/
