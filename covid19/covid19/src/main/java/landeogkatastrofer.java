import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class landeogkatastrofer extends PApplet {

DataBroker data;
String Infofelt = "Land: " + "\nÅR: " +  "\nDøde: ";
TextFlet lande;
TextFlet aar;
Table table;
Plot plot;
PieChart chart = new PieChart(this);


    public static void main(String[] args) { PApplet.main("landeogkatastrofer"); }

    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {
        table = loadTable("csv-deaths-natural-disasters.csv");
        data = new DataBroker(this, table );

        data.loadData();
        lande = new TextFlet(this,  width / 12, (int) (height / 12  ), width / 4, height / 12, "Country");
        aar = new TextFlet(this,  width / 12 + width / 4 + 20, (int) (height / 12  ) , width / 4, height / 12, "year");
        aar.setAcceptLetter(false);
        plot = new Plot(this,width/8, height/3, (width - width/4), height/2);
        println(data.getData("AFGHANISTAN", 2014));
    }

    @Override
    public void draw() {
        plot.setArraylist(data.Datalist, lande.indput);
        clear();
        background(200);
        plot.draw();
        lande.tegnTextFlet();
        aar.tegnTextFlet();

        text(Infofelt,width - width / 4, height / 12  );



    }

    @Override
    public void mouseClicked() {
        lande.KlikTjek(mouseX,mouseY);
        aar.KlikTjek(mouseX,mouseY);
        plot.clicked(mouseX,mouseY);
    }

    @Override
    public void keyTyped() {
        lande.keyindput(key);
        aar.keyindput(key);
        int aarInt = 0;
        plot.deathGraph = new ProcGraph(this,plot.posX, plot.posY, plot.xSize, plot.ySize, 1);
        if (aar.indput.length() > 0)
            aarInt = Integer.parseInt(aar.indput);
        Infofelt = "Land: " + lande.indput + "\nÅR: " + aar.indput + "\nDøde: " + data.getData(lande.indput, aarInt);

    }
}
