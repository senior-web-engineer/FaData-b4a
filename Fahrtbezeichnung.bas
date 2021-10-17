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
	Dim FahrtSelected As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstFahrtBezeichnung As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("List_Fahrtbezeichnung")	
	Activity.Title = "Fahrtenbezeichnung"
	
	lstFahrtBezeichnung.Height = 100%y 
	lstFahrtBezeichnung.Width = 100%x
	lstFahrtBezeichnung.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillFahrtenbezeichnungListView(lstFahrtBezeichnung)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstFahrtBezeichnung_ItemClick (Position As Int, Value As Object)
	FahrtSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub