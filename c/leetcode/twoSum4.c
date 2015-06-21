#include<stdio.h>
#include<stdlib.h>

#define NUMS_SIZE 3 

typedef struct node{
    int pos;
    int val;
}Node;

int cmp(const void *a,const void *b){
    return ((Node *)a)->val-((Node *)b)->val;
}
int* twoSum(int* nums, int numsSize, int target) {
    int *out=malloc(2*sizeof(int));
    int i,j,left,right,middle,rest,rest_index;
    Node nodes[numsSize];
    printf("start to construct arr\n");
    for(i=0; i<numsSize; i++){
        nodes[i].pos=i;
        nodes[i].val=nums[i];
    }
    printf("start to sort arr\n");
    qsort(&nodes, numsSize, sizeof(Node), cmp);
    //for(i=0; i<1000; i++)
    //    printf("val:%d,pos:%d\n",nodes[i].val,nodes[i].pos);

    //return NULL; 
    printf("search target\n");
    for(i=0; i<numsSize-1; i++){
        rest=target-nums[i];
        left=0;
        right=numsSize-1;
        while(left<=right){
            middle=(left+right)/2;
            printf("i:%d,left:%d,right:%d,middle:%d\n",i,left,right,middle);
            if(rest==nodes[middle].val){
                if(i==nodes[middle].pos){
                    if(rest==nodes[middle+1].val){
                        rest_index=nodes[middle+1].pos;
                    }
                    else if(middle>1 && rest==nodes[middle-1].val){
                        rest_index=nodes[middle-1].pos;
                    }
                    else{
                        break;
                    }
                }
                else{
                    rest_index=nodes[middle].pos;
                }
            

                if(nodes[middle].pos<i){
                    out[0]=rest_index+1;
                    out[1]=i+1;
                }   
                else{
                    out[0]=i+1;
                    out[1]=rest_index+1;
                }
                return out;
            }
            else if(rest>nodes[middle].val)
                left=middle+1;
            else
                right=middle-1;
        }
    }
    return out;
}
int main(int argc,char ** argv)
{
    int target;
    int nums[NUMS_SIZE]={3,2,4};
    target=6;
    
    int *ret=twoSum(nums,NUMS_SIZE,target);
    printf("target:%d\t--[%d(%d),%d(%d)\n]",target,ret[0],nums[ret[0]-1],ret[1],nums[ret[1]-1]);
    return 0;
}
