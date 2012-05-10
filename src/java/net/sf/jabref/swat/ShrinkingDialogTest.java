package net.sf.jabref.swat;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

/*
 * Simplified version of the original test.
 */
public class ShrinkingDialogTest {
	@Test
	public void testRegex() {
		assertFalse("".matches(ShrinkingDialog.REGEX));
		assertFalse("skflhfs".matches(ShrinkingDialog.REGEX));
		assertTrue("\\cite{key}".matches(ShrinkingDialog.REGEX));
		assertTrue("\\citet{key}".matches(ShrinkingDialog.REGEX));
		assertTrue("\\citep{key}".matches(ShrinkingDialog.REGEX));
		assertTrue("\\cite {key}".matches(ShrinkingDialog.REGEX));
		assertTrue("\\cite\n{key}".matches(ShrinkingDialog.REGEX));
	}

	@Test
	public void testExtractKeys() {
		File texFile = new File("testShrinking/tex1.tex");

		ShrinkingDialog dialog = new ShrinkingDialog();
		Collection<? extends String> keys = dialog.extractKeys(texFile);
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

		ShrinkingDialog dialog = new ShrinkingDialog();
		Collection<String> keys = dialog.collectKeysFromTexFiles(texFolder);
		Collection<String> expected = Arrays.asList("x", "y", "a", "b", "c",
				"d", "w", "r");

		assertTrue(keys + " does not contains all " + expected,
				keys.containsAll(expected));
		assertTrue(expected + " does not contains all " + keys,
				expected.containsAll(keys));
	}

	@Test
	public void testExtractKeysException() {
		ShrinkingDialog dialog = new ShrinkingDialog();

		File texFile = new File("wrogtex.tex");
		try {
			dialog.extractKeys(texFile);
			fail("No exception thrown.");
		} catch (Exception e) {
			// fine
		}

	}
}
