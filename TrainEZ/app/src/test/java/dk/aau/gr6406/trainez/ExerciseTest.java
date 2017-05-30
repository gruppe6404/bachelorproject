package dk.aau.gr6406.trainez;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marti on 5/20/2017.
 */
public class ExerciseTest {
    @Test
    public void getExcName() throws Exception {


    }

    @Test
    public void setExcName() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setExcName("mærtin");
        String result = exercise.getExcName();
        assertEquals("mærtin",result);

    }

    @Test
    public void getVideoPath() throws Exception {

    }

    @Test
    public void setVideoPath() throws Exception {

    }

    @Test
    public void getRepetitions() throws Exception {

    }

    @Test
    public void setRepetitions() throws Exception {

    }

    @Test
    public void getCategory() throws Exception {

    }

    @Test
    public void setCategory() throws Exception {

    }

    @Test
    public void getDefaultVal() throws Exception {

    }

    @Test
    public void setDefaultVal() throws Exception {

    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

}