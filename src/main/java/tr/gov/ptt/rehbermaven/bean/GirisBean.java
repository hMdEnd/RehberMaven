
package tr.gov.ptt.rehbermaven.bean;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import tr.gov.ptt.rehbermaven.entity.Giris;
import tr.gov.ptt.rehbermaven.service.GirisService;
import tr.gov.ptt.rehbermaven.util.JSFUtil;

@ManagedBean
@RequestScoped
public class GirisBean {
    
    private Giris giris=new Giris();
    @EJB
    private GirisService girisService;
    
    public GirisBean() {
    }

    public Giris getGiris() {
        return giris;
    }

    public void setGiris(Giris giris) {
        this.giris = giris;
    }
    
    public String giriseYetkilimi(Giris p_giris)
    {
        
        boolean sonuc=girisService.giriseYetkilimi(giris);
        if (sonuc) {
            HttpSession session= JSFUtil.getSession();
            System.out.println(session.getId()+" nolu session başlıyorrrrrr");
            session.setAttribute("kullanici",giris.getKullanici());
            return "menü.xhtml?faces-redirect=true";
        } else {
            JSFUtil.hataGoster("Hatalı Giriş", "Kullanıcı Adı veya Şifre Yanlış");
            return "Giris.xhtml?faces-redirect=true";
        }
        
      
              
        
    }
    public String guvenliCikis()
    {
        HttpSession session=JSFUtil.getSession();
        JSFUtil.sessionBitir(session);
        JSFUtil.mesajGoster("Session Bitti", "Yeniden Giriş Yapınız");
        return "Giris.xhtml?faces-redirect=true";
    }
    
}
