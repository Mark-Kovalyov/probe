package mayton.html.entities;

import mayton.html.utils.PGUtils;
import java.time.LocalDateTime;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class MemberInfo {

    //    id int primary key,
    //    state varchar(30),
    //    nickname varchar(30) not null,
    //    messages int not null,
    //    hist jsonb,
    //    email varchar(255),
    //    job_details varchar(255),
    //    registered timestamp,
    //    last_update timestamp

    // Mandatory
    private int id;
    private String state;
    private String nickname;
    private int messages;
    private LinkedHashMap<Integer, Double> messagesDistibution;

    // Optional

    private String email;
    private String jobDetails;
    private LocalDateTime registered;
    private LocalDateTime lastUpdate;

    public MemberInfo(int id, String nickname, int messages) {
        this.id = id;
        setNickname(nickname);
        setMessages(messages);
    }

    public MemberInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getMessages() {
        return messages;
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

    public String getNickname() {
        return nickname;
    }

    public LinkedHashMap<Integer, Double> getMessagesDistibution() {
        return messagesDistibution;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        checkNotNull(nickname);
        this.nickname = nickname;
    }

    public void setMessages(int messages) {
        checkArgument(messages >= 0);
        this.messages = messages;
    }

    public void setMessagesDistibution(LinkedHashMap<Integer, Double> messagesDistibution) {
        checkNotNull(messagesDistibution);
        this.messagesDistibution = messagesDistibution;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        checkNotNull(state);
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", nickname='" + nickname + '\'' +
                ", messages=" + messages +
                ", email='" + email + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", registered=" + registered +
                ", lastUpdate=" + lastUpdate +
                ", messagesDistibution=" + PGUtils.mapToJson(messagesDistibution) +
                '}';
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
