package edu.mimuw.po.marysia.proj2.instrukcje;

public class Negacja implements Instrukcja, Wyrazenie {

    private static final double PRAWDA = 1.0;
    private static final double FALSZ = 0.0;
    private final Instrukcja argument;

    public Negacja(Instrukcja argument) {
        this.argument = argument;
    }

    public double wykonaj() {
        if(argument.wykonaj() != FALSZ) {
            return PRAWDA;
        }
        else {
            return FALSZ;
        }
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append("!(")
          .append(argument.toJava())
          .append(")");

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"Not\",\n")
          .append("\"argument\":")
          .append(argument.toJson())
          .append("\n}");
		return sb.toString();
    }
}
