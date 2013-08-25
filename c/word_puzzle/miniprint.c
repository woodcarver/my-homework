#include <stdarg.h>
#include <stdio.h>

void minprintf(char *fmt,...)
{
	va_list ap;
	char *p,*sval;
	int ival;
	double dval;

	va_start(ap, fmt);
	for(p=fmt; *p; ++p){
		if(*p != '%'){
			putchar(*p);
			continue;
		}
		switch(*++p){
			case 'd':
				ival = va_arg(ap,int);
				printf("%d",ival);
				break;
			case 'f':
				dval = va_arg(ap,double);
				printf("%f",dval);
				break;
			case 's':
				for(sval = va_arg(ap,char *); *sval; ++sval)
					putchar(*sval);
				break;
			default:
				putchar(*p);
				break;
		}
	}
	va_end(ap);
}

int main()
{
	/*minprintf("It's me:%d %f %s",33,33.33,"It's you finally!又是我again哈哈！\n");
	printf("这里的中文！");*/

/*	int sum,v;
	sum = 0;
	while(scanf("%d",&v) == 1)
		printf("\t%d\n",v);
*/
	int day,year;
	//char * monthname;
	char monthname[20];
	scanf("%d %s %d", &day, monthname, &year);

	printf("The date is: %d %s %d \n",day,monthname,year);
	return 0;
}

