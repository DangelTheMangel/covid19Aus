import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;


public class DataBroker<data> extends PApplet {
    PApplet p;
    Table data;
    Table covidData = new Table();



    DataBroker(PApplet p, Table data) {
        this.p = p;
        this.data = data;

    }

    public void loadData(String s) {
        covidData.addRow(data.getRow(0));
        for (int i = 0; i < data.getRowCount(); ++i) {
            if (data.getString(i, 2).equalsIgnoreCase(s)) {
                covidData.addRow(data.getRow(i));
            }
        }
        covidData.addRow();


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
