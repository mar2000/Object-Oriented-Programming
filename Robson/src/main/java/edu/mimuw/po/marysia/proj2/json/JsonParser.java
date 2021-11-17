package edu.mimuw.po.marysia.proj2.json;

import com.google.gson.*;
import edu.mimuw.po.marysia.proj2.instrukcje.*;

import java.lang.reflect.Type;

public class JsonParser {

    private  class InstrukcjaDeserializer implements JsonDeserializer<Instrukcja> {

        public Instrukcja deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = (JsonObject) json;
            String typ = jsonObject.get("typ").getAsString();

            if("Plus".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Dodawanie(wyrazenie1, wyrazenie2);
            }

            if("Minus".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Odejmowanie(wyrazenie1, wyrazenie2);
            }

            if("Razy".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Mnozenie(wyrazenie1, wyrazenie2);
            }

            if("Dzielenie".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Dzielenie(wyrazenie1, wyrazenie2);
            }

            if("Liczba".equals(typ)) {
                Double wartosc = jsonObject.get("wartosc").getAsDouble();
                return new Liczba(wartosc);
            }

            if ("If".equals(typ)) {
                JsonObject warunek = (JsonObject) jsonObject.get("warunek");
                Instrukcja wyrazenie1 = deserialize(warunek, typeOfT, context);
                String blok_prawda = jsonObject.get("blok_prawda").toString();
                JsonElement jsonBlok_falsz = jsonObject.get("blok_falsz");

                Blok wyrazenie3 = jsonBlok_falsz != null ? parse(jsonBlok_falsz.toString(), false) : null;

                Blok wyrazenie2 = parse(blok_prawda, false);

                return new If(wyrazenie1, wyrazenie2, wyrazenie3);
            }

            if ("While".equals(typ)) {
                JsonObject warunek = (JsonObject) jsonObject.get("warunek");
                String blok = jsonObject.get("blok").toString();
                Instrukcja wyrazenie1 = deserialize(warunek, typeOfT, context);
                Blok wyrazenie2 = parse(blok, true);
                return new While(wyrazenie1, wyrazenie2);
            }

            if ("Przypisanie".equals(typ)) {
                JsonObject wartosc = (JsonObject) jsonObject.get("wartosc");
                Instrukcja wyrazenie1 = deserialize(wartosc, typeOfT, context);
                String nazwa = jsonObject.get("nazwa").getAsString();
                return new Przypisanie(nazwa, wyrazenie1);
            }

            if("And".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new And(wyrazenie1, wyrazenie2);
            }

            if("Or".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Lub(wyrazenie1, wyrazenie2);
            }

            if("<".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Mniejsze(wyrazenie1, wyrazenie2);
            }

            if("<=".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new MniejszeRowne(wyrazenie1, wyrazenie2);
            }

            if(">".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Wieksze(wyrazenie1, wyrazenie2);
            }

            if(">=".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new WiekszeRowne(wyrazenie1, wyrazenie2);
            }

            if("==".equals(typ)) {
                JsonObject argument1 = (JsonObject) jsonObject.get("argument1");
                JsonObject argument2 = (JsonObject) jsonObject.get("argument2");
                Instrukcja wyrazenie1 = deserialize(argument1, typeOfT, context);
                Instrukcja wyrazenie2 = deserialize(argument2, typeOfT, context);
                return new Rowne(wyrazenie1, wyrazenie2);
            }

            if("Not".equals(typ)) {
                JsonObject argument = (JsonObject) jsonObject.get("argument");
                Instrukcja wyrazenie = deserialize(argument, typeOfT, context);
                return new Negacja(wyrazenie);
            }

            if("True".equals(typ)) {
                return new Prawda();
            }

            if("False".equals(typ)) {
                return new Falsz();
            }

            if("Zmienna".equals(typ)) {
                String nazwa = jsonObject.get("nazwa").getAsString();
                return new Zmienna(nazwa);
            }

            // Kiedy natrafimy na nieznaną instrukcję
            return null;
        }

    }

    public Blok parse(String json, boolean nieLisc) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instrukcja.class, new InstrukcjaDeserializer());

        Gson gson = gsonBuilder.create();
        Blok blok = gson.fromJson(json, Blok.class);
        blok.setNieLisc(nieLisc);

        return blok;
    }

}
