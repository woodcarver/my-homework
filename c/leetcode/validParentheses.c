#include<stdio.h>
#define true 1
#define false 0
typedef int bool;

bool isValid(char* s) {
    char stack[100],top_item;
    int top=-1;
    if(s==NULL || *s=='\0'){
        return true;
    }

    //initail the stack
    stack[++top]=*s;
    s++;
    while(*s!='\0'){
        //printf("top:%d,%c;s:%c\n",top,stack[top],*s);
        top_item=stack[top];
        if((top_item=='(') && (*s==')')){
            top--;
        }
        else if((top_item=='{') && (*s=='}')){
            top--;
        }
        else if((top_item=='[') && (*s==']')){
            top--;
        }
        else{
            stack[++top]=*s;
        }
        s++;
    }
    if(top==-1)
        return true;
    return false;
}
#define TEST(s)do{printf("%s,%d\n",s,isValid(s));}while(0)
int main()
{
    TEST("()");
    TEST("(){}[]");
    TEST("(()[][{()}])");
    TEST("((])");
    TEST("[(])");
    TEST("");
    return 0;
}
