#include<stdio.h>
#include<string.h>

void reverse_string(char *str)
{
	int head,tail;
	char temp;
	if(str==NULL ||*str=='\0')
		return;
	head=0;
	tail=strlen(str)-1;

	while(head<tail){
		temp=str[head];
		str[head++]=str[tail];
		str[tail--]=temp;
	}
}

int main(int argc,char *argv[])
{
	char *str;
	//str=argv[1];
	//reverse_string(str);
	str="abcde";
	printf("before:%s\n",str);
	reverse_string(str);
	printf("the result:%s\n",str);
}
