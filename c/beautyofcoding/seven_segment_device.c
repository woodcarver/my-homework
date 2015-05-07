#include<stdio.h>

void show(char);

int main()
{
	//将要现示1,2,3,4,5,这五个數字
	char input[5] = {1,2,3,4,5};
	char num_egdes[10] = {0x5f,0xa,0x76,0x75,0x2d,0x79,0x7a,0x15,0x7f,0x3d};
	int i;
	for(i=0; i<=4; ++i){
		if(input[i]<0 || input[i]>9){
			printf("input is wrong!");
			break;
		}
		show(num_egdes[input[i]]);
		printf("--input%d\n",input[i]);
	}
	return 0;
}

void show(char number)
{
 	char edge_pos[7] = {0x10,0x8,0x4,0x20,0x2,0x1,0x40};
	char letter[7] = {'_','|','|','_','|','|','_'};
	char position[7] = {'\n',' ','\n','\n',' ','\n','\n'};
	int j;
	for(j=0; j<=6; ++j){
		if(edge_pos[j]&number){
			//printf("%d",segment[j]);
			//printf("%d",j);
			printf("%c",letter[j]);
		}
		printf("%c",position[j]);
	}
	printf("over%d",number);
}
