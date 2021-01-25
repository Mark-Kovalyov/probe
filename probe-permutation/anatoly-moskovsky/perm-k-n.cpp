#include <iostream>
#include <vector>

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
        generate(0, 0);
        std::cout << "total: " << count << "\n";
    }

private:
    // алгоритм
    void generate(size_t pos, size_t val) noexcept
    {
        const size_t limit = n - k + pos + 1;
        for (; val < limit; val++) {
            curr[pos] = val;
            if (pos == k - 1) {
                //print_current();    
                count++;
            }
            else {
                generate(pos + 1, val + 1);
            }
        }
    }

    void print_current() noexcept
    {
        if (print) {
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
    std::cout.sync_with_stdio(false);
    size_t k = 6;
    int    n = 45;
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
    return 0;
}
