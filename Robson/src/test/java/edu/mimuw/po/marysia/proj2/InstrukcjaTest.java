package edu.mimuw.po.marysia.proj2;

import edu.mimuw.po.marysia.proj2.instrukcje.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstrukcjaTest {

    @Test
    public void IfTestPrawda(){
        Instrukcja prawda = new Prawda();

        List<Instrukcja> listaFalsz = new ArrayList();
        listaFalsz.add(new Falsz());
        Blok blokFalsz = new Blok(listaFalsz);

        List<Instrukcja> listaPrawda = new ArrayList();
        listaPrawda.add(new Prawda());
        Blok blokPrawda = new Blok(listaPrawda);
        If jesli = new If(prawda, blokPrawda, blokFalsz);

        double x = jesli.wykonaj();
        assertEquals (1.0, x, 0.5);
    }

    @Test
    public void IfTestFalsz(){
        Instrukcja falsz = new Falsz();

        List<Instrukcja> listaFalsz = new ArrayList();
        listaFalsz.add(new Falsz());
        Blok blokFalsz = new Blok(listaFalsz);

        List<Instrukcja> listaPrawda = new ArrayList();
        listaPrawda.add(new Prawda());
        Blok blokPrawda = new Blok(listaPrawda);
        If jesli = new If(falsz, blokPrawda, blokFalsz);

        double x = jesli.wykonaj();
        assertEquals (0.0, x, 0.5);
    }

    @Test
    public void WhileTest(){
        Instrukcja falsz = new Falsz();

        List<Instrukcja> lista = new ArrayList();
        lista.add(new Prawda());
        Blok blok = new Blok(lista);
        While jesli = new While(falsz, blok);

        double x = jesli.wykonaj();
        assertEquals (0.0, x, 0.5);
    }

}
