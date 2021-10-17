package fadata.mobil;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class helper {
private static helper mostCurrent = new helper();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public fadata.mobil.main _main = null;
public fadata.mobil.dbutils _dbutils = null;
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
public static String  _arraybisselected(anywheresoftware.b4a.BA _ba,boolean _bvalue) throws Exception{
int _i = 0;
 //BA.debugLineNum = 949;BA.debugLine="Sub ArraybIsSelected(bValue As Boolean)";
 //BA.debugLineNum = 950;BA.debugLine="For i = 0 To Main.bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._main._bisselected /*boolean[]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 951;BA.debugLine="Main.bIsSelected(i) = bValue";
mostCurrent._main._bisselected /*boolean[]*/ [_i] = _bvalue;
 }
};
 //BA.debugLineNum = 953;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblgrundstufe,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf0,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf6) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub AusbildungBVFBeschriftung(lblGrundstufe As Lab";
 //BA.debugLineNum = 186;BA.debugLine="lblGrundstufe.Text = \"Grundstufe\"";
_lblgrundstufe.setText(BA.ObjectToCharSequence("Grundstufe"));
 //BA.debugLineNum = 187;BA.debugLine="lblAusbildungBVF0.Text = \"+\"";
_lblausbildungbvf0.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 188;BA.debugLine="lblAusbildungBVF1.Text = \"-\"";
_lblausbildungbvf1.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 189;BA.debugLine="lblAusbildungBVF2.Text = \"Notiz\"";
_lblausbildungbvf2.setText(BA.ObjectToCharSequence("Notiz"));
 //BA.debugLineNum = 190;BA.debugLine="lblAusbildungBVF3.Text = \"Save\"";
_lblausbildungbvf3.setText(BA.ObjectToCharSequence("Save"));
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan0(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderheinsteigen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkradhaltung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpedale,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltwaehlhebel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzuendschloss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoranlassen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahranhalte) throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub AusbildungBVFPan0(cbBesonderhEinsteigen As Che";
 //BA.debugLineNum = 197;BA.debugLine="cbBesonderhEinsteigen.Text = \"Besonderheit beim E";
_cbbesonderheinsteigen.setText(BA.ObjectToCharSequence("Besonderheit beim Einsteigen"));
 //BA.debugLineNum = 198;BA.debugLine="cbEinstellen.Text = \"Einstellen\"";
_cbeinstellen.setText(BA.ObjectToCharSequence("Einstellen"));
 //BA.debugLineNum = 199;BA.debugLine="cbLenkrad.Text = \"Lenkrad\"";
_cblenkrad.setText(BA.ObjectToCharSequence("Lenkrad"));
 //BA.debugLineNum = 200;BA.debugLine="cbSpiegel.Text = \"Spiegel\"";
_cbspiegel.setText(BA.ObjectToCharSequence("Spiegel"));
 //BA.debugLineNum = 201;BA.debugLine="cbKopfstuetze.Text = \"Kopfstütze\"";
_cbkopfstuetze.setText(BA.ObjectToCharSequence("Kopfstütze"));
 //BA.debugLineNum = 202;BA.debugLine="cbSitz.Text = \" Sitz\"";
_cbsitz.setText(BA.ObjectToCharSequence(" Sitz"));
 //BA.debugLineNum = 203;BA.debugLine="cbLenkradhaltung.Text = \"Lenkradhaltung\"";
_cblenkradhaltung.setText(BA.ObjectToCharSequence("Lenkradhaltung"));
 //BA.debugLineNum = 204;BA.debugLine="cbPedale.Text = \"Pedale\"";
_cbpedale.setText(BA.ObjectToCharSequence("Pedale"));
 //BA.debugLineNum = 205;BA.debugLine="cbGurt.Text = \"Gurt anlegen/anpassen\"";
_cbgurt.setText(BA.ObjectToCharSequence("Gurt anlegen/anpassen"));
 //BA.debugLineNum = 206;BA.debugLine="cbSchaltWaehlhebel.Text = \"Schalt-/Wahlhebel\"";
_cbschaltwaehlhebel.setText(BA.ObjectToCharSequence("Schalt-/Wahlhebel"));
 //BA.debugLineNum = 207;BA.debugLine="cbZuendschloss.Text = \"Zündschloss\"";
_cbzuendschloss.setText(BA.ObjectToCharSequence("Zündschloss"));
 //BA.debugLineNum = 208;BA.debugLine="cbMotorAnlassen.Text = \"Motor anlassen\"";
_cbmotoranlassen.setText(BA.ObjectToCharSequence("Motor anlassen"));
 //BA.debugLineNum = 209;BA.debugLine="cbAnfahrAnhalte.Text = \"Anfahr- / Anhalteübungen\"";
_cbanfahranhalte.setText(BA.ObjectToCharSequence("Anfahr- / Anhalteübungen"));
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan1(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltuebg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch1_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch2_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch3_4,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter2_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkuebung) throws Exception{
 //BA.debugLineNum = 212;BA.debugLine="Sub AusbildungBVFPan1(cbSchaltuebg As CheckBox, cb";
 //BA.debugLineNum = 214;BA.debugLine="cbSchaltuebg.Text = \"Schaltübungen (umweltbewusst";
_cbschaltuebg.setText(BA.ObjectToCharSequence("Schaltübungen (umweltbewusst)"));
 //BA.debugLineNum = 215;BA.debugLine="cbHoch1_2.Text = \"hoch 1-2\"";
_cbhoch1_2.setText(BA.ObjectToCharSequence("hoch 1-2"));
 //BA.debugLineNum = 216;BA.debugLine="cbHoch2_3.Text = \"hoch 2-3\"";
_cbhoch2_3.setText(BA.ObjectToCharSequence("hoch 2-3"));
 //BA.debugLineNum = 217;BA.debugLine="cbHoch3_4.Text = \"hoch 3-4\"";
_cbhoch3_4.setText(BA.ObjectToCharSequence("hoch 3-4"));
 //BA.debugLineNum = 218;BA.debugLine="cbRunter4_3.Text = \"runter 4-3\"";
_cbrunter4_3.setText(BA.ObjectToCharSequence("runter 4-3"));
 //BA.debugLineNum = 219;BA.debugLine="cbRunter3_2.Text = \"runter 3-2\"";
_cbrunter3_2.setText(BA.ObjectToCharSequence("runter 3-2"));
 //BA.debugLineNum = 220;BA.debugLine="cbRunter2_1.Text = \"runter 2-1\"";
_cbrunter2_1.setText(BA.ObjectToCharSequence("runter 2-1"));
 //BA.debugLineNum = 221;BA.debugLine="cbRunter4_2.Text = \"runter 4-2\"";
_cbrunter4_2.setText(BA.ObjectToCharSequence("runter 4-2"));
 //BA.debugLineNum = 222;BA.debugLine="cbRunter4_1.Text = \"runter 4-1\"";
_cbrunter4_1.setText(BA.ObjectToCharSequence("runter 4-1"));
 //BA.debugLineNum = 223;BA.debugLine="cbRunter3_1.Text = \"runter 3-1\"";
_cbrunter3_1.setText(BA.ObjectToCharSequence("runter 3-1"));
 //BA.debugLineNum = 224;BA.debugLine="cbLenkuebung.Text = \"Lenkübungen\"";
_cblenkuebung.setText(BA.ObjectToCharSequence("Lenkübungen"));
 //BA.debugLineNum = 225;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan10(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrtplanung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrtab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabfahrbahnwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwindigkeit,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbababstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschilder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrastparktank,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverhunfall) throws Exception{
 //BA.debugLineNum = 357;BA.debugLine="Sub AusbildungBVFPan10(cbFahrtplanung As CheckBox,";
 //BA.debugLineNum = 359;BA.debugLine="cbFahrtplanung.Text = \"Fahrtplanung\"";
_cbfahrtplanung.setText(BA.ObjectToCharSequence("Fahrtplanung"));
 //BA.debugLineNum = 360;BA.debugLine="cbEinfahrtAB.Text = \"Einfahren in BAB\"";
_cbeinfahrtab.setText(BA.ObjectToCharSequence("Einfahren in BAB"));
 //BA.debugLineNum = 361;BA.debugLine="cbABFahrbahnwechsel.Text = \"Fahrstreifenwahl\"";
_cbabfahrbahnwechsel.setText(BA.ObjectToCharSequence("Fahrstreifenwahl"));
 //BA.debugLineNum = 362;BA.debugLine="cbGeschwindigkeit.Text = \"Geschwindigkeit\"";
_cbgeschwindigkeit.setText(BA.ObjectToCharSequence("Geschwindigkeit"));
 //BA.debugLineNum = 363;BA.debugLine="cbABAbstand.Text = \"Abstand\"";
_cbababstand.setText(BA.ObjectToCharSequence("Abstand"));
 //BA.debugLineNum = 364;BA.debugLine="cbABVorne.Text = \"Vorne\"";
_cbabvorne.setText(BA.ObjectToCharSequence("Vorne"));
 //BA.debugLineNum = 365;BA.debugLine="cbABHinten.Text = \"Hinten\"";
_cbabhinten.setText(BA.ObjectToCharSequence("Hinten"));
 //BA.debugLineNum = 366;BA.debugLine="cbABSeitlich.Text = \"Seitlich\"";
_cbabseitlich.setText(BA.ObjectToCharSequence("Seitlich"));
 //BA.debugLineNum = 367;BA.debugLine="cbABUeberholen.Text = \"Überholen\"";
_cbabueberholen.setText(BA.ObjectToCharSequence("Überholen"));
 //BA.debugLineNum = 368;BA.debugLine="cbSchilder.Text = \"Schilder / Markierungen\"";
_cbschilder.setText(BA.ObjectToCharSequence("Schilder / Markierungen"));
 //BA.debugLineNum = 369;BA.debugLine="cbVorbeifahren.Text = \"Vorbeifahren / Anschlußste";
_cbvorbeifahren.setText(BA.ObjectToCharSequence("Vorbeifahren / Anschlußstellen"));
 //BA.debugLineNum = 370;BA.debugLine="cbRastParkTank.Text = \"Rast-/Parkplätze / Tankste";
_cbrastparktank.setText(BA.ObjectToCharSequence("Rast-/Parkplätze / Tankstellen"));
 //BA.debugLineNum = 371;BA.debugLine="cbVerhUnfall.Text = \"Verhalten bei Unfällen\"";
_cbverhunfall.setText(BA.ObjectToCharSequence("Verhalten bei Unfällen"));
 //BA.debugLineNum = 372;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan11(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdichterverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbableistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkonfliktsitua,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabablenkung,anywheresoftware.b4a.objects.LabelWrapper _lbldaemmerung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstell,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverlassenbab) throws Exception{
 //BA.debugLineNum = 374;BA.debugLine="Sub AusbildungBVFPan11(cbDichterVerkehr As CheckBo";
 //BA.debugLineNum = 376;BA.debugLine="cbDichterVerkehr.Text = \"Dichter Verkehr / Stau\"";
_cbdichterverkehr.setText(BA.ObjectToCharSequence("Dichter Verkehr / Stau"));
 //BA.debugLineNum = 377;BA.debugLine="cbBesonderSituat.Text = \"Besondere Situationen\"";
_cbbesondersituat.setText(BA.ObjectToCharSequence("Besondere Situationen"));
 //BA.debugLineNum = 378;BA.debugLine="cbBesonderAnford.Text = \"Besondere Anforderungen\"";
_cbbesonderanford.setText(BA.ObjectToCharSequence("Besondere Anforderungen"));
 //BA.debugLineNum = 379;BA.debugLine="cbABLeistungsgrenze.Text = \"Leitungsgrenze\"";
_cbableistungsgrenze.setText(BA.ObjectToCharSequence("Leitungsgrenze"));
 //BA.debugLineNum = 380;BA.debugLine="cbKonfliktSitua.Text = \"Konflikt Situationen\"";
_cbkonfliktsitua.setText(BA.ObjectToCharSequence("Konflikt Situationen"));
 //BA.debugLineNum = 381;BA.debugLine="cbABAblenkung.Text = \"Ablenkung\"";
_cbabablenkung.setText(BA.ObjectToCharSequence("Ablenkung"));
 //BA.debugLineNum = 382;BA.debugLine="lblDaemmerung.Text = \"Dämmerung/Dunkelheit\"";
_lbldaemmerung.setText(BA.ObjectToCharSequence("Dämmerung/Dunkelheit"));
 //BA.debugLineNum = 383;BA.debugLine="cbBeleuchtung.Text = \"Beleuchtung\"";
_cbbeleuchtung.setText(BA.ObjectToCharSequence("Beleuchtung"));
 //BA.debugLineNum = 384;BA.debugLine="cbKontrolle.Text = \"Kontrolle\"";
_cbkontrolle.setText(BA.ObjectToCharSequence("Kontrolle"));
 //BA.debugLineNum = 385;BA.debugLine="cbEinstell.Text = \"Einstellung\"";
_cbeinstell.setText(BA.ObjectToCharSequence("Einstellung"));
 //BA.debugLineNum = 386;BA.debugLine="cbBenutzung.Text = \"Benutzung\"";
_cbbenutzung.setText(BA.ObjectToCharSequence("Benutzung"));
 //BA.debugLineNum = 387;BA.debugLine="cbFernlicht.Text = \"Fernlicht\"";
_cbfernlicht.setText(BA.ObjectToCharSequence("Fernlicht"));
 //BA.debugLineNum = 388;BA.debugLine="cbVerlassenBAB.Text = \"Verlassen der BAB\"";
_cbverlassenbab.setText(BA.ObjectToCharSequence("Verlassen der BAB"));
 //BA.debugLineNum = 389;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan12(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbparken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlechtewitterung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergaenge,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbelverkehrtn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesonderanfor,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblendung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbduorientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabschlussbesp) throws Exception{
 //BA.debugLineNum = 391;BA.debugLine="Sub AusbildungBVFPan12(cbBeleuchtStrasse As CheckB";
 //BA.debugLineNum = 394;BA.debugLine="cbBeleuchtStrasse.Text = \"Beleuchtete Strassen\"";
_cbbeleuchtstrasse.setText(BA.ObjectToCharSequence("Beleuchtete Strassen"));
 //BA.debugLineNum = 395;BA.debugLine="cbUnbeleuchtStrasse.Text = \"Unbeleuchtet Strassen";
_cbunbeleuchtstrasse.setText(BA.ObjectToCharSequence("Unbeleuchtet Strassen"));
 //BA.debugLineNum = 396;BA.debugLine="cbParken.Text = \"Parken\"";
_cbparken.setText(BA.ObjectToCharSequence("Parken"));
 //BA.debugLineNum = 397;BA.debugLine="cbDUBesonderSituat.Text = \"Besondere Situationen\"";
_cbdubesondersituat.setText(BA.ObjectToCharSequence("Besondere Situationen"));
 //BA.debugLineNum = 398;BA.debugLine="cbSchlechteWitterung.Text = \"Schlechte Witterung\"";
_cbschlechtewitterung.setText(BA.ObjectToCharSequence("Schlechte Witterung"));
 //BA.debugLineNum = 399;BA.debugLine="cbTiere.Text = \"Tiere\"";
_cbtiere.setText(BA.ObjectToCharSequence("Tiere"));
 //BA.debugLineNum = 400;BA.debugLine="cbBahnuebergaenge.Text = \"Bahnübergänge\"";
_cbbahnuebergaenge.setText(BA.ObjectToCharSequence("Bahnübergänge"));
 //BA.debugLineNum = 401;BA.debugLine="cbUnbelVerkehrTN.Text = \"Unbeleuchtete Verkehrste";
_cbunbelverkehrtn.setText(BA.ObjectToCharSequence("Unbeleuchtete Verkehrsteilnehmer"));
 //BA.debugLineNum = 402;BA.debugLine="cbDUBesonderAnfor.Text = \"Besondere Anforderungen";
_cbdubesonderanfor.setText(BA.ObjectToCharSequence("Besondere Anforderungen"));
 //BA.debugLineNum = 403;BA.debugLine="cbBlendung.Text = \"Blendung\"";
_cbblendung.setText(BA.ObjectToCharSequence("Blendung"));
 //BA.debugLineNum = 404;BA.debugLine="cbDUOrientierung.Text = \"Orientierung\"";
_cbduorientierung.setText(BA.ObjectToCharSequence("Orientierung"));
 //BA.debugLineNum = 405;BA.debugLine="cbAbschlussbesp.Text = \"Abschlussbesprechung\"";
_cbabschlussbesp.setText(BA.ObjectToCharSequence("Abschlussbesprechung"));
 //BA.debugLineNum = 406;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan13(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbselbstfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbinnerorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbausserorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverantwfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtestfpruef,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfakt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbandere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwiederholung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsbew) throws Exception{
 //BA.debugLineNum = 408;BA.debugLine="Sub AusbildungBVFPan13(cbSelbstFahren As CheckBox,";
 //BA.debugLineNum = 410;BA.debugLine="cbSelbstFahren.Text = \"Selbstständiges Fahren\"";
_cbselbstfahren.setText(BA.ObjectToCharSequence("Selbstständiges Fahren"));
 //BA.debugLineNum = 411;BA.debugLine="cbInnerorts.Text = \"Innerorts\"";
_cbinnerorts.setText(BA.ObjectToCharSequence("Innerorts"));
 //BA.debugLineNum = 412;BA.debugLine="cbAusserorts.Text = \"Außerorts\"";
_cbausserorts.setText(BA.ObjectToCharSequence("Außerorts"));
 //BA.debugLineNum = 413;BA.debugLine="cbVerantwFahren.Text = \"Verantwortungsbewusstes F";
_cbverantwfahren.setText(BA.ObjectToCharSequence("Verantwortungsbewusstes Fahren"));
 //BA.debugLineNum = 414;BA.debugLine="cbTestfPruef.Text = \"Testfahrt unter Prüfungsbedi";
_cbtestfpruef.setText(BA.ObjectToCharSequence("Testfahrt unter Prüfungsbedingungen"));
 //BA.debugLineNum = 415;BA.debugLine="cbFAKT.Text = \"FAKT\"";
_cbfakt.setText(BA.ObjectToCharSequence("FAKT"));
 //BA.debugLineNum = 416;BA.debugLine="cbAndere.Text = \"Andere\"";
_cbandere.setText(BA.ObjectToCharSequence("Andere"));
 //BA.debugLineNum = 417;BA.debugLine="cbWiederholung.Text = \"Wiederholung / Vertiefung\"";
_cbwiederholung.setText(BA.ObjectToCharSequence("Wiederholung / Vertiefung"));
 //BA.debugLineNum = 418;BA.debugLine="cbLeistungsbew.Text = \"Leistungsbewertung\"";
_cbleistungsbew.setText(BA.ObjectToCharSequence("Leistungsbewertung"));
 //BA.debugLineNum = 419;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan14(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblchecklistefahrvorb,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinausschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktionpruefen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstandlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbnebelschluss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblinker,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabblendlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarnblicke,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhupe,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbsfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlussleuchte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremslicht) throws Exception{
 //BA.debugLineNum = 421;BA.debugLine="Sub AusbildungBVFPan14(lblChecklisteFahrvorb As La";
 //BA.debugLineNum = 423;BA.debugLine="lblChecklisteFahrvorb.Text = \"Checkliste zur fahr";
_lblchecklistefahrvorb.setText(BA.ObjectToCharSequence("Checkliste zur fahrtechn. Vorbereitung"));
 //BA.debugLineNum = 424;BA.debugLine="cbReifen.Text = \"Reifen (z.B. Beschädigung, ...)\"";
_cbreifen.setText(BA.ObjectToCharSequence("Reifen (z.B. Beschädigung, ...)"));
 //BA.debugLineNum = 425;BA.debugLine="cbEinAusschalten.Text = \"Ein- Aussschalten (Schei";
_cbeinausschalten.setText(BA.ObjectToCharSequence("Ein- Aussschalten (Scheinwerfer, ...)"));
 //BA.debugLineNum = 426;BA.debugLine="cbFunktionPruefen.Text = \"Funktion prüfen\"";
_cbfunktionpruefen.setText(BA.ObjectToCharSequence("Funktion prüfen"));
 //BA.debugLineNum = 427;BA.debugLine="cbStandlicht.Text = \"Standlicht\"";
_cbstandlicht.setText(BA.ObjectToCharSequence("Standlicht"));
 //BA.debugLineNum = 428;BA.debugLine="cbNebelschluss.Text = \"Nebelschlussleuchte\"";
_cbnebelschluss.setText(BA.ObjectToCharSequence("Nebelschlussleuchte"));
 //BA.debugLineNum = 429;BA.debugLine="cbBlinker.Text = \"Blinker\"";
_cbblinker.setText(BA.ObjectToCharSequence("Blinker"));
 //BA.debugLineNum = 430;BA.debugLine="cbAbblendlicht.Text = \"Abblendlicht\"";
_cbabblendlicht.setText(BA.ObjectToCharSequence("Abblendlicht"));
 //BA.debugLineNum = 431;BA.debugLine="cbWarnblicke.Text = \"Warnblickanlage\"";
_cbwarnblicke.setText(BA.ObjectToCharSequence("Warnblickanlage"));
 //BA.debugLineNum = 432;BA.debugLine="cbHupe.Text = \"Hupe\"";
_cbhupe.setText(BA.ObjectToCharSequence("Hupe"));
 //BA.debugLineNum = 433;BA.debugLine="cbBSFernlicht.Text = \"Fernlicht\"";
_cbbsfernlicht.setText(BA.ObjectToCharSequence("Fernlicht"));
 //BA.debugLineNum = 434;BA.debugLine="cbSchlussLeuchte.Text = \"Schlussleuchten m. Kennz";
_cbschlussleuchte.setText(BA.ObjectToCharSequence("Schlussleuchten m. Kennzbel."));
 //BA.debugLineNum = 435;BA.debugLine="cbBremslicht.Text = \"Bremsleuchte\"";
_cbbremslicht.setText(BA.ObjectToCharSequence("Bremsleuchte"));
 //BA.debugLineNum = 436;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan15(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolllbenenn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckstrahler,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorhandensein,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeschaedigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkschlentriegeln,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbprueflenkspiel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbetriebsbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfeststellbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanlegengurt) throws Exception{
 //BA.debugLineNum = 438;BA.debugLine="Sub AusbildungBVFPan15(cbKontrollLBenenn As CheckB";
 //BA.debugLineNum = 440;BA.debugLine="cbKontrollLBenenn.Text = \"Kontrollleuchten benenn";
_cbkontrolllbenenn.setText(BA.ObjectToCharSequence("Kontrollleuchten benennen"));
 //BA.debugLineNum = 441;BA.debugLine="cbRueckstrahler.Text = \"Rückstrahler\"";
_cbrueckstrahler.setText(BA.ObjectToCharSequence("Rückstrahler"));
 //BA.debugLineNum = 442;BA.debugLine="cbVorhandensein.Text = \"Vorhandensein\"";
_cbvorhandensein.setText(BA.ObjectToCharSequence("Vorhandensein"));
 //BA.debugLineNum = 443;BA.debugLine="cbBeschaedigung.Text = \"Beschädigungen\"";
_cbbeschaedigung.setText(BA.ObjectToCharSequence("Beschädigungen"));
 //BA.debugLineNum = 444;BA.debugLine="cbLenkung.Text = \"Lenkung\"";
_cblenkung.setText(BA.ObjectToCharSequence("Lenkung"));
 //BA.debugLineNum = 445;BA.debugLine="cbLenkschlEntriegeln.Text = \"Lenkschloss entriege";
_cblenkschlentriegeln.setText(BA.ObjectToCharSequence("Lenkschloss entriegeln"));
 //BA.debugLineNum = 446;BA.debugLine="cbPruefLenkSpiel.Text = \"Überprüfung des Lenkspie";
_cbprueflenkspiel.setText(BA.ObjectToCharSequence("Überprüfung des Lenkspiels"));
 //BA.debugLineNum = 447;BA.debugLine="cbFunktBremse.Text = \"Funktionsprüfung der Bremse";
_cbfunktbremse.setText(BA.ObjectToCharSequence("Funktionsprüfung der Bremse"));
 //BA.debugLineNum = 448;BA.debugLine="cbBetriebsBremse.Text = \"Betriebsbremse\"";
_cbbetriebsbremse.setText(BA.ObjectToCharSequence("Betriebsbremse"));
 //BA.debugLineNum = 449;BA.debugLine="cbFeststellBremse.Text = \"Feststellbremse\"";
_cbfeststellbremse.setText(BA.ObjectToCharSequence("Feststellbremse"));
 //BA.debugLineNum = 450;BA.debugLine="cbAnlegenGurt.Text = \"Anlegen des Sicherheitsgurt";
_cbanlegengurt.setText(BA.ObjectToCharSequence("Anlegen des Sicherheitsgurts"));
 //BA.debugLineNum = 451;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan16(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrichtigsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellrueckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinlenkrad,anywheresoftware.b4a.objects.LabelWrapper _lblheizunglueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienenagg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheckheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbehsonderaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbklimaanlage) throws Exception{
 //BA.debugLineNum = 453;BA.debugLine="Sub AusbildungBVFPan16(cbRichtigSitz As CheckBox,";
 //BA.debugLineNum = 455;BA.debugLine="cbRichtigSitz.Text = \"Richtige Sitzstellung\"";
_cbrichtigsitz.setText(BA.ObjectToCharSequence("Richtige Sitzstellung"));
 //BA.debugLineNum = 456;BA.debugLine="cbEinstellRueckspiegel.Text = \"Einstellen des Rüc";
_cbeinstellrueckspiegel.setText(BA.ObjectToCharSequence("Einstellen des Rückspiegels"));
 //BA.debugLineNum = 457;BA.debugLine="cbEinKopfstuetze.Text = \"der Kopfstütze\"";
_cbeinkopfstuetze.setText(BA.ObjectToCharSequence("der Kopfstütze"));
 //BA.debugLineNum = 458;BA.debugLine="cbEinLenkrad.Text = \"des Lenkrades\"";
_cbeinlenkrad.setText(BA.ObjectToCharSequence("des Lenkrades"));
 //BA.debugLineNum = 459;BA.debugLine="lblHeizungLueftung.Text = \"Heizung und Lüftung\"";
_lblheizunglueftung.setText(BA.ObjectToCharSequence("Heizung und Lüftung"));
 //BA.debugLineNum = 460;BA.debugLine="cbBedienenAgg.Text = \"Bedienen der Aggregate\"";
_cbbedienenagg.setText(BA.ObjectToCharSequence("Bedienen der Aggregate"));
 //BA.debugLineNum = 461;BA.debugLine="cbHeizung.Text = \"Heizung\"";
_cbheizung.setText(BA.ObjectToCharSequence("Heizung"));
 //BA.debugLineNum = 462;BA.debugLine="cbHeckHeizung.Text = \"Heckscheibenheizung\"";
_cbheckheizung.setText(BA.ObjectToCharSequence("Heckscheibenheizung"));
 //BA.debugLineNum = 463;BA.debugLine="cbBehSonderaus.Text = \"Beheizte Sonderausstattung";
_cbbehsonderaus.setText(BA.ObjectToCharSequence("Beheizte Sonderausstattung"));
 //BA.debugLineNum = 464;BA.debugLine="cbLueftung.Text = \"Lüftung\"";
_cblueftung.setText(BA.ObjectToCharSequence("Lüftung"));
 //BA.debugLineNum = 465;BA.debugLine="cbKlimaanlage.Text = \"Klimaanlage\"";
_cbklimaanlage.setText(BA.ObjectToCharSequence("Klimaanlage"));
 //BA.debugLineNum = 466;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan17(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbenergienutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkeineunnverbr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtztabsch,anywheresoftware.b4a.objects.LabelWrapper _lblbetriebsverkehrssich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotorraum,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoroel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkuehlmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwaschm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtanken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsen) throws Exception{
 //BA.debugLineNum = 468;BA.debugLine="Sub AusbildungBVFPan17(cbEnergieNutzung As CheckBo";
 //BA.debugLineNum = 470;BA.debugLine="cbEnergieNutzung.Text = \"Energiesparende Nutzung\"";
_cbenergienutzung.setText(BA.ObjectToCharSequence("Energiesparende Nutzung"));
 //BA.debugLineNum = 471;BA.debugLine="cbKeineUnnVerbr.Text = \"Keine unnötigen Verbrauch";
_cbkeineunnverbr.setText(BA.ObjectToCharSequence("Keine unnötigen Verbraucher"));
 //BA.debugLineNum = 472;BA.debugLine="cbRechtztAbsch.Text = \"Rechtzeitiges Abschalten\"";
_cbrechtztabsch.setText(BA.ObjectToCharSequence("Rechtzeitiges Abschalten"));
 //BA.debugLineNum = 473;BA.debugLine="lblBetriebsVerkehrssich.Text = \"Betriebs- und Ver";
_lblbetriebsverkehrssich.setText(BA.ObjectToCharSequence("Betriebs- und Verkehrssicherheit"));
 //BA.debugLineNum = 474;BA.debugLine="cbMotorraum.Text = \"Motorraum / Flüssigkeitsständ";
_cbmotorraum.setText(BA.ObjectToCharSequence("Motorraum / Flüssigkeitsstände"));
 //BA.debugLineNum = 475;BA.debugLine="cbMotoroel.Text = \"Motoröl\"";
_cbmotoroel.setText(BA.ObjectToCharSequence("Motoröl"));
 //BA.debugLineNum = 476;BA.debugLine="cbKuehlmittel.Text = \"Kühlmittel\"";
_cbkuehlmittel.setText(BA.ObjectToCharSequence("Kühlmittel"));
 //BA.debugLineNum = 477;BA.debugLine="cbScheibenwaschm.Text = \"Scheibenwaschflüssigkeit";
_cbscheibenwaschm.setText(BA.ObjectToCharSequence("Scheibenwaschflüssigkeit"));
 //BA.debugLineNum = 478;BA.debugLine="cbTanken.Text = \"Tanken\"";
_cbtanken.setText(BA.ObjectToCharSequence("Tanken"));
 //BA.debugLineNum = 479;BA.debugLine="cbBremsen.Text = \"Bremsen\"";
_cbbremsen.setText(BA.ObjectToCharSequence("Bremsen"));
 //BA.debugLineNum = 480;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan18(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsicherungsmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarndreieck,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbordwerkzeug,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzusaetzlichaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverbandskasten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaussenkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwischer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkennzeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladungssicherung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkenntlichmachung) throws Exception{
 //BA.debugLineNum = 482;BA.debugLine="Sub AusbildungBVFPan18(cbSicherungsmittel As Check";
 //BA.debugLineNum = 485;BA.debugLine="cbSicherungsmittel.Text = \"Sicherungsmittel\"";
_cbsicherungsmittel.setText(BA.ObjectToCharSequence("Sicherungsmittel"));
 //BA.debugLineNum = 486;BA.debugLine="cbWarndreieck.Text = \"Warndreieck\"";
_cbwarndreieck.setText(BA.ObjectToCharSequence("Warndreieck"));
 //BA.debugLineNum = 487;BA.debugLine="cbBordwerkzeug.Text = \"Bordwerkzeug\"";
_cbbordwerkzeug.setText(BA.ObjectToCharSequence("Bordwerkzeug"));
 //BA.debugLineNum = 488;BA.debugLine="cbZusaetzlichAus.Text = \"Zusätzliche Ausrüstung\"";
_cbzusaetzlichaus.setText(BA.ObjectToCharSequence("Zusätzliche Ausrüstung"));
 //BA.debugLineNum = 489;BA.debugLine="cbVerbandskasten.Text = \"Verbandskasten\"";
_cbverbandskasten.setText(BA.ObjectToCharSequence("Verbandskasten"));
 //BA.debugLineNum = 490;BA.debugLine="cbAussenkontrolle.Text = \"Aussenkontrolle (Schäde";
_cbaussenkontrolle.setText(BA.ObjectToCharSequence("Aussenkontrolle (Schäden,...)"));
 //BA.debugLineNum = 491;BA.debugLine="cbScheibenWischer.Text = \"Scheiben / Wischer\"";
_cbscheibenwischer.setText(BA.ObjectToCharSequence("Scheiben / Wischer"));
 //BA.debugLineNum = 492;BA.debugLine="cbKennzeichen.Text = \"Kennzeichen (HU / AU)\"";
_cbkennzeichen.setText(BA.ObjectToCharSequence("Kennzeichen (HU / AU)"));
 //BA.debugLineNum = 493;BA.debugLine="cbCheckSpiegel.Text = \"Spiegel\"";
_cbcheckspiegel.setText(BA.ObjectToCharSequence("Spiegel"));
 //BA.debugLineNum = 494;BA.debugLine="cbCheckBeleuchtung.Text = \"Beleuchtung\"";
_cbcheckbeleuchtung.setText(BA.ObjectToCharSequence("Beleuchtung"));
 //BA.debugLineNum = 495;BA.debugLine="cbLadung.Text = \"Ladung\"";
_cbladung.setText(BA.ObjectToCharSequence("Ladung"));
 //BA.debugLineNum = 496;BA.debugLine="cbLadungssicherung.Text = \"Ladungssicherung\"";
_cbladungssicherung.setText(BA.ObjectToCharSequence("Ladungssicherung"));
 //BA.debugLineNum = 497;BA.debugLine="cbKenntlichmachung.Text = \"Kenntlichmachung\"";
_cbkenntlichmachung.setText(BA.ObjectToCharSequence("Kenntlichmachung"));
 //BA.debugLineNum = 498;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan19(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahreschlwitt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittlueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittscheiben,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbregen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwasserlachen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwindsturm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmatchschnee,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeis,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittbeleuchtung) throws Exception{
 //BA.debugLineNum = 500;BA.debugLine="Sub AusbildungBVFPan19(cbFahreSchlWitt As CheckBox";
 //BA.debugLineNum = 502;BA.debugLine="cbFahreSchlWitt.Text = \"Fahren bei schlechter Wit";
_cbfahreschlwitt.setText(BA.ObjectToCharSequence("Fahren bei schlechter Witterung"));
 //BA.debugLineNum = 503;BA.debugLine="cbWittLueftung.Text = \"Lüftung\"";
_cbwittlueftung.setText(BA.ObjectToCharSequence("Lüftung"));
 //BA.debugLineNum = 504;BA.debugLine="cbWittScheiben.Text = \"Scheibenwischer / -wasser\"";
_cbwittscheiben.setText(BA.ObjectToCharSequence("Scheibenwischer / -wasser"));
 //BA.debugLineNum = 505;BA.debugLine="cbRegen.Text = \"Regen / Sprühnebel\"";
_cbregen.setText(BA.ObjectToCharSequence("Regen / Sprühnebel"));
 //BA.debugLineNum = 506;BA.debugLine="cbWasserlachen.Text = \"Wasserlachen / Aquaplaning";
_cbwasserlachen.setText(BA.ObjectToCharSequence("Wasserlachen / Aquaplaning"));
 //BA.debugLineNum = 507;BA.debugLine="cbWindSturm.Text = \"Wind, Sturm, Böen\"";
_cbwindsturm.setText(BA.ObjectToCharSequence("Wind, Sturm, Böen"));
 //BA.debugLineNum = 508;BA.debugLine="cbMatchSchnee.Text = \"Match und Schnee\"";
_cbmatchschnee.setText(BA.ObjectToCharSequence("Match und Schnee"));
 //BA.debugLineNum = 509;BA.debugLine="cbEis.Text = \"Eis\"";
_cbeis.setText(BA.ObjectToCharSequence("Eis"));
 //BA.debugLineNum = 510;BA.debugLine="cbWittBeleuchtung.Text = \"Beleuchtung\"";
_cbwittbeleuchtung.setText(BA.ObjectToCharSequence("Beleuchtung"));
 //BA.debugLineNum = 511;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan2(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbumkehren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenlaengs,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaertsfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenquer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrbremsung) throws Exception{
 //BA.debugLineNum = 227;BA.debugLine="Sub AusbildungBVFPan2(cbUmkehren As CheckBox, cbEi";
 //BA.debugLineNum = 230;BA.debugLine="cbUmkehren.Text = \"Umkehren\"";
_cbumkehren.setText(BA.ObjectToCharSequence("Umkehren"));
 //BA.debugLineNum = 231;BA.debugLine="cbEinparkenLaengs.Text = \"Einparken längs\"";
_cbeinparkenlaengs.setText(BA.ObjectToCharSequence("Einparken längs"));
 //BA.debugLineNum = 232;BA.debugLine="cbLVorwaertsRechts.Text = \"vorwärts rechts\"";
_cblvorwaertsrechts.setText(BA.ObjectToCharSequence("vorwärts rechts"));
 //BA.debugLineNum = 233;BA.debugLine="cbLRueckwaertsLinks.Text = \"rückwärts links\"";
_cblrueckwaertslinks.setText(BA.ObjectToCharSequence("rückwärts links"));
 //BA.debugLineNum = 234;BA.debugLine="cbLRueckwaertsRechts.Text = \"rückwärts rechts\"";
_cblrueckwaertsrechts.setText(BA.ObjectToCharSequence("rückwärts rechts"));
 //BA.debugLineNum = 235;BA.debugLine="cbLVorwaertsLinks.Text = \"vorwärts links\"";
_cblvorwaertslinks.setText(BA.ObjectToCharSequence("vorwärts links"));
 //BA.debugLineNum = 236;BA.debugLine="cbRueckwaertsfahren.Text = \"Rückwärtsfahren\"";
_cbrueckwaertsfahren.setText(BA.ObjectToCharSequence("Rückwärtsfahren"));
 //BA.debugLineNum = 237;BA.debugLine="cbEinparkenQuer.Text = \"Einparken quer\"";
_cbeinparkenquer.setText(BA.ObjectToCharSequence("Einparken quer"));
 //BA.debugLineNum = 238;BA.debugLine="cbQVorwaertsRechts.Text = \"vorwärts rechts\"";
_cbqvorwaertsrechts.setText(BA.ObjectToCharSequence("vorwärts rechts"));
 //BA.debugLineNum = 239;BA.debugLine="cbQRueckwaertsLinks.Text = \"rückwärts links\"";
_cbqrueckwaertslinks.setText(BA.ObjectToCharSequence("rückwärts links"));
 //BA.debugLineNum = 240;BA.debugLine="cbQRueckwaertsRechts.Text = \"rückwärts rechts\"";
_cbqrueckwaertsrechts.setText(BA.ObjectToCharSequence("rückwärts rechts"));
 //BA.debugLineNum = 241;BA.debugLine="cbQVorwaertsLinks.Text = \"vorwärts links\"";
_cbqvorwaertslinks.setText(BA.ObjectToCharSequence("vorwärts links"));
 //BA.debugLineNum = 242;BA.debugLine="cbGefahrbremsung.Text =\" Gefahrbremsung\"";
_cbgefahrbremsung.setText(BA.ObjectToCharSequence(" Gefahrbremsung"));
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan3(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrollenschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdegressiv,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzielbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrsituation,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschalten) throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub AusbildungBVFPan3(cbRollenSchalten As CheckBox";
 //BA.debugLineNum = 247;BA.debugLine="cbRollenSchalten.Text = \"Rollen und Schalten\"";
_cbrollenschalten.setText(BA.ObjectToCharSequence("Rollen und Schalten"));
 //BA.debugLineNum = 248;BA.debugLine="cbBremsSchalten.Text = \"Abbremsen und Schalten\"";
_cbbremsschalten.setText(BA.ObjectToCharSequence("Abbremsen und Schalten"));
 //BA.debugLineNum = 249;BA.debugLine="cbBremsuebung.Text = \"Bremsübungen\"";
_cbbremsuebung.setText(BA.ObjectToCharSequence("Bremsübungen"));
 //BA.debugLineNum = 250;BA.debugLine="cbDegressiv.Text = \"degressiv\"";
_cbdegressiv.setText(BA.ObjectToCharSequence("degressiv"));
 //BA.debugLineNum = 251;BA.debugLine="cbZielbremsung.Text = \"Zielbremsung\"";
_cbzielbremsung.setText(BA.ObjectToCharSequence("Zielbremsung"));
 //BA.debugLineNum = 252;BA.debugLine="cbGefahrsituation.Text = \"Gefahrsituation\"";
_cbgefahrsituation.setText(BA.ObjectToCharSequence("Gefahrsituation"));
 //BA.debugLineNum = 253;BA.debugLine="cbGefaelle.Text = \"Gefälle\"";
_cbgefaelle.setText(BA.ObjectToCharSequence("Gefälle"));
 //BA.debugLineNum = 254;BA.debugLine="cbAnhalten.Text =\"Anhalten\"";
_cbanhalten.setText(BA.ObjectToCharSequence("Anhalten"));
 //BA.debugLineNum = 255;BA.debugLine="cbAnfahren.Text =\"Anfahren\"";
_cbanfahren.setText(BA.ObjectToCharSequence("Anfahren"));
 //BA.debugLineNum = 256;BA.debugLine="cbRueckwaerts.Text = \"Rückwärts\"";
_cbrueckwaerts.setText(BA.ObjectToCharSequence("Rückwärts"));
 //BA.debugLineNum = 257;BA.debugLine="cbSichern.Text = \"Sichern\"";
_cbsichern.setText(BA.ObjectToCharSequence("Sichern"));
 //BA.debugLineNum = 258;BA.debugLine="cbSchalten.Text = \"Schalten\"";
_cbschalten.setText(BA.ObjectToCharSequence("Schalten"));
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan4(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtastgeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienkontroll,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboertlichbesonder) throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Sub AusbildungBVFPan4(cbSteigung As CheckBox, cbSt";
 //BA.debugLineNum = 263;BA.debugLine="cbSteigung.Text = \"Steigung\"";
_cbsteigung.setText(BA.ObjectToCharSequence("Steigung"));
 //BA.debugLineNum = 264;BA.debugLine="cbStAnhalten.Text = \"Anhalten\"";
_cbstanhalten.setText(BA.ObjectToCharSequence("Anhalten"));
 //BA.debugLineNum = 265;BA.debugLine="cbStAnfahren.Text = \"Anfahren\"";
_cbstanfahren.setText(BA.ObjectToCharSequence("Anfahren"));
 //BA.debugLineNum = 266;BA.debugLine="cbStRueckwaerts.Text = \"Rüeckwärts\"";
_cbstrueckwaerts.setText(BA.ObjectToCharSequence("Rüeckwärts"));
 //BA.debugLineNum = 267;BA.debugLine="cbStSichern.Text = \"Sichern\"";
_cbstsichern.setText(BA.ObjectToCharSequence("Sichern"));
 //BA.debugLineNum = 268;BA.debugLine="cbStSchalten.Text = \"Schalten\"";
_cbstschalten.setText(BA.ObjectToCharSequence("Schalten"));
 //BA.debugLineNum = 269;BA.debugLine="cbTastgeschw.Text = \"Tastgeschwindigkeit\"";
_cbtastgeschw.setText(BA.ObjectToCharSequence("Tastgeschwindigkeit"));
 //BA.debugLineNum = 270;BA.debugLine="cbBedienKontroll.Text = \"Bedienungs-/Kontrolleinr";
_cbbedienkontroll.setText(BA.ObjectToCharSequence("Bedienungs-/Kontrolleinrichtungen"));
 //BA.debugLineNum = 271;BA.debugLine="cbOertlichBesonder.Text = \"Örtliche Besonderheite";
_cboertlichbesonder.setText(BA.ObjectToCharSequence("Örtliche Besonderheiten"));
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan5(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrbahnbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinordnen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmarkierungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrstreifenwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabbiegen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmehrspurig,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsonderstreifen) throws Exception{
 //BA.debugLineNum = 274;BA.debugLine="Sub AusbildungBVFPan5(cbFahrbahnbenutzung As Check";
 //BA.debugLineNum = 276;BA.debugLine="cbFahrbahnbenutzung.Text = \"Fahrbahnbenutzung\"";
_cbfahrbahnbenutzung.setText(BA.ObjectToCharSequence("Fahrbahnbenutzung"));
 //BA.debugLineNum = 277;BA.debugLine="cbEinordnen.Text = \"Einordnen\"";
_cbeinordnen.setText(BA.ObjectToCharSequence("Einordnen"));
 //BA.debugLineNum = 278;BA.debugLine="cbMarkierungen.Text = \"Markierungen\"";
_cbmarkierungen.setText(BA.ObjectToCharSequence("Markierungen"));
 //BA.debugLineNum = 279;BA.debugLine="cbFahrstreifenwechsel.Text = \"Fahrstreifenwechsel";
_cbfahrstreifenwechsel.setText(BA.ObjectToCharSequence("Fahrstreifenwechsel"));
 //BA.debugLineNum = 280;BA.debugLine="cbLinks.Text = \"Links\"";
_cblinks.setText(BA.ObjectToCharSequence("Links"));
 //BA.debugLineNum = 281;BA.debugLine="cbRechts.Text = \"Rechts\"";
_cbrechts.setText(BA.ObjectToCharSequence("Rechts"));
 //BA.debugLineNum = 282;BA.debugLine="cbVorbeifUeberholen.Text = \"Vorbeifahren / Überho";
_cbvorbeifueberholen.setText(BA.ObjectToCharSequence("Vorbeifahren / Überholen"));
 //BA.debugLineNum = 283;BA.debugLine="cbAbbiegen.Text = \"Abbiegen\"";
_cbabbiegen.setText(BA.ObjectToCharSequence("Abbiegen"));
 //BA.debugLineNum = 284;BA.debugLine="cbABRechts.Text = \"Rechts\"";
_cbabrechts.setText(BA.ObjectToCharSequence("Rechts"));
 //BA.debugLineNum = 285;BA.debugLine="cbABLinks.Text = \"Links\"";
_cbablinks.setText(BA.ObjectToCharSequence("Links"));
 //BA.debugLineNum = 286;BA.debugLine="cbMehrspurig.Text = \"Mehrspurig\"";
_cbmehrspurig.setText(BA.ObjectToCharSequence("Mehrspurig"));
 //BA.debugLineNum = 287;BA.debugLine="cbRadweg.Text = \"Radweg\"";
_cbradweg.setText(BA.ObjectToCharSequence("Radweg"));
 //BA.debugLineNum = 288;BA.debugLine="cbSonderstreifen.Text = \"Sonderstreifen\"";
_cbsonderstreifen.setText(BA.ObjectToCharSequence("Sonderstreifen"));
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan6(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrassenbahn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorfahrt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtsvorlinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeil,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpolizeibeamte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeilschild,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsituationverkehrstn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerueberweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboeffentlverkehrsm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaelterebehinderte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrradfahrer) throws Exception{
 //BA.debugLineNum = 291;BA.debugLine="Sub AusbildungBVFPan6(cbStrassenbahn As CheckBox,";
 //BA.debugLineNum = 294;BA.debugLine="cbStrassenbahn.Text = \"Strassenbahnen\"";
_cbstrassenbahn.setText(BA.ObjectToCharSequence("Strassenbahnen"));
 //BA.debugLineNum = 295;BA.debugLine="cbEinbahnstrasse.Text = \"Einbahnstrassen\"";
_cbeinbahnstrasse.setText(BA.ObjectToCharSequence("Einbahnstrassen"));
 //BA.debugLineNum = 296;BA.debugLine="cbVorfahrt.Text = \"Vorfahrt\"";
_cbvorfahrt.setText(BA.ObjectToCharSequence("Vorfahrt"));
 //BA.debugLineNum = 297;BA.debugLine="cbRechtsVorLinks.Text = \"Rechts vor Links\"";
_cbrechtsvorlinks.setText(BA.ObjectToCharSequence("Rechts vor Links"));
 //BA.debugLineNum = 298;BA.debugLine="cbGruenpfeil.Text = \"Grünpfeil\"";
_cbgruenpfeil.setText(BA.ObjectToCharSequence("Grünpfeil"));
 //BA.debugLineNum = 299;BA.debugLine="cbPolizeibeamte.Text = \"Polizeibeamter\"";
_cbpolizeibeamte.setText(BA.ObjectToCharSequence("Polizeibeamter"));
 //BA.debugLineNum = 300;BA.debugLine="cbGruenpfeilSchild.Text = \"Grünpfeil-Schild\"";
_cbgruenpfeilschild.setText(BA.ObjectToCharSequence("Grünpfeil-Schild"));
 //BA.debugLineNum = 301;BA.debugLine="cbGeschwAbstand.Text = \"Geschwindigkeit / Abstand";
_cbgeschwabstand.setText(BA.ObjectToCharSequence("Geschwindigkeit / Abstand"));
 //BA.debugLineNum = 302;BA.debugLine="cbSituationVerkehrstn.Text = \"Situation mit ander";
_cbsituationverkehrstn.setText(BA.ObjectToCharSequence("Situation mit anderen Verkehrs-TN"));
 //BA.debugLineNum = 303;BA.debugLine="cbFussgaengerueberweg.Text = \"Fußgänderüberweg\"";
_cbfussgaengerueberweg.setText(BA.ObjectToCharSequence("Fußgänderüberweg"));
 //BA.debugLineNum = 304;BA.debugLine="cbOeffentlVerkehrsm.Text = \"Öffentliche Verkehrsm";
_cboeffentlverkehrsm.setText(BA.ObjectToCharSequence("Öffentliche Verkehrsmittel"));
 //BA.debugLineNum = 305;BA.debugLine="cbAeltereBehinderte.Text = \"Ältere / Behinderte\"";
_cbaelterebehinderte.setText(BA.ObjectToCharSequence("Ältere / Behinderte"));
 //BA.debugLineNum = 306;BA.debugLine="cbEinbahnstrRadfahrer.Text = \"Einbahnstrassen / R";
_cbeinbahnstrradfahrer.setText(BA.ObjectToCharSequence("Einbahnstrassen / Radfahrer"));
 //BA.debugLineNum = 307;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan7(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkinder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschulbus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradfahrermofa,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrsberuhigt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwierigeverkehrsf,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbengpass,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreisverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergangwarte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkritischeverkehrss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhauptverkehrszt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpartnerverhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwungnutzen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerschutzb) throws Exception{
 //BA.debugLineNum = 309;BA.debugLine="Sub AusbildungBVFPan7(cbKinder As CheckBox, cbSchu";
 //BA.debugLineNum = 312;BA.debugLine="cbKinder.Text = \"Kinder\"";
_cbkinder.setText(BA.ObjectToCharSequence("Kinder"));
 //BA.debugLineNum = 313;BA.debugLine="cbSchulbus.Text = \"Schulbus\"";
_cbschulbus.setText(BA.ObjectToCharSequence("Schulbus"));
 //BA.debugLineNum = 314;BA.debugLine="cbRadfahrerMofa.Text = \"Radfahrer / Mofa\"";
_cbradfahrermofa.setText(BA.ObjectToCharSequence("Radfahrer / Mofa"));
 //BA.debugLineNum = 315;BA.debugLine="cbVerkehrsberuhigt.Text = \"Verkehrsberuhigter Ber";
_cbverkehrsberuhigt.setText(BA.ObjectToCharSequence("Verkehrsberuhigter Bereich"));
 //BA.debugLineNum = 316;BA.debugLine="cbSchwierigeVerkehrsf.Text = \"Schwierige Verkehrs";
_cbschwierigeverkehrsf.setText(BA.ObjectToCharSequence("Schwierige Verkehrsführung"));
 //BA.debugLineNum = 317;BA.debugLine="cbEngpass.Text = \"Engpass\"";
_cbengpass.setText(BA.ObjectToCharSequence("Engpass"));
 //BA.debugLineNum = 318;BA.debugLine="cbKreisverkehr.Text = \"Kreisverkehr\"";
_cbkreisverkehr.setText(BA.ObjectToCharSequence("Kreisverkehr"));
 //BA.debugLineNum = 319;BA.debugLine="cbBahnuebergangWarte.Text = \"Bahnübergang (warten";
_cbbahnuebergangwarte.setText(BA.ObjectToCharSequence("Bahnübergang (warten)"));
 //BA.debugLineNum = 320;BA.debugLine="cbKritischeVerkehrss.Text = \"Kritische Verkehrssi";
_cbkritischeverkehrss.setText(BA.ObjectToCharSequence("Kritische Verkehrssituation"));
 //BA.debugLineNum = 321;BA.debugLine="cbHauptverkehrszt.Text = \"Hauptverkehrszeit\"";
_cbhauptverkehrszt.setText(BA.ObjectToCharSequence("Hauptverkehrszeit"));
 //BA.debugLineNum = 322;BA.debugLine="cbPartnerVerhalten.Text = \"Partnerschaftliches Ve";
_cbpartnerverhalten.setText(BA.ObjectToCharSequence("Partnerschaftliches Verhalten"));
 //BA.debugLineNum = 323;BA.debugLine="cbSchwungNutzen.Text = \"Schwung nutzen\"";
_cbschwungnutzen.setText(BA.ObjectToCharSequence("Schwung nutzen"));
 //BA.debugLineNum = 324;BA.debugLine="cbFussgaengerSchutzb.Text = \"Fußgänger Schutzbere";
_cbfussgaengerschutzb.setText(BA.ObjectToCharSequence("Fußgänger Schutzbereich"));
 //BA.debugLineNum = 325;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan8(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbangepasstegeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeobachtspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrszeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreuzungeinmuend,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkurven,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cballeen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbueberholen) throws Exception{
 //BA.debugLineNum = 327;BA.debugLine="Sub AusbildungBVFPan8(cbAngepassteGeschw As CheckB";
 //BA.debugLineNum = 329;BA.debugLine="cbAngepassteGeschw.Text = \"Angepasste Geschwindig";
_cbangepasstegeschw.setText(BA.ObjectToCharSequence("Angepasste Geschwindigkeit"));
 //BA.debugLineNum = 330;BA.debugLine="cbAbstand.Text = \"Abstand\"";
_cbabstand.setText(BA.ObjectToCharSequence("Abstand"));
 //BA.debugLineNum = 331;BA.debugLine="cbULVorne.Text = \"vorne\"";
_cbulvorne.setText(BA.ObjectToCharSequence("vorne"));
 //BA.debugLineNum = 332;BA.debugLine="cbULHinten.Text = \"hinten\"";
_cbulhinten.setText(BA.ObjectToCharSequence("hinten"));
 //BA.debugLineNum = 333;BA.debugLine="cbULSeitlich.Text = \"seitlich\"";
_cbulseitlich.setText(BA.ObjectToCharSequence("seitlich"));
 //BA.debugLineNum = 334;BA.debugLine="cbBeobachtSpiegel.Text = \"Beobachtung / Spiegel\"";
_cbbeobachtspiegel.setText(BA.ObjectToCharSequence("Beobachtung / Spiegel"));
 //BA.debugLineNum = 335;BA.debugLine="cbVerkehrszeichen.Text = \"Verkehrszeichen\"";
_cbverkehrszeichen.setText(BA.ObjectToCharSequence("Verkehrszeichen"));
 //BA.debugLineNum = 336;BA.debugLine="cbKreuzungEinmuend.Text = \"Kreuzungen / Einmündun";
_cbkreuzungeinmuend.setText(BA.ObjectToCharSequence("Kreuzungen / Einmündungen"));
 //BA.debugLineNum = 337;BA.debugLine="cbKurven.Text = \"Kurven\"";
_cbkurven.setText(BA.ObjectToCharSequence("Kurven"));
 //BA.debugLineNum = 338;BA.debugLine="cbSteigungen.Text = \"Steigungen\"";
_cbsteigungen.setText(BA.ObjectToCharSequence("Steigungen"));
 //BA.debugLineNum = 339;BA.debugLine="cbULGefaelle.Text = \"Gefälle\"";
_cbulgefaelle.setText(BA.ObjectToCharSequence("Gefälle"));
 //BA.debugLineNum = 340;BA.debugLine="cbAlleen.Text = \"Alleen\"";
_cballeen.setText(BA.ObjectToCharSequence("Alleen"));
 //BA.debugLineNum = 341;BA.debugLine="cbUeberholen.Text = \"Überholen\"";
_cbueberholen.setText(BA.ObjectToCharSequence("Überholen"));
 //BA.debugLineNum = 342;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungbvfpan9(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderesituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbliegenblsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrenortsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaenger,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwildtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondereanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cborientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablenkung) throws Exception{
 //BA.debugLineNum = 344;BA.debugLine="Sub AusbildungBVFPan9(cbBesondereSituat As CheckBo";
 //BA.debugLineNum = 346;BA.debugLine="cbBesondereSituat.Text = \"Besondere Situationen\"";
_cbbesonderesituat.setText(BA.ObjectToCharSequence("Besondere Situationen"));
 //BA.debugLineNum = 347;BA.debugLine="cbLiegenblSichern.Text = \"Liegenbleiben + Absiche";
_cbliegenblsichern.setText(BA.ObjectToCharSequence("Liegenbleiben + Absichern"));
 //BA.debugLineNum = 348;BA.debugLine="cbEinfahrenOrtsch.Text = \"Einfahren in Ortschafte";
_cbeinfahrenortsch.setText(BA.ObjectToCharSequence("Einfahren in Ortschaften"));
 //BA.debugLineNum = 349;BA.debugLine="cbFussgaenger.Text = \"Fußgänger\"";
_cbfussgaenger.setText(BA.ObjectToCharSequence("Fußgänger"));
 //BA.debugLineNum = 350;BA.debugLine="cbWildTiere.Text = \"Wild / Tiere\"";
_cbwildtiere.setText(BA.ObjectToCharSequence("Wild / Tiere"));
 //BA.debugLineNum = 351;BA.debugLine="cbBesondereAnford.Text = \"Besondere Anforderungen";
_cbbesondereanford.setText(BA.ObjectToCharSequence("Besondere Anforderungen"));
 //BA.debugLineNum = 352;BA.debugLine="cbLeistungsgrenze.Text = \"Leistungsgrenze\"";
_cbleistungsgrenze.setText(BA.ObjectToCharSequence("Leistungsgrenze"));
 //BA.debugLineNum = 353;BA.debugLine="cbOrientierung.Text = \"Orientierung\"";
_cborientierung.setText(BA.ObjectToCharSequence("Orientierung"));
 //BA.debugLineNum = 354;BA.debugLine="cbAblenkung.Text = \"Ablenkung (z.B. Radio)\"";
_cbablenkung.setText(BA.ObjectToCharSequence("Ablenkung (z.B. Radio)"));
 //BA.debugLineNum = 355;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungsanzeigenloeschen(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile7,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz7,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile8,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz8,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile9,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz9,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile10,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz10) throws Exception{
 //BA.debugLineNum = 889;BA.debugLine="Sub AusbildungsAnzeigenLoeschen(lblAusbildungListe";
 //BA.debugLineNum = 897;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
_lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 898;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpare";
_lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 899;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
_lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 900;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpar";
_lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 902;BA.debugLine="lblAusbildungListeZeile2.Text = \"\"";
_lblausbildunglistezeile2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 903;BA.debugLine="lblAusbildungListeZeile2.Color = Colors.Transpare";
_lblausbildunglistezeile2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 904;BA.debugLine="lblAusbildungListeZusatz2.Text = \"\"";
_lblausbildunglistezusatz2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 905;BA.debugLine="lblAusbildungListeZusatz2.Color = Colors.Transpar";
_lblausbildunglistezusatz2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 907;BA.debugLine="lblAusbildungListeZeile3.Text = \"\"";
_lblausbildunglistezeile3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 908;BA.debugLine="lblAusbildungListeZeile3.Color = Colors.Transpare";
_lblausbildunglistezeile3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 909;BA.debugLine="lblAusbildungListeZusatz3.Text = \"\"";
_lblausbildunglistezusatz3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 910;BA.debugLine="lblAusbildungListeZusatz3.Color = Colors.Transpar";
_lblausbildunglistezusatz3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 912;BA.debugLine="lblAusbildungListeZeile4.Text = \"\"";
_lblausbildunglistezeile4.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 913;BA.debugLine="lblAusbildungListeZeile4.Color = Colors.Transpare";
_lblausbildunglistezeile4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 914;BA.debugLine="lblAusbildungListeZusatz4.Text = \"\"";
_lblausbildunglistezusatz4.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 915;BA.debugLine="lblAusbildungListeZusatz4.Color = Colors.Transpar";
_lblausbildunglistezusatz4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 917;BA.debugLine="lblAusbildungListeZeile5.Text = \"\"";
_lblausbildunglistezeile5.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 918;BA.debugLine="lblAusbildungListeZeile5.Color = Colors.Transpare";
_lblausbildunglistezeile5.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 919;BA.debugLine="lblAusbildungListeZusatz5.Text = \"\"";
_lblausbildunglistezusatz5.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 920;BA.debugLine="lblAusbildungListeZusatz5.Color = Colors.Transpar";
_lblausbildunglistezusatz5.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 922;BA.debugLine="lblAusbildungListeZeile6.Text = \"\"";
_lblausbildunglistezeile6.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 923;BA.debugLine="lblAusbildungListeZeile6.Color = Colors.Transpare";
_lblausbildunglistezeile6.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 924;BA.debugLine="lblAusbildungListeZusatz6.Text = \"\"";
_lblausbildunglistezusatz6.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 925;BA.debugLine="lblAusbildungListeZusatz6.Color = Colors.Transpar";
_lblausbildunglistezusatz6.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 927;BA.debugLine="lblAusbildungListeZeile7.Text = \"\"";
_lblausbildunglistezeile7.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 928;BA.debugLine="lblAusbildungListeZeile7.Color = Colors.Transpare";
_lblausbildunglistezeile7.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 929;BA.debugLine="lblAusbildungListeZusatz7.Text = \"\"";
_lblausbildunglistezusatz7.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 930;BA.debugLine="lblAusbildungListeZusatz7.Color = Colors.Transpar";
_lblausbildunglistezusatz7.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 932;BA.debugLine="lblAusbildungListeZeile8.Text = \"\"";
_lblausbildunglistezeile8.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 933;BA.debugLine="lblAusbildungListeZeile8.Color = Colors.Transpare";
_lblausbildunglistezeile8.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 934;BA.debugLine="lblAusbildungListeZusatz8.Text = \"\"";
_lblausbildunglistezusatz8.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 935;BA.debugLine="lblAusbildungListeZusatz8.Color = Colors.Transpar";
_lblausbildunglistezusatz8.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 937;BA.debugLine="lblAusbildungListeZeile9.Text = \"\"";
_lblausbildunglistezeile9.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 938;BA.debugLine="lblAusbildungListeZeile9.Color = Colors.Transpare";
_lblausbildunglistezeile9.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 939;BA.debugLine="lblAusbildungListeZusatz9.Text = \"\"";
_lblausbildunglistezusatz9.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 940;BA.debugLine="lblAusbildungListeZusatz9.Color = Colors.Transpar";
_lblausbildunglistezusatz9.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 942;BA.debugLine="lblAusbildungListeZeile10.Text = \"\"";
_lblausbildunglistezeile10.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 943;BA.debugLine="lblAusbildungListeZeile10.Color = Colors.Transpar";
_lblausbildunglistezeile10.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 944;BA.debugLine="lblAusbildungListeZusatz10.Text = \"\"";
_lblausbildunglistezusatz10.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 945;BA.debugLine="lblAusbildungListeZusatz10.Color = Colors.Transpa";
_lblausbildunglistezusatz10.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 946;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungskontrollebeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung0,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildung6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungplus,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungminus,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl0,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlx,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlminus,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlplus,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlausrufe,anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahldel) throws Exception{
 //BA.debugLineNum = 514;BA.debugLine="Sub AusbildungskontrolleBeschriftung(lblAusbildung";
 //BA.debugLineNum = 519;BA.debugLine="lblAusbildung0.Text = \"0\"";
_lblausbildung0.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 520;BA.debugLine="lblAusbildung1.Text = \"1\"";
_lblausbildung1.setText(BA.ObjectToCharSequence("1"));
 //BA.debugLineNum = 521;BA.debugLine="lblAusbildung2.Text = \"2\"";
_lblausbildung2.setText(BA.ObjectToCharSequence("2"));
 //BA.debugLineNum = 522;BA.debugLine="lblAusbildung3.Text = \"3\"";
_lblausbildung3.setText(BA.ObjectToCharSequence("3"));
 //BA.debugLineNum = 523;BA.debugLine="lblAusbildung4.Text = \"4\"";
_lblausbildung4.setText(BA.ObjectToCharSequence("4"));
 //BA.debugLineNum = 524;BA.debugLine="lblAusbildung5.Text = \"5\"";
_lblausbildung5.setText(BA.ObjectToCharSequence("5"));
 //BA.debugLineNum = 525;BA.debugLine="lblAusbildung6.Text = \"6\"";
_lblausbildung6.setText(BA.ObjectToCharSequence("6"));
 //BA.debugLineNum = 526;BA.debugLine="lblAusbildungPlus.Text = \"+\"";
_lblausbildungplus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 527;BA.debugLine="lblAusbildungMinus.Text = \"-\"";
_lblausbildungminus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 529;BA.debugLine="lblAusbildungZahl0.Text = \"0\"";
_lblausbildungzahl0.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 530;BA.debugLine="lblAusbildungZahl1.Text = \"1\"";
_lblausbildungzahl1.setText(BA.ObjectToCharSequence("1"));
 //BA.debugLineNum = 531;BA.debugLine="lblAusbildungZahl2.Text = \"2\"";
_lblausbildungzahl2.setText(BA.ObjectToCharSequence("2"));
 //BA.debugLineNum = 532;BA.debugLine="lblAusbildungZahl3.Text = \"3\"";
_lblausbildungzahl3.setText(BA.ObjectToCharSequence("3"));
 //BA.debugLineNum = 533;BA.debugLine="lblAusbildungZahl4.Text = \"4\"";
_lblausbildungzahl4.setText(BA.ObjectToCharSequence("4"));
 //BA.debugLineNum = 534;BA.debugLine="lblAusbildungZahl5.Text = \"5\"";
_lblausbildungzahl5.setText(BA.ObjectToCharSequence("5"));
 //BA.debugLineNum = 535;BA.debugLine="lblAusbildungZahl6.Text = \"6\"";
_lblausbildungzahl6.setText(BA.ObjectToCharSequence("6"));
 //BA.debugLineNum = 536;BA.debugLine="lblAusbildungZahlX.Text = \"X\"";
_lblausbildungzahlx.setText(BA.ObjectToCharSequence("X"));
 //BA.debugLineNum = 537;BA.debugLine="lblAusbildungZahlAusrufe.Text = \"!\"";
_lblausbildungzahlausrufe.setText(BA.ObjectToCharSequence("!"));
 //BA.debugLineNum = 538;BA.debugLine="lblAusbildungZahlMinus.Text = \"-\"";
_lblausbildungzahlminus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 539;BA.debugLine="lblAusbildungZahlPlus.Text = \"+\"";
_lblausbildungzahlplus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 540;BA.debugLine="lblAusbildungZahlDel.Text = \" \"";
_lblausbildungzahldel.setText(BA.ObjectToCharSequence(" "));
 //BA.debugLineNum = 541;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungzustandaendern(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz1,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz2,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz3,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz4,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz5,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz6,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile7,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz7,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile8,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz8,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile9,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz9,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile10,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz10,char _scharacter) throws Exception{
int _iposition = 0;
int _i = 0;
 //BA.debugLineNum = 956;BA.debugLine="Sub AusbildungZustandAendern(lblAusbildungListeZei";
 //BA.debugLineNum = 963;BA.debugLine="If lblAusbildungListeZusatz1.Text <> \"\" Then";
if ((_lblausbildunglistezusatz1.getText()).equals("") == false) { 
 //BA.debugLineNum = 964;BA.debugLine="lblAusbildungListeZusatz1.Text = sCharacter";
_lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 966;BA.debugLine="If lblAusbildungListeZusatz2.Text <> \"\" Then";
if ((_lblausbildunglistezusatz2.getText()).equals("") == false) { 
 //BA.debugLineNum = 967;BA.debugLine="lblAusbildungListeZusatz2.Text = sCharacter";
_lblausbildunglistezusatz2.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 969;BA.debugLine="If lblAusbildungListeZusatz3.Text <> \"\" Then";
if ((_lblausbildunglistezusatz3.getText()).equals("") == false) { 
 //BA.debugLineNum = 970;BA.debugLine="lblAusbildungListeZusatz3.Text = sCharacter";
_lblausbildunglistezusatz3.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 972;BA.debugLine="If lblAusbildungListeZusatz4.Text <> \"\" Then";
if ((_lblausbildunglistezusatz4.getText()).equals("") == false) { 
 //BA.debugLineNum = 973;BA.debugLine="lblAusbildungListeZusatz4.Text = sCharacter";
_lblausbildunglistezusatz4.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 975;BA.debugLine="If lblAusbildungListeZusatz5.Text <> \"\" Then";
if ((_lblausbildunglistezusatz5.getText()).equals("") == false) { 
 //BA.debugLineNum = 976;BA.debugLine="lblAusbildungListeZusatz5.Text = sCharacter";
_lblausbildunglistezusatz5.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 978;BA.debugLine="If lblAusbildungListeZusatz6.Text <> \"\" Then";
if ((_lblausbildunglistezusatz6.getText()).equals("") == false) { 
 //BA.debugLineNum = 979;BA.debugLine="lblAusbildungListeZusatz6.Text = sCharacter";
_lblausbildunglistezusatz6.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 981;BA.debugLine="If lblAusbildungListeZusatz7.Text <> \"\" Then";
if ((_lblausbildunglistezusatz7.getText()).equals("") == false) { 
 //BA.debugLineNum = 982;BA.debugLine="lblAusbildungListeZusatz7.Text = sCharacter";
_lblausbildunglistezusatz7.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 984;BA.debugLine="If lblAusbildungListeZusatz8.Text <> \"\" Then";
if ((_lblausbildunglistezusatz8.getText()).equals("") == false) { 
 //BA.debugLineNum = 985;BA.debugLine="lblAusbildungListeZusatz8.Text = sCharacter";
_lblausbildunglistezusatz8.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 987;BA.debugLine="If lblAusbildungListeZusatz9.Text <> \"\" Then";
if ((_lblausbildunglistezusatz9.getText()).equals("") == false) { 
 //BA.debugLineNum = 988;BA.debugLine="lblAusbildungListeZusatz9.Text = sCharacter";
_lblausbildunglistezusatz9.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 990;BA.debugLine="If lblAusbildungListeZusatz10.Text <> \"\" Then";
if ((_lblausbildunglistezusatz10.getText()).equals("") == false) { 
 //BA.debugLineNum = 991;BA.debugLine="lblAusbildungListeZusatz10.Text = sCharacter";
_lblausbildunglistezusatz10.setText(BA.ObjectToCharSequence(_scharacter));
 };
 //BA.debugLineNum = 995;BA.debugLine="Dim iPosition As Int";
_iposition = 0;
 //BA.debugLineNum = 996;BA.debugLine="For i = 0 To Main.bIsSelected.Length - 1";
{
final int step32 = 1;
final int limit32 = (int) (mostCurrent._main._bisselected /*boolean[]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit32 ;_i = _i + step32 ) {
 //BA.debugLineNum = 997;BA.debugLine="If Main.bIsSelected(i) Then";
if (mostCurrent._main._bisselected /*boolean[]*/ [_i]) { 
 //BA.debugLineNum = 998;BA.debugLine="iPosition = i";
_iposition = _i;
 };
 }
};
 //BA.debugLineNum = 1002;BA.debugLine="If DBUtils.UpdateAusbildungLernPunkte(sCharacter";
if (mostCurrent._dbutils._updateausbildunglernpunkte /*boolean*/ (_ba,_scharacter,_iposition)) { 
 //BA.debugLineNum = 1003;BA.debugLine="AusbildungsAnzeigenLoeschen(lblAusbildungListeZ";
_ausbildungsanzeigenloeschen(_ba,_lblausbildunglistezeile1,_lblausbildunglistezusatz1,_lblausbildunglistezeile2,_lblausbildunglistezusatz2,_lblausbildunglistezeile3,_lblausbildunglistezusatz3,_lblausbildunglistezeile4,_lblausbildunglistezusatz4,_lblausbildunglistezeile5,_lblausbildunglistezusatz5,_lblausbildunglistezeile6,_lblausbildunglistezusatz6,_lblausbildunglistezeile7,_lblausbildunglistezusatz7,_lblausbildunglistezeile8,_lblausbildunglistezusatz8,_lblausbildunglistezeile9,_lblausbildunglistezusatz9,_lblausbildunglistezeile10,_lblausbildunglistezusatz10);
 }else {
 //BA.debugLineNum = 1010;BA.debugLine="ToastMessageShow(\"Fehler beim speichern\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim speichern"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 1012;BA.debugLine="End Sub";
return "";
}
public static String  _ausbildungzustandneu(anywheresoftware.b4a.BA _ba,String _seintraege,int _ianfang,int _iende,int _iposition,char _scharacter) throws Exception{
String _seintraegesplit = "";
char[] _cein = null;
int _i = 0;
 //BA.debugLineNum = 1015;BA.debugLine="Sub AusbildungZustandNeu(sEintraege As String, iAn";
 //BA.debugLineNum = 1016;BA.debugLine="Dim sEintraegeSplit As String";
_seintraegesplit = "";
 //BA.debugLineNum = 1018;BA.debugLine="sEintraegeSplit = sEintraege.SubString2(iAnfang,";
_seintraegesplit = _seintraege.substring(_ianfang,_iende);
 //BA.debugLineNum = 1020;BA.debugLine="Dim cEin(10) As Char";
_cein = new char[(int) (10)];
;
 //BA.debugLineNum = 1022;BA.debugLine="For i = 0 To sEintraegeSplit.Length - 1";
{
final int step4 = 1;
final int limit4 = (int) (_seintraegesplit.length()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 1023;BA.debugLine="If i = iPosition Then";
if (_i==_iposition) { 
 //BA.debugLineNum = 1024;BA.debugLine="cEin(i) = sCharacter";
_cein[_i] = _scharacter;
 }else {
 //BA.debugLineNum = 1026;BA.debugLine="cEin(i) = sEintraegeSplit.CharAt(i)";
_cein[_i] = _seintraegesplit.charAt(_i);
 };
 }
};
 //BA.debugLineNum = 1030;BA.debugLine="sEintraegeSplit = \"\"";
_seintraegesplit = "";
 //BA.debugLineNum = 1031;BA.debugLine="For i = 0 To cEin.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cein.length-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 1032;BA.debugLine="sEintraegeSplit = sEintraegeSplit & cEin(i)";
_seintraegesplit = _seintraegesplit+BA.ObjectToString(_cein[_i]);
 }
};
 //BA.debugLineNum = 1034;BA.debugLine="Return sEintraegeSplit";
if (true) return _seintraegesplit;
 //BA.debugLineNum = 1036;BA.debugLine="End Sub";
return "";
}
public static String  _ausgewaehltenschuelersetzen(anywheresoftware.b4a.BA _ba) throws Exception{
int _i = 0;
 //BA.debugLineNum = 1100;BA.debugLine="Sub AusgewaehltenSchuelerSetzen";
 //BA.debugLineNum = 1101;BA.debugLine="For i = 0 To Main.aSchueler.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._main._aschueler /*String[][]*/ .length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 1102;BA.debugLine="If Main.aSchueler(i, 2) = 1 Then";
if ((mostCurrent._main._aschueler /*String[][]*/ [_i][(int) (2)]).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1103;BA.debugLine="Main.sAusgewaehlterSchueler = Main.aSchueler(i,";
mostCurrent._main._sausgewaehlterschueler /*String*/  = mostCurrent._main._aschueler /*String[][]*/ [_i][(int) (0)];
 //BA.debugLineNum = 1104;BA.debugLine="Main.iAusgewaehlterSchuelrID = Main.aSchueler(i";
mostCurrent._main._iausgewaehlterschuelrid /*int*/  = (int)(Double.parseDouble(mostCurrent._main._aschueler /*String[][]*/ [_i][(int) (1)]));
 };
 }
};
 //BA.debugLineNum = 1107;BA.debugLine="End Sub";
return "";
}
public static String  _begleitfahrzeugholen(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
 //BA.debugLineNum = 755;BA.debugLine="Sub BegleitfahrzeugHolen(ITerminID As Int) As Stri";
 //BA.debugLineNum = 756;BA.debugLine="Return DBUtils.BegleitfahrzeugHolen(ITerminID)";
if (true) return mostCurrent._dbutils._begleitfahrzeugholen /*String*/ (_ba,_iterminid);
 //BA.debugLineNum = 757;BA.debugLine="End Sub";
return "";
}
public static String  _bvffelderleeren(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderheinsteigen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkradhaltung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpedale,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltwaehlhebel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzuendschloss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoranlassen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahranhalte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltuebg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch1_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch2_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch3_4,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter2_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbumkehren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenlaengs,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaertsfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenquer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrollenschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdegressiv,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzielbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrsituation,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtastgeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienkontroll,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboertlichbesonder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrbahnbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinordnen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmarkierungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrstreifenwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabbiegen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmehrspurig,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsonderstreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrassenbahn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorfahrt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtsvorlinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeil,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpolizeibeamte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeilschild,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsituationverkehrstn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerueberweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboeffentlverkehrsm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaelterebehinderte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrradfahrer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkinder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschulbus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradfahrermofa,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrsberuhigt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwierigeverkehrsf,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbengpass,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreisverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergangwarte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkritischeverkehrss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhauptverkehrszt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpartnerverhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwungnutzen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerschutzb,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbangepasstegeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeobachtspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrszeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreuzungeinmuend,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkurven,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cballeen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderesituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbliegenblsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrenortsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaenger,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwildtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondereanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cborientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrtplanung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrtab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabfahrbahnwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwindigkeit,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbababstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschilder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrastparktank,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverhunfall,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdichterverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbableistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkonfliktsitua,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstell,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverlassenbab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbparken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlechtewitterung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergaenge,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbelverkehrtn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesonderanfor,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblendung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbduorientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabschlussbesp,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbselbstfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbinnerorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbausserorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverantwfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtestfpruef,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfakt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbandere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwiederholung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsbew,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinausschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktionpruefen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstandlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbnebelschluss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblinker,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabblendlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarnblicke,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhupe,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbsfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlussleuchte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremslicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolllbenenn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckstrahler,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorhandensein,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeschaedigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkschlentriegeln,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbprueflenkspiel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbetriebsbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfeststellbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanlegengurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrichtigsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellrueckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinlenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienenagg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheckheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbehsonderaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbklimaanlage,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbenergienutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkeineunnverbr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtztabsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotorraum,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoroel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkuehlmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwaschm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtanken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsicherungsmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarndreieck,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbordwerkzeug,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzusaetzlichaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverbandskasten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaussenkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwischer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkennzeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladungssicherung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkenntlichmachung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahreschlwitt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittlueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittscheiben,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbregen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwasserlachen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwindsturm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmatchschnee,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeis,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittbeleuchtung,anywheresoftware.b4a.objects.EditTextWrapper _etnotizen) throws Exception{
 //BA.debugLineNum = 1279;BA.debugLine="Sub BVFFelderLeeren(cbBesonderhEinsteigen As Check";
 //BA.debugLineNum = 1299;BA.debugLine="cbBesonderhEinsteigen.Checked = False";
_cbbesonderheinsteigen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1300;BA.debugLine="cbEinstellen.Checked = False";
_cbeinstellen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1301;BA.debugLine="cbLenkrad.Checked = False";
_cblenkrad.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1302;BA.debugLine="cbSpiegel.Checked = False";
_cbspiegel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1303;BA.debugLine="cbKopfstuetze.Checked = False";
_cbkopfstuetze.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1304;BA.debugLine="cbSitz.Checked = False";
_cbsitz.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1305;BA.debugLine="cbLenkradhaltung.Checked = False";
_cblenkradhaltung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1306;BA.debugLine="cbPedale.Checked = False";
_cbpedale.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1307;BA.debugLine="cbGurt.Checked = False";
_cbgurt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1308;BA.debugLine="cbSchaltWaehlhebel.Checked = False";
_cbschaltwaehlhebel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1309;BA.debugLine="cbZuendschloss.Checked = False";
_cbzuendschloss.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1310;BA.debugLine="cbMotorAnlassen.Checked = False";
_cbmotoranlassen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1311;BA.debugLine="cbAnfahrAnhalte.Checked = False";
_cbanfahranhalte.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1312;BA.debugLine="cbSchaltuebg.Checked = False";
_cbschaltuebg.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1313;BA.debugLine="cbHoch1_2.Checked = False";
_cbhoch1_2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1314;BA.debugLine="cbHoch2_3.Checked = False";
_cbhoch2_3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1315;BA.debugLine="cbHoch3_4.Checked = False";
_cbhoch3_4.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1316;BA.debugLine="cbRunter4_3.Checked = False";
_cbrunter4_3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1317;BA.debugLine="cbRunter3_2.Checked = False";
_cbrunter3_2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1318;BA.debugLine="cbRunter2_1.Checked = False";
_cbrunter2_1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1319;BA.debugLine="cbRunter4_2.Checked = False";
_cbrunter4_2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1320;BA.debugLine="cbRunter4_1.Checked = False";
_cbrunter4_1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1321;BA.debugLine="cbRunter3_1.Checked = False";
_cbrunter3_1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1322;BA.debugLine="cbLenkuebung.Checked = False";
_cblenkuebung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1323;BA.debugLine="cbUmkehren.Checked = False";
_cbumkehren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1324;BA.debugLine="cbEinparkenLaengs.Checked = False";
_cbeinparkenlaengs.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1325;BA.debugLine="cbLVorwaertsRechts.Checked = False";
_cblvorwaertsrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1326;BA.debugLine="cbLRueckwaertsLinks.Checked = False";
_cblrueckwaertslinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1327;BA.debugLine="cbLRueckwaertsRechts.Checked = False";
_cblrueckwaertsrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1328;BA.debugLine="cbLVorwaertsLinks.Checked = False";
_cblvorwaertslinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1329;BA.debugLine="cbRueckwaertsfahren.Checked = False";
_cbrueckwaertsfahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1330;BA.debugLine="cbEinparkenQuer.Checked = False";
_cbeinparkenquer.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1331;BA.debugLine="cbQVorwaertsRechts.Checked = False";
_cbqvorwaertsrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1332;BA.debugLine="cbQRueckwaertsLinks.Checked = False";
_cbqrueckwaertslinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1333;BA.debugLine="cbQRueckwaertsRechts.Checked = False";
_cbqrueckwaertsrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1334;BA.debugLine="cbQVorwaertsLinks.Checked = False";
_cbqvorwaertslinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1335;BA.debugLine="cbGefahrbremsung.Checked = False";
_cbgefahrbremsung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1336;BA.debugLine="cbRollenSchalten.Checked = False";
_cbrollenschalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1337;BA.debugLine="cbBremsSchalten.Checked = False";
_cbbremsschalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1338;BA.debugLine="cbBremsuebung.Checked = False";
_cbbremsuebung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1339;BA.debugLine="cbDegressiv.Checked = False";
_cbdegressiv.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1340;BA.debugLine="cbZielbremsung.Checked = False";
_cbzielbremsung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1341;BA.debugLine="cbGefahrsituation.Checked = False";
_cbgefahrsituation.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1342;BA.debugLine="cbGefaelle.Checked = False";
_cbgefaelle.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1343;BA.debugLine="cbAnhalten.Checked = False";
_cbanhalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1344;BA.debugLine="cbAnfahren.Checked = False";
_cbanfahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1345;BA.debugLine="cbRueckwaerts.Checked = False";
_cbrueckwaerts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1346;BA.debugLine="cbSichern.Checked = False";
_cbsichern.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1347;BA.debugLine="cbSchalten.Checked = False";
_cbschalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1348;BA.debugLine="cbSteigung.Checked = False";
_cbsteigung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1349;BA.debugLine="cbStAnhalten.Checked = False";
_cbstanhalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1350;BA.debugLine="cbStAnfahren.Checked = False";
_cbstanfahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1351;BA.debugLine="cbStRueckwaerts.Checked = False";
_cbstrueckwaerts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1352;BA.debugLine="cbStSichern.Checked = False";
_cbstsichern.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1353;BA.debugLine="cbStSchalten.Checked = False";
_cbstschalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1354;BA.debugLine="cbTastgeschw.Checked = False";
_cbtastgeschw.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1355;BA.debugLine="cbBedienKontroll.Checked = False";
_cbbedienkontroll.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1356;BA.debugLine="cbOertlichBesonder.Checked = False";
_cboertlichbesonder.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1357;BA.debugLine="cbFahrbahnbenutzung.Checked = False";
_cbfahrbahnbenutzung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1358;BA.debugLine="cbEinordnen.Checked = False";
_cbeinordnen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1359;BA.debugLine="cbMarkierungen.Checked = False";
_cbmarkierungen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1360;BA.debugLine="cbFahrstreifenwechsel.Checked = False";
_cbfahrstreifenwechsel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1361;BA.debugLine="cbLinks.Checked = False";
_cblinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1362;BA.debugLine="cbRechts.Checked = False";
_cbrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1363;BA.debugLine="cbVorbeifUeberholen.Checked = False";
_cbvorbeifueberholen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1364;BA.debugLine="cbAbbiegen.Checked = False";
_cbabbiegen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1365;BA.debugLine="cbABRechts.Checked = False";
_cbabrechts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1366;BA.debugLine="cbABLinks.Checked = False";
_cbablinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1367;BA.debugLine="cbMehrspurig.Checked = False";
_cbmehrspurig.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1368;BA.debugLine="cbRadweg.Checked = False";
_cbradweg.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1369;BA.debugLine="cbSonderstreifen.Checked = False";
_cbsonderstreifen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1370;BA.debugLine="cbStrassenbahn.Checked = False";
_cbstrassenbahn.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1371;BA.debugLine="cbEinbahnstrasse.Checked = False";
_cbeinbahnstrasse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1372;BA.debugLine="cbVorfahrt.Checked = False";
_cbvorfahrt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1373;BA.debugLine="cbRechtsVorLinks.Checked = False";
_cbrechtsvorlinks.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1374;BA.debugLine="cbGruenpfeil.Checked = False";
_cbgruenpfeil.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1375;BA.debugLine="cbPolizeibeamte.Checked = False";
_cbpolizeibeamte.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1376;BA.debugLine="cbGruenpfeilSchild.Checked = False";
_cbgruenpfeilschild.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1377;BA.debugLine="cbGeschwAbstand.Checked = False";
_cbgeschwabstand.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1378;BA.debugLine="cbSituationVerkehrstn.Checked = False";
_cbsituationverkehrstn.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1379;BA.debugLine="cbFussgaengerueberweg.Checked = False";
_cbfussgaengerueberweg.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1380;BA.debugLine="cbOeffentlVerkehrsm.Checked = False";
_cboeffentlverkehrsm.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1381;BA.debugLine="cbAeltereBehinderte.Checked = False";
_cbaelterebehinderte.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1382;BA.debugLine="cbEinbahnstrRadfahrer.Checked = False";
_cbeinbahnstrradfahrer.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1383;BA.debugLine="cbKinder.Checked = False";
_cbkinder.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1384;BA.debugLine="cbSchulbus.Checked = False";
_cbschulbus.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1385;BA.debugLine="cbRadfahrerMofa.Checked = False";
_cbradfahrermofa.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1386;BA.debugLine="cbVerkehrsberuhigt.Checked = False";
_cbverkehrsberuhigt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1387;BA.debugLine="cbSchwierigeVerkehrsf.Checked = False";
_cbschwierigeverkehrsf.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1388;BA.debugLine="cbEngpass.Checked = False";
_cbengpass.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1389;BA.debugLine="cbKreisverkehr.Checked = False";
_cbkreisverkehr.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1390;BA.debugLine="cbBahnuebergangWarte.Checked = False";
_cbbahnuebergangwarte.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1391;BA.debugLine="cbKritischeVerkehrss.Checked = False";
_cbkritischeverkehrss.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1392;BA.debugLine="cbHauptverkehrszt.Checked = False";
_cbhauptverkehrszt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1393;BA.debugLine="cbPartnerVerhalten.Checked = False";
_cbpartnerverhalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1394;BA.debugLine="cbSchwungNutzen.Checked = False";
_cbschwungnutzen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1395;BA.debugLine="cbFussgaengerSchutzb.Checked = False";
_cbfussgaengerschutzb.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1396;BA.debugLine="cbAngepassteGeschw.Checked = False";
_cbangepasstegeschw.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1397;BA.debugLine="cbAbstand.Checked = False";
_cbabstand.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1398;BA.debugLine="cbULVorne.Checked = False";
_cbulvorne.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1399;BA.debugLine="cbULHinten.Checked = False";
_cbulhinten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1400;BA.debugLine="cbULSeitlich.Checked = False";
_cbulseitlich.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1401;BA.debugLine="cbBeobachtSpiegel.Checked = False";
_cbbeobachtspiegel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1402;BA.debugLine="cbVerkehrszeichen.Checked = False";
_cbverkehrszeichen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1403;BA.debugLine="cbKreuzungEinmuend.Checked = False";
_cbkreuzungeinmuend.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1404;BA.debugLine="cbKurven.Checked = False";
_cbkurven.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1405;BA.debugLine="cbSteigungen.Checked = False";
_cbsteigungen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1406;BA.debugLine="cbULGefaelle.Checked = False";
_cbulgefaelle.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1407;BA.debugLine="cbAlleen.Checked = False";
_cballeen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1408;BA.debugLine="cbUeberholen.Checked = False";
_cbueberholen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1409;BA.debugLine="cbBesondereSituat.Checked = False";
_cbbesonderesituat.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1410;BA.debugLine="cbLiegenblSichern.Checked = False";
_cbliegenblsichern.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1411;BA.debugLine="cbEinfahrenOrtsch.Checked = False";
_cbeinfahrenortsch.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1412;BA.debugLine="cbFussgaenger.Checked = False";
_cbfussgaenger.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1413;BA.debugLine="cbWildTiere.Checked = False";
_cbwildtiere.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1414;BA.debugLine="cbBesondereAnford.Checked = False";
_cbbesondereanford.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1415;BA.debugLine="cbLeistungsgrenze.Checked = False";
_cbleistungsgrenze.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1416;BA.debugLine="cbOrientierung.Checked = False";
_cborientierung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1417;BA.debugLine="cbAblenkung.Checked = False";
_cbablenkung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1418;BA.debugLine="cbFahrtplanung.Checked = False";
_cbfahrtplanung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1419;BA.debugLine="cbEinfahrtAB.Checked = False";
_cbeinfahrtab.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1420;BA.debugLine="cbABFahrbahnwechsel.Checked = False";
_cbabfahrbahnwechsel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1421;BA.debugLine="cbGeschwindigkeit.Checked = False";
_cbgeschwindigkeit.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1422;BA.debugLine="cbABAbstand.Checked = False";
_cbababstand.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1423;BA.debugLine="cbABVorne.Checked = False";
_cbabvorne.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1424;BA.debugLine="cbABHinten.Checked = False";
_cbabhinten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1425;BA.debugLine="cbABSeitlich.Checked = False";
_cbabseitlich.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1426;BA.debugLine="cbABUeberholen.Checked = False";
_cbabueberholen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1427;BA.debugLine="cbSchilder.Checked = False";
_cbschilder.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1428;BA.debugLine="cbVorbeifahren.Checked = False";
_cbvorbeifahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1429;BA.debugLine="cbRastParkTank.Checked = False";
_cbrastparktank.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1430;BA.debugLine="cbVerhUnfall.Checked = False";
_cbverhunfall.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1431;BA.debugLine="cbDichterVerkehr.Checked = False";
_cbdichterverkehr.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1432;BA.debugLine="cbBesonderSituat.Checked = False";
_cbbesondersituat.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1433;BA.debugLine="cbBesonderAnford.Checked = False";
_cbbesonderanford.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1434;BA.debugLine="cbABLeistungsgrenze.Checked = False";
_cbableistungsgrenze.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1435;BA.debugLine="cbKonfliktSitua.Checked = False";
_cbkonfliktsitua.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1436;BA.debugLine="cbABAblenkung.Checked = False";
_cbabablenkung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1437;BA.debugLine="cbVerlassenBAB.Checked = False";
_cbverlassenbab.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1438;BA.debugLine="cbBeleuchtung.Checked = False";
_cbbeleuchtung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1439;BA.debugLine="cbKontrolle.Checked = False";
_cbkontrolle.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1440;BA.debugLine="cbEinstell.Checked = False";
_cbeinstell.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1441;BA.debugLine="cbBenutzung.Checked = False";
_cbbenutzung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1442;BA.debugLine="cbFernlicht.Checked = False";
_cbfernlicht.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1443;BA.debugLine="cbBeleuchtStrasse.Checked = False";
_cbbeleuchtstrasse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1444;BA.debugLine="cbUnbeleuchtStrasse.Checked = False";
_cbunbeleuchtstrasse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1445;BA.debugLine="cbParken.Checked = False";
_cbparken.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1446;BA.debugLine="cbDUBesonderSituat.Checked = False";
_cbdubesondersituat.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1447;BA.debugLine="cbSchlechteWitterung.Checked = False";
_cbschlechtewitterung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1448;BA.debugLine="cbTiere.Checked = False";
_cbtiere.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1449;BA.debugLine="cbBahnuebergaenge.Checked = False";
_cbbahnuebergaenge.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1450;BA.debugLine="cbUnbelVerkehrTN.Checked = False";
_cbunbelverkehrtn.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1451;BA.debugLine="cbDUBesonderAnfor.Checked = False";
_cbdubesonderanfor.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1452;BA.debugLine="cbBlendung.Checked = False";
_cbblendung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1453;BA.debugLine="cbDUOrientierung.Checked = False";
_cbduorientierung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1454;BA.debugLine="cbAbschlussbesp.Checked = False";
_cbabschlussbesp.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1455;BA.debugLine="cbSelbstFahren.Checked = False";
_cbselbstfahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1456;BA.debugLine="cbInnerorts.Checked = False";
_cbinnerorts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1457;BA.debugLine="cbAusserorts.Checked = False";
_cbausserorts.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1458;BA.debugLine="cbVerantwFahren.Checked = False";
_cbverantwfahren.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1459;BA.debugLine="cbTestfPruef.Checked = False";
_cbtestfpruef.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1460;BA.debugLine="cbFAKT.Checked = False";
_cbfakt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1461;BA.debugLine="cbAndere.Checked = False";
_cbandere.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1462;BA.debugLine="cbWiederholung.Checked = False";
_cbwiederholung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1463;BA.debugLine="cbLeistungsbew.Checked = False";
_cbleistungsbew.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1464;BA.debugLine="cbReifen.Checked = False";
_cbreifen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1465;BA.debugLine="cbEinAusschalten.Checked = False";
_cbeinausschalten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1466;BA.debugLine="cbFunktionPruefen.Checked = False";
_cbfunktionpruefen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1467;BA.debugLine="cbStandlicht.Checked = False";
_cbstandlicht.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1468;BA.debugLine="cbNebelschluss.Checked = False";
_cbnebelschluss.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1469;BA.debugLine="cbBlinker.Checked = False";
_cbblinker.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1470;BA.debugLine="cbAbblendlicht.Checked = False";
_cbabblendlicht.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1471;BA.debugLine="cbWarnblicke.Checked = False";
_cbwarnblicke.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1472;BA.debugLine="cbHupe.Checked = False";
_cbhupe.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1473;BA.debugLine="cbBSFernlicht.Checked = False";
_cbbsfernlicht.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1474;BA.debugLine="cbSchlussLeuchte.Checked = False";
_cbschlussleuchte.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1475;BA.debugLine="cbBremslicht.Checked = False";
_cbbremslicht.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1476;BA.debugLine="cbKontrollLBenenn.Checked = False";
_cbkontrolllbenenn.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1477;BA.debugLine="cbRueckstrahler.Checked = False";
_cbrueckstrahler.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1478;BA.debugLine="cbVorhandensein.Checked = False";
_cbvorhandensein.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1479;BA.debugLine="cbBeschaedigung.Checked = False";
_cbbeschaedigung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1480;BA.debugLine="cbLenkung.Checked = False";
_cblenkung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1481;BA.debugLine="cbLenkschlEntriegeln.Checked = False";
_cblenkschlentriegeln.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1482;BA.debugLine="cbPruefLenkSpiel.Checked = False";
_cbprueflenkspiel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1483;BA.debugLine="cbFunktBremse.Checked = False";
_cbfunktbremse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1484;BA.debugLine="cbBetriebsBremse.Checked = False";
_cbbetriebsbremse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1485;BA.debugLine="cbFeststellBremse.Checked = False";
_cbfeststellbremse.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1486;BA.debugLine="cbRichtigSitz.Checked = False";
_cbrichtigsitz.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1487;BA.debugLine="cbEinstellRueckspiegel.Checked = False";
_cbeinstellrueckspiegel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1488;BA.debugLine="cbEinKopfstuetze.Checked = False";
_cbeinkopfstuetze.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1489;BA.debugLine="cbEinLenkrad.Checked = False";
_cbeinlenkrad.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1490;BA.debugLine="cbAnlegenGurt.Checked = False";
_cbanlegengurt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1491;BA.debugLine="cbBedienenAgg.Checked = False";
_cbbedienenagg.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1492;BA.debugLine="cbHeizung.Checked = False";
_cbheizung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1493;BA.debugLine="cbHeckHeizung.Checked = False";
_cbheckheizung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1494;BA.debugLine="cbBehSonderaus.Checked = False";
_cbbehsonderaus.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1495;BA.debugLine="cbLueftung.Checked = False";
_cblueftung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1496;BA.debugLine="cbKlimaanlage.Checked = False";
_cbklimaanlage.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1497;BA.debugLine="cbEnergieNutzung.Checked = False";
_cbenergienutzung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1498;BA.debugLine="cbKeineUnnVerbr.Checked = False";
_cbkeineunnverbr.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1499;BA.debugLine="cbRechtztAbsch.Checked = False";
_cbrechtztabsch.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1500;BA.debugLine="cbMotorraum.Checked = False";
_cbmotorraum.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1501;BA.debugLine="cbMotoroel.Checked = False";
_cbmotoroel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1502;BA.debugLine="cbKuehlmittel.Checked = False";
_cbkuehlmittel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1503;BA.debugLine="cbScheibenwaschm.Checked = False";
_cbscheibenwaschm.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1504;BA.debugLine="cbTanken.Checked = False";
_cbtanken.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1505;BA.debugLine="cbSicherungsmittel.Checked = False";
_cbsicherungsmittel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1506;BA.debugLine="cbWarndreieck.Checked = False";
_cbwarndreieck.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1507;BA.debugLine="cbBordwerkzeug.Checked = False";
_cbbordwerkzeug.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1508;BA.debugLine="cbZusaetzlichAus.Checked = False";
_cbzusaetzlichaus.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1509;BA.debugLine="cbVerbandskasten.Checked = False";
_cbverbandskasten.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1510;BA.debugLine="cbAussenkontrolle.Checked = False";
_cbaussenkontrolle.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1511;BA.debugLine="cbScheibenWischer.Checked = False";
_cbscheibenwischer.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1512;BA.debugLine="cbKennzeichen.Checked = False";
_cbkennzeichen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1513;BA.debugLine="cbCheckSpiegel.Checked = False";
_cbcheckspiegel.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1514;BA.debugLine="cbCheckBeleuchtung.Checked = False";
_cbcheckbeleuchtung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1515;BA.debugLine="cbBremsen.Checked = False";
_cbbremsen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1516;BA.debugLine="cbLadung.Checked = False";
_cbladung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1517;BA.debugLine="cbLadungssicherung.Checked = False";
_cbladungssicherung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1518;BA.debugLine="cbKenntlichmachung.Checked = False";
_cbkenntlichmachung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1519;BA.debugLine="cbFahreSchlWitt.Checked = False";
_cbfahreschlwitt.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1520;BA.debugLine="cbWittLueftung.Checked = False";
_cbwittlueftung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1521;BA.debugLine="cbWittScheiben.Checked = False";
_cbwittscheiben.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1522;BA.debugLine="cbRegen.Checked = False";
_cbregen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1523;BA.debugLine="cbWasserlachen.Checked = False";
_cbwasserlachen.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1524;BA.debugLine="cbWindSturm.Checked = False";
_cbwindsturm.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1525;BA.debugLine="cbMatchSchnee.Checked = False";
_cbmatchschnee.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1526;BA.debugLine="cbEis.Checked = False";
_cbeis.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1527;BA.debugLine="cbWittBeleuchtung.Checked = False";
_cbwittbeleuchtung.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1528;BA.debugLine="etNotizen.Text = \" \"";
_etnotizen.setText(BA.ObjectToCharSequence(" "));
 //BA.debugLineNum = 1529;BA.debugLine="End Sub";
return "";
}
public static boolean  _checkbegleitfahrzeug(anywheresoftware.b4a.BA _ba,String _sauswahlklasse) throws Exception{
 //BA.debugLineNum = 1110;BA.debugLine="Sub CheckBegleitfahrzeug(sAuswahlKlasse As String)";
 //BA.debugLineNum = 1111;BA.debugLine="Return DBUtils.CheckBegleitfahrzeug(sAuswahlKlass";
if (true) return mostCurrent._dbutils._checkbegleitfahrzeug /*boolean*/ (_ba,_sauswahlklasse);
 //BA.debugLineNum = 1112;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkdatenindb(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _bresult = false;
 //BA.debugLineNum = 1049;BA.debugLine="Sub CheckDatenInDB() As Boolean";
 //BA.debugLineNum = 1050;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1052;BA.debugLine="bResult = DBUtils.CheckDatenInDB()";
_bresult = mostCurrent._dbutils._checkdatenindb /*boolean*/ (_ba);
 //BA.debugLineNum = 1054;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1055;BA.debugLine="End Sub";
return false;
}
public static boolean  _checksignature(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
 //BA.debugLineNum = 1125;BA.debugLine="Sub CheckSignature(iTerminID As Int) As Boolean";
 //BA.debugLineNum = 1126;BA.debugLine="Return DBUtils.CheckSignature(iTerminID)";
if (true) return mostCurrent._dbutils._checksignature /*boolean*/ (_ba,_iterminid);
 //BA.debugLineNum = 1127;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkversion(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1044;BA.debugLine="Sub CheckVersion() As Boolean";
 //BA.debugLineNum = 1045;BA.debugLine="Return DBUtils.CheckVersion()";
if (true) return mostCurrent._dbutils._checkversion /*boolean*/ (_ba);
 //BA.debugLineNum = 1046;BA.debugLine="End Sub";
return false;
}
public static int  _cint(anywheresoftware.b4a.BA _ba,Object _o) throws Exception{
 //BA.debugLineNum = 1531;BA.debugLine="Sub CInt(o As Object) As Int";
 //BA.debugLineNum = 1532;BA.debugLine="If o = True Then";
if ((_o).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 1533;BA.debugLine="Return 1";
if (true) return (int) (1);
 }else {
 //BA.debugLineNum = 1535;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 1537;BA.debugLine="End Sub";
return 0;
}
public static boolean  _datenvormerken(anywheresoftware.b4a.BA _ba,int _iterminid,String _stermin,String _sstartzeit,String _sdauer,String _sklasse,String _sfahrbezeichnung,String _streffpunkt,String _sfahrlehrer,String _skfz,String _sbegleitfahrzeug) throws Exception{
boolean _bresult = false;
 //BA.debugLineNum = 704;BA.debugLine="Sub DatenVormerken(iTerminID As Int, sTermin As St";
 //BA.debugLineNum = 705;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 707;BA.debugLine="If Main.bDatenVorhanden Then";
if (mostCurrent._main._bdatenvorhanden /*boolean*/ ) { 
 //BA.debugLineNum = 708;BA.debugLine="bResult = DBUtils.DatenVormerkenUpdate(iTerminID";
_bresult = mostCurrent._dbutils._datenvormerkenupdate /*boolean*/ (_ba,_iterminid,_stermin,_sstartzeit,_sdauer,_sklasse,_sfahrbezeichnung,_streffpunkt,_sfahrlehrer,_skfz,_sbegleitfahrzeug);
 }else {
 //BA.debugLineNum = 710;BA.debugLine="bResult = DBUtils.DatenVormerkenInsert(sTermin,";
_bresult = mostCurrent._dbutils._datenvormerkeninsert /*boolean*/ (_ba,_stermin,_sstartzeit,_sdauer,_sklasse,_sfahrbezeichnung,_streffpunkt,_sfahrlehrer,_skfz,_sbegleitfahrzeug);
 };
 //BA.debugLineNum = 713;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 714;BA.debugLine="End Sub";
return false;
}
public static String  _dbinbenutzung(anywheresoftware.b4a.BA _ba,boolean _bvalue) throws Exception{
 //BA.debugLineNum = 1039;BA.debugLine="Sub DBinBenutzung(bValue As Boolean)";
 //BA.debugLineNum = 1040;BA.debugLine="DBUtils.DBinBenutzung(bValue)";
mostCurrent._dbutils._dbinbenutzung /*String*/ (_ba,_bvalue);
 //BA.debugLineNum = 1041;BA.debugLine="End Sub";
return "";
}
public static String  _einstellungbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lbleinstfahrlehrer,anywheresoftware.b4a.objects.LabelWrapper _lbleinstkfz,anywheresoftware.b4a.objects.LabelWrapper _lbleinstfahreinheit,anywheresoftware.b4a.objects.LabelWrapper _lbleinstelltreffpunkt,anywheresoftware.b4a.objects.ButtonWrapper _bteinstellaktuelleliste,anywheresoftware.b4a.objects.ButtonWrapper _bteinstellspeichern,anywheresoftware.b4a.objects.LabelWrapper _lblbegleitfahrzeugerfassen,anywheresoftware.b4a.objects.LabelWrapper _lblpruefungergebnis,anywheresoftware.b4a.objects.ButtonWrapper _btappbeenden,anywheresoftware.b4a.objects.EditTextWrapper _txtneuertreffpunkt,anywheresoftware.b4a.objects.EditTextWrapper _txteinstanzeigefahreinheit,anywheresoftware.b4a.objects.LabelWrapper _lblbvfkontrolle) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub EinstellungBeschriftung(lblEinstFahrlehrer As";
 //BA.debugLineNum = 105;BA.debugLine="lblEinstFahrlehrer.Text = \"Fahrlehrer einstellen:";
_lbleinstfahrlehrer.setText(BA.ObjectToCharSequence("Fahrlehrer einstellen:"));
 //BA.debugLineNum = 106;BA.debugLine="lblEinstKfz.Text = \"Kfz einstellen:\"";
_lbleinstkfz.setText(BA.ObjectToCharSequence("Kfz einstellen:"));
 //BA.debugLineNum = 107;BA.debugLine="lblEinstFahrEinheit.Text = \"Grundzeit Fahreinheit";
_lbleinstfahreinheit.setText(BA.ObjectToCharSequence("Grundzeit Fahreinheiten:"));
 //BA.debugLineNum = 108;BA.debugLine="lblEinstellTreffpunkt.Text = \"Neuer Treffpunkt /";
_lbleinstelltreffpunkt.setText(BA.ObjectToCharSequence("Neuer Treffpunkt / Bemerkung:"));
 //BA.debugLineNum = 109;BA.debugLine="btEinstellAktuelleListe.Text = \"Aktuelle Liste\"";
_bteinstellaktuelleliste.setText(BA.ObjectToCharSequence("Aktuelle Liste"));
 //BA.debugLineNum = 110;BA.debugLine="btEinstellSpeichern.Text = \"Hinzufügen\"";
_bteinstellspeichern.setText(BA.ObjectToCharSequence("Hinzufügen"));
 //BA.debugLineNum = 111;BA.debugLine="lblBegleitfahrzeugErfassen.Text = \"Begleit-Fahrze";
_lblbegleitfahrzeugerfassen.setText(BA.ObjectToCharSequence("Begleit-Fahrzeug erfassen.......J/N:"));
 //BA.debugLineNum = 112;BA.debugLine="lblPruefungErgebnis.Text = \"Bei Prüfung Ergebnisa";
_lblpruefungergebnis.setText(BA.ObjectToCharSequence("Bei Prüfung Ergebnisabfrage...J/N:"));
 //BA.debugLineNum = 113;BA.debugLine="lblBVFKontrolle.Text = \"Ausbildungsdiagrammkarten";
_lblbvfkontrolle.setText(BA.ObjectToCharSequence("Ausbildungsdiagrammkarten...J/N:"));
 //BA.debugLineNum = 114;BA.debugLine="btAppBeenden.Text = \"App für Transport beenden\"";
_btappbeenden.setText(BA.ObjectToCharSequence("App für Transport beenden"));
 //BA.debugLineNum = 116;BA.debugLine="txtNeuerTreffpunkt.Color = Colors.White";
_txtneuertreffpunkt.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 117;BA.debugLine="txtEinstAnzeigeFahrEinheit.Color = Colors.White";
_txteinstanzeigefahreinheit.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _erfasstezahlungenholen(anywheresoftware.b4a.BA _ba,String _sausswahl,anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungen) throws Exception{
 //BA.debugLineNum = 647;BA.debugLine="Sub ErfassteZahlungenHolen(sAusswahl As String, ls";
 //BA.debugLineNum = 648;BA.debugLine="Return DBUtils.ErfassteZahlungenHolen(sAusswahl,";
if (true) return mostCurrent._dbutils._erfasstezahlungenholen /*String*/ (_ba,_sausswahl,_lstzahlungen);
 //BA.debugLineNum = 649;BA.debugLine="End Sub";
return "";
}
public static String  _fahrdatenbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblabfahrt,anywheresoftware.b4a.objects.LabelWrapper _lbleinheit,anywheresoftware.b4a.objects.LabelWrapper _lblklasse,anywheresoftware.b4a.objects.LabelWrapper _lblauswahlklassealle,anywheresoftware.b4a.objects.LabelWrapper _lblkfz,anywheresoftware.b4a.objects.LabelWrapper _lblfahrtbezeichnung,anywheresoftware.b4a.objects.LabelWrapper _lblstdplus,anywheresoftware.b4a.objects.LabelWrapper _lblstdminus,anywheresoftware.b4a.objects.LabelWrapper _lblminplus,anywheresoftware.b4a.objects.LabelWrapper _lblminminus,anywheresoftware.b4a.objects.LabelWrapper _lblmin1plus,anywheresoftware.b4a.objects.LabelWrapper _lblmin1minus,anywheresoftware.b4a.objects.LabelWrapper _lbleinheitplus,anywheresoftware.b4a.objects.LabelWrapper _lbleinheitminus,anywheresoftware.b4a.objects.LabelWrapper _lblabfahrtanzeige,anywheresoftware.b4a.objects.LabelWrapper _lbleinheitanzeige,anywheresoftware.b4a.objects.LabelWrapper _lbltreffpunkt,anywheresoftware.b4a.objects.LabelWrapper _lblbegleitfahrzeug,anywheresoftware.b4a.objects.ButtonWrapper _btvormerken,anywheresoftware.b4a.objects.ButtonWrapper _btabbruch,anywheresoftware.b4a.objects.ButtonWrapper _btspeichern,anywheresoftware.b4a.objects.ButtonWrapper _btunterschrift,anywheresoftware.b4a.objects.LabelWrapper _lblpfeil,anywheresoftware.b4a.objects.LabelWrapper _lbleinheit5plus,anywheresoftware.b4a.objects.LabelWrapper _lbleinheit5minus,anywheresoftware.b4a.objects.LabelWrapper _lbleinheit1plus,anywheresoftware.b4a.objects.LabelWrapper _lbleinheit1minus,anywheresoftware.b4a.objects.LabelWrapper _lblsonder,anywheresoftware.b4a.objects.LabelWrapper _lblpruefungsergebnisse,anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbnichterfassen,anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbnichtbestanden,anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbbestanden,anywheresoftware.b4a.objects.ButtonWrapper _btpruefergebnisok,anywheresoftware.b4a.objects.EditTextWrapper _txtauswahltreffpunkt) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub FahrdatenBeschriftung(lblAbfahrt As Label, lbl";
 //BA.debugLineNum = 61;BA.debugLine="lblAbfahrt.Text = \"Abfahrt (Std - Min)\"";
_lblabfahrt.setText(BA.ObjectToCharSequence("Abfahrt (Std - Min)"));
 //BA.debugLineNum = 62;BA.debugLine="lblEinheit.Text = \"Menge in Min (15 - 5)\"";
_lbleinheit.setText(BA.ObjectToCharSequence("Menge in Min (15 - 5)"));
 //BA.debugLineNum = 63;BA.debugLine="lblKlasse.Text = \"Klasse\"";
_lblklasse.setText(BA.ObjectToCharSequence("Klasse"));
 //BA.debugLineNum = 64;BA.debugLine="lblAuswahlKlasseAlle.Text = \"alle Kl.\"";
_lblauswahlklassealle.setText(BA.ObjectToCharSequence("alle Kl."));
 //BA.debugLineNum = 65;BA.debugLine="lblKfz.Text = \"KFZ\"";
_lblkfz.setText(BA.ObjectToCharSequence("KFZ"));
 //BA.debugLineNum = 66;BA.debugLine="lblFahrtbezeichnung.Text = \"Fahrtbez.\"";
_lblfahrtbezeichnung.setText(BA.ObjectToCharSequence("Fahrtbez."));
 //BA.debugLineNum = 67;BA.debugLine="lblBegleitfahrzeug.Text  = \"Begleit-Kfz\"";
_lblbegleitfahrzeug.setText(BA.ObjectToCharSequence("Begleit-Kfz"));
 //BA.debugLineNum = 68;BA.debugLine="lblTreffpunkt.Text = \"Treffpunkt\"";
_lbltreffpunkt.setText(BA.ObjectToCharSequence("Treffpunkt"));
 //BA.debugLineNum = 69;BA.debugLine="lblStdPlus.Text = \"+\"";
_lblstdplus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 70;BA.debugLine="lblStdMinus.Text = \"-\"";
_lblstdminus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 71;BA.debugLine="lblMinPlus.Text = \"+\"";
_lblminplus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 72;BA.debugLine="lblMinMinus.Text = \"-\"";
_lblminminus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 73;BA.debugLine="lblMin1Plus.Text = \"+\"";
_lblmin1plus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 74;BA.debugLine="lblMin1Minus.Text = \"-\"";
_lblmin1minus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 75;BA.debugLine="lblEinheitPlus.Text = \"+\"";
_lbleinheitplus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 76;BA.debugLine="lblEinheitMinus.Text = \"-\"";
_lbleinheitminus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 77;BA.debugLine="lblEinheit5Plus.Text = \"+\"";
_lbleinheit5plus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 78;BA.debugLine="lblEinheit5Minus.Text = \"-\"";
_lbleinheit5minus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 79;BA.debugLine="lblEinheit1Plus.Text = \"+\"";
_lbleinheit1plus.setText(BA.ObjectToCharSequence("+"));
 //BA.debugLineNum = 80;BA.debugLine="lblEinheit1Minus.text = \"-\"";
_lbleinheit1minus.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 81;BA.debugLine="lblSonder.Text = \"So. Ttk.\"";
_lblsonder.setText(BA.ObjectToCharSequence("So. Ttk."));
 //BA.debugLineNum = 83;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 84;BA.debugLine="lblAbfahrtAnzeige.Text = DateTime.Time(DateTime.N";
_lblabfahrtanzeige.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 85;BA.debugLine="lblEinheitAnzeige.Text = \"45\"";
_lbleinheitanzeige.setText(BA.ObjectToCharSequence("45"));
 //BA.debugLineNum = 87;BA.debugLine="btVormerken.Text = \"Vormerken\"";
_btvormerken.setText(BA.ObjectToCharSequence("Vormerken"));
 //BA.debugLineNum = 88;BA.debugLine="btAbbruch.Text = \"Abbruch\"";
_btabbruch.setText(BA.ObjectToCharSequence("Abbruch"));
 //BA.debugLineNum = 89;BA.debugLine="btSpeichern.Text = \"Speichern\"";
_btspeichern.setText(BA.ObjectToCharSequence("Speichern"));
 //BA.debugLineNum = 90;BA.debugLine="btUnterschrift.Text = \"Unterschrift\"";
_btunterschrift.setText(BA.ObjectToCharSequence("Unterschrift"));
 //BA.debugLineNum = 92;BA.debugLine="lblPfeil.Text = \"▼\"";
_lblpfeil.setText(BA.ObjectToCharSequence("▼"));
 //BA.debugLineNum = 94;BA.debugLine="lblPruefungsergebnisse.Text = \"Prüfungsergebnis e";
_lblpruefungsergebnisse.setText(BA.ObjectToCharSequence("Prüfungsergebnis erfassen:"));
 //BA.debugLineNum = 95;BA.debugLine="rbNichtErfassen.Text = \" Nicht erfassen\"";
_rbnichterfassen.setText(BA.ObjectToCharSequence(" Nicht erfassen"));
 //BA.debugLineNum = 96;BA.debugLine="rbNichtBestanden.Text = \" Nicht bestanden\"";
_rbnichtbestanden.setText(BA.ObjectToCharSequence(" Nicht bestanden"));
 //BA.debugLineNum = 97;BA.debugLine="rbBestanden.Text = \" Bestanden\"";
_rbbestanden.setText(BA.ObjectToCharSequence(" Bestanden"));
 //BA.debugLineNum = 98;BA.debugLine="btPruefErgebnisOK.Text = \"OK\"";
_btpruefergebnisok.setText(BA.ObjectToCharSequence("OK"));
 //BA.debugLineNum = 100;BA.debugLine="txtAuswahlTreffpunkt.Color = Colors.White";
_txtauswahltreffpunkt.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static boolean  _fahrdatenspeichernupdate(anywheresoftware.b4a.BA _ba,int _iterminid,String _spruefungsergebnis,boolean _bpruefergebnis,boolean _bunterschrift) throws Exception{
 //BA.debugLineNum = 1130;BA.debugLine="Sub FahrdatenSpeichernUpdate(iTerminID As Int, sPr";
 //BA.debugLineNum = 1132;BA.debugLine="If bPruefergebnis Then";
if (_bpruefergebnis) { 
 //BA.debugLineNum = 1133;BA.debugLine="Return DBUtils.FahrdatenSpeichernUpdateMit(iTerm";
if (true) return mostCurrent._dbutils._fahrdatenspeichernupdatemit /*boolean*/ (_ba,_iterminid,_spruefungsergebnis,_bunterschrift);
 }else {
 //BA.debugLineNum = 1135;BA.debugLine="Return DBUtils.FahrdatenSpeichernUpdateOhne(iTer";
if (true) return mostCurrent._dbutils._fahrdatenspeichernupdateohne /*boolean*/ (_ba,_iterminid,_bunterschrift);
 };
 //BA.debugLineNum = 1137;BA.debugLine="End Sub";
return false;
}
public static String  _fahrlehrerfixieren(anywheresoftware.b4a.BA _ba,String _sfahrlehrer) throws Exception{
String _fahrid = "";
 //BA.debugLineNum = 22;BA.debugLine="Sub FahrlehrerFixieren(sFahrlehrer As String)";
 //BA.debugLineNum = 23;BA.debugLine="If DBUtils.FahrlehrerFixieren(sFahrlehrer) Then";
if (mostCurrent._dbutils._fahrlehrerfixieren /*boolean*/ (_ba,_sfahrlehrer)) { 
 //BA.debugLineNum = 24;BA.debugLine="Dim fahrID As String";
_fahrid = "";
 //BA.debugLineNum = 25;BA.debugLine="fahrID = DBUtils.GetFahrlehrerID(sFahrlehrer)";
_fahrid = BA.NumberToString(mostCurrent._dbutils._getfahrlehrerid /*int*/ (_ba,_sfahrlehrer));
 //BA.debugLineNum = 27;BA.debugLine="ReadWriteINI.UpdateFahrINI(\"Fahrlehrer\", \"Fahrle";
mostCurrent._readwriteini._updatefahrini /*String*/ (_ba,"Fahrlehrer","Fahrlehrer",_sfahrlehrer,_fahrid);
 };
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static int[]  _fahrlehrertermineholen(anywheresoftware.b4a.BA _ba,String _sdate,anywheresoftware.b4a.objects.ListViewWrapper _lsttermine) throws Exception{
int[] _istunden = null;
 //BA.debugLineNum = 765;BA.debugLine="Sub FahrlehrerTermineHolen(sDate As String, lstTer";
 //BA.debugLineNum = 766;BA.debugLine="Dim iStunden(2) As Int";
_istunden = new int[(int) (2)];
;
 //BA.debugLineNum = 767;BA.debugLine="iStunden = DBUtils.FahrlehrerGesamtstundenHolen(s";
_istunden = mostCurrent._dbutils._fahrlehrergesamtstundenholen /*int[]*/ (_ba,_sdate);
 //BA.debugLineNum = 770;BA.debugLine="DBUtils.FahrlehrerTermineHolen(sDate, lstTermine)";
mostCurrent._dbutils._fahrlehrertermineholen /*String*/ (_ba,_sdate,_lsttermine);
 //BA.debugLineNum = 772;BA.debugLine="Return iStunden";
if (true) return _istunden;
 //BA.debugLineNum = 773;BA.debugLine="End Sub";
return null;
}
public static String  _fehlendezuordnungenfuellennachtransport(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1059;BA.debugLine="Sub FehlendeZuordnungenFuellenNachTransport()";
 //BA.debugLineNum = 1060;BA.debugLine="DBUtils.SetLernThermen_Kat()";
mostCurrent._dbutils._setlernthermen_kat /*String*/ (_ba);
 //BA.debugLineNum = 1061;BA.debugLine="DBUtils.SetKlassenIdsInTermine()";
mostCurrent._dbutils._setklassenidsintermine /*String*/ (_ba);
 //BA.debugLineNum = 1062;BA.debugLine="DBUtils.SetAnmeldedatum()";
mostCurrent._dbutils._setanmeldedatum /*String*/ (_ba);
 //BA.debugLineNum = 1063;BA.debugLine="DBUtils.SetKl1BeantragtInStammdaten()";
mostCurrent._dbutils._setkl1beantragtinstammdaten /*String*/ (_ba);
 //BA.debugLineNum = 1064;BA.debugLine="End Sub";
return "";
}
public static String  _fillausbildunglernpunktelistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernpunkte,String _sschueler,int _ikategorie) throws Exception{
 //BA.debugLineNum = 642;BA.debugLine="Sub FillAusbildungLernPunkteListView(lstAusbildung";
 //BA.debugLineNum = 643;BA.debugLine="DBUtils.FillAusbildungLernPunkteListView(lstAusbi";
mostCurrent._dbutils._fillausbildunglernpunktelistview /*String*/ (_ba,_lstausbildunglernpunkte,_sschueler,_ikategorie);
 //BA.debugLineNum = 644;BA.debugLine="End Sub";
return "";
}
public static String  _fillausbildunglernthemenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernthemen,int _ikategorie) throws Exception{
 //BA.debugLineNum = 637;BA.debugLine="Sub FillAusbildungLernThemenListView(lstAusbildung";
 //BA.debugLineNum = 638;BA.debugLine="DBUtils.FillAusbildungLernThemenListView(lstAusbi";
mostCurrent._dbutils._fillausbildunglernthemenlistview /*String*/ (_ba,_lstausbildunglernthemen,_ikategorie);
 //BA.debugLineNum = 639;BA.debugLine="End Sub";
return "";
}
public static String  _fillbegleitfahrzeuglistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstbegleitfahrzeug) throws Exception{
 //BA.debugLineNum = 740;BA.debugLine="Sub FillBegleitfahrzeugListView(lstBegleitfahrzeug";
 //BA.debugLineNum = 741;BA.debugLine="DBUtils.FillBegleitfahrzeugListView(lstBegleitfah";
mostCurrent._dbutils._fillbegleitfahrzeuglistview /*String*/ (_ba,_lstbegleitfahrzeug);
 //BA.debugLineNum = 742;BA.debugLine="End Sub";
return "";
}
public static String  _fillfahrlehrerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstfahrlehrer) throws Exception{
 //BA.debugLineNum = 576;BA.debugLine="Sub FillFahrlehrerListView(lstFahrlehrer As ListVi";
 //BA.debugLineNum = 577;BA.debugLine="DBUtils.FillFahrlehrerListView(lstFahrlehrer)";
mostCurrent._dbutils._fillfahrlehrerlistview /*String*/ (_ba,_lstfahrlehrer);
 //BA.debugLineNum = 578;BA.debugLine="End Sub";
return "";
}
public static String  _fillfahrtenbezeichnunglistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstfahrtbezeichnung) throws Exception{
 //BA.debugLineNum = 730;BA.debugLine="Sub FillFahrtenbezeichnungListView(lstFahrtBezeich";
 //BA.debugLineNum = 731;BA.debugLine="DBUtils.FillFahrtenbezeichnungListView(lstFahrtBe";
mostCurrent._dbutils._fillfahrtenbezeichnunglistview /*String*/ (_ba,_lstfahrtbezeichnung);
 //BA.debugLineNum = 732;BA.debugLine="End Sub";
return "";
}
public static String  _fillkfzlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstkfz) throws Exception{
 //BA.debugLineNum = 581;BA.debugLine="Sub FillKfzListView(lstKfz As ListView)";
 //BA.debugLineNum = 582;BA.debugLine="DBUtils.FillKfzListView(lstKfz)";
mostCurrent._dbutils._fillkfzlistview /*String*/ (_ba,_lstkfz);
 //BA.debugLineNum = 583;BA.debugLine="End Sub";
return "";
}
public static String  _fillklassenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstklassen) throws Exception{
 //BA.debugLineNum = 621;BA.debugLine="Sub FillKlassenListView(lstKlassen As ListView)";
 //BA.debugLineNum = 622;BA.debugLine="DBUtils.FillKlassenListView(lstKlassen)";
mostCurrent._dbutils._fillklassenlistview /*String*/ (_ba,_lstklassen);
 //BA.debugLineNum = 623;BA.debugLine="End Sub";
return "";
}
public static String[][]  _fillschuelerarray(anywheresoftware.b4a.BA _ba,String _sabc) throws Exception{
 //BA.debugLineNum = 571;BA.debugLine="Sub FillSchuelerArray(sABC As String) As String(,)";
 //BA.debugLineNum = 572;BA.debugLine="Return DBUtils.FillSchuelerArray(sABC)";
if (true) return mostCurrent._dbutils._fillschuelerarray /*String[][]*/ (_ba,_sabc);
 //BA.debugLineNum = 573;BA.debugLine="End Sub";
return null;
}
public static String  _fillschuelerklasselistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstschuelerklasse) throws Exception{
String _sklassen = "";
String _sklasse1 = "";
String _sklasse2 = "";
String _sklasse3 = "";
String _sklassenspeicher = "";
int _pos1 = 0;
int _pos2 = 0;
 //BA.debugLineNum = 587;BA.debugLine="Sub FillSchuelerKlasseListView(lstSchuelerKlasse A";
 //BA.debugLineNum = 588;BA.debugLine="Dim sKlassen, sKlasse1, sKlasse2, sKlasse3, sKlas";
_sklassen = "";
_sklasse1 = "";
_sklasse2 = "";
_sklasse3 = "";
_sklassenspeicher = "";
 //BA.debugLineNum = 589;BA.debugLine="Dim pos1, pos2 As Int";
_pos1 = 0;
_pos2 = 0;
 //BA.debugLineNum = 591;BA.debugLine="sKlassen = DBUtils.FillSchuelerKlasseListView(Mai";
_sklassen = mostCurrent._dbutils._fillschuelerklasselistview /*String*/ (_ba,mostCurrent._main._sausgewaehlterschueler /*String*/ ,mostCurrent._main._iausgewaehlterschuelrid /*int*/ );
 //BA.debugLineNum = 595;BA.debugLine="lstSchuelerKlasse.Clear";
_lstschuelerklasse.Clear();
 //BA.debugLineNum = 597;BA.debugLine="pos1 = sKlassen.IndexOf(\", \")";
_pos1 = _sklassen.indexOf(", ");
 //BA.debugLineNum = 598;BA.debugLine="If  pos1 > 0 Then";
if (_pos1>0) { 
 //BA.debugLineNum = 599;BA.debugLine="sKlasse1 = sKlassen.SubString2(0, pos1)";
_sklasse1 = _sklassen.substring((int) (0),_pos1);
 //BA.debugLineNum = 600;BA.debugLine="sKlassenspeicher = sKlassen.SubString(pos1 + 2)";
_sklassenspeicher = _sklassen.substring((int) (_pos1+2));
 //BA.debugLineNum = 602;BA.debugLine="pos2 = sKlassenspeicher.IndexOf(\", \")";
_pos2 = _sklassenspeicher.indexOf(", ");
 //BA.debugLineNum = 603;BA.debugLine="If pos2 > 0 Then";
if (_pos2>0) { 
 //BA.debugLineNum = 604;BA.debugLine="sKlasse2 = sKlassenspeicher.SubString2(0, pos2)";
_sklasse2 = _sklassenspeicher.substring((int) (0),_pos2);
 //BA.debugLineNum = 605;BA.debugLine="sKlasse3 = sKlassenspeicher.SubString(pos2 + 2)";
_sklasse3 = _sklassenspeicher.substring((int) (_pos2+2));
 //BA.debugLineNum = 607;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlasse1)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklasse1));
 //BA.debugLineNum = 608;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlasse2)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklasse2));
 //BA.debugLineNum = 609;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlasse3)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklasse3));
 }else {
 //BA.debugLineNum = 611;BA.debugLine="sKlasse2 = sKlassenspeicher";
_sklasse2 = _sklassenspeicher;
 //BA.debugLineNum = 612;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlasse1)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklasse1));
 //BA.debugLineNum = 613;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlasse2)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklasse2));
 };
 }else {
 //BA.debugLineNum = 616;BA.debugLine="lstSchuelerKlasse.AddSingleLine(sKlassen)";
_lstschuelerklasse.AddSingleLine(BA.ObjectToCharSequence(_sklassen));
 };
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return "";
}
public static String  _fillschuelerlist(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lschueler) throws Exception{
 //BA.debugLineNum = 563;BA.debugLine="Sub FillSchuelerList(lSchueler As ListView)";
 //BA.debugLineNum = 564;BA.debugLine="DBUtils.FillSchuelerList(lSchueler)";
mostCurrent._dbutils._fillschuelerlist /*String*/ (_ba,_lschueler);
 //BA.debugLineNum = 565;BA.debugLine="End Sub";
return "";
}
public static String  _fillschuelerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstschueler) throws Exception{
 //BA.debugLineNum = 556;BA.debugLine="Sub FillSchuelerListView(lstSchueler As ListView)";
 //BA.debugLineNum = 557;BA.debugLine="DBUtils.FillSchuelerListView(lstSchueler)";
mostCurrent._dbutils._fillschuelerlistview /*String*/ (_ba,_lstschueler);
 //BA.debugLineNum = 559;BA.debugLine="Main.sAusgewaehlterSchueler = Main.aSchueler(0, 0";
mostCurrent._main._sausgewaehlterschueler /*String*/  = mostCurrent._main._aschueler /*String[][]*/ [(int) (0)][(int) (0)];
 //BA.debugLineNum = 560;BA.debugLine="Main.iAusgewaehlterSchuelrID = Main.aSchueler(0,";
mostCurrent._main._iausgewaehlterschuelrid /*int*/  = (int)(Double.parseDouble(mostCurrent._main._aschueler /*String[][]*/ [(int) (0)][(int) (1)]));
 //BA.debugLineNum = 561;BA.debugLine="End Sub";
return "";
}
public static int  _fillschuelerlistviewitems(anywheresoftware.b4a.BA _ba,String _sabc) throws Exception{
 //BA.debugLineNum = 567;BA.debugLine="Sub FillSchuelerListViewItems(sABC As String) As I";
 //BA.debugLineNum = 568;BA.debugLine="Return DBUtils.FillSchuelerListViewItems(sABC)";
if (true) return mostCurrent._dbutils._fillschuelerlistviewitems /*int*/ (_ba,_sabc);
 //BA.debugLineNum = 569;BA.debugLine="End Sub";
return 0;
}
public static String  _fillsonstigetaetigkeitenlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstsonstigetaetigkeiten) throws Exception{
 //BA.debugLineNum = 735;BA.debugLine="Sub FillSonstigeTaetigkeitenListView(lstSonstigeTa";
 //BA.debugLineNum = 736;BA.debugLine="DBUtils.FillSonstigeTaetigkeitenListView(lstSonst";
mostCurrent._dbutils._fillsonstigetaetigkeitenlistview /*String*/ (_ba,_lstsonstigetaetigkeiten);
 //BA.debugLineNum = 737;BA.debugLine="End Sub";
return "";
}
public static String  _filltreffpunktlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lsttreffpunkt) throws Exception{
 //BA.debugLineNum = 626;BA.debugLine="Sub FillTreffpunktListView(lstTreffpunkt As ListVi";
 //BA.debugLineNum = 627;BA.debugLine="DBUtils.FillTreffpunktListView(lstTreffpunkt)";
mostCurrent._dbutils._filltreffpunktlistview /*String*/ (_ba,_lsttreffpunkt);
 //BA.debugLineNum = 628;BA.debugLine="End Sub";
return "";
}
public static String  _fillzahlungenfuerlistview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungfuer) throws Exception{
 //BA.debugLineNum = 631;BA.debugLine="Sub FillZahlungenFuerListView(lstZahlungFuer As Li";
 //BA.debugLineNum = 632;BA.debugLine="DBUtils.FillZahlungenFuerListView(lstZahlungFuer)";
mostCurrent._dbutils._fillzahlungenfuerlistview /*String*/ (_ba,_lstzahlungfuer);
 //BA.debugLineNum = 633;BA.debugLine="End Sub";
return "";
}
public static String  _getkfz(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
 //BA.debugLineNum = 750;BA.debugLine="Sub GetKFZ(iTerminID As Int) As String";
 //BA.debugLineNum = 751;BA.debugLine="Return DBUtils.GetKFZ(iTerminID)";
if (true) return mostCurrent._dbutils._getkfz /*String*/ (_ba,_iterminid);
 //BA.debugLineNum = 752;BA.debugLine="End Sub";
return "";
}
public static int  _getschuelerid(anywheresoftware.b4a.BA _ba,String _sausgewaehlterschueler) throws Exception{
 //BA.debugLineNum = 1139;BA.debugLine="Sub GetSchuelerID(sAusgewaehlterSchueler As String";
 //BA.debugLineNum = 1140;BA.debugLine="Return DBUtils.GetSchuelerIDMatchcode(sAusgewaehl";
if (true) return mostCurrent._dbutils._getschueleridmatchcode /*int*/ (_ba,_sausgewaehlterschueler);
 //BA.debugLineNum = 1141;BA.debugLine="End Sub";
return 0;
}
public static String  _getschuelerklasse(anywheresoftware.b4a.BA _ba,int _iausgewaehlterschueler) throws Exception{
 //BA.debugLineNum = 745;BA.debugLine="Sub GetSchuelerKlasse(iAusgewaehlterSchueler As In";
 //BA.debugLineNum = 746;BA.debugLine="Return DBUtils.GetSchuelerKlasse(iAusgewaehlterSc";
if (true) return mostCurrent._dbutils._getschuelerklasse /*String*/ (_ba,_iausgewaehlterschueler);
 //BA.debugLineNum = 747;BA.debugLine="End Sub";
return "";
}
public static String  _getselectedkfz(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 760;BA.debugLine="Sub GetSelectedKFZ() As String";
 //BA.debugLineNum = 761;BA.debugLine="Return DBUtils.GetSelectedKFZ";
if (true) return mostCurrent._dbutils._getselectedkfz /*String*/ (_ba);
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
return "";
}
public static String  _initialisierungsignature(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1,anywheresoftware.b4a.objects.PanelWrapper _panunterschrift,fadata.mobil.signaturecapture._signaturedata _sigd) throws Exception{
 //BA.debugLineNum = 870;BA.debugLine="Sub InitialisierungSignature(Canvas1 As Canvas, pa";
 //BA.debugLineNum = 871;BA.debugLine="Canvas1.Initialize(panUnterschrift)";
_canvas1.Initialize((android.view.View)(_panunterschrift.getObject()));
 //BA.debugLineNum = 873;BA.debugLine="SigD.Initialize";
_sigd.Initialize();
 //BA.debugLineNum = 874;BA.debugLine="SigD.Canvas = Canvas1";
_sigd.Canvas /*anywheresoftware.b4a.objects.drawable.CanvasWrapper*/  = _canvas1;
 //BA.debugLineNum = 875;BA.debugLine="SigD.Panel = panUnterschrift";
_sigd.Panel /*anywheresoftware.b4a.objects.PanelWrapper*/  = _panunterschrift;
 //BA.debugLineNum = 876;BA.debugLine="SigD.SignatureColor = Colors.Black";
_sigd.SignatureColor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 877;BA.debugLine="SigD.SignatureWidth = 3dip 'Schriftbreite";
_sigd.SignatureWidth /*int*/  = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3));
 //BA.debugLineNum = 878;BA.debugLine="End Sub";
return "";
}
public static boolean  _insertneuentreffpunkt(anywheresoftware.b4a.BA _ba,String _sneuertreffpunkt) throws Exception{
 //BA.debugLineNum = 776;BA.debugLine="Sub InsertNeuenTreffpunkt(sNeuerTreffpunkt As Stri";
 //BA.debugLineNum = 777;BA.debugLine="If (DBUtils.InsertNeuerTreffpunkt(sNeuerTreffpunk";
if ((mostCurrent._dbutils._insertneuertreffpunkt /*int*/ (_ba,_sneuertreffpunkt,(int) (1))>0)) { 
 //BA.debugLineNum = 778;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 780;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 782;BA.debugLine="End Sub";
return false;
}
public static boolean  _insertupdatebvfdata(anywheresoftware.b4a.BA _ba,String _sdate,String _sschueler,boolean _cbbesonderheinsteigen,boolean _cbeinstellen,boolean _cblenkrad,boolean _cbspiegel,boolean _cbkopfstuetze,boolean _cbsitz,boolean _cblenkradhaltung,boolean _cbpedale,boolean _cbgurt,boolean _cbschaltwaehlhebel,boolean _cbzuendschloss,boolean _cbmotoranlassen,boolean _cbanfahranhalte,boolean _cbschaltuebg,boolean _cbhoch1_2,boolean _cbhoch2_3,boolean _cbhoch3_4,boolean _cbrunter4_3,boolean _cbrunter3_2,boolean _cbrunter2_1,boolean _cbrunter4_2,boolean _cbrunter4_1,boolean _cbrunter3_1,boolean _cblenkuebung,boolean _cbumkehren,boolean _cbeinparkenlaengs,boolean _cblvorwaertsrechts,boolean _cblrueckwaertslinks,boolean _cblrueckwaertsrechts,boolean _cblvorwaertslinks,boolean _cbrueckwaertsfahren,boolean _cbeinparkenquer,boolean _cbqvorwaertsrechts,boolean _cbqrueckwaertslinks,boolean _cbqrueckwaertsrechts,boolean _cbqvorwaertslinks,boolean _cbgefahrbremsung,boolean _cbrollenschalten,boolean _cbbremsschalten,boolean _cbbremsuebung,boolean _cbdegressiv,boolean _cbzielbremsung,boolean _cbgefahrsituation,boolean _cbgefaelle,boolean _cbanhalten,boolean _cbanfahren,boolean _cbrueckwaerts,boolean _cbsichern,boolean _cbschalten,boolean _cbsteigung,boolean _cbstanhalten,boolean _cbstanfahren,boolean _cbstrueckwaerts,boolean _cbstsichern,boolean _cbstschalten,boolean _cbtastgeschw,boolean _cbbedienkontroll,boolean _cboertlichbesonder,boolean _cbfahrbahnbenutzung,boolean _cbeinordnen,boolean _cbmarkierungen,boolean _cbfahrstreifenwechsel,boolean _cblinks,boolean _cbrechts,boolean _cbvorbeifueberholen,boolean _cbabbiegen,boolean _cbabrechts,boolean _cbablinks,boolean _cbmehrspurig,boolean _cbradweg,boolean _cbsonderstreifen,boolean _cbstrassenbahn,boolean _cbeinbahnstrasse,boolean _cbvorfahrt,boolean _cbrechtsvorlinks,boolean _cbgruenpfeil,boolean _cbpolizeibeamte,boolean _cbgruenpfeilschild,boolean _cbgeschwabstand,boolean _cbsituationverkehrstn,boolean _cbfussgaengerueberweg,boolean _cboeffentlverkehrsm,boolean _cbaelterebehinderte,boolean _cbeinbahnstrradfahrer,boolean _cbkinder,boolean _cbschulbus,boolean _cbradfahrermofa,boolean _cbverkehrsberuhigt,boolean _cbschwierigeverkehrsf,boolean _cbengpass,boolean _cbkreisverkehr,boolean _cbbahnuebergangwarte,boolean _cbkritischeverkehrss,boolean _cbhauptverkehrszt,boolean _cbpartnerverhalten,boolean _cbschwungnutzen,boolean _cbfussgaengerschutzb,boolean _cbangepasstegeschw,boolean _cbabstand,boolean _cbulvorne,boolean _cbulhinten,boolean _cbulseitlich,boolean _cbbeobachtspiegel,boolean _cbverkehrszeichen,boolean _cbkreuzungeinmuend,boolean _cbkurven,boolean _cbsteigungen,boolean _cbulgefaelle,boolean _cballeen,boolean _cbueberholen,boolean _cbbesonderesituat,boolean _cbliegenblsichern,boolean _cbeinfahrenortsch,boolean _cbfussgaenger,boolean _cbwildtiere,boolean _cbbesondereanford,boolean _cbleistungsgrenze,boolean _cborientierung,boolean _cbablenkung,boolean _cbfahrtplanung,boolean _cbeinfahrtab,boolean _cbabfahrbahnwechsel,boolean _cbgeschwindigkeit,boolean _cbababstand,boolean _cbabvorne,boolean _cbabhinten,boolean _cbabseitlich,boolean _cbabueberholen,boolean _cbschilder,boolean _cbvorbeifahren,boolean _cbrastparktank,boolean _cbverhunfall,boolean _cbdichterverkehr,boolean _cbbesondersituat,boolean _cbbesonderanford,boolean _cbableistungsgrenze,boolean _cbkonfliktsitua,boolean _cbabablenkung,boolean _cbbeleuchtung,boolean _cbkontrolle,boolean _cbeinstell,boolean _cbbenutzung,boolean _cbfernlicht,boolean _cbverlassenbab,boolean _cbbeleuchtstrasse,boolean _cbunbeleuchtstrasse,boolean _cbparken,boolean _cbdubesondersituat,boolean _cbschlechtewitterung,boolean _cbtiere,boolean _cbbahnuebergaenge,boolean _cbunbelverkehrtn,boolean _cbdubesonderanfor,boolean _cbblendung,boolean _cbduorientierung,boolean _cbabschlussbesp,boolean _cbselbstfahren,boolean _cbinnerorts,boolean _cbausserorts,boolean _cbverantwfahren,boolean _cbtestfpruef,boolean _cbfakt,boolean _cbandere,boolean _cbwiederholung,boolean _cbleistungsbew,boolean _cbreifen,boolean _cbeinausschalten,boolean _cbfunktionpruefen,boolean _cbstandlicht,boolean _cbnebelschluss,boolean _cbblinker,boolean _cbabblendlicht,boolean _cbwarnblicke,boolean _cbhupe,boolean _cbbsfernlicht,boolean _cbschlussleuchte,boolean _cbbremslicht,boolean _cbkontrolllbenenn,boolean _cbrueckstrahler,boolean _cbvorhandensein,boolean _cbbeschaedigung,boolean _cblenkung,boolean _cblenkschlentriegeln,boolean _cbprueflenkspiel,boolean _cbfunktbremse,boolean _cbbetriebsbremse,boolean _cbfeststellbremse,boolean _cbanlegengurt,boolean _cbrichtigsitz,boolean _cbeinstellrueckspiegel,boolean _cbeinkopfstuetze,boolean _cbeinlenkrad,boolean _cbbedienenagg,boolean _cbheizung,boolean _cbheckheizung,boolean _cbbehsonderaus,boolean _cblueftung,boolean _cbklimaanlage,boolean _cbenergienutzung,boolean _cbkeineunnverbr,boolean _cbrechtztabsch,boolean _cbmotorraum,boolean _cbmotoroel,boolean _cbkuehlmittel,boolean _cbscheibenwaschm,boolean _cbtanken,boolean _cbbremsen,boolean _cbsicherungsmittel,boolean _cbwarndreieck,boolean _cbbordwerkzeug,boolean _cbzusaetzlichaus,boolean _cbverbandskasten,boolean _cbaussenkontrolle,boolean _cbscheibenwischer,boolean _cbkennzeichen,boolean _cbcheckspiegel,boolean _cbcheckbeleuchtung,boolean _cbladung,boolean _cbladungssicherung,boolean _cbkenntlichmachung,boolean _cbfahreschlwitt,boolean _cbwittlueftung,boolean _cbwittscheiben,boolean _cbregen,boolean _cbwasserlachen,boolean _cbwindsturm,boolean _cbmatchschnee,boolean _cbeis,boolean _cbwittbeleuchtung,String _etnotizen) throws Exception{
int _iausbkontrolleoid = 0;
 //BA.debugLineNum = 1144;BA.debugLine="Sub InsertUpdateBVFData(sDate As String, sSchueler";
 //BA.debugLineNum = 1164;BA.debugLine="Dim iAusbKontrolleOID As Int";
_iausbkontrolleoid = 0;
 //BA.debugLineNum = 1165;BA.debugLine="iAusbKontrolleOID = DBUtils.CheckBVFDataExists(sD";
_iausbkontrolleoid = mostCurrent._dbutils._checkbvfdataexists /*int*/ (_ba,_sdate,_sschueler);
 //BA.debugLineNum = 1166;BA.debugLine="If iAusbKontrolleOID > 0 Then";
if (_iausbkontrolleoid>0) { 
 //BA.debugLineNum = 1167;BA.debugLine="Return DBUtils.UpdateBVFData(sDate, sSchueler, C";
if (true) return mostCurrent._dbutils._updatebvfdata /*boolean*/ (_ba,_sdate,_sschueler,_cint(_ba,(Object)(_cbbesonderheinsteigen)),_cint(_ba,(Object)(_cbeinstellen)),_cint(_ba,(Object)(_cblenkrad)),_cint(_ba,(Object)(_cbspiegel)),_cint(_ba,(Object)(_cbkopfstuetze)),_cint(_ba,(Object)(_cbsitz)),_cint(_ba,(Object)(_cblenkradhaltung)),_cint(_ba,(Object)(_cbpedale)),_cint(_ba,(Object)(_cbgurt)),_cint(_ba,(Object)(_cbschaltwaehlhebel)),_cint(_ba,(Object)(_cbzuendschloss)),_cint(_ba,(Object)(_cbmotoranlassen)),_cint(_ba,(Object)(_cbanfahranhalte)),_cint(_ba,(Object)(_cbschaltuebg)),_cint(_ba,(Object)(_cbhoch1_2)),_cint(_ba,(Object)(_cbhoch2_3)),_cint(_ba,(Object)(_cbhoch3_4)),_cint(_ba,(Object)(_cbrunter4_3)),_cint(_ba,(Object)(_cbrunter3_2)),_cint(_ba,(Object)(_cbrunter2_1)),_cint(_ba,(Object)(_cbrunter4_2)),_cint(_ba,(Object)(_cbrunter4_1)),_cint(_ba,(Object)(_cbrunter3_1)),_cint(_ba,(Object)(_cblenkuebung)),_cint(_ba,(Object)(_cbumkehren)),_cint(_ba,(Object)(_cbeinparkenlaengs)),_cint(_ba,(Object)(_cblvorwaertsrechts)),_cint(_ba,(Object)(_cblrueckwaertslinks)),_cint(_ba,(Object)(_cblrueckwaertsrechts)),_cint(_ba,(Object)(_cblvorwaertslinks)),_cint(_ba,(Object)(_cbrueckwaertsfahren)),_cint(_ba,(Object)(_cbeinparkenquer)),_cint(_ba,(Object)(_cbqvorwaertsrechts)),_cint(_ba,(Object)(_cbqrueckwaertslinks)),_cint(_ba,(Object)(_cbqrueckwaertsrechts)),_cint(_ba,(Object)(_cbqvorwaertslinks)),_cint(_ba,(Object)(_cbgefahrbremsung)),_cint(_ba,(Object)(_cbrollenschalten)),_cint(_ba,(Object)(_cbbremsschalten)),_cint(_ba,(Object)(_cbbremsuebung)),_cint(_ba,(Object)(_cbdegressiv)),_cint(_ba,(Object)(_cbzielbremsung)),_cint(_ba,(Object)(_cbgefahrsituation)),_cint(_ba,(Object)(_cbgefaelle)),_cint(_ba,(Object)(_cbanhalten)),_cint(_ba,(Object)(_cbanfahren)),_cint(_ba,(Object)(_cbrueckwaerts)),_cint(_ba,(Object)(_cbsichern)),_cint(_ba,(Object)(_cbschalten)),_cint(_ba,(Object)(_cbsteigung)),_cint(_ba,(Object)(_cbstanhalten)),_cint(_ba,(Object)(_cbstanfahren)),_cint(_ba,(Object)(_cbstrueckwaerts)),_cint(_ba,(Object)(_cbstsichern)),_cint(_ba,(Object)(_cbstschalten)),_cint(_ba,(Object)(_cbtastgeschw)),_cint(_ba,(Object)(_cbbedienkontroll)),_cint(_ba,(Object)(_cboertlichbesonder)),_cint(_ba,(Object)(_cbfahrbahnbenutzung)),_cint(_ba,(Object)(_cbeinordnen)),_cint(_ba,(Object)(_cbmarkierungen)),_cint(_ba,(Object)(_cbfahrstreifenwechsel)),_cint(_ba,(Object)(_cblinks)),_cint(_ba,(Object)(_cbrechts)),_cint(_ba,(Object)(_cbvorbeifueberholen)),_cint(_ba,(Object)(_cbabbiegen)),_cint(_ba,(Object)(_cbabrechts)),_cint(_ba,(Object)(_cbablinks)),_cint(_ba,(Object)(_cbmehrspurig)),_cint(_ba,(Object)(_cbradweg)),_cint(_ba,(Object)(_cbsonderstreifen)),_cint(_ba,(Object)(_cbstrassenbahn)),_cint(_ba,(Object)(_cbeinbahnstrasse)),_cint(_ba,(Object)(_cbvorfahrt)),_cint(_ba,(Object)(_cbrechtsvorlinks)),_cint(_ba,(Object)(_cbgruenpfeil)),_cint(_ba,(Object)(_cbpolizeibeamte)),_cint(_ba,(Object)(_cbgruenpfeilschild)),_cint(_ba,(Object)(_cbgeschwabstand)),_cint(_ba,(Object)(_cbsituationverkehrstn)),_cint(_ba,(Object)(_cbfussgaengerueberweg)),_cint(_ba,(Object)(_cboeffentlverkehrsm)),_cint(_ba,(Object)(_cbaelterebehinderte)),_cint(_ba,(Object)(_cbeinbahnstrradfahrer)),_cint(_ba,(Object)(_cbkinder)),_cint(_ba,(Object)(_cbschulbus)),_cint(_ba,(Object)(_cbradfahrermofa)),_cint(_ba,(Object)(_cbverkehrsberuhigt)),_cint(_ba,(Object)(_cbschwierigeverkehrsf)),_cint(_ba,(Object)(_cbengpass)),_cint(_ba,(Object)(_cbkreisverkehr)),_cint(_ba,(Object)(_cbbahnuebergangwarte)),_cint(_ba,(Object)(_cbkritischeverkehrss)),_cint(_ba,(Object)(_cbhauptverkehrszt)),_cint(_ba,(Object)(_cbpartnerverhalten)),_cint(_ba,(Object)(_cbschwungnutzen)),_cint(_ba,(Object)(_cbfussgaengerschutzb)),_cint(_ba,(Object)(_cbangepasstegeschw)),_cint(_ba,(Object)(_cbabstand)),_cint(_ba,(Object)(_cbulvorne)),_cint(_ba,(Object)(_cbulhinten)),_cint(_ba,(Object)(_cbulseitlich)),_cint(_ba,(Object)(_cbbeobachtspiegel)),_cint(_ba,(Object)(_cbverkehrszeichen)),_cint(_ba,(Object)(_cbkreuzungeinmuend)),_cint(_ba,(Object)(_cbkurven)),_cint(_ba,(Object)(_cbsteigungen)),_cint(_ba,(Object)(_cbulgefaelle)),_cint(_ba,(Object)(_cballeen)),_cint(_ba,(Object)(_cbueberholen)),_cint(_ba,(Object)(_cbbesonderesituat)),_cint(_ba,(Object)(_cbliegenblsichern)),_cint(_ba,(Object)(_cbeinfahrenortsch)),_cint(_ba,(Object)(_cbfussgaenger)),_cint(_ba,(Object)(_cbwildtiere)),_cint(_ba,(Object)(_cbbesondereanford)),_cint(_ba,(Object)(_cbleistungsgrenze)),_cint(_ba,(Object)(_cborientierung)),_cint(_ba,(Object)(_cbablenkung)),_cint(_ba,(Object)(_cbfahrtplanung)),_cint(_ba,(Object)(_cbeinfahrtab)),_cint(_ba,(Object)(_cbabfahrbahnwechsel)),_cint(_ba,(Object)(_cbgeschwindigkeit)),_cint(_ba,(Object)(_cbababstand)),_cint(_ba,(Object)(_cbabvorne)),_cint(_ba,(Object)(_cbabhinten)),_cint(_ba,(Object)(_cbabseitlich)),_cint(_ba,(Object)(_cbabueberholen)),_cint(_ba,(Object)(_cbschilder)),_cint(_ba,(Object)(_cbvorbeifahren)),_cint(_ba,(Object)(_cbrastparktank)),_cint(_ba,(Object)(_cbverhunfall)),_cint(_ba,(Object)(_cbdichterverkehr)),_cint(_ba,(Object)(_cbbesondersituat)),_cint(_ba,(Object)(_cbbesonderanford)),_cint(_ba,(Object)(_cbableistungsgrenze)),_cint(_ba,(Object)(_cbkonfliktsitua)),_cint(_ba,(Object)(_cbabablenkung)),_cint(_ba,(Object)(_cbbeleuchtung)),_cint(_ba,(Object)(_cbkontrolle)),_cint(_ba,(Object)(_cbeinstell)),_cint(_ba,(Object)(_cbbenutzung)),_cint(_ba,(Object)(_cbfernlicht)),_cint(_ba,(Object)(_cbverlassenbab)),_cint(_ba,(Object)(_cbbeleuchtstrasse)),_cint(_ba,(Object)(_cbunbeleuchtstrasse)),_cint(_ba,(Object)(_cbparken)),_cint(_ba,(Object)(_cbdubesondersituat)),_cint(_ba,(Object)(_cbschlechtewitterung)),_cint(_ba,(Object)(_cbtiere)),_cint(_ba,(Object)(_cbbahnuebergaenge)),_cint(_ba,(Object)(_cbunbelverkehrtn)),_cint(_ba,(Object)(_cbdubesonderanfor)),_cint(_ba,(Object)(_cbblendung)),_cint(_ba,(Object)(_cbduorientierung)),_cint(_ba,(Object)(_cbabschlussbesp)),_cint(_ba,(Object)(_cbselbstfahren)),_cint(_ba,(Object)(_cbinnerorts)),_cint(_ba,(Object)(_cbausserorts)),_cint(_ba,(Object)(_cbverantwfahren)),_cint(_ba,(Object)(_cbtestfpruef)),_cint(_ba,(Object)(_cbfakt)),_cint(_ba,(Object)(_cbandere)),_cint(_ba,(Object)(_cbwiederholung)),_cint(_ba,(Object)(_cbleistungsbew)),_cint(_ba,(Object)(_cbreifen)),_cint(_ba,(Object)(_cbeinausschalten)),_cint(_ba,(Object)(_cbfunktionpruefen)),_cint(_ba,(Object)(_cbstandlicht)),_cint(_ba,(Object)(_cbnebelschluss)),_cint(_ba,(Object)(_cbblinker)),_cint(_ba,(Object)(_cbabblendlicht)),_cint(_ba,(Object)(_cbwarnblicke)),_cint(_ba,(Object)(_cbhupe)),_cint(_ba,(Object)(_cbbsfernlicht)),_cint(_ba,(Object)(_cbschlussleuchte)),_cint(_ba,(Object)(_cbbremslicht)),_cint(_ba,(Object)(_cbkontrolllbenenn)),_cint(_ba,(Object)(_cbrueckstrahler)),_cint(_ba,(Object)(_cbvorhandensein)),_cint(_ba,(Object)(_cbbeschaedigung)),_cint(_ba,(Object)(_cblenkung)),_cint(_ba,(Object)(_cblenkschlentriegeln)),_cint(_ba,(Object)(_cbprueflenkspiel)),_cint(_ba,(Object)(_cbfunktbremse)),_cint(_ba,(Object)(_cbbetriebsbremse)),_cint(_ba,(Object)(_cbfeststellbremse)),_cint(_ba,(Object)(_cbanlegengurt)),_cint(_ba,(Object)(_cbrichtigsitz)),_cint(_ba,(Object)(_cbeinstellrueckspiegel)),_cint(_ba,(Object)(_cbeinkopfstuetze)),_cint(_ba,(Object)(_cbeinlenkrad)),_cint(_ba,(Object)(_cbbedienenagg)),_cint(_ba,(Object)(_cbheizung)),_cint(_ba,(Object)(_cbheckheizung)),_cint(_ba,(Object)(_cbbehsonderaus)),_cint(_ba,(Object)(_cblueftung)),_cint(_ba,(Object)(_cbklimaanlage)),_cint(_ba,(Object)(_cbenergienutzung)),_cint(_ba,(Object)(_cbkeineunnverbr)),_cint(_ba,(Object)(_cbrechtztabsch)),_cint(_ba,(Object)(_cbmotorraum)),_cint(_ba,(Object)(_cbmotoroel)),_cint(_ba,(Object)(_cbkuehlmittel)),_cint(_ba,(Object)(_cbscheibenwaschm)),_cint(_ba,(Object)(_cbtanken)),_cint(_ba,(Object)(_cbbremsen)),_cint(_ba,(Object)(_cbsicherungsmittel)),_cint(_ba,(Object)(_cbwarndreieck)),_cint(_ba,(Object)(_cbbordwerkzeug)),_cint(_ba,(Object)(_cbzusaetzlichaus)),_cint(_ba,(Object)(_cbverbandskasten)),_cint(_ba,(Object)(_cbaussenkontrolle)),_cint(_ba,(Object)(_cbscheibenwischer)),_cint(_ba,(Object)(_cbkennzeichen)),_cint(_ba,(Object)(_cbcheckspiegel)),_cint(_ba,(Object)(_cbcheckbeleuchtung)),_cint(_ba,(Object)(_cbladung)),_cint(_ba,(Object)(_cbladungssicherung)),_cint(_ba,(Object)(_cbkenntlichmachung)),_cint(_ba,(Object)(_cbfahreschlwitt)),_cint(_ba,(Object)(_cbwittlueftung)),_cint(_ba,(Object)(_cbwittscheiben)),_cint(_ba,(Object)(_cbregen)),_cint(_ba,(Object)(_cbwasserlachen)),_cint(_ba,(Object)(_cbwindsturm)),_cint(_ba,(Object)(_cbmatchschnee)),_cint(_ba,(Object)(_cbeis)),_cint(_ba,(Object)(_cbwittbeleuchtung)),_etnotizen,_iausbkontrolleoid);
 }else {
 //BA.debugLineNum = 1188;BA.debugLine="Return DBUtils.InsertBVFData(sDate, sSchueler, C";
if (true) return mostCurrent._dbutils._insertbvfdata /*boolean*/ (_ba,_sdate,_sschueler,_cint(_ba,(Object)(_cbbesonderheinsteigen)),_cint(_ba,(Object)(_cbeinstellen)),_cint(_ba,(Object)(_cblenkrad)),_cint(_ba,(Object)(_cbspiegel)),_cint(_ba,(Object)(_cbkopfstuetze)),_cint(_ba,(Object)(_cbsitz)),_cint(_ba,(Object)(_cblenkradhaltung)),_cint(_ba,(Object)(_cbpedale)),_cint(_ba,(Object)(_cbgurt)),_cint(_ba,(Object)(_cbschaltwaehlhebel)),_cint(_ba,(Object)(_cbzuendschloss)),_cint(_ba,(Object)(_cbmotoranlassen)),_cint(_ba,(Object)(_cbanfahranhalte)),_cint(_ba,(Object)(_cbschaltuebg)),_cint(_ba,(Object)(_cbhoch1_2)),_cint(_ba,(Object)(_cbhoch2_3)),_cint(_ba,(Object)(_cbhoch3_4)),_cint(_ba,(Object)(_cbrunter4_3)),_cint(_ba,(Object)(_cbrunter3_2)),_cint(_ba,(Object)(_cbrunter2_1)),_cint(_ba,(Object)(_cbrunter4_2)),_cint(_ba,(Object)(_cbrunter4_1)),_cint(_ba,(Object)(_cbrunter3_1)),_cint(_ba,(Object)(_cblenkuebung)),_cint(_ba,(Object)(_cbumkehren)),_cint(_ba,(Object)(_cbeinparkenlaengs)),_cint(_ba,(Object)(_cblvorwaertsrechts)),_cint(_ba,(Object)(_cblrueckwaertslinks)),_cint(_ba,(Object)(_cblrueckwaertsrechts)),_cint(_ba,(Object)(_cblvorwaertslinks)),_cint(_ba,(Object)(_cbrueckwaertsfahren)),_cint(_ba,(Object)(_cbeinparkenquer)),_cint(_ba,(Object)(_cbqvorwaertsrechts)),_cint(_ba,(Object)(_cbqrueckwaertslinks)),_cint(_ba,(Object)(_cbqrueckwaertsrechts)),_cint(_ba,(Object)(_cbqvorwaertslinks)),_cint(_ba,(Object)(_cbgefahrbremsung)),_cint(_ba,(Object)(_cbrollenschalten)),_cint(_ba,(Object)(_cbbremsschalten)),_cint(_ba,(Object)(_cbbremsuebung)),_cint(_ba,(Object)(_cbdegressiv)),_cint(_ba,(Object)(_cbzielbremsung)),_cint(_ba,(Object)(_cbgefahrsituation)),_cint(_ba,(Object)(_cbgefaelle)),_cint(_ba,(Object)(_cbanhalten)),_cint(_ba,(Object)(_cbanfahren)),_cint(_ba,(Object)(_cbrueckwaerts)),_cint(_ba,(Object)(_cbsichern)),_cint(_ba,(Object)(_cbschalten)),_cint(_ba,(Object)(_cbsteigung)),_cint(_ba,(Object)(_cbstanhalten)),_cint(_ba,(Object)(_cbstanfahren)),_cint(_ba,(Object)(_cbstrueckwaerts)),_cint(_ba,(Object)(_cbstsichern)),_cint(_ba,(Object)(_cbstschalten)),_cint(_ba,(Object)(_cbtastgeschw)),_cint(_ba,(Object)(_cbbedienkontroll)),_cint(_ba,(Object)(_cboertlichbesonder)),_cint(_ba,(Object)(_cbfahrbahnbenutzung)),_cint(_ba,(Object)(_cbeinordnen)),_cint(_ba,(Object)(_cbmarkierungen)),_cint(_ba,(Object)(_cbfahrstreifenwechsel)),_cint(_ba,(Object)(_cblinks)),_cint(_ba,(Object)(_cbrechts)),_cint(_ba,(Object)(_cbvorbeifueberholen)),_cint(_ba,(Object)(_cbabbiegen)),_cint(_ba,(Object)(_cbabrechts)),_cint(_ba,(Object)(_cbablinks)),_cint(_ba,(Object)(_cbmehrspurig)),_cint(_ba,(Object)(_cbradweg)),_cint(_ba,(Object)(_cbsonderstreifen)),_cint(_ba,(Object)(_cbstrassenbahn)),_cint(_ba,(Object)(_cbeinbahnstrasse)),_cint(_ba,(Object)(_cbvorfahrt)),_cint(_ba,(Object)(_cbrechtsvorlinks)),_cint(_ba,(Object)(_cbgruenpfeil)),_cint(_ba,(Object)(_cbpolizeibeamte)),_cint(_ba,(Object)(_cbgruenpfeilschild)),_cint(_ba,(Object)(_cbgeschwabstand)),_cint(_ba,(Object)(_cbsituationverkehrstn)),_cint(_ba,(Object)(_cbfussgaengerueberweg)),_cint(_ba,(Object)(_cboeffentlverkehrsm)),_cint(_ba,(Object)(_cbaelterebehinderte)),_cint(_ba,(Object)(_cbeinbahnstrradfahrer)),_cint(_ba,(Object)(_cbkinder)),_cint(_ba,(Object)(_cbschulbus)),_cint(_ba,(Object)(_cbradfahrermofa)),_cint(_ba,(Object)(_cbverkehrsberuhigt)),_cint(_ba,(Object)(_cbschwierigeverkehrsf)),_cint(_ba,(Object)(_cbengpass)),_cint(_ba,(Object)(_cbkreisverkehr)),_cint(_ba,(Object)(_cbbahnuebergangwarte)),_cint(_ba,(Object)(_cbkritischeverkehrss)),_cint(_ba,(Object)(_cbhauptverkehrszt)),_cint(_ba,(Object)(_cbpartnerverhalten)),_cint(_ba,(Object)(_cbschwungnutzen)),_cint(_ba,(Object)(_cbfussgaengerschutzb)),_cint(_ba,(Object)(_cbangepasstegeschw)),_cint(_ba,(Object)(_cbabstand)),_cint(_ba,(Object)(_cbulvorne)),_cint(_ba,(Object)(_cbulhinten)),_cint(_ba,(Object)(_cbulseitlich)),_cint(_ba,(Object)(_cbbeobachtspiegel)),_cint(_ba,(Object)(_cbverkehrszeichen)),_cint(_ba,(Object)(_cbkreuzungeinmuend)),_cint(_ba,(Object)(_cbkurven)),_cint(_ba,(Object)(_cbsteigungen)),_cint(_ba,(Object)(_cbulgefaelle)),_cint(_ba,(Object)(_cballeen)),_cint(_ba,(Object)(_cbueberholen)),_cint(_ba,(Object)(_cbbesonderesituat)),_cint(_ba,(Object)(_cbliegenblsichern)),_cint(_ba,(Object)(_cbeinfahrenortsch)),_cint(_ba,(Object)(_cbfussgaenger)),_cint(_ba,(Object)(_cbwildtiere)),_cint(_ba,(Object)(_cbbesondereanford)),_cint(_ba,(Object)(_cbleistungsgrenze)),_cint(_ba,(Object)(_cborientierung)),_cint(_ba,(Object)(_cbablenkung)),_cint(_ba,(Object)(_cbfahrtplanung)),_cint(_ba,(Object)(_cbeinfahrtab)),_cint(_ba,(Object)(_cbabfahrbahnwechsel)),_cint(_ba,(Object)(_cbgeschwindigkeit)),_cint(_ba,(Object)(_cbababstand)),_cint(_ba,(Object)(_cbabvorne)),_cint(_ba,(Object)(_cbabhinten)),_cint(_ba,(Object)(_cbabseitlich)),_cint(_ba,(Object)(_cbabueberholen)),_cint(_ba,(Object)(_cbschilder)),_cint(_ba,(Object)(_cbvorbeifahren)),_cint(_ba,(Object)(_cbrastparktank)),_cint(_ba,(Object)(_cbverhunfall)),_cint(_ba,(Object)(_cbdichterverkehr)),_cint(_ba,(Object)(_cbbesondersituat)),_cint(_ba,(Object)(_cbbesonderanford)),_cint(_ba,(Object)(_cbableistungsgrenze)),_cint(_ba,(Object)(_cbkonfliktsitua)),_cint(_ba,(Object)(_cbabablenkung)),_cint(_ba,(Object)(_cbbeleuchtung)),_cint(_ba,(Object)(_cbkontrolle)),_cint(_ba,(Object)(_cbeinstell)),_cint(_ba,(Object)(_cbbenutzung)),_cint(_ba,(Object)(_cbfernlicht)),_cint(_ba,(Object)(_cbverlassenbab)),_cint(_ba,(Object)(_cbbeleuchtstrasse)),_cint(_ba,(Object)(_cbunbeleuchtstrasse)),_cint(_ba,(Object)(_cbparken)),_cint(_ba,(Object)(_cbdubesondersituat)),_cint(_ba,(Object)(_cbschlechtewitterung)),_cint(_ba,(Object)(_cbtiere)),_cint(_ba,(Object)(_cbbahnuebergaenge)),_cint(_ba,(Object)(_cbunbelverkehrtn)),_cint(_ba,(Object)(_cbdubesonderanfor)),_cint(_ba,(Object)(_cbblendung)),_cint(_ba,(Object)(_cbduorientierung)),_cint(_ba,(Object)(_cbabschlussbesp)),_cint(_ba,(Object)(_cbselbstfahren)),_cint(_ba,(Object)(_cbinnerorts)),_cint(_ba,(Object)(_cbausserorts)),_cint(_ba,(Object)(_cbverantwfahren)),_cint(_ba,(Object)(_cbtestfpruef)),_cint(_ba,(Object)(_cbfakt)),_cint(_ba,(Object)(_cbandere)),_cint(_ba,(Object)(_cbwiederholung)),_cint(_ba,(Object)(_cbleistungsbew)),_cint(_ba,(Object)(_cbreifen)),_cint(_ba,(Object)(_cbeinausschalten)),_cint(_ba,(Object)(_cbfunktionpruefen)),_cint(_ba,(Object)(_cbstandlicht)),_cint(_ba,(Object)(_cbnebelschluss)),_cint(_ba,(Object)(_cbblinker)),_cint(_ba,(Object)(_cbabblendlicht)),_cint(_ba,(Object)(_cbwarnblicke)),_cint(_ba,(Object)(_cbhupe)),_cint(_ba,(Object)(_cbbsfernlicht)),_cint(_ba,(Object)(_cbschlussleuchte)),_cint(_ba,(Object)(_cbbremslicht)),_cint(_ba,(Object)(_cbkontrolllbenenn)),_cint(_ba,(Object)(_cbrueckstrahler)),_cint(_ba,(Object)(_cbvorhandensein)),_cint(_ba,(Object)(_cbbeschaedigung)),_cint(_ba,(Object)(_cblenkung)),_cint(_ba,(Object)(_cblenkschlentriegeln)),_cint(_ba,(Object)(_cbprueflenkspiel)),_cint(_ba,(Object)(_cbfunktbremse)),_cint(_ba,(Object)(_cbbetriebsbremse)),_cint(_ba,(Object)(_cbfeststellbremse)),_cint(_ba,(Object)(_cbanlegengurt)),_cint(_ba,(Object)(_cbrichtigsitz)),_cint(_ba,(Object)(_cbeinstellrueckspiegel)),_cint(_ba,(Object)(_cbeinkopfstuetze)),_cint(_ba,(Object)(_cbeinlenkrad)),_cint(_ba,(Object)(_cbbedienenagg)),_cint(_ba,(Object)(_cbheizung)),_cint(_ba,(Object)(_cbheckheizung)),_cint(_ba,(Object)(_cbbehsonderaus)),_cint(_ba,(Object)(_cblueftung)),_cint(_ba,(Object)(_cbklimaanlage)),_cint(_ba,(Object)(_cbenergienutzung)),_cint(_ba,(Object)(_cbkeineunnverbr)),_cint(_ba,(Object)(_cbrechtztabsch)),_cint(_ba,(Object)(_cbmotorraum)),_cint(_ba,(Object)(_cbmotoroel)),_cint(_ba,(Object)(_cbkuehlmittel)),_cint(_ba,(Object)(_cbscheibenwaschm)),_cint(_ba,(Object)(_cbtanken)),_cint(_ba,(Object)(_cbbremsen)),_cint(_ba,(Object)(_cbsicherungsmittel)),_cint(_ba,(Object)(_cbwarndreieck)),_cint(_ba,(Object)(_cbbordwerkzeug)),_cint(_ba,(Object)(_cbzusaetzlichaus)),_cint(_ba,(Object)(_cbverbandskasten)),_cint(_ba,(Object)(_cbaussenkontrolle)),_cint(_ba,(Object)(_cbscheibenwischer)),_cint(_ba,(Object)(_cbkennzeichen)),_cint(_ba,(Object)(_cbcheckspiegel)),_cint(_ba,(Object)(_cbcheckbeleuchtung)),_cint(_ba,(Object)(_cbladung)),_cint(_ba,(Object)(_cbladungssicherung)),_cint(_ba,(Object)(_cbkenntlichmachung)),_cint(_ba,(Object)(_cbfahreschlwitt)),_cint(_ba,(Object)(_cbwittlueftung)),_cint(_ba,(Object)(_cbwittscheiben)),_cint(_ba,(Object)(_cbregen)),_cint(_ba,(Object)(_cbwasserlachen)),_cint(_ba,(Object)(_cbwindsturm)),_cint(_ba,(Object)(_cbmatchschnee)),_cint(_ba,(Object)(_cbeis)),_cint(_ba,(Object)(_cbwittbeleuchtung)),_etnotizen);
 };
 //BA.debugLineNum = 1209;BA.debugLine="End Sub";
return false;
}
public static boolean  _ispruefung(anywheresoftware.b4a.BA _ba,String _sfahrtbezeichnung) throws Exception{
 //BA.debugLineNum = 1120;BA.debugLine="Sub IsPruefung(sFahrtbezeichnung As String) As Boo";
 //BA.debugLineNum = 1121;BA.debugLine="Return DBUtils.IsPruefung(sFahrtbezeichnung)";
if (true) return mostCurrent._dbutils._ispruefung /*boolean*/ (_ba,_sfahrtbezeichnung);
 //BA.debugLineNum = 1122;BA.debugLine="End Sub";
return false;
}
public static String  _kfzfixieren(anywheresoftware.b4a.BA _ba,String _skfz) throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Sub KFZFixieren(sKFZ As String)";
 //BA.debugLineNum = 33;BA.debugLine="DBUtils.KFZFixieren(sKFZ)";
mostCurrent._dbutils._kfzfixieren /*String*/ (_ba,_skfz);
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _konfigdatenfahreinheit(anywheresoftware.b4a.BA _ba) throws Exception{
String _sfahreinheit = "";
 //BA.debugLineNum = 839;BA.debugLine="Sub KonfigdatenFahreinheit As String";
 //BA.debugLineNum = 840;BA.debugLine="Dim sFahrEinheit As String";
_sfahreinheit = "";
 //BA.debugLineNum = 842;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\") T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")) { 
 //BA.debugLineNum = 843;BA.debugLine="sFahrEinheit = ReadWriteINI.ReadFahrEinheit()";
_sfahreinheit = mostCurrent._readwriteini._readfahreinheit /*String*/ (_ba);
 };
 //BA.debugLineNum = 846;BA.debugLine="If sFahrEinheit = \"\" Then";
if ((_sfahreinheit).equals("")) { 
 //BA.debugLineNum = 847;BA.debugLine="sFahrEinheit = \"45\"";
_sfahreinheit = "45";
 };
 //BA.debugLineNum = 850;BA.debugLine="Return sFahrEinheit";
if (true) return _sfahreinheit;
 //BA.debugLineNum = 851;BA.debugLine="End Sub";
return "";
}
public static boolean[]  _konfigdatenladen(anywheresoftware.b4a.BA _ba) throws Exception{
boolean[] _akonfig = null;
 //BA.debugLineNum = 828;BA.debugLine="Sub KonfigdatenLaden As Boolean()";
 //BA.debugLineNum = 829;BA.debugLine="Dim aKonfig(3) As Boolean";
_akonfig = new boolean[(int) (3)];
;
 //BA.debugLineNum = 830;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\") T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")) { 
 //BA.debugLineNum = 831;BA.debugLine="aKonfig = ReadWriteINI.ReadConfigData()";
_akonfig = mostCurrent._readwriteini._readconfigdata /*boolean[]*/ (_ba);
 }else {
 //BA.debugLineNum = 833;BA.debugLine="aKonfig = DBUtils.KonfigdatenLaden()";
_akonfig = mostCurrent._dbutils._konfigdatenladen /*boolean[]*/ (_ba);
 };
 //BA.debugLineNum = 835;BA.debugLine="Return aKonfig";
if (true) return _akonfig;
 //BA.debugLineNum = 836;BA.debugLine="End Sub";
return null;
}
public static String  _korrigierenderfehlerhaftenmatchcodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1073;BA.debugLine="Sub KorrigierenDerFehlerhaftenMatchCodes()";
 //BA.debugLineNum = 1074;BA.debugLine="DBUtils.KorrigierenFehlerhafteMatchCodes()";
mostCurrent._dbutils._korrigierenfehlerhaftematchcodes /*String*/ (_ba);
 //BA.debugLineNum = 1075;BA.debugLine="End Sub";
return "";
}
public static String  _korrigierendervorhandenenfahrdaten(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1068;BA.debugLine="Sub KorrigierenDerVorhandenenFahrdaten()";
 //BA.debugLineNum = 1069;BA.debugLine="DBUtils.KorrigierenFehlerhafterEintraege()";
mostCurrent._dbutils._korrigierenfehlerhaftereintraege /*String*/ (_ba);
 //BA.debugLineNum = 1070;BA.debugLine="End Sub";
return "";
}
public static String  _ladenfixertreffpunkte(anywheresoftware.b4a.BA _ba) throws Exception{
int _ianzahl = 0;
String[] _atreffpunkt = null;
int _i = 0;
 //BA.debugLineNum = 1078;BA.debugLine="Sub LadenFixerTreffpunkte";
 //BA.debugLineNum = 1083;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 1084;BA.debugLine="iAnzahl = ReadWriteINI.GetTreffpunktEintraege";
_ianzahl = mostCurrent._readwriteini._gettreffpunkteintraege /*int*/ (_ba);
 //BA.debugLineNum = 1086;BA.debugLine="Dim aTreffpunkt() As String";
_atreffpunkt = new String[(int) (0)];
java.util.Arrays.fill(_atreffpunkt,"");
 //BA.debugLineNum = 1088;BA.debugLine="If iAnzahl > 0 Then";
if (_ianzahl>0) { 
 //BA.debugLineNum = 1090;BA.debugLine="For i = 1 To iAnzahl";
{
final int step5 = 1;
final int limit5 = _ianzahl;
_i = (int) (1) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
 //BA.debugLineNum = 1091;BA.debugLine="aTreffpunkt = ReadWriteINI.ReadTreffpunkte(i)";
_atreffpunkt = mostCurrent._readwriteini._readtreffpunkte /*String[]*/ (_ba,_i);
 //BA.debugLineNum = 1093;BA.debugLine="DBUtils.WriteTreffpunkte(aTreffpunkt)";
mostCurrent._dbutils._writetreffpunkte /*String*/ (_ba,_atreffpunkt);
 }
};
 };
 //BA.debugLineNum = 1097;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public static String  _removeneuentreffpunkt(anywheresoftware.b4a.BA _ba,String _streffpunkt) throws Exception{
 //BA.debugLineNum = 785;BA.debugLine="Sub RemoveNeuenTreffpunkt(sTreffpunkt As String)";
 //BA.debugLineNum = 786;BA.debugLine="DBUtils.RemoveNeuenTreffpunkt(sTreffpunkt)";
mostCurrent._dbutils._removeneuentreffpunkt /*String*/ (_ba,_streffpunkt);
 //BA.debugLineNum = 787;BA.debugLine="End Sub";
return "";
}
public static boolean  _saveconfigdatatoini(anywheresoftware.b4a.BA _ba,String _sfahrlehrer,String _scheckedkfz,String _sfahreinheit,boolean _bbegleitfahrzeug,boolean _bprueferg,boolean _bbvfausbildung) throws Exception{
int _ifahrlehrerid = 0;
int _icheckedkfzid = 0;
 //BA.debugLineNum = 854;BA.debugLine="Sub	SaveConfigDataToINI(sFahrlehrer As String, sCh";
 //BA.debugLineNum = 857;BA.debugLine="Dim iFahrlehrerID, iCheckedKFZID As Int";
_ifahrlehrerid = 0;
_icheckedkfzid = 0;
 //BA.debugLineNum = 858;BA.debugLine="iFahrlehrerID = DBUtils.GetFahrlehrerID(sFahrlehr";
_ifahrlehrerid = mostCurrent._dbutils._getfahrlehrerid /*int*/ (_ba,_sfahrlehrer);
 //BA.debugLineNum = 859;BA.debugLine="iCheckedKFZID = DBUtils.GetKFZID(sCheckedKFZ)";
_icheckedkfzid = mostCurrent._dbutils._getkfzid /*int*/ (_ba,_scheckedkfz);
 //BA.debugLineNum = 861;BA.debugLine="Return ReadWriteINI.WriteData(sFahrlehrer, iFahrl";
if (true) return mostCurrent._readwriteini._writedata /*boolean*/ (_ba,_sfahrlehrer,_ifahrlehrerid,_scheckedkfz,_icheckedkfzid,_sfahreinheit,_bbegleitfahrzeug,_bprueferg,_bbvfausbildung);
 //BA.debugLineNum = 862;BA.debugLine="End Sub";
return false;
}
public static boolean  _savetreffpunktetoini(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 865;BA.debugLine="Sub SaveTreffpunkteToINI As Boolean";
 //BA.debugLineNum = 866;BA.debugLine="Return ReadWriteINI.WriteTreffpunkte";
if (true) return mostCurrent._readwriteini._writetreffpunkte /*boolean*/ (_ba);
 //BA.debugLineNum = 867;BA.debugLine="End Sub";
return false;
}
public static String  _setbvffelder(anywheresoftware.b4a.BA _ba,String _sdate,String _sschueler,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderheinsteigen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkradhaltung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpedale,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltwaehlhebel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzuendschloss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoranlassen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahranhalte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltuebg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch1_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch2_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch3_4,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_3,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter2_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_2,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_1,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbumkehren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenlaengs,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaertsfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenquer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertsrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertslinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrollenschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsuebung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdegressiv,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzielbremsung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrsituation,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrueckwaerts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtastgeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienkontroll,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboertlichbesonder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrbahnbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinordnen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmarkierungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrstreifenwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabbiegen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabrechts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmehrspurig,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsonderstreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrassenbahn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorfahrt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtsvorlinks,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeil,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpolizeibeamte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeilschild,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsituationverkehrstn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerueberweg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboeffentlverkehrsm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaelterebehinderte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrradfahrer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkinder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschulbus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradfahrermofa,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrsberuhigt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwierigeverkehrsf,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbengpass,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreisverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergangwarte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkritischeverkehrss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhauptverkehrszt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpartnerverhalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwungnutzen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerschutzb,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbangepasstegeschw,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeobachtspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrszeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreuzungeinmuend,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkurven,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigungen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulgefaelle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cballeen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderesituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbliegenblsichern,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrenortsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaenger,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwildtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondereanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cborientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrtplanung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrtab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabfahrbahnwechsel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwindigkeit,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbababstand,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabvorne,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabhinten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabseitlich,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabueberholen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschilder,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrastparktank,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverhunfall,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdichterverkehr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderanford,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbableistungsgrenze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkonfliktsitua,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabablenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstell,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbenutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverlassenbab,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbeleuchtstrasse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbparken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesondersituat,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlechtewitterung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtiere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergaenge,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbelverkehrtn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesonderanfor,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblendung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbduorientierung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabschlussbesp,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbselbstfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbinnerorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbausserorts,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverantwfahren,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtestfpruef,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfakt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbandere,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwiederholung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsbew,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbreifen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinausschalten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktionpruefen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstandlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbnebelschluss,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblinker,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabblendlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarnblicke,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhupe,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbsfernlicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlussleuchte,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremslicht,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolllbenenn,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckstrahler,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorhandensein,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeschaedigung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkschlentriegeln,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbprueflenkspiel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbetriebsbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfeststellbremse,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanlegengurt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrichtigsitz,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellrueckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinkopfstuetze,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinlenkrad,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienenagg,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheckheizung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbehsonderaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbklimaanlage,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbenergienutzung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkeineunnverbr,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtztabsch,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotorraum,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoroel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkuehlmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwaschm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtanken,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsicherungsmittel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarndreieck,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbordwerkzeug,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzusaetzlichaus,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverbandskasten,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaussenkontrolle,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwischer,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkennzeichen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckspiegel,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckbeleuchtung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladungssicherung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkenntlichmachung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahreschlwitt,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittlueftung,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittscheiben,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbregen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwasserlachen,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwindsturm,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmatchschnee,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeis,anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittbeleuchtung,anywheresoftware.b4a.objects.EditTextWrapper _etnotizen) throws Exception{
int _ibvfdaten = 0;
 //BA.debugLineNum = 1211;BA.debugLine="Sub SetBVFFelder(sDate As String, sSchueler As Str";
 //BA.debugLineNum = 1232;BA.debugLine="Dim iBVFDaten As Int";
_ibvfdaten = 0;
 //BA.debugLineNum = 1233;BA.debugLine="iBVFDaten = DBUtils.CheckBVFDataExists(sDate, sSc";
_ibvfdaten = mostCurrent._dbutils._checkbvfdataexists /*int*/ (_ba,_sdate,_sschueler);
 //BA.debugLineNum = 1234;BA.debugLine="If iBVFDaten = 0 Then";
if (_ibvfdaten==0) { 
 //BA.debugLineNum = 1235;BA.debugLine="BVFFelderLeeren(cbBesonderhEinsteigen, cbEinstel";
_bvffelderleeren(_ba,_cbbesonderheinsteigen,_cbeinstellen,_cblenkrad,_cbspiegel,_cbkopfstuetze,_cbsitz,_cblenkradhaltung,_cbpedale,_cbgurt,_cbschaltwaehlhebel,_cbzuendschloss,_cbmotoranlassen,_cbanfahranhalte,_cbschaltuebg,_cbhoch1_2,_cbhoch2_3,_cbhoch3_4,_cbrunter4_3,_cbrunter3_2,_cbrunter2_1,_cbrunter4_2,_cbrunter4_1,_cbrunter3_1,_cblenkuebung,_cbumkehren,_cbeinparkenlaengs,_cblvorwaertsrechts,_cblrueckwaertslinks,_cblrueckwaertsrechts,_cblvorwaertslinks,_cbrueckwaertsfahren,_cbeinparkenquer,_cbqvorwaertsrechts,_cbqrueckwaertslinks,_cbqrueckwaertsrechts,_cbqvorwaertslinks,_cbgefahrbremsung,_cbrollenschalten,_cbbremsschalten,_cbbremsuebung,_cbdegressiv,_cbzielbremsung,_cbgefahrsituation,_cbgefaelle,_cbanhalten,_cbanfahren,_cbrueckwaerts,_cbsichern,_cbschalten,_cbsteigung,_cbstanhalten,_cbstanfahren,_cbstrueckwaerts,_cbstsichern,_cbstschalten,_cbtastgeschw,_cbbedienkontroll,_cboertlichbesonder,_cbfahrbahnbenutzung,_cbeinordnen,_cbmarkierungen,_cbfahrstreifenwechsel,_cblinks,_cbrechts,_cbvorbeifueberholen,_cbabbiegen,_cbabrechts,_cbablinks,_cbmehrspurig,_cbradweg,_cbsonderstreifen,_cbstrassenbahn,_cbeinbahnstrasse,_cbvorfahrt,_cbrechtsvorlinks,_cbgruenpfeil,_cbpolizeibeamte,_cbgruenpfeilschild,_cbgeschwabstand,_cbsituationverkehrstn,_cbfussgaengerueberweg,_cboeffentlverkehrsm,_cbaelterebehinderte,_cbeinbahnstrradfahrer,_cbkinder,_cbschulbus,_cbradfahrermofa,_cbverkehrsberuhigt,_cbschwierigeverkehrsf,_cbengpass,_cbkreisverkehr,_cbbahnuebergangwarte,_cbkritischeverkehrss,_cbhauptverkehrszt,_cbpartnerverhalten,_cbschwungnutzen,_cbfussgaengerschutzb,_cbangepasstegeschw,_cbabstand,_cbulvorne,_cbulhinten,_cbulseitlich,_cbbeobachtspiegel,_cbverkehrszeichen,_cbkreuzungeinmuend,_cbkurven,_cbsteigungen,_cbulgefaelle,_cballeen,_cbueberholen,_cbbesonderesituat,_cbliegenblsichern,_cbeinfahrenortsch,_cbfussgaenger,_cbwildtiere,_cbbesondereanford,_cbleistungsgrenze,_cborientierung,_cbablenkung,_cbfahrtplanung,_cbeinfahrtab,_cbabfahrbahnwechsel,_cbgeschwindigkeit,_cbababstand,_cbabvorne,_cbabhinten,_cbabseitlich,_cbabueberholen,_cbschilder,_cbvorbeifahren,_cbrastparktank,_cbverhunfall,_cbdichterverkehr,_cbbesondersituat,_cbbesonderanford,_cbableistungsgrenze,_cbkonfliktsitua,_cbabablenkung,_cbbeleuchtung,_cbkontrolle,_cbeinstell,_cbbenutzung,_cbfernlicht,_cbverlassenbab,_cbbeleuchtstrasse,_cbunbeleuchtstrasse,_cbparken,_cbdubesondersituat,_cbschlechtewitterung,_cbtiere,_cbbahnuebergaenge,_cbunbelverkehrtn,_cbdubesonderanfor,_cbblendung,_cbduorientierung,_cbabschlussbesp,_cbselbstfahren,_cbinnerorts,_cbausserorts,_cbverantwfahren,_cbtestfpruef,_cbfakt,_cbandere,_cbwiederholung,_cbleistungsbew,_cbreifen,_cbeinausschalten,_cbfunktionpruefen,_cbstandlicht,_cbnebelschluss,_cbblinker,_cbabblendlicht,_cbwarnblicke,_cbhupe,_cbbsfernlicht,_cbschlussleuchte,_cbbremslicht,_cbkontrolllbenenn,_cbrueckstrahler,_cbvorhandensein,_cbbeschaedigung,_cblenkung,_cblenkschlentriegeln,_cbprueflenkspiel,_cbfunktbremse,_cbbetriebsbremse,_cbfeststellbremse,_cbanlegengurt,_cbrichtigsitz,_cbeinstellrueckspiegel,_cbeinkopfstuetze,_cbeinlenkrad,_cbbedienenagg,_cbheizung,_cbheckheizung,_cbbehsonderaus,_cblueftung,_cbklimaanlage,_cbenergienutzung,_cbkeineunnverbr,_cbrechtztabsch,_cbmotorraum,_cbmotoroel,_cbkuehlmittel,_cbscheibenwaschm,_cbtanken,_cbbremsen,_cbsicherungsmittel,_cbwarndreieck,_cbbordwerkzeug,_cbzusaetzlichaus,_cbverbandskasten,_cbaussenkontrolle,_cbscheibenwischer,_cbkennzeichen,_cbcheckspiegel,_cbcheckbeleuchtung,_cbladung,_cbladungssicherung,_cbkenntlichmachung,_cbfahreschlwitt,_cbwittlueftung,_cbwittscheiben,_cbregen,_cbwasserlachen,_cbwindsturm,_cbmatchschnee,_cbeis,_cbwittbeleuchtung,_etnotizen);
 }else {
 //BA.debugLineNum = 1256;BA.debugLine="DBUtils.SetBVFFelder(cbBesonderhEinsteigen, cbEi";
mostCurrent._dbutils._setbvffelder /*String*/ (_ba,_cbbesonderheinsteigen,_cbeinstellen,_cblenkrad,_cbspiegel,_cbkopfstuetze,_cbsitz,_cblenkradhaltung,_cbpedale,_cbgurt,_cbschaltwaehlhebel,_cbzuendschloss,_cbmotoranlassen,_cbanfahranhalte,_cbschaltuebg,_cbhoch1_2,_cbhoch2_3,_cbhoch3_4,_cbrunter4_3,_cbrunter3_2,_cbrunter2_1,_cbrunter4_2,_cbrunter4_1,_cbrunter3_1,_cblenkuebung,_cbumkehren,_cbeinparkenlaengs,_cblvorwaertsrechts,_cblrueckwaertslinks,_cblrueckwaertsrechts,_cblvorwaertslinks,_cbrueckwaertsfahren,_cbeinparkenquer,_cbqvorwaertsrechts,_cbqrueckwaertslinks,_cbqrueckwaertsrechts,_cbqvorwaertslinks,_cbgefahrbremsung,_cbrollenschalten,_cbbremsschalten,_cbbremsuebung,_cbdegressiv,_cbzielbremsung,_cbgefahrsituation,_cbgefaelle,_cbanhalten,_cbanfahren,_cbrueckwaerts,_cbsichern,_cbschalten,_cbsteigung,_cbstanhalten,_cbstanfahren,_cbstrueckwaerts,_cbstsichern,_cbstschalten,_cbtastgeschw,_cbbedienkontroll,_cboertlichbesonder,_cbfahrbahnbenutzung,_cbeinordnen,_cbmarkierungen,_cbfahrstreifenwechsel,_cblinks,_cbrechts,_cbvorbeifueberholen,_cbabbiegen,_cbabrechts,_cbablinks,_cbmehrspurig,_cbradweg,_cbsonderstreifen,_cbstrassenbahn,_cbeinbahnstrasse,_cbvorfahrt,_cbrechtsvorlinks,_cbgruenpfeil,_cbpolizeibeamte,_cbgruenpfeilschild,_cbgeschwabstand,_cbsituationverkehrstn,_cbfussgaengerueberweg,_cboeffentlverkehrsm,_cbaelterebehinderte,_cbeinbahnstrradfahrer,_cbkinder,_cbschulbus,_cbradfahrermofa,_cbverkehrsberuhigt,_cbschwierigeverkehrsf,_cbengpass,_cbkreisverkehr,_cbbahnuebergangwarte,_cbkritischeverkehrss,_cbhauptverkehrszt,_cbpartnerverhalten,_cbschwungnutzen,_cbfussgaengerschutzb,_cbangepasstegeschw,_cbabstand,_cbulvorne,_cbulhinten,_cbulseitlich,_cbbeobachtspiegel,_cbverkehrszeichen,_cbkreuzungeinmuend,_cbkurven,_cbsteigungen,_cbulgefaelle,_cballeen,_cbueberholen,_cbbesonderesituat,_cbliegenblsichern,_cbeinfahrenortsch,_cbfussgaenger,_cbwildtiere,_cbbesondereanford,_cbleistungsgrenze,_cborientierung,_cbablenkung,_cbfahrtplanung,_cbeinfahrtab,_cbabfahrbahnwechsel,_cbgeschwindigkeit,_cbababstand,_cbabvorne,_cbabhinten,_cbabseitlich,_cbabueberholen,_cbschilder,_cbvorbeifahren,_cbrastparktank,_cbverhunfall,_cbdichterverkehr,_cbbesondersituat,_cbbesonderanford,_cbableistungsgrenze,_cbkonfliktsitua,_cbabablenkung,_cbbeleuchtung,_cbkontrolle,_cbeinstell,_cbbenutzung,_cbfernlicht,_cbverlassenbab,_cbbeleuchtstrasse,_cbunbeleuchtstrasse,_cbparken,_cbdubesondersituat,_cbschlechtewitterung,_cbtiere,_cbbahnuebergaenge,_cbunbelverkehrtn,_cbdubesonderanfor,_cbblendung,_cbduorientierung,_cbabschlussbesp,_cbselbstfahren,_cbinnerorts,_cbausserorts,_cbverantwfahren,_cbtestfpruef,_cbfakt,_cbandere,_cbwiederholung,_cbleistungsbew,_cbreifen,_cbeinausschalten,_cbfunktionpruefen,_cbstandlicht,_cbnebelschluss,_cbblinker,_cbabblendlicht,_cbwarnblicke,_cbhupe,_cbbsfernlicht,_cbschlussleuchte,_cbbremslicht,_cbkontrolllbenenn,_cbrueckstrahler,_cbvorhandensein,_cbbeschaedigung,_cblenkung,_cblenkschlentriegeln,_cbprueflenkspiel,_cbfunktbremse,_cbbetriebsbremse,_cbfeststellbremse,_cbanlegengurt,_cbrichtigsitz,_cbeinstellrueckspiegel,_cbeinkopfstuetze,_cbeinlenkrad,_cbbedienenagg,_cbheizung,_cbheckheizung,_cbbehsonderaus,_cblueftung,_cbklimaanlage,_cbenergienutzung,_cbkeineunnverbr,_cbrechtztabsch,_cbmotorraum,_cbmotoroel,_cbkuehlmittel,_cbscheibenwaschm,_cbtanken,_cbbremsen,_cbsicherungsmittel,_cbwarndreieck,_cbbordwerkzeug,_cbzusaetzlichaus,_cbverbandskasten,_cbaussenkontrolle,_cbscheibenwischer,_cbkennzeichen,_cbcheckspiegel,_cbcheckbeleuchtung,_cbladung,_cbladungssicherung,_cbkenntlichmachung,_cbfahreschlwitt,_cbwittlueftung,_cbwittscheiben,_cbregen,_cbwasserlachen,_cbwindsturm,_cbmatchschnee,_cbeis,_cbwittbeleuchtung,_etnotizen,_ibvfdaten);
 };
 //BA.debugLineNum = 1277;BA.debugLine="End Sub";
return "";
}
public static String  _setfixenfahrlehrerfahrzeug(anywheresoftware.b4a.BA _ba) throws Exception{
String[] _afahrdata = null;
 //BA.debugLineNum = 9;BA.debugLine="Sub SetFixenFahrlehrerFahrzeug";
 //BA.debugLineNum = 10;BA.debugLine="If File.Exists(Main.SourceFolder, \"FaData.INI\") T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._main._sourcefolder /*String*/ ,"FaData.INI")) { 
 //BA.debugLineNum = 12;BA.debugLine="Dim aFahrData(4) As String";
_afahrdata = new String[(int) (4)];
java.util.Arrays.fill(_afahrdata,"");
 //BA.debugLineNum = 13;BA.debugLine="aFahrData = ReadWriteINI.ReadConfigFahrData()";
_afahrdata = mostCurrent._readwriteini._readconfigfahrdata /*String[]*/ (_ba);
 //BA.debugLineNum = 15;BA.debugLine="DBUtils.CheckSetFixenFahrlehrerFahrzeug(aFahrDat";
mostCurrent._dbutils._checksetfixenfahrlehrerfahrzeug /*String*/ (_ba,_afahrdata);
 }else {
 //BA.debugLineNum = 17;BA.debugLine="DBUtils.SetFixenFahrlehrerFahrzeug";
mostCurrent._dbutils._setfixenfahrlehrerfahrzeug /*String*/ (_ba);
 };
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _setkonfig(anywheresoftware.b4a.BA _ba,boolean _checked,String _cb) throws Exception{
String _skonfig = "";
String _ssession = "";
String _skey = "";
 //BA.debugLineNum = 812;BA.debugLine="Sub SetKonfig(Checked As Boolean, cb As String)";
 //BA.debugLineNum = 813;BA.debugLine="Dim sKonfig, sSession, sKey As String";
_skonfig = "";
_ssession = "";
_skey = "";
 //BA.debugLineNum = 814;BA.debugLine="If cb = \"cbBegleitfahrzueg\" Then";
if ((_cb).equals("cbBegleitfahrzueg")) { 
 //BA.debugLineNum = 815;BA.debugLine="sKonfig = \"Begleitfahrzeug_IsSelected\"";
_skonfig = "Begleitfahrzeug_IsSelected";
 //BA.debugLineNum = 816;BA.debugLine="sSession = \"Begleitfahrzeug\"";
_ssession = "Begleitfahrzeug";
 //BA.debugLineNum = 817;BA.debugLine="sKey = \"anzeigen\"";
_skey = "anzeigen";
 }else if((_cb).equals("cbPruefungsErgebnis")) { 
 //BA.debugLineNum = 819;BA.debugLine="sKonfig = \"Pruefungsergebnis_IsSelected\"";
_skonfig = "Pruefungsergebnis_IsSelected";
 //BA.debugLineNum = 820;BA.debugLine="sSession = \"Pruefungsergebnis\"";
_ssession = "Pruefungsergebnis";
 //BA.debugLineNum = 821;BA.debugLine="sKey = \"erfassen\"";
_skey = "erfassen";
 };
 //BA.debugLineNum = 823;BA.debugLine="DBUtils.SetBegleitfahrzeug(Checked, sKonfig)";
mostCurrent._dbutils._setbegleitfahrzeug /*String*/ (_ba,_checked,_skonfig);
 //BA.debugLineNum = 824;BA.debugLine="ReadWriteINI.UpdateINI(sSession, sKey, Checked)";
mostCurrent._readwriteini._updateini /*String*/ (_ba,_ssession,_skey,BA.ObjectToString(_checked));
 //BA.debugLineNum = 825;BA.debugLine="End Sub";
return "";
}
public static String  _setlabellayout(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile,anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz) throws Exception{
 //BA.debugLineNum = 881;BA.debugLine="Sub SetLabelLayout(lblAusbildungListeZeile As Labe";
 //BA.debugLineNum = 882;BA.debugLine="lblAusbildungListeZeile.TextColor = Colors.Black";
_lblausbildunglistezeile.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 883;BA.debugLine="lblAusbildungListeZeile.Color = Colors.RGB(173, 2";
_lblausbildunglistezeile.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (173),(int) (216),(int) (230)));
 //BA.debugLineNum = 884;BA.debugLine="lblAusbildungListeZusatz.TextColor = Colors.Black";
_lblausbildunglistezusatz.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 885;BA.debugLine="lblAusbildungListeZusatz.Color = Colors.RGB(173,";
_lblausbildunglistezusatz.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (173),(int) (216),(int) (230)));
 //BA.debugLineNum = 886;BA.debugLine="End Sub";
return "";
}
public static boolean  _sonstigetaetigkeitenspeichern(anywheresoftware.b4a.BA _ba,int _iterminid,String _stermin,String _sstartzeit,String _sdauer,String _sfahrbezeichnung,String _sfahrlehrer,boolean _bchecksignature) throws Exception{
boolean _bresult = false;
 //BA.debugLineNum = 717;BA.debugLine="Sub SonstigeTaetigkeitenSpeichern(iTerminID As Int";
 //BA.debugLineNum = 718;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 720;BA.debugLine="If Main.bDatenVorhanden Then";
if (mostCurrent._main._bdatenvorhanden /*boolean*/ ) { 
 //BA.debugLineNum = 721;BA.debugLine="bResult = DBUtils.SonstigeTaetigkeitenUpdate(iTe";
_bresult = mostCurrent._dbutils._sonstigetaetigkeitenupdate /*boolean*/ (_ba,_iterminid,_stermin,_sstartzeit,_sdauer,_sfahrbezeichnung,_sfahrlehrer,_bchecksignature);
 }else {
 //BA.debugLineNum = 723;BA.debugLine="bResult = DBUtils.SonstigeTaetigkeitenInsert(sTe";
_bresult = mostCurrent._dbutils._sonstigetaetigkeiteninsert /*boolean*/ (_ba,_stermin,_sstartzeit,_sdauer,_sfahrbezeichnung,_sfahrlehrer,_bchecksignature);
 };
 //BA.debugLineNum = 726;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 727;BA.debugLine="End Sub";
return false;
}
public static String  _space(anywheresoftware.b4a.BA _ba,int _ianzahl) throws Exception{
String _sreturn = "";
 //BA.debugLineNum = 1581;BA.debugLine="Sub Space(iAnzahl As Int) As String";
 //BA.debugLineNum = 1582;BA.debugLine="Dim sReturn As String";
_sreturn = "";
 //BA.debugLineNum = 1583;BA.debugLine="sReturn = \"\"";
_sreturn = "";
 //BA.debugLineNum = 1584;BA.debugLine="Do While sReturn.Length < iAnzahl";
while (_sreturn.length()<_ianzahl) {
 //BA.debugLineNum = 1585;BA.debugLine="sReturn = sReturn & \" \"";
_sreturn = _sreturn+" ";
 }
;
 //BA.debugLineNum = 1587;BA.debugLine="Return sReturn";
if (true) return _sreturn;
 //BA.debugLineNum = 1588;BA.debugLine="End Sub";
return "";
}
public static String  _stammdatenbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatentelefon,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenhandy,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenarbeitsstelle,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatentelefonarbeit,anywheresoftware.b4a.objects.LabelWrapper _lblstammdaten1,anywheresoftware.b4a.objects.LabelWrapper _lblstammdaten2,anywheresoftware.b4a.objects.LabelWrapper _lblklasse1,anywheresoftware.b4a.objects.LabelWrapper _lblklasse2,anywheresoftware.b4a.objects.LabelWrapper _lblklasse3,anywheresoftware.b4a.objects.LabelWrapper _lblklasse4,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenklassen,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenhatklasse,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenemail) throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub StammdatenBeschriftung(lblStammdatenTelefon As";
 //BA.debugLineNum = 125;BA.debugLine="lblStammdatenTelefon.Text = \"Telefon\"";
_lblstammdatentelefon.setText(BA.ObjectToCharSequence("Telefon"));
 //BA.debugLineNum = 126;BA.debugLine="lblStammdatenHandy.Text = \"Handy\"";
_lblstammdatenhandy.setText(BA.ObjectToCharSequence("Handy"));
 //BA.debugLineNum = 127;BA.debugLine="lblStammdatenArbeitsstelle.Text = \"Arbeitsstelle\"";
_lblstammdatenarbeitsstelle.setText(BA.ObjectToCharSequence("Arbeitsstelle"));
 //BA.debugLineNum = 128;BA.debugLine="lblStammdatenTelefonArbeit.Text = \"Arbeitsstelle";
_lblstammdatentelefonarbeit.setText(BA.ObjectToCharSequence("Arbeitsstelle Telefon"));
 //BA.debugLineNum = 129;BA.debugLine="lblStammdatenKlassen.Text = \"macht Klasse:\"";
_lblstammdatenklassen.setText(BA.ObjectToCharSequence("macht Klasse:"));
 //BA.debugLineNum = 130;BA.debugLine="lblStammdatenHatKlasse.Text = \"hat Klasse:\"";
_lblstammdatenhatklasse.setText(BA.ObjectToCharSequence("hat Klasse:"));
 //BA.debugLineNum = 131;BA.debugLine="lblStammdatenEmail.Text = \"Email\"";
_lblstammdatenemail.setText(BA.ObjectToCharSequence("Email"));
 //BA.debugLineNum = 133;BA.debugLine="lblStammdaten1.Text = \"A\"";
_lblstammdaten1.setText(BA.ObjectToCharSequence("A"));
 //BA.debugLineNum = 134;BA.debugLine="lblStammdaten2.Text = \"B\"";
_lblstammdaten2.setText(BA.ObjectToCharSequence("B"));
 //BA.debugLineNum = 135;BA.debugLine="lblKlasse1.Text = \"1\"";
_lblklasse1.setText(BA.ObjectToCharSequence("1"));
 //BA.debugLineNum = 136;BA.debugLine="lblKlasse2.Text = \"2\"";
_lblklasse2.setText(BA.ObjectToCharSequence("2"));
 //BA.debugLineNum = 137;BA.debugLine="lblKlasse3.Text = \"3\"";
_lblklasse3.setText(BA.ObjectToCharSequence("3"));
 //BA.debugLineNum = 138;BA.debugLine="lblKlasse4.Text = \"4\"";
_lblklasse4.setText(BA.ObjectToCharSequence("4"));
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public static String  _stammdatenbeschriftung1(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenkontostand,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenantrageingereicht,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenantragzurueck,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatengeburtstag,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatennationalitaet,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenanmeldung,anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenschulefiliale) throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub StammdatenBeschriftung1(lblStammdatenKontostan";
 //BA.debugLineNum = 143;BA.debugLine="lblStammdatenKontostand.Text = \"Kontostand\"";
_lblstammdatenkontostand.setText(BA.ObjectToCharSequence("Kontostand"));
 //BA.debugLineNum = 144;BA.debugLine="lblStammdatenAntragEingereicht.Text = \"Antrag ei";
_lblstammdatenantrageingereicht.setText(BA.ObjectToCharSequence("Antrag eingereicht"));
 //BA.debugLineNum = 145;BA.debugLine="lblStammdatenAntragZurueck.Text = \"Antrag zurück";
_lblstammdatenantragzurueck.setText(BA.ObjectToCharSequence("Antrag zurück J/N"));
 //BA.debugLineNum = 146;BA.debugLine="lblStammdatenGeburtstag.Text = \"Geboren am\"";
_lblstammdatengeburtstag.setText(BA.ObjectToCharSequence("Geboren am"));
 //BA.debugLineNum = 147;BA.debugLine="lblStammdatenNationalitaet.Text = \"Nationalität\"";
_lblstammdatennationalitaet.setText(BA.ObjectToCharSequence("Nationalität"));
 //BA.debugLineNum = 148;BA.debugLine="lblStammdatenAnmeldung.Text = \"Anmeldung am\"";
_lblstammdatenanmeldung.setText(BA.ObjectToCharSequence("Anmeldung am"));
 //BA.debugLineNum = 149;BA.debugLine="lblStammdatenSchuleFiliale.Text = \"Schule / Fili";
_lblstammdatenschulefiliale.setText(BA.ObjectToCharSequence("Schule / Filiale"));
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _stammdatenbeschriftungklasse(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblklassegrundstoff,anywheresoftware.b4a.objects.LabelWrapper _lblklassedoppelstunde,anywheresoftware.b4a.objects.LabelWrapper _lblklassespezifisch,anywheresoftware.b4a.objects.LabelWrapper _lblklasseuebungsfahrt,anywheresoftware.b4a.objects.LabelWrapper _lblklasseuebunggrund,anywheresoftware.b4a.objects.LabelWrapper _lblklasseueberland,anywheresoftware.b4a.objects.LabelWrapper _lblklasseautobahn,anywheresoftware.b4a.objects.LabelWrapper _lblklassenachtfaht,anywheresoftware.b4a.objects.LabelWrapper _lblklasseunterweisungfhrzg,anywheresoftware.b4a.objects.LabelWrapper _lblklassetheorpruefung,anywheresoftware.b4a.objects.LabelWrapper _lblklassepraktischepruefung,anywheresoftware.b4a.objects.LabelWrapper _lblklassedoppelstundetext,anywheresoftware.b4a.objects.LabelWrapper _lblstdje45min) throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub StammdatenBeschriftungKlasse(lblKlasseGrundsto";
 //BA.debugLineNum = 156;BA.debugLine="lblKlasseGrundstoff.Text = \"Theor. Grundstoff:\"";
_lblklassegrundstoff.setText(BA.ObjectToCharSequence("Theor. Grundstoff:"));
 //BA.debugLineNum = 157;BA.debugLine="lblKlasseDoppelstunde.Text = \"Doppelstunde\"";
_lblklassedoppelstunde.setText(BA.ObjectToCharSequence("Doppelstunde"));
 //BA.debugLineNum = 159;BA.debugLine="lblKlasseSpezifisch.Text = \"Kla-spez Unterr\"";
_lblklassespezifisch.setText(BA.ObjectToCharSequence("Kla-spez Unterr"));
 //BA.debugLineNum = 160;BA.debugLine="lblKlasseUebungsfahrt.Text = \"Übungsfahrt\"";
_lblklasseuebungsfahrt.setText(BA.ObjectToCharSequence("Übungsfahrt"));
 //BA.debugLineNum = 161;BA.debugLine="lblKlasseUebungGrund.Text = \"Übf+Grf-Übg\"";
_lblklasseuebunggrund.setText(BA.ObjectToCharSequence("Übf+Grf-Übg"));
 //BA.debugLineNum = 162;BA.debugLine="lblKlasseUeberland.Text = \"Überlandfahrt\"";
_lblklasseueberland.setText(BA.ObjectToCharSequence("Überlandfahrt"));
 //BA.debugLineNum = 163;BA.debugLine="lblKlasseAutobahn.Text = \"Autobahnfahrt\"";
_lblklasseautobahn.setText(BA.ObjectToCharSequence("Autobahnfahrt"));
 //BA.debugLineNum = 164;BA.debugLine="lblKlasseNachtfaht.Text = \"Nachtfahrt\"";
_lblklassenachtfaht.setText(BA.ObjectToCharSequence("Nachtfahrt"));
 //BA.debugLineNum = 165;BA.debugLine="lblKlasseUnterweisungFhrzg.Text = \"Unterwe.a.Fzg\"";
_lblklasseunterweisungfhrzg.setText(BA.ObjectToCharSequence("Unterwe.a.Fzg"));
 //BA.debugLineNum = 166;BA.debugLine="lblKlasseTheorPruefung.Text = \"Theor. Prüfung\"";
_lblklassetheorpruefung.setText(BA.ObjectToCharSequence("Theor. Prüfung"));
 //BA.debugLineNum = 167;BA.debugLine="lblKlassePraktischePruefung.Text = \"Prakt. Prüfun";
_lblklassepraktischepruefung.setText(BA.ObjectToCharSequence("Prakt. Prüfung"));
 //BA.debugLineNum = 168;BA.debugLine="lblKlasseDoppelstundeText.Text = \"Doppelstunde\"";
_lblklassedoppelstundetext.setText(BA.ObjectToCharSequence("Doppelstunde"));
 //BA.debugLineNum = 169;BA.debugLine="lblStdJe45Min.Text = \"Std je 45 min\"";
_lblstdje45min.setText(BA.ObjectToCharSequence("Std je 45 min"));
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String[]  _stammdatenholen(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 790;BA.debugLine="Sub StammdatenHolen As String()";
 //BA.debugLineNum = 791;BA.debugLine="Return DBUtils.StammdatenHolen";
if (true) return mostCurrent._dbutils._stammdatenholen /*String[]*/ (_ba);
 //BA.debugLineNum = 792;BA.debugLine="End Sub";
return null;
}
public static String[]  _stammdatenklassendatenholen(anywheresoftware.b4a.BA _ba,int _izahl) throws Exception{
String[] _aklassendaten = null;
int _i = 0;
 //BA.debugLineNum = 795;BA.debugLine="Sub StammdatenKlassendatenHolen(iZahl As Int) As S";
 //BA.debugLineNum = 796;BA.debugLine="Dim aKlassendaten() As String";
_aklassendaten = new String[(int) (0)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 797;BA.debugLine="aKlassendaten = DBUtils.StammdatenKlassendatenHol";
_aklassendaten = mostCurrent._dbutils._stammdatenklassendatenholen /*String[]*/ (_ba,_izahl);
 //BA.debugLineNum = 800;BA.debugLine="For i = 0 To aKlassendaten.Length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_aklassendaten.length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 801;BA.debugLine="If i <> 2 And i <> 3 And i < 10 Then";
if (_i!=2 && _i!=3 && _i<10) { 
 //BA.debugLineNum = 802;BA.debugLine="If aKlassendaten(i) = 0 Or aKlassendaten(i) = \"";
if ((_aklassendaten[_i]).equals(BA.NumberToString(0)) || (_aklassendaten[_i]).equals("")) { 
 //BA.debugLineNum = 803;BA.debugLine="aKlassendaten(i) = \"0,0\"";
_aklassendaten[_i] = "0,0";
 };
 };
 }
};
 //BA.debugLineNum = 808;BA.debugLine="Return aKlassendaten";
if (true) return _aklassendaten;
 //BA.debugLineNum = 809;BA.debugLine="End Sub";
return null;
}
public static String  _startbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _btkalender,anywheresoftware.b4a.objects.ButtonWrapper _bttagvor,anywheresoftware.b4a.objects.ButtonWrapper _bttagzurueck,anywheresoftware.b4a.objects.LabelWrapper _lblfahrlehrer,anywheresoftware.b4a.objects.LabelWrapper _lblschueler,anywheresoftware.b4a.objects.LabelWrapper _lblfahrstd,anywheresoftware.b4a.objects.LabelWrapper _lblgesamtstd) throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub StartBeschriftung(btKalender As Button, btTagV";
 //BA.debugLineNum = 39;BA.debugLine="btKalender.Text = DateTime.Date(DateTime.Now)";
_btkalender.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 40;BA.debugLine="btTagVor.Text = \">\"";
_bttagvor.setText(BA.ObjectToCharSequence(">"));
 //BA.debugLineNum = 41;BA.debugLine="btTagZurueck.Text = \"<\"";
_bttagzurueck.setText(BA.ObjectToCharSequence("<"));
 //BA.debugLineNum = 43;BA.debugLine="lblFahrlehrer.Text = \"Fahrlehrer\"";
_lblfahrlehrer.setText(BA.ObjectToCharSequence("Fahrlehrer"));
 //BA.debugLineNum = 44;BA.debugLine="lblSchueler.Text = \"Schüler\"";
_lblschueler.setText(BA.ObjectToCharSequence("Schüler"));
 //BA.debugLineNum = 45;BA.debugLine="lblFahrStd.Text = \"Fa-Std:\"";
_lblfahrstd.setText(BA.ObjectToCharSequence("Fa-Std:"));
 //BA.debugLineNum = 46;BA.debugLine="lblGesamtStd.Text = \"Gesamt:\"";
_lblgesamtstd.setText(BA.ObjectToCharSequence("Gesamt:"));
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static boolean  _startzeitpuefen(anywheresoftware.b4a.BA _ba,int _iterminid,String _sdatum,String _sstartzeit,String _sdauer) throws Exception{
 //BA.debugLineNum = 1115;BA.debugLine="Sub StartzeitPuefen(iTerminID As Int, sDatum As St";
 //BA.debugLineNum = 1116;BA.debugLine="Return DBUtils.StartzeitPuefen(iTerminID, sDatum,";
if (true) return mostCurrent._dbutils._startzeitpuefen /*boolean*/ (_ba,_iterminid,_sdatum,_sstartzeit,_sdauer);
 //BA.debugLineNum = 1117;BA.debugLine="End Sub";
return false;
}
public static int  _terminidholen(anywheresoftware.b4a.BA _ba,String _stermin,String _sstartzeit,String _sdauer,String _sklasse,String _sfahrbezeichnung,String _streffpunkt) throws Exception{
int _iterminid = 0;
 //BA.debugLineNum = 670;BA.debugLine="Sub TerminIDHolen(sTermin As String, sStartzeit As";
 //BA.debugLineNum = 671;BA.debugLine="Dim iTerminID As Int";
_iterminid = 0;
 //BA.debugLineNum = 672;BA.debugLine="If (sKlasse = \"\" And sTreffpunkt = \"\") Then";
if (((_sklasse).equals("") && (_streffpunkt).equals(""))) { 
 //BA.debugLineNum = 674;BA.debugLine="iTerminID = DBUtils.SonstTaetigkeitenIDHolen(sTe";
_iterminid = mostCurrent._dbutils._sonsttaetigkeitenidholen /*int*/ (_ba,_stermin,_sstartzeit,_sdauer,_sfahrbezeichnung);
 //BA.debugLineNum = 675;BA.debugLine="If iTerminID > 0 Then";
if (_iterminid>0) { 
 //BA.debugLineNum = 676;BA.debugLine="Main.bSonstTaetigkeit = True";
mostCurrent._main._bsonsttaetigkeit /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 678;BA.debugLine="Main.bSonstTaetigkeit = False";
mostCurrent._main._bsonsttaetigkeit /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 682;BA.debugLine="iTerminID = DBUtils.TerminIDHolen(sTermin, sStar";
_iterminid = mostCurrent._dbutils._terminidholen /*int*/ (_ba,_stermin,_sstartzeit,_sdauer,_sklasse,_sfahrbezeichnung,_streffpunkt);
 };
 //BA.debugLineNum = 684;BA.debugLine="Return iTerminID";
if (true) return _iterminid;
 //BA.debugLineNum = 685;BA.debugLine="End Sub";
return 0;
}
public static boolean  _terminloeschen(anywheresoftware.b4a.BA _ba,int _iposition,String _sdatum) throws Exception{
boolean _result = false;
 //BA.debugLineNum = 688;BA.debugLine="Sub TerminLoeschen(iPosition As Int, sDatum As Str";
 //BA.debugLineNum = 689;BA.debugLine="Dim result As Boolean";
_result = false;
 //BA.debugLineNum = 690;BA.debugLine="result = DBUtils.TerminLoeschen(iPosition, sDatum";
_result = mostCurrent._dbutils._terminloeschen /*boolean*/ (_ba,_iposition,_sdatum);
 //BA.debugLineNum = 692;BA.debugLine="Return result";
if (true) return _result;
 //BA.debugLineNum = 693;BA.debugLine="End Sub";
return false;
}
public static boolean  _terminloeschenwenndoppeltgespeichert(anywheresoftware.b4a.BA _ba,int _iterminid) throws Exception{
boolean _result = false;
 //BA.debugLineNum = 696;BA.debugLine="Sub TerminLoeschenWennDoppeltGespeichert(iTerminID";
 //BA.debugLineNum = 697;BA.debugLine="Dim result As Boolean";
_result = false;
 //BA.debugLineNum = 698;BA.debugLine="result = DBUtils.TerminLoeschenWennDoppeltGespeic";
_result = mostCurrent._dbutils._terminloeschenwenndoppeltgespeichert /*boolean*/ (_ba,_iterminid);
 //BA.debugLineNum = 700;BA.debugLine="Return result";
if (true) return _result;
 //BA.debugLineNum = 701;BA.debugLine="End Sub";
return false;
}
public static String  _unterschriftbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _btspeichern,anywheresoftware.b4a.objects.ButtonWrapper _btabbruch,anywheresoftware.b4a.objects.ButtonWrapper _btloeschen) throws Exception{
 //BA.debugLineNum = 547;BA.debugLine="Sub UnterschriftBeschriftung(btSpeichern As Button";
 //BA.debugLineNum = 548;BA.debugLine="btSpeichern.Text = \"Speichern\"";
_btspeichern.setText(BA.ObjectToCharSequence("Speichern"));
 //BA.debugLineNum = 549;BA.debugLine="btAbbruch.Text = \"Abbruch\"";
_btabbruch.setText(BA.ObjectToCharSequence("Abbruch"));
 //BA.debugLineNum = 550;BA.debugLine="btLoeschen.Text = \"X\"";
_btloeschen.setText(BA.ObjectToCharSequence("X"));
 //BA.debugLineNum = 551;BA.debugLine="End Sub";
return "";
}
public static boolean  _unterschriftspeichernneu(anywheresoftware.b4a.BA _ba,int _iterminid,String _sfahrtbez,String _sklassen,String _sfahrzeug,String _sbegleitfahzg,String _sdatum,String _suhrzeit,String _smenge,String _sfahrtkurzbezeichnung,String _sfahrlehrer) throws Exception{
boolean _bresult = false;
String _sverschluesselung = "";
int _ifahrtkurzbz = 0;
int _iklasse = 0;
int _ischuelerid = 0;
int _ifahrlehrerid = 0;
 //BA.debugLineNum = 1542;BA.debugLine="Sub UnterschriftSpeichernNeu(iTerminID As Int, sFa";
 //BA.debugLineNum = 1544;BA.debugLine="Dim bResult As Boolean";
_bresult = false;
 //BA.debugLineNum = 1546;BA.debugLine="Dim sVerschluesselung As String";
_sverschluesselung = "";
 //BA.debugLineNum = 1548;BA.debugLine="Dim iFahrtKurzBz As Int";
_ifahrtkurzbz = 0;
 //BA.debugLineNum = 1549;BA.debugLine="iFahrtKurzBz = DBUtils.FahrtbezeichnungsID(sFahrt";
_ifahrtkurzbz = mostCurrent._dbutils._fahrtbezeichnungsid /*int*/ (_ba,_sfahrtbez);
 //BA.debugLineNum = 1552;BA.debugLine="Dim iKlasse As Int";
_iklasse = 0;
 //BA.debugLineNum = 1553;BA.debugLine="iKlasse = DBUtils.KlasseID(sKlassen)";
_iklasse = mostCurrent._dbutils._klasseid /*int*/ (_ba,_sklassen);
 //BA.debugLineNum = 1561;BA.debugLine="Dim iSchuelerID As Int";
_ischuelerid = 0;
 //BA.debugLineNum = 1562;BA.debugLine="iSchuelerID = DBUtils.GetSchuelerID(iTerminID)";
_ischuelerid = mostCurrent._dbutils._getschuelerid /*int*/ (_ba,_iterminid);
 //BA.debugLineNum = 1565;BA.debugLine="Dim iFahrlehrerID As Int";
_ifahrlehrerid = 0;
 //BA.debugLineNum = 1566;BA.debugLine="iFahrlehrerID = DBUtils.GetFahrlehrerID(sFahrlehr";
_ifahrlehrerid = mostCurrent._dbutils._getfahrlehrerid /*int*/ (_ba,_sfahrlehrer);
 //BA.debugLineNum = 1571;BA.debugLine="If DBUtils.SaveSignature(\" \", iTerminID, iSchuele";
if (mostCurrent._dbutils._savesignature /*boolean*/ (_ba," ",_iterminid,_ischuelerid)) { 
 //BA.debugLineNum = 1572;BA.debugLine="bResult = True";
_bresult = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1574;BA.debugLine="bResult = False";
_bresult = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1577;BA.debugLine="Return bResult";
if (true) return _bresult;
 //BA.debugLineNum = 1578;BA.debugLine="End Sub";
return false;
}
public static String  _zaehlerklassenanzeige(anywheresoftware.b4a.BA _ba,int _izaehler,anywheresoftware.b4a.objects.LabelWrapper _lblklassenbezeichnung) throws Exception{
 //BA.debugLineNum = 543;BA.debugLine="Sub ZaehlerKlassenAnzeige(iZaehler As Int, lblKlas";
 //BA.debugLineNum = 544;BA.debugLine="lblKlassenbezeichnung.Text = iZaehler & \". Klasse";
_lblklassenbezeichnung.setText(BA.ObjectToCharSequence(BA.NumberToString(_izaehler)+". Klasse"));
 //BA.debugLineNum = 545;BA.debugLine="End Sub";
return "";
}
public static String  _zahlungenbeschriftung(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lblzahltag,anywheresoftware.b4a.objects.LabelWrapper _lblzahlalle,anywheresoftware.b4a.objects.LabelWrapper _lblzahlzahlungfuertext,anywheresoftware.b4a.objects.LabelWrapper _lblzahlzahlungstext,anywheresoftware.b4a.objects.LabelWrapper _lblzahlzeilebearbeiten,anywheresoftware.b4a.objects.LabelWrapper _lblzahlspeichern,anywheresoftware.b4a.objects.EditTextWrapper _txtzahlbetrag) throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Sub ZahlungenBeschriftung(lblZahlTag As Label, lbl";
 //BA.debugLineNum = 174;BA.debugLine="lblZahlTag.Text = \"Tag\"";
_lblzahltag.setText(BA.ObjectToCharSequence("Tag"));
 //BA.debugLineNum = 175;BA.debugLine="lblZahlAlle.Text = \"Alle\"";
_lblzahlalle.setText(BA.ObjectToCharSequence("Alle"));
 //BA.debugLineNum = 176;BA.debugLine="lblZahlZahlungFuerText.Text = \"Zahlung für\"";
_lblzahlzahlungfuertext.setText(BA.ObjectToCharSequence("Zahlung für"));
 //BA.debugLineNum = 177;BA.debugLine="lblZahlZahlungstext.Text = \"Zahlung\"";
_lblzahlzahlungstext.setText(BA.ObjectToCharSequence("Zahlung"));
 //BA.debugLineNum = 178;BA.debugLine="lblZahlZeileBearbeiten.Text = \"Zeile ändern\"";
_lblzahlzeilebearbeiten.setText(BA.ObjectToCharSequence("Zeile ändern"));
 //BA.debugLineNum = 179;BA.debugLine="lblZahlSpeichern.Text = \"Speichern\"";
_lblzahlspeichern.setText(BA.ObjectToCharSequence("Speichern"));
 //BA.debugLineNum = 181;BA.debugLine="txtZahlBetrag.Color = Colors.White";
_txtzahlbetrag.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static boolean  _zahlungenspeichern(anywheresoftware.b4a.BA _ba,String _sbetrag,String _sdatum,String _szahlungfuer) throws Exception{
 //BA.debugLineNum = 652;BA.debugLine="Sub ZahlungenSpeichern(sBetrag As String, sDatum A";
 //BA.debugLineNum = 653;BA.debugLine="Return DBUtils.ZahlungenSpeichern(sBetrag, sDatum";
if (true) return mostCurrent._dbutils._zahlungenspeichern /*boolean*/ (_ba,_sbetrag,_sdatum,_szahlungfuer);
 //BA.debugLineNum = 654;BA.debugLine="End Sub";
return false;
}
public static String[]  _zahlungholen(anywheresoftware.b4a.BA _ba,int _iposition,Object _ovalue,String _sauswahl) throws Exception{
 //BA.debugLineNum = 665;BA.debugLine="Sub ZahlungHolen(iPosition As Int, oValue As Objec";
 //BA.debugLineNum = 666;BA.debugLine="Return DBUtils.ZahlungPositionHolen(iPosition, oV";
if (true) return mostCurrent._dbutils._zahlungpositionholen /*String[]*/ (_ba,_iposition,_ovalue,_sauswahl);
 //BA.debugLineNum = 667;BA.debugLine="End Sub";
return null;
}
public static boolean  _zahlungloeschen(anywheresoftware.b4a.BA _ba,int _position,String _sauswahl) throws Exception{
 //BA.debugLineNum = 657;BA.debugLine="Sub ZahlungLoeschen(Position As Int, sAuswahl As S";
 //BA.debugLineNum = 658;BA.debugLine="If Main.bZahlDatum Then";
if (mostCurrent._main._bzahldatum /*boolean*/ ) { 
 //BA.debugLineNum = 659;BA.debugLine="sAuswahl = \"Alle\"";
_sauswahl = "Alle";
 };
 //BA.debugLineNum = 661;BA.debugLine="Return DBUtils.ZahlungLoeschen(Position, sAuswahl";
if (true) return mostCurrent._dbutils._zahlungloeschen /*boolean*/ (_ba,_position,_sauswahl);
 //BA.debugLineNum = 662;BA.debugLine="End Sub";
return false;
}
}
