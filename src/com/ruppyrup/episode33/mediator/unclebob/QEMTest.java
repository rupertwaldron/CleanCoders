package com.ruppyrup.episode33.mediator.unclebob;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QEMTest {

  private static JScrollPane scrollingList;

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("QuickEntryMediator");
    frame.setSize(400, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel(new FlowLayout());
    frame.add(panel);

    JTextField textField = new JTextField(30);
    textField.setSize(100,30);
    textField.setVisible(true);
    panel.add(textField);

    List<Object> words = makeWords(); // gets the dictionary from the dict directory
    JList list = new JList(words.toArray());

    scrollingList = new JScrollPane(list);
    scrollingList.setPreferredSize(new Dimension(300, 400));
    panel.add(scrollingList);

    QuickEntryMediator mediator = new QuickEntryMediator(textField, list);
    frame.setVisible(true);
  }

  private static List<Object> makeWords() throws IOException {
    List words = Files.readAllLines(Paths.get("/usr/share/dict/words"));
    return words;
  }
}
