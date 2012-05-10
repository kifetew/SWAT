package net.sf.jabref.swat;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.swat.ShrinkingDialog;
import net.sf.jabref.swat.ConsolidatingDialogTest.BibtexEntryWrapper;

import org.junit.BeforeClass;
import org.junit.Test;

public class ShrinkingDialogTest {
	@BeforeClass
	public static void init() {
		HashMap<String, String> bindings = new HashMap<String, String>();
		bindings.put("defaultEncoding", System.getProperty("file.encoding"));
		bindings.put("overwriteOwner", Boolean.FALSE.toString());
		bindings.put("overwriteTimeStamp", Boolean.FALSE.toString());
		Globals.prefs = JabRefPreferences.getInstance();
		Globals.prefs.setNewKeyBindings(bindings);
	}

	@Test
	public void testShrinking() {
		BibtexDatabase bib = ConsolidatingDialogTest.loadDb(new File(
				"testShrinking/initialBib.bib"));

		ShrinkingDialog dialog = new ShrinkingDialog();
		Collection<String> keys = Arrays.asList("x", "y", "a", "b", "c", "d",
				"w", "r");
		BibtexDatabase result = dialog.shrinkDatabase(keys, bib);

		BibtexDatabase db = ConsolidatingDialogTest.loadDb(new File(
				"testShrinking/shrinkedBib.bib"));

		Collection<BibtexEntryWrapper> resultEntries = ConsolidatingDialogTest
				.wrap(result);
		Collection<BibtexEntryWrapper> expectedEntries = ConsolidatingDialogTest
				.wrap(db);

		assertTrue(expectedEntries + " does not contains all " + resultEntries,
				expectedEntries.containsAll(resultEntries));
		assertTrue(resultEntries + " does not contains all " + expectedEntries,
				resultEntries.containsAll(expectedEntries));
	}

	@Test
	public void testMain() {
		ShrinkingDialog.main(null);
	}
}
