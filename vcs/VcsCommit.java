package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class VcsCommit extends VcsOperation {

    public VcsCommit(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // daca staging-ul este gol, atunci returnez eruare
        if (vcs.getStaging().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        for (Branch b:vcs.getBranches()) {
            for (Commit c:b.getCommits()) {
                if (vcs.getHead() == c.getId()) {
                    // adaug noul commit in branch si golesc staging-ul
                    this.getOperationArgs().remove(0);
                    String newCommitName = new String();
                    // formez mesajul commit-ului, il adaug,
                    // setez head-ul si golesc staging-ul
                    for (String s:this.getOperationArgs()) {
                        newCommitName += (" " + s);
                    }
                    b.addCommit(new Commit(newCommitName,
                            vcs.getActiveSnapshot().cloneFileSystem()));
                    vcs.setHead(
                    b.getCommits().get(b.getCommits().size() - 1).getId());
                    vcs.clearStaging();
                    return 0;
                }
            }
        }
        return 0;
    }
}
