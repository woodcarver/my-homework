#include<stdio.h>
void insert_sort(int a[],int n)
{
    int i,j;
    for(i=2; i<n; i++){
        a[0]=a[i];
        printf("i:%d\n",a[i]);
        for(j=i-1; a[j]>a[0]; j--){
            printf("j:%d ",a[j]);
            a[j+1]=a[j];
        }
        printf("\n");
        a[j+1]=a[0];
    }
    printf("\n");
}

int main(void)
{
    int i;
    int a[]={0,2,8,7,9,11,6,4,8};
    insert_sort(a,9);
    for(i=0; i<9; i++)
        printf("%d ",a[i]);
    printf("\n");
    return 0;
}
