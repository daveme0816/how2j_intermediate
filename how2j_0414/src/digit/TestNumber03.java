package digit;

public class TestNumber03 {
    public static void main(String[] args) {

        double e = 0;

        for (int i = 1; i < 99999 ; i++) {
            e = Math.pow((1 + (1d/i)),i);

        }

        System.out.println(e);
        System.out.println(Math.E);
    }
}
