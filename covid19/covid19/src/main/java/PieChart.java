import processing.core.PApplet;

import java.util.ArrayList;

public class PieChart {
    PApplet p;
    float[] angler;
    int allDeaths;


    PieChart(PApplet p){

        this.p = p;
    }
    void drawPieChart( ArrayList<Data> Datalist, String Name){
        for(int i = 0;i < Datalist.size();++i){
            allDeaths += Datalist.get(i).Death;

        }
    }

    void Chart(float diameter, float[] data) {
        float lastAngle = 0;
        for (int i = 0; i < data.length; i++) {
            float gray = p.map(i, 0, data.length, 0, 255);
            p.fill(gray);
            p.arc(p.width/2, p.height/2, diameter, diameter, lastAngle, lastAngle+ p.radians(data[i]));
            lastAngle += p.radians(data[i]);
        }
    }
}
