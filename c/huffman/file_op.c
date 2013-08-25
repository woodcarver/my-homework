#include<stdio.h>
#include "file_op.h"

void header_file_reader(const char *filename,enum header_file_module)
{
  FILE *fh;
  fh=fopen(filename,"r");
}
void header_file_writer(const char *filename,enum header_file_modulei,void *data)
{
  FILE *fp;
  char buf[BUF_FILE_SIZE],*strmodule;
  int linenum=0;
  union data_t w_data;

  if(filename[0] == '\0'){
    fprintf(stderr,"the target filename is error!\n");
    exit(1);
  }

  if(!(fp=fopen(filename,"a+"))){
    fprintf(stderr,"can not open the file '%s'!",filename);
    exit(1);
  }
  /*find the module position*/
  switch(header_file_module){
    case WC:
      strmodule="[wc]";
      w_data.hdata=(hash *)data;
      break;
    case HUFFMAN_CODE:
      strmodule="[code]";
      break;
    case TEXT:
      strmodule="[text]";
      w_data.cdata=(char *)data;
      break;
    default:
      fprintf(stderr,"%d module is wrong!\n",header_file_module);
      break;
  }
  
  /*update the module data*/
}
int wc_writer(FILE * fp,hash * data)
{

}
