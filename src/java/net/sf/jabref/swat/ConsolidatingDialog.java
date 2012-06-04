package net.sf.jabref.swat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefFileChooser;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.MetaData;
import net.sf.jabref.Util;
import net.sf.jabref.export.FileActions;
import net.sf.jabref.export.SaveException;
import net.sf.jabref.imports.OpenDatabaseAction;
import net.sf.jabref.imports.ParserResult;

@SuppressWarnings("serial")
public class ConsolidatingDialog extends JDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		new ConsolidatingDialog().setVisible(true);
	}

	public static void init() {
		HashMap<String, String> bindings = new HashMap<String, String>();
		bindings.put("defaultEncoding", System.getProperty("file.encoding"));
		bindings.put("overwriteOwner", Boolean.FALSE.toString());
		bindings.put("overwriteTimeStamp", Boolean.FALSE.toString());
		Globals.prefs = JabRefPreferences.getInstance();
		Globals.prefs.setNewKeyBindings(bindings);
	}

	public ConsolidatingDialog() {
		setLayout(new GridLayout(4, 3, 5, 5));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Consolidate Database");

		this.add(new JLabel("Input Folder"));

		final JLabel inputFolderNameLabel = new JLabel("...");
		this.add(inputFolderNameLabel);

		AbstractAction inputFolderNameAction = new AbstractAction("Browse") {

			@Override
			public void actionPerformed(ActionEvent e) {
				JabRefFileChooser fc = new JabRefFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFolder = fc.getSelectedFile();
					inputFolderNameLabel.setText(selectedFolder.getPath());

				}
			}
		};

		JButton inputFolderNameButton = new JButton(inputFolderNameAction);
		this.add(inputFolderNameButton);

		this.add(new JLabel("Output Folder"));

		final JLabel outputFolderNameLabel = new JLabel("...");
		this.add(outputFolderNameLabel);

		AbstractAction outputFolderNameAction = new AbstractAction("Browse") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO factor code if possible
				JabRefFileChooser fc = new JabRefFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFolder = fc.getSelectedFile();
					outputFolderNameLabel.setText(selectedFolder.getPath());

				}
			}
		};

		JButton outputFolderNameButton = new JButton(outputFolderNameAction);
		this.add(outputFolderNameButton);

		this.add(new JLabel("Output Filename"));

		final JTextField outputFileNameText = new JTextField("reconciled");
		this.add(outputFileNameText);

		this.add(new JPanel());

		AbstractAction runAction = new AbstractAction("Run") {

			@Override
			public void actionPerformed(ActionEvent e) {
				consolidate(inputFolderNameLabel.getText(),
						outputFolderNameLabel.getText(),
						outputFileNameText.getText());
			}
		};
		JButton runButton = new JButton(runAction);
		this.add(runButton);

		AbstractAction cancelAction = new AbstractAction("Cancel") {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		JButton cancelButton = new JButton(cancelAction);
		this.add(cancelButton);

		pack();
	}

	public void consolidate(final String inputFolderName,
			final String outputFolderName, final String outputFileName) {
		File[] bibFiles = searchBibFiles(inputFolderName);
		BibtexDatabase mergedBibDb = mergeBibFiles(bibFiles);
		BibtexDatabase cleanedBibDb = removeDuplicates(mergedBibDb);
		writeBibDb(cleanedBibDb, outputFolderName, outputFileName);
	}

	public BibtexDatabase removeDuplicates(BibtexDatabase db) {
		Collection<BibtexEntry> entries = new LinkedList<BibtexEntry>(
				db.getEntries());
		LinkedList<String> knownKeys = new LinkedList<String>();
		for (BibtexEntry entry : entries) {
			String key = entry.getCiteKey();
			if (!knownKeys.contains(key)) {
				knownKeys.add(key);
			} else {
				db.removeEntry(entry.getId());
			}
		}
		return db;
	}

	public static void writeBibDb(BibtexDatabase cleanedBibDb,
			String folderPath, String filename) {
		try {
			MetaData metadata = new MetaData(new HashMap<String, String>(),
					cleanedBibDb);
			FileActions.saveDatabase(
					cleanedBibDb,
					metadata,
					new File(folderPath + File.separatorChar + filename
							+ ".bib"), Globals.prefs, false, false,
					Globals.prefs.get("defaultEncoding"), true).commit();
		} catch (SaveException e) {
			throw new RuntimeException(e);
		}
	}

	public BibtexDatabase mergeBibFiles(File[] bibFiles) {
		BibtexDatabase completeDb = null;
		for (File file : bibFiles) {
			try {
				ParserResult parserResult = OpenDatabaseAction.loadDatabase(
						file, Globals.prefs.get("defaultEncoding"));
				BibtexDatabase db = parserResult.getDatabase();
				// HashMap<String,String> metaData = parserResult.getMetaData();
				// TODO pass metaData back so that it's not lost when writing
				// bib to file
				if (completeDb == null) {
					completeDb = db;
				} else {
					boolean overwriteOwner = Globals.prefs
							.getBoolean("overwriteOwner");
					boolean overwriteTimeStamp = Globals.prefs
							.getBoolean("overwriteTimeStamp");
					for (String key : db.getKeySet()) {
						BibtexEntry originalEntry = db.getEntryById(key);
						BibtexEntry be = (BibtexEntry) (originalEntry.clone());
						be.setId(Util.createNeutralId());
						Util.setAutomaticFields(be, overwriteOwner,
								overwriteTimeStamp);
						completeDb.insertEntry(be);
					}
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Bibtex file " + file.getName()
								+ " is not a valid bib file.",
						"Invalid bib file", JOptionPane.ERROR_MESSAGE);
			}
		}
		return completeDb;
	}

	public File[] searchBibFiles(String folderPath) {
		Collection<File> listOfFiles = new ArrayList<File>();
		Collection<File> listOfFolders = new ArrayList<File>();
		listOfFolders.add(new File(folderPath));
		while (!listOfFolders.isEmpty()) {
			File folder = listOfFolders.iterator().next();
			listOfFolders.remove(folder);

			File[] folderContent = folder.listFiles();
			for (File file : folderContent) {
				if (file.canRead()) {
					if (file.isDirectory() && file.canExecute()) {
						listOfFolders.add(file);
					} else if (file.getName().endsWith(".bib")) {
						listOfFiles.add(file);
					} else {
						// not accessible folder or another type of file, do
						// nothing
					}
				} else {
					// not readable file, do nothing
				}
			}
		}

		return listOfFiles.toArray(new File[listOfFiles.size()]);
	}
}
