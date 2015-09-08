#include <stdio.h>
#include <stdlib.h>

#define TEST(nums,numsSize,target) do{printf("target:%d,res:%d\n",target,threeSumClosest(nums,numsSize,target));}while(0)
int cmp(const void *a,const void *b)
{
    return (*(int *)a - *(int *)b);
}
void print_arr(int * arr,int size)
{
    int i;
    for(i=0; i<size; i++)
        printf("%d,",arr[i]);
    printf("\n");
}
int threeSumClosest(int* nums, int numsSize, int target){
    int i,j,k,gap,sum,closet;
    //sort the set
    qsort(nums,numsSize,sizeof(int),cmp);
    print_arr(nums,numsSize);

    closet=nums[0]+nums[1]+nums[2];//init closet firist
    
    for(i=0; i<numsSize; i++){
        j=i+1;
        k=numsSize-1;
        while(j<k){
            sum=nums[i]+nums[j]+nums[k];
            gap=sum-target;
            if(abs(gap) < abs(closet-target)){
                closet=sum;
            }
            //printf("a:%d,b:%d,c:%d,gap:%d,closet:%d\n",nums[i],nums[j],nums[k],gap,closet);
            printf("a:%d,b:%d,c:%d,gap:%d,closet:%d\n",i,j,k,gap,closet);
            if(gap>0){
                //subset is bigger than target,need to decrease the sum
                k--;
            }
            else if(gap<0){
                //subset is smaller than target,need to increase the sum
                j++;
            }
            else{
                //the subset is equal to target, this is the closet result,need no future step
                return closet;
            }
        }
    }
    return closet;
}


int main()
{
    int nums[]={-1,2,1,-4};
    TEST(nums,4,1);
    int nums1[]={0,0,0,0};
    TEST(nums1,4,1);
    int nums2[]={-4,-2,9,9,10,-1,-5,-1,-1,1};
    TEST(nums2,10,-2);
    return 0;
}
