package sample;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private Font fontText = new Font("Calibri", Font.BOLD, 50);
    private static String text;
    private JTextField tf;

    GUI(){
        // Создание каркаса
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        /*// Создание панели меню и добавление компонентов
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Открыть");
        JMenuItem m22 = new JMenuItem("Сохранить как");
        m1.add(m11);
        m1.add(m22);*/

        // Создание панели внизу и добавление компонентов
        JPanel panel = new JPanel(); // панель не видна при выводе
        JLabel label = new JLabel("Введите цену:");
        tf = new JTextField(8); // принимает до 10 символов
        JButton send = new JButton("Отправить");
        JTextArea ta = new JTextArea();
        tf.setFont(fontText);
        ta.setFont(fontText);
        //ta.setEnabled(false);
        ta.setPreferredSize(new Dimension(200, 80));
        ta.setText(text);
        panel.add(label); // Компоненты, добавленные с помощью макета Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(ta);
        tf.setPreferredSize(new Dimension(350, 80));

        /*// Текстовая область по центру
        JTextArea ta = new JTextArea();*/

        // Добавление компонентов в рамку.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

    

    public void setText(String text) {

        this.text = text;

    }

    public String getText() {

        return tf.getText();

    }

}
