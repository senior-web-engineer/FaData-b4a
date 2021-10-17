package fadata.mobil;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class readwriteini {
private static readwriteini mostCurrent = new readwriteini();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static ADR.stringdemo.stringfunctions _sf = null;
public fadata.mobil.main _main = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.dbutils _dbutils = null;
public fadata.mobil.signaturecapture _signaturecapture = null;
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
public static int  _gettreffpunkteintraege(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _list1 = null;
int _anzahl = 0;
 //BA.debugLineNum = 309;BA.debugLine="Sub GetTreffpunktEintraege As Int";
 //BA.debugLineNum = 310;BA.debugLine="Dim list1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 311;BA.debugLine="Dim anzahl As Int";
_anzahl = 0;
 //BA.debugLineNum = 313;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaDataMeetingP";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI")) { 
 //BA.debugLineNum = 314;BA.debugLine="list1 = File.ReadList(Main.SourceFolder, \"FaData";
_list1 = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI");
 //BA.debugLineNum = 315;BA.debugLine="anzahl = list1.Size / 6";
_anzahl = (int) (_list1.getSize()/(double)6);
 }else {
 //BA.debugLineNum = 317;BA.debugLine="anzahl = 0";
_anzahl = (int) (0);
 };
 //BA.debugLineNum = 320;BA.debugLine="Return anzahl";
if (true) return _anzahl;
 //BA.debugLineNum = 321;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim sf As StringFunctions";
_sf = new ADR.stringdemo.stringfunctions();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static boolean[]  _readconfigdata(anywheresoftware.b4a.BA _ba) throws Exception{
boolean[] _akonfig = null;
String _sbegleitfahrzeug = "";
String _spruefergebnis = "";
String _sbvfausbildung = "";
 //BA.debugLineNum = 251;BA.debugLine="Sub ReadConfigData() As Boolean()";
 //BA.debugLineNum = 252;BA.debugLine="Dim aKonfig(3) As Boolean";
_akonfig = new boolean[(int) (3)];
;
 //BA.debugLineNum = 253;BA.debugLine="Dim sBegleitfahrzeug, sPruefergebnis, sBVFAusbild";
_sbegleitfahrzeug = "";
_spruefergebnis = "";
_sbvfausbildung = "";
 //BA.debugLineNum = 255;BA.debugLine="sBegleitfahrzeug = ReadINI(Main.SourceFolder, \"Fa";
_sbegleitfahrzeug = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Begleitfahrzeug","anzeigen","");
 //BA.debugLineNum = 256;BA.debugLine="sPruefergebnis = ReadINI(Main.SourceFolder, \"FaDa";
_spruefergebnis = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Pruefungsergebnis","erfassen","");
 //BA.debugLineNum = 257;BA.debugLine="sBVFAusbildung = ReadINI(Main.SourceFolder, \"FaDa";
_sbvfausbildung = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","BVFAusbildung","anzeigen","");
 //BA.debugLineNum = 259;BA.debugLine="If sBegleitfahrzeug = \"\" Then";
if ((_sbegleitfahrzeug).equals("")) { 
 //BA.debugLineNum = 260;BA.debugLine="sBegleitfahrzeug = \"false\"";
_sbegleitfahrzeug = "false";
 };
 //BA.debugLineNum = 263;BA.debugLine="If sPruefergebnis = \"\" Then";
if ((_spruefergebnis).equals("")) { 
 //BA.debugLineNum = 264;BA.debugLine="sPruefergebnis = \"false\"";
_spruefergebnis = "false";
 };
 //BA.debugLineNum = 267;BA.debugLine="If sBVFAusbildung = \"\" Then";
if ((_sbvfausbildung).equals("")) { 
 //BA.debugLineNum = 268;BA.debugLine="sBVFAusbildung = \"false\"";
_sbvfausbildung = "false";
 };
 //BA.debugLineNum = 271;BA.debugLine="aKonfig = Array As Boolean(sBegleitfahrzeug, sPru";
_akonfig = new boolean[]{BA.ObjectToBoolean(_sbegleitfahrzeug),BA.ObjectToBoolean(_spruefergebnis),BA.ObjectToBoolean(_sbvfausbildung)};
 //BA.debugLineNum = 272;BA.debugLine="Return aKonfig";
if (true) return _akonfig;
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return null;
}
public static String[]  _readconfigfahrdata(anywheresoftware.b4a.BA _ba) throws Exception{
String[] _akonfig = null;
String _sfahrlehrervalue = "";
String _sfahrlehrervalueid = "";
String _sfahrzeugvalue = "";
String _sfahrzeugvalueid = "";
 //BA.debugLineNum = 275;BA.debugLine="Sub ReadConfigFahrData() As String()";
 //BA.debugLineNum = 276;BA.debugLine="Dim aKonfig(4) As String";
_akonfig = new String[(int) (4)];
java.util.Arrays.fill(_akonfig,"");
 //BA.debugLineNum = 277;BA.debugLine="Dim sFahrlehrerValue, sFahrlehrerValueID, sFahrze";
_sfahrlehrervalue = "";
_sfahrlehrervalueid = "";
_sfahrzeugvalue = "";
_sfahrzeugvalueid = "";
 //BA.debugLineNum = 279;BA.debugLine="sFahrlehrerValue = ReadINI(Main.SourceFolder, \"Fa";
_sfahrlehrervalue = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Fahrlehrer","Fahrlehrer","");
 //BA.debugLineNum = 280;BA.debugLine="sFahrlehrerValueID = ReadINI(Main.SourceFolder, \"";
_sfahrlehrervalueid = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Fahrlehrer","FahrlehrerID","");
 //BA.debugLineNum = 281;BA.debugLine="sFahrzeugValue = ReadINI(Main.SourceFolder, \"FaDa";
_sfahrzeugvalue = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","KFZ","Fahrzeug","");
 //BA.debugLineNum = 282;BA.debugLine="sFahrzeugValueID = ReadINI(Main.SourceFolder, \"Fa";
_sfahrzeugvalueid = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","KFZ","FahrzeugID","");
 //BA.debugLineNum = 285;BA.debugLine="aKonfig = Array As String(sFahrlehrerValue, sFahr";
_akonfig = new String[]{_sfahrlehrervalue,_sfahrlehrervalueid,_sfahrzeugvalue,_sfahrzeugvalueid};
 //BA.debugLineNum = 286;BA.debugLine="Return aKonfig";
if (true) return _akonfig;
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return null;
}
public static String  _readfahreinheit(anywheresoftware.b4a.BA _ba) throws Exception{
String _sfahreinheit = "";
 //BA.debugLineNum = 289;BA.debugLine="Sub ReadFahrEinheit() As String";
 //BA.debugLineNum = 290;BA.debugLine="Dim sFahrEinheit As String";
_sfahreinheit = "";
 //BA.debugLineNum = 292;BA.debugLine="sFahrEinheit = ReadINI(Main.SourceFolder, \"FaData";
_sfahreinheit = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Einheit","Einheit","");
 //BA.debugLineNum = 294;BA.debugLine="Return sFahrEinheit";
if (true) return _sfahreinheit;
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _readini(anywheresoftware.b4a.BA _ba,String _directory,String _fileini,String _session,String _key,String _default) throws Exception{
String _value = "";
String _rsession = "";
boolean _flgsession = false;
String _rkey = "";
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _rdtext = null;
int _p = 0;
String _k = "";
 //BA.debugLineNum = 13;BA.debugLine="Sub ReadINI(directory As String, fileINI As String";
 //BA.debugLineNum = 15;BA.debugLine="Dim value As String ' Schlüsselwert";
_value = "";
 //BA.debugLineNum = 16;BA.debugLine="Dim rSESSION As String ' Zeile mit dem Namen der";
_rsession = "";
 //BA.debugLineNum = 17;BA.debugLine="Dim flgSESSION As Boolean ' Flagge Session-Unters";
_flgsession = false;
 //BA.debugLineNum = 18;BA.debugLine="Dim rKEY As String ' Zeile mit dem Namen und den";
_rkey = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim rdTEXT As TextReader ' Stream-Objekt für das";
_rdtext = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim p As Int '";
_p = 0;
 //BA.debugLineNum = 21;BA.debugLine="Dim k As String '";
_k = "";
 //BA.debugLineNum = 24;BA.debugLine="value = default";
_value = _default;
 //BA.debugLineNum = 27;BA.debugLine="If File.Exists(directory, fileINI) = True Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_directory,_fileini)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 29;BA.debugLine="rSESSION = \"\"";
_rsession = "";
 //BA.debugLineNum = 30;BA.debugLine="flgSESSION = False";
_flgsession = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 31;BA.debugLine="rKEY = \"\"";
_rkey = "";
 //BA.debugLineNum = 33;BA.debugLine="rdTEXT.Initialize(File.OpenInput(directory, file";
_rdtext.Initialize((java.io.InputStream)(anywheresoftware.b4a.keywords.Common.File.OpenInput(_directory,_fileini).getObject()));
 //BA.debugLineNum = 35;BA.debugLine="rSESSION = rdTEXT.ReadLine '.Trim";
_rsession = _rdtext.ReadLine();
 //BA.debugLineNum = 36;BA.debugLine="Do While rSESSION <> Null";
while (_rsession!= null) {
 //BA.debugLineNum = 38;BA.debugLine="If rSESSION <> \"\" Then";
if ((_rsession).equals("") == false) { 
 //BA.debugLineNum = 40;BA.debugLine="If rSESSION.CompareTo(\"[\" & session & \"]\") = 0";
if (_rsession.compareTo("["+_session+"]")==0) { 
 //BA.debugLineNum = 42;BA.debugLine="flgSESSION = True";
_flgsession = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 44;BA.debugLine="rKEY = rdTEXT.ReadLine";
_rkey = _rdtext.ReadLine();
 //BA.debugLineNum = 45;BA.debugLine="Do While rKEY <> Null";
while (_rkey!= null) {
 //BA.debugLineNum = 47;BA.debugLine="If rKEY <> \"\" Then";
if ((_rkey).equals("") == false) { 
 //BA.debugLineNum = 49;BA.debugLine="p = rKEY.IndexOf(\"=\")";
_p = _rkey.indexOf("=");
 //BA.debugLineNum = 50;BA.debugLine="If p > -1 Then";
if (_p>-1) { 
 //BA.debugLineNum = 51;BA.debugLine="If rKEY.SubString2(0, 0) <> \"[\" Then";
if ((_rkey.substring((int) (0),(int) (0))).equals("[") == false) { 
 //BA.debugLineNum = 52;BA.debugLine="k = rKEY.SubString2(0, p)";
_k = _rkey.substring((int) (0),_p);
 //BA.debugLineNum = 54;BA.debugLine="If key.CompareTo(k) = 0 Then";
if (_key.compareTo(_k)==0) { 
 //BA.debugLineNum = 56;BA.debugLine="value = rKEY.SubString(p + 1)";
_value = _rkey.substring((int) (_p+1));
 //BA.debugLineNum = 57;BA.debugLine="Exit";
if (true) break;
 };
 };
 };
 };
 //BA.debugLineNum = 63;BA.debugLine="rKEY = rdTEXT.ReadLine";
_rkey = _rdtext.ReadLine();
 }
;
 };
 };
 //BA.debugLineNum = 67;BA.debugLine="If flgSESSION = True Then";
if (_flgsession==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 69;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 72;BA.debugLine="rSESSION = rdTEXT.ReadLine";
_rsession = _rdtext.ReadLine();
 };
 }
;
 //BA.debugLineNum = 76;BA.debugLine="rdTEXT.Close";
_rdtext.Close();
 };
 //BA.debugLineNum = 79;BA.debugLine="Return value";
if (true) return _value;
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String[]  _readtreffpunkte(anywheresoftware.b4a.BA _ba,int _i) throws Exception{
String[] _atreffpunkt = null;
String _streffpunkt = "";
String _sfixer_treffpunk = "";
String _streffpunktid = "";
 //BA.debugLineNum = 297;BA.debugLine="Sub ReadTreffpunkte(i As Int) As String()";
 //BA.debugLineNum = 298;BA.debugLine="Dim aTreffpunkt(3) As String";
_atreffpunkt = new String[(int) (3)];
java.util.Arrays.fill(_atreffpunkt,"");
 //BA.debugLineNum = 299;BA.debugLine="Dim sTreffpunkt, sFixer_Treffpunk, sTreffpunktID";
_streffpunkt = "";
_sfixer_treffpunk = "";
_streffpunktid = "";
 //BA.debugLineNum = 301;BA.debugLine="sTreffpunkt = ReadINI(Main.SourceFolder, \"FaDataM";
_streffpunkt = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"Treffpunkt","");
 //BA.debugLineNum = 302;BA.debugLine="sFixer_Treffpunk = ReadINI(Main.SourceFolder, \"Fa";
_sfixer_treffpunk = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"fixer_Treffpunkt","");
 //BA.debugLineNum = 303;BA.debugLine="sTreffpunktID = ReadINI(Main.SourceFolder, \"FaDat";
_streffpunktid = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"TreffpunktID","");
 //BA.debugLineNum = 305;BA.debugLine="aTreffpunkt = Array As String(sTreffpunkt, sFixer";
_atreffpunkt = new String[]{_streffpunkt,_sfixer_treffpunk,_streffpunktid};
 //BA.debugLineNum = 306;BA.debugLine="Return aTreffpunkt";
if (true) return _atreffpunkt;
 //BA.debugLineNum = 307;BA.debugLine="End Sub";
return null;
}
public static String  _updatefahrini(anywheresoftware.b4a.BA _ba,String _session,String _key,String _value,String _id) throws Exception{
String _sconfigdata = "";
String _newsconfigdata = "";
String _svalue = "";
String _svalueid = "";
 //BA.debugLineNum = 355;BA.debugLine="Sub UpdateFahrINI(Session As String, Key As String";
 //BA.debugLineNum = 356;BA.debugLine="Dim sConfigData, newsConfigData As String";
_sconfigdata = "";
_newsconfigdata = "";
 //BA.debugLineNum = 357;BA.debugLine="Dim sValue, sValueID As String";
_svalue = "";
_svalueid = "";
 //BA.debugLineNum = 360;BA.debugLine="sValue = ReadINI(Main.SourceFolder, \"FaData.INI\",";
_svalue = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI",_session,_key,"");
 //BA.debugLineNum = 361;BA.debugLine="sValueID = ReadINI(Main.SourceFolder, \"FaData.INI";
_svalueid = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI",_session,_key+"ID","");
 //BA.debugLineNum = 363;BA.debugLine="If Not(sf.IsEmpty(sValue)) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_sf._vv7(_svalue))) { 
 //BA.debugLineNum = 364;BA.debugLine="sConfigData = File.GetText(Main.SourceFolder, \"F";
_sconfigdata = anywheresoftware.b4a.keywords.Common.File.GetText(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 //BA.debugLineNum = 365;BA.debugLine="newsConfigData = sConfigData.Replace(\"[\" & Sessi";
_newsconfigdata = _sconfigdata.replace("["+_session+"]"+_key+"="+_svalue+"["+_session+"]"+_key+"ID"+"="+_svalueid,"["+_session+"]"+_key+"="+_value+"["+_session+"]"+_key+"ID"+"="+_id);
 //BA.debugLineNum = 368;BA.debugLine="If File.Exists(Main.SourceFolder, \"ini.tmp\") = T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 369;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 370;BA.debugLine="File.Delete(Main.SourceFolder, \"FaData.INI\") '";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 };
 //BA.debugLineNum = 373;BA.debugLine="File.Copy(Main.SourceFolder, \"ini.tmp\", Main.So";
anywheresoftware.b4a.keywords.Common.File.Copy(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp",mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 //BA.debugLineNum = 375;BA.debugLine="File.Delete(Main.SourceFolder, \"ini.tmp\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp");
 };
 //BA.debugLineNum = 378;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")) { 
 //BA.debugLineNum = 379;BA.debugLine="File.Delete(Main.SourceFolder, \"FaData.INI\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 }else {
 //BA.debugLineNum = 381;BA.debugLine="File.WriteString(Main.SourceFolder, \"FaData.INI";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI",_newsconfigdata);
 };
 };
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return "";
}
public static String  _updateini(anywheresoftware.b4a.BA _ba,String _session,String _key,String _value) throws Exception{
String _sconfigdata = "";
String _newsconfigdata = "";
String _svalue = "";
 //BA.debugLineNum = 325;BA.debugLine="Sub UpdateINI(Session As String, Key As String, Va";
 //BA.debugLineNum = 326;BA.debugLine="Dim sConfigData, newsConfigData As String";
_sconfigdata = "";
_newsconfigdata = "";
 //BA.debugLineNum = 327;BA.debugLine="Dim sValue As String";
_svalue = "";
 //BA.debugLineNum = 330;BA.debugLine="sValue = ReadINI(Main.SourceFolder, \"FaData.INI\",";
_svalue = _readini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI",_session,_key,"");
 //BA.debugLineNum = 332;BA.debugLine="If Not(sf.IsEmpty(sValue)) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_sf._vv7(_svalue))) { 
 //BA.debugLineNum = 333;BA.debugLine="sConfigData = File.GetText(Main.SourceFolder, \"F";
_sconfigdata = anywheresoftware.b4a.keywords.Common.File.GetText(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 //BA.debugLineNum = 334;BA.debugLine="newsConfigData = sConfigData.Replace(\"[\" & Sessi";
_newsconfigdata = _sconfigdata.replace("["+_session+"]"+_key+"="+_svalue,"["+_session+"]"+_key+"="+_value);
 //BA.debugLineNum = 337;BA.debugLine="If File.Exists(Main.SourceFolder, \"ini.tmp\") = T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 338;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 339;BA.debugLine="File.Delete(Main.SourceFolder, \"FaData.INI\") '";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 };
 //BA.debugLineNum = 342;BA.debugLine="File.Copy(Main.SourceFolder, \"ini.tmp\", Main.So";
anywheresoftware.b4a.keywords.Common.File.Copy(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp",mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 //BA.debugLineNum = 344;BA.debugLine="File.Delete(Main.SourceFolder, \"ini.tmp\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"ini.tmp");
 };
 //BA.debugLineNum = 347;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")) { 
 //BA.debugLineNum = 348;BA.debugLine="File.Delete(Main.SourceFolder, \"FaData.INI\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 }else {
 //BA.debugLineNum = 350;BA.debugLine="File.WriteString(Main.SourceFolder, \"FaData.INI";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI",_newsconfigdata);
 };
 };
 //BA.debugLineNum = 353;BA.debugLine="End Sub";
return "";
}
public static boolean  _writedata(anywheresoftware.b4a.BA _ba,String _sfahrlehrer,int _ifahrlehrerid,String _scheckedkfz,int _icheckedkfzid,String _sfahreinheit,boolean _bbegleitfahrzeug,boolean _bprueferg,boolean _bbvfausbildung) throws Exception{
 //BA.debugLineNum = 204;BA.debugLine="Sub WriteData(sFahrlehrer As String, iFahrlehrerID";
 //BA.debugLineNum = 206;BA.debugLine="Try";
try { //BA.debugLineNum = 208;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 209;BA.debugLine="File.Delete(Main.SourceFolder, \"FaData.INI\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI");
 };
 //BA.debugLineNum = 212;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"Fahrl";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Fahrlehrer","Fahrlehrer",_sfahrlehrer);
 //BA.debugLineNum = 213;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"Fahrl";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Fahrlehrer","FahrlehrerID",BA.NumberToString(_ifahrlehrerid));
 //BA.debugLineNum = 214;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"KFZ\",";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","KFZ","Fahrzeug",_scheckedkfz);
 //BA.debugLineNum = 215;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"KFZ\",";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","KFZ","FahrzeugID",BA.NumberToString(_icheckedkfzid));
 //BA.debugLineNum = 216;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"Einhe";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Einheit","Einheit",_sfahreinheit);
 //BA.debugLineNum = 217;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"Begle";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Begleitfahrzeug","anzeigen",BA.ObjectToString(_bbegleitfahrzeug));
 //BA.debugLineNum = 218;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"Pruef";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","Pruefungsergebnis","erfassen",BA.ObjectToString(_bprueferg));
 //BA.debugLineNum = 219;BA.debugLine="WriteINI(Main.SourceFolder, \"FaData.INI\", \"BVFAu";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI","BVFAusbildung","anzeigen",BA.ObjectToString(_bbvfausbildung));
 //BA.debugLineNum = 221;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 223;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 225;BA.debugLine="End Sub";
return false;
}
public static String  _writeini(anywheresoftware.b4a.BA _ba,String _directory,String _fileini,String _session,String _key,String _value) throws Exception{
int _nr = 0;
boolean _flgsession = false;
boolean _flgkey = false;
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _rdtext = null;
anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _wrtext = null;
String _rowtxt = "";
int _p = 0;
String _k = "";
 //BA.debugLineNum = 86;BA.debugLine="Sub WriteINI(directory As String, fileINI As Strin";
 //BA.debugLineNum = 88;BA.debugLine="Dim nr As Int ' die Anzahl von Zeilen aus der Dat";
_nr = 0;
 //BA.debugLineNum = 89;BA.debugLine="Dim flgSESSION As Boolean ' Flagge Session-Unters";
_flgsession = false;
 //BA.debugLineNum = 90;BA.debugLine="Dim flgKEY As Boolean ' Flagge der Unterstützung";
_flgkey = false;
 //BA.debugLineNum = 91;BA.debugLine="Dim rdTEXT As TextReader ' Stream-Objekt für. INI";
_rdtext = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 92;BA.debugLine="Dim wrTEXT As TextWriter ' Stream-Objekt für. INI";
_wrtext = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 93;BA.debugLine="Dim rowtxt As String ' Textzeile lesen / schreibe";
_rowtxt = "";
 //BA.debugLineNum = 94;BA.debugLine="Dim p As Int '";
_p = 0;
 //BA.debugLineNum = 95;BA.debugLine="Dim k As String '";
_k = "";
 //BA.debugLineNum = 97;BA.debugLine="If File.Exists(directory, fileINI) = True Then '";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_directory,_fileini)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 99;BA.debugLine="nr = 0";
_nr = (int) (0);
 //BA.debugLineNum = 100;BA.debugLine="flgSESSION = False";
_flgsession = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 101;BA.debugLine="flgKEY = False";
_flgkey = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 103;BA.debugLine="rdTEXT.Initialize(File.OpenInput(directory, file";
_rdtext.Initialize((java.io.InputStream)(anywheresoftware.b4a.keywords.Common.File.OpenInput(_directory,_fileini).getObject()));
 //BA.debugLineNum = 105;BA.debugLine="wrTEXT.Initialize(File.OpenOutput(directory, \"in";
_wrtext.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(_directory,"ini.tmp",anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 107;BA.debugLine="rowtxt = rdTEXT.ReadLine";
_rowtxt = _rdtext.ReadLine();
 //BA.debugLineNum = 109;BA.debugLine="Do While rowtxt <> Null";
while (_rowtxt!= null) {
 //BA.debugLineNum = 111;BA.debugLine="nr = nr + 1";
_nr = (int) (_nr+1);
 //BA.debugLineNum = 112;BA.debugLine="If rowtxt <> \"\" Then ' wenn Sie das Ende der Da";
if ((_rowtxt).equals("") == false) { 
 //BA.debugLineNum = 113;BA.debugLine="If rowtxt.SubString2(0, 0) = \"[\" Then ' es ist";
if ((_rowtxt.substring((int) (0),(int) (0))).equals("[")) { 
 //BA.debugLineNum = 114;BA.debugLine="If flgSESSION = False Then ' tritt auf, wenn";
if (_flgsession==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 115;BA.debugLine="If rowtxt.CompareTo(\"[\" & session & \"]\") = 0";
if (_rowtxt.compareTo("["+_session+"]")==0) { 
 //BA.debugLineNum = 117;BA.debugLine="flgSESSION = True";
_flgsession = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 119;BA.debugLine="If nr > 1 Then ' wenn die Sitzung, um neben";
if (_nr>1) { 
 //BA.debugLineNum = 121;BA.debugLine="wrTEXT.WriteLine(\"\")";
_wrtext.WriteLine("");
 };
 //BA.debugLineNum = 123;BA.debugLine="wrTEXT.WriteLine(rowtxt) ' schreibt die Sitz";
_wrtext.WriteLine(_rowtxt);
 }else {
 //BA.debugLineNum = 126;BA.debugLine="flgSESSION = False";
_flgsession = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 127;BA.debugLine="If flgKEY = False Then";
if (_flgkey==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 129;BA.debugLine="wrTEXT.WriteLine(key & \"=\" & value) ' schre";
_wrtext.WriteLine(_key+"="+_value);
 //BA.debugLineNum = 130;BA.debugLine="flgKEY = True";
_flgkey = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 131;BA.debugLine="wrTEXT.WriteLine(\"\") ' legen Sie eine leere";
_wrtext.WriteLine("");
 //BA.debugLineNum = 132;BA.debugLine="wrTEXT.WriteLine(rowtxt) ' schreibt die näc";
_wrtext.WriteLine(_rowtxt);
 //BA.debugLineNum = 133;BA.debugLine="Exit ' Schlüsselsuche abgeschlossen ist, ve";
if (true) break;
 }else {
 //BA.debugLineNum = 135;BA.debugLine="wrTEXT.WriteLine(\"\") ' legen Sie eine leere";
_wrtext.WriteLine("");
 //BA.debugLineNum = 136;BA.debugLine="wrTEXT.WriteLine(rowtxt) ' schreibt die Sit";
_wrtext.WriteLine(_rowtxt);
 };
 };
 }else {
 //BA.debugLineNum = 140;BA.debugLine="If rowtxt <> \"\" Then ' schließen Sie die leer";
if ((_rowtxt).equals("") == false) { 
 //BA.debugLineNum = 142;BA.debugLine="p = rowtxt.IndexOf(\"=\")";
_p = _rowtxt.indexOf("=");
 //BA.debugLineNum = 143;BA.debugLine="If p > -1 And flgSESSION = True Then ' wenn";
if (_p>-1 && _flgsession==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 144;BA.debugLine="k = rowtxt.SubString2(0, p) ' extrahiert de";
_k = _rowtxt.substring((int) (0),_p);
 //BA.debugLineNum = 145;BA.debugLine="If key.CompareTo(k) = 0 Then ' wenn der Nam";
if (_key.compareTo(_k)==0) { 
 //BA.debugLineNum = 146;BA.debugLine="flgKEY = True ' der Schlüssel identifizier";
_flgkey = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 147;BA.debugLine="wrTEXT.WriteLine(key & \"=\" & value) ' schr";
_wrtext.WriteLine(_key+"="+_value);
 //BA.debugLineNum = 148;BA.debugLine="Exit ' Schlüsselsuche abgeschlossen ist, v";
if (true) break;
 }else {
 //BA.debugLineNum = 151;BA.debugLine="wrTEXT.WriteLine(rowtxt)";
_wrtext.WriteLine(_rowtxt);
 };
 }else {
 //BA.debugLineNum = 155;BA.debugLine="wrTEXT.WriteLine(rowtxt)";
_wrtext.WriteLine(_rowtxt);
 };
 };
 };
 };
 //BA.debugLineNum = 160;BA.debugLine="rowtxt = rdTEXT.ReadLine ' liest eine neue Zeil";
_rowtxt = _rdtext.ReadLine();
 }
;
 //BA.debugLineNum = 163;BA.debugLine="Do While rowtxt <> Null";
while (_rowtxt!= null) {
 //BA.debugLineNum = 164;BA.debugLine="rowtxt = rdTEXT.ReadLine ' liest die Zeile aus d";
_rowtxt = _rdtext.ReadLine();
 //BA.debugLineNum = 165;BA.debugLine="If rowtxt <> Null Then ' wenn Sie das Ende der";
if (_rowtxt!= null) { 
 //BA.debugLineNum = 166;BA.debugLine="wrTEXT.WriteLine(rowtxt) ' schreibt die Linie";
_wrtext.WriteLine(_rowtxt);
 };
 }
;
 //BA.debugLineNum = 170;BA.debugLine="rdTEXT.Close";
_rdtext.Close();
 //BA.debugLineNum = 171;BA.debugLine="If flgSESSION = False And flgKEY = False Then '";
if (_flgsession==anywheresoftware.b4a.keywords.Common.False && _flgkey==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 172;BA.debugLine="wrTEXT.WriteLine(\"\") ' legen Sie eine leere Zei";
_wrtext.WriteLine("");
 //BA.debugLineNum = 173;BA.debugLine="wrTEXT.WriteLine(\"[\" & session & \"]\") ' schreib";
_wrtext.WriteLine("["+_session+"]");
 //BA.debugLineNum = 174;BA.debugLine="wrTEXT.WriteLine(key & \"=\" & value) ' schreibt";
_wrtext.WriteLine(_key+"="+_value);
 };
 //BA.debugLineNum = 176;BA.debugLine="wrTEXT.Close ' schließt die Datei. temporäre INI";
_wrtext.Close();
 }else {
 //BA.debugLineNum = 179;BA.debugLine="wrTEXT.Initialize(File.OpenOutput(directory, fil";
_wrtext.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(_directory,_fileini,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 181;BA.debugLine="wrTEXT.WriteLine(\"[\" & session & \"]\")";
_wrtext.WriteLine("["+_session+"]");
 //BA.debugLineNum = 183;BA.debugLine="wrTEXT.WriteLine(key & \"=\" & value)";
_wrtext.WriteLine(_key+"="+_value);
 //BA.debugLineNum = 185;BA.debugLine="wrTEXT.Close";
_wrtext.Close();
 };
 //BA.debugLineNum = 188;BA.debugLine="If File.Exists(directory, \"ini.tmp\") = True Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_directory,"ini.tmp")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 189;BA.debugLine="If File.Exists(directory, fileINI) = True Then '";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_directory,_fileini)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 190;BA.debugLine="File.Delete(directory, fileINI) ' wenn Sie es z";
anywheresoftware.b4a.keywords.Common.File.Delete(_directory,_fileini);
 };
 //BA.debugLineNum = 193;BA.debugLine="File.Copy(directory, \"ini.tmp\", directory, fileI";
anywheresoftware.b4a.keywords.Common.File.Copy(_directory,"ini.tmp",_directory,_fileini);
 //BA.debugLineNum = 195;BA.debugLine="File.Delete(directory, \"ini.tmp\")";
anywheresoftware.b4a.keywords.Common.File.Delete(_directory,"ini.tmp");
 };
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static boolean  _writetreffpunkte(anywheresoftware.b4a.BA _ba) throws Exception{
String[][] _defaultmeetingpoints = null;
int _i = 0;
 //BA.debugLineNum = 227;BA.debugLine="Sub WriteTreffpunkte As Boolean";
 //BA.debugLineNum = 228;BA.debugLine="Try";
try { //BA.debugLineNum = 230;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaDataMeeting";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 231;BA.debugLine="File.Delete(Main.SourceFolder, \"FaDataMeetingPo";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI");
 };
 //BA.debugLineNum = 235;BA.debugLine="Dim DefaultMeetingPoints(,) As String";
_defaultmeetingpoints = new String[(int) (0)][];
{
int d0 = _defaultmeetingpoints.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_defaultmeetingpoints[i0] = new String[d1];
java.util.Arrays.fill(_defaultmeetingpoints[i0],"");
}
}
;
 //BA.debugLineNum = 236;BA.debugLine="DefaultMeetingPoints = DBUtils.GetAllMeetingPoin";
_defaultmeetingpoints = mostCurrent._dbutils._getallmeetingpoint /*String[][]*/ (_ba);
 //BA.debugLineNum = 238;BA.debugLine="For i = 1 To DefaultMeetingPoints.Length";
{
final int step7 = 1;
final int limit7 = _defaultmeetingpoints.length;
_i = (int) (1) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 239;BA.debugLine="WriteINI(Main.SourceFolder, \"FaDataMeetingPoint";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"Treffpunkt",_defaultmeetingpoints[(int) (_i-1)][(int) (0)]);
 //BA.debugLineNum = 240;BA.debugLine="WriteINI(Main.SourceFolder, \"FaDataMeetingPoint";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"fixer_Treffpunkt",_defaultmeetingpoints[(int) (_i-1)][(int) (1)]);
 //BA.debugLineNum = 241;BA.debugLine="WriteINI(Main.SourceFolder, \"FaDataMeetingPoint";
_writeini(_ba,mostCurrent._main._sourcefolder /*String*/ ,"FaDataMeetingPoints.INI","Treffpunkte_"+BA.NumberToString(_i),"TreffpunktID",_defaultmeetingpoints[(int) (_i-1)][(int) (2)]);
 }
};
 //BA.debugLineNum = 243;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 245;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return false;
}
}
