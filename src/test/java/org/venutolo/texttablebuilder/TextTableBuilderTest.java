package org.venutolo.texttablebuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderTest {

    private final Collection<BoxDrawingCharacters> boxDrawingCharactersCollection =
            Collections.unmodifiableCollection(
                    Arrays.asList(
                            BoxDrawingCharacters.ASCII,
                            BoxDrawingCharacters.CURVED,
                            BoxDrawingCharacters.DOUBLE,
                            BoxDrawingCharacters.HEAVY,
                            BoxDrawingCharacters.LIGHT,
                            BoxDrawingCharacters.NULLS,
                            BoxDrawingCharacters.SPACES
                    )
            );

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TextTableBuilder textTableBuilder;

    @Before
    public void setUp() {
        textTableBuilder = new TextTableBuilder();
    }

    @After
    public void tearDown() {
        textTableBuilder = null;
    }

    @Test
    public void testSetAndGetBoxDrawingCharacters() {
        for (final BoxDrawingCharacters boxDrawingCharacters : boxDrawingCharactersCollection) {
            textTableBuilder.setBoxDrawingCharacters(boxDrawingCharacters);
            assertEquals(
                    "value returned from getter is not equal to that given to setter",
                    boxDrawingCharacters,
                    textTableBuilder.getBoxDrawingCharacters()
            );
        }
    }

    @Test
    public void testSetAndGetHeaderAlignments() {
        final Alignment[] alignments = {Alignment.LEFT, Alignment.RIGHT};
        textTableBuilder.setHeaderAlignments(alignments);
        assertArrayEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetAndGetHeaderAlignmentsForDefensiveCopying() {
        final Alignment[] setAlignments = {Alignment.LEFT, Alignment.RIGHT};
        textTableBuilder.setHeaderAlignments(setAlignments);
        setAlignments[0] = Alignment.RIGHT;
        assertEquals(
                "setter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getHeaderAlignments()[0]
        );
        final Alignment[] getAlignments = textTableBuilder.getHeaderAlignments();
        getAlignments[0] = Alignment.RIGHT;
        assertEquals(
                "getter did not return a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getHeaderAlignments()[0]
        );
    }

    @Test
    public void testSetAndGetHeaderAlignmentsList() {
        final List<Alignment> alignments = Arrays.asList(Alignment.LEFT, Alignment.RIGHT);
        textTableBuilder.setHeaderAlignments(alignments);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                Arrays.asList(textTableBuilder.getHeaderAlignments())
        );
    }

    @Test
    public void testSetAndGetRowAlignments() {
        final Alignment[] alignments = {Alignment.LEFT, Alignment.RIGHT};
        textTableBuilder.setRowAlignments(alignments);
        assertArrayEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetAndGetRowAlignmentsForDefensiveCopying() {
        final Alignment[] setAlignments = {Alignment.LEFT, Alignment.RIGHT};
        textTableBuilder.setRowAlignments(setAlignments);
        setAlignments[0] = Alignment.RIGHT;
        assertEquals(
                "setter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getRowAlignments()[0]
        );
        final Alignment[] getAlignments = textTableBuilder.getRowAlignments();
        getAlignments[0] = Alignment.RIGHT;
        assertEquals(
                "getter did not return a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getRowAlignments()[0]
        );
    }

    @Test
    public void testSetAndGetRowAlignmentsList() {
        final List<Alignment> alignments = Arrays.asList(Alignment.LEFT, Alignment.RIGHT);
        textTableBuilder.setRowAlignments(alignments);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                Arrays.asList(textTableBuilder.getRowAlignments())
        );
    }

}
