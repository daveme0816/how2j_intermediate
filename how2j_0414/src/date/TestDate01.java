package date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        Date date = new Date();
        String str = sdf.format(date);
        System.out.println(str);
    }
}
