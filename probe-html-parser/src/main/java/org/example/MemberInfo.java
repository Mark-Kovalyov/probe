package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.immutables.value.Value;

import javax.annotation.concurrent.Immutable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//TODO: Consider for @Value.Immutable approach

@Immutable
public final class MemberInfo {

    private final int id;
    private final boolean state; // true = registered
    private final String nickname;
    private final int messages;
    private final LocalDate registered;
    private final LocalDate lastUpdate;
    private final List<Pair<Forum,Integer>> messagesDistibution;

    public MemberInfo(int id, boolean state, String nickname, int messages, LocalDate registered, LocalDate lastUpdate, List<Pair<Forum, Integer>> messagesDistibution) {
        this.id = id;
        this.state = state;
        this.nickname = nickname;
        this.messages = messages;
        this.registered = registered;
        this.lastUpdate = lastUpdate;
        this.messagesDistibution = messagesDistibution;
    }

    public int getId() {
        return id;
    }

    public boolean isState() {
        return state;
    }

    public int getMessages() {
        return messages;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public List<Pair<Forum, Integer>> getMessagesDistibution() {
        return Collections.unmodifiableList(messagesDistibution);
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", state=" + state +
                ", nickname='" + nickname + '\'' +
                ", messages=" + messages +
                ", registered=" + registered +
                ", lastUpdate=" + lastUpdate +
                ", messagesDistibution=" + messagesDistibution +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfo that = (MemberInfo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
