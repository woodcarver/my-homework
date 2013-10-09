#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int ***color;
char **cells;
int H;
int W;

enum color{
	R=1,
	G=2,
	B=4
};
enum type{
	N=0,
	Z=1
};

void get_data_from_file(char *filename)
{
	int i,j;
	FILE *file;
	if(!(file=fopen(filename,"r"))){
		fprintf(stderr,"can not open the file!\n");
		exit(1);
	}

	fscanf(file,"%d",&H);
	fscanf(file,"%d",&W);

	color=calloc(H,sizeof(int *));
	for(i=0; i<H; i++){
		color[i]=calloc(W,sizeof(int *));
		for(j=0; j<W; j++)
			color[i][j]=calloc(4,sizeof(int *));
	}

	cells=calloc(H,sizeof(int *));
	for(i=0; i<H; i++){
		cells[i]=calloc(W,sizeof(int *));
		fscanf(file,"%s",cells[i]);
	}
}
void print_data()
{
	int i,j;
	printf("H:%d W:%d\n",H,W);
	for(i=0; i<H; i++){
		for(j=0; j<W; j++)
			printf("%c",cells[i][j]);
		printf("\n");
	}
}
void print_color()
{
	int i,j,k;
	for(i=0; i<H; i++){
		for(j=0; j<W; j++){
			printf("{");
			for(k=0; k<4; k++)
				printf("%d,",color[i][j][k]);
			printf("}");
		}
		printf("\n");	
	}
}
int draw_cell(int type,int i,int j);
char *is_colorable()
{
	int i,j,type;
	for(i=0; i<H; i++)
		for(j=0; j<W; j++){
		//	printf("i:%d j:%d\n",i,j);
			type=cells[i][j]=='N'?N:Z;
		//	printf("%d\n",draw_cell(type,i,j));
			if(!draw_cell(type,i,j))
				return "No";
		}
	return "Yes";
}

int draw_cell(int type,int i,int j)
{
	int shape[2][2][4]={
		{{1,1,1,0},{1,0,1,1}},
		{{1,1,0,1},{0,1,1,1}}
	};

	int diagonal[2][4]={
		{-1,3,-1,1},
		{2,-1,0,-1}
	};

	int k,t,choose_color;
	int color_set1=7;
	int color_set2=7;
	//color the point is not belong the diagonal
	/*if(type=N)
		if(!color[i][j][1])
			color[i][j][3]=color[i][j][1];
		else if(!color[i][j][3])
			color[i][j][1]=color[i][j][3];	

	if(type=Z)
		if(!color[i][j][0])
			color[i][j][2]=color[i][j][0];
		else if(!color[i][j][2])
			color[i][j][0]=color[i][j][2];	
	*/
	for(k=0; k<4; k++)
		if(color[i][j][k]){
			if(shape[type][0][k])
				color_set1&=~color[i][j][k];
			if(shape[type][1][k])
				color_set2&=~color[i][j][k];
			
		}
	printf("i:%d j:%d color_set1:%d color_set2:%d\n",i,j,color_set1,color_set2);
	//if(!color_set1 || !color_set2)
	//	return 0;
	
	for(k=0; k<4; k++){
		if(color[i][j][k])
			continue;
		
		printf("color_set1:%d color_set2:%d\n",color_set1,color_set2);
		//if(!color_set1 || !color_set2)
		//	return 0;
		
		if(shape[type][0][k]){
			if(!color_set1)
				return 0;
			if(diagonal[type][k]!=-1 && (color[i][j][diagonal[type][k]]&color_set1))
				choose_color=color[i][j][diagonal[type][k]];
			else
				choose_color=color_set1&1?1:(color_set1&2?2:4);
			color[i][j][k]=choose_color;
			color_set1&=~choose_color;
			if(shape[type][1][k])
				color_set2&=~choose_color;
		}
		//must complete the first segment,then the seconde one. or will have conflit.
		else if(shape[type][1][k]){
			if(!color_set2)
				return 0;
			if(diagonal[type][k]!=-1 && (color[i][j][diagonal[type][k]]&color_set2))
                                choose_color=color[i][j][diagonal[type][k]];
			else
				choose_color=color_set2&1?1:(color_set2&2?2:4);
			color[i][j][k]=choose_color;
			color_set2&=~choose_color;
			if(shape[type][1][k])
				color_set1&=~choose_color;
		}
	}
	printf("update the adjancent cells.\n");
	if(i<H-1){
		color[i+1][j][0]=color[i][j][3];
		color[i+1][j][1]=color[i][j][2];
	}
	if(j<W-1){
		color[i][j+1][0]=color[i][j][1];
		color[i][j+1][3]=color[i][j][2];
	}
	printf("\n");
	return 1;
}

int main(int argc,char *argv[])
{
	char *fname;
	if(argc!=2)
		fname="iscolorable";
	else
		fname=argv[1];
	get_data_from_file(fname);
	print_data();
	
	printf("The result:%s\n",is_colorable());
	print_color();
}
