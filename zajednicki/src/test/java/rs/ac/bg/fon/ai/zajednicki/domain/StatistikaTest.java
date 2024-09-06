/**
 * 
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 
 */
class StatistikaTest {
	
	private Statistika s;
	private Korisnik k;
	private Bicikl b;

	@BeforeEach
	void setUp() throws Exception {
		b = new Bicikl(1, "Naziv 1", "Model 1", 2022, 200.0, 
				new Lokacija(1, "Lokacija 1", "Adresa 1", "Grad 1"), 
				new ServisBicikl(1, new java.util.Date(2024, 5, 5), "Tip A", 1000));
		k = new Korisnik(1, "Marko", "Markovic", "marko.markovic@gmail.com");
		s = new Statistika(1, 4, k, b);
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
		k = null;
		b = null;
	}

	@Test
	void testGetTableName() {
		assertEquals("statistika", s.getTableName());
	}

	@Test
	void testAlijas() {
		assertEquals("s", s.alijas());
	}

	@Test
	void testJoin() {
		String expectedJoin = " JOIN KORISNIK K ON (K.ID = S.KORISNIK) JOIN BICIKL B ON (B.ID = S.BICIKL) ";
	    assertEquals(expectedJoin, s.join());
	}

	@Test
	void testGetList() throws SQLException {
	    ResultSet rs = mock(ResultSet.class);

	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
	    
	    // Statistika
	    when(rs.getInt("id")).thenReturn(1);
	    when(rs.getInt("ocena")).thenReturn(4);
	    when(rs.getInt("korisnik")).thenReturn(2);
	    when(rs.getInt("bicikl")).thenReturn(3);

	    // Korisnik
	    when(rs.getString("ime")).thenReturn("Ime");
	    when(rs.getString("prezime")).thenReturn("Prezime");
	    when(rs.getString("email")).thenReturn("email@gmail.com");

	    // Bicikl
	    when(rs.getString("naziv")).thenReturn("Naziv 1");
	    when(rs.getString("model")).thenReturn("Model 1");
	    when(rs.getInt("godina_proizvodnje")).thenReturn(2022);
	    when(rs.getDouble("cena_po_satu")).thenReturn(200.0);
	    when(rs.getInt("lokacija")).thenReturn(1);
	    when(rs.getInt("servis")).thenReturn(1);


	    ArrayList<AbstractDomainObject> lista = s.getList(rs);

	    assertEquals(1, lista.size());

	    Statistika stat = (Statistika) lista.get(0);

	    // Statistika
	    assertEquals(1, stat.getId());
	    assertEquals(4, stat.getOcena());
	    assertNotNull(stat.getKorisnik());
	    assertNotNull(stat.getBicikl());

	    // Korisnik
	    assertEquals("Ime", stat.getKorisnik().getIme());
	    assertEquals("Prezime", stat.getKorisnik().getPrezime());
	    assertEquals("email@gmail.com", stat.getKorisnik().getEmail());
	    
	    // Bicikl
	    assertEquals("Naziv 1", stat.getBicikl().getNaziv());
	    assertEquals("Model 1", stat.getBicikl().getModel());
	    assertEquals(2022, stat.getBicikl().getGodinaProizvodnje());
	    assertEquals(200.0, stat.getBicikl().getCenaPoSatu());
	    assertNotNull(stat.getBicikl().getLokacija());
	    assertNotNull(stat.getBicikl().getServis());
	}

	@Test
	void testGetColumnsForInsert() {
		assertEquals("ocena,korisnik,bicikl", s.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
        assertEquals("id = 1", s.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
        assertEquals("4,1,1", s.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
        assertEquals("ocena = 4, korisnik = 1, bicikl = 1", s.getValuesForUpdate());
	}

	@Test
	void testCondition() {
        assertEquals("", s.condition());
	}

	@Test
	void testStatistika() {
		Statistika s = new Statistika();
        assertNotNull(s);
        assertEquals(0, s.getId());
        assertEquals(0, s.getOcena());
        assertNull(s.getKorisnik());
        assertNull(s.getBicikl());
	}

	@Test
	void testStatistikaIntIntKorisnikBicikl() {
		Statistika stat = new Statistika(1, 5, k, b);
	    assertEquals(1, stat.getId());
	    assertEquals(5, stat.getOcena());
	    assertEquals(k, stat.getKorisnik());
	    assertEquals(b, stat.getBicikl());
	}

	@Test
	void testSetId() {
		s.setId(2);
	    assertEquals(2, s.getId());
	}

	@Test
	void testSetOcena() {
		s.setOcena(3);
	    assertEquals(3, s.getOcena());
	}

	@Test
	void testSetKorisnik() {
		Korisnik newKorisnik = new Korisnik();
	    s.setKorisnik(newKorisnik);
	    assertEquals(newKorisnik, s.getKorisnik());
	}
	
	@Test
	void testSetKorisnikNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> s.setKorisnik(null)	);
		
		assertEquals("Korisnik ne sme biti null.", e.getMessage() );
	}
	
	
	@Test
	void testSetBicikl() {
		Bicikl newBicikl = new Bicikl();
	    s.setBicikl(newBicikl);
	    assertEquals(newBicikl, s.getBicikl());
	}
	
	@Test
	void testSetBiciklNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> s.setBicikl(null)	);
		
		assertEquals("Bicikl ne sme biti null.", e.getMessage() );
	}

	@Test
	void testToString() {
		s = new Statistika(1, 5, k, b);
	    String expected = "Statistika: ocena: 5, Marko Markovic, Bicikl: Naziv 1 Model 1, "
	    		+ "Godina proizvodnje: 2022, Cena po satu: 200.0, Lokacija: Lokacija 1, "
	    		+ "Adresa: Adresa 1, Grad: Grad 1, Servis bicikla: Datum: Thu Jun 05 00:00:00 CEST 3924, "
	    		+ "Tip: Tip A, Cena: 1000";
	    assertEquals(expected, s.toString());
	}

}
