import java.util.*;

public class DelMaxAndMin extends DoThing {
    DoThing nextDoThing;

    public void setNext(DoThing next) {
        nextDoThing = next;
    }

    public void doThing(double[] a) {
        Arrays.sort(a);
        double[] b = Arrays.copyOfRange(a, 1, a.length - 1);
        System.out.print("REMOVE highest: " + a[a.length - 1] + ",");
        System.out.println("REMOVE lowest: " + a[0]);
        nextDoThing.doThing(b);
    }
}
