package com.kurt.cryptoapp.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailExample {
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("gro")
    @Expose
    private String gro;
    @SerializedName("tke")
    @Expose
    private String tke;
    @SerializedName("def")
    @Expose
    private String def;
    @SerializedName("ser")
    @Expose
    private String ser;
    @SerializedName("dco")
    @Expose
    private String dco;
    @SerializedName("gco")
    @Expose
    private String gco;
    @SerializedName("cl3")
    @Expose
    private String cl3;
    @SerializedName("usc")
    @Expose
    private String usc;
    @SerializedName("iss")
    @Expose
    private String iss;
    @SerializedName("aco")
    @Expose
    private String aco;
    @SerializedName("col")
    @Expose
    private String col;
    @SerializedName("sco")
    @Expose
    private String sco;
    @SerializedName("las")
    @Expose
    private String las;
    @SerializedName("pdd")
    @Expose
    private String pdd;
    @SerializedName("ddi")
    @Expose
    private String ddi;
    @SerializedName("rtp")
    @Expose
    private Boolean rtp;
    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("graph")
    @Expose
    private Graph graph;
    @SerializedName("newsTypes")
    @Expose
    private List<Object> newsTypes = null;
    @SerializedName("licenseDesc")
    @Expose
    private LicenseDesc licenseDesc;
    @SerializedName("permissions")
    @Expose
    private Permissions permissions;
    @SerializedName("alarm")
    @Expose
    private Alarm alarm;
    @SerializedName("belowgraph")
    @Expose
    private Belowgraph belowgraph;
    @SerializedName("d")
    @Expose
    private List<D> d = null;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getGro() {
        return gro;
    }

    public void setGro(String gro) {
        this.gro = gro;
    }

    public String getTke() {
        return tke;
    }

    public void setTke(String tke) {
        this.tke = tke;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getDco() {
        return dco;
    }

    public void setDco(String dco) {
        this.dco = dco;
    }

    public String getGco() {
        return gco;
    }

    public void setGco(String gco) {
        this.gco = gco;
    }

    public String getCl3() {
        return cl3;
    }

    public void setCl3(String cl3) {
        this.cl3 = cl3;
    }

    public String getUsc() {
        return usc;
    }

    public void setUsc(String usc) {
        this.usc = usc;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getAco() {
        return aco;
    }

    public void setAco(String aco) {
        this.aco = aco;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getSco() {
        return sco;
    }

    public void setSco(String sco) {
        this.sco = sco;
    }

    public String getLas() {
        return las;
    }

    public void setLas(String las) {
        this.las = las;
    }

    public String getPdd() {
        return pdd;
    }

    public void setPdd(String pdd) {
        this.pdd = pdd;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public Boolean getRtp() {
        return rtp;
    }

    public void setRtp(Boolean rtp) {
        this.rtp = rtp;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public List<Object> getNewsTypes() {
        return newsTypes;
    }

    public void setNewsTypes(List<Object> newsTypes) {
        this.newsTypes = newsTypes;
    }

    public LicenseDesc getLicenseDesc() {
        return licenseDesc;
    }

    public void setLicenseDesc(LicenseDesc licenseDesc) {
        this.licenseDesc = licenseDesc;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Belowgraph getBelowgraph() {
        return belowgraph;
    }

    public void setBelowgraph(Belowgraph belowgraph) {
        this.belowgraph = belowgraph;
    }

    public List<D> getD() {
        return d;
    }

    public void setD(List<D> d) {
        this.d = d;
    }
}
