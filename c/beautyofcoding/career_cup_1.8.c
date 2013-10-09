#include<stdio.h>
#include<string.h>

int is_rotation(char *str1,char *str2)
{
	int k,i,j,n;
	if(str1==NULL ||str2==NULL)
		return 0;
	if((n=strlen(str1))!=strlen(str2))
		return 0;

	for(k=0; k<n; k++){
		printf("k:%d\n",k);
		if(str1[k]!=str2[0])
			continue;
		for(i=1; i+k<n; i++){
			printf("%c ",str1[k+i]);
			if(str1[k+i]!=str2[i])
				break;
		}
		printf("(suffix i:%d)\n",i);
		if(i+k!=n)
			continue;
		for(j=0; j<k; j++){
			printf("str1[%d]:%c str2[%d]:%c",j,str1[j],n-k+j,str2[n-k+j]);
			if(str1[j]!=str2[n-k+j])
				break;
		}
		printf("(prefix j:%d)\n",j);
		if(j==k)
			return 1;
	}
	return 0;
}

int main(int argc,char *argv[])
{
	printf("result:%d\n",is_rotation(argv[1],argv[2]));
}
