#include<stdio.h>

void print_arr(int *arr,int len){
    int i;
    if(arr==NULL){
        printf("the arr is NULL!\n");
        return;
    }
    for(i=0; i<len; i++)
        printf("%d,",arr[i]);
    printf("\n");
}
void print_arr_arr(int **arr,int len, int sublen){
    int i,j;
    if(arr==NULL)
        return;
    for(i=0; i<len; i++){
        for(j=0; j<sublen; j++)
            printf("%d,",arr[i][j]);
        printf("\n");
    }
    printf("\n");
}
void print_arr_arr_char(char **arr,int len, int sublen){
    int i,j;
    if(arr==NULL)
        return;
    for(i=0; i<len; i++){
        for(j=0; j<sublen; j++)
            printf("%c,",arr[i][j]);
        printf("\n");
    }
    printf("\n");
}
