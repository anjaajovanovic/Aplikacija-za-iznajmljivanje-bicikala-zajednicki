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
class BiciklTest {
	
	private Bicikl bicikl;
    private Lokacija lokacija;
    private ServisBicikl servis;

	@BeforeEach
	void setUp() throws Exception {
		lokacija = new Lokacija(1, "Lokacija", "Adresa", "Grad");
        servis = new ServisBicikl(1, new java.util.Date(2024, 5, 5), "Popravka gume", 1000);
        bicikl = new Bicikl(1, "Naziv", "Model", 2024, 300.0, lokacija, servis);
	}

	@AfterEach
	void tearDown() throws Exception {
		bicikl = null;
        lokacija = null;
        servis = null;
	}

	@Test
	void testAlijas() {
		assertEquals("b", bicikl.alijas());
	}

	@Test
	void testJoin() {
		String expected = "JOIN lokacija l ON (l.id = b.lokacija) JOIN servisBicikl sb ON (sb.id = b.servis)";
        assertEquals(expected, bicikl.join());
	}

	@Test
	void testGetList() throws SQLException {
		ResultSet rs = mock(ResultSet.class);
		
		Date expectedDate = new Date(2024,1,1);

	    when(rs.next()).thenReturn(true).thenReturn(false);
	    
	    // Bicikl
	    when(rs.getInt("id")).thenReturn(1);
	    when(rs.getString("naziv")).thenReturn("Naziv");
	    when(rs.getString("model")).thenReturn("Model");
	    when(rs.getInt("godina_proizvodnje")).thenReturn(2024);
	    when(rs.getDouble("cena_po_satu")).thenReturn(300.0);
	    when(rs.getInt("lokacija")).thenReturn(1);
	    when(rs.getInt("servis")).thenReturn(1);

	    // Lokacija
	    when(rs.getString("naziv_lokacije")).thenReturn("Lokacija1");
	    when(rs.getString("adresa")).thenReturn("Adresa1");
	    when(rs.getString("grad")).thenReturn("Grad1");
	    
	    // Servis
	    when(rs.getDate("datum_servisa")).thenReturn(new java.sql.Date(2024,1,1));
	    when(rs.getString("tip_servisa")).thenReturn("Servis1");
	    when(rs.getInt("cena_servisa")).thenReturn(100);

	    Bicikl b = new Bicikl();
	    
	    ArrayList<AbstractDomainObject> lista = b.getList(rs);

	    assertEquals(1, lista.size());
	    
	    Bicikl b1 = (Bicikl) lista.get(0);
	    
	    assertEquals(1, b1.getId());
	    assertEquals("Naziv", b1.getNaziv());
	    assertEquals("Model", b1.getModel());
	    assertEquals(2024, b1.getGodinaProizvodnje());
	    assertEquals(300.0, b1.getCenaPoSatu(), 0.01);
	    
	    assertNotNull(b1.getLokacija());
	    assertEquals("Lokacija1", b1.getLokacija().getNaziv());
	    assertEquals("Adresa1", b1.getLokacija().getAdresa());
	    assertEquals("Grad1", b1.getLokacija().getGrad());
	    
	    assertNotNull(b1.getServis());
	    assertEquals(expectedDate.getDate(), b1.getServis().getDatumServisa().getDate());
	    assertEquals("Servis1", b1.getServis().getTipServisa());
	    assertEquals(100, b1.getServis().getCenaServisa());
	}

	@Test
	void testGetColumnsForInsert() {
		assertEquals("naziv,model,godina_proizvodnje,cena_po_satu,lokacija,servis", bicikl.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
		assertEquals("id = 1", bicikl.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
		assertEquals("'Naziv', 'Model', 2024, 300.0, 1, 1", bicikl.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		assertEquals(" naziv = 'Naziv', model = 'Model', godina_proizvodnje = 2024, cena_po_satu = 300.0, lokacija = 1, servis = 1", bicikl.getValuesForUpdate());
	}

	@Test
	void testCondition() {
		assertEquals("", bicikl.condition());
	}

	@Test
	void testBicikl() {
		Bicikl b = new Bicikl();
        assertNotNull(b);
        assertEquals(0, b.getId());
        assertNull(b.getNaziv());
        assertNull(b.getModel());
        assertEquals(0, b.getGodinaProizvodnje());
        assertEquals(0.0, b.getCenaPoSatu());
        assertNull(b.getLokacija());
        assertNull(b.getServis());
	}

	@Test
	void testBiciklIntStringStringIntDoubleLokacijaServisBicikl() {
		assertEquals(1, bicikl.getId());
        assertEquals("Naziv", bicikl.getNaziv());
        assertEquals("Model", bicikl.getModel());
        assertEquals(2024, bicikl.getGodinaProizvodnje());
        assertEquals(300.0, bicikl.getCenaPoSatu());
        assertEquals(lokacija, bicikl.getLokacija());
        assertEquals(servis, bicikl.getServis());
	}

	@Test
	void testSetId() {
		bicikl.setId(2);
        assertEquals(2, bicikl.getId());
	}

	@Test
	void testSetNaziv() {
		bicikl.setNaziv("Novi naziv");
	    assertEquals("Novi naziv", bicikl.getNaziv());
	}
	
	@Test
	void testSetNazivNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> bicikl.setNaziv(null)	);
		
		assertEquals("Naziv ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetNazivEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> bicikl.setNaziv("")	);
		
		assertEquals("Naziv ne sme biti prazan.", e.getMessage() );
	}

	@Test
	void testSetModel() {
		bicikl.setModel("Novi model");
        assertEquals("Novi model", bicikl.getModel());
	}
	
	@Test
	void testSetModelNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> bicikl.setModel(null)	);
		
		assertEquals("Model ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetModelEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> bicikl.setModel("")	);
		
		assertEquals("Model ne sme biti prazan.", e.getMessage() );
	}

	@Test
	void testSetGodinaProizvodnje() {
		bicikl.setGodinaProizvodnje(2023);
        assertEquals(2023, bicikl.getGodinaProizvodnje());
	}

	@Test
	void testSetCenaPoSatu() {
		bicikl.setCenaPoSatu(200.0);
        assertEquals(200.0, bicikl.getCenaPoSatu());
	}

	@Test
	void testSetLokacija() {
		Lokacija novaLokacija = new Lokacija(2, "Nova Lokacija", "Nova adresa", "Novi grad");
        bicikl.setLokacija(novaLokacija);
        assertEquals(novaLokacija, bicikl.getLokacija());
	}
	
	@Test
	void testSetLokacijaNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> bicikl.setLokacija(null)	);
		
		assertEquals("Lokacija ne sme biti null.", e.getMessage() );
	}

	@Test
	void testSetServis() {
		ServisBicikl noviServis = new ServisBicikl(2, new java.util.Date(20204,5,5), "Novi servis", 200);
        bicikl.setServis(noviServis);
        assertEquals(noviServis, bicikl.getServis());
	}

	@Test
	void testToString() {
		String expected = "Bicikl: Naziv Model, Godina proizvodnje: 2024, Cena po satu: 300.0, Lokacija: Lokacija, Adresa: Adresa, Grad: Grad, Servis bicikla: Datum: Thu Jun 05 00:00:00 CEST 3924, Tip: Popravka gume, Cena: 1000";
	    assertEquals(expected, bicikl.toString());
	}

}
