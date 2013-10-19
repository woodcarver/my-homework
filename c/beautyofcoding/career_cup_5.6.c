#include<stdio.h>

int swap_bit(int n)
{
	int even_mod,odd_mod,even_bits,odd_bits;
	even_mod=0xaaaaaaaa;
	odd_mod=0x55555555;
	
	even_bits=n&even_mod;
	printf("even_bits:%d\n",even_bits);
	odd_bits=n&odd_mod;
	printf("odd_bits:%d\n",odd_bits);

	return (even_bits>>1)|(odd_bits<<1);
}

int main(int argc,char *argv[])
{
	printf("the ans:%d\n",swap_bit(atoi(argv[1])));
}
