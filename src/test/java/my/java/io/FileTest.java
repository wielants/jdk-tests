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
	public void rootIsADirectory() {
		File root = new File("/");

		assertTrue(root.isDirectory());
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
	public void aNotExistingDirectoryIsNotADirectory() {
		File notExistingDirectory = new File("/", "notExistingDirectory");

		assertFalse(notExistingDirectory.exists());
		assertFalse(notExistingDirectory.isDirectory());
	}

	@Test
	public void anExistingDirectoryIsADirectory() {
		File existingDirectory = new File("/", "etc");

		assertTrue(existingDirectory.exists());
		assertTrue(existingDirectory.isDirectory());
	}
	
	@Test
	public void theNameOfAFileHasNoSlash() {
		File existingFile = new File("/etc/passwd");
		
		assertThat(existingFile.getName(), Matchers.is("passwd"));
	}

	@Test
	public void theAbsoluteFilenameInJavaDoesNotRemoveInnerRelativeParts() {
		String pathWithInnerRelativeParts = "/etc/../etc/../etc/passwd";
		File existingFile = new File(pathWithInnerRelativeParts);
		
		assertThat(existingFile.getAbsolutePath(), Matchers.is(pathWithInnerRelativeParts));
	}

	@Test
	public void theCanonicalFilenameDoesRemoveInnerRelativeParts() throws IOException {
		String pathWithInnerRelativeParts = "/etc/../etc/../etc/passwd";
		File existingFile = new File(pathWithInnerRelativeParts);
		
		assertThat(existingFile.getCanonicalPath(), Matchers.is("/etc/passwd"));
	}
	
	@Test
	public void createdNewFileOnDiskExistsAndCanBeDeleted() throws IOException {
		File newFile = new File("test");

		boolean createdFile = newFile.createNewFile();

		assertTrue(createdFile);
		
		try {
		   assertTrue(newFile.exists());
		   assertTrue(newFile.delete());
		} finally {
			newFile.delete();
		}
	}
}
