package my.java.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.hamcrest.Matchers;
import org.junit.Test;

public class FileTest {

	@Test(expected=NullPointerException.class)
	public void createFileWithNullStringThrowsNPE() {
		new File((String) null);
	}

	@Test(expected=NullPointerException.class)
	public void createFileWithNullURIThrowsNPE() {
		new File((URI) null);
	}

	@Test
	public void canCreateFileWithEmptyString() {
		File file = new File("");

		assertThat(file.getName(), Matchers.is(Matchers.isEmptyString()));
	}

	@Test
	public void parentOfRootIsNull() {
		File root = new File("/");

		assertNull(root.getParent());
	}

	@Test
	public void rootIsADirectoryAndNotAFile() {
		File root = new File("/");

		assertTrue(root.isDirectory());
		assertFalse(root.isFile());
	}

	@Test
	public void createRootWithNullAndSlash() {
		File root = new File((String) null, "/");

		assertThat(root.getName(), Matchers.is(Matchers.isEmptyString()));
		assertThat(root.getAbsolutePath(), Matchers.is("/"));
		assertNull(root.getParent());
	}

	@Test
	public void createRootWithEmptyStringAndSlash() {
		File root = new File("", "/");

		assertThat(root.getName(), Matchers.is(Matchers.isEmptyString()));
		assertThat(root.getAbsolutePath(), Matchers.is("/"));
		assertNull(root.getParent());
	}

	@Test
	public void test() {
		File directory = new File("dir/", "/");

		assertFalse(directory.exists());
		assertTrue(directory.isDirectory());
		assertThat(directory.getName(), Matchers.is("dir/"));
	}

	//FIXME: Wie mache ich den Test "schöner"?
	@Test
	public void createAndDeleteFile() throws IOException {
		File newFile = new File("test");

		boolean createdFile = newFile.createNewFile();
		assertTrue(createdFile);

		boolean fileExists = newFile.exists();
		assertTrue(fileExists);

		boolean deleted = newFile.delete();
		assertTrue(deleted);
	}
}
