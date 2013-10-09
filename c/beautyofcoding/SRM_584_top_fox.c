#include<stdio.h>
#include<stdlib.h>
#include<string.h>

enum{
	false=0,
	true=1
};

char **handles;

char *handle_gen(char *f_name,int s,char *g_name,int t,int handle)
{
	char f_subset[s+t+1];
	char g_subset[t+1];
	f_subset[s]='\0';
	g_subset[t]='\0';

	strncpy(f_subset,f_name,s);
	strncpy(g_subset,g_name,t);
	
	strcat(f_subset,g_subset);

	printf("%s\n",f_subset);
	handles[handle]=calloc(s+t+1,sizeof(char));
	strcpy(handles[handle],f_subset);
	return handles[handle];
}

int possible_handles(char *f_name,char *g_name)
{
	int i,j,k,t,rep;
	k=0;
	rep=false;
	for(i=1; i<=strlen(f_name); i++)
		for(j=1; j<=strlen(g_name); j++){
			rep=false;
			handle_gen(f_name,i,g_name,j,k);
			for(t=0; t<k; t++)
				if(!strcmp(handles[t],handles[k]))
					rep=true;
			if(!rep)
				k++;
		}
	return k;
}

int main(int argc,char *argv[])
{
	char *f_name,*g_name;
	if(argc!=3){
		f_name="abb";
		g_name="bbc";
	}
	else{
		f_name=argv[1];
		g_name=argv[2];
	}
	handles=calloc(strlen(f_name)*strlen(g_name),sizeof(char *));
	printf("the count:%d\n",possible_handles(f_name,g_name));
	exit(0);
}
