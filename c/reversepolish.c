#include<stdio.h>
#include<stdlib.h>
#include "stack.c"
#include "getop.c"

#define MAXOP 100 //max size of operand or operator
#define NUMBER '0' //signal that a number was found

int getop(char []);
void push(double);
double pop(void);

int main()
{
	int type;
	double op2;
	char s[MAXOP];

	while((type = getop(s)) != EOF)
	{
		printf("tyle: %s\n",s);
		switch(type){
			case NUMBER:
				push(atof(s));
				break;
			case '+':
				push(pop()+pop());
				break;
			case '*':
				push(pop()*pop());
				break;
			case '-':
				op2 = pop();//因为減法和除法都没交换律
				push(pop()-op2);
				break;
			case '/':
				op2 = pop();
				if(op2 != 0.0)
					push(pop()/op2);
				else
					printf("error:zero divisor\n");
			case '\n':
				printf("\t.8g\n",pop());
				break;
			default:
				printf("error:unknow command %s\n",s);
				break;
		}
	}
	return 0;
}

