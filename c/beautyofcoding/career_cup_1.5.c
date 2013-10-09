#include<stdio.h>
#include<string.h>

void replace_space(char *str,int n)
{
	int i,space_count,new_length;
	printf("n:%d str:%s\n",n,str);
	if(str==NULL)
		return;
	space_count=0;
	for(i=0; i<n; i++)
		if(str[i]==' ')
			space_count++;
	new_length=n+space_count*2;
	str[new_length]='\0';
	printf("old_len:%d new_length:%d space_count:%d\n",n,new_length,space_count);
	for(i=n-1; i>=0; i--){
		printf("new_char:%c\n",str[i]);
		if(str[i]==' '){
			str[--new_length]='0';
			str[--new_length]='2';
			str[--new_length]='%';
		}
		else
			str[--new_length]=str[i];
	}
}

int main(int argc,char *argv[])
{
	char *str;
	str=argv[1];
	replace_space(str,strlen(str));
	printf("final:%s\n",str);
}

