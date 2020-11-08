package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;
import seedu.address.model.exercise.TemplateList;
import seedu.address.model.exercise.UniqueExerciseList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameexercise comparison)
 */
public class ExerciseBook implements ReadOnlyExerciseBook {

    protected final UniqueExerciseList exercises;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        exercises = new UniqueExerciseList();
    }

    public ExerciseBook() {
    }

    /**
     * Creates an ExerciseBook using the Exercises in the {@code toBeCopied}
     */
    public ExerciseBook(ReadOnlyExerciseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the exercise list with {@code exercise}.
     * {@code exercise} must not contain duplicate exercise.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code ExerciseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyExerciseBook newData) {
        requireNonNull(newData);
        setExercises(newData.getExerciseList());
    }

    /**
     * Reset all the data inside ExerciseBook.
     */
    public void resetAllData() throws IOException {
        exercises.resetAll();
        TemplateList.reset();
        //@@author Royxuzeng
        Template.writeToFile(new ArrayList<>());
        //@@author Royxuzeng
    }

    // exercise-level operations

    /**
     * Returns true if an Exercise with the same identity as {@code Exercise} exists in the exercise book.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Adds a exercise to the exercise book.
     * The exercise must not already exist in the exercise book.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the exercise book.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in the exercise
     * book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);
        exercises.updateExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code ExerciseBook}.
     * {@code key} must exist in the exercise book.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exercises.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public HashMap<String, Integer> getCaloriesByDay() {
        return exercises.getCaloriesByDay();
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Template> getTemplateList() {
        return TemplateList.getObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseBook // instanceof handles nulls
                && exercises.equals(((ExerciseBook) other).exercises));
    }

    @Override
    public int hashCode() {
        return exercises.hashCode();
    }
}
