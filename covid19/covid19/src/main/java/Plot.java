import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class Plot {
    PApplet p;
    Table table;
    int xSize, ySize , posX, posY;
    ProcGraph deathGraph;
    Axis xAxis;
    Axis yAxis;
    DataBroker dataBroker;
    int colon = 7;
    AlmindeligKnap btnShowProcGraph, btnShowBarCharts, btnShowLines;

    public ArrayList<Data> Datalist = new ArrayList<Data>() ;
    public ArrayList<Data> selectetContryList = new ArrayList<Data>();
    PillarChart pillarChart;
    String cName;

    Plot(PApplet app, int posX, int posY, int xSize, int ySize, DataBroker dataBroker){
        p = app;
        this.dataBroker = dataBroker;
        this.table = table;
        this.xSize = xSize;
        this.ySize = ySize;
        this.posX = posX;
        this.posY = posY;
        Datalist.add(new Data("1",1,1));
        pillarChart = new PillarChart(p,this.posX, this.posY, this.xSize, this.ySize, colon);
        deathGraph = new ProcGraph(p,this.posX, this.posY, this.xSize, this.ySize, colon);
        btnShowProcGraph = new AlmindeligKnap(p,p.width / 24, (int) (p.height / 12  ) + (p.height / 12 +20)*5, p.width / 12, p.height / 12, "Proc graph");
        btnShowBarCharts = new AlmindeligKnap(p,p.width / 24 + (p.width / 12 +10)*2, (int) (p.height / 12  ) + (p.height / 12 +20)*5, p.width / 12, p.height / 12, "Bar charts");
        btnShowLines = new AlmindeligKnap(p,p.width / 24, (int) (p.height / 12  ) + (p.height / 12 +20)*6, p.width / 12, p.height / 12,"Turn off \nlines");
        xAxis = new Axis (p, this.posX , this.posY + this.ySize, this.posX + this.xSize, this.posY + this.ySize, false, Datalist, deathGraph.xInt, deathGraph.yInt , 5);
        yAxis = new Axis (p, this.posX , posY + ySize , posX, posY, true, Datalist, deathGraph.xInt, deathGraph.yInt, 50000);
        btnShowProcGraph.klikket = true;
        //btnShowBarCharts.klikket = true;
    }

    void  draw(String s){

        p.fill(191, 223, 255);
        p.rect(posX  - 50, posY - 100, xSize + 100, ySize + 270 );

        p.fill(191, 223, 255);
        if(btnShowProcGraph.klikket){
            xAxis.maxY = deathGraph.maxY;
            yAxis.maxY = deathGraph.maxY;
            deathGraph.IndputList = selectetContryList;
            p.fill(255);
            p.rect(posX, posY - 10, xSize + 10 , ySize + 10);
            p.stroke(0);
            deathGraph.draw();
            xAxis.draw();
            yAxis.draw();
            checkMouseCoordinates();


        }
        if(btnShowBarCharts.klikket){
           xAxis.maxY = deathGraph.maxY;


            yAxis.maxY = deathGraph.maxY;

            p.fill(255);
            p.rect(posX, posY - 10, xSize + 10 , ySize + 10);
            p.stroke(0);
            p.fill(0);
            pillarChart.drawPillarChart(selectetContryList,cName);
            xAxis.draw();
            yAxis.draw();
            checkMouseCoordinates();

        }



        p.fill(255);
        p.textSize(20);
        p.text("Graph Shows: "+s,posX+xSize/2-p.textWidth(s),posY-32);
        p.textSize(16);
    }

    public void setArraylist(ArrayList<Data> IndputList, String countryName){
        if(IndputList == null){} else{
           selectetContryList.clear();
        }


        Datalist = IndputList;

        for(int i = 0; i < Datalist.size(); ++i) {
            Data data = Datalist.get(i);
             if(countryName.equalsIgnoreCase(data.name)) {
                Data input = Datalist.get(i);
                selectetContryList.add(new Data(data.name, data.Year, data.Death));
                cName = countryName;

            }
        }
    }

    public void clicked(float mx, float my){


        btnShowBarCharts.registrerKlik(mx,my);
        if(btnShowBarCharts.klikket){
            btnShowProcGraph.klikket = false;
        }
        btnShowProcGraph.registrerKlik(mx,my);
        if(btnShowProcGraph.klikket){
            btnShowBarCharts.klikket = false;
            btnShowLines.registrerKlik(mx, my);
        }

        if(deathGraph.linesOn){
            btnShowLines.text = "Turn off \nlines";
        }else {
            btnShowLines.text = "Turn on \nlines";
        }
    }

    void checkMouseCoordinates(){

       int row = deathGraph.getRowFromMouse(p.mouseX, p.mouseY);
        if(row > 0 && row < dataBroker.covidData.getRowCount() ) {
            p.stroke(1, 46, 74);
            p.fill(174, 200, 245);
            p.rect(p.mouseX, p.mouseY, 200,200);
            p.fill(1, 46, 74);

            String msgDeaths = dataBroker.covidData.getString(row,7);
            String msgDate = dataBroker.covidData.getString(row,3);
            String msgName = dataBroker.covidData.getString(row,2);
            String msgCases = dataBroker.covidData.getString(row,4);
            String msgNewCases = dataBroker.covidData.getString(row,5);
            String msgNewtestet = dataBroker.covidData.getString(row,25);
            String msgNewDeath = dataBroker.covidData.getString(row,8) ;


            p.text("Year: " + msgDate , p.mouseX + 10, p.mouseY + 30);
            p.text("Countrey: " + msgName, p.mouseX + 10, p.mouseY + 50);
            p.text("Total Deaths: " + msgDeaths, p.mouseX + 10, p.mouseY + 70);
            p.text("Total Case: " + msgCases, p.mouseX + 10, p.mouseY + 90);
            p.text("New Cases: " + msgNewCases, p.mouseX + 10, p.mouseY + 110);
            p.text("New Test: " + msgNewtestet, p.mouseX + 10, p.mouseY + 130);
            p.text("New Deaths: " + msgNewDeath, p.mouseX + 10, p.mouseY + 150);

            int x1 = (int) deathGraph.getXInt() * row;
            int y1 = ySize - ((int) (deathGraph.table.getInt(row,colon) * deathGraph.yInt));

            p.ellipse(x1 + posX, y1 + posY,10,10);

        }

    }

    int getGraphStarter(String date){
       int staterNumb = 0;

       for (int i = 0; i<dataBroker.covidData.getRowCount(); ++i){
           if(date.equalsIgnoreCase(dataBroker.covidData.getString(i,3))){
               staterNumb = i;
           }
       }

       return staterNumb;
    }

    void changeSize(int x, int y, int sX, int sY , int h, int w){
        posX = x;
        posY = y;
        xSize = sX;
        ySize = sY;
       deathGraph.posX = x;
       deathGraph.posY = y;
        deathGraph.xSize = sX;
        deathGraph.ySize = sY;
     pillarChart.posX = x;
       pillarChart.posY = y;
         pillarChart.xSize = sX;
         pillarChart.ySize = sY;
        xAxis.x1 = x; xAxis.x2 = x+sX; xAxis.y1 = y + sY; xAxis.y2 = y+sY;
        yAxis.x1 = x; yAxis.x2 = x; yAxis.y1 = y + sY; yAxis.y2 = y;
///

/*//
 btnShowProcGraph = new AlmindeligKnap(p,posX - 50, (int) (p.height / 12  + ( p.height / 9)), p.width / 12, p.height / 12, "Proc graph");
        btnShowBarCharts = new AlmindeligKnap(p,posX - 50 + p.width / 12 + 20, (int) (p.height / 12  + ( p.height / 9)), p.width / 12, p.height / 12, "Bar charts");
        btnShowLines = new AlmindeligKnap(p,posX, posY+ySize + 20,p.width / 12 +20, p.height / 12 ,"Turn off lines");
 */

    }

    void drawBtN(){
        if(btnShowProcGraph.klikket){
            btnShowLines.tegnKnap();
            if(btnShowLines.klikket){
                deathGraph.linesOn = !deathGraph.linesOn;
                btnShowLines.registrerRelease();
            }
        }

        btnShowProcGraph.tegnKnap();
        btnShowBarCharts.tegnKnap();

    }
    }

