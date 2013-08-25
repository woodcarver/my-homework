#include<stdio.h>

int main()
{
	int a[3]={1,2,3};
	int *p=a;
	char *s="fdsafd";
static char *name[] = {
"Illegal month",
"January", "February", "March",
"April", "May", "June",
"July", "August", "September",
"October", "November", "December"
};
static char name2[][100] = {
"Illegal month",
"January", "February", "March",
"April", "May", "June",
"July", "August", "September",
"October", "November", "December"
};


	printf("name0:%s\n",name[0]);
	printf("name00:%d\n",name[0][0]);
	printf("name20:%s\n",name2[0]);
	printf("name201:%d\n",name2[0][1]);
	printf("%s\n",s);
	printf("%d\n",a);
	printf("%d\n",p);
	printf("%d\n",a+3-p);
	return 0;
}
