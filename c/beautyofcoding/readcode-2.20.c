#include<stdio.h>

int rg[]={2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

int main(void)
{
	int i,j,hit,hit1,hit2;
	long long unsigned max64=(unsigned)-1>>1;
	for(i=1;i<max64;i++)
	{
		hit=0;
		hit1=-1;
		hit2=-1;
		for(j=0;i<30 && hit<=2;j++){
			if(i%rg[j] != 0){
				hit++;
				if(hit==1)
					hit1=j;
				else if(hit==2)
					hit2=j;
				else
					break;
			}
			
		}
		if(hit==2 && hit1+1==hit2){
			printf("found:%u\n",i);
			break;
		}
		else
			printf("not found:i:%u hit:%d hit1:%d hit2:%d\n",i,hit,hit1,hit2);
	}
}
