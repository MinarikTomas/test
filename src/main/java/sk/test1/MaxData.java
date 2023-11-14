package sk.test1;

import java.util.List;

// Data class for finding max in json
public class MaxData{
    List<String> path;
    int val;

    public MaxData(List<String> path, int val) {
        this.path = path;
        this.val = val;
    }

    @Override
    public String toString() {
        return this.path.get(0) + " -> "
                + this.path.get(1) + " -> "
                + this.path.get(2) + " : "
                + this.val;
    }
}