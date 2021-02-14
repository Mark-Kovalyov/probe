#include <stdint.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

// Следующее сочетание k элементов диапазона 1...n в массив a[]
// Возвращает true если оно есть
bool nexn_combinations(int* a, int k, int n) {
    for (int i = k - 1, max = n; i >= 0; i--, max--) {
	if (a[i] < 1 || a[i] > max) {  // за границей диапазона
	    printf("ERROR\n");
	    return false;
	}
	if (a[i] < max) {
	    a[i]++;
	    return true;
	}
	if (i > 0) {
	    a[i] = a[i - 1] + 2;
	    if (a[i - 1] == max - 1) {
		a[i]--;
	    }
	}
	for (int j = i + 1; j < k; j++) a[j] = a[j - 1] + 1;
    }
    return false;
}



//**********************************************

int main(int argc, char**argv)
{
    int *x = new int[1];
    
    if (argc < 3) {
      fprintf(stderr, "\n  Usage: perm-k-n K N [s]\n\n");
      return 1;
    }
    clock_t start = clock();
    int K = atoi(argv[1]);
    int N = atoi(argv[2]);
    bool silent = argc > 3 ? true : false;
    fprintf(stderr, "C(%d, %d)\n", K,N);
    int a[K];
    for (int i = 0; i < K; i++) a[i] = i + 1;
    long int cnt = 0;
    if (silent) {
     fprintf(stderr, "silent_mode = on\n");
     do {
	cnt++;
     } while (nexn_combinations(a, K, N));
    } else {
     do {
	cnt++;
	for(int& i : a) printf("%d ", i);
	printf("\n");
     } while (nexn_combinations(a, K, N));
    }
    fprintf(stderr, "Total: %ld Time %.2f s\n", cnt, (float)(clock() - start) / CLOCKS_PER_SEC);
    return 0;
}