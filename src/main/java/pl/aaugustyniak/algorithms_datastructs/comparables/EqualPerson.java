package pl.aaugustyniak.algorithms_datastructs.comparables;

import java.util.Objects;

/**
 *
 * @author aaugustyniak
 */
public final class EqualPerson implements Comparable<EqualPerson> {

    private final String name;
    private final long info;

    public EqualPerson(String name, long info) {
        this.name = name;
        this.info = info;
    }

    // how you're supposed to implement equals
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {

            return false;
        }
        EqualPerson that = (EqualPerson) y;
        return (this.name.equals(that.name)) && (this.info == that.info);
    }

    /**
     * hashCode(), to funkcja, która na podstawie wartości obiektu swojego typu
     * zwraca liczbę całkowitą. Różne obiekty różnych typów mogą zwracać tę samą
     * wartość, ale dwa różne obiekty tego samego typu najlepiej powinny dawać
     * unikalne wartości (nie powtarzające się).
     *
     * Teraz - do czego to służy? Skoro różne wartości obiektów dają zawsze
     * różne wartości, to można te wartości wykorzystać jako indeks bardzo
     * szybkiej tablicy referencji obiektów. Dzięki takiemu podejściu
     * teoretyczny koszt dostępu do obiektu to zawsze jedno dodawanie. Tak
     * właśnie działa hashMap. Oczywiście tworzenie tablic o zakresie indeksów
     * -2mld..+2mld (32-bit) byłoby bezsensownym marnotrawstwem pamięci, więc
     * tworzy się znacznie mniejszą tablicę (np. 8192 elementów), w której
     * indeksy zawija się modulo jej rozmiar. Zwykle rozmiar jest potęgą dwójki,
     * co redukuje modulo (resztę z dzielenia) do znacznie szybszej operacji
     * binarnej AND. "Zawinięte" indeksy oraz powtarzające się indeksy różnych
     * typów obiektów powodują, że pod ten sam indeks trafią różne obiekty
     * (również tego samego typu). A to powoduje, że wartościami komórek tej
     * tablicy nie mogą być referencje tych obiektów, ale listy tych obiektów o
     * powtarzającym się "zawiniętym" hashCode. Powoduje to konieczność
     * wyszukania na takiej liście właściwego obiektu, o który nam chodziło. I
     * właśnie w tym miejscu potrzebne jest poprawne zdefiniowanie funkcji
     * equal, która pozwoli wyszukać liniowo właściwy obiekt przez porównanie z
     * szukanym wzorcem. Oczywiście kiedy dodajemy do mapy nie potrzebujemy
     * niczego wyszukiwać, więc tylko dodajemy obiekt (referencję) do listy, co
     * też jest bardzo szybkie.
     *
     * Tak więc cała operacja na hash mapie sprowadza się do wyliczenia wartości
     * hashCode, jednego dodawania dla wyliczenia indeksu, jednej operacji AND
     * oraz, albo wyszukania obiektu na kilkuelementowej liście (często
     * zdegenerowanej do jednego elementu), albo dodania do listy (często
     * pustej). To właśnie dlatego hash mapa jest tak niesamowicie szybka i ma
     * średnio stały koszt czasu dodawania i wyciągania obiektów. A to wszystko
     * opiera się tylko na koncepcji kodu hash. Jedynym wartym uwagi kosztem
     * jest czas obliczania samego kodu - dlatego ważne jest, żeby wyliczać go
     * dobrze i wydajnie
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + (int) (this.info ^ (this.info >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return name + " " + info;
    }

    @Override
    public int compareTo(EqualPerson o) {
        if (this.equals(o)) {
            return 0;
        } else {
            return this.toString().compareTo(o.toString());
        }
    }
}
