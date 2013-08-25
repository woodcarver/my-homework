#include<stdio.h>

void chess_position()
{
	int A_row,A_col,B_row,B_col,map_num;
	for(A_row=0; A_row<3; A_row++)
		for(B_row=0; B_row<3; B_row++)
			for(A_col=0; A_col<2; A_col++)
				for(B_col=A_col+1; B_col<3; B_col++){
					printf("the solution:(%d,%d),(%d,%d)\n",A_row,A_col,B_row,B_col);
					map_num=(A_row*3+B_row)*9+(A_col*3+B_col);
					printf("number:%d %d %d\n\n",A_row*3+B_row,A_col*3+B_col,map_num);
				}
}

int main()
{
	chess_position();
}
