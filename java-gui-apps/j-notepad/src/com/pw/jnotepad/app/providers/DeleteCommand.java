package com.pw.jnotepad.app.providers;

import javafx.scene.control.TextArea;

public class DeleteCommand implements Command {

    private int startIndex;

    private String content;

    private TextArea textArea;

    public DeleteCommand(int startIndex, String content, TextArea textArea){
        this.startIndex = startIndex;
        this.content = content;
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        this.textArea.deleteText(startIndex, startIndex+content.length());
    }

    @Override
    public void unexecute() {
        this.textArea.insertText(startIndex, content);
    }
}
