package be.technifutur.decouverte.stream;

import be.technifutur.decouverte.data.Personne;
import be.technifutur.decouverte.testcollection.CollectionFactory;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UseCaseCreateStream {
    public static Stream<Personne> createEmptyStream() {
        return Stream.empty();
    }

    public static Stream<Personne> createOf1ElementStream() {
        return Stream.of(Personne.dataTest()[0]);
    }

    public static Stream<Personne> createOfxElementStream() {
        return Stream.of(Personne.dataTest());
    }

    public static Stream<Personne> createOf0_1ElementStream() {
        return Stream.ofNullable(Personne.dataTest()[0]);
    }

    public static Stream<Personne> createInfinityStream() {
        return Stream.generate(
                new Supplier<Personne>() {
                    Personne[] personnes = Personne.dataTest();
                    int size = personnes.length;
                    int pos = -1;

                    @Override
                    public Personne get() {
                        pos = (pos + 1) % size;
                        return personnes[pos];
                    }
                }
        );
    }

    public static Stream<int[]> createWithIterate() {
        return Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]});
    }

    public static Stream<Personne> createWithIterateHasNext() {
        Queue<Personne> pq = CollectionFactory.createPriorityQueue(Comparator.comparing(Personne::getAge));
        return Stream.iterate(pq.poll(), p -> !pq.isEmpty(), p -> pq.poll());
    }

    public static Stream<String> createStreamCharacteristicWithBuilder(int characteristics) {
        Stream.Builder<String> builder = Stream.builder();
        if ((characteristics & Spliterator.CONCURRENT) > 0) builder.accept("Concurrent");
        if ((characteristics & Spliterator.DISTINCT) > 0) builder.accept("Distinct");
        if ((characteristics & Spliterator.IMMUTABLE) > 0) builder.accept("Immutable");
        if ((characteristics & Spliterator.NONNULL) > 0) builder.accept("NonNull");
        if ((characteristics & Spliterator.SIZED) > 0) builder.accept("Sized");
        if ((characteristics & Spliterator.ORDERED) > 0) builder.accept("Ordered");
        if ((characteristics & Spliterator.SUBSIZED) > 0) builder.accept("Subsized");
        return builder.build();
    }

    public static Stream<Personne> createWithArrays() {
        return Arrays.stream(Personne.dataTest());
    }

    public static Stream<Personne> createwithCollection(Collection<Personne> col) {
        return col.stream();
    }
}
