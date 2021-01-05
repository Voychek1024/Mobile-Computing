import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

public class StudentFrame extends JFrame implements ActionListener {
    JTextArea showArea;
    JTextField inputName, inputScore;
    JButton button;
    TreeSet<Student> treeSet;

    StudentFrame() {
        treeSet = new TreeSet<Student>(); //TODO: Construct treeSet without params
        showArea = new JTextArea();
        showArea.setFont(new Font("", Font.BOLD, 20));
        inputName = new JTextField(5);
        inputScore = new JTextField(5);
        button = new JButton("Confirm");
        button.addActionListener(this);
        JPanel pNorth = new JPanel();
        pNorth.add(new JLabel("Name:"));
        pNorth.add(inputName);
        pNorth.add(new JLabel("Score:"));
        pNorth.add(inputScore);
        pNorth.add(button);
        add(pNorth, BorderLayout.NORTH);
        add(showArea, BorderLayout.CENTER);
        setSize(360, 360);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = inputName.getText();
        int score = 0;
        try {
            score = Integer.parseInt(inputScore.getText().trim());
            if (name.length() > 0) {
                Student stu = new Student(name, score);
                treeSet.add(stu); //TODO: insert stu to treeSet
                show(treeSet);
            }
        } catch (NumberFormatException exp) {
            inputScore.setText("Please input NUMBER");
        }
    }

    public void show(TreeSet tree) {
        showArea.setText(null);
        for (Student stu : treeSet) {
            showArea.append("Name:" + stu.getName() + " Score:" + stu.getScore() + "\n");
        }
    }

    public static void main(String[] args) {
        new StudentFrame();
    }
}
