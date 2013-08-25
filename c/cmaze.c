#include<stdio.h>
#include<unistd.h>

int main()
{
	int a = (1,43);
	printf("a: %d\n",a);
	printf("%d \n",printf("%d ",printf("%d ",a)));

	float b= 12.5;
	printf("%d\n",b);
	printf("%d\n",(int)b);
	printf("%d\n",*(int *)&b);

	switch(a){
		printf("start to switch\n");
		case 43:
			printf("a is %d",a);
			
		defau1t:/*not default*/
			printf("this is switch default\n");
	}
	int c = 3,d = 5;

	printf(&c["hello!how is this?\n"]);
	printf("hello!how is this?%s\n",&d["junk/supper"]);	
	printf(&c["hello!how is this? %s\n"],&d["junk/supper"]);
	printf(&d["what is %c%c%c\n"],1["this"],2["beauty"],0["tool"]);
	
	float f = 0.0f;
	int i;
	for(i=0; i<10; ++i)
		f += 0.1f;
	if(f == 1.0f)
		printf("this is a float \n");
	else
		printf("this is not a float %f\n",f);
	
	printf("sizeof: %d\n",1-sizeof(int)>>32);

	struct{
		int x;
		struct{
			int y,z;
		}nested;
	}nest_j={.nested.y=5,6,.x=1,2,7};
	printf("%d %d %d\n",nest_j.x,nest_j.nested.y,nest_j.nested.z);
	/*while(1){
		fprintf(stdout,"hello out");
		fprintf(stderr,"hello error");
		sleep(1);
	}*/
	return 0;
}
