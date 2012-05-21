package fixtures;

import java.io.File;
import java.util.HashSet;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.OpenDatabaseAction;
import net.sf.jabref.imports.ParserResult;
import net.sf.jabref.swat.ConsolidatingDialog;
import fit.ColumnFixture;

public class ConsolidatingDialogFixture extends ColumnFixture {

	public String input_dir;
	public String output_dir;
	public String output_filename;
	public String expected_filename;
	public String result;

	public boolean result() throws Exception {
		ConsolidatingDialog dialog = new ConsolidatingDialog();
		dialog.consolidate(input_dir, output_dir, output_filename);

		// TODO comparison based on Citation Keys, enough ??

		ParserResult parserResult = OpenDatabaseAction.loadDatabase(new File(
				expected_filename), Globals.prefs.get("defaultEncoding"));
		HashSet<String> expectedCiteKeys = new HashSet<String>();
		for (BibtexEntry entry : parserResult.getDatabase().getEntries()) {
			expectedCiteKeys.add(entry.getCiteKey());
		}

		parserResult = OpenDatabaseAction.loadDatabase(new File(output_dir,
				output_filename), Globals.prefs.get("defaultEncoding"));
		HashSet<String> actualCiteKeys = new HashSet<String>();
		for (BibtexEntry entry : parserResult.getDatabase().getEntries()) {
			actualCiteKeys.add(entry.getCiteKey());
		}

		return (expectedCiteKeys.containsAll(actualCiteKeys) && actualCiteKeys
				.containsAll(expectedCiteKeys));
	}
}
