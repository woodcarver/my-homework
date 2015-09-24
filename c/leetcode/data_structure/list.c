#include<stdio.h>
#include<stdlib.h>

struct ListNode {
         int val;
         struct ListNode *next;
};

struct ListNode* listCreate(int *arr,int len){
    struct ListNode head,*new,*p;
    int i;
    head.val=0;
    head.next=NULL;
    p=&head;
    for(i=0;i<len;i++){
         new=malloc(sizeof(struct ListNode));
         new->val=arr[i];
         p->next=new;
         p=p->next;
    }
    return head.next; 
}
void listPrint(struct ListNode *head){
    while(head!=NULL){
        printf("%d,",head->val);
        head=head->next;
    }
    printf("\n");
}
