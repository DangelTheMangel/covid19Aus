import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class PillarChart {
    PApplet p; int posX; int posY; int xSize; int ySize;
    int maxY = Integer.MIN_VALUE;
    float xInt;
    float yInt;
    int colon;
    int graphStart = 0;
    Table table = new Table();

    public PillarChart(PApplet p, int posX, int posY, int xSize, int ySize, int colon) {
        this.p = p;
        this.colon = colon;
        this.xSize = xSize;
        this.ySize = ySize;
        this.posX = posX;
        this.posY = posY;
    }

    void drawPillarChart( String Name){
       int x2;
        for (int i=graphStart; i<table.getRowCount(); ++i) {

            int maxList = 0;
            for (int j = 0; j < table.getRowCount(); ++j) {
                if (table.getInt(j,colon) > maxList) {
                    maxList = table.getInt(j,colon);
                    maxY = Math.max(maxList, maxY);
                }
            }

            xInt = xSize/table.getRowCount();
            yInt = ySize*table.getInt(i -graphStart,colon)/maxY;
            x2 = (int) (posX + xInt*i -graphStart);


            p.rect(x2,posY+ySize,xInt,-yInt);
        }

    }

    void inputTable(Table input){
        table = input;
    }
}