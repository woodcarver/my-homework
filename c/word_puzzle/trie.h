#ifndef _TRIE_H
#define _TRIE_H
struct trie;
typedef struct trie trie;
trie * trie_create();
//trie * trie_find(trie **t, char *word);
//void trie_insert(trie *t, char *word);
#endif

struct trie{
	char item;
	int is_word;
	trie *sibling;
	trie *first_child;
	//int children_num;
};
