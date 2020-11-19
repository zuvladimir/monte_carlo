package monte.carlo;

import java.util.logging.Level;
import java.util.logging.Logger;
import monte.carlo.controller.Controller;
import monte.carlo.model.Model;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <b>Класс Main</b> - это точка входа в приложение. Приложение построено с
 * использованием паттерна MVC и содержит класс модели Model, представления View
 * и контроллера Controller.
 *
 * <b>Краткое описание функционала:</b>
 * Вычисление значения рисков методом Монте-Карло:
 * <ul>
 * <li>Риск превышение трудозатрат</li>
 * <li>Риск превышения объема работ</li>
 * <li>Риск недостатка трудовых ресурсов</li>
 * </ul>
 * Построение графиков распределений. Выбор вариантов обработки рисков для
 * поддержки принятия решений.
 *
 */
public class Main {

    public static void main(String... args) {
        try {
            // установка темы оформления Nimbus
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // инициализация приложения
            SwingUtilities.invokeLater(() -> {
                Controller controller = new Controller(new Model());
            });
        } catch (Throwable e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
