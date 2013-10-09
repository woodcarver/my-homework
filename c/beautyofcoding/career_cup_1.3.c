#include<stdio.h>
#include<string.h>
void unique_str1(char *str)
{
	char *p,*q,*k;
	if(str==NULL || *str=='\0')
		return;

	for(p=str; *p!='\0'; p++)
		for(q=p+1; *q!='\0'; q++){
			if(*p!=*q)
				continue;
			k=q;
			while(*(k+1)!='\0'){
				*k=*(k+1);
				k++;
			}
			*k='\0';
			q--;/*restor the q pos after changing it's value.*/
		}
}
void unique_str2(char *str)
{
	int i,j,tail;
	if(str==NULL || *str=='\0')
		return;

	for(i=1,tail=1; i<strlen(str); i++){
		for(j=0; j<tail; j++)
			if(str[i]==str[j])
				break;
		if(j==tail){
			str[tail]=str[i];
			tail++;
		}	
	}
	str[tail]='\0';
}


int main(int argc,char *argv[])
{
	char str1[100],str2[100];

	strcpy(str1,argv[1]);
	strcpy(str2,argv[1]);

	unique_str1(str1);
	printf("str1:%s\n",str1);
	unique_str2(str2);
	printf("str2:%s\n",str2);
	
	return 0;
}
