Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=3.5
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	
End Sub

'Prüfung, Versionsüberprüfung
Sub CheckVersion() As Boolean
	Dim bResult As Boolean
	Dim sVersion As String
	Try	
		If (Main.SQL1.IsInitialized = False) Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
			Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
		End If 
		
		sVersion = Main.SQL1.ExecQuerySingleResult("SELECT DBVersion FROM Konfigdaten")
		
		If sVersion = Main.VersionsNummer Then
			bResult = True
		Else
			bResult = False
		End If
	Catch
		Log("Kein Datenbankzugriff -> Datenbank nicht vorhanden!")
		bResult = False
	End Try
	
	Main.SQL1.Close
	Return bResult
End Sub

'Prüfung, ob Schülerdaten im Systemvorhanden sind
Sub CheckDatenInDB() As Boolean
	Dim bResult As Boolean
	Dim iAnzahl As Int
	
	Try	
		If (Main.SQL1.IsInitialized = False) Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
			Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
		End If 
		
		iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler")
		
		If iAnzahl > 0 Then
			bResult = True
		Else
			bResult = False
		End If
	Catch
		Log("Kein Datenbankzugriff -> Datenbank nicht vorhanden!")
		bResult = False
	End Try
	
	Main.SQL1.Close
	Return bResult 
End Sub

'Setzen der LernThemen Kategorie -> keine Zuordnung vorhanden nach Transport aus Hauptprogramm (FaData)
Sub SetLernThermen_Kat()
	Dim cur As Cursor
	Dim sUpdate As String
	Dim iZaehler As Int
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	LfdNr, " & _
										"Kat_LernThemen_PK " & _
								"FROM LernThemen")

	For i = 1 To cur.RowCount
		cur.Position = i - 1
		If (cur.Position Mod 10 = 0) Then
			iZaehler = iZaehler + 1 
		End If
		
		sUpdate = "UPDATE LernThemen " & _
					"SET Kat_LernThemen_PK = " & iZaehler & _
				  " WHERE LfdNr = " & cur.GetInt("LfdNr")
		
		'Main.SQL1.BeginTransaction
		Try
			Main.SQL1.ExecNonQuery(sUpdate)
			'Main.SQL1.TransactionSuccessful
		Catch 
			Log("Fehler beim setzen der Lern Kategorisierung in LernThemen" & LastException.Message) 'no changes will be made
		End Try
		'Main.SQL1.EndTransaction
	Next
	cur.Close
	Main.SQL1.Close
End Sub

'Setzen/füllen der fehlenden Klassen_ID Zuordnungen in der Tabelle Termine
Sub SetKlassenIdsInTermine()
	Dim cur As Cursor
	Dim sUpdateKl As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	OID, " & _
										"Klassen_ID, " & _
										"Klasse, " & _
										"CASE MeetingPoint WHEN MeetingPoint THEN MeetingPoint ELSE 1 END AS MeetingPoint " & _
								"FROM Termine")

	For i = 0 To cur.RowCount - 1
		cur.Position = i
		
		If (cur.GetString("Klasse") = "") Then
			sUpdateKl = "UPDATE Termine " & _
							"SET Klasse = ( SELECT Bezeichnung FROM Klassen " & _
													"WHERE OID = " & cur.GetInt("Klassen_ID") & " ), " & _
								"Starttermin = SUBSTR(Starttermin, 0, 6) " & _
						  " WHERE OID = " & cur.GetInt("OID")
		Else If (cur.GetString("MeetingPoint") <> "1") Then
			sUpdateKl = "UPDATE Termine " & _
							"SET Starttermin = SUBSTR(Starttermin, 0, 6), " & _
								"Treffpunkt_ID = ( SELECT OID FROM Treffpunkt " & _
													"WHERE Bezeichnung LIKE '" & cur.GetString("MeetingPoint") & "' ) " & _
						  " WHERE OID = " & cur.GetInt("OID")
		Else
			sUpdateKl = "UPDATE Termine " & _
							"SET Starttermin = SUBSTR(Starttermin, 0, 6) " & _
						  " WHERE OID = " & cur.GetInt("OID")
		End If
		
		'Main.SQL1.BeginTransaction
		Try
			Main.SQL1.ExecNonQuery(sUpdateKl)
			'Main.SQL1.TransactionSuccessful
		Catch 
			Log("Fehler beim setzen der Klassen_ID in Tb-Termine " & LastException.Message) 'no changes will be made
		End Try
		'Main.SQL1.EndTransaction
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'korrigiert fehlerhafte Einträge in Termin-Tabelle -> Hat_Unterschrift
Sub KorrigierenFehlerhafterEintraege()
	Dim sUpdate As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sUpdate = "UPDATE Termine " & _
				"SET Hat_Unterschrift = 1 " & _
			  "WHERE Hat_Unterschrift > 1 "
	
	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
	Catch 
		Log("Fehler beim korrgieren der Termin-Tabelle bei Hat_Unterschrift" & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	
	Main.SQL1.Close
End Sub

'korrigiert fehlerhafte Eintränge in der Schüler Tabelle -> leere/fehlende MatchCodes
Sub KorrigierenFehlerhafteMatchCodes()
	Dim sDelete As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sDelete = "DELETE FROM Schueler " & _
				"WHERE MatchCode IS NULL " & _
			  "OR MatchCode LIKE '' "
	
	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sDelete)
		'Main.SQL1.TransactionSuccessful
	Catch 
		Log("Fehler beim löschen der Einträge in der Schueler-Tabelle bei denen der MatchCode leer ist bzw. kein MatchCode vorhanden ist" & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	
	Main.SQL1.Close
End Sub

'korrigiert fehlende Anmeldedatums-Einträge in der Schüler Tabelle
Sub SetAnmeldedatum()
	Dim sUpdate As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sUpdate = "UPDATE Schueler " & _
				"SET AnmeldeDatum = '31.12.1899' " & _
			  "WHERE AnmeldeDatum IS NULL " & _
			  "OR AnmeldeDatum = ''"
	
	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
	Catch 
		Log("Fehler beim setzen der AnmeldeDatums in Schüler-Tabelle" & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	
	Main.SQL1.Close
End Sub

'korrigiert fehlerhafte/fehlende Einträge in den Schüler Stammdaten
Sub SetKl1BeantragtInStammdaten()
	Dim sUpdate As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sUpdate = "UPDATE Schueler " & _
				"SET Kl1Beantragt = 'NV' " & _
			  "WHERE Kl1Beantragt IS NULL " & _
			  "OR Kl1Beantragt = ''"
	
	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
	Catch 
		Log("Fehler beim setzen der beantragten Klasse in Schüler-Tabelle" & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	
	Main.SQL1.Close
End Sub

'fixer Fahrlehrer und fixes Fahrzeug aus INI Datei setzen -> Prüfung, ob Fahrlehrer und Fahrzeug zu DB Daten passen
Sub CheckSetFixenFahrlehrerFahrzeug(aFahrData() As String)
	Dim sUpdate, sSelect As String
	Dim iAnzahl As Int
	
	sSelect = "SELECT OID FROM Fahrlehrer WHERE Name LIKE '" & aFahrData(0) & "' AND FahrlehrerNr = '" & aFahrData(1) & "'"
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	
	Try
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
		
		If iAnzahl > 0 Then
			sUpdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE OID = " & iAnzahl
		Else
			sUpdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE OID = 1"
		End If
	
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
	Catch
		Log("Fehler beim setzen des fixen Fahrlehrers")
	End Try
	
	sSelect = "SELECT OID FROM Fahrzeuge WHERE Bezeichnung LIKE '" & aFahrData(2) & "' AND FzgNr = " & aFahrData(3)
	
	Try
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	
		If iAnzahl > 0 Then
			sUpdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = " & iAnzahl
		Else
			sUpdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = 1"
		End If
		
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
	Catch
		Log("Fehler beim setzen des fixen Fahrzeugs")
	End Try
		'Main.SQL1.EndTransaction
	
	Main.SQL1.Close
End Sub

'ein fixer Fahrlehrer muss gesetzt werden
Sub SetFixenFahrlehrerFahrzeug
	Dim sUpdate, sSelect As String
	Dim iAnzahl As Int
	
	sSelect = "SELECT COUNT(*) FROM Fahrlehrer WHERE IsSelected = 1"
	sUpdate = "UPDATE Fahrlehrer SET IsSelected = 1 WHERE FahrlehrerNr = 1"
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	
	If iAnzahl = 0 Then
		'Main.SQL1.BeginTransaction
		Try
			Main.SQL1.ExecNonQuery(sUpdate)
			'Main.SQL1.TransactionSuccessful
		Catch
			Log("Fehler beim setzen des fixen Fahrlehrers")
		End Try
		'Main.SQL1.EndTransaction
	End If
	
	sSelect = "SELECT COUNT(*) FROM Fahrzeuge WHERE IsSelected = 1"
	sUpdate = "UPDATE Fahrzeuge SET IsSelected = 1 WHERE OID = 1"
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	
	If iAnzahl = 0 Then
		'Main.SQL1.BeginTransaction
		Try
			Main.SQL1.ExecNonQuery(sUpdate)
			'Main.SQL1.TransactionSuccessful
		Catch
			Log("Fehler beim setzen des fixen Fahrzeugs")
		End Try
		'Main.SQL1.EndTransaction
	End If
	
	Main.SQL1.Close
	
End Sub

'Alle Schülerdaten holen und in ListView übergeben
Sub FillSchuelerListView(lstSchueler As ListView)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Schueler-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler")
	
	Dim SchuelerID(iAnzahl, 3) As String
	
	cur = Main.SQL1.ExecQuery("SELECT 	SchuelerID, " & _
										"MatchCode, " & _
										"Strasse, " & _
										"Hausnummer, " & _
										"PLZ, " & _
										"Ort " & _
								"FROM Schueler " & _
								"ORDER BY MatchCode")
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
'		Log("*****************************")
'		Log("---------- Schüler ----------")
'		Log("MatchCode: " & cur.GetString("MatchCode"))
'		Log("ID: " & cur.GetInt("SchuelerID"))
		lstSchueler.AddSingleLine(cur.GetString("MatchCode"))
		
		SchuelerID(i, 0) = cur.GetString("MatchCode")
		SchuelerID(i, 1) = cur.GetString("SchuelerID")
		SchuelerID(i, 2) = "0"
		
'		Log(SchuelerID)
	Next
	
	cur.Close
	Main.SQL1.Close
	
	'ersten Eintrag fixieren
	SchuelerID(0, 2) = 1
	Main.aSchueler = SchuelerID	
End Sub

Sub FillSchuelerList(lSchueler As ListView)
	Dim cur As Cursor
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	SchuelerID, " & _
										"MatchCode, " & _
										"Strasse, " & _
										"Hausnummer, " & _
										"PLZ, " & _
										"Ort " & _
								"FROM Schueler " & _
								"ORDER BY MatchCode")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lSchueler.AddSingleLine(cur.GetString("MatchCode"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

Sub FillSchuelerArray(sABC As String) As String(,)
	Dim cur As Cursor
	Dim iAnzahl As Int
	Dim sSelect As String
	
	iAnzahl = FillSchuelerListViewItems(sABC)
		
	Dim SchuelerID(iAnzahl, 3) As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sSelect = "SELECT 	SchuelerID, " & _
										"MatchCode, " & _
										"Strasse, " & _
										"Hausnummer, " & _
										"PLZ, " & _
										"Ort " & _
								"FROM Schueler " & _
								"WHERE MatchCode LIKE '" & sABC & "%' "

	If (sABC = "A") Then
		sSelect = sSelect & 	"OR Matchcode LIKE 'Ä%' "
	Else If (sABC = "U") Then
		sSelect = sSelect & 	"OR Matchcode LIKE 'Ü%' "
	Else If (sABC = "O") Then
		sSelect = sSelect & 	"OR Matchcode LIKE 'Ö%' "
	End If
	
	sSelect = sSelect & 		"ORDER BY MatchCode"
	
'	cur = Main.SQL1.ExecQuery("SELECT 	SchuelerID, " & _
'										"MatchCode, " & _
'										"Strasse, " & _
'										"Hausnummer, " & _
'										"PLZ, " & _
'										"Ort " & _
'								"FROM Schueler " & _
'								"WHERE MatchCode LIKE '" & sABC & "%' " & _ 
'								"ORDER BY MatchCode")
	cur = Main.SQL1.ExecQuery(sSelect)
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		
		SchuelerID(i, 0) = cur.GetString("MatchCode")
		SchuelerID(i, 1) = cur.GetString("SchuelerID")
		SchuelerID(i, 2) = "0"
		
'		Log(SchuelerID)
	Next
	
	cur.Close
	Main.SQL1.Close
	
	Return SchuelerID
End Sub

Sub FillSchuelerListViewItems(sABC As String) As Int
	'Anzahl der alphabetischen Einträge holen
	Dim iAnzahl As Int
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	If (sABC = "A") Then
		iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler " & _
													"WHERE MatchCode LIKE '" & sABC & "%' " & _
													"OR MatchCode LIKE 'Ä%' " )
	Else If (sABC = "U") Then
		iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler " & _
													"WHERE MatchCode LIKE '" & sABC & "%' " & _
													"OR MatchCode LIKE 'Ü%' " )
	Else If (sABC = "O") Then
		iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler " & _
													"WHERE MatchCode LIKE '" & sABC & "%' " & _
													"OR MatchCode LIKE 'Ö%' " )
	Else
		iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Schueler " & _
													"WHERE MatchCode LIKE '" & sABC & "%' " )
	End If
	
	Main.SQL1.Close
	
	Return iAnzahl
End Sub

'Alle Fahrlehrerdaten holen und in ListView übergeben
Sub FillFahrlehrerListView(lstFahrlehrer As ListView)
	Dim cur As Cursor
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	Name, " & _
										"Vorname, " & _
										"FahrlehrerNr, " & _
										"IsSelected " & _
								"FROM Fahrlehrer " & _
								"ORDER BY IsSelected desc")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
'		Log("---------- Fahrlehrer ----------")
'		Log("Name: " & cur.GetString("Name"))
'		Log("Vorname: " & cur.GetString("Vorname"))
		'lstFahrlehrer.AddSingleLine(cur.GetString("Name") & ", " & cur.GetString("Vorname"))
		lstFahrlehrer.AddSingleLine(cur.GetString("Name"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'ausgewählten Fahrlehrer als Standart festlegen
Sub FahrlehrerFixieren(sFahrlehrer As String) As Boolean
	Dim bReturnValue As Boolean
	Dim sReset, sUpdate As String
'	Dim iZahl As Int
'	Dim sVorname, sNachname As String
	
	'GH: wird nicht mehr benötigt, da nur der Name aus dem Hauptprogramm übergeben wird
'	iZahl = sFahrlehrer.IndexOf(", ")
'	sNachname = sFahrlehrer.SubString2(0, iZahl)
'	sVorname = sFahrlehrer.SubString(iZahl + 2)
	
	sReset = "UPDATE Fahrlehrer " & _
				"SET IsSelected = 0 "
	
	sUpdate = "UPDATE Fahrlehrer " & _
				"SET IsSelected = 1 " & _
			 "WHERE Name LIKE '" & sFahrlehrer & "' "
	'GH 28.03.2013: Vorname rausgenommen
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sReset)
		Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bReturnValue = True
	Catch
	    Log("Fehler beim setzen des fixen Fahrlehrer " & LastException.Message) 'no changes will be made
		bReturnValue = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bReturnValue
End Sub

'Alle KFZdaten holen und in ListView übergeben
Sub FillKfzListView(lstKfz As ListView)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Fahrtbezeichnungs-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrzeuge")
	
	Dim KFZID(iAnzahl, 2) As String
	
	cur = Main.SQL1.ExecQuery("SELECT 	FzgNr, " & _
										"Bezeichnung, " & _
										"IsSelected " & _
								"FROM Fahrzeuge " & _
								"ORDER BY IsSelected desc")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
'		Log("---------- KFZ ----------")
'		Log("Fahrzeug Nummer: " & cur.GetString("FzgNr"))
'		Log("Bezeichnung: " & cur.GetString("Bezeichnung"))
		lstKfz.AddSingleLine(cur.GetString("Bezeichnung"))
		KFZID(i, 0) = cur.GetString("Bezeichnung")
		KFZID(i, 1) = cur.GetInt("FzgNr")
	Next
	
	cur.Close
	Main.SQL1.Close
	Main.aKFZ = KFZID
End Sub

'KFZ fixieren
Sub KFZFixieren(sKFZ As String)
	Dim sReset, sUpdate As String
	
	sReset = "UPDATE Fahrzeuge " & _
				"SET IsSelected = 0 "
'	Log(sReset)
	
	sUpdate = "UPDATE Fahrzeuge " & _
				"SET IsSelected = 1 " & _
			 "WHERE Bezeichnung LIKE '" & sKFZ & "' "
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sReset)
		Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim setzen des fixen KFZ " & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
End Sub

'alle Ausbildungs-Klassen des Schuelers laden
Sub FillSchuelerKlasseListView(sMatchCode As String, iSchuelerID As Int) As String
	Dim sKlassen As String
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	sKlassen = Main.SQL1.ExecQuerySingleResult("SELECT 	LTRIM(BeantragteKlasse) " & _
													"FROM Schueler " & _
												  "WHERE MatchCode LIKE '" & sMatchCode & "' " & _
												  "AND SchuelerID = " & iSchuelerID & " ")
'	Log("Ausbildungs-Klassen:" & sKlassen)
	Main.SQL1.Close
	
	Return sKlassen
End Sub

'alle Klassen laden
Sub FillKlassenListView(lstKlassen As ListView)
	Dim cur As Cursor
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT Bezeichnung " & _
								"FROM Klassen " & _
									"WHERE Land LIKE 'Deutschland' " & _
									"AND enable = 1 " & _
								"ORDER BY Sortierung")
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstKlassen.AddSingleLine(cur.GetString("Bezeichnung"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'Treffpunkt-Liste füllen
Sub FillTreffpunktListView(lstTreffpunkt As ListView)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Fahrtbezeichnungs-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int

	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Treffpunkt WHERE fixer_Treffpunkt = 1")
	
	Dim TreffpunktID(iAnzahl, 2) As String
	
	cur = Main.SQL1.ExecQuery("SELECT 	OID, " & _
										"Bezeichnung " & _
								"FROM Treffpunkt " & _
									"WHERE fixer_Treffpunkt = 1 " & _
								"ORDER BY OID")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstTreffpunkt.AddSingleLine(cur.GetString("Bezeichnung"))
		TreffpunktID(i, 0) = cur.GetString("Bezeichnung")
		TreffpunktID(i, 1) = cur.GetInt("OID")
	Next
	
	cur.Close
	Main.SQL1.Close
	
	Main.aTreffpunkt = TreffpunktID
End Sub

'Ergänzende Treffpunkte holen
Sub GetAllMeetingPoint As String(,)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Fahrtbezeichnungs-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int

	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Treffpunkt")
	
	Dim TreffpunktDefaultID(iAnzahl, 3) As String
	'DefaultMeetingPoints(iAnzahl, 2)
	
	cur = Main.SQL1.ExecQuery("SELECT 	OID, " & _
										"Bezeichnung, " & _
										"fixer_Treffpunkt " & _
								"FROM Treffpunkt " & _
								"ORDER BY OID")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		TreffpunktDefaultID(i, 0) = cur.GetString("Bezeichnung")
		TreffpunktDefaultID(i, 1) = cur.GetInt("fixer_Treffpunkt")
		TreffpunktDefaultID(i, 2) = cur.GetInt("OID")
	Next
	
	cur.Close
	Main.SQL1.Close
	
	Return TreffpunktDefaultID
End Sub

'ZahlungenFuer-Liste füllen
Sub FillZahlungenFuerListView(lstZahlungFuer As ListView)
	Dim cur As Cursor
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	Bezeichnung " & _
								" FROM ZahlungenFuer " & _
								"ORDER BY OID")
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstZahlungFuer.AddSingleLine(cur.GetString("Bezeichnung"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'Erfasste Zahlungen laden
Sub ErfassteZahlungenHolen(sAuswahl As String, lstZahlungen As ListView) As String	
	'Gesamt Fahrstunden und Gesamtstunden des Fahrlehrers des ausgewählten Tages zusammenfassen
	Dim sSelectSum, sSelectList, sSumme As String
	Dim cur As Cursor
	
	If sAuswahl = "Alle" Then
		sSelectSum = 	"SELECT SUM(z.Betrag) " & _
							"FROM Zahlungen z " & _
							"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID " & _
						"WHERE f.IsSelected = 1"
		sSelectList = 	"SELECT 	z.ZahlDatum, " & _
									"z.MatchCode, " & _
									"z.Betrag " & _
							"FROM Zahlungen z " & _
							"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID " & _
						"WHERE f.IsSelected = 1 " & _
						"ORDER BY z.ZahlDatum"
	Else
		sSelectSum = 	"SELECT SUM(z.Betrag) " & _
						"FROM Zahlungen z " & _
						"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID " & _
					"WHERE z.ZahlDatum LIKE '" & sAuswahl & "' " & _
					"AND f.IsSelected = 1"
		sSelectList = 	"SELECT 	z.ZahlDatum, " & _
									"z.MatchCode, " & _
									"z.Betrag " & _
							"FROM Zahlungen z " & _
							"	INNER JOIN Fahrlehrer f ON f.OID = z.Fahrlehrer_ID " & _
						"WHERE z.ZahlDatum LIKE '" & sAuswahl & "' " & _
						"AND f.IsSelected = 1 " & _
						"ORDER BY z.ZahlDatum"
	End If
	
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		sSumme = Main.SQL1.ExecQuerySingleResult(sSelectSum)
		cur = Main.SQL1.ExecQuery(sSelectList)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen der Zahlungen " & LastException.Message) 'no changes will be made
		sSumme = Null
	End Try
	'Main.SQL1.EndTransaction
	
	For i = 0 To cur.RowCount -1
		cur.Position = i
		Dim sMaCode, sBetrag As String
		sMaCode = cur.GetString("MatchCode")

		If Main.lv.Width > 320 And Main.lv.Height > 480 Then
			If sMaCode.Length < 14 Then
				sMaCode = sMaCode & TAB & TAB & TAB
			Else If sMaCode.Length = 14 Then
				sMaCode = sMaCode & TAB & TAB
			End If
		Else
			If sMaCode.Length < 14 Then
				sMaCode = sMaCode & TAB & TAB
			Else If sMaCode.Length = 14 Then
				sMaCode = sMaCode & TAB
			End If
		End If
		
		sBetrag = cur.GetString("Betrag")
'		If sBetrag.LastIndexOf(".") = -1 Then
'			sBetrag = sBetrag & ".00"
'		End If
					
		'Prüfung, ob mehr als 2 Nachkommastellen und notfalls mit 0 auffüllen
		Dim iGesamt, iPos As Int
		iGesamt = sBetrag.Length
		iPos = sBetrag.LastIndexOf(".")
		
		If iPos = -1 Then
			'sBetrag = sBetrag & ".00"
		Else
			If (iGesamt - (iPos + 1)) > 2 Then
				sBetrag = sBetrag.SubString2(0, iPos + 3)
			Else If (iGesamt - (iPos + 1)) = 1 Then
				sBetrag = sBetrag & "0"
			End If
		End If
		
		lstZahlungen.AddSingleLine(cur.GetString("ZahlDatum") & ":  " & sMaCode & TAB & TAB & sBetrag)
	Next
	
	cur.Close
	Main.SQL1.Close
	
	Return sSumme
End Sub

'Zahlung speichern
Sub ZahlungenSpeichern(sBetrag As String, sDatum As String, sZahlungFuer As String) As Boolean
	Dim sInsertUpdate As String
	Dim bResult As Boolean
	
	Dim iZahlungID As Int
	
	If Main.bZahlBearbeiten Then
		'ZahlungID holen
		If Main.bZahlDatum Then
			sDatum = "Alle"
		End If
		
		iZahlungID = ZahlungenIDHolen(Main.iZahlungsPosition, sDatum)
		
		sInsertUpdate = "UPDATE Zahlungen " & _
							"SET 	Betrag = '" & sBetrag & "', " & _
									"ZaFuer_ID = (SELECT OID FROM ZahlungenFuer WHERE Bezeichnung like '" & sZahlungFuer & "') " & _
							"WHERE OID = " & iZahlungID
	Else
		sInsertUpdate = "INSERT INTO Zahlungen (" & _
									"Schueler_ID, " & _
									"MatchCode, " & _
									"ZahlDatum, " & _
									"Betrag, " & _
									"StSchl, " & _
									"StSatz, " & _
									"ZaArt, " & _
									"ZaFuer_ID, " & _
									"Fahrlehrer_ID) " & _
									"Values ( " & _
											"'" & Main.iAusgewaehlterSchuelrID & "', " & _
											"'" & Main.sAusgewaehlterSchueler & "', " & _
											"'" & sDatum & "', " & _ 
											"'" & sBetrag & "', " & _
											"null, " & _
											"null, " & _
											"0, " & _
											"(SELECT OID FROM ZahlungenFuer WHERE Bezeichnung like '" & sZahlungFuer & "'), " & _
											"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1) " & _ 
											") "
	End If

	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 

	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sInsertUpdate)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim speichern der Zahlungen " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Ausgewählte Zahlunugsdaten holen
Sub ZahlungPositionHolen(iPosition As Int, oValue As Object, sAuswahl As String) As String()
	Dim aResult() As String
	Dim sSelect As String
	Dim cur As Cursor
	Dim iPositionID As Int
	
	iPositionID = ZahlungenIDHolen(iPosition, sAuswahl)
	
	'Zahluungsdaten holen
	sSelect = "SELECT 	z.MatchCode, " & _
					"z.Betrag, " & _
					"zf.Bezeichnung " & _
			"FROM Zahlungen z " & _
			"INNER JOIN ZahlungenFuer zf ON zf.OID = z.ZaFuer_ID " & _
			"WHERE z.OID = " & iPositionID
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery(sSelect)
	
	cur.Position = 0
	
	aResult = Array As String(cur.GetString("MatchCode"), cur.getstring("Betrag"), cur.GetString("Bezeichnung"))
	
	cur.Close
	Main.SQL1.Close
	
	Return aResult
End Sub

'ZahlungsID ermitteln
Sub ZahlungenIDHolen(iPosition As Int, sAuswahl As String) As Int
	Dim cur As Cursor
	Dim iZahlungsID As Int
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	If sAuswahl = "Alle" Then
		'Ermitteln der zu löschenden ID
		cur = Main.SQL1.ExecQuery("SELECT z.OID " & _
										"FROM Zahlungen z " & _
									"WHERE z.Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1) " & _
									"ORDER BY z.ZahlDatum")
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			If i = iPosition Then
				iZahlungsID = cur.GetInt("OID")
			End If
		Next
	Else
		cur = Main.SQL1.ExecQuery("SELECT z.OID " & _
										"FROM Zahlungen z " & _
									"WHERE z.ZahlDatum like '" & sAuswahl & "' " & _
									"AND z.Fahrlehrer_ID = (Select OID FROM Fahrlehrer WHERE IsSelected = 1) " & _
									"ORDER BY z.ZahlDatum")
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			If i = iPosition Then
				iZahlungsID = cur.GetInt("OID")
			End If
		Next
	End If
	
	cur.Close
	Main.SQL1.Close
	
	Return iZahlungsID
End Sub

'Zahlungseintrag löschen
Sub ZahlungLoeschen(iPosition As Int, sAuswahl As String) As Boolean
	Dim bResult As Boolean
	Dim iPositionID As Int
	Dim sDelete As String
	
	'Ermitteln der zu löschenden ID
	iPositionID = ZahlungenIDHolen(iPosition, sAuswahl)
	
	sDelete = "DELETE FROM Zahlungen " & _
				"WHERE OID = " & iPositionID
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sDelete)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim löschen des Zahlungseintrags " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Ausbildungsthemen laden
Sub FillAusbildungLernThemenListView(lstAusbildungLernThemen As ListView, iKategorie As Int)
	Dim cur As Cursor
	
	lstAusbildungLernThemen.Clear
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
		
	cur = Main.SQL1.ExecQuery("SELECT 	l.LfdNr, " & _
										"l.Thema " & _
								"FROM LernThemen l " & _
								"WHERE l.Kat_LernThemen_PK = " & iKategorie )
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstAusbildungLernThemen.AddSingleLine(cur.GetInt("LfdNr") & TAB & cur.GetString("Thema"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'Ausbildungs-Prüfeinträge laden
Sub FillAusbildungLernPunkteListView(lstAusbildungLernPunkte As ListView, sSchueler As String, iKategorie As Int)
	Dim sEintraege As String
	
	lstAusbildungLernPunkte.Clear
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Stringeintrag holen
	sEintraege = Main.SQL1.ExecQuerySingleResult("SELECT LernString " & _
													"FROM LernKontrolle " & _
													"WHERE SchuelerID = " & Main.iAusgewaehlterSchuelrID )
'													& " " & _
'													"AND FahrlNr = (Select OID FROM Fahrlehrer WHERE IsSelected = 1)")
	Main.SQL1.Close
	
	If sEintraege = Null Or sEintraege.Length = 0 Then
		For i = 0 To 10
			lstAusbildungLernPunkte.AddSingleLine(" ")
		Next
	Else
		Dim iAnfang, iEnde As Int
		
		If iKategorie = 1 Then
			iAnfang = 0
			iEnde = 10 
		Else If iKategorie = 2 Then
			iAnfang = 10
			iEnde = 20
		Else If iKategorie = 3 Then
			iAnfang = 20
			iEnde = 30
		Else If iKategorie = 4 Then
			iAnfang = 30
			iEnde = 40
		Else If iKategorie = 5 Then
			iAnfang = 40
			iEnde = 50
		Else If iKategorie = 6 Then
			iAnfang = 50
			iEnde = 60
		Else If iKategorie = 7 Then
			iAnfang = 60
			iEnde = 70
		End If
		
		sEintraege = sEintraege.SubString2(iAnfang, iEnde)
		For i = 0 To sEintraege.Length - 1
			lstAusbildungLernPunkte.AddSingleLine(sEintraege.SubString2(i, i + 1))
		Next
	End If
End Sub

'Ausbildungs-Prüfeinträge speichern
Sub UpdateAusbildungLernPunkte(sCharacter As Char, iPosition As Int) As Boolean
	Dim bResult As Boolean
	Dim iBereich As Int
	Dim sEintraege As String
	
	'welcher Bereich ist ausgewählt
	iBereich = Main.iAusbildungBereich
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Stringeintrag holen
	sEintraege = Main.SQL1.ExecQuerySingleResult("SELECT LernString " & _
													"FROM LernKontrolle " & _
													"WHERE SchuelerID = " & Main.iAusgewaehlterSchuelrID )
													'"AND FahrlNr = (Select OID FROM Fahrlehrer WHERE IsSelected = 1) ")
	
	Dim bInsert As Boolean
	bInsert = False
	
	Dim c As Char
	c = Chr(32)
		
	If sEintraege = Null Then
		bInsert = True
		sEintraege =  c
		For i = 0 To 68
			sEintraege =  sEintraege & c
		Next
	End If
	
	Dim sEintraegeNeu As String
	
	If iBereich = 1 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 0, 10, iPosition, sCharacter)
		sEintraege = sEintraegeNeu & sEintraege.SubString2(10, 70)		
	Else If iBereich = 2 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 10, 20, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 10) & sEintraegeNeu & sEintraege.SubString2(20, 70)	
	Else If iBereich = 3 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 20, 30, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 20) & sEintraegeNeu & sEintraege.SubString2(30, 70)
	Else If iBereich = 4 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 30, 40, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 30) & sEintraegeNeu & sEintraege.SubString2(40, 70)
	Else If iBereich = 5 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 40, 50, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 40) & sEintraegeNeu & sEintraege.SubString2(50, 70)
	Else If iBereich = 6 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 50, 60, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 50) & sEintraegeNeu & sEintraege.SubString2(60, 70)
	Else If iBereich = 7 Then
		sEintraegeNeu = Helper.AusbildungZustandNeu(sEintraege, 60, 70, iPosition, sCharacter)
		sEintraege = sEintraege.SubString2(0, 60) & sEintraegeNeu
	End If
	
	'neuen String in DB schreiben
	Dim sUpdate As String
	
	Dim sDatum As String
	sDatum = DateTime.Date(DateTime.Now)
	
	If bInsert Then
		sUpdate = "INSERT INTO LernKontrolle (" & _
									"SchuelerID, " & _
									"LernString, " & _
									"AendDatum, " & _
									"FahrlNr, " & _
									"DatenErfasst ) " & _
									"VALUES ( " & _
											" " & Main.iAusgewaehlterSchuelrID & ",  " & _
											"'" & sEintraege & "', " & _
											"'" & sDatum & "', " & _ 
											"(Select OID FROM Fahrlehrer WHERE IsSelected = 1), " & _
											"'-1' " & _
											") "
	Else
		sUpdate = "UPDATE LernKontrolle " & _
					"SET LernString = '" & sEintraege & "', " & _
						"AendDatum = '" & sDatum & "', " & _
						"DatenErfasst = '-1' " & _
						"WHERE SchuelerID = " & Main.iAusgewaehlterSchuelrID
						'& " " & _
						'"AND FahrlNr = (Select OID FROM Fahrlehrer WHERE IsSelected = 1)"
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log(LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Liste der Begleitfahrzeuge füllen
Sub FillBegleitfahrzeugListView(lstBegleitfahrzeug As ListView)
	Dim cur As Cursor
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT Bezeichnung " & _
								"FROM Fahrzeuge " & _
							  "WHERE Kat_Fahrzeug_PK = 0 " & _
							  "ORDER BY IsSelected DESC, Bezeichnung")
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstBegleitfahrzeug.AddSingleLine(cur.GetString("Bezeichnung"))
	Next
	
	cur.Close
	Main.SQL1.Close
End Sub

'Eingebene Daten vormerken, damit Daten im ListView angezeigt werden
Sub DatenVormerkenInsert(sTermin As String, sStartzeit As String, sDauer As String, sKlasse As String, _
	sFahrbezeichnung As String, sTreffpunkt As String, sFahrlehrer As String, sKFZ As String, sBegleitfahrzeug As String) As Boolean
	
	Dim bResult As Boolean
	Dim sInsert As String
	
	Dim iTreffpunktID As Int
		
	If sFahrbezeichnung = "Unt" Then
		sInsert = "INSERT INTO Termine (" & _
									"Fahrlehrer_ID, " & _
									"Schueler_ID, " & _
									"MatchCode, " & _
									"Termin, " & _
									"Klassen_ID, " & _
									"Klasse, " & _
									"Fahrtbezeichnung_ID, " & _
									"FahrtBezeichnung_Abkuerzen, " & _
									"Starttermin, " & _
									"Dauer, " & _
									"Treffpunkt_ID, " & _
									"MeetingPoint, " & _
									"Hat_Unterschrift, " & _
									"Fahrzeug_ID, " & _
									"Begleitfahrzeug_ID, " & _
									"PruefungsErgebnis, " & _
									"PruefungsTagNr, " & _
									"TransKz) " & _
									"VALUES ( " & _
											"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _ 
											"999, " & _
											"'Sonstige Tätigkeit', " & _
											"'" & sTermin & "', " & _
											"0, " & _
											"' ', " & _ 
											"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
											"'" & sFahrbezeichnung & "', " & _
											"'" & sStartzeit & "', " & _
											"'" & sDauer & "', " & iTreffpunktID & ", " & _
											"' ', " & _ 
											"0, " & _
											"0, " & _
											"0, " & _
											"' ', " & _
											"' ', " & _
											"'T' " & _
											") "
	Else
		'Check ob Treffpunkt schon vorhanden
		iTreffpunktID = TreffpunktPruefen(sTreffpunkt)
		
		If iTreffpunktID = 0 Then
			'wenn keine ID gefunden wird, dann wird ein neuer Treffpunkt hinzugefügt, welcher aber in der Auswahl nicht sichtbar ist
			iTreffpunktID = InsertNeuerTreffpunkt(sTreffpunkt, 0)
		End If
		
		sInsert = "INSERT INTO Termine (" & _
									"Fahrlehrer_ID, " & _
									"Schueler_ID, " & _
									"MatchCode, " & _
									"Termin, " & _
									"Klassen_ID, " & _
									"Klasse, " & _
									"Fahrtbezeichnung_ID, " & _
									"FahrtBezeichnung_Abkuerzen, " & _
									"Starttermin, " & _
									"Dauer, " & _
									"Treffpunkt_ID, " & _
									"MeetingPoint, " & _
									"Hat_Unterschrift, " & _
									"Fahrzeug_ID, " & _
									"Begleitfahrzeug_ID, " & _
									"PruefungsErgebnis, " & _
									"PruefungsTagNr, " & _
									"TransKz) " & _
									"VALUES ( " & _
											"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _ 
											"(SELECT SchuelerID FROM Schueler WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' AND SchuelerID = " & Main.iAusgewaehlterSchuelrID & "),  " & _
											"'" & Main.sAusgewaehlterSchueler & "', " & _
											"'" & sTermin & "', " & _
											"(SELECT OID FROM Klassen WHERE Bezeichnung LIKE '" & sKlasse & "' AND Enable = 1), " & _
											"'" & sKlasse & "', " & _ 
											"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
											"'" & sFahrbezeichnung & "', " & _
											"'" & sStartzeit & "', " & _
											"'" & sDauer & "', " & iTreffpunktID & ", " & _
											"(SELECT Bezeichnung FROM Treffpunkt WHERE OID = " & iTreffpunktID & "), " & _ 
											"0, " & _
											"(SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung like '" & sKFZ & "'), " & _
											"(SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung LIKE '" & sBegleitfahrzeug & "'), " & _
											"' ', " & _
											"' ', " & _
											"'T' " & _
											") "
	End If
	'Log(sInsert)
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sInsert)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim speichern der Daten (Insert) " & CRLF & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Sonstige Tätigkeiten neu speichern
Sub SonstigeTaetigkeitenInsert(sTermin As String, sStartzeit As String, sDauer As String, sFahrbezeichnung As String, sFahrlehrer As String, bUnterschrift As Boolean) As Boolean
	Dim bResult As Boolean
	Dim sInsert As String
	
	'Unterschrift-ID ermitteln
	Dim iID As Int
	Dim sSignature As String
	
	If bUnterschrift Then
		iID = GetSignatureID(sTermin)
		sSignature = GetSignatureString(sTermin)
	End If
	
	sInsert = "INSERT INTO Termine (" & _
							"Fahrlehrer_ID, " & _
							"Schueler_ID, " & _
							"MatchCode, " & _
							"Termin, " & _
							"Klassen_ID, " & _
							"Klasse, " & _
							"Fahrtbezeichnung_ID, " & _
							"FahrtBezeichnung_Abkuerzen, " & _
							"Starttermin, " & _
							"Dauer, " & _
							"Treffpunkt_ID, " & _
							"MeetingPoint, " & _
							"Hat_Unterschrift, " & _
							"Fahrzeug_ID, " & _
							"Begleitfahrzeug_ID, " & _
							"PruefungsErgebnis, " & _
							"PruefungsTagNr, " & _
							"TransKz) " & _
							"VALUES ( " & _
									"(SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _ 
									"999,  " & _
									"'Sonstige Tätigkeit', " & _
									"'" & sTermin & "', " & _
									"0, " & _
									"' ', " & _ 
									"(SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
									"'" & sFahrbezeichnung & "', " & _
									"'" & sStartzeit & "', " & _
									"'" & sDauer & "', " & _
									"0, " & _
									"'', " & _
									"0, " & _
									"0, " & _
									"0, " & _
									"' ', " & _
									"' ', " & _
									"' ' " & _
									") "
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sInsert)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim speichern der Sonstigen Tätigkeiten (Insert) " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Sonstige Tätigkeiten update
Sub SonstigeTaetigkeitenUpdate(iTerminID As Int, sTermin As String, sStartzeit As String, sDauer As String, sFahrbezeichnung As String, sFahrlehrer As String, bUnterschrift As Boolean) As Boolean
	Dim bResult As Boolean
	Dim sUpdate As String
	
	'Unterschrift-ID ermitteln
	Dim iID As Int
	Dim sSignature As String
	
	If bUnterschrift Then
		iID = GetSignatureID(iTerminID)
		sSignature = GetSignatureString(iTerminID)
	End If
	
	If iID > 0 Then
		sUpdate = "UPDATE Termine " & _
						"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _
							"Schueler_ID = 999, " & _
							"MatchCode = 'Sonstige Tätigkeit', " & _
							"Termin = '" & sTermin & "', " & _
							"Klassen_ID = 0, " & _
							"Klasse = ' ', " & _
							"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
							"FahrtBezeichnung_Abkuerzen = '" & sFahrbezeichnung & "', " & _
							"Starttermin = '" & sStartzeit & "', " & _
							"Dauer = '" & sDauer & "', " & _
							"Treffpunkt_ID = 0, " & _
							"MeetingPoint = ' ', " & _
							"Hat_Unterschrift = 1, " & _
							"UnterschriftJaNein = 'Ja', " & _
							"Unterschrift = '" & sSignature & "', " & _
							"Fahrzeug_ID = 0, " & _
							"Begleitfahrzeug_ID = 0, " & _
							"PruefungsErgebnis = ' ', " & _
							"PruefungsTagNr = ' ', " & _
							"TransKz = 'U' " & _
					"WHERE OID = " & iTerminID
	Else
		sUpdate = "UPDATE Termine " & _
						"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _
							"Schueler_ID = 999, " & _
							"MatchCode = 'Sonstige Tätigkeit', " & _
							"Termin = '" & sTermin & "', " & _
							"Klassen_ID = 0, " & _
							"Klasse = ' ', " & _
							"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
							"FahrtBezeichnung_Abkuerzen = '" & sFahrbezeichnung & "', " & _
							"Starttermin = '" & sStartzeit & "', " & _
							"Dauer = '" & sDauer & "', " & _
							"Treffpunkt_ID = 0, " & _
							"MeetingPoint = ' ', " & _
							"Hat_Unterschrift = 0, " & _
							"UnterschriftJaNein = 'Nein', " & _
							"Fahrzeug_ID = 0, " & _
							"Begleitfahrzeug_ID = 0, " & _
							"PruefungsErgebnis = ' ', " & _
							"PruefungsTagNr = ' ', " & _
							"TransKz = ' ' " & _
					"WHERE OID = " & iTerminID
	End If
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
				
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim speichern der Sonstigen Tätigkeiten (Update) " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Alle Fahrtenbezeichnungen holen
Sub FillFahrtenbezeichnungListView(lstFahrtBezeichnung As ListView)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Fahrtbezeichnungs-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrtbezeichnung WHERE SonstTaetigkeiten <> 1")
	
	Dim FahrtbezeichnungID(iAnzahl, 2) As String
	
	cur = Main.SQL1.ExecQuery("SELECT 	OID, " & _
										"Kuerzel, " & _
										"Beschreibung " & _
								"FROM Fahrtbezeichnung " & _
								"WHERE SonstTaetigkeiten <> 1 " & _
								"ORDER BY FahrtbezeichnungsNr")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstFahrtBezeichnung.AddSingleLine(cur.GetString("Kuerzel") & " - " & cur.GetString("Beschreibung"))
		FahrtbezeichnungID(i, 0) = cur.GetString("Kuerzel")
		FahrtbezeichnungID(i, 1) = cur.GetInt("OID")
	Next
	cur.Close
	Main.SQL1.Close
	
	Main.aFahrtbezeichnung = FahrtbezeichnungID
End Sub

'Alle Sonstige Tätigkeiten holen
Sub FillSonstigeTaetigkeitenListView(lstSonstigeTaetigkeiten As ListView)
	Dim cur As Cursor
	
	'Anzahl der Einträge in der Fahrtbezeichnungs-Tabelle ermitteln
	'damit Array mit richtiger Größe erstellt werden kann
	Dim iAnzahl As Int
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM Fahrtbezeichnung WHERE SonstTaetigkeiten = 1")
	
	Dim sSonstigeTaetigkeitenID(iAnzahl, 2) As String
	
	cur = Main.SQL1.ExecQuery("SELECT 	OID, " & _
										"Kuerzel, " & _
										"Beschreibung " & _
								"FROM Fahrtbezeichnung " & _
								"WHERE SonstTaetigkeiten = 1 ")
								
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		lstSonstigeTaetigkeiten.AddSingleLine(cur.GetString("Kuerzel") & " - " & cur.GetString("Beschreibung"))
		sSonstigeTaetigkeitenID(i, 0) = cur.GetString("Kuerzel")
		sSonstigeTaetigkeitenID(i, 1) = cur.GetInt("OID")
	Next
	cur.Close
	Main.SQL1.Close
	
	Main.aSonstigeTaetigkeiten = sSonstigeTaetigkeitenID
End Sub

Sub FahrlehrerGesamtstundenHolen(sDate As String) As Int(2)
	Dim iStunden(2) As Int
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Gesamtstunden des Fahrlehrers des ausgewählten Tages zusammenfassen
	Dim sGesamt, sFahrlehrer As String
	sGesamt = 	"SELECT SUM(Dauer) " & _
					"FROM Termine t " & _
					"	INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID " & _
				"WHERE t.Termin LIKE '" & sDate & "' " & _
				"AND f.IsSelected = 1"
				
	sFahrlehrer = 	"SELECT SUM(Dauer) " & _
						"FROM Termine t " & _
						"	INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID " & _
						"WHERE t.Termin LIKE '" & sDate & "' " & _
						"AND t.Klassen_ID > 0 " & _
						"AND f.IsSelected = 1"
				
	'Main.SQL1.BeginTransaction
	Try
		iStunden(0) = Main.SQL1.ExecQuerySingleResult(sGesamt)
		iStunden(1) = Main.SQL1.ExecQuerySingleResult(sFahrlehrer)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen der Tagessummen des Fahrlehrers " & LastException.Message) 'no changes will be made
		iStunden(0) = 0
		iStunden(1) = 0
	End Try
	
	Return iStunden
End Sub

'Alle Tageseinträge für den Fahrlehrer
Sub FahrlehrerTermineHolen(sDate As String, lstTermine As ListView)
	Dim cur As Cursor
	Dim sUnterschrift, sTreffpunkt, sUpdate As String
	Dim iTreffpunktID As Int
	
	lstTermine.Clear
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery("SELECT 	t.OID, " & _
										"t.Starttermin, " & _
										"t.MatchCode, " & _
										"t.Dauer, " & _
										"t.Treffpunkt_ID, " & _
										"CASE t.MeetingPoint WHEN t.MeetingPoint THEN t.MeetingPoint ELSE 0 END AS MeetingPoint, " & _
										"t.Klasse, " & _
										"t.FahrtBezeichnung_Abkuerzen, " & _
										"t.Hat_Unterschrift " & _
								"FROM Termine t " & _
									"INNER JOIN Fahrlehrer f ON f.OID = t.Fahrlehrer_ID " & _
								"WHERE t.termin LIKE '" & sDate & "' " & _
								"AND f.IsSelected = 1 " & _
								"ORDER BY t.Starttermin ")
	
	For i = 0 To cur.RowCount - 1
		cur.Position = i
		'Unterschrift aufdröseln
		If cur.GetInt("Hat_Unterschrift") > 0 Then
			sUnterschrift = "Ja"
		Else
			sUnterschrift = "Nein"
		End If
		
		'Treffpunkt auflösen
		iTreffpunktID = cur.GetInt("Treffpunkt_ID")
		
		If iTreffpunktID = 0 Then
			If cur.GetString("MeetingPoint") = 0 Then
				sTreffpunkt =  ""
			Else
				sTreffpunkt = cur.GetString("MeetingPoint")
				
				sUpdate = "UPDATE Termine " & _
							"SET Treffpunkt_ID = (SELECT OID FROM Treffpunkt WHERE Bezeichnung LIKE '" & sTreffpunkt & "') " & _
						  "WHERE OID = " & cur.GetInt("OID")
							
					'Main.SQL1.BeginTransaction
					Try
					    Main.SQL1.ExecNonQuery(sUpdate)
					    'Main.SQL1.TransactionSuccessful
					Catch
					    Log("Fehler beim speichern des Treffpunktes zum Termin (Update) " & LastException.Message) 'no changes will be made
					End Try
					'Main.SQL1.EndTransaction
			End If
		Else
			sTreffpunkt = Main.SQL1.ExecQuerySingleResult("SELECT Bezeichnung " & _
																"FROM Treffpunkt " & _
															"WHERE OID = " & iTreffpunktID)
		End If
		
		
		lstTermine.ScrollingBackgroundColor = Colors.Transparent
		'lstTermine.TwoLinesLayout.ItemHeight = 50dip
		
		Dim matchcode As String
		matchcode = cur.GetString("MatchCode")
		
		Dim sf As StringFunctions
		
		'kürzen der Namenslänge beim Anzeigen der Termine
		If matchcode.Length > 30 Then
			'Breite der Terminliste im Verhältnis zur Schülerlänge
			If lstTermine.Width <= 510 And lstTermine.Width > 450 Then
				matchcode = sf.Left(matchcode, 30)
			Else If lstTermine.Width <= 450 Then
				matchcode = sf.Left(matchcode, 25)
			End If
		End If
		
		
		Dim lvd As ListViewData		
'		lvd.FirstRow = cur.GetString("Starttermin") & " " & cur.GetString("MatchCode")
		lvd.FirstRow = cur.GetString("Starttermin") & " " & matchcode
		lvd.SecondRow=  cur.GetString("Dauer") & TAB & TAB & cur.GetString("Klasse") & TAB & TAB & cur.GetString("FahrtBezeichnung_Abkuerzen") & TAB & TAB & sUnterschrift & TAB & TAB & sTreffpunkt
		lstTermine.AddTwoLines2(lvd.FirstRow, lvd.SecondRow, lvd)
		
	Next
End Sub

'TerminID holen für Aktualisierung eines Termins
Sub TerminIDHolen(sTermin As String, sStartzeit As String, sDauer As String, sKlasse As String, sFahrbezeichnung As String, sTreffpunkt As String) As Int
	Dim iTerminID As Int
	
	Dim sSelect As String
	Dim cur As Cursor
	
	sSelect = "SELECT 	OID, " & _
						"Schueler_ID, " & _
						"MatchCode, " & _
						"Fahrzeug_ID, " & _
						"FahrtBezeichnung_ID, " & _
						"Treffpunkt_ID " & _
				"FROM Termine " & _
			  "WHERE Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)" & _
			  "AND Termin LIKE '" & sTermin & "'" & _
			  "AND Klasse LIKE '" & sKlasse & "'" & _ 
			  "AND FahrtBezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "')" & _
			  "AND Starttermin LIKE '" & sStartzeit & "'" & _
			  "AND Dauer LIKE '" & sDauer & "'" '& _
			  '"AND Treffpunkt_ID = (SELECT OID FROM Treffpunkt WHERE Bezeichnung LIKE '" & sTreffpunkt & "')"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		cur = Main.SQL1.ExecQuery(sSelect)
		
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			iTerminID = cur.GetInt("OID")
			Main.iAusgewaehlterSchuelrID = cur.GetInt("Schueler_ID") 
			Main.sAusgewaehlterSchueler = cur.GetString("MatchCode")
			For i = 0 To Main.aKFZ.Length - 1
				If Main.aKFZ(i, 1) = cur.GetInt("Fahrzeug_ID") Then			
					KFZ.KfzSelected = i
				End If
			Next
'			SchuelerKlasse.KlasseSelected = 
			For i = 0 To Main.aFahrtbezeichnung.Length - 1
				If Main.aFahrtbezeichnung(i, 1) = cur.GetInt("FahrtBezeichnung_ID") Then			
					Fahrtbezeichnung.FahrtSelected = i
				End If
			Next
			For i = 0 To Main.aTreffpunkt.Length - 1
				If Main.aTreffpunkt(i, 1) = cur.GetString("Treffpunkt_ID") Then
					Treffpunkt.TreffpunktSelected = i
				End If
			Next
		Next 
		'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen der TerminID " & LastException.Message) 'no changes will be made
		iTerminID = 0
	End Try
	'Main.SQL1.EndTransaction
	
	cur.Close
	Main.SQL1.Close
		
	Return iTerminID
End Sub

'ID für Sonstige Tätigkeiten holen
Sub SonstTaetigkeitenIDHolen(sTermin As String, sStartzeit As String, sDauer As String, sFahrbezeichnung As String) As Int
	Dim iTerminID As Int
	Dim sSelect As String
	Dim cur As Cursor
	
	sSelect = "SELECT 	OID, " & _
						"Schueler_ID, " & _
						"MatchCode, " & _
						"FahrtBezeichnung_ID " & _
				"FROM Termine " & _
			  "WHERE Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)" & _
			  "AND Termin LIKE '" & sTermin & "'" & _
			  "AND Starttermin LIKE '" & sStartzeit & "'" & _
			  "AND FahrtBezeichnung_Abkuerzen LIKE '" & sFahrbezeichnung & "' " & _
			  "AND Dauer LIKE '" & sDauer & "'"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		cur = Main.SQL1.ExecQuery(sSelect)
		
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			iTerminID = cur.GetInt("OID")
			For i = 0 To Main.aSonstigeTaetigkeiten.Length - 1
				If Main.aSonstigeTaetigkeiten(i, 1) = cur.GetInt("FahrtBezeichnung_ID") Then			
					SonstigeTaetigkeiten.SonstTaetigkeitSelected = i
				End If
			Next
		Next 
		'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen der ID des Sonstigen Tätigkeiten " & LastException.Message) 'no changes will be made
		iTerminID = 0
	End Try
	'Main.SQL1.EndTransaction
	cur.Close
	Main.SQL1.Close
		
	Return iTerminID
End Sub

'Termineintrag löschen
Sub TerminLoeschen(iPosition As Int, sDatum As String) As Boolean
	Dim bResult As Boolean
	Dim sDeleteTermin, sDeleteSignature, sSelect As String
	Dim iPositionID As Int
	Dim cur As Cursor
	
	'Ermitteln der zu löschenden ID
	sSelect = "SELECT t.OID " & _
					"FROM Termine t " & _
				"WHERE t.Termin LIKE '" & sDatum & "' " & _
				"AND t.Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)" & _
			  "ORDER BY t.Starttermin"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
					
	'Main.SQL1.BeginTransaction
	Try
		cur = Main.SQL1.ExecQuery(sSelect)
		
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			If i = iPosition Then
				iPositionID = cur.GetInt("OID")
			End If
		Next
		
		sDeleteTermin = "DELETE FROM Termine " & _
				"WHERE Termin LIKE '" & sDatum & "' " & _
				"AND OID = " & iPositionID
		
		sDeleteSignature = "DELETE FROM Signature " & _
				"WHERE Termine_ID = " & iPositionID
		
	    Main.SQL1.ExecNonQuery(sDeleteTermin)
		Main.SQL1.ExecNonQuery(sDeleteSignature)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim löschen eines Termineintrags " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	cur.Close
	Main.SQL1.Close
	
	Return bResult
End Sub

'Termineintrag löschen, da Termin neu gespeichert wird
Sub TerminLoeschenWennDoppeltGespeichert(iTerminID As Int) As Boolean
	Dim bResult As Boolean
	Dim sDeleteTermin, sDeleteSignature As String
	
	
	sDeleteSignature = "DELETE FROM Signature " & _
				"WHERE Termin_ID = " & iTerminID
	
	sDeleteTermin = "DELETE FROM Termine " & _
				"WHERE OID = " & iTerminID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sDeleteTermin)
		Main.SQL1.ExecNonQuery(sDeleteSignature)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
		Log("Fehler beim löschen eines Termineintrags " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Vorhandenen Termin aktualisieren
Sub DatenVormerkenUpdate(iTerminID As Int, sTermin As String, sStartzeit As String, sDauer As String, _
	sKlasse As String, sFahrbezeichnung As String, sTreffpunkt As String, sFahrlehrer As String, _
	sKFZ As String, sBegleitfahrzeug As String) As Boolean
	
	Dim bResult As Boolean
	Dim sUpdate As String
	
	'Treffpunkt ermitteln
	Dim iID As Int
	iID = TreffpunktPruefen(sTreffpunkt)
	
	If iID = 0 Then
		iID = InsertNeuerTreffpunkt(sTreffpunkt, 0)
	End If

	sUpdate = "UPDATE Termine " & _
					"SET Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1), " & _
						"Schueler_ID = " & Main.iAusgewaehlterSchuelrID & ", " & _
						"MatchCode = '" & Main.sAusgewaehlterSchueler & "', " & _
						"Termin = '" & sTermin & "', " & _
						"Klassen_ID = (SELECT OID FROM Klassen WHERE Bezeichnung LIKE '" & sKlasse & "' AND Enable = 1), " & _
						"Klasse = '" & sKlasse & "', " & _
						"Fahrtbezeichnung_ID = (SELECT OID FROM Fahrtbezeichnung WHERE Kuerzel LIKE '" & sFahrbezeichnung & "'), " & _
						"FahrtBezeichnung_Abkuerzen = '" & sFahrbezeichnung & "', " & _
						"Starttermin = '" & sStartzeit & "', " & _
						"Dauer = '" & sDauer & "', " & _
						"Treffpunkt_ID = " & iID & ", " & _
						"MeetingPoint = (SELECT Bezeichnung FROM Treffpunkt WHERE OID = " & iID & "), " & _
						"Hat_Unterschrift = 0, " & _
						"Fahrzeug_ID = (SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung like '" & sKFZ & "'), " & _
						"Begleitfahrzeug_ID = (SELECT FzgNr FROM Fahrzeuge WHERE Bezeichnung LIKE '" & sBegleitfahrzeug & "'), " & _
						"PruefungsErgebnis = ' ', " & _
						"PruefungsTagNr = ' ', " & _
						"TransKz = 'T' " & _				'Kennzeichen für Vormerken
				"WHERE OID = " & iTerminID
					
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Fehler beim speichern der Datenb (Update) " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

'Klasse des Schülers holen
Sub GetSchuelerKlasse(iAusgewaehlterSchueler As Int) As String
	Dim sKlasse, sSelect As String
	
	sSelect = "SELECT 	k.Bezeichnung " & _
					" FROM Klassen k " & _
				"JOIN Schueler s ON TRIM(s.Kl1Beantragt) = TRIM(k.Bezeichnung) " & _
				"WHERE s.SchuelerID = " & iAusgewaehlterSchueler
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    sKlasse = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen der beantragten Klasse des Schülers " & LastException.Message) 'no changes will be made
		sKlasse = "-"
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return sKlasse
End Sub

'KFZ eines Termins holen und zurück geben
Sub GetKFZ(iTerminID As Int) As String
	Dim sKFZ, sSelect As String
	
	sSelect = "SELECT 	f.Bezeichnung " & _
					" FROM Fahrzeuge f " & _
				"JOIN Termine t ON t.Fahrzeug_ID = f.FzgNr " & _
				"WHERE t.OID = " & iTerminID & _
				" AND t.Fahrzeug_ID > 0"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    sKFZ = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen des Begleitfahrzeuges " & LastException.Message) 'no changes will be made
		sKFZ = "keine Auswahl"
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return sKFZ
End Sub

'Begleitfahrzeug eines Termins holen und zurück geben
Sub BegleitfahrzeugHolen(ITerminID As Int) As String
	Dim sBegleitFahrzeug, sSelect As String
	
	sSelect = "SELECT 	f.Bezeichnung " & _
					" FROM Fahrzeuge f " & _
				"JOIN Termine t ON t.BegleitFahrzeug_ID = f.FzgNr " & _
				"WHERE t.OID = " & ITerminID & _
				" AND t.BegleitFahrzeug_ID > 0"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    sBegleitFahrzeug = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen des Begleitfahrzeuges " & LastException.Message) 'no changes will be made
		sBegleitFahrzeug = "keine Auswahl"
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return sBegleitFahrzeug
End Sub

'Fix hinterlegtes KFZ holen
Sub GetSelectedKFZ() As String
	Dim sSelectedKFZ, sSelect As String
	
	sSelect = "SELECT 	f.Bezeichnung " & _
					" FROM Fahrzeuge f " & _
				"WHERE f.IsSelected = 1"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    sSelectedKFZ = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim holen des Begleitfahrzeuges " & LastException.Message) 'no changes will be made
		sSelectedKFZ = "keine Auswahl"
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return sSelectedKFZ
End Sub

'alle relevanten Stammdaten holen - für Anzeige in Stammdaten-Tab
Sub StammdatenHolen() As String()
	Dim aStammdaten(19) As String
	Dim sSelect As String
	Dim cur As Cursor
	
	sSelect = "SELECT 	Strasse, " & _
						"Hausnummer, " & _
						"PLZ, " & _
						"Ort, " & _
						"CASE Phone WHEN Phone THEN Phone ELSE '' END AS Phone, " & _
						"CASE Mobil WHEN Mobil THEN Mobil ELSE '' END AS Mobil, " & _
						"CASE eMail WHEN eMail THEN eMail ELSE '' END AS eMail, " & _
						"CASE Arbeitsstelle WHEN Arbeitsstelle THEN Arbeitsstelle ELSE '' END AS Arbeitsstelle, " & _
						"CASE PhoneGeschaeft WHEN PhoneGeschaeft THEN PhoneGeschaeft ELSE '' END AS PhoneGeschaeft, " & _
						"CASE BeantragteKlasse WHEN BeantragteKlasse THEN BeantragteKlasse ELSE '' END AS BeantragteKlasse, " & _
						"CASE BesitztKlasse WHEN BesitztKlasse THEN BesitztKlasse ELSE '' END AS BesitztKlasse, " & _
						"Saldo, " & _
						"CASE AntragEingereichtAm WHEN AntragEingereichtAm THEN AntragEingereichtAm ELSE '' END AS AntragEingereichtAm, " & _
						"CASE AntragZurJN WHEN AntragZurJN THEN AntragZurJN ELSE '' END AS AntragZurJN, " & _
						"Geburtstag, " & _
						"Nation, " & _
						"AnmeldeDatum, " & _
						"SchuleFil " & _
				"FROM Schueler " & _
				"WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' " & _
				"AND SchuelerID = " & Main.iAusgewaehlterSchuelrID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	cur = Main.SQL1.ExecQuery(sSelect)
	
	cur.Position = 0
	aStammdaten = Array As String(cur.GetString("Strasse"), cur.getstring("Hausnummer"), cur.GetInt("PLZ"), _
			cur.GetString("Ort"), cur.GetString("Phone"), cur.GetString("Mobil"), cur.GetString("eMail"), _
			cur.GetString("Arbeitsstelle"), cur.GetString("PhoneGeschaeft"), cur.GetString("BeantragteKlasse"), _
			cur.GetString("BesitztKlasse"), cur.GetString("Saldo"), cur.GetString("AntragEingereichtAm"), _ 
			cur.GetString("AntragZurJN"), cur.GetString("Geburtstag"), cur.GetString("Nation"), _
			cur.GetString("AnmeldeDatum"), cur.GetString("SchuleFil"))
	
	cur.Close
	Main.SQL1.Close
	
	Return aStammdaten
	
End Sub

'alle Klassen relevaten Daten für die Stammdaten holen
Sub StammdatenKlassendatenHolen(iZahl As Int) As String()
	Dim aKlassendaten(11) As String
	Dim sSelect As String
	Dim cur As Cursor
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	Select iZahl
        Case 1 
			sSelect = "SELECT 	TheorieGrundStd, " & _
								"HatKla1TheoSpez, " & _
								"Kl1Beantragt, " & _
								"Kl1Ausbildungsstand, " & _
								"Kl1FahrStd, " & _
								"HatKla1FAuG, " & _
								"Kl1Ueberland, " & _
								"Kl1Autobahn, " & _
								"Kl1Nacht, " & _
								"Kl1UnterwsgAmFhrzg, " & _
								"Kl1TheoriePfgAm, " & _
								"Kl1PraktischePfgAm " & _
						"FROM Schueler " & _
						"WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' " & _
						"AND SchuelerID = " & Main.iAusgewaehlterSchuelrID
			
			cur = Main.SQL1.ExecQuery(sSelect)
			
			cur.Position = 0
			aKlassendaten = Array As String(cur.GetString("TheorieGrundStd"), cur.GetString("HatKla1TheoSpez"), cur.GetString("Kl1Beantragt"), _
					cur.GetString("Kl1Ausbildungsstand"), cur.GetString("Kl1FahrStd"), cur.GetString("HatKla1FAuG"), cur.GetString("Kl1Ueberland"), _
					cur.GetString("Kl1Autobahn"), cur.GetString("Kl1Nacht"), cur.GetString("Kl1UnterwsgAmFhrzg"), _
					cur.GetString("Kl1TheoriePfgAm"), cur.GetString("Kl1PraktischePfgAm"))
		Case 2
			sSelect = "SELECT 	TheorieGrundStd, " & _
								"HatKla2TheoSpez, " & _
								"Kl2Beantragt, " & _
								"Kl2Ausbildungsstand, " & _
								"Kl2FahrStd, " & _
								"HatKla2FAuG, " & _
								"Kl2Ueberland, " & _
								"Kl2Autobahn, " & _
								"Kl2Nacht, " & _
								"Kl2UnterwsgAmFhrzg, " & _
								"Kl2TheoriePfgAm, " & _
								"Kl2PraktischePfgAm " & _
						"FROM Schueler " & _
						"WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' " & _
						"AND SchuelerID = " & Main.iAusgewaehlterSchuelrID
						
			cur = Main.SQL1.ExecQuery(sSelect)
			
			cur.Position = 0
			aKlassendaten = Array As String(cur.GetString("TheorieGrundStd"), cur.GetString("HatKla2TheoSpez"), cur.GetString("Kl2Beantragt"), _
					cur.GetString("Kl2Ausbildungsstand"), cur.GetString("Kl2FahrStd"), cur.GetString("HatKla2FAuG"), cur.GetString("Kl2Ueberland"), _
					cur.GetString("Kl2Autobahn"), cur.GetString("Kl2Nacht"), cur.GetString("Kl2UnterwsgAmFhrzg"), _
					cur.GetString("Kl2TheoriePfgAm"), cur.GetString("Kl2PraktischePfgAm"))
		Case 3
			sSelect = "SELECT 	TheorieGrundStd, " & _
								"HatKla3TheoSpez, " & _
								"Kl3Beantragt, " & _
								"Kl3Ausbildungsstand, " & _
								"Kl3FahrStd, " & _
								"HatKla3FAuG, " & _
								"Kl3Ueberland, " & _
								"Kl3Autobahn, " & _
								"Kl3Nacht, " & _
								"Kl3UnterwsgAmFhrzg, " & _
								"Kl3TheoriePfgAm, " & _
								"Kl3PraktischePfgAm " & _
						"FROM Schueler " & _
						"WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' " & _
						"AND SchuelerID = " & Main.iAusgewaehlterSchuelrID
	
			cur = Main.SQL1.ExecQuery(sSelect)
			
			cur.Position = 0
			aKlassendaten = Array As String(cur.GetString("TheorieGrundStd"), cur.GetString("HatKla3TheoSpez"), cur.GetString("Kl3Beantragt"), _
					cur.GetString("Kl3Ausbildungsstand"), cur.GetString("Kl3FahrStd"), cur.GetString("HatKla3FAuG"), cur.GetString("Kl3Ueberland"), _
					cur.GetString("Kl3Autobahn"), cur.GetString("Kl3Nacht"), cur.GetString("Kl3UnterwsgAmFhrzg"), _
					cur.GetString("Kl3TheoriePfgAm"), cur.GetString("Kl3PraktischePfgAm"))
		Case 4
			sSelect = "SELECT 	TheorieGrundStd, " & _
								"HatKla4TheoSpez, " & _
								"Kl4Beantragt, " & _
								"Kl4Ausbildungsstand, " & _
								"Kl4FahrStd, " & _
								"HatKla4FAuG, " & _
								"Kl4Ueberland, " & _
								"Kl4Autobahn, " & _
								"Kl4Nacht, " & _
								"Kl4UnterwsgAmFhrzg, " & _
								"Kl4TheoriePfgAm, " & _
								"Kl4PraktischePfgAm " & _
						"FROM Schueler " & _
						"WHERE MatchCode LIKE '" & Main.sAusgewaehlterSchueler & "' " & _
						"AND SchuelerID = " & Main.iAusgewaehlterSchuelrID
	
			cur = Main.SQL1.ExecQuery(sSelect)
			
			cur.Position = 0
			aKlassendaten = Array As String(cur.GetString("TheorieGrundStd"), cur.GetString("HatKla4TheoSpez"), cur.GetString("Kl4Beantragt"), _
					cur.GetString("Kl4Ausbildungsstand"), cur.GetString("Kl4FahrStd"), cur.GetString("HatKla4FAuG"), cur.GetString("Kl4Ueberland"), _
					cur.GetString("Kl4Autobahn"), cur.GetString("Kl4Nacht"), cur.GetString("Kl4UnterwsgAmFhrzg"), _
					cur.GetString("Kl4TheoriePfgAm"), cur.GetString("Kl4PraktischePfgAm"))
	End Select
	
	cur.Close
	Main.SQL1.Close
	
	Return aKlassendaten
End Sub

'Standard Begleitfahrzeug setzen - Konfig
Sub SetBegleitfahrzeug(Checked As Boolean, sTabelle As String)
	Dim iValue As Int
	Dim sUpdate As String
	
	If Checked Then
		iValue = 1
	Else
		iValue = 0
	End If
	
	sUpdate = "UPDATE Konfigdaten " & _
					"SET " & sTabelle & " = " & iValue
							
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim speichern der Daten in Konfig (Update: " & sTabelle & ") " & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
End Sub

'alle Konfigdaten laden
Sub KonfigdatenLaden() As Boolean()
	Dim aKonfig(3) As Boolean
	Dim sSelect As String
	Dim sBegleitfahrzeug, sPruefergebnis, sBVFAusbildung As Boolean
	Dim cur As Cursor
	
'	sSelect = "SELECT 	Begleitfahrzeug_IsSelected, " & _
'						"Pruefungsergebnis_IsSelected, " & _
'						"BVFAusbildung_IsSelected " & _
'					"FROM Konfigdaten"
	sSelect = "SELECT Begleitfahrzeug_IsSelected, Pruefungsergebnis_IsSelected, BVFAusbildung_IsSelected FROM Konfigdaten"
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	 
	cur = Main.SQL1.ExecQuery(sSelect)
	
	cur.Position = 0
	
	If cur.GetString("Begleitfahrzeug_IsSelected") = 1 Then
		sBegleitfahrzeug = True
	Else
		sBegleitfahrzeug = False
	End If
	
	If cur.GetString("Pruefungsergebnis_IsSelected") = 1 Then
		sPruefergebnis = True
	Else
		sPruefergebnis = False
	End If
	
	If cur.GetString("BVFAusbildung_IsSelected") = 1 Then
		sBVFAusbildung = True
	Else
		sBVFAusbildung = False
	End If
	
	cur.Close
	Main.SQL1.Close
	
	aKonfig = Array As Boolean(sBegleitfahrzeug, sPruefergebnis, sBVFAusbildung)
	Return aKonfig
End Sub

'holt KFZID des ausgewählten KFZ
Sub GetKFZID(CheckedKFZ As String) As Int
	Dim iResult As Int
	Dim sSelect As String
	
	sSelect = "SELECT FzgNr " & _
					"FROM Fahrzeuge " & _
				"WHERE Bezeichnung LIKE '" & CheckedKFZ & "' "
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'neuen Treffpunkt hinzufügen - Fixe Treffpunkt-Anzahl ist 21
Sub InsertNeuerTreffpunkt(sNeuerTreffpunkt As String, iFixerEintrag As Int) As Int
	Dim bResult As Int
	Dim sInsertUpdate, sGetID As String
	Dim iID As Int
	
	'Anzahl der fixen Treffpunkte überprüfen - Begrenzung laut Hauptprogramm ist 21 Einträge
	If CheckAnzahlFixerTreffpunkte > 21 Then
		bResult = 0
	Else
		sNeuerTreffpunkt = sNeuerTreffpunkt.Trim
		
		'Längenbegrenzung des Treffpunktes auf 22 Zeichen
		If sNeuerTreffpunkt.Length > 22 Then
			sNeuerTreffpunkt = sNeuerTreffpunkt.SubString2(0, 21)
		Else If sNeuerTreffpunkt.Length = 0 Then
			'keine NULL Einträge in der DB -> führt zu Fehler bei der Übertragung zum Hauptprogramm
			sNeuerTreffpunkt = "-"	
		End If
			
		'eingebener Treffpunkt prüfen unf ggf neu anlegen
		iID = TreffpunktPruefen(sNeuerTreffpunkt)
		If iID > 0 Then
			sInsertUpdate = "UPDATE Treffpunkt " & _
								"SET fixer_Treffpunkt = " & iFixerEintrag & _
							" WHERE OID = " & iID
		Else
			sInsertUpdate = "INSERT INTO Treffpunkt (" & _
										"Bezeichnung, " & _
										"fixer_Treffpunkt )" & _
										"VALUES ( " & _
												"'" & sNeuerTreffpunkt & "', " & iFixerEintrag & " ) "
		End If
		
		'Rückgabe muss alte oder neue ID sein
		sGetID = 	"SELECT OID " & _
						"FROM Treffpunkt " & _
					"WHERE Bezeichnung LIKE '" & sNeuerTreffpunkt & "'"
				
		If (Main.SQL1.IsInitialized = False) Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
			Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
		End If 

		'Main.SQL1.BeginTransaction
		Try
		    Main.SQL1.ExecNonQuery(sInsertUpdate)
			bResult = Main.SQL1.ExecQuerySingleResult(sGetID)
		    'Main.SQL1.TransactionSuccessful
		Catch
		    Log("Fehler beim hinzufügen eines neuen Treffpunkts " & LastException.Message) 'no changes will be made
			bResult = 0
		End Try
		'Main.SQL1.EndTransaction
		Main.SQL1.Close
			
	End If
	
	Return bResult
End Sub

'Prüfen, ob Treffpunkt schon einmal eingetragen wurde
Sub TreffpunktPruefen(sNeuerTreffpunkt As String) As Int
	Dim iResult As Int
	Dim sSelect As String
	
	sSelect = 	"SELECT OID " & _
					"FROM Treffpunkt " & _
				"WHERE Bezeichnung LIKE '" & sNeuerTreffpunkt & "' "
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim prüfen des Treffpunktes " & LastException.Message) 'no changes will be made
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'fixierten Treffpunkt löschen bzw. deaktivieren
Sub RemoveNeuenTreffpunkt(sTreffpunkt As String)
	Dim sUpdate As String
	
	sUpdate = "UPDATE Treffpunkt " & _
					"SET fixer_Treffpunkt = 0 " & _
				"WHERE Bezeichnung LIKE '" & sTreffpunkt & "' "
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
	Catch
	    Log("Fehler beim löschen/deaktivieren eines Treffpunktes " & LastException.Message) 'no changes will be made
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
End Sub

'Prüfen, wieviel Treffpunkte als feste Vorgabe schon eingegeben wurden
Sub CheckAnzahlFixerTreffpunkte As Int
	Dim iAnzahl As Int
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) " & _
													"FROM Treffpunkt " & _
												"WHERE fixer_Treffpunkt = 1 ")
	Main.SQL1.Close
	
	Return iAnzahl	
End Sub

'Eintrag in DB, ob DB in Benutzung  oder nicht
Sub DBinBenutzung(bValue As Boolean)
	Try	
		If (Main.SQL1.IsInitialized = False) Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", False)
			Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
		End If 
			
		' Versionsnummer in Konfig-Tabelle schreiben
		Main.SQL1.ExecNonQuery("UPDATE Konfigdaten " & _
									"SET DBVersion  = '" & Main.VersionsNummer & "' ")
		
		If bValue Then
			If Main.SQL1.ExecQuerySingleResult("SELECT COUNT(*) FROM DateiPass") = 0 Then
				Main.SQL1.ExecNonQuery("INSERT INTO DateiPass (" & _
											"DatenVom, " & _
											"MdbDatei ) " & _
											"VALUES (" & _
												"'" & DateTime.Date(DateTime.Now) & "', " & _
												"'open' )"	)
			Else
				Main.SQL1.ExecNonQuery("UPDATE DateiPass " & _
											"SET MdbDatei = 'open',	" & _
												"DatenVom = '" & DateTime.Date(DateTime.Now) & "' ")
			End If
		Else
			Main.SQL1.ExecNonQuery("UPDATE DateiPass " & _
										"SET MdbDatei = 'closed' ")
		End If
				
		If Main.SQL1.IsInitialized = False Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", False)
			ToastMessageShow("Datenbank initialisiert", False)
		End If
	Catch
		Log("Datenbank-Fehler " & LastException.Message)
	End Try
	
	Main.SQL1.Close
End Sub

'Klassen holen
Sub CheckBegleitfahrzeug(sAuswahlKlasse As String) As Boolean
	Dim bResult As Boolean
	Dim sSelect As String
	Dim iJa As Int 
	
	sSelect = "SELECT MitBegleitfahrzeug " & _
					"FROM Klassen " & _
				"WHERE Bezeichnung LIKE '" & sAuswahlKlasse & "' "
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
		
	'Main.SQL1.BeginTransaction
	Try
	    iJa = Main.SQL1.ExecQuerySingleResult(sSelect)
	    'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
	    Log("Bei dieser Klasse '" & sAuswahlKlasse & "' ist kein Begleitfahrzeug definiert/hinterlegt") 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub 

'Startzeit prüfen, ob Überscheidung mit anderer Uhrzeit
Sub StartzeitPuefen(iTerminID As Int, sDatum As String, sStartzeit As String, sDauer As String) As Boolean
	Dim bResult As Boolean
	Dim sSelect As String
	Dim cur As Cursor
	Dim iStartzeit, iEndZeit, iStartzeitNeu, iEndzeitNeu As Double
	
	sSelect = "SELECT 	strftime('%s', Starttermin) AS Starttime, " & _
						"(strftime('%s', Starttermin) + (Dauer * 60)) AS EndTime, " & _
						"strftime('%s', '" & sStartzeit & "') AS newStarttime, " & _
						"(strftime('%s', '" & sStartzeit & "') + (" & sDauer & " * 60)) AS newEndTime " & _
					"FROM Termine " & _
					"WHERE Termin LIKE '" & sDatum & "' " & _
					"AND Fahrlehrer_ID = (SELECT OID FROM Fahrlehrer WHERE IsSelected = 1)"

	If Main.bDatenVorhanden Then
		sSelect = sSelect & "AND OID <> " & iTerminID
	End If

	bResult = True
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		cur = Main.SQL1.ExecQuery(sSelect)
		
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			
			If bResult Then
				If i = 0 Then
					iStartzeitNeu = cur.GetDouble("newStarttime")
					iEndzeitNeu = cur.GetDouble("newEndTime")
				End If
				
				iStartzeit = cur.GetDouble("Starttime")
				iEndZeit = cur.GetDouble("EndTime")
				
				If iStartzeitNeu >= iStartzeit And iStartzeitNeu < iEndZeit Then
					bResult = False
				Else If iEndzeitNeu > iStartzeit And iEndzeitNeu < iEndZeit Then
					bResult = False
				Else If iStartzeitNeu < iStartzeit And iEndzeitNeu >= iEndZeit Then
					bResult = False
				End If
			End If
		Next
		'Main.SQL1.TransactionSuccessful
	Catch
		Log("Terminliche Überscheidung")
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	cur.Close
	Main.SQL1.Close
	
	Return bResult
End Sub

'Prüfen, ob eingetragener Termin eine Prüfung ist
Sub IsPruefung(sFahrtbezeichnung As String) As Boolean
	Dim bReturn As Boolean
	Dim sSelect As String
	Dim iAnzahl As Int
	
	If (Main.bSonstTaetigkeit) Then
		bReturn = False
	Else
		sSelect = "SELECT COUNT(*) " & _
						"FROM Fahrtbezeichnung " & _
					"WHERE Kuerzel LIKE '" & sFahrtbezeichnung & "' " & _
					"AND Pruefungen = 1 " & _
					"AND SonstTaetigkeiten = 0"
			
		If (Main.SQL1.IsInitialized = False) Then
			Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
			Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
		End If 
		
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
		
		If iAnzahl > 0 Then
			bReturn = True
		Else
			bReturn = False
		End If
	
		Main.SQL1.Close
	End If
	
	Return bReturn
End Sub

'Prüfen, ob schon eine Unterschrift zu dem ausgewählten Termin vorliegt
Sub CheckSignature(iTerminID As Int) As Boolean
	Dim bReturn As Boolean
	Dim sSelect As String
	Dim iAnzahl As Int
	
	sSelect = "SELECT COUNT(*) " & _
					"FROM Signature " & _
				"WHERE Termine_ID = " & iTerminID
		
'	sSelect = "SELECT COUNT(*) " & _
'					"FROM Termine " & _
'				"WHERE Hat_Unterschrift > 0 " & _
'				"AND OID = " & iTerminID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	
	If iAnzahl > 0 Then
		bReturn = True
	Else
		bReturn = False
	End If
	
	Main.SQL1.Close
	
	Return bReturn
End Sub

'Speichern der Termindaten mit Erfassung des Prüfungsergebnis
Sub FahrdatenSpeichernUpdateMit(iTerminID As Int, sPruefungsergebnis As String, bUnterschrift As Boolean) As Boolean
	Dim bReturn As Boolean
	Dim sUpdate As String
	
	'Unterschrift-ID ermitteln
	Dim iID As Int
	Dim sSignature As String
	
	If bUnterschrift Then
		iID = GetSignatureID(iTerminID)
		sSignature = GetSignatureString(iTerminID)
	End If
	
	'Anzahl nur dann ermitteln, wenn Fahrtbezeichnung auch als Prüfung gekennzeichnet ist
	Dim iAnzahl As Int
	iAnzahl = 0
	
	If CheckTerminPruefung(iTerminID) Then
		'Anzahl bisheriger Prüfungsversuche
		iAnzahl = GetAnzahlPruefungen(iTerminID)
		iAnzahl = iAnzahl + 1
	End If
	
	If iID > 0 Then
		sUpdate = "UPDATE Termine " & _
						"SET Hat_Unterschrift = 1, " & _		'Change 23.11.2013: kein ID Eintrag -> 0 oder 1 da sonst Problem beim Mapping
							"UnterschriftJaNein = 'Ja', " & _
							"Unterschrift = '" & sSignature & "', " & _
							"PruefungsErgebnis = '" & sPruefungsergebnis & "', " & _
							"PruefungsTagNr = " & iAnzahl & ", " & _
							"TransKz = 'U' " & _			'Kennzeichen für 'Unterschrift'
					"WHERE OID = " & iTerminID
	Else
		sUpdate = "UPDATE Termine " & _
						"SET PruefungsErgebnis = '" & sPruefungsergebnis & "', " & _
							"PruefungsTagNr = " & iAnzahl & ", " & _
							"UnterschriftJaNein = 'Nein', " & _
							"TransKz = ' ' " & _			'Kennzeichen für 'keine Unterschrift'
					"WHERE OID = " & iTerminID	
	End If
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bReturn = True
	Catch
	    Log("Fehler beim speichern der Termindaten mit Erfassung des Prüfungsergebnis " & LastException.Message) 'no changes will be made
		bReturn = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bReturn
End Sub

'Speichern der Termindaten OHNE Erfassung des Prüfungsergebnis
Sub FahrdatenSpeichernUpdateOhne(iTerminID As Int, bUnterschrift As Boolean) As Boolean
	Dim bReturn As Boolean
	Dim sUpdate As String
	
	'Unterschrift-ID ermitteln
	Dim iID As Int
	Dim sSignatureSting As String
	
	If bUnterschrift Then
		iID = GetSignatureID(iTerminID)
		sSignatureSting = GetSignatureString(iTerminID)
	End If
	
	If sSignatureSting.Length > 0 Then
		sUpdate = "UPDATE Termine " & _
						"SET Hat_Unterschrift = 1, " & _		'Change 23.11.2013: kein ID Eintrag -> 0 oder 1 da sonst Problem beim Mapping
							"UnterschriftJaNein = 'Ja', " & _
							"Unterschrift = '" & sSignatureSting & "', " & _
							"TransKz = 'U' " & _			'Kennzeichen für 'Unterschrift'
					"WHERE OID = " & iTerminID
	Else
		sUpdate = "UPDATE Termine " & _
						"SET UnterschriftJaNein = 'Nein', " & _
						"TransKz = 'T' " & _			'Kennzeichen für 'keine Unterschrift'
					"WHERE OID = " & iTerminID	
		'ToDo: Signatur-Eintrag löschen sofern vorhanden
	End If
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
	    Main.SQL1.ExecNonQuery(sUpdate)
	    'Main.SQL1.TransactionSuccessful
		bReturn = True
	Catch
	    Log("Fehler speichern der Termindaten OHNE Erfassung des Prüfungsergebnis " & LastException.Message) 'no changes will be made
		bReturn = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bReturn
End Sub

'Unterschrift-ID holen
Sub GetSignatureID(iTerminID As Int) As Int
	Dim sSelect As String
	Dim iID As Int
	
	sSelect = "SELECT OID " & _
					"FROM Signature " & _
				"WHERE Termine_ID = " & iTerminID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	Try
		iID = Main.SQL1.ExecQuerySingleResult(sSelect)
	Catch
		Log("keine Unterschriften vorhanden")
		iID = 0
	End Try
	
	Main.SQL1.Close
	
	Return iID
End Sub

'Unterschrift-String holen
Sub GetSignatureString(iTerminID As Int) As String
	Dim sSelect As String
	Dim sSigString As String
	
	sSelect = "SELECT Unterschrift " & _
					"FROM Signature " & _
				"WHERE Termine_ID = " & iTerminID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	Try
		sSigString = Main.SQL1.ExecQuerySingleResult(sSelect)
		sSigString = sSigString.Replace("'", "''")
	Catch
		Log("Fehler beim holen des Unterschriften String" & LastException.Message)
		sSigString = ""
	End Try
	
	Main.SQL1.Close
	
	Return sSigString
End Sub

'Prüfung, ob Termin als Prüfung gekennzeichnet ist
Sub CheckTerminPruefung(iTerminID As Int) As Boolean
	Dim bResult As Boolean
	Dim sSelect As String
	Dim iOID As Int
	
	sSelect = "SELECT COUNT(*) " & _
				"FROM Fahrtbezeichnung f " & _
				"JOIN Termine t ON t.FahrtBezeichnung_ID = f.OID " & _
			   "WHERE f.Pruefungen = 1 " & _
			   "AND t.OID = " & iTerminID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	Try
		iOID = Main.SQL1.ExecQuerySingleResult(sSelect)
		If iOID > 0 Then
			bResult = True
		Else
			bResult = False
		End If
	Catch
		Log("kein Prüfungs-Termin")
		bResult = False
	End Try
	
	Main.SQL1.Close
	Return bResult
End Sub

'Anzahl der bisherigen Prüfungsversuche ermitteln
Sub GetAnzahlPruefungen(iTerminID As Int) As Int
	Dim iAnzahl As Int
	Dim sSelect As String
	
	sSelect = "SELECT PruefungsTagNr " & _
					"FROM Termine " & _
				"WHERE OID = " & iTerminID
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	Try
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	Catch
		Log("bisher keine Prüfungsversuch unternommen")
		iAnzahl = 0
	End Try
	
	Main.SQL1.Close
	
	Return iAnzahl
End Sub

'Speichern der Unterschift als verschlüsseltes Array
Sub UnterschriftSpeichern(iTerminID As Int, data() As Byte, sSignatureToString As String) As Boolean
	Dim bReturn As Boolean
	Dim sUpdate, sUpdate1, sSelect As String
	Dim iAnzahl As Int

	'Update für Termine-Tabelle
	sUpdate = "UPDATE Termine " & _
						"SET Unterschrift = '" & sSignatureToString & "', " & _
							"TransKz = 'U' " & _			'Kennzeichen für 'Unterschrift'
					"WHERE OID = " & iTerminID
	
	'Insert für Signature-Tabelle
	'Berücksichtigung, dass wenn Unterschrift schon vorhanden ein Update und kein Insert durchgeführt wird
	sSelect = "SELECT COUNT(*) FROM Signature " & _
				"WHERE Termine_ID = " & iTerminID
	
	sUpdate1 = "UPDATE Signature " & _
					"SET Datum = '" & DateTime.Date(DateTime.Now) & "', " & _
						"Unterschrift = '" & sSignatureToString & "' " & _
				"WHERE Termine_ID = " & iTerminID
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		'Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	''Main.SQL1.BeginTransaction
	Try
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
		
		'GHE: auskommentiert -> doppelte Speicherung - Performance Problem
		If iAnzahl > 0 Then
			Main.SQL1.ExecNonQuery(sUpdate1)
		Else
	    	Main.SQL1.ExecNonQuery2("INSERT INTO Signature ( Termine_ID, " & _
															"Datum, " & _
															"Unterschrift, " & _
															"Unterschrift_Blob ) " & _
													"VALUES (" & iTerminID & ", '" & DateTime.Date(DateTime.Now) & "', '" & sSignatureToString & "', ?)", Array As Object(data))
		End If
		
		Main.SQL1.ExecNonQuery(sUpdate)
	    ''Main.SQL1.TransactionSuccessful
		bReturn = True
	Catch
	    Log("Fehler beim speichern der Unterschrift " & LastException.Message) 'no changes will be made
		bReturn = False
	End Try
	''Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bReturn
End Sub

'Prüfung, ob Termin schon gespeichert wurde
Sub CheckEintragSchonGespeichert(iTerminID As Int) As String
	Dim sSelect, sResult As String
	
	sSelect = "SELECT TransKz " & _
					"FROM Termine " & _
				"WHERE OID = " & iTerminID
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		sResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
		
	Catch
		sResult = "Error"
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return sResult
End Sub

'Prüfung, ob fixe Treffpunkte in der DB-Tabelle Treffpunkte vorhanden sind
Sub CheckFixeTreffpunkte As Boolean
	Dim bReturn As Boolean
	Dim sSelect As String
	Dim iAnzahl As Int
	
	sSelect = "SELECT COUNT(*) " & _
					"FROM Treffpunkt " & _
				"WHERE Reserve1 IS NOT null"
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
	
	If iAnzahl > 0 Then
		bReturn = True
	Else
		bReturn = False
	End If
	
	Main.SQL1.Close
	Return bReturn
End Sub

'Schreiben der Treffpunkte aus INI Datei
Sub WriteTreffpunkte(aTreffpunkt() As String)
	Dim anz As Int
	
	anz = InsertNeuerTreffpunkt(aTreffpunkt(0), aTreffpunkt(1))
End Sub

#region Ausbildungskontrolle BVF
Sub CheckBVFDataExists(sDate As String, sSchueler As String) As Int
	Dim iBVFid As String
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If
	
	'Prüfen, ob Daten zum besthenden Datum schon vorhanden sind
	iBVFid = Main.SQL1.ExecQuerySingleResult("SELECT OID " & _
													"FROM AusbKontrolle " & _
													"WHERE SchuelerID = " & Main.iAusgewaehlterSchuelrID & _
													" AND MatchCode = '" & sSchueler & "' " & _
													" AND Datum = '" & sDate & "' ")	
	Main.SQL1.Close
	
	If iBVFid = Null Then
		Return 0
	Else
		Return iBVFid
	End If
End Sub

Sub InsertBVFData(sDate As String, sSchueler As String, cbBesonderhEinsteigen As Int, cbEinstellen As Int, cbLenkrad As Int, cbSpiegel As Int, cbKopfstuetze As Int, cbSitz As Int, cbLenkradhaltung As Int, cbPedale As Int, cbGurt As Int, cbSchaltWaehlhebel As Int, cbZuendschloss As Int, cbMotorAnlassen As Int, _
			cbAnfahrAnhalte As Int, cbSchaltuebg As Int, cbHoch1_2 As Int, cbHoch2_3 As Int, cbHoch3_4 As Int, cbRunter4_3 As Int, cbRunter3_2 As Int, cbRunter2_1 As Int, cbRunter4_2 As Int, cbRunter4_1 As Int, cbRunter3_1 As Int, cbLenkuebung As Int, cbUmkehren As Int, cbEinparkenLaengs As Int, _
			cbLVorwaertsRechts As Int, cbLRueckwaertsLinks As Int, cbLRueckwaertsRechts As Int, cbLVorwaertsLinks As Int, cbRueckwaertsfahren As Int, cbEinparkenQuer As Int, cbQVorwaertsRechts As Int, cbQRueckwaertsLinks As Int, _
			cbQRueckwaertsRechts As Int, cbQVorwaertsLinks As Int, cbGefahrbremsung As Int, cbRollenSchalten As Int, cbBremsSchalten As Int, cbBremsuebung As Int, cbDegressiv As Int, cbZielbremsung As Int, cbGefahrsituation As Int, cbGefaelle As Int, _
			cbAnhalten As Int, cbAnfahren As Int, cbRueckwaerts As Int, cbSichern As Int, cbSchalten As Int, cbSteigung As Int, cbStAnhalten As Int, cbStAnfahren As Int, cbStRueckwaerts As Int, cbStSichern As Int, cbStSchalten As Int, cbTastgeschw As Int, cbBedienKontroll As Int, _
			cbOertlichBesonder As Int, cbFahrbahnbenutzung As Int, cbEinordnen As Int, cbMarkierungen As Int, cbFahrstreifenwechsel As Int, cbLinks As Int, cbRechts As Int, cbVorbeifUeberholen As Int, cbAbbiegen As Int, cbABRechts As Int, cbABLinks As Int, cbMehrspurig As Int, _
			cbRadweg As Int, cbSonderstreifen As Int, cbStrassenbahn As Int, cbEinbahnstrasse As Int, cbVorfahrt As Int, cbRechtsVorLinks As Int, cbGruenpfeil As Int, cbPolizeibeamte As Int, cbGruenpfeilSchild As Int, cbGeschwAbstand As Int, cbSituationVerkehrstn As Int, _
			cbFussgaengerueberweg As Int, cbOeffentlVerkehrsm As Int, cbAeltereBehinderte As Int, cbEinbahnstrRadfahrer As Int, cbKinder As Int, cbSchulbus As Int, cbRadfahrerMofa As Int, cbVerkehrsberuhigt As Int, cbSchwierigeVerkehrsf As Int, cbEngpass As Int, _
			cbKreisverkehr As Int, cbBahnuebergangWarte As Int, cbKritischeVerkehrss As Int, cbHauptverkehrszt As Int, cbPartnerVerhalten As Int, cbSchwungNutzen As Int, cbFussgaengerSchutzb As Int, cbAngepassteGeschw As Int, cbAbstand As Int, cbULVorne As Int, _
			cbULHinten As Int, cbULSeitlich As Int, cbBeobachtSpiegel As Int, cbVerkehrszeichen As Int, cbKreuzungEinmuend As Int, cbKurven As Int, cbSteigungen As Int, cbULGefaelle As Int, cbAlleen As Int, cbUeberholen As Int, cbBesondereSituat As Int, cbLiegenblSichern As Int, _
			cbEinfahrenOrtsch As Int, cbFussgaenger As Int, cbWildTiere As Int, cbBesondereAnford As Int, cbLeistungsgrenze As Int, cbOrientierung As Int, cbAblenkung As Int, cbFahrtplanung As Int, cbEinfahrtAB As Int, cbABFahrbahnwechsel As Int, cbGeschwindigkeit As Int, _
			cbABAbstand As Int, cbABVorne As Int, cbABHinten As Int, cbABSeitlich As Int, cbABUeberholen As Int, cbSchilder As Int, cbVorbeifahren As Int, cbRastParkTank As Int, cbVerhUnfall As Int, cbDichterVerkehr As Int, cbBesonderSituat As Int, cbBesonderAnford As Int, _
			cbABLeistungsgrenze As Int, cbKonfliktSitua As Int, cbABAblenkung As Int, cbBeleuchtung As Int, cbKontrolle As Int, cbEinstell As Int, cbBenutzung As Int, cbFernlicht As Int, cbVerlassenBAB As Int, cbBeleuchtStrasse As Int, cbUnbeleuchtStrasse As Int, cbParken As Int, cbDUBesonderSituat As Int, _
			cbSchlechteWitterung As Int, cbTiere As Int, cbBahnuebergaenge As Int, cbUnbelVerkehrTN As Int, cbDUBesonderAnfor As Int, cbBlendung As Int, cbDUOrientierung As Int, cbAbschlussbesp As Int, cbSelbstFahren As Int, cbInnerorts As Int, cbAusserorts As Int, cbVerantwFahren As Int, cbTestfPruef As Int, _
			cbFAKT As Int, cbAndere As Int, cbWiederholung As Int, cbLeistungsbew As Int, cbReifen As Int, cbEinAusschalten As Int, cbFunktionPruefen As Int, cbStandlicht As Int, cbNebelschluss As Int, cbBlinker As Int, cbAbblendlicht As Int, cbWarnblicke As Int, cbHupe As Int, cbBSFernlicht As Int, cbSchlussLeuchte As Int, _
			cbBremslicht As Int, cbKontrollLBenenn As Int, cbRueckstrahler As Int, cbVorhandensein As Int, cbBeschaedigung As Int, cbLenkung As Int, cbLenkschlEntriegeln As Int, cbPruefLenkSpiel As Int, cbFunktBremse As Int, cbBetriebsBremse As Int, cbFeststellBremse As Int, _
			cbAnlegenGurt As Int, cbRichtigSitz As Int, cbEinstellRueckspiegel As Int, cbEinKopfstuetze As Int, cbEinLenkrad As Int, cbBedienenAgg As Int, cbHeizung As Int, cbHeckHeizung As Int, cbBehSonderaus As Int, cbLueftung As Int, cbKlimaanlage As Int, cbEnergieNutzung As Int, _
			cbKeineUnnVerbr As Int, cbRechtztAbsch As Int, cbMotorraum As Int, cbMotoroel As Int, cbKuehlmittel As Int, cbScheibenwaschm As Int, cbTanken As Int, cbBremsen As Int, cbSicherungsmittel As Int, cbWarndreieck As Int, cbBordwerkzeug As Int, cbZusaetzlichAus As Int, cbVerbandskasten As Int, cbAussenkontrolle As Int, _
			cbScheibenWischer As Int, cbKennzeichen As Int, cbCheckSpiegel As Int, cbCheckBeleuchtung As Int, cbLadung As Int, cbLadungssicherung As Int, cbKenntlichmachung As Int, cbFahreSchlWitt As Int, cbWittLueftung As Int, cbWittScheiben As Int, cbRegen As Int, _
			cbWasserlachen As Int, cbWindSturm As Int, cbMatchSchnee As Int, cbEis As Int, cbWittBeleuchtung As Int, etNotizen As String) As Boolean
	Dim sInsertUpdate As String
	Dim bResult As Boolean
	
	sInsertUpdate = "INSERT INTO AusbKontrolle (" & _
								"SchuelerID, " & _
								"MatchCode, " & _
								"Datum, " & _
								"BesonderhEinsteigen, " & _
								"Einstellen, " & _
								"Lenkrad, " & _
								"Spiegel, " & _
								"Kopfstuetze, " & _
								"Sitz, " & _
								"Lenkradhalt, " & _
								"Pedale, " & _
								"Gurt, " & _
								"SchaltWaehlhebel, " & _
								"Zuendschloss, " & _
								"MotorAnlassen, " & _
								"AnfahrAnhalte, " & _
								"Schaltuebg, " & _
								"hoch1_2, " & _
								"hoch2_3, " & _
								"hoch3_4, " & _
								"runter4_3, " & _
								"runter3_2, " & _
								"runter2_1, " & _
								"runter4_2, " & _
								"runter4_1, " & _
								"runter3_1, " & _
								"Lenkuebg, " & _
								"Umkehren, " & _
								"EinpLaengs, " & _
								"LVorwRechts, " & _
								"LRueckwLinks, " & _
								"LRueckwRechts, " & _
								"LVorwLinks, " & _
								"Rueckfahren, " & _
								"EinpQuer, " & _
								"QVvorwRechts, " & _
								"QRueckwLinks, " & _
								"QRueckwRechts, " & _
								"QVorwLinks, " & _
								"Gefahrbrems, " & _
								"RollenUndSchalten, " & _
								"AbbrUndSchalten, " & _
								"Bremsuebung, " & _
								"degressiv, " & _
								"Zielbremsung, " & _
								"GefahrSituation, " & _
								"Gefaelle, " & _
								"GeAnhalten, " & _
								"GeAnfahren, " & _
								"GeRueckw, " & _
								"GeSichern, " & _
								"GeSchalten, " & _
								"Steigung, " & _
								"StAnhalten, " & _
								"StAnfahren, " & _
								"StRueckw, " & _
								"StSichern, " & _
								"StSchalten, " & _
								"Tastgeschw, " & _
								"BedienungKontrolle, " & _
								"OertlicheBesonder, " & _
								"FahrbahnNutz, " & _
								"Einordnen, " & _
								"Markierungen, " & _
								"FahrstreifenWechsel, " & _
								"FahrLinks, " & _
								"FahrRechts, " & _
								"Vorbeifahren, " & _
								"Abbiegen, " & _
								"AbbRechts, " & _
								"AbbLinks, " & _
								"FahrMehrSp, " & _
								"FahrRadweg, " & _
								"FahrSonderStr, " & _
								"FahrStrassenb, " & _
								"FahrEinbahnstr, " & _
								"Vorfahrt, " & _
								"RechtsVorLinks, " & _
								"Gruenpfeil, " & _
								"PolizeiBeamte, " & _
								"GruenpfeilSchild, " & _
								"GeschwAbstand, " & _
								"SituationTN, " & _
								"Fussgaenger, " & _
								"OeffentVKTN, " & _
								"AeltereBehind, " & _
								"EinbahnStrRadf, " & _
								"Kinder, " & _
								"Schulbus, " & _
								"RadfMofa, " & _
								"VerkBeruhigt, " & _
								"SchwierigeVKFuehrung, " & _
								"Engpass, " & _
								"Kreisverkehr, " & _
								"Bahnuebergang, " & _
								"KritischeVKSit, " & _
								"HauptVKZeit, " & _
								"PartnerVerhalten, " & _
								"SchwungNutzen, " & _
								"FussgaengerSchutz, " & _
								"AngepGeschw, " & _
								"Abstand, " & _
								"AbstVorne, " & _
								"AbstHinten, " & _
								"AbstSeite, " & _
								"BeobSpiegel, " & _
								"VerkehrsZeich, " & _
								"KreuzMuend, " & _
								"Kurven, " & _
								"Steigungen, " & _
								"BesGefaelle, " & _
								"Alleen, " & _
								"Ueberholen, " & _
								"BesSituation, " & _
								"LiegeblAbsicher, " & _
								"EinfOrtschaft, " & _
								"BesFussgaenger, " & _
								"WildTiere, " & _
								"BesAnforderung, " & _
								"Leistungsgr, " & _
								"Orientierung, " & _
								"Ablenkung, " & _
								"Fahrtplanung, " & _
								"EinfahBAB, " & _
								"FahrStrWechsel, " & _
								"Geschwindig, " & _
								"AbstandBAB, " & _
								"VorneBAB, " & _
								"HintenBAB, " & _
								"SeiteBAB, " & _
								"BABUeberholen, " & _
								"SchildMarkierung, " & _
								"VoebeifAnschluss, " & _
								"RastParkTank, " & _
								"VerhaltenUnfall, " & _
								"DichtStau, " & _
								"BesSitBAB, " & _
								"BesAnfordBAB, " & _
								"LeistungsgrBAB, " & _
								"KonfliktBAB, " & _
								"AblenkungBAB, " & _
								"VerlassBAB, " & _
								"Beleuchtung, " & _
								"KontrolleBel, " & _
								"EinstellBel, " & _
								"BenutzBel, " & _
								"FernlichtBel, " & _
								"BelStrasse, " & _
								"UnbelStrasse, " & _
								"Parken, " & _
								"BesSitDunkel, " & _
								"SchlechWittDun, " & _
								"TiereDun, " & _
								"BahnueberDun, " & _
								"UnbelVKTN, " & _
								"BesAnforDun, " & _
								"Blendung, " & _
								"OrientDun, " & _
								"AbschlussbespDun, " & _
								"SelbstFahren, " & _
								"Innerorts, " & _
								"Ausserorts, " & _
								"VerantwFahren, " & _
								"TestfPruefung, " & _
								"FAKT, " & _
								"andere, " & _
								"WiederhVertief, " & _
								"Leistungsbew, " & _
								"Reifen, " & _
								"EinAusschalten, " & _
								"FunkPruefen, " & _
								"Standlicht, " & _
								"Nebelschl, " & _
								"Blinker, " & _
								"Abblendlicht, " & _
								"Warnblick, " & _
								"Hupe, " & _
								"Fernlicht, " & _
								"SchlussLeuchte, " & _
								"Bremslicht, " & _
								"KontrollLeuchten, " & _
								"Rueckstrahler, " & _
								"Vorhanden, " & _
								"Beschaedigt, " & _
								"Lenkung, " & _
								"LenkschlEntriegel, " & _
								"Lenkspiel, " & _
								"FunkBremsen, " & _
								"BetriebBremse, " & _
								"FeststellBremse, " & _
								"Sitzeinstellung, " & _
								"EinstRueckspiegel, " & _
								"EinstKopf, " & _
								"EinstLenksrad, " & _
								"AnlegenGurt, " & _
								"BedienHeizung, " & _
								"Heizung, " & _
								"HeckHeizung, " & _
								"BehSonderAusstg, " & _
								"Lueftung, " & _
								"Klima, " & _
								"EnergiespNutz, " & _
								"KeineUnnVerbr, " & _
								"RechtzAbschalten, " & _
								"CheckMotorraum, " & _
								"Motoroel, " & _
								"Kuehlmittel, " & _
								"Wischwasser, " & _
								"Tanken, " & _
								"SicherungsMittel, " & _
								"WarnDreieck, " & _
								"Bordwerkzeug, " & _
								"zusaetzAusruest, " & _
								"Verbandskasten, " & _
								"AussenKontrolle, " & _
								"Scheiben, " & _
								"KennzeichenHUAU, " & _
								"CheckSpiegel, " & _
								"CheckBeleuchtung, " & _
								"Bremsung, " & _
								"Ladung, " & _
								"Sicherung, " & _
								"KenntlichMachung, " & _
								"FahrenSchelchtWetter, " & _
								"WittLueftung, " & _
								"ScheibenWischer, " & _
								"RegenSprueh, " & _
								"Wasserlachen, " & _
								"WindSturm, " & _
								"SchneeMatsch, " & _
								"Eis, " & _
								"WittBeleucht, " & _
								"Kommentar ) " & _
								"Values ( " & _
										"'" & Main.iAusgewaehlterSchuelrID & "', " & _
										"'" & sSchueler & "', " & _
										"'" & sDate & "', " & _ 
										cbBesonderhEinsteigen & ", " & _
										cbEinstellen & ", " & _
										cbLenkrad & ", " & _
										cbSpiegel & ", " & _
										cbKopfstuetze & ", " & _
										cbSitz & ", " & _
										cbLenkradhaltung & ", " & _
										cbPedale & ", " & _
										cbGurt & ", " & _
										cbSchaltWaehlhebel & ", " & _
										cbZuendschloss & ", " & _
										cbMotorAnlassen & ", " & _
										cbAnfahrAnhalte & ", " & _
										cbSchaltuebg & ", " & _
										cbHoch1_2 & ", " & _
										cbHoch2_3 & ", " & _
										cbHoch3_4 & ", " & _
										cbRunter4_3 & ", " & _
										cbRunter3_2 & ", " & _
										cbRunter2_1 & ", " & _
										cbRunter4_2 & ", " & _
										cbRunter4_1 & ", " & _
										cbRunter3_1 & ", " & _
										cbLenkuebung & ", " & _
										cbUmkehren & ", " & _
										cbEinparkenLaengs & ", " & _
										cbLVorwaertsRechts & ", " & _
										cbLRueckwaertsLinks & ", " & _
										cbLRueckwaertsRechts & ", " & _
										cbLVorwaertsLinks & ", " & _
										cbRueckwaertsfahren & ", " & _
										cbEinparkenQuer & ", " & _
										cbQVorwaertsRechts & ", " & _
										cbQRueckwaertsLinks & ", " & _
										cbQRueckwaertsRechts & ", " & _
										cbQVorwaertsLinks & ", " & _
										cbGefahrbremsung & ", " & _
										cbRollenSchalten & ", " & _
										cbBremsSchalten & ", " & _
										cbBremsuebung & ", " & _
										cbDegressiv & ", " & _
										cbZielbremsung & ", " & _
										cbGefahrsituation & ", " & _
										cbGefaelle & ", " & _
										cbAnhalten & ", " & _
										cbAnfahren & ", " & _
										cbRueckwaerts & ", " & _
										cbSichern & ", " & _
										cbSchalten & ", " & _
										cbSteigung & ", " & _
										cbStAnhalten & ", " & _
										cbStAnfahren & ", " & _
										cbStRueckwaerts & ", " & _
										cbStSichern & ", " & _
										cbStSchalten & ", " & _
										cbTastgeschw & ", " & _
										cbBedienKontroll & ", " & _
										cbOertlichBesonder & ", " & _
										cbFahrbahnbenutzung & ", " & _
										cbEinordnen & ", " & _
										cbMarkierungen & ", " & _
										cbFahrstreifenwechsel & ", " & _
										cbLinks & ", " & _
										cbRechts & ", " & _
										cbVorbeifUeberholen & ", " & _
										cbAbbiegen & ", " & _
										cbABRechts & ", " & _
										cbABLinks & ", " & _
										cbMehrspurig & ", " & _
										cbRadweg & ", " & _
										cbSonderstreifen & ", " & _
										cbStrassenbahn & ", " & _
										cbEinbahnstrasse & ", " & _
										cbVorfahrt & ", " & _
										cbRechtsVorLinks & ", " & _
										cbGruenpfeil & ", " & _
										cbPolizeibeamte & ", " & _
										cbGruenpfeilSchild & ", " & _
										cbGeschwAbstand & ", " & _
										cbSituationVerkehrstn & ", " & _
										cbFussgaengerueberweg & ", " & _
										cbOeffentlVerkehrsm & ", " & _
										cbAeltereBehinderte & ", " & _
										cbEinbahnstrRadfahrer & ", " & _
										cbKinder & ", " & _
										cbSchulbus & ", " & _
										cbRadfahrerMofa & ", " & _
										cbVerkehrsberuhigt & ", " & _
										cbSchwierigeVerkehrsf & ", " & _
										cbEngpass & ", " & _
										cbKreisverkehr & ", " & _
										cbBahnuebergangWarte & ", " & _
										cbKritischeVerkehrss & ", " & _
										cbHauptverkehrszt & ", " & _
										cbPartnerVerhalten & ", " & _
										cbSchwungNutzen & ", " & _
										cbFussgaengerSchutzb & ", " & _
										cbAngepassteGeschw & ", " & _
										cbAbstand & ", " & _
										cbULVorne & ", " & _
										cbULHinten & ", " & _
										cbULSeitlich & ", " & _
										cbBeobachtSpiegel & ", " & _
										cbVerkehrszeichen & ", " & _
										cbKreuzungEinmuend & ", " & _
										cbKurven & ", " & _
										cbSteigungen & ", " & _
										cbULGefaelle & ", " & _
										cbAlleen & ", " & _
										cbUeberholen & ", " & _
										cbBesondereSituat & ", " & _
										cbLiegenblSichern & ", " & _
										cbEinfahrenOrtsch & ", " & _
										cbFussgaenger & ", " & _
										cbWildTiere & ", " & _
										cbBesondereAnford & ", " & _
										cbLeistungsgrenze & ", " & _
										cbOrientierung & ", " & _
										cbAblenkung & ", " & _
										cbFahrtplanung & ", " & _
										cbEinfahrtAB & ", " & _
										cbABFahrbahnwechsel & ", " & _
										cbGeschwindigkeit & ", " & _
										cbABAbstand & ", " & _
										cbABVorne & ", " & _
										cbABHinten & ", " & _
										cbABSeitlich & ", " & _
										cbABUeberholen & ", " & _
										cbSchilder & ", " & _
										cbVorbeifahren & ", " & _
										cbRastParkTank & ", " & _
										cbVerhUnfall & ", " & _
										cbDichterVerkehr & ", " & _
										cbBesonderSituat & ", " & _
										cbBesonderAnford & ", " & _
										cbABLeistungsgrenze & ", " & _
										cbKonfliktSitua & ", " & _
										cbABAblenkung & ", " & _
										cbVerlassenBAB & ", " & _
										cbBeleuchtung & ", " & _
										cbKontrolle & ", " & _
										cbEinstell & ", " & _
										cbBenutzung & ", " & _
										cbFernlicht & ", " & _
										cbBeleuchtStrasse & ", " & _
										cbUnbeleuchtStrasse & ", " & _
										cbParken & ", " & _
										cbDUBesonderSituat & ", " & _
										cbSchlechteWitterung & ", " & _
										cbTiere & ", " & _
										cbBahnuebergaenge & ", " & _
										cbUnbelVerkehrTN & ", " & _
										cbDUBesonderAnfor & ", " & _
										cbBlendung & ", " & _
										cbDUOrientierung & ", " & _
										cbAbschlussbesp & ", " & _
										cbSelbstFahren & ", " & _
										cbInnerorts & ", " & _
										cbAusserorts & ", " & _
										cbVerantwFahren & ", " & _
										cbTestfPruef & ", " & _
										cbFAKT & ", " & _
										cbAndere & ", " & _
										cbWiederholung & ", " & _
										cbLeistungsbew & ", " & _
										cbReifen & ", " & _
										cbEinAusschalten & ", " & _
										cbFunktionPruefen & ", " & _
										cbStandlicht & ", " & _
										cbNebelschluss & ", " & _
										cbBlinker & ", " & _
										cbAbblendlicht & ", " & _
										cbWarnblicke & ", " & _
										cbHupe & ", " & _
										cbBSFernlicht & ", " & _
										cbSchlussLeuchte & ", " & _
										cbBremslicht & ", " & _
										cbKontrollLBenenn & ", " & _
										cbRueckstrahler & ", " & _
										cbVorhandensein & ", " & _
										cbBeschaedigung & ", " & _
										cbLenkung & ", " & _
										cbLenkschlEntriegeln & ", " & _
										cbPruefLenkSpiel & ", " & _
										cbFunktBremse & ", " & _
										cbBetriebsBremse & ", " & _
										cbFeststellBremse & ", " & _
										cbRichtigSitz & ", " & _
										cbEinstellRueckspiegel & ", " & _
										cbEinKopfstuetze & ", " & _
										cbEinLenkrad & ", " & _
										cbAnlegenGurt & ", " & _
										cbBedienenAgg & ", " & _
										cbHeizung & ", " & _
										cbHeckHeizung & ", " & _
										cbBehSonderaus & ", " & _
										cbLueftung & ", " & _
										cbKlimaanlage & ", " & _
										cbEnergieNutzung & ", " & _
										cbKeineUnnVerbr & ", " & _
										cbRechtztAbsch & ", " & _
										cbMotorraum & ", " & _
										cbMotoroel & ", " & _
										cbKuehlmittel & ", " & _
										cbScheibenwaschm & ", " & _
										cbTanken & ", " & _
										cbSicherungsmittel & ", " & _
										cbWarndreieck & ", " & _
										cbBordwerkzeug & ", " & _
										cbZusaetzlichAus & ", " & _
										cbVerbandskasten & ", " & _
										cbAussenkontrolle & ", " & _
										cbScheibenWischer & ", " & _
										cbKennzeichen & ", " & _
										cbCheckSpiegel & ", " & _
										cbCheckBeleuchtung & ", " & _
										cbBremsen & ", " & _
										cbLadung & ", " & _
										cbLadungssicherung & ", " & _
										cbKenntlichmachung & ", " & _
										cbFahreSchlWitt & ", " & _
										cbWittLueftung & ", " & _
										cbWittScheiben & ", " & _
										cbRegen & ", " & _
										cbWasserlachen & ", " & _
										cbWindSturm & ", " & _
										cbMatchSchnee & ", " & _
										cbEis & ", " & _
										cbWittBeleuchtung & ", " & _
										"'" & etNotizen & "'" & _
										") "

	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If

	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sInsertUpdate)
		'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
		Log("Fehler beim speichern der BVF Daten " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bResult
End Sub

Sub UpdateBVFData(sDate As String, sSchueler As String, cbBesonderhEinsteigen As Int, cbEinstellen As Int, cbLenkrad As Int, cbSpiegel As Int, cbKopfstuetze As Int, cbSitz As Int, cbLenkradhaltung As Int, cbPedale As Int, cbGurt As Int, cbSchaltWaehlhebel As Int, cbZuendschloss As Int, cbMotorAnlassen As Int, _
			cbAnfahrAnhalte As Int, cbSchaltuebg As Int, cbHoch1_2 As Int, cbHoch2_3 As Int, cbHoch3_4 As Int, cbRunter4_3 As Int, cbRunter3_2 As Int, cbRunter2_1 As Int, cbRunter4_2 As Int, cbRunter4_1 As Int, cbRunter3_1 As Int, cbLenkuebung As Int, cbUmkehren As Int, cbEinparkenLaengs As Int, _
			cbLVorwaertsRechts As Int, cbLRueckwaertsLinks As Int, cbLRueckwaertsRechts As Int, cbLVorwaertsLinks As Int, cbRueckwaertsfahren As Int, cbEinparkenQuer As Int, cbQVorwaertsRechts As Int, cbQRueckwaertsLinks As Int, _
			cbQRueckwaertsRechts As Int, cbQVorwaertsLinks As Int, cbGefahrbremsung As Int, cbRollenSchalten As Int, cbBremsSchalten As Int, cbBremsuebung As Int, cbDegressiv As Int, cbZielbremsung As Int, cbGefahrsituation As Int, cbGefaelle As Int, _
			cbAnhalten As Int, cbAnfahren As Int, cbRueckwaerts As Int, cbSichern As Int, cbSchalten As Int, cbSteigung As Int, cbStAnhalten As Int, cbStAnfahren As Int, cbStRueckwaerts As Int, cbStSichern As Int, cbStSchalten As Int, cbTastgeschw As Int, cbBedienKontroll As Int, _
			cbOertlichBesonder As Int, cbFahrbahnbenutzung As Int, cbEinordnen As Int, cbMarkierungen As Int, cbFahrstreifenwechsel As Int, cbLinks As Int, cbRechts As Int, cbVorbeifUeberholen As Int, cbAbbiegen As Int, cbABRechts As Int, cbABLinks As Int, cbMehrspurig As Int, _
			cbRadweg As Int, cbSonderstreifen As Int, cbStrassenbahn As Int, cbEinbahnstrasse As Int, cbVorfahrt As Int, cbRechtsVorLinks As Int, cbGruenpfeil As Int, cbPolizeibeamte As Int, cbGruenpfeilSchild As Int, cbGeschwAbstand As Int, cbSituationVerkehrstn As Int, _
			cbFussgaengerueberweg As Int, cbOeffentlVerkehrsm As Int, cbAeltereBehinderte As Int, cbEinbahnstrRadfahrer As Int, cbKinder As Int, cbSchulbus As Int, cbRadfahrerMofa As Int, cbVerkehrsberuhigt As Int, cbSchwierigeVerkehrsf As Int, cbEngpass As Int, _
			cbKreisverkehr As Int, cbBahnuebergangWarte As Int, cbKritischeVerkehrss As Int, cbHauptverkehrszt As Int, cbPartnerVerhalten As Int, cbSchwungNutzen As Int, cbFussgaengerSchutzb As Int, cbAngepassteGeschw As Int, cbAbstand As Int, cbULVorne As Int, _
			cbULHinten As Int, cbULSeitlich As Int, cbBeobachtSpiegel As Int, cbVerkehrszeichen As Int, cbKreuzungEinmuend As Int, cbKurven As Int, cbSteigungen As Int, cbULGefaelle As Int, cbAlleen As Int, cbUeberholen As Int, cbBesondereSituat As Int, cbLiegenblSichern As Int, _
			cbEinfahrenOrtsch As Int, cbFussgaenger As Int, cbWildTiere As Int, cbBesondereAnford As Int, cbLeistungsgrenze As Int, cbOrientierung As Int, cbAblenkung As Int, cbFahrtplanung As Int, cbEinfahrtAB As Int, cbABFahrbahnwechsel As Int, cbGeschwindigkeit As Int, _
			cbABAbstand As Int, cbABVorne As Int, cbABHinten As Int, cbABSeitlich As Int, cbABUeberholen As Int, cbSchilder As Int, cbVorbeifahren As Int, cbRastParkTank As Int, cbVerhUnfall As Int, cbDichterVerkehr As Int, cbBesonderSituat As Int, cbBesonderAnford As Int, _
			cbABLeistungsgrenze As Int, cbKonfliktSitua As Int, cbABAblenkung As Int, cbBeleuchtung As Int, cbKontrolle As Int, cbEinstell As Int, cbBenutzung As Int, cbFernlicht As Int, cbVerlassenBAB As Int, cbBeleuchtStrasse As Int, cbUnbeleuchtStrasse As Int, cbParken As Int, cbDUBesonderSituat As Int, _
			cbSchlechteWitterung As Int, cbTiere As Int, cbBahnuebergaenge As Int, cbUnbelVerkehrTN As Int, cbDUBesonderAnfor As Int, cbBlendung As Int, cbDUOrientierung As Int, cbAbschlussbesp As Int, cbSelbstFahren As Int, cbInnerorts As Int, cbAusserorts As Int, cbVerantwFahren As Int, cbTestfPruef As Int, _
			cbFAKT As Int, cbAndere As Int, cbWiederholung As Int, cbLeistungsbew As Int, cbReifen As Int, cbEinAusschalten As Int, cbFunktionPruefen As Int, cbStandlicht As Int, cbNebelschluss As Int, cbBlinker As Int, cbAbblendlicht As Int, cbWarnblicke As Int, cbHupe As Int, cbBSFernlicht As Int, cbSchlussLeuchte As Int, _
			cbBremslicht As Int, cbKontrollLBenenn As Int, cbRueckstrahler As Int, cbVorhandensein As Int, cbBeschaedigung As Int, cbLenkung As Int, cbLenkschlEntriegeln As Int, cbPruefLenkSpiel As Int, cbFunktBremse As Int, cbBetriebsBremse As Int, cbFeststellBremse As Int, _
			cbAnlegenGurt As Int, cbRichtigSitz As Int, cbEinstellRueckspiegel As Int, cbEinKopfstuetze As Int, cbEinLenkrad As Int, cbBedienenAgg As Int, cbHeizung As Int, cbHeckHeizung As Int, cbBehSonderaus As Int, cbLueftung As Int, cbKlimaanlage As Int, cbEnergieNutzung As Int, _
			cbKeineUnnVerbr As Int, cbRechtztAbsch As Int, cbMotorraum As Int, cbMotoroel As Int, cbKuehlmittel As Int, cbScheibenwaschm As Int, cbTanken As Int, cbBremsen As Int, cbSicherungsmittel As Int, cbWarndreieck As Int, cbBordwerkzeug As Int, cbZusaetzlichAus As Int, cbVerbandskasten As Int, cbAussenkontrolle As Int, _
			cbScheibenWischer As Int, cbKennzeichen As Int, cbCheckSpiegel As Int, cbCheckBeleuchtung As Int, cbLadung As Int, cbLadungssicherung As Int, cbKenntlichmachung As Int, cbFahreSchlWitt As Int, cbWittLueftung As Int, cbWittScheiben As Int, cbRegen As Int, _
			cbWasserlachen As Int, cbWindSturm As Int, cbMatchSchnee As Int, cbEis As Int, cbWittBeleuchtung As Int, etNotizen As String, iAusbKontrolleOID As Int) As Boolean
	Dim sUpdate As String
	Dim bResult As Boolean
		
	sUpdate = "UPDATE AusbKontrolle " & _
							"SET 	SchuelerID = '" & Main.iAusgewaehlterSchuelrID & "', " & _
									"MatchCode = '" & sSchueler & "', " & _
									"Datum = '" & sDate & "', " & _
									"BesonderhEinsteigen = " & cbBesonderhEinsteigen & ", " & _
									"Einstellen = " & cbEinstellen & ", " & _
									"Lenkrad = " & cbLenkrad & ", " & _
									"Spiegel = " & cbSpiegel & ", " & _
									"Kopfstuetze = " & cbKopfstuetze & ", " & _
									"Sitz = " & cbSitz & ", " & _
									"Lenkradhalt = " & cbLenkradhaltung & ", " & _
									"Pedale = " & cbPedale & ", " & _
									"Gurt = " & cbGurt & ", " & _
									"SchaltWaehlhebel = " & cbSchaltWaehlhebel & ", " & _
									"Zuendschloss = " & cbZuendschloss & ", " & _
									"MotorAnlassen = " & cbMotorAnlassen & ", " & _
									"AnfahrAnhalte = " & cbAnfahrAnhalte & ", " & _
									"Schaltuebg = " & cbSchaltuebg & ", " & _
									"hoch1_2 = " & cbHoch1_2 & ", " & _
									"hoch2_3 = " & cbHoch2_3 & ", " & _
									"hoch3_4 = " & cbHoch3_4 & ", " & _
									"runter4_3 = " & cbRunter4_3 & ", " & _
									"runter3_2 = " & cbRunter3_2 & ", " & _
									"runter2_1 = " & cbRunter2_1 & ", " & _
									"runter4_2 = " & cbRunter4_2 & ", " & _
									"runter4_1 = " & cbRunter4_1 & ", " & _
									"runter3_1 = " & cbRunter3_1 & ", " & _
									"Lenkuebg = " & cbLenkuebung & ", " & _
									"Umkehren = " & cbUmkehren & ", " & _
									"EinpLaengs = " & cbEinparkenLaengs & ", " & _
									"LVorwRechts = " & cbLVorwaertsRechts & ", " & _
									"LRueckwLinks = " & cbLRueckwaertsLinks & ", " & _
									"LRueckwRechts = " & cbLRueckwaertsRechts & ", " & _
									"LVorwLinks = " & cbLVorwaertsLinks & ", " & _
									"Rueckfahren = " & cbRueckwaertsfahren & ", " & _
									"EinpQuer = " & cbEinparkenQuer & ", " & _
									"QVvorwRechts = " & cbQVorwaertsRechts & ", " & _
									"QRueckwLinks = " & cbQRueckwaertsLinks & ", " & _
									"QRueckwRechts = " & cbQRueckwaertsRechts & ", " & _
									"QVorwLinks = " & cbQVorwaertsLinks & ", " & _
									"Gefahrbrems = " & cbGefahrbremsung & ", " & _
									"RollenUndSchalten = " & cbRollenSchalten & ", " & _
									"AbbrUndSchalten = " & cbBremsSchalten & ", " & _
									"Bremsuebung = " & cbBremsuebung & ", " & _
									"degressiv = " & cbDegressiv & ", " & _
									"Zielbremsung = " & cbZielbremsung & ", " & _
									"GefahrSituation = " & cbGefahrsituation & ", " & _
									"Gefaelle = " & cbGefaelle & ", " & _
									"GeAnhalten = " & cbAnhalten & ", " & _
									"GeAnfahren = " & cbAnfahren & ", " & _
									"GeRueckw = " & cbRueckwaerts & ", " & _
									"GeSichern = " & cbSichern & ", " & _
									"GeSchalten = " & cbSchalten & ", " & _
									"Steigung = " & cbSteigung & ", " & _
									"StAnhalten = " & cbStAnhalten & ", " & _
									"StAnfahren = " & cbStAnfahren & ", " & _
									"StRueckw = " & cbStRueckwaerts & ", " & _
									"StSichern = " & cbStSichern & ", " & _
									"StSchalten = " & cbStSchalten & ", " & _
									"Tastgeschw = " & cbTastgeschw & ", " & _
									"BedienungKontrolle = " & cbBedienKontroll & ", " & _
									"OertlicheBesonder = " & cbOertlichBesonder & ", " & _
									"FahrbahnNutz = " & cbFahrbahnbenutzung & ", " & _
									"Einordnen = " & cbEinordnen & ", " & _
									"Markierungen = " & cbMarkierungen & ", " & _
									"FahrstreifenWechsel = " & cbFahrstreifenwechsel & ", " & _
									"FahrLinks = " & cbLinks & ", " & _
									"FahrRechts = " & cbRechts & ", " & _
									"Vorbeifahren = " & cbVorbeifUeberholen & ", " & _
									"Abbiegen = " & cbAbbiegen & ", " & _
									"AbbRechts = " & cbABRechts & ", " & _
									"AbbLinks = " & cbABLinks & ", " & _
									"FahrMehrSp = " & cbMehrspurig & ", " & _
									"FahrRadweg = " & cbRadweg & ", " & _
									"FahrSonderStr = " & cbSonderstreifen & ", " & _
									"FahrStrassenb = " & cbStrassenbahn & ", " & _
									"FahrEinbahnstr = " & cbEinbahnstrasse & ", " & _
									"Vorfahrt = " & cbVorfahrt & ", " & _
									"RechtsVorLinks = " & cbRechtsVorLinks & ", " & _
									"Gruenpfeil = " & cbGruenpfeil & ", " & _
									"PolizeiBeamte = " & cbPolizeibeamte & ", " & _
									"GruenpfeilSchild = " & cbGruenpfeilSchild & ", " & _
									"GeschwAbstand = " & cbGeschwAbstand & ", " & _
									"SituationTN = " & cbSituationVerkehrstn & ", " & _
									"Fussgaenger = " & cbFussgaengerueberweg & ", " & _
									"OeffentVKTN = " & cbOeffentlVerkehrsm & ", " & _
									"AeltereBehind = " & cbAeltereBehinderte & ", " & _
									"EinbahnStrRadf = " & cbEinbahnstrRadfahrer & ", " & _
									"Kinder = " & cbKinder & ", " & _
									"Schulbus = " & cbSchulbus & ", " & _
									"RadfMofa = " & cbRadfahrerMofa & ", " & _
									"VerkBeruhigt = " & cbVerkehrsberuhigt & ", " & _
									"SchwierigeVKFuehrung = " & cbSchwierigeVerkehrsf & ", " & _
									"Engpass = " & cbEngpass & ", " & _
									"Kreisverkehr = " & cbKreisverkehr & ", " & _
									"Bahnuebergang = " & cbBahnuebergangWarte & ", " & _
									"KritischeVKSit = " & cbKritischeVerkehrss & ", " & _
									"HauptVKZeit = " & cbHauptverkehrszt & ", " & _
									"PartnerVerhalten = " & cbPartnerVerhalten & ", " & _
									"SchwungNutzen = " & cbSchwungNutzen & ", " & _
									"FussgaengerSchutz = " & cbFussgaengerSchutzb & ", " & _
									"AngepGeschw = " & cbAngepassteGeschw & ", " & _
									"Abstand = " & cbAbstand & ", " & _
									"AbstVorne = " & cbULVorne & ", " & _
									"AbstHinten = " & cbULHinten & ", " & _
									"AbstSeite = " & cbULSeitlich & ", " & _
									"BeobSpiegel = " & cbBeobachtSpiegel & ", " & _
									"VerkehrsZeich = " & cbVerkehrszeichen & ", " & _
									"KreuzMuend = " & cbKreuzungEinmuend & ", " & _
									"Kurven = " & cbKurven & ", " & _
									"Steigungen = " & cbSteigungen & ", " & _
									"BesGefaelle = " & cbULGefaelle & ", " & _
									"Alleen = " & cbAlleen & ", " & _
									"Ueberholen = " & cbUeberholen & ", " & _
									"BesSituation = " & cbBesondereSituat & ", " & _
									"LiegeblAbsicher = " & cbLiegenblSichern & ", " & _
									"EinfOrtschaft = " & cbEinfahrenOrtsch & ", " & _
									"BesFussgaenger = " & cbFussgaenger & ", " & _
									"WildTiere = " & cbWildTiere & ", " & _
									"BesAnforderung = " & cbBesondereAnford & ", " & _
									"Leistungsgr = " & cbLeistungsgrenze & ", " & _
									"Orientierung = " & cbOrientierung & ", " & _
									"Ablenkung = " & cbAblenkung & ", " & _
									"Fahrtplanung = " & cbFahrtplanung & ", " & _
									"EinfahBAB = " & cbEinfahrtAB & ", " & _
									"FahrStrWechsel = " & cbABFahrbahnwechsel & ", " & _
									"Geschwindig = " & cbGeschwindigkeit & ", " & _
									"AbstandBAB = " & cbABAbstand & ", " & _
									"VorneBAB = " & cbABVorne & ", " & _
									"HintenBAB = " & cbABHinten & ", " & _
									"SeiteBAB = " & cbABSeitlich & ", " & _
									"BABUeberholen = " & cbABUeberholen & ", " & _
									"SchildMarkierung = " & cbSchilder & ", " & _
									"VoebeifAnschluss = " & cbVorbeifahren & ", " & _
									"RastParkTank = " & cbRastParkTank & ", " & _
									"VerhaltenUnfall = " & cbVerhUnfall & ", " & _
									"DichtStau = " & cbDichterVerkehr & ", " & _
									"BesSitBAB = " & cbBesonderSituat & ", " & _
									"BesAnfordBAB = " & cbBesonderAnford & ", " & _
									"LeistungsgrBAB = " & cbABLeistungsgrenze & ", " & _
									"KonfliktBAB = " & cbKonfliktSitua & ", " & _
									"AblenkungBAB = " & cbABAblenkung & ", " & _
									"VerlassBAB = " & cbVerlassenBAB & ", " & _
									"Beleuchtung = " & cbBeleuchtung & ", " & _
									"KontrolleBel = " & cbKontrolle & ", " & _
									"EinstellBel = " & cbEinstell & ", " & _
									"BenutzBel = " & cbBenutzung & ", " & _
									"FernlichtBel = " & cbFernlicht & ", " & _
									"BelStrasse = " & cbBeleuchtStrasse & ", " & _
									"UnbelStrasse = " & cbUnbeleuchtStrasse & ", " & _
									"Parken = " & cbParken & ", " & _
									"BesSitDunkel = " & cbDUBesonderSituat & ", " & _
									"SchlechWittDun = " & cbSchlechteWitterung & ", " & _
									"TiereDun = " & cbTiere & ", " & _
									"BahnueberDun = " & cbBahnuebergaenge & ", " & _
									"UnbelVKTN = " & cbUnbelVerkehrTN & ", " & _
									"BesAnforDun = " & cbDUBesonderAnfor & ", " & _
									"Blendung = " & cbBlendung & ", " & _
									"OrientDun = " & cbDUOrientierung & ", " & _
									"AbschlussbespDun = " & cbAbschlussbesp & ", " & _
									"SelbstFahren = " & cbSelbstFahren & ", " & _
									"Innerorts = " & cbInnerorts & ", " & _
									"Ausserorts = " & cbAusserorts & ", " & _
									"VerantwFahren = " & cbVerantwFahren & ", " & _
									"TestfPruefung = " & cbTestfPruef & ", " & _
									"FAKT = " & cbFAKT & ", " & _
									"andere = " & cbAndere & ", " & _
									"WiederhVertief = " & cbWiederholung & ", " & _
									"Leistungsbew = " & cbLeistungsbew & ", " & _
									"Reifen = " & cbReifen & ", " & _
									"EinAusschalten = " & cbEinAusschalten & ", " & _
									"FunkPruefen = " & cbFunktionPruefen & ", " & _
									"Standlicht = " & cbStandlicht & ", " & _
									"Nebelschl = " & cbNebelschluss & ", " & _
									"Blinker = " & cbBlinker & ", " & _
									"Abblendlicht = " & cbAbblendlicht & ", " & _
									"Warnblick = " & cbWarnblicke & ", " & _
									"Hupe = " & cbHupe & ", " & _
									"Fernlicht = " & cbBSFernlicht & ", " & _
									"SchlussLeuchte = " & cbSchlussLeuchte & ", " & _
									"Bremslicht = " & cbBremslicht & ", " & _
									"KontrollLeuchten = " & cbKontrollLBenenn & ", " & _
									"Rueckstrahler = " & cbRueckstrahler & ", " & _
									"Vorhanden = " & cbVorhandensein & ", " & _
									"Beschaedigt = " & cbBeschaedigung & ", " & _
									"Lenkung = " & cbLenkung & ", " & _
									"LenkschlEntriegel = " & cbLenkschlEntriegeln & ", " & _
									"Lenkspiel = " & cbPruefLenkSpiel & ", " & _
									"FunkBremsen = " & cbFunktBremse & ", " & _
									"BetriebBremse = " & cbBetriebsBremse & ", " & _
									"FeststellBremse = " & cbFeststellBremse & ", " & _
									"Sitzeinstellung = " & cbRichtigSitz & ", " & _
									"EinstRueckspiegel = " & cbEinstellRueckspiegel & ", " & _
									"EinstKopf = " & cbEinKopfstuetze & ", " & _
									"EinstLenksrad = " & cbEinLenkrad & ", " & _
									"AnlegenGurt = " & cbAnlegenGurt & ", " & _
									"BedienHeizung = " & cbBedienenAgg & ", " & _
									"Heizung = " & cbHeizung & ", " & _
									"HeckHeizung = " & cbHeckHeizung & ", " & _
									"BehSonderAusstg = " & cbBehSonderaus & ", " & _
									"Lueftung = " & cbLueftung & ", " & _
									"Klima = " & cbKlimaanlage & ", " & _
									"EnergiespNutz = " & cbEnergieNutzung & ", " & _
									"KeineUnnVerbr = " & cbKeineUnnVerbr & ", " & _
									"RechtzAbschalten = " & cbRechtztAbsch & ", " & _
									"CheckMotorraum = " & cbMotorraum & ", " & _
									"Motoroel = " & cbMotoroel & ", " & _
									"Kuehlmittel = " & cbKuehlmittel & ", " & _
									"Wischwasser = " & cbScheibenwaschm & ", " & _
									"Tanken = " & cbTanken & ", " & _
									"SicherungsMittel = " & cbSicherungsmittel & ", " & _
									"WarnDreieck = " & cbWarndreieck & ", " & _
									"Bordwerkzeug = " & cbBordwerkzeug & ", " & _
									"zusaetzAusruest = " & cbZusaetzlichAus & ", " & _
									"Verbandskasten = " & cbVerbandskasten & ", " & _
									"AussenKontrolle = " & cbAussenkontrolle & ", " & _
									"Scheiben = " & cbScheibenWischer & ", " & _
									"KennzeichenHUAU = " & cbKennzeichen & ", " & _
									"CheckSpiegel = " & cbCheckSpiegel & ", " & _
									"CheckBeleuchtung = " & cbCheckBeleuchtung & ", " & _
									"Bremsung = " & cbBremsen & ", " & _
									"Ladung = " & cbLadung & ", " & _
									"Sicherung = " & cbLadungssicherung & ", " & _
									"KenntlichMachung = " & cbKenntlichmachung & ", " & _
									"FahrenSchelchtWetter = " & cbFahreSchlWitt & ", " & _
									"WittLueftung = " & cbWittLueftung & ", " & _
									"ScheibenWischer = " & cbWittScheiben & ", " & _
									"RegenSprueh = " & cbRegen & ", " & _
									"Wasserlachen = " & cbWasserlachen & ", " & _
									"WindSturm = " & cbWindSturm & ", " & _
									"SchneeMatsch = " & cbMatchSchnee & ", " & _
									"Eis = " & cbEis & ", " & _
									"WittBeleucht = " & cbWittBeleuchtung & ", " & _
									"Kommentar = '" & etNotizen & "' " & _
							"WHERE OID = " & iAusbKontrolleOID

	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If

	'Main.SQL1.BeginTransaction
	Try
		Main.SQL1.ExecNonQuery(sUpdate)
		'Main.SQL1.TransactionSuccessful
		bResult = True
	Catch
		Log("Fehler beim speichern der Zahlungen " & LastException.Message) 'no changes will be made
		bResult = False
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	Return bResult
End Sub

Sub SetBVFFelder(cbBesonderhEinsteigen As CheckBox, cbEinstellen As CheckBox, cbLenkrad As CheckBox, cbSpiegel As CheckBox, cbKopfstuetze As CheckBox, cbSitz As CheckBox, cbLenkradhaltung As CheckBox, cbPedale As CheckBox, cbGurt As CheckBox, cbSchaltWaehlhebel As CheckBox, cbZuendschloss As CheckBox, cbMotorAnlassen As CheckBox, _
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
			cbWasserlachen As CheckBox, cbWindSturm As CheckBox, cbMatchSchnee As CheckBox, cbEis As CheckBox, cbWittBeleuchtung As CheckBox, etNotizen As EditText, iBVFOID As Int)
			
	Dim sSelect As String
	Dim cur As Cursor
	
	sSelect = "SELECT * FROM AusbKontrolle " & _
					"WHERE OID = " & iBVFOID
			
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If
	
	'Main.SQL1.BeginTransaction
	Try
		cur = Main.SQL1.ExecQuery(sSelect)
		
		For i = 0 To cur.RowCount - 1
			cur.Position = i
			
			cbBesonderhEinsteigen.Checked = CBool(cur.GetInt("BesonderhEinsteigen"))
			cbEinstellen.Checked =  CBool(cur.GetInt("Einstellen"))
			cbLenkrad.Checked =  CBool(cur.GetInt("Lenkrad"))
			cbSpiegel.Checked =  CBool(cur.GetInt("Spiegel"))
			cbKopfstuetze.Checked =  CBool(cur.GetInt("Kopfstuetze"))
			cbSitz.Checked =  CBool(cur.GetInt("Sitz"))
			cbLenkradhaltung.Checked =  CBool(cur.GetInt("Lenkradhalt"))
			cbPedale.Checked =  CBool(cur.GetInt("Pedale"))
			cbGurt.Checked =  CBool(cur.GetInt("Gurt"))
			cbSchaltWaehlhebel.Checked =  CBool(cur.GetInt("SchaltWaehlhebel"))
			cbZuendschloss.Checked =  CBool(cur.GetInt("Zuendschloss"))
			cbMotorAnlassen.Checked =  CBool(cur.GetInt("MotorAnlassen"))
			cbAnfahrAnhalte.Checked =  CBool(cur.GetInt("AnfahrAnhalte"))
			cbSchaltuebg.Checked =  CBool(cur.GetInt("Schaltuebg"))
			cbHoch1_2.Checked =  CBool(cur.GetInt("hoch1_2"))
			cbHoch2_3.Checked =  CBool(cur.GetInt("hoch2_3"))
			cbHoch3_4.Checked =  CBool(cur.GetInt("hoch3_4"))
			cbRunter4_3.Checked =  CBool(cur.GetInt("runter4_3"))
			cbRunter3_2.Checked =  CBool(cur.GetInt("runter3_2"))
			cbRunter2_1.Checked =  CBool(cur.GetInt("runter2_1"))
			cbRunter4_2.Checked =  CBool(cur.GetInt("runter4_2"))
			cbRunter4_1.Checked =  CBool(cur.GetInt("runter4_1"))
			cbRunter3_1.Checked =  CBool(cur.GetInt("runter3_1"))
			cbLenkuebung.Checked =  CBool(cur.GetInt("Lenkuebg"))
			cbUmkehren.Checked =  CBool(cur.GetInt("Umkehren"))
			cbEinparkenLaengs.Checked =  CBool(cur.GetInt("EinpLaengs"))
			cbLVorwaertsRechts.Checked =  CBool(cur.GetInt("LVorwRechts"))
			cbLRueckwaertsLinks.Checked =  CBool(cur.GetInt("LRueckwLinks"))
			cbLRueckwaertsRechts.Checked =  CBool(cur.GetInt("LRueckwRechts"))
			cbLVorwaertsLinks.Checked =  CBool(cur.GetInt("LVorwLinks"))
			cbRueckwaertsfahren.Checked =  CBool(cur.GetInt("Rueckfahren"))
			cbEinparkenQuer.Checked =  CBool(cur.GetInt("EinpQuer"))
			cbQVorwaertsRechts.Checked =  CBool(cur.GetInt("QVvorwRechts"))
			cbQRueckwaertsLinks.Checked =  CBool(cur.GetInt("QRueckwLinks"))
			cbQRueckwaertsRechts.Checked =  CBool(cur.GetInt("QRueckwRechts"))
			cbQVorwaertsLinks.Checked =  CBool(cur.GetInt("QVorwLinks"))
			cbGefahrbremsung.Checked =  CBool(cur.GetInt("Gefahrbrems"))
			cbRollenSchalten.Checked =  CBool(cur.GetInt("RollenUndSchalten"))
			cbBremsSchalten.Checked =  CBool(cur.GetInt("AbbrUndSchalten"))
			cbBremsuebung.Checked =  CBool(cur.GetInt("Bremsuebung"))
			cbDegressiv.Checked =  CBool(cur.GetInt("degressiv"))
			cbZielbremsung.Checked =  CBool(cur.GetInt("Zielbremsung"))
			cbGefahrsituation.Checked =  CBool(cur.GetInt("GefahrSituation"))
			cbGefaelle.Checked =  CBool(cur.GetInt("Gefaelle"))
			cbAnhalten.Checked =  CBool(cur.GetInt("GeAnhalten"))
			cbAnfahren.Checked =  CBool(cur.GetInt("GeAnfahren"))
			cbRueckwaerts.Checked =  CBool(cur.GetInt("GeRueckw"))
			cbSichern.Checked =  CBool(cur.GetInt("GeSichern"))
			cbSchalten.Checked =  CBool(cur.GetInt("GeSchalten"))
			cbSteigung.Checked =  CBool(cur.GetInt("Steigung"))
			cbStAnhalten.Checked =  CBool(cur.GetInt("StAnhalten"))
			cbStAnfahren.Checked =  CBool(cur.GetInt("StAnfahren"))
			cbStRueckwaerts.Checked =  CBool(cur.GetInt("StRueckw"))
			cbStSichern.Checked =  CBool(cur.GetInt("StSichern"))
			cbStSchalten.Checked =  CBool(cur.GetInt("StSchalten"))
			cbTastgeschw.Checked =  CBool(cur.GetInt("Tastgeschw"))
			cbBedienKontroll.Checked =  CBool(cur.GetInt("BedienungKontrolle"))
			cbOertlichBesonder.Checked =  CBool(cur.GetInt("OertlicheBesonder"))
			cbFahrbahnbenutzung.Checked =  CBool(cur.GetInt("FahrbahnNutz"))
			cbEinordnen.Checked =  CBool(cur.GetInt("Einordnen"))
			cbMarkierungen.Checked =  CBool(cur.GetInt("Markierungen"))
			cbFahrstreifenwechsel.Checked =  CBool(cur.GetInt("FahrstreifenWechsel"))
			cbLinks.Checked =  CBool(cur.GetInt("FahrLinks"))
			cbRechts.Checked =  CBool(cur.GetInt("FahrRechts"))
			cbVorbeifUeberholen.Checked =  CBool(cur.GetInt("Vorbeifahren"))
			cbAbbiegen.Checked =  CBool(cur.GetInt("Abbiegen"))
			cbABRechts.Checked =  CBool(cur.GetInt("AbbRechts"))
			cbABLinks.Checked =  CBool(cur.GetInt("AbbLinks"))
			cbMehrspurig.Checked =  CBool(cur.GetInt("FahrMehrSp"))
			cbRadweg.Checked =  CBool(cur.GetInt("FahrRadweg"))
			cbSonderstreifen.Checked =  CBool(cur.GetInt("FahrSonderStr"))
			cbStrassenbahn.Checked =  CBool(cur.GetInt("FahrStrassenb"))
			cbEinbahnstrasse.Checked =  CBool(cur.GetInt("FahrEinbahnstr"))
			cbVorfahrt.Checked =  CBool(cur.GetInt("Vorfahrt"))
			cbRechtsVorLinks.Checked =  CBool(cur.GetInt("RechtsVorLinks"))
			cbGruenpfeil.Checked =  CBool(cur.GetInt("Gruenpfeil"))
			cbPolizeibeamte.Checked =  CBool(cur.GetInt("PolizeiBeamte"))
			cbGruenpfeilSchild.Checked =  CBool(cur.GetInt("GruenpfeilSchild"))
			cbGeschwAbstand.Checked =  CBool(cur.GetInt("GeschwAbstand"))
			cbSituationVerkehrstn.Checked =  CBool(cur.GetInt("SituationTN"))
			cbFussgaengerueberweg.Checked =  CBool(cur.GetInt("Fussgaenger"))
			cbOeffentlVerkehrsm.Checked =  CBool(cur.GetInt("OeffentVKTN"))
			cbAeltereBehinderte.Checked =  CBool(cur.GetInt("AeltereBehind"))
			cbEinbahnstrRadfahrer.Checked =  CBool(cur.GetInt("EinbahnStrRadf"))
			cbKinder.Checked =  CBool(cur.GetInt("Kinder"))
			cbSchulbus.Checked =  CBool(cur.GetInt("Schulbus"))
			cbRadfahrerMofa.Checked =  CBool(cur.GetInt("RadfMofa"))
			cbVerkehrsberuhigt.Checked =  CBool(cur.GetInt("VerkBeruhigt"))
			cbSchwierigeVerkehrsf.Checked =  CBool(cur.GetInt("SchwierigeVKFuehrung"))
			cbEngpass.Checked =  CBool(cur.GetInt("Engpass"))
			cbKreisverkehr.Checked =  CBool(cur.GetInt("Kreisverkehr"))
			cbBahnuebergangWarte.Checked =  CBool(cur.GetInt("Bahnuebergang"))
			cbKritischeVerkehrss.Checked =  CBool(cur.GetInt("KritischeVKSit"))
			cbHauptverkehrszt.Checked =  CBool(cur.GetInt("HauptVKZeit"))
			cbPartnerVerhalten.Checked =  CBool(cur.GetInt("PartnerVerhalten"))
			cbSchwungNutzen.Checked =  CBool(cur.GetInt("SchwungNutzen"))
			cbFussgaengerSchutzb.Checked =  CBool(cur.GetInt("FussgaengerSchutz"))
			cbAngepassteGeschw.Checked =  CBool(cur.GetInt("AngepGeschw"))
			cbAbstand.Checked =  CBool(cur.GetInt("Abstand"))
			cbULVorne.Checked =  CBool(cur.GetInt("AbstVorne"))
			cbULHinten.Checked =  CBool(cur.GetInt("AbstHinten"))
			cbULSeitlich.Checked =  CBool(cur.GetInt("AbstSeite"))
			cbBeobachtSpiegel.Checked =  CBool(cur.GetInt("BeobSpiegel"))
			cbVerkehrszeichen.Checked =  CBool(cur.GetInt("VerkehrsZeich"))
			cbKreuzungEinmuend.Checked =  CBool(cur.GetInt("KreuzMuend"))
			cbKurven.Checked =  CBool(cur.GetInt("Kurven"))
			cbSteigungen.Checked =  CBool(cur.GetInt("Steigungen"))
			cbULGefaelle.Checked =  CBool(cur.GetInt("BesGefaelle"))
			cbAlleen.Checked =  CBool(cur.GetInt("Alleen"))
			cbUeberholen.Checked =  CBool(cur.GetInt("Ueberholen"))
			cbBesondereSituat.Checked =  CBool(cur.GetInt("BesSituation"))
			cbLiegenblSichern.Checked =  CBool(cur.GetInt("LiegeblAbsicher"))
			cbEinfahrenOrtsch.Checked =  CBool(cur.GetInt("EinfOrtschaft"))
			cbFussgaenger.Checked =  CBool(cur.GetInt("BesFussgaenger"))
			cbWildTiere.Checked =  CBool(cur.GetInt("WildTiere"))
			cbBesondereAnford.Checked =  CBool(cur.GetInt("BesAnforderung"))
			cbLeistungsgrenze.Checked =  CBool(cur.GetInt("Leistungsgr"))
			cbOrientierung.Checked =  CBool(cur.GetInt("Orientierung"))
			cbAblenkung.Checked =  CBool(cur.GetInt("Ablenkung"))
			cbFahrtplanung.Checked =  CBool(cur.GetInt("Fahrtplanung"))
			cbEinfahrtAB.Checked =  CBool(cur.GetInt("EinfahBAB"))
			cbABFahrbahnwechsel.Checked =  CBool(cur.GetInt("FahrStrWechsel"))
			cbGeschwindigkeit.Checked =  CBool(cur.GetInt("Geschwindig"))
			cbABAbstand.Checked =  CBool(cur.GetInt("AbstandBAB"))
			cbABVorne.Checked =  CBool(cur.GetInt("VorneBAB"))
			cbABHinten.Checked =  CBool(cur.GetInt("HintenBAB"))
			cbABSeitlich.Checked =  CBool(cur.GetInt("SeiteBAB"))
			cbABUeberholen.Checked =  CBool(cur.GetInt("BABUeberholen"))
			cbSchilder.Checked =  CBool(cur.GetInt("SchildMarkierung"))
			cbVorbeifahren.Checked =  CBool(cur.GetInt("VoebeifAnschluss"))
			cbRastParkTank.Checked =  CBool(cur.GetInt("RastParkTank"))
			cbVerhUnfall.Checked =  CBool(cur.GetInt("VerhaltenUnfall"))
			cbDichterVerkehr.Checked =  CBool(cur.GetInt("DichtStau"))
			cbBesonderSituat.Checked =  CBool(cur.GetInt("BesSitBAB"))
			cbBesonderAnford.Checked =  CBool(cur.GetInt("BesAnfordBAB"))
			cbABLeistungsgrenze.Checked =  CBool(cur.GetInt("LeistungsgrBAB"))
			cbKonfliktSitua.Checked =  CBool(cur.GetInt("KonfliktBAB"))
			cbABAblenkung.Checked =  CBool(cur.GetInt("AblenkungBAB"))
			cbVerlassenBAB.Checked =  CBool(cur.GetInt("VerlassBAB"))
			cbBeleuchtung.Checked =  CBool(cur.GetInt("Beleuchtung"))
			cbKontrolle.Checked =  CBool(cur.GetInt("KontrolleBel"))
			cbEinstell.Checked =  CBool(cur.GetInt("EinstellBel"))
			cbBenutzung.Checked =  CBool(cur.GetInt("BenutzBel"))
			cbFernlicht.Checked =  CBool(cur.GetInt("FernlichtBel"))
			cbBeleuchtStrasse.Checked =  CBool(cur.GetInt("BelStrasse"))
			cbUnbeleuchtStrasse.Checked =  CBool(cur.GetInt("UnbelStrasse"))
			cbParken.Checked =  CBool(cur.GetInt("Parken"))
			cbDUBesonderSituat.Checked =  CBool(cur.GetInt("BesSitDunkel"))
			cbSchlechteWitterung.Checked =  CBool(cur.GetInt("SchlechWittDun"))
			cbTiere.Checked =  CBool(cur.GetInt("TiereDun"))
			cbBahnuebergaenge.Checked =  CBool(cur.GetInt("BahnueberDun"))
			cbUnbelVerkehrTN.Checked =  CBool(cur.GetInt("UnbelVKTN"))
			cbDUBesonderAnfor.Checked =  CBool(cur.GetInt("BesAnforDun"))
			cbBlendung.Checked =  CBool(cur.GetInt("Blendung"))
			cbDUOrientierung.Checked =  CBool(cur.GetInt("OrientDun"))
			cbAbschlussbesp.Checked =  CBool(cur.GetInt("AbschlussbespDun"))
			cbSelbstFahren.Checked =  CBool(cur.GetInt("SelbstFahren"))
			cbInnerorts.Checked =  CBool(cur.GetInt("Innerorts"))
			cbAusserorts.Checked =  CBool(cur.GetInt("Ausserorts"))
			cbVerantwFahren.Checked =  CBool(cur.GetInt("VerantwFahren"))
			cbTestfPruef.Checked =  CBool(cur.GetInt("TestfPruefung"))
			cbFAKT.Checked =  CBool(cur.GetInt("FAKT"))
			cbAndere.Checked =  CBool(cur.GetInt("andere"))
			cbWiederholung.Checked =  CBool(cur.GetInt("WiederhVertief"))
			cbLeistungsbew.Checked =  CBool(cur.GetInt("Leistungsbew"))
			cbReifen.Checked =  CBool(cur.GetInt("Reifen"))
			cbEinAusschalten.Checked =  CBool(cur.GetInt("EinAusschalten"))
			cbFunktionPruefen.Checked =  CBool(cur.GetInt("FunkPruefen"))
			cbStandlicht.Checked =  CBool(cur.GetInt("Standlicht"))
			cbNebelschluss.Checked =  CBool(cur.GetInt("Nebelschl"))
			cbBlinker.Checked =  CBool(cur.GetInt("Blinker"))
			cbAbblendlicht.Checked =  CBool(cur.GetInt("Abblendlicht"))
			cbWarnblicke.Checked =  CBool(cur.GetInt("Warnblick"))
			cbHupe.Checked =  CBool(cur.GetInt("Hupe"))
			cbBSFernlicht.Checked =  CBool(cur.GetInt("Fernlicht"))
			cbSchlussLeuchte.Checked =  CBool(cur.GetInt("SchlussLeuchte"))
			cbBremslicht.Checked =  CBool(cur.GetInt("Bremslicht"))
			cbKontrollLBenenn.Checked =  CBool(cur.GetInt("KontrollLeuchten"))
			cbRueckstrahler.Checked =  CBool(cur.GetInt("Rueckstrahler"))
			cbVorhandensein.Checked =  CBool(cur.GetInt("Vorhanden"))
			cbBeschaedigung.Checked =  CBool(cur.GetInt("Beschaedigt"))
			cbLenkung.Checked =  CBool(cur.GetInt("Lenkung"))
			cbLenkschlEntriegeln.Checked =  CBool(cur.GetInt("LenkschlEntriegel"))
			cbPruefLenkSpiel.Checked =  CBool(cur.GetInt("Lenkspiel"))
			cbFunktBremse.Checked =  CBool(cur.GetInt("FunkBremsen"))
			cbBetriebsBremse.Checked =  CBool(cur.GetInt("BetriebBremse"))
			cbFeststellBremse.Checked =  CBool(cur.GetInt("FeststellBremse"))
			cbRichtigSitz.Checked =  CBool(cur.GetInt("Sitzeinstellung"))
			cbEinstellRueckspiegel.Checked =  CBool(cur.GetInt("EinstRueckspiegel"))
			cbEinKopfstuetze.Checked =  CBool(cur.GetInt("EinstKopf"))
			cbEinLenkrad.Checked =  CBool(cur.GetInt("EinstLenksrad"))
			cbAnlegenGurt.Checked =  CBool(cur.GetInt("AnlegenGurt"))
			cbBedienenAgg.Checked =  CBool(cur.GetInt("BedienHeizung"))
			cbHeizung.Checked =  CBool(cur.GetInt("Heizung"))
			cbHeckHeizung.Checked =  CBool(cur.GetInt("HeckHeizung"))
			cbBehSonderaus.Checked =  CBool(cur.GetInt("BehSonderAusstg"))
			cbLueftung.Checked =  CBool(cur.GetInt("Lueftung"))
			cbKlimaanlage.Checked =  CBool(cur.GetInt("Klima"))
			cbEnergieNutzung.Checked =  CBool(cur.GetInt("EnergiespNutz"))
			cbKeineUnnVerbr.Checked =  CBool(cur.GetInt("KeineUnnVerbr"))
			cbRechtztAbsch.Checked =  CBool(cur.GetInt("RechtzAbschalten"))
			cbMotorraum.Checked =  CBool(cur.GetInt("CheckMotorraum"))
			cbMotoroel.Checked =  CBool(cur.GetInt("Motoroel"))
			cbKuehlmittel.Checked =  CBool(cur.GetInt("Kuehlmittel"))
			cbScheibenwaschm.Checked =  CBool(cur.GetInt("Wischwasser"))
			cbTanken.Checked =  CBool(cur.GetInt("Tanken"))
			cbSicherungsmittel.Checked =  CBool(cur.GetInt("SicherungsMittel"))
			cbWarndreieck.Checked =  CBool(cur.GetInt("WarnDreieck"))
			cbBordwerkzeug.Checked =  CBool(cur.GetInt("Bordwerkzeug"))
			cbZusaetzlichAus.Checked =  CBool(cur.GetInt("zusaetzAusruest"))
			cbVerbandskasten.Checked =  CBool(cur.GetInt("Verbandskasten"))
			cbAussenkontrolle.Checked =  CBool(cur.GetInt("AussenKontrolle"))
			cbScheibenWischer.Checked =  CBool(cur.GetInt("Scheiben"))
			cbKennzeichen.Checked =  CBool(cur.GetInt("KennzeichenHUAU"))
			cbCheckSpiegel.Checked =  CBool(cur.GetInt("CheckSpiegel"))
			cbCheckBeleuchtung.Checked =  CBool(cur.GetInt("CheckBeleuchtung"))
			cbBremsen.Checked =  CBool(cur.GetInt("Bremsung"))
			cbLadung.Checked =  CBool(cur.GetInt("Ladung"))
			cbLadungssicherung.Checked =  CBool(cur.GetInt("Sicherung"))
			cbKenntlichmachung.Checked =  CBool(cur.GetInt("KenntlichMachung"))
			cbFahreSchlWitt.Checked =  CBool(cur.GetInt("FahrenSchelchtWetter"))
			cbWittLueftung.Checked =  CBool(cur.GetInt("WittLueftung"))
			cbWittScheiben.Checked =  CBool(cur.GetInt("ScheibenWischer"))
			cbRegen.Checked =  CBool(cur.GetInt("RegenSprueh"))
			cbWasserlachen.Checked =  CBool(cur.GetInt("Wasserlachen"))
			cbWindSturm.Checked =  CBool(cur.GetInt("WindSturm"))
			cbMatchSchnee.Checked =  CBool(cur.GetInt("SchneeMatsch"))
			cbEis.Checked =  CBool(cur.GetInt("Eis"))
			cbWittBeleuchtung.Checked =  CBool(cur.GetInt("WittBeleucht"))
			etNotizen.Text = cur.GetString("Kommentar")


		Next
		'Main.SQL1.TransactionSuccessful
	Catch
		Log("FEHLER beim Lesen/Füllen der BVF Felder")
	End Try
	'Main.SQL1.EndTransaction
	cur.Close
	Main.SQL1.Close
End Sub

Sub CBool(o As Object) As Boolean
	If o = 1 Then
		Return True
	Else
		Return False
	End If
End Sub
#End Region

#Region für Verschlüsselung
'Fahrtbezeichnungs ID anhand des Kürzels ermitteln und zurückgeben
Sub FahrtbezeichnungsID(sFahrtBez As String) As Int
	Dim sSelect As String
	Dim iResult As Int
	
	sSelect = "SELECT OID " & _
					"FROM Fahrtbezeichnung " & _
				"WHERE Kuerzel LIKE '" & sFahrtBez & "'"
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
		
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'Klassen ID ermitteln und zurückgeben
Sub KlasseID(sKlasse As String) As Int
	Dim sSelect As String
	Dim iResult As Int
	
	sSelect = "SELECT OID " & _
					"FROM Klassen " & _
				"WHERE Bezeichnung LIKE '" & sKlasse & "'" & _
				"AND Enable = 1"
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
		
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'Schueler_ID ermitteln und zurückgeben aus Termindaten
Sub GetSchuelerID(iTerminID As Int) As Int
	Dim iResult As Int
	Dim sSelect As String
	
	sSelect = "SELECT Schueler_ID " & _
					"FROM Termine " & _
				"WHERE OID LIKE '" & iTerminID & "' "
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

Sub GetSchuelerIDMatchcode(sAusgewaehlterSchueler As String) As Int
	Dim iResult As Int
	Dim sSelect As String

	sSelect = "SELECT SchuelerID " & _
					"FROM Schueler " & _
				"WHERE MatchCode LIKE '" & sAusgewaehlterSchueler & "' "
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'FahrlehrerID ermitteln und zurückgeben
Sub GetFahrlehrerID(sFahrlehrer As String) As Int
	Dim iResult As Int
	Dim sSelect As String
	
	sSelect = "SELECT FahrlehrerNr " & _
					"FROM Fahrlehrer " & _
				"WHERE Name LIKE '" & sFahrlehrer & "' "
		
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	'Main.SQL1.BeginTransaction
	Try
		iResult = Main.SQL1.ExecQuerySingleResult(sSelect)
		'Main.SQL1.TransactionSuccessful
	Catch
		iResult = 0
	End Try
	'Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return iResult
End Sub

'verschüsselte Signature speichern
Sub SaveSignature(sVerschluesselung As String, iTerminID As Int, iSchuelerID As Int) As Boolean
	Dim bReturn As Boolean
	Dim sUpdate, sSelect, sInsert As String
	Dim iAnzahl As Int

	'Hochkommas im verschlüsselten String müssen maskiert werden
	sVerschluesselung = sVerschluesselung.Replace("'", "''")
		
	'Berücksichtigung, dass wenn Unterschrift schon vorhanden ein Update und kein Insert durchgeführt wird
	'Prüfen, ob Unterschrift schon vorhanden ist
	sSelect = "SELECT COUNT(*) FROM Signature " & _
				"WHERE Termine_ID = " & iTerminID

	'Insert in Signature-Tabelle
	sInsert = "INSERT INTO Signature ( 	Termine_ID, " & _
									   "Schueler_ID, " & _
									   "Datum, " & _
									   "Unterschrift ) " & _
							"VALUES ( " & iTerminID & ", " & iSchuelerID & ", " & _
									  "'" & DateTime.Date(DateTime.Now) & "', " & _
									  "'" & sVerschluesselung & "' )"
	
	'Update in Signature-Tabelle
	sUpdate = "UPDATE Signature " & _
					"SET Datum = '" & DateTime.Date(DateTime.Now) & "', " & _
						"Unterschrift = '" & sVerschluesselung & "' " & _
				"WHERE Termine_ID = " & iTerminID
	
	If (Main.SQL1.IsInitialized = False) Then
		Main.SQL1.Initialize(Main.SourceFolder, "FaData2012.db", True)
		'Main.SQL1.ExecQuery("PRAGMA journal_mode=OFF")
	End If 
	
	''Main.SQL1.BeginTransaction
	Try
		iAnzahl = Main.SQL1.ExecQuerySingleResult(sSelect)
		
		If iAnzahl > 0 Then
			Main.SQL1.ExecNonQuery(sUpdate)
		Else
	    	Main.SQL1.ExecNonQuery(sInsert)
		End If
		
	    ''Main.SQL1.TransactionSuccessful
		bReturn = True
	Catch
	    Log(LastException.Message) 'no changes will be made
		bReturn = False
	End Try
	''Main.SQL1.EndTransaction
	Main.SQL1.Close
	
	Return bReturn
End Sub
#End Region