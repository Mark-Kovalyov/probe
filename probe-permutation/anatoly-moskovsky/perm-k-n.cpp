#include <iostream>
#include <vector>

#include <stdint.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>


class RecursiveCombinator
{
public:
    RecursiveCombinator(size_t k, std::vector<int> items, bool print)
        : k(k)
        , n(items.size())
        , items(std::move(items))
        , print(print)
    {
        curr.resize(k);
    }

    void generate() noexcept
    {
        std::cout << "k=" << k << ", n=" << n << ", print=" << print << "\n";
        generate(0, 0, 0);
        std::cout << "total: " << count << "\n";
    }

private:
    // алгоритм
    void generate(size_t pos, size_t val, int level) noexcept {
        std::cout << ":: level = " << level << ", pos = " << pos << "\n";
        const size_t limit = n - k + pos + 1;
        while (val < limit) {
            curr[pos] = val;
            if (pos == k - 1) {
                print_current();    
                count++;
            }
            else {
                generate(pos + 1, val + 1, level + 1);
            }
            val++;
        }
    }

    void print_current() noexcept
    {
        if (print) {
            std::cout << "       ";
            for (size_t i = 0; i < k; i++) {
                std::cout << items[curr[i]] << " ";
            }
            std::cout << "\n";
        }
    }

private:
    const size_t k;
    const size_t n;
    const std::vector<int> items;
    const bool print;
    std::vector<size_t> curr;
    size_t count = 0;
};


std::vector<int> iota(size_t n)
{
    std::vector<int> rv(n);
    for (size_t i = 0; i < n; i++) {
        rv[i] = i + 1;
    }
    return rv;
}

int main(int argc, char* argv[])
{
    clock_t start = clock();
    std::cout.sync_with_stdio(false);
    size_t k = 2;
    int    n = 3;
    bool print = true;
    if (argc > 2) {
        k = atol(argv[1]);
        n = atol(argv[2]);
    }
    if (argc > 3) {
        print = argv[3][0] == 'p';
    }
    RecursiveCombinator comb{k, iota(n), print};
    comb.generate();
    fprintf(stderr, "Time %.2f s\n", (float)(clock() - start) / CLOCKS_PER_SEC);
    return 0;
}
