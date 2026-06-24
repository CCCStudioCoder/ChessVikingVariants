package com.cccstudio.chess_viking_variants.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * The singleton {@link PlayContext} is the central part of the application. It contains all the metadata relating to a game.
 * It's organised using {@link Field}s. Each field represent one piece of metadata.
 * @see PlayContext#get()
 * @see Field
 */
public class PlayContext {

    @Nullable
    private static PlayContext instance;

    private final Collection<Field<?>> fields = new ArrayList<>(List.of(
            new Field<>("turn", Integer.class, 0),
            new Field<>("player-count", Integer.class, 2),
            new Field<>("board", Board.class, null)
    ));

    @ApiStatus.Internal
    public static void init(Variant variant) {}

    void setContext(PlayContext context) {
        instance = context;
    }

    PlayContext(boolean isSample) {
        if(!isSample) instance = this;
    }

    /**
     * Create a "sample" {@link PlayContext}. Calling this function <b>will not</b> update the {@link PlayContext#instance}.
     * It can be used for example for the parsing system at {@link PGNParser}.
     */
    public PlayContext createSample() {
        return new PlayContext(true);
    }

    /**
     * If the field with the given name is already present in {@link PlayContext#fields}, its value will be set.
     * Otherwise, it will register a new field in {@link PlayContext#fields}.
     * @param name
     * The name of the target field.
     * @param type
     * Its type.
     * @param value
     * The default value of the field.
     */
    public <T> void registerOrSetField(String name, Class<T> type, @Nullable T value) {
        if(fields.stream().anyMatch(field -> field.name.equals(name))) {
            findField(name, type).setValue(value);
        } else fields.add(new Field<>(name, type, value));
    }

    /**
     * Allows you to get any field of this context.
     * @param name
     * The name of the field.
     * @param type
     * The type of this field.
     * @return
     * The field with the given name.
     * @throws NoSuchElementException if there is no field with this name.
     * @throws ClassCastException if the field is not of the given type.
     */
    @SuppressWarnings("unchecked")
    public <T> Field<T> findField(String name, Class<T> type) {
        Optional<Field<?>> maybe = fields.stream().filter(field ->
                field.name.equals(name)).findFirst();
        if(maybe.isEmpty()) {
            throw new NoSuchElementException("No field with name " + name);
        } else {
            Object value = maybe.get().get();
            if(type.isInstance(value)) {
                return (Field<T>) maybe.get();
            }
            throw new ClassCastException("Type " + value.getClass() + " is not assignable to " + type);
        }
    }

    /**
     * Shortcut for {@code findField(...).value}
     */
    public <T> T findData(String name, Class<T> type) {
        Field<T> field = findField(name, type);
        return field.get();
    }

    /**
     * Returns an instance of {@link PlayContext} recorded at runtime.
     * @return The instance of {@link PlayContext}.
     */
    public static PlayContext get() {
        return Objects.requireNonNull(instance);
    }

    /* Utility functions */

    public int nextTurn() {
        int newTurn = this.findData("turn", Integer.class) + 1;
        if(newTurn == this.findData("player-count", Integer.class)) {
            newTurn = 0;
        }
        return newTurn;
    }

    public static Board getBoard() {
        return instance.findData("board", Board.class);
    }

    /**
     * A {@link Field} is a piece of metadata accessible by its {@link Field#name}.
     * If its {@link Field#value} is {@code null}, it's considered empty, and if it needs to be considered empty,
     * its {@link Field#value} will be {@code null}.
     * @param <T>
     * The type of the value held by the field.
     * @see Field#get()
     */
    public static class Field<T> {

        String name;

        @Nullable
        private T value;

        public final Class<T> type;

        public Field(String name, Class<T> type, @Nullable T value) {
            this.name = name;
            this.value = value;
            this.type = type;
        }

        public void setValue(@Nullable T value) {
            this.value = value;
        }

        /**
         * Return the value held by the field.
         * @return {@link Field#value}
         * @throws UnsetContextFieldException if the value is null. If it's the case, the field is considered empty,
         * so it throws an exception.
         */
        public T get() throws UnsetContextFieldException {
            if(value == null){
                throw new UnsetContextFieldException(this.name);
            } else return value;
        }

    }

}