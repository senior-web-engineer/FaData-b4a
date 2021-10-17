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
	Dim BegleitfahrzeugSelected As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstBegleitfahrzeug As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
'	Dim lv As LayoutValues
'	lv = GetDeviceLayoutValues
	
	Activity.LoadLayout("List_Begleitfahrzeug")	
	Activity.Title = "Begleitfahrzeug"
	
	lstBegleitfahrzeug.Height = 100%y
	lstBegleitfahrzeug.Width = 100%x
	lstBegleitfahrzeug.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillBegleitfahrzeugListView(lstBegleitfahrzeug)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstBegleitfahrzeug_ItemClick (Position As Int, Value As Object)
	BegleitfahrzeugSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub