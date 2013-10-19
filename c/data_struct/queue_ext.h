#ifndef _NODE_H
#define _NODE_H
typedef struct node node;
struct node{
	void *element;
	struct node *next;
};
#endif

#ifndef _QUEUE_H
typedef struct queue *queue;
//typedef struct node *q_list;

int queue_is_empty(queue q);
queue create_queue(void);
int dispose_queue(queue q);
int en_queue(queue q, void *x);
void *de_queue(queue q);


struct queue{
	node *front;
	node *rear;
//	q_list list;	
};
#endif

