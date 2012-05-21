package fixtures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntryDescriptor {
	private static final Map<String, EntryDescriptor> entries = new HashMap<String, EntryDescriptor>();
	private final String id;
	private final Map<String, String> fields = new HashMap<String, String>();

	public static String registerEntry(String entryString) {
		EntryDescriptor entry = new EntryDescriptor(entryString);
		entries.put(entry.getId(), entry);
		return entry.getId();
	}

	public static EntryDescriptor getEntry(String id) {
		return entries.get(id);
	}

	public EntryDescriptor(String entryString) {
		final String[] split = entryString.split("\\|");
		id = split[0];
		for (int i = 1; i < split.length; i++) {
			final String[] row = split[i].split("\\=", 2);
			fields.put(row[0], row[1]);
		}
	}

	public String getId() {
		return id;
	}

	public Set<String> getFields() {
		return fields.keySet();
	}

	public String getField(String name) {
		return fields.get(name);
	}
}
