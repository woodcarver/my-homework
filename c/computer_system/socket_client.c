#include "./socket.c"

int main(int argc,char **argv)
{
    int clientfd,port;
    char *host,buf[MAX_LEN];
    int nread;

    if(argc!=3){
        fprintf(stderr,"usage:%s <host> <port>\n",argv[0]);
        exit(0);
    }
    host=argv[1];
    port=atoi(argv[2]);
    clientfd=open_clientfd(host,port);

    while((nread=read(STDIN_FILENO,&buf,sizeof(buf)))!=0){
        write(clientfd,&buf,nread);
        if((nread=read(clientfd,&buf,sizeof(buf)))!=0){
            printf("%s\n",buf);
            //write(STDOUT_FILENO,&buf,nread);
        }
    }

    close(clientfd);

    return 0;
}
