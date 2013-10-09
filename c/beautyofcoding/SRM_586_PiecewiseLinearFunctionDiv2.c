#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int *Y;
int *query;

int *increas_seq;
int *decreas_seq;
int increas_count;
int decreas_count;
int n;

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

	printf("enter Y:\n");
	for(i=0; i<n; i++)
		scanf("%d",Y+i);
	for(i=0; i<n; i++)
		scanf("%d",query+i);
}

void init_increas_sequences(int *Y)
{
	int i,count;
	increas_count=0;
	increas_seq[0]=Y[0];
	count=0;
	for(i=0; i<n-1; i++)
		if(Y[i]<=Y[i+1])
			count++;
		else{
			if(count>0){
				increas_seq[2*increas_count+1]=Y[i];
				increas_count++;
				count=0;
			}
			increas_seq[2*increas_count]=Y[i+1];
		}
	if(count>0){
		increas_seq[2*increas_count+1]=Y[i];
		increas_count++;
	}
}

void init_decreas_sequences(int *Y)
{
	int i,count;
	decreas_count=0;
	decreas_seq[0]=Y[0];
	count=0;
	for(i=0; i<n-1; i++)
		if(Y[i]>=Y[i+1])
			count++;
		else{
			if(count>0){
				//printf("record the number:%d\n",Y[i]);
				decreas_seq[2*decreas_count+1]=Y[i];
				decreas_count++;
				count=0;
			}
			decreas_seq[2*decreas_count]=Y[i+1];
		}
	if(count>0){
		decreas_seq[2*decreas_count+1]=Y[i];
		decreas_count++;
	}
}

int *count_solution()
{
	int i,j,k,t,*res;
	res=calloc(n,sizeof(int));

	init_increas_sequences(Y);
	print_array(increas_seq,2*n);

	init_decreas_sequences(Y);
	print_array(decreas_seq,2*n);

	printf("inc_count:%d dec_count:%d \n",increas_count,decreas_count);

	for(k=0; k<n; k++){
		/*for(t=0; t<n; t++)
			if(query[k]==Y[t])
				res[k]++;*/
		//the first and the last point
		if(query[k]==Y[0] && Y[0]!=decreas_seq[0])
			res[k]++;
		if(query[k]==Y[n-1] && Y[n-1]!=decreas_seq[2*(descreas_count-1)])
			res[k]++;
		
		for(i=0; i<increas_count; i++)
			if(query[k]>increas_seq[2*i] && query[k]<increas_seq[2*i+1])
				res[k]++;
		for(j=0; j<decreas_count; j++)
			/*parallar line*/
			if(query[k]==decreas_seq[2*j] && decreas_seq[2*j]==decreas_seq[2*j+1]){
					res[k]=-1;
					break;
				}
			else if(query[k]<=decreas_seq[2*j] && query[k]=>decreas_seq[2*j+1])
				res[k]++;
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
