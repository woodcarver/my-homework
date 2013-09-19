#include<stdio.h>

#define N 6
enum mark{
	NOT_WATCH=0,
	MAY_WATCH=1,
	WATCH=2};

/*too many varibles to remember!!!!*/
char * get_container_info(char * containers, int *reports, int l)
{
	int all_solutions[N],solution_mark[N],solution_nmark[N],store_nmark[N],all_solution_state[N],final_solution[N];
	
	get_all_solutions(all_solutions,containers,l);
	get_solution_mark(solution_nmark,all_solution);
	update_first_time(final_solution,all_solution,reports,l);
	
	get_solution_mark(solution_nmark,all_solution);
	update_seconde_time(final_solution,solution_nmark,reports);	
}

void get_all_solutions(int *all_solutions,char *containers, int l)
{
	int i,j,count;
	for(i=0; i<N-l; i++){
		count=0;
		for(j=i; j<i+l; j++){
			if(containers[j]=='X')
				count++;
		}
		all_solutions[i]=count;
	}
}

void get_solution_mark(int *solution_nmark,const int *all_solutions,const int *reports)
{
	
}
