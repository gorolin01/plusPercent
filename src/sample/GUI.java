package sample;

import javafx.scene.input.ClipboardContent;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    private Font fontText = new Font("Calibri", Font.BOLD, 50);
    private Font fontTextPercent = new Font("Calibri", Font.BOLD, 14);
    private static String text;
    private JTextField tf;
    private JFrame frame;
    private JPanel panel;
    private JTextArea ta;
    private JTextField percentField;
    private int PERCENT = 0;

    GUI(){
        // Создание каркаса
        frame = new JFrame("Plus Percent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Создание панели внизу и добавление компонентов
        panel = new JPanel(); // панель не видна при выводе
        JLabel label = new JLabel("Введите цену:");
        tf = new JTextField(8); // принимает до 10 символов
        JButton clear = new JButton("Очистить");

        //Панель задания процента надбавки
        percentField = new JTextField();
        percentField.setFont(fontTextPercent);
        percentField.setPreferredSize(new Dimension(50, 20));
        JLabel labelPercent = new JLabel();
        labelPercent.setText("Текущий процент:");
        JPanel percentPanel = new JPanel();
        percentPanel.add(labelPercent);
        percentPanel.add(percentField);

        ta = new JTextArea();
        tf.setFont(fontText);
        ta.setFont(fontText);
        //ta.setEnabled(false);
        ta.setPreferredSize(new Dimension(350, 80));
        ta.setText(text);
        panel.add(label); // Компоненты, добавленные с помощью макета Flow Layout
        panel.add(tf);
        panel.add(clear);
        panel.add(ta);
        tf.setPreferredSize(new Dimension(350, 80));

        // Добавление компонентов в рамку.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, percentPanel);
        frame.setVisible(true);

        tf.addKeyListener (new KeyAdapter() {// Добавляем слушателя клавиатуры для первого поля ввода
            public void keyReleased (KeyEvent e)
            {
                if(getText() != null){
                    try {
                        String buf = String.format("%.0f", calcPlusPercent(Integer.parseInt(getText())));
                        setText(buf);

                        //Копирование цены в буфер обмена
                        ClipboardThread clipboardThread = new ClipboardThread();
                        clipboardThread.start();
                    }catch (NumberFormatException ex){
                        System.out.println("NumberFormatException occurred");
                        setText("");
                    }
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                setText("");
            }
        });
        percentField.addKeyListener (new KeyAdapter() {// Добавляем слушателя клавиатуры для первого поля ввода
            public void keyReleased (KeyEvent e)
            {
                try{
                    PERCENT = Integer.parseInt(percentField.getText());
                    String buf = String.format("%.0f", calcPlusPercent(Integer.parseInt(getText())));
                    setText(buf);

                    //Копирование цены в буфер обмена
                    ClipboardThread clipboardThread = new ClipboardThread();
                    clipboardThread.start();
                }catch (NumberFormatException ex){
                    System.out.println("NumberFormatException occurred");
                    setText("");
                }
            }
        });

    }

    private Double calcPlusPercent(int price){

        Double priceNew;
        priceNew = Double.valueOf(((price * PERCENT) / 100) + price);
        return priceNew;

    }

    public void setText(String text) {

        this.text = text;
        update();

    }

    private void update() {

        ta.removeAll();
        ta.setText(text);

        frame.repaint();
        frame.revalidate();

    }

    public String getText() {

        return tf.getText();

    }

    public class ClipboardThread extends Thread {
        @Override
        public void run() {
            String buf = String.format("%.0f", calcPlusPercent(Integer.parseInt(getText())));
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(buf);
            clipboard.setContents(strSel, null);
        }
    }

}
