public class RandomString {
    String string = "";
    public String getRandomString(String s) {
        StringBuffer stringBuffer = new StringBuffer(s);
        int m = stringBuffer.length();
        for (int k = 0; k < m; k++) {
            int index = (int) (Math.random() * stringBuffer.length());
            char c = stringBuffer.charAt(index);
            string += c;
            stringBuffer = stringBuffer.deleteCharAt(index);
        }
        return string;
    }
}
