#include<stdio.h>
#include<malloc.h>
#include<string.h>
#include <stdlib.h>
#include "trie_word.h"

#define TRUE 1
#define FALSE 0

trie * trie_create()
{
	trie *t;
	t = malloc(sizeof(struct trie));
	if(!t){
		printf("can not malloc the memory!\n");
		return NULL;
	}
	t->item = '0';
	t->is_word = FALSE;
	t->first_child = NULL;
	t->sibling = NULL;
	return t;
}

trie * trie_find(trie *t, const char *word)
{
	int i,j;
	t = t->first_child;
	for(i = 0; i < strlen(word); ++i){
		while(t && t->item != word[i]){
			t = t->sibling;
		}
		if(!t){
			return NULL;
		}
		else{
			t = t->first_child;
		}
	}
	return t;
}

void trie_insert(trie **t, const char *word)
{
	int i,j;
	t = &((*t)->first_child);
	for(i = 0; i < strlen(word); ++i){
		while(*t && (*t)->item != word[i])
			t = &((*t)->sibling);
			
		if(!(*t)){
			*t = malloc(sizeof(struct trie));
			(*t)->item = word[i];
			(*t)->is_word = FALSE;
			(*t)->sibling = NULL;
			(*t)->first_child = NULL;	
		}
		
		t = &((*t)->first_child);
	}
	/*(*t)->is_word = TRUE;*/
}

void trie_print(trie *t)
{
	trie *pos;
	pos = t;
	while(pos){
		printf("%c ",pos->item);
		if(pos->first_child){
			printf("\n");
			printf("[first_child]");
			trie_print(pos->first_child);
		}
		printf("[%c sibling]",pos->item);
		pos = pos->sibling;
	}
	printf("\n");
}

int main()
{
	int c;
	trie * t;
	t = trie_create();
	if(!t){
		printf("can not create a trie!\n");
		exit(1);
	}
	/*while((c=getchar()) != EOF){
		if(c == '\n')
			trie_print(pos);
	}*/
	trie_insert(&t,"the");
	trie_insert(&t,"tea");
	trie_insert(&t,"that");
	trie_insert(&t,"am");
	trie_print(t);
}
