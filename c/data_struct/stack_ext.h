#ifndef _NODE_H
#define _NODE_H
typedef struct node node;
struct node{
        void *element;
        struct node *next;
};
#endif

#ifndef __STACK_EXT__
typedef struct stack stack;

struct stack{
	node *top;
};
stack *create_stack();
int is_empty(stack *);
void push(stack *,void *data);
void *pop(stack *);
void apply_stack(stack * stck,void (*fn)(void *data,void *arg),void *arg);
#endif
