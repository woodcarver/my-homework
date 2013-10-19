#include<stdio.h>
void ccout(int s[]);
void quickSort(int s[],int start,int end)
{
        printf("-----------new recusive--------------\n");
        printf("start:%d-end:%d\n",start,end);
        int i=start;
        int j=end;
        int temp;
        if(start >= end)
                return;
        while(i<j){
                printf("i:%d-j:%d\n",i,j);
                if(s[i]>s[j]){
                        temp=s[i];
                        s[i]=s[j];
                        s[j]=temp;
                }
                --j;
        }
        ccout(s);
        quickSort(s,start,i);
        quickSort(s,i+1,end);
}
void ccout(int s[])
{
        int k;
        for(k=0;k<8;++k)
                printf("%d ",s[k]);
        printf("\n");
}
int main()
{
        int s[8]={3,7,5,2,1,9,5,4};
        ccout(s);
        quickSort(s,0,7);
        ccout(s);
        return 0;
}
