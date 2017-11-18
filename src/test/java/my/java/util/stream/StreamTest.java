package my.java.util.stream;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.Test;

public class StreamTest {
	@Test
	public void filterLongerStringsOutOfStream() {
		Stream<String> stream = Arrays.asList("short", "longer").stream();

		List<String> result = stream.filter(str -> str.length() > "short".length()).collect(Collectors.toList());

		assertThat(result, contains("longer"));
	}

	@Test
	public void mapStringsToValuesAndAdd2() {
		Stream<String> stream = Arrays.asList("1", "2", "3").stream();

		List<Integer> result = stream.map(str -> Integer.valueOf(str) + 2).collect(Collectors.toList());

		assertThat(result, contains(3, 4, 5));
	}

	@Test
	public void returnDistinctValuesFromStream() {
		Stream<String> stream = Arrays.asList("same", "same", "different").stream();

		List<String> result = stream.distinct().collect(Collectors.toList());

		assertThat(result, containsInAnyOrder("same", "different"));
	}

	@Test
	public void returnSortedValuesFromStream() {
		Stream<String> stream = Arrays.asList("this", "is", "a", "totally", "unsorted", "list").stream();

		List<String> result = stream.sorted().collect(Collectors.toList());

		assertThat(result, contains("a", "is", "list", "this", "totally", "unsorted"));
	}

	@Test
	public void returnFirst5ItemsFromStream() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		List<Integer> result = stream.limit(5).collect(Collectors.toList());

		assertThat(result, contains(1, 2, 3, 4, 5));
	}

	@Test
	public void returnAllButTheFirst5ItemsFromStream() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		List<Integer> result = stream.skip(5).collect(Collectors.toList());

		assertThat(result, contains(6, 7, 8, 9, 10));
	}

	@Test
	public void returnNumberOfItemsOfStream() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		long lengthOfStream = list.stream().count();

		assertThat(lengthOfStream, is(Long.valueOf(list.size())));
	}

	@Test
	public void returnMaxItemOfStream() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		Optional<Integer> maxValue = stream.max(Integer::compare);

		assertThat(maxValue.get(), is(10));
	}

	@Test
	public void returnMinItemOfStream() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		Optional<Integer> minValue = stream.min(Integer::compare);

		assertThat(minValue.get(), is(1));
	}

	@Test
	public void returnIfAnyItemIsGreaterThan10() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		boolean result = stream.anyMatch(i -> i > 10);

		assertThat(result, is(false));
	}

	@Test
	public void returnIfAllItemAreLessThan11() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		boolean result = stream.allMatch(i -> i < 11);

		assertThat(result, is(true));
	}

	@Test
	public void returnIfNoItemIsLessThan1() {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

		boolean result = stream.noneMatch(i -> i < 1);

		assertThat(result, is(true));
	}

	@Test
	public void firstElementOfEmptyStreamReturnsEmptyOptional() {
		Stream<Integer> stream = Stream.empty();

		Optional<Integer> result = stream.findFirst();

		assertThat(result.isPresent(), is(false));
	}

	@Test(expected=NullPointerException.class)
	public void returnFirstElementOfStreamWithNullValueThowsNPE() {
		Stream<Integer> stream = Stream.of((Integer) null);

		stream.findFirst();
	}

	@Test
	public void aStreamOfOneIntegerConsistsOfOneIntegerOnly() {
		Stream<Integer> stream = Stream.of(Integer.valueOf(0));

		assertThat(stream.toArray(), Matchers.arrayContaining(Integer.valueOf(0)));
	}

	@Test
	public void emptyStreamIsEmpty() {
		Stream<Integer> stream = Stream.empty();

		assertThat(stream.toArray(), Matchers.emptyArray());
	}

	@Test
	public void iterateCanBuildVeryLongStreams() {
		Stream<Integer> stream = Stream.iterate(Integer.valueOf(0), i -> i + 1);

		Optional<Integer> element = stream.skip(1000).findFirst();
		assertThat(element.get(), is(1000));
	}

	@Test
	public void sumAllElementsbyReduction() {
		Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream();

		Optional<Integer> sum = stream.reduce(Integer::sum);

		assertThat(sum.get(), is(55));
	}

	@Test
	public void sumOfOneElementIsElement() {
		Stream<Integer> stream = Stream.of(Integer.valueOf(1));

		Optional<Integer> sum = stream.reduce(Integer::sum);

		assertThat(sum.get(), is(Integer.valueOf(1)));
	}

	@Test
	public void sumOfNoElementIsNothing() {
		Stream<Integer> stream = Stream.empty();

		Optional<Integer> sum = stream.reduce(Integer::sum);

		assertThat(sum.isPresent(), is(false));
	}

	@Test
	public void sumOfNoElementIsIdentity() {
		Stream<Integer> stream = Stream.empty();

		Integer dummyIdentity = 42;

		Integer sum = stream.reduce(dummyIdentity, Integer::sum);

		assertThat(sum, is(dummyIdentity));
	}

	@Test
	public void reductionIsFoldL() {
		Stream<Integer> stream = Arrays.asList(1,2,3).stream();

		Optional<Integer> diff = stream.reduce((a, b) -> a - b);

		assertThat(diff.get(), is(1 - 2 - 3));
	}

	@Test
	public void reductionIsFoldL2() {
		Stream<Integer> stream = Arrays.asList(1,2,3).stream();

		Integer identity = 0;
		Integer diff = stream.reduce(identity, (a, b) -> a - b);

		assertThat(diff, is(identity - 1 - 2 - 3));
	}
}
