
package tr.gov.ptt.rehbermaven.bean;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import tr.gov.ptt.rehbermaven.entity.Giris;
import tr.gov.ptt.rehbermaven.service.GirisService;

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
            return "menu.xhtml";
        } else {
            return "giris.xhtml";
        }
    }
    
}
