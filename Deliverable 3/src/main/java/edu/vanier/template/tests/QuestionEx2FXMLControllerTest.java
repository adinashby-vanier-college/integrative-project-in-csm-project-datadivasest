package edu.vanier.template.tests;

import edu.vanier.template.controllers.QuestionEx2FXMLController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionEx2FXMLControllerTest {

    private QuestionEx2FXMLController controller;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws Exception {
        controller = new QuestionEx2FXMLController();

        // Inject some sample atomic masses for K, Br2, and KBr
        Field mapField = QuestionEx2FXMLController.class
                .getDeclaredField("molarMassMap");
        mapField.setAccessible(true);
        Map<String, Double> mm = (Map<String, Double>) mapField.get(controller);

        mm.put("K", 39.10);
        mm.put("Br", 79.90);
//        mm.put("KBr", 39.10 + 79.90);  // 119.00
    }

    @Test
    void testComputeMolarMass() throws Exception {
        // call private computeMolarMass("Br2")
        Method m = QuestionEx2FXMLController.class
                .getDeclaredMethod("computeMolarMass", String.class);
        m.setAccessible(true);

        double computed = (double) m.invoke(controller, "Br2");
        assertEquals(159.80, computed, 1e-6);
    }

    @Test
    void testStoichiometryForKBr() throws Exception {
        // reflectively build a StoichProblem(2,1,2,66,"K","Br2","KBr")
        Class<?> probCls = Class.forName(
                "edu.vanier.template.controllers.QuestionEx2FXMLController$StoichProblem");
        Constructor<?> constructor = probCls.getDeclaredConstructor(
                int.class, int.class, int.class,
                int.class, String.class, String.class, String.class
        );
        constructor.setAccessible(true);
        Object problem = constructor.newInstance(
                2,    // aCoeff
                1,    // bCoeff
                2,    // cCoeff
                66,   // productMass
                "K", "Br2", "KBr"
        );

        // set controller.currentProblem = problem
        Field cpField = QuestionEx2FXMLController.class
                .getDeclaredField("currentProblem");
        cpField.setAccessible(true);
        cpField.set(controller, problem);

        // now manually compute reqGramsX and reqGramsY as your code does
        Method compute = QuestionEx2FXMLController.class
                .getDeclaredMethod("computeMolarMass", String.class);
        compute.setAccessible(true);

        double mmK    = (double) compute.invoke(controller, "K");
        double mmBr2  = (double) compute.invoke(controller, "Br2");
        double mmProd = (double) compute.invoke(controller, "KBr");

        double targetMoles = 66.0 / mmProd;      // ≈0.5546 mol KBr
        double reqK   = targetMoles * 2/2 * mmK; // = 0.5546*1*39.1 ≈21.7 g
        double reqBr2 = targetMoles * 1/2 * mmBr2; // ≈44.3 g

        assertEquals(21.7, reqK,   0.1, "Required grams of K");
        assertEquals(44.3, reqBr2, 0.1, "Required grams of Br2");
    }
}
