package com.ruppyrup.episode33.mediator.unclebob;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
QuickEntryMediator.  This class takes a JTextField and a JList.
It assumes that the user will type characters into the JTextField
that are prefixes of entries in the JList.  It automatically selects
the first item in the JList that matches the current prefix in the
JTextField.

If the JTextField is null, or the prefix does not match any element
in the JList, then the JList selection is cleared.

There are no methods to call for this object.  You simply create it,
and forget it.  (But don't let it be garbage collected...)

Example:

JTextField t = new JTextField();
JList l = new JList();

QuickEntryMediator qem = new QuickEntryMediator(t, l);
 // that's all folks.

 @author Robert C. Martin, Robert S. Koss
 @date 30 Jun, 1999 2113 (SLAC)
 */

public class QuickEntryMediator {

  private JTextField itsTextField;
  private JList itsList;
  public QuickEntryMediator(JTextField t, JList l) {
    itsTextField = t;
    itsList = l;

    // Add listeners which call textFieldChanged method
    itsTextField.getDocument().addDocumentListener(
      new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {
          textFieldChanged();
        }

        public void insertUpdate(DocumentEvent e) {
          textFieldChanged();
        }

        public void removeUpdate(DocumentEvent e) {
          textFieldChanged();
        }
      } // new DocumentListener
    ); // addDocumentListener
  } // QuickEntryMediator()

  // Gets the text of the text field
  private void textFieldChanged() {
    String prefix = itsTextField.getText();

    if (prefix.length() == 0) {
      itsList.clearSelection();
      return;
    }

    ListModel m = itsList.getModel();
    boolean found = false;
    for (int i = 0; !found && i < m.getSize(); i++) {
      Object o = m.getElementAt(i);
      String s = o.toString();
      if (s.startsWith(prefix)) { // looks for start element set found to true
        System.out.println(s);
        itsList.setSelectedValue(o, true);
        found = true;
      }
    }

    if (!found) {
      itsList.clearSelection();
    }
  } // textFieldChanged


} // class QuickEntryMediator