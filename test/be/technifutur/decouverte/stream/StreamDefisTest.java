package be.technifutur.decouverte.stream;

import be.technifutur.decouverte.data.Personne;
import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamDefisTest {

    private List<Personne> list;
    private Stream<Personne> stream;
    private StreamDefis defis;

    @BeforeEach
    void init() {
        list = Arrays.asList(Personne.dataTest());
        stream = list.stream();
        defis = new StreamDefis();
    }

    @Test
    void testTroisPremier() {
        List<Personne> expected = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            expected.add(list.get(i));
        }
        assertIterableEquals(expected, defis.troisPremier(stream));
    }

    @Test
    void testSansTroisPremier() {
        List<Personne> expected = new ArrayList<>();
        for (int i = 3; i < list.size(); i++) {
            expected.add(list.get(i));
        }
        assertIterableEquals(expected, defis.sansTroisPremier(stream));
    }

    @Test
    void testAfterDeneuve() {
        List<Personne> expected = new ArrayList<>();
        boolean after = false;
        for (Personne p : list) {
            if (p != null && "Deneuve".equals(p.getName()))
                after = true;
            if (after)
                expected.add(p);
        }
        assertIterableEquals(expected, defis.afterDeneuve(stream));
    }

    @Test
    void testBeforeDeneuve() {
        List<Personne> expected = new ArrayList<>();
        boolean before = true;
        for (Personne p : list) {
            if (p != null && "Deneuve".equals(p.getName()))
                before = false;
            if (before)
                expected.add(p);
        }
        assertIterableEquals(expected, defis.beforeDeneuve(stream));
    }

    @Test
    void testsansdoublon() {
        Set<Personne> expected = new LinkedHashSet<>();
        expected.addAll(list);
        assertIterableEquals(expected, defis.sansdoublon(stream));
    }

    @Test
    void testPrenomBeginByJ() {
        Set<Personne> expected = new LinkedHashSet<>();
        for (Personne p : list) {
            if (p != null && p.getPrenom().startsWith("J"))
                expected.add(p);
        }
        assertIterableEquals(expected, defis.prenomBeginByJ(stream));
    }

    @Test
    void testTailleMoyenne() {
        Set<Personne> set = new LinkedHashSet<>();
        set.addAll(list);
        double somme = 0;
        int count = 0;
        for (Personne p : set) {
            if (p != null) {
                somme += p.getTaille();
                count++;
            }
        }
        assertEquals(somme / count, defis.tailleMoyenne(stream), 0.00001);
    }

    @Test
    void testmaxNameLength() {
        Personne pmax = null;
        int max = 0;
        for (Personne p : list) {
            if (p != null) {
                int length = p.getName().length();
                if (length > max) {
                    max = length;
                    pmax = p;
                }
            }
        }
        assertEquals(pmax, defis.maxNameLength(stream));
    }

    @Test
    @DisplayName("Nombre de 'e' dans les prénoms")
    void testcountChar() {
        Set<Personne> set = new LinkedHashSet<>();
        set.addAll(list);
        char car = 'e';
        int count = 0;
        for (Personne p : set) {
            if (p != null) {
                int pos = p.getPrenom().indexOf(car);
                while (pos > 0) {
                    count++;
                    pos = p.getPrenom().indexOf(car, ++pos);
                }
            }
        }
        assertEquals(count, defis.countChar(stream, car));
    }

    @Test
    @DisplayName("le plus grand acteur par decénie")
    void testMaxbyDizaine() {
        TreeMap<Integer, Personne> map = new TreeMap<>();
        for (Personne p : list) {
            if (p != null) {
                int dizaine = p.getNaissance().getYear() / 10 * 10;
                Personne personne = map.get(dizaine);
                if (personne == null) {
                    map.put(dizaine, p);
                } else {
                    if (p.getTaille() > personne.getTaille()) {
                        map.put(dizaine, p);
                    }
                }
            }
        }
        assertIterableEquals(map.entrySet(), defis.MaxbyDizaine(stream).entrySet());
    }

    @Test
    @DisplayName("Séparer ceux qui ont eu leur anniversaire des autres")
    void testAnniversaire() {
        Map<Boolean, List<Personne>> expected = new HashMap<>();
        Set<Personne> set = new LinkedHashSet<>();
        set.addAll(list);
        char car = 'e';
        int count = 0;
        for (Personne p : set) {
            if (p != null) {
                int lastBithDayYear = p.getNaissance().plusYears(p.getAge()).getYear();
                boolean birthday = lastBithDayYear < LocalDate.now().getYear();
                List<Personne> personnes = expected.get(birthday);
                if (personnes == null) {
                    personnes = new ArrayList<>();
                    expected.put(birthday, personnes);
                }
                personnes.add(p);
            }
        }
        Map<Boolean, List<Personne>> actual = defis.anniversaire(stream);
        assertAll(
                () -> assertIterableEquals(expected.get(true), actual.get(true)),
                () -> assertIterableEquals(expected.get(false), actual.get(false))
        );
    }

}