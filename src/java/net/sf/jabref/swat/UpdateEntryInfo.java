package net.sf.jabref.swat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jabref.BibtexEntry;

public class UpdateEntryInfo {

	public static void autoFillUrl(BibtexEntry entry) {
		if (entry.getField("url") == null || entry.getField("url").isEmpty()) {
			for (String field : entry.getAllFields()) {
				if (field.equalsIgnoreCase("doi")) {
					// ignore the DOI
				} else {
					Pattern p = Pattern
							.compile("http\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?");
					Matcher m = p.matcher(entry.getField(field));
					if (m.find()) {
						entry.setField("url", m.group());
						break;
					} else {
						// continue
					}
				}
			}
		} else {
			// OK, URL already exists so we can't use it.
		}
	}

}
