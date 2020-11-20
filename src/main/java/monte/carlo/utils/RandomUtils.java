package monte.carlo.utils;

import java.util.Random;

public class RandomUtils {
    /**
     * Генерирование статистических данных. Вместо генерирование в будущем будут
     * использоваться настоящие статистические данные.
     *
     * @param min
     * @param max
     * @return
     */
    public static int[] generateStatisticData(int min, int max) {
        int[] result = new int[100];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRandomNumber(min, max);
        }
        return result;
    }

    /**
     * Генератор случайных числе из выбранного диапозона
     * 
     * @param min
     * @param max
     * @return 
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Получить случайное значение из распределения
     * 
     * @param distribution
     * @return 
     */
    public static int getValueFromDistribution(int[] distribution) {
        return distribution[getRandomNumber(0, distribution.length)];
    }
}
