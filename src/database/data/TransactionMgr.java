package database.data;

import java.util.ArrayList;
import java.util.List;
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
     * Commit transactions from oldest to most recent to create DataValues object
     *
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
     * Iterate through transactions from most recent to oldest searching for key. Will return null if not found.
     *
     */
    public String getCurrentValForKey(String key) {
        for (int i = transactionQueue.size() - 1; i >= 0; i--) {
            DataValues transaction = transactionQueue.get(i);
            if (transaction.isKeyDeleted(key)) {
                return null;
            } else {
                String value = transaction.getKeyValue(key);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * Iterates through transactions from most recent to oldest to find value
     *
     */
    public Integer getNumOfTimesValIsPresent(String value) {
        for (int i = transactionQueue.size() - 1; i >= 0; i--) {
            DataValues transaction = transactionQueue.get(i);
            Integer valueCount = transaction.getValueCount(value);
            if (valueCount != null) {
                return valueCount;
            }
        }
        return 0;
    }

    /**
     * Resets currently open transactions
     */
    public void flush() {
        transactionQueue = new ArrayList<>();
    }
}
