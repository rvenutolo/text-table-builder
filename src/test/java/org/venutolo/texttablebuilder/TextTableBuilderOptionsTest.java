package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.NOT_EMPTY_AFTER_CLEAR;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderOptionsTest {

    private static final boolean[] trueFalseArray = {true, false};

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setPrepender("PREPENDER")
                .setAppender("APPENDER");
    }

    @Test
    public void testSetAndGetShowRowNums() {
        for (final boolean showRowNums : trueFalseArray) {
            emptyTextTableBuilder.setShowRowNums(showRowNums);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    showRowNums,
                    emptyTextTableBuilder.getShowRowNums()
            );
        }
    }

    @Test
    public void testShowRowNums() {
        emptyTextTableBuilder.showRowNums();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getShowRowNums()
        );
    }

    @Test
    public void testSetAndGetRepeatHeaders() {
        final int numRows = 10;
        emptyTextTableBuilder.repeatHeaders(numRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numRows,
                emptyTextTableBuilder.getRepeatHeaders()
        );
    }

    @Test
    public void testSetAndGetRepeatHeadersAtBottom() {
        for (final boolean repeatHeadersAtBottom : trueFalseArray) {
            emptyTextTableBuilder.setRepeatHeadersAtBottom(repeatHeadersAtBottom);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    repeatHeadersAtBottom,
                    emptyTextTableBuilder.getRepeatHeadersAtBottom()
            );
        }
    }

    @Test
    public void testRepeatHeadersAtBottom() {
        emptyTextTableBuilder.repeatHeadersAtBottom();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getRepeatHeadersAtBottom()
        );
    }

    @Test
    public void testSetAndGetPrepender() {
        final String prepender = "prepender";
        emptyTextTableBuilder.setPrepender(prepender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                prepender,
                emptyTextTableBuilder.getPrepender()
        );
    }

    @Test
    public void testClearPrepender() {
        populatedTextTableBuilder.clearPrepender();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getPrepender().isEmpty()
        );
    }

    @Test
    public void testSetAndGetAppender() {
        final String appenderString = "appender";
        emptyTextTableBuilder.setAppender(appenderString);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                appenderString,
                emptyTextTableBuilder.getAppender()
        );
    }

    @Test
    public void testClearAppender() {
        populatedTextTableBuilder.clearAppender();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getAppender().isEmpty()
        );
    }

    @Test
    public void testSetAndGetReplaceNullWithEmptyString() {
        for (final boolean replaceNullWithEmptyString : trueFalseArray) {
            emptyTextTableBuilder.setReplaceNullWithEmptyString(replaceNullWithEmptyString);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    replaceNullWithEmptyString,
                    emptyTextTableBuilder.getReplaceNullWithEmptyString()
            );
        }
    }

    @Test
    public void testReplaceNullWithEmptyString() {
        emptyTextTableBuilder.replaceNullWithEmptyString();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getReplaceNullWithEmptyString()
        );
    }

}
