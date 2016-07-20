#include<unistd.h>
#include<fcntl.h>
#include<errno.h>
#include<sys/types.h>
#include "./io.h"
void echo1()
{
    char c[3];//try using different size of buffer
    int ss=3;
    int nread;
    /*while(read(STDIN_FILENO,&c,ss)!=0)
        write(STDOUT_FILENO,&c,ss);*/
    while((nread=read(STDIN_FILENO,&c,ss))!=0)
        write(STDOUT_FILENO,&c,nread);
}
ssize_t rio_readn(int fd,void *usrbuf,size_t n)
{
    size_t nleft=n;
    ssize_t nread;
    char *bufp=usrbuf;
    while(nleft>0){
        if((nread=read(fd,bufp,nleft))<0){//error occur
            if(errno==EINTR)
                nread=0;
            else
                return -1;
        }
        else if(nread==0)//EOF
            break;
        nleft-=nread;
        bufp+=nread;
    }
    return (n-nleft);
}
ssize_t rio_writen(int fd,void *usrbuf,size_t n)
{
    size_t nleft=n;
    ssize_t nwrite;
    char *bufp=usrbuf;

    while(nleft>0){
        if((nwrite=write(fd,bufp,nleft))<=0){
            if(errno==EINTR)
                nwrite=0;
            else
                return -1;
        }
        nleft-=nwrite;
        bufp+=nwrite;
    }
    return n;
}
void rio_readinitb(rio_t *rp,int fd)
{
    rp->rio_fd=fd;
    rp->rio_cnt=0;
    rp->rio_bufptr=rp->rio_buf;
}

int main()
{
    echo1();
    int n;

    return 0;
}
