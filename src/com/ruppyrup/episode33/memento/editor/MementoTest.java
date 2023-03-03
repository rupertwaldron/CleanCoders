package com.ruppyrup.episode33.memento.editor;

import org.junit.jupiter.api.Assertions;

public class MementoTest {
    public static void main(String[] args) {
        var editor = new Editor();
        var history = new History();
        editor.setContent("a");
        editor.setSize(7);
        System.out.println(editor);
        Assertions.assertEquals("a", editor.getContent());
        history.saveState(editor.getMemento());
        editor.setContent("b");
        editor.setSize(8);
        System.out.println(editor);
        history.saveState(editor.getMemento());
        editor.setContent("c");
        editor.setSize(9);
        System.out.println(editor);
        history.saveState(editor.getMemento());
        editor.restore(history.getPreviousState());
        System.out.println(editor);
        editor.restore(history.getPreviousState());
        System.out.println(editor);
        editor.restore(history.getPreviousState());
        System.out.println(editor);
    }
}
