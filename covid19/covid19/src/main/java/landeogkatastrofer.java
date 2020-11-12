import processing.core.PApplet;
import processing.data.Table;

//Bazinga min guttermand
public class landeogkatastrofer extends PApplet {

DataBroker data;
String Infofelt = "Land: " + "\nÅR: " +  "\nDøde: ";
TextFlet year,day, mounths, contry;
Table table;
Plot plot;
String ChosenDate = " ";
String chosenContrey = "Australia";
AlmindeligKnap btnColonDown, btnColonUp;
ToggleKnap btnOptions;
int chosenColon = 1;
int[] graphTopic = {4,7,8,25};
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
        year = new TextFlet(this,  width / 24, (int) (height / 12  ), width / 12, height / 12, "Year");
        day = new TextFlet(this,  width / 24 + width / 12 +10 , (int) (height / 12  )  , width / 12, height / 12, "Day");
        mounths = new TextFlet(this,  width / 24 + (width / 12 +10)*2, (int) (height / 12  )  , width / 12 , height / 12, "Months");

        contry = new TextFlet(this, width / 24, (int) (height / 12  ) + (height / 12 +20)*1  , (width / 12 +7)*3, height / 12, "Countries");
        contry.indput = chosenContrey;
        plot = new Plot(this,width/2 - width/8, height/3, ( width/2), height/4, data);
       // println(data.getData("AFGHANISTAN", 2014));
        year.indput = "2020";
        plot.deathGraph.inputTable(data.covidData);
        plot.pillarChart.inputTable(data.covidData);

        btnColonUp = new AlmindeligKnap(this,width / 24, (int) (height / 12  ) + (height / 12 +20)*2  , (width / 24), height / 12, "<");
        btnColonDown = new AlmindeligKnap(this,width / 24 + (width / 12 +7)*3 - (width / 24 ), (int) (height / 12  ) + (height / 12 +20)*2  , (width / 24 ), height / 12, ">");
        btnOptions = new ToggleKnap(this, width / 24,height / 30, width / 20, height / 14, "Options");




    }

    @Override
    public void draw() {

        clear();
        background(200);
        plot.draw();
        if(btnOptions.klikket){
            year.tegnTextFlet();
            day.tegnTextFlet();
            mounths.tegnTextFlet();
            contry.tegnTextFlet();

            text(Infofelt,width - width / 4, height / 12  );
            text(data.covidData.getString(0,graphTopic[chosenColon]),width / 24 + (width / 12+ 40), (int) (height / 12  ) + (height / 12 +20)*2  );
            btnColonDown.tegnKnap();
            btnColonUp.tegnKnap();

        }
        btnOptions.tegnKnap();




    }

    @Override
    public void mouseClicked() {
        System.out.println(graphTopic[chosenColon]);
        plot.clicked(mouseX,mouseY);
        btnOptions.registrerKlik(mouseX,mouseY);
        if(!btnOptions.klikket) {
            plot.changeSize(100,200,1000,370,height,width);

        } else {
            year.KlikTjek(mouseX, mouseY);
            day.KlikTjek(mouseX, mouseY);
            mounths.KlikTjek(mouseX, mouseY);
            contry.KlikTjek(mouseX, mouseY);
            plot.changeSize(width/2 - width/8, height/3, ( width/2), height/4,height,width);

            btnColonDown.registrerKlik(mouseX, mouseY);
            btnColonUp.registrerKlik(mouseX, mouseY);

            if (btnColonDown.klikket) {
                if (chosenColon - 1 >= 0) {
                    chosenColon--;
                } else {
                    chosenColon = graphTopic.length - 1;
                }
                plot.deathGraph.maxY = Integer.MIN_VALUE;
                plot.pillarChart.maxY = Integer.MIN_VALUE;
                plot.deathGraph.colon = graphTopic[chosenColon];
                plot.pillarChart.colon = graphTopic[chosenColon];
                plot.colon = graphTopic[chosenColon];
                btnColonDown.registrerRelease();
            }

            if (btnColonUp.klikket) {
                if (chosenColon + 1 < graphTopic.length - 1) {
                    chosenColon++;
                } else {
                    chosenColon = 0;
                }
                plot.deathGraph.maxY = Integer.MIN_VALUE;
                plot.pillarChart.maxY = Integer.MIN_VALUE;
                plot.deathGraph.colon = graphTopic[chosenColon];
                plot.pillarChart.colon = graphTopic[chosenColon];
                plot.colon = graphTopic[chosenColon];

          /*  plot.deathGraph = new ProcGraph(this,plot.posX,plot.posY,plot.xSize,plot.ySize,graphTopic[chosenColon]);
            plot.pillarChart = new PillarChart(this,plot.posX,plot.posY,plot.xSize,plot.ySize,graphTopic[chosenColon]);*/
                btnColonUp.registrerRelease();
            }
        }
    }

    @Override
    public void keyTyped() {
        year.keyindput(key);
        day.keyindput(key);
        mounths.keyindput(key);
        contry.keyindput(key);
        int aarInt = 0;
        plot.deathGraph = new ProcGraph(this,plot.posX, plot.posY, plot.xSize, plot.ySize, 6);
        //if (year.indput.length() > 0)
           // aarInt = Integer.parseInt(aar.indput);
        if(ChosenDate !=  year.indput + "-"+mounths.indput+"-"+day.indput){
            ChosenDate = year.indput + "-"+mounths.indput+"-"+day.indput;
            System.out.println(data.getAllDeaths(ChosenDate) + " " + ChosenDate);
            Infofelt = "Year: " + year.indput + "\nDay: " + day.indput + "\nmonths: " + mounths.indput + "\nTotal Deaths: " + data.getAllDeaths(ChosenDate);
            plot.deathGraph.inputTable(data.covidData);
            plot.pillarChart.inputTable(data.covidData);
            plot.deathGraph.graphStart = plot.getGraphStarter(ChosenDate);
            plot.pillarChart.graphStart = plot.getGraphStarter(ChosenDate);
        }


    }
}
