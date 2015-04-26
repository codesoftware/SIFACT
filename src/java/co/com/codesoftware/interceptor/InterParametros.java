/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.interceptor;

import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.utilidades.ParametrosActivos;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author nicolas
 */
public class InterParametros implements Interceptor{

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        Parametro parametro = (Parametro) session.get("parametros");
        if(parametro == null){
            return Action.LOGIN;
        }else{
            Action action = (Action) actionInvocation.getAction();
            if (action instanceof ParametrosActivos) {
                ((ParametrosActivos) action).setParametro(parametro);
            }
            return actionInvocation.invoke();
        }
     }
    
}
