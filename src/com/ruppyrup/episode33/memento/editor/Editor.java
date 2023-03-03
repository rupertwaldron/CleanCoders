package com.ruppyrup.episode33.memento.editor;

public class Editor {
  /**
   * This is used to encopass the state of the object - not that necessary
   */
  private EditorState state;

  public String getContent() {
    return state.content;
  }

  public void setSize(int size) {
    state.size = size;
  }

  public void setContent(String content) {
    state.content = content;
  }

  public Editor() {
    this.state = new EditorState();
  }

  public EditorMemento getMemento() {
    return new EditorMementoImpl(state);
  }

  public void restore(EditorMemento memento) {
    state = ((EditorMementoImpl)memento).state();
  }

  @Override
  public String toString() {
    return "Editor{" +
        "state=" + state +
        '}';
  }

  // Private inner class as object should be opacque to the rest of the system
  private record EditorMementoImpl(EditorState state) implements EditorMemento {

      private EditorMementoImpl(final EditorState state) {
        this.state = new EditorState(state);
      }
    }

  public static class EditorState {

    private String content;
    private int size;

    public EditorState(final EditorState state) {
      content = state.content;
      size = state.size;
    }

    public EditorState() {
    }

    @Override
    public String toString() {
      return "EditorState{" +
          "content='" + content + '\'' +
          ", size=" + size +
          '}';
    }

  }
}

