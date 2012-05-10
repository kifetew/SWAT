package net.sf.jabref.swat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefFileChooser;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.imports.OpenDatabaseAction;

@SuppressWarnings("serial")
public class ShrinkingDialog extends JDialog {

	public static void init() {
		HashMap<String, String> bindings = new HashMap<String, String>();
		bindings.put("defaultEncoding", System.getProperty("file.encoding"));
		bindings.put("overwriteOwner", Boolean.FALSE.toString());
		bindings.put("overwriteTimeStamp", Boolean.FALSE.toString());
		Globals.prefs = JabRefPreferences.getInstance();
		Globals.prefs.setNewKeyBindings(bindings);
	}

	public ShrinkingDialog() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Shrinking Database");

		setLayout(new GridLayout(3, 2, 5, 5));

		this.add(new JLabel("bib File"));
		JPanel bibPathPanel = new JPanel();
		final JTextField bibPathText = new JTextField();
		bibPathText.setColumns(20);
		bibPathPanel.add(bibPathText);
		bibPathPanel.add(new JButton(new AbstractAction("Browse") {

			@Override
			public void actionPerformed(ActionEvent e) {
				JabRefFileChooser fc = new JabRefFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FileNameExtensionFilter("Bib Files", "bib"));
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFolder = fc.getSelectedFile();
					bibPathText.setText(selectedFolder.getPath());

				}
			}
		}));
		this.add(bibPathPanel);

		this.add(new JLabel("tex Folder"));
		JPanel texPathPanel = new JPanel();
		final JTextField texPathText = new JTextField();
		texPathText.setColumns(20);
		texPathPanel.add(texPathText);
		texPathPanel.add(new JButton(new AbstractAction("Browse") {

			@Override
			public void actionPerformed(ActionEvent e) {
				JabRefFileChooser fc = new JabRefFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFolder = fc.getSelectedFile();
					texPathText.setText(selectedFolder.getPath());

				}
			}
		}));
		this.add(texPathPanel);

		this.add(new JButton(new AbstractAction("Run") {

			@Override
			public void actionPerformed(ActionEvent e) {
				String bibPath = bibPathText.getText();
				File bibFile = new File(bibPath);
				String bibName = bibFile.getName();

				Collection<String> keys = ShrinkingDialogUtils
						.collectKeysFromTexFiles(texPathText.getText());
				BibtexDatabase bib;
				try {
					bib = OpenDatabaseAction.loadDatabase(new File(bibPath),
							Globals.prefs.get("defaultEncoding")).getDatabase();
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
				BibtexDatabase shrinkedBib = shrinkDatabase(keys, bib);
				String folderPath = bibFile.getParent();
				String fileName = bibName.substring(0, bibName.length() - 4);
				ConsolidatingDialog.writeBibDb(shrinkedBib, folderPath,
						fileName + "_shrinked");
			}
		}));

		this.add(new JButton(new AbstractAction("Close") {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}));

		pack();

	}

	public BibtexDatabase shrinkDatabase(Collection<String> keys,
			BibtexDatabase bib) {
		keys = new LinkedList<String>(keys);
		BibtexDatabase shrinkedDb = new BibtexDatabase();
		for (String id : bib.getKeySet()) {
			BibtexEntry entry = bib.getEntryById(id);
			String key = entry.getCiteKey();
			if (keys.contains(key)) {
				BibtexEntry clone = (BibtexEntry) (entry.clone());
				shrinkedDb.insertEntry(clone);
				keys.remove(key);
			}
		}
		return shrinkedDb;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		new ShrinkingDialog().setVisible(true);
	}

}
