#include<stdio.h>
#include<stdlib.h>
#include<math.h>

int main(int argc,char **argv)
{
	double dvar=atol(argv[1]);
	dvar = ~dvar;
	printf("%f\n",dvar);
	printf("\u3000 \u25a0 \n");
}
