package edu.mimuw.po.marysia.proj2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.mimuw.po.marysia.proj2.instrukcje.Blok;
import edu.mimuw.po.marysia.proj2.json.JsonParser;


public class Robson {

    static String kod_w_formacie_json;
    static String kod_w_formacie_java;

    static Map<String, Double> zmienneGlobalne = new HashMap<>();

    static Blok blokGlowny;

    public static Map<String, Double> getZmienneGlobalne() {
        return zmienneGlobalne;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // podczas kompilacji podajemy:
        // nazwe pliku w formacie json - plik wejciowy
        // nawa klasy w javie - plik wyjsiowy do javy
        // nawa programu w json - plik wyjsciowy do jsona

        if (args.length != 3) {
            System.out.println("Podano złą liczbę plików");
            System.exit(7);
        }

        if (!((args[0])).endsWith(".json")) {
            System.out.println("Plik wejściowy ma złe rozszerzenie");
            System.exit(7);
        }


        // Wczytujemy program w języku ROBSON zapisanego w formacieJSON
        String input_json = args[0];
        try {
            fromJSON(input_json);
        }
        catch (Exception exception) {
            System.out.println("wystąpił błąd podczas wczytywanie pliku wejściowego");
            System.exit(7);
        }

        // Wykonujemy program i wypisujemy jego wartosc
        System.out.println(wykonaj());

        // Zapisujemy program do javy
        String output_java = args[1];
        kod_w_formacie_java = convertingToJAVA(output_java);
        toJAVA(output_java);

        // Zapisujemy program do jsona
        String output_json = args[2];
        kod_w_formacie_json = convertingToJSON();
        toJSON(output_json);

    }

    static String convertingToJAVA(String nazwaProgramu) {
        StringBuilder sb = new StringBuilder();

        Set<String> zmienne = zmienneGlobalne.keySet();

        sb.append("public class ")
          .append(nazwaProgramu)
          .append(" { \n\n");

        for(String zmienna : zmienne) {
            sb.append("public static Double ")
              .append(zmienna)
              .append("; \n");
        }

        sb.append("\n")
          .append("public static void main(String[] args) { \n\n")
          .append("System.out.println(\"\" + program()); \n\n} \n\n");

        sb.append("public static double program()")
          .append(blokGlowny.toJava())
          .append("\n}");

        return sb.toString();
    }

    static String convertingToJSON() {
        return blokGlowny.toJson();
    }

    static void fromJSON(String filename) throws IOException {
        kod_w_formacie_json  = Files.readString(Path.of(filename), StandardCharsets.UTF_8);

        JsonParser parser = new JsonParser();
        blokGlowny = parser.parse(kod_w_formacie_json, false);
    }

    static void toJAVA(String filename) throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter(filename + ".java");
        zapis.println(kod_w_formacie_java);
        zapis.close();
    }

    static void toJSON(String filename) throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter(filename + ".json");
        zapis.println(kod_w_formacie_json);
        zapis.close();
    }

    static double wykonaj() {
        return blokGlowny.wykonaj();
    }

}


