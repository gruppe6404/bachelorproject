package dk.aau.gr6406.trainez;


/**
 * @author Group 6404, Aalborg University, Sundhedsteknologi, 6th semester
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This class is a entity class for the dysphagia exercise measurements.
 * The constructor has no arguments
 */
public class ExerciseMeasurement extends Measurement {


    private ArrayList<Integer> exercises;

    public ExerciseMeasurement(){

    }

    public void setExercises(ArrayList<Integer> exercises){
       this.exercises = exercises;
    }

    public ArrayList<Integer> getExercies(){
        return exercises;
    }



}
