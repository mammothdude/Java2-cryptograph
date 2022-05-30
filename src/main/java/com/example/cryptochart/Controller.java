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
        this.setupChartValues();
        this.loadData();
        this.drawChart();
    }
    @FXML
    protected void loadHour () {
        url = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
        this.setupChartValues();
        this.loadData();
        this.drawChart();
    }
    @FXML
    protected void loadMinute () {
        url = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
        this.setupChartValues();
        this.loadData();
        this.drawChart();
    }

    /*
    *       before I could work on passing button url to the CryptoData class I found that
    *       JavaFX was refusing to let me add the GSON library to this project (or
    *       any JavaFX project I tried.)
    *       I can println JSON data from a regular Java, but not JavaFX projects.
    */

    public ObservableList<CryptoData> loadData () {
        ObservableList<CryptoData> values = CryptoData.getCryptoData();
        return values;
    }

    public XYChart.Series<Long, Double> setupChartValues() {
        XYChart.Series<Long, Double> series = new XYChart.Series<>();
        Long time = 0L;
        Double open = 0.0;

        series.getData().add(new XYChart.Data<Long, Double>(time, open));

        return series;
    }

    public void drawChart() {
        line_chart.getXAxis().setLabel("Time");
        line_chart.getYAxis().setLabel("Value");
        line_chart.getData().setAll();
    }

}