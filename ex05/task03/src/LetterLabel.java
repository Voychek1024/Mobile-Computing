import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LetterLabel extends JTextField implements FocusListener {
    LetterLabel() {
        setEditable(false);
        // TODO: register this to self FOCUS
        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 30));
    }
    public static LetterLabel[] getLetterLabel(int n) {
        LetterLabel[] a = new LetterLabel[n];
        for (int k = 0; k < a.length; k++)
            a[k] = new LetterLabel();
        return a;
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.cyan);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(Color.white);
    }
    public void setText(char c) {
        setText("" + c);
    }
}
