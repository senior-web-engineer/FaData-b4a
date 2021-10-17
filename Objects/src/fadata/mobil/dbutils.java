package fadata.mobil;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class dbutils {
private static dbutils mostCurrent = new dbutils();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public fadata.mobil.main _main = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.signaturecapture _signaturecapture = null;
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
public static String  _begleitfahrzeugholen(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
String _sbegleitfahrzeug = "";
String _sselect = "";
 //BA.debugLineNum = 2052;BA.debugLine="Sub BegleitfahrzeugHolen(ITerminID As Int) As Stri";
 //BA.debugLineNum = 2053;BA.debugLine="Dim sBegleitFahrzeug, sSelect As String";
_sbegleitfahrzeug = "";
_sselect = "";
 //BA.debugLineNum = 2055;BA.debugLine="sSelect = \"SELECT 	f.Bezeichnung \" & _ 					\" FRO";
_sselect = "SELECT 	f.Bezeichnung "+" FROM Fahrzeuge f "+"JOIN Termine t ON t.BegleitFahrzeug_ID = f.FzgNr "+"WHERE t.OID = "+BA.NumberToString(_iterminid)+" AND t.BegleitFahrzeug_ID > 0";
 //BA.debugLineNum = 2061;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2062;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2063;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2067;BA.debugLine="Try";
try { //BA.debugLineNum = 2068;BA.debugLine="sBegleitFahrzeug = Main.SQL1.ExecQuerySingleR";
_sbegleitfahrzeug = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 2071;BA.debugLine="Log(\"Fehler beim holen des Begleitfahrzeuges";
anywheresoftware.b4a.keywords.Common.LogImpl("017825811","Fehler beim holen des Begleitfahrzeuges "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2072;BA.debugLine="sBegleitFahrzeug = \"keine Auswahl\"";
_sbegleitfahrzeug = "keine Auswahl";
 };
 //BA.debugLineNum = 2075;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2077;BA.debugLine="Return sBegleitFahrzeug";
if (true) return _sbegleitfahrzeug;
 //BA.debugLineNum = 2078;BA.debugLine="End Sub";
return "";
}
public static boolean  _cbool(anywheresoftware.b4a.BA _ba,Object _o) throws Exception{
 //BA.debugLineNum = 4143;BA.debugLine="Sub CBool(o As Object) As Boolean";
 //BA.debugLineNum = 4144;BA.debugLine="If o = 1 Then";
if ((_o).equals((Object)(1))) { 
 //BA.debugLineNum = 4145;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 4147;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 4149;BA.debugLine="End Sub";
return false;
}
public static int  _checkanzahlfixertreffpunkte(anywheresoftware.b4a.BA _ba) throws Exception{
int _ianzahl = 0;
 //BA.debugLineNum = 2491;BA.debugLine="Sub CheckAnzahlFixerTreffpunkte As Int";
 //BA.debugLineNum = 2492;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2494;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2495;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2496;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2499;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) "+"FROM Treffpunkt "+"WHERE fixer_Treffpunkt = 1 ")));
 //BA.debugLineNum = 2502;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2504;BA.debugLine="Return iAnzahl";
if (true) return _ianzahl;
 //BA.debugLineNum = 2505;BA.debugLine="End Sub";
return 0;
}
public static boolean  _checkbegleitfahrzeug(anywheresoftware.b4a.BA _ba,String _sauswahlklasse) throws Exception{
boolean _bresult = false;
String _sselect = "";
int _ija = 0;
 //BA.debugLineNum = 2549;BA.debugLine="Sub CheckBegleitfahrzeug(sAuswahlKlasse As String)";
 //BA.debugLineNum = 2550;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 2551;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2552;BA.debugLine="Dim iJa As Int";
_ija = 0;
 //BA.debugLineNum = 2554;BA.debugLine="sSelect = \"SELECT MitBegleitfahrzeug \" & _ 					\"";
_sselect = "SELECT MitBegleitfahrzeug "+"FROM Klassen "+"WHERE Bezeichnung LIKE '"+_sauswahlklasse+"' ";
 //BA.debugLineNum = 2558;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2559;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2560;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2564;BA.debugLine="Try";
try { //BA.debugLineNum = 2565;BA.debugLine="iJa = Main.SQL1.ExecQuerySingleResult(sSelect";
_ija = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 2567;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 2569;BA.debugLine="Log(\"Bei dieser Klasse '\" & sAuswahlKlasse &";
anywheresoftware.b4a.keywords.Common.LogImpl("018612244","Bei dieser Klasse '"+_sauswahlklasse+"' ist kein Begleitfahrzeug definiert/hinterlegt",0);
 //BA.debugLineNum = 2570;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2573;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2575;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 2576;BA.debugLine="End Sub";
return false;
}
public static int  _checkbvfdataexists(anywheresoftware.b4a.BA _ba,String _sdate,String _sschueler) throws Exception{
String _ibvfid = "";
 //BA.debugLineNum = 3048;BA.debugLine="Sub CheckBVFDataExists(sDate As String, sSchueler";
 //BA.debugLineNum = 3049;BA.debugLine="Dim iBVFid As String";
_ibvfid = "";
 //BA.debugLineNum = 3051;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 3052;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3053;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3057;BA.debugLine="iBVFid = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ibvfid = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT OID "+"FROM AusbKontrolle "+"WHERE SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+" AND MatchCode = '"+_sschueler+"' "+" AND Datum = '"+_sdate+"' ");
 //BA.debugLineNum = 3062;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 3064;BA.debugLine="If iBVFid = Null Then";
if (_ibvfid== null) { 
 //BA.debugLineNum = 3065;BA.debugLine="Return 0";
if (true) return (int) (0);
 }else {
 //BA.debugLineNum = 3067;BA.debugLine="Return iBVFid";
if (true) return (int)(Double.parseDouble(_ibvfid));
 };
 //BA.debugLineNum = 3069;BA.debugLine="End Sub";
return 0;
}
public static boolean  _checkdatenindb(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _bresult = false;
int _ianzahl = 0;
 //BA.debugLineNum = 34;BA.debugLine="Sub CheckDatenInDB() As Boolean";
 //BA.debugLineNum = 35;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 36;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 38;BA.debugLine="Try";
try { //BA.debugLineNum = 39;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 40;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 41;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 44;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler")));
 //BA.debugLineNum = 46;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 47;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 49;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 52;BA.debugLine="Log(\"Kein Datenbankzugriff -> Datenbank nicht vo";
anywheresoftware.b4a.keywords.Common.LogImpl("014876690","Kein Datenbankzugriff -> Datenbank nicht vorhanden!",0);
 //BA.debugLineNum = 53;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 56;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 57;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return false;
}
public static String  _checkeintragschongespeichert(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
String _sselect = "";
String _sresult = "";
 //BA.debugLineNum = 2987;BA.debugLine="Sub CheckEintragSchonGespeichert(iTerminID As Int)";
 //BA.debugLineNum = 2988;BA.debugLine="Dim sSelect, sResult As String";
_sselect = "";
_sresult = "";
 //BA.debugLineNum = 2990;BA.debugLine="sSelect = \"SELECT TransKz \" & _ 					\"FROM Termin";
_sselect = "SELECT TransKz "+"FROM Termine "+"WHERE OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2994;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2995;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2996;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3000;BA.debugLine="Try";
try { //BA.debugLineNum = 3001;BA.debugLine="sResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_sresult = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 3005;BA.debugLine="sResult = \"Error\"";
_sresult = "Error";
 };
 //BA.debugLineNum = 3008;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 3010;BA.debugLine="Return sResult";
if (true) return _sresult;
 //BA.debugLineNum = 3011;BA.debugLine="End Sub";
return "";
}
public static boolean  _checkfixetreffpunkte(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _breturn = false;
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 3014;BA.debugLine="Sub CheckFixeTreffpunkte As Boolean";
 //BA.debugLineNum = 3015;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 3016;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 3017;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 3019;BA.debugLine="sSelect = \"SELECT COUNT(*) \" & _ 					\"FROM Treff";
_sselect = "SELECT COUNT(*) "+"FROM Treffpunkt "+"WHERE Reserve1 IS NOT null";
 //BA.debugLineNum = 3023;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 3024;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3025;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3028;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 3030;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 3031;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 3033;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 3036;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 3037;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 3038;BA.debugLine="End Sub";
return false;
}
public static String  _checksetfixenfahrlehrerfahrzeug(anywheresoftware.b4a.BA _ba,String[] _afahrdata) throws Exception{
String _supdate = "";
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 252;BA.debugLine="Sub CheckSetFixenFahrlehrerFahrzeug(aFahrData() As";
 //BA.debugLineNum = 253;BA.debugLine="Dim sUpdate, sSelect As String";
_supdate = "";
_sselect = "";
 //BA.debugLineNum = 254;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 256;BA.debugLine="sSelect = \"SELECT OID FROM Fahrlehrer WHERE Name";
_sselect = "SELECT OID FROM Fahrlehrer WHERE Name LIKE '"+_afahrdata[(int) (0)]+"' AND FahrlehrerNr = '"+_afahrdata[(int) (1)]+"'";
 //BA.debugLineNum = 258;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 259;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 260;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 264;BA.debugLine="Try";
try { //BA.debugLineNum = 265;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 267;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 268;BA.debugLine="sUpdate = \"UPDATE Fahrlehrer SET IsSelected = 1";
_supdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE OID = "+BA.NumberToString(_ianzahl);
 }else {
 //BA.debugLineNum = 270;BA.debugLine="sUpdate = \"UPDATE Fahrlehrer SET IsSelected = 1";
_supdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE OID = 1";
 };
 //BA.debugLineNum = 273;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e17) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e17); //BA.debugLineNum = 276;BA.debugLine="Log(\"Fehler beim setzen des fixen Fahrlehrers\")";
anywheresoftware.b4a.keywords.Common.LogImpl("015335448","Fehler beim setzen des fixen Fahrlehrers",0);
 };
 //BA.debugLineNum = 279;BA.debugLine="sSelect = \"SELECT OID FROM Fahrzeuge WHERE Bezeic";
_sselect = "SELECT OID FROM Fahrzeuge WHERE Bezeichnung LIKE '"+_afahrdata[(int) (2)]+"' AND FzgNr = "+_afahrdata[(int) (3)];
 //BA.debugLineNum = 281;BA.debugLine="Try";
try { //BA.debugLineNum = 282;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 284;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 285;BA.debugLine="sUpdate = \"UPDATE Fahrzeuge SET IsSelected = 1";
_supdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = "+BA.NumberToString(_ianzahl);
 }else {
 //BA.debugLineNum = 287;BA.debugLine="sUpdate = \"UPDATE Fahrzeuge SET IsSelected = 1";
_supdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = 1";
 };
 //BA.debugLineNum = 290;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e29) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e29); //BA.debugLineNum = 293;BA.debugLine="Log(\"Fehler beim setzen des fixen Fahrzeugs\")";
anywheresoftware.b4a.keywords.Common.LogImpl("015335465","Fehler beim setzen des fixen Fahrzeugs",0);
 };
 //BA.debugLineNum = 297;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 298;BA.debugLine="End Sub";
return "";
}
public static boolean  _checksignature(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
boolean _breturn = false;
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 2676;BA.debugLine="Sub CheckSignature(iTerminID As Int) As Boolean";
 //BA.debugLineNum = 2677;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 2678;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2679;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2681;BA.debugLine="sSelect = \"SELECT COUNT(*) \" & _ 					\"FROM Signa";
_sselect = "SELECT COUNT(*) "+"FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2690;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2691;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2692;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2695;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 2697;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 2698;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2700;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2703;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2705;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 2706;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkterminpruefung(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
boolean _bresult = false;
String _sselect = "";
int _ioid = 0;
 //BA.debugLineNum = 2873;BA.debugLine="Sub CheckTerminPruefung(iTerminID As Int) As Boole";
 //BA.debugLineNum = 2874;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 2875;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2876;BA.debugLine="Dim iOID As Int";
_ioid = 0;
 //BA.debugLineNum = 2878;BA.debugLine="sSelect = \"SELECT COUNT(*) \" & _ 				\"FROM Fahrtb";
_sselect = "SELECT COUNT(*) "+"FROM Fahrtbezeichnung f "+"JOIN Termine t ON t.FahrtBezeichnung_ID = f.OID "+"WHERE f.Pruefungen = 1 "+"AND t.OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2884;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2885;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2886;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2889;BA.debugLine="Try";
try { //BA.debugLineNum = 2890;BA.debugLine="iOID = Main.SQL1.ExecQuerySingleResult(sSelect)";
_ioid = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 2891;BA.debugLine="If iOID > 0 Then";
if (_ioid>0) { 
 //BA.debugLineNum = 2892;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2894;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e17) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e17); //BA.debugLineNum = 2897;BA.debugLine="Log(\"kein Prüfungs-Termin\")";
anywheresoftware.b4a.keywords.Common.LogImpl("019136536","kein Prüfungs-Termin",0);
 //BA.debugLineNum = 2898;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2901;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2902;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 2903;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkversion(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _bresult = false;
String _sversion = "";
 //BA.debugLineNum = 8;BA.debugLine="Sub CheckVersion() As Boolean";
 //BA.debugLineNum = 9;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 10;BA.debugLine="Dim sVersion As String";
_sversion = "";
 //BA.debugLineNum = 11;BA.debugLine="Try";
try { //BA.debugLineNum = 12;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 13;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 14;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 17;BA.debugLine="sVersion = Main.SQL1.ExecQuerySingleResult(\"SELE";
_sversion = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT DBVersion FROM Konfigdaten");
 //BA.debugLineNum = 19;BA.debugLine="If sVersion = Main.VersionsNummer Then";
if ((_sversion).equals(mostCurrent._main._versionsnummer /*String*/ )) { 
 //BA.debugLineNum = 20;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 22;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 25;BA.debugLine="Log(\"Kein Datenbankzugriff -> Datenbank nicht vo";
anywheresoftware.b4a.keywords.Common.LogImpl("014811153","Kein Datenbankzugriff -> Datenbank nicht vorhanden!",0);
 //BA.debugLineNum = 26;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 29;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 30;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return false;
}
public static boolean  _datenvormerkeninsert(anywheresoftware.b4a.BA _ba,String _stermin,String _sstartzeit,String _sdauer,String _sklasse,String _sfahrbezeichnung,String _streffpunkt,String _sfahrlehrer,String _skfz,String _sbegleitfahrzeug) throws Exception{
boolean _bresult = false;
String _sinsert = "";
int _itreffpunktid = 0;
 //BA.debugLineNum = 1265;BA.debugLine="Sub DatenVormerkenInsert(sTermin As String, sStart";
 //BA.debugLineNum = 1268;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1269;BA.debugLine="Dim sInsert As String";
_sinsert = "";
 //BA.debugLineNum = 1271;BA.debugLine="Dim iTreffpunktID As Int";
_itreffpunktid = 0;
 //BA.debugLineNum = 1273;BA.debugLine="If sFahrbezeichnung = \"Unt\" Then";
if ((_sfahrbezeichnung).equals("Unt")) { 
 //BA.debugLineNum = 1274;BA.debugLine="sInsert = \"INSERT INTO Termine (\" & _ 									\"";
_sinsert = "INSERT INTO Termine ("+"Fahrlehrer_ID, "+"Schueler_ID, "+"MatchCode, "+"Termin, "+"Klassen_ID, "+"Klasse, "+"Fahrtbezeichnung_ID, "+"FahrtBezeichnung_Abkuerzen, "+"Starttermin, "+"Dauer, "+"Treffpunkt_ID, "+"MeetingPoint, "+"Hat_Unterschrift, "+"Fahrzeug_ID, "+"Begleitfahrzeug_ID, "+"PruefungsErgebnis, "+"PruefungsTagNr, "+"TransKz) "+"VALUES ( "+"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"999, "+"'Sonstige Tätigkeit', "+"'"+_stermin+"', "+"0, "+"' ', "+"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"'"+_sfahrbezeichnung+"', "+"'"+_sstartzeit+"', "+"'"+_sdauer+"', "+BA.NumberToString(_itreffpunktid)+", "+"' ', "+"0, "+"0, "+"0, "+"' ', "+"' ', "+"'T' "+") ";
 }else {
 //BA.debugLineNum = 1314;BA.debugLine="iTreffpunktID = TreffpunktPruefen(sTreffpunkt)";
_itreffpunktid = _treffpunktpruefen(_ba,_streffpunkt);
 //BA.debugLineNum = 1316;BA.debugLine="If iTreffpunktID = 0 Then";
if (_itreffpunktid==0) { 
 //BA.debugLineNum = 1318;BA.debugLine="iTreffpunktID = InsertNeuerTreffpunkt(sTreffpun";
_itreffpunktid = _insertneuertreffpunkt(_ba,_streffpunkt,(int) (0));
 };
 //BA.debugLineNum = 1321;BA.debugLine="sInsert = \"INSERT INTO Termine (\" & _ 									\"";
_sinsert = "INSERT INTO Termine ("+"Fahrlehrer_ID, "+"Schueler_ID, "+"MatchCode, "+"Termin, "+"Klassen_ID, "+"Klasse, "+"Fahrtbezeichnung_ID, "+"FahrtBezeichnung_Abkuerzen, "+"Starttermin, "+"Dauer, "+"Treffpunkt_ID, "+"MeetingPoint, "+"Hat_Unterschrift, "+"Fahrzeug_ID, "+"Begleitfahrzeug_ID, "+"PruefungsErgebnis, "+"PruefungsTagNr, "+"TransKz) "+"VALUES ( "+"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"(SELECT SchuelerID FROM Schueler WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+"),  "+"'"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"', "+"'"+_stermin+"', "+"(SELECT OID FROM Klassen WHERE Bezeichnung LIKE '"+_sklasse+"' AND Enable = 1), "+"'"+_sklasse+"', "+"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"'"+_sfahrbezeichnung+"', "+"'"+_sstartzeit+"', "+"'"+_sdauer+"', "+BA.NumberToString(_itreffpunktid)+", "+"(SELECT Bezeichnung FROM Treffpunkt WHERE OID = "+BA.NumberToString(_itreffpunktid)+"), "+"0, "+"(SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung like '"+_skfz+"'), "+"(SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung LIKE '"+_sbegleitfahrzeug+"'), "+"' ', "+"' ', "+"'T' "+") ";
 };
 //BA.debugLineNum = 1361;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1362;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1363;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1367;BA.debugLine="Try";
try { //BA.debugLineNum = 1368;BA.debugLine="Main.SQL1.ExecNonQuery(sInsert)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsert);
 //BA.debugLineNum = 1370;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e21) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e21); //BA.debugLineNum = 1372;BA.debugLine="Log(\"Fehler beim speichern der Daten (Insert)";
anywheresoftware.b4a.keywords.Common.LogImpl("016908395","Fehler beim speichern der Daten (Insert) "+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1373;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1376;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1378;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1379;BA.debugLine="End Sub";
return false;
}
public static boolean  _datenvormerkenupdate(anywheresoftware.b4a.BA _ba,int _iterminid,String _stermin,String _sstartzeit,String _sdauer,String _sklasse,String _sfahrbezeichnung,String _streffpunkt,String _sfahrlehrer,String _skfz,String _sbegleitfahrzeug) throws Exception{
boolean _bresult = false;
String _supdate = "";
int _iid = 0;
 //BA.debugLineNum = 1938;BA.debugLine="Sub DatenVormerkenUpdate(iTerminID As Int, sTermin";
 //BA.debugLineNum = 1942;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1943;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 1946;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 1947;BA.debugLine="iID = TreffpunktPruefen(sTreffpunkt)";
_iid = _treffpunktpruefen(_ba,_streffpunkt);
 //BA.debugLineNum = 1949;BA.debugLine="If iID = 0 Then";
if (_iid==0) { 
 //BA.debugLineNum = 1950;BA.debugLine="iID = InsertNeuerTreffpunkt(sTreffpunkt, 0)";
_iid = _insertneuertreffpunkt(_ba,_streffpunkt,(int) (0));
 };
 //BA.debugLineNum = 1953;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 					\"SET Fahrleh";
_supdate = "UPDATE Termine "+"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"Schueler_ID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+", "+"MatchCode = '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"', "+"Termin = '"+_stermin+"', "+"Klassen_ID = (SELECT OID FROM Klassen WHERE Bezeichnung LIKE '"+_sklasse+"' AND Enable = 1), "+"Klasse = '"+_sklasse+"', "+"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"FahrtBezeichnung_Abkuerzen = '"+_sfahrbezeichnung+"', "+"Starttermin = '"+_sstartzeit+"', "+"Dauer = '"+_sdauer+"', "+"Treffpunkt_ID = "+BA.NumberToString(_iid)+", "+"MeetingPoint = (SELECT Bezeichnung FROM Treffpunkt WHERE OID = "+BA.NumberToString(_iid)+"), "+"Hat_Unterschrift = 0, "+"Fahrzeug_ID = (SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung like '"+_skfz+"'), "+"Begleitfahrzeug_ID = (SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung LIKE '"+_sbegleitfahrzeug+"'), "+"PruefungsErgebnis = ' ', "+"PruefungsTagNr = ' ', "+"TransKz = 'T' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 1974;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1975;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1976;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1980;BA.debugLine="Try";
try { //BA.debugLineNum = 1981;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 1983;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e17) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e17); //BA.debugLineNum = 1985;BA.debugLine="Log(\"Fehler beim speichern der Datenb (Update";
anywheresoftware.b4a.keywords.Common.LogImpl("017629231","Fehler beim speichern der Datenb (Update) "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1986;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1989;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1991;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1992;BA.debugLine="End Sub";
return false;
}
public static String  _dbinbenutzung(anywheresoftware.b4a.BA _ba,boolean _bvalue) throws Exception{
 //BA.debugLineNum = 2508;BA.debugLine="Sub DBinBenutzung(bValue As Boolean)";
 //BA.debugLineNum = 2509;BA.debugLine="Try";
try { //BA.debugLineNum = 2510;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2511;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2512;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2516;BA.debugLine="Main.SQL1.ExecNonQuery(\"UPDATE Konfigdaten \" & _";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery("UPDATE Konfigdaten "+"SET DBVersion  = '"+mostCurrent._main._versionsnummer /*String*/ +"' ");
 //BA.debugLineNum = 2519;BA.debugLine="If bValue Then";
if (_bvalue) { 
 //BA.debugLineNum = 2520;BA.debugLine="If Main.SQL1.ExecQuerySingleResult(\"SELECT COUN";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM DateiPass")).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 2521;BA.debugLine="Main.SQL1.ExecNonQuery(\"INSERT INTO DateiPass";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery("INSERT INTO DateiPass ("+"DatenVom, "+"MdbDatei ) "+"VALUES ("+"'"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"', "+"'open' )");
 }else {
 //BA.debugLineNum = 2528;BA.debugLine="Main.SQL1.ExecNonQuery(\"UPDATE DateiPass \" & _";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery("UPDATE DateiPass "+"SET MdbDatei = 'open',	"+"DatenVom = '"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"' ");
 };
 }else {
 //BA.debugLineNum = 2533;BA.debugLine="Main.SQL1.ExecNonQuery(\"UPDATE DateiPass \" & _";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery("UPDATE DateiPass "+"SET MdbDatei = 'closed' ");
 };
 //BA.debugLineNum = 2537;BA.debugLine="If Main.SQL1.IsInitialized = False Then";
if (mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 2538;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2539;BA.debugLine="ToastMessageShow(\"Datenbank initialisiert\", Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Datenbank initialisiert"),anywheresoftware.b4a.keywords.Common.False);
 };
 } 
       catch (Exception e21) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e21); //BA.debugLineNum = 2542;BA.debugLine="Log(\"Datenbank-Fehler \" & LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("018546722","Datenbank-Fehler "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 2545;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2546;BA.debugLine="End Sub";
return "";
}
public static String  _erfasstezahlungenholen(anywheresoftware.b4a.BA _ba,String _sauswahl,anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungen) throws Exception{
String _sselectsum = "";
String _sselectlist = "";
String _ssumme = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
String _smacode = "";
String _sbetrag = "";
int _igesamt = 0;
int _ipos = 0;
 //BA.debugLineNum = 787;BA.debugLine="Sub ErfassteZahlungenHolen(sAuswahl As String, lst";
 //BA.debugLineNum = 789;BA.debugLine="Dim sSelectSum, sSelectList, sSumme As String";
_sselectsum = "";
_sselectlist = "";
_ssumme = "";
 //BA.debugLineNum = 790;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 792;BA.debugLine="If sAuswahl = \"Alle\" Then";
if ((_sauswahl).equals("Alle")) { 
 //BA.debugLineNum = 793;BA.debugLine="sSelectSum = 	\"SELECT SUM(z.Betrag) \" & _";
_sselectsum = "SELECT SUM(z.Betrag) "+"FROM Zahlungen z "+"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID "+"WHERE f.IsSelected = 1";
 //BA.debugLineNum = 797;BA.debugLine="sSelectList = 	\"SELECT 	z.ZahlDatum, \" & _";
_sselectlist = "SELECT 	z.ZahlDatum, "+"z.MatchCode, "+"z.Betrag "+"FROM Zahlungen z "+"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID "+"WHERE f.IsSelected = 1 "+"ORDER BY z.ZahlDatum";
 }else {
 //BA.debugLineNum = 805;BA.debugLine="sSelectSum = 	\"SELECT SUM(z.Betrag) \" & _";
_sselectsum = "SELECT SUM(z.Betrag) "+"FROM Zahlungen z "+"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID "+"WHERE z.ZahlDatum LIKE '"+_sauswahl+"' "+"AND f.IsSelected = 1";
 //BA.debugLineNum = 810;BA.debugLine="sSelectList = 	\"SELECT 	z.ZahlDatum, \" & _";
_sselectlist = "SELECT 	z.ZahlDatum, "+"z.MatchCode, "+"z.Betrag "+"FROM Zahlungen z "+"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID "+"WHERE z.ZahlDatum LIKE '"+_sauswahl+"' "+"AND f.IsSelected = 1 "+"ORDER BY z.ZahlDatum";
 };
 //BA.debugLineNum = 821;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 822;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 823;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 827;BA.debugLine="Try";
try { //BA.debugLineNum = 828;BA.debugLine="sSumme = Main.SQL1.ExecQuerySingleResult(sSelect";
_ssumme = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselectsum);
 //BA.debugLineNum = 829;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelectList)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselectlist)));
 } 
       catch (Exception e18) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e18); //BA.debugLineNum = 832;BA.debugLine="Log(\"Fehler beim holen der Zahlungen \" & Last";
anywheresoftware.b4a.keywords.Common.LogImpl("016318509","Fehler beim holen der Zahlungen "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 833;BA.debugLine="sSumme = Null";
_ssumme = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null);
 };
 //BA.debugLineNum = 837;BA.debugLine="For i = 0 To cur.RowCount -1";
{
final int step21 = 1;
final int limit21 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit21 ;_i = _i + step21 ) {
 //BA.debugLineNum = 838;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 839;BA.debugLine="Dim sMaCode, sBetrag As String";
_smacode = "";
_sbetrag = "";
 //BA.debugLineNum = 840;BA.debugLine="sMaCode = cur.GetString(\"MatchCode\")";
_smacode = _cur.GetString("MatchCode");
 //BA.debugLineNum = 842;BA.debugLine="If Main.lv.Width > 320 And Main.lv.Height > 480";
if (mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Width>320 && mostCurrent._main._lv /*anywheresoftware.b4a.keywords.LayoutValues*/ .Height>480) { 
 //BA.debugLineNum = 843;BA.debugLine="If sMaCode.Length < 14 Then";
if (_smacode.length()<14) { 
 //BA.debugLineNum = 844;BA.debugLine="sMaCode = sMaCode & TAB & TAB & TAB";
_smacode = _smacode+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB;
 }else if(_smacode.length()==14) { 
 //BA.debugLineNum = 846;BA.debugLine="sMaCode = sMaCode & TAB & TAB";
_smacode = _smacode+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB;
 };
 }else {
 //BA.debugLineNum = 849;BA.debugLine="If sMaCode.Length < 14 Then";
if (_smacode.length()<14) { 
 //BA.debugLineNum = 850;BA.debugLine="sMaCode = sMaCode & TAB & TAB";
_smacode = _smacode+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB;
 }else if(_smacode.length()==14) { 
 //BA.debugLineNum = 852;BA.debugLine="sMaCode = sMaCode & TAB";
_smacode = _smacode+anywheresoftware.b4a.keywords.Common.TAB;
 };
 };
 //BA.debugLineNum = 856;BA.debugLine="sBetrag = cur.GetString(\"Betrag\")";
_sbetrag = _cur.GetString("Betrag");
 //BA.debugLineNum = 862;BA.debugLine="Dim iGesamt, iPos As Int";
_igesamt = 0;
_ipos = 0;
 //BA.debugLineNum = 863;BA.debugLine="iGesamt = sBetrag.Length";
_igesamt = _sbetrag.length();
 //BA.debugLineNum = 864;BA.debugLine="iPos = sBetrag.LastIndexOf(\".\")";
_ipos = _sbetrag.lastIndexOf(".");
 //BA.debugLineNum = 866;BA.debugLine="If iPos = -1 Then";
if (_ipos==-1) { 
 }else {
 //BA.debugLineNum = 869;BA.debugLine="If (iGesamt - (iPos + 1)) > 2 Then";
if ((_igesamt-(_ipos+1))>2) { 
 //BA.debugLineNum = 870;BA.debugLine="sBetrag = sBetrag.SubString2(0, iPos + 3)";
_sbetrag = _sbetrag.substring((int) (0),(int) (_ipos+3));
 }else if((_igesamt-(_ipos+1))==1) { 
 //BA.debugLineNum = 872;BA.debugLine="sBetrag = sBetrag & \"0\"";
_sbetrag = _sbetrag+"0";
 };
 };
 //BA.debugLineNum = 876;BA.debugLine="lstZahlungen.AddSingleLine(cur.GetString(\"ZahlDa";
_lstzahlungen.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("ZahlDatum")+":  "+_smacode+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+_sbetrag));
 }
};
 //BA.debugLineNum = 879;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 880;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 882;BA.debugLine="Return sSumme";
if (true) return _ssumme;
 //BA.debugLineNum = 883;BA.debugLine="End Sub";
return "";
}
public static boolean  _fahrdatenspeichernupdatemit(anywheresoftware.b4a.BA _ba,int _iterminid,String _spruefungsergebnis,boolean _bunterschrift) throws Exception{
boolean _breturn = false;
String _supdate = "";
int _iid = 0;
String _ssignature = "";
int _ianzahl = 0;
 //BA.debugLineNum = 2709;BA.debugLine="Sub FahrdatenSpeichernUpdateMit(iTerminID As Int,";
 //BA.debugLineNum = 2710;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 2711;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 2714;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 2715;BA.debugLine="Dim sSignature As String";
_ssignature = "";
 //BA.debugLineNum = 2717;BA.debugLine="If bUnterschrift Then";
if (_bunterschrift) { 
 //BA.debugLineNum = 2718;BA.debugLine="iID = GetSignatureID(iTerminID)";
_iid = _getsignatureid(_ba,_iterminid);
 //BA.debugLineNum = 2719;BA.debugLine="sSignature = GetSignatureString(iTerminID)";
_ssignature = _getsignaturestring(_ba,_iterminid);
 };
 //BA.debugLineNum = 2723;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2724;BA.debugLine="iAnzahl = 0";
_ianzahl = (int) (0);
 //BA.debugLineNum = 2726;BA.debugLine="If CheckTerminPruefung(iTerminID) Then";
if (_checkterminpruefung(_ba,_iterminid)) { 
 //BA.debugLineNum = 2728;BA.debugLine="iAnzahl = GetAnzahlPruefungen(iTerminID)";
_ianzahl = _getanzahlpruefungen(_ba,_iterminid);
 //BA.debugLineNum = 2729;BA.debugLine="iAnzahl = iAnzahl + 1";
_ianzahl = (int) (_ianzahl+1);
 };
 //BA.debugLineNum = 2732;BA.debugLine="If iID > 0 Then";
if (_iid>0) { 
 //BA.debugLineNum = 2733;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Hat_U";
_supdate = "UPDATE Termine "+"SET Hat_Unterschrift = 1, "+"UnterschriftJaNein = 'Ja', "+"Unterschrift = '"+_ssignature+"', "+"PruefungsErgebnis = '"+_spruefungsergebnis+"', "+"PruefungsTagNr = "+BA.NumberToString(_ianzahl)+", "+"TransKz = 'U' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 }else {
 //BA.debugLineNum = 2742;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Pruef";
_supdate = "UPDATE Termine "+"SET PruefungsErgebnis = '"+_spruefungsergebnis+"', "+"PruefungsTagNr = "+BA.NumberToString(_ianzahl)+", "+"UnterschriftJaNein = 'Nein', "+"TransKz = ' ' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 };
 //BA.debugLineNum = 2750;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2751;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2752;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2756;BA.debugLine="Try";
try { //BA.debugLineNum = 2757;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 2759;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e28) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e28); //BA.debugLineNum = 2761;BA.debugLine="Log(\"Fehler beim speichern der Termindaten mi";
anywheresoftware.b4a.keywords.Common.LogImpl("018874420","Fehler beim speichern der Termindaten mit Erfassung des Prüfungsergebnis "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2762;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2765;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2767;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 2768;BA.debugLine="End Sub";
return false;
}
public static boolean  _fahrdatenspeichernupdateohne(anywheresoftware.b4a.BA _ba,int _iterminid,boolean _bunterschrift) throws Exception{
boolean _breturn = false;
String _supdate = "";
int _iid = 0;
String _ssignaturesting = "";
 //BA.debugLineNum = 2771;BA.debugLine="Sub FahrdatenSpeichernUpdateOhne(iTerminID As Int,";
 //BA.debugLineNum = 2772;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 2773;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 2776;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 2777;BA.debugLine="Dim sSignatureSting As String";
_ssignaturesting = "";
 //BA.debugLineNum = 2779;BA.debugLine="If bUnterschrift Then";
if (_bunterschrift) { 
 //BA.debugLineNum = 2780;BA.debugLine="iID = GetSignatureID(iTerminID)";
_iid = _getsignatureid(_ba,_iterminid);
 //BA.debugLineNum = 2781;BA.debugLine="sSignatureSting = GetSignatureString(iTerminID)";
_ssignaturesting = _getsignaturestring(_ba,_iterminid);
 };
 //BA.debugLineNum = 2784;BA.debugLine="If sSignatureSting.Length > 0 Then";
if (_ssignaturesting.length()>0) { 
 //BA.debugLineNum = 2785;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Hat_U";
_supdate = "UPDATE Termine "+"SET Hat_Unterschrift = 1, "+"UnterschriftJaNein = 'Ja', "+"Unterschrift = '"+_ssignaturesting+"', "+"TransKz = 'U' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 }else {
 //BA.debugLineNum = 2792;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Unter";
_supdate = "UPDATE Termine "+"SET UnterschriftJaNein = 'Nein', "+"TransKz = 'T' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 };
 //BA.debugLineNum = 2799;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2800;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2801;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2805;BA.debugLine="Try";
try { //BA.debugLineNum = 2806;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 2808;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e22) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e22); //BA.debugLineNum = 2810;BA.debugLine="Log(\"Fehler speichern der Termindaten OHNE Er";
anywheresoftware.b4a.keywords.Common.LogImpl("018939943","Fehler speichern der Termindaten OHNE Erfassung des Prüfungsergebnis "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2811;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2814;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2816;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 2817;BA.debugLine="End Sub";
return false;
}
public static boolean  _fahrlehrerfixieren(anywheresoftware.b4a.BA _ba,String _sfahrlehrer) throws Exception{
boolean _breturnvalue = false;
String _sreset = "";
String _supdate = "";
 //BA.debugLineNum = 542;BA.debugLine="Sub FahrlehrerFixieren(sFahrlehrer As String) As B";
 //BA.debugLineNum = 543;BA.debugLine="Dim bReturnValue As Boolean";
_breturnvalue = false;
 //BA.debugLineNum = 544;BA.debugLine="Dim sReset, sUpdate As String";
_sreset = "";
_supdate = "";
 //BA.debugLineNum = 553;BA.debugLine="sReset = \"UPDATE Fahrlehrer \" & _ 				\"SET IsSele";
_sreset = "UPDATE Fahrlehrer "+"SET IsSelected = 0 ";
 //BA.debugLineNum = 556;BA.debugLine="sUpdate = \"UPDATE Fahrlehrer \" & _ 				\"SET IsSel";
_supdate = "UPDATE Fahrlehrer "+"SET IsSelected = 1 "+"WHERE Name LIKE '"+_sfahrlehrer+"' ";
 //BA.debugLineNum = 561;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 562;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 563;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 567;BA.debugLine="Try";
try { //BA.debugLineNum = 568;BA.debugLine="Main.SQL1.ExecNonQuery(sReset)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sreset);
 //BA.debugLineNum = 569;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 571;BA.debugLine="bReturnValue = True";
_breturnvalue = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 573;BA.debugLine="Log(\"Fehler beim setzen des fixen Fahrlehrer";
anywheresoftware.b4a.keywords.Common.LogImpl("015794207","Fehler beim setzen des fixen Fahrlehrer "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 574;BA.debugLine="bReturnValue = False";
_breturnvalue = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 577;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 579;BA.debugLine="Return bReturnValue";
if (true) return _breturnvalue;
 //BA.debugLineNum = 580;BA.debugLine="End Sub";
return false;
}
public static int[]  _fahrlehrergesamtstundenholen(anywheresoftware.b4a.BA _ba,String _sdate) throws Exception{
int[] _istunden = null;
String _sgesamt = "";
String _sfahrlehrer = "";
 //BA.debugLineNum = 1608;BA.debugLine="Sub FahrlehrerGesamtstundenHolen(sDate As String)";
 //BA.debugLineNum = 1609;BA.debugLine="Dim iStunden(2) As Int";
_istunden = new int[(int) (2)];
;
 //BA.debugLineNum = 1611;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1612;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1613;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1617;BA.debugLine="Dim sGesamt, sFahrlehrer As String";
_sgesamt = "";
_sfahrlehrer = "";
 //BA.debugLineNum = 1618;BA.debugLine="sGesamt = 	\"SELECT SUM(Dauer) \" & _ 					\"FROM Te";
_sgesamt = "SELECT SUM(Dauer) "+"FROM Termine t "+"	INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID "+"WHERE t.Termin LIKE '"+_sdate+"' "+"AND f.IsSelected = 1";
 //BA.debugLineNum = 1624;BA.debugLine="sFahrlehrer = 	\"SELECT SUM(Dauer) \" & _ 						\"FR";
_sfahrlehrer = "SELECT SUM(Dauer) "+"FROM Termine t "+"	INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID "+"WHERE t.Termin LIKE '"+_sdate+"' "+"AND t.Klassen_ID > 0 "+"AND f.IsSelected = 1";
 //BA.debugLineNum = 1632;BA.debugLine="Try";
try { //BA.debugLineNum = 1633;BA.debugLine="iStunden(0) = Main.SQL1.ExecQuerySingleResult(sG";
_istunden[(int) (0)] = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sgesamt)));
 //BA.debugLineNum = 1634;BA.debugLine="iStunden(1) = Main.SQL1.ExecQuerySingleResult(sF";
_istunden[(int) (1)] = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sfahrlehrer)));
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 1637;BA.debugLine="Log(\"Fehler beim holen der Tagessummen des Fa";
anywheresoftware.b4a.keywords.Common.LogImpl("017235997","Fehler beim holen der Tagessummen des Fahrlehrers "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1638;BA.debugLine="iStunden(0) = 0";
_istunden[(int) (0)] = (int) (0);
 //BA.debugLineNum = 1639;BA.debugLine="iStunden(1) = 0";
_istunden[(int) (1)] = (int) (0);
 };
 //BA.debugLineNum = 1642;BA.debugLine="Return iStunden";
if (true) return _istunden;
 //BA.debugLineNum = 1643;BA.debugLine="End Sub";
return null;
}
public static String  _fahrlehrertermineholen(anywheresoftware.b4a.BA _ba,String _sdate,anywheresoftware.b4a.objects.ListViewWrapper _lsttermine) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
String _sunterschrift = "";
String _streffpunkt = "";
String _supdate = "";
int _itreffpunktid = 0;
int _i = 0;
String _matchcode = "";
ADR.stringdemo.stringfunctions _sf = null;
fadata.mobil.main._listviewdata _lvd = null;
 //BA.debugLineNum = 1646;BA.debugLine="Sub FahrlehrerTermineHolen(sDate As String, lstTer";
 //BA.debugLineNum = 1647;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1648;BA.debugLine="Dim sUnterschrift, sTreffpunkt, sUpdate As String";
_sunterschrift = "";
_streffpunkt = "";
_supdate = "";
 //BA.debugLineNum = 1649;BA.debugLine="Dim iTreffpunktID As Int";
_itreffpunktid = 0;
 //BA.debugLineNum = 1651;BA.debugLine="lstTermine.Clear";
_lsttermine.Clear();
 //BA.debugLineNum = 1653;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1654;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1655;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1658;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	t.OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	t.OID, "+"t.Starttermin, "+"t.MatchCode, "+"t.Dauer, "+"t.Treffpunkt_ID, "+"CASE t.MeetingPoint WHEN t.MeetingPoint THEN t.MeetingPoint ELSE 0 END AS MeetingPoint, "+"t.Klasse, "+"t.FahrtBezeichnung_Abkuerzen, "+"t.Hat_Unterschrift "+"FROM Termine t "+"INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID "+"WHERE t.termin LIKE '"+_sdate+"' "+"AND f.IsSelected = 1 "+"ORDER BY t.Starttermin ")));
 //BA.debugLineNum = 1673;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1674;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1676;BA.debugLine="If cur.GetInt(\"Hat_Unterschrift\") > 0 Then";
if (_cur.GetInt("Hat_Unterschrift")>0) { 
 //BA.debugLineNum = 1677;BA.debugLine="sUnterschrift = \"Ja\"";
_sunterschrift = "Ja";
 }else {
 //BA.debugLineNum = 1679;BA.debugLine="sUnterschrift = \"Nein\"";
_sunterschrift = "Nein";
 };
 //BA.debugLineNum = 1683;BA.debugLine="iTreffpunktID = cur.GetInt(\"Treffpunkt_ID\")";
_itreffpunktid = _cur.GetInt("Treffpunkt_ID");
 //BA.debugLineNum = 1685;BA.debugLine="If iTreffpunktID = 0 Then";
if (_itreffpunktid==0) { 
 //BA.debugLineNum = 1686;BA.debugLine="If cur.GetString(\"MeetingPoint\") = 0 Then";
if ((_cur.GetString("MeetingPoint")).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1687;BA.debugLine="sTreffpunkt =  \"\"";
_streffpunkt = "";
 }else {
 //BA.debugLineNum = 1689;BA.debugLine="sTreffpunkt = cur.GetString(\"MeetingPoint\")";
_streffpunkt = _cur.GetString("MeetingPoint");
 //BA.debugLineNum = 1691;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 							\"SET Tr";
_supdate = "UPDATE Termine "+"SET Treffpunkt_ID = (SELECT OID FROM Treffpunkt WHERE Bezeichnung LIKE '"+_streffpunkt+"') "+"WHERE OID = "+BA.NumberToString(_cur.GetInt("OID"));
 //BA.debugLineNum = 1696;BA.debugLine="Try";
try { //BA.debugLineNum = 1697;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e27) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e27); //BA.debugLineNum = 1700;BA.debugLine="Log(\"Fehler beim speichern des Treffpunkt";
anywheresoftware.b4a.keywords.Common.LogImpl("017301558","Fehler beim speichern des Treffpunktes zum Termin (Update) "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 };
 }else {
 //BA.debugLineNum = 1705;BA.debugLine="sTreffpunkt = Main.SQL1.ExecQuerySingleResult(\"";
_streffpunkt = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Bezeichnung "+"FROM Treffpunkt "+"WHERE OID = "+BA.NumberToString(_itreffpunktid));
 };
 //BA.debugLineNum = 1711;BA.debugLine="lstTermine.ScrollingBackgroundColor = Colors.Tra";
_lsttermine.setScrollingBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1714;BA.debugLine="Dim matchcode As String";
_matchcode = "";
 //BA.debugLineNum = 1715;BA.debugLine="matchcode = cur.GetString(\"MatchCode\")";
_matchcode = _cur.GetString("MatchCode");
 //BA.debugLineNum = 1717;BA.debugLine="Dim sf As StringFunctions";
_sf = new ADR.stringdemo.stringfunctions();
 //BA.debugLineNum = 1720;BA.debugLine="If matchcode.Length > 30 Then";
if (_matchcode.length()>30) { 
 //BA.debugLineNum = 1722;BA.debugLine="If lstTermine.Width <= 510 And lstTermine.Width";
if (_lsttermine.getWidth()<=510 && _lsttermine.getWidth()>450) { 
 //BA.debugLineNum = 1723;BA.debugLine="matchcode = sf.Left(matchcode, 30)";
_matchcode = _sf._vv0(_matchcode,(long) (30));
 }else if(_lsttermine.getWidth()<=450) { 
 //BA.debugLineNum = 1725;BA.debugLine="matchcode = sf.Left(matchcode, 25)";
_matchcode = _sf._vv0(_matchcode,(long) (25));
 };
 };
 //BA.debugLineNum = 1730;BA.debugLine="Dim lvd As ListViewData";
_lvd = new fadata.mobil.main._listviewdata();
 //BA.debugLineNum = 1732;BA.debugLine="lvd.FirstRow = cur.GetString(\"Starttermin\") & \"";
_lvd.FirstRow /*String*/  = _cur.GetString("Starttermin")+" "+_matchcode;
 //BA.debugLineNum = 1733;BA.debugLine="lvd.SecondRow=  cur.GetString(\"Dauer\") & TAB & T";
_lvd.SecondRow /*String*/  = _cur.GetString("Dauer")+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+_cur.GetString("Klasse")+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+_cur.GetString("FahrtBezeichnung_Abkuerzen")+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+_sunterschrift+anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB+_streffpunkt;
 //BA.debugLineNum = 1734;BA.debugLine="lstTermine.AddTwoLines2(lvd.FirstRow, lvd.Second";
_lsttermine.AddTwoLines2(BA.ObjectToCharSequence(_lvd.FirstRow /*String*/ ),BA.ObjectToCharSequence(_lvd.SecondRow /*String*/ ),(Object)(_lvd));
 }
};
 //BA.debugLineNum = 1737;BA.debugLine="End Sub";
return "";
}
public static int  _fahrtbezeichnungsid(anywheresoftware.b4a.BA _ba,String _sfahrtbez) throws Exception{
String _sselect = "";
int _iresult = 0;
 //BA.debugLineNum = 4154;BA.debugLine="Sub FahrtbezeichnungsID(sFahrtBez As String) As In";
 //BA.debugLineNum = 4155;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 4156;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 4158;BA.debugLine="sSelect = \"SELECT OID \" & _ 					\"FROM Fahrtbezei";
_sselect = "SELECT OID "+"FROM Fahrtbezeichnung "+"WHERE Kuerzel LIKE '"+_sfahrtbez+"'";
 //BA.debugLineNum = 4162;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4163;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 4164;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 4168;BA.debugLine="Try";
try { //BA.debugLineNum = 4169;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 4173;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 4176;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4178;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 4179;BA.debugLine="End Sub";
return 0;
}
public static String  _fillausbildunglernpunktelistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernpunkte,String _sschueler,int _ikategorie) throws Exception{
String _seintraege = "";
int _i = 0;
int _ianfang = 0;
int _iende = 0;
 //BA.debugLineNum = 1082;BA.debugLine="Sub FillAusbildungLernPunkteListView(lstAusbildung";
 //BA.debugLineNum = 1083;BA.debugLine="Dim sEintraege As String";
_seintraege = "";
 //BA.debugLineNum = 1085;BA.debugLine="lstAusbildungLernPunkte.Clear";
_lstausbildunglernpunkte.Clear();
 //BA.debugLineNum = 1087;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1088;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1089;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1093;BA.debugLine="sEintraege = Main.SQL1.ExecQuerySingleResult(\"SEL";
_seintraege = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT LernString "+"FROM LernKontrolle "+"WHERE SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ ));
 //BA.debugLineNum = 1098;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1100;BA.debugLine="If sEintraege = Null Or sEintraege.Length = 0 The";
if (_seintraege== null || _seintraege.length()==0) { 
 //BA.debugLineNum = 1101;BA.debugLine="For i = 0 To 10";
{
final int step10 = 1;
final int limit10 = (int) (10);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1102;BA.debugLine="lstAusbildungLernPunkte.AddSingleLine(\" \")";
_lstausbildunglernpunkte.AddSingleLine(BA.ObjectToCharSequence(" "));
 }
};
 }else {
 //BA.debugLineNum = 1105;BA.debugLine="Dim iAnfang, iEnde As Int";
_ianfang = 0;
_iende = 0;
 //BA.debugLineNum = 1107;BA.debugLine="If iKategorie = 1 Then";
if (_ikategorie==1) { 
 //BA.debugLineNum = 1108;BA.debugLine="iAnfang = 0";
_ianfang = (int) (0);
 //BA.debugLineNum = 1109;BA.debugLine="iEnde = 10";
_iende = (int) (10);
 }else if(_ikategorie==2) { 
 //BA.debugLineNum = 1111;BA.debugLine="iAnfang = 10";
_ianfang = (int) (10);
 //BA.debugLineNum = 1112;BA.debugLine="iEnde = 20";
_iende = (int) (20);
 }else if(_ikategorie==3) { 
 //BA.debugLineNum = 1114;BA.debugLine="iAnfang = 20";
_ianfang = (int) (20);
 //BA.debugLineNum = 1115;BA.debugLine="iEnde = 30";
_iende = (int) (30);
 }else if(_ikategorie==4) { 
 //BA.debugLineNum = 1117;BA.debugLine="iAnfang = 30";
_ianfang = (int) (30);
 //BA.debugLineNum = 1118;BA.debugLine="iEnde = 40";
_iende = (int) (40);
 }else if(_ikategorie==5) { 
 //BA.debugLineNum = 1120;BA.debugLine="iAnfang = 40";
_ianfang = (int) (40);
 //BA.debugLineNum = 1121;BA.debugLine="iEnde = 50";
_iende = (int) (50);
 }else if(_ikategorie==6) { 
 //BA.debugLineNum = 1123;BA.debugLine="iAnfang = 50";
_ianfang = (int) (50);
 //BA.debugLineNum = 1124;BA.debugLine="iEnde = 60";
_iende = (int) (60);
 }else if(_ikategorie==7) { 
 //BA.debugLineNum = 1126;BA.debugLine="iAnfang = 60";
_ianfang = (int) (60);
 //BA.debugLineNum = 1127;BA.debugLine="iEnde = 70";
_iende = (int) (70);
 };
 //BA.debugLineNum = 1130;BA.debugLine="sEintraege = sEintraege.SubString2(iAnfang, iEnd";
_seintraege = _seintraege.substring(_ianfang,_iende);
 //BA.debugLineNum = 1131;BA.debugLine="For i = 0 To sEintraege.Length - 1";
{
final int step38 = 1;
final int limit38 = (int) (_seintraege.length()-1);
_i = (int) (0) ;
for (;_i <= limit38 ;_i = _i + step38 ) {
 //BA.debugLineNum = 1132;BA.debugLine="lstAusbildungLernPunkte.AddSingleLine(sEintraeg";
_lstausbildunglernpunkte.AddSingleLine(BA.ObjectToCharSequence(_seintraege.substring(_i,(int) (_i+1))));
 }
};
 };
 //BA.debugLineNum = 1135;BA.debugLine="End Sub";
return "";
}
public static String  _fillausbildunglernthemenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernthemen,int _ikategorie) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 1057;BA.debugLine="Sub FillAusbildungLernThemenListView(lstAusbildung";
 //BA.debugLineNum = 1058;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1060;BA.debugLine="lstAusbildungLernThemen.Clear";
_lstausbildunglernthemen.Clear();
 //BA.debugLineNum = 1062;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1063;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1064;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1067;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	l.LfdNr, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	l.LfdNr, "+"l.Thema "+"FROM LernThemen l "+"WHERE l.Kat_LernThemen_PK = "+BA.NumberToString(_ikategorie))));
 //BA.debugLineNum = 1072;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step8 = 1;
final int limit8 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 1073;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1074;BA.debugLine="lstAusbildungLernThemen.AddSingleLine(cur.GetInt";
_lstausbildunglernthemen.AddSingleLine(BA.ObjectToCharSequence(BA.NumberToString(_cur.GetInt("LfdNr"))+anywheresoftware.b4a.keywords.Common.TAB+_cur.GetString("Thema")));
 }
};
 //BA.debugLineNum = 1077;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1078;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1079;BA.debugLine="End Sub";
return "";
}
public static String  _fillbegleitfahrzeuglistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstbegleitfahrzeug) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 1242;BA.debugLine="Sub FillBegleitfahrzeugListView(lstBegleitfahrzeug";
 //BA.debugLineNum = 1243;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1245;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1246;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1247;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1250;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT Bezeichnung \" &";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT Bezeichnung "+"FROM Fahrzeuge "+"WHERE Kat_Fahrzeug_PK = 0 "+"ORDER BY IsSelected DESC, Bezeichnung")));
 //BA.debugLineNum = 1255;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 1256;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1257;BA.debugLine="lstBegleitfahrzeug.AddSingleLine(cur.GetString(\"";
_lstbegleitfahrzeug.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Bezeichnung")));
 }
};
 //BA.debugLineNum = 1260;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1261;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1262;BA.debugLine="End Sub";
return "";
}
public static String  _fillfahrlehrerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstfahrlehrer) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 513;BA.debugLine="Sub FillFahrlehrerListView(lstFahrlehrer As ListVi";
 //BA.debugLineNum = 514;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 516;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 517;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 518;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 521;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	Name, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	Name, "+"Vorname, "+"FahrlehrerNr, "+"IsSelected "+"FROM Fahrlehrer "+"ORDER BY IsSelected desc")));
 //BA.debugLineNum = 528;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 529;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 534;BA.debugLine="lstFahrlehrer.AddSingleLine(cur.GetString(\"Name\"";
_lstfahrlehrer.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Name")));
 }
};
 //BA.debugLineNum = 537;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 538;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 539;BA.debugLine="End Sub";
return "";
}
public static String  _fillfahrtenbezeichnunglistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstfahrtbezeichnung) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _fahrtbezeichnungid = null;
int _i = 0;
 //BA.debugLineNum = 1538;BA.debugLine="Sub FillFahrtenbezeichnungListView(lstFahrtBezeich";
 //BA.debugLineNum = 1539;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1543;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 1545;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1546;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1547;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1550;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrtbezeichnung WHERE SonstTaetigkeiten <> 1")));
 //BA.debugLineNum = 1552;BA.debugLine="Dim FahrtbezeichnungID(iAnzahl, 2) As String";
_fahrtbezeichnungid = new String[_ianzahl][];
{
int d0 = _fahrtbezeichnungid.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_fahrtbezeichnungid[i0] = new String[d1];
java.util.Arrays.fill(_fahrtbezeichnungid[i0],"");
}
}
;
 //BA.debugLineNum = 1554;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	OID, "+"Kuerzel, "+"Beschreibung "+"FROM Fahrtbezeichnung "+"WHERE SonstTaetigkeiten <> 1 "+"ORDER BY FahrtbezeichnungsNr")));
 //BA.debugLineNum = 1561;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1562;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1563;BA.debugLine="lstFahrtBezeichnung.AddSingleLine(cur.GetString(";
_lstfahrtbezeichnung.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Kuerzel")+" - "+_cur.GetString("Beschreibung")));
 //BA.debugLineNum = 1564;BA.debugLine="FahrtbezeichnungID(i, 0) = cur.GetString(\"Kuerze";
_fahrtbezeichnungid[_i][(int) (0)] = _cur.GetString("Kuerzel");
 //BA.debugLineNum = 1565;BA.debugLine="FahrtbezeichnungID(i, 1) = cur.GetInt(\"OID\")";
_fahrtbezeichnungid[_i][(int) (1)] = BA.NumberToString(_cur.GetInt("OID"));
 }
};
 //BA.debugLineNum = 1567;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1568;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1570;BA.debugLine="Main.aFahrtbezeichnung = FahrtbezeichnungID";
mostCurrent._main._afahrtbezeichnung /*String[][]*/  = _fahrtbezeichnungid;
 //BA.debugLineNum = 1571;BA.debugLine="End Sub";
return "";
}
public static String  _fillkfzlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstkfz) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _kfzid = null;
int _i = 0;
 //BA.debugLineNum = 583;BA.debugLine="Sub FillKfzListView(lstKfz As ListView)";
 //BA.debugLineNum = 584;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 588;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 590;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 591;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 592;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 595;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrzeuge")));
 //BA.debugLineNum = 597;BA.debugLine="Dim KFZID(iAnzahl, 2) As String";
_kfzid = new String[_ianzahl][];
{
int d0 = _kfzid.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_kfzid[i0] = new String[d1];
java.util.Arrays.fill(_kfzid[i0],"");
}
}
;
 //BA.debugLineNum = 599;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	FzgNr, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	FzgNr, "+"Bezeichnung, "+"IsSelected "+"FROM Fahrzeuge "+"ORDER BY IsSelected desc")));
 //BA.debugLineNum = 605;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 606;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 610;BA.debugLine="lstKfz.AddSingleLine(cur.GetString(\"Bezeichnung\"";
_lstkfz.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Bezeichnung")));
 //BA.debugLineNum = 611;BA.debugLine="KFZID(i, 0) = cur.GetString(\"Bezeichnung\")";
_kfzid[_i][(int) (0)] = _cur.GetString("Bezeichnung");
 //BA.debugLineNum = 612;BA.debugLine="KFZID(i, 1) = cur.GetInt(\"FzgNr\")";
_kfzid[_i][(int) (1)] = BA.NumberToString(_cur.GetInt("FzgNr"));
 }
};
 //BA.debugLineNum = 615;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 616;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 617;BA.debugLine="Main.aKFZ = KFZID";
mostCurrent._main._akfz /*String[][]*/  = _kfzid;
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return "";
}
public static String  _fillklassenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstklassen) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 669;BA.debugLine="Sub FillKlassenListView(lstKlassen As ListView)";
 //BA.debugLineNum = 670;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 672;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 673;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 674;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 677;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT Bezeichnung \" &";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT Bezeichnung "+"FROM Klassen "+"WHERE Land LIKE 'Deutschland' "+"AND enable = 1 "+"ORDER BY Sortierung")));
 //BA.debugLineNum = 683;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 684;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 685;BA.debugLine="lstKlassen.AddSingleLine(cur.GetString(\"Bezeichn";
_lstklassen.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Bezeichnung")));
 }
};
 //BA.debugLineNum = 688;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 689;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 690;BA.debugLine="End Sub";
return "";
}
public static String[][]  _fillschuelerarray(anywheresoftware.b4a.BA _ba,String _sabc) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String _sselect = "";
String[][] _schuelerid = null;
int _i = 0;
 //BA.debugLineNum = 421;BA.debugLine="Sub FillSchuelerArray(sABC As String) As String(,)";
 //BA.debugLineNum = 422;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 423;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 424;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 426;BA.debugLine="iAnzahl = FillSchuelerListViewItems(sABC)";
_ianzahl = _fillschuelerlistviewitems(_ba,_sabc);
 //BA.debugLineNum = 428;BA.debugLine="Dim SchuelerID(iAnzahl, 3) As String";
_schuelerid = new String[_ianzahl][];
{
int d0 = _schuelerid.length;
int d1 = (int) (3);
for (int i0 = 0;i0 < d0;i0++) {
_schuelerid[i0] = new String[d1];
java.util.Arrays.fill(_schuelerid[i0],"");
}
}
;
 //BA.debugLineNum = 430;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 431;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 432;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 435;BA.debugLine="sSelect = \"SELECT 	SchuelerID, \" & _ 										\"M";
_sselect = "SELECT 	SchuelerID, "+"MatchCode, "+"Strasse, "+"Hausnummer, "+"PLZ, "+"Ort "+"FROM Schueler "+"WHERE MatchCode LIKE '"+_sabc+"%' ";
 //BA.debugLineNum = 444;BA.debugLine="If (sABC = \"A\") Then";
if (((_sabc).equals("A"))) { 
 //BA.debugLineNum = 445;BA.debugLine="sSelect = sSelect & 	\"OR Matchcode LIKE 'Ä%' \"";
_sselect = _sselect+"OR Matchcode LIKE 'Ä%' ";
 }else if(((_sabc).equals("U"))) { 
 //BA.debugLineNum = 447;BA.debugLine="sSelect = sSelect & 	\"OR Matchcode LIKE 'Ü%' \"";
_sselect = _sselect+"OR Matchcode LIKE 'Ü%' ";
 }else if(((_sabc).equals("O"))) { 
 //BA.debugLineNum = 449;BA.debugLine="sSelect = sSelect & 	\"OR Matchcode LIKE 'Ö%' \"";
_sselect = _sselect+"OR Matchcode LIKE 'Ö%' ";
 };
 //BA.debugLineNum = 452;BA.debugLine="sSelect = sSelect & 		\"ORDER BY MatchCode\"";
_sselect = _sselect+"ORDER BY MatchCode";
 //BA.debugLineNum = 463;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 465;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step20 = 1;
final int limit20 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit20 ;_i = _i + step20 ) {
 //BA.debugLineNum = 466;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 468;BA.debugLine="SchuelerID(i, 0) = cur.GetString(\"MatchCode\")";
_schuelerid[_i][(int) (0)] = _cur.GetString("MatchCode");
 //BA.debugLineNum = 469;BA.debugLine="SchuelerID(i, 1) = cur.GetString(\"SchuelerID\")";
_schuelerid[_i][(int) (1)] = _cur.GetString("SchuelerID");
 //BA.debugLineNum = 470;BA.debugLine="SchuelerID(i, 2) = \"0\"";
_schuelerid[_i][(int) (2)] = "0";
 }
};
 //BA.debugLineNum = 475;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 476;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 478;BA.debugLine="Return SchuelerID";
if (true) return _schuelerid;
 //BA.debugLineNum = 479;BA.debugLine="End Sub";
return null;
}
public static String  _fillschuelerklasselistview(anywheresoftware.b4a.BA _ba,String _smatchcode,int _ischuelerid) throws Exception{
String _sklassen = "";
 //BA.debugLineNum = 650;BA.debugLine="Sub FillSchuelerKlasseListView(sMatchCode As Strin";
 //BA.debugLineNum = 651;BA.debugLine="Dim sKlassen As String";
_sklassen = "";
 //BA.debugLineNum = 653;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 654;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 655;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 658;BA.debugLine="sKlassen = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_sklassen = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT 	LTRIM(BeantragteKlasse) "+"FROM Schueler "+"WHERE MatchCode LIKE '"+_smatchcode+"' "+"AND SchuelerID = "+BA.NumberToString(_ischuelerid)+" ");
 //BA.debugLineNum = 663;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 665;BA.debugLine="Return sKlassen";
if (true) return _sklassen;
 //BA.debugLineNum = 666;BA.debugLine="End Sub";
return "";
}
public static String  _fillschuelerlist(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lschueler) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 395;BA.debugLine="Sub FillSchuelerList(lSchueler As ListView)";
 //BA.debugLineNum = 396;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 398;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 399;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 400;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 403;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	SchuelerID, \"";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	SchuelerID, "+"MatchCode, "+"Strasse, "+"Hausnummer, "+"PLZ, "+"Ort "+"FROM Schueler "+"ORDER BY MatchCode")));
 //BA.debugLineNum = 412;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 413;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 414;BA.debugLine="lSchueler.AddSingleLine(cur.GetString(\"MatchCode";
_lschueler.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("MatchCode")));
 }
};
 //BA.debugLineNum = 417;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 418;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 419;BA.debugLine="End Sub";
return "";
}
public static String  _fillschuelerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstschueler) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _schuelerid = null;
int _i = 0;
 //BA.debugLineNum = 347;BA.debugLine="Sub FillSchuelerListView(lstSchueler As ListView)";
 //BA.debugLineNum = 348;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 352;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 354;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 355;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 356;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 359;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler")));
 //BA.debugLineNum = 361;BA.debugLine="Dim SchuelerID(iAnzahl, 3) As String";
_schuelerid = new String[_ianzahl][];
{
int d0 = _schuelerid.length;
int d1 = (int) (3);
for (int i0 = 0;i0 < d0;i0++) {
_schuelerid[i0] = new String[d1];
java.util.Arrays.fill(_schuelerid[i0],"");
}
}
;
 //BA.debugLineNum = 363;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	SchuelerID, \"";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	SchuelerID, "+"MatchCode, "+"Strasse, "+"Hausnummer, "+"PLZ, "+"Ort "+"FROM Schueler "+"ORDER BY MatchCode")));
 //BA.debugLineNum = 372;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 373;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 378;BA.debugLine="lstSchueler.AddSingleLine(cur.GetString(\"MatchCo";
_lstschueler.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("MatchCode")));
 //BA.debugLineNum = 380;BA.debugLine="SchuelerID(i, 0) = cur.GetString(\"MatchCode\")";
_schuelerid[_i][(int) (0)] = _cur.GetString("MatchCode");
 //BA.debugLineNum = 381;BA.debugLine="SchuelerID(i, 1) = cur.GetString(\"SchuelerID\")";
_schuelerid[_i][(int) (1)] = _cur.GetString("SchuelerID");
 //BA.debugLineNum = 382;BA.debugLine="SchuelerID(i, 2) = \"0\"";
_schuelerid[_i][(int) (2)] = "0";
 }
};
 //BA.debugLineNum = 387;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 388;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 391;BA.debugLine="SchuelerID(0, 2) = 1";
_schuelerid[(int) (0)][(int) (2)] = BA.NumberToString(1);
 //BA.debugLineNum = 392;BA.debugLine="Main.aSchueler = SchuelerID";
mostCurrent._main._aschueler /*String[][]*/  = _schuelerid;
 //BA.debugLineNum = 393;BA.debugLine="End Sub";
return "";
}
public static int  _fillschuelerlistviewitems(anywheresoftware.b4a.BA _ba,String _sabc) throws Exception{
int _ianzahl = 0;
 //BA.debugLineNum = 481;BA.debugLine="Sub FillSchuelerListViewItems(sABC As String) As I";
 //BA.debugLineNum = 483;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 485;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 486;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 487;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 490;BA.debugLine="If (sABC = \"A\") Then";
if (((_sabc).equals("A"))) { 
 //BA.debugLineNum = 491;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler "+"WHERE MatchCode LIKE '"+_sabc+"%' "+"OR MatchCode LIKE 'Ä%' ")));
 }else if(((_sabc).equals("U"))) { 
 //BA.debugLineNum = 495;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler "+"WHERE MatchCode LIKE '"+_sabc+"%' "+"OR MatchCode LIKE 'Ü%' ")));
 }else if(((_sabc).equals("O"))) { 
 //BA.debugLineNum = 499;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler "+"WHERE MatchCode LIKE '"+_sabc+"%' "+"OR MatchCode LIKE 'Ö%' ")));
 }else {
 //BA.debugLineNum = 503;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELEC";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler "+"WHERE MatchCode LIKE '"+_sabc+"%' ")));
 };
 //BA.debugLineNum = 507;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 509;BA.debugLine="Return iAnzahl";
if (true) return _ianzahl;
 //BA.debugLineNum = 510;BA.debugLine="End Sub";
return 0;
}
public static String  _fillsonstigetaetigkeitenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstsonstigetaetigkeiten) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _ssonstigetaetigkeitenid = null;
int _i = 0;
 //BA.debugLineNum = 1574;BA.debugLine="Sub FillSonstigeTaetigkeitenListView(lstSonstigeTa";
 //BA.debugLineNum = 1575;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1579;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 1581;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1582;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1583;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1586;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrtbezeichnung WHERE SonstTaetigkeiten = 1")));
 //BA.debugLineNum = 1588;BA.debugLine="Dim sSonstigeTaetigkeitenID(iAnzahl, 2) As String";
_ssonstigetaetigkeitenid = new String[_ianzahl][];
{
int d0 = _ssonstigetaetigkeitenid.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_ssonstigetaetigkeitenid[i0] = new String[d1];
java.util.Arrays.fill(_ssonstigetaetigkeitenid[i0],"");
}
}
;
 //BA.debugLineNum = 1590;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	OID, "+"Kuerzel, "+"Beschreibung "+"FROM Fahrtbezeichnung "+"WHERE SonstTaetigkeiten = 1 ")));
 //BA.debugLineNum = 1596;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1597;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1598;BA.debugLine="lstSonstigeTaetigkeiten.AddSingleLine(cur.GetStr";
_lstsonstigetaetigkeiten.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Kuerzel")+" - "+_cur.GetString("Beschreibung")));
 //BA.debugLineNum = 1599;BA.debugLine="sSonstigeTaetigkeitenID(i, 0) = cur.GetString(\"K";
_ssonstigetaetigkeitenid[_i][(int) (0)] = _cur.GetString("Kuerzel");
 //BA.debugLineNum = 1600;BA.debugLine="sSonstigeTaetigkeitenID(i, 1) = cur.GetInt(\"OID\"";
_ssonstigetaetigkeitenid[_i][(int) (1)] = BA.NumberToString(_cur.GetInt("OID"));
 }
};
 //BA.debugLineNum = 1602;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1603;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1605;BA.debugLine="Main.aSonstigeTaetigkeiten = sSonstigeTaetigkeite";
mostCurrent._main._asonstigetaetigkeiten /*String[][]*/  = _ssonstigetaetigkeitenid;
 //BA.debugLineNum = 1606;BA.debugLine="End Sub";
return "";
}
public static String  _filltreffpunktlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lsttreffpunkt) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _treffpunktid = null;
int _i = 0;
 //BA.debugLineNum = 693;BA.debugLine="Sub FillTreffpunktListView(lstTreffpunkt As ListVi";
 //BA.debugLineNum = 694;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 698;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 700;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 701;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 702;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 705;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Treffpunkt WHERE fixer_Treffpunkt = 1")));
 //BA.debugLineNum = 707;BA.debugLine="Dim TreffpunktID(iAnzahl, 2) As String";
_treffpunktid = new String[_ianzahl][];
{
int d0 = _treffpunktid.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_treffpunktid[i0] = new String[d1];
java.util.Arrays.fill(_treffpunktid[i0],"");
}
}
;
 //BA.debugLineNum = 709;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	OID, "+"Bezeichnung "+"FROM Treffpunkt "+"WHERE fixer_Treffpunkt = 1 "+"ORDER BY OID")));
 //BA.debugLineNum = 715;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 716;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 717;BA.debugLine="lstTreffpunkt.AddSingleLine(cur.GetString(\"Bezei";
_lsttreffpunkt.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Bezeichnung")));
 //BA.debugLineNum = 718;BA.debugLine="TreffpunktID(i, 0) = cur.GetString(\"Bezeichnung\"";
_treffpunktid[_i][(int) (0)] = _cur.GetString("Bezeichnung");
 //BA.debugLineNum = 719;BA.debugLine="TreffpunktID(i, 1) = cur.GetInt(\"OID\")";
_treffpunktid[_i][(int) (1)] = BA.NumberToString(_cur.GetInt("OID"));
 }
};
 //BA.debugLineNum = 722;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 723;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 725;BA.debugLine="Main.aTreffpunkt = TreffpunktID";
mostCurrent._main._atreffpunkt /*String[][]*/  = _treffpunktid;
 //BA.debugLineNum = 726;BA.debugLine="End Sub";
return "";
}
public static String  _fillzahlungenfuerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungfuer) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 766;BA.debugLine="Sub FillZahlungenFuerListView(lstZahlungFuer As Li";
 //BA.debugLineNum = 767;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 769;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 770;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 771;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 774;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	Bezeichnung \"";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	Bezeichnung "+" FROM ZahlungenFuer "+"ORDER BY OID")));
 //BA.debugLineNum = 777;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 778;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 779;BA.debugLine="lstZahlungFuer.AddSingleLine(cur.GetString(\"Beze";
_lstzahlungfuer.AddSingleLine(BA.ObjectToCharSequence(_cur.GetString("Bezeichnung")));
 }
};
 //BA.debugLineNum = 782;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 783;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 784;BA.debugLine="End Sub";
return "";
}
public static String[][]  _getallmeetingpoint(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ianzahl = 0;
String[][] _treffpunktdefaultid = null;
int _i = 0;
 //BA.debugLineNum = 729;BA.debugLine="Sub GetAllMeetingPoint As String(,)";
 //BA.debugLineNum = 730;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 734;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 736;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 737;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 738;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 741;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(\"SELECT";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM Treffpunkt")));
 //BA.debugLineNum = 743;BA.debugLine="Dim TreffpunktDefaultID(iAnzahl, 3) As String";
_treffpunktdefaultid = new String[_ianzahl][];
{
int d0 = _treffpunktdefaultid.length;
int d1 = (int) (3);
for (int i0 = 0;i0 < d0;i0++) {
_treffpunktdefaultid[i0] = new String[d1];
java.util.Arrays.fill(_treffpunktdefaultid[i0],"");
}
}
;
 //BA.debugLineNum = 746;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	OID, "+"Bezeichnung, "+"fixer_Treffpunkt "+"FROM Treffpunkt "+"ORDER BY OID")));
 //BA.debugLineNum = 752;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 753;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 754;BA.debugLine="TreffpunktDefaultID(i, 0) = cur.GetString(\"Bezei";
_treffpunktdefaultid[_i][(int) (0)] = _cur.GetString("Bezeichnung");
 //BA.debugLineNum = 755;BA.debugLine="TreffpunktDefaultID(i, 1) = cur.GetInt(\"fixer_Tr";
_treffpunktdefaultid[_i][(int) (1)] = BA.NumberToString(_cur.GetInt("fixer_Treffpunkt"));
 //BA.debugLineNum = 756;BA.debugLine="TreffpunktDefaultID(i, 2) = cur.GetInt(\"OID\")";
_treffpunktdefaultid[_i][(int) (2)] = BA.NumberToString(_cur.GetInt("OID"));
 }
};
 //BA.debugLineNum = 759;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 760;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 762;BA.debugLine="Return TreffpunktDefaultID";
if (true) return _treffpunktdefaultid;
 //BA.debugLineNum = 763;BA.debugLine="End Sub";
return null;
}
public static int  _getanzahlpruefungen(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
int _ianzahl = 0;
String _sselect = "";
 //BA.debugLineNum = 2906;BA.debugLine="Sub GetAnzahlPruefungen(iTerminID As Int) As Int";
 //BA.debugLineNum = 2907;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2908;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2910;BA.debugLine="sSelect = \"SELECT PruefungsTagNr \" & _ 					\"FROM";
_sselect = "SELECT PruefungsTagNr "+"FROM Termine "+"WHERE OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2914;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2915;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2916;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2919;BA.debugLine="Try";
try { //BA.debugLineNum = 2920;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 2922;BA.debugLine="Log(\"bisher keine Prüfungsversuch unternommen\")";
anywheresoftware.b4a.keywords.Common.LogImpl("019202064","bisher keine Prüfungsversuch unternommen",0);
 //BA.debugLineNum = 2923;BA.debugLine="iAnzahl = 0";
_ianzahl = (int) (0);
 };
 //BA.debugLineNum = 2926;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2928;BA.debugLine="Return iAnzahl";
if (true) return _ianzahl;
 //BA.debugLineNum = 2929;BA.debugLine="End Sub";
return 0;
}
public static int  _getfahrlehrerid(anywheresoftware.b4a.BA _ba,String _sfahrlehrer) throws Exception{
int _iresult = 0;
String _sselect = "";
 //BA.debugLineNum = 4264;BA.debugLine="Sub GetFahrlehrerID(sFahrlehrer As String) As Int";
 //BA.debugLineNum = 4265;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 4266;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 4268;BA.debugLine="sSelect = \"SELECT FahrlehrerNr \" & _ 					\"FROM F";
_sselect = "SELECT FahrlehrerNr "+"FROM Fahrlehrer "+"WHERE Name LIKE '"+_sfahrlehrer+"' ";
 //BA.debugLineNum = 4272;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4273;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 4274;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 4278;BA.debugLine="Try";
try { //BA.debugLineNum = 4279;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 4282;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 4285;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4287;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 4288;BA.debugLine="End Sub";
return 0;
}
public static String  _getkfz(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
String _skfz = "";
String _sselect = "";
 //BA.debugLineNum = 2023;BA.debugLine="Sub GetKFZ(iTerminID As Int) As String";
 //BA.debugLineNum = 2024;BA.debugLine="Dim sKFZ, sSelect As String";
_skfz = "";
_sselect = "";
 //BA.debugLineNum = 2026;BA.debugLine="sSelect = \"SELECT 	f.Bezeichnung \" & _ 					\" FRO";
_sselect = "SELECT 	f.Bezeichnung "+" FROM Fahrzeuge f "+"JOIN Termine t ON t.Fahrzeug_ID = f.FzgNr "+"WHERE t.OID = "+BA.NumberToString(_iterminid)+" AND t.Fahrzeug_ID > 0";
 //BA.debugLineNum = 2032;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2033;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2034;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2038;BA.debugLine="Try";
try { //BA.debugLineNum = 2039;BA.debugLine="sKFZ = Main.SQL1.ExecQuerySingleResult(sSelec";
_skfz = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 2042;BA.debugLine="Log(\"Fehler beim holen des Begleitfahrzeuges";
anywheresoftware.b4a.keywords.Common.LogImpl("017760275","Fehler beim holen des Begleitfahrzeuges "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2043;BA.debugLine="sKFZ = \"keine Auswahl\"";
_skfz = "keine Auswahl";
 };
 //BA.debugLineNum = 2046;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2048;BA.debugLine="Return sKFZ";
if (true) return _skfz;
 //BA.debugLineNum = 2049;BA.debugLine="End Sub";
return "";
}
public static int  _getkfzid(anywheresoftware.b4a.BA _ba,String _checkedkfz) throws Exception{
int _iresult = 0;
String _sselect = "";
 //BA.debugLineNum = 2351;BA.debugLine="Sub GetKFZID(CheckedKFZ As String) As Int";
 //BA.debugLineNum = 2352;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 2353;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2355;BA.debugLine="sSelect = \"SELECT FzgNr \" & _ 					\"FROM Fahrzeug";
_sselect = "SELECT FzgNr "+"FROM Fahrzeuge "+"WHERE Bezeichnung LIKE '"+_checkedkfz+"' ";
 //BA.debugLineNum = 2359;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2360;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2361;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2365;BA.debugLine="Try";
try { //BA.debugLineNum = 2366;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 2369;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 2372;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2374;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 2375;BA.debugLine="End Sub";
return 0;
}
public static int  _getschuelerid(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
int _iresult = 0;
String _sselect = "";
 //BA.debugLineNum = 4211;BA.debugLine="Sub GetSchuelerID(iTerminID As Int) As Int";
 //BA.debugLineNum = 4212;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 4213;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 4215;BA.debugLine="sSelect = \"SELECT Schueler_ID \" & _ 					\"FROM Te";
_sselect = "SELECT Schueler_ID "+"FROM Termine "+"WHERE OID LIKE '"+BA.NumberToString(_iterminid)+"' ";
 //BA.debugLineNum = 4219;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4220;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 4221;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 4225;BA.debugLine="Try";
try { //BA.debugLineNum = 4226;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 4229;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 4232;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4234;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 4235;BA.debugLine="End Sub";
return 0;
}
public static int  _getschueleridmatchcode(anywheresoftware.b4a.BA _ba,String _sausgewaehlterschueler) throws Exception{
int _iresult = 0;
String _sselect = "";
 //BA.debugLineNum = 4237;BA.debugLine="Sub GetSchuelerIDMatchcode(sAusgewaehlterSchueler";
 //BA.debugLineNum = 4238;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 4239;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 4241;BA.debugLine="sSelect = \"SELECT SchuelerID \" & _ 					\"FROM Sch";
_sselect = "SELECT SchuelerID "+"FROM Schueler "+"WHERE MatchCode LIKE '"+_sausgewaehlterschueler+"' ";
 //BA.debugLineNum = 4245;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4246;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 4247;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 4251;BA.debugLine="Try";
try { //BA.debugLineNum = 4252;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 4255;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 4258;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4260;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 4261;BA.debugLine="End Sub";
return 0;
}
public static String  _getschuelerklasse(anywheresoftware.b4a.BA _ba,int _iausgewaehlterschueler) throws Exception{
String _sklasse = "";
String _sselect = "";
 //BA.debugLineNum = 1995;BA.debugLine="Sub GetSchuelerKlasse(iAusgewaehlterSchueler As In";
 //BA.debugLineNum = 1996;BA.debugLine="Dim sKlasse, sSelect As String";
_sklasse = "";
_sselect = "";
 //BA.debugLineNum = 1998;BA.debugLine="sSelect = \"SELECT 	k.Bezeichnung \" & _ 					\" FRO";
_sselect = "SELECT 	k.Bezeichnung "+" FROM Klassen k "+"JOIN Schueler s ON TRIM(s.Kl1Beantragt) = TRIM(k.Bezeichnung) "+"WHERE s.SchuelerID = "+BA.NumberToString(_iausgewaehlterschueler);
 //BA.debugLineNum = 2003;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2004;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2005;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2009;BA.debugLine="Try";
try { //BA.debugLineNum = 2010;BA.debugLine="sKlasse = Main.SQL1.ExecQuerySingleResult(sSe";
_sklasse = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 2013;BA.debugLine="Log(\"Fehler beim holen der beantragten Klasse";
anywheresoftware.b4a.keywords.Common.LogImpl("017694738","Fehler beim holen der beantragten Klasse des Schülers "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2014;BA.debugLine="sKlasse = \"-\"";
_sklasse = "-";
 };
 //BA.debugLineNum = 2017;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2019;BA.debugLine="Return sKlasse";
if (true) return _sklasse;
 //BA.debugLineNum = 2020;BA.debugLine="End Sub";
return "";
}
public static String  _getselectedkfz(anywheresoftware.b4a.BA _ba) throws Exception{
String _sselectedkfz = "";
String _sselect = "";
 //BA.debugLineNum = 2081;BA.debugLine="Sub GetSelectedKFZ() As String";
 //BA.debugLineNum = 2082;BA.debugLine="Dim sSelectedKFZ, sSelect As String";
_sselectedkfz = "";
_sselect = "";
 //BA.debugLineNum = 2084;BA.debugLine="sSelect = \"SELECT 	f.Bezeichnung \" & _ 					\" FRO";
_sselect = "SELECT 	f.Bezeichnung "+" FROM Fahrzeuge f "+"WHERE f.IsSelected = 1";
 //BA.debugLineNum = 2088;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2089;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2090;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2094;BA.debugLine="Try";
try { //BA.debugLineNum = 2095;BA.debugLine="sSelectedKFZ = Main.SQL1.ExecQuerySingleResul";
_sselectedkfz = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 2098;BA.debugLine="Log(\"Fehler beim holen des Begleitfahrzeuges";
anywheresoftware.b4a.keywords.Common.LogImpl("017891345","Fehler beim holen des Begleitfahrzeuges "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2099;BA.debugLine="sSelectedKFZ = \"keine Auswahl\"";
_sselectedkfz = "keine Auswahl";
 };
 //BA.debugLineNum = 2102;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2104;BA.debugLine="Return sSelectedKFZ";
if (true) return _sselectedkfz;
 //BA.debugLineNum = 2105;BA.debugLine="End Sub";
return "";
}
public static int  _getsignatureid(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
String _sselect = "";
int _iid = 0;
 //BA.debugLineNum = 2820;BA.debugLine="Sub GetSignatureID(iTerminID As Int) As Int";
 //BA.debugLineNum = 2821;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2822;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 2824;BA.debugLine="sSelect = \"SELECT OID \" & _ 					\"FROM Signature";
_sselect = "SELECT OID "+"FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2828;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2829;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2830;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2833;BA.debugLine="Try";
try { //BA.debugLineNum = 2834;BA.debugLine="iID = Main.SQL1.ExecQuerySingleResult(sSelect)";
_iid = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 2836;BA.debugLine="Log(\"keine Unterschriften vorhanden\")";
anywheresoftware.b4a.keywords.Common.LogImpl("019005456","keine Unterschriften vorhanden",0);
 //BA.debugLineNum = 2837;BA.debugLine="iID = 0";
_iid = (int) (0);
 };
 //BA.debugLineNum = 2840;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2842;BA.debugLine="Return iID";
if (true) return _iid;
 //BA.debugLineNum = 2843;BA.debugLine="End Sub";
return 0;
}
public static String  _getsignaturestring(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
String _sselect = "";
String _ssigstring = "";
 //BA.debugLineNum = 2846;BA.debugLine="Sub GetSignatureString(iTerminID As Int) As String";
 //BA.debugLineNum = 2847;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2848;BA.debugLine="Dim sSigString As String";
_ssigstring = "";
 //BA.debugLineNum = 2850;BA.debugLine="sSelect = \"SELECT Unterschrift \" & _ 					\"FROM S";
_sselect = "SELECT Unterschrift "+"FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2854;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2855;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2856;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2859;BA.debugLine="Try";
try { //BA.debugLineNum = 2860;BA.debugLine="sSigString = Main.SQL1.ExecQuerySingleResult(sSe";
_ssigstring = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect);
 //BA.debugLineNum = 2861;BA.debugLine="sSigString = sSigString.Replace(\"'\", \"''\")";
_ssigstring = _ssigstring.replace("'","''");
 } 
       catch (Exception e12) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e12); //BA.debugLineNum = 2863;BA.debugLine="Log(\"Fehler beim holen des Unterschriften String";
anywheresoftware.b4a.keywords.Common.LogImpl("019070993","Fehler beim holen des Unterschriften String"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2864;BA.debugLine="sSigString = \"\"";
_ssigstring = "";
 };
 //BA.debugLineNum = 2867;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2869;BA.debugLine="Return sSigString";
if (true) return _ssigstring;
 //BA.debugLineNum = 2870;BA.debugLine="End Sub";
return "";
}
public static boolean  _insertbvfdata(anywheresoftware.b4a.BA _ba,String _sdate,String _sschueler,int _cbbesonderheinsteigen,int _cbeinstellen,int _cblenkrad,int _cbspiegel,int _cbkopfstuetze,int _cbsitz,int _cblenkradhaltung,int _cbpedale,int _cbgurt,int _cbschaltwaehlhebel,int _cbzuendschloss,int _cbmotoranlassen,int _cbanfahranhalte,int _cbschaltuebg,int _cbhoch1_2,int _cbhoch2_3,int _cbhoch3_4,int _cbrunter4_3,int _cbrunter3_2,int _cbrunter2_1,int _cbrunter4_2,int _cbrunter4_1,int _cbrunter3_1,int _cblenkuebung,int _cbumkehren,int _cbeinparkenlaengs,int _cblvorwaertsrechts,int _cblrueckwaertslinks,int _cblrueckwaertsrechts,int _cblvorwaertslinks,int _cbrueckwaertsfahren,int _cbeinparkenquer,int _cbqvorwaertsrechts,int _cbqrueckwaertslinks,int _cbqrueckwaertsrechts,int _cbqvorwaertslinks,int _cbgefahrbremsung,int _cbrollenschalten,int _cbbremsschalten,int _cbbremsuebung,int _cbdegressiv,int _cbzielbremsung,int _cbgefahrsituation,int _cbgefaelle,int _cbanhalten,int _cbanfahren,int _cbrueckwaerts,int _cbsichern,int _cbschalten,int _cbsteigung,int _cbstanhalten,int _cbstanfahren,int _cbstrueckwaerts,int _cbstsichern,int _cbstschalten,int _cbtastgeschw,int _cbbedienkontroll,int _cboertlichbesonder,int _cbfahrbahnbenutzung,int _cbeinordnen,int _cbmarkierungen,int _cbfahrstreifenwechsel,int _cblinks,int _cbrechts,int _cbvorbeifueberholen,int _cbabbiegen,int _cbabrechts,int _cbablinks,int _cbmehrspurig,int _cbradweg,int _cbsonderstreifen,int _cbstrassenbahn,int _cbeinbahnstrasse,int _cbvorfahrt,int _cbrechtsvorlinks,int _cbgruenpfeil,int _cbpolizeibeamte,int _cbgruenpfeilschild,int _cbgeschwabstand,int _cbsituationverkehrstn,int _cbfussgaengerueberweg,int _cboeffentlverkehrsm,int _cbaelterebehinderte,int _cbeinbahnstrradfahrer,int _cbkinder,int _cbschulbus,int _cbradfahrermofa,int _cbverkehrsberuhigt,int _cbschwierigeverkehrsf,int _cbengpass,int _cbkreisverkehr,int _cbbahnuebergangwarte,int _cbkritischeverkehrss,int _cbhauptverkehrszt,int _cbpartnerverhalten,int _cbschwungnutzen,int _cbfussgaengerschutzb,int _cbangepasstegeschw,int _cbabstand,int _cbulvorne,int _cbulhinten,int _cbulseitlich,int _cbbeobachtspiegel,int _cbverkehrszeichen,int _cbkreuzungeinmuend,int _cbkurven,int _cbsteigungen,int _cbulgefaelle,int _cballeen,int _cbueberholen,int _cbbesonderesituat,int _cbliegenblsichern,int _cbeinfahrenortsch,int _cbfussgaenger,int _cbwildtiere,int _cbbesondereanford,int _cbleistungsgrenze,int _cborientierung,int _cbablenkung,int _cbfahrtplanung,int _cbeinfahrtab,int _cbabfahrbahnwechsel,int _cbgeschwindigkeit,int _cbababstand,int _cbabvorne,int _cbabhinten,int _cbabseitlich,int _cbabueberholen,int _cbschilder,int _cbvorbeifahren,int _cbrastparktank,int _cbverhunfall,int _cbdichterverkehr,int _cbbesondersituat,int _cbbesonderanford,int _cbableistungsgrenze,int _cbkonfliktsitua,int _cbabablenkung,int _cbbeleuchtung,int _cbkontrolle,int _cbeinstell,int _cbbenutzung,int _cbfernlicht,int _cbverlassenbab,int _cbbeleuchtstrasse,int _cbunbeleuchtstrasse,int _cbparken,int _cbdubesondersituat,int _cbschlechtewitterung,int _cbtiere,int _cbbahnuebergaenge,int _cbunbelverkehrtn,int _cbdubesonderanfor,int _cbblendung,int _cbduorientierung,int _cbabschlussbesp,int _cbselbstfahren,int _cbinnerorts,int _cbausserorts,int _cbverantwfahren,int _cbtestfpruef,int _cbfakt,int _cbandere,int _cbwiederholung,int _cbleistungsbew,int _cbreifen,int _cbeinausschalten,int _cbfunktionpruefen,int _cbstandlicht,int _cbnebelschluss,int _cbblinker,int _cbabblendlicht,int _cbwarnblicke,int _cbhupe,int _cbbsfernlicht,int _cbschlussleuchte,int _cbbremslicht,int _cbkontrolllbenenn,int _cbrueckstrahler,int _cbvorhandensein,int _cbbeschaedigung,int _cblenkung,int _cblenkschlentriegeln,int _cbprueflenkspiel,int _cbfunktbremse,int _cbbetriebsbremse,int _cbfeststellbremse,int _cbanlegengurt,int _cbrichtigsitz,int _cbeinstellrueckspiegel,int _cbeinkopfstuetze,int _cbeinlenkrad,int _cbbedienenagg,int _cbheizung,int _cbheckheizung,int _cbbehsonderaus,int _cblueftung,int _cbklimaanlage,int _cbenergienutzung,int _cbkeineunnverbr,int _cbrechtztabsch,int _cbmotorraum,int _cbmotoroel,int _cbkuehlmittel,int _cbscheibenwaschm,int _cbtanken,int _cbbremsen,int _cbsicherungsmittel,int _cbwarndreieck,int _cbbordwerkzeug,int _cbzusaetzlichaus,int _cbverbandskasten,int _cbaussenkontrolle,int _cbscheibenwischer,int _cbkennzeichen,int _cbcheckspiegel,int _cbcheckbeleuchtung,int _cbladung,int _cbladungssicherung,int _cbkenntlichmachung,int _cbfahreschlwitt,int _cbwittlueftung,int _cbwittscheiben,int _cbregen,int _cbwasserlachen,int _cbwindsturm,int _cbmatchschnee,int _cbeis,int _cbwittbeleuchtung,String _etnotizen) throws Exception{
String _sinsertupdate = "";
boolean _bresult = false;
 //BA.debugLineNum = 3071;BA.debugLine="Sub InsertBVFData(sDate As String, sSchueler As St";
 //BA.debugLineNum = 3091;BA.debugLine="Dim sInsertUpdate As String";
_sinsertupdate = "";
 //BA.debugLineNum = 3092;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 3094;BA.debugLine="sInsertUpdate = \"INSERT INTO AusbKontrolle (\" & _";
_sinsertupdate = "INSERT INTO AusbKontrolle ("+"SchuelerID, "+"MatchCode, "+"Datum, "+"BesonderhEinsteigen, "+"Einstellen, "+"Lenkrad, "+"Spiegel, "+"Kopfstuetze, "+"Sitz, "+"Lenkradhalt, "+"Pedale, "+"Gurt, "+"SchaltWaehlhebel, "+"Zuendschloss, "+"MotorAnlassen, "+"AnfahrAnhalte, "+"Schaltuebg, "+"hoch1_2, "+"hoch2_3, "+"hoch3_4, "+"runter4_3, "+"runter3_2, "+"runter2_1, "+"runter4_2, "+"runter4_1, "+"runter3_1, "+"Lenkuebg, "+"Umkehren, "+"EinpLaengs, "+"LVorwRechts, "+"LRueckwLinks, "+"LRueckwRechts, "+"LVorwLinks, "+"Rueckfahren, "+"EinpQuer, "+"QVvorwRechts, "+"QRueckwLinks, "+"QRueckwRechts, "+"QVorwLinks, "+"Gefahrbrems, "+"RollenUndSchalten, "+"AbbrUndSchalten, "+"Bremsuebung, "+"degressiv, "+"Zielbremsung, "+"GefahrSituation, "+"Gefaelle, "+"GeAnhalten, "+"GeAnfahren, "+"GeRueckw, "+"GeSichern, "+"GeSchalten, "+"Steigung, "+"StAnhalten, "+"StAnfahren, "+"StRueckw, "+"StSichern, "+"StSchalten, "+"Tastgeschw, "+"BedienungKontrolle, "+"OertlicheBesonder, "+"FahrbahnNutz, "+"Einordnen, "+"Markierungen, "+"FahrstreifenWechsel, "+"FahrLinks, "+"FahrRechts, "+"Vorbeifahren, "+"Abbiegen, "+"AbbRechts, "+"AbbLinks, "+"FahrMehrSp, "+"FahrRadweg, "+"FahrSonderStr, "+"FahrStrassenb, "+"FahrEinbahnstr, "+"Vorfahrt, "+"RechtsVorLinks, "+"Gruenpfeil, "+"PolizeiBeamte, "+"GruenpfeilSchild, "+"GeschwAbstand, "+"SituationTN, "+"Fussgaenger, "+"OeffentVKTN, "+"AeltereBehind, "+"EinbahnStrRadf, "+"Kinder, "+"Schulbus, "+"RadfMofa, "+"VerkBeruhigt, "+"SchwierigeVKFuehrung, "+"Engpass, "+"Kreisverkehr, "+"Bahnuebergang, "+"KritischeVKSit, "+"HauptVKZeit, "+"PartnerVerhalten, "+"SchwungNutzen, "+"FussgaengerSchutz, "+"AngepGeschw, "+"Abstand, "+"AbstVorne, "+"AbstHinten, "+"AbstSeite, "+"BeobSpiegel, "+"VerkehrsZeich, "+"KreuzMuend, "+"Kurven, "+"Steigungen, "+"BesGefaelle, "+"Alleen, "+"Ueberholen, "+"BesSituation, "+"LiegeblAbsicher, "+"EinfOrtschaft, "+"BesFussgaenger, "+"WildTiere, "+"BesAnforderung, "+"Leistungsgr, "+"Orientierung, "+"Ablenkung, "+"Fahrtplanung, "+"EinfahBAB, "+"FahrStrWechsel, "+"Geschwindig, "+"AbstandBAB, "+"VorneBAB, "+"HintenBAB, "+"SeiteBAB, "+"BABUeberholen, "+"SchildMarkierung, "+"VoebeifAnschluss, "+"RastParkTank, "+"VerhaltenUnfall, "+"DichtStau, "+"BesSitBAB, "+"BesAnfordBAB, "+"LeistungsgrBAB, "+"KonfliktBAB, "+"AblenkungBAB, "+"VerlassBAB, "+"Beleuchtung, "+"KontrolleBel, "+"EinstellBel, "+"BenutzBel, "+"FernlichtBel, "+"BelStrasse, "+"UnbelStrasse, "+"Parken, "+"BesSitDunkel, "+"SchlechWittDun, "+"TiereDun, "+"BahnueberDun, "+"UnbelVKTN, "+"BesAnforDun, "+"Blendung, "+"OrientDun, "+"AbschlussbespDun, "+"SelbstFahren, "+"Innerorts, "+"Ausserorts, "+"VerantwFahren, "+"TestfPruefung, "+"FAKT, "+"andere, "+"WiederhVertief, "+"Leistungsbew, "+"Reifen, "+"EinAusschalten, "+"FunkPruefen, "+"Standlicht, "+"Nebelschl, "+"Blinker, "+"Abblendlicht, "+"Warnblick, "+"Hupe, "+"Fernlicht, "+"SchlussLeuchte, "+"Bremslicht, "+"KontrollLeuchten, "+"Rueckstrahler, "+"Vorhanden, "+"Beschaedigt, "+"Lenkung, "+"LenkschlEntriegel, "+"Lenkspiel, "+"FunkBremsen, "+"BetriebBremse, "+"FeststellBremse, "+"Sitzeinstellung, "+"EinstRueckspiegel, "+"EinstKopf, "+"EinstLenksrad, "+"AnlegenGurt, "+"BedienHeizung, "+"Heizung, "+"HeckHeizung, "+"BehSonderAusstg, "+"Lueftung, "+"Klima, "+"EnergiespNutz, "+"KeineUnnVerbr, "+"RechtzAbschalten, "+"CheckMotorraum, "+"Motoroel, "+"Kuehlmittel, "+"Wischwasser, "+"Tanken, "+"SicherungsMittel, "+"WarnDreieck, "+"Bordwerkzeug, "+"zusaetzAusruest, "+"Verbandskasten, "+"AussenKontrolle, "+"Scheiben, "+"KennzeichenHUAU, "+"CheckSpiegel, "+"CheckBeleuchtung, "+"Bremsung, "+"Ladung, "+"Sicherung, "+"KenntlichMachung, "+"FahrenSchelchtWetter, "+"WittLueftung, "+"ScheibenWischer, "+"RegenSprueh, "+"Wasserlachen, "+"WindSturm, "+"SchneeMatsch, "+"Eis, "+"WittBeleucht, "+"Kommentar ) "+"Values ( "+"'"+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+"', "+"'"+_sschueler+"', "+"'"+_sdate+"', "+BA.NumberToString(_cbbesonderheinsteigen)+", "+BA.NumberToString(_cbeinstellen)+", "+BA.NumberToString(_cblenkrad)+", "+BA.NumberToString(_cbspiegel)+", "+BA.NumberToString(_cbkopfstuetze)+", "+BA.NumberToString(_cbsitz)+", "+BA.NumberToString(_cblenkradhaltung)+", "+BA.NumberToString(_cbpedale)+", "+BA.NumberToString(_cbgurt)+", "+BA.NumberToString(_cbschaltwaehlhebel)+", "+BA.NumberToString(_cbzuendschloss)+", "+BA.NumberToString(_cbmotoranlassen)+", "+BA.NumberToString(_cbanfahranhalte)+", "+BA.NumberToString(_cbschaltuebg)+", "+BA.NumberToString(_cbhoch1_2)+", "+BA.NumberToString(_cbhoch2_3)+", "+BA.NumberToString(_cbhoch3_4)+", "+BA.NumberToString(_cbrunter4_3)+", "+BA.NumberToString(_cbrunter3_2)+", "+BA.NumberToString(_cbrunter2_1)+", "+BA.NumberToString(_cbrunter4_2)+", "+BA.NumberToString(_cbrunter4_1)+", "+BA.NumberToString(_cbrunter3_1)+", "+BA.NumberToString(_cblenkuebung)+", "+BA.NumberToString(_cbumkehren)+", "+BA.NumberToString(_cbeinparkenlaengs)+", "+BA.NumberToString(_cblvorwaertsrechts)+", "+BA.NumberToString(_cblrueckwaertslinks)+", "+BA.NumberToString(_cblrueckwaertsrechts)+", "+BA.NumberToString(_cblvorwaertslinks)+", "+BA.NumberToString(_cbrueckwaertsfahren)+", "+BA.NumberToString(_cbeinparkenquer)+", "+BA.NumberToString(_cbqvorwaertsrechts)+", "+BA.NumberToString(_cbqrueckwaertslinks)+", "+BA.NumberToString(_cbqrueckwaertsrechts)+", "+BA.NumberToString(_cbqvorwaertslinks)+", "+BA.NumberToString(_cbgefahrbremsung)+", "+BA.NumberToString(_cbrollenschalten)+", "+BA.NumberToString(_cbbremsschalten)+", "+BA.NumberToString(_cbbremsuebung)+", "+BA.NumberToString(_cbdegressiv)+", "+BA.NumberToString(_cbzielbremsung)+", "+BA.NumberToString(_cbgefahrsituation)+", "+BA.NumberToString(_cbgefaelle)+", "+BA.NumberToString(_cbanhalten)+", "+BA.NumberToString(_cbanfahren)+", "+BA.NumberToString(_cbrueckwaerts)+", "+BA.NumberToString(_cbsichern)+", "+BA.NumberToString(_cbschalten)+", "+BA.NumberToString(_cbsteigung)+", "+BA.NumberToString(_cbstanhalten)+", "+BA.NumberToString(_cbstanfahren)+", "+BA.NumberToString(_cbstrueckwaerts)+", "+BA.NumberToString(_cbstsichern)+", "+BA.NumberToString(_cbstschalten)+", "+BA.NumberToString(_cbtastgeschw)+", "+BA.NumberToString(_cbbedienkontroll)+", "+BA.NumberToString(_cboertlichbesonder)+", "+BA.NumberToString(_cbfahrbahnbenutzung)+", "+BA.NumberToString(_cbeinordnen)+", "+BA.NumberToString(_cbmarkierungen)+", "+BA.NumberToString(_cbfahrstreifenwechsel)+", "+BA.NumberToString(_cblinks)+", "+BA.NumberToString(_cbrechts)+", "+BA.NumberToString(_cbvorbeifueberholen)+", "+BA.NumberToString(_cbabbiegen)+", "+BA.NumberToString(_cbabrechts)+", "+BA.NumberToString(_cbablinks)+", "+BA.NumberToString(_cbmehrspurig)+", "+BA.NumberToString(_cbradweg)+", "+BA.NumberToString(_cbsonderstreifen)+", "+BA.NumberToString(_cbstrassenbahn)+", "+BA.NumberToString(_cbeinbahnstrasse)+", "+BA.NumberToString(_cbvorfahrt)+", "+BA.NumberToString(_cbrechtsvorlinks)+", "+BA.NumberToString(_cbgruenpfeil)+", "+BA.NumberToString(_cbpolizeibeamte)+", "+BA.NumberToString(_cbgruenpfeilschild)+", "+BA.NumberToString(_cbgeschwabstand)+", "+BA.NumberToString(_cbsituationverkehrstn)+", "+BA.NumberToString(_cbfussgaengerueberweg)+", "+BA.NumberToString(_cboeffentlverkehrsm)+", "+BA.NumberToString(_cbaelterebehinderte)+", "+BA.NumberToString(_cbeinbahnstrradfahrer)+", "+BA.NumberToString(_cbkinder)+", "+BA.NumberToString(_cbschulbus)+", "+BA.NumberToString(_cbradfahrermofa)+", "+BA.NumberToString(_cbverkehrsberuhigt)+", "+BA.NumberToString(_cbschwierigeverkehrsf)+", "+BA.NumberToString(_cbengpass)+", "+BA.NumberToString(_cbkreisverkehr)+", "+BA.NumberToString(_cbbahnuebergangwarte)+", "+BA.NumberToString(_cbkritischeverkehrss)+", "+BA.NumberToString(_cbhauptverkehrszt)+", "+BA.NumberToString(_cbpartnerverhalten)+", "+BA.NumberToString(_cbschwungnutzen)+", "+BA.NumberToString(_cbfussgaengerschutzb)+", "+BA.NumberToString(_cbangepasstegeschw)+", "+BA.NumberToString(_cbabstand)+", "+BA.NumberToString(_cbulvorne)+", "+BA.NumberToString(_cbulhinten)+", "+BA.NumberToString(_cbulseitlich)+", "+BA.NumberToString(_cbbeobachtspiegel)+", "+BA.NumberToString(_cbverkehrszeichen)+", "+BA.NumberToString(_cbkreuzungeinmuend)+", "+BA.NumberToString(_cbkurven)+", "+BA.NumberToString(_cbsteigungen)+", "+BA.NumberToString(_cbulgefaelle)+", "+BA.NumberToString(_cballeen)+", "+BA.NumberToString(_cbueberholen)+", "+BA.NumberToString(_cbbesonderesituat)+", "+BA.NumberToString(_cbliegenblsichern)+", "+BA.NumberToString(_cbeinfahrenortsch)+", "+BA.NumberToString(_cbfussgaenger)+", "+BA.NumberToString(_cbwildtiere)+", "+BA.NumberToString(_cbbesondereanford)+", "+BA.NumberToString(_cbleistungsgrenze)+", "+BA.NumberToString(_cborientierung)+", "+BA.NumberToString(_cbablenkung)+", "+BA.NumberToString(_cbfahrtplanung)+", "+BA.NumberToString(_cbeinfahrtab)+", "+BA.NumberToString(_cbabfahrbahnwechsel)+", "+BA.NumberToString(_cbgeschwindigkeit)+", "+BA.NumberToString(_cbababstand)+", "+BA.NumberToString(_cbabvorne)+", "+BA.NumberToString(_cbabhinten)+", "+BA.NumberToString(_cbabseitlich)+", "+BA.NumberToString(_cbabueberholen)+", "+BA.NumberToString(_cbschilder)+", "+BA.NumberToString(_cbvorbeifahren)+", "+BA.NumberToString(_cbrastparktank)+", "+BA.NumberToString(_cbverhunfall)+", "+BA.NumberToString(_cbdichterverkehr)+", "+BA.NumberToString(_cbbesondersituat)+", "+BA.NumberToString(_cbbesonderanford)+", "+BA.NumberToString(_cbableistungsgrenze)+", "+BA.NumberToString(_cbkonfliktsitua)+", "+BA.NumberToString(_cbabablenkung)+", "+BA.NumberToString(_cbverlassenbab)+", "+BA.NumberToString(_cbbeleuchtung)+", "+BA.NumberToString(_cbkontrolle)+", "+BA.NumberToString(_cbeinstell)+", "+BA.NumberToString(_cbbenutzung)+", "+BA.NumberToString(_cbfernlicht)+", "+BA.NumberToString(_cbbeleuchtstrasse)+", "+BA.NumberToString(_cbunbeleuchtstrasse)+", "+BA.NumberToString(_cbparken)+", "+BA.NumberToString(_cbdubesondersituat)+", "+BA.NumberToString(_cbschlechtewitterung)+", "+BA.NumberToString(_cbtiere)+", "+BA.NumberToString(_cbbahnuebergaenge)+", "+BA.NumberToString(_cbunbelverkehrtn)+", "+BA.NumberToString(_cbdubesonderanfor)+", "+BA.NumberToString(_cbblendung)+", "+BA.NumberToString(_cbduorientierung)+", "+BA.NumberToString(_cbabschlussbesp)+", "+BA.NumberToString(_cbselbstfahren)+", "+BA.NumberToString(_cbinnerorts)+", "+BA.NumberToString(_cbausserorts)+", "+BA.NumberToString(_cbverantwfahren)+", "+BA.NumberToString(_cbtestfpruef)+", "+BA.NumberToString(_cbfakt)+", "+BA.NumberToString(_cbandere)+", "+BA.NumberToString(_cbwiederholung)+", "+BA.NumberToString(_cbleistungsbew)+", "+BA.NumberToString(_cbreifen)+", "+BA.NumberToString(_cbeinausschalten)+", "+BA.NumberToString(_cbfunktionpruefen)+", "+BA.NumberToString(_cbstandlicht)+", "+BA.NumberToString(_cbnebelschluss)+", "+BA.NumberToString(_cbblinker)+", "+BA.NumberToString(_cbabblendlicht)+", "+BA.NumberToString(_cbwarnblicke)+", "+BA.NumberToString(_cbhupe)+", "+BA.NumberToString(_cbbsfernlicht)+", "+BA.NumberToString(_cbschlussleuchte)+", "+BA.NumberToString(_cbbremslicht)+", "+BA.NumberToString(_cbkontrolllbenenn)+", "+BA.NumberToString(_cbrueckstrahler)+", "+BA.NumberToString(_cbvorhandensein)+", "+BA.NumberToString(_cbbeschaedigung)+", "+BA.NumberToString(_cblenkung)+", "+BA.NumberToString(_cblenkschlentriegeln)+", "+BA.NumberToString(_cbprueflenkspiel)+", "+BA.NumberToString(_cbfunktbremse)+", "+BA.NumberToString(_cbbetriebsbremse)+", "+BA.NumberToString(_cbfeststellbremse)+", "+BA.NumberToString(_cbrichtigsitz)+", "+BA.NumberToString(_cbeinstellrueckspiegel)+", "+BA.NumberToString(_cbeinkopfstuetze)+", "+BA.NumberToString(_cbeinlenkrad)+", "+BA.NumberToString(_cbanlegengurt)+", "+BA.NumberToString(_cbbedienenagg)+", "+BA.NumberToString(_cbheizung)+", "+BA.NumberToString(_cbheckheizung)+", "+BA.NumberToString(_cbbehsonderaus)+", "+BA.NumberToString(_cblueftung)+", "+BA.NumberToString(_cbklimaanlage)+", "+BA.NumberToString(_cbenergienutzung)+", "+BA.NumberToString(_cbkeineunnverbr)+", "+BA.NumberToString(_cbrechtztabsch)+", "+BA.NumberToString(_cbmotorraum)+", "+BA.NumberToString(_cbmotoroel)+", "+BA.NumberToString(_cbkuehlmittel)+", "+BA.NumberToString(_cbscheibenwaschm)+", "+BA.NumberToString(_cbtanken)+", "+BA.NumberToString(_cbsicherungsmittel)+", "+BA.NumberToString(_cbwarndreieck)+", "+BA.NumberToString(_cbbordwerkzeug)+", "+BA.NumberToString(_cbzusaetzlichaus)+", "+BA.NumberToString(_cbverbandskasten)+", "+BA.NumberToString(_cbaussenkontrolle)+", "+BA.NumberToString(_cbscheibenwischer)+", "+BA.NumberToString(_cbkennzeichen)+", "+BA.NumberToString(_cbcheckspiegel)+", "+BA.NumberToString(_cbcheckbeleuchtung)+", "+BA.NumberToString(_cbbremsen)+", "+BA.NumberToString(_cbladung)+", "+BA.NumberToString(_cbladungssicherung)+", "+BA.NumberToString(_cbkenntlichmachung)+", "+BA.NumberToString(_cbfahreschlwitt)+", "+BA.NumberToString(_cbwittlueftung)+", "+BA.NumberToString(_cbwittscheiben)+", "+BA.NumberToString(_cbregen)+", "+BA.NumberToString(_cbwasserlachen)+", "+BA.NumberToString(_cbwindsturm)+", "+BA.NumberToString(_cbmatchschnee)+", "+BA.NumberToString(_cbeis)+", "+BA.NumberToString(_cbwittbeleuchtung)+", "+"'"+_etnotizen+"'"+") ";
 //BA.debugLineNum = 3564;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 3565;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3566;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3570;BA.debugLine="Try";
try { //BA.debugLineNum = 3571;BA.debugLine="Main.SQL1.ExecNonQuery(sInsertUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsertupdate);
 //BA.debugLineNum = 3573;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e12) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e12); //BA.debugLineNum = 3575;BA.debugLine="Log(\"Fehler beim speichern der BVF Daten \" & Las";
anywheresoftware.b4a.keywords.Common.LogImpl("019595768","Fehler beim speichern der BVF Daten "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 3576;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 3579;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 3581;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 3582;BA.debugLine="End Sub";
return false;
}
public static int  _insertneuertreffpunkt(anywheresoftware.b4a.BA _ba,String _sneuertreffpunkt,int _ifixereintrag) throws Exception{
int _bresult = 0;
String _sinsertupdate = "";
String _sgetid = "";
int _iid = 0;
 //BA.debugLineNum = 2378;BA.debugLine="Sub InsertNeuerTreffpunkt(sNeuerTreffpunkt As Stri";
 //BA.debugLineNum = 2379;BA.debugLine="Dim bResult As Int";
_bresult = 0;
 //BA.debugLineNum = 2380;BA.debugLine="Dim sInsertUpdate, sGetID As String";
_sinsertupdate = "";
_sgetid = "";
 //BA.debugLineNum = 2381;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 2384;BA.debugLine="If CheckAnzahlFixerTreffpunkte > 21 Then";
if (_checkanzahlfixertreffpunkte(_ba)>21) { 
 //BA.debugLineNum = 2385;BA.debugLine="bResult = 0";
_bresult = (int) (0);
 }else {
 //BA.debugLineNum = 2387;BA.debugLine="sNeuerTreffpunkt = sNeuerTreffpunkt.Trim";
_sneuertreffpunkt = _sneuertreffpunkt.trim();
 //BA.debugLineNum = 2390;BA.debugLine="If sNeuerTreffpunkt.Length > 22 Then";
if (_sneuertreffpunkt.length()>22) { 
 //BA.debugLineNum = 2391;BA.debugLine="sNeuerTreffpunkt = sNeuerTreffpunkt.SubString2(";
_sneuertreffpunkt = _sneuertreffpunkt.substring((int) (0),(int) (21));
 }else if(_sneuertreffpunkt.length()==0) { 
 //BA.debugLineNum = 2394;BA.debugLine="sNeuerTreffpunkt = \"-\"";
_sneuertreffpunkt = "-";
 };
 //BA.debugLineNum = 2398;BA.debugLine="iID = TreffpunktPruefen(sNeuerTreffpunkt)";
_iid = _treffpunktpruefen(_ba,_sneuertreffpunkt);
 //BA.debugLineNum = 2399;BA.debugLine="If iID > 0 Then";
if (_iid>0) { 
 //BA.debugLineNum = 2400;BA.debugLine="sInsertUpdate = \"UPDATE Treffpunkt \" & _";
_sinsertupdate = "UPDATE Treffpunkt "+"SET fixer_Treffpunkt = "+BA.NumberToString(_ifixereintrag)+" WHERE OID = "+BA.NumberToString(_iid);
 }else {
 //BA.debugLineNum = 2404;BA.debugLine="sInsertUpdate = \"INSERT INTO Treffpunkt (\" & _";
_sinsertupdate = "INSERT INTO Treffpunkt ("+"Bezeichnung, "+"fixer_Treffpunkt )"+"VALUES ( "+"'"+_sneuertreffpunkt+"', "+BA.NumberToString(_ifixereintrag)+" ) ";
 };
 //BA.debugLineNum = 2412;BA.debugLine="sGetID = 	\"SELECT OID \" & _ 						\"FROM Treffpun";
_sgetid = "SELECT OID "+"FROM Treffpunkt "+"WHERE Bezeichnung LIKE '"+_sneuertreffpunkt+"'";
 //BA.debugLineNum = 2416;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2417;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2418;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2422;BA.debugLine="Try";
try { //BA.debugLineNum = 2423;BA.debugLine="Main.SQL1.ExecNonQuery(sInsertUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsertupdate);
 //BA.debugLineNum = 2424;BA.debugLine="bResult = Main.SQL1.ExecQuerySingleResult(sGetI";
_bresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sgetid)));
 } 
       catch (Exception e28) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e28); //BA.debugLineNum = 2427;BA.debugLine="Log(\"Fehler beim hinzufügen eines neuen Tref";
anywheresoftware.b4a.keywords.Common.LogImpl("018284593","Fehler beim hinzufügen eines neuen Treffpunkts "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2428;BA.debugLine="bResult = 0";
_bresult = (int) (0);
 };
 //BA.debugLineNum = 2431;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 };
 //BA.debugLineNum = 2435;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 2436;BA.debugLine="End Sub";
return 0;
}
public static boolean  _ispruefung(anywheresoftware.b4a.BA _ba,String _sfahrtbezeichnung) throws Exception{
boolean _breturn = false;
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 2642;BA.debugLine="Sub IsPruefung(sFahrtbezeichnung As String) As Boo";
 //BA.debugLineNum = 2643;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 2644;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2645;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2647;BA.debugLine="If (Main.bSonstTaetigkeit) Then";
if ((mostCurrent._main._bsonsttaetigkeit /*boolean*/ )) { 
 //BA.debugLineNum = 2648;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 2650;BA.debugLine="sSelect = \"SELECT COUNT(*) \" & _ 						\"FROM Fah";
_sselect = "SELECT COUNT(*) "+"FROM Fahrtbezeichnung "+"WHERE Kuerzel LIKE '"+_sfahrtbezeichnung+"' "+"AND Pruefungen = 1 "+"AND SonstTaetigkeiten = 0";
 //BA.debugLineNum = 2656;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2657;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2658;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2661;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 2663;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 2664;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2666;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2669;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 };
 //BA.debugLineNum = 2672;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 2673;BA.debugLine="End Sub";
return false;
}
public static String  _kfzfixieren(anywheresoftware.b4a.BA _ba,String _skfz) throws Exception{
String _sreset = "";
String _supdate = "";
 //BA.debugLineNum = 621;BA.debugLine="Sub KFZFixieren(sKFZ As String)";
 //BA.debugLineNum = 622;BA.debugLine="Dim sReset, sUpdate As String";
_sreset = "";
_supdate = "";
 //BA.debugLineNum = 624;BA.debugLine="sReset = \"UPDATE Fahrzeuge \" & _ 				\"SET IsSelec";
_sreset = "UPDATE Fahrzeuge "+"SET IsSelected = 0 ";
 //BA.debugLineNum = 628;BA.debugLine="sUpdate = \"UPDATE Fahrzeuge \" & _ 				\"SET IsSele";
_supdate = "UPDATE Fahrzeuge "+"SET IsSelected = 1 "+"WHERE Bezeichnung LIKE '"+_skfz+"' ";
 //BA.debugLineNum = 632;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 633;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 634;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 638;BA.debugLine="Try";
try { //BA.debugLineNum = 639;BA.debugLine="Main.SQL1.ExecNonQuery(sReset)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sreset);
 //BA.debugLineNum = 640;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e12) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e12); //BA.debugLineNum = 643;BA.debugLine="Log(\"Fehler beim setzen des fixen KFZ \" & Las";
anywheresoftware.b4a.keywords.Common.LogImpl("015925270","Fehler beim setzen des fixen KFZ "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 646;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 647;BA.debugLine="End Sub";
return "";
}
public static int  _klasseid(anywheresoftware.b4a.BA _ba,String _sklasse) throws Exception{
String _sselect = "";
int _iresult = 0;
 //BA.debugLineNum = 4182;BA.debugLine="Sub KlasseID(sKlasse As String) As Int";
 //BA.debugLineNum = 4183;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 4184;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 4186;BA.debugLine="sSelect = \"SELECT OID \" & _ 					\"FROM Klassen \"";
_sselect = "SELECT OID "+"FROM Klassen "+"WHERE Bezeichnung LIKE '"+_sklasse+"'"+"AND Enable = 1";
 //BA.debugLineNum = 4191;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4192;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 4193;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 4197;BA.debugLine="Try";
try { //BA.debugLineNum = 4198;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSelec";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 4202;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 4205;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4207;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 4208;BA.debugLine="End Sub";
return 0;
}
public static boolean[]  _konfigdatenladen(anywheresoftware.b4a.BA _ba) throws Exception{
boolean[] _akonfig = null;
String _sselect = "";
boolean _sbegleitfahrzeug = false;
boolean _spruefergebnis = false;
boolean _sbvfausbildung = false;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
 //BA.debugLineNum = 2305;BA.debugLine="Sub KonfigdatenLaden() As Boolean()";
 //BA.debugLineNum = 2306;BA.debugLine="Dim aKonfig(3) As Boolean";
_akonfig = new boolean[(int) (3)];
;
 //BA.debugLineNum = 2307;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2308;BA.debugLine="Dim sBegleitfahrzeug, sPruefergebnis, sBVFAusbild";
_sbegleitfahrzeug = false;
_spruefergebnis = false;
_sbvfausbildung = false;
 //BA.debugLineNum = 2309;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 2315;BA.debugLine="sSelect = \"SELECT Begleitfahrzeug_IsSelected, Pru";
_sselect = "SELECT Begleitfahrzeug_IsSelected, Pruefungsergebnis_IsSelected, BVFAusbildung_IsSelected FROM Konfigdaten";
 //BA.debugLineNum = 2316;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2317;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2318;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2321;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2323;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2325;BA.debugLine="If cur.GetString(\"Begleitfahrzeug_IsSelected\") =";
if ((_cur.GetString("Begleitfahrzeug_IsSelected")).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2326;BA.debugLine="sBegleitfahrzeug = True";
_sbegleitfahrzeug = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2328;BA.debugLine="sBegleitfahrzeug = False";
_sbegleitfahrzeug = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2331;BA.debugLine="If cur.GetString(\"Pruefungsergebnis_IsSelected\")";
if ((_cur.GetString("Pruefungsergebnis_IsSelected")).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2332;BA.debugLine="sPruefergebnis = True";
_spruefergebnis = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2334;BA.debugLine="sPruefergebnis = False";
_spruefergebnis = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2337;BA.debugLine="If cur.GetString(\"BVFAusbildung_IsSelected\") = 1";
if ((_cur.GetString("BVFAusbildung_IsSelected")).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2338;BA.debugLine="sBVFAusbildung = True";
_sbvfausbildung = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 2340;BA.debugLine="sBVFAusbildung = False";
_sbvfausbildung = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2343;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 2344;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2346;BA.debugLine="aKonfig = Array As Boolean(sBegleitfahrzeug, sPru";
_akonfig = new boolean[]{_sbegleitfahrzeug,_spruefergebnis,_sbvfausbildung};
 //BA.debugLineNum = 2347;BA.debugLine="Return aKonfig";
if (true) return _akonfig;
 //BA.debugLineNum = 2348;BA.debugLine="End Sub";
return null;
}
public static String  _korrigierenfehlerhaftematchcodes(anywheresoftware.b4a.BA _ba) throws Exception{
String _sdelete = "";
 //BA.debugLineNum = 175;BA.debugLine="Sub KorrigierenFehlerhafteMatchCodes()";
 //BA.debugLineNum = 176;BA.debugLine="Dim sDelete As String";
_sdelete = "";
 //BA.debugLineNum = 178;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 179;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 180;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 183;BA.debugLine="sDelete = \"DELETE FROM Schueler \" & _ 				\"WHERE";
_sdelete = "DELETE FROM Schueler "+"WHERE MatchCode IS NULL "+"OR MatchCode LIKE '' ";
 //BA.debugLineNum = 188;BA.debugLine="Try";
try { //BA.debugLineNum = 189;BA.debugLine="Main.SQL1.ExecNonQuery(sDelete)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdelete);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 192;BA.debugLine="Log(\"Fehler beim löschen der Einträge in der Sch";
anywheresoftware.b4a.keywords.Common.LogImpl("015138833","Fehler beim löschen der Einträge in der Schueler-Tabelle bei denen der MatchCode leer ist bzw. kein MatchCode vorhanden ist"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 196;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _korrigierenfehlerhaftereintraege(anywheresoftware.b4a.BA _ba) throws Exception{
String _supdate = "";
 //BA.debugLineNum = 150;BA.debugLine="Sub KorrigierenFehlerhafterEintraege()";
 //BA.debugLineNum = 151;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 153;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 154;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 155;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 158;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 				\"SET Hat_Unte";
_supdate = "UPDATE Termine "+"SET Hat_Unterschrift = 1 "+"WHERE Hat_Unterschrift > 1 ";
 //BA.debugLineNum = 163;BA.debugLine="Try";
try { //BA.debugLineNum = 164;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 167;BA.debugLine="Log(\"Fehler beim korrgieren der Termin-Tabelle b";
anywheresoftware.b4a.keywords.Common.LogImpl("015073297","Fehler beim korrgieren der Termin-Tabelle bei Hat_Unterschrift"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 171;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _removeneuentreffpunkt(anywheresoftware.b4a.BA _ba,String _streffpunkt) throws Exception{
String _supdate = "";
 //BA.debugLineNum = 2467;BA.debugLine="Sub RemoveNeuenTreffpunkt(sTreffpunkt As String)";
 //BA.debugLineNum = 2468;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 2470;BA.debugLine="sUpdate = \"UPDATE Treffpunkt \" & _ 					\"SET fixe";
_supdate = "UPDATE Treffpunkt "+"SET fixer_Treffpunkt = 0 "+"WHERE Bezeichnung LIKE '"+_streffpunkt+"' ";
 //BA.debugLineNum = 2474;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2475;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2476;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2480;BA.debugLine="Try";
try { //BA.debugLineNum = 2481;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 2484;BA.debugLine="Log(\"Fehler beim löschen/deaktivieren eines T";
anywheresoftware.b4a.keywords.Common.LogImpl("018415633","Fehler beim löschen/deaktivieren eines Treffpunktes "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 2487;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2488;BA.debugLine="End Sub";
return "";
}
public static boolean  _savesignature(anywheresoftware.b4a.BA _ba,String _sverschluesselung,int _iterminid,int _ischuelerid) throws Exception{
boolean _breturn = false;
String _supdate = "";
String _sselect = "";
String _sinsert = "";
int _ianzahl = 0;
 //BA.debugLineNum = 4291;BA.debugLine="Sub SaveSignature(sVerschluesselung As String, iTe";
 //BA.debugLineNum = 4292;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 4293;BA.debugLine="Dim sUpdate, sSelect, sInsert As String";
_supdate = "";
_sselect = "";
_sinsert = "";
 //BA.debugLineNum = 4294;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 4297;BA.debugLine="sVerschluesselung = sVerschluesselung.Replace(\"'\"";
_sverschluesselung = _sverschluesselung.replace("'","''");
 //BA.debugLineNum = 4301;BA.debugLine="sSelect = \"SELECT COUNT(*) FROM Signature \" & _";
_sselect = "SELECT COUNT(*) FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 4305;BA.debugLine="sInsert = \"INSERT INTO Signature ( 	Termine_ID, \"";
_sinsert = "INSERT INTO Signature ( 	Termine_ID, "+"Schueler_ID, "+"Datum, "+"Unterschrift ) "+"VALUES ( "+BA.NumberToString(_iterminid)+", "+BA.NumberToString(_ischuelerid)+", "+"'"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"', "+"'"+_sverschluesselung+"' )";
 //BA.debugLineNum = 4314;BA.debugLine="sUpdate = \"UPDATE Signature \" & _ 					\"SET Datum";
_supdate = "UPDATE Signature "+"SET Datum = '"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"', "+"Unterschrift = '"+_sverschluesselung+"' "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 4319;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 4320;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 4325;BA.debugLine="Try";
try { //BA.debugLineNum = 4326;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 4328;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 4329;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 }else {
 //BA.debugLineNum = 4331;BA.debugLine="Main.SQL1.ExecNonQuery(sInsert)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsert);
 };
 //BA.debugLineNum = 4335;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e20) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e20); //BA.debugLineNum = 4337;BA.debugLine="Log(LastException.Message) 'no changes will b";
anywheresoftware.b4a.keywords.Common.LogImpl("020185134",anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 4338;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 4341;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4343;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 4344;BA.debugLine="End Sub";
return false;
}
public static String  _setanmeldedatum(anywheresoftware.b4a.BA _ba) throws Exception{
String _supdate = "";
 //BA.debugLineNum = 200;BA.debugLine="Sub SetAnmeldedatum()";
 //BA.debugLineNum = 201;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 203;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 204;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 205;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 208;BA.debugLine="sUpdate = \"UPDATE Schueler \" & _ 				\"SET Anmelde";
_supdate = "UPDATE Schueler "+"SET AnmeldeDatum = '31.12.1899' "+"WHERE AnmeldeDatum IS NULL "+"OR AnmeldeDatum = ''";
 //BA.debugLineNum = 214;BA.debugLine="Try";
try { //BA.debugLineNum = 215;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 218;BA.debugLine="Log(\"Fehler beim setzen der AnmeldeDatums in Sch";
anywheresoftware.b4a.keywords.Common.LogImpl("015204370","Fehler beim setzen der AnmeldeDatums in Schüler-Tabelle"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 222;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static String  _setbegleitfahrzeug(anywheresoftware.b4a.BA _ba,boolean _checked,String _stabelle) throws Exception{
int _ivalue = 0;
String _supdate = "";
 //BA.debugLineNum = 2274;BA.debugLine="Sub SetBegleitfahrzeug(Checked As Boolean, sTabell";
 //BA.debugLineNum = 2275;BA.debugLine="Dim iValue As Int";
_ivalue = 0;
 //BA.debugLineNum = 2276;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 2278;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 2279;BA.debugLine="iValue = 1";
_ivalue = (int) (1);
 }else {
 //BA.debugLineNum = 2281;BA.debugLine="iValue = 0";
_ivalue = (int) (0);
 };
 //BA.debugLineNum = 2284;BA.debugLine="sUpdate = \"UPDATE Konfigdaten \" & _ 					\"SET \" &";
_supdate = "UPDATE Konfigdaten "+"SET "+_stabelle+" = "+BA.NumberToString(_ivalue);
 //BA.debugLineNum = 2287;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2288;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2289;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2293;BA.debugLine="Try";
try { //BA.debugLineNum = 2294;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e16) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e16); //BA.debugLineNum = 2297;BA.debugLine="Log(\"Fehler beim speichern der Daten in Konfi";
anywheresoftware.b4a.keywords.Common.LogImpl("018087959","Fehler beim speichern der Daten in Konfig (Update: "+_stabelle+") "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 2300;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2302;BA.debugLine="End Sub";
return "";
}
public static String  _setbvffelder(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderheinsteigen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkradhaltung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpedale,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltwaehlhebel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzuendschloss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoranlassen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahranhalte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltuebg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch1_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch2_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch3_4,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter2_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbumkehren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenlaengs,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaertsfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenquer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrollenschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdegressiv,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzielbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrsituation,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtastgeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienkontroll,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboertlichbesonder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrbahnbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinordnen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmarkierungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrstreifenwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabbiegen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmehrspurig,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsonderstreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrassenbahn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorfahrt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtsvorlinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeil,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpolizeibeamte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeilschild,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsituationverkehrstn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerueberweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboeffentlverkehrsm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaelterebehinderte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrradfahrer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkinder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschulbus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradfahrermofa,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrsberuhigt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwierigeverkehrsf,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbengpass,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreisverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergangwarte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkritischeverkehrss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhauptverkehrszt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpartnerverhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwungnutzen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerschutzb,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbangepasstegeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeobachtspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrszeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreuzungeinmuend,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkurven,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cballeen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderesituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbliegenblsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrenortsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaenger,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwildtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondereanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cborientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrtplanung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrtab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabfahrbahnwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwindigkeit,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbababstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschilder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrastparktank,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverhunfall,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdichterverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbableistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkonfliktsitua,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstell,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverlassenbab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbparken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlechtewitterung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergaenge,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbelverkehrtn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesonderanfor,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblendung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbduorientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabschlussbesp,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbselbstfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbinnerorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbausserorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverantwfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtestfpruef,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfakt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbandere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwiederholung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsbew,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinausschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktionpruefen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstandlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbnebelschluss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblinker,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabblendlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarnblicke,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhupe,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbsfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlussleuchte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremslicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolllbenenn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckstrahler,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorhandensein,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeschaedigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkschlentriegeln,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbprueflenkspiel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbetriebsbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfeststellbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanlegengurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrichtigsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellrueckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinlenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienenagg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheckheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbehsonderaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbklimaanlage,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbenergienutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkeineunnverbr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtztabsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotorraum,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoroel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkuehlmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwaschm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtanken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsicherungsmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarndreieck,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbordwerkzeug,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzusaetzlichaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverbandskasten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaussenkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwischer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkennzeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladungssicherung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkenntlichmachung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahreschlwitt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittlueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittscheiben,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbregen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwasserlachen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwindsturm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmatchschnee,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeis,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittbeleuchtung,anywheresoftware.b4a.objects.EditTextWrapper _etnotizen,int _ibvfoid) throws Exception{
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 3862;BA.debugLine="Sub SetBVFFelder(cbBesonderhEinsteigen As CheckBox";
 //BA.debugLineNum = 3883;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 3884;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 3886;BA.debugLine="sSelect = \"SELECT * FROM AusbKontrolle \" & _";
_sselect = "SELECT * FROM AusbKontrolle "+"WHERE OID = "+BA.NumberToString(_ibvfoid);
 //BA.debugLineNum = 3889;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 3890;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3891;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3895;BA.debugLine="Try";
try { //BA.debugLineNum = 3896;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 3898;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 3899;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 3901;BA.debugLine="cbBesonderhEinsteigen.Checked = CBool(cur.GetIn";
_cbbesonderheinsteigen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesonderhEinsteigen"))));
 //BA.debugLineNum = 3902;BA.debugLine="cbEinstellen.Checked =  CBool(cur.GetInt(\"Einst";
_cbeinstellen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Einstellen"))));
 //BA.debugLineNum = 3903;BA.debugLine="cbLenkrad.Checked =  CBool(cur.GetInt(\"Lenkrad\"";
_cblenkrad.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lenkrad"))));
 //BA.debugLineNum = 3904;BA.debugLine="cbSpiegel.Checked =  CBool(cur.GetInt(\"Spiegel\"";
_cbspiegel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Spiegel"))));
 //BA.debugLineNum = 3905;BA.debugLine="cbKopfstuetze.Checked =  CBool(cur.GetInt(\"Kopf";
_cbkopfstuetze.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Kopfstuetze"))));
 //BA.debugLineNum = 3906;BA.debugLine="cbSitz.Checked =  CBool(cur.GetInt(\"Sitz\"))";
_cbsitz.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Sitz"))));
 //BA.debugLineNum = 3907;BA.debugLine="cbLenkradhaltung.Checked =  CBool(cur.GetInt(\"L";
_cblenkradhaltung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lenkradhalt"))));
 //BA.debugLineNum = 3908;BA.debugLine="cbPedale.Checked =  CBool(cur.GetInt(\"Pedale\"))";
_cbpedale.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Pedale"))));
 //BA.debugLineNum = 3909;BA.debugLine="cbGurt.Checked =  CBool(cur.GetInt(\"Gurt\"))";
_cbgurt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Gurt"))));
 //BA.debugLineNum = 3910;BA.debugLine="cbSchaltWaehlhebel.Checked =  CBool(cur.GetInt(";
_cbschaltwaehlhebel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchaltWaehlhebel"))));
 //BA.debugLineNum = 3911;BA.debugLine="cbZuendschloss.Checked =  CBool(cur.GetInt(\"Zue";
_cbzuendschloss.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Zuendschloss"))));
 //BA.debugLineNum = 3912;BA.debugLine="cbMotorAnlassen.Checked =  CBool(cur.GetInt(\"Mo";
_cbmotoranlassen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("MotorAnlassen"))));
 //BA.debugLineNum = 3913;BA.debugLine="cbAnfahrAnhalte.Checked =  CBool(cur.GetInt(\"An";
_cbanfahranhalte.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AnfahrAnhalte"))));
 //BA.debugLineNum = 3914;BA.debugLine="cbSchaltuebg.Checked =  CBool(cur.GetInt(\"Schal";
_cbschaltuebg.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Schaltuebg"))));
 //BA.debugLineNum = 3915;BA.debugLine="cbHoch1_2.Checked =  CBool(cur.GetInt(\"hoch1_2\"";
_cbhoch1_2.setChecked(_cbool(_ba,(Object)(_cur.GetInt("hoch1_2"))));
 //BA.debugLineNum = 3916;BA.debugLine="cbHoch2_3.Checked =  CBool(cur.GetInt(\"hoch2_3\"";
_cbhoch2_3.setChecked(_cbool(_ba,(Object)(_cur.GetInt("hoch2_3"))));
 //BA.debugLineNum = 3917;BA.debugLine="cbHoch3_4.Checked =  CBool(cur.GetInt(\"hoch3_4\"";
_cbhoch3_4.setChecked(_cbool(_ba,(Object)(_cur.GetInt("hoch3_4"))));
 //BA.debugLineNum = 3918;BA.debugLine="cbRunter4_3.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter4_3.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter4_3"))));
 //BA.debugLineNum = 3919;BA.debugLine="cbRunter3_2.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter3_2.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter3_2"))));
 //BA.debugLineNum = 3920;BA.debugLine="cbRunter2_1.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter2_1.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter2_1"))));
 //BA.debugLineNum = 3921;BA.debugLine="cbRunter4_2.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter4_2.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter4_2"))));
 //BA.debugLineNum = 3922;BA.debugLine="cbRunter4_1.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter4_1.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter4_1"))));
 //BA.debugLineNum = 3923;BA.debugLine="cbRunter3_1.Checked =  CBool(cur.GetInt(\"runter";
_cbrunter3_1.setChecked(_cbool(_ba,(Object)(_cur.GetInt("runter3_1"))));
 //BA.debugLineNum = 3924;BA.debugLine="cbLenkuebung.Checked =  CBool(cur.GetInt(\"Lenku";
_cblenkuebung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lenkuebg"))));
 //BA.debugLineNum = 3925;BA.debugLine="cbUmkehren.Checked =  CBool(cur.GetInt(\"Umkehre";
_cbumkehren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Umkehren"))));
 //BA.debugLineNum = 3926;BA.debugLine="cbEinparkenLaengs.Checked =  CBool(cur.GetInt(\"";
_cbeinparkenlaengs.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinpLaengs"))));
 //BA.debugLineNum = 3927;BA.debugLine="cbLVorwaertsRechts.Checked =  CBool(cur.GetInt(";
_cblvorwaertsrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LVorwRechts"))));
 //BA.debugLineNum = 3928;BA.debugLine="cbLRueckwaertsLinks.Checked =  CBool(cur.GetInt";
_cblrueckwaertslinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LRueckwLinks"))));
 //BA.debugLineNum = 3929;BA.debugLine="cbLRueckwaertsRechts.Checked =  CBool(cur.GetIn";
_cblrueckwaertsrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LRueckwRechts"))));
 //BA.debugLineNum = 3930;BA.debugLine="cbLVorwaertsLinks.Checked =  CBool(cur.GetInt(\"";
_cblvorwaertslinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LVorwLinks"))));
 //BA.debugLineNum = 3931;BA.debugLine="cbRueckwaertsfahren.Checked =  CBool(cur.GetInt";
_cbrueckwaertsfahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Rueckfahren"))));
 //BA.debugLineNum = 3932;BA.debugLine="cbEinparkenQuer.Checked =  CBool(cur.GetInt(\"Ei";
_cbeinparkenquer.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinpQuer"))));
 //BA.debugLineNum = 3933;BA.debugLine="cbQVorwaertsRechts.Checked =  CBool(cur.GetInt(";
_cbqvorwaertsrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("QVvorwRechts"))));
 //BA.debugLineNum = 3934;BA.debugLine="cbQRueckwaertsLinks.Checked =  CBool(cur.GetInt";
_cbqrueckwaertslinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("QRueckwLinks"))));
 //BA.debugLineNum = 3935;BA.debugLine="cbQRueckwaertsRechts.Checked =  CBool(cur.GetIn";
_cbqrueckwaertsrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("QRueckwRechts"))));
 //BA.debugLineNum = 3936;BA.debugLine="cbQVorwaertsLinks.Checked =  CBool(cur.GetInt(\"";
_cbqvorwaertslinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("QVorwLinks"))));
 //BA.debugLineNum = 3937;BA.debugLine="cbGefahrbremsung.Checked =  CBool(cur.GetInt(\"G";
_cbgefahrbremsung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Gefahrbrems"))));
 //BA.debugLineNum = 3938;BA.debugLine="cbRollenSchalten.Checked =  CBool(cur.GetInt(\"R";
_cbrollenschalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RollenUndSchalten"))));
 //BA.debugLineNum = 3939;BA.debugLine="cbBremsSchalten.Checked =  CBool(cur.GetInt(\"Ab";
_cbbremsschalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbbrUndSchalten"))));
 //BA.debugLineNum = 3940;BA.debugLine="cbBremsuebung.Checked =  CBool(cur.GetInt(\"Brem";
_cbbremsuebung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Bremsuebung"))));
 //BA.debugLineNum = 3941;BA.debugLine="cbDegressiv.Checked =  CBool(cur.GetInt(\"degres";
_cbdegressiv.setChecked(_cbool(_ba,(Object)(_cur.GetInt("degressiv"))));
 //BA.debugLineNum = 3942;BA.debugLine="cbZielbremsung.Checked =  CBool(cur.GetInt(\"Zie";
_cbzielbremsung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Zielbremsung"))));
 //BA.debugLineNum = 3943;BA.debugLine="cbGefahrsituation.Checked =  CBool(cur.GetInt(\"";
_cbgefahrsituation.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GefahrSituation"))));
 //BA.debugLineNum = 3944;BA.debugLine="cbGefaelle.Checked =  CBool(cur.GetInt(\"Gefaell";
_cbgefaelle.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Gefaelle"))));
 //BA.debugLineNum = 3945;BA.debugLine="cbAnhalten.Checked =  CBool(cur.GetInt(\"GeAnhal";
_cbanhalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeAnhalten"))));
 //BA.debugLineNum = 3946;BA.debugLine="cbAnfahren.Checked =  CBool(cur.GetInt(\"GeAnfah";
_cbanfahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeAnfahren"))));
 //BA.debugLineNum = 3947;BA.debugLine="cbRueckwaerts.Checked =  CBool(cur.GetInt(\"GeRu";
_cbrueckwaerts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeRueckw"))));
 //BA.debugLineNum = 3948;BA.debugLine="cbSichern.Checked =  CBool(cur.GetInt(\"GeSicher";
_cbsichern.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeSichern"))));
 //BA.debugLineNum = 3949;BA.debugLine="cbSchalten.Checked =  CBool(cur.GetInt(\"GeSchal";
_cbschalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeSchalten"))));
 //BA.debugLineNum = 3950;BA.debugLine="cbSteigung.Checked =  CBool(cur.GetInt(\"Steigun";
_cbsteigung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Steigung"))));
 //BA.debugLineNum = 3951;BA.debugLine="cbStAnhalten.Checked =  CBool(cur.GetInt(\"StAnh";
_cbstanhalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("StAnhalten"))));
 //BA.debugLineNum = 3952;BA.debugLine="cbStAnfahren.Checked =  CBool(cur.GetInt(\"StAnf";
_cbstanfahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("StAnfahren"))));
 //BA.debugLineNum = 3953;BA.debugLine="cbStRueckwaerts.Checked =  CBool(cur.GetInt(\"St";
_cbstrueckwaerts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("StRueckw"))));
 //BA.debugLineNum = 3954;BA.debugLine="cbStSichern.Checked =  CBool(cur.GetInt(\"StSich";
_cbstsichern.setChecked(_cbool(_ba,(Object)(_cur.GetInt("StSichern"))));
 //BA.debugLineNum = 3955;BA.debugLine="cbStSchalten.Checked =  CBool(cur.GetInt(\"StSch";
_cbstschalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("StSchalten"))));
 //BA.debugLineNum = 3956;BA.debugLine="cbTastgeschw.Checked =  CBool(cur.GetInt(\"Tastg";
_cbtastgeschw.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Tastgeschw"))));
 //BA.debugLineNum = 3957;BA.debugLine="cbBedienKontroll.Checked =  CBool(cur.GetInt(\"B";
_cbbedienkontroll.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BedienungKontrolle"))));
 //BA.debugLineNum = 3958;BA.debugLine="cbOertlichBesonder.Checked =  CBool(cur.GetInt(";
_cboertlichbesonder.setChecked(_cbool(_ba,(Object)(_cur.GetInt("OertlicheBesonder"))));
 //BA.debugLineNum = 3959;BA.debugLine="cbFahrbahnbenutzung.Checked =  CBool(cur.GetInt";
_cbfahrbahnbenutzung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrbahnNutz"))));
 //BA.debugLineNum = 3960;BA.debugLine="cbEinordnen.Checked =  CBool(cur.GetInt(\"Einord";
_cbeinordnen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Einordnen"))));
 //BA.debugLineNum = 3961;BA.debugLine="cbMarkierungen.Checked =  CBool(cur.GetInt(\"Mar";
_cbmarkierungen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Markierungen"))));
 //BA.debugLineNum = 3962;BA.debugLine="cbFahrstreifenwechsel.Checked =  CBool(cur.GetI";
_cbfahrstreifenwechsel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrstreifenWechsel"))));
 //BA.debugLineNum = 3963;BA.debugLine="cbLinks.Checked =  CBool(cur.GetInt(\"FahrLinks\"";
_cblinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrLinks"))));
 //BA.debugLineNum = 3964;BA.debugLine="cbRechts.Checked =  CBool(cur.GetInt(\"FahrRecht";
_cbrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrRechts"))));
 //BA.debugLineNum = 3965;BA.debugLine="cbVorbeifUeberholen.Checked =  CBool(cur.GetInt";
_cbvorbeifueberholen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Vorbeifahren"))));
 //BA.debugLineNum = 3966;BA.debugLine="cbAbbiegen.Checked =  CBool(cur.GetInt(\"Abbiege";
_cbabbiegen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Abbiegen"))));
 //BA.debugLineNum = 3967;BA.debugLine="cbABRechts.Checked =  CBool(cur.GetInt(\"AbbRech";
_cbabrechts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbbRechts"))));
 //BA.debugLineNum = 3968;BA.debugLine="cbABLinks.Checked =  CBool(cur.GetInt(\"AbbLinks";
_cbablinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbbLinks"))));
 //BA.debugLineNum = 3969;BA.debugLine="cbMehrspurig.Checked =  CBool(cur.GetInt(\"FahrM";
_cbmehrspurig.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrMehrSp"))));
 //BA.debugLineNum = 3970;BA.debugLine="cbRadweg.Checked =  CBool(cur.GetInt(\"FahrRadwe";
_cbradweg.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrRadweg"))));
 //BA.debugLineNum = 3971;BA.debugLine="cbSonderstreifen.Checked =  CBool(cur.GetInt(\"F";
_cbsonderstreifen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrSonderStr"))));
 //BA.debugLineNum = 3972;BA.debugLine="cbStrassenbahn.Checked =  CBool(cur.GetInt(\"Fah";
_cbstrassenbahn.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrStrassenb"))));
 //BA.debugLineNum = 3973;BA.debugLine="cbEinbahnstrasse.Checked =  CBool(cur.GetInt(\"F";
_cbeinbahnstrasse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrEinbahnstr"))));
 //BA.debugLineNum = 3974;BA.debugLine="cbVorfahrt.Checked =  CBool(cur.GetInt(\"Vorfahr";
_cbvorfahrt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Vorfahrt"))));
 //BA.debugLineNum = 3975;BA.debugLine="cbRechtsVorLinks.Checked =  CBool(cur.GetInt(\"R";
_cbrechtsvorlinks.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RechtsVorLinks"))));
 //BA.debugLineNum = 3976;BA.debugLine="cbGruenpfeil.Checked =  CBool(cur.GetInt(\"Gruen";
_cbgruenpfeil.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Gruenpfeil"))));
 //BA.debugLineNum = 3977;BA.debugLine="cbPolizeibeamte.Checked =  CBool(cur.GetInt(\"Po";
_cbpolizeibeamte.setChecked(_cbool(_ba,(Object)(_cur.GetInt("PolizeiBeamte"))));
 //BA.debugLineNum = 3978;BA.debugLine="cbGruenpfeilSchild.Checked =  CBool(cur.GetInt(";
_cbgruenpfeilschild.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GruenpfeilSchild"))));
 //BA.debugLineNum = 3979;BA.debugLine="cbGeschwAbstand.Checked =  CBool(cur.GetInt(\"Ge";
_cbgeschwabstand.setChecked(_cbool(_ba,(Object)(_cur.GetInt("GeschwAbstand"))));
 //BA.debugLineNum = 3980;BA.debugLine="cbSituationVerkehrstn.Checked =  CBool(cur.GetI";
_cbsituationverkehrstn.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SituationTN"))));
 //BA.debugLineNum = 3981;BA.debugLine="cbFussgaengerueberweg.Checked =  CBool(cur.GetI";
_cbfussgaengerueberweg.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Fussgaenger"))));
 //BA.debugLineNum = 3982;BA.debugLine="cbOeffentlVerkehrsm.Checked =  CBool(cur.GetInt";
_cboeffentlverkehrsm.setChecked(_cbool(_ba,(Object)(_cur.GetInt("OeffentVKTN"))));
 //BA.debugLineNum = 3983;BA.debugLine="cbAeltereBehinderte.Checked =  CBool(cur.GetInt";
_cbaelterebehinderte.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AeltereBehind"))));
 //BA.debugLineNum = 3984;BA.debugLine="cbEinbahnstrRadfahrer.Checked =  CBool(cur.GetI";
_cbeinbahnstrradfahrer.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinbahnStrRadf"))));
 //BA.debugLineNum = 3985;BA.debugLine="cbKinder.Checked =  CBool(cur.GetInt(\"Kinder\"))";
_cbkinder.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Kinder"))));
 //BA.debugLineNum = 3986;BA.debugLine="cbSchulbus.Checked =  CBool(cur.GetInt(\"Schulbu";
_cbschulbus.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Schulbus"))));
 //BA.debugLineNum = 3987;BA.debugLine="cbRadfahrerMofa.Checked =  CBool(cur.GetInt(\"Ra";
_cbradfahrermofa.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RadfMofa"))));
 //BA.debugLineNum = 3988;BA.debugLine="cbVerkehrsberuhigt.Checked =  CBool(cur.GetInt(";
_cbverkehrsberuhigt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VerkBeruhigt"))));
 //BA.debugLineNum = 3989;BA.debugLine="cbSchwierigeVerkehrsf.Checked =  CBool(cur.GetI";
_cbschwierigeverkehrsf.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchwierigeVKFuehrung"))));
 //BA.debugLineNum = 3990;BA.debugLine="cbEngpass.Checked =  CBool(cur.GetInt(\"Engpass\"";
_cbengpass.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Engpass"))));
 //BA.debugLineNum = 3991;BA.debugLine="cbKreisverkehr.Checked =  CBool(cur.GetInt(\"Kre";
_cbkreisverkehr.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Kreisverkehr"))));
 //BA.debugLineNum = 3992;BA.debugLine="cbBahnuebergangWarte.Checked =  CBool(cur.GetIn";
_cbbahnuebergangwarte.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Bahnuebergang"))));
 //BA.debugLineNum = 3993;BA.debugLine="cbKritischeVerkehrss.Checked =  CBool(cur.GetIn";
_cbkritischeverkehrss.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KritischeVKSit"))));
 //BA.debugLineNum = 3994;BA.debugLine="cbHauptverkehrszt.Checked =  CBool(cur.GetInt(\"";
_cbhauptverkehrszt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("HauptVKZeit"))));
 //BA.debugLineNum = 3995;BA.debugLine="cbPartnerVerhalten.Checked =  CBool(cur.GetInt(";
_cbpartnerverhalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("PartnerVerhalten"))));
 //BA.debugLineNum = 3996;BA.debugLine="cbSchwungNutzen.Checked =  CBool(cur.GetInt(\"Sc";
_cbschwungnutzen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchwungNutzen"))));
 //BA.debugLineNum = 3997;BA.debugLine="cbFussgaengerSchutzb.Checked =  CBool(cur.GetIn";
_cbfussgaengerschutzb.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FussgaengerSchutz"))));
 //BA.debugLineNum = 3998;BA.debugLine="cbAngepassteGeschw.Checked =  CBool(cur.GetInt(";
_cbangepasstegeschw.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AngepGeschw"))));
 //BA.debugLineNum = 3999;BA.debugLine="cbAbstand.Checked =  CBool(cur.GetInt(\"Abstand\"";
_cbabstand.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Abstand"))));
 //BA.debugLineNum = 4000;BA.debugLine="cbULVorne.Checked =  CBool(cur.GetInt(\"AbstVorn";
_cbulvorne.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbstVorne"))));
 //BA.debugLineNum = 4001;BA.debugLine="cbULHinten.Checked =  CBool(cur.GetInt(\"AbstHin";
_cbulhinten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbstHinten"))));
 //BA.debugLineNum = 4002;BA.debugLine="cbULSeitlich.Checked =  CBool(cur.GetInt(\"AbstS";
_cbulseitlich.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbstSeite"))));
 //BA.debugLineNum = 4003;BA.debugLine="cbBeobachtSpiegel.Checked =  CBool(cur.GetInt(\"";
_cbbeobachtspiegel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BeobSpiegel"))));
 //BA.debugLineNum = 4004;BA.debugLine="cbVerkehrszeichen.Checked =  CBool(cur.GetInt(\"";
_cbverkehrszeichen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VerkehrsZeich"))));
 //BA.debugLineNum = 4005;BA.debugLine="cbKreuzungEinmuend.Checked =  CBool(cur.GetInt(";
_cbkreuzungeinmuend.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KreuzMuend"))));
 //BA.debugLineNum = 4006;BA.debugLine="cbKurven.Checked =  CBool(cur.GetInt(\"Kurven\"))";
_cbkurven.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Kurven"))));
 //BA.debugLineNum = 4007;BA.debugLine="cbSteigungen.Checked =  CBool(cur.GetInt(\"Steig";
_cbsteigungen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Steigungen"))));
 //BA.debugLineNum = 4008;BA.debugLine="cbULGefaelle.Checked =  CBool(cur.GetInt(\"BesGe";
_cbulgefaelle.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesGefaelle"))));
 //BA.debugLineNum = 4009;BA.debugLine="cbAlleen.Checked =  CBool(cur.GetInt(\"Alleen\"))";
_cballeen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Alleen"))));
 //BA.debugLineNum = 4010;BA.debugLine="cbUeberholen.Checked =  CBool(cur.GetInt(\"Ueber";
_cbueberholen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Ueberholen"))));
 //BA.debugLineNum = 4011;BA.debugLine="cbBesondereSituat.Checked =  CBool(cur.GetInt(\"";
_cbbesonderesituat.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesSituation"))));
 //BA.debugLineNum = 4012;BA.debugLine="cbLiegenblSichern.Checked =  CBool(cur.GetInt(\"";
_cbliegenblsichern.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LiegeblAbsicher"))));
 //BA.debugLineNum = 4013;BA.debugLine="cbEinfahrenOrtsch.Checked =  CBool(cur.GetInt(\"";
_cbeinfahrenortsch.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinfOrtschaft"))));
 //BA.debugLineNum = 4014;BA.debugLine="cbFussgaenger.Checked =  CBool(cur.GetInt(\"BesF";
_cbfussgaenger.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesFussgaenger"))));
 //BA.debugLineNum = 4015;BA.debugLine="cbWildTiere.Checked =  CBool(cur.GetInt(\"WildTi";
_cbwildtiere.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WildTiere"))));
 //BA.debugLineNum = 4016;BA.debugLine="cbBesondereAnford.Checked =  CBool(cur.GetInt(\"";
_cbbesondereanford.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesAnforderung"))));
 //BA.debugLineNum = 4017;BA.debugLine="cbLeistungsgrenze.Checked =  CBool(cur.GetInt(\"";
_cbleistungsgrenze.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Leistungsgr"))));
 //BA.debugLineNum = 4018;BA.debugLine="cbOrientierung.Checked =  CBool(cur.GetInt(\"Ori";
_cborientierung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Orientierung"))));
 //BA.debugLineNum = 4019;BA.debugLine="cbAblenkung.Checked =  CBool(cur.GetInt(\"Ablenk";
_cbablenkung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Ablenkung"))));
 //BA.debugLineNum = 4020;BA.debugLine="cbFahrtplanung.Checked =  CBool(cur.GetInt(\"Fah";
_cbfahrtplanung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Fahrtplanung"))));
 //BA.debugLineNum = 4021;BA.debugLine="cbEinfahrtAB.Checked =  CBool(cur.GetInt(\"Einfa";
_cbeinfahrtab.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinfahBAB"))));
 //BA.debugLineNum = 4022;BA.debugLine="cbABFahrbahnwechsel.Checked =  CBool(cur.GetInt";
_cbabfahrbahnwechsel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrStrWechsel"))));
 //BA.debugLineNum = 4023;BA.debugLine="cbGeschwindigkeit.Checked =  CBool(cur.GetInt(\"";
_cbgeschwindigkeit.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Geschwindig"))));
 //BA.debugLineNum = 4024;BA.debugLine="cbABAbstand.Checked =  CBool(cur.GetInt(\"Abstan";
_cbababstand.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbstandBAB"))));
 //BA.debugLineNum = 4025;BA.debugLine="cbABVorne.Checked =  CBool(cur.GetInt(\"VorneBAB";
_cbabvorne.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VorneBAB"))));
 //BA.debugLineNum = 4026;BA.debugLine="cbABHinten.Checked =  CBool(cur.GetInt(\"HintenB";
_cbabhinten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("HintenBAB"))));
 //BA.debugLineNum = 4027;BA.debugLine="cbABSeitlich.Checked =  CBool(cur.GetInt(\"Seite";
_cbabseitlich.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SeiteBAB"))));
 //BA.debugLineNum = 4028;BA.debugLine="cbABUeberholen.Checked =  CBool(cur.GetInt(\"BAB";
_cbabueberholen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BABUeberholen"))));
 //BA.debugLineNum = 4029;BA.debugLine="cbSchilder.Checked =  CBool(cur.GetInt(\"SchildM";
_cbschilder.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchildMarkierung"))));
 //BA.debugLineNum = 4030;BA.debugLine="cbVorbeifahren.Checked =  CBool(cur.GetInt(\"Voe";
_cbvorbeifahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VoebeifAnschluss"))));
 //BA.debugLineNum = 4031;BA.debugLine="cbRastParkTank.Checked =  CBool(cur.GetInt(\"Ras";
_cbrastparktank.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RastParkTank"))));
 //BA.debugLineNum = 4032;BA.debugLine="cbVerhUnfall.Checked =  CBool(cur.GetInt(\"Verha";
_cbverhunfall.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VerhaltenUnfall"))));
 //BA.debugLineNum = 4033;BA.debugLine="cbDichterVerkehr.Checked =  CBool(cur.GetInt(\"D";
_cbdichterverkehr.setChecked(_cbool(_ba,(Object)(_cur.GetInt("DichtStau"))));
 //BA.debugLineNum = 4034;BA.debugLine="cbBesonderSituat.Checked =  CBool(cur.GetInt(\"B";
_cbbesondersituat.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesSitBAB"))));
 //BA.debugLineNum = 4035;BA.debugLine="cbBesonderAnford.Checked =  CBool(cur.GetInt(\"B";
_cbbesonderanford.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesAnfordBAB"))));
 //BA.debugLineNum = 4036;BA.debugLine="cbABLeistungsgrenze.Checked =  CBool(cur.GetInt";
_cbableistungsgrenze.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LeistungsgrBAB"))));
 //BA.debugLineNum = 4037;BA.debugLine="cbKonfliktSitua.Checked =  CBool(cur.GetInt(\"Ko";
_cbkonfliktsitua.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KonfliktBAB"))));
 //BA.debugLineNum = 4038;BA.debugLine="cbABAblenkung.Checked =  CBool(cur.GetInt(\"Able";
_cbabablenkung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AblenkungBAB"))));
 //BA.debugLineNum = 4039;BA.debugLine="cbVerlassenBAB.Checked =  CBool(cur.GetInt(\"Ver";
_cbverlassenbab.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VerlassBAB"))));
 //BA.debugLineNum = 4040;BA.debugLine="cbBeleuchtung.Checked =  CBool(cur.GetInt(\"Bele";
_cbbeleuchtung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Beleuchtung"))));
 //BA.debugLineNum = 4041;BA.debugLine="cbKontrolle.Checked =  CBool(cur.GetInt(\"Kontro";
_cbkontrolle.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KontrolleBel"))));
 //BA.debugLineNum = 4042;BA.debugLine="cbEinstell.Checked =  CBool(cur.GetInt(\"Einstel";
_cbeinstell.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinstellBel"))));
 //BA.debugLineNum = 4043;BA.debugLine="cbBenutzung.Checked =  CBool(cur.GetInt(\"Benutz";
_cbbenutzung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BenutzBel"))));
 //BA.debugLineNum = 4044;BA.debugLine="cbFernlicht.Checked =  CBool(cur.GetInt(\"Fernli";
_cbfernlicht.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FernlichtBel"))));
 //BA.debugLineNum = 4045;BA.debugLine="cbBeleuchtStrasse.Checked =  CBool(cur.GetInt(\"";
_cbbeleuchtstrasse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BelStrasse"))));
 //BA.debugLineNum = 4046;BA.debugLine="cbUnbeleuchtStrasse.Checked =  CBool(cur.GetInt";
_cbunbeleuchtstrasse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("UnbelStrasse"))));
 //BA.debugLineNum = 4047;BA.debugLine="cbParken.Checked =  CBool(cur.GetInt(\"Parken\"))";
_cbparken.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Parken"))));
 //BA.debugLineNum = 4048;BA.debugLine="cbDUBesonderSituat.Checked =  CBool(cur.GetInt(";
_cbdubesondersituat.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesSitDunkel"))));
 //BA.debugLineNum = 4049;BA.debugLine="cbSchlechteWitterung.Checked =  CBool(cur.GetIn";
_cbschlechtewitterung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchlechWittDun"))));
 //BA.debugLineNum = 4050;BA.debugLine="cbTiere.Checked =  CBool(cur.GetInt(\"TiereDun\")";
_cbtiere.setChecked(_cbool(_ba,(Object)(_cur.GetInt("TiereDun"))));
 //BA.debugLineNum = 4051;BA.debugLine="cbBahnuebergaenge.Checked =  CBool(cur.GetInt(\"";
_cbbahnuebergaenge.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BahnueberDun"))));
 //BA.debugLineNum = 4052;BA.debugLine="cbUnbelVerkehrTN.Checked =  CBool(cur.GetInt(\"U";
_cbunbelverkehrtn.setChecked(_cbool(_ba,(Object)(_cur.GetInt("UnbelVKTN"))));
 //BA.debugLineNum = 4053;BA.debugLine="cbDUBesonderAnfor.Checked =  CBool(cur.GetInt(\"";
_cbdubesonderanfor.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BesAnforDun"))));
 //BA.debugLineNum = 4054;BA.debugLine="cbBlendung.Checked =  CBool(cur.GetInt(\"Blendun";
_cbblendung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Blendung"))));
 //BA.debugLineNum = 4055;BA.debugLine="cbDUOrientierung.Checked =  CBool(cur.GetInt(\"O";
_cbduorientierung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("OrientDun"))));
 //BA.debugLineNum = 4056;BA.debugLine="cbAbschlussbesp.Checked =  CBool(cur.GetInt(\"Ab";
_cbabschlussbesp.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AbschlussbespDun"))));
 //BA.debugLineNum = 4057;BA.debugLine="cbSelbstFahren.Checked =  CBool(cur.GetInt(\"Sel";
_cbselbstfahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SelbstFahren"))));
 //BA.debugLineNum = 4058;BA.debugLine="cbInnerorts.Checked =  CBool(cur.GetInt(\"Innero";
_cbinnerorts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Innerorts"))));
 //BA.debugLineNum = 4059;BA.debugLine="cbAusserorts.Checked =  CBool(cur.GetInt(\"Ausse";
_cbausserorts.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Ausserorts"))));
 //BA.debugLineNum = 4060;BA.debugLine="cbVerantwFahren.Checked =  CBool(cur.GetInt(\"Ve";
_cbverantwfahren.setChecked(_cbool(_ba,(Object)(_cur.GetInt("VerantwFahren"))));
 //BA.debugLineNum = 4061;BA.debugLine="cbTestfPruef.Checked =  CBool(cur.GetInt(\"Testf";
_cbtestfpruef.setChecked(_cbool(_ba,(Object)(_cur.GetInt("TestfPruefung"))));
 //BA.debugLineNum = 4062;BA.debugLine="cbFAKT.Checked =  CBool(cur.GetInt(\"FAKT\"))";
_cbfakt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FAKT"))));
 //BA.debugLineNum = 4063;BA.debugLine="cbAndere.Checked =  CBool(cur.GetInt(\"andere\"))";
_cbandere.setChecked(_cbool(_ba,(Object)(_cur.GetInt("andere"))));
 //BA.debugLineNum = 4064;BA.debugLine="cbWiederholung.Checked =  CBool(cur.GetInt(\"Wie";
_cbwiederholung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WiederhVertief"))));
 //BA.debugLineNum = 4065;BA.debugLine="cbLeistungsbew.Checked =  CBool(cur.GetInt(\"Lei";
_cbleistungsbew.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Leistungsbew"))));
 //BA.debugLineNum = 4066;BA.debugLine="cbReifen.Checked =  CBool(cur.GetInt(\"Reifen\"))";
_cbreifen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Reifen"))));
 //BA.debugLineNum = 4067;BA.debugLine="cbEinAusschalten.Checked =  CBool(cur.GetInt(\"E";
_cbeinausschalten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinAusschalten"))));
 //BA.debugLineNum = 4068;BA.debugLine="cbFunktionPruefen.Checked =  CBool(cur.GetInt(\"";
_cbfunktionpruefen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FunkPruefen"))));
 //BA.debugLineNum = 4069;BA.debugLine="cbStandlicht.Checked =  CBool(cur.GetInt(\"Stand";
_cbstandlicht.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Standlicht"))));
 //BA.debugLineNum = 4070;BA.debugLine="cbNebelschluss.Checked =  CBool(cur.GetInt(\"Neb";
_cbnebelschluss.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Nebelschl"))));
 //BA.debugLineNum = 4071;BA.debugLine="cbBlinker.Checked =  CBool(cur.GetInt(\"Blinker\"";
_cbblinker.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Blinker"))));
 //BA.debugLineNum = 4072;BA.debugLine="cbAbblendlicht.Checked =  CBool(cur.GetInt(\"Abb";
_cbabblendlicht.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Abblendlicht"))));
 //BA.debugLineNum = 4073;BA.debugLine="cbWarnblicke.Checked =  CBool(cur.GetInt(\"Warnb";
_cbwarnblicke.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Warnblick"))));
 //BA.debugLineNum = 4074;BA.debugLine="cbHupe.Checked =  CBool(cur.GetInt(\"Hupe\"))";
_cbhupe.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Hupe"))));
 //BA.debugLineNum = 4075;BA.debugLine="cbBSFernlicht.Checked =  CBool(cur.GetInt(\"Fern";
_cbbsfernlicht.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Fernlicht"))));
 //BA.debugLineNum = 4076;BA.debugLine="cbSchlussLeuchte.Checked =  CBool(cur.GetInt(\"S";
_cbschlussleuchte.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchlussLeuchte"))));
 //BA.debugLineNum = 4077;BA.debugLine="cbBremslicht.Checked =  CBool(cur.GetInt(\"Brems";
_cbbremslicht.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Bremslicht"))));
 //BA.debugLineNum = 4078;BA.debugLine="cbKontrollLBenenn.Checked =  CBool(cur.GetInt(\"";
_cbkontrolllbenenn.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KontrollLeuchten"))));
 //BA.debugLineNum = 4079;BA.debugLine="cbRueckstrahler.Checked =  CBool(cur.GetInt(\"Ru";
_cbrueckstrahler.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Rueckstrahler"))));
 //BA.debugLineNum = 4080;BA.debugLine="cbVorhandensein.Checked =  CBool(cur.GetInt(\"Vo";
_cbvorhandensein.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Vorhanden"))));
 //BA.debugLineNum = 4081;BA.debugLine="cbBeschaedigung.Checked =  CBool(cur.GetInt(\"Be";
_cbbeschaedigung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Beschaedigt"))));
 //BA.debugLineNum = 4082;BA.debugLine="cbLenkung.Checked =  CBool(cur.GetInt(\"Lenkung\"";
_cblenkung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lenkung"))));
 //BA.debugLineNum = 4083;BA.debugLine="cbLenkschlEntriegeln.Checked =  CBool(cur.GetIn";
_cblenkschlentriegeln.setChecked(_cbool(_ba,(Object)(_cur.GetInt("LenkschlEntriegel"))));
 //BA.debugLineNum = 4084;BA.debugLine="cbPruefLenkSpiel.Checked =  CBool(cur.GetInt(\"L";
_cbprueflenkspiel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lenkspiel"))));
 //BA.debugLineNum = 4085;BA.debugLine="cbFunktBremse.Checked =  CBool(cur.GetInt(\"Funk";
_cbfunktbremse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FunkBremsen"))));
 //BA.debugLineNum = 4086;BA.debugLine="cbBetriebsBremse.Checked =  CBool(cur.GetInt(\"B";
_cbbetriebsbremse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BetriebBremse"))));
 //BA.debugLineNum = 4087;BA.debugLine="cbFeststellBremse.Checked =  CBool(cur.GetInt(\"";
_cbfeststellbremse.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FeststellBremse"))));
 //BA.debugLineNum = 4088;BA.debugLine="cbRichtigSitz.Checked =  CBool(cur.GetInt(\"Sitz";
_cbrichtigsitz.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Sitzeinstellung"))));
 //BA.debugLineNum = 4089;BA.debugLine="cbEinstellRueckspiegel.Checked =  CBool(cur.Get";
_cbeinstellrueckspiegel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinstRueckspiegel"))));
 //BA.debugLineNum = 4090;BA.debugLine="cbEinKopfstuetze.Checked =  CBool(cur.GetInt(\"E";
_cbeinkopfstuetze.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinstKopf"))));
 //BA.debugLineNum = 4091;BA.debugLine="cbEinLenkrad.Checked =  CBool(cur.GetInt(\"Einst";
_cbeinlenkrad.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EinstLenksrad"))));
 //BA.debugLineNum = 4092;BA.debugLine="cbAnlegenGurt.Checked =  CBool(cur.GetInt(\"Anle";
_cbanlegengurt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AnlegenGurt"))));
 //BA.debugLineNum = 4093;BA.debugLine="cbBedienenAgg.Checked =  CBool(cur.GetInt(\"Bedi";
_cbbedienenagg.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BedienHeizung"))));
 //BA.debugLineNum = 4094;BA.debugLine="cbHeizung.Checked =  CBool(cur.GetInt(\"Heizung\"";
_cbheizung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Heizung"))));
 //BA.debugLineNum = 4095;BA.debugLine="cbHeckHeizung.Checked =  CBool(cur.GetInt(\"Heck";
_cbheckheizung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("HeckHeizung"))));
 //BA.debugLineNum = 4096;BA.debugLine="cbBehSonderaus.Checked =  CBool(cur.GetInt(\"Beh";
_cbbehsonderaus.setChecked(_cbool(_ba,(Object)(_cur.GetInt("BehSonderAusstg"))));
 //BA.debugLineNum = 4097;BA.debugLine="cbLueftung.Checked =  CBool(cur.GetInt(\"Lueftun";
_cblueftung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Lueftung"))));
 //BA.debugLineNum = 4098;BA.debugLine="cbKlimaanlage.Checked =  CBool(cur.GetInt(\"Klim";
_cbklimaanlage.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Klima"))));
 //BA.debugLineNum = 4099;BA.debugLine="cbEnergieNutzung.Checked =  CBool(cur.GetInt(\"E";
_cbenergienutzung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("EnergiespNutz"))));
 //BA.debugLineNum = 4100;BA.debugLine="cbKeineUnnVerbr.Checked =  CBool(cur.GetInt(\"Ke";
_cbkeineunnverbr.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KeineUnnVerbr"))));
 //BA.debugLineNum = 4101;BA.debugLine="cbRechtztAbsch.Checked =  CBool(cur.GetInt(\"Rec";
_cbrechtztabsch.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RechtzAbschalten"))));
 //BA.debugLineNum = 4102;BA.debugLine="cbMotorraum.Checked =  CBool(cur.GetInt(\"CheckM";
_cbmotorraum.setChecked(_cbool(_ba,(Object)(_cur.GetInt("CheckMotorraum"))));
 //BA.debugLineNum = 4103;BA.debugLine="cbMotoroel.Checked =  CBool(cur.GetInt(\"Motoroe";
_cbmotoroel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Motoroel"))));
 //BA.debugLineNum = 4104;BA.debugLine="cbKuehlmittel.Checked =  CBool(cur.GetInt(\"Kueh";
_cbkuehlmittel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Kuehlmittel"))));
 //BA.debugLineNum = 4105;BA.debugLine="cbScheibenwaschm.Checked =  CBool(cur.GetInt(\"W";
_cbscheibenwaschm.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Wischwasser"))));
 //BA.debugLineNum = 4106;BA.debugLine="cbTanken.Checked =  CBool(cur.GetInt(\"Tanken\"))";
_cbtanken.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Tanken"))));
 //BA.debugLineNum = 4107;BA.debugLine="cbSicherungsmittel.Checked =  CBool(cur.GetInt(";
_cbsicherungsmittel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SicherungsMittel"))));
 //BA.debugLineNum = 4108;BA.debugLine="cbWarndreieck.Checked =  CBool(cur.GetInt(\"Warn";
_cbwarndreieck.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WarnDreieck"))));
 //BA.debugLineNum = 4109;BA.debugLine="cbBordwerkzeug.Checked =  CBool(cur.GetInt(\"Bor";
_cbbordwerkzeug.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Bordwerkzeug"))));
 //BA.debugLineNum = 4110;BA.debugLine="cbZusaetzlichAus.Checked =  CBool(cur.GetInt(\"z";
_cbzusaetzlichaus.setChecked(_cbool(_ba,(Object)(_cur.GetInt("zusaetzAusruest"))));
 //BA.debugLineNum = 4111;BA.debugLine="cbVerbandskasten.Checked =  CBool(cur.GetInt(\"V";
_cbverbandskasten.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Verbandskasten"))));
 //BA.debugLineNum = 4112;BA.debugLine="cbAussenkontrolle.Checked =  CBool(cur.GetInt(\"";
_cbaussenkontrolle.setChecked(_cbool(_ba,(Object)(_cur.GetInt("AussenKontrolle"))));
 //BA.debugLineNum = 4113;BA.debugLine="cbScheibenWischer.Checked =  CBool(cur.GetInt(\"";
_cbscheibenwischer.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Scheiben"))));
 //BA.debugLineNum = 4114;BA.debugLine="cbKennzeichen.Checked =  CBool(cur.GetInt(\"Kenn";
_cbkennzeichen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KennzeichenHUAU"))));
 //BA.debugLineNum = 4115;BA.debugLine="cbCheckSpiegel.Checked =  CBool(cur.GetInt(\"Che";
_cbcheckspiegel.setChecked(_cbool(_ba,(Object)(_cur.GetInt("CheckSpiegel"))));
 //BA.debugLineNum = 4116;BA.debugLine="cbCheckBeleuchtung.Checked =  CBool(cur.GetInt(";
_cbcheckbeleuchtung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("CheckBeleuchtung"))));
 //BA.debugLineNum = 4117;BA.debugLine="cbBremsen.Checked =  CBool(cur.GetInt(\"Bremsung";
_cbbremsen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Bremsung"))));
 //BA.debugLineNum = 4118;BA.debugLine="cbLadung.Checked =  CBool(cur.GetInt(\"Ladung\"))";
_cbladung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Ladung"))));
 //BA.debugLineNum = 4119;BA.debugLine="cbLadungssicherung.Checked =  CBool(cur.GetInt(";
_cbladungssicherung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Sicherung"))));
 //BA.debugLineNum = 4120;BA.debugLine="cbKenntlichmachung.Checked =  CBool(cur.GetInt(";
_cbkenntlichmachung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("KenntlichMachung"))));
 //BA.debugLineNum = 4121;BA.debugLine="cbFahreSchlWitt.Checked =  CBool(cur.GetInt(\"Fa";
_cbfahreschlwitt.setChecked(_cbool(_ba,(Object)(_cur.GetInt("FahrenSchelchtWetter"))));
 //BA.debugLineNum = 4122;BA.debugLine="cbWittLueftung.Checked =  CBool(cur.GetInt(\"Wit";
_cbwittlueftung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WittLueftung"))));
 //BA.debugLineNum = 4123;BA.debugLine="cbWittScheiben.Checked =  CBool(cur.GetInt(\"Sch";
_cbwittscheiben.setChecked(_cbool(_ba,(Object)(_cur.GetInt("ScheibenWischer"))));
 //BA.debugLineNum = 4124;BA.debugLine="cbRegen.Checked =  CBool(cur.GetInt(\"RegenSprue";
_cbregen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("RegenSprueh"))));
 //BA.debugLineNum = 4125;BA.debugLine="cbWasserlachen.Checked =  CBool(cur.GetInt(\"Was";
_cbwasserlachen.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Wasserlachen"))));
 //BA.debugLineNum = 4126;BA.debugLine="cbWindSturm.Checked =  CBool(cur.GetInt(\"WindSt";
_cbwindsturm.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WindSturm"))));
 //BA.debugLineNum = 4127;BA.debugLine="cbMatchSchnee.Checked =  CBool(cur.GetInt(\"Schn";
_cbmatchschnee.setChecked(_cbool(_ba,(Object)(_cur.GetInt("SchneeMatsch"))));
 //BA.debugLineNum = 4128;BA.debugLine="cbEis.Checked =  CBool(cur.GetInt(\"Eis\"))";
_cbeis.setChecked(_cbool(_ba,(Object)(_cur.GetInt("Eis"))));
 //BA.debugLineNum = 4129;BA.debugLine="cbWittBeleuchtung.Checked =  CBool(cur.GetInt(\"";
_cbwittbeleuchtung.setChecked(_cbool(_ba,(Object)(_cur.GetInt("WittBeleucht"))));
 //BA.debugLineNum = 4130;BA.debugLine="etNotizen.Text = cur.GetString(\"Kommentar\")";
_etnotizen.setText(BA.ObjectToCharSequence(_cur.GetString("Kommentar")));
 }
};
 } 
       catch (Exception e244) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e244); //BA.debugLineNum = 4136;BA.debugLine="Log(\"FEHLER beim Lesen/Füllen der BVF Felder\")";
anywheresoftware.b4a.keywords.Common.LogImpl("019726610","FEHLER beim Lesen/Füllen der BVF Felder",0);
 };
 //BA.debugLineNum = 4139;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 4140;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 4141;BA.debugLine="End Sub";
return "";
}
public static String  _setfixenfahrlehrerfahrzeug(anywheresoftware.b4a.BA _ba) throws Exception{
String _supdate = "";
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 301;BA.debugLine="Sub SetFixenFahrlehrerFahrzeug";
 //BA.debugLineNum = 302;BA.debugLine="Dim sUpdate, sSelect As String";
_supdate = "";
_sselect = "";
 //BA.debugLineNum = 303;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 305;BA.debugLine="sSelect = \"SELECT COUNT(*) FROM Fahrlehrer WHERE";
_sselect = "SELECT COUNT(*) FROM Fahrlehrer WHERE IsSelected = 1";
 //BA.debugLineNum = 306;BA.debugLine="sUpdate = \"UPDATE Fahrlehrer SET IsSelected = 1 W";
_supdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE FahrlehrerNr = 1";
 //BA.debugLineNum = 308;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 309;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 310;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 313;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 315;BA.debugLine="If iAnzahl = 0 Then";
if (_ianzahl==0) { 
 //BA.debugLineNum = 317;BA.debugLine="Try";
try { //BA.debugLineNum = 318;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 321;BA.debugLine="Log(\"Fehler beim setzen des fixen Fahrlehrers\")";
anywheresoftware.b4a.keywords.Common.LogImpl("015400980","Fehler beim setzen des fixen Fahrlehrers",0);
 };
 };
 //BA.debugLineNum = 326;BA.debugLine="sSelect = \"SELECT COUNT(*) FROM Fahrzeuge WHERE I";
_sselect = "SELECT COUNT(*) FROM Fahrzeuge WHERE IsSelected = 1";
 //BA.debugLineNum = 327;BA.debugLine="sUpdate = \"UPDATE Fahrzeuge SET IsSelected = 1 WH";
_supdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = 1";
 //BA.debugLineNum = 329;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 331;BA.debugLine="If iAnzahl = 0 Then";
if (_ianzahl==0) { 
 //BA.debugLineNum = 333;BA.debugLine="Try";
try { //BA.debugLineNum = 334;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e24) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e24); //BA.debugLineNum = 337;BA.debugLine="Log(\"Fehler beim setzen des fixen Fahrzeugs\")";
anywheresoftware.b4a.keywords.Common.LogImpl("015400996","Fehler beim setzen des fixen Fahrzeugs",0);
 };
 };
 //BA.debugLineNum = 342;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 344;BA.debugLine="End Sub";
return "";
}
public static String  _setkl1beantragtinstammdaten(anywheresoftware.b4a.BA _ba) throws Exception{
String _supdate = "";
 //BA.debugLineNum = 226;BA.debugLine="Sub SetKl1BeantragtInStammdaten()";
 //BA.debugLineNum = 227;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 229;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 230;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 231;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 234;BA.debugLine="sUpdate = \"UPDATE Schueler \" & _ 				\"SET Kl1Bean";
_supdate = "UPDATE Schueler "+"SET Kl1Beantragt = 'NV' "+"WHERE Kl1Beantragt IS NULL "+"OR Kl1Beantragt = ''";
 //BA.debugLineNum = 240;BA.debugLine="Try";
try { //BA.debugLineNum = 241;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 244;BA.debugLine="Log(\"Fehler beim setzen der beantragten Klasse i";
anywheresoftware.b4a.keywords.Common.LogImpl("015269906","Fehler beim setzen der beantragten Klasse in Schüler-Tabelle"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 248;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _setklassenidsintermine(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
String _supdatekl = "";
int _i = 0;
 //BA.debugLineNum = 99;BA.debugLine="Sub SetKlassenIdsInTermine()";
 //BA.debugLineNum = 100;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 101;BA.debugLine="Dim sUpdateKl As String";
_supdatekl = "";
 //BA.debugLineNum = 103;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 104;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 105;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 108;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	OID, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	OID, "+"Klassen_ID, "+"Klasse, "+"CASE MeetingPoint WHEN MeetingPoint THEN MeetingPoint ELSE 1 END AS MeetingPoint "+"FROM Termine")));
 //BA.debugLineNum = 114;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step8 = 1;
final int limit8 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 115;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 117;BA.debugLine="If (cur.GetString(\"Klasse\") = \"\") Then";
if (((_cur.GetString("Klasse")).equals(""))) { 
 //BA.debugLineNum = 118;BA.debugLine="sUpdateKl = \"UPDATE Termine \" & _ 							\"SET K";
_supdatekl = "UPDATE Termine "+"SET Klasse = ( SELECT Bezeichnung FROM Klassen "+"WHERE OID = "+BA.NumberToString(_cur.GetInt("Klassen_ID"))+" ), "+"Starttermin = SUBSTR(Starttermin, 0, 6) "+" WHERE OID = "+BA.NumberToString(_cur.GetInt("OID"));
 }else if(((_cur.GetString("MeetingPoint")).equals("1") == false)) { 
 //BA.debugLineNum = 124;BA.debugLine="sUpdateKl = \"UPDATE Termine \" & _ 							\"SET S";
_supdatekl = "UPDATE Termine "+"SET Starttermin = SUBSTR(Starttermin, 0, 6), "+"Treffpunkt_ID = ( SELECT OID FROM Treffpunkt "+"WHERE Bezeichnung LIKE '"+_cur.GetString("MeetingPoint")+"' ) "+" WHERE OID = "+BA.NumberToString(_cur.GetInt("OID"));
 }else {
 //BA.debugLineNum = 130;BA.debugLine="sUpdateKl = \"UPDATE Termine \" & _ 							\"SET S";
_supdatekl = "UPDATE Termine "+"SET Starttermin = SUBSTR(Starttermin, 0, 6) "+" WHERE OID = "+BA.NumberToString(_cur.GetInt("OID"));
 };
 //BA.debugLineNum = 136;BA.debugLine="Try";
try { //BA.debugLineNum = 137;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdateKl)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdatekl);
 } 
       catch (Exception e20) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e20); //BA.debugLineNum = 140;BA.debugLine="Log(\"Fehler beim setzen der Klassen_ID in Tb-Te";
anywheresoftware.b4a.keywords.Common.LogImpl("015007785","Fehler beim setzen der Klassen_ID in Tb-Termine "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 }
};
 //BA.debugLineNum = 145;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 146;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _setlernthermen_kat(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
String _supdate = "";
int _izaehler = 0;
int _i = 0;
 //BA.debugLineNum = 61;BA.debugLine="Sub SetLernThermen_Kat()";
 //BA.debugLineNum = 62;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 64;BA.debugLine="Dim iZaehler As Int";
_izaehler = 0;
 //BA.debugLineNum = 66;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 67;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 68;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 71;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT 	LfdNr, \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT 	LfdNr, "+"Kat_LernThemen_PK "+"FROM LernThemen")));
 //BA.debugLineNum = 75;BA.debugLine="For i = 1 To cur.RowCount";
{
final int step9 = 1;
final int limit9 = _cur.getRowCount();
_i = (int) (1) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 76;BA.debugLine="cur.Position = i - 1";
_cur.setPosition((int) (_i-1));
 //BA.debugLineNum = 77;BA.debugLine="If (cur.Position Mod 10 = 0) Then";
if ((_cur.getPosition()%10==0)) { 
 //BA.debugLineNum = 78;BA.debugLine="iZaehler = iZaehler + 1";
_izaehler = (int) (_izaehler+1);
 };
 //BA.debugLineNum = 81;BA.debugLine="sUpdate = \"UPDATE LernThemen \" & _ 					\"SET Kat";
_supdate = "UPDATE LernThemen "+"SET Kat_LernThemen_PK = "+BA.NumberToString(_izaehler)+" WHERE LfdNr = "+BA.NumberToString(_cur.GetInt("LfdNr"));
 //BA.debugLineNum = 86;BA.debugLine="Try";
try { //BA.debugLineNum = 87;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 } 
       catch (Exception e18) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e18); //BA.debugLineNum = 90;BA.debugLine="Log(\"Fehler beim setzen der Lern Kategorisierun";
anywheresoftware.b4a.keywords.Common.LogImpl("014942237","Fehler beim setzen der Lern Kategorisierung in LernThemen"+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 }
};
 //BA.debugLineNum = 94;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 95;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static boolean  _sonstigetaetigkeiteninsert(anywheresoftware.b4a.BA _ba,String _stermin,String _sstartzeit,String _sdauer,String _sfahrbezeichnung,String _sfahrlehrer,boolean _bunterschrift) throws Exception{
boolean _bresult = false;
String _sinsert = "";
int _iid = 0;
String _ssignature = "";
 //BA.debugLineNum = 1382;BA.debugLine="Sub SonstigeTaetigkeitenInsert(sTermin As String,";
 //BA.debugLineNum = 1383;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1384;BA.debugLine="Dim sInsert As String";
_sinsert = "";
 //BA.debugLineNum = 1387;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 1388;BA.debugLine="Dim sSignature As String";
_ssignature = "";
 //BA.debugLineNum = 1390;BA.debugLine="If bUnterschrift Then";
if (_bunterschrift) { 
 //BA.debugLineNum = 1391;BA.debugLine="iID = GetSignatureID(sTermin)";
_iid = _getsignatureid(_ba,(int)(Double.parseDouble(_stermin)));
 //BA.debugLineNum = 1392;BA.debugLine="sSignature = GetSignatureString(sTermin)";
_ssignature = _getsignaturestring(_ba,(int)(Double.parseDouble(_stermin)));
 };
 //BA.debugLineNum = 1395;BA.debugLine="sInsert = \"INSERT INTO Termine (\" & _ 							\"Fah";
_sinsert = "INSERT INTO Termine ("+"Fahrlehrer_ID, "+"Schueler_ID, "+"MatchCode, "+"Termin, "+"Klassen_ID, "+"Klasse, "+"Fahrtbezeichnung_ID, "+"FahrtBezeichnung_Abkuerzen, "+"Starttermin, "+"Dauer, "+"Treffpunkt_ID, "+"MeetingPoint, "+"Hat_Unterschrift, "+"Fahrzeug_ID, "+"Begleitfahrzeug_ID, "+"PruefungsErgebnis, "+"PruefungsTagNr, "+"TransKz) "+"VALUES ( "+"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"999,  "+"'Sonstige Tätigkeit', "+"'"+_stermin+"', "+"0, "+"' ', "+"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"'"+_sfahrbezeichnung+"', "+"'"+_sstartzeit+"', "+"'"+_sdauer+"', "+"0, "+"'', "+"0, "+"0, "+"0, "+"' ', "+"' ', "+"' ' "+") ";
 //BA.debugLineNum = 1435;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1436;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1437;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1441;BA.debugLine="Try";
try { //BA.debugLineNum = 1442;BA.debugLine="Main.SQL1.ExecNonQuery(sInsert)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsert);
 //BA.debugLineNum = 1444;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e18) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e18); //BA.debugLineNum = 1446;BA.debugLine="Log(\"Fehler beim speichern der Sonstigen Täti";
anywheresoftware.b4a.keywords.Common.LogImpl("016973888","Fehler beim speichern der Sonstigen Tätigkeiten (Insert) "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1447;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1451;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1453;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1454;BA.debugLine="End Sub";
return false;
}
public static boolean  _sonstigetaetigkeitenupdate(anywheresoftware.b4a.BA _ba,int _iterminid,String _stermin,String _sstartzeit,String _sdauer,String _sfahrbezeichnung,String _sfahrlehrer,boolean _bunterschrift) throws Exception{
boolean _bresult = false;
String _supdate = "";
int _iid = 0;
String _ssignature = "";
 //BA.debugLineNum = 1457;BA.debugLine="Sub SonstigeTaetigkeitenUpdate(iTerminID As Int, s";
 //BA.debugLineNum = 1458;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1459;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 1462;BA.debugLine="Dim iID As Int";
_iid = 0;
 //BA.debugLineNum = 1463;BA.debugLine="Dim sSignature As String";
_ssignature = "";
 //BA.debugLineNum = 1465;BA.debugLine="If bUnterschrift Then";
if (_bunterschrift) { 
 //BA.debugLineNum = 1466;BA.debugLine="iID = GetSignatureID(iTerminID)";
_iid = _getsignatureid(_ba,_iterminid);
 //BA.debugLineNum = 1467;BA.debugLine="sSignature = GetSignatureString(iTerminID)";
_ssignature = _getsignaturestring(_ba,_iterminid);
 };
 //BA.debugLineNum = 1470;BA.debugLine="If iID > 0 Then";
if (_iid>0) { 
 //BA.debugLineNum = 1471;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Fahrl";
_supdate = "UPDATE Termine "+"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"Schueler_ID = 999, "+"MatchCode = 'Sonstige Tätigkeit', "+"Termin = '"+_stermin+"', "+"Klassen_ID = 0, "+"Klasse = ' ', "+"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"FahrtBezeichnung_Abkuerzen = '"+_sfahrbezeichnung+"', "+"Starttermin = '"+_sstartzeit+"', "+"Dauer = '"+_sdauer+"', "+"Treffpunkt_ID = 0, "+"MeetingPoint = ' ', "+"Hat_Unterschrift = 1, "+"UnterschriftJaNein = 'Ja', "+"Unterschrift = '"+_ssignature+"', "+"Fahrzeug_ID = 0, "+"Begleitfahrzeug_ID = 0, "+"PruefungsErgebnis = ' ', "+"PruefungsTagNr = ' ', "+"TransKz = 'U' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 }else {
 //BA.debugLineNum = 1494;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Fahrl";
_supdate = "UPDATE Termine "+"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), "+"Schueler_ID = 999, "+"MatchCode = 'Sonstige Tätigkeit', "+"Termin = '"+_stermin+"', "+"Klassen_ID = 0, "+"Klasse = ' ', "+"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"'), "+"FahrtBezeichnung_Abkuerzen = '"+_sfahrbezeichnung+"', "+"Starttermin = '"+_sstartzeit+"', "+"Dauer = '"+_sdauer+"', "+"Treffpunkt_ID = 0, "+"MeetingPoint = ' ', "+"Hat_Unterschrift = 0, "+"UnterschriftJaNein = 'Nein', "+"Fahrzeug_ID = 0, "+"Begleitfahrzeug_ID = 0, "+"PruefungsErgebnis = ' ', "+"PruefungsTagNr = ' ', "+"TransKz = ' ' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 };
 //BA.debugLineNum = 1517;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1518;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1519;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1523;BA.debugLine="Try";
try { //BA.debugLineNum = 1524;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 1526;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e22) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e22); //BA.debugLineNum = 1528;BA.debugLine="Log(\"Fehler beim speichern der Sonstigen Täti";
anywheresoftware.b4a.keywords.Common.LogImpl("017039431","Fehler beim speichern der Sonstigen Tätigkeiten (Update) "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1529;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1532;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1534;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1535;BA.debugLine="End Sub";
return false;
}
public static int  _sonsttaetigkeitenidholen(anywheresoftware.b4a.BA _ba,String _stermin,String _sstartzeit,String _sdauer,String _sfahrbezeichnung) throws Exception{
int _iterminid = 0;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 1806;BA.debugLine="Sub SonstTaetigkeitenIDHolen(sTermin As String, sS";
 //BA.debugLineNum = 1807;BA.debugLine="Dim iTerminID As Int";
_iterminid = 0;
 //BA.debugLineNum = 1808;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 1809;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1811;BA.debugLine="sSelect = \"SELECT 	OID, \" & _ 						\"Schueler_ID,";
_sselect = "SELECT 	OID, "+"Schueler_ID, "+"MatchCode, "+"FahrtBezeichnung_ID "+"FROM Termine "+"WHERE Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)"+"AND Termin LIKE '"+_stermin+"'"+"AND Starttermin LIKE '"+_sstartzeit+"'"+"AND FahrtBezeichnung_Abkuerzen LIKE '"+_sfahrbezeichnung+"' "+"AND Dauer LIKE '"+_sdauer+"'";
 //BA.debugLineNum = 1822;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1823;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1824;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1828;BA.debugLine="Try";
try { //BA.debugLineNum = 1829;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 1831;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step11 = 1;
final int limit11 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 1832;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1833;BA.debugLine="iTerminID = cur.GetInt(\"OID\")";
_iterminid = _cur.GetInt("OID");
 //BA.debugLineNum = 1834;BA.debugLine="For i = 0 To Main.aSonstigeTaetigkeiten.Length";
{
final int step14 = 1;
final int limit14 = (int) (mostCurrent._main._asonstigetaetigkeiten /*String[][]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit14 ;_i = _i + step14 ) {
 //BA.debugLineNum = 1835;BA.debugLine="If Main.aSonstigeTaetigkeiten(i, 1) = cur.GetI";
if ((mostCurrent._main._asonstigetaetigkeiten /*String[][]*/ [_i][(int) (1)]).equals(BA.NumberToString(_cur.GetInt("FahrtBezeichnung_ID")))) { 
 //BA.debugLineNum = 1836;BA.debugLine="SonstigeTaetigkeiten.SonstTaetigkeitSelected";
mostCurrent._sonstigetaetigkeiten._sonsttaetigkeitselected /*int*/  = _i;
 };
 }
};
 }
};
 } 
       catch (Exception e21) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e21); //BA.debugLineNum = 1842;BA.debugLine="Log(\"Fehler beim holen der ID des Sonstigen T";
anywheresoftware.b4a.keywords.Common.LogImpl("017432612","Fehler beim holen der ID des Sonstigen Tätigkeiten "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1843;BA.debugLine="iTerminID = 0";
_iterminid = (int) (0);
 };
 //BA.debugLineNum = 1846;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1847;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1849;BA.debugLine="Return iTerminID";
if (true) return _iterminid;
 //BA.debugLineNum = 1850;BA.debugLine="End Sub";
return 0;
}
public static String[]  _stammdatenholen(anywheresoftware.b4a.BA _ba) throws Exception{
String[] _astammdaten = null;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
 //BA.debugLineNum = 2108;BA.debugLine="Sub StammdatenHolen() As String()";
 //BA.debugLineNum = 2109;BA.debugLine="Dim aStammdaten(19) As String";
_astammdaten = new String[(int) (19)];
java.util.Arrays.fill(_astammdaten,"");
 //BA.debugLineNum = 2110;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2111;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 2113;BA.debugLine="sSelect = \"SELECT 	Strasse, \" & _ 						\"Hausnumm";
_sselect = "SELECT 	Strasse, "+"Hausnummer, "+"PLZ, "+"Ort, "+"CASE Phone WHEN Phone THEN Phone ELSE '' END AS Phone, "+"CASE Mobil WHEN Mobil THEN Mobil ELSE '' END AS Mobil, "+"CASE eMail WHEN eMail THEN eMail ELSE '' END AS eMail, "+"CASE Arbeitsstelle WHEN Arbeitsstelle THEN Arbeitsstelle ELSE '' END AS Arbeitsstelle, "+"CASE PhoneGeschaeft WHEN PhoneGeschaeft THEN PhoneGeschaeft ELSE '' END AS PhoneGeschaeft, "+"CASE BeantragteKlasse WHEN BeantragteKlasse THEN BeantragteKlasse ELSE '' END AS BeantragteKlasse, "+"CASE BesitztKlasse WHEN BesitztKlasse THEN BesitztKlasse ELSE '' END AS BesitztKlasse, "+"Saldo, "+"CASE AntragEingereichtAm WHEN AntragEingereichtAm THEN AntragEingereichtAm ELSE '' END AS AntragEingereichtAm, "+"CASE AntragZurJN WHEN AntragZurJN THEN AntragZurJN ELSE '' END AS AntragZurJN, "+"Geburtstag, "+"Nation, "+"AnmeldeDatum, "+"SchuleFil "+"FROM Schueler "+"WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' "+"AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 2135;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2136;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2137;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2140;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2142;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2143;BA.debugLine="aStammdaten = Array As String(cur.GetString(\"Stra";
_astammdaten = new String[]{_cur.GetString("Strasse"),_cur.GetString("Hausnummer"),BA.NumberToString(_cur.GetInt("PLZ")),_cur.GetString("Ort"),_cur.GetString("Phone"),_cur.GetString("Mobil"),_cur.GetString("eMail"),_cur.GetString("Arbeitsstelle"),_cur.GetString("PhoneGeschaeft"),_cur.GetString("BeantragteKlasse"),_cur.GetString("BesitztKlasse"),_cur.GetString("Saldo"),_cur.GetString("AntragEingereichtAm"),_cur.GetString("AntragZurJN"),_cur.GetString("Geburtstag"),_cur.GetString("Nation"),_cur.GetString("AnmeldeDatum"),_cur.GetString("SchuleFil")};
 //BA.debugLineNum = 2150;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 2151;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2153;BA.debugLine="Return aStammdaten";
if (true) return _astammdaten;
 //BA.debugLineNum = 2155;BA.debugLine="End Sub";
return null;
}
public static String[]  _stammdatenklassendatenholen(anywheresoftware.b4a.BA _ba,int _izahl) throws Exception{
String[] _aklassendaten = null;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
 //BA.debugLineNum = 2158;BA.debugLine="Sub StammdatenKlassendatenHolen(iZahl As Int) As S";
 //BA.debugLineNum = 2159;BA.debugLine="Dim aKlassendaten(11) As String";
_aklassendaten = new String[(int) (11)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 2160;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2161;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 2163;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2164;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2165;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2168;BA.debugLine="Select iZahl";
switch (_izahl) {
case 1: {
 //BA.debugLineNum = 2170;BA.debugLine="sSelect = \"SELECT 	TheorieGrundStd, \" & _";
_sselect = "SELECT 	TheorieGrundStd, "+"HatKla1TheoSpez, "+"Kl1Beantragt, "+"Kl1Ausbildungsstand, "+"Kl1FahrStd, "+"HatKla1FAuG, "+"Kl1Ueberland, "+"Kl1Autobahn, "+"Kl1Nacht, "+"Kl1UnterwsgAmFhrzg, "+"Kl1TheoriePfgAm, "+"Kl1PraktischePfgAm "+"FROM Schueler "+"WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' "+"AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 2186;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2188;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2189;BA.debugLine="aKlassendaten = Array As String(cur.GetString(\"";
_aklassendaten = new String[]{_cur.GetString("TheorieGrundStd"),_cur.GetString("HatKla1TheoSpez"),_cur.GetString("Kl1Beantragt"),_cur.GetString("Kl1Ausbildungsstand"),_cur.GetString("Kl1FahrStd"),_cur.GetString("HatKla1FAuG"),_cur.GetString("Kl1Ueberland"),_cur.GetString("Kl1Autobahn"),_cur.GetString("Kl1Nacht"),_cur.GetString("Kl1UnterwsgAmFhrzg"),_cur.GetString("Kl1TheoriePfgAm"),_cur.GetString("Kl1PraktischePfgAm")};
 break; }
case 2: {
 //BA.debugLineNum = 2194;BA.debugLine="sSelect = \"SELECT 	TheorieGrundStd, \" & _";
_sselect = "SELECT 	TheorieGrundStd, "+"HatKla2TheoSpez, "+"Kl2Beantragt, "+"Kl2Ausbildungsstand, "+"Kl2FahrStd, "+"HatKla2FAuG, "+"Kl2Ueberland, "+"Kl2Autobahn, "+"Kl2Nacht, "+"Kl2UnterwsgAmFhrzg, "+"Kl2TheoriePfgAm, "+"Kl2PraktischePfgAm "+"FROM Schueler "+"WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' "+"AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 2210;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2212;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2213;BA.debugLine="aKlassendaten = Array As String(cur.GetString(\"";
_aklassendaten = new String[]{_cur.GetString("TheorieGrundStd"),_cur.GetString("HatKla2TheoSpez"),_cur.GetString("Kl2Beantragt"),_cur.GetString("Kl2Ausbildungsstand"),_cur.GetString("Kl2FahrStd"),_cur.GetString("HatKla2FAuG"),_cur.GetString("Kl2Ueberland"),_cur.GetString("Kl2Autobahn"),_cur.GetString("Kl2Nacht"),_cur.GetString("Kl2UnterwsgAmFhrzg"),_cur.GetString("Kl2TheoriePfgAm"),_cur.GetString("Kl2PraktischePfgAm")};
 break; }
case 3: {
 //BA.debugLineNum = 2218;BA.debugLine="sSelect = \"SELECT 	TheorieGrundStd, \" & _";
_sselect = "SELECT 	TheorieGrundStd, "+"HatKla3TheoSpez, "+"Kl3Beantragt, "+"Kl3Ausbildungsstand, "+"Kl3FahrStd, "+"HatKla3FAuG, "+"Kl3Ueberland, "+"Kl3Autobahn, "+"Kl3Nacht, "+"Kl3UnterwsgAmFhrzg, "+"Kl3TheoriePfgAm, "+"Kl3PraktischePfgAm "+"FROM Schueler "+"WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' "+"AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 2234;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2236;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2237;BA.debugLine="aKlassendaten = Array As String(cur.GetString(\"";
_aklassendaten = new String[]{_cur.GetString("TheorieGrundStd"),_cur.GetString("HatKla3TheoSpez"),_cur.GetString("Kl3Beantragt"),_cur.GetString("Kl3Ausbildungsstand"),_cur.GetString("Kl3FahrStd"),_cur.GetString("HatKla3FAuG"),_cur.GetString("Kl3Ueberland"),_cur.GetString("Kl3Autobahn"),_cur.GetString("Kl3Nacht"),_cur.GetString("Kl3UnterwsgAmFhrzg"),_cur.GetString("Kl3TheoriePfgAm"),_cur.GetString("Kl3PraktischePfgAm")};
 break; }
case 4: {
 //BA.debugLineNum = 2242;BA.debugLine="sSelect = \"SELECT 	TheorieGrundStd, \" & _";
_sselect = "SELECT 	TheorieGrundStd, "+"HatKla4TheoSpez, "+"Kl4Beantragt, "+"Kl4Ausbildungsstand, "+"Kl4FahrStd, "+"HatKla4FAuG, "+"Kl4Ueberland, "+"Kl4Autobahn, "+"Kl4Nacht, "+"Kl4UnterwsgAmFhrzg, "+"Kl4TheoriePfgAm, "+"Kl4PraktischePfgAm "+"FROM Schueler "+"WHERE MatchCode LIKE '"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"' "+"AND SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 2258;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2260;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 2261;BA.debugLine="aKlassendaten = Array As String(cur.GetString(\"";
_aklassendaten = new String[]{_cur.GetString("TheorieGrundStd"),_cur.GetString("HatKla4TheoSpez"),_cur.GetString("Kl4Beantragt"),_cur.GetString("Kl4Ausbildungsstand"),_cur.GetString("Kl4FahrStd"),_cur.GetString("HatKla4FAuG"),_cur.GetString("Kl4Ueberland"),_cur.GetString("Kl4Autobahn"),_cur.GetString("Kl4Nacht"),_cur.GetString("Kl4UnterwsgAmFhrzg"),_cur.GetString("Kl4TheoriePfgAm"),_cur.GetString("Kl4PraktischePfgAm")};
 break; }
}
;
 //BA.debugLineNum = 2267;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 2268;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2270;BA.debugLine="Return aKlassendaten";
if (true) return _aklassendaten;
 //BA.debugLineNum = 2271;BA.debugLine="End Sub";
return null;
}
public static boolean  _startzeitpuefen(anywheresoftware.b4a.BA _ba,int _iterminid,String _sdatum,String _sstartzeit,String _sdauer) throws Exception{
boolean _bresult = false;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
double _istartzeit = 0;
double _iendzeit = 0;
double _istartzeitneu = 0;
double _iendzeitneu = 0;
int _i = 0;
 //BA.debugLineNum = 2579;BA.debugLine="Sub StartzeitPuefen(iTerminID As Int, sDatum As St";
 //BA.debugLineNum = 2580;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 2581;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2582;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 2583;BA.debugLine="Dim iStartzeit, iEndZeit, iStartzeitNeu, iEndzeit";
_istartzeit = 0;
_iendzeit = 0;
_istartzeitneu = 0;
_iendzeitneu = 0;
 //BA.debugLineNum = 2585;BA.debugLine="sSelect = \"SELECT 	strftime('%s', Starttermin) AS";
_sselect = "SELECT 	strftime('%s', Starttermin) AS Starttime, "+"(strftime('%s', Starttermin) + (Dauer * 60)) AS EndTime, "+"strftime('%s', '"+_sstartzeit+"') AS newStarttime, "+"(strftime('%s', '"+_sstartzeit+"') + ("+_sdauer+" * 60)) AS newEndTime "+"FROM Termine "+"WHERE Termin LIKE '"+_sdatum+"' "+"AND Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)";
 //BA.debugLineNum = 2593;BA.debugLine="If Main.bDatenVorhanden Then";
if (mostCurrent._main._bdatenvorhanden /*boolean*/ ) { 
 //BA.debugLineNum = 2594;BA.debugLine="sSelect = sSelect & \"AND OID <> \" & iTerminID";
_sselect = _sselect+"AND OID <> "+BA.NumberToString(_iterminid);
 };
 //BA.debugLineNum = 2597;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 2599;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2600;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2601;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2605;BA.debugLine="Try";
try { //BA.debugLineNum = 2606;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 2608;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step16 = 1;
final int limit16 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 2609;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 2611;BA.debugLine="If bResult Then";
if (_bresult) { 
 //BA.debugLineNum = 2612;BA.debugLine="If i = 0 Then";
if (_i==0) { 
 //BA.debugLineNum = 2613;BA.debugLine="iStartzeitNeu = cur.GetDouble(\"newStarttime\")";
_istartzeitneu = _cur.GetDouble("newStarttime");
 //BA.debugLineNum = 2614;BA.debugLine="iEndzeitNeu = cur.GetDouble(\"newEndTime\")";
_iendzeitneu = _cur.GetDouble("newEndTime");
 };
 //BA.debugLineNum = 2617;BA.debugLine="iStartzeit = cur.GetDouble(\"Starttime\")";
_istartzeit = _cur.GetDouble("Starttime");
 //BA.debugLineNum = 2618;BA.debugLine="iEndZeit = cur.GetDouble(\"EndTime\")";
_iendzeit = _cur.GetDouble("EndTime");
 //BA.debugLineNum = 2620;BA.debugLine="If iStartzeitNeu >= iStartzeit And iStartzeitN";
if (_istartzeitneu>=_istartzeit && _istartzeitneu<_iendzeit) { 
 //BA.debugLineNum = 2621;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 }else if(_iendzeitneu>_istartzeit && _iendzeitneu<_iendzeit) { 
 //BA.debugLineNum = 2623;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 }else if(_istartzeitneu<_istartzeit && _iendzeitneu>=_iendzeit) { 
 //BA.debugLineNum = 2625;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 }
};
 } 
       catch (Exception e35) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e35); //BA.debugLineNum = 2631;BA.debugLine="Log(\"Terminliche Überscheidung\")";
anywheresoftware.b4a.keywords.Common.LogImpl("018677812","Terminliche Überscheidung",0);
 //BA.debugLineNum = 2632;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2635;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 2636;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2638;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 2639;BA.debugLine="End Sub";
return false;
}
public static int  _terminidholen(anywheresoftware.b4a.BA _ba,String _stermin,String _sstartzeit,String _sdauer,String _sklasse,String _sfahrbezeichnung,String _streffpunkt) throws Exception{
int _iterminid = 0;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 1740;BA.debugLine="Sub TerminIDHolen(sTermin As String, sStartzeit As";
 //BA.debugLineNum = 1741;BA.debugLine="Dim iTerminID As Int";
_iterminid = 0;
 //BA.debugLineNum = 1743;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 1744;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1746;BA.debugLine="sSelect = \"SELECT 	OID, \" & _ 						\"Schueler_ID,";
_sselect = "SELECT 	OID, "+"Schueler_ID, "+"MatchCode, "+"Fahrzeug_ID, "+"FahrtBezeichnung_ID, "+"Treffpunkt_ID "+"FROM Termine "+"WHERE Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)"+"AND Termin LIKE '"+_stermin+"'"+"AND Klasse LIKE '"+_sklasse+"'"+"AND FahrtBezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '"+_sfahrbezeichnung+"')"+"AND Starttermin LIKE '"+_sstartzeit+"'"+"AND Dauer LIKE '"+_sdauer+"'";
 //BA.debugLineNum = 1761;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1762;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1763;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1767;BA.debugLine="Try";
try { //BA.debugLineNum = 1768;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 1770;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step11 = 1;
final int limit11 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 1771;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1772;BA.debugLine="iTerminID = cur.GetInt(\"OID\")";
_iterminid = _cur.GetInt("OID");
 //BA.debugLineNum = 1773;BA.debugLine="Main.iAusgewaehlterSchuelrID = cur.GetInt(\"Schu";
mostCurrent._main._iausgewaehlterschuelrid /*int*/  = _cur.GetInt("Schueler_ID");
 //BA.debugLineNum = 1774;BA.debugLine="Main.sAusgewaehlterSchueler = cur.GetString(\"Ma";
mostCurrent._main._sausgewaehlterschueler /*String*/  = _cur.GetString("MatchCode");
 //BA.debugLineNum = 1775;BA.debugLine="For i = 0 To Main.aKFZ.Length - 1";
{
final int step16 = 1;
final int limit16 = (int) (mostCurrent._main._akfz /*String[][]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 1776;BA.debugLine="If Main.aKFZ(i, 1) = cur.GetInt(\"Fahrzeug_ID\")";
if ((mostCurrent._main._akfz /*String[][]*/ [_i][(int) (1)]).equals(BA.NumberToString(_cur.GetInt("Fahrzeug_ID")))) { 
 //BA.debugLineNum = 1777;BA.debugLine="KFZ.KfzSelected = i";
mostCurrent._kfz._kfzselected /*int*/  = _i;
 };
 }
};
 //BA.debugLineNum = 1781;BA.debugLine="For i = 0 To Main.aFahrtbezeichnung.Length - 1";
{
final int step21 = 1;
final int limit21 = (int) (mostCurrent._main._afahrtbezeichnung /*String[][]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit21 ;_i = _i + step21 ) {
 //BA.debugLineNum = 1782;BA.debugLine="If Main.aFahrtbezeichnung(i, 1) = cur.GetInt(\"";
if ((mostCurrent._main._afahrtbezeichnung /*String[][]*/ [_i][(int) (1)]).equals(BA.NumberToString(_cur.GetInt("FahrtBezeichnung_ID")))) { 
 //BA.debugLineNum = 1783;BA.debugLine="Fahrtbezeichnung.FahrtSelected = i";
mostCurrent._fahrtbezeichnung._fahrtselected /*int*/  = _i;
 };
 }
};
 //BA.debugLineNum = 1786;BA.debugLine="For i = 0 To Main.aTreffpunkt.Length - 1";
{
final int step26 = 1;
final int limit26 = (int) (mostCurrent._main._atreffpunkt /*String[][]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit26 ;_i = _i + step26 ) {
 //BA.debugLineNum = 1787;BA.debugLine="If Main.aTreffpunkt(i, 1) = cur.GetString(\"Tre";
if ((mostCurrent._main._atreffpunkt /*String[][]*/ [_i][(int) (1)]).equals(_cur.GetString("Treffpunkt_ID"))) { 
 //BA.debugLineNum = 1788;BA.debugLine="Treffpunkt.TreffpunktSelected = i";
mostCurrent._treffpunkt._treffpunktselected /*int*/  = _i;
 };
 }
};
 }
};
 } 
       catch (Exception e33) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e33); //BA.debugLineNum = 1794;BA.debugLine="Log(\"Fehler beim holen der TerminID \" & LastE";
anywheresoftware.b4a.keywords.Common.LogImpl("017367094","Fehler beim holen der TerminID "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1795;BA.debugLine="iTerminID = 0";
_iterminid = (int) (0);
 };
 //BA.debugLineNum = 1799;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1800;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1802;BA.debugLine="Return iTerminID";
if (true) return _iterminid;
 //BA.debugLineNum = 1803;BA.debugLine="End Sub";
return 0;
}
public static boolean  _terminloeschen(anywheresoftware.b4a.BA _ba,int _iposition,String _sdatum) throws Exception{
boolean _bresult = false;
String _sdeletetermin = "";
String _sdeletesignature = "";
String _sselect = "";
int _ipositionid = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 1853;BA.debugLine="Sub TerminLoeschen(iPosition As Int, sDatum As Str";
 //BA.debugLineNum = 1854;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1855;BA.debugLine="Dim sDeleteTermin, sDeleteSignature, sSelect As S";
_sdeletetermin = "";
_sdeletesignature = "";
_sselect = "";
 //BA.debugLineNum = 1856;BA.debugLine="Dim iPositionID As Int";
_ipositionid = 0;
 //BA.debugLineNum = 1857;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1860;BA.debugLine="sSelect = \"SELECT t.OID \" & _ 					\"FROM Termine";
_sselect = "SELECT t.OID "+"FROM Termine t "+"WHERE t.Termin LIKE '"+_sdatum+"' "+"AND t.Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)"+"ORDER BY t.Starttermin";
 //BA.debugLineNum = 1866;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1867;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1868;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1872;BA.debugLine="Try";
try { //BA.debugLineNum = 1873;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 1875;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 1876;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1877;BA.debugLine="If i = iPosition Then";
if (_i==_iposition) { 
 //BA.debugLineNum = 1878;BA.debugLine="iPositionID = cur.GetInt(\"OID\")";
_ipositionid = _cur.GetInt("OID");
 };
 }
};
 //BA.debugLineNum = 1882;BA.debugLine="sDeleteTermin = \"DELETE FROM Termine \" & _ 				\"";
_sdeletetermin = "DELETE FROM Termine "+"WHERE Termin LIKE '"+_sdatum+"' "+"AND OID = "+BA.NumberToString(_ipositionid);
 //BA.debugLineNum = 1886;BA.debugLine="sDeleteSignature = \"DELETE FROM Signature \" & _";
_sdeletesignature = "DELETE FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_ipositionid);
 //BA.debugLineNum = 1889;BA.debugLine="Main.SQL1.ExecNonQuery(sDeleteTermin)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdeletetermin);
 //BA.debugLineNum = 1890;BA.debugLine="Main.SQL1.ExecNonQuery(sDeleteSignature)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdeletesignature);
 //BA.debugLineNum = 1892;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e24) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e24); //BA.debugLineNum = 1894;BA.debugLine="Log(\"Fehler beim löschen eines Termineintrags";
anywheresoftware.b4a.keywords.Common.LogImpl("017498153","Fehler beim löschen eines Termineintrags "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1895;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1898;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1899;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1901;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1902;BA.debugLine="End Sub";
return false;
}
public static boolean  _terminloeschenwenndoppeltgespeichert(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
boolean _bresult = false;
String _sdeletetermin = "";
String _sdeletesignature = "";
 //BA.debugLineNum = 1905;BA.debugLine="Sub TerminLoeschenWennDoppeltGespeichert(iTerminID";
 //BA.debugLineNum = 1906;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1907;BA.debugLine="Dim sDeleteTermin, sDeleteSignature As String";
_sdeletetermin = "";
_sdeletesignature = "";
 //BA.debugLineNum = 1910;BA.debugLine="sDeleteSignature = \"DELETE FROM Signature \" & _";
_sdeletesignature = "DELETE FROM Signature "+"WHERE Termin_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 1913;BA.debugLine="sDeleteTermin = \"DELETE FROM Termine \" & _ 				\"W";
_sdeletetermin = "DELETE FROM Termine "+"WHERE OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 1916;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1917;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1918;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1922;BA.debugLine="Try";
try { //BA.debugLineNum = 1923;BA.debugLine="Main.SQL1.ExecNonQuery(sDeleteTermin)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdeletetermin);
 //BA.debugLineNum = 1924;BA.debugLine="Main.SQL1.ExecNonQuery(sDeleteSignature)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdeletesignature);
 //BA.debugLineNum = 1926;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 1928;BA.debugLine="Log(\"Fehler beim löschen eines Termineintrags \"";
anywheresoftware.b4a.keywords.Common.LogImpl("017563671","Fehler beim löschen eines Termineintrags "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1929;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1932;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1934;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1935;BA.debugLine="End Sub";
return false;
}
public static int  _treffpunktpruefen(anywheresoftware.b4a.BA _ba,String _sneuertreffpunkt) throws Exception{
int _iresult = 0;
String _sselect = "";
 //BA.debugLineNum = 2439;BA.debugLine="Sub TreffpunktPruefen(sNeuerTreffpunkt As String)";
 //BA.debugLineNum = 2440;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 2441;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 2443;BA.debugLine="sSelect = 	\"SELECT OID \" & _ 					\"FROM Treffpunk";
_sselect = "SELECT OID "+"FROM Treffpunkt "+"WHERE Bezeichnung LIKE '"+_sneuertreffpunkt+"' ";
 //BA.debugLineNum = 2447;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2448;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2449;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 2453;BA.debugLine="Try";
try { //BA.debugLineNum = 2454;BA.debugLine="iResult = Main.SQL1.ExecQuerySingleResult(sSe";
_iresult = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 2457;BA.debugLine="Log(\"Fehler beim prüfen des Treffpunktes \" &";
anywheresoftware.b4a.keywords.Common.LogImpl("018350098","Fehler beim prüfen des Treffpunktes "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2458;BA.debugLine="iResult = 0";
_iresult = (int) (0);
 };
 //BA.debugLineNum = 2461;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2463;BA.debugLine="Return iResult";
if (true) return _iresult;
 //BA.debugLineNum = 2464;BA.debugLine="End Sub";
return 0;
}
public static boolean  _unterschriftspeichern(anywheresoftware.b4a.BA _ba,int _iterminid,byte[] _data,String _ssignaturetostring) throws Exception{
boolean _breturn = false;
String _supdate = "";
String _supdate1 = "";
String _sselect = "";
int _ianzahl = 0;
 //BA.debugLineNum = 2932;BA.debugLine="Sub UnterschriftSpeichern(iTerminID As Int, data()";
 //BA.debugLineNum = 2933;BA.debugLine="Dim bReturn As Boolean";
_breturn = false;
 //BA.debugLineNum = 2934;BA.debugLine="Dim sUpdate, sUpdate1, sSelect As String";
_supdate = "";
_supdate1 = "";
_sselect = "";
 //BA.debugLineNum = 2935;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 2938;BA.debugLine="sUpdate = \"UPDATE Termine \" & _ 						\"SET Unters";
_supdate = "UPDATE Termine "+"SET Unterschrift = '"+_ssignaturetostring+"', "+"TransKz = 'U' "+"WHERE OID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2945;BA.debugLine="sSelect = \"SELECT COUNT(*) FROM Signature \" & _";
_sselect = "SELECT COUNT(*) FROM Signature "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2948;BA.debugLine="sUpdate1 = \"UPDATE Signature \" & _ 					\"SET Datu";
_supdate1 = "UPDATE Signature "+"SET Datum = '"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"', "+"Unterschrift = '"+_ssignaturetostring+"' "+"WHERE Termine_ID = "+BA.NumberToString(_iterminid);
 //BA.debugLineNum = 2953;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 2954;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 2959;BA.debugLine="Try";
try { //BA.debugLineNum = 2960;BA.debugLine="iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelec";
_ianzahl = (int)(Double.parseDouble(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(_sselect)));
 //BA.debugLineNum = 2963;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 2964;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate1)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate1);
 }else {
 //BA.debugLineNum = 2966;BA.debugLine="Main.SQL1.ExecNonQuery2(\"INSERT INTO Signatu";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO Signature ( Termine_ID, "+"Datum, "+"Unterschrift, "+"Unterschrift_Blob ) "+"VALUES ("+BA.NumberToString(_iterminid)+", '"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+"', '"+_ssignaturetostring+"', ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_data)}));
 };
 //BA.debugLineNum = 2973;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 2975;BA.debugLine="bReturn = True";
_breturn = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e20) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e20); //BA.debugLineNum = 2977;BA.debugLine="Log(\"Fehler beim speichern der Unterschrift \"";
anywheresoftware.b4a.keywords.Common.LogImpl("019267629","Fehler beim speichern der Unterschrift "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 2978;BA.debugLine="bReturn = False";
_breturn = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 2981;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 2983;BA.debugLine="Return bReturn";
if (true) return _breturn;
 //BA.debugLineNum = 2984;BA.debugLine="End Sub";
return false;
}
public static boolean  _updateausbildunglernpunkte(anywheresoftware.b4a.BA _ba,char _scharacter,int _iposition) throws Exception{
boolean _bresult = false;
int _ibereich = 0;
String _seintraege = "";
boolean _binsert = false;
char _c = '\0';
int _i = 0;
String _seintraegeneu = "";
String _supdate = "";
String _sdatum = "";
 //BA.debugLineNum = 1138;BA.debugLine="Sub UpdateAusbildungLernPunkte(sCharacter As Char,";
 //BA.debugLineNum = 1139;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1140;BA.debugLine="Dim iBereich As Int";
_ibereich = 0;
 //BA.debugLineNum = 1141;BA.debugLine="Dim sEintraege As String";
_seintraege = "";
 //BA.debugLineNum = 1144;BA.debugLine="iBereich = Main.iAusbildungBereich";
_ibereich = mostCurrent._main._iausbildungbereich /*int*/ ;
 //BA.debugLineNum = 1146;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1147;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1148;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1152;BA.debugLine="sEintraege = Main.SQL1.ExecQuerySingleResult(\"SEL";
_seintraege = mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT LernString "+"FROM LernKontrolle "+"WHERE SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ ));
 //BA.debugLineNum = 1157;BA.debugLine="Dim bInsert As Boolean";
_binsert = false;
 //BA.debugLineNum = 1158;BA.debugLine="bInsert = False";
_binsert = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1160;BA.debugLine="Dim c As Char";
_c = '\0';
 //BA.debugLineNum = 1161;BA.debugLine="c = Chr(32)";
_c = anywheresoftware.b4a.keywords.Common.Chr((int) (32));
 //BA.debugLineNum = 1163;BA.debugLine="If sEintraege = Null Then";
if (_seintraege== null) { 
 //BA.debugLineNum = 1164;BA.debugLine="bInsert = True";
_binsert = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1165;BA.debugLine="sEintraege =  c";
_seintraege = BA.ObjectToString(_c);
 //BA.debugLineNum = 1166;BA.debugLine="For i = 0 To 68";
{
final int step17 = 1;
final int limit17 = (int) (68);
_i = (int) (0) ;
for (;_i <= limit17 ;_i = _i + step17 ) {
 //BA.debugLineNum = 1167;BA.debugLine="sEintraege =  sEintraege & c";
_seintraege = _seintraege+BA.ObjectToString(_c);
 }
};
 };
 //BA.debugLineNum = 1171;BA.debugLine="Dim sEintraegeNeu As String";
_seintraegeneu = "";
 //BA.debugLineNum = 1173;BA.debugLine="If iBereich = 1 Then";
if (_ibereich==1) { 
 //BA.debugLineNum = 1174;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (0),(int) (10),_iposition,_scharacter);
 //BA.debugLineNum = 1175;BA.debugLine="sEintraege = sEintraegeNeu & sEintraege.SubStrin";
_seintraege = _seintraegeneu+_seintraege.substring((int) (10),(int) (70));
 }else if(_ibereich==2) { 
 //BA.debugLineNum = 1177;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (10),(int) (20),_iposition,_scharacter);
 //BA.debugLineNum = 1178;BA.debugLine="sEintraege = sEintraege.SubString2(0, 10) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (10))+_seintraegeneu+_seintraege.substring((int) (20),(int) (70));
 }else if(_ibereich==3) { 
 //BA.debugLineNum = 1180;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (20),(int) (30),_iposition,_scharacter);
 //BA.debugLineNum = 1181;BA.debugLine="sEintraege = sEintraege.SubString2(0, 20) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (20))+_seintraegeneu+_seintraege.substring((int) (30),(int) (70));
 }else if(_ibereich==4) { 
 //BA.debugLineNum = 1183;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (30),(int) (40),_iposition,_scharacter);
 //BA.debugLineNum = 1184;BA.debugLine="sEintraege = sEintraege.SubString2(0, 30) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (30))+_seintraegeneu+_seintraege.substring((int) (40),(int) (70));
 }else if(_ibereich==5) { 
 //BA.debugLineNum = 1186;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (40),(int) (50),_iposition,_scharacter);
 //BA.debugLineNum = 1187;BA.debugLine="sEintraege = sEintraege.SubString2(0, 40) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (40))+_seintraegeneu+_seintraege.substring((int) (50),(int) (70));
 }else if(_ibereich==6) { 
 //BA.debugLineNum = 1189;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (50),(int) (60),_iposition,_scharacter);
 //BA.debugLineNum = 1190;BA.debugLine="sEintraege = sEintraege.SubString2(0, 50) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (50))+_seintraegeneu+_seintraege.substring((int) (60),(int) (70));
 }else if(_ibereich==7) { 
 //BA.debugLineNum = 1192;BA.debugLine="sEintraegeNeu = Helper.AusbildungZustandNeu(sEin";
_seintraegeneu = mostCurrent._helper._ausbildungzustandneu /*String*/ (_ba,_seintraege,(int) (60),(int) (70),_iposition,_scharacter);
 //BA.debugLineNum = 1193;BA.debugLine="sEintraege = sEintraege.SubString2(0, 60) & sEin";
_seintraege = _seintraege.substring((int) (0),(int) (60))+_seintraegeneu;
 };
 //BA.debugLineNum = 1197;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 1199;BA.debugLine="Dim sDatum As String";
_sdatum = "";
 //BA.debugLineNum = 1200;BA.debugLine="sDatum = DateTime.Date(DateTime.Now)";
_sdatum = anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 1202;BA.debugLine="If bInsert Then";
if (_binsert) { 
 //BA.debugLineNum = 1203;BA.debugLine="sUpdate = \"INSERT INTO LernKontrolle (\" & _";
_supdate = "INSERT INTO LernKontrolle ("+"SchuelerID, "+"LernString, "+"AendDatum, "+"FahrlNr, "+"DatenErfasst ) "+"VALUES ( "+" "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+",  "+"'"+_seintraege+"', "+"'"+_sdatum+"', "+"(Select OID FROM Fahrlehrer WHERE IsSelected = 1), "+"'-1' "+") ";
 }else {
 //BA.debugLineNum = 1217;BA.debugLine="sUpdate = \"UPDATE LernKontrolle \" & _ 					\"SET";
_supdate = "UPDATE LernKontrolle "+"SET LernString = '"+_seintraege+"', "+"AendDatum = '"+_sdatum+"', "+"DatenErfasst = '-1' "+"WHERE SchuelerID = "+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 };
 //BA.debugLineNum = 1227;BA.debugLine="Try";
try { //BA.debugLineNum = 1228;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 1230;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e56) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e56); //BA.debugLineNum = 1232;BA.debugLine="Log(LastException.Message) 'no changes will b";
anywheresoftware.b4a.keywords.Common.LogImpl("016777310",anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1233;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1236;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1238;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1239;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatebvfdata(anywheresoftware.b4a.BA _ba,String _sdate,String _sschueler,int _cbbesonderheinsteigen,int _cbeinstellen,int _cblenkrad,int _cbspiegel,int _cbkopfstuetze,int _cbsitz,int _cblenkradhaltung,int _cbpedale,int _cbgurt,int _cbschaltwaehlhebel,int _cbzuendschloss,int _cbmotoranlassen,int _cbanfahranhalte,int _cbschaltuebg,int _cbhoch1_2,int _cbhoch2_3,int _cbhoch3_4,int _cbrunter4_3,int _cbrunter3_2,int _cbrunter2_1,int _cbrunter4_2,int _cbrunter4_1,int _cbrunter3_1,int _cblenkuebung,int _cbumkehren,int _cbeinparkenlaengs,int _cblvorwaertsrechts,int _cblrueckwaertslinks,int _cblrueckwaertsrechts,int _cblvorwaertslinks,int _cbrueckwaertsfahren,int _cbeinparkenquer,int _cbqvorwaertsrechts,int _cbqrueckwaertslinks,int _cbqrueckwaertsrechts,int _cbqvorwaertslinks,int _cbgefahrbremsung,int _cbrollenschalten,int _cbbremsschalten,int _cbbremsuebung,int _cbdegressiv,int _cbzielbremsung,int _cbgefahrsituation,int _cbgefaelle,int _cbanhalten,int _cbanfahren,int _cbrueckwaerts,int _cbsichern,int _cbschalten,int _cbsteigung,int _cbstanhalten,int _cbstanfahren,int _cbstrueckwaerts,int _cbstsichern,int _cbstschalten,int _cbtastgeschw,int _cbbedienkontroll,int _cboertlichbesonder,int _cbfahrbahnbenutzung,int _cbeinordnen,int _cbmarkierungen,int _cbfahrstreifenwechsel,int _cblinks,int _cbrechts,int _cbvorbeifueberholen,int _cbabbiegen,int _cbabrechts,int _cbablinks,int _cbmehrspurig,int _cbradweg,int _cbsonderstreifen,int _cbstrassenbahn,int _cbeinbahnstrasse,int _cbvorfahrt,int _cbrechtsvorlinks,int _cbgruenpfeil,int _cbpolizeibeamte,int _cbgruenpfeilschild,int _cbgeschwabstand,int _cbsituationverkehrstn,int _cbfussgaengerueberweg,int _cboeffentlverkehrsm,int _cbaelterebehinderte,int _cbeinbahnstrradfahrer,int _cbkinder,int _cbschulbus,int _cbradfahrermofa,int _cbverkehrsberuhigt,int _cbschwierigeverkehrsf,int _cbengpass,int _cbkreisverkehr,int _cbbahnuebergangwarte,int _cbkritischeverkehrss,int _cbhauptverkehrszt,int _cbpartnerverhalten,int _cbschwungnutzen,int _cbfussgaengerschutzb,int _cbangepasstegeschw,int _cbabstand,int _cbulvorne,int _cbulhinten,int _cbulseitlich,int _cbbeobachtspiegel,int _cbverkehrszeichen,int _cbkreuzungeinmuend,int _cbkurven,int _cbsteigungen,int _cbulgefaelle,int _cballeen,int _cbueberholen,int _cbbesonderesituat,int _cbliegenblsichern,int _cbeinfahrenortsch,int _cbfussgaenger,int _cbwildtiere,int _cbbesondereanford,int _cbleistungsgrenze,int _cborientierung,int _cbablenkung,int _cbfahrtplanung,int _cbeinfahrtab,int _cbabfahrbahnwechsel,int _cbgeschwindigkeit,int _cbababstand,int _cbabvorne,int _cbabhinten,int _cbabseitlich,int _cbabueberholen,int _cbschilder,int _cbvorbeifahren,int _cbrastparktank,int _cbverhunfall,int _cbdichterverkehr,int _cbbesondersituat,int _cbbesonderanford,int _cbableistungsgrenze,int _cbkonfliktsitua,int _cbabablenkung,int _cbbeleuchtung,int _cbkontrolle,int _cbeinstell,int _cbbenutzung,int _cbfernlicht,int _cbverlassenbab,int _cbbeleuchtstrasse,int _cbunbeleuchtstrasse,int _cbparken,int _cbdubesondersituat,int _cbschlechtewitterung,int _cbtiere,int _cbbahnuebergaenge,int _cbunbelverkehrtn,int _cbdubesonderanfor,int _cbblendung,int _cbduorientierung,int _cbabschlussbesp,int _cbselbstfahren,int _cbinnerorts,int _cbausserorts,int _cbverantwfahren,int _cbtestfpruef,int _cbfakt,int _cbandere,int _cbwiederholung,int _cbleistungsbew,int _cbreifen,int _cbeinausschalten,int _cbfunktionpruefen,int _cbstandlicht,int _cbnebelschluss,int _cbblinker,int _cbabblendlicht,int _cbwarnblicke,int _cbhupe,int _cbbsfernlicht,int _cbschlussleuchte,int _cbbremslicht,int _cbkontrolllbenenn,int _cbrueckstrahler,int _cbvorhandensein,int _cbbeschaedigung,int _cblenkung,int _cblenkschlentriegeln,int _cbprueflenkspiel,int _cbfunktbremse,int _cbbetriebsbremse,int _cbfeststellbremse,int _cbanlegengurt,int _cbrichtigsitz,int _cbeinstellrueckspiegel,int _cbeinkopfstuetze,int _cbeinlenkrad,int _cbbedienenagg,int _cbheizung,int _cbheckheizung,int _cbbehsonderaus,int _cblueftung,int _cbklimaanlage,int _cbenergienutzung,int _cbkeineunnverbr,int _cbrechtztabsch,int _cbmotorraum,int _cbmotoroel,int _cbkuehlmittel,int _cbscheibenwaschm,int _cbtanken,int _cbbremsen,int _cbsicherungsmittel,int _cbwarndreieck,int _cbbordwerkzeug,int _cbzusaetzlichaus,int _cbverbandskasten,int _cbaussenkontrolle,int _cbscheibenwischer,int _cbkennzeichen,int _cbcheckspiegel,int _cbcheckbeleuchtung,int _cbladung,int _cbladungssicherung,int _cbkenntlichmachung,int _cbfahreschlwitt,int _cbwittlueftung,int _cbwittscheiben,int _cbregen,int _cbwasserlachen,int _cbwindsturm,int _cbmatchschnee,int _cbeis,int _cbwittbeleuchtung,String _etnotizen,int _iausbkontrolleoid) throws Exception{
String _supdate = "";
boolean _bresult = false;
 //BA.debugLineNum = 3584;BA.debugLine="Sub UpdateBVFData(sDate As String, sSchueler As St";
 //BA.debugLineNum = 3604;BA.debugLine="Dim sUpdate As String";
_supdate = "";
 //BA.debugLineNum = 3605;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 3607;BA.debugLine="sUpdate = \"UPDATE AusbKontrolle \" & _ 							\"SET";
_supdate = "UPDATE AusbKontrolle "+"SET 	SchuelerID = '"+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+"', "+"MatchCode = '"+_sschueler+"', "+"Datum = '"+_sdate+"', "+"BesonderhEinsteigen = "+BA.NumberToString(_cbbesonderheinsteigen)+", "+"Einstellen = "+BA.NumberToString(_cbeinstellen)+", "+"Lenkrad = "+BA.NumberToString(_cblenkrad)+", "+"Spiegel = "+BA.NumberToString(_cbspiegel)+", "+"Kopfstuetze = "+BA.NumberToString(_cbkopfstuetze)+", "+"Sitz = "+BA.NumberToString(_cbsitz)+", "+"Lenkradhalt = "+BA.NumberToString(_cblenkradhaltung)+", "+"Pedale = "+BA.NumberToString(_cbpedale)+", "+"Gurt = "+BA.NumberToString(_cbgurt)+", "+"SchaltWaehlhebel = "+BA.NumberToString(_cbschaltwaehlhebel)+", "+"Zuendschloss = "+BA.NumberToString(_cbzuendschloss)+", "+"MotorAnlassen = "+BA.NumberToString(_cbmotoranlassen)+", "+"AnfahrAnhalte = "+BA.NumberToString(_cbanfahranhalte)+", "+"Schaltuebg = "+BA.NumberToString(_cbschaltuebg)+", "+"hoch1_2 = "+BA.NumberToString(_cbhoch1_2)+", "+"hoch2_3 = "+BA.NumberToString(_cbhoch2_3)+", "+"hoch3_4 = "+BA.NumberToString(_cbhoch3_4)+", "+"runter4_3 = "+BA.NumberToString(_cbrunter4_3)+", "+"runter3_2 = "+BA.NumberToString(_cbrunter3_2)+", "+"runter2_1 = "+BA.NumberToString(_cbrunter2_1)+", "+"runter4_2 = "+BA.NumberToString(_cbrunter4_2)+", "+"runter4_1 = "+BA.NumberToString(_cbrunter4_1)+", "+"runter3_1 = "+BA.NumberToString(_cbrunter3_1)+", "+"Lenkuebg = "+BA.NumberToString(_cblenkuebung)+", "+"Umkehren = "+BA.NumberToString(_cbumkehren)+", "+"EinpLaengs = "+BA.NumberToString(_cbeinparkenlaengs)+", "+"LVorwRechts = "+BA.NumberToString(_cblvorwaertsrechts)+", "+"LRueckwLinks = "+BA.NumberToString(_cblrueckwaertslinks)+", "+"LRueckwRechts = "+BA.NumberToString(_cblrueckwaertsrechts)+", "+"LVorwLinks = "+BA.NumberToString(_cblvorwaertslinks)+", "+"Rueckfahren = "+BA.NumberToString(_cbrueckwaertsfahren)+", "+"EinpQuer = "+BA.NumberToString(_cbeinparkenquer)+", "+"QVvorwRechts = "+BA.NumberToString(_cbqvorwaertsrechts)+", "+"QRueckwLinks = "+BA.NumberToString(_cbqrueckwaertslinks)+", "+"QRueckwRechts = "+BA.NumberToString(_cbqrueckwaertsrechts)+", "+"QVorwLinks = "+BA.NumberToString(_cbqvorwaertslinks)+", "+"Gefahrbrems = "+BA.NumberToString(_cbgefahrbremsung)+", "+"RollenUndSchalten = "+BA.NumberToString(_cbrollenschalten)+", "+"AbbrUndSchalten = "+BA.NumberToString(_cbbremsschalten)+", "+"Bremsuebung = "+BA.NumberToString(_cbbremsuebung)+", "+"degressiv = "+BA.NumberToString(_cbdegressiv)+", "+"Zielbremsung = "+BA.NumberToString(_cbzielbremsung)+", "+"GefahrSituation = "+BA.NumberToString(_cbgefahrsituation)+", "+"Gefaelle = "+BA.NumberToString(_cbgefaelle)+", "+"GeAnhalten = "+BA.NumberToString(_cbanhalten)+", "+"GeAnfahren = "+BA.NumberToString(_cbanfahren)+", "+"GeRueckw = "+BA.NumberToString(_cbrueckwaerts)+", "+"GeSichern = "+BA.NumberToString(_cbsichern)+", "+"GeSchalten = "+BA.NumberToString(_cbschalten)+", "+"Steigung = "+BA.NumberToString(_cbsteigung)+", "+"StAnhalten = "+BA.NumberToString(_cbstanhalten)+", "+"StAnfahren = "+BA.NumberToString(_cbstanfahren)+", "+"StRueckw = "+BA.NumberToString(_cbstrueckwaerts)+", "+"StSichern = "+BA.NumberToString(_cbstsichern)+", "+"StSchalten = "+BA.NumberToString(_cbstschalten)+", "+"Tastgeschw = "+BA.NumberToString(_cbtastgeschw)+", "+"BedienungKontrolle = "+BA.NumberToString(_cbbedienkontroll)+", "+"OertlicheBesonder = "+BA.NumberToString(_cboertlichbesonder)+", "+"FahrbahnNutz = "+BA.NumberToString(_cbfahrbahnbenutzung)+", "+"Einordnen = "+BA.NumberToString(_cbeinordnen)+", "+"Markierungen = "+BA.NumberToString(_cbmarkierungen)+", "+"FahrstreifenWechsel = "+BA.NumberToString(_cbfahrstreifenwechsel)+", "+"FahrLinks = "+BA.NumberToString(_cblinks)+", "+"FahrRechts = "+BA.NumberToString(_cbrechts)+", "+"Vorbeifahren = "+BA.NumberToString(_cbvorbeifueberholen)+", "+"Abbiegen = "+BA.NumberToString(_cbabbiegen)+", "+"AbbRechts = "+BA.NumberToString(_cbabrechts)+", "+"AbbLinks = "+BA.NumberToString(_cbablinks)+", "+"FahrMehrSp = "+BA.NumberToString(_cbmehrspurig)+", "+"FahrRadweg = "+BA.NumberToString(_cbradweg)+", "+"FahrSonderStr = "+BA.NumberToString(_cbsonderstreifen)+", "+"FahrStrassenb = "+BA.NumberToString(_cbstrassenbahn)+", "+"FahrEinbahnstr = "+BA.NumberToString(_cbeinbahnstrasse)+", "+"Vorfahrt = "+BA.NumberToString(_cbvorfahrt)+", "+"RechtsVorLinks = "+BA.NumberToString(_cbrechtsvorlinks)+", "+"Gruenpfeil = "+BA.NumberToString(_cbgruenpfeil)+", "+"PolizeiBeamte = "+BA.NumberToString(_cbpolizeibeamte)+", "+"GruenpfeilSchild = "+BA.NumberToString(_cbgruenpfeilschild)+", "+"GeschwAbstand = "+BA.NumberToString(_cbgeschwabstand)+", "+"SituationTN = "+BA.NumberToString(_cbsituationverkehrstn)+", "+"Fussgaenger = "+BA.NumberToString(_cbfussgaengerueberweg)+", "+"OeffentVKTN = "+BA.NumberToString(_cboeffentlverkehrsm)+", "+"AeltereBehind = "+BA.NumberToString(_cbaelterebehinderte)+", "+"EinbahnStrRadf = "+BA.NumberToString(_cbeinbahnstrradfahrer)+", "+"Kinder = "+BA.NumberToString(_cbkinder)+", "+"Schulbus = "+BA.NumberToString(_cbschulbus)+", "+"RadfMofa = "+BA.NumberToString(_cbradfahrermofa)+", "+"VerkBeruhigt = "+BA.NumberToString(_cbverkehrsberuhigt)+", "+"SchwierigeVKFuehrung = "+BA.NumberToString(_cbschwierigeverkehrsf)+", "+"Engpass = "+BA.NumberToString(_cbengpass)+", "+"Kreisverkehr = "+BA.NumberToString(_cbkreisverkehr)+", "+"Bahnuebergang = "+BA.NumberToString(_cbbahnuebergangwarte)+", "+"KritischeVKSit = "+BA.NumberToString(_cbkritischeverkehrss)+", "+"HauptVKZeit = "+BA.NumberToString(_cbhauptverkehrszt)+", "+"PartnerVerhalten = "+BA.NumberToString(_cbpartnerverhalten)+", "+"SchwungNutzen = "+BA.NumberToString(_cbschwungnutzen)+", "+"FussgaengerSchutz = "+BA.NumberToString(_cbfussgaengerschutzb)+", "+"AngepGeschw = "+BA.NumberToString(_cbangepasstegeschw)+", "+"Abstand = "+BA.NumberToString(_cbabstand)+", "+"AbstVorne = "+BA.NumberToString(_cbulvorne)+", "+"AbstHinten = "+BA.NumberToString(_cbulhinten)+", "+"AbstSeite = "+BA.NumberToString(_cbulseitlich)+", "+"BeobSpiegel = "+BA.NumberToString(_cbbeobachtspiegel)+", "+"VerkehrsZeich = "+BA.NumberToString(_cbverkehrszeichen)+", "+"KreuzMuend = "+BA.NumberToString(_cbkreuzungeinmuend)+", "+"Kurven = "+BA.NumberToString(_cbkurven)+", "+"Steigungen = "+BA.NumberToString(_cbsteigungen)+", "+"BesGefaelle = "+BA.NumberToString(_cbulgefaelle)+", "+"Alleen = "+BA.NumberToString(_cballeen)+", "+"Ueberholen = "+BA.NumberToString(_cbueberholen)+", "+"BesSituation = "+BA.NumberToString(_cbbesonderesituat)+", "+"LiegeblAbsicher = "+BA.NumberToString(_cbliegenblsichern)+", "+"EinfOrtschaft = "+BA.NumberToString(_cbeinfahrenortsch)+", "+"BesFussgaenger = "+BA.NumberToString(_cbfussgaenger)+", "+"WildTiere = "+BA.NumberToString(_cbwildtiere)+", "+"BesAnforderung = "+BA.NumberToString(_cbbesondereanford)+", "+"Leistungsgr = "+BA.NumberToString(_cbleistungsgrenze)+", "+"Orientierung = "+BA.NumberToString(_cborientierung)+", "+"Ablenkung = "+BA.NumberToString(_cbablenkung)+", "+"Fahrtplanung = "+BA.NumberToString(_cbfahrtplanung)+", "+"EinfahBAB = "+BA.NumberToString(_cbeinfahrtab)+", "+"FahrStrWechsel = "+BA.NumberToString(_cbabfahrbahnwechsel)+", "+"Geschwindig = "+BA.NumberToString(_cbgeschwindigkeit)+", "+"AbstandBAB = "+BA.NumberToString(_cbababstand)+", "+"VorneBAB = "+BA.NumberToString(_cbabvorne)+", "+"HintenBAB = "+BA.NumberToString(_cbabhinten)+", "+"SeiteBAB = "+BA.NumberToString(_cbabseitlich)+", "+"BABUeberholen = "+BA.NumberToString(_cbabueberholen)+", "+"SchildMarkierung = "+BA.NumberToString(_cbschilder)+", "+"VoebeifAnschluss = "+BA.NumberToString(_cbvorbeifahren)+", "+"RastParkTank = "+BA.NumberToString(_cbrastparktank)+", "+"VerhaltenUnfall = "+BA.NumberToString(_cbverhunfall)+", "+"DichtStau = "+BA.NumberToString(_cbdichterverkehr)+", "+"BesSitBAB = "+BA.NumberToString(_cbbesondersituat)+", "+"BesAnfordBAB = "+BA.NumberToString(_cbbesonderanford)+", "+"LeistungsgrBAB = "+BA.NumberToString(_cbableistungsgrenze)+", "+"KonfliktBAB = "+BA.NumberToString(_cbkonfliktsitua)+", "+"AblenkungBAB = "+BA.NumberToString(_cbabablenkung)+", "+"VerlassBAB = "+BA.NumberToString(_cbverlassenbab)+", "+"Beleuchtung = "+BA.NumberToString(_cbbeleuchtung)+", "+"KontrolleBel = "+BA.NumberToString(_cbkontrolle)+", "+"EinstellBel = "+BA.NumberToString(_cbeinstell)+", "+"BenutzBel = "+BA.NumberToString(_cbbenutzung)+", "+"FernlichtBel = "+BA.NumberToString(_cbfernlicht)+", "+"BelStrasse = "+BA.NumberToString(_cbbeleuchtstrasse)+", "+"UnbelStrasse = "+BA.NumberToString(_cbunbeleuchtstrasse)+", "+"Parken = "+BA.NumberToString(_cbparken)+", "+"BesSitDunkel = "+BA.NumberToString(_cbdubesondersituat)+", "+"SchlechWittDun = "+BA.NumberToString(_cbschlechtewitterung)+", "+"TiereDun = "+BA.NumberToString(_cbtiere)+", "+"BahnueberDun = "+BA.NumberToString(_cbbahnuebergaenge)+", "+"UnbelVKTN = "+BA.NumberToString(_cbunbelverkehrtn)+", "+"BesAnforDun = "+BA.NumberToString(_cbdubesonderanfor)+", "+"Blendung = "+BA.NumberToString(_cbblendung)+", "+"OrientDun = "+BA.NumberToString(_cbduorientierung)+", "+"AbschlussbespDun = "+BA.NumberToString(_cbabschlussbesp)+", "+"SelbstFahren = "+BA.NumberToString(_cbselbstfahren)+", "+"Innerorts = "+BA.NumberToString(_cbinnerorts)+", "+"Ausserorts = "+BA.NumberToString(_cbausserorts)+", "+"VerantwFahren = "+BA.NumberToString(_cbverantwfahren)+", "+"TestfPruefung = "+BA.NumberToString(_cbtestfpruef)+", "+"FAKT = "+BA.NumberToString(_cbfakt)+", "+"andere = "+BA.NumberToString(_cbandere)+", "+"WiederhVertief = "+BA.NumberToString(_cbwiederholung)+", "+"Leistungsbew = "+BA.NumberToString(_cbleistungsbew)+", "+"Reifen = "+BA.NumberToString(_cbreifen)+", "+"EinAusschalten = "+BA.NumberToString(_cbeinausschalten)+", "+"FunkPruefen = "+BA.NumberToString(_cbfunktionpruefen)+", "+"Standlicht = "+BA.NumberToString(_cbstandlicht)+", "+"Nebelschl = "+BA.NumberToString(_cbnebelschluss)+", "+"Blinker = "+BA.NumberToString(_cbblinker)+", "+"Abblendlicht = "+BA.NumberToString(_cbabblendlicht)+", "+"Warnblick = "+BA.NumberToString(_cbwarnblicke)+", "+"Hupe = "+BA.NumberToString(_cbhupe)+", "+"Fernlicht = "+BA.NumberToString(_cbbsfernlicht)+", "+"SchlussLeuchte = "+BA.NumberToString(_cbschlussleuchte)+", "+"Bremslicht = "+BA.NumberToString(_cbbremslicht)+", "+"KontrollLeuchten = "+BA.NumberToString(_cbkontrolllbenenn)+", "+"Rueckstrahler = "+BA.NumberToString(_cbrueckstrahler)+", "+"Vorhanden = "+BA.NumberToString(_cbvorhandensein)+", "+"Beschaedigt = "+BA.NumberToString(_cbbeschaedigung)+", "+"Lenkung = "+BA.NumberToString(_cblenkung)+", "+"LenkschlEntriegel = "+BA.NumberToString(_cblenkschlentriegeln)+", "+"Lenkspiel = "+BA.NumberToString(_cbprueflenkspiel)+", "+"FunkBremsen = "+BA.NumberToString(_cbfunktbremse)+", "+"BetriebBremse = "+BA.NumberToString(_cbbetriebsbremse)+", "+"FeststellBremse = "+BA.NumberToString(_cbfeststellbremse)+", "+"Sitzeinstellung = "+BA.NumberToString(_cbrichtigsitz)+", "+"EinstRueckspiegel = "+BA.NumberToString(_cbeinstellrueckspiegel)+", "+"EinstKopf = "+BA.NumberToString(_cbeinkopfstuetze)+", "+"EinstLenksrad = "+BA.NumberToString(_cbeinlenkrad)+", "+"AnlegenGurt = "+BA.NumberToString(_cbanlegengurt)+", "+"BedienHeizung = "+BA.NumberToString(_cbbedienenagg)+", "+"Heizung = "+BA.NumberToString(_cbheizung)+", "+"HeckHeizung = "+BA.NumberToString(_cbheckheizung)+", "+"BehSonderAusstg = "+BA.NumberToString(_cbbehsonderaus)+", "+"Lueftung = "+BA.NumberToString(_cblueftung)+", "+"Klima = "+BA.NumberToString(_cbklimaanlage)+", "+"EnergiespNutz = "+BA.NumberToString(_cbenergienutzung)+", "+"KeineUnnVerbr = "+BA.NumberToString(_cbkeineunnverbr)+", "+"RechtzAbschalten = "+BA.NumberToString(_cbrechtztabsch)+", "+"CheckMotorraum = "+BA.NumberToString(_cbmotorraum)+", "+"Motoroel = "+BA.NumberToString(_cbmotoroel)+", "+"Kuehlmittel = "+BA.NumberToString(_cbkuehlmittel)+", "+"Wischwasser = "+BA.NumberToString(_cbscheibenwaschm)+", "+"Tanken = "+BA.NumberToString(_cbtanken)+", "+"SicherungsMittel = "+BA.NumberToString(_cbsicherungsmittel)+", "+"WarnDreieck = "+BA.NumberToString(_cbwarndreieck)+", "+"Bordwerkzeug = "+BA.NumberToString(_cbbordwerkzeug)+", "+"zusaetzAusruest = "+BA.NumberToString(_cbzusaetzlichaus)+", "+"Verbandskasten = "+BA.NumberToString(_cbverbandskasten)+", "+"AussenKontrolle = "+BA.NumberToString(_cbaussenkontrolle)+", "+"Scheiben = "+BA.NumberToString(_cbscheibenwischer)+", "+"KennzeichenHUAU = "+BA.NumberToString(_cbkennzeichen)+", "+"CheckSpiegel = "+BA.NumberToString(_cbcheckspiegel)+", "+"CheckBeleuchtung = "+BA.NumberToString(_cbcheckbeleuchtung)+", "+"Bremsung = "+BA.NumberToString(_cbbremsen)+", "+"Ladung = "+BA.NumberToString(_cbladung)+", "+"Sicherung = "+BA.NumberToString(_cbladungssicherung)+", "+"KenntlichMachung = "+BA.NumberToString(_cbkenntlichmachung)+", "+"FahrenSchelchtWetter = "+BA.NumberToString(_cbfahreschlwitt)+", "+"WittLueftung = "+BA.NumberToString(_cbwittlueftung)+", "+"ScheibenWischer = "+BA.NumberToString(_cbwittscheiben)+", "+"RegenSprueh = "+BA.NumberToString(_cbregen)+", "+"Wasserlachen = "+BA.NumberToString(_cbwasserlachen)+", "+"WindSturm = "+BA.NumberToString(_cbwindsturm)+", "+"SchneeMatsch = "+BA.NumberToString(_cbmatchschnee)+", "+"Eis = "+BA.NumberToString(_cbeis)+", "+"WittBeleucht = "+BA.NumberToString(_cbwittbeleuchtung)+", "+"Kommentar = '"+_etnotizen+"' "+"WHERE OID = "+BA.NumberToString(_iausbkontrolleoid);
 //BA.debugLineNum = 3843;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 3844;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3845;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 3849;BA.debugLine="Try";
try { //BA.debugLineNum = 3850;BA.debugLine="Main.SQL1.ExecNonQuery(sUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_supdate);
 //BA.debugLineNum = 3852;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e12) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e12); //BA.debugLineNum = 3854;BA.debugLine="Log(\"Fehler beim speichern der Zahlungen \" & Las";
anywheresoftware.b4a.keywords.Common.LogImpl("019661070","Fehler beim speichern der Zahlungen "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 3855;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 3858;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 3859;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 3860;BA.debugLine="End Sub";
return false;
}
public static String  _writetreffpunkte(anywheresoftware.b4a.BA _ba,String[] _atreffpunkt) throws Exception{
int _anz = 0;
 //BA.debugLineNum = 3041;BA.debugLine="Sub WriteTreffpunkte(aTreffpunkt() As String)";
 //BA.debugLineNum = 3042;BA.debugLine="Dim anz As Int";
_anz = 0;
 //BA.debugLineNum = 3044;BA.debugLine="anz = InsertNeuerTreffpunkt(aTreffpunkt(0), aTref";
_anz = _insertneuertreffpunkt(_ba,_atreffpunkt[(int) (0)],(int)(Double.parseDouble(_atreffpunkt[(int) (1)])));
 //BA.debugLineNum = 3045;BA.debugLine="End Sub";
return "";
}
public static int  _zahlungenidholen(anywheresoftware.b4a.BA _ba,int _iposition,String _sauswahl) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _izahlungsid = 0;
int _i = 0;
 //BA.debugLineNum = 983;BA.debugLine="Sub ZahlungenIDHolen(iPosition As Int, sAuswahl As";
 //BA.debugLineNum = 984;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 985;BA.debugLine="Dim iZahlungsID As Int";
_izahlungsid = 0;
 //BA.debugLineNum = 987;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 988;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 989;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 992;BA.debugLine="If sAuswahl = \"Alle\" Then";
if ((_sauswahl).equals("Alle")) { 
 //BA.debugLineNum = 994;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT z.OID \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT z.OID "+"FROM Zahlungen z "+"WHERE z.Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1) "+"ORDER BY z.ZahlDatum")));
 //BA.debugLineNum = 998;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step9 = 1;
final int limit9 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 999;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1000;BA.debugLine="If i = iPosition Then";
if (_i==_iposition) { 
 //BA.debugLineNum = 1001;BA.debugLine="iZahlungsID = cur.GetInt(\"OID\")";
_izahlungsid = _cur.GetInt("OID");
 };
 }
};
 }else {
 //BA.debugLineNum = 1005;BA.debugLine="cur = Main.SQL1.ExecQuery(\"SELECT z.OID \" & _";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("SELECT z.OID "+"FROM Zahlungen z "+"WHERE z.ZahlDatum like '"+_sauswahl+"' "+"AND z.Fahrlehrer_ID = (Select OID FROM Fahrlehrer WHERE IsSelected = 1) "+"ORDER BY z.ZahlDatum")));
 //BA.debugLineNum = 1010;BA.debugLine="For i = 0 To cur.RowCount - 1";
{
final int step17 = 1;
final int limit17 = (int) (_cur.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit17 ;_i = _i + step17 ) {
 //BA.debugLineNum = 1011;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 1012;BA.debugLine="If i = iPosition Then";
if (_i==_iposition) { 
 //BA.debugLineNum = 1013;BA.debugLine="iZahlungsID = cur.GetInt(\"OID\")";
_izahlungsid = _cur.GetInt("OID");
 };
 }
};
 };
 //BA.debugLineNum = 1018;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 1019;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1021;BA.debugLine="Return iZahlungsID";
if (true) return _izahlungsid;
 //BA.debugLineNum = 1022;BA.debugLine="End Sub";
return 0;
}
public static boolean  _zahlungenspeichern(anywheresoftware.b4a.BA _ba,String _sbetrag,String _sdatum,String _szahlungfuer) throws Exception{
String _sinsertupdate = "";
boolean _bresult = false;
int _izahlungid = 0;
 //BA.debugLineNum = 886;BA.debugLine="Sub ZahlungenSpeichern(sBetrag As String, sDatum A";
 //BA.debugLineNum = 887;BA.debugLine="Dim sInsertUpdate As String";
_sinsertupdate = "";
 //BA.debugLineNum = 888;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 890;BA.debugLine="Dim iZahlungID As Int";
_izahlungid = 0;
 //BA.debugLineNum = 892;BA.debugLine="If Main.bZahlBearbeiten Then";
if (mostCurrent._main._bzahlbearbeiten /*boolean*/ ) { 
 //BA.debugLineNum = 894;BA.debugLine="If Main.bZahlDatum Then";
if (mostCurrent._main._bzahldatum /*boolean*/ ) { 
 //BA.debugLineNum = 895;BA.debugLine="sDatum = \"Alle\"";
_sdatum = "Alle";
 };
 //BA.debugLineNum = 898;BA.debugLine="iZahlungID = ZahlungenIDHolen(Main.iZahlungsPosi";
_izahlungid = _zahlungenidholen(_ba,mostCurrent._main._izahlungsposition /*int*/ ,_sdatum);
 //BA.debugLineNum = 900;BA.debugLine="sInsertUpdate = \"UPDATE Zahlungen \" & _ 							\"";
_sinsertupdate = "UPDATE Zahlungen "+"SET 	Betrag = '"+_sbetrag+"', "+"ZaFuer_ID = (SELECT OID FROM ZahlungenFuer WHERE Bezeichnung like '"+_szahlungfuer+"') "+"WHERE OID = "+BA.NumberToString(_izahlungid);
 }else {
 //BA.debugLineNum = 905;BA.debugLine="sInsertUpdate = \"INSERT INTO Zahlungen (\" & _";
_sinsertupdate = "INSERT INTO Zahlungen ("+"Schueler_ID, "+"MatchCode, "+"ZahlDatum, "+"Betrag, "+"StSchl, "+"StSatz, "+"ZaArt, "+"ZaFuer_ID, "+"Fahrlehrer_ID) "+"Values ( "+"'"+BA.NumberToString(mostCurrent._main._iausgewaehlterschuelrid /*int*/ )+"', "+"'"+mostCurrent._main._sausgewaehlterschueler /*String*/ +"', "+"'"+_sdatum+"', "+"'"+_sbetrag+"', "+"null, "+"null, "+"0, "+"(SELECT OID FROM ZahlungenFuer WHERE Bezeichnung like '"+_szahlungfuer+"'), "+"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1) "+") ";
 };
 //BA.debugLineNum = 928;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 929;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 930;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 934;BA.debugLine="Try";
try { //BA.debugLineNum = 935;BA.debugLine="Main.SQL1.ExecNonQuery(sInsertUpdate)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sinsertupdate);
 //BA.debugLineNum = 937;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e21) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e21); //BA.debugLineNum = 939;BA.debugLine="Log(\"Fehler beim speichern der Zahlungen \" &";
anywheresoftware.b4a.keywords.Common.LogImpl("016384053","Fehler beim speichern der Zahlungen "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 940;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 943;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 945;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 946;BA.debugLine="End Sub";
return false;
}
public static boolean  _zahlungloeschen(anywheresoftware.b4a.BA _ba,int _iposition,String _sauswahl) throws Exception{
boolean _bresult = false;
int _ipositionid = 0;
String _sdelete = "";
 //BA.debugLineNum = 1025;BA.debugLine="Sub ZahlungLoeschen(iPosition As Int, sAuswahl As";
 //BA.debugLineNum = 1026;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1027;BA.debugLine="Dim iPositionID As Int";
_ipositionid = 0;
 //BA.debugLineNum = 1028;BA.debugLine="Dim sDelete As String";
_sdelete = "";
 //BA.debugLineNum = 1031;BA.debugLine="iPositionID = ZahlungenIDHolen(iPosition, sAuswah";
_ipositionid = _zahlungenidholen(_ba,_iposition,_sauswahl);
 //BA.debugLineNum = 1033;BA.debugLine="sDelete = \"DELETE FROM Zahlungen \" & _ 				\"WHERE";
_sdelete = "DELETE FROM Zahlungen "+"WHERE OID = "+BA.NumberToString(_ipositionid);
 //BA.debugLineNum = 1036;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 1037;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1038;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 1042;BA.debugLine="Try";
try { //BA.debugLineNum = 1043;BA.debugLine="Main.SQL1.ExecNonQuery(sDelete)";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(_sdelete);
 //BA.debugLineNum = 1045;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 1047;BA.debugLine="Log(\"Fehler beim löschen des Zahlungseintrags";
anywheresoftware.b4a.keywords.Common.LogImpl("016580630","Fehler beim löschen des Zahlungseintrags "+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 //BA.debugLineNum = 1048;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1051;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 1053;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1054;BA.debugLine="End Sub";
return false;
}
public static String[]  _zahlungpositionholen(anywheresoftware.b4a.BA _ba,int _iposition,Object _ovalue,String _sauswahl) throws Exception{
String[] _aresult = null;
String _sselect = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _ipositionid = 0;
 //BA.debugLineNum = 949;BA.debugLine="Sub ZahlungPositionHolen(iPosition As Int, oValue";
 //BA.debugLineNum = 950;BA.debugLine="Dim aResult() As String";
_aresult = new String[(int) (0)];
java.util.Arrays.fill(_aresult,"");
 //BA.debugLineNum = 951;BA.debugLine="Dim sSelect As String";
_sselect = "";
 //BA.debugLineNum = 952;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 953;BA.debugLine="Dim iPositionID As Int";
_ipositionid = 0;
 //BA.debugLineNum = 955;BA.debugLine="iPositionID = ZahlungenIDHolen(iPosition, sAuswah";
_ipositionid = _zahlungenidholen(_ba,_iposition,_sauswahl);
 //BA.debugLineNum = 958;BA.debugLine="sSelect = \"SELECT 	z.MatchCode, \" & _ 					\"z.Bet";
_sselect = "SELECT 	z.MatchCode, "+"z.Betrag, "+"zf.Bezeichnung "+"FROM Zahlungen z "+"INNER JOIN ZahlungenFuer zf ON zf.OID = z.ZaFuer_ID "+"WHERE z.OID = "+BA.NumberToString(_ipositionid);
 //BA.debugLineNum = 965;BA.debugLine="If (Main.SQL1.IsInitialized = False) Then";
if ((mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 966;BA.debugLine="Main.SQL1.Initialize(Main.SourceFolder, \"FaData2";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Initialize(mostCurrent._main._sourcefolder /*String*/ ,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 967;BA.debugLine="Main.SQL1.ExecQuery(\"PRAGMA journal_mode=OFF\")";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery("PRAGMA journal_mode=OFF");
 };
 //BA.debugLineNum = 970;BA.debugLine="cur = Main.SQL1.ExecQuery(sSelect)";
_cur = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(_sselect)));
 //BA.debugLineNum = 972;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 974;BA.debugLine="aResult = Array As String(cur.GetString(\"MatchCod";
_aresult = new String[]{_cur.GetString("MatchCode"),_cur.GetString("Betrag"),_cur.GetString("Bezeichnung")};
 //BA.debugLineNum = 976;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 977;BA.debugLine="Main.SQL1.Close";
mostCurrent._main._sql1 /*anywheresoftware.b4a.sql.SQL*/ .Close();
 //BA.debugLineNum = 979;BA.debugLine="Return aResult";
if (true) return _aresult;
 //BA.debugLineNum = 980;BA.debugLine="End Sub";
return null;
}
}
