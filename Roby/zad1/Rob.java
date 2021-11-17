package zad1;

import java.util.Random;

class Rob {

    private final Parametry parametr;
    private int pozycja_x;
    private int pozycja_y;
    private Kierunek kierunek_skierowania;

    private final String program;
    private int energia;
    private int wiek;

    public int getPozycja_x() {
        return pozycja_x;
    }

    public int getPozycja_y() {
        return pozycja_y;
    }

    public Kierunek getKierunek_skierowania() {
        return kierunek_skierowania;
    }

    public String getProgram() {
        return program;
    }

    public int getEnergia() {
        return energia;
    }

    public int getWiek() {
        return wiek;
    }

    Rob(int x, int y, Kierunek kier, int pocz_energia, String pocz_progr, Parametry p) {
        this.parametr = p;
        this.pozycja_x = x;
        this.pozycja_y = y;
        this.kierunek_skierowania = kier;

        this.program = pocz_progr;
        this.energia = pocz_energia;
        this.wiek = 0;
    }

    public void zmecz_roba(int ile_zmeczyc) {
        energia = energia - ile_zmeczyc;
    }

    public void postarz_roba() {
        wiek++;
    }

    public void skrec_w_lewo() {
        if (kierunek_skierowania == Kierunek.N) {
            kierunek_skierowania = Kierunek.W;
        } else if (kierunek_skierowania == Kierunek.S) {
            kierunek_skierowania = Kierunek.E;
        } else if (kierunek_skierowania == Kierunek.W) {
            kierunek_skierowania = Kierunek.S;
        } else if (kierunek_skierowania == Kierunek.E) {
            kierunek_skierowania = Kierunek.N;
        }
    }

    public void skrec_w_prawo() {
        if (kierunek_skierowania == Kierunek.N) {
            kierunek_skierowania = Kierunek.E;
        } else if (kierunek_skierowania == Kierunek.S) {
            kierunek_skierowania = Kierunek.W;
        } else if (kierunek_skierowania == Kierunek.W) {
            kierunek_skierowania = Kierunek.N;
        } else if (kierunek_skierowania == Kierunek.E) {
            kierunek_skierowania = Kierunek.S;
        }
    }

    public void idz_prosto() {
        int roz_y = parametr.getRozmiar_planszy_y();
        int roz_x = parametr.getRozmiar_planszy_x();
        if (kierunek_skierowania == Kierunek.N) {
            pozycja_y = (pozycja_y + roz_y - 1) % roz_y;
        } else if (kierunek_skierowania == Kierunek.E) {
            pozycja_x = (pozycja_x + 1) % roz_x;
        } else if (kierunek_skierowania == Kierunek.S) {
            pozycja_y = (pozycja_y + 1) % roz_y;
        } else if (kierunek_skierowania == Kierunek.W) {
            pozycja_x = (pozycja_x + roz_x - 1) % roz_x;
        }
    }

    public void wachaj(Kierunek kierunek) {
        kierunek_skierowania = kierunek;
    }

    public void jedz(int x, int y) {
        pozycja_x = x;
        pozycja_y = y;
    }

    public void zjedz_jedzenie() {
        energia += parametr.getIle_daje_jedzenie();
    }

    public String mutuj() {

        String program_zmutowany = program;
        Random liczba = new Random();

        int wylosowana = liczba.nextInt(100);
        if (wylosowana < parametr.getPr_usuniecia_instr()) {
            if (program.length() > 0) {
                program_zmutowany = program_zmutowany.substring(0, program_zmutowany.length() - 1);
            } else {
                program_zmutowany = "";
            }
        }

        wylosowana = liczba.nextInt(100);
        if (wylosowana < parametr.getPr_dodania_instr()) {
            int liczba_dostepnych_instrukcji = (parametr.getSpis_instr()).length();
            int los = liczba.nextInt(liczba_dostepnych_instrukcji);
            String instrukcja = (parametr.getSpis_instr()).substring(los, los + 1);
            program_zmutowany = program + instrukcja;
        }

        wylosowana = liczba.nextInt(100);
        if (wylosowana < parametr.getPr_zmiany_instr()) {
            int liczba_dostepnych_instrukcji = (parametr.getSpis_instr()).length();
            int dlugosc_naszego_programu = program_zmutowany.length();
            int los = liczba.nextInt(liczba_dostepnych_instrukcji);
            int gdzie_zmienic = liczba.nextInt(dlugosc_naszego_programu);
            String instrukcja = (parametr.getSpis_instr()).substring(los, los + 1);
            String pref = (program_zmutowany).substring(0, gdzie_zmienic);
            String sufi = (program_zmutowany).substring(gdzie_zmienic + 1, dlugosc_naszego_programu);
            program_zmutowany = pref + instrukcja + sufi;
        }

        return program_zmutowany;
    }

}
