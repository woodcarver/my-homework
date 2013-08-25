#include<stdio.h>
#include<malloc.h>
#include<string.h>
#include <stdlib.h>
#include "trie.h"

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

trie ** trie_find(trie **t, char *word)
{
	int i,j;
	//trie *pos;
	//pos = (*t)->first_child;
	//*t = (*t)->first_child;
	t = &((*t)->first_child);
	for(i = 0; i < strlen(word); ++i){
		printf("current word:%c strindex:%d",word[i],i);
		while(*t && (*t)->item != word[i]){
			printf("sibling:%c\n",(*t)->item);
			//*t = (*t)->sibling;
			t = &((*t)->sibling);
		}
		/*if(*t && (*t)->item == word[i])
			*t = (*t)->first_child;*/

	/*	if(!(*t))
			return NULL;
		else*/
		if(*t){
			//t = &((*t)->first_child);
			//printf("first_child:%c\n",(*t)->item);
		}
		else{
			break;
		}
	}
	printf("\n");
	//*t = pos;
	return t;
}

void trie_insert(trie *t,char *word)
{
	int i,j,last;
	char str_char[2];
	str_char[1]='\0';
	trie **pos, *new;
	pos = &t;
	for(i = 0; i < strlen(word); ++i){
		//str_char ={word[i]};  
		//pos = trie_find(pos, word+i);
		str_char[0] = word[i];
		pos = trie_find(pos,str_char);
		if(!(*pos)){
			*pos = malloc(sizeof(struct trie));	
		//	t->children[last] = malloc(sizeof(struct trie));
			if(!(*pos)){
				printf("failed to malloc memory for new word:%s %c",word,word[i]);
				return;
			}
		
			/*new->item = word[i];
			new->is_word = FALSE;
			new->first_child = NULL;*/

			(*pos)->item = word[i];
			(*pos)->is_word = FALSE;
			(*pos)->sibling = NULL;
			(*pos)->first_child = NULL;	
		}
	}
	(*pos)->is_word = TRUE;
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
	trie_insert(t,"the");
	trie_insert(t,"tea");
	trie_insert(t,"that");
	trie_insert(t,"am");
	trie_print(t);
}
