#include<stdio.h>
#include<string.h>

#define MAP_SIZE 9
#define TRUE 1
#define FALSE 0

int map[MAP_SIZE][MAP_SIZE];

void print_map(int (*map)[MAP_SIZE],int size);
int find_avai_set(int (*map)[MAP_SIZE],int row,int col,int *avai_set)
{
	int i,j,set_size,temp;
	*avai_set=~(~(unsigned int)0<<MAP_SIZE);
	set_size=MAP_SIZE;

	for(i=0; i<MAP_SIZE; i++){
		if(map[row][i]!=0){
			*avai_set^=1<<(map[row][i]-1);
			set_size--;
		}
	}
	for(j=0;j<MAP_SIZE; j++){
		if(map[j][col]!=0){
			/*need test the result is smaller,beacuase there will be duplicate number which recover the result of row*/
			temp=*avai_set^1<<(map[j][col]-1);
			if(temp<*avai_set){
				*avai_set=temp;
				set_size--;
			}
		}
	}
	return set_size;
}

int sudoku(int (*map)[MAP_SIZE],int row,int col)
{
	printf("row:%d col:%d\n",row,col);

	int avai_set,avai_size,i,j,found,num;
	found=FALSE;
	
	if(row>MAP_SIZE-1)
		return TRUE;
	
	avai_size=find_avai_set(map,row,col,&avai_set);
	printf("avai_size:%d set:%d\n",avai_size,avai_set);
	if(!avai_size)
		return FALSE;

	for(i=avai_set; i>0 && !found; i=avai_set){
		num=1;
		while(!(i%2)){
			num++;
			i=i/2;
		}
		avai_set &=(avai_set-1);

		map[row][col]=num;
		//printf("new cell--row:%d col:%d value:%d\n\n",row,col,num);
		//print_map(map,MAP_SIZE);
		if(col==MAP_SIZE-1){
			found=sudoku(map,row+1,0);
		}
		else
			found=sudoku(map,row,col+1);
		if(!found){
			printf("backtracking at row:%d col:%d\n\n",row,col);
			/*recover the map*/
			for(j=col; j<MAP_SIZE; j++)
				map[row][j]=0;
			for(i=row+1; i<MAP_SIZE; i++)
				for(j=0; j<MAP_SIZE; j++)
					map[i][j]=0;
		}
	}
	return found;
}

void print_map(int (*map)[MAP_SIZE],int size)
{
	int i,j;
	for(i=0; i<size; i++){
		for(j=0; j<size; j++)
			printf(" %d ",map[i][j]);
		printf("\n");
	}
	printf("\n");
}

int main()
{
	memset(map,0,MAP_SIZE*MAP_SIZE*sizeof(int));
	sudoku(map,0,0);
	print_map(map,MAP_SIZE);
}
