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

    private List<Alignment> alignments;

    private Alignment[] alignmentsArray;

    @Before
    public void setUp() {
        textTableBuilder = new TextTableBuilder();
        alignments = Arrays.asList(Alignment.LEFT, Alignment.RIGHT);
        alignmentsArray = alignments.toArray(new Alignment[alignments.size()]);
    }

    @After
    public void tearDown() {
        textTableBuilder = null;
        alignments = null;
        alignmentsArray = null;
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
        textTableBuilder.setHeaderAlignments(alignments);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetHeaderAlignmentsArray() {
        textTableBuilder.setHeaderAlignments(alignmentsArray);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsForDefensiveCopying() {
        textTableBuilder.setHeaderAlignments(alignments);
        alignments.set(0, Alignment.RIGHT);
        assertEquals(
                "setter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testGetHeaderAlignmentsForDefensiveCopying() {
        textTableBuilder.setHeaderAlignments(alignments);
        textTableBuilder.getHeaderAlignments().set(0, Alignment.RIGHT);
        assertEquals(
                "getter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetAndGetRowAlignments() {
        textTableBuilder.setRowAlignments(alignments);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetRowAlignmentsArray() {
        textTableBuilder.setRowAlignments(alignmentsArray);
        assertEquals(
                "value returned from getter is not equal to that given to setter",
                alignments,
                textTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetRowAlignmentsForDefensiveCopying() {
        textTableBuilder.setRowAlignments(alignments);
        alignments.set(0, Alignment.RIGHT);
        assertEquals(
                "setter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testGetRowAlignmentsForDefensiveCopying() {
        textTableBuilder.setRowAlignments(alignments);
        textTableBuilder.getRowAlignments().set(0, Alignment.RIGHT);
        assertEquals(
                "getter did not create a defensive copy",
                Alignment.LEFT,
                textTableBuilder.getRowAlignments().get(0)
        );
    }

}
