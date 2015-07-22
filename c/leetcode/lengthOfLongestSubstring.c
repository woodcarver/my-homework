#include<stdio.h>
int lengthOfLongestSubstring(char* s) {
    if(!s || '\0'==s[0])
        return 0;
    int i,j,start,end,len,long_len;
    start=end=0;
    long_len=1;
    for(i=1;s[i]!='\0';i++){
        for(j=start;j<=end;j++){
            if(s[i]==s[j]){
                printf("start:%c,end:%c,break:%c\n",s[start],s[end],s[i]);
                len=end-start+1;
                long_len=long_len<len?len:long_len;
                start=j+1;
                break;
            }
        }
        end++;
    }
    len=end-start+1;
    long_len=long_len<len?len:long_len;
    return long_len;
}

int main(int argc,char **argv){
    int len;
    len=lengthOfLongestSubstring(argv[1]);
    printf("result:%d\n",len);
    return 0;
}
