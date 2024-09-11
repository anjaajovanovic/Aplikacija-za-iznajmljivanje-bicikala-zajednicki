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
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 
 */
class ServisBiciklTest {
	
	private ServisBicikl servisBicikl;

	@BeforeEach
	void setUp() throws Exception {
		servisBicikl = new ServisBicikl();
	}

	@AfterEach
	void tearDown() throws Exception {
		servisBicikl = null;
	}

	@Test
	void testGetTableName() {
		assertEquals("servisBicikl", servisBicikl.getTableName());
	}

	@Test
	void testAlijas() {
		assertEquals("sb", servisBicikl.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", servisBicikl.join());
	}

	@Test
	void testGetList() throws SQLException {
		ResultSet rs = mock(ResultSet.class);
	    
		Date expectedDate = new Date(System.currentTimeMillis());
		
		when(rs.next()).thenReturn(true).thenReturn(false);
	    when(rs.getInt("id")).thenReturn(1);
	    when(rs.getDate("datumServisa")).thenReturn((java.sql.Date) expectedDate);
	    when(rs.getString("tipServisa")).thenReturn("Tip A");
	    when(rs.getInt("cenaServisa")).thenReturn(1000);

	    ServisBicikl sb = new ServisBicikl();
	    ArrayList<AbstractDomainObject> lista = sb.getList(rs);

	    assertEquals(1, lista.size());
	    ServisBicikl s = (ServisBicikl) lista.get(0);
	    assertEquals(1, s.getId());
	    assertEquals(expectedDate, s.getDatumServisa());
	    assertEquals("Tip A", s.getTipServisa());
	    assertEquals(1000, s.getCenaServisa());
	}

	@Test
	void testGetColumnsForInsert() {
		 assertEquals("datum_servisa,tip_servisa,cena_servisa", servisBicikl.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
		assertEquals("id = 0", servisBicikl.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
		servisBicikl.setCenaServisa(1000);
		servisBicikl.setDatumServisa(new java.util.Date(2024, 4, 4));
		servisBicikl.setTipServisa("Popravka kocnice");
		String expected = "'" + new Date(2024, 4, 4) + "', 'Popravka kocnice', 1000";
		assertEquals(expected, servisBicikl.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		servisBicikl.setCenaServisa(1000);
		servisBicikl.setDatumServisa(new java.util.Date(2024, 4, 4));
		servisBicikl.setTipServisa("Popravka kocnice");
		String expected = " datum_servisa = '" + new Date(2024, 4, 4) + "', tip_servisa = 'Popravka kocnice', cena_servisa = 1000";
		assertEquals(expected, servisBicikl.getValuesForUpdate());
	}

	@Test
	void testCondition() {
		assertEquals("", servisBicikl.condition());
	}

	@Test
	void testServisBicikl() {
		ServisBicikl sb = new ServisBicikl();
        assertNotNull(sb);
        assertEquals(0, sb.getId());
        assertEquals(0, sb.getCenaServisa());
        assertNull(sb.getDatumServisa());
        assertNull(sb.getTipServisa());
	}

	@Test
	void testServisBiciklIntDateStringInt() {
		ServisBicikl sb = new ServisBicikl(1, new java.util.Date(2024, 3, 3), "Popravka gume", 1000);
        assertEquals(1, sb.getId());
        assertEquals("Popravka gume", sb.getTipServisa());
        assertEquals(1000, sb.getCenaServisa());
        assertEquals(new java.util.Date(2024, 3, 3), sb.getDatumServisa());
	}

	@Test
	void testSetId() {
		servisBicikl.setId(2);
        assertEquals(2, servisBicikl.getId());
	}

	@Test
	void testSetDatumServisa() {
        servisBicikl.setDatumServisa(new java.util.Date(2024, 3, 3));
        assertEquals(new java.util.Date(2024, 3, 3), servisBicikl.getDatumServisa());
	}
	
	@Test
	void testSetDatumServisaNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> servisBicikl.setDatumServisa(null)	);
		
		assertEquals("Datum servisa ne sme biti null.", e.getMessage() );
	}

	@Test
	void testSetTipServisa() {
		servisBicikl.setTipServisa("Tip B");
        assertEquals("Tip B", servisBicikl.getTipServisa());
	}
	
	@Test
	void testSetTipServisaNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> servisBicikl.setTipServisa(null)	);
		
		assertEquals("Tip servisa ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetTipServisaEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> servisBicikl.setTipServisa("")	);
		
		assertEquals("Tip servisa ne sme biti prazan.", e.getMessage() );
	}
	

	@Test
	void testSetCenaServisa() {
		servisBicikl.setCenaServisa(1500);
        assertEquals(1500, servisBicikl.getCenaServisa());
	}
	
	@Test
	void testSetCenaServisaNegativna() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> servisBicikl.setCenaServisa(-2000)	);
		
		assertEquals("Cena servisa ne moze biti negativna.", e.getMessage() );
	}
	
	

	@Test
	void testToString() {
		servisBicikl.setCenaServisa(1000);
		servisBicikl.setDatumServisa(new java.util.Date(2024, 4, 4));
		servisBicikl.setTipServisa("Popravka kocnice");
		String expectedString = "Servis bicikla: Datum: Sun May 04 00:00:00 CEST 3924, Tip: Popravka kocnice, Cena: 1000";
        assertEquals(expectedString, servisBicikl.toString());
	}

}
