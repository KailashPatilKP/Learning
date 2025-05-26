package CustomStreamAPI;

import java.util.*;
import java.util.function.Predicate;

public interface CustomStream<T> {
    <R, A> R collect(CustomCollector<? super T, A, R> collector);
    static <T> CustomStream<T> of(List<T> list) {
        return new CustomStreamImpl<>(list);
    }

    CustomStream<T> filter(Predicate<? super T> predicate);
}
