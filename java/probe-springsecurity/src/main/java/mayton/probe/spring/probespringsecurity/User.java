package mayton.probe.spring.probespringsecurity;

public class User {

    private String nickName;
    private int id;

    public User(String nickName, int id) {
        this.nickName = nickName;
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
