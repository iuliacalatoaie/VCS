package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class VcsBranch extends VcsOperation {

    public VcsBranch(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        Commit lastCommit = new Commit("nume", vcs.getActiveSnapshot());
        // daca numele branch-ului exista, intorc mesajul de eruare
        for (Branch b:vcs.getBranches()) {
            if (this.getOperationArgs().get(0).equals(b.getOwner())) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        // retin numele noului branch
        String branchName = this.getOperationArgs().get(0);
        // caut commit-ul pointat de head
        for (Branch b:vcs.getBranches()) {
            for (Commit c:b.getCommits()) {
                if (c.getId() == vcs.getHead()) {
                    lastCommit = c;
                    break;
                }
            }
        }
        vcs.addBranch(new Branch(branchName, new Commit(lastCommit.getMessage(),
                lastCommit.getActiveSnapshot().cloneFileSystem())));
        return 0;
    }

}
