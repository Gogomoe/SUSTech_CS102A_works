package taskb;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String name;
    List<Item> items = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }
}
