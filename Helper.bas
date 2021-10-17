Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=3.5
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
End Sub

'erster Fahrlehrer muss als fixer gesetzt werden
Sub SetFixenFahrlehrerFahrzeug
	If File.Exists(Main.SourceFolder, "FaData.INI") Then
		'Prüfen, ob Fahrlehrer der INI-Datei in DB Tabelle vorhanden ist
		Dim aFahrData(4) As String
		aFahrData = ReadWriteINI.ReadConfigFahrData()
		
		DBUtils.CheckSetFixenFahrlehrerFahrzeug(aFahrData)
	Else
		DBUtils.SetFixenFahrlehrerFahrzeug
	End If
End Sub

'Fixieren des konfigurierten Fahrlehrers
Sub FahrlehrerFixieren(sFahrlehrer As String)
	If DBUtils.FahrlehrerFixieren(sFahrlehrer) Then
		Dim fahrID As String 
		fahrID = DBUtils.GetFahrlehrerID(sFahrlehrer)
		
		ReadWriteINI.UpdateFahrINI("Fahrlehrer", "Fahrlehrer", sFahrlehrer, fahrID)
	End If
End Sub

'Fixieren des Fahrschul-Farzeugs
Sub KFZFixieren(sKFZ As String)
	DBUtils.KFZFixieren(sKFZ)
End Sub

#Region Beschriftungen
'Beschriftung aller Felder im Start-Tab
Sub StartBeschriftung(btKalender As Button, btTagVor As Button, btTagZurueck As Button, lblFahrlehrer As Label, lblSchueler As Label, lblFahrStd As Label, lblGesamtStd As Label)
	btKalender.Text = DateTime.Date(DateTime.Now)
	btTagVor.Text = ">"
	btTagZurueck.Text = "<"
	
	lblFahrlehrer.Text = "Fahrlehrer"
	lblSchueler.Text = "Schüler"
	lblFahrStd.Text = "Fa-Std:"
	lblGesamtStd.Text = "Gesamt:"
End Sub

'Beschriftung aller Felder im Fahrdaten-Tab
Sub FahrdatenBeschriftung(lblAbfahrt As Label, lblEinheit As Label, lblKlasse As Label, lblAuswahlKlasseAlle As Label, _
		lblKfz As Label, lblFahrtbezeichnung As Label, lblStdPlus As Label, lblStdMinus As Label, _
		lblMinPlus As Label, lblMinMinus As Label, lblMin1Plus As Label, lblMin1Minus As Label, _
		lblEinheitPlus As Label, lblEinheitMinus As Label, lblAbfahrtAnzeige As Label, _
		lblEinheitAnzeige As Label, lblTreffpunkt As Label, lblBegleitfahrzeug As Label, _
		btVormerken As Button, btAbbruch As Button, btSpeichern As Button, btUnterschrift As Button, _
		lblPfeil As Label, lblEinheit5Plus As Label, lblEinheit5Minus As Label, lblEinheit1Plus As Label, _
		lblEinheit1Minus As Label, lblSonder As Label, lblPruefungsergebnisse As Label, _
		rbNichtErfassen As RadioButton, rbNichtBestanden As RadioButton, rbBestanden As RadioButton, _
		btPruefErgebnisOK As Button, txtAuswahlTreffpunkt As EditText)
	
	lblAbfahrt.Text = "Abfahrt (Std - Min)"
	lblEinheit.Text = "Menge in Min (15 - 5)"
	lblKlasse.Text = "Klasse"
	lblAuswahlKlasseAlle.Text = "alle Kl."
	lblKfz.Text = "KFZ"
	lblFahrtbezeichnung.Text = "Fahrtbez."
	lblBegleitfahrzeug.Text  = "Begleit-Kfz" 
	lblTreffpunkt.Text = "Treffpunkt"
	lblStdPlus.Text = "+"
	lblStdMinus.Text = "-"
	lblMinPlus.Text = "+" 
	lblMinMinus.Text = "-"
	lblMin1Plus.Text = "+"
	lblMin1Minus.Text = "-"
	lblEinheitPlus.Text = "+"
	lblEinheitMinus.Text = "-"
	lblEinheit5Plus.Text = "+"
	lblEinheit5Minus.Text = "-"
	lblEinheit1Plus.Text = "+"
	lblEinheit1Minus.text = "-"
	lblSonder.Text = "So. Ttk."
	
	DateTime.TimeFormat = "HH:mm"
	lblAbfahrtAnzeige.Text = DateTime.Time(DateTime.Now)
	lblEinheitAnzeige.Text = "45"
	
	btVormerken.Text = "Vormerken"
	btAbbruch.Text = "Abbruch"
	btSpeichern.Text = "Speichern"
	btUnterschrift.Text = "Unterschrift"
	
	lblPfeil.Text = "▼"
	
	lblPruefungsergebnisse.Text = "Prüfungsergebnis erfassen:"
	rbNichtErfassen.Text = " Nicht erfassen"
	rbNichtBestanden.Text = " Nicht bestanden"
	rbBestanden.Text = " Bestanden"
	btPruefErgebnisOK.Text = "OK"
	
	txtAuswahlTreffpunkt.Color = Colors.White
End Sub

Sub EinstellungBeschriftung(lblEinstFahrlehrer As Label, lblEinstKfz As Label, lblEinstFahrEinheit As Label, lblEinstellTreffpunkt As Label, btEinstellAktuelleListe As Button, _
		btEinstellSpeichern As Button, lblBegleitfahrzeugErfassen As Label, lblPruefungErgebnis As Label, btAppBeenden As Button, txtNeuerTreffpunkt As EditText, txtEinstAnzeigeFahrEinheit As EditText, lblBVFKontrolle As Label)
	lblEinstFahrlehrer.Text = "Fahrlehrer einstellen:"
	lblEinstKfz.Text = "Kfz einstellen:"
	lblEinstFahrEinheit.Text = "Grundzeit Fahreinheiten:"
	lblEinstellTreffpunkt.Text = "Neuer Treffpunkt / Bemerkung:"
	btEinstellAktuelleListe.Text = "Aktuelle Liste"
	btEinstellSpeichern.Text = "Hinzufügen"
	lblBegleitfahrzeugErfassen.Text = "Begleit-Fahrzeug erfassen.......J/N:"
	lblPruefungErgebnis.Text = "Bei Prüfung Ergebnisabfrage...J/N:"
	lblBVFKontrolle.Text = "Ausbildungsdiagrammkarten...J/N:"
	btAppBeenden.Text = "App für Transport beenden"
	
	txtNeuerTreffpunkt.Color = Colors.White
	txtEinstAnzeigeFahrEinheit.Color = Colors.White
End Sub

Sub StammdatenBeschriftung(lblStammdatenTelefon As Label, lblStammdatenHandy As Label, _
		lblStammdatenArbeitsstelle As Label, lblStammdatenTelefonArbeit As Label, lblStammdaten1 As Label, _
		lblStammdaten2 As Label, lblKlasse1 As Label, lblKlasse2 As Label, lblKlasse3 As Label, lblKlasse4 As Label, _
		lblStammdatenKlassen As Label, lblStammdatenHatKlasse As Label, lblStammdatenEmail As Label)
	'Felder bezeichnen 'Stammdaten'
	lblStammdatenTelefon.Text = "Telefon"
	lblStammdatenHandy.Text = "Handy"
	lblStammdatenArbeitsstelle.Text = "Arbeitsstelle"
	lblStammdatenTelefonArbeit.Text = "Arbeitsstelle Telefon"
	lblStammdatenKlassen.Text = "macht Klasse:" 
	lblStammdatenHatKlasse.Text = "hat Klasse:"
	lblStammdatenEmail.Text = "Email"
	
	lblStammdaten1.Text = "A"
	lblStammdaten2.Text = "B"
	lblKlasse1.Text = "1"
	lblKlasse2.Text = "2"
	lblKlasse3.Text = "3"
	lblKlasse4.Text = "4"
End Sub

Sub StammdatenBeschriftung1(lblStammdatenKontostand As Label, lblStammdatenAntragEingereicht As Label, lblStammdatenAntragZurueck As Label, _
		lblStammdatenGeburtstag As Label, lblStammdatenNationalitaet As Label, lblStammdatenAnmeldung As Label, lblStammdatenSchuleFiliale As Label)
	 lblStammdatenKontostand.Text = "Kontostand"
	 lblStammdatenAntragEingereicht.Text = "Antrag eingereicht"
	 lblStammdatenAntragZurueck.Text = "Antrag zurück J/N"
	 lblStammdatenGeburtstag.Text = "Geboren am"
	 lblStammdatenNationalitaet.Text = "Nationalität"
	 lblStammdatenAnmeldung.Text = "Anmeldung am"
	 lblStammdatenSchuleFiliale.Text = "Schule / Filiale"
End Sub

Sub StammdatenBeschriftungKlasse(lblKlasseGrundstoff As Label, lblKlasseDoppelstunde As Label, lblKlasseSpezifisch As Label, _
		lblKlasseUebungsfahrt As Label, lblKlasseUebungGrund As Label, lblKlasseUeberland As Label, lblKlasseAutobahn As Label, _
		lblKlasseNachtfaht As Label, lblKlasseUnterweisungFhrzg As Label, lblKlasseTheorPruefung As Label, lblKlassePraktischePruefung As Label, _
		lblKlasseDoppelstundeText As Label, lblStdJe45Min As Label)
	lblKlasseGrundstoff.Text = "Theor. Grundstoff:"
	lblKlasseDoppelstunde.Text = "Doppelstunde"
	'lblKlassenbezeichnung.Text = iZaehler & ". Klasse"
	lblKlasseSpezifisch.Text = "Kla-spez Unterr"
	lblKlasseUebungsfahrt.Text = "Übungsfahrt"
	lblKlasseUebungGrund.Text = "Übf+Grf-Übg"
	lblKlasseUeberland.Text = "Überlandfahrt"
	lblKlasseAutobahn.Text = "Autobahnfahrt"
	lblKlasseNachtfaht.Text = "Nachtfahrt"
	lblKlasseUnterweisungFhrzg.Text = "Unterwe.a.Fzg"
	lblKlasseTheorPruefung.Text = "Theor. Prüfung" 
	lblKlassePraktischePruefung.Text = "Prakt. Prüfung"
	lblKlasseDoppelstundeText.Text = "Doppelstunde"
	lblStdJe45Min.Text = "Std je 45 min"
End Sub

Sub ZahlungenBeschriftung(lblZahlTag As Label, lblZahlAlle As Label, lblZahlZahlungFuerText As Label, lblZahlZahlungstext As Label, _
	lblZahlZeileBearbeiten As Label, lblZahlSpeichern As Label, txtZahlBetrag As EditText)
	lblZahlTag.Text = "Tag"
	lblZahlAlle.Text = "Alle"
	lblZahlZahlungFuerText.Text = "Zahlung für"
	lblZahlZahlungstext.Text = "Zahlung"
	lblZahlZeileBearbeiten.Text = "Zeile ändern"
	lblZahlSpeichern.Text = "Speichern"
	
	txtZahlBetrag.Color = Colors.White
End Sub

Sub AusbildungBVFBeschriftung(lblGrundstufe As Label, lblAusbildungBVF0 As Label, lblAusbildungBVF1 As Label, lblAusbildungBVF2 As Label, lblAusbildungBVF3 As Label, lblAusbildungBVF4 As Label, _
		lblAusbildungBVF5 As Label, lblAusbildungBVF6 As Label)
	lblGrundstufe.Text = "Grundstufe"
	lblAusbildungBVF0.Text = "+"
	lblAusbildungBVF1.Text = "-"
	lblAusbildungBVF2.Text = "Notiz"
	lblAusbildungBVF3.Text = "Save"
End Sub

#region BVF Pan Beschriftungen
Sub AusbildungBVFPan0(cbBesonderhEinsteigen As CheckBox, cbEinstellen As CheckBox, cbLenkrad As CheckBox, cbSpiegel As CheckBox, cbKopfstuetze As CheckBox, cbSitz As CheckBox, _
		cbLenkradhaltung As CheckBox, cbPedale As CheckBox, cbGurt As CheckBox, cbSchaltWaehlhebel As CheckBox, cbZuendschloss As CheckBox, cbMotorAnlassen As CheckBox, _
		cbAnfahrAnhalte As CheckBox)
	cbBesonderhEinsteigen.Text = "Besonderheit beim Einsteigen"
	cbEinstellen.Text = "Einstellen"
	cbLenkrad.Text = "Lenkrad"
	cbSpiegel.Text = "Spiegel"
	cbKopfstuetze.Text = "Kopfstütze"
	cbSitz.Text = " Sitz"
	cbLenkradhaltung.Text = "Lenkradhaltung"
	cbPedale.Text = "Pedale"
	cbGurt.Text = "Gurt anlegen/anpassen"
	cbSchaltWaehlhebel.Text = "Schalt-/Wahlhebel"
	cbZuendschloss.Text = "Zündschloss"
	cbMotorAnlassen.Text = "Motor anlassen"
	cbAnfahrAnhalte.Text = "Anfahr- / Anhalteübungen"
End Sub

Sub AusbildungBVFPan1(cbSchaltuebg As CheckBox, cbHoch1_2 As CheckBox, cbHoch2_3 As CheckBox, cbHoch3_4 As CheckBox, cbRunter4_3 As CheckBox, cbRunter3_2 As CheckBox, cbRunter2_1 As CheckBox, _
		cbRunter4_2 As CheckBox, cbRunter4_1 As CheckBox, cbRunter3_1 As CheckBox, cbLenkuebung As CheckBox)
	cbSchaltuebg.Text = "Schaltübungen (umweltbewusst)"
	cbHoch1_2.Text = "hoch 1-2"
	cbHoch2_3.Text = "hoch 2-3"
	cbHoch3_4.Text = "hoch 3-4"
	cbRunter4_3.Text = "runter 4-3"
	cbRunter3_2.Text = "runter 3-2"
	cbRunter2_1.Text = "runter 2-1"
	cbRunter4_2.Text = "runter 4-2"
	cbRunter4_1.Text = "runter 4-1"
	cbRunter3_1.Text = "runter 3-1"
	cbLenkuebung.Text = "Lenkübungen"
End Sub

Sub AusbildungBVFPan2(cbUmkehren As CheckBox, cbEinparkenLaengs As CheckBox, cbLVorwaertsRechts As CheckBox, cbLRueckwaertsLinks As CheckBox, cbLRueckwaertsRechts As CheckBox, _
		cbLVorwaertsLinks As CheckBox, cbRueckwaertsfahren As CheckBox, cbEinparkenQuer As CheckBox, cbQVorwaertsRechts As CheckBox, cbQRueckwaertsLinks As CheckBox, _
		cbQRueckwaertsRechts As CheckBox, cbQVorwaertsLinks As CheckBox, cbGefahrbremsung As CheckBox)
	cbUmkehren.Text = "Umkehren"
	cbEinparkenLaengs.Text = "Einparken längs"
	cbLVorwaertsRechts.Text = "vorwärts rechts"
	cbLRueckwaertsLinks.Text = "rückwärts links"
	cbLRueckwaertsRechts.Text = "rückwärts rechts"
	cbLVorwaertsLinks.Text = "vorwärts links"
	cbRueckwaertsfahren.Text = "Rückwärtsfahren"
	cbEinparkenQuer.Text = "Einparken quer"
	cbQVorwaertsRechts.Text = "vorwärts rechts"
	cbQRueckwaertsLinks.Text = "rückwärts links"
	cbQRueckwaertsRechts.Text = "rückwärts rechts"
	cbQVorwaertsLinks.Text = "vorwärts links"
	cbGefahrbremsung.Text =" Gefahrbremsung"
End Sub

Sub AusbildungBVFPan3(cbRollenSchalten As CheckBox, cbBremsSchalten As CheckBox, cbBremsuebung As CheckBox, cbDegressiv As CheckBox, cbZielbremsung As CheckBox, cbGefahrsituation As CheckBox, _
		cbGefaelle As CheckBox, cbAnhalten As CheckBox, cbAnfahren As CheckBox, cbRueckwaerts As CheckBox, cbSichern As CheckBox, cbSchalten As CheckBox)
	cbRollenSchalten.Text = "Rollen und Schalten"
	cbBremsSchalten.Text = "Abbremsen und Schalten"
	cbBremsuebung.Text = "Bremsübungen"
	cbDegressiv.Text = "degressiv"
	cbZielbremsung.Text = "Zielbremsung"
	cbGefahrsituation.Text = "Gefahrsituation"
	cbGefaelle.Text = "Gefälle"
	cbAnhalten.Text ="Anhalten"
	cbAnfahren.Text ="Anfahren"
	cbRueckwaerts.Text = "Rückwärts"
	cbSichern.Text = "Sichern"
	cbSchalten.Text = "Schalten"
End Sub

Sub AusbildungBVFPan4(cbSteigung As CheckBox, cbStAnhalten As CheckBox, cbStAnfahren As CheckBox, cbStRueckwaerts As CheckBox, cbStSichern As CheckBox, cbStSchalten As CheckBox, _
		cbTastgeschw As CheckBox, cbBedienKontroll As CheckBox, cbOertlichBesonder As CheckBox)
	cbSteigung.Text = "Steigung"
	cbStAnhalten.Text = "Anhalten"
	cbStAnfahren.Text = "Anfahren"
	cbStRueckwaerts.Text = "Rüeckwärts"
	cbStSichern.Text = "Sichern"
	cbStSchalten.Text = "Schalten"
	cbTastgeschw.Text = "Tastgeschwindigkeit"
	cbBedienKontroll.Text = "Bedienungs-/Kontrolleinrichtungen"
	cbOertlichBesonder.Text = "Örtliche Besonderheiten"
End Sub

Sub AusbildungBVFPan5(cbFahrbahnbenutzung As CheckBox, cbEinordnen As CheckBox, cbMarkierungen As CheckBox, cbFahrstreifenwechsel As CheckBox, cbLinks As CheckBox, cbRechts As CheckBox, _
		cbVorbeifUeberholen As CheckBox, cbAbbiegen As CheckBox, cbABRechts As CheckBox, cbABLinks As CheckBox, cbMehrspurig As CheckBox, cbRadweg As CheckBox, cbSonderstreifen As CheckBox)
	cbFahrbahnbenutzung.Text = "Fahrbahnbenutzung"
	cbEinordnen.Text = "Einordnen"
	cbMarkierungen.Text = "Markierungen"
	cbFahrstreifenwechsel.Text = "Fahrstreifenwechsel"
	cbLinks.Text = "Links"
	cbRechts.Text = "Rechts"
	cbVorbeifUeberholen.Text = "Vorbeifahren / Überholen"
	cbAbbiegen.Text = "Abbiegen"
	cbABRechts.Text = "Rechts"
	cbABLinks.Text = "Links"
	cbMehrspurig.Text = "Mehrspurig"
	cbRadweg.Text = "Radweg"
	cbSonderstreifen.Text = "Sonderstreifen"
End Sub

Sub AusbildungBVFPan6(cbStrassenbahn As CheckBox, cbEinbahnstrasse As CheckBox, cbVorfahrt As CheckBox, cbRechtsVorLinks As CheckBox, cbGruenpfeil As CheckBox, cbPolizeibeamte As CheckBox, _
		cbGruenpfeilSchild As CheckBox, cbGeschwAbstand As CheckBox, cbSituationVerkehrstn As CheckBox, cbFussgaengerueberweg As CheckBox, cbOeffentlVerkehrsm As CheckBox, _
		cbAeltereBehinderte As CheckBox, cbEinbahnstrRadfahrer As CheckBox)
	cbStrassenbahn.Text = "Strassenbahnen"
	cbEinbahnstrasse.Text = "Einbahnstrassen"
	cbVorfahrt.Text = "Vorfahrt"
	cbRechtsVorLinks.Text = "Rechts vor Links"
	cbGruenpfeil.Text = "Grünpfeil"
	cbPolizeibeamte.Text = "Polizeibeamter"
	cbGruenpfeilSchild.Text = "Grünpfeil-Schild"
	cbGeschwAbstand.Text = "Geschwindigkeit / Abstand"
	cbSituationVerkehrstn.Text = "Situation mit anderen Verkehrs-TN"
	cbFussgaengerueberweg.Text = "Fußgänderüberweg"
	cbOeffentlVerkehrsm.Text = "Öffentliche Verkehrsmittel"
	cbAeltereBehinderte.Text = "Ältere / Behinderte"
	cbEinbahnstrRadfahrer.Text = "Einbahnstrassen / Radfahrer"
End Sub

Sub AusbildungBVFPan7(cbKinder As CheckBox, cbSchulbus As CheckBox, cbRadfahrerMofa As CheckBox, cbVerkehrsberuhigt As CheckBox, cbSchwierigeVerkehrsf As CheckBox, cbEngpass As CheckBox, _
		cbKreisverkehr As CheckBox, cbBahnuebergangWarte As CheckBox, cbKritischeVerkehrss As CheckBox, cbHauptverkehrszt As CheckBox, cbPartnerVerhalten As CheckBox, cbSchwungNutzen As CheckBox, _
		cbFussgaengerSchutzb As CheckBox)
	cbKinder.Text = "Kinder"
	cbSchulbus.Text = "Schulbus"
	cbRadfahrerMofa.Text = "Radfahrer / Mofa"
	cbVerkehrsberuhigt.Text = "Verkehrsberuhigter Bereich"
	cbSchwierigeVerkehrsf.Text = "Schwierige Verkehrsführung"
	cbEngpass.Text = "Engpass"
	cbKreisverkehr.Text = "Kreisverkehr"
	cbBahnuebergangWarte.Text = "Bahnübergang (warten)"
	cbKritischeVerkehrss.Text = "Kritische Verkehrssituation"
	cbHauptverkehrszt.Text = "Hauptverkehrszeit"
	cbPartnerVerhalten.Text = "Partnerschaftliches Verhalten"
	cbSchwungNutzen.Text = "Schwung nutzen"
	cbFussgaengerSchutzb.Text = "Fußgänger Schutzbereich"
End Sub

Sub AusbildungBVFPan8(cbAngepassteGeschw As CheckBox, cbAbstand As CheckBox, cbULVorne As CheckBox, cbULHinten As CheckBox, cbULSeitlich As CheckBox, cbBeobachtSpiegel As CheckBox, _
		cbVerkehrszeichen As CheckBox, cbKreuzungEinmuend As CheckBox, cbKurven As CheckBox, cbSteigungen As CheckBox, cbULGefaelle As CheckBox, cbAlleen As CheckBox, cbUeberholen As CheckBox)
	cbAngepassteGeschw.Text = "Angepasste Geschwindigkeit"
	cbAbstand.Text = "Abstand"
	cbULVorne.Text = "vorne"
	cbULHinten.Text = "hinten"
	cbULSeitlich.Text = "seitlich"
	cbBeobachtSpiegel.Text = "Beobachtung / Spiegel"
	cbVerkehrszeichen.Text = "Verkehrszeichen"
	cbKreuzungEinmuend.Text = "Kreuzungen / Einmündungen"
	cbKurven.Text = "Kurven"
	cbSteigungen.Text = "Steigungen"
	cbULGefaelle.Text = "Gefälle"
	cbAlleen.Text = "Alleen"
	cbUeberholen.Text = "Überholen"
End Sub

Sub AusbildungBVFPan9(cbBesondereSituat As CheckBox, cbLiegenblSichern As CheckBox, cbEinfahrenOrtsch As CheckBox, cbFussgaenger As CheckBox, cbWildTiere As CheckBox, cbBesondereAnford As CheckBox, _
		cbLeistungsgrenze As CheckBox, cbOrientierung As CheckBox, cbAblenkung As CheckBox)
	cbBesondereSituat.Text = "Besondere Situationen"
	cbLiegenblSichern.Text = "Liegenbleiben + Absichern"
	cbEinfahrenOrtsch.Text = "Einfahren in Ortschaften"
	cbFussgaenger.Text = "Fußgänger"
	cbWildTiere.Text = "Wild / Tiere"
	cbBesondereAnford.Text = "Besondere Anforderungen"
	cbLeistungsgrenze.Text = "Leistungsgrenze"
	cbOrientierung.Text = "Orientierung"
	cbAblenkung.Text = "Ablenkung (z.B. Radio)"
End Sub

Sub AusbildungBVFPan10(cbFahrtplanung As CheckBox, cbEinfahrtAB As CheckBox, cbABFahrbahnwechsel As CheckBox, cbGeschwindigkeit As CheckBox, cbABAbstand As CheckBox, cbABVorne As CheckBox, _
		cbABHinten As CheckBox, cbABSeitlich As CheckBox, cbABUeberholen As CheckBox, cbSchilder As CheckBox, cbVorbeifahren As CheckBox, cbRastParkTank As CheckBox, cbVerhUnfall As CheckBox)
	cbFahrtplanung.Text = "Fahrtplanung"
	cbEinfahrtAB.Text = "Einfahren in BAB"
	cbABFahrbahnwechsel.Text = "Fahrstreifenwahl"
	cbGeschwindigkeit.Text = "Geschwindigkeit"
	cbABAbstand.Text = "Abstand"
	cbABVorne.Text = "Vorne"
	cbABHinten.Text = "Hinten"
	cbABSeitlich.Text = "Seitlich"
	cbABUeberholen.Text = "Überholen"
	cbSchilder.Text = "Schilder / Markierungen"
	cbVorbeifahren.Text = "Vorbeifahren / Anschlußstellen"
	cbRastParkTank.Text = "Rast-/Parkplätze / Tankstellen"
	cbVerhUnfall.Text = "Verhalten bei Unfällen"
End Sub

Sub AusbildungBVFPan11(cbDichterVerkehr As CheckBox, cbBesonderSituat As CheckBox, cbBesonderAnford As CheckBox, cbABLeistungsgrenze As CheckBox, cbKonfliktSitua As CheckBox, cbABAblenkung As CheckBox, _
		lblDaemmerung As Label, cbBeleuchtung As CheckBox, cbKontrolle As CheckBox, cbEinstell As CheckBox, cbBenutzung As CheckBox, cbFernlicht As CheckBox, cbVerlassenBAB As CheckBox)
	cbDichterVerkehr.Text = "Dichter Verkehr / Stau"
	cbBesonderSituat.Text = "Besondere Situationen"
	cbBesonderAnford.Text = "Besondere Anforderungen"
	cbABLeistungsgrenze.Text = "Leitungsgrenze"
	cbKonfliktSitua.Text = "Konflikt Situationen"
	cbABAblenkung.Text = "Ablenkung"
	lblDaemmerung.Text = "Dämmerung/Dunkelheit"
	cbBeleuchtung.Text = "Beleuchtung"
	cbKontrolle.Text = "Kontrolle"
	cbEinstell.Text = "Einstellung"
	cbBenutzung.Text = "Benutzung"
	cbFernlicht.Text = "Fernlicht"
	cbVerlassenBAB.Text = "Verlassen der BAB"
End Sub

Sub AusbildungBVFPan12(cbBeleuchtStrasse As CheckBox, cbUnbeleuchtStrasse As CheckBox, cbParken As CheckBox, cbDUBesonderSituat As CheckBox, cbSchlechteWitterung As CheckBox, _
		cbTiere As CheckBox, cbBahnuebergaenge As CheckBox, cbUnbelVerkehrTN As CheckBox, cbDUBesonderAnfor As CheckBox, cbBlendung As CheckBox, cbDUOrientierung As CheckBox, _
		cbAbschlussbesp As CheckBox)
	cbBeleuchtStrasse.Text = "Beleuchtete Strassen"
	cbUnbeleuchtStrasse.Text = "Unbeleuchtet Strassen"
	cbParken.Text = "Parken"
	cbDUBesonderSituat.Text = "Besondere Situationen"
	cbSchlechteWitterung.Text = "Schlechte Witterung"
	cbTiere.Text = "Tiere"
	cbBahnuebergaenge.Text = "Bahnübergänge"
	cbUnbelVerkehrTN.Text = "Unbeleuchtete Verkehrsteilnehmer"
	cbDUBesonderAnfor.Text = "Besondere Anforderungen"
	cbBlendung.Text = "Blendung"
	cbDUOrientierung.Text = "Orientierung"
	cbAbschlussbesp.Text = "Abschlussbesprechung"
End Sub

Sub AusbildungBVFPan13(cbSelbstFahren As CheckBox, cbInnerorts As CheckBox, cbAusserorts As CheckBox, cbVerantwFahren As CheckBox, cbTestfPruef As CheckBox, cbFAKT As CheckBox, _
		cbAndere As CheckBox, cbWiederholung As CheckBox, cbLeistungsbew As CheckBox)
	cbSelbstFahren.Text = "Selbstständiges Fahren"
	cbInnerorts.Text = "Innerorts"
	cbAusserorts.Text = "Außerorts"
	cbVerantwFahren.Text = "Verantwortungsbewusstes Fahren"
	cbTestfPruef.Text = "Testfahrt unter Prüfungsbedingungen"
	cbFAKT.Text = "FAKT"
	cbAndere.Text = "Andere"
	cbWiederholung.Text = "Wiederholung / Vertiefung"
	cbLeistungsbew.Text = "Leistungsbewertung"
End Sub

Sub AusbildungBVFPan14(lblChecklisteFahrvorb As Label, cbReifen As CheckBox, cbEinAusschalten As CheckBox, cbFunktionPruefen As CheckBox, cbStandlicht As CheckBox, cbNebelschluss As CheckBox, _
		cbBlinker As CheckBox, cbAbblendlicht As CheckBox, cbWarnblicke As CheckBox, cbHupe As CheckBox, cbBSFernlicht As CheckBox, cbSchlussLeuchte As CheckBox, cbBremslicht As CheckBox)
	lblChecklisteFahrvorb.Text = "Checkliste zur fahrtechn. Vorbereitung"
	cbReifen.Text = "Reifen (z.B. Beschädigung, ...)"
	cbEinAusschalten.Text = "Ein- Aussschalten (Scheinwerfer, ...)"
	cbFunktionPruefen.Text = "Funktion prüfen"
	cbStandlicht.Text = "Standlicht"
	cbNebelschluss.Text = "Nebelschlussleuchte"
	cbBlinker.Text = "Blinker"
	cbAbblendlicht.Text = "Abblendlicht"
	cbWarnblicke.Text = "Warnblickanlage"
	cbHupe.Text = "Hupe"
	cbBSFernlicht.Text = "Fernlicht"
	cbSchlussLeuchte.Text = "Schlussleuchten m. Kennzbel."
	cbBremslicht.Text = "Bremsleuchte"
End Sub

Sub AusbildungBVFPan15(cbKontrollLBenenn As CheckBox, cbRueckstrahler As CheckBox, cbVorhandensein As CheckBox, cbBeschaedigung As CheckBox, cbLenkung As CheckBox, cbLenkschlEntriegeln As CheckBox, _
		cbPruefLenkSpiel As CheckBox, cbFunktBremse As CheckBox, cbBetriebsBremse As CheckBox, cbFeststellBremse As CheckBox, cbAnlegenGurt As CheckBox)
	cbKontrollLBenenn.Text = "Kontrollleuchten benennen"
	cbRueckstrahler.Text = "Rückstrahler"
	cbVorhandensein.Text = "Vorhandensein"
	cbBeschaedigung.Text = "Beschädigungen"
	cbLenkung.Text = "Lenkung"
	cbLenkschlEntriegeln.Text = "Lenkschloss entriegeln"
	cbPruefLenkSpiel.Text = "Überprüfung des Lenkspiels"
	cbFunktBremse.Text = "Funktionsprüfung der Bremse"
	cbBetriebsBremse.Text = "Betriebsbremse"
	cbFeststellBremse.Text = "Feststellbremse"
	cbAnlegenGurt.Text = "Anlegen des Sicherheitsgurts"
End Sub

Sub AusbildungBVFPan16(cbRichtigSitz As CheckBox, cbEinstellRueckspiegel As CheckBox, cbEinKopfstuetze As CheckBox, cbEinLenkrad As CheckBox, lblHeizungLueftung As Label, _
		cbBedienenAgg As CheckBox, cbHeizung As CheckBox, cbHeckHeizung As CheckBox, cbBehSonderaus As CheckBox, cbLueftung As CheckBox, cbKlimaanlage As CheckBox)
	cbRichtigSitz.Text = "Richtige Sitzstellung"
	cbEinstellRueckspiegel.Text = "Einstellen des Rückspiegels"
	cbEinKopfstuetze.Text = "der Kopfstütze"
	cbEinLenkrad.Text = "des Lenkrades"
	lblHeizungLueftung.Text = "Heizung und Lüftung"
	cbBedienenAgg.Text = "Bedienen der Aggregate"
	cbHeizung.Text = "Heizung"
	cbHeckHeizung.Text = "Heckscheibenheizung"
	cbBehSonderaus.Text = "Beheizte Sonderausstattung"
	cbLueftung.Text = "Lüftung"
	cbKlimaanlage.Text = "Klimaanlage"
End Sub

Sub AusbildungBVFPan17(cbEnergieNutzung As CheckBox, cbKeineUnnVerbr As CheckBox, cbRechtztAbsch As CheckBox, lblBetriebsVerkehrssich As Label, cbMotorraum As CheckBox, _
	cbMotoroel As CheckBox, cbKuehlmittel As CheckBox, cbScheibenwaschm As CheckBox, cbTanken As CheckBox, cbBremsen As CheckBox)
	cbEnergieNutzung.Text = "Energiesparende Nutzung"
	cbKeineUnnVerbr.Text = "Keine unnötigen Verbraucher"
	cbRechtztAbsch.Text = "Rechtzeitiges Abschalten"
	lblBetriebsVerkehrssich.Text = "Betriebs- und Verkehrssicherheit"
	cbMotorraum.Text = "Motorraum / Flüssigkeitsstände"
	cbMotoroel.Text = "Motoröl"
	cbKuehlmittel.Text = "Kühlmittel"
	cbScheibenwaschm.Text = "Scheibenwaschflüssigkeit"
	cbTanken.Text = "Tanken"
	cbBremsen.Text = "Bremsen"
End Sub

Sub AusbildungBVFPan18(cbSicherungsmittel As CheckBox, cbWarndreieck As CheckBox, cbBordwerkzeug As CheckBox, cbZusaetzlichAus As CheckBox, cbVerbandskasten As CheckBox, cbAussenkontrolle As CheckBox, _
		cbScheibenWischer As CheckBox, cbKennzeichen As CheckBox, cbCheckSpiegel As CheckBox, cbCheckBeleuchtung As CheckBox, cbLadung As CheckBox, cbLadungssicherung As CheckBox, _
		cbKenntlichmachung As CheckBox)
	cbSicherungsmittel.Text = "Sicherungsmittel"
	cbWarndreieck.Text = "Warndreieck"
	cbBordwerkzeug.Text = "Bordwerkzeug"
	cbZusaetzlichAus.Text = "Zusätzliche Ausrüstung"
	cbVerbandskasten.Text = "Verbandskasten"
	cbAussenkontrolle.Text = "Aussenkontrolle (Schäden,...)"
	cbScheibenWischer.Text = "Scheiben / Wischer"
	cbKennzeichen.Text = "Kennzeichen (HU / AU)"
	cbCheckSpiegel.Text = "Spiegel"
	cbCheckBeleuchtung.Text = "Beleuchtung"
	cbLadung.Text = "Ladung"
	cbLadungssicherung.Text = "Ladungssicherung"
	cbKenntlichmachung.Text = "Kenntlichmachung"
End Sub

Sub AusbildungBVFPan19(cbFahreSchlWitt As CheckBox, cbWittLueftung As CheckBox, cbWittScheiben As CheckBox, cbRegen As CheckBox, cbWasserlachen As CheckBox, _
		cbWindSturm As CheckBox, cbMatchSchnee As CheckBox, cbEis As CheckBox, cbWittBeleuchtung As CheckBox)
	cbFahreSchlWitt.Text = "Fahren bei schlechter Witterung"
	cbWittLueftung.Text = "Lüftung"
	cbWittScheiben.Text = "Scheibenwischer / -wasser"
	cbRegen.Text = "Regen / Sprühnebel"
	cbWasserlachen.Text = "Wasserlachen / Aquaplaning"
	cbWindSturm.Text = "Wind, Sturm, Böen"
	cbMatchSchnee.Text = "Match und Schnee"
	cbEis.Text = "Eis"
	cbWittBeleuchtung.Text = "Beleuchtung"
End Sub
#End Region

Sub AusbildungskontrolleBeschriftung(lblAusbildung0 As Label, lblAusbildung1 As Label, lblAusbildung2 As Label, lblAusbildung3 As Label, _
		lblAusbildung4 As Label, lblAusbildung5 As Label, lblAusbildung6 As Label, lblAusbildungPlus As Label, lblAusbildungMinus As Label, _
		lblAusbildungZahl0 As Label, lblAusbildungZahl1 As Label, lblAusbildungZahl2 As Label,lblAusbildungZahl3 As Label, lblAusbildungZahl4 As Label, _
		lblAusbildungZahl5 As Label, lblAusbildungZahl6 As Label, lblAusbildungZahlX As Label, lblAusbildungZahlMinus As Label, lblAusbildungZahlPlus As Label, _
		lblAusbildungZahlAusrufe As Label, lblAusbildungZahlDel As Label)
	lblAusbildung0.Text = "0"
	lblAusbildung1.Text = "1"
	lblAusbildung2.Text = "2"
	lblAusbildung3.Text = "3"
	lblAusbildung4.Text = "4"
	lblAusbildung5.Text = "5"
	lblAusbildung6.Text = "6"
	lblAusbildungPlus.Text = "+"
	lblAusbildungMinus.Text = "-"
	
	lblAusbildungZahl0.Text = "0"
	lblAusbildungZahl1.Text = "1"
	lblAusbildungZahl2.Text = "2"
	lblAusbildungZahl3.Text = "3"
	lblAusbildungZahl4.Text = "4"
	lblAusbildungZahl5.Text = "5"
	lblAusbildungZahl6.Text = "6"
	lblAusbildungZahlX.Text = "X"
	lblAusbildungZahlAusrufe.Text = "!"
	lblAusbildungZahlMinus.Text = "-"
	lblAusbildungZahlPlus.Text = "+"
	lblAusbildungZahlDel.Text = " "
End Sub

Sub ZaehlerKlassenAnzeige(iZaehler As Int, lblKlassenbezeichnung As Label)
	lblKlassenbezeichnung.Text = iZaehler & ". Klasse"
End Sub

Sub UnterschriftBeschriftung(btSpeichern As Button, btAbbruch As Button, btLoeschen As Button)
	btSpeichern.Text = "Speichern"
	btAbbruch.Text = "Abbruch"
	btLoeschen.Text = "X"
End Sub
#End Region

#Region List-View
'Füllen der ListView aller Schüler
Sub FillSchuelerListView(lstSchueler As ListView)
	DBUtils.FillSchuelerListView(lstSchueler)
	
	Main.sAusgewaehlterSchueler = Main.aSchueler(0, 0)
	Main.iAusgewaehlterSchuelrID = Main.aSchueler(0, 1)
End Sub

Sub FillSchuelerList(lSchueler As ListView)
	DBUtils.FillSchuelerList(lSchueler)
End Sub

Sub FillSchuelerListViewItems(sABC As String) As Int
	Return DBUtils.FillSchuelerListViewItems(sABC)
End Sub

Sub FillSchuelerArray(sABC As String) As String(,)	
	Return DBUtils.FillSchuelerArray(sABC)
End Sub

'Füllen der ListView aller Fahrlehrer
Sub FillFahrlehrerListView(lstFahrlehrer As ListView)
	DBUtils.FillFahrlehrerListView(lstFahrlehrer)
End Sub

'Füllen der ListView aller vorhanden KfZ
Sub FillKfzListView(lstKfz As ListView)
	DBUtils.FillKfzListView(lstKfz)
End Sub

'Alle Klassen, welche zur Ausbildung angemeledet sind, des ausgewählten Schülers ermitteln und anzeigen
'gibt erste Klasse der Ausbildung zurück zum Anzeigen in Label
Sub FillSchuelerKlasseListView(lstSchuelerKlasse As ListView)
	Dim sKlassen, sKlasse1, sKlasse2, sKlasse3, sKlassenspeicher As String
	Dim pos1, pos2 As Int
	
	sKlassen = DBUtils.FillSchuelerKlasseListView(Main.sAusgewaehlterSchueler, Main.iAusgewaehlterSchuelrID)
	'klassen = DBUtils.FillSchuelerKlasseListView(sSchueler, iSchuelerID)
	
	'Rücksetzen der ListView
	lstSchuelerKlasse.Clear
	
	pos1 = sKlassen.IndexOf(", ")
	If  pos1 > 0 Then
		sKlasse1 = sKlassen.SubString2(0, pos1)
		sKlassenspeicher = sKlassen.SubString(pos1 + 2)
		
		pos2 = sKlassenspeicher.IndexOf(", ")
		If pos2 > 0 Then
			sKlasse2 = sKlassenspeicher.SubString2(0, pos2)
			sKlasse3 = sKlassenspeicher.SubString(pos2 + 2)
			
			lstSchuelerKlasse.AddSingleLine(sKlasse1)
			lstSchuelerKlasse.AddSingleLine(sKlasse2)
			lstSchuelerKlasse.AddSingleLine(sKlasse3)
		Else
			sKlasse2 = sKlassenspeicher
			lstSchuelerKlasse.AddSingleLine(sKlasse1)
			lstSchuelerKlasse.AddSingleLine(sKlasse2)
		End If
	Else
		lstSchuelerKlasse.AddSingleLine(sKlassen)
	End If
End Sub

'Alle Klassen anzeigen
Sub FillKlassenListView(lstKlassen As ListView)
	DBUtils.FillKlassenListView(lstKlassen)
End Sub

'Treffpunkt Liste füllen
Sub FillTreffpunktListView(lstTreffpunkt As ListView)
	DBUtils.FillTreffpunktListView(lstTreffpunkt)
End Sub

'ZahluungenFuer Liste füllen
Sub FillZahlungenFuerListView(lstZahlungFuer As ListView)
	DBUtils.FillZahlungenFuerListView(lstZahlungFuer)
End Sub
#End Region

'Ausbildungsstand: Themen laden
Sub FillAusbildungLernThemenListView(lstAusbildungLernThemen As ListView, iKategorie As Int)
	DBUtils.FillAusbildungLernThemenListView(lstAusbildungLernThemen, iKategorie)
End Sub

'Ausbildungsstand: Punkte/Zuordnung laden
Sub FillAusbildungLernPunkteListView(lstAusbildungLernPunkte As ListView, sSchueler As String, iKategorie As Int)
	DBUtils.FillAusbildungLernPunkteListView(lstAusbildungLernPunkte, sSchueler, iKategorie)
End Sub

'Holen der erfassten Zahlunugen
Sub ErfassteZahlungenHolen(sAusswahl As String, lstZahlungen As ListView) As String
	 Return DBUtils.ErfassteZahlungenHolen(sAusswahl, lstZahlungen)
End Sub

'Zahlung speichern
Sub ZahlungenSpeichern(sBetrag As String, sDatum As String, sZahlungFuer As String) As Boolean
	Return DBUtils.ZahlungenSpeichern(sBetrag, sDatum, sZahlungFuer)
End Sub

'Zahlungseintrag löschen
Sub ZahlungLoeschen(Position As Int, sAuswahl As String) As Boolean	
	If Main.bZahlDatum Then
		sAuswahl = "Alle"
	End If
	Return DBUtils.ZahlungLoeschen(Position, sAuswahl)
End Sub

'ausgewählte Zahlungsdaten holen
Sub ZahlungHolen(iPosition As Int, oValue As Object, sAuswahl As String) As String()
	Return DBUtils.ZahlungPositionHolen(iPosition, oValue, sAuswahl)
End Sub

'ID des ausgewählten Termins holen
Sub TerminIDHolen(sTermin As String, sStartzeit As String, sDauer As String, sKlasse As String, sFahrbezeichnung As String, sTreffpunkt As String) As Int
	Dim iTerminID As Int
	If (sKlasse = "" And sTreffpunkt = "") Then
		'ID für Sonstige Tätigkeit
		iTerminID = DBUtils.SonstTaetigkeitenIDHolen(sTermin, sStartzeit, sDauer, sFahrbezeichnung)
		If iTerminID > 0 Then
			Main.bSonstTaetigkeit = True
		Else
			Main.bSonstTaetigkeit = False
		End If
	Else
		'ID der normalen Termine
		iTerminID = DBUtils.TerminIDHolen(sTermin, sStartzeit, sDauer, sKlasse, sFahrbezeichnung, sTreffpunkt)	
	End If
	Return iTerminID
End Sub

'Termin löschen
Sub TerminLoeschen(iPosition As Int, sDatum As String) As Boolean
	Dim result As Boolean
	result = DBUtils.TerminLoeschen(iPosition, sDatum)
	
	Return result
End Sub

'Termin löschen, wenn Eintrag schon vorhanden ist und nochmals gespeichert werden soll
Sub TerminLoeschenWennDoppeltGespeichert(iTerminID As Int) As Boolean
	Dim result As Boolean
	result = DBUtils.TerminLoeschenWennDoppeltGespeichert(iTerminID)
	
	Return result
End Sub

'Termindaten anlegen ohne Unterschrift
Sub DatenVormerken(iTerminID As Int, sTermin As String, sStartzeit As String, sDauer As String, sKlasse As String, sFahrbezeichnung As String, sTreffpunkt As String, sFahrlehrer As String, sKFZ As String, sBegleitfahrzeug As String) As Boolean
	Dim bResult As Boolean
	
	If Main.bDatenVorhanden Then 
		bResult = DBUtils.DatenVormerkenUpdate(iTerminID, sTermin, sStartzeit, sDauer, sKlasse, sFahrbezeichnung, sTreffpunkt, sFahrlehrer, sKFZ, sBegleitfahrzeug)
	Else
		bResult = DBUtils.DatenVormerkenInsert(sTermin, sStartzeit, sDauer, sKlasse, sFahrbezeichnung, sTreffpunkt, sFahrlehrer, sKFZ, sBegleitfahrzeug)
	End If
	
	Return bResult
End Sub

'Sonstige Tätigkeiten speichern
Sub SonstigeTaetigkeitenSpeichern(iTerminID As Int, sTermin As String, sStartzeit As String, sDauer As String, sFahrbezeichnung As String, sFahrlehrer As String, bCheckSignature As Boolean) As Boolean
	Dim bResult As Boolean
	
	If Main.bDatenVorhanden Then
		bResult = DBUtils.SonstigeTaetigkeitenUpdate(iTerminID, sTermin, sStartzeit, sDauer, sFahrbezeichnung, sFahrlehrer, bCheckSignature)
	Else
		bResult = DBUtils.SonstigeTaetigkeitenInsert(sTermin, sStartzeit, sDauer, sFahrbezeichnung, sFahrlehrer, bCheckSignature)
	End If
	
	Return bResult
End Sub

'Füllen des ListView mit allen Fahrtenbezeichnungen
Sub FillFahrtenbezeichnungListView(lstFahrtBezeichnung As ListView)
	DBUtils.FillFahrtenbezeichnungListView(lstFahrtBezeichnung)
End Sub

'Füllen der ListView mit Sonstige Tätigkeiten
Sub FillSonstigeTaetigkeitenListView(lstSonstigeTaetigkeiten As ListView)
	DBUtils.FillSonstigeTaetigkeitenListView(lstSonstigeTaetigkeiten)
End Sub

'Füllen des ListView mit Begleitfahrzeugen - Begleitfahrzeuge sind nur PKWs
Sub FillBegleitfahrzeugListView(lstBegleitfahrzeug As ListView)
	DBUtils.FillBegleitfahrzeugListView(lstBegleitfahrzeug)
End Sub

'Klasse des Schülers holen
Sub GetSchuelerKlasse(iAusgewaehlterSchueler As Int) As String
	Return DBUtils.GetSchuelerKlasse(iAusgewaehlterSchueler)
End Sub

'KFZ holen
Sub GetKFZ(iTerminID As Int) As String
	Return DBUtils.GetKFZ(iTerminID)
End Sub

'Begleitfahrzeug holen
Sub BegleitfahrzeugHolen(ITerminID As Int) As String
	Return DBUtils.BegleitfahrzeugHolen(ITerminID)
End Sub

'Fix hinterlegtes KFZ holen
Sub GetSelectedKFZ() As String
	Return DBUtils.GetSelectedKFZ
End Sub

'Termine des definierten Fahrlehrers holen und in ListView auf Startseite anzeigen
Sub FahrlehrerTermineHolen(sDate As String, lstTermine As ListView) As Int(2)
	Dim iStunden(2) As Int
	iStunden = DBUtils.FahrlehrerGesamtstundenHolen(sDate)
	
	'ListView erstellen und mit Daten füllen
	DBUtils.FahrlehrerTermineHolen(sDate, lstTermine)
	
	Return iStunden
End Sub

'Neuen Treffpunkt hinzufügen
Sub InsertNeuenTreffpunkt(sNeuerTreffpunkt As String) As Boolean
	If (DBUtils.InsertNeuerTreffpunkt(sNeuerTreffpunkt, 1) > 0) Then
		Return True
	Else
		Return False
	End If
End Sub

'eingetragenen Treffpunkt löschen
Sub RemoveNeuenTreffpunkt(sTreffpunkt As String)
	DBUtils.RemoveNeuenTreffpunkt(sTreffpunkt)
End Sub

'Stammdaten holen
Sub StammdatenHolen As String()
	Return DBUtils.StammdatenHolen
End Sub

'Klassen von den Stammdaten holen und Anzeige mit Kommastellen versehen
Sub StammdatenKlassendatenHolen(iZahl As Int) As String()
	Dim aKlassendaten() As String
	aKlassendaten = DBUtils.StammdatenKlassendatenHolen(iZahl)
	
	'Kommastellen anzeigen: 0 als 0,0
	For i = 0 To aKlassendaten.Length - 1
		If i <> 2 And i <> 3 And i < 10 Then
			If aKlassendaten(i) = 0 Or aKlassendaten(i) = "" Then 
				aKlassendaten(i) = "0,0"
			End If
		End If
	Next
	
	Return aKlassendaten
End Sub

'Speichern des Zustandes der Konfigurationsdaten
Sub SetKonfig(Checked As Boolean, cb As String)
	Dim sKonfig, sSession, sKey As String
	If cb = "cbBegleitfahrzueg" Then
		sKonfig = "Begleitfahrzeug_IsSelected"
		sSession = "Begleitfahrzeug"
		sKey = "anzeigen"
	Else If cb = "cbPruefungsErgebnis" Then 
		sKonfig = "Pruefungsergebnis_IsSelected"
		sSession = "Pruefungsergebnis"
		sKey = "erfassen"
	End If
	DBUtils.SetBegleitfahrzeug(Checked, sKonfig)
	ReadWriteINI.UpdateINI(sSession, sKey, Checked)
End Sub

'KonfigurationsDaten setzen
Sub KonfigdatenLaden As Boolean()
	Dim aKonfig(3) As Boolean
	If File.Exists(Main.SourceFolder, "FaData.INI") Then
		aKonfig = ReadWriteINI.ReadConfigData()
	Else
		aKonfig = DBUtils.KonfigdatenLaden()
	End If
	Return aKonfig
End Sub

'Fahreinheit aus Konfigurations-Datei holen
Sub KonfigdatenFahreinheit As String
	Dim sFahrEinheit As String
	
	If File.Exists(Main.SourceFolder, "FaData.INI") Then
		sFahrEinheit = ReadWriteINI.ReadFahrEinheit()
	End If
	
	If sFahrEinheit = "" Then
		sFahrEinheit = "45"
	End If
	
	Return sFahrEinheit
End Sub

'KonfigurationsDaten in INI Datei speichern
Sub	SaveConfigDataToINI(sFahrlehrer As String, sCheckedKFZ As String, sFahrEinheit As String, _
							bBegleitfahrzeug As Boolean, bPrueferg As Boolean, bBVFAusbildung As Boolean) As Boolean
	'Eingestellte Daten holen (Fahrlehrer + ID, KFZ + ID, Begleitfahrzeug, Prüfungsergebnis)
	Dim iFahrlehrerID, iCheckedKFZID As Int
	iFahrlehrerID = DBUtils.GetFahrlehrerID(sFahrlehrer)
	iCheckedKFZID = DBUtils.GetKFZID(sCheckedKFZ)
	
	Return ReadWriteINI.WriteData(sFahrlehrer, iFahrlehrerID, sCheckedKFZ, iCheckedKFZID, sFahrEinheit, bBegleitfahrzeug, bPrueferg, bBVFAusbildung)
End Sub

'Speichert die Treffpunkte in eine extra Datei
Sub SaveTreffpunkteToINI As Boolean
	Return ReadWriteINI.WriteTreffpunkte
End Sub

'Initialsierung des Unterschriftenfeldes
Sub InitialisierungSignature(Canvas1 As Canvas, panUnterschrift As Panel, SigD As SignatureData)
	Canvas1.Initialize(panUnterschrift)
	
	SigD.Initialize
	SigD.Canvas = Canvas1
	SigD.Panel = panUnterschrift
	SigD.SignatureColor = Colors.Black
	SigD.SignatureWidth = 3dip 'Schriftbreite
End Sub

'Label-Layout für ausgewählte Zeile
Sub SetLabelLayout(lblAusbildungListeZeile As Label, lblAusbildungListeZusatz As Label)
	lblAusbildungListeZeile.TextColor = Colors.Black
	lblAusbildungListeZeile.Color = Colors.RGB(173, 216, 230)
	lblAusbildungListeZusatz.TextColor = Colors.Black
	lblAusbildungListeZusatz.Color = Colors.RGB(173, 216, 230)
End Sub

'Label-Layout aller relevaten Labels zurücksetzen
Sub AusbildungsAnzeigenLoeschen(lblAusbildungListeZeile1 As Label, lblAusbildungListeZusatz1 As Label, _
		lblAusbildungListeZeile2 As Label, lblAusbildungListeZusatz2 As Label, lblAusbildungListeZeile3 As Label, _
		lblAusbildungListeZusatz3 As Label, lblAusbildungListeZeile4 As Label, lblAusbildungListeZusatz4 As Label, _
		lblAusbildungListeZeile5 As Label, lblAusbildungListeZusatz5 As Label, lblAusbildungListeZeile6 As Label, _
		lblAusbildungListeZusatz6 As Label, lblAusbildungListeZeile7 As Label, lblAusbildungListeZusatz7 As Label, _
		lblAusbildungListeZeile8 As Label, lblAusbildungListeZusatz8 As Label, lblAusbildungListeZeile9 As Label, _
		lblAusbildungListeZusatz9 As Label, lblAusbildungListeZeile10 As Label, lblAusbildungListeZusatz10 As Label)
	'1.Zeile
	lblAusbildungListeZeile1.Text = ""
	lblAusbildungListeZeile1.Color = Colors.Transparent
	lblAusbildungListeZusatz1.Text = ""
	lblAusbildungListeZusatz1.Color = Colors.Transparent
	'2.Zeile
	lblAusbildungListeZeile2.Text = ""
	lblAusbildungListeZeile2.Color = Colors.Transparent
	lblAusbildungListeZusatz2.Text = ""
	lblAusbildungListeZusatz2.Color = Colors.Transparent
	'3.Zeile
	lblAusbildungListeZeile3.Text = ""
	lblAusbildungListeZeile3.Color = Colors.Transparent
	lblAusbildungListeZusatz3.Text = ""
	lblAusbildungListeZusatz3.Color = Colors.Transparent
	'4.Zeile
	lblAusbildungListeZeile4.Text = ""
	lblAusbildungListeZeile4.Color = Colors.Transparent
	lblAusbildungListeZusatz4.Text = ""
	lblAusbildungListeZusatz4.Color = Colors.Transparent
	'5.Zeile
	lblAusbildungListeZeile5.Text = ""
	lblAusbildungListeZeile5.Color = Colors.Transparent
	lblAusbildungListeZusatz5.Text = ""
	lblAusbildungListeZusatz5.Color = Colors.Transparent
	'6.Zeile
	lblAusbildungListeZeile6.Text = ""
	lblAusbildungListeZeile6.Color = Colors.Transparent
	lblAusbildungListeZusatz6.Text = ""
	lblAusbildungListeZusatz6.Color = Colors.Transparent
	'7.Zeile
	lblAusbildungListeZeile7.Text = ""
	lblAusbildungListeZeile7.Color = Colors.Transparent
	lblAusbildungListeZusatz7.Text = ""
	lblAusbildungListeZusatz7.Color = Colors.Transparent
	'8.Zeile
	lblAusbildungListeZeile8.Text = ""
	lblAusbildungListeZeile8.Color = Colors.Transparent
	lblAusbildungListeZusatz8.Text = ""
	lblAusbildungListeZusatz8.Color = Colors.Transparent
	'9.Zeile
	lblAusbildungListeZeile9.Text = ""
	lblAusbildungListeZeile9.Color = Colors.Transparent
	lblAusbildungListeZusatz9.Text = ""
	lblAusbildungListeZusatz9.Color = Colors.Transparent
	'10.Zeile
	lblAusbildungListeZeile10.Text = ""
	lblAusbildungListeZeile10.Color = Colors.Transparent
	lblAusbildungListeZusatz10.Text = ""
	lblAusbildungListeZusatz10.Color = Colors.Transparent
End Sub

'Array reset - alle Einträge auf false
Sub ArraybIsSelected(bValue As Boolean)
	For i = 0 To Main.bIsSelected.Length - 1
		Main.bIsSelected(i) = bValue
	Next
End Sub

'Bewertung eintragen und gleich in DB speichern 
Sub AusbildungZustandAendern(lblAusbildungListeZeile1 As Label, lblAusbildungListeZusatz1 As Label, lblAusbildungListeZeile2 As Label, _
		lblAusbildungListeZusatz2 As Label, lblAusbildungListeZeile3 As Label, lblAusbildungListeZusatz3 As Label, lblAusbildungListeZeile4 As Label, _
		lblAusbildungListeZusatz4 As Label, lblAusbildungListeZeile5 As Label, lblAusbildungListeZusatz5 As Label, lblAusbildungListeZeile6 As Label, _
		lblAusbildungListeZusatz6 As Label, lblAusbildungListeZeile7 As Label, lblAusbildungListeZusatz7 As Label, lblAusbildungListeZeile8 As Label, _
		lblAusbildungListeZusatz8 As Label, lblAusbildungListeZeile9 As Label, lblAusbildungListeZusatz9 As Label, lblAusbildungListeZeile10 As Label, _
		lblAusbildungListeZusatz10 As Label, sCharacter As Char)
	
	If lblAusbildungListeZusatz1.Text <> "" Then
		lblAusbildungListeZusatz1.Text = sCharacter
	End If
	If lblAusbildungListeZusatz2.Text <> "" Then
		lblAusbildungListeZusatz2.Text = sCharacter
	End If
	If lblAusbildungListeZusatz3.Text <> "" Then
		lblAusbildungListeZusatz3.Text = sCharacter
	End If
	If lblAusbildungListeZusatz4.Text <> "" Then
		lblAusbildungListeZusatz4.Text = sCharacter
	End If
	If lblAusbildungListeZusatz5.Text <> "" Then
		lblAusbildungListeZusatz5.Text = sCharacter
	End If
	If lblAusbildungListeZusatz6.Text <> "" Then
		lblAusbildungListeZusatz6.Text = sCharacter
	End If
	If lblAusbildungListeZusatz7.Text <> "" Then
		lblAusbildungListeZusatz7.Text = sCharacter
	End If
	If lblAusbildungListeZusatz8.Text <> "" Then
		lblAusbildungListeZusatz8.Text = sCharacter
	End If
	If lblAusbildungListeZusatz9.Text <> "" Then
		lblAusbildungListeZusatz9.Text = sCharacter
	End If
	If lblAusbildungListeZusatz10.Text <> "" Then
		lblAusbildungListeZusatz10.Text = sCharacter
	End If
	
	'Welche Position muss geändert werden?
	Dim iPosition As Int
	For i = 0 To Main.bIsSelected.Length - 1
		If Main.bIsSelected(i) Then
			iPosition = i
		End If
	Next
	
	 If DBUtils.UpdateAusbildungLernPunkte(sCharacter, iPosition) Then
	 	AusbildungsAnzeigenLoeschen(lblAusbildungListeZeile1, lblAusbildungListeZusatz1, _
			lblAusbildungListeZeile2, lblAusbildungListeZusatz2, lblAusbildungListeZeile3, lblAusbildungListeZusatz3, _
			lblAusbildungListeZeile4, lblAusbildungListeZusatz4, lblAusbildungListeZeile5, lblAusbildungListeZusatz5, _
			lblAusbildungListeZeile6, lblAusbildungListeZusatz6, lblAusbildungListeZeile7, lblAusbildungListeZusatz7, _
			lblAusbildungListeZeile8, lblAusbildungListeZusatz8, lblAusbildungListeZeile9, lblAusbildungListeZusatz9, _
			lblAusbildungListeZeile10, lblAusbildungListeZusatz10)
	 Else
	 	ToastMessageShow("Fehler beim speichern", True)
	 End If
End Sub

'Ausbildungs-Zustand mit neuem Wert zurückgeben
Sub AusbildungZustandNeu(sEintraege As String, iAnfang As Int , iEnde As Int, iPosition As Int, sCharacter As Char) As String
	Dim sEintraegeSplit As String
	
	sEintraegeSplit = sEintraege.SubString2(iAnfang, iEnde)
	
	Dim cEin(10) As Char
	
	For i = 0 To sEintraegeSplit.Length - 1
		If i = iPosition Then
			cEin(i) = sCharacter
		Else 
			cEin(i) = sEintraegeSplit.CharAt(i)
		End If
	Next 
	
	sEintraegeSplit = ""
	For i = 0 To cEin.Length - 1
		sEintraegeSplit = sEintraegeSplit & cEin(i)
	Next
	Return sEintraegeSplit
	
End Sub

'Datenbank in Benutzung
Sub DBinBenutzung(bValue As Boolean)
	DBUtils.DBinBenutzung(bValue)
End Sub

'Prüfung, ob richtige Datenbank-Version vorhanden ist
Sub CheckVersion() As Boolean
	Return DBUtils.CheckVersion()
End Sub

'Prüfung, ob Daten im System vorhanden sind
Sub CheckDatenInDB() As Boolean
	Dim bResult As Boolean
	
	bResult = DBUtils.CheckDatenInDB()
	
	Return bResult
End Sub

'Datenbank-Check, ob Einträge für LernThemen gesetzt und bei LernThemen die Kat ID eingetragen ist
'--> erforderlich, da sonst keine Seitenzuordnung vorhanden ist
Sub FehlendeZuordnungenFuellenNachTransport()
	DBUtils.SetLernThermen_Kat()
	DBUtils.SetKlassenIdsInTermine()
	DBUtils.SetAnmeldedatum()
	DBUtils.SetKl1BeantragtInStammdaten()
End Sub

'Korriert fehlhafte Einträge in DB 
'--> Hat_Unterschrift wurde falsch befüllt
Sub KorrigierenDerVorhandenenFahrdaten()
	DBUtils.KorrigierenFehlerhafterEintraege()
End Sub

'Korrigiert die fehlerhaften Matchcodes nach dem Import der 
Sub KorrigierenDerFehlerhaftenMatchCodes()
	DBUtils.KorrigierenFehlerhafteMatchCodes()
End Sub

'Laden fehlender fixer Treffpunkte aus INI Datei
Sub LadenFixerTreffpunkte
	'Check, ob fixe Treffpunkte schon vorhanden sind
'	If DBUtils.CheckFixeTreffpunkte Then
'		'keine Aktion nötig
'	Else
		Dim iAnzahl As Int 
		iAnzahl = ReadWriteINI.GetTreffpunktEintraege
		
		Dim aTreffpunkt() As String
		
		If iAnzahl > 0 Then
			'Treffpunkte aus INI Datei einlesen und in DB schreiben
			For i = 1 To iAnzahl
				aTreffpunkt = ReadWriteINI.ReadTreffpunkte(i)
				
				DBUtils.WriteTreffpunkte(aTreffpunkt)
			Next
		End If
'	End If
End Sub

'Setzen des markierten ausgewählten Schüler
Sub AusgewaehltenSchuelerSetzen
	For i = 0 To Main.aSchueler.Length - 1
		If Main.aSchueler(i, 2) = 1 Then
			Main.sAusgewaehlterSchueler = Main.aSchueler(i, 0)
			Main.iAusgewaehlterSchuelrID = Main.aSchueler(i, 1)
		End If
	Next
End Sub

'Prüfen, ob Begleitfahrzeug nötig ist
Sub CheckBegleitfahrzeug(sAuswahlKlasse As String) As Boolean
	Return DBUtils.CheckBegleitfahrzeug(sAuswahlKlasse)
End Sub

'Prüfen, ob Startzeit sich mit vorherigem Termin überschneidet
Sub StartzeitPuefen(iTerminID As Int, sDatum As String, sStartzeit As String, sDauer As String) As Boolean
	Return DBUtils.StartzeitPuefen(iTerminID, sDatum, sStartzeit, sDauer)
End Sub

'Prüfen, ob ausgewählte Fahrtbezeichnung eine Prüfungn ist
Sub IsPruefung(sFahrtbezeichnung As String) As Boolean
	Return DBUtils.IsPruefung(sFahrtbezeichnung)
End Sub

'Prüfen, ob Unterschrift vohanden ist
Sub CheckSignature(iTerminID As Int) As Boolean
	Return DBUtils.CheckSignature(iTerminID)
End Sub

'Fahrdaten speichern
Sub FahrdatenSpeichernUpdate(iTerminID As Int, sPruefungsergebnis As String, bPruefergebnis As Boolean, bUnterschrift As Boolean) As Boolean
	'Speicherung mit Prüfungsergebnis
	If bPruefergebnis Then
		Return DBUtils.FahrdatenSpeichernUpdateMit(iTerminID, sPruefungsergebnis, bUnterschrift)
	Else
		Return DBUtils.FahrdatenSpeichernUpdateOhne(iTerminID, bUnterschrift)
	End If
End Sub

Sub GetSchuelerID(sAusgewaehlterSchueler As String) As Int
	Return DBUtils.GetSchuelerIDMatchcode(sAusgewaehlterSchueler)
End Sub

#region BVF Daten
Sub InsertUpdateBVFData(sDate As String, sSchueler As String, cbBesonderhEinsteigen As Boolean, cbEinstellen As Boolean, cbLenkrad As Boolean, cbSpiegel As Boolean, cbKopfstuetze As Boolean, cbSitz As Boolean, cbLenkradhaltung As Boolean, cbPedale As Boolean, cbGurt As Boolean, cbSchaltWaehlhebel As Boolean, cbZuendschloss As Boolean, cbMotorAnlassen As Boolean, _
			cbAnfahrAnhalte As Boolean, cbSchaltuebg As Boolean, cbHoch1_2 As Boolean, cbHoch2_3 As Boolean, cbHoch3_4 As Boolean, cbRunter4_3 As Boolean, cbRunter3_2 As Boolean, cbRunter2_1 As Boolean, cbRunter4_2 As Boolean, cbRunter4_1 As Boolean, cbRunter3_1 As Boolean, cbLenkuebung As Boolean, cbUmkehren As Boolean, cbEinparkenLaengs As Boolean, _
			cbLVorwaertsRechts As Boolean, cbLRueckwaertsLinks As Boolean, cbLRueckwaertsRechts As Boolean, cbLVorwaertsLinks As Boolean, cbRueckwaertsfahren As Boolean, cbEinparkenQuer As Boolean, cbQVorwaertsRechts As Boolean, cbQRueckwaertsLinks As Boolean, _
			cbQRueckwaertsRechts As Boolean, cbQVorwaertsLinks As Boolean, cbGefahrbremsung As Boolean, cbRollenSchalten As Boolean, cbBremsSchalten As Boolean, cbBremsuebung As Boolean, cbDegressiv As Boolean, cbZielbremsung As Boolean, cbGefahrsituation As Boolean, cbGefaelle As Boolean, _
			cbAnhalten As Boolean, cbAnfahren As Boolean, cbRueckwaerts As Boolean, cbSichern As Boolean, cbSchalten As Boolean, cbSteigung As Boolean, cbStAnhalten As Boolean, cbStAnfahren As Boolean, cbStRueckwaerts As Boolean, cbStSichern As Boolean, cbStSchalten As Boolean, cbTastgeschw As Boolean, cbBedienKontroll As Boolean, _
			cbOertlichBesonder As Boolean, cbFahrbahnbenutzung As Boolean, cbEinordnen As Boolean, cbMarkierungen As Boolean, cbFahrstreifenwechsel As Boolean, cbLinks As Boolean, cbRechts As Boolean, cbVorbeifUeberholen As Boolean, cbAbbiegen As Boolean, cbABRechts As Boolean, cbABLinks As Boolean, cbMehrspurig As Boolean, _
			cbRadweg As Boolean, cbSonderstreifen As Boolean, cbStrassenbahn As Boolean, cbEinbahnstrasse As Boolean, cbVorfahrt As Boolean, cbRechtsVorLinks As Boolean, cbGruenpfeil As Boolean, cbPolizeibeamte As Boolean, cbGruenpfeilSchild As Boolean, cbGeschwAbstand As Boolean, cbSituationVerkehrstn As Boolean, _
			cbFussgaengerueberweg As Boolean, cbOeffentlVerkehrsm As Boolean, cbAeltereBehinderte As Boolean, cbEinbahnstrRadfahrer As Boolean, cbKinder As Boolean, cbSchulbus As Boolean, cbRadfahrerMofa As Boolean, cbVerkehrsberuhigt As Boolean, cbSchwierigeVerkehrsf As Boolean, cbEngpass As Boolean, _
			cbKreisverkehr As Boolean, cbBahnuebergangWarte As Boolean, cbKritischeVerkehrss As Boolean, cbHauptverkehrszt As Boolean, cbPartnerVerhalten As Boolean, cbSchwungNutzen As Boolean, cbFussgaengerSchutzb As Boolean, cbAngepassteGeschw As Boolean, cbAbstand As Boolean, cbULVorne As Boolean, _
			cbULHinten As Boolean, cbULSeitlich As Boolean, cbBeobachtSpiegel As Boolean, cbVerkehrszeichen As Boolean, cbKreuzungEinmuend As Boolean, cbKurven As Boolean, cbSteigungen As Boolean, cbULGefaelle As Boolean, cbAlleen As Boolean, cbUeberholen As Boolean, cbBesondereSituat As Boolean, cbLiegenblSichern As Boolean, _
			cbEinfahrenOrtsch As Boolean, cbFussgaenger As Boolean, cbWildTiere As Boolean, cbBesondereAnford As Boolean, cbLeistungsgrenze As Boolean, cbOrientierung As Boolean, cbAblenkung As Boolean, cbFahrtplanung As Boolean, cbEinfahrtAB As Boolean, cbABFahrbahnwechsel As Boolean, cbGeschwindigkeit As Boolean, _
			cbABAbstand As Boolean, cbABVorne As Boolean, cbABHinten As Boolean, cbABSeitlich As Boolean, cbABUeberholen As Boolean, cbSchilder As Boolean, cbVorbeifahren As Boolean, cbRastParkTank As Boolean, cbVerhUnfall As Boolean, cbDichterVerkehr As Boolean, cbBesonderSituat As Boolean, cbBesonderAnford As Boolean, _
			cbABLeistungsgrenze As Boolean, cbKonfliktSitua As Boolean, cbABAblenkung As Boolean, cbBeleuchtung As Boolean, cbKontrolle As Boolean, cbEinstell As Boolean, cbBenutzung As Boolean, cbFernlicht As Boolean, cbVerlassenBAB As Boolean, cbBeleuchtStrasse As Boolean, cbUnbeleuchtStrasse As Boolean, cbParken As Boolean, cbDUBesonderSituat As Boolean, _
			cbSchlechteWitterung As Boolean, cbTiere As Boolean, cbBahnuebergaenge As Boolean, cbUnbelVerkehrTN As Boolean, cbDUBesonderAnfor As Boolean, cbBlendung As Boolean, cbDUOrientierung As Boolean, cbAbschlussbesp As Boolean, cbSelbstFahren As Boolean, cbInnerorts As Boolean, cbAusserorts As Boolean, cbVerantwFahren As Boolean, cbTestfPruef As Boolean, _
			cbFAKT As Boolean, cbAndere As Boolean, cbWiederholung As Boolean, cbLeistungsbew As Boolean, cbReifen As Boolean, cbEinAusschalten As Boolean, cbFunktionPruefen As Boolean, cbStandlicht As Boolean, cbNebelschluss As Boolean, cbBlinker As Boolean, cbAbblendlicht As Boolean, cbWarnblicke As Boolean, cbHupe As Boolean, cbBSFernlicht As Boolean, cbSchlussLeuchte As Boolean, _
			cbBremslicht As Boolean, cbKontrollLBenenn As Boolean, cbRueckstrahler As Boolean, cbVorhandensein As Boolean, cbBeschaedigung As Boolean, cbLenkung As Boolean, cbLenkschlEntriegeln As Boolean, cbPruefLenkSpiel As Boolean, cbFunktBremse As Boolean, cbBetriebsBremse As Boolean, cbFeststellBremse As Boolean, _
			cbAnlegenGurt As Boolean, cbRichtigSitz As Boolean, cbEinstellRueckspiegel As Boolean, cbEinKopfstuetze As Boolean, cbEinLenkrad As Boolean, cbBedienenAgg As Boolean, cbHeizung As Boolean, cbHeckHeizung As Boolean, cbBehSonderaus As Boolean, cbLueftung As Boolean, cbKlimaanlage As Boolean, cbEnergieNutzung As Boolean, _
			cbKeineUnnVerbr As Boolean, cbRechtztAbsch As Boolean, cbMotorraum As Boolean, cbMotoroel As Boolean, cbKuehlmittel As Boolean, cbScheibenwaschm As Boolean, cbTanken As Boolean, cbBremsen As Boolean, cbSicherungsmittel As Boolean, cbWarndreieck As Boolean, cbBordwerkzeug As Boolean, cbZusaetzlichAus As Boolean, cbVerbandskasten As Boolean, cbAussenkontrolle As Boolean, _
			cbScheibenWischer As Boolean, cbKennzeichen As Boolean, cbCheckSpiegel As Boolean, cbCheckBeleuchtung As Boolean, cbLadung As Boolean, cbLadungssicherung As Boolean, cbKenntlichmachung As Boolean, cbFahreSchlWitt As Boolean, cbWittLueftung As Boolean, cbWittScheiben As Boolean, cbRegen As Boolean, _
			cbWasserlachen As Boolean, cbWindSturm As Boolean, cbMatchSchnee As Boolean, cbEis As Boolean, cbWittBeleuchtung As Boolean, etNotizen As String) As Boolean
	Dim iAusbKontrolleOID As Int
	iAusbKontrolleOID = DBUtils.CheckBVFDataExists(sDate, sSchueler)
	If iAusbKontrolleOID > 0 Then
		Return DBUtils.UpdateBVFData(sDate, sSchueler, CInt(cbBesonderhEinsteigen), CInt(cbEinstellen), CInt(cbLenkrad), CInt(cbSpiegel), CInt(cbKopfstuetze), CInt(cbSitz), CInt(cbLenkradhaltung), CInt(cbPedale), CInt(cbGurt), CInt(cbSchaltWaehlhebel), CInt(cbZuendschloss), CInt(cbMotorAnlassen), _
			CInt(cbAnfahrAnhalte), CInt(cbSchaltuebg), CInt(cbHoch1_2), CInt(cbHoch2_3), CInt(cbHoch3_4), CInt(cbRunter4_3), CInt(cbRunter3_2), CInt(cbRunter2_1), CInt(cbRunter4_2), CInt(cbRunter4_1), CInt(cbRunter3_1), CInt(cbLenkuebung), CInt(cbUmkehren), CInt(cbEinparkenLaengs), _
			CInt(cbLVorwaertsRechts), CInt(cbLRueckwaertsLinks), CInt(cbLRueckwaertsRechts), CInt(cbLVorwaertsLinks), CInt(cbRueckwaertsfahren), CInt(cbEinparkenQuer), CInt(cbQVorwaertsRechts), CInt(cbQRueckwaertsLinks), _
			CInt(cbQRueckwaertsRechts), CInt(cbQVorwaertsLinks), CInt(cbGefahrbremsung), CInt(cbRollenSchalten), CInt(cbBremsSchalten), CInt(cbBremsuebung), CInt(cbDegressiv), CInt(cbZielbremsung), CInt(cbGefahrsituation), CInt(cbGefaelle), _
			CInt(cbAnhalten), CInt(cbAnfahren), CInt(cbRueckwaerts), CInt(cbSichern), CInt(cbSchalten), CInt(cbSteigung), CInt(cbStAnhalten), CInt(cbStAnfahren), CInt(cbStRueckwaerts), CInt(cbStSichern), CInt(cbStSchalten), CInt(cbTastgeschw), CInt(cbBedienKontroll), _
			CInt(cbOertlichBesonder), CInt(cbFahrbahnbenutzung), CInt(cbEinordnen), CInt(cbMarkierungen), CInt(cbFahrstreifenwechsel), CInt(cbLinks), CInt(cbRechts), CInt(cbVorbeifUeberholen), CInt(cbAbbiegen), CInt(cbABRechts), CInt(cbABLinks), CInt(cbMehrspurig), _
			CInt(cbRadweg), CInt(cbSonderstreifen), CInt(cbStrassenbahn), CInt(cbEinbahnstrasse), CInt(cbVorfahrt), CInt(cbRechtsVorLinks), CInt(cbGruenpfeil), CInt(cbPolizeibeamte), CInt(cbGruenpfeilSchild), CInt(cbGeschwAbstand), CInt(cbSituationVerkehrstn), _
			CInt(cbFussgaengerueberweg), CInt(cbOeffentlVerkehrsm), CInt(cbAeltereBehinderte), CInt(cbEinbahnstrRadfahrer), CInt(cbKinder), CInt(cbSchulbus), CInt(cbRadfahrerMofa), CInt(cbVerkehrsberuhigt), CInt(cbSchwierigeVerkehrsf), CInt(cbEngpass), _
			CInt(cbKreisverkehr), CInt(cbBahnuebergangWarte), CInt(cbKritischeVerkehrss), CInt(cbHauptverkehrszt), CInt(cbPartnerVerhalten), CInt(cbSchwungNutzen), CInt(cbFussgaengerSchutzb), CInt(cbAngepassteGeschw), CInt(cbAbstand), CInt(cbULVorne), _
			CInt(cbULHinten), CInt(cbULSeitlich), CInt(cbBeobachtSpiegel), CInt(cbVerkehrszeichen), CInt(cbKreuzungEinmuend), CInt(cbKurven), CInt(cbSteigungen), CInt(cbULGefaelle), CInt(cbAlleen), CInt(cbUeberholen), CInt(cbBesondereSituat), CInt(cbLiegenblSichern), _
			CInt(cbEinfahrenOrtsch), CInt(cbFussgaenger), CInt(cbWildTiere), CInt(cbBesondereAnford), CInt(cbLeistungsgrenze), CInt(cbOrientierung), CInt(cbAblenkung), CInt(cbFahrtplanung), CInt(cbEinfahrtAB), CInt(cbABFahrbahnwechsel), CInt(cbGeschwindigkeit), _
			CInt(cbABAbstand), CInt(cbABVorne), CInt(cbABHinten), CInt(cbABSeitlich), CInt(cbABUeberholen), CInt(cbSchilder), CInt(cbVorbeifahren), CInt(cbRastParkTank), CInt(cbVerhUnfall), CInt(cbDichterVerkehr), CInt(cbBesonderSituat), CInt(cbBesonderAnford), _
			CInt(cbABLeistungsgrenze), CInt(cbKonfliktSitua), CInt(cbABAblenkung), CInt(cbBeleuchtung), CInt(cbKontrolle), CInt(cbEinstell), CInt(cbBenutzung), CInt(cbFernlicht), CInt(cbVerlassenBAB), CInt(cbBeleuchtStrasse), CInt(cbUnbeleuchtStrasse), CInt(cbParken), CInt(cbDUBesonderSituat), _
			CInt(cbSchlechteWitterung), CInt(cbTiere), CInt(cbBahnuebergaenge), CInt(cbUnbelVerkehrTN), CInt(cbDUBesonderAnfor), CInt(cbBlendung), CInt(cbDUOrientierung), CInt(cbAbschlussbesp), CInt(cbSelbstFahren), CInt(cbInnerorts), CInt(cbAusserorts), CInt(cbVerantwFahren), CInt(cbTestfPruef), _
			CInt(cbFAKT), CInt(cbAndere), CInt(cbWiederholung), CInt(cbLeistungsbew), CInt(cbReifen), CInt(cbEinAusschalten), CInt(cbFunktionPruefen), CInt(cbStandlicht), CInt(cbNebelschluss), CInt(cbBlinker), CInt(cbAbblendlicht), CInt(cbWarnblicke), CInt(cbHupe), CInt(cbBSFernlicht), CInt(cbSchlussLeuchte), _
			CInt(cbBremslicht), CInt(cbKontrollLBenenn), CInt(cbRueckstrahler), CInt(cbVorhandensein), CInt(cbBeschaedigung), CInt(cbLenkung), CInt(cbLenkschlEntriegeln), CInt(cbPruefLenkSpiel), CInt(cbFunktBremse), CInt(cbBetriebsBremse), CInt(cbFeststellBremse), _
			CInt(cbAnlegenGurt), CInt(cbRichtigSitz), CInt(cbEinstellRueckspiegel), CInt(cbEinKopfstuetze), CInt(cbEinLenkrad), CInt(cbBedienenAgg), CInt(cbHeizung), CInt(cbHeckHeizung), CInt(cbBehSonderaus), CInt(cbLueftung), CInt(cbKlimaanlage), CInt(cbEnergieNutzung), _
			CInt(cbKeineUnnVerbr), CInt(cbRechtztAbsch), CInt(cbMotorraum), CInt(cbMotoroel), CInt(cbKuehlmittel), CInt(cbScheibenwaschm), CInt(cbTanken), CInt(cbBremsen), CInt(cbSicherungsmittel), CInt(cbWarndreieck), CInt(cbBordwerkzeug), CInt(cbZusaetzlichAus), CInt(cbVerbandskasten), CInt(cbAussenkontrolle), _
			CInt(cbScheibenWischer), CInt(cbKennzeichen), CInt(cbCheckSpiegel), CInt(cbCheckBeleuchtung), CInt(cbLadung), CInt(cbLadungssicherung), CInt(cbKenntlichmachung), CInt(cbFahreSchlWitt), CInt(cbWittLueftung), CInt(cbWittScheiben), CInt(cbRegen), _
			CInt(cbWasserlachen), CInt(cbWindSturm), CInt(cbMatchSchnee), CInt(cbEis), CInt(cbWittBeleuchtung), etNotizen, iAusbKontrolleOID)
	Else
		Return DBUtils.InsertBVFData(sDate, sSchueler, CInt(cbBesonderhEinsteigen), CInt(cbEinstellen), CInt(cbLenkrad), CInt(cbSpiegel), CInt(cbKopfstuetze), CInt(cbSitz), CInt(cbLenkradhaltung), CInt(cbPedale), CInt(cbGurt), CInt(cbSchaltWaehlhebel), CInt(cbZuendschloss), CInt(cbMotorAnlassen), _
			CInt(cbAnfahrAnhalte), CInt(cbSchaltuebg), CInt(cbHoch1_2), CInt(cbHoch2_3), CInt(cbHoch3_4), CInt(cbRunter4_3), CInt(cbRunter3_2), CInt(cbRunter2_1), CInt(cbRunter4_2), CInt(cbRunter4_1), CInt(cbRunter3_1), CInt(cbLenkuebung), CInt(cbUmkehren), CInt(cbEinparkenLaengs), _
			CInt(cbLVorwaertsRechts), CInt(cbLRueckwaertsLinks), CInt(cbLRueckwaertsRechts), CInt(cbLVorwaertsLinks), CInt(cbRueckwaertsfahren), CInt(cbEinparkenQuer), CInt(cbQVorwaertsRechts), CInt(cbQRueckwaertsLinks), _
			CInt(cbQRueckwaertsRechts), CInt(cbQVorwaertsLinks), CInt(cbGefahrbremsung), CInt(cbRollenSchalten), CInt(cbBremsSchalten), CInt(cbBremsuebung), CInt(cbDegressiv), CInt(cbZielbremsung), CInt(cbGefahrsituation), CInt(cbGefaelle), _
			CInt(cbAnhalten), CInt(cbAnfahren), CInt(cbRueckwaerts), CInt(cbSichern), CInt(cbSchalten), CInt(cbSteigung), CInt(cbStAnhalten), CInt(cbStAnfahren), CInt(cbStRueckwaerts), CInt(cbStSichern), CInt(cbStSchalten), CInt(cbTastgeschw), CInt(cbBedienKontroll), _
			CInt(cbOertlichBesonder), CInt(cbFahrbahnbenutzung), CInt(cbEinordnen), CInt(cbMarkierungen), CInt(cbFahrstreifenwechsel), CInt(cbLinks), CInt(cbRechts), CInt(cbVorbeifUeberholen), CInt(cbAbbiegen), CInt(cbABRechts), CInt(cbABLinks), CInt(cbMehrspurig), _
			CInt(cbRadweg), CInt(cbSonderstreifen), CInt(cbStrassenbahn), CInt(cbEinbahnstrasse), CInt(cbVorfahrt), CInt(cbRechtsVorLinks), CInt(cbGruenpfeil), CInt(cbPolizeibeamte), CInt(cbGruenpfeilSchild), CInt(cbGeschwAbstand), CInt(cbSituationVerkehrstn), _
			CInt(cbFussgaengerueberweg), CInt(cbOeffentlVerkehrsm), CInt(cbAeltereBehinderte), CInt(cbEinbahnstrRadfahrer), CInt(cbKinder), CInt(cbSchulbus), CInt(cbRadfahrerMofa), CInt(cbVerkehrsberuhigt), CInt(cbSchwierigeVerkehrsf), CInt(cbEngpass), _
			CInt(cbKreisverkehr), CInt(cbBahnuebergangWarte), CInt(cbKritischeVerkehrss), CInt(cbHauptverkehrszt), CInt(cbPartnerVerhalten), CInt(cbSchwungNutzen), CInt(cbFussgaengerSchutzb), CInt(cbAngepassteGeschw), CInt(cbAbstand), CInt(cbULVorne), _
			CInt(cbULHinten), CInt(cbULSeitlich), CInt(cbBeobachtSpiegel), CInt(cbVerkehrszeichen), CInt(cbKreuzungEinmuend), CInt(cbKurven), CInt(cbSteigungen), CInt(cbULGefaelle), CInt(cbAlleen), CInt(cbUeberholen), CInt(cbBesondereSituat), CInt(cbLiegenblSichern), _
			CInt(cbEinfahrenOrtsch), CInt(cbFussgaenger), CInt(cbWildTiere), CInt(cbBesondereAnford), CInt(cbLeistungsgrenze), CInt(cbOrientierung), CInt(cbAblenkung), CInt(cbFahrtplanung), CInt(cbEinfahrtAB), CInt(cbABFahrbahnwechsel), CInt(cbGeschwindigkeit), _
			CInt(cbABAbstand), CInt(cbABVorne), CInt(cbABHinten), CInt(cbABSeitlich), CInt(cbABUeberholen), CInt(cbSchilder), CInt(cbVorbeifahren), CInt(cbRastParkTank), CInt(cbVerhUnfall), CInt(cbDichterVerkehr), CInt(cbBesonderSituat), CInt(cbBesonderAnford), _
			CInt(cbABLeistungsgrenze), CInt(cbKonfliktSitua), CInt(cbABAblenkung), CInt(cbBeleuchtung), CInt(cbKontrolle), CInt(cbEinstell), CInt(cbBenutzung), CInt(cbFernlicht), CInt(cbVerlassenBAB), CInt(cbBeleuchtStrasse), CInt(cbUnbeleuchtStrasse), CInt(cbParken), CInt(cbDUBesonderSituat), _
			CInt(cbSchlechteWitterung), CInt(cbTiere), CInt(cbBahnuebergaenge), CInt(cbUnbelVerkehrTN), CInt(cbDUBesonderAnfor), CInt(cbBlendung), CInt(cbDUOrientierung), CInt(cbAbschlussbesp), CInt(cbSelbstFahren), CInt(cbInnerorts), CInt(cbAusserorts), CInt(cbVerantwFahren), CInt(cbTestfPruef), _
			CInt(cbFAKT), CInt(cbAndere), CInt(cbWiederholung), CInt(cbLeistungsbew), CInt(cbReifen), CInt(cbEinAusschalten), CInt(cbFunktionPruefen), CInt(cbStandlicht), CInt(cbNebelschluss), CInt(cbBlinker), CInt(cbAbblendlicht), CInt(cbWarnblicke), CInt(cbHupe), CInt(cbBSFernlicht), CInt(cbSchlussLeuchte), _
			CInt(cbBremslicht), CInt(cbKontrollLBenenn), CInt(cbRueckstrahler), CInt(cbVorhandensein), CInt(cbBeschaedigung), CInt(cbLenkung), CInt(cbLenkschlEntriegeln), CInt(cbPruefLenkSpiel), CInt(cbFunktBremse), CInt(cbBetriebsBremse), CInt(cbFeststellBremse), _
			CInt(cbAnlegenGurt), CInt(cbRichtigSitz), CInt(cbEinstellRueckspiegel), CInt(cbEinKopfstuetze), CInt(cbEinLenkrad), CInt(cbBedienenAgg), CInt(cbHeizung), CInt(cbHeckHeizung), CInt(cbBehSonderaus), CInt(cbLueftung), CInt(cbKlimaanlage), CInt(cbEnergieNutzung), _
			CInt(cbKeineUnnVerbr), CInt(cbRechtztAbsch), CInt(cbMotorraum), CInt(cbMotoroel), CInt(cbKuehlmittel), CInt(cbScheibenwaschm), CInt(cbTanken), CInt(cbBremsen), CInt(cbSicherungsmittel), CInt(cbWarndreieck), CInt(cbBordwerkzeug), CInt(cbZusaetzlichAus), CInt(cbVerbandskasten), CInt(cbAussenkontrolle), _
			CInt(cbScheibenWischer), CInt(cbKennzeichen), CInt(cbCheckSpiegel), CInt(cbCheckBeleuchtung), CInt(cbLadung), CInt(cbLadungssicherung), CInt(cbKenntlichmachung), CInt(cbFahreSchlWitt), CInt(cbWittLueftung), CInt(cbWittScheiben), CInt(cbRegen), _
			CInt(cbWasserlachen), CInt(cbWindSturm), CInt(cbMatchSchnee), CInt(cbEis), CInt(cbWittBeleuchtung), etNotizen)
	End If
End Sub

Sub SetBVFFelder(sDate As String, sSchueler As String, cbBesonderhEinsteigen As CheckBox, cbEinstellen As CheckBox, cbLenkrad As CheckBox, cbSpiegel As CheckBox, cbKopfstuetze As CheckBox, cbSitz As CheckBox, cbLenkradhaltung As CheckBox, cbPedale As CheckBox, cbGurt As CheckBox, cbSchaltWaehlhebel As CheckBox, cbZuendschloss As CheckBox, cbMotorAnlassen As CheckBox, _
			cbAnfahrAnhalte As CheckBox, cbSchaltuebg As CheckBox, cbHoch1_2 As CheckBox, cbHoch2_3 As CheckBox, cbHoch3_4 As CheckBox, cbRunter4_3 As CheckBox, cbRunter3_2 As CheckBox, cbRunter2_1 As CheckBox, cbRunter4_2 As CheckBox, cbRunter4_1 As CheckBox, cbRunter3_1 As CheckBox, cbLenkuebung As CheckBox, cbUmkehren As CheckBox, cbEinparkenLaengs As CheckBox, _
			cbLVorwaertsRechts As CheckBox, cbLRueckwaertsLinks As CheckBox, cbLRueckwaertsRechts As CheckBox, cbLVorwaertsLinks As CheckBox, cbRueckwaertsfahren As CheckBox, cbEinparkenQuer As CheckBox, cbQVorwaertsRechts As CheckBox, cbQRueckwaertsLinks As CheckBox, _
			cbQRueckwaertsRechts As CheckBox, cbQVorwaertsLinks As CheckBox, cbGefahrbremsung As CheckBox, cbRollenSchalten As CheckBox, cbBremsSchalten As CheckBox, cbBremsuebung As CheckBox, cbDegressiv As CheckBox, cbZielbremsung As CheckBox, cbGefahrsituation As CheckBox, cbGefaelle As CheckBox, _
			cbAnhalten As CheckBox, cbAnfahren As CheckBox, cbRueckwaerts As CheckBox, cbSichern As CheckBox, cbSchalten As CheckBox, cbSteigung As CheckBox, cbStAnhalten As CheckBox, cbStAnfahren As CheckBox, cbStRueckwaerts As CheckBox, cbStSichern As CheckBox, cbStSchalten As CheckBox, cbTastgeschw As CheckBox, cbBedienKontroll As CheckBox, _
			cbOertlichBesonder As CheckBox, cbFahrbahnbenutzung As CheckBox, cbEinordnen As CheckBox, cbMarkierungen As CheckBox, cbFahrstreifenwechsel As CheckBox, cbLinks As CheckBox, cbRechts As CheckBox, cbVorbeifUeberholen As CheckBox, cbAbbiegen As CheckBox, cbABRechts As CheckBox, cbABLinks As CheckBox, cbMehrspurig As CheckBox, _
			cbRadweg As CheckBox, cbSonderstreifen As CheckBox, cbStrassenbahn As CheckBox, cbEinbahnstrasse As CheckBox, cbVorfahrt As CheckBox, cbRechtsVorLinks As CheckBox, cbGruenpfeil As CheckBox, cbPolizeibeamte As CheckBox, cbGruenpfeilSchild As CheckBox, cbGeschwAbstand As CheckBox, cbSituationVerkehrstn As CheckBox, _
			cbFussgaengerueberweg As CheckBox, cbOeffentlVerkehrsm As CheckBox, cbAeltereBehinderte As CheckBox, cbEinbahnstrRadfahrer As CheckBox, cbKinder As CheckBox, cbSchulbus As CheckBox, cbRadfahrerMofa As CheckBox, cbVerkehrsberuhigt As CheckBox, cbSchwierigeVerkehrsf As CheckBox, cbEngpass As CheckBox, _
			cbKreisverkehr As CheckBox, cbBahnuebergangWarte As CheckBox, cbKritischeVerkehrss As CheckBox, cbHauptverkehrszt As CheckBox, cbPartnerVerhalten As CheckBox, cbSchwungNutzen As CheckBox, cbFussgaengerSchutzb As CheckBox, cbAngepassteGeschw As CheckBox, cbAbstand As CheckBox, cbULVorne As CheckBox, _
			cbULHinten As CheckBox, cbULSeitlich As CheckBox, cbBeobachtSpiegel As CheckBox, cbVerkehrszeichen As CheckBox, cbKreuzungEinmuend As CheckBox, cbKurven As CheckBox, cbSteigungen As CheckBox, cbULGefaelle As CheckBox, cbAlleen As CheckBox, cbUeberholen As CheckBox, cbBesondereSituat As CheckBox, cbLiegenblSichern As CheckBox, _
			cbEinfahrenOrtsch As CheckBox, cbFussgaenger As CheckBox, cbWildTiere As CheckBox, cbBesondereAnford As CheckBox, cbLeistungsgrenze As CheckBox, cbOrientierung As CheckBox, cbAblenkung As CheckBox, cbFahrtplanung As CheckBox, cbEinfahrtAB As CheckBox, cbABFahrbahnwechsel As CheckBox, cbGeschwindigkeit As CheckBox, _
			cbABAbstand As CheckBox, cbABVorne As CheckBox, cbABHinten As CheckBox, cbABSeitlich As CheckBox, cbABUeberholen As CheckBox, cbSchilder As CheckBox, cbVorbeifahren As CheckBox, cbRastParkTank As CheckBox, cbVerhUnfall As CheckBox, cbDichterVerkehr As CheckBox, cbBesonderSituat As CheckBox, cbBesonderAnford As CheckBox, _
			cbABLeistungsgrenze As CheckBox, cbKonfliktSitua As CheckBox, cbABAblenkung As CheckBox, cbBeleuchtung As CheckBox, cbKontrolle As CheckBox, cbEinstell As CheckBox, cbBenutzung As CheckBox, cbFernlicht As CheckBox, cbVerlassenBAB As CheckBox, cbBeleuchtStrasse As CheckBox, cbUnbeleuchtStrasse As CheckBox, cbParken As CheckBox, cbDUBesonderSituat As CheckBox, _
			cbSchlechteWitterung As CheckBox, cbTiere As CheckBox, cbBahnuebergaenge As CheckBox, cbUnbelVerkehrTN As CheckBox, cbDUBesonderAnfor As CheckBox, cbBlendung As CheckBox, cbDUOrientierung As CheckBox, cbAbschlussbesp As CheckBox, cbSelbstFahren As CheckBox, cbInnerorts As CheckBox, cbAusserorts As CheckBox, cbVerantwFahren As CheckBox, cbTestfPruef As CheckBox, _
			cbFAKT As CheckBox, cbAndere As CheckBox, cbWiederholung As CheckBox, cbLeistungsbew As CheckBox, cbReifen As CheckBox, cbEinAusschalten As CheckBox, cbFunktionPruefen As CheckBox, cbStandlicht As CheckBox, cbNebelschluss As CheckBox, cbBlinker As CheckBox, cbAbblendlicht As CheckBox, cbWarnblicke As CheckBox, cbHupe As CheckBox, cbBSFernlicht As CheckBox, cbSchlussLeuchte As CheckBox, _
			cbBremslicht As CheckBox, cbKontrollLBenenn As CheckBox, cbRueckstrahler As CheckBox, cbVorhandensein As CheckBox, cbBeschaedigung As CheckBox, cbLenkung As CheckBox, cbLenkschlEntriegeln As CheckBox, cbPruefLenkSpiel As CheckBox, cbFunktBremse As CheckBox, cbBetriebsBremse As CheckBox, cbFeststellBremse As CheckBox, _
			cbAnlegenGurt As CheckBox, cbRichtigSitz As CheckBox, cbEinstellRueckspiegel As CheckBox, cbEinKopfstuetze As CheckBox, cbEinLenkrad As CheckBox, cbBedienenAgg As CheckBox, cbHeizung As CheckBox, cbHeckHeizung As CheckBox, cbBehSonderaus As CheckBox, cbLueftung As CheckBox, cbKlimaanlage As CheckBox, cbEnergieNutzung As CheckBox, _
			cbKeineUnnVerbr As CheckBox, cbRechtztAbsch As CheckBox, cbMotorraum As CheckBox, cbMotoroel As CheckBox, cbKuehlmittel As CheckBox, cbScheibenwaschm As CheckBox, cbTanken As CheckBox, cbBremsen As CheckBox, cbSicherungsmittel As CheckBox, cbWarndreieck As CheckBox, cbBordwerkzeug As CheckBox, cbZusaetzlichAus As CheckBox, cbVerbandskasten As CheckBox, cbAussenkontrolle As CheckBox, _
			cbScheibenWischer As CheckBox, cbKennzeichen As CheckBox, cbCheckSpiegel As CheckBox, cbCheckBeleuchtung As CheckBox, cbLadung As CheckBox, cbLadungssicherung As CheckBox, cbKenntlichmachung As CheckBox, cbFahreSchlWitt As CheckBox, cbWittLueftung As CheckBox, cbWittScheiben As CheckBox, cbRegen As CheckBox, _
			cbWasserlachen As CheckBox, cbWindSturm As CheckBox, cbMatchSchnee As CheckBox, cbEis As CheckBox, cbWittBeleuchtung As CheckBox, etNotizen As EditText)
	'Prüfen, ob Daten zum ausgewählten Datum vorhanden sind
	Dim iBVFDaten As Int
	iBVFDaten = DBUtils.CheckBVFDataExists(sDate, sSchueler)
	If iBVFDaten = 0 Then
		BVFFelderLeeren(cbBesonderhEinsteigen, cbEinstellen, cbLenkrad, cbSpiegel, cbKopfstuetze, cbSitz, cbLenkradhaltung, cbPedale, cbGurt, cbSchaltWaehlhebel, cbZuendschloss, cbMotorAnlassen, _
			cbAnfahrAnhalte, cbSchaltuebg, cbHoch1_2, cbHoch2_3, cbHoch3_4, cbRunter4_3, cbRunter3_2, cbRunter2_1, cbRunter4_2, cbRunter4_1, cbRunter3_1, cbLenkuebung, cbUmkehren, cbEinparkenLaengs, _
			cbLVorwaertsRechts, cbLRueckwaertsLinks, cbLRueckwaertsRechts, cbLVorwaertsLinks, cbRueckwaertsfahren, cbEinparkenQuer, cbQVorwaertsRechts, cbQRueckwaertsLinks, _
			cbQRueckwaertsRechts, cbQVorwaertsLinks, cbGefahrbremsung, cbRollenSchalten, cbBremsSchalten, cbBremsuebung, cbDegressiv, cbZielbremsung, cbGefahrsituation, cbGefaelle, _
			cbAnhalten, cbAnfahren, cbRueckwaerts, cbSichern, cbSchalten, cbSteigung, cbStAnhalten, cbStAnfahren, cbStRueckwaerts, cbStSichern, cbStSchalten, cbTastgeschw, cbBedienKontroll, _
			cbOertlichBesonder, cbFahrbahnbenutzung, cbEinordnen, cbMarkierungen, cbFahrstreifenwechsel, cbLinks, cbRechts, cbVorbeifUeberholen, cbAbbiegen, cbABRechts, cbABLinks, cbMehrspurig, _
			cbRadweg, cbSonderstreifen, cbStrassenbahn, cbEinbahnstrasse, cbVorfahrt, cbRechtsVorLinks, cbGruenpfeil, cbPolizeibeamte, cbGruenpfeilSchild, cbGeschwAbstand, cbSituationVerkehrstn, _
			cbFussgaengerueberweg, cbOeffentlVerkehrsm, cbAeltereBehinderte, cbEinbahnstrRadfahrer, cbKinder, cbSchulbus, cbRadfahrerMofa, cbVerkehrsberuhigt, cbSchwierigeVerkehrsf, cbEngpass, _
			cbKreisverkehr, cbBahnuebergangWarte, cbKritischeVerkehrss, cbHauptverkehrszt, cbPartnerVerhalten, cbSchwungNutzen, cbFussgaengerSchutzb, cbAngepassteGeschw, cbAbstand, cbULVorne, _
			cbULHinten, cbULSeitlich, cbBeobachtSpiegel, cbVerkehrszeichen, cbKreuzungEinmuend, cbKurven, cbSteigungen, cbULGefaelle, cbAlleen, cbUeberholen, cbBesondereSituat, cbLiegenblSichern, _
			cbEinfahrenOrtsch, cbFussgaenger, cbWildTiere, cbBesondereAnford, cbLeistungsgrenze, cbOrientierung, cbAblenkung, cbFahrtplanung, cbEinfahrtAB, cbABFahrbahnwechsel, cbGeschwindigkeit, _
			cbABAbstand, cbABVorne, cbABHinten, cbABSeitlich, cbABUeberholen, cbSchilder, cbVorbeifahren, cbRastParkTank, cbVerhUnfall, cbDichterVerkehr, cbBesonderSituat, cbBesonderAnford, _
			cbABLeistungsgrenze, cbKonfliktSitua, cbABAblenkung, cbBeleuchtung, cbKontrolle, cbEinstell, cbBenutzung, cbFernlicht, cbVerlassenBAB, cbBeleuchtStrasse, cbUnbeleuchtStrasse, cbParken, cbDUBesonderSituat, _
			cbSchlechteWitterung, cbTiere, cbBahnuebergaenge, cbUnbelVerkehrTN, cbDUBesonderAnfor, cbBlendung, cbDUOrientierung, cbAbschlussbesp, cbSelbstFahren, cbInnerorts, cbAusserorts, cbVerantwFahren, cbTestfPruef, _
			cbFAKT, cbAndere, cbWiederholung, cbLeistungsbew, cbReifen, cbEinAusschalten, cbFunktionPruefen, cbStandlicht, cbNebelschluss, cbBlinker, cbAbblendlicht, cbWarnblicke, cbHupe, cbBSFernlicht, cbSchlussLeuchte, _
			cbBremslicht, cbKontrollLBenenn, cbRueckstrahler, cbVorhandensein, cbBeschaedigung, cbLenkung, cbLenkschlEntriegeln, cbPruefLenkSpiel, cbFunktBremse, cbBetriebsBremse, cbFeststellBremse, _
			cbAnlegenGurt, cbRichtigSitz, cbEinstellRueckspiegel, cbEinKopfstuetze, cbEinLenkrad, cbBedienenAgg, cbHeizung, cbHeckHeizung, cbBehSonderaus, cbLueftung, cbKlimaanlage, cbEnergieNutzung, _
			cbKeineUnnVerbr, cbRechtztAbsch, cbMotorraum, cbMotoroel, cbKuehlmittel, cbScheibenwaschm, cbTanken, cbBremsen, cbSicherungsmittel, cbWarndreieck, cbBordwerkzeug, cbZusaetzlichAus, cbVerbandskasten, cbAussenkontrolle, _
			cbScheibenWischer, cbKennzeichen, cbCheckSpiegel, cbCheckBeleuchtung, cbLadung, cbLadungssicherung, cbKenntlichmachung, cbFahreSchlWitt, cbWittLueftung, cbWittScheiben, cbRegen, _
			cbWasserlachen, cbWindSturm, cbMatchSchnee, cbEis, cbWittBeleuchtung, etNotizen)
	Else
		DBUtils.SetBVFFelder(cbBesonderhEinsteigen, cbEinstellen, cbLenkrad, cbSpiegel, cbKopfstuetze, cbSitz, cbLenkradhaltung, cbPedale, cbGurt, cbSchaltWaehlhebel, cbZuendschloss, cbMotorAnlassen, _
			cbAnfahrAnhalte, cbSchaltuebg, cbHoch1_2, cbHoch2_3, cbHoch3_4, cbRunter4_3, cbRunter3_2, cbRunter2_1, cbRunter4_2, cbRunter4_1, cbRunter3_1, cbLenkuebung, cbUmkehren, cbEinparkenLaengs, _
			cbLVorwaertsRechts, cbLRueckwaertsLinks, cbLRueckwaertsRechts, cbLVorwaertsLinks, cbRueckwaertsfahren, cbEinparkenQuer, cbQVorwaertsRechts, cbQRueckwaertsLinks, _
			cbQRueckwaertsRechts, cbQVorwaertsLinks, cbGefahrbremsung, cbRollenSchalten, cbBremsSchalten, cbBremsuebung, cbDegressiv, cbZielbremsung, cbGefahrsituation, cbGefaelle, _
			cbAnhalten, cbAnfahren, cbRueckwaerts, cbSichern, cbSchalten, cbSteigung, cbStAnhalten, cbStAnfahren, cbStRueckwaerts, cbStSichern, cbStSchalten, cbTastgeschw, cbBedienKontroll, _
			cbOertlichBesonder, cbFahrbahnbenutzung, cbEinordnen, cbMarkierungen, cbFahrstreifenwechsel, cbLinks, cbRechts, cbVorbeifUeberholen, cbAbbiegen, cbABRechts, cbABLinks, cbMehrspurig, _
			cbRadweg, cbSonderstreifen, cbStrassenbahn, cbEinbahnstrasse, cbVorfahrt, cbRechtsVorLinks, cbGruenpfeil, cbPolizeibeamte, cbGruenpfeilSchild, cbGeschwAbstand, cbSituationVerkehrstn, _
			cbFussgaengerueberweg, cbOeffentlVerkehrsm, cbAeltereBehinderte, cbEinbahnstrRadfahrer, cbKinder, cbSchulbus, cbRadfahrerMofa, cbVerkehrsberuhigt, cbSchwierigeVerkehrsf, cbEngpass, _
			cbKreisverkehr, cbBahnuebergangWarte, cbKritischeVerkehrss, cbHauptverkehrszt, cbPartnerVerhalten, cbSchwungNutzen, cbFussgaengerSchutzb, cbAngepassteGeschw, cbAbstand, cbULVorne, _
			cbULHinten, cbULSeitlich, cbBeobachtSpiegel, cbVerkehrszeichen, cbKreuzungEinmuend, cbKurven, cbSteigungen, cbULGefaelle, cbAlleen, cbUeberholen, cbBesondereSituat, cbLiegenblSichern, _
			cbEinfahrenOrtsch, cbFussgaenger, cbWildTiere, cbBesondereAnford, cbLeistungsgrenze, cbOrientierung, cbAblenkung, cbFahrtplanung, cbEinfahrtAB, cbABFahrbahnwechsel, cbGeschwindigkeit, _
			cbABAbstand, cbABVorne, cbABHinten, cbABSeitlich, cbABUeberholen, cbSchilder, cbVorbeifahren, cbRastParkTank, cbVerhUnfall, cbDichterVerkehr, cbBesonderSituat, cbBesonderAnford, _
			cbABLeistungsgrenze, cbKonfliktSitua, cbABAblenkung, cbBeleuchtung, cbKontrolle, cbEinstell, cbBenutzung, cbFernlicht, cbVerlassenBAB, cbBeleuchtStrasse, cbUnbeleuchtStrasse, cbParken, cbDUBesonderSituat, _
			cbSchlechteWitterung, cbTiere, cbBahnuebergaenge, cbUnbelVerkehrTN, cbDUBesonderAnfor, cbBlendung, cbDUOrientierung, cbAbschlussbesp, cbSelbstFahren, cbInnerorts, cbAusserorts, cbVerantwFahren, cbTestfPruef, _
			cbFAKT, cbAndere, cbWiederholung, cbLeistungsbew, cbReifen, cbEinAusschalten, cbFunktionPruefen, cbStandlicht, cbNebelschluss, cbBlinker, cbAbblendlicht, cbWarnblicke, cbHupe, cbBSFernlicht, cbSchlussLeuchte, _
			cbBremslicht, cbKontrollLBenenn, cbRueckstrahler, cbVorhandensein, cbBeschaedigung, cbLenkung, cbLenkschlEntriegeln, cbPruefLenkSpiel, cbFunktBremse, cbBetriebsBremse, cbFeststellBremse, _
			cbAnlegenGurt, cbRichtigSitz, cbEinstellRueckspiegel, cbEinKopfstuetze, cbEinLenkrad, cbBedienenAgg, cbHeizung, cbHeckHeizung, cbBehSonderaus, cbLueftung, cbKlimaanlage, cbEnergieNutzung, _
			cbKeineUnnVerbr, cbRechtztAbsch, cbMotorraum, cbMotoroel, cbKuehlmittel, cbScheibenwaschm, cbTanken, cbBremsen, cbSicherungsmittel, cbWarndreieck, cbBordwerkzeug, cbZusaetzlichAus, cbVerbandskasten, cbAussenkontrolle, _
			cbScheibenWischer, cbKennzeichen, cbCheckSpiegel, cbCheckBeleuchtung, cbLadung, cbLadungssicherung, cbKenntlichmachung, cbFahreSchlWitt, cbWittLueftung, cbWittScheiben, cbRegen, _
			cbWasserlachen, cbWindSturm, cbMatchSchnee, cbEis, cbWittBeleuchtung, etNotizen, iBVFDaten)
	End If
End Sub

Sub BVFFelderLeeren(cbBesonderhEinsteigen As CheckBox, cbEinstellen As CheckBox, cbLenkrad As CheckBox, cbSpiegel As CheckBox, cbKopfstuetze As CheckBox, cbSitz As CheckBox, cbLenkradhaltung As CheckBox, cbPedale As CheckBox, cbGurt As CheckBox, cbSchaltWaehlhebel As CheckBox, cbZuendschloss As CheckBox, cbMotorAnlassen As CheckBox, _
			cbAnfahrAnhalte As CheckBox, cbSchaltuebg As CheckBox, cbHoch1_2 As CheckBox, cbHoch2_3 As CheckBox, cbHoch3_4 As CheckBox, cbRunter4_3 As CheckBox, cbRunter3_2 As CheckBox, cbRunter2_1 As CheckBox, cbRunter4_2 As CheckBox, cbRunter4_1 As CheckBox, cbRunter3_1 As CheckBox, cbLenkuebung As CheckBox, cbUmkehren As CheckBox, cbEinparkenLaengs As CheckBox, _
			cbLVorwaertsRechts As CheckBox, cbLRueckwaertsLinks As CheckBox, cbLRueckwaertsRechts As CheckBox, cbLVorwaertsLinks As CheckBox, cbRueckwaertsfahren As CheckBox, cbEinparkenQuer As CheckBox, cbQVorwaertsRechts As CheckBox, cbQRueckwaertsLinks As CheckBox, _
			cbQRueckwaertsRechts As CheckBox, cbQVorwaertsLinks As CheckBox, cbGefahrbremsung As CheckBox, cbRollenSchalten As CheckBox, cbBremsSchalten As CheckBox, cbBremsuebung As CheckBox, cbDegressiv As CheckBox, cbZielbremsung As CheckBox, cbGefahrsituation As CheckBox, cbGefaelle As CheckBox, _
			cbAnhalten As CheckBox, cbAnfahren As CheckBox, cbRueckwaerts As CheckBox, cbSichern As CheckBox, cbSchalten As CheckBox, cbSteigung As CheckBox, cbStAnhalten As CheckBox, cbStAnfahren As CheckBox, cbStRueckwaerts As CheckBox, cbStSichern As CheckBox, cbStSchalten As CheckBox, cbTastgeschw As CheckBox, cbBedienKontroll As CheckBox, _
			cbOertlichBesonder As CheckBox, cbFahrbahnbenutzung As CheckBox, cbEinordnen As CheckBox, cbMarkierungen As CheckBox, cbFahrstreifenwechsel As CheckBox, cbLinks As CheckBox, cbRechts As CheckBox, cbVorbeifUeberholen As CheckBox, cbAbbiegen As CheckBox, cbABRechts As CheckBox, cbABLinks As CheckBox, cbMehrspurig As CheckBox, _
			cbRadweg As CheckBox, cbSonderstreifen As CheckBox, cbStrassenbahn As CheckBox, cbEinbahnstrasse As CheckBox, cbVorfahrt As CheckBox, cbRechtsVorLinks As CheckBox, cbGruenpfeil As CheckBox, cbPolizeibeamte As CheckBox, cbGruenpfeilSchild As CheckBox, cbGeschwAbstand As CheckBox, cbSituationVerkehrstn As CheckBox, _
			cbFussgaengerueberweg As CheckBox, cbOeffentlVerkehrsm As CheckBox, cbAeltereBehinderte As CheckBox, cbEinbahnstrRadfahrer As CheckBox, cbKinder As CheckBox, cbSchulbus As CheckBox, cbRadfahrerMofa As CheckBox, cbVerkehrsberuhigt As CheckBox, cbSchwierigeVerkehrsf As CheckBox, cbEngpass As CheckBox, _
			cbKreisverkehr As CheckBox, cbBahnuebergangWarte As CheckBox, cbKritischeVerkehrss As CheckBox, cbHauptverkehrszt As CheckBox, cbPartnerVerhalten As CheckBox, cbSchwungNutzen As CheckBox, cbFussgaengerSchutzb As CheckBox, cbAngepassteGeschw As CheckBox, cbAbstand As CheckBox, cbULVorne As CheckBox, _
			cbULHinten As CheckBox, cbULSeitlich As CheckBox, cbBeobachtSpiegel As CheckBox, cbVerkehrszeichen As CheckBox, cbKreuzungEinmuend As CheckBox, cbKurven As CheckBox, cbSteigungen As CheckBox, cbULGefaelle As CheckBox, cbAlleen As CheckBox, cbUeberholen As CheckBox, cbBesondereSituat As CheckBox, cbLiegenblSichern As CheckBox, _
			cbEinfahrenOrtsch As CheckBox, cbFussgaenger As CheckBox, cbWildTiere As CheckBox, cbBesondereAnford As CheckBox, cbLeistungsgrenze As CheckBox, cbOrientierung As CheckBox, cbAblenkung As CheckBox, cbFahrtplanung As CheckBox, cbEinfahrtAB As CheckBox, cbABFahrbahnwechsel As CheckBox, cbGeschwindigkeit As CheckBox, _
			cbABAbstand As CheckBox, cbABVorne As CheckBox, cbABHinten As CheckBox, cbABSeitlich As CheckBox, cbABUeberholen As CheckBox, cbSchilder As CheckBox, cbVorbeifahren As CheckBox, cbRastParkTank As CheckBox, cbVerhUnfall As CheckBox, cbDichterVerkehr As CheckBox, cbBesonderSituat As CheckBox, cbBesonderAnford As CheckBox, _
			cbABLeistungsgrenze As CheckBox, cbKonfliktSitua As CheckBox, cbABAblenkung As CheckBox, cbBeleuchtung As CheckBox, cbKontrolle As CheckBox, cbEinstell As CheckBox, cbBenutzung As CheckBox, cbFernlicht As CheckBox, cbVerlassenBAB As CheckBox, cbBeleuchtStrasse As CheckBox, cbUnbeleuchtStrasse As CheckBox, cbParken As CheckBox, cbDUBesonderSituat As CheckBox, _
			cbSchlechteWitterung As CheckBox, cbTiere As CheckBox, cbBahnuebergaenge As CheckBox, cbUnbelVerkehrTN As CheckBox, cbDUBesonderAnfor As CheckBox, cbBlendung As CheckBox, cbDUOrientierung As CheckBox, cbAbschlussbesp As CheckBox, cbSelbstFahren As CheckBox, cbInnerorts As CheckBox, cbAusserorts As CheckBox, cbVerantwFahren As CheckBox, cbTestfPruef As CheckBox, _
			cbFAKT As CheckBox, cbAndere As CheckBox, cbWiederholung As CheckBox, cbLeistungsbew As CheckBox, cbReifen As CheckBox, cbEinAusschalten As CheckBox, cbFunktionPruefen As CheckBox, cbStandlicht As CheckBox, cbNebelschluss As CheckBox, cbBlinker As CheckBox, cbAbblendlicht As CheckBox, cbWarnblicke As CheckBox, cbHupe As CheckBox, cbBSFernlicht As CheckBox, cbSchlussLeuchte As CheckBox, _
			cbBremslicht As CheckBox, cbKontrollLBenenn As CheckBox, cbRueckstrahler As CheckBox, cbVorhandensein As CheckBox, cbBeschaedigung As CheckBox, cbLenkung As CheckBox, cbLenkschlEntriegeln As CheckBox, cbPruefLenkSpiel As CheckBox, cbFunktBremse As CheckBox, cbBetriebsBremse As CheckBox, cbFeststellBremse As CheckBox, _
			cbAnlegenGurt As CheckBox, cbRichtigSitz As CheckBox, cbEinstellRueckspiegel As CheckBox, cbEinKopfstuetze As CheckBox, cbEinLenkrad As CheckBox, cbBedienenAgg As CheckBox, cbHeizung As CheckBox, cbHeckHeizung As CheckBox, cbBehSonderaus As CheckBox, cbLueftung As CheckBox, cbKlimaanlage As CheckBox, cbEnergieNutzung As CheckBox, _
			cbKeineUnnVerbr As CheckBox, cbRechtztAbsch As CheckBox, cbMotorraum As CheckBox, cbMotoroel As CheckBox, cbKuehlmittel As CheckBox, cbScheibenwaschm As CheckBox, cbTanken As CheckBox, cbBremsen As CheckBox, cbSicherungsmittel As CheckBox, cbWarndreieck As CheckBox, cbBordwerkzeug As CheckBox, cbZusaetzlichAus As CheckBox, cbVerbandskasten As CheckBox, cbAussenkontrolle As CheckBox, _
			cbScheibenWischer As CheckBox, cbKennzeichen As CheckBox, cbCheckSpiegel As CheckBox, cbCheckBeleuchtung As CheckBox, cbLadung As CheckBox, cbLadungssicherung As CheckBox, cbKenntlichmachung As CheckBox, cbFahreSchlWitt As CheckBox, cbWittLueftung As CheckBox, cbWittScheiben As CheckBox, cbRegen As CheckBox, _
			cbWasserlachen As CheckBox, cbWindSturm As CheckBox, cbMatchSchnee As CheckBox, cbEis As CheckBox, cbWittBeleuchtung As CheckBox, etNotizen As EditText)
	cbBesonderhEinsteigen.Checked = False
	cbEinstellen.Checked = False
	cbLenkrad.Checked = False
	cbSpiegel.Checked = False
	cbKopfstuetze.Checked = False
	cbSitz.Checked = False
	cbLenkradhaltung.Checked = False
	cbPedale.Checked = False
	cbGurt.Checked = False
	cbSchaltWaehlhebel.Checked = False
	cbZuendschloss.Checked = False
	cbMotorAnlassen.Checked = False
	cbAnfahrAnhalte.Checked = False
	cbSchaltuebg.Checked = False
	cbHoch1_2.Checked = False
	cbHoch2_3.Checked = False
	cbHoch3_4.Checked = False
	cbRunter4_3.Checked = False
	cbRunter3_2.Checked = False
	cbRunter2_1.Checked = False
	cbRunter4_2.Checked = False
	cbRunter4_1.Checked = False
	cbRunter3_1.Checked = False
	cbLenkuebung.Checked = False
	cbUmkehren.Checked = False
	cbEinparkenLaengs.Checked = False
	cbLVorwaertsRechts.Checked = False
	cbLRueckwaertsLinks.Checked = False
	cbLRueckwaertsRechts.Checked = False
	cbLVorwaertsLinks.Checked = False
	cbRueckwaertsfahren.Checked = False
	cbEinparkenQuer.Checked = False
	cbQVorwaertsRechts.Checked = False
	cbQRueckwaertsLinks.Checked = False
	cbQRueckwaertsRechts.Checked = False
	cbQVorwaertsLinks.Checked = False
	cbGefahrbremsung.Checked = False
	cbRollenSchalten.Checked = False
	cbBremsSchalten.Checked = False
	cbBremsuebung.Checked = False
	cbDegressiv.Checked = False
	cbZielbremsung.Checked = False
	cbGefahrsituation.Checked = False
	cbGefaelle.Checked = False
	cbAnhalten.Checked = False
	cbAnfahren.Checked = False
	cbRueckwaerts.Checked = False
	cbSichern.Checked = False
	cbSchalten.Checked = False
	cbSteigung.Checked = False
	cbStAnhalten.Checked = False
	cbStAnfahren.Checked = False
	cbStRueckwaerts.Checked = False
	cbStSichern.Checked = False
	cbStSchalten.Checked = False
	cbTastgeschw.Checked = False
	cbBedienKontroll.Checked = False
	cbOertlichBesonder.Checked = False
	cbFahrbahnbenutzung.Checked = False
	cbEinordnen.Checked = False
	cbMarkierungen.Checked = False
	cbFahrstreifenwechsel.Checked = False
	cbLinks.Checked = False
	cbRechts.Checked = False
	cbVorbeifUeberholen.Checked = False
	cbAbbiegen.Checked = False
	cbABRechts.Checked = False
	cbABLinks.Checked = False
	cbMehrspurig.Checked = False
	cbRadweg.Checked = False
	cbSonderstreifen.Checked = False
	cbStrassenbahn.Checked = False
	cbEinbahnstrasse.Checked = False
	cbVorfahrt.Checked = False
	cbRechtsVorLinks.Checked = False
	cbGruenpfeil.Checked = False
	cbPolizeibeamte.Checked = False
	cbGruenpfeilSchild.Checked = False
	cbGeschwAbstand.Checked = False
	cbSituationVerkehrstn.Checked = False
	cbFussgaengerueberweg.Checked = False
	cbOeffentlVerkehrsm.Checked = False
	cbAeltereBehinderte.Checked = False
	cbEinbahnstrRadfahrer.Checked = False
	cbKinder.Checked = False
	cbSchulbus.Checked = False
	cbRadfahrerMofa.Checked = False
	cbVerkehrsberuhigt.Checked = False
	cbSchwierigeVerkehrsf.Checked = False
	cbEngpass.Checked = False
	cbKreisverkehr.Checked = False
	cbBahnuebergangWarte.Checked = False
	cbKritischeVerkehrss.Checked = False
	cbHauptverkehrszt.Checked = False
	cbPartnerVerhalten.Checked = False
	cbSchwungNutzen.Checked = False
	cbFussgaengerSchutzb.Checked = False
	cbAngepassteGeschw.Checked = False
	cbAbstand.Checked = False
	cbULVorne.Checked = False
	cbULHinten.Checked = False
	cbULSeitlich.Checked = False
	cbBeobachtSpiegel.Checked = False
	cbVerkehrszeichen.Checked = False
	cbKreuzungEinmuend.Checked = False
	cbKurven.Checked = False
	cbSteigungen.Checked = False
	cbULGefaelle.Checked = False
	cbAlleen.Checked = False
	cbUeberholen.Checked = False
	cbBesondereSituat.Checked = False
	cbLiegenblSichern.Checked = False
	cbEinfahrenOrtsch.Checked = False
	cbFussgaenger.Checked = False
	cbWildTiere.Checked = False
	cbBesondereAnford.Checked = False
	cbLeistungsgrenze.Checked = False
	cbOrientierung.Checked = False
	cbAblenkung.Checked = False
	cbFahrtplanung.Checked = False
	cbEinfahrtAB.Checked = False
	cbABFahrbahnwechsel.Checked = False
	cbGeschwindigkeit.Checked = False
	cbABAbstand.Checked = False
	cbABVorne.Checked = False
	cbABHinten.Checked = False
	cbABSeitlich.Checked = False
	cbABUeberholen.Checked = False
	cbSchilder.Checked = False
	cbVorbeifahren.Checked = False
	cbRastParkTank.Checked = False
	cbVerhUnfall.Checked = False
	cbDichterVerkehr.Checked = False
	cbBesonderSituat.Checked = False
	cbBesonderAnford.Checked = False
	cbABLeistungsgrenze.Checked = False
	cbKonfliktSitua.Checked = False
	cbABAblenkung.Checked = False
	cbVerlassenBAB.Checked = False
	cbBeleuchtung.Checked = False
	cbKontrolle.Checked = False
	cbEinstell.Checked = False
	cbBenutzung.Checked = False
	cbFernlicht.Checked = False
	cbBeleuchtStrasse.Checked = False
	cbUnbeleuchtStrasse.Checked = False
	cbParken.Checked = False
	cbDUBesonderSituat.Checked = False
	cbSchlechteWitterung.Checked = False
	cbTiere.Checked = False
	cbBahnuebergaenge.Checked = False
	cbUnbelVerkehrTN.Checked = False
	cbDUBesonderAnfor.Checked = False
	cbBlendung.Checked = False
	cbDUOrientierung.Checked = False
	cbAbschlussbesp.Checked = False
	cbSelbstFahren.Checked = False
	cbInnerorts.Checked = False
	cbAusserorts.Checked = False
	cbVerantwFahren.Checked = False
	cbTestfPruef.Checked = False
	cbFAKT.Checked = False
	cbAndere.Checked = False
	cbWiederholung.Checked = False
	cbLeistungsbew.Checked = False
	cbReifen.Checked = False
	cbEinAusschalten.Checked = False
	cbFunktionPruefen.Checked = False
	cbStandlicht.Checked = False
	cbNebelschluss.Checked = False
	cbBlinker.Checked = False
	cbAbblendlicht.Checked = False
	cbWarnblicke.Checked = False
	cbHupe.Checked = False
	cbBSFernlicht.Checked = False
	cbSchlussLeuchte.Checked = False
	cbBremslicht.Checked = False
	cbKontrollLBenenn.Checked = False
	cbRueckstrahler.Checked = False
	cbVorhandensein.Checked = False
	cbBeschaedigung.Checked = False
	cbLenkung.Checked = False
	cbLenkschlEntriegeln.Checked = False
	cbPruefLenkSpiel.Checked = False
	cbFunktBremse.Checked = False
	cbBetriebsBremse.Checked = False
	cbFeststellBremse.Checked = False
	cbRichtigSitz.Checked = False
	cbEinstellRueckspiegel.Checked = False
	cbEinKopfstuetze.Checked = False
	cbEinLenkrad.Checked = False
	cbAnlegenGurt.Checked = False
	cbBedienenAgg.Checked = False
	cbHeizung.Checked = False
	cbHeckHeizung.Checked = False
	cbBehSonderaus.Checked = False
	cbLueftung.Checked = False
	cbKlimaanlage.Checked = False
	cbEnergieNutzung.Checked = False
	cbKeineUnnVerbr.Checked = False
	cbRechtztAbsch.Checked = False
	cbMotorraum.Checked = False
	cbMotoroel.Checked = False
	cbKuehlmittel.Checked = False
	cbScheibenwaschm.Checked = False
	cbTanken.Checked = False
	cbSicherungsmittel.Checked = False
	cbWarndreieck.Checked = False
	cbBordwerkzeug.Checked = False
	cbZusaetzlichAus.Checked = False
	cbVerbandskasten.Checked = False
	cbAussenkontrolle.Checked = False
	cbScheibenWischer.Checked = False
	cbKennzeichen.Checked = False
	cbCheckSpiegel.Checked = False
	cbCheckBeleuchtung.Checked = False
	cbBremsen.Checked = False
	cbLadung.Checked = False
	cbLadungssicherung.Checked = False
	cbKenntlichmachung.Checked = False
	cbFahreSchlWitt.Checked = False
	cbWittLueftung.Checked = False
	cbWittScheiben.Checked = False
	cbRegen.Checked = False
	cbWasserlachen.Checked = False
	cbWindSturm.Checked = False
	cbMatchSchnee.Checked = False
	cbEis.Checked = False
	cbWittBeleuchtung.Checked = False
	etNotizen.Text = " "
End Sub

Sub CInt(o As Object) As Int
	If o = True Then
		Return 1
	Else
		Return 0
	End If
End Sub
#End Region

#Region Verschüsselung
'Unterschrift speichern
Sub UnterschriftSpeichernNeu(iTerminID As Int, sFahrtBez As String, sKlassen As String, sFahrzeug As String, _
							sBegleitFahzg As String, sDatum As String, sUhrzeit As String, sMenge As String, sFahrtkurzBezeichnung As String, sFahrlehrer As String) As Boolean
	Dim bResult As Boolean
	
	Dim sVerschluesselung As String
	'OID der Fahrtbezeichnung holen
	Dim iFahrtKurzBz As Int
	iFahrtKurzBz = DBUtils.FahrtbezeichnungsID(sFahrtBez)
	
	'OID der Klassen holen
	Dim iKlasse As Int
	iKlasse = DBUtils.KlasseID(sKlassen)
	
	'OID des Fahrzeug und Begleitfahrzeug holen
'	Dim iFahrzeug, iBegleitFhzg As Int
'	iFahrzeug = DBUtils.FahrzeugbezeichnungsID(sFahrzeug)
'	iBegleitFhzg = DBUtils.BegleitfahrzeugsID(sFahrzeug, sBegleitFahzg)
	
	'OID des Fahrschuelers holen
	Dim iSchuelerID As Int
	iSchuelerID = DBUtils.GetSchuelerID(iTerminID)
	
	'OID des Fahrlehrer s holen
	Dim iFahrlehrerID As Int
	iFahrlehrerID = DBUtils.GetFahrlehrerID(sFahrlehrer)
	
	
	'ToDo: Daten in DB speichern in extra Tabelle - wird dann später beim speichern in Termin-Tabelle übernehmen
	'Speicherung der verschlüsselten Signatur in Tabelle Signature zusammen mit Termin_ID, SchuelerID und Datum
	If DBUtils.SaveSignature(" ", iTerminID, iSchuelerID) Then
		bResult = True
	Else
		bResult = False
	End If

	Return bResult
End Sub

'Erzeugt einen String mit Anzahl der Leerzeichen welche übergeben werden
Sub Space(iAnzahl As Int) As String
	Dim sReturn As String
    sReturn = ""
    Do While sReturn.Length < iAnzahl
        sReturn = sReturn & " "
    Loop
    Return sReturn
End Sub

'Sub WriteStringToStream(Out As OutputStream, s As String)
'    Dim t As TextWriter
'    t.Initialize(Out)
'    t.Write(s)
'    t.Close 'Closes the internal stream as well
'End Sub 
#End Region
