import processing.core.PApplet;

import java.util.ArrayList;

public class PillarChart {
    PApplet p; int posX; int posY; int xSize; int ySize;
    int maxY = Integer.MIN_VALUE;
    float xInt;
    float yInt;
    public PillarChart(PApplet p, int posX, int posY, int xSize, int ySize) {
        this.p = p;
        this.xSize = xSize;
        this.ySize = ySize;
        this.posX = posX;
        this.posY = posY;
    }

    void drawPillarChart(ArrayList<Data> inputlist, String Name){
       int x2;
        for (int i=0; i<inputlist.size(); ++i) {

            int maxList = 0;
            for (int j = 0; j < inputlist.size(); ++j) {
                if (inputlist.get(j).Death > maxList) {
                    maxList = inputlist.get(j).Death;
                    maxY = Math.max(maxList, maxY);
                }
            }

            xInt = xSize/inputlist.size();
            yInt = ySize*inputlist.get(i).Death/maxY;
            x2 = (int) (posX + xInt*i);
            System.out.println(" year: " + inputlist.get(i).Year + "death: " + inputlist.get(i).Death );

            p.rect(x2,p.height-(posY- 120),xInt,-yInt);
        }

    }
}