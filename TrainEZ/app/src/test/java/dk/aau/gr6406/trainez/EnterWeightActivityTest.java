package dk.aau.gr6406.trainez;

import android.util.Log;
import android.widget.EditText;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marti on 5/20/2017.
 */
public class EnterWeightActivityTest {
    @Test
    public void saveButtonClicked() throws Exception {


        String res = "55.2";


        float weight = Float.parseFloat(res);

        assertEquals(55.204,weight,0.001);


    }


}