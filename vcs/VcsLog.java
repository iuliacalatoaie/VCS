package vcs;

import java.util.ArrayList;

import utils.OperationType;

public final class VcsLog extends VcsOperation {

    public VcsLog(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        int element = 0;
        for (Branch b:vcs.getBranches()) {
            // caut commit-ul la care e head
            for (Commit c:b.getCommits()) {
                if (c.getId() == vcs.getHead()) {
                    // afiseaz mesajul si id-ul pentru commit-urile de pe branch
                    for (Commit print:b.getCommits()) {
                        ++element;
                        vcs.getOutputWriter().write(
                        "Commit id: " + print.getId() + "\n");
                        vcs.getOutputWriter().write(
                        "Message:" + print.getMessage() + "\n");
                        if (element != b.getCommits().size()) {
                            vcs.getOutputWriter().write("\n");
                        }
                    }
                    break;
                }
            }
        }
        return 0;
    }

}
