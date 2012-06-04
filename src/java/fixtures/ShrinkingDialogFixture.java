package fixtures;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.OpenDatabaseAction;
import net.sf.jabref.imports.ParserResult;
import net.sf.jabref.swat.ConsolidatingDialog;
import net.sf.jabref.swat.ShrinkingDialog;
import net.sf.jabref.swat.ShrinkingDialogUtils;
import fit.ColumnFixture;

public class ShrinkingDialogFixture extends ColumnFixture {
	public String unshrinked_bib;
	public String shrinked_bib;
	public String tex_dir;
	public String result;

	public boolean result() throws Exception {
		
		if (!new File (tex_dir).exists() || !new File (tex_dir).isDirectory()) {
			throw new RuntimeException("Make sure tex_dir is in your home directory.");
		}
		
		Collection<String> keysFromTexFiles = ShrinkingDialogUtils.collectKeysFromTexFiles(tex_dir);
		
		ShrinkingDialog dialog = new ShrinkingDialog();
		ShrinkingDialog.init();
		ParserResult parserResult = OpenDatabaseAction.loadDatabase(new File(unshrinked_bib), Globals.prefs.get("defaultEncoding"));
		BibtexDatabase expectedBib = dialog.shrinkDatabase(keysFromTexFiles, parserResult.getDatabase());
		
		parserResult = OpenDatabaseAction.loadDatabase(new File(shrinked_bib), Globals.prefs.get("defaultEncoding"));
		BibtexDatabase actualBib = parserResult.getDatabase();

		// TODO comparison based on Citation Keys, enough ??
		HashSet<String> expectedCiteKeys = new HashSet<String>();
		for (BibtexEntry entry : expectedBib.getEntries()) {
			expectedCiteKeys.add(entry.getCiteKey());
		}

		HashSet<String> actualCiteKeys = new HashSet<String>();
		for (BibtexEntry entry : actualBib.getEntries()) {
			actualCiteKeys.add(entry.getCiteKey());
		}

		return (expectedCiteKeys.containsAll(actualCiteKeys) && actualCiteKeys
				.containsAll(expectedCiteKeys));
	}
}
