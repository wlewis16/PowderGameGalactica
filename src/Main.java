import javax.swing.*;
import java.awt.*;

/** Wanted to try my hand at making my own powder game in Java.
 * Just a clone of other games. Purely for fun :)
 *
 * - Will Lewis
 * - Have fun :p
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var frame = new JFrame("Powder Game Galactica");
            frame.getContentPane().add(new BasicPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}