package org.venutolo.texttablebuilder;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderRowAlignmentsTest extends TextTableBuilderTest {

    @Test
    public void testSetAndGetRowAlignments() {
        emptyTextTableBuilder.setRowAlignments(rowAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                rowAlignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetAndGetRowAlignmentsArray() {
        emptyTextTableBuilder.setRowAlignmentsFromArray(rowAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                rowAlignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = rowAlignments.get(0);
        emptyTextTableBuilder.setRowAlignments(rowAlignments);
        rowAlignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = rowAlignmentsArray[0];
        emptyTextTableBuilder.setRowAlignmentsFromArray(rowAlignmentsArray);
        rowAlignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testGetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = rowAlignments.get(0);
        emptyTextTableBuilder.setRowAlignments(rowAlignments);
        emptyTextTableBuilder.getRowAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignments(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetRowAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignmentsFromArray();
    }

    @Test
    public void testClearRowAlignments() {
        populatedTextTableBuilder.clearRowAlignments();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getRowAlignments().isEmpty()
        );
    }

}
