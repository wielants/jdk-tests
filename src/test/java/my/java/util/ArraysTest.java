package my.java.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArraysTest {
	@Test
	public void test_asList () {
		String[] sut = new String[] {"test"};
		
		List<String> l = Arrays.asList(sut);
		
		assertThat (l, hasItem("test"));
	}
	
	@Test
	public void test_asList_Exception_addEntries () {
		String[] sut = new String[] {"test"};
		
		List<String> l = Arrays.asList(sut);
		try {
			l.add("test");
			fail ();
		}
		catch (UnsupportedOperationException e) {
			assertEquals (l.size(), sut.length);
		}
	}
	
	@Test
	public void test_asList_Exception_removeEntries () {
		String[] sut = new String[] {"test"};
		
		List<String> l = Arrays.asList(sut);
		try {
			l.remove("test");
			fail ();
		}
		catch (UnsupportedOperationException e) {
			assertEquals (l.size(), sut.length);
		}
	}
	
	@Test
	public void test_asList_updateEntries () {
		String oldValue = "test";
		String newValue = oldValue + "2";

		String[] sut = new String[] {oldValue};
		List<String> l = Arrays.asList(sut);
		
		l.set (0, newValue);
		
		assertEquals (l.get(0), newValue);
		assertEquals (sut[0], newValue);
	}
}
