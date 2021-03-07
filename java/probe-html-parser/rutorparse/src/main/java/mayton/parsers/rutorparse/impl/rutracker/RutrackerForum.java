package mayton.parsers.rutorparse.impl.rutracker;

import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RutrackerForum {

    private final String name;
    private final int forumId;

    public RutrackerForum(@NotNull String name, int forumId) {
        this.name = name;
        this.forumId = forumId;
    }

    public int getForumId() {
        return forumId;
    }

    public String getName() {
        return name;
    }
}
