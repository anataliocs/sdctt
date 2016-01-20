package com.thumbtack.database.data.manager;

import com.thumbtack.database.data.DataValues;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * Manage transactions
 */
public class TransactionMgr {
    private List<DataValues> transactionQueue = new ArrayList<>();


    public TransactionMgr() {
    }

    public DataValues start(DataValues oldTransaction) {
        transactionQueue.add(oldTransaction);
        return new DataValues();
    }

    public DataValues rollback() {
        if (transactionQueue.isEmpty()) {
            return null;
        }
        return transactionQueue.remove(transactionQueue.size() - 1);
    }

    /**
     * Flush current transaction queue
     */
    public void flush() {
        transactionQueue = new ArrayList<>();
    }

    /**
     * Iterates through transactions from most recent to oldest to find value
     */
    public Integer getNumOfTimesValIsPresent(String value) {

        if (transactionQueue.isEmpty())
            return 0;

        ListIterator<DataValues> itr = transactionQueue.listIterator(transactionQueue.size());

        while (itr.hasPrevious()) {
            DataValues transaction = itr.previous();
            Optional<Integer> valueCount = Optional.ofNullable(transaction.getValueCount(value));
            if (valueCount.isPresent()) {
                return valueCount.get();
            }
        }

        return 0;
    }

    /**
     * Commit transactions from oldest to most recent to create DataValues object
     */
    public DataValues commit(DataValues lastTransaction) {
        if (transactionQueue.isEmpty()) {
            return null;
        }

        Optional<DataValues> oldestTransaction = transactionQueue.stream()
                .limit(1).findFirst();
        transactionQueue.stream().skip(1).
                forEach(pt -> oldestTransaction.get().mergeTransaction(pt));

        oldestTransaction.get().mergeTransaction(lastTransaction);
        return oldestTransaction.get();
    }

    /**
     * Iterate through transactions from most recent to oldest searching for key. Returns null if not found.
     */
    public String getCurrentValForKey(String key) {

        if (transactionQueue.isEmpty())
            return null;

        ListIterator<DataValues> itr = transactionQueue.listIterator(transactionQueue.size());

        while (itr.hasPrevious()) {
            DataValues data = itr.previous();
            if (data.isKeyDeleted(key)) {
                return null;
            } else {
                Optional<String> value = Optional.ofNullable(data.getKeyValue(key));
                if (value.isPresent()) {
                    return value.get();
                }
            }
        }

        return null;
    }


}
