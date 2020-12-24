import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputerFrame extends JFrame {
    JMenuBar menubar;
    JMenu choiceGrade;
    JMenuItem grade1,grade2;
    JTextField textOne, textTwo, textResult;
    JButton getProblem, giveAnwser;
    JLabel operatorLabel, message;
    Teacher teacherZhang;
    ComputerFrame() {
        teacherZhang = new Teacher();
        teacherZhang.setMaxInteger(20);
        setLayout(new FlowLayout());
        menubar = new JMenuBar();
        choiceGrade = new JMenu("Select Mode");
        grade1 = new JMenuItem("Very Easy");
        grade2 = new JMenuItem("Easy");
        grade1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherZhang.setMaxInteger(10);
            }
        });
        grade2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherZhang.setMaxInteger(50);
            }
        });
        choiceGrade.add(grade1);
        choiceGrade.add(grade2);
        menubar.add(choiceGrade);
        setJMenuBar(menubar);
        textOne = new JTextField(5); // TODO: init textOne -> [5]
        textTwo = new JTextField(5);
        textResult = new JTextField(5);
        operatorLabel = new JLabel("+");
        operatorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        message = new JLabel("CHK Answer");
        getProblem = new JButton("Get Problem");
        giveAnwser = new JButton("Confirm Answer");
        add(getProblem);
        add(textOne);
        add(operatorLabel);
        add(textTwo);
        add(new JLabel("="));
        add(textResult);
        add(giveAnwser);
        add(message);
        textResult.requestFocus();
        textOne.setEditable(false);
        textTwo.setEditable(false);
        getProblem.setActionCommand("getProblem");
        textResult.setActionCommand("answer");
        giveAnwser.setActionCommand("answer");
        teacherZhang.setJTextField(textOne, textTwo, textResult);
        teacherZhang.setJLabel(operatorLabel, message);
        getProblem.addActionListener(teacherZhang); // TODO: getProblem addActionEvent teacherZhang
        giveAnwser.addActionListener(teacherZhang); // TODO: giveAnswer addActionEvent teacherZhang
        textResult.addActionListener(teacherZhang); // TODO: textResult addActionEvent teacherZhang
        setVisible(true);
        validate();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
