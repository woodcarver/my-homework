#define BUFSIZE 100

char buf[BUFSIZE];//buffer for ungetch
int bufp=0;//next free position in buf

//get a possibly pushed-back character
int getch(void)
{
	printf("buf:%s\n",buf);
	return (bufp>0)?buf[--bufp]:getchar();
}
//push character back on input
void ungetch(int c)
{
	printf("buf:%s\n",buf);
	if(bufp>=BUFSIZE)
		printf("ungetch:too many characters\n");
	else
		buf[bufp++] = c;
}
