#include<stdio.h>
#include<stdlib.h>
struct ListNode {
         int val;
         struct ListNode *next;
};

struct ListNode* removeNthFromEnd(struct ListNode* head, int n){
    struct ListNode *p_front,*p_target,*p_end;
    int i;
    
    p_front=p_target=p_end=head;
    for(i=0; (p_end!=NULL) && i<n; i++)
        p_end=p_end->next;

    if(n<1 || i!=n){
        //printf("%d,%d\n",i,n);
        return head;
    }
    if(p_end==NULL){
        printf("front and target are the same\n");
        p_front=p_target->next;
        free(p_target);
        return p_front;
    }

    p_target=p_target->next;
    p_end=p_end->next;
    while(p_end!=NULL){
        p_front=p_front->next;
        p_target=p_target->next;
        p_end=p_end->next;
    }
    p_front->next=p_target->next;
    free(p_target);
    return head;
}
struct ListNode* listCreate(int len){
    struct ListNode head,*new,*p;
    int i;
    head.val=0;
    head.next=NULL;
    p=&head;
    for(i=0;i<len;i++){
         new=malloc(sizeof(struct ListNode));
         new->val=i;
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
int main()
{
    //for simple I don't free those lists 
    struct ListNode *input,*output;
    printf("#test1\n");
    input=listCreate(0);
    listPrint(input);
    output=removeNthFromEnd(input,2);
    listPrint(output);

    printf("#test2\n");
    input=listCreate(1);
    listPrint(input);
    output=removeNthFromEnd(input,2);
    listPrint(output);

    printf("#test3\n");
    input=listCreate(2);
    listPrint(input);
    output=removeNthFromEnd(input,2);
    listPrint(output);

    printf("#test4\n");
    input=listCreate(10);
    listPrint(input);
    output=removeNthFromEnd(input,2);
    listPrint(output);
    return 0;
}
