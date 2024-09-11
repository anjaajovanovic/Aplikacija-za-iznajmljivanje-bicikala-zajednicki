/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Predstavlja iznajmljivanje u sistemu. 
 * Sadrži informacije o datumu iznajmljivanja, ukupnoj ceni, korisniku, zaposlenom, 
 * kao i listu stavki iznajmljivanja.
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractDomainObject} i koristi se 
 * za interakciju sa bazom podataka u vezi sa iznajmljivanjem.
 * 
 * @author Anja Jovanovic
 */
public class Iznajmljivanje extends AbstractDomainObject {
    
	/**
     * Jedinstveni identifikator iznajmljivanja.
     */
    private int id;
    
    /**
     * Datum kada je iznajmljivanje obavljeno.
     */
    private Date datum;
    
    /**
     * Ukupna cena iznajmljivanja.
     */
    private double ukupnaCena;
    
    /**
     * Zaposleni koji je obavio iznajmljivanje.
     */
    private Zaposleni zaposleni;
    
    /**
     * Korisnik koji je iznajmio predmet.
     */
    private Korisnik korisnik;
    
    /**
     * Lista stavki koje su iznajmljene.
     */
    private ArrayList<StavkaIznajmljivanja> stavkaIznajmljivanja;

    /**
     * Prazan konstruktor za kreiranje instance klase Iznajmljivanje bez inicijalizacije atributa.
     */
    public Iznajmljivanje() {
    }

    /**
     * Parametrizovani konstruktor koji inicijalizuje iznajmljivanje sa zadatim vrednostima.
     * 
     * @param id Jedinstveni identifikator iznajmljivanja.
     * @param datum Datum iznajmljivanja.
     * @param ukupnaCena Ukupna cena iznajmljivanja.
     * @param zaposleni Zaposleni koji je obavio iznajmljivanje.
     * @param korisnik Korisnik koji je izvršio iznajmljivanje.
     * @param stavkeIznajmljivanja Lista stavki koje su iznajmljene.
     */
    public Iznajmljivanje(int id, Date datum, double ukupnaCena, 
            Zaposleni zaposleni, Korisnik korisnik, 
            ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        setId(id);
        setDatum(datum);
        setUkupnaCena(ukupnaCena);
        setZaposleni(zaposleni);
        setKorisnik(korisnik);
        setStavkaIznajmljivanja(stavkeIznajmljivanja);
    }

    /**
     * Vraća jedinstveni identifikator iznajmljivanja.
     * 
     * @return Jedinstveni ID iznajmljivanja.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator iznajmljivanja.
     * 
     * @param id Jedinstveni ID iznajmljivanja.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća datum iznajmljivanja.
     * 
     * @return Datum iznajmljivanja.
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum iznajmljivanja.
     * Datum ne sme biti null.
     * 
     * @param datum Datum iznajmljivanja.
     * @throws NullPointerException ako je datum null.
     */
    public void setDatum(Date datum) {
    	if(datum == null) 
    		throw new NullPointerException("Datum ne sme biti null.");
    	
        this.datum = datum;
    }

    /**
     * Vraća ukupnu cenu iznajmljivanja.
     * 
     * @return Ukupna cena iznajmljivanja.
     */
    public double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Postavlja ukupnu cenu iznajmljivanja.
     * 
     * Ukupna cena ne sme biti negativna.
     * 
     * @param ukupnaCena Ukupna cena iznajmljivanja.
     * 
     * @throws java.lang.IllegalArgumentException ako je ukupna cena negativna
     */
    public void setUkupnaCena(double ukupnaCena) {
    	if(ukupnaCena < 0)
    		throw new IllegalArgumentException("Ukupna cena ne sme biti negativna.");
    	
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraća zaposlenog koji je obavio iznajmljivanje.
     * 
     * @return Zaposleni.
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Postavlja zaposlenog koji je obavio iznajmljivanje.
     * Zaposleni ne sme biti null.
     * 
     * @param zaposleni Zaposleni koji je obavio iznajmljivanje.
     * @throws NullPointerException ako je zaposleni null.
     */
    public void setZaposleni(Zaposleni zaposleni) {
    	if(zaposleni == null) 
    		throw new NullPointerException("Zaposleni ne sme biti null.");
    	
        this.zaposleni = zaposleni;
    }

    /**
     * Vraća korisnika koji je iznajmio predmet.
     * 
     * @return Korisnik.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja korisnika koji je iznajmio predmet.
     * Korisnik ne sme biti null.
     * 
     * @param korisnik Korisnik.
     * @throws NullPointerException ako je korisnik null.
     */
    public void setKorisnik(Korisnik korisnik) {
    	if(korisnik == null) 
    		throw new NullPointerException("Korisnik ne sme biti null.");
    	
        this.korisnik = korisnik;
    }

    /**
     * Vraća listu stavki iznajmljivanja.
     * 
     * @return Lista stavki iznajmljivanja.
     */
    public ArrayList<StavkaIznajmljivanja> getStavkaIznajmljivanja() {
        return stavkaIznajmljivanja;
    }

    /**
     * Postavlja listu stavki iznajmljivanja.
     * 
     * @param stavke Lista stavki iznajmljivanja.
     */
    public void setStavkaIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavke) {
        this.stavkaIznajmljivanja = stavke;
    }
    
    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     * 
     * @return naziv tabele kao string.
     */
    @Override
    public String getTableName() {
        return "iznajmljivanje";
    }

    /**
     * Vraća alias tabele koji se koristi prilikom SQL upita.
     * 
     * @return alias tabele kao string.
     */
    @Override
    public String alijas() {
        return "i";
    }

    /**
     * Vraća SQL JOIN naredbu za povezivanje sa drugim tabelama.
     * 
     * @return SQL JOIN naredba.
     */
    @Override
    public String join() {
        return "JOIN korisnik k ON (k.id = i.korisnik) "
                + "JOIN zaposleni z ON (z.id = i.zaposleni)";
    }

    /**
     * Kreira listu iznajmljivanja iz ResultSet-a nakon izvršenog SQL upita.
     * 
     * @param rs ResultSet koji sadrži rezultate SQL upita.
     * @return Lista objekata tipa Iznajmljivanje.
     * @throws SQLException ako dođe do greške pri čitanju iz ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista= new ArrayList<>();
       
        while(rs.next()){
            Zaposleni z = new Zaposleni(
                    rs.getInt("id"), 
                    rs.getString("ime"), 
                    rs.getString("prezime"), 
                    rs.getString("username"),
                    rs.getString("password"));
            Korisnik k = new Korisnik(
                    rs.getInt("id"),
                    rs.getString("ime"), 
                    rs.getString("prezime"),
                    rs.getString("email"));
           
            Iznajmljivanje i = new Iznajmljivanje(
                    rs.getInt("id"), 
                    rs.getDate("datum"), 
                    rs.getDouble("ukupna_cena"), z, k, null);
            
            lista.add(i);
        }
        
        rs.close();
        return lista;
    }

    /**
     * Vraća naziv kolona koje će biti korišćene u SQL upitu za unos podataka u bazu.
     * Kolone koje se koriste za unos podataka su: datum, ukupna cena, zaposleni i korisnik.
     * 
     * @return Naziv kolona za unos podataka kao string.
     */
    @Override
    public String getColumnsForInsert() {
        return "datum,ukupna_cena,zaposleni,korisnik";
    }

    /**
     * Vraća uslov za primarni ključ koji se koristi u SQL upitima za identifikaciju specifičnog zapisa.
     * Primarni ključ za ovu klasu je polje id.
     * 
     * @return Uslov za primarni ključ kao string.
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /**
     * Vraća vrednosti koje će biti korišćene u SQL upitu za unos podataka u bazu.
     * Vrednosti su datum iznajmljivanja, ukupna cena, ID zaposlenog i ID korisnika.
     * Datum se konvertuje u SQL format.
     * 
     * @return Vrednosti za unos u bazu kao string.
     */
    @Override
    public String getValuesForInsert() {
        return "'" + new java.sql.Date(datum.getTime()) + "', "
                + ukupnaCena + ", " + zaposleni.getId() + ", "
                + korisnik.getId();
    }

    /**
     * Vraća vrednosti koje će biti korišćene u SQL upitu za ažuriranje podataka u bazi.
     * Ažuriraju se polja datum i ukupna cena.
     * Datum se konvertuje u SQL format.
     * 
     * @return Vrednosti za ažuriranje u bazi kao string.
     */
    @Override
    public String getValuesForUpdate() {
        return "datum = '" + new java.sql.Date(datum.getTime()) + "', ukupna_cena = " + ukupnaCena;
    }

    /**
     * Vraća uslov koji se koristi u SQL upitima za filtriranje zapisa. 
     * U ovoj klasi, uslov je prazan, što znači da se ne primenjuju dodatni uslovi.
     * 
     * @return Prazan string jer nema specifičnih uslova.
     */
    @Override
    public String condition() {
        return "";
    }
    
}
