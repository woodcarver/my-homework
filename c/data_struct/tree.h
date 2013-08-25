#ifndef _TREE_H

typedef struct tree_node *tree;
typedef struct tree_node *tree_node;

tree insert_tree1(tree t, int x);
tree insert_tree2(tree *t, int x);
tree insert_tree3(tree *t, int x);

void replace_node(tree_node *t, tree_node snode);
tree_node find_min(tree_node t);
#endif

struct tree_node{
	int element;
	tree left;
	tree right;
};
