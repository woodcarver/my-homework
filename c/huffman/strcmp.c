#include<stdio.h>
#include<string.h>
void main(int argc,char **argv)
{
  int i,s;
  //i = strcmp(argv[1],argv[2]);
  i = strcmp("a","a");
  printf("%d\n",i);
  if(!(s=strcmp("b","b")))
   printf("equal\n");
}
