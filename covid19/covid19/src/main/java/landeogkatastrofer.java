import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;
//Bazinga min guttermand
public class landeogkatastrofer extends PApplet {

DataBroker data;
String Infofelt = "Land: " + "\nÅR: " +  "\nDøde: ";
TextFlet year,day, mounths;
Table table;
Plot plot;
String ChosenDate = " ";
PieChart chart = new PieChart(this);


    public static void main(String[] args) { PApplet.main("landeogkatastrofer"); }

    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {
        table = loadTable("owid-covid-data.csv");
        data = new DataBroker(this, table );

        data.loadData();
        year = new TextFlet(this,  width / 24, (int) (height / 12  ), width / 4, height / 12, "Year");
        day = new TextFlet(this,  width / 24 , (int) (height / 12  ) + height / 12 +20 , width / 4, height / 12, "Day");
        mounths = new TextFlet(this,  width / 24, (int) (height / 12  ) + (height / 12 +20)*2  , width / 4 , height / 12, "mounths");
        plot = new Plot(this,width/2 - width/8, height/3, ( width/2), height/4);
       // println(data.getData("AFGHANISTAN", 2014));
        year.indput = "2020";
        plot.deathGraph.inputTable(data.covidData);

    }

    @Override
    public void draw() {

        clear();
        background(200);
        plot.draw();
        year.tegnTextFlet();
        day.tegnTextFlet();
        mounths.tegnTextFlet();

        text(Infofelt,width - width / 4, height / 12  );



    }

    @Override
    public void mouseClicked() {
        year.KlikTjek(mouseX,mouseY);
        day.KlikTjek(mouseX,mouseY);
        mounths.KlikTjek(mouseX,mouseY);
        plot.clicked(mouseX,mouseY);
    }

    @Override
    public void keyTyped() {
        year.keyindput(key);
        day.keyindput(key);
        mounths.keyindput(key);
        int aarInt = 0;
        plot.deathGraph = new ProcGraph(this,plot.posX, plot.posY, plot.xSize, plot.ySize, 6);
        //if (year.indput.length() > 0)
           // aarInt = Integer.parseInt(aar.indput);
        if(ChosenDate !=  year.indput + "-"+mounths.indput+"-"+day.indput){
            ChosenDate = year.indput + "-"+mounths.indput+"-"+day.indput;
            System.out.println(data.getAllDeaths(ChosenDate) + " " + ChosenDate);
            Infofelt = "Year: " + year.indput + "\nDay: " + day.indput + "\nmouths: " + mounths.indput + "\nTotal Deaths: " + data.getAllDeaths(ChosenDate);
            plot.deathGraph.inputTable(data.covidData);
        }


    }
}
