Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=3.5
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim KlassenSelected As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstKlassen As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
'	Dim lv As LayoutValues
'	lv = GetDeviceLayoutValues
	
	Activity.LoadLayout("List_Klassen")	
	Activity.Title = "alle Klassen"
	
	lstKlassen.Height = 100%y 
	lstKlassen.Width = 100%x
	lstKlassen.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillKlassenListView(lstKlassen)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstKlassen_ItemClick (Position As Int, Value As Object)
	Main.bAlleKlassen = True
	KlassenSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub
