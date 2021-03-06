package diarsid.librarian.impl.model;

import java.time.LocalDateTime;
import java.util.UUID;

import diarsid.jdbc.api.sqltable.rows.Row;
import diarsid.librarian.api.model.Pattern;

import static diarsid.support.model.Storable.State.STORED;

public class RealPattern extends AbstractIdentifiableUserScoped implements Pattern {

    private final String string;

    public RealPattern(UUID uuid, String string, UUID userUuid) {
        super(uuid, userUuid);
        this.string = string;
    }

    public RealPattern(UUID uuid, String string, LocalDateTime time, UUID userUuid, State state) {
        super(uuid, time, userUuid, state);
        this.string = string;
    }

    public RealPattern(Row row) {
        super(
                row.get("uuid", UUID.class),
                row.get("time", LocalDateTime.class),
                row.get("user_uuid", UUID.class),
                STORED);
        this.string = row.get("string", String.class);
    }

    public RealPattern(String columnPrefix, Row row) {
        super(
                row.get(columnPrefix + "uuid", UUID.class),
                row.get(columnPrefix + "time", LocalDateTime.class),
                row.get(columnPrefix + "user_uuid", UUID.class),
                STORED);
        this.string = row.get(columnPrefix + "string", String.class);
    }

    @Override
    public String string() {
        return this.string;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "'" + super.uuid() + '\'' +
                ", '" + string + '\'' +
                '}';
    }
}
