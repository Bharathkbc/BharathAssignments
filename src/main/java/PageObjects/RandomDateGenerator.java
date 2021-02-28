package PageObjects;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {
    public int getDayNumber(Date rdate) {
        Calendar c = Calendar.getInstance();
        c.setTime(rdate);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public LocalDate Dategeneration(long start, long end) {

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);}
}