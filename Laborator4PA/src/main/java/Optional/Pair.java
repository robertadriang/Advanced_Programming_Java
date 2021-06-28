package Optional;

public class Pair<T1, T2> {
    T1 left;
    T2 right;

    public Pair(T1 left, T2 right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "\n[" +
                 left +
                ", " + right +
                "]";
    }
}
