#include<stdio.h>
int main()
{
	const double value=0.0f;
	double *ptr=NULL;
	ptr=(double *)&value;
	printf("\n%f\n",*ptr);
	return 0;
}
