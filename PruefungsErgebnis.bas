Type=Activity
Version=2.30
FullScreen=False
IncludeTitle=True
@EndOfDesignText@
'Activity module
Sub Process_Globals
	Dim bPruefErgebnisErfassen As Boolean
	Dim iPruefErgebnis As Int
End Sub

Sub Globals
	Dim rbNichtErfassen, rbBestanden, rbNichtBestanden As RadioButton
	Dim panPruefung As Panel
	Dim btPruefErgenbisOK As Button
	Dim lblPruefungsergebisse As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("pruefergebnis")	
	Activity.Title = "Prüfungs-Ergebnis"
	
	'Beschriftung
	Helper.PruefungsergebnisBeschriftung(lblPruefungsergebisse, rbNichtErfassen, rbBestanden, rbNichtBestanden, btPruefErgenbisOK)
	
	bPruefErgebnisErfassen = False
	
	'Vorauswahl festlegen -> 'nicht bestanden'
	rbNichtErfassen.Checked = True
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'Fahrdaten: Prüfungsergebnis erfassen
Sub btPruefErgenbisOK_Click
	'Welche Auswahl wurde getroffen
	If rbBestanden.Checked Then
		iPruefErgebnis = 2					'2 = Bestanden
	Else If rbNichtBestanden.Checked Then
		iPruefErgebnis = 1					'1 = nicht bestanden
	Else
		iPruefErgebnis = 0					'0 = nicht erfasst
	End If
	
	bPruefErgebnisErfassen = True
	Activity.Finish 
	StartActivity(Main)
End Sub