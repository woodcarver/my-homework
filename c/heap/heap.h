#ifndef _Heap_H
struct heap
{
 	int capacity;
	int size;
	int *items;
};

typedef struct heap heap;

heap *heap_create(int capacity);
void heap_insert(heap * h, int item);
int heap_delete_min(heap * h);
void heap_distory(heap * h);
#endif
