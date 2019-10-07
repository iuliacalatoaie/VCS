package vcs;

import java.util.ArrayList;

import utils.AbstractOperation;
import utils.OperationType;

public final class VcsStatus extends VcsOperation {

    public VcsStatus(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // caut branch-ul curent
        Branch currentBranch = new Branch("owner", new Commit("nume",
                vcs.getActiveSnapshot().cloneFileSystem()));
        for (Branch b:vcs.getBranches()) {
            for (Commit c:b.getCommits()) {
                if (vcs.getHead() == c.getId()) {
                    currentBranch = b;
                    break;
                }
            }
        }
        // afisez mesajul corespunzator in functie de tip
        vcs.getOutputWriter().write("On branch: ");
        vcs.getOutputWriter().write(currentBranch.getOwner() + "\n");
        vcs.getOutputWriter().write("Staged changes:\n");
        if (!vcs.getStaging().isEmpty()) {
            for (AbstractOperation command:vcs.getStaging()) {
                vcs.getOutputWriter().write("\t");
                if (command.getType().toString().equals("REMOVE")) {
                    vcs.getOutputWriter().write("Removed ");
                    vcs.getOutputWriter().write(
                            command.getOperationArgs().get(1).toString());
                }
                if (command.getType().toString().equals("TOUCH")) {
                    vcs.getOutputWriter().write("Created file ");
                    vcs.getOutputWriter().write(
                            command.getOperationArgs().get(1).toString());
                }
                if (command.getType().toString().equals("WRITETOFILE")) {
                    vcs.getOutputWriter().write("Added ");
                    vcs.getOutputWriter().write('"'
                            + command.getOperationArgs().get(1).toString()
                            + '"' + " to file "
                            + command.getOperationArgs().get(0).toString());
                }
                if (command.getType().toString().equals("MAKEDIR")) {
                    vcs.getOutputWriter().write("Created directory "
                            + command.getOperationArgs().get(1));
                }
                if (command.getType().toString().equals("CHANGEDIR")) {
                    vcs.getOutputWriter().write("Changed directory ");
                    vcs.getOutputWriter().write(
                            command.getOperationArgs().get(1).toString());
                }
                vcs.getOutputWriter().write("\n");
            }
        }
        return 0;
    }

}
