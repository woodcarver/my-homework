#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char *target;
char *source;
//int (*dis_tab)[1];/*wrong==> dis_tab[0][1]==dis_tab[1][0]==*(dis_tab+0+1)*/
//int (*dis_tab)[2];
int *dis_tab;

int min(int a,int b,int c)
{
	if(a<b)
		if(a<c)
			return a;
		else
			return c;
	else
		if(b<c)
			return b;
		else
			return c;
	
}

int edit_dis(int tar_pos,int source_pos)
{
	int dis_change,dis_delete,dis_add,dis;
	if(target[tar_pos]=='\0'){
		printf("distance:%d tar_pos:%d source_pos:%d\n",strlen(source)-source_pos,tar_pos,source_pos);
		return strlen(source)-source_pos;
	}
	else if(source[source_pos]=='\0'){
		printf("distance:%d tar_pos:%d source_pos:%d\n",strlen(target)-tar_pos,tar_pos,source_pos);
		return strlen(target)-tar_pos;
	}

	if(target[tar_pos]==source[source_pos]){
		dis=edit_dis(tar_pos+1,source_pos+1);
		printf("distance:%d tar_pos:%d source_pos:%d\n",dis,tar_pos,source_pos);
		return dis;
	}
	else{
		dis_change=edit_dis(tar_pos+1,source_pos+1)+1;
		dis_delete=edit_dis(tar_pos+1,source_pos)+1;
		dis_add=edit_dis(tar_pos,source_pos+1)+1;
		return min(dis_change,dis_delete,dis_add);
	}
}

int edit_dis2(int tar_len,int tar_pos,int source_len,int source_pos)
{
	int dis_change,dis_delete,dis_add,dis;
	printf("\ntar_pos:%d source_pos:%d\n",tar_pos,source_pos);
	if(target[tar_pos]=='\0'){
		printf("tar end distance:%d tar_pos:%d source_pos:%d\n",source_len-source_pos,tar_pos,source_pos);
		return source_len-source_pos;
	}
	else if(source[source_pos]=='\0'){
		printf("source end distance:%d tar_pos:%d source_pos:%d\n",tar_len-tar_pos,tar_pos,source_pos);
		return tar_len-tar_pos;
	}

	if(dis_tab[tar_pos*source_len+source_pos]!=-1){
		printf("read form dis_tab distance:%d tar_pos:%d source_pos:%d\n",dis_tab[tar_pos*source_len+source_pos],tar_pos,source_pos);
		return dis_tab[tar_pos*source_len+source_pos];
	}

	if(target[tar_pos]==source[source_pos]){
		dis=edit_dis2(tar_len,tar_pos+1,source_len,source_pos+1);
		dis_tab[tar_pos*source_pos+source_pos]=dis;
		printf("equl distance:%d tar_pos:%d source_pos:%d\n",dis,tar_pos,source_pos);
		return dis;
	}
	else{
		dis_change=edit_dis2(tar_len,tar_pos+1,source_len,source_pos+1)+1;
		dis_delete=edit_dis2(tar_len,tar_pos+1,source_len,source_pos)+1;
		dis_add=edit_dis2(tar_len,tar_pos,source_len,source_pos+1)+1;
		dis_tab[tar_pos*source_len+source_pos]=min(dis_change,dis_delete,dis_add);
		printf("write in dis_tab distance:%d tar_pos:%d source_pos:%d\n",dis_tab[tar_pos*source_len+source_pos],tar_pos,source_pos);
		return dis_tab[tar_pos*source_len+source_pos];
	}
}

int main(int argc,char **argv)
{
	int tar_len,source_len,i,j;
	if(argc!=3){
		target="li";
		source="eli";
	}else{
		target=argv[1];
		source=argv[2];
	}
	tar_len=strlen(target);
	source_len=strlen(source);

	dis_tab=malloc(tar_len*source_len*sizeof(int));
	memset(dis_tab,-1,tar_len*source_len*sizeof(int));
	/*for(i=0;i<tar_len;i++)
		for(j=0;j<source_len;j++)
			dis_tab[i][j]=-1;*/

	//printf("the edit distance is %d\n\n",edit_dis(0,0));
	printf("the edit distance2 is %d\n",edit_dis2(tar_len,0,source_len,0));
	free(dis_tab);	
	return 0;
}
