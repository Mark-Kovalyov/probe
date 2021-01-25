#include <stdint.h>
#include <stdio.h>
#include <time.h>

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
#define K 6
#define N 45

int main(int argc, char**argv) {
    printf("C++ version : %ld\n", __cplusplus);
    printf("C(%d, %d)\n", K, N);
    int a[K];
    for (int i = 0; i < K; i++) a[i] = i + 1;
    int cnt = 0;
    do {
	cnt++;
	//for(int& i : a) printf("%d ", i);
	printf("\n");
    } while (nexn_combinations(a, K, N));
    printf("Total: %d Time %ld ms", cnt, clock());
    return 0;
}
