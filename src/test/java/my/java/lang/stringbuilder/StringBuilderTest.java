package my.java.lang.stringbuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class StringBuilderTest {

	@Test
	public void buildEmptyStringWithDefaultConstructor() {
		StringBuilder sb = new StringBuilder();

		assertThat(sb.toString(), is(""));
	}

	@Test
	public void buildEmptyString() {
		StringBuilder sb = new StringBuilder("");

		assertThat(sb.toString(), is(""));
	}

	@Test
	public void appendToEmptyString() {
		StringBuilder sb = new StringBuilder();
		sb.append("test");

		assertThat(sb.toString(), is("test"));
	}

	@Test
	public void appendTwoStrings() {
		StringBuilder sb = new StringBuilder("test");
		sb.append("foo");

		assertThat(sb.toString(), is("testfoo"));
	}

	@Test
	public void setLengthLargerThanOldLengthExtendsStringPaddedWithNullChars() {
		StringBuilder sb = new StringBuilder("test");

		sb.setLength(20);

		assertEquals(20, sb.length());
		String padding = sb.substring("test".length());
		for (int i = 0; i < padding.length(); i++) {
			assertEquals((char) 0, padding.charAt(i));
		}
	}

	@Test
	public void setLengthLessThanOldLengthTruncatesString() {
		StringBuilder sb = new StringBuilder("test");

		sb.setLength("te".length());

		assertThat(sb.toString(), is("te"));
	}

	@Test
	public void deletePrefix() {
		StringBuilder sb = new StringBuilder("prefixtest");

		sb.delete(0, "prefix".length());

		assertThat(sb.toString(), is("test"));
	}

	@Test
	public void deletePostfix() {
		StringBuilder sb = new StringBuilder("testpostfix");

		sb.delete("test".length(), "testpostfix".length());

		assertThat(sb.toString(), is("test"));
	}

	@Test
	public void deleteMiddlePart() {
		StringBuilder sb = new StringBuilder("prefixtestpostfix");

		sb.delete("prefix".length(), "prefixtest".length());

		assertThat(sb.toString(), is("prefixpostfix"));
	}

	@Test
	public void replacePrefix() {
		StringBuilder sb = new StringBuilder("prefixtest");

		sb.replace(0, "prefix".length(), "PRE");

		assertThat(sb.toString(), is("PREtest"));
	}

	@Test
	public void replacePostfix() {
		StringBuilder sb = new StringBuilder("testpostfix");

		sb.replace("test".length(), "testpostfix".length(), "POST");

		assertThat(sb.toString(), is("testPOST"));
	}

	@Test
	public void replaceMiddlePart() {
		StringBuilder sb = new StringBuilder("prefixtestpostfix");

		sb.replace("prefix".length(), "prefixtest".length(), "MIDDLE");

		assertThat(sb.toString(), is("prefixMIDDLEpostfix"));
	}

	@Test
	public void replacePostfixWithLongerString() {
		StringBuilder sb = new StringBuilder("testpostfix");

		sb.replace("test".length(), "testpostfix".length(), "MUCH LONGER STRING");

		assertThat(sb.toString(), is("testMUCH LONGER STRING"));
	}

	@Test
	public void replaceUsedAsInsert() {
		StringBuilder sb = new StringBuilder("prefixpostfix");

		sb.replace("prefix".length(), "prefix".length(), "INSERTED STRING");

		assertThat(sb.toString(), is("prefixINSERTED STRINGpostfix"));
	}

	@Test
	public void replaceWholeString() {
		StringBuilder sb = new StringBuilder("test");

		sb.replace(0, "test".length(), "REPLACEMENT");

		assertThat(sb.toString(), is("REPLACEMENT"));
	}

	@Test(expected = NullPointerException.class)
	public void constructorWithNullThrowsNPE() {
		new StringBuilder(null);
	}

	@Test
	public void appendNullResultsInStringNull() {
		StringBuilder sb = new StringBuilder();

		sb.append((String) null);

		assertThat(sb.toString(), is("null"));
	}

	@Test
	public void insertNullResultsInStringNull() {
		StringBuilder sb = new StringBuilder();

		sb.insert(0, (String) null);

		assertThat(sb.toString(), is("null"));
	}

	@Test(expected = NullPointerException.class)
	public void replaceWithNullThrowsNPE() {
		StringBuilder sb = new StringBuilder();

		sb.replace(0, 0, (String) null);
	}

	@Test(expected = NullPointerException.class)
	public void indexOfNullThrowsNPE() {
		StringBuilder sb = new StringBuilder();

		sb.indexOf(null);
	}

	@Test
	public void constructWithCapacitySet() {
		StringBuilder sb = new StringBuilder(1);

		assertEquals(1, sb.capacity());
	}

	@Test
	public void appendIncreasesTooLowCapacity() {
		StringBuilder sb = new StringBuilder(1);

		sb.append("hello");

		assertThat(sb.capacity(), Matchers.greaterThan(1));
	}
}
