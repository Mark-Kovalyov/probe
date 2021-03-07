#include "mycode.h"
#include <string.h>

char *rev(char *str) {
  int len = strlen(str);
  for(int i = 0 ;i<len/2;i++){
     char tmp = str[i];
     str[i] = str[len - 1 - i];
     str[len - 1 - i] = tmp;
  }
  return str;
}
