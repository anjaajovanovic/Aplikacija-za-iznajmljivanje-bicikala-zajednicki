/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja statistiku ocene za bicikl koju je dao korisnik.
 * 
 * @author Anja Jovanovic
 */
public class Statistika extends AbstractDomainObject {
    
	/**
	 * Jedinstveni identifikator statistike.
	 */
    private int id;
    
    /**
     * Ocena koju je korisnik dao biciklu na osnovu iskustva.
     */
    private int ocena;
    
    /**
     * Korisnik koji je dao ocenu.
     */
    private Korisnik korisnik;
    
    /**
     * Bicikl na koji se odnosi ocena.
     */
    private Bicikl bicikl;

    /**
     * Prazan konstruktor za inicijalizaciju objekta bez postavljanja vrednosti.
     */
    public Statistika() {
    }

    /**
     * Konstruktor za inicijalizaciju objekta sa datim vrednostima.
     * 
     * @param id Identifikator statistike.
     * @param ocena Ocena koju je korisnik dao biciklu.
     * @param korisnik Korisnik koji je dao ocenu.
     * @param bicikl Bicikl koji je ocenjen.
     */
    public Statistika(int id, int ocena, Korisnik korisnik, Bicikl bicikl) {
        this.id = id;
        this.ocena = ocena;
        this.korisnik = korisnik;
        this.bicikl = bicikl;
    }

    /**
     * Vraća identifikator statistike.
     * 
     * @return Identifikator statistike.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja identifikator statistike.
     * 
     * @param id Identifikator statistike.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ocenu koju je korisnik dao biciklu.
     * 
     * @return Ocena bicikla.
     */
    public int getOcena() {
        return ocena;
    }

    /**
     * Postavlja ocenu koju je korisnik dao biciklu.
     * 
     * @param ocena Ocena bicikla.
     */
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    /**
     * Vraća korisnika koji je dao ocenu.
     * 
     * @return Korisnik koji je dao ocenu.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja korisnika koji je dao ocenu.
     * 
     * @param korisnik Korisnik koji je dao ocenu.
     * @throws NullPointerException Ako je korisnik null.
     */
    public void setKorisnik(Korisnik korisnik) {
    	if(korisnik == null) 
    		throw new NullPointerException("Korisnik ne sme biti null.");
    	
        this.korisnik = korisnik;
    }

    /**
     * Vraća bicikl koji je ocenjen.
     * 
     * @return Bicikl koji je ocenjen.
     */
    public Bicikl getBicikl() {
        return bicikl;
    }

    /**
     * Postavlja bicikl koji je ocenjen.
     * 
     * @param bicikl Bicikl koji je ocenjen.
     * @throws NullPointerException Ako je bicikl null.
     */
    public void setBicikl(Bicikl bicikl) {
    	if(bicikl == null)
    		throw new NullPointerException("Bicikl ne sme biti null.");
    	
        this.bicikl = bicikl;
    }

    /**
     * Vraća String reprezentaciju objekta Statistika.
     * 
     * @return String reprezentacija objekta Statistika.
     */
    @Override
    public String toString() {
        return "Statistika: ocena: " + ocena + ", " + korisnik + ", " + bicikl;
    }
    
    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj klasi.
     * 
     * @return Naziv tabele u bazi podataka.
     */
    @Override
    public String getTableName() {
        return "statistika";
    }

    /**
     * Vraća alias za tabelu u SQL upitima.
     * 
     * @return Alias za tabelu.
     */
    @Override
    public String alijas() {
        return "s";
    }

    /**
     * Vraća SQL JOIN klauzulu za povezivanje tabela u SQL upitima.
     * 
     * @return SQL JOIN klauzula.
     */
    @Override
    public String join() {
        return " JOIN KORISNIK K ON (K.ID = S.KORISNIK) "
                + "JOIN BICIKL B ON (B.ID = S.BICIKL) ";
    }

    /**
     * Vraća listu objekata Statistika na osnovu rezultata upita.
     * 
     * @param rs ResultSet koji sadrži rezultate SQL upita.
     * @return Lista objekata Statistika.
     * @throws SQLException Ako dođe do greške prilikom čitanja iz ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista= new ArrayList<>();
       
        while (rs.next()) {
        	
        	Lokacija l = new Lokacija(
        			rs.getInt("id"), 
                    rs.getString("naziv_lokacije"), 
                    rs.getString("adresa"), 
                    rs.getString("grad")
                );
        	
        	ServisBicikl sb = new ServisBicikl(
                    rs.getInt("id"), 
                    rs.getDate("datum_servisa"), 
                    rs.getString("tip_servisa"), 
                    rs.getInt("cena_servisa")
                );

            Korisnik k = new Korisnik(
                rs.getInt("id"), 
                rs.getString("ime"), 
                rs.getString("prezime"), 
                rs.getString("email")
            );

            Bicikl b = new Bicikl(
                rs.getInt("id"), 
                rs.getString("naziv"), 
                rs.getString("model"),
                rs.getInt("godina_proizvodnje"), 
                rs.getDouble("cena_po_satu"),l,sb
            );

            Statistika s = new Statistika(id, ocena, k, b);

            lista.add(s);
        }
        rs.close();
        return lista;
    }

    /**
     * Vraća naziv kolona u tabeli koje su potrebne za INSERT SQL upit.
     * 
     * @return Nazivi kolona za INSERT upit.
     */
    @Override
    public String getColumnsForInsert() {
        return "ocena,korisnik,bicikl";
    }

    /**
     * Vraća SQL klauzulu za primarni ključ za ovu instancu objekta.
     * 
     * @return SQL klauzula za primarni ključ.
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /**
     * Vraća SQL vrednosti za INSERT upit u tabelu.
     * 
     * @return SQL vrednosti za INSERT upit.
     */
    @Override
    public String getValuesForInsert() {
        return ocena + "," + korisnik.getId() + "," + bicikl.getId();
    }

    /**
     * Vraća SQL klauzulu za ažuriranje vrednosti u tabeli.
     * 
     * @return SQL klauzula za ažuriranje vrednosti.
     */
    @Override
    public String getValuesForUpdate() {
        return "ocena = " + ocena + ", korisnik = " + korisnik.getId() + ", bicikl = " + bicikl.getId();
    }

    /**
     * Vraća uslov za SQL upit.
     * 
     * @return Uslov za SQL upit.
     */
    @Override
    public String condition() {
        return "";
    }
    
}
