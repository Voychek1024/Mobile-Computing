import java.util.*;

public class DoInput extends DoThing {
    DoThing nextDoThing;

    public void setNext(DoThing next) {
        nextDoThing = next;
    }

    public void doThing(double[] a) {
        System.out.println("Input Number of Judges: ");
        Scanner read = new Scanner(System.in);
        int count = read.nextInt();
        System.out.println("Input Score Array: ");
        a = new double[count];
        for (int i = 0; i < count; i++) {
            a[i] = read.nextDouble();
        }
        nextDoThing.doThing(a);
    }
}
