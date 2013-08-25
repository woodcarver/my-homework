#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#include "heap.h"

#define MAX_CAPACITY 1000

heap *heap_create(int capacity)
{
	if(capacity > MAX_CAPACITY){
		printf("the heap can not be so big!\n");
		exit(0);
	}
	heap * h = malloc(sizeof(struct heap));
	if(!h){
		printf("can not alloc space for heap!\n");
		exit(0);
	}
	
	h->capacity = capacity;
	h->size = 0;
	h->items = malloc(sizeof(int)*capacity);
	if(!h->items){
		printf("can not alloc space for items!\n");
		exit(0);
	}
	return h;
}

void heap_insert(heap * h, int item)
{
	int i;
	if(h->size+1 > h->capacity){
		printf("the heap is full!\n");
		return;
	}
	
	/*for(i=h->size++; item < h->items[i];i /= 2){*/
	for(i=++h->size; item < h->items[i/2];i /= 2){
		printf("parent item:%d, item:%d\n",h->items[i/2],h->items[i]);
		h->items[i] = h->items[i/2];
	}
	printf("current pos:%d item:%d\n",i,item);
	h->items[i] = item;
}

int heap_delete_min(heap * h)
{
	int i,min,last,child;
	if(h->size <= 0){
		printf("the heap is empty!\n");
		return -1;
	}
		
	min = h->items[1];/*be carefull that the 0th element is a sentinel.*/
	last = h->items[h->size--];

	for(i=1; i<=h->size/2; i=child){
		child = 2*i;
		if(h->items[child] > h->items[child+1])
			child++;
		if(h->items[child] < last)
			h->items[i]=h->items[child];
		else
			break;
	}
	
	h->items[i]=last;
	return min;
}
void heap_distory(heap * h)
{
	free(h->items);
	free(h);
}
void heap_print(heap * h)
{
	int i;
	for(i=1; i<=h->size; i++)
		printf("%d ",h->items[i]);
	printf("\n");
}

int main()
{
	heap * h;
	h = heap_create(20);
	heap_insert(h,24);
	heap_insert(h,21);
	heap_insert(h,6);
	heap_insert(h,16);
	heap_insert(h,63);
	heap_print(h);
	printf("delete min:%d\n",heap_delete_min(h));
	heap_print(h);
	heap_distory(h);
}
