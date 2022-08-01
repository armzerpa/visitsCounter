import java.util.*;

public class VisitCounterTest {

    void testCount_whenNull() {
        VisitCounter vc = new VisitCounter();

        //case null
        Map<Long, Long> result = vc.count(null);
        assert result == null;
    }
    void testCount() {
        VisitCounter vc = new VisitCounter();

        //case data
        Map<String, UserStats> m1 = new HashMap<>();
        m1.put("111", new UserStats(Optional.of(1L)));
        m1.put("222", new UserStats(Optional.of(2L)));

        Map<String, UserStats> m2 = new HashMap<>();
        m2.put("xxx", new UserStats(Optional.of(2L)));
        m2.put("777", new UserStats(Optional.of(7L)));

        Map<String, UserStats> m3 = new HashMap<>();
        m3.put("444", new UserStats(Optional.ofNullable(null)));
        m3.put("333", new UserStats(Optional.of(3L)));

        Map<String, UserStats> m4 = new HashMap<>();
        m4.put("123", new UserStats(Optional.empty()));
        m4.put("456", new UserStats(Optional.of(8L)));

        Map<String, UserStats> m5 = new HashMap<>();
        m5.put("123", null);
        m5.put("777", new UserStats(Optional.of(3L)));
        m5.put("876", new UserStats(Optional.of(22L)));

        Map<Long, Long> result = vc.count(m1, m2, m3, m4, m5);
        assert result != null;
        assert result.size() == 6;
    }
}
