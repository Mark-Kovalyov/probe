CC=gcc
CFLAGS+=-Wall -g

all: librarytest

libmycode.o: mycode.c mycode.h
    $(CC) $(CFLAGS) -c mycode.c -o libmycode.o

librarytest: main.c libmycode.o
    $(CC) $(CFLAGS) -o $@ $^

clean:
    rm *.o *.so