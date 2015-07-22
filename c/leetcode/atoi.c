#include<stdio.h>
#include<string.h>
#include<limits.h>

int myAtoi(char* str) {
    // expect{i:0<=i<=9}: i-'0' : 1001,0001,900000000000000-overflow!!!,-1,-91111111111111-overflow!!!!
    // {i: '\t' ' '...} how deal this? example: ' '123--> 123, 1' '23-->1, 123''--->123
    // {i: a b c ...}this? example: ab123-->123, 123db-->123, 1ab23-->1
    // {}

    unsigned long len=strlen(str);
    int out=0,sign=1;
    int digit;
    int i=0;

    // ' '123--> 123
    //ab123-->123
    while(i<len && (str[i]==' ' || str[i]=='\t' || str[i]=='\r' || str[i]=='\n'))
        i++;

    //get the sign
    if(str[i]=='-'){
        sign=-1;
        i++;
    }
    else if(str[i]=='+'){
        sign=1;
        i++;
    }
    printf("len:%lu,i:%d,sign:%d\n",len,i,sign);
    //get the body
    for(; i<len; i++){
        printf("out:%d,stri:%d\n",out,str[i]-'0');
        if('0'<=str[i] && str[i]<='9'){
            digit=str[i]-'0';
            //out*10+digit <= INT_MAX, out*10+digit >= INT_MIN. but INT_MAX/10==INT_MIN/10
            //out>INT_MAX/10 predicate is not enough ,think about the case:out=214748364,digit=8
            if(out<INT_MAX/10 || (out==INT_MAX/10 && str[i]-'0'<=(INT_MAX-INT_MAX/10*10))){
                out=out*10+(str[i]-'0');
            }
            else{
                printf("%d,%d\n",sign,sign>0?INT_MIN:INT_MAX);
                return sign?INT_MAX:INT_MIN;
            }
        }
        else //123db-->123,1ab23-->1
            break;
    }
    return out*sign;
}
int main(int argc,char **argv)
{
    printf("%d\n",myAtoi(argv[1]));
    return 0;
}
