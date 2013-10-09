#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int *Y;
int *query;

int *increas_seq;
int *decreas_seq;
int *parall_seq;
int increas_count;
int decreas_count;
int parall_count;
int n;

int (*seq_cmp)(int a,int b);

void print_array(int *a,int n);

void get_data_from_keyboard()
{
	int i;

	printf("enter n:\n");
	scanf("%d",&n);

	Y=calloc(n,sizeof(int));
	query=calloc(n,sizeof(int));
	increas_seq=calloc(2*n,sizeof(int));
	decreas_seq=calloc(2*n,sizeof(int));
	parall_seq=calloc(2*n,sizeof(int));

	printf("enter Y:\n");
	for(i=0; i<n; i++)
		scanf("%d",Y+i);
	for(i=0; i<n; i++)
		scanf("%d",query+i);
}

int inc_cmp(int a,int b)
{
	return a<b?1:0;
}
int dec_cmp(int a,int b)
{
	return a>b?1:0;
}
int para_cmp(int a,int b)
{
	return a==b?1:0;
}

void get_sub_sequences(int *seq_count,int *seq,int (*seq_cmp)(int a,int b))
{
	int i,count;
	*seq_count=0;
	seq[0]=Y[0];
	count=0;
	for(i=0; i<n-1; i++)
		if(seq_cmp(Y[i],Y[i+1]))
			count++;
		else{
			if(count>0){
				seq[2*(*seq_count)+1]=Y[i];
				(*seq_count)++;
				count=0;
			}
			seq[2*(*seq_count)]=Y[i+1];
		}
	if(count>0){
		seq[2*(*seq_count)+1]=Y[i];
		(*seq_count)++;
	}
}

int *count_solution()
{
	int i,j,k,t,*res;
	res=calloc(n,sizeof(int));

	seq_cmp=inc_cmp;
	get_sub_sequences(&increas_count,increas_seq,seq_cmp);
	print_array(increas_seq,2*n);

	seq_cmp=dec_cmp;
	get_sub_sequences(&decreas_count,decreas_seq,seq_cmp);
	print_array(decreas_seq,2*n);

	seq_cmp=para_cmp;
	get_sub_sequences(&parall_count,parall_seq,seq_cmp);
	print_array(parall_seq,2*n);

	printf("inc_count:%d dec_count:%d para_count:%d \n",increas_count,decreas_count,parall_count);

	for(k=0; k<n; k++){
		if(query[k]==Y[0] && Y[0]==increas_seq[0])
			res[k]++;
		if(query[k]==Y[n-1] && Y[n-1]==increas_seq[2*(increas_count-1)+1])
			res[k]++;

		for(i=0; i<increas_count; i++)
			if(query[k]>increas_seq[2*i] && query[k]<increas_seq[2*i+1])
				res[k]++;
		for(j=0; j<decreas_count; j++)
			if(query[k]<=decreas_seq[2*j] && query[k]>=decreas_seq[2*j+1])
				res[k]++;
		for(t=0; t<parall_count; t++)
			if(query[k]==parall_seq[2*t]){
				res[k]=-1;
				break;
			}
	}
	return res;
}
void print_array(int *a,int n)
{
	int i;
	//printf("\n");
	for(i=0; i<n; i++)
		printf("%d ",a[i]);
	printf("\n");
}
int main()
{
	int *res;
	get_data_from_keyboard();
	res=count_solution();
	print_array(res,n);
}
