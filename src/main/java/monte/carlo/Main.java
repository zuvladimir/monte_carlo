package monte.carlo;

import java.util.Locale;
import javax.swing.SwingUtilities;

/**
 * <b>Класс Main</b> - это точка входа в приложение.
 * Приложение построено с использованием паттерна MVC и содержит класс модели Model, 
 * представления View и контроллера Controller.
 * 
 * <b>Краткое описание функционала:</b>
 * Вычисление значения рисков:
 * <ul>
 *      <li>Риск превышение трудозатрат</li>
 *      <li>Риск превышения объема работ</li>
 *      <li>Риск недостатка трудовых ресурсов</li>
 * </ul>
 * Построение графиков распределений.
 * Выбор вариантов обработки рисков для поддержки принятия решений.
 * 
 */
public class Main {
    public static void main(String... args) {
        Locale.setDefault(new Locale("ru","RU"));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new Model());
            }
        });
    }
}
