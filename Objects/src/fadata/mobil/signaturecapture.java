package fadata.mobil;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class signaturecapture {
private static signaturecapture mostCurrent = new signaturecapture();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _lax = 0;
public static int _lay = 0;
public static int _s = 0;
public static int _istartx = 0;
public static int _istarty = 0;
public static int[] _savx = null;
public static int[] _savy = null;
public static String _spath = "";
public static int[] _pathpoints = null;
public static int _pathpointindex = 0;
public fadata.mobil.main _main = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.dbutils _dbutils = null;
public fadata.mobil.readwriteini _readwriteini = null;
public fadata.mobil.sonstigetaetigkeiten _sonstigetaetigkeiten = null;
public fadata.mobil.schueler _schueler = null;
public fadata.mobil.begleitfahrzeuge _begleitfahrzeuge = null;
public fadata.mobil.fahrlehrer _fahrlehrer = null;
public fadata.mobil.fahrtbezeichnung _fahrtbezeichnung = null;
public fadata.mobil.kalender _kalender = null;
public fadata.mobil.kfz _kfz = null;
public fadata.mobil.klassen _klassen = null;
public fadata.mobil.pruefergebnis _pruefergebnis = null;
public fadata.mobil.schuelerklasse _schuelerklasse = null;
public fadata.mobil.treffpunkt _treffpunkt = null;
public fadata.mobil.zahlungenfuer _zahlungenfuer = null;
public static class _signaturedata{
public boolean IsInitialized;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper Canvas;
public anywheresoftware.b4a.objects.PanelWrapper Panel;
public int SignatureColor;
public int SignatureWidth;
public void Initialize() {
IsInitialized = true;
Canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
Panel = new anywheresoftware.b4a.objects.PanelWrapper();
SignatureColor = 0;
SignatureWidth = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _pointtype{
public boolean IsInitialized;
public int x;
public int y;
public void Initialize() {
IsInitialized = true;
x = 0;
y = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _calculatmissingpoints(anywheresoftware.b4a.BA _ba,boolean _bcalc) throws Exception{
 //BA.debugLineNum = 147;BA.debugLine="Sub CalculatMissingPoints(bCalc As Boolean)";
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return "";
}
public static String  _clear(anywheresoftware.b4a.BA _ba,fadata.mobil.signaturecapture._signaturedata _sd) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub Clear(SD As SignatureData)";
 //BA.debugLineNum = 135;BA.debugLine="SD.Canvas.DrawColor(Colors.White)";
_sd.Canvas /*anywheresoftware.b4a.objects.drawable.CanvasWrapper*/ .DrawColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 136;BA.debugLine="SD.Panel.Invalidate";
_sd.Panel /*anywheresoftware.b4a.objects.PanelWrapper*/ .Invalidate();
 //BA.debugLineNum = 137;BA.debugLine="s = 0";
_s = (int) (0);
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _panel_touch(anywheresoftware.b4a.BA _ba,fadata.mobil.signaturecapture._signaturedata _sd,float _x,float _y,int _action) throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Panel_Touch(SD As SignatureData, x As Float, y";
 //BA.debugLineNum = 22;BA.debugLine="Select Action";
switch (_action) {
case 0: {
 //BA.debugLineNum = 24;BA.debugLine="laX = x";
_lax = (int) (_x);
 //BA.debugLineNum = 25;BA.debugLine="laY = y";
_lay = (int) (_y);
 break; }
case 1: {
 //BA.debugLineNum = 27;BA.debugLine="If laX = x And laY = y Then";
if (_lax==_x && _lay==_y) { 
 //BA.debugLineNum = 28;BA.debugLine="SD.Canvas.DrawCircle(laX, laY, 2, Colors.Black";
_sd.Canvas /*anywheresoftware.b4a.objects.drawable.CanvasWrapper*/ .DrawCircle((float) (_lax),(float) (_lay),(float) (2),anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.True,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 };
 break; }
}
;
 //BA.debugLineNum = 32;BA.debugLine="SD.Canvas.DrawLine(laX, laY, x, y, SD.SignatureCo";
_sd.Canvas /*anywheresoftware.b4a.objects.drawable.CanvasWrapper*/ .DrawLine((float) (_lax),(float) (_lay),_x,_y,_sd.SignatureColor /*int*/ ,(float) (_sd.SignatureWidth /*int*/ ));
 //BA.debugLineNum = 33;BA.debugLine="SD.Panel.Invalidate";
_sd.Panel /*anywheresoftware.b4a.objects.PanelWrapper*/ .Invalidate();
 //BA.debugLineNum = 34;BA.debugLine="laX = x";
_lax = (int) (_x);
 //BA.debugLineNum = 35;BA.debugLine="laY = y";
_lay = (int) (_y);
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_touch(anywheresoftware.b4a.BA _ba,fadata.mobil.signaturecapture._signaturedata _sd,float _x,float _y,int _action) throws Exception{
int _i = 0;
 //BA.debugLineNum = 38;BA.debugLine="Sub Panel1_Touch(SD As SignatureData, X As Float,";
 //BA.debugLineNum = 40;BA.debugLine="Panel_Touch(SD, X, Y, Action)";
_panel_touch(_ba,_sd,_x,_y,_action);
 //BA.debugLineNum = 43;BA.debugLine="If Main.lv.Height = 960 And Main.lv.Width = 540 T";
if (mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==960 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==540) { 
 //BA.debugLineNum = 44;BA.debugLine="X = Round(X * 2 / 6)";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)6));
 //BA.debugLineNum = 45;BA.debugLine="Y = Round(Y / 3)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)3));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height<976 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width<600) { 
 //BA.debugLineNum = 47;BA.debugLine="X = Round(X * 2 / 3)";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)3));
 //BA.debugLineNum = 48;BA.debugLine="Y = Round(Y / 2)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)2));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height>=976 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height<1280 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width>=600 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width<800 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width!=720) { 
 //BA.debugLineNum = 51;BA.debugLine="X = Round(X * 2 / 5)			'X = Round(X * 2 / 9)";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)5));
 //BA.debugLineNum = 52;BA.debugLine="Y = Round(Y / 2)				'Y = Round(Y / 10)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)2));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==1216 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==720) { 
 //BA.debugLineNum = 54;BA.debugLine="X = Round(X * 2 / 7)";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)7));
 //BA.debugLineNum = 55;BA.debugLine="Y = Round(Y / 3)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)3));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==1216 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==800) { 
 //BA.debugLineNum = 57;BA.debugLine="X = Round(X * 2 / 6)";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)6));
 //BA.debugLineNum = 58;BA.debugLine="Y = Round(Y / 2)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)2));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==1232 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==800) { 
 //BA.debugLineNum = 60;BA.debugLine="X = Round(X * 2 / 7)			'1280x720 Size: 10'' Tabl";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)7));
 //BA.debugLineNum = 61;BA.debugLine="Y = Round(Y / 2)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)2));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==1280 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==800) { 
 //BA.debugLineNum = 63;BA.debugLine="X = Round(X * 2 / 7)			'1280x720 Size: 8'' Table";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)7));
 //BA.debugLineNum = 64;BA.debugLine="Y = Round(Y / 3)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)3));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height==1396 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width==420) { 
 //BA.debugLineNum = 66;BA.debugLine="X = Round(X * 2 / 6)			'1396x420 Size: 8'' Table";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)6));
 //BA.debugLineNum = 67;BA.debugLine="Y = Round(Y / 2)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)2));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height<=1920 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width<=1080) { 
 //BA.debugLineNum = 70;BA.debugLine="X = Round(X * 2 / 9)			'1920x1080 Size: 3 Samsug";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)9));
 //BA.debugLineNum = 71;BA.debugLine="Y = Round(Y / 8)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)8));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height<=2560 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width<=1440 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Scale==4) { 
 //BA.debugLineNum = 74;BA.debugLine="X = Round(X * 2 / 14)			'2560x1440 Size: 4 Samsu";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)14));
 //BA.debugLineNum = 75;BA.debugLine="Y = Round(Y / 8)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)8));
 }else if(mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height<=2560 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width<=1440) { 
 //BA.debugLineNum = 78;BA.debugLine="X = Round(X * 2 / 14)			'2560x1440 Size: 3 Samsu";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)14));
 //BA.debugLineNum = 79;BA.debugLine="Y = Round(Y / 6)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)6));
 }else {
 //BA.debugLineNum = 82;BA.debugLine="X = Round(X * 2 / 14)			'2560x1600 Size: 2";
_x = (float) (anywheresoftware.b4a.keywords.Common.Round(_x*2/(double)14));
 //BA.debugLineNum = 83;BA.debugLine="Y = Round(Y / 4)";
_y = (float) (anywheresoftware.b4a.keywords.Common.Round(_y/(double)4));
 };
 //BA.debugLineNum = 90;BA.debugLine="Select Action";
switch (_action) {
case 0: {
 //BA.debugLineNum = 93;BA.debugLine="iStartX = X";
_istartx = (int) (_x);
 //BA.debugLineNum = 94;BA.debugLine="iStartY = Y";
_istarty = (int) (_y);
 //BA.debugLineNum = 96;BA.debugLine="PathPointIndex = 1";
_pathpointindex = (int) (1);
 //BA.debugLineNum = 98;BA.debugLine="For i = 0 To PathPoints.Length - 1 Step 1";
{
final int step44 = 1;
final int limit44 = (int) (_pathpoints.length-1);
_i = (int) (0) ;
for (;_i <= limit44 ;_i = _i + step44 ) {
 //BA.debugLineNum = 99;BA.debugLine="PathPoints(i) = 0";
_pathpoints[_i] = (int) (0);
 }
};
 //BA.debugLineNum = 102;BA.debugLine="PathPoints(6) = X";
_pathpoints[(int) (6)] = (int) (_x);
 //BA.debugLineNum = 103;BA.debugLine="PathPoints(7) = Y";
_pathpoints[(int) (7)] = (int) (_y);
 break; }
case 2: {
 //BA.debugLineNum = 105;BA.debugLine="PathPointIndex = PathPointIndex + 1";
_pathpointindex = (int) (_pathpointindex+1);
 //BA.debugLineNum = 108;BA.debugLine="PathPoints(0) = PathPoints(2)";
_pathpoints[(int) (0)] = _pathpoints[(int) (2)];
 //BA.debugLineNum = 109;BA.debugLine="PathPoints(1) = PathPoints(3)";
_pathpoints[(int) (1)] = _pathpoints[(int) (3)];
 //BA.debugLineNum = 110;BA.debugLine="PathPoints(2) = PathPoints(4)";
_pathpoints[(int) (2)] = _pathpoints[(int) (4)];
 //BA.debugLineNum = 111;BA.debugLine="PathPoints(3) = PathPoints(5)";
_pathpoints[(int) (3)] = _pathpoints[(int) (5)];
 //BA.debugLineNum = 112;BA.debugLine="PathPoints(4) = PathPoints(6)";
_pathpoints[(int) (4)] = _pathpoints[(int) (6)];
 //BA.debugLineNum = 113;BA.debugLine="PathPoints(5) = PathPoints(7)";
_pathpoints[(int) (5)] = _pathpoints[(int) (7)];
 //BA.debugLineNum = 114;BA.debugLine="PathPoints(6) = X";
_pathpoints[(int) (6)] = (int) (_x);
 //BA.debugLineNum = 115;BA.debugLine="PathPoints(7) = Y";
_pathpoints[(int) (7)] = (int) (_y);
 //BA.debugLineNum = 118;BA.debugLine="If PathPoints(0) > 0 Then";
if (_pathpoints[(int) (0)]>0) { 
 //BA.debugLineNum = 119;BA.debugLine="CalculatMissingPoints(True)";
_calculatmissingpoints(_ba,anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
case 1: {
 //BA.debugLineNum = 123;BA.debugLine="If PathPointIndex < 4 Then";
if (_pathpointindex<4) { 
 //BA.debugLineNum = 124;BA.debugLine="CalculatMissingPoints(False)";
_calculatmissingpoints(_ba,anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 127;BA.debugLine="For i = 0 To PathPoints.Length - 1 Step 1";
{
final int step66 = 1;
final int limit66 = (int) (_pathpoints.length-1);
_i = (int) (0) ;
for (;_i <= limit66 ;_i = _i + step66 ) {
 //BA.debugLineNum = 128;BA.debugLine="PathPoints(i) = 0";
_pathpoints[_i] = (int) (0);
 }
};
 break; }
}
;
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim laX, laY, s, iStartX, iStartY As Int";
_lax = 0;
_lay = 0;
_s = 0;
_istartx = 0;
_istarty = 0;
 //BA.debugLineNum = 7;BA.debugLine="Type SignatureData (Canvas As Canvas, Panel As Pa";
;
 //BA.debugLineNum = 9;BA.debugLine="Dim savX(5000) As Int";
_savx = new int[(int) (5000)];
;
 //BA.debugLineNum = 10;BA.debugLine="Dim savY(5000) As Int";
_savy = new int[(int) (5000)];
;
 //BA.debugLineNum = 12;BA.debugLine="Dim sPath As String";
_spath = "";
 //BA.debugLineNum = 14;BA.debugLine="Dim PathPoints(8) As Int";
_pathpoints = new int[(int) (8)];
;
 //BA.debugLineNum = 15;BA.debugLine="Dim PathPointIndex As Int";
_pathpointindex = 0;
 //BA.debugLineNum = 17;BA.debugLine="Type PointType (x As Int, y As Int)";
;
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _signaturetostring(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub SignatureToString";
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
}
