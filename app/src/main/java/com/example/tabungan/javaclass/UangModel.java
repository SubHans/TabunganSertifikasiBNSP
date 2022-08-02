package com.example.tabungan.javaclass;

public class UangModel {

    private String nominal, keterangan, tanggal;
    private int status;

    public UangModel(int status, String nominal, String keterangan, String tanggal) {
        this.status = status;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
