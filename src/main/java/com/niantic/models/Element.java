package com.niantic.models;

import java.util.*;

public class Element {

    public static final String fire = "Fire";
    public static final String water = "Water";
    public static final String earth = "Earth";
    public static final String air = "Air";
    public static final String lightning = "Lightning";

    private static final Random random = new Random();

    private static final String[] elements = {fire, water, earth, air, lightning};

    public static String getRandomElement() {
        return elements[random.nextInt(elements.length)];
    }

    private static final Map<String, String> strongAgainst = new HashMap<>();
    private static final Map<String, String> weakAgainst = new HashMap<>();

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

    public static String getStrongAgainst(String element) {
        return strongAgainst.get(element);
    }

    public static String getWeakAgainst(String element) {
        return weakAgainst.get(element);
    }

    public static boolean isStrongAgainst(String element, String other) {
        return strongAgainst.get(element).equals(other);
    }

    public static boolean isWeakAgainst(String element, String other) {
        return weakAgainst.get(element).equals(other);
    }

}
