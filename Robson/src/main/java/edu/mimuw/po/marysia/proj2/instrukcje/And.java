package edu.mimuw.po.marysia.proj2.instrukcje;

import static edu.mimuw.po.marysia.proj2.instrukcje.Falsz.FALSZ;
import static edu.mimuw.po.marysia.proj2.instrukcje.Prawda.PRAWDA;

public class And implements Instrukcja, Wyrazenie {

    private final Instrukcja argument1;
    private final Instrukcja argument2;

    public And(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public double wykonaj() {
        if (argument1.wykonaj() != FALSZ && argument2.wykonaj() != FALSZ) {
            return PRAWDA;
        }
        else {
            return FALSZ;
        }
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(argument1.toJava())
          .append(" && ")
          .append(argument2.toJava());

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"And\",\n")
          .append("\"argument1\":")
          .append(argument1.toJson())
          .append(",\n\"argument2\":")
          .append(argument2.toJson())
          .append("\n}");

        return sb.toString();
    }
}
