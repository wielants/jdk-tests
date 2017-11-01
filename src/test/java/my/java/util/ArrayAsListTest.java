package my.java.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayAsListTest {
	@Test
	public void asListReturnsSameContentsAsArray() {
		String[] array = new String[] {"hello", "world"};

		List<String> list = Arrays.asList(array);

		assertThat (list, contains("hello", "world"));
	}

	@Test
	public void addingItemsToArrayAsListThrowsException() {
		String[] array = new String[] {"test"};

		List<String> list = Arrays.asList(array);
		try {
			list.add("test");
			fail ();
		}
		catch (UnsupportedOperationException e) {
			assertThat(list, hasSize(1));
		}
	}

	@Test
	public void removingEntriesFromArrayAsListThrowsException() {
		String[] array = new String[] {"test"};

		List<String> list = Arrays.asList(array);
		try {
			list.remove("test");
			fail ();
		}
		catch (UnsupportedOperationException e) {
			assertThat(list, hasSize(1));
		}
	}

	@Test
	public void updateValuesOnArrayAsListUpdatesValuesOnOriginalArray() {
		String[] array = new String[] {"oldValue"};
		List<String> list = Arrays.asList(array);

		list.set (0, "newValue");

		assertThat(list, contains("newValue"));
		assertThat(array, arrayContaining("newValue"));
	}
}
