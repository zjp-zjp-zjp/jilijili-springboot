package com.example.jilijili.test;

public class Test {
    String id;
    String name;
    int dianZan;
    int pingLun;
    String content;
    public Test() {
    }

    public Test(String id, String name, int dianZan, int pingLun, String content) {
        this.id = id;
        this.name = name;
        this.dianZan = dianZan;
        this.pingLun = pingLun;
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDianZan(int dianZan) {
        this.dianZan = dianZan;
    }

    public void setPingLun(int pingLun) {
        this.pingLun = pingLun;
    }

    public void setContent(String  content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDianZan() {
        return dianZan;
    }

    public int getPingLun() {
        return pingLun;
    }

    public String  getContent() {
        return content;
    }
}
