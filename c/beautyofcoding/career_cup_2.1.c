#include<stdio.h>
#include<string.h>
#include "../data_struct/list_ext.c"

node *lst;

void unique_list(node *lst)
{
	node *p,*q,*temp;
	if(lst==NULL)
		return;
	for(p=lst; p!=NULL; p=p->next)
		for(q=p; q->next!=NULL;)
			if(p->element==q->next->element){
				temp=q->next;
				q->next=temp->next;
				free(temp);
			}
			else
				q=q->next;
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
	lst=NULL;
	generic_data(argv[1]);
	print_list(lst);

	unique_list(lst);
	print_list(lst);
	return 0;
}
