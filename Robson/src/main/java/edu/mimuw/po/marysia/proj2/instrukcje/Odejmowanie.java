package edu.mimuw.po.marysia.proj2.instrukcje;

public class Odejmowanie implements Instrukcja, Wyrazenie {

    private final Instrukcja argument1;
    private final Instrukcja argument2;

    public Odejmowanie(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public double wykonaj() {
        return argument1.wykonaj() - argument2.wykonaj();
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(argument1.toJava())
          .append(" - ")
          .append(argument2.toJava());

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();


        sb.append("{\n")
          .append("\"typ\":\"Minus\",\n")
          .append("\"argument1\":")
          .append(argument1.toJson())
          .append(",\n\"argument2\":")
          .append(argument2.toJson())
          .append("\n}");


        return sb.toString();
    }
}
