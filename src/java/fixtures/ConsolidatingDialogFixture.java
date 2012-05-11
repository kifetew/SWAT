package fixtures;

import java.io.File;

import net.sf.jabref.swat.ConsolidatingDialog;
import fit.ColumnFixture;
import fitnesse.util.FileUtil;

public class ConsolidatingDialogFixture extends ColumnFixture {

	public String input_dir;
	public String output_dir;
	public String output_filename;
	public String result;

	public String result() throws Exception {
		ConsolidatingDialog dialog = new ConsolidatingDialog();
		dialog.consolidate(input_dir, output_dir, output_filename);
		return FileUtil.getFileContent(new File(output_dir, output_filename)
				.getPath());
	}
}
