package my.java.util;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class HashSetTest {
	@Test
	public void addItemAddsItem() {
		Set<String> hs = new HashSet<String>();

		boolean hasBeenAdded = hs.add("hello");

		assertThat(hasBeenAdded, is(true));
		assertThat(hs, contains("hello"));
	}

	@Test
	public void addItemTwiceDoesNothing() {
		Set<String> hs = new HashSet<String>();

		hs.add("hello");
		boolean hasBeenAdded = hs.add("hello");

		assertThat(hasBeenAdded, is(false));
		assertThat(hs, contains("hello"));
	}

	@Test
	public void addNullAddsNullAsItem() {
		Set<String> hs = new HashSet<String>();

		boolean hasBeenAdded = hs.add(null);

		assertThat(hasBeenAdded, is(true));
		assertThat(hs, contains((String) null));
	}

	@Test
	public void addNullTwiceDoesNothing() {
		Set<String> hs = new HashSet<String>();

		hs.add(null);
		boolean hasBeenAdded = hs.add(null);

		assertThat(hasBeenAdded, is(false));
		assertThat(hs, contains((String) null));
	}

	@Test
	public void removeExistingItemRemovesItem() {
		Set<String> hs = new HashSet<String>();
		hs.add("hello");

		boolean hasBeenRemoved = hs.remove("hello");

		assertThat(hasBeenRemoved, is(true));
		assertThat(hs, hasSize(0));
	}

	@Test
	public void removeNonExistingItemDoesNothing() {
		Set<String> hs = new HashSet<String>();
		hs.add("hello");

		boolean hasBeenRemoved = hs.remove("world");

		assertThat(hasBeenRemoved, is(false));
		assertThat(hs, contains("hello"));
	}

	@Test
	public void retainItemsOnlyRetainsGivenItems() {
		Set<String> hs = new HashSet<String>();
		hs.addAll(Arrays.asList("hello", "world"));

		hs.retainAll(Arrays.asList("hello"));

		assertThat(hs, contains("hello"));
	}

	@Test
	public void removeItemsRemovesItemsFromSet() {
		Set<String> hs = new HashSet<String>();
		hs.addAll(Arrays.asList("hello", "world", "foo", "bar"));

		hs.removeAll(Arrays.asList("hello", "foo"));

		assertThat(hs, containsInAnyOrder("world", "bar"));
	}


	@Test
	public void clearSetRemovesAllItems() {
		Set<String> hs = new HashSet<String>();
		hs.addAll(Arrays.asList("hello", "world"));
		hs.clear();

		assertThat(hs, empty());
	}

	@Test
	public void containsAllChecksIfSetContainsAllGivenItems() {
		Set<String> hs = new HashSet<String>();
		hs.addAll(Arrays.asList("hello", "world"));
		boolean containsAllItems = hs.containsAll(Arrays.asList("hello", "world"));

		assertThat(containsAllItems, is(true));
	}
}
