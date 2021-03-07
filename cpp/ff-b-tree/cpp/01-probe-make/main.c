#include <stdio.h>
#include "mycode.h"

int main(int argc, char **argv) {
  if (argc > 0) {
    printf("%s\n", reverse(argv[1]));
  }
  return 0;
}

