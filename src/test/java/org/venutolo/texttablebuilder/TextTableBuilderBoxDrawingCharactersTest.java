package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderBoxDrawingCharactersTest {

    private static final Collection<BoxDrawingCharacters> boxDrawingCharactersCollection =
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

    private TextTableBuilder emptyTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
    }

    @Test
    public void testSetAndGetBoxDrawingCharacters() {
        for (final BoxDrawingCharacters boxDrawingCharacters : boxDrawingCharactersCollection) {
            emptyTextTableBuilder.setBoxDrawingCharacters(boxDrawingCharacters);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    boxDrawingCharacters,
                    emptyTextTableBuilder.getBoxDrawingCharacters()
            );
        }
    }

}
