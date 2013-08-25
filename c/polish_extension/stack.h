//#include<stdio.h>
#define MAXVAL 100

//int sp = 0;
//double val[MAXVAL];

struct stack{
	int top;
	int count;
	int val[MAXVAL+1];
//	struct stack stack_r;
//	void (*ppush)(double f,struct stack);
//	int (*ppop)(struct stack);
//	int (*ptop)(struct stack);
};

void print_stack(struct stack);

void push(struct stack *stack_r,int f)
{
	printf("push stack\n");
	if(stack_r->top < MAXVAL){
		printf("push %d into stack\n",f);
		stack_r->val[stack_r->top++] = f;
		stack_r->val[stack_r->top] = '\0';
	}
	else
		printf("error:stack full,can't push %d\n",f);
	//print_stack(*stack_r);
}

int pop(struct stack *stack_r)
{
	printf("pop stack\n");
	if(stack_r->top > 0)
		return stack_r->val[--stack_r->top];
	else{
		printf("error:stack empty\n");
		return 0.0;
	}	
}
void print_stack(struct stack stack_r)
{
	int i;
	printf("stack:");
	for(i = 0; stack_r.val[i] != '\0' ; ++i)
		printf(" %d",stack_r.val[i]);
	printf("\n");
}
