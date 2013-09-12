#ifndef __STACK_EXT__
typedef struct node node;
typedef struct stack stack;

struct stack{
	node *top;
};
struct node{
	void *data;
	node *next;
};
stack *create_stack();
int is_empty(stack *);
void push(stack *,void *data);
void *pop(stack *);
void apply_stack(stack * stck,void (*fn)(void *data,void *arg),void *arg);
#endif
