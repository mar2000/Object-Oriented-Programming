package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Symulacja {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.out.println("Podano złą liczbę plików");
            System.exit(7);
        }

        Parametry parametr = new Parametry();
        wczytaj_parametry(parametr, args[0], args[1]);

        System.out.println("Wczytywanie parametrów zakończyło się pomyślnie");

        Plansza plansza = new Plansza(parametr, args[0]);

        System.out.println("Tworzenie planszy zakończyło się pomyślnie");

        plansza.rozmiesc_roby();

        System.out.println("Roby zostły rozmieszczone - możemy zaczynać SYMULACJE");

        for (int i=0; i<parametr.getIle_tur(); i++) {
            plansza.symuluj();
        }
        System.out.println("\n" + "--------------------------------MAŁE STATYSTYKI--------------------------------");
        plansza.wypisz_statystyki();
        System.out.println("\n" + "--------------------------------DUŻE STATYSTYKI--------------------------------");
        plansza.wypisz_duze_statystyki();

    }

    static void wczytaj_parametry(Parametry parametr, String plansza, String parametry) throws FileNotFoundException {

        File file = new File(plansza);
        Scanner input = new Scanner(file);

        if (file.length() == 0) {
            System.out.println("Plik z planszą jest pusty");
            System.exit(7);
        }

        int y = 0, x = 0;
        while (input.hasNextLine()) {
            if (y == 0) {
                x = input.nextLine().length();
            }
            else if (x != input.nextLine().length()) {
                System.out.println("Plansza nie składa się z wierszy równej długości");
                System.exit(7);
            }

            y++;
        }
        parametr.setRozmiar_planszy_x(x);
        parametr.setRozmiar_planszy_y(y);

        file = new File(parametry);
        input = new Scanner(file);

        if (file.length() == 0) {
            System.out.println("Plik z parametrami jest pusty");
            System.exit(7);
        }

        int [] tab = new int[15];
        while (input.hasNextLine()) {
            String linia = input.nextLine();
            String[] actualValue = linia.split(" ");
            int a = 0;
            for (int i=0; i<1; i++) {
                if (actualValue.length > 2) {
                    System.out.println("podałeś za dużo wartości, spacji lub nazw parametrów w jednej linii");
                    System.exit(7);
                }

                if (actualValue[i].equals("spis_instr")) {
                    tab[0]++;
                    parametr.setSpis_instr(actualValue[i + 1]);

                    String kod = actualValue[i + 1];
                    int l = kod.length();
                    for (int k = 0; k < l; k++) {
                        String instrukcja = kod.substring(k, k + 1);
                        if (!instrukcja.equals("l") && !instrukcja.equals("p") && !instrukcja.equals("i") && !instrukcja.equals("w") && !instrukcja.equals("j")) {
                            System.out.println("spis instrukcji powinnien składać się z liter l,p,i,w lub j");
                            System.exit(7);
                        }
                    }

                }
                if (actualValue[i].equals("pocz_progr")) {
                    tab[1]++;
                    parametr.setPocz_progr(actualValue[i+1]);

                    String kod = actualValue[i + 1];
                    int l = kod.length();
                    for (int k = 0; k < l; k++) {
                        String instrukcja = kod.substring(k, k + 1);
                        if (!instrukcja.equals("l") && !instrukcja.equals("p") && !instrukcja.equals("i") && !instrukcja.equals("w") && !instrukcja.equals("j")) {
                            System.out.println("spis instrukcji powinnien składać się z liter l,p,i,w lub j");
                            System.exit(7);
                        }
                    }
                }
                if (actualValue[i].equals("ile_tur")) {
                    tab[2]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setIle_tur(a);
                }
                if (actualValue[i].equals("ile_rośnie_jedzenie")) {
                    tab[3]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setIle_rosnie_jedzenie(a);
                }
                if (actualValue[i].equals("pocz_ile_robów")) {
                    tab[4]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPocz_ile_robow(a);
                }
                if (actualValue[i].equals("pocz_energia")) {
                    tab[5]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPocz_energia(a);
                }
                if (actualValue[i].equals("ile_daje_jedzenie")) {
                    tab[6]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setIle_daje_jedzenie(a);
                }
                if (actualValue[i].equals("koszt_tury")) {
                    tab[7]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setKoszt_tury(a);
                }
                if (actualValue[i].equals("pr_powielenia")) {
                    tab[8]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPr_powielenia(a);
                }
                if (actualValue[i].equals("ułamek_energii_rodzica")) {
                    tab[9]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setUlamek_energii_rodzica(a);
                }
                if (actualValue[i].equals("limit_powielania")) {
                    tab[10]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setLimit_powielania(a);

                }
                if (actualValue[i].equals("pr_usunięcia_instr")) {
                    tab[11]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPr_usuniecia_instr(a);
                }
                if (actualValue[i].equals("pr_dodania_instr")) {
                    tab[12]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPr_dodania_instr(a);
                }
                if (actualValue[i].equals("pr_zmiany_instr")) {
                    tab[13]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setPr_zmiany_instr(a);
                }
                if (actualValue[i].equals("co_ile_wypisz")) {
                    tab[14]++;
                    try {
                        a = Integer.parseInt(actualValue[i+1]);
                    }
                    catch (Exception blad) {
                        System.out.println("Typ parametru jest niewłaściwy");
                        System.exit(7);
                    }
                    parametr.setCo_ile_wypisz(a);
                }
            }
        }

        for (int i=0; i<15; i++) {
            if (tab[i] != 1) {
                System.out.println("W pliku parametry.txt nie ma 15 parametrów lub parametr ma złą nazwę");
                System.exit(7);
            }
        }
    }
}



