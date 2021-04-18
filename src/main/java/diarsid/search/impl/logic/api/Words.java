package diarsid.search.impl.logic.api;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import diarsid.search.impl.model.Word;

public interface Words {

    Word getOrSave(UUID userUuid, String string, LocalDateTime time);

    Optional<Word> findBy(UUID userUuid, String string);
}
