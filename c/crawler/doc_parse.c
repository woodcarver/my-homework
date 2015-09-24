#include <stdio.h>
#include <sys/types.h>
#include <regex.h>

void get_url()
{
    int status,i;
    int cflags=REG_EXTENDED;
    regmatch_t pmatch[1];
    const size_t nmatch=1;
    regex_t reg;
    const char *pattern="^http://.*";
    char *buf="http://www.baidu.com";

    regcomp(&reg,pattern,cflags);
    status=regexec(&reg,buf,nmatch,pmatch,0);
    if(status==REG_NOMATCH)
        printf("No Match\n");
    else if(status==0)
    {
        printf("Match:\n");
        for(i=pmatch[0].rm_so; i<pmatch[0].rm_eo; i++)
            putchar(buf[i]);
        printf("\n");
    }
    regfree(&reg);
}

void get_host_name(char *host,char *name)
{
    int status,i;
    int cflags=REG_EXTENDED;
    regmatch_t pmatch[2];
    const size_t nmatch=2;
    regex_t reg;
    const char *pattern="^http://([a-zA-z.]*)";

    regcomp(&reg,pattern,cflags);
    status=regexec(&reg,host,nmatch,pmatch,0);
    if(status==REG_NOMATCH)
        printf("No Match\n");
    else if(status==0)
    {
        printf("Match:\n");
        for(i=pmatch[1].rm_so; i<pmatch[1].rm_eo; i++)
            putchar(host[i]);
        printf("\n");
    }
    regfree(&reg);
}

int main(int argc,char **argv)
{
    char name[100];
    get_url();
    get_host_name("http://www.baidu.com",name);
    return 0;
}
