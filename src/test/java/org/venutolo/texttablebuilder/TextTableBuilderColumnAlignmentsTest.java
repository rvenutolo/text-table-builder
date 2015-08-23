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
public class TextTableBuilderColumnAlignmentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Alignment[] columnAlignmentsArray;

    private List<Alignment> columnAlignments;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        columnAlignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.RIGHT};
        columnAlignments = Arrays.asList(columnAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setColumnAlignmentsInternal(columnAlignments);
    }

    @Test
    public void testGetColumnAlignmentsForDefensiveCopying() {
        final Alignment expected = columnAlignments.get(0);
        populatedTextTableBuilder.getColumnAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                populatedTextTableBuilder.getColumnAlignments().get(0)
        );
    }

    @Test
    public void testSetColumnAlignmentsCollection() {
        emptyTextTableBuilder.setColumnAlignmentsInternal(columnAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                columnAlignments,
                emptyTextTableBuilder.getColumnAlignments()
        );
    }

    @Test
    public void testSetColumnAlignmentsArray() {
        emptyTextTableBuilder.setColumnAlignments(columnAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                columnAlignments,
                emptyTextTableBuilder.getColumnAlignments()
        );
    }

    @Test
    public void testSetColumnAlignmentsCollectionForDefensiveCopying() {
        final Alignment expected = columnAlignments.get(0);
        emptyTextTableBuilder.setColumnAlignmentsInternal(columnAlignments);
        columnAlignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getColumnAlignments().get(0)
        );
    }

    @Test
    public void testSetColumnAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = columnAlignmentsArray[0];
        emptyTextTableBuilder.setColumnAlignments(columnAlignmentsArray);
        columnAlignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getColumnAlignments().get(0)
        );
    }

    @Test
    public void testSetColumnAlignmentsCollectionForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setColumnAlignmentsInternal(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetColumnAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setColumnAlignments();
    }

    @Test
    public void testSetColumnAlignmentsCollectionForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setColumnAlignmentsInternal(null);
    }

    @Test
    public void testSetColumnAlignmentsArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setColumnAlignments((Alignment[]) null);
    }

    @Test
    public void testSetColumnAlignmentsCollectionForNullAlignment() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setColumnAlignmentsInternal(Arrays.asList(null, Alignment.LEFT));
    }

    @Test
    public void testSetColumnAlignmentsArrayForNullAlignment() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setColumnAlignments(null, Alignment.LEFT);
    }

    @Test
    public void testClearColumnAlignments() {
        populatedTextTableBuilder.clearColumnAlignments();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getColumnAlignments().isEmpty()
        );
    }

}
