#define MAXVAL 100
struct queue{
	int header;
	int tail;
	int val[MAXVAL+1];
};

void enQueue(struct queue *queue_r,double f)
{
	printf("en queue %f\n",f);
	if(queue_r->tail < MAXVAL){
		queue_r->val[queue_r->tail++] = f;
		queue_r->val[queue_r->tail] = '\0';
	}
	else
		printf("the queue is full!\n");
}
double deQueue(struct queue *queue_r)
{
	printf("de queue\n");
	if(queue_r->header < MAXVAL)
		queue_r->val[queue_r->header++];
	else
		printf("the queue is empty now!\n");
}
void print_queue(struct queue queue_r)
{
	int i;
	printf("queue:");
	for(i = 0; '\0' != queue_r.val[i]; ++i)
		printf(" %d",queue_r.val[i]);
	printf("\n");
}
