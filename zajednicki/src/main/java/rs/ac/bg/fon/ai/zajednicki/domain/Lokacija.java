/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 * Predstvalja lokaciju na kojoj se nalazi bicikl.
 *
 * @author Anja Jovanovic
 */
public class Lokacija extends AbstractDomainObject {
    
	/*
	 * Id lokacije kao int.
	 */
    private int id;
    
    /*
     * Naziv lokacije kao String.
     */
    private String naziv;
    
    /*
     * Adresa lokacije kao String.
     */
    private String adresa;
    
    /*
     * Grad lokacije kao String.
     */
    private String grad;

    /*
     * Pravi nov objekat klase Lokacija.
     */
    public Lokacija() {
    }

    /*
     * Pravi novu lokaciju i postavlja id, naziv, adresu i grad na unete vrednosti.
     * 
     * @param id id lokacije kao int
     * @param naziv naziv lokacije kao String
     * @param adresa lokacije kao String
     * @param grad lokacije kao String
     */
    public Lokacija(int id, String naziv, String adresa, String grad) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
    }

    /*
     * Vraca id lokacije.
     * 
     * @return id lokacije kao int
     */
    public int getId() {
        return id;
    }

    /*
     * Postavlja id lokacije na unetu vrednost.
     * 
     * @param id id lokacije kao int
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * Vraca naziv lokacije.
     * 
     * @return naziv lokacije kao String
     */
    public String getNaziv() {
        return naziv;
    }

    /*
     * Postavlja naziv lokacije na unetu vrednost.
     * 
     * Uneti naziv ne sme biti null ili prazan String.
     * 
     * @param naziv naziv lokacije kao String
     * 
     * @throws java.lang.NullPointerException ako je uneti naziv null
     * @throws java.lang.IllegalArgumentException ako je uneti naziv prazan String
     */
    public void setNaziv(String naziv) {
    	if(naziv == null)
    		throw new NullPointerException("Naziv lokacije ne sme biti null.");
    	
    	if(naziv.isEmpty())
    		throw new IllegalArgumentException("Naziv lokacije ne sme biti prazan.");
    	
        this.naziv = naziv;
    }

    /*
     * Vraca adresu lokacije.
     * 
     * @return adresa lokacije kao String
     */
    public String getAdresa() {
        return adresa;
    }

    /*
     * Postavlja adresu lokacije na unetu vrednost.
     * 
     * Uneta adresa ne sme biti null ili prazan String.
     * 
     * @param adresa adresa lokacije kao String
     * 
     * @throws java.lang.NullPointerException ako je uneta adresa null
     * @throws java.lang.IllegalArgumentException ako je uneta adresa prazan String
     */
    public void setAdresa(String adresa) {
    	if(adresa == null)
    		throw new NullPointerException("Adresa lokacije ne sme biti null.");
    	
    	if(adresa.isEmpty())
    		throw new IllegalArgumentException("Adresa lokacije ne sme biti prazna.");
    	
        this.adresa = adresa;
    }

    /*
     * Vraca grad u kojem se lokacija nalazi.
     * 
     * @return grad lokacije kao String
     */
    public String getGrad() {
        return grad;
    }

    /*
     * Postavlja grad lokacije na unetu vrednost.
     * 
     * Uneti grad ne sme biti null ili prazan String.
     * 
     * @param grad grad lokacije kao String
     * 
     * @throws java.lang.NullPointerException ako je uneti grad null
     * @throws java.lang.IllegalArgumentException ako je uneti grad prazan String
     */
    public void setGrad(String grad) {
    	if(grad == null)
    		throw new NullPointerException("Grad ne sme biti null.");
    	
    	if(grad.isEmpty())
    		throw new IllegalArgumentException("Grad ne sme biti prazan.");
    	
        this.grad = grad;
    }

    /*
     * Vraca tekstualni opis lokacije.
     * 
     * @return String koji predstavlja lokaciju u formatu "Lokacija: naziv, Adresa: adresa, Grad: grad"
     */
    @Override
    public String toString() {
        return "Lokacija: " + naziv + ", Adresa: " + adresa + ", Grad: " + grad;
    }

    /*
     * Vraca ime tabele u bazi podataka.
     * 
     * @return ime tabele kao String
     */
    @Override
    public String getTableName() {
        return "lokacija";
    }

    /*
     * Vraca alias tabele za upotrebu u SQL upitima.
     * 
     * @return alias tabele kao String
     */
    @Override
    public String alijas() {
        return "l";
    }

    /*
     * Vraca deo SQL upita za spajanje tabela. 
     * U ovom slučaju, metoda ne vraca ništa jer nema join operacija za lokaciju.
     * 
     * @return prazan String
     */
    @Override
    public String join() {
        return "";
    }

    /*
     * Kreira listu objekata tipa AbstractDomainObject na osnovu podataka iz ResultSet-a.
     * 
     * @param rs ResultSet sa podacima
     * @return lista objekata AbstractDomainObject
     * @throws SQLException ako se dogodi SQL greška
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()) {
            Lokacija l = new Lokacija(
                    rs.getInt("id"), 
                    rs.getString("naziv_lokacije"), 
                    rs.getString("adresa"), 
                    rs.getString("grad"));
            
            lista.add(l);
        }
        return lista;
    }

    /*
     * Vraca nazive kolona za SQL upit INSERT.
     * 
     * @return nazivi kolona kao String
     */ 
    @Override
    public String getColumnsForInsert() {
        return "naziv_lokacije,adresa,grad";
    }

    /*
     * Vraca uslov za primarni ključ u SQL upitima.
     * 
     * @return uslov za primarni ključ kao String
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /*
     * Vraca vrednosti koje će se uneti u SQL upit INSERT.
     * 
     * @return vrednosti za INSERT kao String
     */
    @Override
    public String getValuesForInsert() {
        return "'" + naziv + "', '" + adresa + "', '" + grad + "'";
    }

    /*
     * Vraca vrednosti koje će se koristiti za SQL upit UPDATE.
     * 
     * @return vrednosti za UPDATE kao String
     */
    @Override
    public String getValuesForUpdate() {
        return " naziv_lokacije = '" + naziv + "', adresa = '" + adresa + "', grad = '" + grad + "'";
    }

    /*
     * Vraca uslov za WHERE klauzulu SQL upita.
     * 
     * U ovom slučaju, ne postoji specifičan uslov, pa metoda vraća prazan String.
     * 
     * @return prazan String
     */
    @Override
    public String condition() {
        return "";
    }

    /*
     * Generiše hash kod na osnovu id lokacije.
     * 
     * @return hash kod kao int
     */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/*
	 * Poredi dva objekta klase Lokacija na osnovu id-a.
	 * 
	 * @param obj objekat koji se poredi
	 * @return true ako su objekti isti, false ako nisu
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lokacija other = (Lokacija) obj;
		return id == other.id;
	}
    
}
