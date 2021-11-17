package edu.mimuw.po.marysia.proj2.instrukcje;

import static edu.mimuw.po.marysia.proj2.instrukcje.Falsz.FALSZ;

public class If implements Instrukcja {

    private final Instrukcja warunek;
    private final Blok blok_prawda;
    private final Blok blok_falsz;


    public If(Instrukcja warunek, Blok blok_prawda, Blok blok_falsz) {
        this.warunek = warunek;
        this.blok_prawda = blok_prawda;
        this.blok_falsz = blok_falsz;

    }

    public double wykonaj() {
        if( warunek.wykonaj() != FALSZ ) {
            return blok_prawda.wykonaj();
        }
        else if(blok_falsz != null) {
            return blok_falsz.wykonaj();
        }
        else {
            return 0.0;
        }
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nif(")
          .append(warunek.toJava())
          .append(")")
          .append(blok_prawda.toJava());
        if(blok_falsz != null) {
            sb.append(" else")
              .append(blok_falsz.toJava());
        }

		return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"If\",\n")
          .append("\"warunek\":").append(warunek.toJson())
          .append(",\n\"blok_prawda\":").append(blok_prawda.toJson());
        if (blok_falsz != null) {
            sb.append(",\n\"blok_falsz\":").append(blok_falsz.toJson());
        }
        sb.append("\n}");
        
		return sb.toString();
    }
}
