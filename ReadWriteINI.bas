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
	Dim sf As StringFunctions
   	
End Sub
#Region ***** Routine UNTERSTÜTZUNG Dateiverwaltung. INI *****

#Region ***** LESEN *****
' *** liest einen Wert aus einer Datei. INI ***
Sub ReadINI(directory As String, fileINI As String, session As String, key As String, default As String) As String
	
	Dim value As String ' Schlüsselwert
	Dim rSESSION As String ' Zeile mit dem Namen der Sitzung
	Dim flgSESSION As Boolean ' Flagge Session-Unterstützung (True = gefunden)
	Dim rKEY As String ' Zeile mit dem Namen und den Wert des Schlüssels
	Dim rdTEXT As TextReader ' Stream-Objekt für das Lesen der Datei
	Dim p As Int '
	Dim k As String '
	
	' zuweisen des Standardwert:
	value = default
	
	' überprüfen, ob die Datei vorhanden ist:
	If File.Exists(directory, fileINI) = True Then
		' initialisiert die Steuergrößen im Einsatz:
		rSESSION = ""
		flgSESSION = False
		rKEY = ""
		' öffnet die Datei INI lesen:
		rdTEXT.Initialize(File.OpenInput(directory, fileINI))
		' Lesen INI-Datei Zeile für Zeile:
		rSESSION = rdTEXT.ReadLine '.Trim
		Do While rSESSION <> Null
			' wenn der String zu lesen ist nicht leer:
			If rSESSION <> "" Then
				' identifiziert die Sitzung, zu der der Schlüssel gehört:
				If rSESSION.CompareTo("[" & session & "]") = 0 Then
				 	' die Sitzung ist identifiziert worden:
					flgSESSION = True
					' weiter lesen INI-Datei Zeile für Zeile, um den Schlüssel zu identifizieren:
					rKEY = rdTEXT.ReadLine
					Do While rKEY <> Null
						' wenn der String zu lesen ist nicht leer:
						If rKEY <> "" Then
							' erfasst den Namen des Schlüssels:
							p = rKEY.IndexOf("=")
							If p > -1 Then
								If rKEY.SubString2(0, 0) <> "[" Then
									k = rKEY.SubString2(0, p)
									' wenn Sie lesen, der Name der Schlüssel ist der Name des Schlüssels zu erkennen:
									If key.CompareTo(k) = 0 Then
										' weist den Wert des Schlüssels gefunden:
										value = rKEY.SubString(p + 1)
										Exit
									End If
								End If
							End If
						End If
						' führen eine neue Lesart:
						rKEY = rdTEXT.ReadLine
					Loop
				End If
			End If
			If flgSESSION = True Then
				' die Sitzung gefunden wird, stoppen Sie die Suche (Ausfahrt Schleife):
				Exit
			Else
				' die Sitzung nicht gefunden wird, weiter im Lesen:
				rSESSION = rdTEXT.ReadLine
			End If
		Loop
		' schließt die Datei INI:
		rdTEXT.Close
	End If

	Return value

End Sub
#End Region

#Region ***** SCHREIBEN *****
' *** schreibt oder aktualisiert einen Wert in der Datei. INI ***
Sub WriteINI(directory As String, fileINI As String, session As String, key As String, value As String)
	
	Dim nr As Int ' die Anzahl von Zeilen aus der Datei gelesen. INI
	Dim flgSESSION As Boolean ' Flagge Session-Unterstützung (True = gefunden)
	Dim flgKEY As Boolean ' Flagge der Unterstützung für den Schlüssel (True = gefunden)
	Dim rdTEXT As TextReader ' Stream-Objekt für. INI-Datei lesen
	Dim wrTEXT As TextWriter ' Stream-Objekt für. INI-Datei schreiben
	Dim rowtxt As String ' Textzeile lesen / schreiben
	Dim p As Int '
	Dim k As String '
	
	If File.Exists(directory, fileINI) = True Then ' prüft, ob der. INI-Datei vorhanden ist ...
		' initialisiert die Steuergrößen im Einsatz:
		nr = 0
		flgSESSION = False
		flgKEY = False
		' öffnet die Datei INI lesen:
		rdTEXT.Initialize(File.OpenInput(directory, fileINI))
		' öffnet eine Datei INI temporäre Schreib:
		wrTEXT.Initialize(File.OpenOutput(directory, "ini.tmp", False))
		' Lesen. INI-Datei Zeile für Zeile ...
		rowtxt = rdTEXT.ReadLine
		' Schleife um den Schlüssel zu finden, in der Sitzung angedeutet:
		Do While rowtxt <> Null
			' erhöht die Zählerstände:
			nr = nr + 1
			If rowtxt <> "" Then ' wenn Sie das Ende der Datei nicht erreicht haben. INI ursprünglich zum Lesen geöffnet werden ...
				If rowtxt.SubString2(0, 0) = "[" Then ' es ist eine Sitzung ...
					If flgSESSION = False Then ' tritt auf, wenn die Sitzung zu identifizieren ...
						If rowtxt.CompareTo("[" & session & "]") = 0 Then
							' die Sitzung ist identifiziert worden:
							flgSESSION = True
						End If
						If nr > 1 Then ' wenn die Sitzung, um neben dem ersten auf der Liste geschrieben werden ...
							' legen Sie eine leere Zeile, um das Lesen der Datei zu verbessern:
							wrTEXT.WriteLine("")
						End If
						wrTEXT.WriteLine(rowtxt) ' schreibt die Sitzung ohne Änderungen gefunden ...
					Else
						' die Sitzung wird geändert ...
						flgSESSION = False
						If flgKEY = False Then
							' aber wurde der Schlüssel der Sitzung zugeordnet wurde nicht gefunden ...
							wrTEXT.WriteLine(key & "=" & value) ' schreibt den Schlüssel und seinen Wert als neue ...
							flgKEY = True
							wrTEXT.WriteLine("") ' legen Sie eine leere Zeile, um das Lesen der Datei zu verbessern ...
							wrTEXT.WriteLine(rowtxt) ' schreibt die nächste Sitzung festgestellt, dass der Schlüssel nur geschrieben ...
							Exit ' Schlüsselsuche abgeschlossen ist, verlassen Sie die Schleife ...
						Else
							wrTEXT.WriteLine("") ' legen Sie eine leere Zeile, um das Lesen der Datei zu verbessern ...
							wrTEXT.WriteLine(rowtxt) ' schreibt die Sitzung ohne Änderungen gefunden ...
						End If
					End If
				Else
					If rowtxt <> "" Then ' schließen Sie die leeren Zeilen und prüfen, ob es ein Schlüssel ist ...
						' überprüfen, ob es der Schlüssel zu finden ist:
						p = rowtxt.IndexOf("=")
						If p > -1 And flgSESSION = True Then ' wenn es eine Taste mit der Sitzung verknüpft ist ...
							k = rowtxt.SubString2(0, p) ' extrahiert den Namen des Schlüssels ...
							If key.CompareTo(k) = 0 Then ' wenn der Name des Schlüssels in der Lese. INI-Datei ist der Name des Schlüssels zu finden ...
								flgKEY = True ' der Schlüssel identifiziert worden ...
								wrTEXT.WriteLine(key & "=" & value) ' schreibt den Schlüssel und seinen Wert ...
								Exit ' Schlüsselsuche abgeschlossen ist, verlassen Sie die Schleife ...
							Else
								' es ist nicht der Schlüssel angemeldet schreibt ohne Änderung der Zeile zu lesen:
								wrTEXT.WriteLine(rowtxt)
							End If
						Else
							' es ist ein Schlüssel zu einer anderen Sitzung gehört, schreibt die Leseleitung ohne edit:
							wrTEXT.WriteLine(rowtxt)
						End If
					End If
				End If
			End If
			rowtxt = rdTEXT.ReadLine ' liest eine neue Zeile aus der Datei INI Herkunft. ...
		Loop
		' alle Zeilen kopieren noch von INI-Datei im temporären Herkunft gelesen werden.:
		Do While rowtxt <> Null
		rowtxt = rdTEXT.ReadLine ' liest die Zeile aus der Datei INI Herkunft. ...
			If rowtxt <> Null Then ' wenn Sie das Ende der Datei nicht erreicht haben. INI ursprünglich zum Lesen geöffnet werden ...
				wrTEXT.WriteLine(rowtxt) ' schreibt die Linie nur in der Datei zu lesen. INI temporäre ...
			End If
		Loop
		' schließt die Datei in INI Lesen verwendet.:
		rdTEXT.Close
		If flgSESSION = False And flgKEY = False Then ' die Sitzung und der Schlüssel nicht gefunden wurde ...
			wrTEXT.WriteLine("") ' legen Sie eine leere Zeile, um das Lesen der Datei zu verbessern ...
			wrTEXT.WriteLine("[" & session & "]") ' schreibt die Session-ID ...
			wrTEXT.WriteLine(key & "=" & value) ' schreibt den Schlüssel und seinen Wert ...
		End If
		wrTEXT.Close ' schließt die Datei. temporäre INI schriftlich verwendet ...
	Else
		' die INI-Datei nicht vorhanden ist, dann wird es mit dem einzigen Sitzung und nur mit dem Schlüssel erstellt:
		wrTEXT.Initialize(File.OpenOutput(directory, fileINI, False))
		' schreibt die Session-ID:
		wrTEXT.WriteLine("[" & session & "]")
		' schreibt den Schlüssel und seinen Wert:
		wrTEXT.WriteLine(key & "=" & value)
		' schließt die Datei INI:
		wrTEXT.Close
	End If

	If File.Exists(directory, "ini.tmp") = True Then ' wenn die. temporäre INI vorhanden ...
		If File.Exists(directory, fileINI) = True Then ' wenn die Datei vorhanden. INI Herkunft ...
			File.Delete(directory, fileINI) ' wenn Sie es zu löschen ...
		End If
		' ersetzt die INI-Datei das Original mit der temporären ein.:
		File.Copy(directory, "ini.tmp", directory, fileINI)
		' Löschen Sie die Datei vorübergehend INI.:
		File.Delete(directory, "ini.tmp")
	End If
	
End Sub
#End Region

#End Region

#Region Übergabe der Daten
Sub WriteData(sFahrlehrer As String, iFahrlehrerID As Int, sCheckedKFZ As String, iCheckedKFZID As Int, _
				sFahrEinheit As String, bBegleitfahrzeug As Boolean, bPrueferg As Boolean, bBVFAusbildung As Boolean) As Boolean
	Try
		'Wenn INI schon vorhanden, dann löschen und neu erstellen lassen 
		If File.Exists(Main.SourceFolder, "FaData.INI") = True Then
			File.Delete(Main.SourceFolder, "FaData.INI")
		End If
		
		WriteINI(Main.SourceFolder, "FaData.INI", "Fahrlehrer", "Fahrlehrer", sFahrlehrer)
		WriteINI(Main.SourceFolder, "FaData.INI", "Fahrlehrer", "FahrlehrerID", iFahrlehrerID)
		WriteINI(Main.SourceFolder, "FaData.INI", "KFZ", "Fahrzeug", sCheckedKFZ)
		WriteINI(Main.SourceFolder, "FaData.INI", "KFZ", "FahrzeugID", iCheckedKFZID)
		WriteINI(Main.SourceFolder, "FaData.INI", "Einheit", "Einheit", sFahrEinheit)
		WriteINI(Main.SourceFolder, "FaData.INI", "Begleitfahrzeug", "anzeigen", bBegleitfahrzeug)
		WriteINI(Main.SourceFolder, "FaData.INI", "Pruefungsergebnis", "erfassen", bPrueferg)
		WriteINI(Main.SourceFolder, "FaData.INI", "BVFAusbildung", "anzeigen", bBVFAusbildung)
		'WriteINI(Main.SourceFolder, "FaData.INI", "Schueler", "ausgewaehlt", sSchueler)
		Return True
	Catch
		Return False
	End Try
End Sub

Sub WriteTreffpunkte As Boolean
	Try
		'Wenn INI schon vorhanden, dann löschen und neu erstellen lassen 
		If File.Exists(Main.SourceFolder, "FaDataMeetingPoints.INI") = True Then
			File.Delete(Main.SourceFolder, "FaDataMeetingPoints.INI")
		End If
		
		'Flexible Treffpunkte aus DB lesen und in Datei schreiben
		Dim DefaultMeetingPoints(,) As String
		DefaultMeetingPoints = DBUtils.GetAllMeetingPoint
		
		For i = 1 To DefaultMeetingPoints.Length
			WriteINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "Treffpunkt", DefaultMeetingPoints(i - 1, 0))
			WriteINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "fixer_Treffpunkt", DefaultMeetingPoints(i - 1, 1))
			WriteINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "TreffpunktID", DefaultMeetingPoints(i - 1, 2))
		Next
		Return True
	Catch
		Return False
	End Try
End Sub
#End Region

#Region Konfigdaten aus INI lesen
Sub ReadConfigData() As Boolean()
	Dim aKonfig(3) As Boolean
	Dim sBegleitfahrzeug, sPruefergebnis, sBVFAusbildung As String
	
	sBegleitfahrzeug = ReadINI(Main.SourceFolder, "FaData.INI", "Begleitfahrzeug", "anzeigen", "")
	sPruefergebnis = ReadINI(Main.SourceFolder, "FaData.INI", "Pruefungsergebnis", "erfassen", "")
	sBVFAusbildung = ReadINI(Main.SourceFolder, "FaData.INI", "BVFAusbildung", "anzeigen", "")
	
	If sBegleitfahrzeug = "" Then
		sBegleitfahrzeug = "false"
	End If
	
	If sPruefergebnis = "" Then
		sPruefergebnis = "false"
	End If
	
	If sBVFAusbildung = "" Then
		sBVFAusbildung = "false"
	End If
	
	aKonfig = Array As Boolean(sBegleitfahrzeug, sPruefergebnis, sBVFAusbildung)
	Return aKonfig	
End Sub

Sub ReadConfigFahrData() As String()
	Dim aKonfig(4) As String
	Dim sFahrlehrerValue, sFahrlehrerValueID, sFahrzeugValue, sFahrzeugValueID As String
	
	sFahrlehrerValue = ReadINI(Main.SourceFolder, "FaData.INI", "Fahrlehrer", "Fahrlehrer", "")
	sFahrlehrerValueID = ReadINI(Main.SourceFolder, "FaData.INI", "Fahrlehrer", "FahrlehrerID", "")
	sFahrzeugValue = ReadINI(Main.SourceFolder, "FaData.INI", "KFZ", "Fahrzeug", "")
	sFahrzeugValueID = ReadINI(Main.SourceFolder, "FaData.INI", "KFZ", "FahrzeugID", "")
	'sSchueler = ReadINI(Main.SourceFolder, "FaData.INI", "Schueler", "ausgewaehlt", "")
	
	aKonfig = Array As String(sFahrlehrerValue, sFahrlehrerValueID, sFahrzeugValue, sFahrzeugValueID)
	Return aKonfig
End Sub

Sub ReadFahrEinheit() As String
	Dim sFahrEinheit As String
	
	sFahrEinheit = ReadINI(Main.SourceFolder, "FaData.INI", "Einheit", "Einheit", "")
	
	Return sFahrEinheit
End Sub

Sub ReadTreffpunkte(i As Int) As String()
	Dim aTreffpunkt(3) As String	
	Dim sTreffpunkt, sFixer_Treffpunk, sTreffpunktID As String
	
	sTreffpunkt = ReadINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "Treffpunkt", "")
	sFixer_Treffpunk = ReadINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "fixer_Treffpunkt", "")
	sTreffpunktID = ReadINI(Main.SourceFolder, "FaDataMeetingPoints.INI", "Treffpunkte_" & i, "TreffpunktID", "")
	
	aTreffpunkt = Array As String(sTreffpunkt, sFixer_Treffpunk, sTreffpunktID)
	Return aTreffpunkt
End Sub

Sub GetTreffpunktEintraege As Int
	Dim list1 As List
	Dim anzahl As Int
	
	If File.Exists(Main.SourceFolder, "FaDataMeetingPoints.INI") Then
		list1 = File.ReadList(Main.SourceFolder, "FaDataMeetingPoints.INI")
		anzahl = list1.Size / 6
	Else
		anzahl = 0
	End If
	
	Return anzahl
End Sub
#End Region

#Region Update Konfigdaten
Sub UpdateINI(Session As String, Key As String, Value As String)
	Dim sConfigData, newsConfigData As String
	Dim sValue As String
	
	'Value des bisherigen Eintrags auslesen
	sValue = ReadINI(Main.SourceFolder, "FaData.INI", Session, Key, "")
	
	If Not(sf.IsEmpty(sValue)) Then
		sConfigData = File.GetText(Main.SourceFolder, "FaData.INI")
		newsConfigData = sConfigData.Replace("[" & Session & "]" & Key & "=" & sValue, "[" & Session & "]" & Key & "=" & Value)
		

		If File.Exists(Main.SourceFolder, "ini.tmp") = True Then ' wenn die. temporäre INI vorhanden ...
			If File.Exists(Main.SourceFolder, "FaData.INI") = True Then ' wenn die Datei vorhanden. INI Herkunft ...
				File.Delete(Main.SourceFolder, "FaData.INI") ' wenn Sie es zu löschen ...
			End If
			' ersetzt die INI-Datei das Original mit der temporären ein.:
			File.Copy(Main.SourceFolder, "ini.tmp", Main.SourceFolder, "FaData.INI")
			' Löschen Sie die Datei vorübergehend INI.:
			File.Delete(Main.SourceFolder, "ini.tmp")
		End If
		
		If File.Exists(Main.SourceFolder, "FaData.INI") Then
			File.Delete(Main.SourceFolder, "FaData.INI")
		Else
			File.WriteString(Main.SourceFolder, "FaData.INI", newsConfigData)
		End If
	End If
End Sub

Sub UpdateFahrINI(Session As String, Key As String, Value As String, ID As String)
	Dim sConfigData, newsConfigData As String
	Dim sValue, sValueID As String
	
	'Value des bisherigen Eintrags auslesen
	sValue = ReadINI(Main.SourceFolder, "FaData.INI", Session, Key, "")
	sValueID = ReadINI(Main.SourceFolder, "FaData.INI", Session, Key & "ID", "")
	
	If Not(sf.IsEmpty(sValue)) Then
		sConfigData = File.GetText(Main.SourceFolder, "FaData.INI")
		newsConfigData = sConfigData.Replace("[" & Session & "]" & Key & "=" & sValue & "[" & Session & "]" & Key & "ID" & "=" & sValueID, "[" & Session & "]" & Key & "=" & Value & "[" & Session & "]" & Key & "ID" & "=" & ID)
		

		If File.Exists(Main.SourceFolder, "ini.tmp") = True Then ' wenn die. temporäre INI vorhanden ...
			If File.Exists(Main.SourceFolder, "FaData.INI") = True Then ' wenn die Datei vorhanden. INI Herkunft ...
				File.Delete(Main.SourceFolder, "FaData.INI") ' wenn Sie es zu löschen ...
			End If
			' ersetzt die INI-Datei das Original mit der temporären ein.:
			File.Copy(Main.SourceFolder, "ini.tmp", Main.SourceFolder, "FaData.INI")
			' Löschen die vorübergehend INI. Datei :
			File.Delete(Main.SourceFolder, "ini.tmp")
		End If
		
		If File.Exists(Main.SourceFolder, "FaData.INI") Then
			File.Delete(Main.SourceFolder, "FaData.INI")
		Else
			File.WriteString(Main.SourceFolder, "FaData.INI", newsConfigData)
		End If
	End If
End Sub
#End Region