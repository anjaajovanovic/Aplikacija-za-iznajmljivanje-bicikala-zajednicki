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
 * @author PC
 */
public class Zaposleni extends AbstractDomainObject {
    
	/**
	 * Jedinstveni identifikator zaposlenog.
	 */
    private int id;
    
    /**
     * Ime zaposlenog.
     */
    private String ime;
    
    /**
     * Prezime zaposlenog.
     */
    private String prezime;
    
    /**
     * Korisnicko ime zaposlenog.
     */
    private String username;
    
    /**
     * Lozinka zaposlenog.
     */
    private String password;

    /**
     * Ovaj konstruktor inicijalizuje novi objekat Zaposleni.
     */
    public Zaposleni() {
    }

    /**
     * Ovaj konstruktor inicijalizuje novi objekat Zaposleni sa zadatim vrednostima za atribute.
     * 
     * @param id Jedinstveni identifikator zaposlenog.
     * @param ime Ime zaposlenog.
     * @param prezime Prezime zaposlenog.
     * @param username Korisničko ime zaposlenog.
     * @param password Lozinka zaposlenog.
     */
    public Zaposleni(int id, String ime, String prezime, String username, String password) {
        setId(id);
        setIme(ime);
        setPassword(password);
        setPrezime(prezime);
        setUsername(username);
    }

    /**
     * Vraća jedinstveni identifikator zaposlenog.
     * 
     * @return Jedinstveni identifikator zaposlenog.
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća ime zaposlenog.
     * 
     * @return Ime zaposlenog.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Vraća prezime zaposlenog.
     * 
     * @return Prezime zaposlenog.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Vraća korisničko ime zaposlenog.
     * 
     * @return Korisničko ime zaposlenog.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Vraća lozinku zaposlenog.
     * 
     * @return Lozinka zaposlenog.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja jedinstveni identifikator zaposlenog.
     * 
     * @param id Jedinstveni identifikator zaposlenog.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Postavlja ime zaposlenog.
     * 
     * Ime ne sme biti null ili prazno.
     * 
     * @param ime Ime zaposlenog.
     * @throws NullPointerException Ako je ime null.
     * @throws IllegalArgumentException Ako je ime prazno.
     */
    public void setIme(String ime) {
    	if(ime == null) 
    		throw new NullPointerException("Ime ne sme biti null.");
    	
    	if(ime.isEmpty())
    		throw new IllegalArgumentException("Ime ne sme biti prazno.");
    	
        this.ime = ime;
    }

    /**
     * Postavlja prezime zaposlenog.
     * 
     * Prezime ne sme biti null ili prazno.
     * 
     * @param prezime Prezime zaposlenog.
     * @throws NullPointerException Ako je prezime null.
     * @throws IllegalArgumentException Ako je prezime prazno.
     */
    public void setPrezime(String prezime) {
    	if(prezime == null) 
    		throw new NullPointerException("Prezime ne sme biti null.");
    	
    	if(prezime.isEmpty())
    		throw new IllegalArgumentException("Prezime ne sme biti prazno.");
    	
        this.prezime = prezime;
    }

    /**
     * Postavlja korisničko ime zaposlenog.
     * 
     * Korisničko ime ne sme biti null ili prazno.
     * 
     * @param username Korisničko ime zaposlenog.
     * @throws NullPointerException Ako je korisničko ime null.
     * @throws IllegalArgumentException Ako je korisničko ime prazno.
     */
    public void setUsername(String username) {
    	if(username == null) 
    		throw new NullPointerException("Username ne sme biti null.");
    	
    	if(username.isEmpty())
    		throw new IllegalArgumentException("Username ne sme biti prazno.");
    	
        this.username = username;
    }

    /**
     * Postavlja lozinku zaposlenog.
     * 
     * Lozinka ne sme biti null ili prazna.
     * 
     * @param password Lozinka zaposlenog.
     * @throws NullPointerException Ako je lozinka null.
     * @throws IllegalArgumentException Ako je lozinka prazna.
     */
    public void setPassword(String password) {
    	if(password == null) 
    		throw new NullPointerException("Password ne sme biti null.");
    	
    	if(password.isEmpty())
    		throw new IllegalArgumentException("Password ne sme biti prazno.");
    	
        this.password = password;
    }

    /**
     * Vraća String reprezentaciju objekta Zaposleni.
     * 
     * @return String reprezentacija objekta Zaposleni.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraća hash kod objekta Zaposleni.
     * 
     * @return Hash kod objekta.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Upoređuje ovaj objekat sa drugim objekatom Zaposleni.
     * 
     * @param obj Objekat sa kojim se upoređuje.
     * @return true ako su objekti isti, inače false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zaposleni other = (Zaposleni) obj;
        return Objects.equals(this.username, other.username);
    }
    
    /**
     * Vraća naziv tabele u bazi podataka za ovu klasu.
     * 
     * @return Naziv tabele kao String.
     */
    @Override
    public String getTableName() {
        return "zaposleni";
    }

    /**
     * Vraća alias za tabelu u SQL upitima.
     * 
     * @return Alias kao String.
     */
    @Override
    public String alijas() {
        return "z";
    }

    /**
     * Vraća JOIN klauzulu za SQL upite, ako je potrebna.
     * 
     * @return JOIN klauzula kao String.
     */
    @Override
    public String join() {
        return "";
    }

    /**
     * Kreira listu objekata iz ResultSet-a.
     * 
     * @param rs ResultSet koji sadrži podatke o zaposlenima.
     * @return Lista objekata klase Zaposleni.
     * @throws SQLException Ako dođe do greške pri čitanju iz ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()) {
            Zaposleni z = new Zaposleni(
                    rs.getInt("id"),
                    rs.getString("ime"), 
                    rs.getString("prezime"),
                    rs.getString("username"), 
                    rs.getString("password"));
            lista.add(z);
        }
        
        rs.close();
        return lista;
    }

    /**
     * Vraća nazive kolona za INSERT SQL upit.
     * 
     * @return Kolone za INSERT kao String.
     */
    @Override
    public String getColumnsForInsert() {
        return " (ime,prezime,username,password) ";
    }

    /**
     * Vraća uslov za primarni ključ u SQL upitima.
     * 
     * @return Uslov za primarni ključ kao String.
     */
    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    /**
     * Vraća vrednosti za INSERT SQL upit.
     * 
     * @return Vrednosti za INSERT kao String.
     */
    @Override
    public String getValuesForInsert() {
        return "'" + ime + "', '" + prezime + "', '" + username + "', '" + password +"'";
    }

    /**
     * Vraća vrednosti za UPDATE SQL upit.
     * 
     * @return Vrednosti za UPDATE kao String.
     */
    @Override
    public String getValuesForUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', username = '" + username + "', password = '" + password + "' ";
    }

    /**
     * Vraća dodatne uslove za SQL upite.
     * 
     * @return Dodatni uslovi kao String.
     */
    @Override
    public String condition() {
        return "";
    }
    
    
}
