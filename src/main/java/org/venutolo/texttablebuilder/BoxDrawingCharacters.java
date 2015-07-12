package org.venutolo.texttablebuilder;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Set of characters used to create text tables.
 * <p>
 * See <a href="http://www.unicode.org/charts/PDF/U2500.pdf">Unicode Box Drawing Character Code
 * Chart</a>
 *
 * @author Rick Venutolo
 */
public final class BoxDrawingCharacters {

    /**
     * Set of heavy solid line box drawing characters.
     */
    public static final BoxDrawingCharacters HEAVY = fromBoxDrawing(
            //@formatter:off
              "┏━┳━┓"
            + "┃ ┃ ┃"
            + "┣━╋━┫"
            + "┃ ┃ ┃"
            + "┗━┻━┛"
            //@formatter:on
    );

    /**
     * Set of light solid line box drawing characters.
     */
    public static final BoxDrawingCharacters LIGHT = fromBoxDrawing(
            //@formatter:off
              "┌─┬─┐"
            + "│ │ │"
            + "├─┼─┤"
            + "│ │ │"
            + "└─┴─┘"
            //@formatter:on
    );

    /**
     * Set of light solid line box drawing characters with curved corners.
     */
    public static final BoxDrawingCharacters CURVED = fromBoxDrawing(
            //@formatter:off
              "╭─┬─╮"
            + "│ │ │"
            + "├─┼─┤"
            + "│ │ │"
            + "╰─┴─╯"
            //@formatter:on
    );

    /**
     * Set of double line box drawing characters.
     */
    public static final BoxDrawingCharacters DOUBLE = fromBoxDrawing(
            //@formatter:off
              "╔═╦═╗"
            + "║ ║ ║"
            + "╠═╬═╣"
            + "║ ║ ║"
            + "╚═╩═╝"
            //@formatter:on
    );

    /**
     * Set of box drawing characters using only ASCII characters.
     */
    public static final BoxDrawingCharacters ASCII = fromBoxDrawing(
            //@formatter:off
              "+-+-+"
            + "| | |"
            + "+-+-+"
            + "| | |"
            + "+-+-+"
            //@formatter:on
    );

    /**
     * Set of box drawing characters using space for all characters.
     */
    public static final BoxDrawingCharacters SPACES = new Builder().setHorizontal(' ')
                                                                   .setVertical(' ')
                                                                   .setTopLeftCorner(' ')
                                                                   .setTopIntersect(' ')
                                                                   .setTopRightCorner(' ')
                                                                   .setLeftIntersect(' ')
                                                                   .setMiddleIntersect(' ')
                                                                   .setRightIntersect(' ')
                                                                   .setBottomLeftCorner(' ')
                                                                   .setBottomIntersect(' ')
                                                                   .setBottomRightCorner(' ')
                                                                   .build();

    // TODO figure out if this is worthwhile
    /**
     * Set of box drawing characters using null for all characters.
     */
    public static final BoxDrawingCharacters NULLS = new Builder().setHorizontal('\u0000')
                                                                  .setVertical('\u0000')
                                                                  .setTopLeftCorner('\u0000')
                                                                  .setTopIntersect('\u0000')
                                                                  .setTopRightCorner('\u0000')
                                                                  .setLeftIntersect('\u0000')
                                                                  .setMiddleIntersect('\u0000')
                                                                  .setRightIntersect('\u0000')
                                                                  .setBottomLeftCorner('\u0000')
                                                                  .setBottomIntersect('\u0000')
                                                                  .setBottomRightCorner('\u0000')
                                                                  .build();

    private final char horizontal;
    private final char vertical;
    private final char topLeftCorner;
    private final char topIntersect;
    private final char topRightCorner;
    private final char leftIntersect;
    private final char middleIntersect;
    private final char rightIntersect;
    private final char bottomLeftCorner;
    private final char bottomIntersect;
    private final char bottomRightCorner;

    private BoxDrawingCharacters(final Builder builder) {
        this.horizontal = builder.horizontal;
        this.vertical = builder.vertical;
        this.topLeftCorner = builder.topLeftCorner;
        this.topIntersect = builder.topIntersect;
        this.topRightCorner = builder.topRightCorner;
        this.leftIntersect = builder.leftIntersect;
        this.middleIntersect = builder.middleIntersect;
        this.rightIntersect = builder.rightIntersect;
        this.bottomLeftCorner = builder.bottomLeftCorner;
        this.bottomIntersect = builder.bottomIntersect;
        this.bottomRightCorner = builder.bottomRightCorner;
    }

    /**
     * Creates and returns a new instance from the given text box drawing.
     * <p>
     * Example usage:
     * <pre>{@code
     * BoxDrawingCharacters example = fromBoxDrawing(
     *         "┏━┳━┓"
     *       + "┃ ┃ ┃"
     *       + "┣━╋━┫"
     *       + "┃ ┃ ┃"
     *       + "┗━┻━┛"
     * );
     * }</pre>
     *
     * @param boxDrawing the text box drawing
     *
     * @return the BoxDrawingCharacters created from the given text box drawing
     *
     * @throws IllegalArgumentException if the box drawing is the incorrect length or is
     *                                  inconsistent with usage of horizontal and vertical bar
     *                                  characters
     */
    public static BoxDrawingCharacters fromBoxDrawing(final String boxDrawing) {
        return new BoxDrawingParser(boxDrawing).parse();
    }

    /**
     * Get the character used to draw horizontal lines, such as {@code '━'}.
     *
     * @return the character used to draw horizontal lines
     */
    char getHorizontal() {
        return horizontal;
    }

    /**
     * Get the character used to draw vertical lines, such as {@code '┃'}.
     *
     * @return the character used to draw vertical lines
     */
    char getVertical() {
        return vertical;
    }

    /**
     * Get the character used to draw the top left corner of a box, such as {@code '┏'}.
     *
     * @return the character used to draw the top left corner of a box
     */
    public char getTopLeftCorner() {
        return topLeftCorner;
    }

    /**
     * Get the character used to draw where a vertical line intersects with the top edge of a box,
     * such as {@code '┳'}.
     *
     * @return the character used to draw where a vertical line intersects with the top edge of a
     * box
     */
    public char getTopIntersect() {
        return topIntersect;
    }

    /**
     * Get the character used to draw the top right corner of a box, such as {@code '┓'}.
     *
     * @return the character used to draw the top right corner of a box
     */
    public char getTopRightCorner() {
        return topRightCorner;
    }

    /**
     * Get the character used to draw where a horizontal line intersects with the left edge of a
     * box, such as {@code '┣'}.
     *
     * @return the character used to draw where a horizontal line intersects with the left edge of a
     * box
     */
    public char getLeftIntersect() {
        return leftIntersect;
    }

    /**
     * Get the character used to draw where a horizontal and vertical line intersect in the interior
     * of a box, such as {@code '╋'}.
     *
     * @return the character used to draw where a horizontal and vertical line intersect in the
     * interior of a box
     */
    public char getMiddleIntersect() {
        return middleIntersect;
    }

    /**
     * Get the character used to draw where a horizontal line intersects with the right edge of a
     * box, such as {@code '┫'}.
     *
     * @return the character used to draw where a horizontal line intersects with the right edge of
     * a box
     */
    public char getRightIntersect() {
        return rightIntersect;
    }

    /**
     * Get the character used to draw the bottom left corner of a box, such as {@code '┗'}.
     *
     * @return the character used to draw the bottom left corner of a box
     */
    public char getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    /**
     * Get the character used to draw where a vertical line intersects with the bottom edge of a
     * box, such as {@code '┻'}.
     *
     * @return the character used to draw where a vertical line intersects with the bottom edge of a
     * box
     */
    public char getBottomIntersect() {
        return bottomIntersect;
    }

    /**
     * Get the character used to draw the bottom right corner of a box, such as {@code '┛'}.
     *
     * @return the character used to draw the bottom right corner of a box
     */
    public char getBottomRightCorner() {
        return bottomRightCorner;
    }

    /**
     * Returns {@code true} if {@code object} is a {@code BoxDrawingCharacters} with the same set of
     * characters used for box drawing.
     *
     * @param object The object to compare this {@code BoxDrawingCharacters} against
     *
     * @return {@code true} if the given object represents a {@code BoxDrawingCharacters} equivalent
     * to this {@code BoxDrawingCharacters}, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || (getClass() != object.getClass())) {
            return false;
        }
        final BoxDrawingCharacters that = (BoxDrawingCharacters) object;
        return Objects.equal(horizontal, that.horizontal) &&
               Objects.equal(vertical, that.vertical) &&
               Objects.equal(topLeftCorner, that.topLeftCorner) &&
               Objects.equal(topIntersect, that.topIntersect) &&
               Objects.equal(topRightCorner, that.topRightCorner) &&
               Objects.equal(leftIntersect, that.leftIntersect) &&
               Objects.equal(middleIntersect, that.middleIntersect) &&
               Objects.equal(rightIntersect, that.rightIntersect) &&
               Objects.equal(bottomLeftCorner, that.bottomLeftCorner) &&
               Objects.equal(bottomIntersect, that.bottomIntersect) &&
               Objects.equal(bottomRightCorner, that.bottomRightCorner);
    }

    /**
     * Returns a hash code for this {@code BoxDrawingCharacters}.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(
                horizontal,
                vertical,
                topLeftCorner,
                topIntersect,
                topRightCorner,
                leftIntersect,
                middleIntersect,
                rightIntersect,
                bottomLeftCorner,
                bottomIntersect,
                bottomRightCorner
        );
    }

    /**
     * Returns a string representation of this this {@code BoxDrawingCharacters}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("horizontal", horizontal)
                          .add("vertical", vertical)
                          .add("topLeftCorner", topLeftCorner)
                          .add("topIntersect", topIntersect)
                          .add("topRightCorner", topRightCorner)
                          .add("leftIntersect", leftIntersect)
                          .add("middleIntersect", middleIntersect)
                          .add("rightIntersect", rightIntersect)
                          .add("bottomLeftCorner", bottomLeftCorner)
                          .add("bottomIntersect", bottomIntersect)
                          .add("bottomRightCorner", bottomRightCorner)
                          .toString();
    }

    /**
     * Builder pattern class used to create new {@link BoxDrawingCharacters} instances.
     */
    public static class Builder {

        private char horizontal;
        private char vertical;
        private char topLeftCorner;
        private char topIntersect;
        private char topRightCorner;
        private char leftIntersect;
        private char middleIntersect;
        private char rightIntersect;
        private char bottomLeftCorner;
        private char bottomIntersect;
        private char bottomRightCorner;

        /**
         * Set the character used to draw horizontal lines, such as {@code '━'}.
         *
         * @param horizontal the character used to draw horizontal lines
         *
         * @return this instance
         */
        public Builder setHorizontal(final char horizontal) {
            this.horizontal = horizontal;
            return this;
        }

        /**
         * Set the character used to draw vertical lines, such as {@code '┃'}.
         *
         * @param vertical the character used to draw vertical lines
         *
         * @return this instance
         */
        public Builder setVertical(final char vertical) {
            this.vertical = vertical;
            return this;
        }

        /**
         * Set the character used to draw the top left corner of a box, such as {@code '┏'}.
         *
         * @param topLeftCorner the character used to draw the top left corner of a box
         *
         * @return this instance
         */
        public Builder setTopLeftCorner(final char topLeftCorner) {
            this.topLeftCorner = topLeftCorner;
            return this;
        }

        /**
         * Set the character used to draw where a vertical line intersects with the top edge of a
         * box, such as {@code '┳'}.
         *
         * @param topIntersect the character used to draw where a vertical line intersects with the
         *                     top edge of a box
         *
         * @return this instance
         */
        public Builder setTopIntersect(final char topIntersect) {
            this.topIntersect = topIntersect;
            return this;
        }

        /**
         * Set the character used to draw the top right corner of a box, such as {@code '┓'}.
         *
         * @param topRightCorner the character used to draw the top right corner of a box
         *
         * @return this instance
         */
        public Builder setTopRightCorner(final char topRightCorner) {
            this.topRightCorner = topRightCorner;
            return this;
        }

        /**
         * Set the character used to draw where a horizontal line intersects with the left edge of a
         * box, such as {@code '┣'}.
         *
         * @param leftIntersect the character used to draw where a horizontal line intersects with
         *                      the left edge of a box
         *
         * @return this instance
         */
        public Builder setLeftIntersect(final char leftIntersect) {
            this.leftIntersect = leftIntersect;
            return this;
        }

        /**
         * Set the character used to draw where a horizontal and vertical line intersect in the
         * interior of a box, such as {@code '╋'}.
         *
         * @param middleIntersect the character used to draw where a horizontal and vertical line
         *                        intersect in the interior of a box
         *
         * @return this instance
         */
        public Builder setMiddleIntersect(final char middleIntersect) {
            this.middleIntersect = middleIntersect;
            return this;
        }

        /**
         * Set the character used to draw where a horizontal line intersects with the right edge of
         * a box, such as {@code '┫'}.
         *
         * @param rightIntersect the character used to draw where a horizontal line intersects with
         *                       the right edge of a box
         *
         * @return this instance
         */
        public Builder setRightIntersect(final char rightIntersect) {
            this.rightIntersect = rightIntersect;
            return this;
        }

        /**
         * Set the character used to draw the bottom left corner of a box, such as {@code '┗'}.
         *
         * @param bottomLeftCorner the character used to draw the bottom left corner of a box
         *
         * @return this instance
         */
        public Builder setBottomLeftCorner(final char bottomLeftCorner) {
            this.bottomLeftCorner = bottomLeftCorner;
            return this;
        }

        /**
         * Set the character used to draw where a vertical line intersects with the bottom edge of a
         * box, such as {@code '┻'}.
         *
         * @param bottomIntersect the character used to draw where a vertical line intersects with
         *                        the bottom edge of a box
         *
         * @return this instance
         */
        public Builder setBottomIntersect(final char bottomIntersect) {
            this.bottomIntersect = bottomIntersect;
            return this;
        }

        /**
         * Set the character used to draw the bottom right corner of a box, such as {@code '┛'}.
         *
         * @param bottomRightCorner the character used to draw the bottom right corner of a box
         *
         * @return this instance
         */
        public Builder setBottomRightCorner(final char bottomRightCorner) {
            this.bottomRightCorner = bottomRightCorner;
            return this;
        }

        /**
         * Builds a new {@link BoxDrawingCharacters instance}.
         *
         * @return the {@link BoxDrawingCharacters}
         */
        public BoxDrawingCharacters build() {
            return new BoxDrawingCharacters(this);
        }

    }

    private static final class BoxDrawingParser {

        private static final int EXPECTED_LENGTH = 25;

        private static final int TOP_LEFT_CORNER = 0;
        private static final int TOP_LEFT_HORIZONTAL = 1;
        private static final int TOP_INTERSECT = 2;
        private static final int TOP_RIGHT_HORIZONTAL = 3;
        private static final int TOP_RIGHT_CORNER = 4;
        private static final int TOP_ROW_LEFT_VERTICAL = 5;
        private static final int TOP_ROW_MIDDLE_VERTICAL = 7;
        private static final int TOP_ROW_RIGHT_VERTICAL = 9;
        private static final int LEFT_INTERSECT = 10;
        private static final int MIDDLE_LEFT_HORIZONTAL = 11;
        private static final int MIDDLE_INTERSECT = 12;
        private static final int MIDDLE_RIGHT_HORIZONTAL = 13;
        private static final int RIGHT_INTERSECT = 14;
        private static final int BOTTOM_ROW_LEFT_VERTICAL = 15;
        private static final int BOTTOM_ROW_MIDDLE_VERTICAL = 17;
        private static final int BOTTOM_ROW_RIGHT_VERTICAL = 19;
        private static final int BOTTOM_LEFT_CORNER = 20;
        private static final int BOTTOM_LEFT_HORIZONTAL = 21;
        private static final int BOTTOM_INTERSECT = 22;
        private static final int BOTTOM_RIGHT_HORIZONTAL = 23;
        private static final int BOTTOM_RIGHT_CORNER = 24;

        private static final int[] horizontalCharIndexes = {
                TOP_LEFT_HORIZONTAL,
                TOP_RIGHT_HORIZONTAL,
                MIDDLE_LEFT_HORIZONTAL,
                MIDDLE_RIGHT_HORIZONTAL,
                BOTTOM_LEFT_HORIZONTAL,
                BOTTOM_RIGHT_HORIZONTAL
        };
        private static final int[] verticalCharIndexes = {
                TOP_ROW_LEFT_VERTICAL,
                TOP_ROW_MIDDLE_VERTICAL,
                TOP_ROW_RIGHT_VERTICAL,
                BOTTOM_ROW_LEFT_VERTICAL,
                BOTTOM_ROW_MIDDLE_VERTICAL,
                BOTTOM_ROW_RIGHT_VERTICAL
        };

        private final String boxDrawing;

        private BoxDrawingParser(final String boxDrawing) {
            this.boxDrawing = boxDrawing;
        }

        private char getChar(final int charIndex) {
            return boxDrawing.charAt(charIndex);
        }

        private void checkLength() {
            if (boxDrawing.length() != EXPECTED_LENGTH) {
                throw new IllegalArgumentException(
                        "boxDrawing is expected to be " + EXPECTED_LENGTH + " long"
                );
            }
        }

        private char getHorizontalOrVerticalChar(
                final String horizontalOrVertical,
                final int... indexes
        ) {
            final char expectedChar = getChar(indexes[0]);
            for (int i = 1; i < indexes.length; i++) {
                final char foundChar = getChar(indexes[i]);
                if (expectedChar != foundChar) {
                    throw new IllegalArgumentException(
                            "Inconsistent " + horizontalOrVertical + " line character; "
                            + "encountered both: " + expectedChar + " and " + foundChar
                    );
                }
            }
            return expectedChar;
        }

        private char getHorizontalChar() {
            return getHorizontalOrVerticalChar("horizontal", horizontalCharIndexes);
        }

        private char getVerticalChar() {
            return getHorizontalOrVerticalChar("vertical", verticalCharIndexes);
        }

        private BoxDrawingCharacters parse() {
            checkLength();
            return new Builder()
                    .setHorizontal(getHorizontalChar())
                    .setVertical(getVerticalChar())
                    .setTopLeftCorner(getChar(TOP_LEFT_CORNER))
                    .setTopIntersect(getChar(TOP_INTERSECT))
                    .setTopRightCorner(getChar(TOP_RIGHT_CORNER))
                    .setLeftIntersect(getChar(LEFT_INTERSECT))
                    .setMiddleIntersect(getChar(MIDDLE_INTERSECT))
                    .setRightIntersect(getChar(RIGHT_INTERSECT))
                    .setBottomLeftCorner(getChar(BOTTOM_LEFT_CORNER))
                    .setBottomIntersect(getChar(BOTTOM_INTERSECT))
                    .setBottomRightCorner(getChar(BOTTOM_RIGHT_CORNER))
                    .build();
        }

    }

}
