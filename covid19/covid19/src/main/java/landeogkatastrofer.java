import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;
import processing.data.Table;

//Bazinga min guttermand
public class landeogkatastrofer extends PApplet {

DataBroker data;
String Infofelt = "Land: " + "\nÅR: " +  "\nDøde: ";
TextFlet year,day, mounths, contry;
String[] news = {"Seek immediate medical\nattention if you\nhave serious symptoms.\nAlways call before\nvisiting your doctor\nor health facility."
        ,"People with mild\nsymptoms who are\notherwise healthy should\nmanage their symptoms\n at home."
        ,"On average it\ntakes 5–6 days\nfrom when someone\nis infected with\nthe virus for\nsymptoms to show,\nhowever it can\ntake up to\n14 days."};
int newsInt = 0;
Table table;
PVector[] chosen = {null, null,null};
int chosenshow =0;
Plot plot;
String ChosenDate = " ";
String chosenContrey = "Australia";
AlmindeligKnap btnColonDown, btnColonUp, btnAdvice,btnGernalfacts,btnGraph;
ToggleKnap btnOptions;
int chosenColon = 1;
int[] graphTopic = {4,7,8,25};
PieChart chart = new PieChart(this);
PFont font;
PFont lillefont;


    public static void main(String[] args) { PApplet.main("landeogkatastrofer"); }

    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {
        table = loadTable("owid-covid-data.csv");
        data = new DataBroker(this, table );
        font =createFont("WorkSans-ExtraBold.ttf",16);
        lillefont = createFont("WorkSans-Medium.ttf",16);
        data.loadData();
        btnGernalfacts = new AlmindeligKnap(this, (width / 3)*2,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14, "general fackts");
        btnGraph =new AlmindeligKnap(this, 0,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14, "insert name");
        btnAdvice = new AlmindeligKnap(this, width / 3,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14, "advance");
        btnOptions = new ToggleKnap(this, 0,(int) (height / 12  ) + (height / 12 +20)*1/2 + height / 14, width / 3, height / 14, "Options");

        year = new TextFlet(this,  width / 24, (int) (height / 12  ) + (height / 12 +20)*2, width / 12, height / 12, "Year");
        day = new TextFlet(this,  width / 24 + width / 12 +10 , (int) (height / 12  ) + (height / 12 +20)*2  , width / 12, height / 12, "Day");
        mounths = new TextFlet(this,  width / 24 + (width / 12 +10)*2, (int) (height / 12  ) + (height / 12 +20)*2 , width / 12 , height / 12, "Months");

        btnColonUp = new AlmindeligKnap(this,width / 24,
                (int) (height / 12  ) + (height / 12 +20)*4
                , (width / 24), height / 12, "<");

        btnColonDown = new AlmindeligKnap(this,width / 24 + (width / 12 +7)*3 - (width / 24 )
                , (int) (height / 12  ) + (height / 12 +20)*4
                , (width / 24 ), height / 12, ">");

        contry = new TextFlet(this, width / 24, (int) (height / 12  ) + (height / 12 +20)*3  , (width / 12 +7)*3, height / 12, "Countries");
        contry.indput = chosenContrey;
        plot = new Plot(this,width/2 - width/8, height/3, ( width/2), height/4, data);
       // println(data.getData("AFGHANISTAN", 2014));
        year.indput = "2020";
        plot.deathGraph.inputTable(data.covidData);
        plot.pillarChart.inputTable(data.covidData);

        chosen[0] = new PVector(0,(int) (height / 12  ) + (height / 12 +20)*1/2);
        chosen[1] = new PVector(width/3,(int) (height / 12  ) + (height / 12 +20)*1/2);
        chosen[2] = new PVector((width/3)*2,(int) (height / 12  ) + (height / 12 +20)*1/2);



    }

    @Override
    public void draw() {
        textFont(lillefont);
        clear();
        textSize(16);
        background(232, 244, 255);

        fill(157, 183, 209);
        rect(0,0,width,(height / 14)*3-2);
        fill(232, 244, 255);
        rect(chosen[chosenshow].x, chosen[chosenshow].y,width / 3, height / 14);
        if(chosenshow ==0) {
            textFont(lillefont);
            plot.draw();
            btnOptions.tegnKnap();
            if (btnOptions.klikket) {
                textFont(lillefont);
                year.tegnTextFlet();
                day.tegnTextFlet();
                mounths.tegnTextFlet();
                contry.tegnTextFlet();

                text(Infofelt, width / 24 + (width / 12 + 40), (int) (height / 12) + (height / 12 + 20) * 6);
                text(data.covidData.getString(0, graphTopic[chosenColon]), width / 24 + (width / 12 + 40), (int) (height / 12) + (height / 12 + 20) * 9 / 2);
                btnColonDown.tegnKnap();
                btnColonUp.tegnKnap();

            }
        } else if(chosenshow ==1){
            textFont(font);
            textBox("WASH YOUR HANDS \nOR\nUSE HAND SANITISER",height/14);
            textBox("hello",(height/14)*2+ 250);
            textBox("hello",width-((height/14)*1+ 250));
            textBox("hello",width-((height/14)*2) - 500);
        } else if(chosenshow == 2){
            textFont(font);
            fill(0);
            String gf= "Date: \n" +data.covidData.getString(data.covidData.getRowCount()-1,3) +"\n\nInfected: \n" + data.covidData.getString(data.covidData.getRowCount()-1,4) + "\n\nTotal death: \n" + data.covidData.getString(data.covidData.getRowCount()-1,7);
            textBox(gf,width-(height/14) - 250);
            if (frameCount % 500 == 0) {
                if (news.length - 1 > newsInt){
                    newsInt++;
            }else{
                    newsInt =0;
                }
            }
            textBox("REMEMBER\n\n"+news[newsInt],width-((height/14)*2) - 500);
            textBox("MOST COMMON SYMPTOMS: \n\n-fever\n \n-dry\n \n-cough\n \n-tiredness\n",(height/14));
            textBox( "LESS COMMON SYMPTOMS:\n\n-aches and pains\n" +
                    "\n-sore throat\n" +
                    "\n-diarrhoea\n" +
                    "\n-conjunctivitis\n" +
                    "\n-headache\n" +
                    "\n-loss of taste or smell\n" +
                    "\n-a rash on skin\n  "
                   +
                    "\n-discolouration of \nfingers or toes",(height/14)+250);

        }
        textFont(font);
        btnGernalfacts.tegnKnap();
        btnGraph.tegnKnap();
        btnAdvice.tegnKnap();
        textFont(lillefont);




    }

    @Override
    public void mouseClicked() {
        System.out.println(graphTopic[chosenColon]);
        plot.clicked(mouseX,mouseY);
        btnOptions.registrerKlik(mouseX,mouseY);
        btnAdvice.registrerKlik(mouseX,mouseY);
        btnGraph.registrerKlik(mouseX,mouseY);
        btnGernalfacts.registrerKlik(mouseX,mouseY);
        if(btnGraph.klikket){
            chosenshow = 0;
            btnGraph.registrerRelease();
        }
        if(btnGernalfacts.klikket){
            chosenshow = 2;
            btnGernalfacts.registrerRelease();
        }
        if(btnAdvice.klikket){
            chosenshow = 1;
            btnAdvice.registrerRelease();
        }


        if(!btnOptions.klikket) {
            plot.changeSize(100+ height/14,200+ height/14,1000 ,370- height/14,height,width);

        } else {
            year.KlikTjek(mouseX, mouseY);
            day.KlikTjek(mouseX, mouseY);
            mounths.KlikTjek(mouseX, mouseY);
            contry.KlikTjek(mouseX, mouseY);
            plot.changeSize(width/2 - width/8, height/3+ height/14 , ( width/2)- height/14, height/4- height/14,height,width);

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

    public void textBox(String s,float x){
        fill(191, 223, 255);
        rect(x,200,250,519-height / 14);
        textSize(14);

        fill(57, 92, 128);
        text(s,x+125- textWidth(s)/2,200+height/14 );
    }
}
