#include<stdio.h>
#include<stdlib.h>
#define N 12
#define NBIG 5
#define NSMALL 3
int set[N];
int **set_big;
int **set_small;
int *one_set;
int last_set_pos;
int *is_covered;
int *is_chosen;

/*{{{*/
int product(int *array,int n)
{
    int i;
    int prod=1;
    for(i=0; i<n; i++)
        prod*=array[i];
    return prod;
}
void print_array(int *array,int n)
{
    int i;
    for(i=0; i<n; i++)
        printf("%d ",array[i]);
    printf("\n");
}
void print_sets(int **sets,int count_set,int count_elem)
{
    int i,j;
    printf("----------print sub set----------\n");
    if(!sets)
        printf("the sets is null\n");
    for(i=0; i<count_set; i++){
        for(j=0; j<count_elem; j++)
            printf("%d ",sets[i][j]);
        printf("\n");
    }
    printf("\n");
}
void append(int **sets,int *set,int count,int pos)
{
    int i;
    printf("----------append:%d--------\n",pos);
    if(!set)
        printf("set is not null\n");
    if(!sets[pos])
        printf("sets have this pos\n");

//print_array(set,count);
    for(i=0; i<count; i++){
        printf("%d ",set[i]);
        sets[pos][i]=set[i];
    }
    printf("[end append]\n");
}
/*}}}*/
void get_prime_array(int *list,int n)
{/*{{{*/
    int i;
    int primes[27]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
    for(i=0; i<n; i++)
        list[i]=primes[i];
}/*}}}*/

int combinator_count(int n,int m)
{/*{{{*/
    int i;
    int num=1;
    if(m<0 || n<0)
        return 0;
    if(n<m || m==0)
        return 1;
    for(i=1; i<=m; i++)
        num=num*(n-i+1)/i;
    return num;
}/*}}}*/

void gen_combinator_iter(int start,int level,int count_ss,int **sub_sets)
{/*{{{*/
    int k;
    printf("start:%d,level:%d,count_ss:%d\n",start,level,count_ss);
    if(level == count_ss){
        append(sub_sets,one_set,count_ss,last_set_pos++);
        return;
    }
    for(k=start; k< N-(count_ss-level)+1; k++){
      one_set[level]=set[k];
      gen_combinator_iter(k+1,level+1,count_ss,sub_sets);
    }
}/*}}}*/

void gen_combinator(int ***sub_sets,int n,int m)
{/*{{{*/
    int j;
    int count_ss=combinator_count(n,m);
    printf("n:%d,m:%d,count_ss:%d\n",n,m,count_ss);
    *sub_sets=malloc(sizeof(size_t)*count_ss);
    for(j=0; j<count_ss; j++)
        (*sub_sets)[j]=malloc(sizeof(int)*m);

    one_set=malloc(sizeof(int)*m);
    last_set_pos=0;

    gen_combinator_iter(0,0,m,*sub_sets);
}/*}}}*/

void cover(int **set_big,int count_big,int ele_count_big,int **set_small,int count_small,int ele_count_small)
{/*{{{*/
    int i,j,k;
    int is_over=1;
    for(i=0; i<count_big; i++){
        /*check the small sets are covered all.*/
        for(k=0; k<count_small; k++)
            is_over &=is_covered[k];
        if(is_over){
            print_array(is_covered,count_small);
            return;
        }
        else
            is_over=1;

        for(j=0; j<count_small; j++)
            if(!(product(set_big[i],ele_count_big)%product(set_small[j],ele_count_small)))
                is_covered[j]=1;
        if(j>0)
            is_chosen[i]=1;//keeping coverd one set_small at least.
    }
}/*}}}*/
int main(int argc,char **argv)
{
    int i,count_big,count_small;
    get_prime_array(set,N);
    print_array(set,N);

    count_big=combinator_count(N,NBIG);
    count_small=combinator_count(N,NSMALL);
    is_chosen=malloc(sizeof(int)*count_big);
    is_covered=malloc(sizeof(int)*count_small);
    printf("count_big:%d,count_small:%d\n",count_big,count_small);

    gen_combinator(&set_big,N,NBIG);
    gen_combinator(&set_small,N,NSMALL);
    print_sets(set_big,count_big,NBIG);    
    print_sets(set_small,count_small,NSMALL);  

    cover(set_big,count_big,NBIG,set_small,count_small,NSMALL);
    printf("----------the result---------\n");
    for(i=0; i<count_big; i++){
        if(is_chosen[i])
            print_array(set_big[i],NBIG);
    }
    return 0;
}
