package com.kurt.cryptoapp.model.list;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CryptoModel implements Serializable {

    @SerializedName("def")
    @Expose
    private String def;
    @SerializedName("hig")
    @Expose
    private String hig;
    @SerializedName("clo")
    @Expose
    private String clo;
    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("ddi")
    @Expose
    private String ddi;
    @SerializedName("cl3")
    @Expose
    private String cl3;
    @SerializedName("pdc")
    @Expose
    private String pdc;
    @SerializedName("tke")
    @Expose
    private String tke;
    @SerializedName("rtp")
    @Expose
    private Boolean rtp;
    @SerializedName("pdd")
    @Expose
    private String pdd;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("sel")
    @Expose
    private String sel;
    @SerializedName("las")
    @Expose
    private String las;

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getHig() {
        return hig;
    }

    public void setHig(String hig) {
        this.hig = hig;
    }

    public String getClo() {
        return clo;
    }

    public void setClo(String clo) {
        this.clo = clo;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getCl3() {
        return cl3;
    }

    public void setCl3(String cl3) {
        this.cl3 = cl3;
    }

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public String getTke() {
        return tke;
    }

    public void setTke(String tke) {
        this.tke = tke;
    }

    public Boolean getRtp() {
        return rtp;
    }

    public void setRtp(Boolean rtp) {
        this.rtp = rtp;
    }

    public String getPdd() {
        return pdd;
    }

    public void setPdd(String pdd) {
        this.pdd = pdd;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public String getLas() {
        return las;
    }

    public void setLas(String las) {
        this.las = las;
    }

    @Override
    public String toString() {
        return "CryptoModel{" +
                "def='" + def + '\'' +
                ", hig=" + hig +
                ", clo='" + clo + '\'' +
                ", buy=" + buy +
                ", ddi=" + ddi +
                ", cl3='" + cl3 + '\'' +
                ", pdc=" + pdc +
                ", tke='" + tke + '\'' +
                ", rtp=" + rtp +
                ", pdd=" + pdd +
                ", low=" + low +
                ", cod='" + cod + '\'' +
                ", sel=" + sel +
                ", las=" + las +
                '}';
    }
}
