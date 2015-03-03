package co.com.codesoftware.general.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware {

    private String user;
    private String pass;
    public static final String CAJERO = "CAJERO";

    public String execute() {
        return "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public void setSession(Map<String, Object> map) {

    }

}
