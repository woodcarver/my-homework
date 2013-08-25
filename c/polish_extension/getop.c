#include<stdio.h>
#include<ctype.h>


#include "getch.h"

#define NUMBER '0'
#define OP '1'

int getch(void);
void ungetch(int);

int getop(char s[])
{
	int i,c;
	while((s[0] = c = getch()) == ' ' || c=='\t')
	s[1] = '\0';
	if(!isdigit(c) && c!='.' && c!='\n')
		return OP;//not a number
	if(c == '\n')
		return '\n';
	i=0;
	if(isdigit(c))//collect integer part
		while(isdigit(s[++i]=c=getch()))
			;
	if(c=='.')//collect fraction part
		while(isdigit(s[++i]=c=getch()))
			;
	s[i]='\0';
	if(c != EOF)
		ungetch(c);
	return NUMBER;
}
