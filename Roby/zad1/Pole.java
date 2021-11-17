package zad1;

class Pole {
    private final boolean czy_zywieniowe;
    private boolean czy_ma_jedzenie;
    private int kiedy_zjedzono;

    public boolean isCzy_zywieniowe() {
        return czy_zywieniowe;
    }

    public boolean isCzy_ma_jedzenie() {
        return czy_ma_jedzenie;
    }

    Pole(boolean czy_zyw) {
        czy_zywieniowe = czy_zyw;
        czy_ma_jedzenie = czy_zyw;
    }

    public void zabierz_jedzenie(int ktora_tura) {
        kiedy_zjedzono = ktora_tura;
        czy_ma_jedzenie = false;
    }

    public boolean sprawdz_czy_uroslo(int numer_tury, int ile_rosnie_jedzenie) {
        return numer_tury - kiedy_zjedzono > ile_rosnie_jedzenie;
    }

    public void urosnij() {
        czy_ma_jedzenie = true;
    }
}
