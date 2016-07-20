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

    if(argc!=2){
        fprintf(stderr,"usage: %s <port>\n",argv[0]);
        exit(0);
    }
    port=atoi(argv[1]);
    if((listenfd=open_listenfd(port))<0)
        exit(-1);
    while(1){
       clientlen=sizeof(clientaddr); 
       connfd=accept(listenfd,(SA *)&clientaddr,(socklen_t *)&clientlen);

       if(fork()==0){
           close(listenfd);
           hp=gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr,sizeof(clientaddr.sin_addr.s_addr),AF_INET);
           haddrp=inet_ntoa(clientaddr.sin_addr);
           printf("server connected to %s (%s)\n",hp->h_name,haddrp);

           echo(connfd);
           close(connfd);
       }
       close(connfd);
    }
    return 0;
}
