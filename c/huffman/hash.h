#ifndef _HASH_H
#define _HASH_H
#define HASH_OK 1
#define HASH_ERROR 0
#define HASH_TABLE 50
enum hash_status{
  FULL,
  DELETE,
  EMPTY
};
typedef struct cell{
  char *key;
  unsigned int count;
  enum hash_status info;
}cell;
typedef struct hash{
  int count;
  int table_size;
  cell *the_cells;/*cell **the_cells;*/
}hash;

typedef unsigned int inx;
inx hash_map(const char *key,inx table_size);
hash *hash_create(inx table_size);
int hash_add(hash **h,const char *key,unsigned int count);
cell *hash_find(hash **h,const char *key);
/*use the soft delete*/
int hash_delete(hash *h,const char *key);
void hash_print(hash *h);
#endif
