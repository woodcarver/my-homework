#include<stdio.h>
#include<malloc.h>

typedef struct node *node;
typedef node position;
typedef struct node{
	int coefficient;
	int exponent;
	struct node * next;
} *polynomial;

int copy_node(node from_node, polynomial *to_poly)
{	
	polynomial new_node = malloc(sizeof(struct node));
	if(new_node == NULL){
		printf("out of space\n");
		return 0;
	}
	new_node->coefficient = from_node->coefficient;
	new_node->exponent = from_node->exponent;
	new_node->next = NULL;
	
	if(*to_poly == NULL)
		return 0;

	(*to_poly)->next = new_node;
	*to_poly = new_node;

	printf("copy (%d,x%d)\n",from_node->coefficient,from_node->exponent);
	return 1;
}

int add_polynomial(const polynomial ppoly1, const polynomial ppoly2, polynomial poly_sum)
{
	int temp_coe;
	position poly1 = ppoly1->next;
	position poly2 = ppoly2->next;
	
	ppoly1->next->exponent = 10000;	
	
	printf("----start to add----\n");
	while(poly1 && poly2){
		if(poly1->exponent > poly2->exponent){
			copy_node(poly1,&poly_sum);

			poly1 = poly1->next;
		}
		else if(poly1->exponent < poly2->exponent){
			copy_node(poly2,&poly_sum);

			poly2 = poly2->next;	
		}
		else{
			temp_coe = poly1->coefficient + poly2->coefficient;

			if(temp_coe){
				polynomial new_node = malloc(sizeof(struct node));
				if(new_node == NULL){
					printf("out of space\n");
					return 0;
				}
				new_node->coefficient = temp_coe;
				new_node->exponent = poly1->exponent;
				new_node->next = NULL;
				
				copy_node(new_node,&poly_sum);

				poly1 = poly1->next;
				poly2 = poly2->next;
			}
		}
	}

	while(poly1){
		copy_node(poly1,&poly_sum);
		poly1 = poly1->next;
	}
	while(poly2){
		copy_node(poly2,&poly_sum);
		poly2 = poly2->next;
	}
}

polynomial create_poly(void)
{
	polynomial poly = malloc(sizeof(struct node));
	if(poly)
		poly->next = NULL;
	return poly;
}

int insert_node(polynomial poly,int coe,int exp)
{
	polynomial new_node = malloc(sizeof(struct node));
	if(new_node == NULL){
		printf("out of space\n");
		return 0;
	}
	new_node->coefficient = coe;
	new_node->exponent = exp;
	new_node->next = NULL;
	
	if(poly == NULL)
		return 0;
	
	while(poly->next)
		poly = poly->next;
	poly->next = new_node;
}
void print_poly(polynomial poly)
{
	poly = poly->next;
	while(poly){
		printf("(%d,x%d)+",poly->coefficient,poly->exponent);
		poly = poly->next;
	}
	printf("\n");
}

void dispose_poly(polynomial poly)
{
	node temp_node;

	if(!poly)
		return;	
	while(poly->next){
		temp_node = poly->next;
		poly->next = temp_node->next;
		free(temp_node);
	}
	free(poly);
}
int main()
{
	polynomial poly1 = create_poly();
	insert_node(poly1,10,1000);
	insert_node(poly1,5,14);
	insert_node(poly1,1,0);
	print_poly(poly1);

	polynomial poly2 = create_poly();
	insert_node(poly2,3,1990);
	insert_node(poly2,-2,14);
	print_poly(poly2);
	
	polynomial poly3 = create_poly();

	add_polynomial(poly1,poly2,poly3);

	print_poly(poly3);
	/*free the memory*/
	dispose_poly(poly1);
	dispose_poly(poly2);
	dispose_poly(poly3);
}
