#include<stdio.h>
#include<pthread.h>
void *thread(void *vargp)
{
    printf("hello world again!\n");
    return NULL;
}
int main()
{
    pthread_t tid;
    pthread_create(&tid,NULL,thread,NULL);
    printf("hello world from main\n");
    pthread_join(tid,NULL);
    return 0;
}
