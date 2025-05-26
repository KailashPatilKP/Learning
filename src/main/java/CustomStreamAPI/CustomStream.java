package CustomStreamAPI;

import java.util.*;

public interface CustomStream<T> {
    <R, A> R collect(CustomCollector<? super T, A, R> collector);
    static <T> CustomStream<T> of(List<T> list) {
        return new CustomStreamImpl<>(list);
    }
}
