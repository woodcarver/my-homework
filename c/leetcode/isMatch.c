#include<stdio.h>
#include<stdlib.h>
#define true 1
#define false 0
#define TEST(s,p) do{printf("\n");printf("s:%s p:%s res:%d\n",s,p,isMatch(s,p));}while(0)
int isSubchar(char a,char *str){
    printf("sub char:%s\n",str);
    if(str==NULL)
        return false;
    while(*str!='\0'){
        if(*str=='.' || a==*str)
            return true;
        else{
            *str='*';//"aaba", "ab*a*c*a": disable the those match char,keep the match order,b must before a
        }
        str++;
    }
    printf("sub char:%s\n",str);
    return false;
}
int isMatch(char* s, char* p) {
    //s input: NULL,abdf,cccacaca,abcefoadbdef
    //p input:a,.*,a*
    // At begining: .*abc,a*bc
    // At end: abc*,ab.*
    // At middle: ab*c.*def
    char *back=NULL;
    char *back_s=NULL;
    int is_backable=false;
    int back_step=0;
    char *match_str=malloc(100*sizeof(char));
    match_str[0]='\0';
    int k=0;
    if(s==NULL || p==NULL)
        return false;

    for(;*s!='\0';s++){
        printf("#######s:%c p:%c\n",*s,*p);
        //checking if the next char is * in p and keeping the backtracing point
        while(*p!='\0' && *(p+1)=='*'){
            match_str[k++]=*p;//"aaa", "ab*a*c*a"
            match_str[k]='\0';
            is_backable=true;//reset
            back_step=0;
            back=p;
            p=p+2;
            printf("keep back point:%c \n",*back);
            back_s=s;
        }
        if((*p=='.') || (*s==*p)){
            if(back && (*back=='.' || *s==*back)){//"aa" 'a*a'
                back_step++;
            }
            //else{
            //    is_backable=false;//???
            //    printf("is_backable:%d\n",is_backable);
            //}

            if(*(p+1)=='*'){ //"aab", "c*a*b"
                s--;//match more and get back
                k=0;//start a new backtracking point
                match_str[0]='\0';
            }
            else{
                p++;
            }
        }
        else{
            if(!is_backable || !isSubchar(*s,match_str)){
                printf("is_backable:%d match_str:%s\n",is_backable,match_str);
                return false;
            }
            p=back+2+back_step;
            printf("backtracing! back:%c p:%c back_step:%d\n",*back,*p,back_step);
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
    TEST("aab","ab*a*c*b");
    TEST("aaba","ab*a*c*a");
    TEST("aaca","ab*a*c*a");
    return 0;
}
