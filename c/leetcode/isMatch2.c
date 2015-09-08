#include<stdio.h>
#define true 1
#define false 0
#define TEST(s,p) do{printf("\n");printf("s:%s p:%s res:%d\n",s,p,isMatch(s,p));}while(0)
int isMatch(char* s, char* p) {
    //s input: NULL,abdf,cccacaca,abcefoadbdef
    //p input:a,.*,a*
    // At begining: .*abc,a*bc
    // At end: abc*,ab.*
    // At middle: ab*c.*def
    char *back;
    back=NULL;
    int is_backable=false;
    int back_step=0;
    char *match_str;//"aaa", "ab*a*c*a"
    if(s==NULL || p==NULL)
        return false;

    for(;*s!='\0';s++){
        //checking if the next char is * in p and keeping the backtracing point
        if(*p!='\0' && *(p+1)=='*'){
            back=p;
            p=p+2;
            is_backable=true;//reset
            back_step=0;
        }
        if(back && (*back!='.') && (*back!=*s)){
            is_backable=false;
        }
        if((*p=='.') || (*s==*p)){
            if(back && *back==*p)
                back_step++;
            p++;
        }
        else{
            if(!is_backable || (back && (*back!='.') && (*s!=*back))){
                return false;
            }
            p=back+2+back_step;
        }
    }
    if(*p!='\0')
        return false;
    return true;
}

int main()
{
    TEST("aa","a");
    TEST("aa","a*");
    TEST("aa","a*a");
    TEST("cccaccac","c*cac");
    TEST("ccccac","c*ac");
    TEST("ccccac","c*cac");
    TEST("ccccac","c*cccac");
    TEST("aab","c*a*b");
    return 0;
}
