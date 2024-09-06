/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja stavku iznajmljivanja u sistemu za iznajmljivanje bicikala.
 * 
 * @author Anja Jovanovic
 */
public class StavkaIznajmljivanja extends AbstractDomainObject {
    
	/*
	 * Predstavlja iznajmljivanje kojem ova stavka pripada.
	 */
    private Iznajmljivanje iznajmljivanje;
    
    /*
     * Redni broj stavke u okviru iznajmljivanja.
     */
    private int stavkaRB;
    
    /*
     * Broj sati tokom kojih je bicikl bio iznajmljen.
     */
    private int brojSati;
    
    /*
     * Cena stavke iznajmljivanja.
     */
    private double cenaStavke;
    
    /*
     * Bicikl koji je iznajmljen u okviru stavke.
     */
    private Bicikl bicikl;

    /**
     * Prazan konstruktor.
     */
    public StavkaIznajmljivanja() {
    }

    /**
     * Konstruktor sa parametrima.
     * 
     * @param iznajmljivanje Iznajmljivanje povezano sa ovom stavkom.
     * @param stavkaRB Redni broj stavke.
     * @param brojSati Broj sati za koje je bicikl iznajmljen.
     * @param cenaStavke Cena stavke.
     * @param bicikl Bicikl koji je iznajmljen.
     */
    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, 
            int stavkaRB, int brojSati, double cenaStavke, Bicikl bicikl) {
        this.iznajmljivanje = iznajmljivanje;
        this.stavkaRB = stavkaRB;
        this.brojSati = brojSati;
        this.cenaStavke = cenaStavke;
        this.bicikl = bicikl;
    }

    /**
     * Vraća iznajmljivanje povezano sa ovom stavkom.
     * 
     * @return Iznajmljivanje.
     */
    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    /**
     * Postavlja iznajmljivanje povezano sa ovom stavkom.
     * 
     * @param iznajmljivanje Iznajmljivanje koje treba postaviti.
     * @throws NullPointerException Ako je iznajmljivanje null.
     */
    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
    	if(iznajmljivanje == null)
    		throw new NullPointerException("Iznajmljivanje ne sme biti null.");
    	
        this.iznajmljivanje = iznajmljivanje;
    }

    /**
     * Vraća redni broj stavke.
     * 
     * @return Redni broj stavke.
     */
    public int getStavkaRB() {
        return stavkaRB;
    }

    /**
     * Postavlja redni broj stavke.
     * 
     * @param stavkaRB Redni broj stavke koji treba postaviti.
     */

    public void setStavkaRB(int stavkaRB) {
        this.stavkaRB = stavkaRB;
    }

    /**
     * Vraća broj sati za koje je bicikl iznajmljen.
     * 
     * @return Broj sati.
     */
    public int getBrojSati() {
        return brojSati;
    }

    /**
     * Postavlja broj sati za koje je bicikl iznajmljen.
     * 
     * @param brojSati Broj sati koji treba postaviti.
     */
    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    /**
     * Vraća cenu stavke.
     * 
     * @return Cena stavke.
     */
    public double getCenaStavke() {
        return cenaStavke;
    }

    /**
     * Postavlja cenu stavke.
     * 
     * @param cenaStavke Cena stavke koju treba postaviti.
     */
    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    /**
     * Vraća bicikl koji je iznajmljen.
     * 
     * @return Bicikl.
     */
    public Bicikl getBicikl() {
        return bicikl;
    }

    /**
     * Postavlja bicikl koji je iznajmljen.
     * 
     * @param bicikl Bicikl koji treba postaviti.
     * @throws NullPointerException Ako je bicikl null.
     */
    public void setBicikl(Bicikl bicikl) {
    	if(bicikl == null) 
    		throw new NullPointerException("Bicikl ne sme biti null.");
        this.bicikl = bicikl;
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     * 
     * @return Naziv tabele.
     */
    @Override
    public String getTableName() {
        return "stavka_iznajmljivanja";
    }

    /**
     * Vraća alias za tabelu koji se koristi u SQL upitima.
     * 
     * @return Alias za tabelu.
     */
    @Override
    public String alijas() {
        return "si";
    }

    /**
     * Vraća SQL JOIN uslov za povezivanje tabela u SQL upitima.
     * 
     * @return SQL JOIN uslov.
     */
    @Override
    public String join() {
        return " JOIN IZNAJMLJIVANJE I ON (I.ID = SI.IZNAJMLJIVANJE) "
                + "JOIN BICIKL B ON (B.ID = SI.BICIKL) "
                + "JOIN ZAPOSLENI Z ON (Z.ID = I.ZAPOSLENI) "
                + "JOIN KORISNIK K ON (K.ID = I.KORISNIK)";
    }

    /**
     * Vraća listu objekata koji odgovaraju rezultatu SQL upita.
     * 
     * @param rs ResultSet iz SQL upita.
     * @return Lista objekata StavkaIznajmljivanja.
     * @throws SQLException Ako dođe do greške prilikom obrade ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject>  lista = new ArrayList<>();
        
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
           
           
           Lokacija l = new Lokacija(
                   rs.getInt("id"), 
                   rs.getString("naziv_lokacije"), 
                   rs.getString("adresa"), 
                   rs.getString("grad"));
           
           ServisBicikl sb = new ServisBicikl(
                   rs.getInt("id"), 
                   rs.getDate("datum_servisa"), 
                   rs.getString("tip_servisa"), 
                   rs.getInt("cena_servisa"));
           
           Bicikl b = new Bicikl(rs.getInt("id"), 
                   rs.getString("naziv"), 
                   rs.getString("model"), 
                   rs.getInt("godina_proizvodnje"), 
                   rs.getDouble("cena_po_satu"),
                   l,
                   sb);
           Iznajmljivanje iznajmljivanje = new Iznajmljivanje(
                   rs.getInt("id"), 
                   rs.getDate("datum"),
                   rs.getDouble("ukupna_cena"), 
                   z, 
                   k, 
                   null);
            
            StavkaIznajmljivanja si = new StavkaIznajmljivanja(
                    iznajmljivanje, 
                    rs.getInt("stavkaRB"), 
                    rs.getInt("broj_sati"), 
                    rs.getDouble("cena_stavke"),
                    b);
            
            lista.add(si);
        }
        
        rs.close();
        return lista;
    }

    /**
     * Vraća nazive kolona u tabeli za INSERT SQL upit.
     * 
     * @return Nazivi kolona za INSERT upit.
     */
    @Override
    public String getColumnsForInsert() {
        return " iznajmljivanje,stavkaRB,broj_sati,cena_stavke,bicikl ";
    }

    /**
     * Vraća uslov za pronalaženje primarnog ključa u SQL upitima.
     * 
     * @return Uslov za primarni ključ.
     */
    @Override
    public String getPrimaryKey() {
        return "iznajmljivanje = " + iznajmljivanje.getId();
    }

    /**
     * Vraća vrednosti za INSERT SQL upit.
     * 
     * @return Vrednosti za INSERT upit.
     */
    @Override
    public String getValuesForInsert() {
        return " " + iznajmljivanje.getId() + ", " + stavkaRB + ", " + brojSati + ", " + cenaStavke + ", " + bicikl.getId() + "";
    }

    /**
     * Vraća vrednosti za UPDATE SQL upit.
     * 
     * @return Vrednosti za UPDATE upit.
     */
    @Override
    public String getValuesForUpdate() {
        return "";
    }

    /**
     * Vraća SQL uslov za filtriranje rezultata.
     * 
     * @return SQL uslov za filtriranje rezultata.
     */
    @Override
    public String condition() {
        return "WHERE I.ID = " + iznajmljivanje.getId();
    }

    /**
     * Vraća String reprezentaciju objekta StavkaIznajmljivanja.
     * 
     * @return String reprezentacija objekta StavkaIznajmljivanja.
     */
    @Override
    public String toString() {
        return "Stavka iznajmljivanja: " + "iznajmljivanje: " + iznajmljivanje + ", redni broj: " + stavkaRB + ", broj sati: " + brojSati + ", cena stavke: " + cenaStavke + ", " + bicikl;
    }
 
}
