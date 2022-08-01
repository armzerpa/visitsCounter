import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VisitCounter {
    Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null || visits.length == 0) {
            return null;
        }
        Stream<Map<String, UserStats>> stream = Arrays.stream(visits);
        List<Stat> allData = new ArrayList<>();
        stream.forEach( (v) -> {
                    Map<Long, UserStats> result = v.entrySet().stream().filter((a) -> {
                        try {
                            Long.parseLong(a.getKey());
                            return true;
                        } catch (Exception ex) {
                            return false;
                        }
                    }).filter(b -> b.getValue() != null).filter(c -> !c.getValue().visitCount.isEmpty()).collect(Collectors.toMap(
                            entry -> Long.parseLong(entry.getKey()),
                            entry -> entry.getValue()));
                    allData.addAll(result.entrySet().stream().map(m -> new Stat(m.getKey(), m.getValue().visitCount.get())).collect(Collectors.toList()));
                }
        );
        System.out.println(allData);
        Map<Long, Long> result = allData.stream()
                .collect(Collectors.groupingBy(s -> s.userId, Collectors.summingLong(s -> s.visits)));

        return result;
    }
}
