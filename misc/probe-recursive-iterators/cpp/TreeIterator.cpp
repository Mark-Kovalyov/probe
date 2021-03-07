#include <boost/coroutine2/all.hpp>

#include <functional>
#include <iostream>
#include <vector>

#define LOG_TRACE(x) do { std::cout << x << std::endl; } while (0)


// Some tree with a recursive walker
struct TreeNode {
    TreeNode(int value)
        : value(value) {}

    int value;
    std::vector<TreeNode> children;

    // recursive walk
    void walk_tree(std::function<void(const TreeNode& node, int level)> output, int level = 0) const
    {
        output(*this, level);
        for (auto& c: children) {
           c.walk_tree(output, level + 1);
        }
    }
};

// Implementation of iterator with coroutine
template <class T>
struct TreeIterator {
    // Wrapper for tree node + level
    struct TreeValue {
        TreeValue(int level, const T& node)
            : level(level)
            , node(node)
        {}
        int level;
        const T& node;
    };

    // C-tor from a tree
    TreeIterator(const T& node)
        : m_reader(make_coro_reader(node))
        , m_iterator(begin(m_reader))
    {}

    // Iterator interface

    bool has_next()
    {
        return m_iterator != end(m_reader);
    }

    TreeValue next()
    {
        auto rv = *m_iterator;
        ++m_iterator;
        return rv;
    }

private:
    // Coroutine helper class
    using CoroType = boost::coroutines2::coroutine<TreeValue>;

    // Reading coroutine (main)
    using CoroReader = typename CoroType::pull_type;

    // Reading coroutine iterator
    using CoroReaderIter = typename CoroReader::iterator;

    // Generating coroutine (walker)
    using CoroWalker = typename CoroType::push_type;

    static CoroReader make_coro_reader(const T& node)
    {
        CoroReader rv([&node](CoroWalker& sink){
            node.walk_tree([&sink](const T& node, int level) {
                sink(TreeValue(level, node));
            });
        });
        return  rv;
    }

private:
    CoroReader m_reader;
    CoroReaderIter m_iterator;
};

int main()
{
    // 1
    // |- 11
    // |- 12
    //     |-121
    //     |-122
    // |- 13
    TreeNode tree{1};
    tree.children.emplace_back(11);
    tree.children.emplace_back(12);
    tree.children.emplace_back(13);
    tree.children[1].children.emplace_back(121);
    tree.children[1].children.emplace_back(122);

    // print recursively
    tree.walk_tree([](const TreeNode& node, int level){
        LOG_TRACE(std::string(level*2, ' ') << node.value);
    });

    // print sequentially via iterator that flattens the tree using a coroutine
    for (auto it = TreeIterator<TreeNode>(tree); it.has_next(); ) {
        auto next = it.next();
        LOG_TRACE(std::string(next.level*2, ' ') << next.node.value);
    }
    return 0;
}
