#pragma once

typedef int boolean;

#define TRUE 1
#define FALSE 0

typedef struct Ffb_node {
  struct Ffb_node *splitters;  
} Ffb_node;

boolean put(Ffb_node *root, int key, int value);

boolean contains(Ffb_node *root, int key);

int get(Ffb_node *root, int key);

boolean delete(Ffb_node *root, int key);
