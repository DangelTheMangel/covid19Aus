import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;


public class DataBroker<data> extends PApplet {
    PApplet p;
    Table data;
    Table covidData = new Table();

    public ArrayList<Data> Datalist = new ArrayList<Data>();

    DataBroker(PApplet p, Table data) {
        this.p = p;
        this.data = data;

    }

    public void loadData() {
        covidData.addRow(data.getRow(0));
        for (int i = 0; i < data.getRowCount(); ++i) {
            if (data.getString(i, 2).equalsIgnoreCase("Australia")) {
                covidData.addRow(data.getRow(i));
            }
        }
        for (int j = 0; j < covidData.getRowCount(); ++j) {
            System.out.println(covidData.getString(j, 1));
        }

    }

    public String getAllDeaths(String date) {
       
        String deaths = "none";
        for (int i = 0; i < covidData.getRowCount(); i++) {

            if (date.equals(covidData.getString(i, 3))) {

                deaths =  covidData.getString(i, 6);
            }
        }

        return deaths;
    }




}
