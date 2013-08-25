#include<stdio.h>
void shell_sort(int a[], int n)
{
    int i,j,d,temp;
    for(d=n/2; d>0; d/=2){
        for(i=d; i<n; i++){
            temp=a[i];
            for(j=i-d; a[j]>temp&&j>=0; j-=d)
                a[j+d]=a[j];
            a[j+d]=temp;
        }
    }
}

int main(void)
{
    int i;
    int a[]={0,2,8,7,9,11,6,4,8};
    shell_sort(a,9);
    for(i=0; i<9; i++)
        printf("%d ",a[i]);
    printf("\n");
    return 0;

}
