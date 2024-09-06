/**
 * 
 */
package rs.ac.bg.fon.ai.zajednicki.domain;

import static org.junit.jupiter.api.Assertions.*;

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
class KorisnikTest {
	
	private Korisnik korisnik;

	@BeforeEach
	void setUp() throws Exception {
		korisnik = new Korisnik();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		korisnik = null;
	}

	@Test
	void testGetTableName() {
		assertEquals("korisnik", korisnik.getTableName());
	}

	@Test
	void testAlijas() {
		assertEquals("k", korisnik.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", korisnik.join());
	}

	@Test
	void testGetList() throws SQLException {
		ResultSet rs = Mockito.mock(ResultSet.class);

        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("id")).thenReturn(1);
        Mockito.when(rs.getString("ime")).thenReturn("Marko");
        Mockito.when(rs.getString("prezime")).thenReturn("Markovic");
        Mockito.when(rs.getString("email")).thenReturn("marko.markovic@example.com");

        Korisnik k = new Korisnik();
        ArrayList<AbstractDomainObject> lista = k.getList(rs);

        assertEquals(1, lista.size());
        Korisnik k1 = (Korisnik) lista.get(0);
        assertEquals(1, k1.getId());
        assertEquals("Marko", k1.getIme());
        assertEquals("Markovic", k1.getPrezime());
        assertEquals("marko.markovic@example.com", k1.getEmail());
	}

	@Test
	void testGetColumnsForInsert() {
		assertEquals("ime,prezime,email", korisnik.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
		assertEquals("id = 0", korisnik.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
		korisnik.setIme("Marko");
		korisnik.setPrezime("Markovic");
		korisnik.setEmail("marko.markovic@example.com");
		assertEquals("'Marko', 'Markovic', 'marko.markovic@example.com'", korisnik.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		korisnik.setIme("Marko");
		korisnik.setPrezime("Markovic");
		korisnik.setEmail("marko.markovic@example.com");
		assertEquals(" ime = 'Marko', prezime = 'Markovic', email = 'marko.markovic@example.com' ", korisnik.getValuesForUpdate());
	}

	@Test
	void testCondition() {
		assertEquals("", korisnik.condition());
	}

	@Test
	void testKorisnik() {
        assertNotNull(korisnik);
        assertEquals(0, korisnik.getId());
        assertNull(korisnik.getIme());
        assertNull(korisnik.getPrezime());
        assertNull(korisnik.getEmail());
	}

	@Test
	void testKorisnikIntStringStringString() {
		Korisnik k = new Korisnik(2, "Jovan", "Jovanovic", "jovan@gmail.com");
		assertEquals(2, k.getId());
        assertEquals("Jovan", k.getIme());
        assertEquals("Jovanovic", k.getPrezime());
        assertEquals("jovan@gmail.com", k.getEmail());
	}

	@Test
	void testSetId() {
		korisnik.setId(2);
        assertEquals(2, korisnik.getId());
	}

	@Test
	void testSetIme() {
		korisnik.setIme("Nikola");
        assertEquals("Nikola", korisnik.getIme());
	}
	
	@Test
	void testSetPrezimeNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> korisnik.setPrezime(null)	);
		
		assertEquals("Prezime ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetPrezimeEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> korisnik.setPrezime("")	);
		
		assertEquals("Prezime ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testSetPrezime() {
		korisnik.setPrezime("Jovanovic");
        assertEquals("Jovanovic", korisnik.getPrezime());
	}
	
	@Test
	void testSetImeNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> korisnik.setIme(null)	);
		
		assertEquals("Ime ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetImeEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> korisnik.setIme("")	);
		
		assertEquals("Ime ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testSetEmail() {
		korisnik.setEmail("nikola.jovanovic@gmail.com");
        assertEquals("nikola.jovanovic@gmail.com", korisnik.getEmail());
	}
	
	@Test
	void testSetEmailNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> korisnik.setEmail(null)	);
		
		assertEquals("Email ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetEmailEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> korisnik.setEmail("")	);
		
		assertEquals("Email ne sme biti prazan.", e.getMessage() );
	}

	@Test
	void testToString() {
		korisnik.setIme("Marko");
		korisnik.setPrezime("Markovic");
		assertEquals("Marko Markovic", korisnik.toString());
	}

}
