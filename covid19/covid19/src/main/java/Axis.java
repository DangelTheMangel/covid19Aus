import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class Axis {

    PApplet pApplet;
    int x1 , x2 , y2, y1, xSize;
    boolean isyAxis;
    float xInt;
    float yInt;
    Table table;
    int maxY = Integer.MIN_VALUE;
    int minorTick ;
    int minorTickLength = 10;
    ArrayList<Data> dataArrayList;

    Axis(PApplet app, int x1 , int y1 , int x2 , int y2, boolean isyAxis, ArrayList<Data> dataArrayList,  float xInt, float yInt, int minorTick) {
        pApplet = app;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.xInt = xInt;
        this.yInt = yInt;
        this.xSize = xSize;
        this.minorTick = minorTick;
        this.isyAxis = isyAxis;
        this.table = table;
        this.dataArrayList = dataArrayList;


    }

    void draw() {
        // Draw axis
        pApplet.fill(23, 94, 150);
        pApplet.stroke(1, 46, 74);
        pApplet.line(x1, y1, x2, y2);

        if(isyAxis == true) {
            // Draw arrow at endde
            pApplet.triangle(x2  + 10, y2, x2 - 10, y2, x2, y2-10);

            // Draw tick marks
            int xRight = x1 + minorTickLength/2;
            int xLeft = x1 - minorTickLength/2;
            for (int i=0; i<maxY; ++i) {
                if (i % minorTick == 0) {
                    int y = y1 - (int) (yInt * i);
                    pApplet.line(xRight, y, xLeft,y );
                }
            }
        } else {
            // Draw arrow at end
            pApplet.triangle(x2+ 10, y2, x2, y2 + 10, x2, y2-10);

            // Draw tick marks
            int yLow = y1 + minorTickLength/2;
            int yHi = y1 - minorTickLength/2;
            for (int i=0; i<dataArrayList.size(); ++i) {
                if (i % minorTick == 0) {
                    int x = x1 + (int) (xInt * i);
                    pApplet.line(x, yLow, x, yHi);
                }
            }
        }
    }
}
