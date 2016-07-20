#include<stdio.h>
#include<unistd.h>

int main()
{
    static int counter=0;
    counter++;
    if(fork()==0)
        printf("child counter:%d\n",counter);
    counter++;
    printf("parent counter:%d\n",counter);
    return 0;
}
