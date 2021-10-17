Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=3.5
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: False
	#IncludeTitle: True
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim TreffpunktSelected As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstTreffpunkt As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
'	Dim lv As LayoutValues
'	lv = GetDeviceLayoutValues
'	
	Activity.LoadLayout("List_Treffpunkt")	
	Activity.Title = "Treffpunkte"
	
	lstTreffpunkt.Height = 100%y
	lstTreffpunkt.Width = 100%x
	lstTreffpunkt.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillTreffpunktListView(lstTreffpunkt)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstTreffpunkt_ItemClick (Position As Int, Value As Object)
	TreffpunktSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub

Sub lstTreffpunkt_ItemLongClick (Position As Int, Value As Object)
	Dim iResult As Int
	
	If Main.bTreffpunktAuswahl Then
		iResult = Msgbox2("Soll der Treffpunkt '" & Value & "' wirklich gelöscht werden?", "Sicherheitsabfrage", "Ja", "", "Nein", Null)
		
		If iResult = DialogResponse.POSITIVE Then
			'Eintrag aus Liste nehmen und in DB unsichtbar machen
			lstTreffpunkt.RemoveAt(Position)
			Helper.RemoveNeuenTreffpunkt(Value)
			ToastMessageShow("Eintrag gelöscht!", False)
		End If
		'Variable zum Löschen und Position merken zurücksetzen
		Main.bTreffpunktAuswahl = False
		TreffpunktSelected = 0
		
		Activity.Finish
		StartActivity(Main)
	End If
End Sub