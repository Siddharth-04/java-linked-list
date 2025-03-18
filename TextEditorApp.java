class TextState {
    String content;
    TextState next, prev;

    public TextState(String content) {
        this.content = content;
        this.next = this.prev = null;
    }
}

class TextEditor {
    private TextState head, tail, current;
    private int historySize;
    private int currentSize;

    public TextEditor(int historySize) {
        this.historySize = historySize;
        this.currentSize = 0;
    }

    public void addTextState(String content) {
        TextState newState = new TextState(content);

        if (current != null) {
            current.next = newState;
            newState.prev = current;
        }

        current = newState;

        if (head == null) {
            head = tail = current;
        } else {
            tail = current;
        }

        if (currentSize < historySize) {
            currentSize++;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No content available.");
        }
    }
}

public class TextEditorApp {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);

        editor.addTextState("Hello");
        editor.addTextState("Hello, World!");
        editor.addTextState("Hello, World! This is a test.");

        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
    }
}
//Current State: Hello, World! This is a test.
//Current State: Hello, World!
//Current State: Hello, World! This is a test.