#include<stdio.h>
int main(int argc,char **argv)
{
    FILE *fp;
    char ch;

    fp=fopen("baidu.com","r+");
    if(fp==NULL)
    {
        printf("can not open the file\n");
        return 1;
    }

    while((ch=fgetc(fp))!=EOF)
    {
        putchar(ch);
    }

    while((ch=getchar())!='\n')
    {
        fputc(ch,fp);
    }
        
    fputc('\n',fp);
    rewind(fp);
    while((ch=fgetc(fp))!=EOF)
    {
        putchar(ch);
    }
    fclose(fp);
    putchar('\n');
    return 0;
}

