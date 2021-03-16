package be.technifutur.decouverte.stream;

import be.technifutur.decouverte.data.Personne;
import be.technifutur.decouverte.testcollection.CollectionFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamCharacteritic {

    public static void main(String[] args) {
        System.out.println("HashSet");
        printStreamCharacteristics(CollectionFactory.createHashSet());

        System.out.println("PriorityQueue");
        Comparator<Personne> comparator = Comparator.comparing(Personne::getAge);
        printStreamCharacteristics(CollectionFactory.createPriorityQueue(comparator));

        System.out.println("TreeSet");
        printStreamCharacteristics(CollectionFactory.createTreeSet(comparator));

        System.out.println("ArrayList");
        printStreamCharacteristics(CollectionFactory.createArrayList());

        System.out.println("InfinityStream");
        printStreamCharacteristics(UseCaseCreateStream.createInfinityStream());

    }

    public static void printStreamCharacteristics(Collection collection) {
        Stream<?> stream = UseCaseCreateStream.createwithCollection(collection);
        printStreamCharacteristics(stream);
    }
    public static void printStreamCharacteristics(Stream<?> stream) {
        System.out.println(
                UseCaseCreateStream.createStreamCharacteristicWithBuilder(
                        stream.spliterator().characteristics()
                ).collect(Collectors.joining(", ","","\n"))
        );
    }
}
