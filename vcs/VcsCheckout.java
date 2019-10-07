package vcs;

import java.util.ArrayList;
import java.util.List;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class VcsCheckout extends VcsOperation {

    public VcsCheckout(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // daca staging-ul nu e gol, intorc eruare
        if (!vcs.getStaging().isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        boolean found = false;
        List<Commit> keepCommits = new ArrayList<>();
        // verific primul parametru
        if (this.getOperationArgs().get(0).equals("-c")) {
            int wantedId = Integer.valueOf(this.getOperationArgs().get(1));
            for (Branch b:vcs.getBranches()) {
                for (Commit c:b.getCommits()) {
                    // pastrez commit-urile posterioare commit-ului gasit
                    if (found) {
                        keepCommits.add(c);
                    }
                    if (c.getId() == wantedId) {  // daca am gasit commit-ul
                        vcs.setHead(c.getId());  // setez head-ul
                        // setez snaphot-ul curent
                        vcs.setActiveSnapshot(
                        c.getActiveSnapshot().cloneFileSystem());
                        vcs.clearStaging();  // golesc staging-ul
                        found = true;
                    }
                }
                // daca am gasit id-ul atunci sterg commit-urile
                // cu id mai mare de pe branch-ul lui
                if (found) {
                    b.getCommits().removeAll(keepCommits);
                    break;
                }
            }
            // daca nu am gasit id-ul
            if (!found) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
        } else {
            // caut branch-ul
            for (Branch b:vcs.getBranches()) {
                if (b.getOwner().equals(this.getOperationArgs().get(0))) {
                    // setez head-ul
                    vcs.setHead(
                    b.getCommits().get(b.getCommits().size() - 1).getId());
                    found = true;
                    return 0;
                }
            }
            // daca nu am gasit branch-ul returnez codul de eruare
            if (!found) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        return 0;
    }

}
