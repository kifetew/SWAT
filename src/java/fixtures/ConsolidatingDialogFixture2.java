package fixtures;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.imports.OpenDatabaseAction;
import net.sf.jabref.imports.ParserResult;
import net.sf.jabref.swat.ConsolidatingDialog;
import fit.ColumnFixture;

public class ConsolidatingDialogFixture2 extends ColumnFixture {

	public String entry1;
	public String entry2;
	public String entry3;
	public String file1;
	public String file2;

	static {
		HashMap<String, String> bindings = new HashMap<String, String>();
		bindings.put("defaultEncoding", System.getProperty("file.encoding"));
		bindings.put("overwriteOwner", Boolean.FALSE.toString());
		bindings.put("overwriteTimeStamp", Boolean.FALSE.toString());
		Globals.prefs = JabRefPreferences.getInstance();
		Globals.prefs.setNewKeyBindings(bindings);
	}

	public int amount1() throws Exception {
		return amountOf("1");
	}

	public int amount2() throws Exception {
		return amountOf("2");
	}

	public int amount3() {
		return amountOf("3");
	}

	private int amountOf(String id) {
		buildInputs();

		ConsolidatingDialog dialog = new ConsolidatingDialog();
		dialog.consolidate(input_dir.getPath(), output_dir.getPath(),
				output_filename);
		
		ParserResult parserResult;
		try {
			parserResult = OpenDatabaseAction.loadDatabase(new File(output_dir,
					output_filename + ".bib"), Globals.prefs
					.get("defaultEncoding"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		BibtexDatabase db = parserResult.getDatabase();

		int counter = 0;
		Collection<BibtexEntry> resultEntries = db.getEntries();
		BibtexEntry ref = createEntryIn(null, EntryDescriptor.getEntry(id));
		for (BibtexEntry entry : resultEntries) {
			if (sameEntries(ref, entry)) {
				counter++;
			}
		}
		return counter;
	}

	private boolean sameEntries(BibtexEntry ref, BibtexEntry entry) {
		return ref.getCiteKey().equals(entry.getCiteKey());
		// Set<String> refFields = ref.getAllFields();
		// Set<String> fields = entry.getAllFields();
		// if (!refFields.containsAll(fields) || !fields.containsAll(refFields))
		// {
		// return false;
		// } else {
		// for (String field : fields) {
		// if (ref.getField(field).equals(entry.getField(field))) {
		// continue;
		// } else {
		// return false;
		// }
		// }
		// return true;
		// }
	}

	private File input_dir;
	private File output_dir;
	private final String output_filename = "out";

	private void buildInputs() {
		input_dir = createTempDir("input");
		input_dir.deleteOnExit();
		output_dir = createTempDir("output");
		output_dir.deleteOnExit();
		new File(output_dir, output_filename + ".bib").deleteOnExit();
		
		for (String entry : new String[] { entry1, entry2, entry3 }) {
			EntryDescriptor.registerEntry(entry);
		}
		int counter = 0;
		for (String content : new String[] { file1, file2 }) {
			BibtexDatabase db = new BibtexDatabase();
			counter++;
			if (content == null) {
				continue;
			} else {
				final String[] split = content.split("\\|");
				for (int i = 0; i < split.length; i++) {
					createEntryIn(db, EntryDescriptor.getEntry(split[i]));
				}
			}
			String filename = "in" + counter;
			ConsolidatingDialog.writeBibDb(db, input_dir.getPath(), filename);
			new File(input_dir, filename + ".bib").deleteOnExit();
		}
	}

	private BibtexEntry createEntryIn(BibtexDatabase db, EntryDescriptor desc) {
		BibtexEntry entry = new BibtexEntry(desc.getId());
		for (String name : desc.getFields()) {
			entry.setField(name, desc.getField(name));
		}

		if (db == null) {
			db = new BibtexDatabase();
		} else {
			// keep the one we have
		}
		db.insertEntry(entry);
		db.setCiteKeyForEntry(desc.getId(), desc.getId());
		return entry;
	}

	private static File createTempDir(String name2) {
		try {
			File dir = File.createTempFile(name2, "");
			dir.delete();
			dir.mkdirs();
			dir.deleteOnExit();
			return dir;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
