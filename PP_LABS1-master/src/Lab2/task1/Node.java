package Lab2.task1;

public class Node {
    private final Top from;
    private final Top to;
    private final boolean isDirected;

    public Node(Top from, Top to, boolean isDirected) {
        this.from = from;
        this.to = to;
        this.isDirected = isDirected;
    }
    public Top getFrom() {
        return from;
    }

    public Top getTo() {
        return to;
    }

    public boolean isDirected() {
        return isDirected;
    }
    @Override
    public String toString() {
        if (isDirected) {
            return from + " -> " + to;
        } else {
            return from + " -- " + to;
        }
    }


}
