package sample;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.util.ArrayList;

class GlobalMouseListener implements NativeMouseInputListener, NativeKeyListener {

    private ArrayList<Integer> CoordinateStart = new ArrayList<>();
    private Integer Width = 0;
    private Integer Height = 0;
    private Boolean HotKeyStatus = false;
    private Integer HOT_KAY = 3639;
    private GUI gui;

    public GlobalMouseListener(GUI gui) {
        this.gui = gui;
    }

    //реализация запуска по нажатию клавиши
    public void nativeKeyPressed(NativeKeyEvent e) {
        //System.out.println("Key Released: " + e.getKeyCode());
        if (e.getKeyCode() == HOT_KAY) {  //Клавиша PS
            HotKeyStatus = true;
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == HOT_KAY) {  //Клавиша PS
            HotKeyStatus = false;
        }
        //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    //слушатели мыши
    public void nativeMouseReleased(NativeMouseEvent e) {
        Width = e.getX() - CoordinateStart.get(0);
        Height = e.getY() - CoordinateStart.get(1);

        //пересчет координат для правильного позиционирования прямоугольника выдиления
        if(Width < 0){
            CoordinateStart.set(0, CoordinateStart.get(0) - Math.abs(Width));
            Width = Math.abs(Width) + Math.abs(Width);
        }
        if(Height < 0){
            CoordinateStart.set(1, CoordinateStart.get(1) - Math.abs(Height));
            Height = Math.abs(Height) + Math.abs(Height);
        }

        System.out.println("Width: " + Width + ", Height: " + Height);
        start();
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        CoordinateStart.clear();
        CoordinateStart.add(e.getX());
        CoordinateStart.add(e.getY());
    }

    public void start(){
        if (HotKeyStatus) {
            Screenshot screenshot = new Screenshot(gui);
            screenshot.doScreenshot(CoordinateStart, Width, Height);
        }
    }

}
