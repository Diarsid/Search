package diarsid.search.impl.logic.api;

import java.util.List;

import diarsid.search.api.model.Entry;

public interface EntriesLabelsJoinTable {

    List<Entry.Labeled> getAllJoinedTo(Entry entry);

    int removeAllBy(Entry entry);

    boolean removeBy(Entry entry, Entry.Label label);

    boolean removeBy(Entry entry, List<Entry.Label> labels);
}
