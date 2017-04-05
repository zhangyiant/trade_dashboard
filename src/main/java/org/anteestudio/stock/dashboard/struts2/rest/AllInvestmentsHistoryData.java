package org.anteestudio.stock.dashboard.struts2.rest;


public class AllInvestmentsHistoryData {
    private long id;
    private long epochMilli;
    private float totalValue;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setEpochMilli(long epochMilli) {
        this.epochMilli = epochMilli;
        return;
    }
    public long getEpochMilli() {
        return this.epochMilli;
    }
    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
        return;
    }
    public float getTotalValue() {
        return this.totalValue;
    }
}
