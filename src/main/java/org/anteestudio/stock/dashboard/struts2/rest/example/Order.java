package org.anteestudio.stock.dashboard.struts2.rest.example;

public class Order
{
    private Integer id;
    private String name;
    private double price;

    public Order() {}

    public void setId(Integer id)
    {
        this.id = id;
        return;
    }
    public Integer getId() {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
        return;
    }
    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
        return;
    }
    public double getPrice() {
        return this.price;
    }
}
