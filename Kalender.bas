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
	Dim ZeitEinheit As Long
	
	Dim FirstDayOfMonth, LastDayOfMonth, DaySelected As Long
    Dim Day(7) As String
    Dim Month(12) As String
	Dim FirstDayOfWeek As Int
	
	DaySelected = 0
End Sub

Sub Globals
	Dim panKalender As Panel
	Dim Label1 As Label
	Dim Label4 As Label
	Dim Label5 As Label
	Dim Label6 As Label
	Dim Label7 As Label
	Dim Label8 As Label
	Dim Label9 As Label
	Dim Label10 As Label
	Dim NextMonth,PrevMonth, btAbbruch, btAktuellerMonat As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)

	'Set which day is the first day of the week
	'0 = Sunday
	'1 = Monday
	FirstDayOfWeek = 1

	If FirstTime Then
		'Change according to your language
		Day(0) = "So"
		Day(1) = "Mo"
		Day(2) = "Di"
		Day(3) = "Mi"
		Day(4) = "Do"
		Day(5) = "Fr"
		Day(6) = "Sa"
		Month(0) = "Januar"
		Month(1) = "Februar"
		Month(2) = "März"
		Month(3) = "April"
		Month(4) = "Mai"
		Month(5) = "Juni"
		Month(6) = "Juli"
		Month(7) = "August"
		Month(8) = "September"
		Month(9) = "Oktober"
		Month(10) = "November"
		Month(11) = "Dezember"
	End If
	
'	NextMonth.Color = Colors.LightGray
'	PrevMonth.Color = Colors.LightGray
'	btAbbruch.Color = Colors.LightGray
'	btAktuellerMonat.Color = Colors.LightGray
		
	DaySelected = 0
	ZeitEinheit = DateTime.Now 
	Activity.LoadLayout("Kalender")	
	Activity.Title = "Tagesauswahl"
'	panKalender.Height = 340 * lv.Scale 
'	panKalender.Left = (100%x - panKalender.Width) / 2

	If FirstDayOfWeek = 1 Then
		Label4.Text = Day(1)
		Label5.Text = Day(2)
		Label6.Text = Day(3)
		Label7.Text = Day(4)
		Label8.Text = Day(5)
		Label9.Text = Day(6)
		Label10.Text = Day(0)
	Else
		Label4.Text = Day(0)
		Label5.Text = Day(1)
		Label6.Text = Day(2)
		Label7.Text = Day(3)
		Label8.Text = Day(4)
		Label9.Text = Day(5)
		Label10.Text = Day(6)
	End If
	
'	Label4.Width = -2
'	Label5.Width = -2
'	Label6.Width = -2
'	Label7.Width = -2
'	Label8.Width = -2
'	Label9.Width = -2
'	Label10.Width = -2
	
	'DoEvents
'		i = 10 * lv.Scale 
'		Label4.Left = i + (7 * lv.Scale)
'		i = i + (40 * lv.Scale)
'		Label5.Left = i + (7 * lv.Scale)
'		i = i + (40 * lv.Scale)
'		Label6.Left = i + (7 * lv.Scale)
'		i = i + (40 * lv.Scale)
'		Label7.Left = i + (7 * lv.Scale)
'		i = i + (40 * lv.Scale)
'		Label8.Left = i + (7 * lv.Scale) 
'		i = i + (40 * lv.Scale)
'		Label9.Left = i + (7 * lv.Scale) 
'		i = i + (40 * lv.Scale)
'		Label10.Left = i + (7 * lv.Scale) 
'		btAbbruch.Top = panKalender.Top + panKalender.Height + (15 * lv.Scale)
'		btAktuellerMonat.Top = panKalender.Top + panKalender.Height + (15 * lv.Scale)
'		btAbbruch.Left = (100%x / 2) - (btAbbruch.Width + btAktuellerMonat.Width + 15) / 2 
'		btAktuellerMonat.Left = btAbbruch.Left + btAbbruch.Width + 15
		CreateCalendar
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub CreateCalendar
	Dim i As Int
	Dim OffsetX As Int
	Dim OffsetY As Int
	Dim TempDay As Long 
	Dim StartDate As Long
	Dim EndDate As Long 
	Dim bDay As Long
	Dim Today As Long 
	Dim lv As LayoutValues
	lv = GetDeviceLayoutValues

	Today = DateTime.Now 
	DateTime.DateFormat="ddMMyyyy"
	'ersten Tag des Montas berechnen
	FirstDayOfMonth = DateTime.DateParse("01" & DateTime.Date(ZeitEinheit).SubString(2))
	'letzten Tag des Montas berechnen
	TempDay = DateTime.Add(ZeitEinheit,0,1,0)
	TempDay = DateTime.DateParse("01" & DateTime.Date(TempDay).SubString(2))
	LastDayOfMonth = DateTime.Add(TempDay,0,0,-1)

	Label1.Text = Month(DateTime.GetMonth(ZeitEinheit) - 1) & "  " & DateTime.GetYear(ZeitEinheit)
	OffsetX = panKalender.Left
	OffsetY = Label4.Top + Label4.Height + 5
			
	'ersten Tag berechnen, der anzeigt wird
	If FirstDayOfWeek = 1 Then
		If (DateTime.GetDayOfWeek(FirstDayOfMonth) - 1) = 0 Then
			StartDate = DateTime.Add(FirstDayOfMonth,0,0,-6)
		Else
			StartDate = DateTime.Add(FirstDayOfMonth,0,0, 0-(DateTime.GetDayOfWeek(FirstDayOfMonth) - 2))
		End If
	Else
		StartDate = DateTime.Add(FirstDayOfMonth,0,0, 0-(DateTime.GetDayOfWeek(FirstDayOfMonth) - 1))
	End If

	bDay = StartDate

	'letzten Tag des Montas berechnen, welcher anzeigt wird
	If FirstDayOfWeek = 1 Then
		If (DateTime.GetDayOfWeek(LastDayOfMonth) - 1) = 0 Then
			EndDate = LastDayOfMonth
		Else
			EndDate = DateTime.Add(LastDayOfMonth,0,0, 8 - DateTime.GetDayOfWeek(LastDayOfMonth))
		End If  
	Else
		EndDate = DateTime.Add(LastDayOfMonth,0,0, 7 - DateTime.GetDayOfWeek(LastDayOfMonth))
	End If

	'Button für jeden Tag der angezeigt werden soll
	'Die Tage in vergangenden und im nächsten Monat werden grau angezeigt und können nicht ausgewählt werden
	'der aktuelle Tag wird in fettem ROT angezeigt
	For i = 1 To 42
		If bDay <= EndDate Then
			Dim bn As Button 
			bn.Initialize("ButtonPress")
			bn.Text = DateTime.GetDayOfMonth(bDay)
			bn.Color = Colors.LightGray
			bn.TextColor = Colors.Black
			bn.TextSize = 11

			If DateTime.GetMonth(bDay) <> DateTime.GetMonth(ZeitEinheit) Then
				bn.Enabled = False
				bn.Tag = 0
			Else
				bn.Enabled = True
				bn.Tag = bDay
			End If
			
			If DateTime.Date(bDay) = DateTime.Date(Today)  Then
				bn.TextColor = Colors.Red 
				bn.Typeface = Typeface.DEFAULT_BOLD 
			End If
			
			'Alle Buttons plazieren
			panKalender.AddView(bn, OffsetX, OffsetY, Label4.Width, Label4.Height)
			OffsetX = (OffsetX + Label4.Width) + ((Label10.Left - Label4.Left - (6 * Label4.Width)) / 6) 
			
			If OffsetX > (panKalender.Width - Label4.Width) Then
				OffsetX = panKalender.Left
				OffsetY = OffsetY + Label4.Height + ((Label10.Left - Label4.Left - (6 * Label4.Width)) / 6)
			End If
			bDay = DateTime.Add(bDay,0,0,1)
		End If
	Next

End Sub

Sub RemovePanelViews
	Dim i As Int
	Dim v As View 
	Dim ii As Long 

	For i=panKalender.NumberOfViews-1 To 0 Step -1
		v = panKalender.GetView(i)
		Try
			ii = v.Tag
			If ii >= 0 Then
				panKalender.RemoveViewAt(i)
			End If
		Catch
			Log(LastException.Message)
		End Try
	Next
End Sub

Sub ButtonPress_Click
	'Update DaySelected with the selected day and return to the calling activity
	Dim b As Button 
	b = Sender
	DaySelected = b.Tag 
	Activity.Finish 
	StartActivity(Main)		
End Sub

Sub PrevMonth_Click
	'vorigen Monat anzeigen
	RemovePanelViews	
	ZeitEinheit = DateTime.Add(FirstDayOfMonth,0,-1,0)
	CreateCalendar	
End Sub

Sub NextMonth_Click
	'nexten Monat anzeigen
	RemovePanelViews 	
	ZeitEinheit = DateTime.Add(FirstDayOfMonth,0,1,0)
	CreateCalendar	
End Sub

Sub btAktuellerMonat_Click
	'aktuellen Monat anzeigen
	RemovePanelViews	
	ZeitEinheit = DateTime.Now 	
	CreateCalendar	
End Sub

Sub btAbbruch_Click
	'Zurück zum aufrufenden Fenster ohne ausgewählten Tag
	Activity.Finish 
	StartActivity(Main)			
End Sub