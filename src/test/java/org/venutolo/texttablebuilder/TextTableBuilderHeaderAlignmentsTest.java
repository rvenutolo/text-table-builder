package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TestStrings.SETTER_NO_DEFENSIVE_COPY;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderHeaderAlignmentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Alignment[] headerAlignmentsArray;

    private List<Alignment> headerAlignments;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        headerAlignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.LEFT};
        headerAlignments = Arrays.asList(headerAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setHeaderAlignments(headerAlignments);
    }

    @Test
    public void testSetAndGetHeaderAlignments() {
        emptyTextTableBuilder.setHeaderAlignments(headerAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetAndGetHeaderAlignmentsArray() {
        emptyTextTableBuilder.setHeaderAlignmentsFromArray(headerAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = headerAlignments.get(0);
        emptyTextTableBuilder.setHeaderAlignments(headerAlignments);
        headerAlignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = headerAlignmentsArray[0];
        emptyTextTableBuilder.setHeaderAlignmentsFromArray(headerAlignmentsArray);
        headerAlignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testGetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = headerAlignments.get(0);
        emptyTextTableBuilder.setHeaderAlignments(headerAlignments);
        emptyTextTableBuilder.getHeaderAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetHeaderAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignmentsFromArray();
    }

    @Test
    public void testClearHeaderAlignments() {
        populatedTextTableBuilder.clearHeaderAlignments();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getHeaderAlignments().isEmpty()
        );
    }

}
