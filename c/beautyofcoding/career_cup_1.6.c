#include<stdio.h>
#include<stdlib.h>

int n;
int **matrix;
void get_data_from_keyboard()
{
	int i,j;
	printf("enter n:\n");
	scanf("%d",&n);
	printf("enter martix:\n");
	matrix=calloc(n,sizeof(int *));
	for(i=0; i<n; i++){
		matrix[i]=calloc(n,sizeof(int));
		for(j=0; j<n; j++)
			scanf("%3d",&matrix[i][j]);
	}
}
void print_data()
{
	int i,j;
	printf("\n");
	for(i=0; i<n; i++){
		for(j=0; j<n; j++)
			printf("%d ",matrix[i][j]);
		printf("\n");
	}
}

void rotate90(int **matrix,int n)
{
	int temp,i,j;
	for(i=0; i<n; i++)
		for(j=i+1; j<n; j++){
			temp=matrix[i][j];
			matrix[i][j]=matrix[j][i];
			matrix[j][i]=temp;
		}
		
	for(i=0; i<n; i++)
		for(j=0; j<n/2; j++){
			temp=matrix[i][j];
			matrix[i][j]=matrix[i][n-1-j];
			matrix[i][n-1-j]=temp;
		}
}

void rotate90_2(int **matrix,int n)
{
	int  i,j,first,last,offset,top;
	for(i=0; i<n/2; i++){
		first=i;
		last=n-1-i;
		for(j=first; j<last; j++){
			offset=j-first;
			top=matrix[first][j];

			//left->top
			matrix[first][j]=matrix[last-offset][first];
			//bottom->left
			matrix[last-offset][first]=matrix[last][last-offset];
			//right->bottom
			matrix[last][last-offset]=matrix[j][last];
			//top->right
			matrix[j][last]=top;
		}
	}
}
int main(int argc,char *argv[])
{
	get_data_from_keyboard();
	print_data();
	if(argv[1][0]=='2'){
		printf("version2:\n");
		rotate90_2(matrix,n);
	}
	else{
		printf("version1:\n");
		rotate90(matrix,n);
	}
	print_data();
}
