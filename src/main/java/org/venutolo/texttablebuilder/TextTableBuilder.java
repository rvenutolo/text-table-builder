package org.venutolo.texttablebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private Integer numColumns;

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private List<String> headers;

    private List<Alignment> headerAlignments;

    private List<List<String>> rows = new ArrayList<List<String>>();

    private List<Alignment> rowAlignments;

    private static <T> List<T> defensiveCopy(final Collection<T> collection) {
        return new ArrayList<T>(collection);
    }

    private <T> void checkNumColumns(final Collection<T> collection) {
        if (numColumns == null) {
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

    public TextTableBuilder setBoxDrawingCharacters(final BoxDrawingCharacters boxDrawingCharacters) {
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    public TextTableBuilder setHeaders(final Collection<String> headers) {
        checkNumColumns(headers);
        this.headers = defensiveCopy(headers);
        return this;
    }

    public TextTableBuilder setHeaders(final String... headers) {
        return setHeaders(Arrays.asList(headers));
    }

    public List<String> getHeaders() {
        return defensiveCopy(headers);
    }

    public TextTableBuilder setHeaderAlignments(final Collection<Alignment> headerAlignments) {
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(final Alignment... headerAlignments) {
        return setHeaderAlignments(Arrays.asList(headerAlignments));
    }

    public List<Alignment> getHeaderAlignments() {
        return defensiveCopy(headerAlignments);
    }

    public TextTableBuilder addRow(final Collection<String> row) {
        checkNumColumns(row);
        rows.add(defensiveCopy(row));
        return this;
    }

    public TextTableBuilder addRow(final String... row) {
        addRow(Arrays.asList(row));
        return this;
    }

    public TextTableBuilder setRows(final Collection<Collection<String>> rows) {
        this.rows = new ArrayList<List<String>>(rows.size());
        for (final Collection<String> row : rows) {
            addRow(row);
        }
        return this;
    }

    public List<List<String>> getRows() {
        final List<List<String>> rows = new ArrayList<List<String>>(this.rows.size());
        for (final Collection<String> row : this.rows) {
            rows.add(defensiveCopy(row));
        }
        return rows;
    }

    public TextTableBuilder setRowAlignments(final Collection<Alignment> rowAlignments) {
        checkNumColumns(rowAlignments);
        this.rowAlignments = defensiveCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(final Alignment... rowAlignments) {
        return setRowAlignments(Arrays.asList(rowAlignments));
    }

    public List<Alignment> getRowAlignments() {
        return defensiveCopy(rowAlignments);
    }

}
