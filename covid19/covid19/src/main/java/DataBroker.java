import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;


public class DataBroker<data> extends PApplet {
    PApplet p;
    Table data;

   public ArrayList<Data> Datalist = new ArrayList<Data>();

    DataBroker(PApplet p, Table data){
        this.p = p;
        this.data = data;
    }

    public void loadData() {

        for(int i = 1; i < data.getRowCount(); ++i){
            for(int j = 2; j < data.getColumnCount(); ++j) {
                String country = data.getString( i ,1);
                int year = data.getInt(0, j);
                int deaths = data.getInt(i, j);
                Data myData = new Data(country, year, deaths);
                Datalist.add(myData);
                println("land: " + myData.name + " år: " + myData.Year + " døde: " + myData.Death);
            }
        }
    }
    public int getData(String countryName, int Year){
        int a = 0;
        for(int i = 0; i < Datalist.size(); ++i) {
            Data data = Datalist.get(i);
            if(countryName.equalsIgnoreCase(data.name) && Year == data.Year) {
                a = Datalist.get(i).Death;
            }
        }
        return a;
    }

    public ArrayList<Integer> getAllContryDeath(String countryName){
        ArrayList<Integer> deathList = new ArrayList<Integer>();

        for(int i = 0; i < Datalist.size(); ++i) {
            Data data = Datalist.get(i);
            if(countryName.equalsIgnoreCase(data.name)) {
                deathList.add(Datalist.get(i).Death);
                System.out.println(Datalist.get(i).Death);
            }
        }
        return deathList;
    }




}
