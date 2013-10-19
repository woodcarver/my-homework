#include<stdio.h>
#include<stdlib.h>

struct person{
	int height;	
	int weight;
};
typedef struct person person;

int n;
person **people;

void print_person(person **people,int n)
{
	int i;
	printf("\n");
	for(i=0; i<n; i++)
		printf("(%d,%d) ",people[i]->height,people[i]->weight);
	printf("\n");
}
void get_data_from_keyboard()
{
	int i;
	printf("enter the n:\n");
	scanf("%d",&n);
	
	people=calloc(n,sizeof(person *));
	printf("enter the data set:\n");
	
	for(i=0; i<n; i++){
		people[i]=malloc(sizeof(struct person));
		scanf("%d %d",&(people[i]->height),&(people[i]->weight));
	}
	print_person(people,n);
}

int cmpht(const void *p1,const void *p2)
{
	person *per1,*per2;
	per1=(person *)p1;
	per2=(person *)p2;

	return per1->height < per2->height?1:0;
}
int cmpwt(const void *p1,const void *p2)
{
	person *per1,*per2;
	per1=(person *)p1;
	per2=(person *)p2;

	return per1->weight < per2->weight?1:0;
}

int find_chain(person **people,int n)
{
	int i,count;
	person **start,**subseq;
	subseq=calloc(n,sizeof(person *));

	qsort(people,n,sizeof(struct person),cmpht);
	print_person(people,n);

	count=1;
	start=people;
	for(i=0; i<n; i++){
		if(people[i]->height != people[i+1]->height){
			qsort(start,count,sizeof(struct person),cmpwt);
			count=1;
			start=&people[i+1];
		}
		else{
			count++;
		}
	}
	print_person(people,n);

	/*find the max increasing sub sequence*/
	count=0;
	subseq[0]=people[0];
	for(i=0; i<n; i++){
		if(people[i]->weight > subseq[count]->weight)
			subseq[++count]=people[i];
		else if(people[i]->weight > subseq[count-1]->weight)
			subseq[count]=people[i];
	}
	print_person(subseq,count);

	return count;
}

int main()
{
	get_data_from_keyboard();
	find_chain(people,n);
	return 0;
}
