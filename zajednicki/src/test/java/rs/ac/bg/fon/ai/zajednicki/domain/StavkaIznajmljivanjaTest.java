/**
 * 
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 
 */
class StavkaIznajmljivanjaTest {
	
	private StavkaIznajmljivanja si;
	private Iznajmljivanje i;
	private Bicikl b;

	@BeforeEach
	void setUp() throws Exception {
		b = new Bicikl();
		i = new Iznajmljivanje();
		si = new StavkaIznajmljivanja(i, 1, 4, 2000, b);
	}

	@AfterEach
	void tearDown() throws Exception {
		b = null;
		i = null;
		si = null;
	}

	@Test
	void testGetTableName() {
		StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    assertEquals("stavka_iznajmljivanja", si.getTableName());
	}

	@Test
	void testAlijas() {
		StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    assertEquals("si", si.alijas());
	}

	@Test
	void testJoin() {
		StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    String expected = " JOIN IZNAJMLJIVANJE I ON (I.ID = SI.IZNAJMLJIVANJE) "
	            + "JOIN BICIKL B ON (B.ID = SI.BICIKL) "
	            + "JOIN ZAPOSLENI Z ON (Z.ID = I.ZAPOSLENI) "
	            + "JOIN KORISNIK K ON (K.ID = I.KORISNIK)";
	    assertEquals(expected, si.join());
	}

	@Test
	void testGetList() throws SQLException {
		
		ResultSet rs = Mockito.mock(ResultSet.class);
		
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		
		Mockito.when(rs.getInt("id")).thenReturn(1);
	    Mockito.when(rs.getString("ime")).thenReturn("Marko");
	    Mockito.when(rs.getString("prezime")).thenReturn("Marković");
	    Mockito.when(rs.getString("username")).thenReturn("marko123");
	    Mockito.when(rs.getString("password")).thenReturn("password123");
	    Mockito.when(rs.getString("email")).thenReturn("marko@example.com");
	    Mockito.when(rs.getString("naziv_lokacije")).thenReturn("Centar");
	    Mockito.when(rs.getString("adresa")).thenReturn("Glavna 1");
	    Mockito.when(rs.getString("grad")).thenReturn("Beograd");
	    Mockito.when(rs.getDate("datum_servisa")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
	    Mockito.when(rs.getString("tip_servisa")).thenReturn("Veliki servis");
	    Mockito.when(rs.getInt("cena_servisa")).thenReturn(5000);
	    Mockito.when(rs.getString("naziv")).thenReturn("Bicikl X");
	    Mockito.when(rs.getString("model")).thenReturn("Model 2021");
	    Mockito.when(rs.getInt("godina_proizvodnje")).thenReturn(2021);
	    Mockito.when(rs.getDouble("cena_po_satu")).thenReturn(200.0);
	    Mockito.when(rs.getDate("datum")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
	    Mockito.when(rs.getDouble("ukupna_cena")).thenReturn(1000.0);
	    Mockito.when(rs.getInt("stavkaRB")).thenReturn(1);
	    Mockito.when(rs.getInt("broj_sati")).thenReturn(2);
	    Mockito.when(rs.getDouble("cena_stavke")).thenReturn(300.0);

	    StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    
	    ArrayList<AbstractDomainObject> lista = si.getList(rs);
	    
	    assertEquals(1, lista.size());
	    
	    StavkaIznajmljivanja result = (StavkaIznajmljivanja) lista.get(0);
	    
	    assertEquals(1, result.getStavkaRB());
	    assertEquals(2, result.getBrojSati());
	    assertEquals(300.0, result.getCenaStavke(), 0.01);
	    assertEquals("Bicikl X", result.getBicikl().getNaziv());
	    assertEquals(1, result.getBicikl().getId());
	    assertEquals("Marko", result.getIznajmljivanje().getZaposleni().getIme());
	}

	@Test
	void testGetColumnsForInsert() {
		StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    assertEquals(" iznajmljivanje,stavkaRB,broj_sati,cena_stavke,bicikl ", si.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
	    i.setId(1);
	    assertEquals("iznajmljivanje = 1", si.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
	    i.setId(1);
	    b.setId(2);
	    StavkaIznajmljivanja si = new StavkaIznajmljivanja(i, 1, 2, 300.0, b);
	    String expected = " 1, 1, 2, 300.0, 2";
	    assertEquals(expected, si.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
	    assertEquals("", si.getValuesForUpdate());
	}

	@Test
	void testCondition() {
	    i.setId(1);
	    assertEquals("WHERE I.ID = 1", si.condition());
	}

	@Test
	void testStavkaIznajmljivanja() {
		StavkaIznajmljivanja si = new StavkaIznajmljivanja();
	    assertNotNull(si);
	    assertNull(si.getIznajmljivanje());
	    assertNull(si.getBicikl());
	    assertEquals(0, si.getStavkaRB());
	    assertEquals(0.0, si.getCenaStavke());
	    assertEquals(0, si.getBrojSati());
	}

	@Test
	void testStavkaIznajmljivanjaIznajmljivanjeIntIntDoubleBicikl() {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
	    Bicikl bicikl = new Bicikl();
	    StavkaIznajmljivanja si = new StavkaIznajmljivanja(iznajmljivanje, 1, 2, 300.0, bicikl);
	    
	    assertEquals(iznajmljivanje, si.getIznajmljivanje());
	    assertEquals(1, si.getStavkaRB());
	    assertEquals(2, si.getBrojSati());
	    assertEquals(300.0, si.getCenaStavke());
	    assertEquals(bicikl, si.getBicikl());
	}

	@Test
	void testSetIznajmljivanje() {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
	    si.setIznajmljivanje(iznajmljivanje);
	    assertEquals(iznajmljivanje, si.getIznajmljivanje());
	}
	
	@Test
	void testSetIznajmljivanjeNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> si.setIznajmljivanje(null)	);
		
		assertEquals("Iznajmljivanje ne sme biti null.", e.getMessage() );
	}

	@Test
	void testSetStavkaRB() {
	    si.setStavkaRB(1);
	    assertEquals(1, si.getStavkaRB());
	}

	@Test
	void testSetBrojSati() {
	    si.setBrojSati(2);
	    assertEquals(2, si.getBrojSati());
	}
	
	@Test
	void testSetBrojSatiNegativan() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> si.setBrojSati(-2)	);
		
		assertEquals("Broj sati iznajmljivanja bicikla ne moze biti negativan.", e.getMessage() );
	}

	@Test
	void testSetCenaStavke() {
		 si.setCenaStavke(300.0);
		 assertEquals(300.0, si.getCenaStavke(), 0.01);
	}
	
	@Test
	void testSetCenaStavkeNegativna() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> si.setCenaStavke(-200.0)	);
		
		assertEquals("Cena stavke ne moze biti negativna.", e.getMessage() );
	}

	@Test
	void testSetBicikl() {
		Bicikl bicikl = new Bicikl();
	    si.setBicikl(bicikl);
	    assertEquals(bicikl, si.getBicikl());
	}
	
	@Test
	void testSetBiciklNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> si.setBicikl(null)	);
		
		assertEquals("Bicikl ne sme biti null.", e.getMessage() );
	}
	

	@Test
	void testToString() {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
	    iznajmljivanje.setId(1);
	    
        Bicikl bicikl = new Bicikl(1, "Naziv", "Model", 2024, 300.0, 
        		new Lokacija(1, "Lokacija", "Adresa", "Grad"), 
        		new ServisBicikl(1, new java.util.Date(2024, 5, 5), "Popravka gume", 1000));
        
	    si = new StavkaIznajmljivanja(iznajmljivanje, 1, 2, 300.0, bicikl);
	    
	    String expected = "Stavka iznajmljivanja: " +
	            "iznajmljivanje: " + iznajmljivanje +
	            ", redni broj: 1, broj sati: 2, cena stavke: 300.0, Bicikl: Naziv Model, Godina proizvodnje: 2024, Cena po satu: 300.0, Lokacija: Lokacija, Adresa: Adresa, Grad: Grad, Servis bicikla: Datum: Thu Jun 05 00:00:00 CEST 3924, Tip: Popravka gume, Cena: 1000";
	    assertEquals(expected, si.toString());
	}

}
