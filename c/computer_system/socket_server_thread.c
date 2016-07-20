#include "./socket.c"
#include <pthread.h>
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
void *thread_funp(void *vargp){
    int connfd=*((int *) vargp);
    pthread_detach(pthread_self());//why
    free(vargp);
    echo(connfd);
    close(connfd);
    return NULL;
}
int main(int argc,char **argv)
{
    int listenfd;
    int port,*connfdp;
    struct sockaddr_in clientaddr;
    struct hostent *hp;
    char *haddrp;
    int clientlen;
    pthread_t tid;

    if(argc!=2){
        fprintf(stderr,"usage: %s <port>\n",argv[0]);
        exit(0);
    }
    port=atoi(argv[1]);
    if((listenfd=open_listenfd(port))<0)
        exit(-1);
    while(1){
       clientlen=sizeof(clientaddr); 
       connfdp=malloc(sizeof(int));
       *connfdp=accept(listenfd,(SA *)&clientaddr,(socklen_t *)&clientlen);
       hp=gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr,sizeof(clientaddr.sin_addr.s_addr),AF_INET);
       haddrp=inet_ntoa(clientaddr.sin_addr);
       printf("server connected to %s (%s)\n",hp->h_name,haddrp);
       pthread_create(&tid,NULL,thread_funp,connfdp);
    }
    return 0;
}
