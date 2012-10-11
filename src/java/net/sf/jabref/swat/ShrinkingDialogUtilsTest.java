package net.sf.jabref.swat;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

/*
 * Simplified version of the original test.
 */
public class ShrinkingDialogUtilsTest {
	@Test
	public void testRegex() {
		assertFalse("".matches(ShrinkingDialogUtils.REGEX));
		assertFalse("skflhfs".matches(ShrinkingDialogUtils.REGEX));
		assertTrue("\\cite{key}".matches(ShrinkingDialogUtils.REGEX));
		assertTrue("\\citet{key}".matches(ShrinkingDialogUtils.REGEX));
		assertTrue("\\citep{key}".matches(ShrinkingDialogUtils.REGEX));
		assertTrue("\\cite {key}".matches(ShrinkingDialogUtils.REGEX));
		assertTrue("\\cite\n{key}".matches(ShrinkingDialogUtils.REGEX));
	}

	@Test
	public void testExtractKeys() {
		File texFile = new File("testShrinking/tex1.tex");

		Collection<? extends String> keys = ShrinkingDialogUtils
				.extractKeys(texFile);
		Collection<? extends String> expected = Arrays.asList("x", "y", "a",
				"b", "c", "d");

		assertTrue(keys + " does not contains all " + expected,
				keys.containsAll(expected));
		assertTrue(expected + " does not contains all " + keys,
				expected.containsAll(keys));
	}

	@Test
	public void testCollectKeys() {
		String texFolder = "testShrinking";

		Collection<String> keys = ShrinkingDialogUtils
				.collectKeysFromTexFiles(texFolder);
		Collection<String> expected = Arrays.asList("x", "y", "a", "b", "c",
				"d", "w", "r");

		assertTrue(keys + " does not contains all " + expected,
				keys.containsAll(expected));
		assertTrue(expected + " does not contains all " + keys,
				expected.containsAll(keys));
	}

	@Test
	public void testExtractKeysException() {
		File texFile = new File("wrogtex.tex");
		try {
			ShrinkingDialogUtils.extractKeys(texFile);
			fail("No exception thrown.");
		} catch (Exception e) {
			// fine
		}

	}
}
