package org.anteestudio.stock.dashboard.struts2.rest.example;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;

public class OrdersController {
    private Order model;
    private String id;
    private String name;
    private int[] a;
    
    public String getName() {
        return "HelloWorld";
    }
    public void setName(String name) {
        return;
    }
    public void setId(String id) {
        this.id = id;
        return;
    }
    public String getId() {
        return this.id;
    }
    public Order getOrder() {
        return this.model;
    }
    public void setOrder(Order order) {
        this.model = order;
        return;
    }
    public int[] getQQ() {
        int[] a;
        a = new int[10];
        a[0] = 1;
        a[1] = 2;
        return a;
    }

    @Action(className="orderscontroller")
    public String show() {
        this.id = "ABCDEF";
        this.name = "QQ";
        this.a = getQQ();
        return "show";
    }

    public Order getModel() {
        return this.model;
    }
}
