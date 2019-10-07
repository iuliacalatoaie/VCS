package vcs;

import java.util.ArrayList;
import utils.OperationType;

public final class VcsRollback extends VcsOperation {

    public VcsRollback(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        Commit lastCommit = new Commit("name", vcs.getActiveSnapshot(), 2);
        // caut ultimul commit
        for (Branch b:vcs.getBranches()) {
            for (Commit c:b.getCommits()) {
                if (c.getId() > lastCommit.getId()) {
                    lastCommit = c;
                }
            }
        }
        // golesc staging-ul si resetez starea curenta a sistemului de fisiere
        vcs.getStaging().clear();
        vcs.setActiveSnapshot(lastCommit.getActiveSnapshot().cloneFileSystem());
        return 0;
    }


}
