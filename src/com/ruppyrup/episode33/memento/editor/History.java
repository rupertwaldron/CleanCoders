package com.ruppyrup.episode33.memento.editor;

import java.util.ArrayDeque;
import java.util.Deque;

public class History {
    private final Deque<EditorMemento> states = new ArrayDeque<>(4);

    public void saveState(EditorMemento editorMemento) {
        states.push(editorMemento);
    }

    public EditorMemento getPreviousState() {
        return states.pop();
    }
}
