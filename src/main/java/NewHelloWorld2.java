import com.opensymphony.xwork2.ActionSupport;

public class NewHelloWorld2 extends ActionSupport {

    public String execute() throws Exception {
        return SUCCESS;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
