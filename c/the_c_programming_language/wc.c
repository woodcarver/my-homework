#include<stdio.h>
#define IN 1
#define OUT 0
#define MAX 1000
main()
{
	int c,nl,nw,nc,state;
	c = nl = nw = nc = 0;
	state = OUT;
	while((c = getchar()) != EOF){
		++nc;
		if(c == '\n')
			++nl;
		if(c == ' ' || c == '\n' || c == '\t'){
			if(state == IN)
				putchar('\n');
			state = OUT;
		}
		else{
			putchar(c);
			if(state == OUT)
				++nw;
			state = IN;
		}
	}
	printf("%d %d %d\n",nl,nw,nc);
}
