package com.pw.jnotepad.app.providers;

import javafx.scene.control.TextArea;

public class InsertCommand implements Command{

    private int startIndex;

    private String content;

    private TextArea textArea;

    public InsertCommand(int startIndex, String content, TextArea textArea){
        this.startIndex = startIndex;
        this.content = content;
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        textArea.insertText(startIndex, content);
    }

    @Override
    public void unexecute() {
        textArea.deleteText(startIndex, startIndex+content.length());
    }
}
