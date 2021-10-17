package fadata.mobil.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pruefergebnis{

public static void LS_480x800_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.65d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(16d));

}
public static void LS_800x1280_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.8d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(1d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.15d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.2d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.6d * scale)));
views.get("rbnichterfassen").vw.setLeft((int)((50d / 100 * width)-((views.get("rbnichtbestanden").vw.getWidth())*(1.8d * scale))));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbnichtbestanden").vw.setLeft((int)((views.get("rbnichterfassen").vw.getLeft())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setLeft((int)((views.get("rbnichtbestanden").vw.getLeft())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(22d));

}
public static void LS_480x800_1_5(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.65d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(14d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(14d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(14d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(14d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(14d));

}
public static void LS_1200x1920_1_5(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.65d * scale)));
views.get("panpruefung").vw.setTop((int)((50d / 100 * width)-(120d * scale)));
views.get("panpruefung").vw.setLeft((int)((50d / 100 * height)-(470d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(22d));

}
public static void LS_1200x1920_1_75(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.6d * scale)));
views.get("panpruefung").vw.setTop((int)((50d / 100 * width)-(120d * scale)));
views.get("panpruefung").vw.setLeft((int)((50d / 100 * height)-(470d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.09d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.09d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(15d));

}
public static void LS_1536x2048_2(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.6d * scale)));
views.get("panpruefung").vw.setTop((int)((50d / 100 * width)-(120d * scale)));
views.get("panpruefung").vw.setLeft((int)((50d / 100 * height)-(470d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.09d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.09d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(15d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(15d));

}
public static void LS_720x1396_1_75(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.65d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(16d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(16d));

}
public static void LS_800x1216_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.8d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(1d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.15d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.2d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.6d * scale)));
views.get("rbnichterfassen").vw.setLeft((int)((50d / 100 * width)-((views.get("rbnichtbestanden").vw.getWidth())*(1.8d * scale))));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbnichtbestanden").vw.setLeft((int)((views.get("rbnichterfassen").vw.getLeft())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setLeft((int)((views.get("rbnichtbestanden").vw.getLeft())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(22d));

}
public static void LS_800x1223_1_5(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.6d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(1d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.15d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.2d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.5d * scale)));
views.get("rbnichterfassen").vw.setLeft((int)((100d / 100 * width)-((views.get("rbnichtbestanden").vw.getWidth())*(1.85d * scale))));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbnichtbestanden").vw.setLeft((int)((views.get("rbnichterfassen").vw.getLeft())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setLeft((int)((views.get("rbnichtbestanden").vw.getLeft())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(22d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(22d));

}
public static void LS_1200x1848_1_5(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panpruefung").vw.setWidth((int)((100d / 100 * width)*(0.4375d * scale)));
views.get("panpruefung").vw.setHeight((int)((50d / 100 * height)*(0.65d * scale)));
views.get("lblpruefungsergebisse").vw.setWidth((int)((views.get("panpruefung").vw.getWidth())));
views.get("lblpruefungsergebisse").vw.setHeight((int)((100d / 100 * width)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setTop((int)((views.get("lblpruefungsergebisse").vw.getHeight())+(5d * scale)));
views.get("rbnichterfassen").vw.setHeight((int)((50d / 100 * height)*(0.11d * scale)));
views.get("rbnichterfassen").vw.setWidth((int)((100d / 100 * width)*(0.4d * scale)));
views.get("rbnichtbestanden").vw.setTop((int)((views.get("rbnichterfassen").vw.getTop() + views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbnichtbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("rbbestanden").vw.setTop((int)((views.get("rbnichtbestanden").vw.getTop() + views.get("rbnichtbestanden").vw.getHeight())));
views.get("rbbestanden").vw.setHeight((int)((views.get("rbnichterfassen").vw.getHeight())));
views.get("rbbestanden").vw.setWidth((int)((views.get("rbnichterfassen").vw.getWidth())));
views.get("btpruefergebnisok").vw.setTop((int)((views.get("rbbestanden").vw.getTop() + views.get("rbbestanden").vw.getHeight())+(5d * scale)));
views.get("btpruefergebnisok").vw.setWidth((int)((100d / 100 * width)*(0.125d * scale)));
views.get("btpruefergebnisok").vw.setHeight((int)((50d / 100 * height)*(0.15d * scale)));
views.get("btpruefergebnisok").vw.setLeft((int)(((views.get("lblpruefungsergebisse").vw.getWidth())/2d)-((views.get("btpruefergebnisok").vw.getWidth())/2d)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpruefungsergebisse").vw).setTextSize((float)(19d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichterfassen").vw).setTextSize((float)(19d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbnichtbestanden").vw).setTextSize((float)(19d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("rbbestanden").vw).setTextSize((float)(19d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("btpruefergebnisok").vw).setTextSize((float)(19d));

}
}