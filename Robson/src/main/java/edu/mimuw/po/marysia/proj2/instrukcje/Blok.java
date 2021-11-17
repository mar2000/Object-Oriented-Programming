package edu.mimuw.po.marysia.proj2.instrukcje;

import java.util.Iterator;
import java.util.List;

public class Blok implements Instrukcja {

    private List<Instrukcja> instrukcje;
    boolean nieLisc; // nielisc = true oznacza że to nie jest lisc

    public Blok() {
    }

    public void setNieLisc(boolean nieLisc) {
        this.nieLisc = nieLisc;
    }

    public Blok(List<Instrukcja> instrukcje) {
        this.instrukcje = instrukcje;
    }

    public List<Instrukcja> getInstrukcje() {
        return instrukcje;
    }

    public double wykonaj() {
        double wartosc = 0.0;

        for(Instrukcja instrukcja : instrukcje) {
            wartosc = instrukcja.wykonaj();
        }

        return wartosc;
    }

    public String toJava() {
        StringBuilder sb = new StringBuilder();

        sb.append(" {\n");

        Iterator<Instrukcja> iteratorInstrukcji = instrukcje.iterator();
            while(iteratorInstrukcji.hasNext()) {
                Instrukcja instrukcja = iteratorInstrukcji.next();
                if(!iteratorInstrukcji.hasNext() && instrukcja instanceof  Wyrazenie) {
                    sb.append("return ");
                }
                sb.append(instrukcja.toJava());
                if(!iteratorInstrukcji.hasNext() && instrukcja instanceof  Wyrazenie) {
                    sb.append(";\n");
                }
            }
            sb.append("}\n");

        return sb.toString();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n")
          .append("\"typ\":\"Blok\",\n")
          .append("\"instrukcje\":[\n");

        String separator ="";
        for(Instrukcja instrukcja : instrukcje) {
            sb.append(separator);
            sb.append(instrukcja.toJson());
            separator =",";
        }

        sb.append("]\n")
          .append("}");

        return sb.toString();
    }

}
