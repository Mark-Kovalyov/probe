
/*  bplus.h - data structures and constants  */


/* the next two lines delete the 'pascal' and 'cdecl' keywords
   to make the source compile on an ANSI compiler.
*/
#ifndef	MSC		/* not Microsoft C */
#define cdecl
#define pascal
#endif			/* MSC */

/* the following checks are to define things frequently not in
   UNIX compilers, since they are recent ANSI additions.
*/
#ifndef SEEK_SET
#define SEEK_SET	0
#endif
#ifndef O_BINARY
#define O_BINARY	0
#endif

#if	defined(ANSI_C) | defined(MSC) | defined(M_XENIX)
#define Param(x) x
#else
#define Param(x) ()
#endif	/* ANSI or PCC style decls */

#define IX_OK       1
#define IX_FAIL     0
#define EOIX       (-2)
#define MAXKEY      100
#define NUM_BUFS    8
#define MAX_LEVELS  8
#define IXB_SIZE    1024
#define IXB_SPACE  (IXB_SIZE - sizeof(short) - sizeof(long) * 2)

typedef long RECPOS;

typedef struct                    /*  entry structure in index        */
  {  RECPOS   idxptr;             /*  points to lower index level     */
     RECPOS   recptr;             /*  points to data record           */
     char     key[MAXKEY];        /*  start of record key             */
  }  ENTRY;

typedef struct                    /*  index record format             */
  {  RECPOS   brec;               /*  position in index file          */
                                  /*  or location of next free block  */
     short    bend;               /*  first unused block location     */
     RECPOS   p0;                 /*  points to next level            */
     char     entries[IXB_SPACE]; /*  here are the key entries        */
  }  BLOCK;

typedef struct                    /*  disk file info                  */
  {  RECPOS   ff;                 /*  location of first free block    */
     short    nl;                 /*  number of index levels          */
  }  IX_DISK;

typedef struct                    /*  memory buffer pool of indx blks */
  {  short    dirty;              /*  true if changed                 */
     short    handle;             /*  index file handle               */
     short    count;              /*  number of times referenced      */
     BLOCK    mb;
  }  MEMBLOCK;

typedef struct
  {  MEMBLOCK     cache [ NUM_BUFS ];
  }  IX_BUFFER;

typedef struct                    /*  in-memory index descriptor      */
  {  short    ixfile;
     short    level;              /*  level in btree                  */
     short    duplicate;          /*  no duplicate keys if 0          */
     struct
       {  RECPOS    cblock;       /*  position in index file          */
          short     coffset;      /*  current offset within block     */
       }  pos [ MAX_LEVELS ];
     BLOCK    root;               /*  root index record               */
     IX_DISK  dx;
  }  IX_DESC;

/* a few system procedure types here */
extern long filelength(), lseek(), tell();
extern char *mktemp();
//extern int read(), write(), open(), close();
//extern void exit(), memcpy();

/* ================ external interface ================ */
int cdecl open_index Param((char *,IX_DESC *, int));
int cdecl close_index Param((IX_DESC *));
int cdecl make_index Param((char *,IX_DESC *, int));
int cdecl first_key Param((IX_DESC *));
int cdecl last_key Param((IX_DESC *));
int cdecl next_key Param((ENTRY *, IX_DESC *));
int cdecl prev_key Param((ENTRY *, IX_DESC *));
int cdecl find_key Param((ENTRY *, IX_DESC *));
int cdecl add_key Param((ENTRY *, IX_DESC *));
int cdecl locate_key Param((ENTRY *, IX_DESC *));
int cdecl delete_key Param((ENTRY *, IX_DESC *));
int cdecl find_exact Param((ENTRY *, IX_DESC *));
int cdecl find_1stkey Param((ENTRY *, IX_DESC *));
