package CustomStreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class CustomStreamImpl<T> implements CustomStream<T> {
    private final List<T> elements;

    public CustomStreamImpl(List<T> elements) {
        this.elements = new ArrayList<>(elements);
    }

    @Override
    public <R, A> R collect(CustomCollector<? super T, A, R> collector) {
        A container = collector.supplier().get();
        BiConsumer<A, ? super T> accumulator = collector.accumulator();
        for (T element : elements) {
            accumulator.accept(container, element);
        }
        return (R) collector.finisher().apply(container);
    }

    @Override
    public CustomStream<T> filter(Predicate<? super T> predicate) {
        List<T> filteredElements = new ArrayList<>();
        for(T element : elements) {
            if(predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        return new CustomStreamImpl<>(filteredElements);
    }
}
