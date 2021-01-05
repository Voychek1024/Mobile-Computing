import java.util.Arrays;

public class ComputerAver extends DoThing {
    DoThing nextDoThing;

    public void setNext(DoThing next) {
        nextDoThing = next;
    }

    public void doThing(double[] a) {
        double sum = 0;
        for (double v : a)
            sum = sum + v;
        double aver = sum / a.length;
        System.out.print("Final Score: " + aver);
    }
}
