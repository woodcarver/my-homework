#include<stdio.h>
#include<stdlib.h>

#define SUM_NUM 4

int compar(const void *a, const void *b){
    return (*(int *)a)-(*(int *)b);
}

int ** fourSum1(int *nums,int numsSize, int target, int *returnSize){
    int i,j,k,s,sum;
    int *temp;
    int **result=malloc(100*sizeof(int *));//100 is not very good,but How can I know the size of result?

    *returnSize=0;
    //input {11111111},4 ===> one {1111} or many {1111}
    qsort(nums,numsSize,sizeof(int),compar);
    for(i=0; i<numsSize-3; i++){
        for(j=i+1; j<s; j++){
            k=j+1;
            s=numsSize-1;
            while(k>s){
                sum=nums[i]+nums[j]+nums[k]+nums[s];
                if(target<=sum){
                    s--;
                }
                else if(target>sum){
                    k++;
                }

                if(target==sum){
                   temp=malloc(4*sizeof(int));
                   temp[0]=i;
                   temp[1]=j;
                   temp[2]=k;
                   temp[3]=s;
                   result[(*returnSize)++]=temp;
                }
            }
        }
    }
    return result;
    //R:i<j<k<s ^ nums[i]+nums[j]+nums[k]+nums[s]=target
}
int ** fourSum2(int *nums,int numsSize, int target, int *returnSize){
    int i,j,k,s,sum;
    int *temp;
    int **result=malloc(100*sizeof(int *));//100 is not very good,but How can I know the size of result?
    int *temp=malloc(SUM_NUM*sizeof(int));

    *returnSize=0;
    //input {11111111},4 ===> one {1111} or many {1111}
    qsort(nums,numsSize,sizeof(int),compar);
    find_fourSum_iterator(result,nums,numsSize,target,returnSize,0,temp);
    return result;
    //R:i<j<k<s ^ nums[i]+nums[j]+nums[k]+nums[s]=target
}
//brute force
void find_fourSum_iterator(int **result,int *nums, int numsSize, int target, int *returnSize, int level,int *temp){
    if(level==SUM_NUM){
        if(target==0){
            result[(*returnSize)++]=temp;
        }
        return;
    }

    int i;
    for(i=0; i<numsSize; i++){
        temp[level]=nums[i];
        find_fourSum_iterator(result,nums,numsSize-1,target-nums[i],returnSize,level+1,temp);
    }
}
