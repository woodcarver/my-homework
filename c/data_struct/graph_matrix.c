#include<stdio.h>
int **matrix;
int H;
int W;

void build_graph_from_file(char *filename)
{
        int i,j;
        FILE *file;
        if(!(file=fopen(filename,"r"))){
                fprintf(stderr,"can not open the file(%s)!\n",filename);
                exit(1);
        }

        fscanf(file,"%d",&H);
        fscanf(file,"%d",&W);

        matrix=calloc(H,sizeof(int *));
        for(i=0; i<H; i++){
                matrix[i]=calloc(W,sizeof(int *));
		for(j=0; j<W; j++)
                	fscanf(file,"%d",&matrix[i][j]);
        }
}

void print_graph()
{
        int i,j;
        printf("\nthe graph:\n");
        for(i=0; i<H; i++){
                for(j=0; j<W; j++)
                        printf("%d ",matrix[i][j]);
                printf("\n");
        }
        printf("\n\n");
}
