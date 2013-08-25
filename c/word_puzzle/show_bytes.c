#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start,int len){
	int i;
	for(i=0; i<len; i++)
		printf(" %.2x",start[i]);
	printf("\n");
}

int main(int argc, char **argv)
{
	unsigned long int temp;
	if(argc!=2)
		exit(-1);
	temp=atoll(argv[1]);
	show_bytes((byte_pointer) &temp,sizeof(int));
	show_bytes((byte_pointer) argv+1,sizeof(void *));
	return 0;
}
