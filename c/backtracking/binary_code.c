#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define true 1
#define false 0

typedef char *string;

int decode(string binary,const string cipher,int n,int k)
{
	int diff,found,previous;
	found=false;
	if(k>=n-1){
		/*check for the last element whether it has to been backtracking*/
		/*if(cipher[k-1]-binary[k-2]!=binary[k-1]-'0')
			return false;
		else
			return true;*/
		/*calculate the last element*/
		if(cipher[k]-binary[k-1]>=0){
			binary[k]=cipher[k]-binary[k-1]+'0';
			return true;
		}
		else
			return false;
	}

	/*previous=k-1>0?atoi(binary+k-1):0;
	diff = atoi(cipher+k)-previous;*/
	previous=k-1>0?binary[k-1]-'0':0;
	diff = cipher[k]-'0'-previous;
	printf("previous:%d diff:%d k:%d\n",previous,diff,k);

	if(diff==0){
		binary[k]='0';
		found=decode(binary,cipher,n,k+1);
	}
	else if(diff==2){
		binary[k]='1';
		found=decode(binary,cipher,n,k+1);
	}
	else if(diff==1 && (cipher[k+1]-'0'==1 || cipher[k+1]-'0'==2)){
		binary[k]='0';
		found=decode(binary,cipher,n,k+1);
		if(!found){
			binary[k]='1';
			printf("back: diff:%d k:%d %c\n",diff,k,binary[k]);
			found=decode(binary,cipher,n,k+1);
		}
	}

	return found;
}

int main(void)
{
	char binary_code[50];
	string binary,cipher="123210122";
	binary=binary_code;
	
	decode(binary,cipher,strlen(cipher),0);
	printf("%s\n",binary);
}

