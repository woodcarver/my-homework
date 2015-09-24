#include<stdio.h>
void inner1(int i)
{
    printf("This is given by outer:%d\n",i);
}
void inner2()
{
    printf("This is given by outer:%d\n",i);
}

void outer()
{
    static int i=1;
    inner1(i);
    inner2();
}

int main()
{
    outer();
    return 0;
}
