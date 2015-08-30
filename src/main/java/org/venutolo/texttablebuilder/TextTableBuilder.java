package org.venutolo.texttablebuilder;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private static final String EMPTY_STRING = "";

    private Integer numColumns;

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private List<Object> headers;

    private List<Alignment> headerAlignments;

    private List<List<Object>> table;

    private List<Alignment> columnAlignments;

    private int repeatHeadersEveryXRows;

    private boolean repeatHeadersAtBottom;

    private boolean showRowNums;

    private String linePrepender = EMPTY_STRING;

    private String lineAppender = EMPTY_STRING;

    private String nullColumnReplacement = EMPTY_STRING;

    /**************************************************************************
     * CONSTRUCTOR
     **************************************************************************/

    public TextTableBuilder() {
        initRows();
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

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
            checkNotNull(alignment, "alignment at index %s cannot be null", index);
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

    /**************************************************************************
     * INSTANCE UTILITY METHODS
     **************************************************************************/

    private void initRows() {
        this.table = new ArrayList<List<Object>>();
    }

    protected void checkNumColumns(@Nonnull final Collection<?> collection) {
        checkNotNull(collection, "collection cannot be null");
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

    /**************************************************************************
     * HEADER ALIGNMENT METHODS
     **************************************************************************/

    @Nonnull
    public List<Alignment> getHeaderAlignments() {
        return listForOutput(headerAlignments);
    }

    @Nonnull
    protected TextTableBuilder setHeaderAlignmentsInternal(@Nonnull final Collection<Alignment> headerAlignments) {
        checkNotNull(headerAlignments, "header alignments cannot be null");
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveAlignmentListCopy(headerAlignments);
        return this;
    }

    @Nonnull
    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignmentsInternal(alignmentArrayToCollection(headerAlignments));
    }

    @Nonnull
    public TextTableBuilder clearHeaderAlignments() {
        this.headerAlignments = null;
        return this;
    }

    /**************************************************************************
     * COLUMN ALIGNMENT METHODS
     **************************************************************************/

    @Nonnull
    public List<Alignment> getColumnAlignments() {
        return listForOutput(columnAlignments);
    }

    @Nonnull
    protected TextTableBuilder setColumnAlignmentsInternal(@Nonnull final Collection<Alignment> columnAlignments) {
        checkNotNull(columnAlignments, "column alignments cannot be null");
        checkAlignmentsForNull(columnAlignments);
        checkNumColumns(columnAlignments);
        this.columnAlignments = defensiveAlignmentListCopy(columnAlignments);
        return this;
    }

    @Nonnull
    public TextTableBuilder setColumnAlignments(@Nonnull final Alignment... columnAlignments) {
        return setColumnAlignmentsInternal(alignmentArrayToCollection(columnAlignments));
    }

    @Nonnull
    public TextTableBuilder clearColumnAlignments() {
        this.columnAlignments = null;
        return this;
    }

    /**************************************************************************
     * HEADER METHODS
     **************************************************************************/

    @Nonnull
    public List<Object> getHeaders() {
        return listForOutput(headers);
    }

    @Nonnull
    protected TextTableBuilder setHeadersInternal(@Nonnull final Collection<?> headers) {
        checkNotNull(headers, "headers cannot be null");
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    @Nonnull
    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeadersInternal(objectArrayToCollection(headers));
    }

    @Nonnull
    public TextTableBuilder clearHeaders() {
        this.headers = null;
        return this;
    }

    /**************************************************************************
     * ROW METHODS
     **************************************************************************/

    @Nonnull
    public List<List<Object>> getTable() {
        final List<List<Object>> tableToReturn = new ArrayList<List<Object>>(table.size());
        for (final List<Object> row : table) {
            tableToReturn.add(listForOutput(row));
        }
        return tableToReturn;
    }

    @Nonnull
    protected TextTableBuilder addRowInternal(@Nonnull final Collection<?> row) {
        checkNotNull(row, "row cannot be null");
        checkNumColumns(row);
        table.add(defensiveObjectListCopy(row));
        return this;
    }

    @Nonnull
    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRowInternal(objectArrayToCollection(row));
    }

    @Nonnull
    public TextTableBuilder clearRows() {
        initRows();
        return this;
    }

    /**************************************************************************
     * NUMBER OF ROW AND COLUMN GETTERS
     **************************************************************************/

    public int getNumRows() {
        return table.size();
    }

    public int getNumColumns() {
        return (numColumns == null) ? 0 : numColumns;
    }

    /**************************************************************************
     * MISC OPTION METHODS
     **************************************************************************/

    @Nonnull
    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    @Nonnull
    public TextTableBuilder setBoxDrawingCharacters(@Nonnull final BoxDrawingCharacters boxDrawingCharacters) {
        checkNotNull(boxDrawingCharacters, "box drawing characters cannot be null");
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public int getRepeatHeadersEveryXRows() {
        return repeatHeadersEveryXRows;
    }

    @Nonnull
    public TextTableBuilder setRepeatHeadersEveryXRows(final int repeatHeadersEveryXRows) {
        Preconditions.checkArgument(repeatHeadersEveryXRows >= 0);
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

//    /**************************************************************************
//     * TOSTRING
//     **************************************************************************/
//
//    @Override
//    public String toString() {
//        return TextTableToStringBuilder.getToStringFor(this);
//    }

}
