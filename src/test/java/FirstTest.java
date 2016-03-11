import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class FirstTest {
	@Test
	public void test() {
		assertThat(1, is(1));
	}
}
