/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Predstavlja servis bicikla, uključujući podatke o datumu servisa, tipu servisa i ceni servisa.
 * Nasleđuje `AbstractDomainObject` klasu i implementira metode za rad sa bazom podataka.
 * 
 * @author Anja Jovanovic
 */
public class ServisBicikl extends AbstractDomainObject {
    
	/*
	 * Jedinstveni identifikator servisa.
	 */
    private int id;
    
    /*
     * Datum obavljanja servisa.
     */
    private Date datumServisa;
    
    /*
     * Tip servisa kao String.
     */
    private String tipServisa;
    
    /*
     * Cena servisa kao int.
     */
    private int cenaServisa;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat sa default vrednostima.
     */
    public ServisBicikl() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat sa zadatim vrednostima.
     * 
     * @param id Identifikator servisa bicikla.
     * @param datumServisa Datum kada je servis obavljen.
     * @param tipServisa Tip servisa koji je obavljen.
     * @param cenaServisa Cena servisa u dinarima.
     */
    public ServisBicikl(int id, Date datumServisa, String tipServisa, int cenaServisa) {
        this.id = id;
        this.datumServisa = datumServisa;
        this.tipServisa = tipServisa;
        this.cenaServisa = cenaServisa;
    }

    /**
     * Vraća identifikator servisa bicikla.
     * 
     * @return Identifikator servisa bicikla.
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća datum kada je servis obavljen.
     * 
     * @return Datum servisa.
     */
    public Date getDatumServisa() {
        return datumServisa;
    }

    /**
     * Vraća tip servisa koji je obavljen.
     * 
     * @return Tip servisa.
     */
    public String getTipServisa() {
        return tipServisa;
    }

    /**
     * Vraća cenu servisa u dinarima.
     * 
     * @return Cena servisa.
     */
    public int getCenaServisa() {
        return cenaServisa;
    }

    /**
     * Postavlja identifikator servisa bicikla.
     * 
     * @param id Identifikator servisa bicikla.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Postavlja datum kada je servis obavljen.
     * 
     * @param datumServisa Datum servisa.
     * @throws NullPointerException Ako je datum servisa null.
     */
    public void setDatumServisa(Date datumServisa) {
    	if(datumServisa == null) 
    		throw new NullPointerException("Datum servisa ne sme biti null.");
        this.datumServisa = datumServisa;
    }

    /**
     * Postavlja tip servisa koji je obavljen.
     * 
     * @param tipServisa Tip servisa.
     * @throws NullPointerException Ako je tip servisa null.
     * @throws IllegalArgumentException Ako je tip servisa prazan.
     */
    public void setTipServisa(String tipServisa) {
    	if(tipServisa == null) 
    		throw new NullPointerException("Tip servisa ne sme biti null.");
    	
    	if(tipServisa.isEmpty()) 
    		throw new IllegalArgumentException("Tip servisa ne sme biti prazan.");
    	
        this.tipServisa = tipServisa;
    }

    /**
     * Postavlja cenu servisa u dinarima.
     * 
     * @param cenaServisa Cena servisa.
     */
    public void setCenaServisa(int cenaServisa) {
        this.cenaServisa = cenaServisa;
    }

    /**
     * Vraća string reprezentaciju objekta koja uključuje datum servisa, tip servisa i cenu servisa.
     * 
     * @return String reprezentacija objekta.
     */
    @Override
    public String toString() {
        return "Servis bicikla: Datum: " + datumServisa + ", Tip: " + tipServisa + ", Cena: " + cenaServisa;
    }
    
    /**
     * Vraća naziv tabele u bazi podataka u kojoj su smešteni podaci o servisu bicikla.
     * 
     * @return Naziv tabele u bazi podataka.
     */
    @Override
    public String getTableName() {
        return "servisBicikl";
    }

    /**
     * Vraća alias koji se koristi za identifikaciju ove klase u SQL upitima.
     * 
     * @return Alias za ovu klasu.
     */
    @Override
    public String alijas() {
        return "sb";
    }

    /**
     * Vraća SQL JOIN uslove za povezivanje ove tabele sa drugim tabelama.
     * 
     * @return JOIN uslov kao string.
     */
    @Override
    public String join() {
        return "";
    }

    /**
     * Kreira listu objekata klase `ServisBicikl` iz rezultata SQL upita.
     * 
     * @param rs Rezultati SQL upita.
     * @return Lista objekata klase `ServisBicikl`.
     * @throws SQLException Ako dođe do greške pri radu sa SQL rezultatima.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()) {
            ServisBicikl sb = new ServisBicikl(
                    rs.getInt("id"), 
                    rs.getDate("datumServisa"), 
                    rs.getString("tipServisa"), 
                    rs.getInt("cenaServisa"));
            
            lista.add(sb);
        }
        return lista;
    }

    /**
     * Vraća nazive kolona koje će biti korišćene u SQL upitu za unos podataka u bazu.
     * Kolone koje se koriste za unos su: datum servisa, tip servisa i cena servisa.
     * 
     * @return Naziv kolona za unos podataka kao string.
     */
    @Override
    public String getColumnsForInsert() {
        return "datum_servisa,tip_servisa,cena_servisa";
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
     * Vrednosti su datum servisa, tip servisa i cena servisa.
     * Datum se konvertuje u SQL format.
     * 
     * @return Vrednosti za unos u bazu kao string.
     */
    @Override
    public String getValuesForInsert() {
        return "'" + new java.sql.Date(datumServisa.getTime()) + "', '" + tipServisa +"', " + cenaServisa;
    }

    /**
     * Vraća vrednosti koje će biti korišćene u SQL upitu za ažuriranje podataka u bazi.
     * Ažuriraju se polja datum servisa, tip servisa i cena servisa.
     * Datum se konvertuje u SQL format.
     * 
     * @return Vrednosti za ažuriranje u bazi kao string.
     */
    @Override
    public String getValuesForUpdate() {
        return " datum_servisa = '" + new java.sql.Date(datumServisa.getTime()) + "', tip_servisa = '" + 
                tipServisa + "', cena_servisa = " + cenaServisa;
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
