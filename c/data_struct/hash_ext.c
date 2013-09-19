#include<stdio.h>
#include<malloc.h>
#include<string.h>

#define MIN_TABLE_SIZE 10

typedef struct list_node *list_node;
typedef struct list_node *list;
typedef struct hash_tbl * hash_tbl;

struct list_node{
	char *key;
	char *value;
	list_node next;
};

struct hash_tbl{
	unsigned int table_size;
	list_node *list;
};

/*how to impliment the function??????*/
unsigned int next_prime(unsigned int num)
{
	return num;
}

hash_tbl initial(unsigned int table_size)
{
	int i;
	hash_tbl table;
	
	/*if(table_size < MIN_TABLE_SIZE){
		printf("table size is too small!!!\n");
		return NULL;
	}*/

	table = malloc(sizeof(struct hash_tbl));
	if(!table){
		printf("can alloc space!!!\n");
		return NULL;
	}
	
	table->table_size = next_prime(table_size);

	table->list = malloc(sizeof(list_node)*table->table_size);

	/*need to initial the list or you can not figure out which one is unoccupted list_node*/
	/*for(i=0; i<table->table_size; ++i)
		table->list[i] = NULL;*/

	return table;
}

unsigned int hash(hash_tbl table,const char *key)
{
	unsigned int sum = 0;

	while(*key != '\0')
		sum += *key++;
	return sum % table->table_size;
}

list_node find(hash_tbl table, const char *key)
{
	list_node row;
	
	row = table->list[hash(table,key)];
	while(strcmp(row->key,key) && !row)
		row = row->next;
	return row;
}

int insert(hash_tbl table, const char *key, const char * value)
{
	list_node *row = NULL,new;//此时的row必须赋予初值
	
	row = table->list + hash(table,key);
	
	while(*row && strcmp((*row)->key,key))//(strcmp(row->key,key) && !row)
		*row = (*row)->next;

	if(!*row){
		*row = malloc(sizeof(struct list_node));
		(*row)->key = malloc(20);
		(*row)->value = malloc(20);
		strcpy((*row)->key,key);
		strcpy((*row)->value,value);
		(*row)->next = NULL;
		//row = new;
	}
	else
		strcpy((*row)->value,value);

	return 1;
}

void print_hash_tbl(hash_tbl table)
{
	int i;
	list_node row;

	for(i=0; i < table->table_size ; ++i){
		row = table->list[i];
		while(row){
			printf("%s %s",row->key,row->value);
			row = row->next;
		}
	}
	printf("\n");
}

int main()
{

	hash_tbl table;
	table = initial(3);
	
	insert(table,"key1","value1");
	insert(table,"key2","value2");
	print_hash_tbl(table);
}
