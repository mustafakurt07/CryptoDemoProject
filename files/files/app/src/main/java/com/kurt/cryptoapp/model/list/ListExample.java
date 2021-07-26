package com.kurt.cryptoapp.model.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListExample {

    @SerializedName("l")
    @Expose
    private List<CryptoModel> cryptoModel = null;
    @SerializedName("z")
    @Expose
    private String z;

    public List<CryptoModel> getCryptoModelList() {
        return cryptoModel;
    }

    public void setCryptoModelList(List<CryptoModel> cryptoModel) {
        this.cryptoModel = cryptoModel;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

}
