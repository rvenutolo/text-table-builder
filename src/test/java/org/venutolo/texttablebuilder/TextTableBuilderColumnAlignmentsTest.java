package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;
import static org.venutolo.texttablebuilder.TestStrings.ALIGNMENTS_EMPTY;
import static org.venutolo.texttablebuilder.TestStrings.ALIGNMENTS_NOT_NULL;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_ALIGNMENT;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TestStrings.SETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.SHOULD_NOT_REACH_THIS_POINT;

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
        columnAlignmentsArray = new Alignment[]{LEFT, RIGHT};
        columnAlignments = Arrays.asList(columnAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setColumnAlignments(columnAlignments);
    }

    /*========================================================================
     * TESTS FOR getColumnAlignments()
     *========================================================================*/

    @Test
    public void testGetColumnAlignmentsWhenNotSet() {
        final List<Alignment> emptyColumnAlignments = emptyTextTableBuilder.getColumnAlignments();
        assertNotNull(
                ALIGNMENTS_NOT_NULL,
                emptyColumnAlignments
        );
        assertTrue(
                ALIGNMENTS_EMPTY,
                emptyColumnAlignments.isEmpty()
        );
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

    /*========================================================================
     * TESTS FOR setColumnAlignments(Collection<Alignment>)
     *========================================================================*/

    @Test
    public void testSetColumnAlignments() {
        emptyTextTableBuilder.setColumnAlignments(columnAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                columnAlignments,
                emptyTextTableBuilder.getColumnAlignments()
        );
    }

    @Test
    public void testSetColumnAlignmentsForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setColumnAlignments((Collection<Alignment>) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetColumnAlignmentsForNullAlignment() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setColumnAlignments(Arrays.asList(null, LEFT));
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetColumnAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setColumnAlignments(Collections.<Alignment>emptyList());
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetColumnAlignmentsForDefensiveCopying() {
        final Alignment expected = columnAlignments.get(0);
        emptyTextTableBuilder.setColumnAlignments(columnAlignments);
        columnAlignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getColumnAlignments().get(0)
        );
    }

    /*========================================================================
     * TESTS FOR setColumnAlignments(Alignment...)
     *========================================================================*/

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
    public void testSetColumnAlignmentsArrayForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setColumnAlignments((Alignment[]) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetColumnAlignmentsArrayForNullAlignment() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setColumnAlignments(null, LEFT);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetColumnAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setColumnAlignments();
        fail(SHOULD_NOT_REACH_THIS_POINT);
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

    /*========================================================================
     * TESTS FOR clearColumnAlignments()
     *========================================================================*/

    @Test
    public void testClearColumnAlignments() {
        populatedTextTableBuilder.clearColumnAlignments();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getColumnAlignments().isEmpty()
        );
    }

}
