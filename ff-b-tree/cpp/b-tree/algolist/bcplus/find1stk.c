/*****************************************************************
 |  find_1stkey - find first key matching
 |----------------------------------------------------------------
 |  The find_key routine may not return a pointer to the first
 |  matching key in terms of next_key order. To allow searching
 |  of all matching keys, use get_1stkey and next_key.
 |----------------------------------------------------------------
 |  Arguments:
 |   1) pointer to the ENTRY struct
 |   2) pointer to the IX_DESC struct
 ****************************************************************/

#include "bplus.h"
 
int
find_1stkey(wkptr, ixptr)
    ENTRY *wkptr;
    IX_DESC *ixptr;
{
    ENTRY lastok;               /* last matching entry      */
    IX_DESC ixwk;               /* last valid index value   */
    int stat;

    if ((stat = find_key(wkptr, ixptr)) == IX_OK)
    {
        lastok = *wkptr;
        ixwk = *ixptr;
        
        while (prev_key(wkptr, ixptr) != EOIX &&
            strcmp(wkptr->key, lastok.key) == 0)
        {
            lastok = *wkptr;
            ixwk = *ixptr;
	}

        *wkptr = lastok;
        *ixptr = ixwk;
     }
     return stat;
}

