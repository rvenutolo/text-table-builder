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
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_NPE_FOR_NULL_ALIGNMENT;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_NPE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.SETTER_NO_DEFENSIVE_COPY;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderRowAlignmentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Alignment[] rowAlignmentsArray;

    private List<Alignment> rowAlignments;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        rowAlignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.RIGHT};
        rowAlignments = Arrays.asList(rowAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setRowAlignmentsList(rowAlignments);
    }

    @Test
    public void testSetAndGetRowAlignmentsList() {
        emptyTextTableBuilder.setRowAlignmentsList(rowAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                rowAlignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetAndGetRowAlignmentsArray() {
        emptyTextTableBuilder.setRowAlignments(rowAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                rowAlignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetRowAlignmentsListForDefensiveCopying() {
        final Alignment expected = rowAlignments.get(0);
        emptyTextTableBuilder.setRowAlignmentsList(rowAlignments);
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
        emptyTextTableBuilder.setRowAlignments(rowAlignmentsArray);
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
        emptyTextTableBuilder.setRowAlignmentsList(rowAlignments);
        emptyTextTableBuilder.getRowAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsListForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignmentsList(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetRowAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignments();
    }

    @Test
    public void testSetRowAlignmentsListForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRowAlignmentsList(null);
    }

    @Test
    public void testSetRowAlignmentsArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRowAlignments((Alignment[]) null);
    }

    @Test
    public void testSetRowAlignmentsListForNullAlignment() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setRowAlignmentsList(Arrays.asList(null, Alignment.LEFT));
    }

    @Test
    public void testSetRowAlignmentsArrayForNullAlignment() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setRowAlignments(null, Alignment.LEFT);
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
