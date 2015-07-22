#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char* convert(char* s, int numRows) {
    unsigned long len=strlen(s);
    char *out=malloc((len+1)*sizeof(char));

    int r,j,k,step,stepi;
    k=j=0;

    for(r=0; r<numRows; r++){
        step=numRows+1;

        for(k=r; k<len; k+=step){
            printf("%d:%c ",k,s[k]);
            out[j++]=s[k];
            //for the special row with more elements
            if(r==numRows/2){
                stepi=numRows<=1?1:numRows-1;
                if((k+stepi)<len){
                    printf("m:%d:%c ",k+step,s[k+step]);
                    out[j++]=s[k+stepi];
                }
            }
        }
        printf("row:%d,j:%d\n",r,j);
    }
    out[len+1]='\0';
    printf("numRows:%d,len:%lu,out_len:%lu\n",numRows,len,strlen(out));
    printf("%s\n",out);
    return out;
}
int main(int argc, char **argv)
{
    char *s=convert(argv[1],atoi(argv[2]));
    printf("%s\n",s);
    return 0;
}
