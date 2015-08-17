package org.venutolo.texttablebuilder;

import javax.annotation.Nonnull;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rick Venutolo
 */
public class CollectionTextTableBuilder extends TextTableBuilder {

    public CollectionTextTableBuilder setHeaderAlignmentsCollection(@Nonnull final Collection<Alignment> headerAlignments) {
        return (CollectionTextTableBuilder) setHeaderAlignmentsInternal(headerAlignments);
    }

    @Override
    public CollectionTextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return (CollectionTextTableBuilder) super.setHeaderAlignments(headerAlignments);
    }

    @Override
    public CollectionTextTableBuilder clearHeaderAlignments() {
        return (CollectionTextTableBuilder) super.clearHeaderAlignments();
    }

    public CollectionTextTableBuilder setRowAlignmentsCollection(@Nonnull final Collection<Alignment> rowAlignments) {
        return (CollectionTextTableBuilder) setRowAlignmentsInternal(rowAlignments);
    }

    @Override
    public CollectionTextTableBuilder setRowAlignments(@Nonnull final Alignment... rowAlignments) {
        return (CollectionTextTableBuilder) super.setRowAlignments(rowAlignments);
    }

    @Override
    public CollectionTextTableBuilder clearRowAlignments() {
        return (CollectionTextTableBuilder) super.clearRowAlignments();
    }

    public CollectionTextTableBuilder setHeadersCollection(@Nonnull final Collection<?> headers) {
        return (CollectionTextTableBuilder) setHeadersInternal(headers);
    }

    @Override
    public CollectionTextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return (CollectionTextTableBuilder) super.setHeaders(headers);
    }

    @Override
    public CollectionTextTableBuilder clearHeaders() {
        return (CollectionTextTableBuilder) super.clearHeaders();
    }

    public CollectionTextTableBuilder addRowCollection(@Nonnull final Collection<?> row) {
        return (CollectionTextTableBuilder) addRowInternal(row);
    }

    @Override
    public CollectionTextTableBuilder addRow(@Nonnull final Object... row) {
        return (CollectionTextTableBuilder) super.addRow(row);
    }

    public CollectionTextTableBuilder addRowsCollection(@Nonnull final Collection<? extends Collection<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        for (Collection<?> row : rows) {
            addRowCollection(row);
        }
        return this;
    }

    @Override
    public CollectionTextTableBuilder clearRows() {
        return (CollectionTextTableBuilder) super.clearRows();
    }

    @Override
    public CollectionTextTableBuilder setBoxDrawingCharacters(@Nonnull final BoxDrawingCharacters boxDrawingCharacters) {
        return (CollectionTextTableBuilder) super.setBoxDrawingCharacters(boxDrawingCharacters);
    }

    @Override
    public CollectionTextTableBuilder setRepeatHeadersEveryXRows(final int repeatHeadersEveryXRows) {
        return (CollectionTextTableBuilder) super.setRepeatHeadersEveryXRows(repeatHeadersEveryXRows);
    }

    @Override
    public CollectionTextTableBuilder setRepeatHeadersAtBottom(final boolean repeatHeadersAtBottom) {
        return (CollectionTextTableBuilder) super.setRepeatHeadersAtBottom(repeatHeadersAtBottom);
    }

    @Override
    public CollectionTextTableBuilder repeatHeadersAtBottom() {
        return (CollectionTextTableBuilder) super.repeatHeadersAtBottom();
    }

    @Override
    public CollectionTextTableBuilder setShowRowNums(final boolean showRowNums) {
        return (CollectionTextTableBuilder) super.setShowRowNums(showRowNums);
    }

    @Override
    public CollectionTextTableBuilder showRowNums() {
        return (CollectionTextTableBuilder) super.showRowNums();
    }

    @Override
    public CollectionTextTableBuilder setLinePrepender(final String linePrepender) {
        return (CollectionTextTableBuilder) super.setLinePrepender(linePrepender);
    }

    @Override
    public CollectionTextTableBuilder setLineAppender(final String lineAppender) {
        return (CollectionTextTableBuilder) super.setLineAppender(lineAppender);
    }

    @Override
    public CollectionTextTableBuilder setNullColumnReplacement(final String nullColumnReplacement) {
        return (CollectionTextTableBuilder) super.setNullColumnReplacement(nullColumnReplacement);
    }

}
