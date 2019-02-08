package com.soilair.moisture.app.data.object;

/**
 * Created by User on 21/02/2018.
 */

public class Sensor {
    private String waktu_suhu;
    private String waktu_kelembaban;
    private int data_suhu;
    private int data_kelembaban;

    public String getWaktu_suhu() {
        return waktu_suhu;
    }

    public void setWaktu_suhu(String waktu_suhu) {
        this.waktu_suhu = waktu_suhu;
    }

    public String getWaktu_kelembaban() {
        return waktu_kelembaban;
    }

    public void setWaktu_kelembaban(String waktu_kelembaban) {
        this.waktu_kelembaban = waktu_kelembaban;
    }

    public int getData_suhu() {
        return data_suhu;
    }

    public void setData_suhu(int data_suhu) {
        this.data_suhu = data_suhu;
    }

    public int getData_kelembaban() {
        return data_kelembaban;
    }

    public void setData_kelembaban(int data_kelembaban) {
        this.data_kelembaban = data_kelembaban;
    }
}
