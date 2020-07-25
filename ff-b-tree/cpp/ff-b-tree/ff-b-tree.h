#pragma once

typedef int boolean;

#define TRUE 1
#define FALSE 0

enum Color { BLACK, RED };

typedef struct Rnb_node {
  struct rnb_node *left;
  struct rnb_node *right;
  int value;
  enum Color color;
} Rnb_node;

boolean put(Rnb_node *root, int key, int value);

boolean contains(Rnb_node *root, int key);

int     get(Rnb_node *root, int key);

boolean delete(Rnb_node *root, int key);
