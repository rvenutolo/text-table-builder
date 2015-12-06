package org.venutolo.texttablebuilder;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;

/**
 * @author Rick Venutolo
 */
final class ToStringBuilder {

    private static final int STRING_BUILDER_INITIAL_CAPACITY = 128;

    private final StringBuilder stringBuilder = new StringBuilder(STRING_BUILDER_INITIAL_CAPACITY);

    private final int numColumns;

    private final BoxDrawingCharacters boxDrawingCharacters;

    private final String linePrepender;

    private final String lineAppender;

    private final boolean repeatHeadersAtBottom;

    private final int repeatHeadersEveryXRows;

    private final List<String> headerStrings;

    private final List<List<String>> tableStrings;

    private final List<String> horizontalStrings;

    private ToStringBuilder(final TextTableBuilder textTableBuilder) {
        final boolean showRowNums = textTableBuilder.getShowRowNums();
        numColumns = textTableBuilder.getNumColumns() + (showRowNums ? 1 : 0);
        boxDrawingCharacters = textTableBuilder.getBoxDrawingCharacters();
        linePrepender = textTableBuilder.getLinePrepender();
        lineAppender = textTableBuilder.getLineAppender();
        repeatHeadersAtBottom = textTableBuilder.getRepeatHeadersAtBottom();
        repeatHeadersEveryXRows = textTableBuilder.getRepeatHeadersEveryXRows();
        final List<Object> headers = textTableBuilder.getHeaders();
        final List<List<Object>> rows = textTableBuilder.getRows();
        final List<Alignment> headerAlignments = textTableBuilder.getHeaderAlignments();
        final List<Alignment> columnAlignments = textTableBuilder.getColumnAlignments();
        // if alignments are empty, then populate with LEFT for defaults
        ifEmptyPopulateWithLeft(headerAlignments, textTableBuilder.getNumColumns());
        ifEmptyPopulateWithLeft(columnAlignments, textTableBuilder.getNumColumns());
        final String nullColumnReplacement = textTableBuilder.getNullColumnReplacement();
        // if showing row number, add column items for row numbers
        if (showRowNums) {
            addItemsForShowRowNums(
                    textTableBuilder,
                    headers,
                    rows,
                    headerAlignments,
                    columnAlignments
            );
        }
        // determine widths of columns to be used when padding strings
        final int[] columnWidths = getColumnWidths(
                numColumns,
                nullColumnReplacement,
                headers,
                rows
        );
        // generate strings for headers, rows, and horizontal strings
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

    private void addItemsForShowRowNums(
            final TextTableBuilder textTableBuilder,
            final List<Object> headers,
            final List<List<Object>> rows,
            final List<Alignment> headerAlignments,
            final List<Alignment> columnAlignments
    ) {
        headerAlignments.add(0, RIGHT);
        columnAlignments.add(0, RIGHT);
        final String rowNumHeader = textTableBuilder.getRowNumHeader();
        final boolean headersWereEmpty = headers.isEmpty();
        if (!headersWereEmpty || (rowNumHeader.length() != 0)) {
            headers.add(0, rowNumHeader);
            // if headers were empty, need to populate other headers with empty strings
            if (headersWereEmpty) {
                for (int i = 1; i < numColumns; i++) {
                    headers.add("");
                }
            }
        }
        int rowNum = 1;
        final NumberFormat rowNumFormat = textTableBuilder.getRowNumFormat();
        for (final List<Object> row : rows) {
            row.add(0, (rowNumFormat == null) ? rowNum : rowNumFormat.format(rowNum));
            rowNum++;
        }
    }

    private static String leftPad(@Nonnull final String s, final int length) {
        assert s != null;
        return String.format("%1$" + length + "s", s);
    }

    private static String rightPad(@Nonnull final String s, final int length) {
        assert s != null;
        return String.format("%1$-" + length + "s", s);
    }

    private static String repeatChar(final char c, final int times) {
        final char[] chars = new char[times];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    private static void ifEmptyPopulateWithLeft(
            @Nonnull final Collection<Alignment> alignments,
            final int numColumns
    ) {
        if (alignments.isEmpty()) {
            for (int i = 0; i < numColumns; i++) {
                alignments.add(LEFT);
            }
        }
    }

    private static void checkAndUpdateColumnMaxWidths(
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
            final String columnString = (columnObject == null)
                                        ? nullColumnReplacement
                                        : columnObject.toString();
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
            @Nonnull final Iterable<List<Object>> table
    ) {
        assert numColumns >= 0;
        assert nullColumnReplacement != null;
        assert headers != null;
        assert table != null;
        final int[] columnWidths = new int[numColumns];
        if (!headers.isEmpty()) {
            checkAndUpdateColumnMaxWidths(columnWidths, nullColumnReplacement, headers);
        }
        for (final List<Object> row : table) {
            checkAndUpdateColumnMaxWidths(columnWidths, nullColumnReplacement, row);
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
        return (alignment == LEFT)
               ? rightPad(columnStringPlusSpaces, columnWidthWithPadding)
               : leftPad(columnStringPlusSpaces, columnWidthWithPadding);
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
        assert alignments.size() == row.size();
        final List<String> paddedAndAlignedRowStrings = new ArrayList<String>(row.size());
        for (int i = 0; i < columnWidths.length; i++) {
            final Object columnObject = row.get(i);
            final String columnString = (columnObject == null)
                                        ? nullColumnReplacement
                                        : columnObject.toString();
            final int columnWidth = columnWidths[i];
            final String paddedAndAlignedColumnString = getPaddedAndAlignedColumnString(
                    columnString,
                    alignments.get(i),
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
            final String horizontalString = repeatChar(horizontalChar, columnWidth + 2);
            horizontalStrings.add(horizontalString);
        }
        return horizontalStrings;
    }

    private void appendLine(final String line) {
        stringBuilder.append(linePrepender).append(line).append(lineAppender).append('\n');
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
        final boolean checkForRepeatingHeader =
                (headerStrings != null) && (repeatHeadersEveryXRows != 0);
        int rowNum = 1;
        boolean justPrintedHeader = false;
        for (final List<String> rowString : tableStrings) {
            if (justPrintedHeader) {
                appendLine(getInteriorLine());
            }
            appendLine(getRowLine(rowString));
            if (checkForRepeatingHeader) {
                if ((rowNum % repeatHeadersEveryXRows) == 0) {
                    appendLine(getInteriorLine());
                    appendLine(getRowLine(headerStrings));
                    // can't just append interior line after repeating headers
                    // in case it ends up being the bottom headers
                    justPrintedHeader = true;
                } else {
                    justPrintedHeader = false;
                }
            }
            rowNum++;
        }
        if ((headerStrings != null) && repeatHeadersAtBottom && !justPrintedHeader) {
            appendLine(getInteriorLine());
            appendLine(getRowLine(headerStrings));
        }
        appendLine(getBottomLine());
    }

    private String getToString() {
        buildToString();
        // remove last newline
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static String getToStringFor(@Nonnull final TextTableBuilder textTableBuilder) {
        assert textTableBuilder != null;
        return new ToStringBuilder(textTableBuilder).getToString();
    }

}
