package edu.mimuw.po.marysia.proj2;

import edu.mimuw.po.marysia.proj2.instrukcje.Blok;
import edu.mimuw.po.marysia.proj2.instrukcje.If;
import edu.mimuw.po.marysia.proj2.instrukcje.Instrukcja;
import edu.mimuw.po.marysia.proj2.instrukcje.While;
import edu.mimuw.po.marysia.proj2.json.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {

    @Test
    public void WhileTest () {
        String json =
                "{" + "\"typ\":\"Blok\"," +
                        "\"instrukcje\":[" +
                        "{\"typ\":\"While\"," +
                        "\"warunek\":{" +
                        "\"typ\":\"True\"" +
                        "}," +
                        "\"blok\":{" +
                        "\"typ\":\"Blok\"," +
                        "\"instrukcje\":[" +
                        "{" +
                        "\"typ\":\"True\"" +
                        "}" +
                        "]" + "}" + "}" +

                        "]" + "}";

        JsonParser parser = new JsonParser();
        Blok blok = parser.parse(json, false);

        assertEquals(1, blok.getInstrukcje().size());

        Instrukcja instrukcja = blok.getInstrukcje().get(0);
        assertTrue(instrukcja instanceof While);
    }

    @Test
    public void IfTest () {
        String json =
                "{" + "\"typ\":\"Blok\"," +
                        "\"instrukcje\":["    +
                        "{\"typ\":\"If\"," +
                        "\"warunek\":{" +
                        "\"typ\":\"True\"" +
                        "}," +
                        "\"blok_prawda\":{" +
                                "\"typ\":\"Blok\"," +
                                "\"instrukcje\":[" +
                                "{" +
                                "\"typ\":\"True\"" +
                                "}" +
                                "]"+ "}," +
                        "\"blok_falsz\":{" +
                                "\"typ\":\"Blok\"," +
                                "\"instrukcje\":[" +
                                "{" +
                                "\"typ\":\"True\"" +
                                "}" +
                                "]"+ "}" +
                        "}" +
                        "]" + "}";

        JsonParser parser = new JsonParser();
        Blok blok = parser.parse(json, false);

        assertEquals(1, blok.getInstrukcje().size());

        Instrukcja instrukcja =blok.getInstrukcje().get(0);
        assertTrue(instrukcja instanceof If);

    }
}
