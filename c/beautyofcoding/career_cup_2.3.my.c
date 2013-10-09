#include<stdio.h>
#include<string.h>
#include "../data_struct/list_ext.c"

node *lst;

void delete_middle(node *lst)
{
	int n;
	node *p,*mid,*pre_mid;
	if(lst==NULL)
		return;
	n=1;
	p=mid=pre_mid=lst;
	for(; p; p=p->next){
		/*n&1 n is not a odd number*/
		if(n!=1 && n&1){
			pre_mid=mid;
			mid=mid->next;
		}
		n++;
	}
	printf("delete the mid:%c\n",mid->element);
	pre_mid->next=mid->next;
	free(mid);
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
	if(argc!=2)
		exit(1);

	lst=NULL;
	generic_data(argv[1]);
	print_list(lst);

	delete_middle(lst);
	print_list(lst);
	return 0;
}
