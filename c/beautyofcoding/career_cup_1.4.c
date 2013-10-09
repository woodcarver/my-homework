#include<stdio.h>
#include<string.h>

#define CHARACT_NUM 58

int charact_count[CHARACT_NUM];
int buck(char ch)
{
	return ch-'A';
}
int is_anagram(char *str1,char *str2)
{
	char *p,*q;
	int pos;
	if(str1==NULL ||str2==NULL)
		return -1;
	if(strlen(str1)!=strlen(str2))
		return 0;

	for(p=str1; *p!='\0'; p++){
		pos=buck(*p);
		//printf("pos:%d\n",pos);
		charact_count[pos]++;
	}

	for(q=str2; *q!='\0'; q++){
		pos=buck(*q);
		if(charact_count[pos]==0)
			return 0;
		charact_count[pos]--;
	}
	return 1;
}

int main(int argc,char *argv[])
{
	printf("result:%d\n",is_anagram(argv[1],argv[2]));
	return 0;
}
