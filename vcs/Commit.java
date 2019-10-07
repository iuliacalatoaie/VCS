package vcs;

import utils.IDGenerator;
import filesystem.FileSystemSnapshot;

public final class Commit {
    private String message;
    private int id;
    private FileSystemSnapshot activeSnapshot;

    public Commit(final String message, final FileSystemSnapshot activeSnapshot) {
        this.message = message;
        this.activeSnapshot = activeSnapshot;
        this.id = IDGenerator.generateCommitID();
    }
    public Commit(final String message, final FileSystemSnapshot activeSnapshot, final int id) {
        this.message = message;
        this.activeSnapshot = activeSnapshot;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return this.message;
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return this.activeSnapshot;
    }
}
