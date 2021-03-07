/*****************************************************************
 |  filelength - returns the length of a file
 ****************************************************************/

long  filelength (fd)
    int  fd;
{
#if	0		/* original version */
    long  orig, last, lseek();

    orig = lseek (fd, 0L, 1);		/* seek to the current position */
    last = lseek (fd, 0L, 2);		/* seek to the end */
    lseek (fd, orig, 0);		/* reset the pointer */

    return last;
#else			/* new network version */
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

    struct stat sb;
    fstat(fd, &sb);
    return sb.st_size;
#endif
}
