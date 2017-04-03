package org.anteestudio.stock.dashboard.struts2;

import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.ServletActionContext;

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
        HttpServletResponse response = null;
        response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        return SUCCESS;
    }
}

