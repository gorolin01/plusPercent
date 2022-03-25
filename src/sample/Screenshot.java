package sample;

import java.awt.*;
import java.util.ArrayList;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Screenshot {

    private GUI gui;
    private String CHAR_OFF = " .,[]{}():;'‘’“«»-—_=+|/?<>!@#$%^&*qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM£";    //символы подлежащие удалению
    private String CHAR_ALL_OFF = ".,"; //удалить всю строку правее заданного символа

    public Screenshot(GUI gui) {
        this.gui = gui;
    }

    private static Tesseract getTesseract(){

        Tesseract Tesseract = new Tesseract();
        Tesseract.setDatapath("D:\\Programs\\Tesseract\\tessdata"); //путь к датасету
        Tesseract.setLanguage("eng");
        //Tesseract.setHocr(true);  //вывод через html
        return Tesseract;
    }

    public void doScreenshot(ArrayList<Integer> CoordinateStart, int Width, int Height){

        Robot robot = null;
        String result = null;
        Tesseract tesseract = getTesseract();

        try{
            robot = new Robot();
        }catch (AWTException e){
            e.printStackTrace();
        }

        try {
            result = tesseract.doOCR(robot.createScreenCapture( new Rectangle(CoordinateStart.get(0), CoordinateStart.get(1), Width, Height)));
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        if(gui.statusFilter()){
            gui.setTextMainField(resultFormatting(result));
        }else{
            gui.setTextMainField(result);
        }
        //System.out.println(result);

    }

    //удаление неугодных символов
    public String resultFormatting(String result) {

        String [] excludedChar = CHAR_OFF.split("");
        String [] excludedCharAll = CHAR_ALL_OFF.split("");

        //отделяем порядок от мантиссы числа
        for(int i = 0; i < excludedCharAll.length; i++){
            if(result.contains(excludedCharAll[i])){
                result = result.substring(0, result.indexOf(excludedCharAll[i]));
            }
        }

        //исключаем посторонние символы из числа
        for(int i = 0; i < excludedChar.length; i++){
            if (result.contains(excludedChar[i])) {
                result = result.replace(excludedChar[i], "");
            }
        }
        return result;
    }

}
