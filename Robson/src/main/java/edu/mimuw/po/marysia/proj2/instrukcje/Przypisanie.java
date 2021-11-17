package edu.mimuw.po.marysia.proj2.instrukcje;

import edu.mimuw.po.marysia.proj2.Robson;

public class Przypisanie implements Instrukcja {

    private final String nazwa;
    private final Instrukcja wartosc;

    public Przypisanie(String nazwa, Instrukcja wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }

    public double wykonaj() {

        double wartoscZmiennej = wartosc.wykonaj();
        Robson.getZmienneGlobalne().put(nazwa, wartoscZmiennej);

        return wartoscZmiennej;
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(nazwa)
          .append(" = ")
          .append(wartosc.toJava())
          .append(";\n");

        return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"Przypisanie\",\n")
          .append("\"nazwa\":\"")
          .append(nazwa).append("\",\n")
          .append("\"wartosc\":")
          .append(wartosc.toJson())
          .append("\n}");

        return sb.toString();
    }
}
