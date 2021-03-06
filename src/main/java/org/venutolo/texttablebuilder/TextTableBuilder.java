package org.venutolo.texttablebuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.venutolo.texttablebuilder.BoxDrawingCharacters.LIGHT;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    @Nonnull
    private static final String EMPTY_STRING = "";

    @Nullable
    private Integer numColumns;

    @Nullable
    private List<Alignment> headerAlignments;

    @Nullable
    private List<Alignment> columnAlignments;

    @Nullable
    private List<Object> headers;

    @Nonnull
    private List<List<Object>> table = emptyTable();

    @Nonnull
    private BoxDrawingCharacters boxDrawingCharacters = LIGHT;

    private int repeatHeadersEveryXRows;

    private boolean repeatHeadersAtBottom;

    private boolean showRowNums;

    @Nonnull
    private String rowNumHeader = EMPTY_STRING;

    @Nullable
    private NumberFormat rowNumFormat;

    @Nonnull
    private String linePrepender = EMPTY_STRING;

    @Nonnull
    private String lineAppender = EMPTY_STRING;

    @Nonnull
    private String nullColumnReplacement = EMPTY_STRING;

    /*========================================================================
     * STATIC UTILITY METHODS
     *========================================================================*/

    @Nonnull
    private static List<Alignment> defensiveAlignmentListCopy(@Nonnull final Collection<Alignment> collection) {
        assert collection != null;
        // use to make a defensive copy of a collection that has already been checked for null
        return new ArrayList<Alignment>(collection);
    }

    @Nullable
    private static Collection<Alignment> alignmentArrayToCollection(@Nullable final Alignment... array) {
        // want to retain null for later null-checking
        return (array == null) ? null : Arrays.asList(array);
    }

    private static void checkAlignmentsForNull(@Nonnull final Iterable<Alignment> alignments) {
        assert alignments != null;
        // check that no alignments are null
        int index = 0;
        for (final Alignment alignment : alignments) {
            if (alignment == null) {
                throw new IllegalArgumentException(
                        "alignment at index " + index + " cannot be null");
            }
            index++;
        }
    }

    @Nonnull
    private static List<Object> defensiveObjectListCopy(@Nonnull final Collection<?> collection) {
        assert collection != null;
        // used to make a defensive copy of a collection that has already been checked for null
        return new ArrayList<Object>(collection);
    }

    @Nullable
    private static Collection<Object> objectArrayToCollection(@Nullable final Object... array) {
        // want to retain null for later null-checking
        return (array == null) ? null : Arrays.asList(array);
    }

    @Nonnull
    private static <T> List<T> listForOutput(@Nullable final List<T> list) {
        // output is returned to user, so return empty collection instead of null
        return (list == null) ? new ArrayList<T>() : new ArrayList<T>(list);
    }

    @Nonnull
    private static List<List<Object>> emptyTable() {
        return new ArrayList<List<Object>>();
    }

    /*========================================================================
     * INSTANCE UTILITY METHODS
     *========================================================================*/

    protected void checkNumColumns(@Nonnull final Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("collection cannot be null");
        }
        if (numColumns == null) {
            // if numColumns is null, this is the first check for number of columns, so set it
            numColumns = collection.size();
        } else {
            if (numColumns != collection.size()) {
                throw new IllegalArgumentException(
                        "Wrong number of columns: " + collection.size()
                        + "; expected " + numColumns + " columns"
                );
            }
        }
    }

    /*========================================================================
     * HEADER ALIGNMENT METHODS
     *========================================================================*/

    @Nonnull
    public List<Alignment> getHeaderAlignments() {
        return listForOutput(headerAlignments);
    }

    @Nonnull
    public TextTableBuilder setHeaderAlignments(@Nonnull final Collection<Alignment> headerAlignments) {
        if (headerAlignments == null) {
            throw new IllegalArgumentException("header alignments cannot be null");
        }
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveAlignmentListCopy(headerAlignments);
        return this;
    }

    @Nonnull
    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignments(alignmentArrayToCollection(headerAlignments));
    }

    @Nonnull
    public TextTableBuilder clearHeaderAlignments() {
        this.headerAlignments = null;
        return this;
    }

    /*========================================================================
     * COLUMN ALIGNMENT METHODS
     *========================================================================*/

    @Nonnull
    public List<Alignment> getColumnAlignments() {
        return listForOutput(columnAlignments);
    }

    @Nonnull
    public TextTableBuilder setColumnAlignments(@Nonnull final Collection<Alignment> columnAlignments) {
        if (columnAlignments == null) {
            throw new IllegalArgumentException("column alignments cannot be null");
        }
        checkAlignmentsForNull(columnAlignments);
        checkNumColumns(columnAlignments);
        this.columnAlignments = defensiveAlignmentListCopy(columnAlignments);
        return this;
    }

    @Nonnull
    public TextTableBuilder setColumnAlignments(@Nonnull final Alignment... columnAlignments) {
        return setColumnAlignments(alignmentArrayToCollection(columnAlignments));
    }

    @Nonnull
    public TextTableBuilder clearColumnAlignments() {
        this.columnAlignments = null;
        return this;
    }

    /*========================================================================
     * HEADER METHODS
     *========================================================================*/

    @Nonnull
    public List<Object> getHeaders() {
        return listForOutput(headers);
    }

    @Nonnull
    public TextTableBuilder setHeaders(@Nonnull final Collection<?> headers) {
        if (headers == null) {
            throw new IllegalArgumentException("headers cannot be null");
        }
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    @Nonnull
    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeaders(objectArrayToCollection(headers));
    }

    @Nonnull
    public TextTableBuilder clearHeaders() {
        this.headers = null;
        return this;
    }

    /*========================================================================
     * ROW METHODS
     *========================================================================*/

    @Nonnull
    public List<List<Object>> getRows() {
        final List<List<Object>> tableToReturn = new ArrayList<List<Object>>(table.size());
        for (final List<Object> row : table) {
            tableToReturn.add(listForOutput(row));
        }
        return tableToReturn;
    }

    @Nonnull
    public TextTableBuilder addRow(@Nonnull final Collection<?> row) {
        if (row == null) {
            throw new IllegalArgumentException("row cannot be null");
        }
        checkNumColumns(row);
        table.add(defensiveObjectListCopy(row));
        return this;
    }

    @Nonnull
    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRow(objectArrayToCollection(row));
    }

    @Nonnull
    public TextTableBuilder clearRows() {
        table = emptyTable();
        return this;
    }

    /*========================================================================
     * NUMBER OF ROW AND COLUMN GETTERS
     *========================================================================*/

    public int getNumRows() {
        return table.size();
    }

    public int getNumColumns() {
        return (numColumns == null) ? 0 : numColumns;
    }

    /*========================================================================
     * MISC OPTION METHODS
     *========================================================================*/

    @Nonnull
    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    @Nonnull
    public TextTableBuilder setBoxDrawingCharacters(@Nonnull final BoxDrawingCharacters boxDrawingCharacters) {
        if (boxDrawingCharacters == null) {
            throw new IllegalArgumentException("box drawing characters cannot be null");
        }
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public int getRepeatHeadersEveryXRows() {
        return repeatHeadersEveryXRows;
    }

    @Nonnull
    public TextTableBuilder setRepeatHeadersEveryXRows(final int repeatHeadersEveryXRows) {
        if (repeatHeadersEveryXRows < 0) {
            throw new IllegalArgumentException(
                    "num rows must be non-negative: " + repeatHeadersEveryXRows
            );
        }
        this.repeatHeadersEveryXRows = repeatHeadersEveryXRows;
        return this;
    }

    public boolean getRepeatHeadersAtBottom() {
        return repeatHeadersAtBottom;
    }

    @Nonnull
    public TextTableBuilder setRepeatHeadersAtBottom(final boolean repeatHeadersAtBottom) {
        this.repeatHeadersAtBottom = repeatHeadersAtBottom;
        return this;
    }

    @Nonnull
    public TextTableBuilder repeatHeadersAtBottom() {
        return setRepeatHeadersAtBottom(true);
    }

    public boolean getShowRowNums() {
        return showRowNums;
    }

    @Nonnull
    public TextTableBuilder setShowRowNums(final boolean showRowNums) {
        this.showRowNums = showRowNums;
        return this;
    }

    @Nonnull
    public TextTableBuilder showRowNums() {
        return setShowRowNums(true);
    }

    @Nonnull
    public String getRowNumHeader() {
        return rowNumHeader;
    }

    @Nonnull
    public TextTableBuilder setRowNumHeader(@Nullable final String rowNumHeader) {
        this.rowNumHeader = (rowNumHeader == null) ? EMPTY_STRING : rowNumHeader;
        return this;
    }

    @Nullable
    public NumberFormat getRowNumFormat() {
        return rowNumFormat;
    }

    public TextTableBuilder setRowNumFormat(@Nullable final NumberFormat rowNumFormat) {
        this.rowNumFormat = rowNumFormat;
        return this;
    }

    @Nonnull
    public String getLinePrepender() {
        return linePrepender;
    }

    @Nonnull
    public TextTableBuilder setLinePrepender(@Nullable final String linePrepender) {
        this.linePrepender = (linePrepender == null) ? EMPTY_STRING : linePrepender;
        return this;
    }

    @Nonnull
    public String getLineAppender() {
        return lineAppender;
    }

    @Nonnull
    public TextTableBuilder setLineAppender(@Nullable final String lineAppender) {
        this.lineAppender = (lineAppender == null) ? EMPTY_STRING : lineAppender;
        return this;
    }

    @Nonnull
    public String getNullColumnReplacement() {
        return nullColumnReplacement;
    }

    @Nonnull
    public TextTableBuilder setNullColumnReplacement(@Nullable final String nullColumnReplacement) {
        this.nullColumnReplacement = (nullColumnReplacement == null)
                                     ? EMPTY_STRING
                                     : nullColumnReplacement;
        return this;
    }

    /*========================================================================
     * TOSTRING
     *========================================================================*/

    @Override
    public String toString() {
        return ToStringBuilder.getToStringFor(this);
    }

    /*========================================================================
     * EQUALS / HASHCODE
     *========================================================================*/

    private Object[] getObjectArray() {
        return new Object[]{
                repeatHeadersEveryXRows,
                repeatHeadersAtBottom,
                showRowNums,
                headerAlignments,
                columnAlignments,
                headers,
                table,
                boxDrawingCharacters,
                linePrepender,
                lineAppender,
                nullColumnReplacement
        };
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final TextTableBuilder that = (TextTableBuilder) o;
        return Arrays.equals(getObjectArray(), that.getObjectArray());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getObjectArray());
    }

}
