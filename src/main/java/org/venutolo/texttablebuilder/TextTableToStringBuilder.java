package org.venutolo.texttablebuilder;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;

/**
 * @author Rick Venutolo
 */
final class TextTableToStringBuilder {

    private static final int STRING_BUILDER_INITIAL_CAPACITY = 128;

    private final StringBuilder toStringBuilder = new StringBuilder(STRING_BUILDER_INITIAL_CAPACITY);

    private final int numColumns;

    private final String linePrepender;

    private final String lineAppender;

    private final BoxDrawingCharacters boxDrawingCharacters;

    private final List<String> headerStrings;

    private final List<List<String>> tableStrings;

    private final List<String> horizontalStrings;

    private TextTableToStringBuilder(final TextTableBuilder textTableBuilder) {

        final boolean showRowNums = textTableBuilder.getShowRowNums();
        numColumns = textTableBuilder.getNumColumns() + (showRowNums ? 1 : 0);
        linePrepender = textTableBuilder.getLinePrepender();
        lineAppender = textTableBuilder.getLineAppender();

        boxDrawingCharacters = textTableBuilder.getBoxDrawingCharacters();


        final List<Object> headers = textTableBuilder.getHeaders();
        final List<List<Object>> rows = textTableBuilder.getRows();
        final List<Alignment> headerAlignments = textTableBuilder.getHeaderAlignments();
        final List<Alignment> columnAlignments = textTableBuilder.getColumnAlignments();
        final String nullColumnReplacement = textTableBuilder.getNullColumnReplacement();



        if (showRowNums) {
            headerAlignments.add(0, RIGHT);
            columnAlignments.add(0, RIGHT);
            headers.add(0, "");
            int rowNum = 1;
            for (final List<Object> row : rows) {
                row.add(0, rowNum);
                rowNum++;
            }
        }


        final int[] columnWidths = getColumnWidths(
                numColumns,
                nullColumnReplacement,
                headers,
                rows
        );

        headerStrings = headers.isEmpty()
                        ? null
                        : getPaddedAndAlignedRowStrings(
                                columnWidths,
                                nullColumnReplacement,
                                headers,
                                headerAlignments
                        );

        tableStrings = getPaddedAndAlignedTableStrings(
                columnWidths,
                nullColumnReplacement,
                rows,
                columnAlignments
        );

        horizontalStrings = getHorizontalStrings(
                columnWidths,
                boxDrawingCharacters.getHorizontal()
        );
    }

    private static void checkColumnMaxWidths(
            @Nonnull final int[] columnWidths,
            @Nonnull final String nullColumnReplacement,
            @Nonnull final List<Object> row
    ) {
        assert columnWidths != null;
        assert nullColumnReplacement != null;
        assert row != null;
        assert columnWidths.length == row.size();
        for (int i = 0; i < columnWidths.length; i++) {
            final int currentMaxWidth = columnWidths[i];
            final Object columnObject = row.get(i);
            final String columnString = (columnObject != null)
                                        ? columnObject.toString()
                                        : nullColumnReplacement;
            final int columnWidth = columnString.length();
            if (columnWidth > currentMaxWidth) {
                columnWidths[i] = columnWidth;
            }
        }
    }

    private static int[] getColumnWidths(
            final int numColumns,
            @Nonnull final String nullColumnReplacement,
            @Nonnull final List<Object> headers,
            @Nonnull final List<List<Object>> table
    ) {
        assert numColumns >= 0;
        assert nullColumnReplacement != null;
        assert headers != null;
        assert table != null;
        final int[] columnWidths = new int[numColumns];
        if (!headers.isEmpty()) {
            checkColumnMaxWidths(columnWidths, nullColumnReplacement, headers);
        }
        for (final List<Object> row : table) {
            checkColumnMaxWidths(columnWidths, nullColumnReplacement, row);
        }
        return columnWidths;
    }

    private static String getPaddedAndAlignedColumnString(
            @Nonnull final String columnString,
            @Nonnull final Alignment alignment,
            final int columnWidth
    ) {
        assert columnString != null;
        assert alignment != null;
        assert columnWidth >= 0;
        final String columnStringPlusSpaces = " " + columnString + " ";
        final int columnWidthWithPadding = columnWidth + 2;
        switch (alignment) {
            case LEFT:
                return StringUtils.rightPad(columnStringPlusSpaces, columnWidthWithPadding);
            case RIGHT:
                return StringUtils.leftPad(columnStringPlusSpaces, columnWidthWithPadding);
            default:
                throw new IllegalArgumentException("Unexpected alignment: " + alignment);
        }
    }

    private static List<String> getPaddedAndAlignedRowStrings(
            @Nonnull final int[] columnWidths,
            @Nonnull final String nullColumnReplacement,
            @Nonnull final List<Object> row,
            @Nonnull final List<Alignment> alignments
    ) {
        assert columnWidths != null;
        assert nullColumnReplacement != null;
        assert row != null;
        assert alignments != null;
        assert columnWidths.length == row.size();
        assert alignments.isEmpty() || (alignments.size() == row.size());
        // alignments can be empty, if so default to using LEFT
        final Alignment defaultAlignment = alignments.isEmpty() ? LEFT : null;
        final List<String> paddedAndAlignedRowStrings = new ArrayList<String>(row.size());
        for (int i = 0; i < columnWidths.length; i++) {
            final Object columnObject = row.get(i);
            final String columnString = (columnObject != null)
                                        ? columnObject.toString()
                                        : nullColumnReplacement;
            final Alignment alignment = (defaultAlignment != null)
                                        ? defaultAlignment
                                        : alignments.get(i);
            final int columnWidth = columnWidths[i];
            final String paddedAndAlignedColumnString = getPaddedAndAlignedColumnString(
                    columnString,
                    alignment,
                    columnWidth
            );
            paddedAndAlignedRowStrings.add(paddedAndAlignedColumnString);
        }
        return paddedAndAlignedRowStrings;
    }

    private static List<List<String>> getPaddedAndAlignedTableStrings(
            @Nonnull final int[] columnWidths,
            @Nonnull final String nullColumnReplacement,
            @Nonnull final Collection<List<Object>> table,
            @Nonnull final List<Alignment> alignments
    ) {
        assert columnWidths != null;
        assert nullColumnReplacement != null;
        assert table != null;
        assert alignments != null;
        final List<List<String>> paddedAndAlignedTableStrings = new ArrayList<List<String>>(table.size());
        for (final List<Object> row : table) {
            final List<String> paddedAndAlignedRowStrings = getPaddedAndAlignedRowStrings(
                    columnWidths,
                    nullColumnReplacement,
                    row,
                    alignments
            );
            paddedAndAlignedTableStrings.add(paddedAndAlignedRowStrings);
        }
        return paddedAndAlignedTableStrings;
    }

    private static List<String> getHorizontalStrings(
            @Nonnull final int[] columnWidths,
            final char horizontalChar
    ) {
        assert columnWidths != null;
        final List<String> horizontalStrings = new ArrayList<String>(columnWidths.length);
        for (final int columnWidth : columnWidths) {
            final String horizontalString = StringUtils.repeat(horizontalChar, columnWidth + 2);
            horizontalStrings.add(horizontalString);
        }
        return horizontalStrings;
    }

    private void appendLine(final String line) {
        toStringBuilder.append(linePrepender).append(line).append(lineAppender).append("\n");
    }

    private String getLine(
            final char leftChar,
            final char interiorChar,
            final char rightChar,
            @Nonnull final List<String> strings
    ) {
        assert strings != null;
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(leftChar);
        for (int i = 0; i < numColumns; i++) {
            if (i != 0) {
                stringBuilder.append(interiorChar);
            }
            stringBuilder.append(strings.get(i));
        }
        stringBuilder.append(rightChar);
        return stringBuilder.toString();
    }

    private String getTopLine() {
        return getLine(
                boxDrawingCharacters.getTopLeftCorner(),
                boxDrawingCharacters.getTopIntersect(),
                boxDrawingCharacters.getTopRightCorner(),
                horizontalStrings
        );
    }

    private String getInteriorLine() {
        return getLine(
                boxDrawingCharacters.getLeftIntersect(),
                boxDrawingCharacters.getMiddleIntersect(),
                boxDrawingCharacters.getRightIntersect(),
                horizontalStrings
        );
    }

    private String getBottomLine() {
        return getLine(
                boxDrawingCharacters.getBottomLeftCorner(),
                boxDrawingCharacters.getBottomIntersect(),
                boxDrawingCharacters.getBottomRightCorner(),
                horizontalStrings
        );
    }

    private String getRowLine(@Nonnull final List<String> strings) {
        assert strings != null;
        return getLine(
                boxDrawingCharacters.getVertical(),
                boxDrawingCharacters.getVertical(),
                boxDrawingCharacters.getVertical(),
                strings
        );
    }

    private void buildToString() {
        appendLine(getTopLine());
        if (headerStrings != null) {
            appendLine(getRowLine(headerStrings));
            appendLine(getInteriorLine());
        }
        for (final List<String> rowString : tableStrings) {
            appendLine(getRowLine(rowString));
        }
        appendLine(getBottomLine());
    }

    private String getToString() {
        buildToString();
        // remove last newline
        toStringBuilder.setLength(toStringBuilder.length() - 1);
        return toStringBuilder.toString();
    }

    static String getToStringFor(@Nonnull final TextTableBuilder textTableBuilder) {
        assert textTableBuilder != null;
        return new TextTableToStringBuilder(textTableBuilder).getToString();
    }

    public static void main(final String... args) {
        TextTableBuilder textTableBuilder = new TextTableBuilder();
        textTableBuilder.setHeaderAlignments(LEFT, RIGHT, LEFT);
        textTableBuilder.setColumnAlignments(RIGHT, LEFT, RIGHT);
        textTableBuilder.setHeaders("Col1", "Column2", "Column #3");
        for (int i = 0; i < 10; i++) {
            final String c1 = RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(1, 12));
            final String c2 = RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(1, 12));
            final String c3 = RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(1, 12));
            textTableBuilder.addRow(
                    (c1.length() == 3) ? null : c1,
                    (c2.length() == 3) ? null : c2,
                    (c3.length() == 3) ? null : c3
            );
        }

        textTableBuilder.setLineAppender("<");
        textTableBuilder.setLinePrepender(">");
        textTableBuilder.setNullColumnReplacement("-NON-");
        textTableBuilder.setShowRowNums(true);

        textTableBuilder.repeatHeadersAtBottom();

        textTableBuilder.setRepeatHeadersEveryXRows(5);

        System.out.println(getToStringFor(textTableBuilder));
    }

}
