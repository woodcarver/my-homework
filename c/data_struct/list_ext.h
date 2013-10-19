#ifndef _LIST_H_

struct list_node;
typedef struct list_node list_node;
typedef struct list_node list;

list *make_empty(list *l);
int list_is_empty(list *l);
int list_is_last(list *l, list_node *p);
list_node *list_find(list *l, int x);
void list_delete(list *l, int x);
void list_insert(list **l, int x);
void list_destory(list *l);

struct list_node{
	int element;
	list_node *next;
};
#endif
