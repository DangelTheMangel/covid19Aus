import processing.core.PApplet;
import processing.data.Table;

public class ProcGraph extends Graph {
    ProcGraph(PApplet app, int posX, int posY, int xSize, int ySize, int colon) {
        super(app, posX, posY, xSize, ySize, colon);
        table = new Table();
    }

    @Override
    void draw() {
            pApplet.fill(0);
            int x1, y1, x2,  y2;
            x1 = 0;
            y1 = ySize;


            for (int i=0; i<table.getRowCount(); ++i) {

                int maxList = 0;
                for (int j = 0; j < table.getRowCount(); ++j) {
                    if (table.getInt(j,colon) > maxList) {
                        maxList = table.getInt(j,colon);
                        maxY = Math.max(maxList, maxY);
                    }
                }

                xInt = xSize/table.getRowCount();
                yInt = (float) ySize/maxY;


                x2 = (int) xInt * i;
                y2 = ySize - ((int) (table.getInt(i,colon) * yInt));
                pApplet.stroke(0);
                if(linesOn) {
                    pApplet.line(posX + x1, posY + y1, posX + x2, posY + y2);
                }
                pApplet.ellipse(posX+x1,posY+y1, 2,2);

                x1 = x2;
                y1 = y2;

            }

        }


    }

