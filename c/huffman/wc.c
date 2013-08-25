#include<stdio.h>
#include<ctype.h>
#include "hash.h"
#define MAX_WORD_LENGTH 100

hash *word_table;
char word[MAX_WORD_LENGTH];

void word_count(const char *key)
{
  cell *pcell;
  pcell=hash_find(&word_table,key);
  if(pcell->info == FULL)
    pcell->count++;
  else
    hash_add(&word_table,key,1);
}

int get_word(FILE *input)
{
  int i;
  char c,*pword;
  pword=word;
  i=0;

  while((c=fgetc(input))==' '||c=='\t')
    ;
  if(isalnum(c))
    word[i++]=c;
  /*while(isalnum(word[i++]=fgetc(input)))
    c=word[i];*//*will have a nosalnum character behind the word*/
  while(isalnum(c=fgetc(input)))
    word[i++]=c;
  
  word[i++]='\0';/*end the string,clean the tail characters which lefted by previous word.*/
  return c;
}

/*int main(void)
{
  FILE *fp;

  fp=fopen("test.txt","r");
  word_table=hash_create(HASH_TABLE);

  while(get_word(fp)!=EOF){
    printf("-------%s--------\n",word);
    if(word[0]!='\0')
      word_count(word);
  }
  hash_print(word_table);
  return 0;
}*/
