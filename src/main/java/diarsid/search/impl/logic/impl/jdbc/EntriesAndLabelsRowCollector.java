package diarsid.search.impl.logic.impl.jdbc;

import java.util.UUID;

import diarsid.search.api.model.Entry;
import diarsid.search.impl.model.RealEntry;
import diarsid.search.impl.model.RealLabel;
import diarsid.jdbc.api.rows.OneToManyOnesListCollectorEmbeddedLists;

public class EntriesAndLabelsRowCollector extends OneToManyOnesListCollectorEmbeddedLists<Entry, UUID, Entry.Label, UUID> {

    public EntriesAndLabelsRowCollector() {
        super(
                Entry::labels,
                row -> row.get("entries.uuid", UUID.class),
                row -> row.get("labels.uuid", UUID.class),
                row -> new RealEntry("entries.", row),
                row -> new RealLabel("labels.", row));
    }

    public EntriesAndLabelsRowCollector(String entriesTable, String labelsTable) {
        super(
                Entry::labels,
                row -> row.get(entriesTable + "uuid", UUID.class),
                row -> row.get(labelsTable + "uuid", UUID.class),
                row -> new RealEntry(entriesTable, row),
                row -> new RealLabel(labelsTable, row));
    }

}