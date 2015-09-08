#include<stdio.h>
#include "./data_structure/list.c"

struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2) {
    struct ListNode *l1_prev;
    struct ListNode head={0,l1};

    if(l1==NULL){
        return l2;
    }
    else if(l2==NULL){
        return l1;
    }

    l1_prev=&head;
    while(l1!=NULL && l2!=NULL){
        if(l1->val>l2->val){
            l1_prev->next=l2;
            l2=l2->next;
            l1_prev->next->next=l1;
            l1_prev=l1_prev->next;
        }
        else{
            l1_prev=l1;
            l1=l1->next;
        }
    }
    if(l1==NULL){
        l1_prev->next=l2;
    }
    return head.next;
}

int main()
{
    struct ListNode *l1,*l2,*l3;
    int arr1[3]={1,3,5};
    int arr2[4]={2,10,11,17};
    l1=listCreate(arr1,3);
    l2=listCreate(arr2,4);
    l3=mergeTwoLists(l1,l2);
    listPrint(l3);
    printf("\n");

    l3=mergeTwoLists(NULL,NULL);
    listPrint(l3);
    printf("\n");

    l1=listCreate(arr1,3);
    l3=mergeTwoLists(l1,NULL);
    listPrint(l3);
    printf("\n");

    l2=listCreate(arr2,4);
    l3=mergeTwoLists(NULL,l2);
    listPrint(l3);
    printf("\n");

    int arr3[1]={12};
    int arr4[4]={2,10,11,17};
    l1=listCreate(arr3,1);
    l2=listCreate(arr4,4);
    l3=mergeTwoLists(l1,l2);
    listPrint(l3);
    printf("\n");

    int arr5[2]={12,100};
    int arr6[4]={2,10,11,17};
    l1=listCreate(arr5,2);
    l2=listCreate(arr6,4);
    l3=mergeTwoLists(l1,l2);
    listPrint(l3);
    printf("\n");
    return 0;
}
