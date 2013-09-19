#include<stdio.h>
#include<stdlib.h>

int bin_coef(int n, int r)
{
 int i,b;
 if (r<0 || n<r) 
	return(0);
 if ((2*r) > n) 
	r = n-r;
 b= 1;
 if (r > 0)
 	for(i=0;i<=(r-1);i=i+1)
  		b= (b * (n-i))/(i+1);
 return b;
}

int binomail(int n,int r)
{
        int i,per_n,per_r;
        per_r=1;//need the production to keep the precision.
        per_n=1;

        if(n<=0 || r<0 || r>n)
                return 0;

        for(i=n; i>n-r; i--){
                per_n*=i;
                per_r*=i-n+r;
        }

        if(!per_r)
                return 0;
        else
                return per_n/per_r;
}

int main(int argc,char *argv[])
{
	if(argc!=3){
		fprintf(stderr,"please input the n and r");
		exit(1);
	}
	
	int i,j,count,n,r;
	for(i=0,count=0; i<10; i++)
		for(j=i+1; j<10; j++){
			printf("%d %d\n",i,j);
			count++;
		}
	printf("the total space is %d\n",count);

	n=atoi(argv[1]);
	r=atoi(argv[2]);
	printf("bincoef:%d\n",bin_coef(n,r));
	printf("binomial:%d\n",binomail(n,r));
}
