#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>

#define LONGEST_LEN 1000
char* longestCommonPrefix(char** strs, int strsSize) {
       int i,p=0;
       char *prefix=malloc(LONGEST_LEN*sizeof(char));
       prefix[0]='\0';

       assert(strs!=NULL);
       if(strsSize==0)// case for []
           return prefix;

       while(strs[0][p]!='\0'){
           for(i=0; i<strsSize; i++){
                if(strs[i][p]=='\0' || strs[i][p]!=strs[0][p]){
                    prefix[p]='\0';
                    return prefix;
                }
                if(i==(strsSize-1))
                    prefix[p]=strs[0][p];
           }
           p++;
       }

       //is prefix a legal string, {prefix[last_element]='\0'}
       prefix[p]='\0';
       return prefix;
}

int main()
{
    char *strs1[]={"abc","abcd","ab","abcdefg"};
    printf("%s\n",longestCommonPrefix(strs1,4));
    char *strs2[]={"abc","abcd","","abcdefg"};
    printf("%s\n",longestCommonPrefix(strs2,4));
    char *strs3[]={};
    printf("%s\n",longestCommonPrefix(strs3,0));
    return 0;
}
