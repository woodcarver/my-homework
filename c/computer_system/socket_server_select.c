#include "./socket.c"
void echo(int connfd)
{
    size_t n;
    char buf[MAX_LEN];
    int nread;
    while((nread=read(connfd,&buf,sizeof(buf)))!=0){
        //write(connfd,&buf,nread);
        //write(STDOUT_FILENO,"from client: ",nread);
        printf("from client: %s\n",buf);
        //write(STDOUT_FILENO,&buf,nread);
        /*why use multiply times,it will miss char random*/
        //write(connfd,"from server echo: ",nread);
        //strncat(buf,":(server)\n",MAX_LEN);
        //write(connfd,&buf,nread);
        write(connfd,"from server\n",nread);
    }
}
int main(int argc,char **argv)
{
    int listenfd;
    int port,connfd;
    struct sockaddr_in clientaddr;
    struct hostent *hp;
    char *haddrp;
    int clientlen;
    int fd_cnt;
    fd_set read_set,ready_set;

    if(argc!=2){
        fprintf(stderr,"usage: %s <port>\n",argv[0]);
        exit(0);
    }
    port=atoi(argv[1]);
    clientlen=sizeof(clientaddr); 

    FD_ZERO(&read_set);
    FD_SET(STDIN_FILENO,&read_set);
    FD_SET(listenfd,&read_set);

    if((listenfd=open_listenfd(port))<0)
        exit(-1);

    ready_set=read_set;
    while(1){
       //ready_set=read_set;//failed to restored the bitmap and can not select listenfd branch
       FD_ZERO(&ready_set);
       FD_SET(STDIN_FILENO,&ready_set);
       FD_SET(listenfd,&ready_set);

       fd_cnt=select(listenfd+1,&ready_set,NULL,NULL,NULL);
       printf("fd_cnt:%d\n",fd_cnt);
       if(FD_ISSET(listenfd,&ready_set)){
           connfd=accept(listenfd,(SA *)&clientaddr,(socklen_t *)&clientlen);
           hp=gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr,sizeof(clientaddr.sin_addr.s_addr),AF_INET);
           haddrp=inet_ntoa(clientaddr.sin_addr);
           printf("server connected to %s (%s) (listenfd %d)\n",hp->h_name,haddrp,listenfd);

           echo(connfd);
           close(connfd);
       }
       if(FD_ISSET(STDIN_FILENO,&ready_set)){
           printf("It is obviously something has happend in the game,but I don't what it is!\n");
           close(STDIN_FILENO);//need to close a file descriptor!the status will be changed until the file descriptor is closed.But how to reopen it again?
           //open(STDIN_FILENO,0);
       }
    }
    return 0;
}
