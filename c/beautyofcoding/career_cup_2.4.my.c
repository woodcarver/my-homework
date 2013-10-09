#include<stdio.h>
#include<string.h>
#include "../data_struct/list_ext.c"

node *lst;

void num_add(node *lst,int separate)
{
	printf("separate:%d\n",separate);
	node *p1,*p2,*psum,*temp;
	int carry,sp,sum;
	if(lst==NULL || separate<1){
		printf("illegal lst or separate!\n");
		return;
	}

	psum=p1=p2=lst;
	sp=separate;
	carry=0;

	while(p2&&separate--)
		p2=p2->next;
	printf("the p2 start:%c\n",p2->element);

	for(;sp && p2; p1=p1->next,p2=p2->next){
		sum=p1->element+p2->element+carry-2*'0';
		carry=sum/10;
		psum->element=sum%10+'0';
		printf("p1:%d p2:%d sum:%d\n",p1->element,p2->element,sum);
		psum=psum->next;
		sp--;
	}
	
	while(sp){
		sum=p1->element+carry-'0';
		carry=sum/10;
		psum->element=sum%10+'0';
		printf("p1:%d sum:%d\n",p1->element,sum);
		psum=psum->next;
		sp--;
	}

	while(p2){
		sum=p2->element+carry-'0';
		carry=sum/10;
		psum->element=sum%10+'0';
		printf("p2:%d sum:%d\n",p2->element,sum);
		psum=psum->next;
		p2=p2->next;
	}

	if(carry){
		psum->element=carry+'0';
		psum=psum->next;
	}

	/*delete the useless tail*/
	printf("delete the tail from:%c\n",psum->element);
	while(psum->next){
		temp=psum->next;
		psum->next=temp->next;
		free(temp);
	}
	free(psum);
	psum=NULL;
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
	if(argc!=3){
		printf("the argument is not correct!\n");
		exit(1);
	}
	lst=NULL;
	generic_data(argv[2]);
	print_list(lst);

	num_add(lst,atoi(argv[1]));
	print_list(lst);
	return 0;
}
