package com.huanaco.crm.VO;

import java.util.List;

public class CountAndActivityVO<T>{
    private int total;
    private List<T> aList;

    public CountAndActivityVO(int total, List<T> aList) {
        this.total = total;
        this.aList = aList;
    }

    public CountAndActivityVO() {
    }

    @Override
    public String toString() {
        return "CountAndActivityVO{" +
                "total=" + total +
                ", aList=" + aList +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getaList() {
        return aList;
    }

    public void setaList(List<T> aList) {
        this.aList = aList;
    }
}
