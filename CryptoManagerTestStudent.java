//package CryptoManager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testStringInBounds() {
		assertEquals("The selected string is not in bounds, Try again.", CryptoManager.caesarEncryption("abc", 1));
		assertEquals("The selected string is not in bounds, Try again.", CryptoManager.caesarEncryption("AbC", 30));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("S]#SB@C0Q##YW^]U^$C", CryptoManager.caesarEncryption("CMSC203 ASSIGNMENT3", 16));
		assertEquals("&06&USVC$66,*10(17V", CryptoManager.caesarEncryption("CMSC203 ASSIGNMENT3", 355));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("CMSC203 ASSIGNMENT3", CryptoManager.caesarDecryption("S]#SB@C0Q##YW^]U^$C", 16));
		assertEquals("CMSC203 ASSIGNMENT3", CryptoManager.caesarDecryption("&06&USVC$66,*10(17V", 355));
	}

	@Test
	public void testEncryptBellaso() {
		assertEquals("GSO^Z##\"GQXMT\"Q",  CryptoManager.bellasoEncryption("ENCRYPT BELLASO", "BELLASO"));
		assertEquals("CGOPFYVP=8M'&*",  CryptoManager.bellasoEncryption("ABCDEFG1234567", "BELLASO_KEY203"));
	}

	@Test
	public void testDecryptBellaso() {
		assertEquals("DECRYPT BELLASO",  CryptoManager.bellasoDecryption("FJO^Z##\"GQXMT\"Q", "BELLASO"));
		assertEquals("ABCDEFG1234567",  CryptoManager.bellasoDecryption("CGOPFYVP=8M'&*", "BELLASO_KEY203"));
	}

}
