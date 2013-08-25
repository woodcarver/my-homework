#ifndef _FILE_OP
#define _FILE_OP
#define MAX_FILE_LINE 100
#define BUF_FILE_SIZE 1000
#define OP_OK 1
#define OP_ERROR 0
#include "hash.h"
enum header_file_module{
  WC,
  HUFFMAN_CODE,
  TEXT
};
union data_t{
  hash *hdata;
  char *cdata;
};
void header_file_reader(const char *filename,enum header_file_module);
void header_file_writer(const char *filename,enum header_file_module,void *data);
#endif
