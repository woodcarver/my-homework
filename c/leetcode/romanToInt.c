#include<stdio.h>
#include<string.h>

int rToi(char c)
{
    switch(c){
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
    }
    return 0;
}

int romanToInt(char* s) {
    int i,digit1,digit2;
    int res=0;
    int n=strlen(s);
    res+=rToi(s[n-1]);
    for(i=n-2; i>=0; i--){
        digit1=rToi(s[i]);
        digit2=rToi(s[i+1]);

        if(!digit1 || !digit2)
            return 0;

        if(digit1<digit2)
            res-=digit1;
        else
            res+=digit1;
    }
    return res;
}
#define TEST(s) do{printf("%s:%d\n",s,romanToInt(s));}while(0)
int main()
{
    TEST("I");
    TEST("III");
    TEST("IV");
    TEST("DCCC");
    TEST("MCDLXXVI");
    TEST("MMMCMXCIX");
    return 0;
}
