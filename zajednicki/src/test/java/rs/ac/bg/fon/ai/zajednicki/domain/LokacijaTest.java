/**
 * 
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
class LokacijaTest {
	
	private Lokacija lokacija;

	@BeforeEach
	void setUp() throws Exception {
		lokacija = new Lokacija();
	}

	@AfterEach
	void tearDown() throws Exception {
		lokacija = null;
	}

	@Test
	void testGetTableName() {
		assertEquals("lokacija", lokacija.getTableName());
	}

	@Test
	void testAlijas() {
	    assertEquals("l", lokacija.alijas());
	}

	@Test
	void testJoin() {
	    assertEquals("", lokacija.join());
	}

	@Test
	void testGetList() throws SQLException {
		ResultSet rs = mock(ResultSet.class);

	    when(rs.next()).thenReturn(true).thenReturn(false);
	    when(rs.getInt("id")).thenReturn(1);
	    when(rs.getString("naziv_lokacije")).thenReturn("Lokacija 1");
	    when(rs.getString("adresa")).thenReturn("Adresa 1");
	    when(rs.getString("grad")).thenReturn("Grad 1");

	    Lokacija lokacija = new Lokacija();
	    ArrayList<AbstractDomainObject> lista = lokacija.getList(rs);

	    assertEquals(1, lista.size());
	    Lokacija l = (Lokacija) lista.get(0);
	    assertEquals(1, l.getId());
	    assertEquals("Lokacija 1", l.getNaziv());
	    assertEquals("Adresa 1", l.getAdresa());
	    assertEquals("Grad 1", l.getGrad());
	}

	@Test
	void testGetColumnsForInsert() {
	    assertEquals("naziv_lokacije,adresa,grad", lokacija.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
	    assertEquals("id = 0", lokacija.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
		lokacija.setAdresa("Adresa 1");
		lokacija.setGrad("Grad 1");
		lokacija.setNaziv("Lokacija 1");
	    assertEquals("'Lokacija 1', 'Adresa 1', 'Grad 1'", lokacija.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		lokacija.setAdresa("Adresa 1");
		lokacija.setGrad("Grad 1");
		lokacija.setNaziv("Lokacija 1");
	    String expected = " naziv_lokacije = 'Lokacija 1', adresa = 'Adresa 1', grad = 'Grad 1'";
	    String actual = lokacija.getValuesForUpdate();
	    
	    assertEquals(expected, actual);
	}

	@Test
	void testCondition() {
	    assertEquals("", lokacija.condition());
	}

	@Test
	void testLokacija() {
		Lokacija l = new Lokacija();
	    assertNotNull(l);
	    assertEquals(0, l.getId());
	    assertNull(l.getNaziv());
	    assertNull(l.getAdresa());
	    assertNull(l.getGrad());
	}

	@Test
	void testLokacijaIntStringStringString() {
		Lokacija l = new Lokacija(2, "Lokacija 2", "Adresa 2", "Grad 2");
	    assertEquals(2, l.getId());
	    assertEquals("Lokacija 2", l.getNaziv());
	    assertEquals("Adresa 2", l.getAdresa());
	    assertEquals("Grad 2", l.getGrad());
	}

	@Test
	void testSetId() {
	    lokacija.setId(3);
	    assertEquals(3, lokacija.getId());
	}

	@Test
	void testSetNaziv() {
	    lokacija.setNaziv("Lokacija 3");
	    assertEquals("Lokacija 3", lokacija.getNaziv());
	}
	
	@Test
	void testSetNazivNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> lokacija.setNaziv(null)	);
		
		assertEquals("Naziv lokacije ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetNazivEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> lokacija.setNaziv("")	);
		
		assertEquals("Naziv lokacije ne sme biti prazan.", e.getMessage() );
	}

	@Test
	void testSetAdresa() {
	    lokacija.setAdresa("Adresa 3");
	    assertEquals("Adresa 3", lokacija.getAdresa());
	}
	
	@Test
	void testSetAdresaNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> lokacija.setAdresa(null)	);
		
		assertEquals("Adresa lokacije ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetAdresaEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> lokacija.setAdresa("")	);
		
		assertEquals("Adresa lokacije ne sme biti prazna.", e.getMessage() );
	}

	@Test
	void testSetGrad() {
	    lokacija.setGrad("Grad 3");
	    assertEquals("Grad 3", lokacija.getGrad());
	}
	
	@Test
	void testSetGradNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> lokacija.setGrad(null)	);
		
		assertEquals("Grad ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetGradEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> lokacija.setGrad("")	);
		
		assertEquals("Grad ne sme biti prazan.", e.getMessage() );
	}

	
	
	@Test
	void testToString() {
		lokacija.setAdresa("Adresa 1");
		lokacija.setGrad("Grad 1");
		lokacija.setNaziv("Lokacija 1");
	    String expected = "Lokacija: Lokacija 1, Adresa: Adresa 1, Grad: Grad 1";
	    assertEquals(expected, lokacija.toString());
	}

}
