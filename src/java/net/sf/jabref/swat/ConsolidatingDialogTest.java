package net.sf.jabref.swat;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexString;
import net.sf.jabref.DuplicateCheck;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.imports.OpenDatabaseAction;
import net.sf.jabref.swat.ConsolidatingDialog;

import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("serial")
public class ConsolidatingDialogTest extends ConsolidatingDialog {

	@Test
	public void removeDuplicatesWithNoDuplication() {
		Collection<BibtexString> entries = new ArrayList<BibtexString>();
		entries.add(new BibtexString("1", "name1", "content1"));
		entries.add(new BibtexString("2", "name2", "content2"));
		entries.add(new BibtexString("3", "name3", "content3"));
		entries.add(new BibtexString("4", "name4", "content4"));

		BibtexDatabase mergedBibDb = new BibtexDatabase();
		for (BibtexString bibtexString : entries) {
			mergedBibDb.addString(bibtexString);
		}

		ConsolidatingDialog dialog = new ConsolidatingDialog();
		BibtexDatabase result = dialog.removeDuplicates(mergedBibDb);

		Collection<BibtexString> resultEntries = result.getStringValues();
		assertEquals(resultEntries.size(), entries.size());
		assertTrue(entries.containsAll(resultEntries));
		assertTrue(resultEntries.containsAll(entries));
	}

	@Test
	public void removeDuplicatesWithDuplication() {
		File bibFile = new File("bib1.bib");
		BibtexDatabase originalDb = loadDb(bibFile);

		ConsolidatingDialog dialog = new ConsolidatingDialog();
		BibtexDatabase mergedDb = dialog.mergeBibFiles(new File[] { bibFile,
				bibFile });

		assertTrue(mergedDb.getEntries().size() == 2 * originalDb.getEntries()
				.size());

		BibtexDatabase result = dialog.removeDuplicates(mergedDb);

		Collection<BibtexEntryWrapper> bibEntriesResult = wrap(result);
		Collection<BibtexEntryWrapper> bibEntriesDup = wrap(originalDb);

		assertTrue(bibEntriesResult.size() == bibEntriesDup.size());
		assertTrue(bibEntriesResult.containsAll(bibEntriesDup));
		assertTrue(bibEntriesDup.containsAll(bibEntriesResult));
	}

	public static BibtexDatabase loadDb(File bibFile) {
		try {
			return OpenDatabaseAction.loadDatabase(bibFile,
					Globals.prefs.get("defaultEncoding")).getDatabase();
		} catch (IOException e) {
			fail(e.getMessage());
			return null;
		}
	}

	@Test
	public void writeBibDb() {
		String folderPath = ".";
		String filename = "out";
		String filePath = folderPath + File.separator + filename + ".bib";

		File bibFile = new File("bib1.bib");
		BibtexDatabase bibDb = loadDb(bibFile);

		ConsolidatingDialog.writeBibDb(bibDb, folderPath, filename);

		File outFile = new File(filePath);
		assertTrue(outFile.exists());
		assertTrue(outFile.isFile());

		BibtexDatabase outBibDb = loadDb(outFile);
		Collection<BibtexEntryWrapper> bibEntries = wrap(bibDb);
		Collection<BibtexEntryWrapper> outBibEntries = wrap(outBibDb);
		assertTrue(bibEntries.containsAll(outBibEntries));
		assertTrue(outBibEntries.containsAll(bibEntries));

		outFile.delete();
	}

	@Test
	public void writeBibDbException() {
		try {
			String folderPath = "";
			String filename = "";
			ConsolidatingDialog.writeBibDb(null, folderPath, filename);
			fail("No exception thrown");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void mainTest() {
		ConsolidatingDialog.main(null);
	}

	@Test
	public void mergeBibFiles() {
		File[] bibFiles = new File[2];
		bibFiles[0] = new File("bib1.bib");
		bibFiles[1] = new File("bib2.bib");

		ConsolidatingDialog dialog = new ConsolidatingDialog();
		BibtexDatabase result = dialog.mergeBibFiles(bibFiles);

		File file = new File("merged.bib");
		BibtexDatabase db = loadDb(file);

		Collection<BibtexEntryWrapper> resultEntries = wrap(result);
		Collection<BibtexEntryWrapper> expectedEntries = wrap(db);

		assertTrue(expectedEntries.containsAll(resultEntries));
		assertTrue(resultEntries.containsAll(expectedEntries));
	}

	@Test
	public void mergeBibFilesException() {
		File[] bibFiles = new File[2];
		bibFiles[0] = new File("bib0.bib");

		try {
			ConsolidatingDialog dialog = new ConsolidatingDialog();
			dialog.mergeBibFiles(bibFiles);
			fail("No exception thrown");
		} catch (Exception e) {
			// OK
		}
	}

	public static Collection<BibtexEntryWrapper> wrap(BibtexDatabase db) {
		Collection<BibtexEntryWrapper> entries = new LinkedList<BibtexEntryWrapper>();
		for (BibtexEntry bibtexEntry : db.getEntries()) {
			entries.add(new BibtexEntryWrapper(bibtexEntry));
		}
		return entries;
	}

	@BeforeClass
	public static void init() {
		HashMap<String, String> bindings = new HashMap<String, String>();
		bindings.put("defaultEncoding", System.getProperty("file.encoding"));
		bindings.put("overwriteOwner", Boolean.FALSE.toString());
		bindings.put("overwriteTimeStamp", Boolean.FALSE.toString());
		Globals.prefs = JabRefPreferences.getInstance();
		Globals.prefs.setNewKeyBindings(bindings);
	}

	public static class BibtexEntryWrapper {
		private BibtexEntry entry;

		public BibtexEntryWrapper(BibtexEntry entry) {
			this.entry = entry;
		}

		@Override
		public String toString() {
			return entry.toString();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			} else if (obj instanceof BibtexEntryWrapper) {
				BibtexEntryWrapper e = (BibtexEntryWrapper) obj;
				return DuplicateCheck.isDuplicate(e.entry, entry);
			} else {
				return false;
			}
		}
	}

	@Test
	public void searchBibFiles() {
		ConsolidatingDialog dialog = new ConsolidatingDialog();
		String folderPath = "./searchBibFilesTest";
		File folder = new File(folderPath);
		folder.mkdirs();
		File f1 = new File(folder, "1.bib");
		File f2 = new File(folder, "2.txt");
		File f3 = new File(folder, "3.bib");
		File d1 = new File(folder, "sub");
		try {
			f1.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			d1.mkdir();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		f1.deleteOnExit();
		f2.deleteOnExit();
		f3.deleteOnExit();
		folder.deleteOnExit();

		File[] result = dialog.searchBibFiles(folderPath);
		assertTrue(Arrays.asList(result).contains(f1));
		assertFalse(Arrays.asList(result).contains(f2));
		assertTrue(Arrays.asList(result).contains(f3));
		assertEquals(2, result.length);

		f1.delete();
		f2.delete();
		f3.delete();
		folder.delete();
	}

}
