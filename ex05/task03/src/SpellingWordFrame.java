import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpellingWordFrame extends JFrame implements KeyListener, ActionListener {
    JTextField input_word;
    JButton button;
    LetterLabel label[];
    JPanel northP, centerP;
    Box wordBox;
    String hintMessage = "CLICK AND SWAP BY ARROW";
    JLabel messageLabel = new JLabel(hintMessage);
    String word = "";
    SpellingWordFrame() {
        input_word = new JTextField(12);
        button = new JButton("Confirm");
        button.addActionListener(this);
        northP = new JPanel();
        northP.add(new JLabel("Input Word: "));
        northP.add(input_word);
        northP.add(button);
        centerP = new JPanel();
        wordBox = Box.createHorizontalBox();
        centerP.add(wordBox);
        add(northP, BorderLayout.NORTH);
        add(centerP, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
        setBounds(100, 100, 350, 180);
        setVisible(true);
        validate();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        word = input_word.getText();
        int n = word.length();
        RandomString randomString = new RandomString();
        String randomWord = randomString.getRandomString(word);
        wordBox.removeAll();
        messageLabel.setText(hintMessage);
        if (n > 0) {
            label = LetterLabel.getLetterLabel(n);
            for (int k = 0; k < label.length; k++) {
                label[k].setText("" + randomWord.charAt(k));
                wordBox.add(label[k]);
                label[k].addKeyListener(this); // TODO: 将当前窗口注册为label[k]的键盘监视器
            }
                validate();
                input_word.setText(null);
                label[0].requestFocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        LetterLabel sourceLabel = (LetterLabel) e.getSource();
        int index = -1;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            for (int k = 0; k < label.length; k++) {
                if (label[k] == sourceLabel) {
                    index = k;
                    break;
                }
            }
            if (index != 0) {
                String temp = label[index].getText();
                label[index].setText(label[index - 1].getText());
                label[index - 1].setText(temp);
                label[index - 1].requestFocus();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // TODO: KeyEvent.VK_RIGHT
            for (int k = 0; k < label.length; k++) {
                if (label[k] == sourceLabel) {
                    index = k;
                    break;
                }
            }
            if (index != label.length - 1) {
                String temp = label[index].getText();
                label[index].setText(label[index + 1].getText());
                label[index + 1].setText(temp);
                label[index + 1].requestFocus();
            }
        }
        validate();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String success = "";
        for (LetterLabel letterLabel : label) {
            String str = letterLabel.getText();
            success += str;
        }
        if (success.equals(word)) {
            messageLabel.setText("CONGRATULATES");
            for (LetterLabel letterLabel : label) {
                letterLabel.removeKeyListener(this);
                letterLabel.removeFocusListener(letterLabel);
                letterLabel.setBackground(Color.white);
            }
            input_word.requestFocus();
        }
    }
}
