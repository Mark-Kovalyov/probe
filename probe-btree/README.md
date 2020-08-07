# BTree in-memory

# Links

* https://www.sql.ru/forum/1325688/rabota-s-matricey
* https://www.sql.ru/forum/1320903/razmer-kesha-kak-ego-maksimalno-ispolzovat
* https://www.sql.ru/forum/960607/vozmozhnosti-optimizacii-dostupa-k-dannym-na-c-c
* https://www.sql.ru/forum/808958/skorost-vyzova-i-fizicheskiy-razmer-char-i-int

00 01 02 03 04 05 06 07 : 08 09 0A 0B 0C 0D 0E 0F
10 11 12 13 14 15 16 17 : 18 19 1A 1B 1C 1D 1E 1F
20 21 22 23 24 25 26 27 : 28 29 2A 2B 2C 2D 2E 2F
30 31 32 33 34 35 36 37 : 38 39 3A 3B 3C 3D 3E 3F


#define LEAF_NODE
#define MAIN_NODE

#pragma pack(push)  /* push current alignment to stack */
#pragma pack(64)     /* set alignment to 64 byte boundary */

union node {
  leaf_node ln;
  main_node mn;
}

#pragma pack(pop)

struct leaf_node {
  int node_type;    // { MAIN_NODE | LEAF_NODE } 8 bytes
  leaf_node *left;  // = NULL                    8 bytes
  leaf_node *right; // = NULL                    8 bytes
  int n;            // 
}

struct main_node {
  int node_type;    // 
  int splitvalue;
}

struct pair {
  void *pnode;            // 8 bytes (8)
  int n;                  // 8 bytes (16)
  main_node *main_nodes;  // (8 * n) bytes
}


// Example: get 4096 bytes aligned on a 4096 byte buffer with malloc()

// unaligned pointer to large area
void *up = malloc((1 << 13) - 1);
// well-aligned pointer to 4 KiB
void *ap = aligntonext(up, 12);

// Assume `uint32_t p, bits;` for readability
#define alignto(p, bits)      (((p) >> bits) << bits)
#define aligntonext(p, bits)  alignto(((p) + (1 << bits) - 1), bits)
