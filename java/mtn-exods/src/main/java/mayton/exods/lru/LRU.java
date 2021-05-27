package mayton.exods.lru;

import javax.annotation.Nonnull;

public interface LRU<K,V> {

    V lookup(@Nonnull K k);

}
