package be.technifutur.decouverte.data;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Personne implements Comparable<Personne>{

    private String name;
    private String prenom;
    private LocalDate naissance;
    private int taille;

    public Personne(String name, String prenom, LocalDate naissance, int taille) {
        this.name = Objects.requireNonNull(name);
        this.prenom = Objects.requireNonNull(prenom);
        this.naissance = Objects.requireNonNull(naissance);
        this.taille = taille;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getAge(){
        return (int) this.naissance.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", naissance=" + naissance +
                ", taille=" + taille +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (!name.equals(personne.name)) return false;
        if (!prenom.equals(personne.prenom)) return false;
        return naissance.equals(personne.naissance);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + prenom.hashCode();
        result = 31 * result + naissance.hashCode();
        return result;
    }

    @Override
    public int compareTo(Personne o) {
        return this.taille - o.taille;
    }

    public static void main(String[] args) {
        Personne[] tab = dataTest();

        System.out.println(tab[0]);
        System.out.println(tab[0].getAge());
        System.out.println(tab[0].compareTo(tab[2]));
    }

    public static Personne[] dataTest() {
        return new Personne[]{
                new Personne("Belmondo", "Jean-Paul", LocalDate.of(1933 , 4, 26), 176),
                new Personne("Laurent", "Mélanie", LocalDate.of(1983, 2, 21), 157),
                new Personne("Gabin", "Jean", LocalDate.of(1904, 2, 2), 174),
                new Personne("André Robert","Raimbourg" , LocalDate.of(1917,7,27), 174),
                new Personne("Deneuve", "Catherine", LocalDate.of(1943 ,10,22), 168),
                new Personne("Brasseur", "Claude", LocalDate.of(1936, 6, 15), 175),
                new Personne("Dewaere", "Patrick", LocalDate.of(1947, 1, 26), 177),
                new Personne("Montand", "Yves", LocalDate.of(1921, 10, 13), 187),
                new Personne("Louis", "de Funès", LocalDate.of(1914, 7, 31), 164)
        };
    }
}
