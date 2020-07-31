#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

#include "rnb-tree.h"

int main(int argc, char **argv) {
  /*
  void *handle = dlopen("./librnb-tree.so", RTLD_LAZY);
  if (!handle) {
     fprintf(stderr, "%s\n", dlerror());
     exit(EXIT_FAILURE);
  }*/
  Rnb_node *rnb_root = (Rnb_node*) malloc(sizeof(Rnb_node));
  if (put(rnb_root, 555, 777)) {

  };
  free(rnb_root);
  //dlclose(handle);
}
