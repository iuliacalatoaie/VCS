package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class InvalidVcsOperation extends VcsOperation {

    public InvalidVcsOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // daca operatia este invalida se returneaza doar codul de eruare
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
