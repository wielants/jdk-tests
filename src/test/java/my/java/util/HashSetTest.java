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
	
	private Set<String> set = new HashSet<>();
	
	@Test
	public void addItemAddsItem() {
		boolean hasBeenAdded = set.add("hello");

		assertThat(hasBeenAdded, is(true));
		assertThat(set, contains("hello"));
	}

	@Test
	public void addItemTwiceDoesNothing() {
		set.add("hello");
		boolean hasBeenAdded = set.add("hello");

		assertThat(hasBeenAdded, is(false));
		assertThat(set, contains("hello"));
	}

	@Test
	public void addNullAddsNullAsItem() {
		boolean hasBeenAdded = set.add(null);

		assertThat(hasBeenAdded, is(true));
		assertThat(set, contains((String) null));
	}

	@Test
	public void addNullTwiceDoesNothing() {
		set.add(null);
		boolean hasBeenAdded = set.add(null);

		assertThat(hasBeenAdded, is(false));
		assertThat(set, contains((String) null));
	}

	@Test
	public void removeExistingItemRemovesItem() {
		set.add("hello");

		boolean hasBeenRemoved = set.remove("hello");

		assertThat(hasBeenRemoved, is(true));
		assertThat(set, hasSize(0));
	}

	@Test
	public void removeNonExistingItemDoesNothing() {
		set.add("hello");

		boolean hasBeenRemoved = set.remove("world");

		assertThat(hasBeenRemoved, is(false));
		assertThat(set, contains("hello"));
	}

	@Test
	public void retainItemsOnlyRetainsGivenItems() {
		set.addAll(Arrays.asList("hello", "world"));

		set.retainAll(Arrays.asList("hello"));

		assertThat(set, contains("hello"));
	}

	@Test
	public void removeItemsRemovesItemsFromSet() {
		set.addAll(Arrays.asList("hello", "world", "foo", "bar"));

		set.removeAll(Arrays.asList("hello", "foo"));

		assertThat(set, containsInAnyOrder("world", "bar"));
	}


	@Test
	public void clearSetRemovesAllItems() {
		set.addAll(Arrays.asList("hello", "world"));
		set.clear();

		assertThat(set, empty());
	}

	@Test
	public void containsAllChecksIfSetContainsAllGivenItems() {
		set.addAll(Arrays.asList("hello", "world"));
		boolean containsAllItems = set.containsAll(Arrays.asList("hello", "world"));

		assertThat(containsAllItems, is(true));
	}
}
