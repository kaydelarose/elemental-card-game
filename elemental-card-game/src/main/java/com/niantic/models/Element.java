package com.niantic.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Element {

    public static final String fire = "Fire";
    public static final String water = "Water";
    public static final String earth = "Earth";
    public static final String air = "Air";
    public static final String lightning = "Lightning";

    // created random object to generate random elements
    private static final Random random = new Random();

    // array holding all elements
    private static final String[] elements = {fire, water, earth, air, lightning};

    // method used to randomly select an element
    public static String getRandomElement() {
        return elements[random.nextInt(elements.length)]; // generating a random number between 0 and the length of elements
    }

    // hashmap to store which element is stronger against other elements
    // hashmap to store which element is weaker against other elements
    private static final Map<String, String> strongAgainst = new HashMap<>();
    private static final Map<String, String> weakAgainst = new HashMap<>();


    // static block to initialize each map
    static {
        strongAgainst.put(fire, air);
        strongAgainst.put(water, fire);
        strongAgainst.put(earth, water);
        strongAgainst.put(air, earth);
        strongAgainst.put(lightning, water);

        weakAgainst.put(fire, water);
        weakAgainst.put(water, earth);
        weakAgainst.put(earth, air);
        weakAgainst.put(air, fire);
        weakAgainst.put(lightning, earth);

    }

    // return the element this one is strong against
    public static String getStrongAgainst(String element) {
        return strongAgainst.get(element);
    }

    // return the element this one is weak against
    public static String getWeakAgainst(String element) {
        return weakAgainst.get(element);
    }

    // method to check if element is strong against other element
    public static boolean isStrongAgainst(String element, String other) {
        return strongAgainst.get(element).equals(other);
    }

    // method to check if element is weaker against other element
    public static boolean isWeakAgainst(String element, String other) {
        return weakAgainst.get(element).equals(other);
    }


}
