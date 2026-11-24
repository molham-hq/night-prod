package me.xhurricane.nightProductivity.util;

import me.xhurricane.nightProductivity.hallucinate.Hallucination;

import java.util.Random;

public class RandomUtils {

    public static Hallucination getRandomHallucination() {
        Random random = new Random();
        int index = random.nextInt(Hallucination.values().length);
        return Hallucination.values()[index];
    }

    public static double getRandomPercentage() {
        Random random = new Random();
        return random.nextDouble();
    }

}
