package FII.Laborator11PA.entity;

import java.io.Serializable;
import java.util.Objects;

public class FriendshipPK implements Serializable {
    private String user1;
    private String user2;

    public FriendshipPK() {
    }

    public FriendshipPK(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipPK that = (FriendshipPK) o;
        return Objects.equals(getUser1(), that.getUser1()) && Objects.equals(getUser2(), that.getUser2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser1(), getUser2());
    }
}
