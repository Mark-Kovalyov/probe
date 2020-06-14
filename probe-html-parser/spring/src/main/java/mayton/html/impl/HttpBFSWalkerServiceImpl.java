package mayton.html.impl;

import mayton.html.WalkerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Route's mebers by breadth-first search of sub-forums
 *
 * - Requires fully initialized dictionary
 *
 */
@Component("BFS")
public class HttpBFSWalkerServiceImpl implements WalkerService {

    @Override
    public void walk(@NotNull String url) {

    }
}
