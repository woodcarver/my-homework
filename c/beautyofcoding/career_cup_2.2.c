#include<stdio.h>
#include<string.h>
#include "../data_struct/list_ext.c"

node *lst;

node *find_nth(node *lst,int n)
{
	node *p,*q;
	int i;
	if(lst==NULL || n<1)
		return NULL;
	
	for(p=lst,i=0; i<=n; i++)
		if(p==NULL)
			return NULL;
		else
			p=p->next;
	for(q=lst; p; p=p->next)
		q=q->next;
	return q;
}

void generic_data(char *str)
{
	int i;
	for(i=0; i<strlen(str); i++){
		printf("insert the char:%c\n",str[i]);
		list_insert(&lst,str[i]);
	}
}
int main(int argc,char *argv[])
{
	node *ans;
	lst=NULL;
	generic_data(argv[2]);
	print_list(lst);

	ans=find_nth(lst,atoi(argv[1]));
	printf("the nth element is:%c\n",ans->element);
	return 0;
}
