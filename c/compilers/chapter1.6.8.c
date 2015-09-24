#include<stdio.h>

int main(int argc,char *args[])
{
    int w,x,y,z;
    int i=4;
    int j=5;
    {   int j=7;
        i=6;
        w=i+j;
    }
    x=i+j;
    {   int i=8;
        y=i+j;
    }
    z=i+j;
    printf("w:%d,x:%d,y:%d,z:%d\n",w,x,y,z);
}
