// https://web.archive.org/web/20070927222509/http://eternallyconfuzzled.com/tuts/datastructures/jsw_tut_rbtree.aspx

1 struct jsw_node {
2   int red;
3   int data;
4   struct jsw_node *link[2];
5 };
6 
7 struct jsw_tree {
8   struct jsw_node *root;
9 };

 /* Classic binary search tree insertion */
 2 struct cbt_node *cbt_insert ( struct cbt_node *root, int data )
 3 {
 4   if ( root == NULL )
 5     root = make_node ( data );
 6   else if ( data < root->data )
 7     root->left = cbt_insert ( root->left, data );
 8   else
 9     root->right = cbt_insert ( root->right, data );
10 
11   return root;
12 }
13 
14 /* Julienne Walker's binary search tree insertion */
15 struct jsw_node *jsw_insert ( struct jsw_node *root, int data )
16 {
17   if ( root == NULL )
18     root = make_node ( data );
19   else {
20     int dir = root->data < data;
21     root->link[dir] = jsw_insert ( root->link[dir], data );
22   }
23 
24   return root;
25 }

int is_red ( struct jsw_node *root )
2 {
3   return root != NULL && root->red == 1;
4 }

1 struct jsw_node *jsw_single ( struct jsw_node *root, int dir )
 2 {
 3   struct jsw_node *save = root->link[!dir];
 4 
 5   root->link[!dir] = save->link[dir];
 6   save->link[dir] = root;
 7 
 8   root->red = 1;
 9   save->red = 0;
10 
11   return save;
12 }
13 
14 struct jsw_node *jsw_double ( struct jsw_node *root, int dir )
15 {
16   root->link[!dir] = jsw_single ( root->link[!dir], !dir );
17   return jsw_single ( root, dir );
18 }

 1 int jsw_rb_assert ( struct jsw_node *root )
 2 {
 3   int lh, rh;
 4 
 5   if ( root == NULL )
 6     return 1;
 7   else {
 8     struct jsw_node *ln = root->link[0];
 9     struct jsw_node *rn = root->link[1];
10 
11     /* Consecutive red links */
12     if ( is_red ( root ) ) {
13       if ( is_red ( ln ) || is_red ( rn ) ) {
14         puts ( "Red violation" );
15         return 0;
16       }
17     }
18 
19     lh = jsw_rb_assert ( ln );
20     rh = jsw_rb_assert ( rn );
21 
22     /* Invalid binary search tree */
23     if ( ( ln != NULL && ln->data >= root->data )
24       || ( rn != NULL && rn->data <= root->data ) )
25     {
26       puts ( "Binary tree violation" );
27       return 0;
28     }
29 
30     /* Black height mismatch */
31     if ( lh != 0 && rh != 0 && lh != rh ) {
32       puts ( "Black violation" );
33       return 0;
34     }
35 
36     /* Only count black links */
37     if ( lh != 0 && rh != 0 )
38       return is_red ( root ) ? lh : lh + 1;
39     else
40       return 0;
41   }
42 }

 1 struct jsw_node *make_node ( int data )
 2 {
 3   struct jsw_node *rn = malloc ( sizeof *rn );
 4 
 5   if ( rn != NULL ) {
 6     rn->data = data;
 7     rn->red = 1; /* 1 is red, 0 is black */
 8     rn->link[0] = NULL;
 9     rn->link[1] = NULL;
10   }
11 
12   return rn;
13 }

 1 struct jsw_node *jsw_insert_r ( struct jsw_node *root, int data )
 2 {
 3   if ( root == NULL )
 4     root = make_node ( data );
 5   else if ( data != root->data ) {
 6     int dir = root->data < data;
 7 
 8     root->link[dir] = jsw_insert_r ( root->link[dir], data );
 9 
10     /* Hey, let's rebalance here! */
11   }
12 
13   return root;
14 }
15 
16 int jsw_insert ( struct jsw_tree *tree, int data )
17 {
18   tree->root = jsw_insert_r ( tree->root, data );
19   tree->root->red = 0;
20   return 1;
21 }

 1 struct jsw_node *jsw_insert_r ( struct jsw_node *root, int data )
 2 {
 3   if ( root == NULL )
 4     root = make_node ( data );
 5   else if ( data != root->data ) {
 6     int dir = root->data < data;
 7 
 8     root->link[dir] = jsw_insert_r ( root->link[dir], data );
 9 
10     if ( is_red ( root->link[dir] ) ) {
11       if ( is_red ( root->link[!dir] ) ) {
12         /* Case 1 */
13         root->red = 1;
14         root->link[0]->red = 0;
15         root->link[1]->red = 0;
16       }
17       else {
18         /* Cases 2 & 3 */
19         if ( is_red ( root->link[dir]->link[dir] ) )
20           root = jsw_single ( root, !dir );
21         else if ( is_red ( root->link[dir]->link[!dir] ) )
22           root = jsw_double ( root, !dir );
23       }
24     }
25   }
26 
27   return root;
28 }
29 
30 int jsw_insert ( struct jsw_tree *tree, int data )
31 {
32   tree->root = jsw_insert_r ( tree->root, data );
33   tree->root->red = 0;
34   return 1;
35 }

 1 struct jsw_node *jsw_remove_r ( struct jsw_node *root, int data, int *done )
 2 {
 3   if ( root == NULL )
 4     *done = 1;
 5   else {
 6     int dir;
 7 
 8     if ( root->data == data ) {
 9       if ( root->link[0] == NULL || root->link[1] == NULL ) {
10         struct jsw_node *save =
11           root->link[root->link[0] == NULL];
12 
13         /* Case 0 */
14         if ( is_red ( root ) )
15           *done = 1;
16         else if ( is_red ( save ) ) {
17           save->red = 0;
18           *done = 1;
19         }
20 
21         free ( root );
22 
23         return save;
24       }
25       else {
26         struct jsw_node *heir = root->link[0];
27 
28         while ( heir->link[1] != NULL )
29           heir = heir->link[1];
30 
31         root->data = heir->data;
32         data = heir->data;
33       }
34     }
35 
36     dir = root->data < data;
37     root->link[dir] = jsw_remove_r ( root->link[dir], data, done );
38 
39     if ( !*done )
40       root = jsw_remove_balance ( root, dir, done );
41   }
42 
43   return root;
44 }
45 
46 int jsw_remove ( struct jsw_tree *tree, int data )
47 {
48   int done = 0;
49 
50   tree->root = jsw_remove_r ( tree->root, data, &done );
51   if ( tree->root != NULL )
52     tree->root->red = 0;
53 
54   return 1;
55 }

 1 struct jsw_node *jsw_remove_balance ( struct jsw_node *root, int dir, int *done )
 2 {
 3   struct jsw_node *p = root;
 4   struct jsw_node *s = root->link[!dir];
 5 
 6   if ( s != NULL && !is_red ( s ) ) {
 7     /* Black sibling cases */
 8     if ( !is_red ( s->link[0] ) && !is_red ( s->link[1] ) ) {
 9       if ( is_red ( p ) )
10         *done = 1;
11       p->red = 0;
12       s->red = 1;
13     }
14     else {
15       int save = root->red;
16 
17       if ( is_red ( s->link[!dir] ) )
18         p = jsw_single ( p, dir );
19       else
20         p = jsw_double ( p, dir );
21 
22       p->red = save;
23       p->link[0]->red = 0;
24       p->link[1]->red = 0;
25       *done = 1;
26     }
27   }
28   else if ( s->link[dir] != NULL ) {
29     /* Red sibling cases */
30     struct jsw_node *r = s->link[dir];
31 
32     if ( !is_red ( r->link[0] ) && !is_red ( r->link[1] ) ) {
33       p = jsw_single ( p, dir );
34       p->link[dir]->link[!dir]->red = 1;
35     }
36     else {
37       if ( is_red ( r->link[dir] ) )
38         s->link[dir] = jsw_single ( r, !dir );
39       p = jsw_double ( p, dir );
40       s->link[dir]->red = 0;
41       p->link[!dir]->red = 1;
42     }
43 
44     p->red = 0;
45     p->link[dir]->red = 0;
46     *done = 1;
47   }
48 
49   return p;
50 }

 1 struct jsw_node *jsw_remove_balance ( struct jsw_node *root, int dir, int *done )
 2 {
 3   struct jsw_node *p = root;
 4   struct jsw_node *s = root->link[!dir];
 5 
 6   /* Case reduction, remove red sibling */
 7   if ( is_red ( s ) ) {
 8     root = jsw_single ( root, dir );
 9     s = p->link[!dir];
10   }
11 
12   if ( s != NULL ) {
13     if ( !is_red ( s->link[0] ) && !is_red ( s->link[1] ) ) {
14       if ( is_red ( p ) )
15         *done = 1;
16       p->red = 0;
17       s->red = 1;
18     }
19     else {
20       int save = root->red;
21       int new_root = ( root == p );
22 
23       if ( is_red ( s->link[!dir] ) )
24         p = jsw_single ( p, dir );
25       else
26         p = jsw_double ( p, dir );
27 
28       p->red = save;
29       p->link[0]->red = 0;
30       p->link[1]->red = 0;
31 
32       if ( new_root )
33         root = p;
34 
35       *done = 1;
36     }
37   }
38 
39   return root;
40 }

 1 int jsw_insert ( struct jsw_tree *tree, int data )
 2 {
 3   if ( tree->root == NULL ) {
 4     /* Empty tree case */
 5     tree->root = make_node ( data );
 6     if ( tree->root == NULL )
 7       return 0;
 8   }
 9   else {
10     struct jsw_node head = {0}; /* False tree root */
11 
12     struct jsw_node *g, *t;     /* Grandparent & parent */
13     struct jsw_node *p, *q;     /* Iterator & parent */
14     int dir = 0, last;
15 
16     /* Set up helpers */
17     t = &head;
18     g = p = NULL;
19     q = t->link[1] = tree->root;
20 
21     /* Search down the tree */
22     for ( ; ; ) {
23       if ( q == NULL ) {
24         /* Insert new node at the bottom */
25         p->link[dir] = q = make_node ( data );
26         if ( q == NULL )
27           return 0;
28       }
29       else if ( is_red ( q->link[0] ) && is_red ( q->link[1] ) ) {
30         /* Color flip */
31         q->red = 1;
32         q->link[0]->red = 0;
33         q->link[1]->red = 0;
34       }
35 
36       /* Fix red violation */
37       if ( is_red ( q ) && is_red ( p ) ) {
38         int dir2 = t->link[1] == g;
39 
40         if ( q == p->link[last] )
41           t->link[dir2] = jsw_single ( g, !last );
42         else
43           t->link[dir2] = jsw_double ( g, !last );
44       }
45 
46       /* Stop if found */
47       if ( q->data == data )
48         break;
49 
50       last = dir;
51       dir = q->data < data;
52 
53       /* Update helpers */
54       if ( g != NULL )
55         t = g;
56       g = p, p = q;
57       q = q->link[dir];
58     }
59 
60     /* Update root */
61     tree->root = head.link[1];
62   }
63 
64   /* Make root black */
65   tree->root->red = 0;
66 
67   return 1;
68 }

1 int jsw_remove ( struct jsw_tree *tree, int data )
 2 {
 3   if ( tree->root != NULL ) {
 4     struct jsw_node head = {0}; /* False tree root */
 5     struct jsw_node *q, *p, *g; /* Helpers */
 6     struct jsw_node *f = NULL;  /* Found item */
 7     int dir = 1;
 8 
 9     /* Set up helpers */
10     q = &head;
11     g = p = NULL;
12     q->link[1] = tree->root;
13 
14     /* Search and push a red down */
15     while ( q->link[dir] != NULL ) {
16       int last = dir;
17 
18       /* Update helpers */
19       g = p, p = q;
20       q = q->link[dir];
21       dir = q->data < data;
22 
23       /* Save found node */
24       if ( q->data == data )
25         f = q;
26 
27       /* Push the red node down */
28       if ( !is_red ( q ) && !is_red ( q->link[dir] ) ) {
29         if ( is_red ( q->link[!dir] ) )
30           p = p->link[last] = jsw_single ( q, dir );
31         else if ( !is_red ( q->link[!dir] ) ) {
32           struct jsw_node *s = p->link[!last];
33 
34           if ( s != NULL ) {
35             if ( !is_red ( s->link[!last] ) && !is_red ( s->link[last] ) ) {
36               /* Color flip */
37               p->red = 0;
38               s->red = 1;
39               q->red = 1;
40             }
41             else {
42               int dir2 = g->link[1] == p;
43 
44               if ( is_red ( s->link[last] ) )
45                 g->link[dir2] = jsw_double ( p, last );
46               else if ( is_red ( s->link[!last] ) )
47                 g->link[dir2] = jsw_single ( p, last );
48 
49               /* Ensure correct coloring */
50               q->red = g->link[dir2]->red = 1;
51               g->link[dir2]->link[0]->red = 0;
52               g->link[dir2]->link[1]->red = 0;
53             }
54           }
55         }
56       }
57     }
58 
59     /* Replace and remove if found */
60     if ( f != NULL ) {
61       f->data = q->data;
62       p->link[p->link[1] == q] =
63         q->link[q->link[0] == NULL];
64       free ( q );
65     }
66 
67     /* Update root and make it black */
68     tree->root = head.link[1];
69     if ( tree->root != NULL )
70       tree->root->red = 0;
71   }
72 
73   return 1;
74 }

