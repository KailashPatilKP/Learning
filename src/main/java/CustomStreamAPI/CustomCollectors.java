package CustomStreamAPI;

import java.util.*;
import java.util.function.*;

public class CustomCollectors {
    public static <T, K> CustomCollector<T, Map<K, Double>, Map<K, Double>> groupingBy(
            Function<? super T, ? extends K> classifier,
            ToDoubleFunction<? super T> mapper) {
        return new CustomCollector<>() {
            @Override
            public Supplier<Map<K, Double>> supplier() {
                return HashMap::new;
            }

            @Override
            public BiConsumer<Map<K, Double>, T> accumulator() {
                return (map, element) -> {
                    K key = classifier.apply(element);
                    Double value = map.getOrDefault(key, 0.0);
                    map.put(key, value + mapper.applyAsDouble(element));
                };
            }

            @Override
            public Function<Map<K, Double>, Map<K, Double>> finisher() {
                return Function.identity();
            }
        };
    }
}
