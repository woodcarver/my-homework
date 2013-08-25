#include<stdio.h>
int main()
{
	double nc;
	int i,j,c;
	char output[1000];
	i = j = 0;
	for(nc = 0;(c=getchar()) != EOF; ++nc){
		printf("i:%d j:%d\n",i,j);
		 if(c == '\t' || c == '\b'){
			output[i++] = '\\';
			output[i++] = 't';
			printf("slash\n");
		}
		else if(c != ' '){
			if(j){
				output[i++]=' ';
				j = 0;
			}
			output[i++] = c;
		}
		else
			++j;	
	}
		;
	printf("%.0f\n",nc);
	printf("%s\n",output);
}
