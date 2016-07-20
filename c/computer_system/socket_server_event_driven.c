#include "./socket.c"

typedef struct{
    int maxfd;
    fd_set read_set;
    fd_set ready_set;
    int nready;
    int maxi;
    int clientfd[FD_SETSIZE];
} pool;
void init_pool(int listenfd,pool *p)
{
    int i;
    p->maxi=-1;
    for(i=0;i<FD_SETSIZE;i++){
        p->clientfd[i]=-1;
    }

    p->maxfd=listenfd;
    FD_ZERO(&p->read_set);
    FD_SET(listenfd,&p->read_set);
}
void add_client(int connfd,pool *p)
{
    int i;
    p->nready--;
    for(i=0;i<FD_SETSIZE;i++){
        if(p->clientfd[i]<0){
            p->clientfd[i]=connfd;
            FD_SET(connfd,&p->read_set);
            if(connfd>p->maxfd)
                p->maxfd=connfd;
            if(i>p->maxi)
                p->maxi=i;
            break;
        }
    }
    if(i==FD_SETSIZE){
        printf("add_client error:Too many clients\n");
    }
}
void echo(int connfd)
{
    size_t n;
    char buf[MAX_LEN];
    int nread;
    while((nread=read(connfd,&buf,sizeof(buf)))!=0){
        printf("from client: %s\n",buf);
    }
}
void check_clients(pool *p)
{
    int i,connfd;
    for(i=0; (i<=p->maxi) && (i<FD_SETSIZE); i++){
        connfd=p->clientfd[i];
        printf("i:%d connfd:%d \n",i,connfd);
        if(FD_ISSET(connfd,&p->ready_set)){
            printf("connfd(%d) active!\n",connfd);
        }
        else{
            printf("connfd over\n");
            close(connfd);
            FD_CLR(connfd,&p->read_set);
            p->clientfd[i]=-1;
        }
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
    static pool pool;

    if(argc!=2){
        fprintf(stderr,"usage: %s <port>\n",argv[0]);
        exit(0);
    }
    port=atoi(argv[1]);
    clientlen=sizeof(clientaddr); 


    if((listenfd=open_listenfd(port))<0)
        exit(-1);

    init_pool(listenfd,&pool);
    while(1){
       pool.ready_set=pool.read_set;
       pool.nready=select(pool.maxfd+1,&pool.ready_set,NULL,NULL,NULL);
       if(FD_ISSET(listenfd,&pool.ready_set)){
           connfd=accept(listenfd,(SA *)&clientaddr,(socklen_t *)&clientlen);
           hp=gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr,sizeof(clientaddr.sin_addr.s_addr),AF_INET);
           haddrp=inet_ntoa(clientaddr.sin_addr);
           printf("server connected to %s (%s) (listenfd %d) (connfd %d)\n",hp->h_name,haddrp,listenfd,connfd);
           add_client(connfd,&pool);
       }
       check_clients(&pool);
    }
    return 0;
}
