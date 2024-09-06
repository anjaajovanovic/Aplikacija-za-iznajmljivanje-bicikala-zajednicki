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
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 
 */
class ZaposleniTest {
	
	private Zaposleni z;

	@BeforeEach
	void setUp() throws Exception {
		z = new Zaposleni();
	}

	@AfterEach
	void tearDown() throws Exception {
		z = null;
	}

	@Test
	void testGetTableName() {
        assertEquals("zaposleni", z.getTableName());
    }

	@Test
	void testAlijas() {
        assertEquals("z", z.alijas());
    }

	@Test
	void testJoin() {
	     assertEquals("", z.join());
	}

	@Test
	void testGetList() throws SQLException {
	    ResultSet rs = mock(ResultSet.class);
	    when(rs.next()).thenReturn(true).thenReturn(false);
	    when(rs.getInt("id")).thenReturn(1);
	    when(rs.getString("ime")).thenReturn("Ime");
	    when(rs.getString("prezime")).thenReturn("Prezime");
	    when(rs.getString("username")).thenReturn("username");
	    when(rs.getString("password")).thenReturn("password");

	    Zaposleni zaposleni = new Zaposleni();
	    ArrayList<AbstractDomainObject> lista = zaposleni.getList(rs);

	    assertEquals(1, lista.size());
	    Zaposleni z = (Zaposleni) lista.get(0);
	    assertEquals(1, z.getId());
	    assertEquals("Ime", z.getIme());
	    assertEquals("Prezime", z.getPrezime());
	    assertEquals("username", z.getUsername());
	    assertEquals("password", z.getPassword());
	}

	@Test
	void testGetColumnsForInsert() {
	    assertEquals(" (ime,prezime,username,password) ", z.getColumnsForInsert());
	}

	@Test
	void testGetPrimaryKey() {
	    assertEquals("id = 0", z.getPrimaryKey());
	}

	@Test
	void testGetValuesForInsert() {
	    z.setId(4);
	    z.setIme("Ime");
	    z.setPrezime("Prezime");
	    z.setUsername("username");
	    z.setPassword("password");
	    
	    assertEquals("'Ime', 'Prezime', 'username', 'password'", z.getValuesForInsert());
	}

	@Test
	void testGetValuesForUpdate() {
		z.setId(4);
	    z.setIme("Ime");
	    z.setPrezime("Prezime");
	    z.setUsername("username");
	    z.setPassword("password");
	    
	    assertEquals(" ime = 'Ime', prezime = 'Prezime', username = 'username', password = 'password' ", z.getValuesForUpdate());
	}

	@Test
	void testCondition() {
        assertEquals("", z.condition());
    }

	@Test
	void testZaposleni() {
	    Zaposleni zaposleni = new Zaposleni();
	    assertNotNull(zaposleni);
	    assertEquals(0, zaposleni.getId());
	    assertNull(zaposleni.getIme());
	    assertNull(zaposleni.getPrezime());
	    assertNull(zaposleni.getUsername());
	    assertNull(zaposleni.getPassword());
	}

	@Test
	void testZaposleniIntStringStringStringString() {
		Zaposleni zaposleni = new Zaposleni(1, "Ime", "Prezime", "username", "password");
	    assertEquals(1, zaposleni.getId());
	    assertEquals("Ime", zaposleni.getIme());
	    assertEquals("Prezime", zaposleni.getPrezime());
	    assertEquals("username", zaposleni.getUsername());
	    assertEquals("password", zaposleni.getPassword());
	}

	@Test
	void testSetId() {
        z.setId(10);
        assertEquals(10, z.getId());
    }

	@Test
	void testSetIme() {
        z.setIme("Marko");
        assertEquals("Marko", z.getIme());
    }
	
	@Test
	void testSetImeNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> z.setIme(null)	);
		
		assertEquals("Ime ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetImeEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> z.setIme("")	);
		
		assertEquals("Ime ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testSetPrezime() {
        z.setPrezime("Marković");
        assertEquals("Marković", z.getPrezime());
    }
	
	@Test
	void testSetPrezimeNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> z.setPrezime(null)	);
		
		assertEquals("Prezime ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetPrezimeEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> z.setPrezime("")	);
		
		assertEquals("Prezime ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testSetUsername() {
        z.setUsername("marko123");
        assertEquals("marko123", z.getUsername());
    }
	
	@Test
	void testSetUsernameNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> z.setUsername(null)	);
		
		assertEquals("Username ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetUsernameEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> z.setUsername("")	);
		
		assertEquals("Username ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testSetPassword() {
        z.setPassword("password");
        assertEquals("password", z.getPassword());
    }
	
	@Test
	void testSetPasswordNull() {
		Exception e = assertThrows(java.lang.NullPointerException.class,   
				() -> z.setPassword(null)	);
		
		assertEquals("Password ne sme biti null.", e.getMessage() );
	}
	
	@Test
	void testSetPassowrdEmpty() {
		Exception e = assertThrows(java.lang.IllegalArgumentException.class,   
				() -> z.setPassword("")	);
		
		assertEquals("Password ne sme biti prazno.", e.getMessage() );
	}

	@Test
	void testToString() {
		z.setIme("Ime");
	    z.setPrezime("Prezime");
	    
	    assertEquals("Ime Prezime", z.toString());
	}

	@Test
	void testEqualsObject() {
	    Zaposleni z1 = new Zaposleni(1, "Ime", "Prezime", "username", "password");
	    Zaposleni z2 = new Zaposleni(1, "Ime", "Prezime", "username", "password");
	    Zaposleni z3 = new Zaposleni(2, "Ime2", "Prezime2", "username2", "password2");

	    assertTrue(z1.equals(z2));
	    assertFalse(z1.equals(z3));
	    assertFalse(z1.equals(null));
	    assertFalse(z1.equals(new Object()));
	}

}
