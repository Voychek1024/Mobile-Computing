public class Student implements Comparable {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {

        Student st = (Student) o;
        int m = this.score - st.score;
        if (m != 0)
            return m;
        else
            return 1;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
