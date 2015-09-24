#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>

void share_v_node()
{
    int fd1,fd2;
    char c;
    fd1=open("foobar.txt",O_RDONLY,0);
    fd2=open("foobar.txt",O_RDONLY,0);
    read(fd1,&c,1);
    read(fd2,&c,1);
    printf("v_node_table:c=%c\n",c);
}
void share_file()
{
    int fd;
    char c;

    fd=open("foobar.txt",O_RDONLY,0);
    if(fork()==0){
        read(fd,&c,1);
        exit(0);
    }
    wait(NULL);
    read(fd,&c,1);
    printf("file_table:c=%c\n",c);
}

int main()
{
    share_v_node();
    share_file();
    return 0;
}
