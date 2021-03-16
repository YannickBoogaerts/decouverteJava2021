package be.technifutur.decouverte.testcollection;

import be.technifutur.decouverte.data.Personne;

import java.util.*;

public class CollectionFactory {

    public static Set<Personne> createHashSet(){
        return addPersonne(new HashSet<Personne>());
    }

    public static Set<Personne> createLinkedHashSet(){
        return addPersonne(new LinkedHashSet<Personne>());
    }

    public static NavigableSet<Personne> createTreeSet(Comparator<? super Personne> comparator){
        return addPersonne(new TreeSet<Personne>(comparator));
    }

    public static List<Personne> createArrayList(){
        return addPersonne(new ArrayList<Personne>());
    }

    public static List<Personne> createLinkedListInList(){
        return addPersonne(new LinkedList<Personne>());
    }

    public static Deque<Personne> createArrayDeque(){
        return addPersonne(new ArrayDeque<Personne>());
    }

    public static Deque<Personne> createLinkedListInDeque(){
        return addPersonne(new LinkedList<Personne>());
    }

    public static Queue<Personne> createPriorityQueue(Comparator<? super Personne> comparator){
        return addPersonne(new PriorityQueue<Personne>(comparator));
    }

    public static List<Personne> createUnmodifiableListFromArray(){
        return Arrays.asList(Personne.dataTest());
    }

    private static < T extends Collection<Personne>> T addPersonne( T collection) {
        System.out.println("ajout des personnes...");
        for(Personne p : Personne.dataTest()){
            try {
                if (!collection.add(p)) {
                    System.out.println("refus :" + p);
                }
            }catch (Exception e){
                System.out.println("refus :" + p);
            }
        }
        System.out.println();
        return collection;
    }


}
