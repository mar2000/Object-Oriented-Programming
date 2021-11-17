package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Plansza {

    private final Parametry parametr;
    private final int rozmiar_planszy_x;
    private final int rozmiar_planszy_y;
    private final Pole[][] pola;
    private final List<Rob> roby = new ArrayList<>();

    private int numer_tury;
    private int liczba_robow;
    private int liczba_pol_z_zywnoscia;
    private int minimalna_dlugosc_programu;
    private int maksymalna_dlugosc_programu;
    private int suma_dlugosci_programu;
    private int minimalna_energia_roba;
    private int maksymalna_energia_roba;
    private int suma_energii_robow;
    private int minimalny_wiek_roba;
    private int maksymalny_wiek_roba;
    private int suma_lat_robow;

    Plansza(Parametry p, String plansza) throws FileNotFoundException {
        File file = new File(plansza);
        Scanner input = new Scanner(file);

        this.parametr = p;
        this.rozmiar_planszy_x = p.getRozmiar_planszy_x();
        this.rozmiar_planszy_y = p.getRozmiar_planszy_y();

        this.numer_tury = 0;
        this.liczba_robow = p.getPocz_ile_robow();
        this.liczba_pol_z_zywnoscia = 0;
        this.minimalna_dlugosc_programu = p.getPocz_progr().length();
        this.maksymalna_dlugosc_programu = p.getPocz_progr().length();
        this.suma_dlugosci_programu = liczba_robow * p.getPocz_progr().length();
        this.minimalna_energia_roba = p.getPocz_energia();
        this.maksymalna_energia_roba = p.getPocz_energia();
        this.suma_energii_robow = liczba_robow * p.getPocz_energia();
        this.minimalny_wiek_roba = 0;
        this.maksymalny_wiek_roba = 0;
        this.suma_lat_robow = 0;

        pola = new Pole[rozmiar_planszy_x][rozmiar_planszy_y];

        int j = 0;
        while (input.hasNextLine()) {
            String linia = input.nextLine();
            for (int i = 0; i < rozmiar_planszy_x; i++) {
                if (linia.charAt(i) == ' ') {
                    pola[i][j] = new Pole(false);
                } else if (linia.charAt(i) == 'x') {
                    pola[i][j] = new Pole(true);
                    liczba_pol_z_zywnoscia++;
                } else {
                    System.out.println("Plansza zawiera złe elementy");
                    System.exit(7);
                }
            }
            j++;
        }
    }

    public void rozmiesc_roby() {
        for (int i = 0; i < parametr.getPocz_ile_robow(); i++) {
            Random liczba = new Random();
            int x = liczba.nextInt(rozmiar_planszy_x);
            int y = liczba.nextInt(rozmiar_planszy_y);
            Kierunek kierRoba;
            int kier = liczba.nextInt(4);
            if (kier == 0) {
                kierRoba = Kierunek.N;
            } else if (kier == 1) {
                kierRoba = Kierunek.E;
            } else if (kier == 2) {
                kierRoba = Kierunek.S;
            } else {
                kierRoba = Kierunek.W;
            }

            Rob rob = new Rob(x, y, kierRoba, parametr.getPocz_energia(), parametr.getPocz_progr(), parametr);
            roby.add(rob);
        }
    }

    public void wypisz_statystyki() {
        if (liczba_robow > 0) {
            System.out.println(
                    numer_tury + ", " +
                            "rob: " + liczba_robow + ", " +
                            "żyw: " + liczba_pol_z_zywnoscia + ", " +
                            "prg: " + minimalna_dlugosc_programu + " / "
                            + suma_dlugosci_programu / liczba_robow + " / "
                            + maksymalna_dlugosc_programu + ", " +
                            "energ: " + minimalna_energia_roba + " / "
                            + suma_energii_robow / liczba_robow + " / "
                            + maksymalna_energia_roba + ", " +
                            "wiek: " + minimalny_wiek_roba + " / "
                            + suma_lat_robow / liczba_robow + " / "
                            + maksymalny_wiek_roba
            );
        } else {
            System.out.println(
                    numer_tury + ", " +
                            "rob: " + liczba_robow + ", " +
                            "żyw: " + liczba_pol_z_zywnoscia + ", " +
                            "prg: " + 0 + " / " + 0 + " / " + 0 + ", " +
                            "energ: " + 0 + " / " + 0 + " / " + 0 + ", " +
                            "wiek: " + 0 + " / " + 0 + " / " + 0
            );
        }
    }

    public void wypisz_duze_statystyki() {
        int c = roby.size();
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            System.out.println(
                    "-------------------------------------------------------------------------------" + "\n" +
                            "Współrzędne roba : (" + R.getPozycja_x() + "; " + R.getPozycja_y() + ") " + R.getKierunek_skierowania() + "\n" +
                            "Energia roba: " + R.getEnergia() + " kcal" + "\n" +
                            "Wiek roba: " + R.getWiek() + " lat" + "\n" +
                            "Długość programu roba: " + R.getProgram().length() + " instrukcji" + "\n" +
                            "Kod roba: " + R.getProgram() + "\n" +
                            "-------------------------------------------------------------------------------" + "\n"
            );
        }
    }

    public void symuluj() {
        if (roby.size() > 0) {
            System.out.println("\n" + "--------------------------------MAŁE STATYSTYKI--------------------------------");
            wypisz_statystyki();
            if (numer_tury % parametr.getCo_ile_wypisz() == 0) {
                System.out.println("\n\n\n" + "--------------------------------DUŻE STATYSTYKI--------------------------------");
                wypisz_duze_statystyki();
            }
            System.out.println("\n");
            zmecz_roby();
            wykonaj_program_robow();
            powiel_roby();
            aktualizuj_energie_robow();
            if (roby.size() > 0) {
                aktualizuj_dlugosc_programow();
                aktualizuj_wiek_robow();
            }
        } else {
            return;
        }
        numer_tury++;
        postarz_pola();
    }

    public void powiel_roby() {
        int c = roby.size();
        while (c > 0) {
            Random liczba = new Random();
            int wylosowana = liczba.nextInt(100);
            Rob R = roby.get(c - 1);
            c--;
            if (wylosowana < parametr.getPr_powielenia() && R.getEnergia() > parametr.getLimit_powielania()) {

                int x = R.getPozycja_x();
                int y = R.getPozycja_y();

                Kierunek kier;
                if (R.getKierunek_skierowania() == Kierunek.N) {
                    kier = Kierunek.S;
                } else if (R.getKierunek_skierowania() == Kierunek.S) {
                    kier = Kierunek.N;
                } else if (R.getKierunek_skierowania() == Kierunek.W) {
                    kier = Kierunek.E;
                } else {
                    kier = Kierunek.W;
                }

                int energia = (R.getEnergia() * parametr.getUlamek_energii_rodzica()) / 100;
                R.zmecz_roba(energia);

                String program = R.mutuj();

                Rob rob = new Rob(x, y, kier, energia, program, parametr);
                roby.add(rob);

            }
        }


    }

    public void aktualizuj_energie_robow() {
        int c = roby.size();
        suma_energii_robow = 0;
        minimalna_energia_roba = 0;
        maksymalna_energia_roba = 0;
        boolean czy_ustalono_minimalna = false;
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            if (R.getEnergia() <= 0) {
                roby.remove(c);
            } else if (!czy_ustalono_minimalna) {
                czy_ustalono_minimalna = true;
                suma_energii_robow += R.getEnergia();
                minimalna_energia_roba = R.getEnergia();
                maksymalna_energia_roba = R.getEnergia();
            } else {
                suma_energii_robow += R.getEnergia();
                if (R.getEnergia() < minimalna_energia_roba) minimalna_energia_roba = R.getEnergia();
                if (R.getEnergia() > maksymalna_energia_roba) maksymalna_energia_roba = R.getEnergia();
            }
        }
        liczba_robow = roby.size();
    }

    public void aktualizuj_dlugosc_programow() {
        int c = roby.size();
        minimalna_dlugosc_programu = roby.get(c - 1).getProgram().length();
        maksymalna_dlugosc_programu = roby.get(c - 1).getProgram().length();
        suma_dlugosci_programu = roby.get(c - 1).getProgram().length();
        c--;
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            suma_dlugosci_programu += R.getProgram().length();
            if (R.getProgram().length() < minimalna_dlugosc_programu)
                minimalna_dlugosc_programu = R.getProgram().length();
            if (R.getProgram().length() > maksymalna_dlugosc_programu)
                maksymalna_dlugosc_programu = R.getProgram().length();
        }
    }

    public void aktualizuj_wiek_robow() {
        int c = roby.size();
        minimalny_wiek_roba = roby.get(c - 1).getWiek();
        maksymalny_wiek_roba = roby.get(c - 1).getWiek();
        suma_lat_robow = roby.get(c - 1).getWiek();
        c--;
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            suma_lat_robow += R.getWiek();
            if (R.getWiek() < minimalny_wiek_roba) minimalny_wiek_roba = R.getWiek();
            if (R.getWiek() > maksymalny_wiek_roba) maksymalny_wiek_roba = R.getWiek();
        }
    }

    public void postarz_pola() {
        for (int j = 0; j < rozmiar_planszy_y; j++) {
            for (int i = 0; i < rozmiar_planszy_x; i++) {
                if (pola[i][j].isCzy_zywieniowe()) {
                    if (!pola[i][j].isCzy_ma_jedzenie() && pola[i][j].sprawdz_czy_uroslo(numer_tury, parametr.getIle_rosnie_jedzenie())) {
                        pola[i][j].urosnij();
                        liczba_pol_z_zywnoscia++;
                    }
                }
            }
        }
    }

    public void zmecz_roby() {
        int c = roby.size();
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            R.zmecz_roba(parametr.getKoszt_tury());
            R.postarz_roba();
        }
    }

    public void wykonaj_program_robow() {
        int c = roby.size();
        while (c > 0) {
            Rob R = roby.get(c - 1);
            c--;
            String kod = R.getProgram();

            int l = kod.length();
            for (int i = 0; i < l; i++) {
                if (R.getEnergia() <= 0) {
                    break;
                }
                String instrukcja = kod.substring(i, i + 1);

                if (instrukcja.equals("l")) {
                    R.skrec_w_lewo();
                }
                if (instrukcja.equals("p")) {
                    R.skrec_w_prawo();
                }
                if (instrukcja.equals("i")) {
                    R.idz_prosto();
                    if (pola[R.getPozycja_x()][R.getPozycja_y()].isCzy_ma_jedzenie()) {
                        R.zjedz_jedzenie();
                        liczba_pol_z_zywnoscia--;
                        pola[R.getPozycja_x()][R.getPozycja_y()].zabierz_jedzenie(numer_tury);
                    }
                }
                if (instrukcja.equals("w")) {
                    int wsp_x = R.getPozycja_x();
                    int wsp_y = R.getPozycja_y();

                    int lewo = (wsp_x + rozmiar_planszy_x - 1) % rozmiar_planszy_x;
                    int prawo = (wsp_x + 1) % rozmiar_planszy_x;
                    int gora = (wsp_y + rozmiar_planszy_y - 1) % rozmiar_planszy_y;
                    int dol = (wsp_y + 1) % rozmiar_planszy_y;

                    if (pola[wsp_x][gora].isCzy_ma_jedzenie()) {
                        R.wachaj(Kierunek.N);
                    } else if (pola[prawo][wsp_y].isCzy_ma_jedzenie()) {
                        R.wachaj(Kierunek.E);
                    } else if (pola[wsp_x][dol].isCzy_ma_jedzenie()) {
                        R.wachaj(Kierunek.S);
                    } else if (pola[lewo][wsp_y].isCzy_ma_jedzenie()) {
                        R.wachaj(Kierunek.W);
                    }
                }
                if (instrukcja.equals("j")) {
                    int wsp_x = R.getPozycja_x();
                    int wsp_y = R.getPozycja_y();
                    int lewo = (wsp_x + rozmiar_planszy_x - 1) % rozmiar_planszy_x;
                    int prawo = (wsp_x + 1) % rozmiar_planszy_x;
                    int gora = (wsp_y + rozmiar_planszy_y - 1) % rozmiar_planszy_y;
                    int dol = (wsp_y + 1) % rozmiar_planszy_y;

                    if (pola[wsp_x][gora].isCzy_ma_jedzenie()) {
                        R.jedz(wsp_x, gora);
                    } else if (pola[prawo][gora].isCzy_ma_jedzenie()) {
                        R.jedz(prawo, gora);
                    } else if (pola[prawo][wsp_y].isCzy_ma_jedzenie()) {
                        R.jedz(prawo, wsp_y);
                    } else if (pola[prawo][dol].isCzy_ma_jedzenie()) {
                        R.jedz(prawo, dol);
                    } else if (pola[wsp_x][dol].isCzy_ma_jedzenie()) {
                        R.jedz(wsp_x, dol);
                    } else if (pola[lewo][dol].isCzy_ma_jedzenie()) {
                        R.jedz(lewo, dol);
                    } else if (pola[lewo][wsp_y].isCzy_ma_jedzenie()) {
                        R.jedz(lewo, wsp_y);
                    } else if (pola[lewo][gora].isCzy_ma_jedzenie()) {
                        R.jedz(lewo, gora);
                    }

                    if (pola[R.getPozycja_x()][R.getPozycja_y()].isCzy_ma_jedzenie()) {
                        R.zjedz_jedzenie();
                        liczba_pol_z_zywnoscia--;
                        pola[R.getPozycja_x()][R.getPozycja_y()].zabierz_jedzenie(numer_tury);
                    }
                }
                R.zmecz_roba(1);
            }
        }
    }
}
