package com.example.cryptochart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Controller {
    @FXML
    private LineChart line_chart;

    public String url;

    @FXML
    protected void loadDay () {
        url = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    }
    @FXML
    protected void loadHour () {
        url = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    }
    @FXML
    protected void loadMinute () {
        url = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    }

    /*public void drawChart () {
        line_chart.getXAxis().setLabel("Time");
        line_chart.getYAxis().setLabel("Value");
    }

     */

    public ObservableList<CryptoData> loadData () {
        ObservableList<CryptoData> values = CryptoData.getCryptoData();
        return values;
    }

    public XYChart.Series<Long, Double> setupChartValues(ObservableList<CryptoData> values) {
        XYChart.Series<Long, Double> series = new XYChart.Series<>();
        Long time = 0L;
        Double open = 0.0;

        series.getData().add(new XYChart.Data<Long, Double>(time, open));

        return series;
    }

    public void drawChart (XYChart.Series<Long, Float> ch) {
        line_chart.getXAxis().setLabel("Time");
        line_chart.getYAxis().setLabel("Value");
        line_chart.getData().setAll(ch);
    }

}