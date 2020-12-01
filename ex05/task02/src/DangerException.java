public class DangerException extends Exception {
    String message;
    public DangerException() {
        message = "DANGEROUS!";
    }
    public void toShow() {
        System.out.print(message + " ");
    }
}
