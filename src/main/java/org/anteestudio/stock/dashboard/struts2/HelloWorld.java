package org.anteestudio.stock.dashboard.struts2;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

public class HelloWorld extends ActionSupport {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        return;
    }

    @Action(className="HelloWorldBean")
    public String execute() {
        //message = "Hello World! convention!";
        return SUCCESS;
    }
}

