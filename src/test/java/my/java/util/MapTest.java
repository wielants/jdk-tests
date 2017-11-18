package my.java.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MapTest {

	@Parameters(name="{0}")
	public static Object[] data() {
		return new Object[] { HashMap.class, TreeMap.class };
	}

	private Map<String, String> map;

	public MapTest(Class<Map<String, String>> implClass) throws InstantiationException, IllegalAccessException {
		map = implClass.newInstance();
	}

	@Test
	public void createdMapIsEmpty() {
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void addedMapIsNotEMpty() {
		map.put("key", "value");

		assertFalse(map.isEmpty());
	}

	@Test
	public void addedKeyCanBeQueried() {
		map.put("key", "value");

		assertThat(map, Matchers.hasEntry("key", "value"));
	}

	@Test
	public void addedKeyCanBeDeleted() {
		map.put("key", "value");

		map.remove("key");

		assertThat(map, Matchers.not(Matchers.hasEntry("key", "value")));
	}

	@Test
	public void clearedMapIsEmpty() {
		map.put("key", "value");

		map.clear();

		assertTrue(map.isEmpty());
	}
}
