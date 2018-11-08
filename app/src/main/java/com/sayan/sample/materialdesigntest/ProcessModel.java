package com.sayan.sample.materialdesigntest;

public class ProcessModel {
    private String packageName;
    private int pId;

    public ProcessModel(String packageName, int pId) {
        this.packageName = packageName;
        this.pId = pId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
}
