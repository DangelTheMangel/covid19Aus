import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
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
PVector colorBigbtn = new PVector(143, 182, 217),colorSmallbrn = new PVector(242, 242, 242), colorMediumBtn = new PVector(204, 211, 217);
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
PImage logo,handtryk,hoste,kontakt,vaskhand;


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
        data.loadData("Australia");

        btnGernalfacts = new AlmindeligKnap(this, (width / 3)*2,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14, "Information and news");
        btnGraph =new AlmindeligKnap(this, 0,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14,
                "Graph dashboard");
        btnAdvice = new AlmindeligKnap(this, width / 3,(int) (height / 12  ) + (height / 12 +20)*1/2 , width / 3, height / 14, "Advice and guidelines");
        btnOptions = new ToggleKnap(this, 0,(int) (height / 12  ) + (height / 12 +20)*1/2 + height / 14, width / 3, height / 14, "Options");

        year = new TextFlet(this,  43, (int) (height / 12  ) + (height / 12 +20)*2,
                ((width / 12 +7)*3)/3-10, height / 12, "Year");
        day = new TextFlet(this,  43 + width / 12 +10 , (int) (height / 12  ) + (height / 12 +20)*2  ,
                ((width / 12 +7)*3)/3-10, height / 12, "Day");
        mounths = new TextFlet(this,  43 + (width / 12 +10)*2, (int) (height / 12  ) + (height / 12 +20)*2 ,
                ((width / 12 +7)*3)/3-10 , height / 12, "Months");

        btnColonUp = new AlmindeligKnap(this,43,
                (int) (height / 12  ) + (height / 12 +20)*4
                , (width / 24), height / 12, "<");

        btnColonDown = new AlmindeligKnap(this,width/3-(width / 24 )-43
                , (int) (height / 12  ) + (height / 12 +20)*4
                , (width / 24 ), height / 12, ">");

        contry = new TextFlet(this, 43, (int) (height / 12  ) + (height / 12 +20)*3  , (width / 12 +7)*3, height / 12, "Countries");
        contry.indput = chosenContrey;
        plot = new Plot(this,width/2 - width/8, height/3, ( width/2), height/4, data);

        year.indput = "2020";
        plot.deathGraph.inputTable(data.covidData);
        plot.pillarChart.inputTable(data.covidData);

        chosen[0] = new PVector(0,(int) (height / 12  ) + (height / 12 +20)*1/2);
        chosen[1] = new PVector(width/3,(int) (height / 12  ) + (height / 12 +20)*1/2);
        chosen[2] = new PVector((width/3)*2,(int) (height / 12  ) + (height / 12 +20)*1/2);

        plot.changeSize(100+ height/14,200+ height/14,1000 ,370- height/14,height,width);
        logo = loadImage("logo.png");
        vaskhand = loadImage("vaskehand.png");
        kontakt = loadImage("kontakt.png");
        hoste =loadImage("hoste.png");
        handtryk =loadImage("handtryk.png");

    }

    @Override
    public void draw() {

        textFont(lillefont);
        clear();
        textSize(16);
        background(204, 211, 217);

        fill(115, 148, 191);
        rect(0, 0, width, (height / 14) * 2 - 2 );
        fill(232, 244, 255);
        textSize(32);
        text("Covid-19 in " + data.covidData.getString(1, 2), width / 2 - textWidth("Covid-19 in Australia") / 2, 50);
        textSize(16);

        if (chosenshow == 0) {
            String s = data.covidData.getString(0, graphTopic[chosenColon]);
            String[] splitS = s.split("_");
            if (splitS.length > 1)
                s = splitS[0] + " " + splitS[1];
            else
                s = splitS[0];
            textFont(lillefont);
            plot.draw(s);
            btnOptions.tegnKnap(colorMediumBtn);
            if (btnOptions.klikket) {
                textFont(lillefont);
                fill(195, 200, 205);
                rect(0, (float) (height / 14 + height / (9.5) * 2), width / 3, height);
                year.tegnTextFlet();
                day.tegnTextFlet();
                mounths.tegnTextFlet();
                contry.tegnTextFlet();


                text(s, width/3/2-textWidth(s)/2, (int) (height / 12) + (height / 12 + 20) * 9 / 2);
                btnColonDown.tegnKnap(colorSmallbrn);
                btnColonUp.tegnKnap(colorSmallbrn);
                plot.drawBtN();

            }
        } else if (chosenshow == 1) {
            textFont(font);
            textBox("WASH YOUR HANDS \nOR\nUSE HAND SANITISER", height / 14,true,vaskhand);
            textBox("COUGH OR SNEEZE \nINTO YOUR SLEEVE", (height / 14) * 2 + 250,true,hoste);
            textBox("AVOID HANDSHAKES, \nKISSES AND HUGS \nLIMIT PHYSICAL CONTACT", width - ((height / 14) * 1 + 250),true,handtryk);
            textBox("KEEP AWAY FROM \nOTHER PEOPLE AND \nASK OTHERS TO \nDO THE SAME", width - ((height / 14) * 2) - 500,true,kontakt);
        } else if (chosenshow == 2) {
            textFont(font);
            fill(41, 61, 82);

            String msgDeaths = data.covidData.getString(data.covidData.getRowCount() - 2, 7);
            String msgDate = data.covidData.getString(data.covidData.getRowCount() - 2, 3);
            String msgCases = data.covidData.getString(data.covidData.getRowCount() - 2, 4);
            String msgNewCases = data.covidData.getString(data.covidData.getRowCount() - 2, 5);
            String msgName = data.covidData.getString(data.covidData.getRowCount() - 2, 2);
            String msgNewDeath = data.covidData.getString(data.covidData.getRowCount() - 2, 8);

            String gf = "Date: \n" + msgDate + "\n\nContrys:\n" + msgName +
                    "\n\nTotal Cases: \n" + msgCases +
                    "\n\nTotal death: \n" + msgDeaths +
                    "\n\nNew Cases: \n" + msgNewCases +
                    "\n\nNew death \n" + msgNewDeath;
            textBox(gf, width - (height / 14) - 250,false,kontakt);
            if (frameCount % 500 == 0) {
                if (news.length - 1 > newsInt) {
                    newsInt++;
                } else {
                    newsInt = 0;
                }
            }
            textBox("REMEMBER\n\n" + news[newsInt], width - ((height / 14) * 2) - 500,false,kontakt);
            textBox("MOST COMMON SYMPTOMS: \n\n-fever\n \n-dry\n \n-cough\n \n-tiredness\n", (height / 14),false,kontakt);
            textBox("LESS COMMON SYMPTOMS:\n\n-aches and pains\n" +
                    "\n-sore throat\n" +
                    "\n-diarrhoea\n" +
                    "\n-conjunctivitis\n" +
                    "\n-headache\n" +
                    "\n-loss of taste or smell\n" +
                    "\n-a rash on skin\n  "
                    +
                    "\n-discolouration of \nfingers or toes", (height / 14) + 250,false,kontakt);

        }
        textFont(font);
        if (chosenshow == 0){
            btnGraph.tegnKnap(colorSmallbrn);
        }
        else{
            btnGraph.tegnKnap(colorBigbtn);}

        if(chosenshow ==2){
            btnGernalfacts.tegnKnap(colorSmallbrn);
        }else{
            btnGernalfacts.tegnKnap(colorBigbtn);
        }



        if(chosenshow ==1){
            btnAdvice.tegnKnap(colorSmallbrn);
        } else {
            btnAdvice.tegnKnap(colorBigbtn);}

        textFont(lillefont);


        image(logo,width-width/5,0,width/8,height/8);

    }

    @Override
    public void mouseClicked() {

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

            ///////////////////////////////////////
            plot.changeSize(   width / 3 + width / 12 , height/3 + height/12 , width/2, height/3-height/14,height,width);

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
        data.covidData.clearRows();
        data.loadData(contry.indput);
        plot.deathGraph = new ProcGraph(this,plot.posX, plot.posY, plot.xSize, plot.ySize, 6);
        if(ChosenDate !=  year.indput + "-"+mounths.indput+"-"+day.indput){
            ChosenDate = year.indput + "-"+mounths.indput+"-"+day.indput;


            plot.deathGraph.inputTable(data.covidData);
            plot.pillarChart.inputTable(data.covidData);
            plot.deathGraph.graphStart = plot.getGraphStarter(ChosenDate);
            plot.pillarChart.graphStart = plot.getGraphStarter(ChosenDate);
        }


    }

    public void textBox(String s,float x, boolean y, PImage p){
        fill(242, 242, 242);
        rect(x,200,250,519-height / 14);
        textSize(14);

        fill(41, 61, 82);
        text(s,x+125- textWidth(s)/2,200+height/14 );

        if(y){
            image(p,x,200+519-height / 14-250,250,250);
        }
    }
}
