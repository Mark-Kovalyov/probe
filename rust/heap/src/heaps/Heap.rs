mod heaps {

use std::cmp::Ordering;

    trait Heap<T : Ord> {

        fn insert(item : T) -> boolean;

        fn size() -> i32;

        fn peek_top_item() -> T;

        fn poll_top_item() -> T;

        fn replace_top_item(item : T);

        fn items() -> Vec<T>;

        fn merge(heap : Heap);

        fn merge(comparables : Vec<Comparable>);

    }

}
