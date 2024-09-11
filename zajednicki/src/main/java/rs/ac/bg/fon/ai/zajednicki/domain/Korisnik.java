/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja korisnika u sistemu.
 * Korisnik ima jedinstveni ID, ime, prezime i email adresu.
 * 
 * Koristi se za upravljanje podacima o korisnicima u bazi podataka.
 * Nasleđuje apstraktnu klasu AbstractDomainObject i implementira metode 
 * potrebne za rad sa bazom podataka.
 * 
 * @author Anja Jovanovic
 */
public class Korisnik extends AbstractDomainObject {
    
	/**
     * Jedinstveni identifikator korisnika kao int.
     */
    private int id;
    
    /**
     * Ime korisnika kao String.
     */
    private String ime;
    
    /**
     * Prezime korisnika kao String.
     */
    private String prezime;
    
    /**
     * Email adresa korisnika kao String.
     */
    private String email;

    /*
     * Pravi nov objekat klase Korisnik.
     */
    public Korisnik() {
    }

    /**
     * Pravi novog korisnika i postavlja id, ime, prezime i email na unete vrednosti.
     * 
     * @param id id korisnika kao int
     * @param ime Ime korisnika kao String
     * @param prezime Prezime korisnika kao String
     * @param email Email adresa korisnika kao String
     */
    public Korisnik(int id, String ime, String prezime, String email) {
        setId(id);
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
    }

    /**
     * Vraća jedinstveni identifikator korisnika.
     * 
     * @return Jedinstveni ID korisnika.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator korisnika.
     * 
     * @param id ID korisnika.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime korisnika.
     * 
     * @return Ime korisnika.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika. 
     * Ne sme biti null niti prazan string.
     * 
     * @param ime Ime korisnika.
     * @throws NullPointerException ako je ime null.
     * @throws IllegalArgumentException ako je ime prazan string.
     */
    public void setIme(String ime) {
    	if(ime == null) 
    		throw new NullPointerException("Ime ne sme biti null.");
    	
    	if(ime.isEmpty())
    		throw new IllegalArgumentException("Ime ne sme biti prazno.");
    	
        this.ime = ime;
    }

    /**
     * Vraća prezime korisnika.
     * 
     * @return Prezime korisnika.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime korisnika.
     * Ne sme biti null niti prazan string.
     * 
     * @param prezime Prezime korisnika.
     * @throws NullPointerException ako je prezime null.
     * @throws IllegalArgumentException ako je prezime prazan string.
     */
    public void setPrezime(String prezime) {
    	if(prezime == null) 
    		throw new NullPointerException("Prezime ne sme biti null.");
    	
    	if(prezime.isEmpty())
    		throw new IllegalArgumentException("Prezime ne sme biti prazno.");
    	
        this.prezime = prezime;
    }

    /**
     * Vraća email adresu korisnika.
     * 
     * @return Email adresa korisnika.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu korisnika.
     * Ne sme biti null niti prazan string.
     * 
     * @param email Email adresa korisnika.
     * @throws NullPointerException ako je email null.
     * @throws IllegalArgumentException ako je email prazan string.
     */
    public void setEmail(String email) {
    	if(email == null) 
    		throw new NullPointerException("Email ne sme biti null.");
    	
    	if(email.isEmpty())
    		throw new IllegalArgumentException("Email ne sme biti prazan.");
    	
        this.email = email;
    }

    /**
     * Vraća string reprezentaciju korisnika u formatu "Ime Prezime".
     * 
     * @return Ime i prezime korisnika.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     * 
     * @return naziv tabele kao string.
     */
    @Override
    public String getTableName() {
        return "korisnik";
    }

    /**
     * Vraća alias tabele koji se koristi prilikom SQL upita.
     * 
     * @return alias tabele kao string.
     */
    @Override
    public String alijas() {
        return "k";
    }

    /**
     * Ne koristi se spajanje sa drugim tabelama u SQL upitima za ovu klasu.
     * 
     * @return prazan string jer nema join upita.
     */
    @Override
    public String join() {
        return "";
    }

    /**
     * Kreira listu korisnika iz ResultSet-a nakon izvršenog SQL upita.
     * 
     * @param rs ResultSet koji sadrži rezultate SQL upita.
     * @return Lista objekata tipa Korisnik.
     * @throws SQLException ako dođe do greške pri čitanju iz ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()) {
            Korisnik k = new Korisnik(
                    rs.getInt("id"), 
                    rs.getString("ime"), 
                    rs.getString("prezime"),
                    rs.getString("email"));
            lista.add(k);
        }
        return lista;
    }

    /**
     * Vraća string koji predstavlja kolone za unos podataka u SQL upitu.
     * 
     * @return Kolone za unos podataka kao string.
     */
    @Override
    public String getColumnsForInsert() {
        return "ime,prezime,email";
    }

    /**
     * Vraća primarni ključ koji se koristi u SQL upitima.
     * 
     * @return Primarni ključ kao string.
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /**
     * Vraća vrednosti za unos u bazu podataka.
     * 
     * @return Vrednosti za unos kao string.
     */
    @Override
    public String getValuesForInsert() {
        return "'" + ime + "', '" + prezime +"', '" + email + "'";
    }

    /**
     * Vraća vrednosti za ažuriranje podataka u bazi podataka.
     * 
     * @return Vrednosti za ažuriranje kao string.
     */
    @Override
    public String getValuesForUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime +"', email = '" + email + "' ";
    }

    /**
     * Vraća uslov za SQL upit.
     * 
     * @return Prazan string jer nema specifičnih uslova.
     */
    @Override
    public String condition() {
        return "";
    }
    
    
}
