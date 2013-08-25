#include "hash.h"
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

hash *hash_create(inx table_size)
{
  int i;
  hash *h = malloc(sizeof(struct hash));
  if(!h){
    fprintf(stderr,"failed to alloc memory to hash table!\n");
    exit(1);
  }
  h->table_size = table_size;
  h->count = 0;
  h->the_cells = malloc(table_size*sizeof(struct cell));
  if(!h->the_cells){
    fprintf(stderr,"failed to alloc memory to hash element!\n");
    exit(1);
  }
  for(i = 0; i < table_size; i++)
     h->the_cells[i].info = EMPTY;
  return h;
}

inx hash_map(const char *key,inx table_size)
{
  inx inx_val = 0;
  while(*key != '\0')
    inx_val = (inx_val << 5) + *key++;
  //printf("table size:%d inx_var:%d pos:%d\n",table_size,inx_val%table_size);
  return inx_val%table_size;
}

int hash_add(hash **h, const char *key,unsigned int count)
{
  cell *pos;
  pos = hash_find(h,key);
  if(pos->info != FULL){
    /*strcpy(pos->key,key); can not do this,beacause the memory for key is not alloced!*/
    pos->key = malloc(strlen(key)+1);
    strcpy(pos->key,key);
    /*pos->key = key;*/
    pos->count = count;
    pos->info = FULL;
  }
  printf("add a hash->key:%s\n",key);
  return HASH_OK; 
}

cell *hash_find(hash **h, const char *key)
{
  int i,scmp;
  inx inx_value;
  inx_value = hash_map(key,(*h)->table_size);
  i = inx_value;
  printf("the key find pos:%d\n",inx_value);  
  while((*h)->the_cells[i].info != EMPTY && strcmp((*h)->the_cells[i].key,key)){
    i = i * 2;
    if(i > (*h)->table_size)
        i -= (*h)->table_size;
  }
  printf("the collison pos:%d\n",i);  
  return &((*h)->the_cells[i]);
}
void hash_print(hash *h)
{
  int i;
  printf("\n----------the hash table---------------\n");
  for(i=0; i<h->table_size; i++)
    printf("key:%s count:%d\n",h->the_cells[i].key,h->the_cells[i].count);
  printf("\n");
}
