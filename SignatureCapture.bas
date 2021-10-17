Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=3.5
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim laX, laY, s, iStartX, iStartY As Int
	Type SignatureData (Canvas As Canvas, Panel As Panel, SignatureColor As Int, SignatureWidth As Int)
	
	Dim savX(5000) As Int
	Dim savY(5000) As Int
	
	Dim sPath As String
	
	Dim PathPoints(8) As Int
	Dim PathPointIndex As Int
	
	Type PointType (x As Int, y As Int)
	
End Sub

Sub Panel_Touch(SD As SignatureData, x As Float, y As Float, Action As Int)
	Select Action
		Case 0 				' Mouse down
			laX = x
			laY = y
		Case 1				' Mouse up
			If laX = x And laY = y Then
				SD.Canvas.DrawCircle(laX, laY, 2, Colors.Black, True, 1dip)
			End If
	End Select
	
	SD.Canvas.DrawLine(laX, laY, x, y, SD.SignatureColor, SD.SignatureWidth)
	SD.Panel.Invalidate
	laX = x
	laY = y
End Sub

Sub Panel1_Touch(SD As SignatureData, X As Float, Y As Float, Action As Int)
	'Anzeige im Panel
	Panel_Touch(SD, X, Y, Action)
	
	'Größenverhältnis anpassen
	If Main.lv.Height = 960 And Main.lv.Width = 540 Then	'SAMSUNG Galaxy S4 mini
		X = Round(X * 2 / 6)
		Y = Round(Y / 3)
	Else If Main.lv.Height < 976 And Main.lv.Width < 600 Then
		X = Round(X * 2 / 3)
		Y = Round(Y / 2)
	Else If Main.lv.Height >= 976 And Main.lv.Height < 1280 And Main.lv.Width >= 600 And Main.lv.Width < 800 And Main.lv.Width <> 720 Then
	'Else If Main.lv.Height >= 976 AND Main.lv.Width >= 600 Then
		X = Round(X * 2 / 5)			'X = Round(X * 2 / 9)
		Y = Round(Y / 2)				'Y = Round(Y / 10)
	Else If Main.lv.Height = 1216 And Main.lv.Width = 720 Then
		X = Round(X * 2 / 7)
		Y = Round(Y / 3)
	Else If Main.lv.Height = 1216 And Main.lv.Width = 800 Then	'Ellwart
		X = Round(X * 2 / 6)
		Y = Round(Y / 2)
	Else If Main.lv.Height = 1232 And Main.lv.Width = 800 Then
		X = Round(X * 2 / 7)			'1280x720 Size: 10'' Tablet
		Y = Round(Y / 2)
	Else If Main.lv.Height = 1280 And Main.lv.Width = 800 Then
		X = Round(X * 2 / 7)			'1280x720 Size: 8'' Tablet
		Y = Round(Y / 3)
	Else If Main.lv.Height = 1396 And Main.lv.Width = 420 Then
		X = Round(X * 2 / 6)			'1396x420 Size: 8'' Tablet
		Y = Round(Y / 2)
	Else If Main.lv.Height <= 1920 And Main.lv.Width <= 1080 Then
		'GHE: Größenanpassung für großes Display
		X = Round(X * 2 / 9)			'1920x1080 Size: 3 SamsugS4
		Y = Round(Y / 8)	
	Else If Main.lv.Height <= 2560 And Main.lv.Width <= 1440 And Main.lv.Scale = 4 Then
		'GHE: Größenanpassung für großes Display
		X = Round(X * 2 / 14)			'2560x1440 Size: 4 Samsung 
		Y = Round(Y / 8)	
	Else If Main.lv.Height <= 2560 And Main.lv.Width <= 1440 Then
		'GHE: Größenanpassung für großes Display
		X = Round(X * 2 / 14)			'2560x1440 Size: 3 Samsung 
		Y = Round(Y / 6)
	Else
		'GHE: Größenanpassung für großes Display 
		X = Round(X * 2 / 14)			'2560x1600 Size: 2
		Y = Round(Y / 4)				
	End If
		
	'Aktion Definitionen:
	'		0 -> Mouse down
	'		1 -> Mouse up
	'		2 -> Mouse move
	Select Action 
		Case 0
			'Start Koordinaten
			iStartX = X
			iStartY = Y
			
			PathPointIndex = 1
			
			For i = 0 To PathPoints.Length - 1 Step 1
				PathPoints(i) = 0
			Next 
			
			PathPoints(6) = X
			PathPoints(7) = Y
		Case 2
			PathPointIndex = PathPointIndex + 1
			
			'Punkte Sammlung während des zeichnens 
			PathPoints(0) = PathPoints(2)
			PathPoints(1) = PathPoints(3)
			PathPoints(2) = PathPoints(4)
			PathPoints(3) = PathPoints(5)
			PathPoints(4) = PathPoints(6)
			PathPoints(5) = PathPoints(7)
			PathPoints(6) = X
			PathPoints(7) = Y
			
			'Aufruf der Kurvenberechnung erfolgt erst nach dem Punkte erfasst wurden
			If PathPoints(0) > 0 Then
				CalculatMissingPoints(True)
			End If
			
		Case 1
			If PathPointIndex < 4 Then
				CalculatMissingPoints(False)
			End If
			
			For i = 0 To PathPoints.Length - 1 Step 1 
				PathPoints(i) = 0
			Next
	End Select
	
End Sub

Sub Clear(SD As SignatureData)
	SD.Canvas.DrawColor(Colors.White)
	SD.Panel.Invalidate
	s = 0
End Sub

'Unterschrift in String wandeln
Sub SignatureToString
	
End Sub

'Berechnen der fehlenden Punkten 
'aus 4 erfassten Punkten erfolgt die Kurvenberechnung zur Ermittlung der fehlenden Koordinaten
Sub CalculatMissingPoints(bCalc As Boolean)
	
End Sub
