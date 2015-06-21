#include<stdio.h>
#include<stdlib.h>

int* twoSum(int* nums, int numsSize, int target) {
    int *out=malloc(2*sizeof(int));
    int i,left,right,middle;
    for(i=0; i<numsSize; i++){
        if(nums[i]>target)
            break;

        for(left=i+1,right=numsSize; left<=right;){
            middle=(right-left)/2;
            if(nums[i]+nums[middle] == target){
                out[0]=i;
                out[1]=middle;
                return out;
            }
            else if(nums[i]+nums[middle] > target)
                left=middle;
            else
                right=middle;
        }
    }
    return out;
}

int main(int argc,char ** argv)
{
    int nums[12]={901,321,889,100,1,3,4,6,19,71,66,32};
    int *ret=twoSum(nums,12,902);
    printf("%d\t%d,%d\t%d\n",ret[0],nums[ret[0]],ret[1],nums[ret[1]]);
    return 0;
}
