package edu.mimuw.po.marysia.proj2.instrukcje;

import static edu.mimuw.po.marysia.proj2.instrukcje.Falsz.FALSZ;

public class While implements Instrukcja {

    private final Instrukcja warunek;
    private final Blok blok;

    public While(Instrukcja warunek, Blok blok) {
        this.warunek = warunek;
        this.blok = blok;
    }

    public double wykonaj() {
        while( warunek.wykonaj() != FALSZ ) {
             blok.wykonaj();
        }
        return 0;
    }


    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nwhile(")
          .append(warunek.toJava())
          .append(")")
          .append(blok.toJava());

        return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"While\",\n")
          .append("\"warunek\":")
          .append(warunek.toJson())
          .append(",\n\"blok\":")
          .append(blok.toJson())
          .append("\n}");

        return sb.toString();
    }
}
