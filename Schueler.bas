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
	Dim SchuelerSelected As Int			'Rückgabe an das Active Fenster
	
	SchuelerSelected = 0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim lstSchueler As ListView
	
	Type ExpListViewItem(IsParent As Boolean, Expanded As Boolean, id As Int, childto As Int, Sort As String, Schueler As String, SchuelerID As Int)
	Dim ExpandableList As List
	Dim SV As ScrollView
	Dim sABC As String
	Dim iAnzahl As Int
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'SV.Initialize(1500dip)
	
	Activity.LoadLayout("List_Schueler")	
	Activity.Title = "Schüler Auswahl"
	'Activity.AddView(SV, 0, 0, 100%x, 100%y)
	
	SV.Height = 100%y
	SV.Width = 100%x
	
'	lstSchueler.Height = 100%y
'	lstSchueler.Width = 100%x
'	lstSchueler.ScrollingBackgroundColor = Colors.Transparent
'	
'	Helper.FillSchuelerListView(lstSchueler)
	Helper.FillSchuelerList(lstSchueler)

	ExpandableList.Initialize
	For i = 0 To 26
		Dim ParentItem As ExpListViewItem
		ParentItem.Initialize
		ParentItem.Expanded = False
		ParentItem.id = i
		ParentItem.childto = 0
		If i <> 0 Then
			ParentItem.IsParent = True
			If i = 1 Then
				sABC = "A"
			Else
				sABC = Chr(Asc(sABC) + 1)
			End If
			ParentItem.Sort = sABC
		End If
		ExpandableList.Add(ParentItem)
		
		iAnzahl = Helper.FillSchuelerListViewItems(sABC)
		
		For j = 0 To iAnzahl - 1
			Dim item As ExpListViewItem 
			item.Initialize 
			item.IsParent = False
			item.Expanded = False
			item.id = i * 10 + j
			item.childto = ParentItem.id 
			item.Sort = sABC
			ExpandableList.Add(item)
		Next
	Next
	
	DrawListView
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lstSchueler_ItemClick (Position As Int, Value As Object)
	SchuelerSelected = Position
	Activity.Finish 
	StartActivity(Main)
End Sub

'Draw the list here. There is only 1 level of hierarchy
Sub DrawListView
	'Clear scrollview
	For i = SV.Panel.NumberOfViews - 1 To 0 Step -1
		SV.Panel.RemoveViewAt(i)
	Next
	
	Dim ItemHeight As Int 
	ItemHeight = 40dip
	Dim ChildOffset As Int 
	ChildOffset = 30dip
	
	Dim pHeight As Int 
	pHeight = 0

	For i = 0 To ExpandableList.Size - 1
		Dim item As ExpListViewItem 
		item = ExpandableList.Get(i)
		If item.IsParent = True Then
			Dim p As Panel 
			p.Initialize("pnl")
			SV.Panel.AddView(p, 0, pHeight, 100%x, ItemHeight)
			ConstructPanel(item, p)
			pHeight = pHeight + ItemHeight
		End If
		If item.Expanded = True Then
			For j = 0 To ExpandableList.Size - 1 
				Dim childitem As ExpListViewItem 
				childitem = ExpandableList.Get(j)
				If childitem.childto = item.id Then
					Dim p As Panel 
					p.Initialize("pnl")
					SV.Panel.AddView(p, ChildOffset, pHeight, 100%x - ChildOffset, ItemHeight)
					ConstructPanel(childitem, p)
					pHeight = pHeight + ItemHeight
				End If
			Next
		End If
	Next
	SV.Panel.Height = pHeight
End Sub

'Deal with Panel click. All controls are clickable
Sub pnl_Click
	Dim v As View
	v = Sender
	Dim item As ExpListViewItem 
	item = v.Tag
	
	If item.IsParent = True Then
		item.Expanded = Not(item.Expanded)
		DrawListView
	Else
		Main.iAusgewaehlterSchuelrID = item.SchuelerID
		Main.sAusgewaehlterSchueler = item.Schueler
		SchuelerSelected = item.SchuelerID
		
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub

'Create a custom layout for the panel here. It may also be dependent on the item
Sub ConstructPanel(item As ExpListViewItem, p As Panel)
	Dim l As Label 
	Dim img As ImageView 
	
	l.Initialize("")
	img.Initialize("")
	
	p.AddView(l, 48dip, 0dip, 100%y, 40dip)
	p.AddView(img, 4dip, 2dip, 32dip, 32dip)
	l.Gravity = Gravity.CENTER_VERTICAL 
	l.TextSize = 20
	l.TextColor = Colors.Black
	
	Dim c As Canvas 
	c.Initialize(p)
	c.DrawLine(0, p.Height - 2dip, 100%x, p.Height - 2dip, Colors.Black, 1dip)
		
	If item.IsParent Then
		l.Text = item.Sort
	Else
		If (item.childto * 10 = item.id) Then
			sABC = item.Sort
			Main.aSchueler = Helper.FillSchuelerArray(sABC)
			iAnzahl = 0
		End If
		If Main.aSchueler.Length <> 0 Then
			l.Text = Main.aSchueler(iAnzahl, 0)
			item.Schueler = Main.aSchueler(iAnzahl, 0)
			item.SchuelerID = Main.aSchueler(iAnzahl, 1)
			iAnzahl = iAnzahl + 1
		End If
		'Helper.FillSchuelerListViewItem(l, sABC)
	End If
	
	If item.IsParent = True And item.Expanded = True Then
		img.Bitmap = LoadBitmap(File.DirAssets, "ic_menu_more.png")
	Else If item.IsParent = True Then
		img.Bitmap = LoadBitmap(File.DirAssets, "ic_menu_less.png")
	End If
	p.Tag = item

End Sub