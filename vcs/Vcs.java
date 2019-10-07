package vcs;

import java.util.ArrayList;
import java.util.List;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.AbstractOperation;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    // un vcs are branch-uri, staging si head
    private List<Branch> branches;
    private List<AbstractOperation> staging;
      private int head;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initializations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        this.branches = new ArrayList<>();
        // primul branch este master
        this.branches.add(new Branch("master",
                new Commit(" First commit", this.activeSnapshot.cloneFileSystem())));
        this.staging = new ArrayList<>();
        // head-ul pointeaza la primul commit
        this.head = this.branches.get(0).getCommits().get(0).getId();
    }

  public OutputWriter getOutputWriter() {
        return this.outputWriter;
       }

    public FileSystemSnapshot getActiveSnapshot() {
        return this.activeSnapshot;
    }

    public List<Branch> getBranches() {
        return this.branches;
    }

    public void addBranch(final Branch branch) {
        this.branches.add(branch);
    }

    public int getHead() {
        return head;
    }

    public void setHead(final int head) {
        this.head = head;
    }

    public void setActiveSnapshot(final FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

  public List<AbstractOperation> getStaging() {
        return staging;
    }

    public void addOnStaging(final AbstractOperation operation) {
        this.staging.add(operation);
    }

    public void clearStaging() {
        this.staging.clear();
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }
}
