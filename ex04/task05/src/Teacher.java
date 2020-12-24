import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Teacher implements ActionListener {
    int numberOne, numberTwo;
    String operator = "";
    Random random;
    int maxInteger;
    JTextField textOne, textTwo, textResult;
    JLabel operatorLabel, message;

    Teacher() {
        random = new Random();
    }

    public void setMaxInteger(int n) {
        maxInteger = n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("getProblem")) {
            numberOne = random.nextInt(maxInteger) + 1;
            numberTwo = random.nextInt(maxInteger) + 1;
            double d = Math.random();
            if (d >= 0.5)
                operator = "+";
            else
                operator = "-";
            textOne.setText("" + numberOne);
            textTwo.setText("" + numberTwo);
            operatorLabel.setText(operator);
            message.setText("Please answer...");
            textResult.setText(null);
        }
        else if (str.equals("answer")) {
            String answer = textResult.getText();
            try {
                int result = Integer.parseInt(answer);
                if (operator.equals("+")) {
                    if (result == numberOne + numberTwo) {
                        message.setText("Correct");
                    }
                    else
                        message.setText("Wrong");
                }
                else if (operator.equals("-")) {
                    if (result == numberOne - numberTwo) {
                        message.setText("Correct");
                    }
                    else
                        message.setText("Wrong");
                }
            }
            catch (NumberFormatException ex) {
                message.setText("Check input");
            }
        }
    }
    public void setJTextField(JTextField ... t) {
        textOne = t[0];
        textTwo = t[1];
        textResult = t[2];
    }

    public void setJLabel(JLabel ... label) {
        operatorLabel = label[0];
        message = label[1];
    }
}
