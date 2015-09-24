#include<stdio.h>
#define swap(s,t) {int tt=s;s=t;t=tt;}

void sort_three_nums1(int a,int b,int c){
    if(c<b){
        swap(b,c);
    }
    //{b<c}
    if(b<a){
        swap(a,b);
    }
    //{a<b && a<c}
    //be careful!Swap a,b maybe destory the precondition {b<c}, because the value of b maybe changed
    //so you need to compare b and c again!!
    //the confusion of side effect
    if(c<b){
        swap(b,c);
    }
    //{a<b && b<c && a<c}
    //{a<b<c}
    printf("%d %d %d\n",a,b,c);
}
void sort_three_nums2(int a,int b,int c){
    if(b<a){
        swap(a,b);
    }
    //{a<b}

    if(c<a){
        //{a<b && c<a}={c<a<b}
        swap(a,c);
        //{a<c<b}=>{a<b && a<c}
    }
    //{a<b && a<c}

    if(c<b){
        //{a<b && a<c && c<b}={a<c<b}
        swap(b,c);
        //{a<b<b}
    }
    //{a<b && a<c && b<c}={a<b<c}

    printf("%d %d %d\n",a,b,c);
}
int main()
{
    int a,b,c;
    scanf("%d %d %d",&a,&b,&c);
    sort_three_nums1(a,b,c);
    sort_three_nums2(a,b,c);
    return 0;
}
