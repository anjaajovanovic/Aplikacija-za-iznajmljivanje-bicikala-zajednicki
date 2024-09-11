/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * Predstavlja bicikl koji se iznajmljuje.
 *
 * @author Anja Jovanovic
 */
public class Bicikl extends AbstractDomainObject {
    
	/**
	 * Id bicikla kao int.
	 */
    private int id;
    
    /**
     * Naziv bicikla kao String.
     */
    private String naziv;
    
    /**
     * Model bicikla kao String.
     */
    private String model;
    
    /**
     * Godina proizvodnje bicikla kao int.
     */
    private int godinaProizvodnje;
    
    /**
     * Cena iznajmljivanja bicikla po satu kao double.
     */
    private double cenaPoSatu;
    
    /**
     * Lokacija na kojoj se nalazi bicikl.
     */
    private Lokacija lokacija;
    
    /**
     * Servis koji je odradjen na bicikli.
     */
    private ServisBicikl servis;

    /**
     * Pravi nov objekat klase Bicikl.
     */
    public Bicikl() {
    }

    /**
     * Pravi novi bicikl i postavlja id, naziv, model, godinu proizvodnje, cenu po satu, lokaciju i servis na unete vrednosti.
     * 
     * @param id id bicikla kao String
     * @param naziv naziv bicikla kao String
     * @param model model bicikla kao String
     * @param godinaProizvodnje godina proizvodnje bicikla kao int
     * @param cenaPoSatu cena jednog sata iznajmljivanja bicikla kao double
     * @param lokacija lokacija bicikle kao objekat klase Lokacija
     * @param servis servis koji je odradjen na bicikli kao objekat klase ServisBicikl
     * 
     */
    public Bicikl(int id, String naziv, String model, int godinaProizvodnje, double cenaPoSatu, Lokacija lokacija, ServisBicikl servis) {
        this.id = id;
        this.naziv = naziv;
        this.model = model;
        this.godinaProizvodnje = godinaProizvodnje;
        this.cenaPoSatu = cenaPoSatu;
        this.lokacija = lokacija;
        this.servis = servis;
    }

    /**
     * Vraca id bicikla
     * 
     * @return id bicikla kao int
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja id bicikla na unetu vredost.
     * 
     * @param id id bicikla kao int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraca naziv bicikla kao String.
     * 
     * @return naziv bicikla kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv bicikla na unetu vrednost.
     * 
     * Uneti naziv ne sme biti null ili prazan String.
     * 
     * @param naziv naziv bicikla kao String
     * 
     * @throws java.lang.NullPointerException ako je uneti naziv null
     * 
     * @throws java.lang.IllegalArgumentException ako je uneti naziv prazan String
     */
    public void setNaziv(String naziv) {
    	if(naziv == null) 
    		throw new NullPointerException("Naziv ne sme biti null.");
    	
    	if(naziv.isEmpty())
    		throw new IllegalArgumentException("Naziv ne sme biti prazan.");
    	
        this.naziv = naziv;
    }

    /**
     * Vraca model bicikla kao String.
     * 
     * @return model bicikla kao String
     */
    public String getModel() {
        return model;
    }

    /**
     * Postavlja model bicikla na unetu vrednost.
     * 
     * Uneti model ne sme biti null ili prazan String.
     * 
     * @param model model bicikla kao String
     * 
     * @throws java.lang.NullPointerException ako je uneti model null
     * 
     * @throws java.lang.IllegalArgumentException ako je uneti model prazan String
     */
    public void setModel(String model) {
    	if(model == null) 
    		throw new NullPointerException("Model ne sme biti null.");
    	
    	if(model.isEmpty())
    		throw new IllegalArgumentException("Model ne sme biti prazan.");
    	
        this.model = model;
    }

    /**
     * Vraca godinu proizvodnje bicikla.
     * 
     * @return godina proizvodnje bicikla kao int
     */
    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    /**
     * Postavlja godinu proizvodnje bicikla na unetu vrednost.
     * 
     * @param godinaProizvodnje godina proizvodnje bicikla kao int
     */
    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    /**
     * Vraca cenu iznajmljivanja bicikla po satu kao double.
     * 
     * @return cena iznajmljivanja bicikla po satu
     */
    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    /**
     * Postavlja cenu jednog sata iznajmljivanja bicikla na unetu vrednost.
     * 
     * @param cenaPoSatu cena po satu kao double
     */
    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    /**
     * Vraca lokaciju na kojoj se nalazi bicikl kao objekat klase Lokacija.
     * 
     * @return lokacija lokacija bicikla kao objekat klase Lokacija
     */
    public Lokacija getLokacija() {
        return lokacija;
    }

    /**
     * Postavlja lokaciju bicikla na unetu vrednost.
     * 
     * Lokacija ne sme biti null.
     * 
     * @param lokacija lokacija bicikla kao objekat klase Lokacija
     * 
     * @throws java.jang.NullPointerException ako je uneta vrednost za lokaciju null
     */
    public void setLokacija(Lokacija lokacija) {
    	
    	if(lokacija == null)
    		throw new NullPointerException("Lokacija ne sme biti null.");
    	
        this.lokacija = lokacija;
    }

    /**
     * Vraca servis koji je odradjen na biciklu kao objekat klase ServisBicikl.
     * 
     * @return servis servis bicikle kao objekat klase ServisBicikl
     */
    public ServisBicikl getServis() {
        return servis;
    }

    /**
     * Postavlja servis bicikle na unetu vrednost.
     * 
     * @param servis servis bicikle kao objekat klase ServisBicikl
     */
    public void setServis(ServisBicikl servis) {    	
        this.servis = servis;
    }

    /**
     * Vraca tekstualni opis bicikla.
     * 
     * @return String koji predstavlja bicikl.
     */
    @Override
    public String toString() {
        return "Bicikl: " + naziv + " " + model + ", Godina proizvodnje: " + godinaProizvodnje + ", Cena po satu: " + cenaPoSatu + ", " + lokacija + ", " + servis;
    }
    
    /**
     * Vraca naziv tabele u bazi podataka za entitet Bicikl.
     * 
     * @return naziv tabele kao String.
     */
    @Override
    public String getTableName() {
        return "bicikl";
    }

    /**
     * Vraca alias koji se koristi za tabelu bicikla u SQL upitima.
     * 
     * @return alias za tabelu bicikl kao String.
     */
    @Override
    public String alijas() {
        return "b";
    }

    /**
     * Vraca SQL JOIN izraz koji se koristi za povezivanje tabele bicikla sa tabelama lokacija i servis.
     * 
     * @return SQL JOIN izraz kao String.
     */
    @Override
    public String join() {
        return "JOIN lokacija l ON (l.id = b.lokacija) "
                + "JOIN servisBicikl sb ON (sb.id = b.servis)";
    }

    /**
     * Kreira listu objekata klase Bicikl na osnovu podataka iz ResultSet-a.
     * 
     * @param rs ResultSet sa podacima iz baze.
     * 
     * @return lista objekata klase Bicikl.
     * 
     * @throws SQLException ako dođe do greske prilikom citanja podataka iz ResultSet-a.
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
            lista.add(b);
        }
        return lista;
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom INSERT operacije u bazu podataka.
     * 
     * @return nazivi kolona kao String, u ovom slucaju "naziv,model,godina_proizvodnje,cena_po_satu,lokacija,servis".
     */
    @Override
    public String getColumnsForInsert() {
        return "naziv,model,godina_proizvodnje,cena_po_satu,lokacija,servis";
    }

    /**
     * Vraca uslov koji definixe primarni kljuc za identifikaciju bicikla u bazi podataka.
     * 
     * @return uslov za primarni kljuc kao String.
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /**
     * Vraca vrednosti koje će biti ubacene u bazu podataka prilikom INSERT operacije.
     * 
     * @return vrednosti za insert kao String.
     */
    @Override
    public String getValuesForInsert() {
        return "'" + naziv + "', '" + model +"', " + godinaProizvodnje + ", " + cenaPoSatu + ", " + lokacija.getId() + ", " + servis.getId();
    }

    /**
     * Vraca vrednosti koje će biti azurirane u bazi podataka prilikom UPDATE operacije.
     * 
     * @return vrednosti za update kao String.
     */
    @Override
    public String getValuesForUpdate() {
        return " naziv = '" + naziv + "', model = '" + model +
                "', godina_proizvodnje = " + godinaProizvodnje + ", cena_po_satu = " + cenaPoSatu +
                ", lokacija = " + lokacija.getId() + ", servis = " + servis.getId();
    }

    /**
     * Vraca dodatne uslove koji se mogu koristiti u SQL upitima, trenutno vraca prazan string.
     * 
     * @return uslov kao String.
     */
    @Override
    public String condition() {
        return "";
    }
    
}
