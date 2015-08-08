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
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.SETTER_NO_DEFENSIVE_COPY;

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
        headerAlignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.RIGHT};
        headerAlignments = Arrays.asList(headerAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setHeaderAlignmentsList(headerAlignments);
    }

    @Test
    public void testSetAndGetHeaderAlignmentsList() {
        emptyTextTableBuilder.setHeaderAlignmentsList(headerAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetAndGetHeaderAlignmentsArray() {
        emptyTextTableBuilder.setHeaderAlignments(headerAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsListForDefensiveCopying() {
        final Alignment expected = headerAlignments.get(0);
        emptyTextTableBuilder.setHeaderAlignmentsList(headerAlignments);
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
        emptyTextTableBuilder.setHeaderAlignments(headerAlignmentsArray);
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
        emptyTextTableBuilder.setHeaderAlignmentsList(headerAlignments);
        emptyTextTableBuilder.getHeaderAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsListForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignmentsList(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetHeaderAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments();
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
