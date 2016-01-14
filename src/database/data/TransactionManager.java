package database.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Maintains the list of transactions
 *
 */
public class TransactionManager {
    private List<DataValues> previousTransactions = new ArrayList<DataValues>();

    public TransactionManager() {
    }

    public DataValues begin(DataValues oldTransaction) {
        previousTransactions.add(oldTransaction);
        return new DataValues();
    }

    public DataValues rollback() {
        if (previousTransactions.size() == 0) {
            return null;
        }
        return previousTransactions.remove(previousTransactions.size() - 1);
    }

    /**
     * The transactions are commit one by one, from the oldest to the newest to create a single DataValues object
     * @param lastTransaction
     * @return
     */
    public DataValues commit(DataValues lastTransaction) {
        if (previousTransactions.size() == 0) {
            return null;
        }

        Optional<DataValues> oldestTransaction = previousTransactions.stream().findFirst();
        for (int i = 1; i < previousTransactions.size(); i++) {
            DataValues transactionToBeMerged = previousTransactions.get(i);
            oldestTransaction.get().mergeTransaction(transactionToBeMerged);
        }

        oldestTransaction.get().mergeTransaction(lastTransaction);
        return oldestTransaction.get();
    }

    /**
     * Iterates trough the transactions from newest to oldest until if finds the key. If it's not present the will return null.
     * @param key
     * @return
     */
    public String getMostRecentValueForKey(String key) {
        for (int i = previousTransactions.size() - 1; i >= 0 ; i--) {
            DataValues transaction = previousTransactions.get(i);
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
     * Iterates trough all the transactions from newest to oldest to find the occurrences of the value.
     * @param value
     * @return
     */
    public Integer getOccurrencesForValue(String value) {
        for (int i = previousTransactions.size() - 1; i >= 0 ; i--) {
            DataValues transaction = previousTransactions.get(i);
            Integer valueCount = transaction.getValueCount(value);
            if (valueCount != null) {
                return valueCount;
            }
        }
        return 0;
    }

    /**
     * Method used to reset the current opened transactions. Used after commit
     */
    public void cleanOldTransactions() {
        previousTransactions = new ArrayList<DataValues>();
    }
}
