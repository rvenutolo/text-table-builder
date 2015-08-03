package org.venutolo.texttablebuilder;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderHeaderAlignmentsTest extends TextTableBuilderTest {

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
