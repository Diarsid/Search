package diarsid.search.impl.logic.impl;

import java.util.Optional;
import java.util.UUID;

import diarsid.jdbc.api.Jdbc;
import diarsid.search.api.Users;
import diarsid.search.api.exceptions.NotFoundException;
import diarsid.search.api.model.User;
import diarsid.search.impl.logic.impl.support.ThreadBoundTransactional;
import diarsid.search.impl.model.RealUser;

import static diarsid.support.model.Storable.State.STORED;

public class UsersImpl extends ThreadBoundTransactional implements Users {

    public UsersImpl(Jdbc jdbc) {
        super(jdbc);
    }

    @Override
    public User create(String name) {
        User user = new RealUser(name);

        int updated = super.currentTransaction()
                .doUpdate(
                        "INSERT INTO users (uuid, name, time) \n" +
                                "VALUES (?, ?, ?);",
                        user.uuid(),
                        user.name(),
                        user.createdAt());

        if ( updated == 1 ) {
            user.setState(STORED);
        }

        return user;
    }

    @Override
    public Optional<User> findBy(String name) {
        Optional<User> user = super.currentTransaction()
                .doQueryAndConvertFirstRow(
                        RealUser::new,
                        "SELECT * \n" +
                                "FROM users \n" +
                                "WHERE users.name = ?;",
                        name);

        return user;
    }

    @Override
    public User getBy(UUID uuid) {
        User user = super.currentTransaction()
                .doQueryAndConvertFirstRow(
                        RealUser::new,
                        "SELECT * \n" +
                                "FROM users \n" +
                                "WHERE users.uuid = ?;",
                        uuid)
                .orElseThrow(NotFoundException::new);

        return user;
    }
}
