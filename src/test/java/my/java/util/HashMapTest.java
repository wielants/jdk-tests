package my.java.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

public class HashMapTest {

	private Map<String, String> map = new HashMap<>();

	@Test
	public void addingWithNullKeyIsAllowed() {
		map.put(null, "null-value");
		
		assertThat(map.keySet(), Matchers.contains((String) null));
	}
	
	@Test
	public void createdMapContainsNoNullKey() {
		assertFalse(map.containsKey(null));
	}
}
