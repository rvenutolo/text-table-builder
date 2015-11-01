package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.CURVED;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.HEAVY;
import static org.venutolo.texttablebuilder.TestStrings.EQUAL_ITSELF;
import static org.venutolo.texttablebuilder.TestStrings.EQUAL_TO_INSTANCE_WITH_SAME_VALUES;
import static org.venutolo.texttablebuilder.TestStrings.HASH_CODE_MUST_BE_DISTINCT;
import static org.venutolo.texttablebuilder.TestStrings.HASH_CODE_MUST_BE_SAME;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_RANDOM_OBJECT;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_TO_NULL;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderEqualsAndHashCodeTest {

    private TextTableBuilder textTableBuilder1;

    private TextTableBuilder textTableBuilder2;

    @Before
    public void setUp() {
        textTableBuilder1 = new TextTableBuilder()
                .setRepeatHeadersEveryXRows(5)
                .setRepeatHeadersAtBottom(true)
                .setShowRowNums(true)
                .setBoxDrawingCharacters(HEAVY)
                .setLinePrepender(">")
                .setLineAppender("<")
                .setNullColumnReplacement("NULL")
                .setHeaderAlignments(LEFT, RIGHT)
                .setColumnAlignments(RIGHT, LEFT)
                .setHeaders("h1", "h2")
                .addRow("r1c1", "r1c2")
                .addRow("r2c1", "r2c2");
        textTableBuilder2 = new TextTableBuilder()
                .setRepeatHeadersEveryXRows(5)
                .setRepeatHeadersAtBottom(true)
                .setShowRowNums(true)
                .setBoxDrawingCharacters(HEAVY)
                .setLinePrepender(">")
                .setLineAppender("<")
                .setNullColumnReplacement("NULL")
                .setHeaderAlignments(LEFT, RIGHT)
                .setColumnAlignments(RIGHT, LEFT)
                .setHeaders("h1", "h2")
                .addRow("r1c1", "r1c2")
                .addRow("r2c1", "r2c2");
    }

    /*========================================================================
     * TESTS FOR equals(Object)
     *========================================================================*/

    @Test
    public void testNotEqualToNull() {
        assertNotEquals(
                NOT_EQUAL_TO_NULL,
                textTableBuilder1,
                null
        );
    }

    @Test
    public void testNotEqualRandomObject() {
        assertNotEquals(
                NOT_EQUAL_RANDOM_OBJECT,
                textTableBuilder1,
                new Object()
        );
    }

    @Test
    public void testEqualItself() {
        assertEquals(
                EQUAL_ITSELF,
                textTableBuilder1,
                textTableBuilder1
        );
    }

    @Test
    public void testEqualToSameProperties() {
        assertEquals(
                EQUAL_TO_INSTANCE_WITH_SAME_VALUES,
                textTableBuilder1,
                textTableBuilder2
        );
    }

    @Test
    public void testNotEqualToDifferentHeaderAlignments() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setHeaderAlignments(RIGHT, LEFT)
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.clearHeaderAlignments()
        );
    }

    @Test
    public void testNotEqualToDifferentColumnAlignments() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setColumnAlignments(LEFT, RIGHT)
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.clearColumnAlignments()
        );
    }

    @Test
    public void testNotEqualToDifferentHeaders() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setHeaders("H1", "H2")
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.clearHeaders()
        );
    }

    @Test
    public void testNotEqualToDifferentRows() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setHeaders("r2c1", "r2c2")
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.clearRows()
        );
    }

    @Test
    public void testNotEqualToDifferentBoxDrawingCharacters() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setBoxDrawingCharacters(CURVED)
        );
    }

    @Test
    public void testNotEqualToDifferentRepeatHeadersEveryXRows() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setRepeatHeadersEveryXRows(10)
        );
    }

    @Test
    public void testNotEqualToDifferentRepeatHeadersAtBottom() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setRepeatHeadersAtBottom(false)
        );
    }

    @Test
    public void testNotEqualToDifferentShowRowNums() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setShowRowNums(false)
        );
    }

    @Test
    public void testNotEqualToDifferentLinePrepender() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setLinePrepender("X")
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setLinePrepender(null)
        );
    }

    @Test
    public void testNotEqualToDifferentLineAppender() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setLineAppender("X")
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setLineAppender(null)
        );
    }

    @Test
    public void testNotEqualToDifferentNullColumnReplacement() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setNullColumnReplacement("X")
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                textTableBuilder1,
                textTableBuilder2.setNullColumnReplacement(null)
        );
    }

    /*========================================================================
     * TESTS FOR hashCode()
     *========================================================================*/

    @Test
    public void testHashCodeSameForEqualInstances() {
        assertEquals(
                HASH_CODE_MUST_BE_SAME,
                textTableBuilder1.hashCode(),
                textTableBuilder2.hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentHeaderAlignments() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setHeaderAlignments(RIGHT, LEFT).hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.clearHeaderAlignments().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentColumnAlignments() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setColumnAlignments(LEFT, RIGHT).hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.clearColumnAlignments().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentHeaders() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setHeaders("H1", "H2").hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.clearHeaders().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentRows() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setHeaders("r2c1", "r2c2").hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.clearRows().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentBoxDrawingCharacters() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setBoxDrawingCharacters(CURVED).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentRepeatHeadersEveryXRows() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setRepeatHeadersEveryXRows(10).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentRepeatHeadersAtBottom() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setRepeatHeadersAtBottom(false).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentShowRowNums() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setShowRowNums(false).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentLinePrepender() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setLinePrepender("X").hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setLinePrepender(null).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentLineAppender() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setLineAppender("X").hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setLineAppender(null).hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentNullColumnReplacement() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setNullColumnReplacement("X").hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                textTableBuilder1.hashCode(),
                textTableBuilder2.setNullColumnReplacement(null).hashCode()
        );
    }

}
