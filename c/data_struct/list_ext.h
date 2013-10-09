#ifndef _LIST_H_

struct node;
typedef struct node node;
typedef struct node *ptr_to_node;
typedef ptr_to_node list;
typedef ptr_to_node position;

list make_empty(list l);
int is_empty(list l);
int is_last(list l, position p);
position find_node(list l, char x);
void delete_node(list l, char x);
void insert_node(list l, char x, position p);
void delete_list(list l);

struct node{
	char element;
	position next;
};
#endif
