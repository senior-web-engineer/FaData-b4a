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
	Dim ZahlungFuerSelected As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstZahlungFuer As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
'	Dim lv As LayoutValues
'	lv = GetDeviceLayoutValues
	
	Activity.LoadLayout("List_ZahlungenFuer")	
	Activity.Title = "Zahlungen für"
	
	lstZahlungFuer.Height = 100%y
	lstZahlungFuer.Width = 100%x
	lstZahlungFuer.ScrollingBackgroundColor = Colors.Transparent
	
	Helper.FillZahlungenFuerListView(lstZahlungFuer)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstZahlungFuer_ItemClick (Position As Int, Value As Object)
	ZahlungFuerSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub