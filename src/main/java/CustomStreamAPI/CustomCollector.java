package CustomStreamAPI;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface CustomCollector<T, A, R> {

    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
}
