package com.pw.jnotepad.app.providers;

public interface Command {

    void execute();

    void unexecute();

}
