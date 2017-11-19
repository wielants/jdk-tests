package my.java.util;

import static org.junit.Assert.assertFalse;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {
	private Map<String, String> map = new TreeMap<>();

	@Test(expected = NullPointerException.class)
	public void addingWithNullKeyIsNotAllowed() {
		map.put(null, "null-value");
	}
	
	@Test(expected=NullPointerException.class)
	public void containsKeyWithNullThrowsNPE() {
		assertFalse(map.containsKey(null));
	}
}
