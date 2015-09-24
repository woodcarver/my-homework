#include<stdio.h>
void foo()
{
    static int i;
    i++;
    printf("%d\n",i);
}

int main()
{
    foo();
    foo();
    foo();
    return 0;
}
