package diarsid.search.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import diarsid.search.impl.logic.api.UsersLocking;
import diarsid.search.impl.logic.impl.UsersLockingImpl;
import diarsid.search.tests.EmbeddedTransactionalRollbackTest;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.CompletableFuture.runAsync;

import static diarsid.support.concurrency.threads.ThreadsUtil.sleepSafely;
import static org.junit.jupiter.api.Assertions.fail;

public class UsersLockingTest extends EmbeddedTransactionalRollbackTest {

    UsersLocking locking = new UsersLockingImpl(JDBC);

    @Test
    public void test() {
        AtomicReference<Long> transact1Begin = new AtomicReference<>();
        AtomicReference<Long> transact2Begin = new AtomicReference<>();
        AtomicReference<Long> transact1BeforeUnlock = new AtomicReference<>();
        AtomicReference<Long> transact2AfterLock = new AtomicReference<>();
        AtomicReference<Long> transact2End = new AtomicReference<>();

        CompletableFuture asyncTransact1 = runAsync(() -> {
            JDBC.doInTransaction(transaction -> {
                transact1Begin.set(currentTimeMillis());
                locking.lock(USER);
                sleepSafely(500);
                transact1BeforeUnlock.set(currentTimeMillis());
            });
        });

        CompletableFuture asyncTransact2 = runAsync(() -> {
            JDBC.doInTransaction(transaction -> {
                sleepSafely(100);
                transact2Begin.set(currentTimeMillis());
                locking.lock(USER);
                transact2AfterLock.set(currentTimeMillis());
                transact2End.set(currentTimeMillis());
            });
        });

        CompletableFuture.allOf(asyncTransact1, asyncTransact2).join();

        if ( transact1Begin.get() > transact2Begin.get() ) {
            fail();
        }

        if ( transact2AfterLock.get() < transact1BeforeUnlock.get() ) {
            fail();
        }
    }
}