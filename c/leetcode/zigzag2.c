#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char* convert(char* s, int numRows) {
    unsigned long len=strlen(s);
    char *out=malloc(sizeof(char)*(len+1));

    int r,j,k,insert;
    k=0;
    if(numRows==1 || len<=numRows)
        return s;
    for(r=0; r<numRows; r++){
        for(j=r; j<len; j+=2*(numRows-1)){
            printf("len:%lu,r:%d,j:%d,k:%d\n",len,r,j,k);
            out[k++]=s[j];
            //not the first row and last row
            if(r!=0 && r!=numRows-1){
                insert=j+2*(numRows-1)-2*r;
                if(insert<len)
                    out[k++]=s[insert];
            }
        }
    }
    out[k]='\0';
    printf("%s\n",out);
        return out;
}
int main(int argc, char **argv)
{
    char *s=convert(argv[1],atoi(argv[2]));
    printf("%s\n",s);
    return 0;
}
