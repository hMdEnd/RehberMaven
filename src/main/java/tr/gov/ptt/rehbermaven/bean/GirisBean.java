
package tr.gov.ptt.rehbermaven.bean;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import tr.gov.ptt.rehbermaven.entity.Giris;
import tr.gov.ptt.rehbermaven.entity.Log;
import tr.gov.ptt.rehbermaven.facade.LogFacade;
import tr.gov.ptt.rehbermaven.service.GirisService;
import tr.gov.ptt.rehbermaven.util.JSFUtil;

@ManagedBean
@RequestScoped
public class GirisBean {
    
    private java.util.Date tarihSaat=new java.util.Date();
    private Giris giris=new Giris();
    @EJB
    private GirisService girisService;
    @EJB
    private LogFacade logFacade;
    
    public GirisBean() {
        
        giris.setKullanici("hamdi");
        giris.setSifre("1234");
    }

    public Date getTarihsaat() {
        return tarihSaat;
    }

    public void setTarihsaat(Date tarihsaat) {
        this.tarihSaat = tarihsaat;
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
            
            Log log=new Log();
            log.setKullanici(giris.getKullanici());
            log.setTarihsaat(new java.util.Date());
            log.setIslem("Giriş");
            logFacade.create(log);
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
    public void sistemTarihSaatGüncelle()
    {
        tarihSaat=new java.util.Date();
        
   
    }
    public String sistemTarihSaatGetir()
    {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
        return sdf.format(tarihSaat);
    }
    
}
