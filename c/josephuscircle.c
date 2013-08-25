#include<stdio.h>
#include<assert.h>
void josephus(int [],int);
int main()
{
        int a[5]={1,2,3,4,5};
        josephus(a,5);
        return 0;
}

void josephus(int a[],int n)
{
        int next,rest,count;
        next = 0;
        count = 1;//count = 0 is wrong,the a[0] should be included.
        rest = n;
        while(rest){
                printf("next:%d count:%d rest:%d\n",next,count,rest);
                next = (next+1)%n;//the circle queue
                if(a[next] != 0)
                        ++count;
                if(3 == count){
                        printf("out:%d\n\n",a[next]);
                        a[next]=0;
                        count = 0;
                        --rest;
                }
        }
        printf("\n");
}
