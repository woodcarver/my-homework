#include<stdio.h>

struct test
{
	char * name;
	int value;
};

int main(int argc,char ** argv)
{
	struct test t,t2,*p;
	p=&t;

	if(argc!=2)
		argv[1]="argv1";
		//return -1;
	
	printf("struct size: %d\n",sizeof(struct test));
	p->name=argv[1];
	printf("string size: %d\n",sizeof(argv[1]));
	printf("struct string is: %s\n",p->name);
	t2=t;
	printf("struct t2 string is: %s\n",t2.name);
	t.name="sssssss";
	//argv[1]="argv111111";
	argv[1][0]='b';
	printf("struct t1:%s t2: %s\n",t.name,t2.name);
	t2.name="iiiiiiii";
	printf("struct t1:%s t2: %s\n",t.name,t2.name);
	return 0;
}
