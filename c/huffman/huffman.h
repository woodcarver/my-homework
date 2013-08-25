#ifndef _HUFF_MAN
#define _HUFF_MAN
#include "hash.h"
#define sawp(x,y) do{huffman_tree *temp;temp=x;x=y;y=temp;}while(0)
typedef struct huffman_tree{
  char *key;
  int weight;
  struct huffman_tree *left;
  struct huffman_tree *right;
}huffman_tree;

typedef struct huffman_code_list{
  char *key;
  char *code;
  struct huffman_code_list *next;
}huffman_code_list;

/******api*********/
void huffman_encode();
void huffman_decode();
huffman_tree *huffman_generate_code(hash *word_table);
huffman_tree *huffman_get_code();
void print_huffman_tree(huffman_tree *huff);
void print_huffman_code(huffman_tree *huff,char *prefix);
huffman_tree *perc_down(huffman_tree *word_forest[],int i,int n);
void insert(huffman_tree *word_forest[], huffman_tree *new,int n);
#endif
