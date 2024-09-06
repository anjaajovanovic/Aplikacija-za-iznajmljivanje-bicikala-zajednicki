/**
 * 
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
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
class IznajmljivanjeTest {
	
	private Iznajmljivanje i;

	@BeforeEach
	void setUp() throws Exception {
		i = new Iznajmljivanje();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	void testGetTableName() {
		Iznajmljivanje i = new Iznajmljivanje();
	    assertEquals("iznajmljivanje", i.getTableName());
	}

	@Test
	void testAlijas() {
		Iznajmljivanje i = new Iznajmljivanje();
	    assertEquals("i", i.alijas());
	}

	@Test
	void testJoin() {
		Iznajmljivanje i = new Iznajmljivanje();
	    assertEquals("JOIN korisnik k ON (k.id = i.korisnik) JOIN zaposleni z ON (z.id = i.zaposleni)", i.join());
	}

	@Test
	void testGetList() throws SQLException {
		ResultSet rs = mock(ResultSet.class);

		Date expectedDate = new java.sql.Date(System.currentTimeMillis());
		
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getDate("datum")).thenReturn((java.sql.Date) expectedDate);
        when(rs.getDouble("ukupna_cena")).thenReturn(500.0);
        when(rs.getInt("zaposleni")).thenReturn(2);
        when(rs.getInt("korisnik")).thenReturn(3);

        Iznajmljivanje i = new Iznajmljivanje();
        ArrayList<AbstractDomainObject> lista = i.getList(rs);

        assertEquals(1, lista.size());
        Iznajmljivanje i1 = (Iznajmljivanje) lista.get(0);

        assertEquals(1, i1.getId());
        assertEquals(500.0, i1.getUkupnaCena(), 0.01);
        assertEquals((java.sql.Date)expectedDate, i1.getDatum());
        assertNotNull(i1.getZaposleni());
        assertNotNull(i1.getKorisnik());
        
	}

	@Test
	void testGetColumnsForInsert() {
		Iznajmljivanje i = new Iznajmljivanje();
	    assertEquals("datum,ukupna_cena,zaposleni,korisnik", i.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
		Iznajmljivanje i = new Iznajmljivanje();
	    i.setId(1);
	    assertEquals("id = 1", i.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
		Zaposleni z = new Zaposleni(2, "ImeZaposleni", "PrezimeZaposleni", "username", "password");
	    Korisnik k = new Korisnik(3, "ImeKorisnik", "PrezimeKorisnik", "email@gmail.com");
	    Iznajmljivanje i = new Iznajmljivanje(1, new Date(2024,5,5), 500.0, z, k, new ArrayList<>());

	    String expected = "'3924-06-05', 500.0, 2, 3";
	    assertEquals(expected, i.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		Iznajmljivanje i = new Iznajmljivanje();
	    i.setId(1);
	    i.setDatum(new Date(2024,5,5));
	    i.setUkupnaCena(500.0);

	    String expected = "datum = '3924-06-05', ukupna_cena = 500.0";
	    assertEquals(expected, i.getValuesForUpdate());
	}

	@Test
	void testCondition() {
	    assertEquals("", i.condition());
	}

	@Test
	void testIznajmljivanje() {
		Iznajmljivanje i = new Iznajmljivanje();
	    assertNotNull(i);
	    assertEquals(0, i.getId());
	    assertNull(i.getDatum());
	    assertEquals(0, i.getUkupnaCena());
	    assertNull(i.getKorisnik());
	    assertNull(i.getZaposleni());
	    assertNull(i.getStavkaIznajmljivanja());
	}

	@Test
	void testIznajmljivanjeIntDateDoubleZaposleniKorisnikArrayListOfStavkaIznajmljivanja() {
		Zaposleni z = new Zaposleni(2, "ImeZaposleni", "PrezimeZaposleni", "username", "password");
	    Korisnik k = new Korisnik(3, "ImeKorisnik", "PrezimeKorisnik", "email@example.com");
	    ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<>();

	    Iznajmljivanje i = new Iznajmljivanje(1, new Date(2024,7,7), 500.0, z, k, stavke);

	    assertEquals(1, i.getId());
	    assertEquals(new Date(2024, 7, 7), i.getDatum());
	    assertEquals(500.0, i.getUkupnaCena());
	    assertEquals(z, i.getZaposleni());
	    assertEquals(k, i.getKorisnik());
	    assertEquals(stavke, i.getStavkaIznajmljivanja());
	}

	@Test
	void testSetId() {
	    i.setId(5);
	    assertEquals(5, i.getId());
	}
	
	@Test
	void testSetDatum() {
	    Date datum = new Date(2024,1,1);
	    i.setDatum(datum);
	    assertEquals(datum, i.getDatum());
	}

	@Test
	void testSetDatumNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> i.setDatum(null)	);
		
		assertEquals("Datum ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetUkupnaCena() {
	    double ukupnaCena = 2000.0;
	    i.setUkupnaCena(ukupnaCena);
	    assertEquals(ukupnaCena, i.getUkupnaCena());
	}

	@Test
	void testSetZaposleni() {
	    Zaposleni zaposleni = new Zaposleni(2, "Ime", "Prezime", "username", "password");
	    i.setZaposleni(zaposleni);
	    assertEquals(zaposleni, i.getZaposleni());
	}
	
	@Test
	void testSetZaposleniNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> i.setZaposleni(null)	);
		
		assertEquals("Zaposleni ne sme biti null.", e.getMessage() );
	}

	@Test
	void testSetKorisnik() {
	    Korisnik korisnik = new Korisnik(3, "Ime", "Prezime", "email@example.com");
	    i.setKorisnik(korisnik);
	    assertEquals(korisnik, i.getKorisnik());
	}
	
	@Test
	void testSetKorisnikNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> i.setKorisnik(null)	);
		
		assertEquals("Korisnik ne sme biti null.", e.getMessage() );
	}

	@Test
	void testSetStavkaIznajmljivanja() {
	    ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<>();
	    i.setStavkaIznajmljivanja(stavke);
	    assertEquals(stavke, i.getStavkaIznajmljivanja());
	}

}
