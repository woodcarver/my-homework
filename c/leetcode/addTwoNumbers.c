#include<stdio.h>
#include<stdlib.h>

struct ListNode{
    int val;
    struct ListNode *next;
}

struct ListNode* addTwoNumbers(struct ListsNode* l1,struct ListNode *l2)
{
}

void print_list(struct ListNode* l)
{
    while(l!=NULL)
        printf("%d ,\n",l->val);
}
int main(int argc, char ** argv)
{
    struct ListNode* list;
    list=addTwoNumbers();
    print_list(list);
}
