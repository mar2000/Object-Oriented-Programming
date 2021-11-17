package edu.mimuw.po.marysia.proj2.instrukcje;

import edu.mimuw.po.marysia.proj2.Robson;

public class Zmienna implements Instrukcja, Wyrazenie {

    private final String nazwa;

    public Zmienna(String nazwa) {
        this.nazwa = nazwa;
    }

    public double wykonaj() {
        return Robson.getZmienneGlobalne().get(nazwa);
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(nazwa);

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n\"typ\":\"Zmienna\",")
          .append("\n\"nazwa\": \"")
          .append(nazwa)
          .append("\"\n}");

		return sb.toString();
    }
}
