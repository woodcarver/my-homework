#include "huffman.h"
#include<stdlib.h>
#include<stdio.h>
#include<string.h>

void print_huffman_forest(huffman_tree *word_forest[],int n)
{
  int i;
  printf("\n[start print the forest]\n");
  for(i=0;i<n;i++){
      printf("index:%d ",i);
      if(word_forest[i])
        print_huffman_tree(word_forest[i]);
      printf("\n");
  }
}

huffman_tree *huffman_generate_code(hash *word_table)
{
  huffman_tree *word_forest[HASH_TABLE],*min,*secon_min,*merge_tree;
  int i,j,k;

  for(i=0,j=0; i<word_table->table_size; i++){
    if(word_table->the_cells[i].key && word_table->the_cells[i].key[0]!='\0'){
      word_forest[j]=malloc(sizeof(struct huffman_tree));
      word_forest[j]->key=word_table->the_cells[i].key;
      word_forest[j]->weight=word_table->the_cells[i].count;
      word_forest[j]->left=NULL;
      word_forest[j]->right=NULL;
      print_huffman_tree(word_forest[j]);
      printf("\n");
      j++;
    }
  }
 
  print_huffman_forest(word_forest,j);
 
  for(k=j/2; k>=0; k--)
    perc_down(word_forest,k,j);/*build heap*/
  print_huffman_forest(word_forest,j);
  //for(k=j-1; k>=0; k--){
  k=j-1;
  while(k>0){
    /*continue to find min element*/
    /*if(k>=j-1)
      min=word_forest[0];
    else{
      sawp(word_forest[0],word_forest[k]);
      secon_min=perc_down(word_forest,0,k);
      k--;*//*never use auto decrease int a macro*/
    //}
    //sawp(list_word[0],list_word[k--]);
    min=word_forest[0];
    sawp(word_forest[0],word_forest[k]);
    perc_down(word_forest,0,k);
    k--;
    printf("min\n");
    print_huffman_forest(word_forest,j);

    secon_min=word_forest[0];
    sawp(word_forest[0],word_forest[k]);
    perc_down(word_forest,0,k);
    k--;
    printf("secon\n");
    print_huffman_forest(word_forest,j);

    merge_tree=malloc(sizeof(struct huffman_tree));
    merge_tree->key=malloc(10);
    merge_tree->key[0]='m';
    merge_tree->key[1]=k+'0';
    merge_tree->weight=min->weight+secon_min->weight;
    merge_tree->left=min;
    merge_tree->right=secon_min;

    insert(word_forest,merge_tree,++k);
    printf("after insert\n");
    print_huffman_forest(word_forest,j);
  }
  return word_forest[0];
}
huffman_tree *perc_down(huffman_tree *word_forest[],int i,int n)
{
  int child;
  huffman_tree *temp;
  temp=word_forest[i];
  
  for(; 2*i+1<n; i=child){
    child = i*2+1;
    if(child<n-1 && word_forest[child]->weight>word_forest[child+1]->weight)
      child++;
    if(temp->weight>word_forest[child]->weight)
      word_forest[i]=word_forest[child];
    else
      break;
  }
  word_forest[i]=temp;
  return word_forest[0];
}
void insert(huffman_tree *word_forest[], huffman_tree *new,int n)
{
  int i;
  for(i=n; new->weight < word_forest[i/2]->weight && i/2>0;i /=2){
     word_forest[i]=word_forest[i/2];
  }
  word_forest[i]=new;
}
void print_huffman_tree(huffman_tree *huff)
{
  if(!huff){
    printf("over");
    return;
  }
  printf("(");
  printf("[key:%s weight:%d] ",huff->key,huff->weight);
  print_huffman_tree(huff->left);
  print_huffman_tree(huff->right);
  printf(")");
}
void print_huffman_code(huffman_tree *huff,char *prefix,huffman_code_list *code_list)
{
  char lcode[1000],rcode[1000],*lpcode,*rpcode;
  lcode[0]='\0';
  rcode[0]='\0';
  lpcode=lcode;
  rpcode=rcode;
  /*find the leaf node*/
  if(huff && !huff->left && !huff->right){
    /*pcode = strcat(pcode,prefix);*/
    printf("[key:%s weight:%d code:%s] ",huff->key,huff->weight,prefix);
    huffman_code_list *new_node=malloc(sizeof(struct huffman_code_list));
    new_node->key=huff->key;
    new_node->code=prefix;
    new_node->next=code_list->next;
    code_list->next=new_node;
    return;
  }
  strcat(lpcode,prefix);
  strcat(rpcode,prefix);
  print_huffman_code(huff->left,strcat(lpcode,"0"));
  print_huffman_code(huff->right,strcat(rpcode,"1"));
}
