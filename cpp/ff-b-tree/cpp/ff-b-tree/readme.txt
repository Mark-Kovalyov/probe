.a: static, traditional libraries.
    Applications link to these libraries of object code.

.so: dynamically linked shared object libraries.
     These libraries can either be linked in at
     runtime but statically aware or loaded during
     execution by the dynamic link loader.

-L{dir}

-l{library}


A static library has file extension of ".a" (archive file) in Unixes or
".lib" (library) in Windows. When your program is linked against a static
library, the machine code of external functions used in your program is
copied into the executable. A static library can be created via the archive
program "ar.exe".

A shared library has file extension of ".so" (shared objects) in Unixes
or ".dll" (dynamic link library) in Windows. When your program is linked
against a shared library, only a small table is created in the executable.
Before the executable starts running, the operating system loads the machine
code needed for the external functions - a process known as dynamic linking.
Dynamic linking makes executable files smaller and saves disk space, because
one copy of a library can be shared between multiple programs. Furthermore, most
operating systems allows one copy of a shared library in memory to be used
by all running programs, thus, saving memory. The shared library codes
can be upgraded without the need to recompile your program.
