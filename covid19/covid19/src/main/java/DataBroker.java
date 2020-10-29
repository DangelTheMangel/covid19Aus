import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;


public class DataBroker<data> extends PApplet {
    PApplet p;
    Table data;
    Table covidData = new Table();

   public ArrayList<Data> Datalist = new ArrayList<Data>();

    DataBroker(PApplet p, Table data){
        this.p = p;
        this.data = data;



    }

    public void loadData() {
        covidData.addRow(data.getRow(0));
      for(int i = 0 ; i < data.getRowCount() ; ++i){

          if(data.getString(i,2).equalsIgnoreCase("Australia")){
              covidData.addRow(data.getRow(i));
          }

          for(int j = 0; j< covidData.getRowCount(); ++j){
              System.out.println(covidData.getString(j,1));

          }

      }
        /*
        for(int i = 1; i < data.getRowCount(); ++i){
            for(int j = 2; j < data.getColumnCount(); ++j) {
                String country = data.getString( i ,1);
                int year = data.getInt(0, j);
                int deaths = data.getInt(i, j);
                Data myData = new Data(country, year, deaths);
                Datalist.add(myData);
                println("land: " + myData.name + " år: " + myData.Year + " døde: " + myData.Death);
            }
        }*/
    }
  public int getData(String Year){
        int a = 0;

        return a;
    }

 /*    public ArrayList<Integer> getAllContryDeath(String countryName){
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
*/



}
