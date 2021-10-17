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
	Dim SonstTaetigkeitSelected As Int 
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstSonstigeTaetigkeiten As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
'	Dim lv As LayoutValues
'	lv = GetDeviceLayoutValues
	
	Activity.LoadLayout("List_SonstigeTaetigkeiten")	
	Activity.Title = "Sonstige Tätigkeiten"
	
	lstSonstigeTaetigkeiten.Height = 100%y 
	lstSonstigeTaetigkeiten.Width = 100%x
	lstSonstigeTaetigkeiten.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillSonstigeTaetigkeitenListView(lstSonstigeTaetigkeiten)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstSonstigeTaetigkeiten_ItemClick (Position As Int, Value As Object)
	SonstTaetigkeitSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub