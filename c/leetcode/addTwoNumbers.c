#include<stdio.h>
#include<stdlib.h>

struct ListNode{
    int val;
    struct ListNode *next;
};

struct ListNode *newListNode(int val)
{
    struct ListNode *l=(struct ListNode *)malloc(sizeof(struct ListNode));
    l->val=val;
    l->next=NULL;
    return l;
}
struct ListNode *addTwoNumbers(struct ListNode *l1,struct ListNode *l2)
{
    int overflow,digit;
    overflow=0;
    struct ListNode *output=newListNode(0);
    struct ListNode *pl=output;
    while(l1!=NULL && l2!=NULL){
        digit=l1->val+l2->val+overflow;
        overflow=0;
        if(digit>=10){
            overflow=digit/10;
            digit-=10;
        }
        //list with head node
        pl->next=newListNode(digit);
        pl=pl->next;

        //mv the list
        l1=l1->next;
        l2=l2->next;
    }
    //for the tail of list
    while(l1!=NULL){
        digit=l1->val+overflow;
        overflow=0;
        if(digit>=10){
            overflow=digit/10;
            digit-=10;
        }
        pl->next=newListNode(digit);
        pl=pl->next;

        l1=l1->next;
    }
    while(l2!=NULL){
        digit=l2->val+overflow;
        overflow=0;
        if(digit>=10){
            overflow=digit/10;
            digit-=10;
        }
        pl->next=newListNode(digit);
        pl=pl->next;

        l2=l2->next;
    }
    //for the possible last node if the overflow is not equal zero
    if(overflow>0){
        pl->next=newListNode(overflow);
    }
    //return a list without head node
    pl=output;
    output=output->next;
    free(pl);
    return output;
}

void print_list(struct ListNode* l)
{
    while(l!=NULL){
        printf("%d ,",l->val);
        l=l->next;
    }
    printf("\n");
}
struct ListNode * construct_list(int arr[],int len)
{
    int i;
    struct ListNode* head=newListNode(0);
    struct ListNode* list=head;
    for(i=0;i<len;i++){
        printf("construct:%d,%d\n",i,arr[i]);
        if(list==NULL)
            head=list=newListNode(arr[i]);
        else{
            list->next=newListNode(arr[i]);
            list=list->next;
        }
    }
    list=head;
    head=head->next;
    free(list);
    return head;
}

int main(int argc, char ** argv)
{
    struct ListNode *list,*l1,*l2;
    int arr1[]={5,1};
    int arr2[]={9,2,9};
    l1=construct_list(arr1,2);
    l2=construct_list(arr2,3);
    print_list(l1);
    print_list(l2);

    list=addTwoNumbers(l1,l2);
    print_list(list);
}
