#include<stdio.h>
int htoi(char []);
#define MAX_BIT 10
main()
{
	int i,c;
	char num[MAX_BIT];
	for(i=0;(c=getchar()) != EOF && i<MAX_BIT; ++i)
		num[i] = c;
	num[i]='\0';
	printf("result:%d\n",htoi(num));
}
int htoi(char s[])
{
int i,n = 0;
if(s[0] != '0' || (s[1] != 'x' && s[1] != 'X')){
	printf("the format of the char array is not start with 0x or 0X!\n");
	return 0;
}
for(i=2;; ++i){
	if(s[i] >= '0' && s[i] <= '9')
		n = 16*n+(s[i]-'0');
	else if(s[i] >= 'A' && s[i] <= 'F')
		n = 16*n+(s[i]-'A'+11);
	else if(s[i] >= 'a' && s[i] <= 'f')
		n= 16*n+(s[i]-'a'+10);
	else
		break;
	printf("current:%c %d\n",s[i],n);
}
return n;
}
