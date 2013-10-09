#include<stdio.h>
#include<math.h>
#include "../data_struct/tree_ext.c"

int is_balanced(tree *t,int *dis)
{
	int left_d,right_d;
	int distince=*dis+1;

	printf("dis:%d\n",*dis);
	if(t==NULL)
		return 1;

	(*dis)++;
	if(!is_balanced(t->left,dis))
		return 0;
	left_d=*dis;

	*dis=distince;
	if(!is_balanced(t->right,dis))
		return 0;
	right_d=*dis;

	*dis=left_d>right_d ? left_d:right_d;

	printf("dis:%d left_d:%d right_d:%d\n",*dis,left_d,right_d);
	if(abs(left_d-right_d)<=1)
		return 1;
	else
		return 0;	
}

int main()
{
	int dis,c;
	dis=0;
	tree *t=NULL;
        while((c = getchar()) != EOF){
                if(c != '\n'){
			printf("insert:%c\n",c);
                        //t = insert_tree1(t,c-'0');
                        //insert_tree2(&t,c-'0');
                        insert_tree3(&t,c-'0');
                }
                else
                        print_tree(t,"root");
        }

	printf("\nthe result:%d\n",is_balanced(t,&dis));
	return 0;
}
