package com.vominh.example.scalableweb.config.data;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class ReplicaAwareTransactionManager implements PlatformTransactionManager {

    private final PlatformTransactionManager wrapped;

    public ReplicaAwareTransactionManager(PlatformTransactionManager wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();

        if (isTxActive) {
            AppDataSourceRoute.setCurrentDataSource(transactionDefinition.isReadOnly());

        }

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
