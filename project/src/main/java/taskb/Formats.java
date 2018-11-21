package taskb;

import java.awt.*;

class Formats {

    double topMargin = 0.15;
    double bottomMargin = 0.15;
    double leftMargin = 0.15;
    double rightMargin = 0.15;

    boolean isBarFilled = true;
    Color barFillColor = Color.BLACK;
    boolean hasBarFrame = true;
    Color barFrameColor = Color.BLACK;
    boolean hasBorder = true;
    Color borderColor = Color.BLACK;

    Color rulerColor = Color.BLACK;
    Color rulerMarkColor = Color.BLACK;
    boolean hasRightRuler = true;
    Font rulerFont = new Font("consolas", Font.PLAIN, 12);

    Color keyColor = Color.BLACK;
    Font keyFont = new Font("consolas", Font.PLAIN, 12);

    boolean hasHeader = true;
    Color headerColor = Color.BLACK;
    Font headerFont = new Font("calibri", Font.PLAIN, 20);
    double headerOffsetX = 0.5;
    double headerOffsetY = 0.5;

    int groupMargin = 2;

}
