#include<stdio.h>
#include "stack.h"
#include "queue.h"

void main()
{
	struct stack stack1,*p_stack;
	stack1.sp = 0;
	stack1.val[0] = '\0';
	p_stack = &stack1;

	push(1,p_stack);
	push(2,p_stack);
	print_stack(stack1);
	//stack1.ppush(2);
	
	struct queue queue1,*p_queue;
	queue1.header = queue1.tail = 0;
	queue1.val[0]='\0';
	p_queue = &queue1;

	enQueue(p_queue,1);
	print_queue(queue1);
}
