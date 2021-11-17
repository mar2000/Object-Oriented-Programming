package edu.mimuw.po.marysia.proj2.instrukcje;

public class Liczba implements Instrukcja, Wyrazenie {

    private final Double wartosc;

    public Liczba(Double wartosc) {
        this.wartosc = wartosc;
    }

    public double wykonaj() {
        return wartosc;
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(wartosc);

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"Liczba\",\n")
          .append("\"wartosc\":").append(wartosc)
          .append("\n}");

		return sb.toString();
    }
}
