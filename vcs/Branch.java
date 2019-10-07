package vcs;

import java.util.ArrayList;
import java.util.List;

public final class Branch {
    private List<Commit> commits;
    private String owner;

    public Branch(final String owner, final Commit commit) {
        this.setOwner(owner);
        commits = new ArrayList<>();
        commits.add(commit);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public List<Commit> getCommits() {
        return this.commits;
    }

    public void addCommit(final Commit commit) {
        this.commits.add(commit);
    }
}
