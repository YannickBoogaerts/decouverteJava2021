package be.technifutur.decouverte.stream;

import be.technifutur.decouverte.data.Personne;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDefis {

    public List<Personne> troisPremier(Stream<Personne> stream) {
        return stream.limit(3)
                     .collect(toList());
    }

    public List<Personne> sansTroisPremier(Stream<Personne> stream) {
        return stream.skip(3)
                     .collect(toList());
    }

    public List<Personne> afterDeneuve(Stream<Personne> stream) {
        return stream.dropWhile(p -> p == null || !p.getName().equals("Deneuve"))
                     .collect(toList());
    }

    public List<Personne> beforeDeneuve(Stream<Personne> stream) {
        return stream.takeWhile(p -> p == null || !p.getName().equals("Deneuve"))
                     .collect(toList());
    }

    public List<Personne> sansdoublon(Stream<Personne> stream) {
        return stream.distinct()
                     .collect(toList());
    }

    public List<Personne> prenomBeginByJ(Stream<Personne> stream) {
        return stream.filter(p -> p != null && p.getPrenom().charAt(0) == 'J')
                     .distinct()
                     .collect(toList());
    }

    public double tailleMoyenne(Stream<Personne> stream) {
        return stream
                .filter(p -> p != null)
                .distinct()
                .collect(averagingInt(Personne::getTaille));
    }

    public Personne maxNameLength(Stream<Personne> stream) {
        return stream
                .filter(p -> p != null)
                .collect(reducing((p1, p2) -> p1.getName().length() < p2.getName().length() ? p2 : p1)).get();
    }

    public long countChar(Stream<Personne> stream, char car) {
        return stream
                .filter(p -> p != null)
                .distinct()
                .flatMapToInt(p -> p.getPrenom().chars())
                .filter(c -> c == car)
                .count();
    }

    public SortedMap<Integer, Personne> MaxbyDizaine(Stream<Personne> stream) {
        return stream
                .filter(p -> p != null)
                .distinct()
                .collect(toMap(
                        p -> p.getNaissance().getYear() / 10 * 10,
                        Function.identity(),
                        (p1, p2) -> p2.getTaille() > p1.getTaille() ? p2 : p1,
                        TreeMap::new
                ));
    }

    public Map<Boolean, List<Personne>> anniversaire(Stream<Personne> stream) {
        return stream
                .filter(p -> p != null)
                .distinct()
                .collect(
                        partitioningBy(
                                p -> p.getNaissance().plusYears(p.getAge()).getYear() < LocalDate.now().getYear()
                        )
                );
    }
}
