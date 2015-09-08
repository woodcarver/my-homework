#include<stdio.h>
#include<stdlib.h>
#include<string.h>
//暴露的问题：对二位数组试用不灵活，还有是指向指针的指针的理解模型有问题

void iterator_digits(char **res,int *pos, char *tmp,int level,char **table,char *digit)
{
    char *tmp_res,*letter;
    int i;

    printf("pos:%d,level:%d\n",*pos,level);

    if(*digit=='\0'){
        tmp_res=malloc(level*sizeof(char));
        strcpy(tmp_res,tmp);
        res[*pos]=tmp_res;
        (*pos)++;

        return;
    }

    letter=table[*digit-'0'];
    for(i=0; letter[i]!='\0'; i++){
        tmp[level]=letter[i];
        iterator_digits(res,pos,tmp,level+1,table,digit+1);
    }
}

char ** letterCombinations(char* digits, int* returnSize) {
    int i,digits_len;
    char *table[10]={" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    digits_len=strlen(digits);
    *returnSize=digits_len?1:0;
    if(digits_len==0)
        return NULL;

    for(i=0; i<digits_len; i++)
        *returnSize *=strlen(table[digits[i]-'0']);
    printf("returnSize:%d\n",*returnSize);

    char **res=malloc(*returnSize * sizeof(char *));
    char *tmp=malloc((digits_len+1)*sizeof(char));
    tmp[digits_len]=0;

    int pos=0;
    iterator_digits(res,&pos,tmp,0,table,digits);
    free(tmp);
    return res;
}
void print_muli_arr(char **result,int size)
{
    int i;
    for(i=0;i<size;i++)
        printf("%s\n",result[i]);
    printf("---------------\n");
}

#define TEST(digits) do{printf("digits:%s\n",digits); \
    result=letterCombinations(digits,&returnSize); \
    printf("returnSize:%d\n",returnSize);print_muli_arr(result,returnSize); \
    }while(0)

int main()
{
    char *d1="23";
    char **result;
    int returnSize=0;

    TEST(d1);
    TEST("89");
    TEST("");
    TEST("7");
    //TEST("2567");
    return 0;
}
