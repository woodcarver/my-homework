#include<stdio.h>
#include<stdlib.h>

#include "stack.h"
#include "queue.h"
#include "getop.c"

#define MAXOP 100
#define NUMBER '0'
#define OP '1'
#define MAXEXP 1000

int compare_op(char,char);

void main()
{
	int type;
	double element;
	char input[MAXOP];
	struct stack operator_stack,*stack_op;
	struct queue queue_output,*output;

	operator_stack.top = 0;
	operator_stack.val[0] = '\0';

	queue_output.header = 0;
	queue_output.tail = 0;
	queue_output.val[0] = '\0';

	stack_op = &operator_stack;
	output = &queue_output;

	while((type = getop(input)) != EOF ){
		switch(type){
			case NUMBER:
				enQueue(output,atof(input));
				break;
			case '(':
				push(stack_op,'(');
				break;
			case ')':
				while('(' != (element = pop(stack_op)))
					enQueue(output,element);
				break;
			case OP:
				if(compare_op(input[0],stack_op->val[stack_op->top]))
					push(stack_op,input[0]);
				else
					enQueue(output,pop(stack_op));
				break;
			case '\n':
			        while(stack_op->top>0)
 			                 enQueue(output,pop(stack_op));
 			        print_queue(queue_output);
				break;
			default:
				printf("unkown operator!\n");
				break;
		}
	}
}

int compare_op(char op1,char op2)
{
	printf("op1 %c\n",op1);
	printf("op2 %c\n",op2);
	char operators1[] = "*/";
	char operators2[] = "+-";
	int i,rop1,rop2;
	op1 = op2 = 0;

	for(i = 0; operators1[i] != '\0'; ++i){
		if(op1 == operators1[i])
			rop1 = 1;
			
		if(op2 == operators1[i])
			rop2 = 1;
	}
	if(!rop1 || !rop2)
	        for(i = 0; operators2[i] != '\0'; ++i){
        	        if(op1 == operators2[i])
                	        rop1 = 2;
               		 if(op2 == operators2[i])
                       		 rop2 = 2;
        	}

	if(rop1 > rop2)
		return 1;
	else if(rop1 = rop2)
		return 0;
	else if(rop1 < rop2)
		return -1;
}
