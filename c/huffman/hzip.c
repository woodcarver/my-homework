#include "huffman.h"
#include "wc.h"
#include "file_op.h"
#include<stdio.h>

extern hash *word_table;
extern char word[];

int main(int argc,char **argv)
{
  FILE *fp;
  huffman_tree *huff;
  char prefix_arr[1000],*prefix;
  prefix=prefix_arr;
  /*start to statistics the word count*/
  fp=fopen("test.txt","r");
  word_table=hash_create(HASH_TABLE);

  while(get_word(fp)!=EOF){
    printf("-------%s--------\n",word);
    if(word[0]!='\0')
      word_count(word);
  }
  hash_print(word_table);
  /*start to generate the huffman code*/
  huff=huffman_generate_code(word_table);
  print_huffman_tree(huff);
  printf("print the huffman code\n");
  print_huffman_code(huff,"");
  /*start to write the huffman code into file*/
  
  return 0;
}
