#include<stdio.h>

#define CHARACT_NUM 58

int charact_count[CHARACT_NUM];
int buck(char ch)
{
	return ch-'A';
}
int is_unique1(char *str)
{
	int pos;
	if(str==NULL)
		return -1;
	while(*str!='\0'){
		pos=buck(*str);
		printf("version1 pos:%d\n",pos);
		if(charact_count[pos]<1)
			charact_count[pos]++;
		else
			return 0;
		str++;
	}
	return 1;
}

int is_unique2(char *str)
{
	int pos;
	long long checker=0;
	if(str==NULL)
		return -1;
	while(*str!='\0'){
		printf("version2 subtract:%d\n",(*str)-'A');
		pos=1<<((*str)-'A');
		printf("version2 pos:%d\n",pos);
		if(checker&pos)
			return 0;
		else
			checker|=pos;
		str++;
	}
	return 1;
}

int main(int argc,char *argv[])
{
	printf("version1 result:%d\n\n",is_unique1(argv[1]));
	printf("version2 result:%d\n",is_unique2(argv[1]));
	return 0;
}
