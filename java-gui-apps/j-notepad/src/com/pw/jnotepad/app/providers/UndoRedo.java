package com.pw.jnotepad.app.providers;

import java.util.Stack;

public class UndoRedo  {

    private static final Stack<Command> undoStack = new Stack<>();

    private static final Stack<Command> redoStack = new Stack<>();


    public static void redo(){
        if(redoStack.isEmpty()){
            return;
        }
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }

    public static void undo(){
        if(undoStack.isEmpty()){
            return;
        }
        Command command = undoStack.pop();
        command.unexecute();
        redoStack.push(command);
    }


    public static void insertIntoUndoStack(Command command){
        undoStack.push(command);
    }

    public static void insertIntoRedoStack(Command command){
        redoStack.push(command);
    }
}
