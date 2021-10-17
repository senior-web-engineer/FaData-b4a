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
	Dim KfzSelected As Int			'Rückgabe an das Active Fenster
	
	KfzSelected = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstKfz As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("List_KFZ")	
	Activity.Title = "KFZ Auswahl"
	
	lstKfz.Height = 100%y
	lstKfz.Width = 100%x
	lstKfz.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillKfzListView(lstKfz)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstKfz_ItemClick (Position As Int, Value As Object)
	KfzSelected = Position
	
	If Main.bKFZmanuel = False Then
		Helper.KFZFixieren(Value)
	End If
	
	Main.bKFZmanuel = False
	Activity.Finish 
	StartActivity(Main)
End Sub