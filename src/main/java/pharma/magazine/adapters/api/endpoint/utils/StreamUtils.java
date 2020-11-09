package pharma.magazine.adapters.api.endpoint.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        return null;
                    }
                    return list.get(0);
                }
        );
    }
}
