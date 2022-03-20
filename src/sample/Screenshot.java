package sample;

import java.awt.*;
import java.util.ArrayList;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Screenshot {

    private GUI gui;

    public Screenshot(GUI gui) {
        this.gui = gui;
    }

    private static Tesseract getTesseract(){

        Tesseract Tesseract = new Tesseract();
        Tesseract.setDatapath("G:\\Program\\Tesseract\\tessdata");
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

        gui.setTextMainField(result);
        //System.out.println(result);

    }

}
