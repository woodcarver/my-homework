#include<stdio.h>
#include<stdlib.h>
#include "utility/print_ext.c"

#define MAGIC_NUM 100

void generator(char **result,int n,int left,int right,char *tmp,int *returnSize);
char** generateParenthesis(int n, int* returnSize) {
    char **result=malloc(MAGIC_NUM*sizeof(char *));
    char *tmp=malloc(2*n*sizeof(char)+1);
    tmp[2*n*sizeof(char)+1]='\0';
    *returnSize=0;

    generator(result,n,0,0,tmp,returnSize);
    return result;
}

void generator(char **result,int n,int left,int right,char *tmp,int *returnSize){
    printf("n:%d,left:%d,right:%d\n",n,left,right);
    if(left==n && right==n){
        result[*returnSize]=tmp;
        tmp=malloc(2*sizeof(tmp)*sizeof(char)+1);
        tmp[2*sizeof(tmp)*sizeof(char)+1]='\0';
        (*returnSize)++;
    }
    if(left<n){
        tmp[left]='(';
        generator(result,n,left+1,right,tmp,returnSize);
    }
    if(right<n && right<left){
        tmp[left+right]=')';
        generator(result,n,left,right+1,tmp,returnSize);
    }
}

int main()
{
    char **result;
    int returnSize;
    result=generateParenthesis(3,&returnSize);
    print_arr_arr_char(result,returnSize,3);
    return 0;
}
