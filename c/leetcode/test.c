#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

int main()
{
    char *c=malloc(10);
    printf("size1:%lu,size2:%lu\n",sizeof(c),sizeof(*c));
    return 0;
}
