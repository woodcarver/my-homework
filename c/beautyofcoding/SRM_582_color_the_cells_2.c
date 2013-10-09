#include<stdio.h>
#include<stdlib.h>

enum{
	UNPAINT=-1,
	PAINTED=0
};
int N;/*the number of the cells*/
int *dry_time;
int *cell_state;
//int *cell_state_bak;  //can not be a global variable
int opt_time;/*the optimail time*/

void get_data_from_keyboard()
{
	int i;
	printf("Please enter the number of cells:\n");
	scanf("%d",&N);
	
	dry_time=calloc(N,sizeof(int));
	cell_state=calloc(N,sizeof(int));
	//cell_state_bak=calloc(N,sizeof(int));
	printf("Please enter the dry time of cells:\n");
	for(i=0; i<N; i++)
		scanf("%d",dry_time+i);
	for(i=0; i<N; i++){
		cell_state[i]=-1;
		//cell_state_bak[i]=-1;
	}
	/*make the up bound*/
	opt_time=0;
	for(i=0; i<N; i++)
		opt_time+=dry_time[i];
	opt_time+=N;
	printf("\nthe opt_time is:%d\n\n",opt_time);
}
void print_array(int *array,int n,char *msg)
{
	int i;
	printf("\n%s:",msg);
	for(i=0; i<n; i++)
		printf("%d ",array[i]);
	printf("\n");
}
void update_cell_state(int *cell_state_bak)
{
	int i;
        /*update the dry time state of each cell*/
        for(i=0; i<N; i++){
                cell_state_bak[i]=cell_state[i];
                if(cell_state[i]>0)
                        cell_state[i]--;
        }
}
void backup_cell_state(int *cell_state_bak)
{
	int i;
	printf("backup cell\n");
	for(i=0; i<N; i++)
		cell_state[i]=cell_state_bak[i];
}
int min_time(int rest_cell,int pos,int time,int level)
{
	int i,cell_state_bak[N],found;
	printf("\n");
	for(i=level; i>=0; i--)
		printf("--");
	printf("rest_cell:%d pos:%d time:%d level:%d\n",rest_cell,pos,time,level);
	print_array(cell_state,N,"cell state");
	if(time>opt_time){
		printf("time is out\n");
		return -1;
	}
	if(rest_cell<1 && time<=opt_time){
		printf("found the restult\n");
		return opt_time=time;
	}


	/*try every fessible solutions*/
	if((pos==0 && cell_state[pos+1]>0) || (pos==N-1 && cell_state[pos-1]>0) || cell_state[pos+1]>0 && cell_state[pos-1]>0){
		printf("waiting for drying\n");
		update_cell_state(cell_state_bak);
		found=min_time(rest_cell,pos,time+1,level+1);
		backup_cell_state(cell_state_bak);
		return found;
	}

	if(pos>0 && cell_state[pos-1]<=0){
		printf("move to left\n");
		update_cell_state(cell_state_bak);
		found=min_time(rest_cell,pos-1,time+1,level+1);
		backup_cell_state(cell_state_bak);
	}
	if(pos>0 && cell_state[pos-1]==UNPAINT){
		printf("paint left\n");
		update_cell_state(cell_state_bak);
		cell_state[pos-1]=dry_time[pos-1];
		found=min_time(rest_cell-1,pos,time+1,level+1);
		backup_cell_state(cell_state_bak);
	}
	if(pos<N-1 && cell_state[pos+1]==UNPAINT){
		printf("paint right\n");
		update_cell_state(cell_state_bak);
		cell_state[pos+1]=dry_time[pos+1];
		found=min_time(rest_cell-1,pos,time+1,level+1);
		backup_cell_state(cell_state_bak);
	}
	if(pos<N-1 && cell_state[pos+1]<=0){
		printf("move to right\n");
		update_cell_state(cell_state_bak);
		found=min_time(rest_cell,pos+1,time+1,level+1);
		backup_cell_state(cell_state_bak);
	}
	if(found<0)
		printf("backtracking\n");

	for(i=level; i>=0; i--)
		printf("--");
	printf("end\n");
	return found;
}


int main()
{
	get_data_from_keyboard();
	min_time(N,0,0,0);
	printf("The minimal time is:%d\n",opt_time);
}
