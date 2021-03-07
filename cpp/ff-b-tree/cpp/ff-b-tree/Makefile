all: benchmark-static benchmark-shared

static-rnblibs:
	gcc -g -Wall -o librnb-tree.a rnb-tree.c

shared-rnblibs:
	gcc -g -Wall -shared -o librnb-tree.so rnb-tree.c

benchmark-shared: shared-rnblibs
	gcc -g -Wall benchmark.c -Llibs -L. -ldl -lrnb-tree -o benchmark-shared.exe

benchmark-static: static-rnblibs
	gcc -g -Wall benchmark.c -Llibs -L. -ldl -lrnb-tree -o benchmark-static.exe

clean:
	rm -f *.o *.so
