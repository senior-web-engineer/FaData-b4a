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
	Dim FahrlehrerSelected As Int			'Rückgabe an das Active Fenster
	
	FahrlehrerSelected = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstFahrlehrer As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("List_Fahrlehrer")	
	Activity.Title = "Fahrlehrer Auswahl"
	
	lstFahrlehrer.Height = 100%y
	lstFahrlehrer.Width = 100%x
	lstFahrlehrer.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillFahrlehrerListView(lstFahrlehrer)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstFahrlehrer_ItemClick (Position As Int, Value As Object)
	FahrlehrerSelected = Position
	Main.sFahrlehrer = Value
	Helper.FahrlehrerFixieren(Value)
	Activity.Finish 
	StartActivity(Main)
End Sub