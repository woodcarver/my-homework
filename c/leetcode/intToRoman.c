#include<string.h>
#include<stdio.h>
#include<stdlib.h>

char* intToRoman(int num) {
    int digit,pos;
    int i;
    int stack[10],top=0;

    char *roman=(char *)malloc(1000*sizeof(char));
    roman[0]='\0';
    char *pres=roman;

    if(num>3999 || num<=0)
        return roman;

    char *num_tab[4][10]={
        {0,"I","II","III","IV","V","VI","VII","VIII","IX"},
        {0,"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
        {0,"C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
        {0,"M","MM","MMM",0,0,0,0,0,0}
    };

    for(i=0;num>0;i++){
        digit = num-num/10*10;
        if(digit>0){
            //printf("i:%d,digit:%d,roman:%s\n",i,digit,num_tab[i][digit]);
            //todo: practice how to process string in c
            stack[top++]=i*10+digit;
        }
        num/=10;
    }
    for(i=top-1;i>=0;i--){
        pos=stack[i];
        //printf("pos:%d\n",pos);
        strcat(roman,num_tab[pos/10][pos%10]);
    }

    return roman;
}

#define TEST(num) do{printf("case: %d,%s\n",num,intToRoman(num));}while(0)
int main()
{
    TEST(0);
    TEST(1);
    TEST(100);
    TEST(123);
    TEST(3999);
    TEST(100000001);
    return 0;
}
