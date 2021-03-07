#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

#include "rnb-tree.h"

extern boolean put(Rnb_node *root, int key, int value);

int main(int argc, char **argv) {
  Rnb_node *rnb_root = (Rnb_node*) malloc(sizeof(Rnb_node));
  if (put(rnb_root, 555, 777)) {

  };
  free(rnb_root);  
}
