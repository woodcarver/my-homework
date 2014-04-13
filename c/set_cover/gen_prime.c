#include<stdio.h>
#include<stdlib.h>
int isPrime(int n)
{
    int d;
    for(d=2;d*d<=n;d++)
        if(n%d == 0)
            return 0;
    return 1;
}
int main(int argc,char **argv)
{
    int i;
    for(i=2;i<atoi(argv[1]);i++)
        if(isPrime(i))
            printf("%d,",i);
    printf("\n");

    return 0;
}
