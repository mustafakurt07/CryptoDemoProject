package com.kurt.cryptoapp.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class D {

    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("clo")
    @Expose
    private String clo;
    @SerializedName("fields")
    @Expose
    private Fields fields;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClo() {
        return clo;
    }

    public void setClo(String clo) {
        this.clo = clo;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

}
