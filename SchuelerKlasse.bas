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
	Dim KlasseSelected As Int			'Rückgabe an das Active Fenster
	
	KlasseSelected = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstSchuelerKlasse As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("List_Schueler_Klasse")	
	Activity.Title = "Ausbildungs-Klasse"
	
	lstSchuelerKlasse.Height = 100%y
	lstSchuelerKlasse.Width = 100%x
	lstSchuelerKlasse.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillSchuelerKlasseListView(lstSchuelerKlasse)
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub

Sub lstSchuelerKlasse_ItemClick (Position As Int, Value As Object)
	Main.bAlleKlassen = False
	KlasseSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub