#include<stdio.h>
#include<stdlib.h>
int binary_search(int *arr,int left,int right,int target)
{
    int middle;
    while(left<=right){
        middle=(left+right)/2;
        //printf("binary_search:left:%d,right:%d;middle:%d,target:%d,pivot:%d\n",left,right,middle,target,arr[middle]);
        if(target<arr[middle]){
            right=middle-1;
        }
        else if(target>arr[middle]){
            left=middle+1;
        }
        else
            return middle;
    }
    
    return right<0?-1:(left+right)/2;
}
double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size)
{
    int leftl,rightl,middlel,pos;
    int lefts,rights,middles;
    int *longArr,*shortArr;
    double median=0.0;
    int median_pos=(nums1Size+nums2Size)/2;
    printf("median_pos:%d\n",median_pos);
    int med1,med2;
    //the longer array is assigned to longArr
    if(nums1Size>=nums2Size){
        longArr=nums1;
        shortArr=nums2;
        leftl=lefts=0;
        rightl=nums1Size-1;
        rights=nums2Size-1;
    }
    else{
        longArr=nums2;
        shortArr=nums1;
        leftl=lefts=0;
        rightl=nums2Size-1;
        rights=nums1Size-1;
    }

    middlel=(leftl+rightl+1)/2;
    middles=(lefts+rights+1)/2;
    while((leftl<=rightl) && (lefts<=rights)){
        middlel=(leftl+rightl+1)/2;
        middles=binary_search(shortArr,lefts,rights,longArr[middlel]);
        pos=middlel+middles+1;
        printf("leftl:%d,rightl:%d,middlel:%d\n",leftl,rightl,middlel);
        printf("lefts:%d,rights:%d,middles:%d\n",lefts,rights,middles);
        printf("pos:%d\n",pos);
        if(pos==median_pos){
            median=longArr[middlel];
            break;
        }
        if(pos<median_pos){
            leftl=middlel+1;
            lefts=middles+1;
        }
        else{
            rightl=middlel-1;
            rights=middles;//for special
        }
    }
    //shortArr is empty
    if((leftl<=rightl) && (lefts>rights)){
        printf("longArr:leftl:%d,middlel:%d,middles:%d\n",lefts,middlel,middles);
        median=longArr[median_pos];
    }
    med1=middlel-1;
    med2=middles;
    //longArr is empty
    if( !median && (leftl>rightl) && (lefts<=rights)){
        printf("shortArr:lefts:%d,middlel:%d,middles:%d\n",lefts,middlel,middles);
        pos=middlel+middles+1;
        median=shortArr[lefts+(median_pos-pos)-1];

        med1=middlel;
        med2=middles-1;
    }
    printf("bigger_median:%f\n",median);

    //even total length of those two nums
    if((nums1Size+nums2Size)/2 != (nums1Size+nums2Size-1)/2){
        if((med2==-1) || nums1[med1]>nums2[med2])
            median=(median+nums1[med1])/2.0;
        else
            median=(median+nums2[med2])/2.0;

    }

    return median;
}

void print_arr(int *arr,int size)
{
    int i;
    for(i=0; i<size; i++)
        printf("%d ",arr[i]);
    printf("\n");
}
int main(int argc, char ** argv)
{
    int nums1Size,nums2Size,i;
    int *nums1,*nums2;
    printf("input the sizes of num1 and num2:\n");
    scanf("%d %d",&nums1Size,&nums2Size);
    printf("input nums1:\n");
    nums1=malloc(nums1Size*sizeof(int));
    for(i=0; i< nums1Size; i++){
        scanf("%d",nums1+i);
    }
    print_arr(nums1,nums1Size);
    printf("input nums2:\n");
    nums2=malloc(nums2Size*sizeof(int));
    for(i=0; i< nums2Size; i++){
        scanf("%d",nums2+i);
    }
    print_arr(nums2,nums2Size);

    printf("median:%f\n",findMedianSortedArrays(nums1,nums1Size,nums2,nums2Size));
}
