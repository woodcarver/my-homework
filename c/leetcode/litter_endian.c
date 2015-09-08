#include<stdio.h>

void show_byte(void *num,int len){
    int i;
    char *pnum;
    pnum=(char *)num;
    for(i=0;i<len;i++){
        printf("byte%d:%x,",i,pnum[i]);
    }
    printf("\n");
}

int main(int argc, char **argv){
    int num=3000;
    printf("hex:0x%x\n",num);
    show_byte(&num,4);
    return 0;
}
