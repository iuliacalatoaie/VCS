package utils;

import filesystem.CatOperation;
import filesystem.CdOperation;
import filesystem.LsOperation;
import filesystem.MkdirOperation;
import filesystem.RemoveOperation;
import filesystem.TouchOperation;
import filesystem.WriteToFileOperation;
import vcs.InvalidVcsOperation;
import vcs.VcsBranch;
import vcs.VcsCheckout;
import vcs.VcsCommit;
import vcs.VcsLog;
import vcs.VcsRollback;
import vcs.VcsStatus;
import filesystem.PrintOperation;
import filesystem.InvalidFileSystemOperation;

import java.util.ArrayList;

public final class OperationFactory {
    private static OperationFactory instance = null;

    private OperationFactory() {
    }

    /**
     * Gets the instance.
     *
     * @return the instance
     */
    public static OperationFactory getInstance() {
        if (instance == null) {
            instance = new OperationFactory();
        }

        return instance;
    }

    /**
     * Creates a custom operation.
     *
     * @param type the operation type
     * @param args the operation arguments
     * @return the operation
     */
    public AbstractOperation createOperation(OperationType type, ArrayList<String> args) {
        switch (type) {
            case CAT:
                return new CatOperation(type, args);
            case CHANGEDIR:
                return new CdOperation(type, args);
            case LIST:
                return new LsOperation(type, args);
            case MAKEDIR:
                return new MkdirOperation(type, args);
            case REMOVE:
                return new RemoveOperation(type, args);
            case TOUCH:
                return new TouchOperation(type, args);
            case WRITETOFILE:
                return new WriteToFileOperation(type, args);
            case PRINT:
                return new PrintOperation(type, args);
            case FILESYSTEM_INVALID_OPERATION:
                return new InvalidFileSystemOperation(type, args);
            // operatiile vcs
            case STATUS:
                return new VcsStatus(type, args);
            case LOG:
                return new VcsLog(type, args);
            case CHECKOUT:
                return new VcsCheckout(type, args);
            case BRANCH:
                return new VcsBranch(type, args);
            case ROLLBACK:
                return new VcsRollback(type, args);
            case COMMIT:
                return new VcsCommit(type, args);
            case VCS_INVALID_OPERATION:
                return new InvalidVcsOperation(type, args);
            default:
                return null;
        }
    }
}
