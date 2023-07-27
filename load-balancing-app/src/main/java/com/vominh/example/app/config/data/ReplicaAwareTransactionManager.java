package com.vominh.example.app.config.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

@Component
@Profile("scalable")
public class ReplicaAwareTransactionManager implements PlatformTransactionManager {

    private final PlatformTransactionManager wrapped;

    public ReplicaAwareTransactionManager(PlatformTransactionManager wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        RouteDataSource.setCurrentDataSource(transactionDefinition.isReadOnly());
        return wrapped.getTransaction(transactionDefinition);
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        wrapped.commit(transactionStatus);
    }

    @Override
    public void rollback(TransactionStatus transactionStatus) throws TransactionException {
        wrapped.rollback(transactionStatus);
    }
}
