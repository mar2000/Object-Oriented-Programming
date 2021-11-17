package edu.mimuw.po.marysia.proj2.instrukcje;

public class Prawda implements Instrukcja, Wyrazenie {

    public static final double PRAWDA = 1.0;

    public Prawda() {}

    public double wykonaj() {
        return PRAWDA;
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append("true");

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"True\"\n")
          .append("}");

		return sb.toString();
    }
}
