package taskb;

import java.util.List;

public abstract class Histogram {

    Canvas canvas;
    Formats formats;
    String header;

    List<Group> groups;
    List<Property> properties;


    public void init(Canvas canvas, Formats formats, String header, List<Group> groups, List<Property> properties) {
        this.canvas = canvas;
        this.formats = formats;
        this.header = header;
        this.groups = groups;
        this.properties = properties;
        setHistogramParameters();
    }

    protected abstract void setHistogramParameters();

    public void draw() {

    }
}
