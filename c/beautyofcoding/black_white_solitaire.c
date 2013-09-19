#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#include<string.h>

int edit_distance(char *source,char *target)
{
	if(*source=='\0')
		return strlen(target);
	else if(*target=='\0')
		return strlen(source);

	if(*source==*target)
		return edit_distance(source+1,target+1);
	else
		return edit_distance(source+1,target+1)+1;
}

int min_turns(char *card_front)
{
	int i,count_B,count_card;
	int WB_dist,BW_dist;
	char *target_WB,*target_BW;
	char charact[2]={'W','B'};
	
	count_B=count_card=0;
	for(i=0; card_front[i]!='\0'; i++){
		count_card++;
		if(card_front[i]=='B')
			count_B++;
	}
	
	target_WB=malloc(count_card+1);
	target_BW=malloc(count_card+1);
	if(!target_WB || !target_BW){
		fprintf(stderr,"have no enough space!\n");
		exit(1);
	}
	for(i=0; i<count_card; i++){
		target_WB[i]=charact[i&1];
		target_BW[i]=charact[~(i|~1)];
	}
	target_WB[i]=target_BW[i]='\0';
	printf("WB:%s  BW:%s\n",target_WB,target_BW);

	/*if(count_B<count_card/2){
		printf("WB\n");
		return edit_distance(card_front,target_WB);
	}
	else if(count_B>count_card/2){
		printf("BW\n");
		return edit_distance(card_front,target_BW);
	}
	else{*/
		printf("both\n");
		WB_dist=edit_distance(card_front,target_WB);
		BW_dist=edit_distance(card_front,target_BW);
		return WB_dist<BW_dist?WB_dist:BW_dist;
	//}
}

int main(int argc,char *argv[])
{
	char *card_front;
	if(argc!=2)
		card_front="BBBW";
	else 
		card_front=argv[1];

	printf("the distance:%d\n",min_turns(card_front));
}
