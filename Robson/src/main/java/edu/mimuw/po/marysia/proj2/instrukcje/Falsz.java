package edu.mimuw.po.marysia.proj2.instrukcje;

public class Falsz implements Instrukcja, Wyrazenie {

    public static final double FALSZ = 0.0;

    public Falsz() {}

    public double wykonaj() {
        return FALSZ;
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append("false");

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"False\"\n")
          .append("}");

        return sb.toString();
    }
}
