#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include "utility/print_ext.c"

#define MAGIC_NUMBER 1000 
void * myMalloc(size_t n){
    void *p;
    p=malloc(n);
    if(p==NULL)
        return NULL;
    return p;
}
int calSize(int n,int m){
    int i,product;
    product=1;
    if(n<0 || m>n)return 0;
    if(m==0)return 1;
    for(i=0; i<m; i++){
        product*=n-i;
        product/=i+1;
    }
    return product;
}
void permula_iterator(int **bucket,int n,int level_total,int level,int *tmp,int *pos,int pos_i){
    //static int pos;//why not?Becuae when call the function mulity times out of recursive,the pos will not be reset to 0
    int i;
    //if(level<0){
    //    print_arr(tmp,sizeof(tmp)/sizeof(tmp[0]));
    //    bucket[pos++]=tmp;
    //    return;
    //}
    //for(i=n-1; i>=0; i--){
    //    tmp[level]=i;
    //    permula_iterator(bucket,n-1,level-1,tmp);
    //}
    if(level==level_total){
        print_arr(tmp,level_total);
        //can not share the tmp for mulity branches
        i=*pos;
        bucket[i]=myMalloc(level_total*sizeof(int));
        memmove(bucket[i],tmp,level_total*sizeof(int));
        //print_arr(bucket[i],level_total);//why bucket[*pos] is NULL?
        (*pos)++;
        return;
    }
    printf("permula-iterator--level:%d,n:%d,tmp:%d,pos:%d,pos_i:%d\n",level,n,*tmp,*pos,pos_i);
    for(i=pos_i;i<n;i++){
        tmp[level]=i;
        //printf("(%d,%d,%d)\n",level,pos_i,i);
        permula_iterator(bucket,n,level_total,level+1,tmp,pos,i+1);
    }
}
int **permulation(int n,int level,int *size){
    *size=calSize(n,level);
    printf("permulation--level:%d.bucket-size:%d\n",level,*size);

    int **bucket=(int **)myMalloc((*size)*sizeof(int *)); 
    int *tmp=(int *)myMalloc(level*sizeof(int));
    int pos=0;

    permula_iterator(bucket,n,level,0,tmp,&pos,0);
    return bucket;
}
char** generateParenthesis(int n, int* returnSize){
    int **bucket;
    char **result,*temp;
    int b_size,i,j,k;
    int *stack_a,top_a,top_b;

    *returnSize=0;
    //bucket=(int **)myMalloc(MAGIC_NUMBER*sizeof(int *));
    result=(char **)myMalloc(MAGIC_NUMBER*sizeof(char *));

    for(i=0; i<n; i++){
        bucket=permulation(n,i+1,&b_size);
        printf("bucket here\n");
        print_arr_arr(bucket,b_size,i+1);
        for(j=0; j<b_size; j++){
            stack_a=bucket[j];
            print_arr(stack_a,i+1);
            top_a=j-1;
            top_b=-1;
            temp=myMalloc(2*n*sizeof(char)+1);
            for(k=0; k<n; k++){
                temp[k]='(';
                if(k!=stack_a[top_a]){
                    temp[++k]=')';
                }
                else{
                    top_a--;//pop(stack_a)
                    top_b++;//push(stack_b,k)
                }
                if(top_a==-1){//empty(top_a)
                    while(top_b>-1){//!emptu(top_b)
                        temp[++k]=')';
                        top_b--;
                    }
                }
            }
            temp[++k]='\0';
            result[(*returnSize)++]=temp;
        }
    }
    free(bucket);
    return result;
}

int main()
{
    char **result;
    int returnSize;
    result=generateParenthesis(3,&returnSize);
    print_arr_arr((int **)result,returnSize,3);
    return 0;
}
