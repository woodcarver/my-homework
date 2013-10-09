#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char *can_transfrom(char *init,char *goal)
{
	int i,j,n;
	char *goal_noz;
	if(init==NULL || goal==NULL)
		return "No";
	goal_noz=calloc(strlen(goal)+1,sizeof(char));
	
	for(j=0; *goal!='\0'; goal++)
		if(*goal!='z')
			goal_noz[j++]=*goal;
	goal_noz[j]='\0';
	printf("goal_noz:%s\n",goal_noz);
	if(strcmp(init,goal_noz)==0)
		return "Yes";
	return "No";
}

int main(int argc,char *argv[])
{
	if(argc!=3)
		exit(1);
	printf("The result:%s\n",can_transfrom(argv[1],argv[2]));
}
