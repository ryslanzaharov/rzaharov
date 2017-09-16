package ru.job4j.polymorphism.task786;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {

    @Test
    public void whenDrawtTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        StringBuilder sbtriangle = new StringBuilder();
        sbtriangle.append("  +\n");
        sbtriangle.append(" + +\n");
        sbtriangle.append("+++++\r\n");
        paint.draw(new Triangle());
        assertThat(out.toString(), is(sbtriangle.toString()));
    }

    @Test
    public void whenDrawtSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        StringBuilder sbsquare = new StringBuilder();
        sbsquare.append("+++++++\n");
        sbsquare.append("+     +\n");
        sbsquare.append("+     +\n");
        sbsquare.append("+++++++\r\n");
        paint.draw(new Square());
        assertThat(out.toString(), is(sbsquare.toString()));
    }
}
