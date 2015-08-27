
package tr.gov.ptt.rehbermaven.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



public class JSFUtil {
    
    public static HttpSession getSession()
    {
        
        HttpSession session= (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        return session;
    }
    public static void sessionBitir(HttpSession p_session)
    {
        System.out.println(p_session.getId()+ " nolu session bitiriliyor bitiriliyor bitiriliyor");
        p_session.invalidate();
    }
    
    public static void mesajGoster(String p_mesaj,String p_detay)
    {
        FacesContext.getCurrentInstance().addMessage("null",new FacesMessage(FacesMessage.SEVERITY_INFO, p_mesaj, p_detay));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    public static void hataGoster(String p_mesaj,String p_detay)
    {
        FacesContext.getCurrentInstance().addMessage("null",new FacesMessage(FacesMessage.SEVERITY_ERROR, p_mesaj, p_detay));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
    
}
