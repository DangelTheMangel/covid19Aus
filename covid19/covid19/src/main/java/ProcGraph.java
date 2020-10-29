import processing.core.PApplet;

public class ProcGraph extends Graph {
    ProcGraph(PApplet app, int posX, int posY, int xSize, int ySize, int colon) {
        super(app, posX, posY, xSize, ySize, colon);
    }

    @Override
    void draw() {
            pApplet.fill(0);
            int x1, y1, x2,  y2;
            x1 = 0;
            y1 = ySize;


            for (int i=0; i<IndputList.size(); ++i) {

                int maxList = 0;
                for (int j = 0; j < IndputList.size(); ++j) {
                    if (IndputList.get(j).Death > maxList) {
                        maxList = IndputList.get(j).Death;
                        maxY = Math.max(maxList, maxY);
                    }
                }
                xInt = xSize/IndputList.size();
                yInt = (float) ySize/maxY;


                x2 = (int) xInt * i;
                y2 = ySize - ((int) (IndputList.get(i).Death * yInt));
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

