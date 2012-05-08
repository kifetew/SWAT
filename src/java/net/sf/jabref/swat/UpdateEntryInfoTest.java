package net.sf.jabref.swat;

import static org.junit.Assert.*;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.swat.UpdateEntryInfo;

import org.junit.BeforeClass;
import org.junit.Test;

public class UpdateEntryInfoTest {

	@BeforeClass
	public static void init() {
		Globals.prefs = JabRefPreferences.getInstance();
	}

	@Test
	public void testAutoFillUrl() {
		BibtexEntry newEntry1 = new BibtexEntry();
		newEntry1.setField("title", "title1");
		newEntry1.setField("journal", "journal1");
		newEntry1.setField("url", "");

		UpdateEntryInfo.autoFillUrl(newEntry1);
		assertEquals("", newEntry1.getField("url"));

		BibtexEntry newEntry2 = new BibtexEntry();
		newEntry2.setField("title", "title2");
		newEntry2.setField("journal", "http://www.journal1.net");
		newEntry2.setField("url", "");

		UpdateEntryInfo.autoFillUrl(newEntry2);
		assertEquals("http://www.journal1.net", newEntry2.getField("url"));

		BibtexEntry newEntry3 = new BibtexEntry();
		newEntry3.setField("title", "title3");
		newEntry3.setField("journal", "http://www.journal3.net");
		newEntry3.setField("url", "www.title3.net");

		UpdateEntryInfo.autoFillUrl(newEntry3);
		assertEquals("www.title3.net", newEntry3.getField("url"));

		BibtexEntry newEntry4 = new BibtexEntry();
		newEntry4.setField("title", "title4");
		newEntry4.setField("journal", "http://www.journal4.net");

		UpdateEntryInfo.autoFillUrl(newEntry4);
		assertEquals("http://www.journal4.net", newEntry4.getField("url"));

		BibtexEntry newEntry5 = new BibtexEntry();
		newEntry5.setField("title", "title5");
		newEntry5.setField("journal", "journal5");

		UpdateEntryInfo.autoFillUrl(newEntry5);
		assertNull(newEntry5.getField("url"));

		BibtexEntry newEntry6 = new BibtexEntry();
		newEntry6.setField("title", "title6");
		newEntry6.setField("journal", "journal6");
		newEntry6.setField("url", "www.title3.net");

		UpdateEntryInfo.autoFillUrl(newEntry6);
		assertEquals("www.title3.net", newEntry6.getField("url"));

	}

}
