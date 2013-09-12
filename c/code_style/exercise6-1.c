#include<stdio.h>
#include<string.h>
int factorial(int n)
{
	int fac;
	fac=1;
	while(n--)
		fac*=n;
	return fac;
}
int factorial2(int n)
{
	int fac;
	fac=1;
	while(n){
		fac*=n;
		n--;
	}
	return fac;
}
void str_cpy(char *dest,char *src)
{
	int i;
	for(i=0;src[i]!='\0';i++)
		dest[i]=src[i];
}
void str_ncpy2(char *dest,char *src,int n)
{
	/*should check the dest len,guarantee it is large enough to keep the source*/
	int i;
	for(i=0; i<n && src[i]!='\0'; i++)
		dest[i]=src[i];
	for(; i<n; i++)
		dest[i]='\0';
}

int main(int argc,char **argv)
{
	int i;
	i=0;
	char string[10];
	if(argc!=2){
		string[0]='\0';
		string[1]='a';
		string[2]='c';
	}
	else{
		strcpy(string,argv[1]);
	}
	/*printf("%d\n",factorial(atoi(argv[1])));
	printf("%d\n",factorial2(atoi(argv[1])));*/

	//when the string is empty,it maybe stop at the wrong position.
	do{
		putchar(string[i++]);
		printf(" %d %d\n",string[i],i);
		putchar('\n');
	}while(string[i]!='\0');

	printf("\nversion two:\n");
	while(string[i]!='\0'){
		putchar(string[i++]);
		putchar('\n');
	}
}
