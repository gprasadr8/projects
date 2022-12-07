
### cut, copy and paste:

In human–computer interaction and user interface design, cut, copy and paste are related commands that offer an inter process communication technique for transferring data through a computer's user interface. <br>
The cut command removes the selected data from its original position, while the copy command creates a duplicate; in both cases the selected data is kept in temporary storage (the clipboard). <br>
The data from the [clipboard](https://en.wikipedia.org/wiki/Clipboard_%28computing%29) is later inserted wherever a paste command is issued. <br> 
The data remains available to any application supporting the feature, thus allowing easy data transfer between applications. <br>

[Source Wikipedia](https://en.wikipedia.org/wiki/Cut,_copy,_and_paste)

### clipboard:
The clipboard is a buffer that some operating systems provide for short-term storage and transfer within and between application programs. <br>
The clipboard is usually temporary and unnamed, and its contents reside in the computer's RAM.<br>
The clipboard is sometimes called the paste buffer.

**Additional Info:** <br>
An operating system that supports a clipboard provides an application programming interface by which programs can specify cut, copy and paste operations.<br> 
It is left to the program to define methods for the user to command these operations, which may include keybindings and menu selections.<br>
When an element is copied or cut, the clipboard must store enough information to enable a sensible result no matter where the element is pasted.<br>
Application programs may extend the clipboard functions that the operating system provides. A [clipboard manager](https://en.wikipedia.org/wiki/Clipboard_manager) may give the user additional control over the clipboard.<br>

[Source](https://en.wikipedia.org/wiki/Clipboard_%28computing%29)

### How to get System Clipboard in javafx?

Represents an operating system clipboard, on which data may be placed during cut, copy, and paste operations.<br>
To access the general system clipboard, use the following code:

``Clipboard clipboard = Clipboard.getSystemClipboard();``

There is only ever one instance of the system clipboard in the application, so it is perfectly acceptable to stash a reference to it somewhere handy if you so choose.

Content is specified on the Clipboard by using the setContent(java.util.Map<javafx.scene.input.DataFormat, java.lang.Object>) method. First, construct a ClipboardContent object, then invoke setContent. Every time setContent is called, any previous data on the clipboard is cleared and replaced with this new content.

```
     final Clipboard clipboard = Clipboard.getSystemClipboard();
     final ClipboardContent content = new ClipboardContent();
     content.putString("Some text");
     content.putHtml("<b>Some</b> text");
     clipboard.setContent(content);

```
[Source](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/Clipboard.html)

### undo and redo:
**Undo** is an interaction technique which is implemented in many computer programs. <br>
It erases the last change done to the document, reverting it to an older state. <br>
In some more advanced programs, such as graphic processing, undo will negate the last command done to the file being edited.<br>

**Redo:** The opposite of undo is Redo. The redo command reverses the undo or advances the buffer to a more recent state. <br>

The common components of Undo functionality are the commands which were executed of the user, the history buffer(s) which stores the completed actions, the undo/redo manager for controlling the history buffer, and the user interface for interacting with the user

Undo can be implemented through different patterns. The most common patterns are Command pattern and Memento pattern.

#### Command Pattern
The command pattern is a software design pattern which encapsulates information from the operation into command objects. This means that every action is stored in an object. The abstract command class implements an abstract execute operation, so every command object has an execute operation. For undo there also have to be unexecuted operation, which undoes the effect of the executed command, which are stored in a history list. Undo and redo are implemented so that the list is run through forwards and backwards when the execute or unexecute command is called.[13]

For single undo only the executed command is stored. In contrast to the multi level undo where not only the history list with the commands is saved but also the number of undo levels can be determined of the maximum length of the list.[13]

#### Memento Pattern
With Memento pattern the internal state of an object is stored. The object in which the state is saved, is called memento and is organized through the memento originator. This returns a memento, initialized with information of the current state, when undo is executed, so that the state can be checked. The memento is only visible for the originator.

In memento pattern the undo mechanism is called caretaker. It is responsible for the safekeeping of the mementos but never change the contents of these. For undo the caretaker requests a memento of the originator and then applying the undo.[13]

The most part of undo mechanism can implemented without dependency to specific applications or command classes. This includes "the management of history list, the history scroller, menu entries for undo and redo and update of the menu entries depending on the name of the next available command."[1]

Every command class has a do method which is called when a command is executed. The undo-method implements the reverse operation of the do-method. To implement the reverse, there are several different strategies.

- full checkpoint: That means that the complete state is saved after a command is executed. This is the easiest implementation, but is not highly efficient and therefore not often used.
- complete rerun: Therefore, the initial state is saved and every state in the history list can be reached through "starting with the initial state and redoing all commands from the beginning of the history."[1]
- partial checkpoint: This is the most used strategy. The changed application state is saved and with undo the part of the state is set back to the forward value.
- inverse function: Inverse function needs no saved state information. "For example, moving can be reversed by moving the object back by relative amount."[1] For selective undo there is not enough information for saving the state.


[Source](https://en.wikipedia.org/wiki/Undo)


#### new notes for undo redo:

As we know, an application changes its state after every operation. As an application is operated, it changes its state. So if someone would like to do undo he has to go the previous state. To enable going to the previous state, we need to store the states of the application while it runs. To support redo, we have to go to the next state from the present state.

To implement Undo/Redo we have to store the states of the application and go to the previous state for undo and to the next state for redo. So we have to maintain the states of the application to support Undo/Redo.


To maintain the states of an application in all the three approaches we uses two stacks, one stack contains the states for undo operation. The second stack contains the states for redo operation. Undo operation pops the undo stack to get the previous state and sets the previous state to the application. In the same way, redo operation pops the redo stack to get the next state and sets the next state to the application.


Now we know Implementing Undo Redo operation is all about keeping state after each operation of the application. Now the question is how this approach keeps state? In Command pattern, we keep the changes of a single operation in an ICommand object which is intended for this particular type of operation as state.

Now we know Implementing Undo Redo operation is all about keeping state after each operation of the application. Now the question is how this approach keeps state? In Command pattern, we keep the changes of a single operation in an ICommand object which is intended for this particular type of operation as state.

```java
interface Command
   {
       void Execute();
       void UnExecute();
   }

```

Step 3
Then make a class named UndoRedo which contains two stacks. The first one is for Undo operation and the second one is for Redo operation. This class implements Undo method, Redo method and a number of InsertInUnDoRedo methods to insert ICommand object in Undo/Redo system. When InsertInUnDoRedo method is called, then the ICommand object is inserted into Undo stack to make the operation Undo/Redo Enable and clear the Redostack.

In Undo operation:

First check whether UndoStack is empty or not. If empty, then return otherwise proceed.
Then pop an ICommand object from the UndoStack.
Then push this command to RedoStack.
Then invoke the Unexecute method of the  I<code>command object.
In Redo operation:

First check whether RedoStack is empty or not. If empty, then return otherwise proceed.
Then  pop an Icommand object  from the RedoStack.
Then push this command to Undostack.
Then invoke the execute method of the Icommand object. 




### simple notes for command patterns:

The main motivation for using the Command pattern is that the executor of the command does not need to know anything at all about what the command is, what context information it needs on or what it does. All of that is encapsulated in the command.

This allows you to do things such as have a list of commands that are executed in order, that are dependent on other items, that are assigned to some triggering event etc.

So, in summary, the pattern encapsulates everything required to take an action and allows the execution of the action to occur completely independently of any of that context. If that is not a requirement for you then the pattern is probably not helpful for your problem space.


Let us focus on the non-implementation aspect of the command design, and some main reasons for using the Command desing pattern grouped in two major categories:

Hiding actual implementation of how the command is executed
Allow methods to be built around command, aka command extensions





