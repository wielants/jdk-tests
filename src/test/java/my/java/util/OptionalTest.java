package my.java.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class OptionalTest {
	@Test
	public void creatingOptionalWithPresentValue() {
		Optional<String> optional = Optional.of("hello");

		assertThat(optional.isPresent(), is(true));
		assertThat(optional.get(), is("hello"));
	}

	@Test(expected=NullPointerException.class)
	public void createOptionalWithPresentValueNullThrowsNPE() {
		Optional.of(null);
	}

	@Test
	public void anEmptyOptionalIsEmpty() {
		Optional<String> emptyOptional = Optional.empty();

		assertThat(emptyOptional.isPresent(), is(false));
	}

	@Test
	public void mappingAnEmptyOptionalReturnsAnEmptyOptional() {
		Optional<String> emptyOptional = Optional.empty();

		Optional<Object> result = emptyOptional.map(s -> s + " world");

		assertThat(result.isPresent(), is(false));
	}

	@Test
	public void mappingAnOptionalWithPresetValueReturnsAnOptionalWithPresentValue() {
		Optional<String> optional = Optional.of("hello");

		Optional<Object> result = optional.map(s -> s + " world");

		assertThat(result.get(), is("hello world"));
	}

	@Test
	public void mappingAnOptionalWithPresetValueReturnsEmptyOptional() {
		Optional<String> optional = Optional.of("hello");

		Optional<Object> result = optional.map(s -> null);

		assertThat(result.isPresent(), is(false));
	}

	@Test
	public void createOptionalTheNullableWayCreatesEmptyOptional() {
		Optional<Object> emptyOptional = Optional.ofNullable(null);

		assertThat(emptyOptional.isPresent(), is(false));
	}

	@Test(expected=NoSuchElementException.class)
	public void callGetOnEmptyOptionalThrowsNoSuchElementException() {
		Optional<String> emptyOptional = Optional.ofNullable(null);

		emptyOptional.get();
	}

	@Test
	public void filterAnEmptyOptionalAlwaysReturnsAnEmptyOptional() {
		Optional<String> emptyOptional = Optional.ofNullable(null);

		Optional<String> result = emptyOptional.filter(item -> true);

		assertThat(result.isPresent(), is(false));
	}

	@Test
	public void aClosedFilterAlwaysReturnsAnEmptyOptional() {
		Optional<String> fullOptional = Optional.ofNullable("hello");

		Optional<String> result = fullOptional.filter(item -> false);

		assertThat(result.isPresent(), is(false));
	}

	@Test
	public void anOpenFilterAlwaysReturnsThePresentValue() {
		Optional<String> fullOptional = Optional.ofNullable("hello");

		Optional<String> result = fullOptional.filter(item -> true);

		assertThat(result.get(), is("hello"));
	}

	@Test(expected=NullPointerException.class)
	public void orElseThrowsOnEmptyOptionalWihoutAnExceptionSupplierThrowsNPE() {
		Optional<String> emptyOptional = Optional.empty();

		emptyOptional.orElseThrow(null);
	}

	@Test
	public void orElseOnEmptyOptionalReturnsGivenValue() {
		Optional<String> emptyOptional = Optional.empty();

		String result = emptyOptional.orElse("something instead");

		assertThat(result, is("something instead"));
	}
}
