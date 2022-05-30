package com.example.cryptochart;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class CryptoData {
    private LongProperty time;
    private DoubleProperty open;
    private DoubleProperty close;
    private DoubleProperty high;
    private DoubleProperty low;

    public CryptoData (Long time, Double open, Double close, Double high, Double low) {
        this.time = new SimpleLongProperty(this, "time");
        this.open = new SimpleDoubleProperty(this, "open");
        this.close = new SimpleDoubleProperty(this, "close");
        this.high = new SimpleDoubleProperty(this, "high");
        this.low = new SimpleDoubleProperty(this, "low");
        this.setTime(time);
        this.setOpen(open);
        this.setClose(close);
        this.setHigh(high);
        this.setLow(low);
        }

        public long getTime() { return time.get(); }
        public void setTime(long time) { this.time.set(time); }

        public double getOpen() { return open.get(); }
        public void setOpen(double open) { this.open.set(open); }

        public double getClose() { return close.get(); }
        public void setClose(double close) { this.close.set(close); }

        public double getHigh() { return high.get(); }
        public void setHigh(double high) { this.high.set(high); }

        public double getLow() { return low.get(); }
        public void setLow(double low) { this.low.set(low); }

        public static ObservableList<CryptoData> getCryptoData () {
            String addr = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
            /* I put a single address here to see if it would work, it isn't
            * JavaFX is acting funny with the GSON library. I added it, but it still wouldn't let me
            * "import class". I added the import statements at top manually, I still can't get data. */

            try {
                URL address = new URL(addr);
                JsonReader reader = new JsonReader (new InputStreamReader(address.openStream()));

                Gson gson = new Gson();
                JsonObject root = gson.fromJson(reader, JsonObject.class);
                JsonObject data = root.getAsJsonObject("Data");

                ObservableList<CryptoData> value = FXCollections.observableArrayList();

                Set<Map.Entry<String, JsonElement>> entrySet = data.entrySet();

                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    Long time = entry.getValue().getAsLong();
                    Double open = entry.getValue().getAsDouble();
                    Double close = entry.getValue().getAsDouble();
                    Double high = entry.getValue().getAsDouble();
                    Double low = entry.getValue().getAsDouble();

                    // convert the date to something readable...
                    //Date date = new Date();
                    //date.setTime((long)time*1000);

                    System.out.println(time + " " + open);                 //a test to see if anything is coming through - nothing?
                }
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
