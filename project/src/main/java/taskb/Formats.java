package taskb;

import java.awt.*;

class Formats {
    double[] margins = {0.15, 0.15, 0.1, 0.05};  // NORTH, SOUTH, WEST, EAST
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
    double[] headerOffset = {0.5, 0.5};

    boolean hasFooter = true;
    Color footerColor = Color.BLACK;
    Font footerFont = new Font("consolas", Font.PLAIN, 16);
    double[] footerOffset = {0.5, 0.5};

}
