#include<stdio.h>
#include<limits.h>
#include<stdlib.h>

int reverse(int x) {
    int result=0;
    int digit[100];
    int top=-1,i;

    while(x){
        digit[++top]=x-(x/10)*10;
        x=x/10;
        printf("digit:%d,top:%d\n",digit[top],top);
    }
    for(i=0; i<=top; i++){
        //for detecting overflow
        printf("MIN:%d,MAX:%d\n",(INT_MIN)/10,(INT_MAX)/10);
        if(result<(INT_MIN-digit[i])/10 || result>(INT_MAX-digit[i])/10)
            return 0;
        result=result*10+digit[i];
        printf("result:%d\n",result);
    }
    return result;
}

int main(int argc, char **argv)
{
    printf("%d\n",reverse(atoi(argv[1])));
    return 0;
}
