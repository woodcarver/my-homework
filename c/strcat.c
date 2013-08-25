#include<stdio.h>
void strrcat(char *s, char *t, int n)
{
	int i=0;
	while(*s++ != '\0')
		;
	--s;//put the copy start pinter at the right position
	while((*s++ = *t++) && ++i < n)
		;
	if(i<=n)
		*s='\0';
}
int main()
{
	char a[100] = "this is a:";
	char b[3] = {'a','c','\0'};//try to remove the \o emlment,it can be right. The compiler optimize it? 
	char *ss = b;
	printf("test:%s\n",ss); 
	char *s = a;
	char *t = "this is b:";

	printf("before cat:%s\n",s);
	strrcat(s,t,6);
	printf("after cat:%s\n",s);
}
