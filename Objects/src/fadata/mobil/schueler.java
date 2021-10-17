package fadata.mobil;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class schueler extends Activity implements B4AActivity{
	public static schueler mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "fadata.mobil", "fadata.mobil.schueler");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (schueler).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "fadata.mobil", "fadata.mobil.schueler");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "fadata.mobil.schueler", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (schueler) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (schueler) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return schueler.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (schueler) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (schueler) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            schueler mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (schueler) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static int _schuelerselected = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _lstschueler = null;
public anywheresoftware.b4a.objects.collections.List _expandablelist = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public static String _sabc = "";
public static int _ianzahl = 0;
public fadata.mobil.main _main = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.dbutils _dbutils = null;
public fadata.mobil.signaturecapture _signaturecapture = null;
public fadata.mobil.readwriteini _readwriteini = null;
public fadata.mobil.sonstigetaetigkeiten _sonstigetaetigkeiten = null;
public fadata.mobil.begleitfahrzeuge _begleitfahrzeuge = null;
public fadata.mobil.fahrlehrer _fahrlehrer = null;
public fadata.mobil.fahrtbezeichnung _fahrtbezeichnung = null;
public fadata.mobil.kalender _kalender = null;
public fadata.mobil.kfz _kfz = null;
public fadata.mobil.klassen _klassen = null;
public fadata.mobil.pruefergebnis _pruefergebnis = null;
public fadata.mobil.schuelerklasse _schuelerklasse = null;
public fadata.mobil.treffpunkt _treffpunkt = null;
public fadata.mobil.zahlungenfuer _zahlungenfuer = null;
public static class _explistviewitem{
public boolean IsInitialized;
public boolean IsParent;
public boolean Expanded;
public int id;
public int childto;
public String Sort;
public String Schueler;
public int SchuelerID;
public void Initialize() {
IsInitialized = true;
IsParent = false;
Expanded = false;
id = 0;
childto = 0;
Sort = "";
Schueler = "";
SchuelerID = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _i = 0;
fadata.mobil.schueler._explistviewitem _parentitem = null;
int _j = 0;
fadata.mobil.schueler._explistviewitem _item = null;
 //BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"List_Schueler\")";
mostCurrent._activity.LoadLayout("List_Schueler",mostCurrent.activityBA);
 //BA.debugLineNum = 31;BA.debugLine="Activity.Title = \"Schüler Auswahl\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("Schüler Auswahl"));
 //BA.debugLineNum = 34;BA.debugLine="SV.Height = 100%y";
mostCurrent._sv.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 35;BA.debugLine="SV.Width = 100%x";
mostCurrent._sv.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 42;BA.debugLine="Helper.FillSchuelerList(lstSchueler)";
mostCurrent._helper._fillschuelerlist /*String*/ (mostCurrent.activityBA,mostCurrent._lstschueler);
 //BA.debugLineNum = 44;BA.debugLine="ExpandableList.Initialize";
mostCurrent._expandablelist.Initialize();
 //BA.debugLineNum = 45;BA.debugLine="For i = 0 To 26";
{
final int step7 = 1;
final int limit7 = (int) (26);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 46;BA.debugLine="Dim ParentItem As ExpListViewItem";
_parentitem = new fadata.mobil.schueler._explistviewitem();
 //BA.debugLineNum = 47;BA.debugLine="ParentItem.Initialize";
_parentitem.Initialize();
 //BA.debugLineNum = 48;BA.debugLine="ParentItem.Expanded = False";
_parentitem.Expanded /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 49;BA.debugLine="ParentItem.id = i";
_parentitem.id /*int*/  = _i;
 //BA.debugLineNum = 50;BA.debugLine="ParentItem.childto = 0";
_parentitem.childto /*int*/  = (int) (0);
 //BA.debugLineNum = 51;BA.debugLine="If i <> 0 Then";
if (_i!=0) { 
 //BA.debugLineNum = 52;BA.debugLine="ParentItem.IsParent = True";
_parentitem.IsParent /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 53;BA.debugLine="If i = 1 Then";
if (_i==1) { 
 //BA.debugLineNum = 54;BA.debugLine="sABC = \"A\"";
mostCurrent._sabc = "A";
 }else {
 //BA.debugLineNum = 56;BA.debugLine="sABC = Chr(Asc(sABC) + 1)";
mostCurrent._sabc = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (anywheresoftware.b4a.keywords.Common.Asc(BA.ObjectToChar(mostCurrent._sabc))+1)));
 };
 //BA.debugLineNum = 58;BA.debugLine="ParentItem.Sort = sABC";
_parentitem.Sort /*String*/  = mostCurrent._sabc;
 };
 //BA.debugLineNum = 60;BA.debugLine="ExpandableList.Add(ParentItem)";
mostCurrent._expandablelist.Add((Object)(_parentitem));
 //BA.debugLineNum = 62;BA.debugLine="iAnzahl = Helper.FillSchuelerListViewItems(sABC)";
_ianzahl = mostCurrent._helper._fillschuelerlistviewitems /*int*/ (mostCurrent.activityBA,mostCurrent._sabc);
 //BA.debugLineNum = 64;BA.debugLine="For j = 0 To iAnzahl - 1";
{
final int step24 = 1;
final int limit24 = (int) (_ianzahl-1);
_j = (int) (0) ;
for (;_j <= limit24 ;_j = _j + step24 ) {
 //BA.debugLineNum = 65;BA.debugLine="Dim item As ExpListViewItem";
_item = new fadata.mobil.schueler._explistviewitem();
 //BA.debugLineNum = 66;BA.debugLine="item.Initialize";
_item.Initialize();
 //BA.debugLineNum = 67;BA.debugLine="item.IsParent = False";
_item.IsParent /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 68;BA.debugLine="item.Expanded = False";
_item.Expanded /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 69;BA.debugLine="item.id = i * 10 + j";
_item.id /*int*/  = (int) (_i*10+_j);
 //BA.debugLineNum = 70;BA.debugLine="item.childto = ParentItem.id";
_item.childto /*int*/  = _parentitem.id /*int*/ ;
 //BA.debugLineNum = 71;BA.debugLine="item.Sort = sABC";
_item.Sort /*String*/  = mostCurrent._sabc;
 //BA.debugLineNum = 72;BA.debugLine="ExpandableList.Add(item)";
mostCurrent._expandablelist.Add((Object)(_item));
 }
};
 }
};
 //BA.debugLineNum = 76;BA.debugLine="DrawListView";
_drawlistview();
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _constructpanel(fadata.mobil.schueler._explistviewitem _item,anywheresoftware.b4a.objects.PanelWrapper _p) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _l = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _c = null;
 //BA.debugLineNum = 156;BA.debugLine="Sub ConstructPanel(item As ExpListViewItem, p As P";
 //BA.debugLineNum = 157;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 158;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 160;BA.debugLine="l.Initialize(\"\")";
_l.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 161;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 163;BA.debugLine="p.AddView(l, 48dip, 0dip, 100%y, 40dip)";
_p.AddView((android.view.View)(_l.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (48)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 164;BA.debugLine="p.AddView(img, 4dip, 2dip, 32dip, 32dip)";
_p.AddView((android.view.View)(_img.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)));
 //BA.debugLineNum = 165;BA.debugLine="l.Gravity = Gravity.CENTER_VERTICAL";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 166;BA.debugLine="l.TextSize = 20";
_l.setTextSize((float) (20));
 //BA.debugLineNum = 167;BA.debugLine="l.TextColor = Colors.Black";
_l.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 169;BA.debugLine="Dim c As Canvas";
_c = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 170;BA.debugLine="c.Initialize(p)";
_c.Initialize((android.view.View)(_p.getObject()));
 //BA.debugLineNum = 171;BA.debugLine="c.DrawLine(0, p.Height - 2dip, 100%x, p.Height -";
_c.DrawLine((float) (0),(float) (_p.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)),(float) (_p.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),anywheresoftware.b4a.keywords.Common.Colors.Black,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 173;BA.debugLine="If item.IsParent Then";
if (_item.IsParent /*boolean*/ ) { 
 //BA.debugLineNum = 174;BA.debugLine="l.Text = item.Sort";
_l.setText(BA.ObjectToCharSequence(_item.Sort /*String*/ ));
 }else {
 //BA.debugLineNum = 176;BA.debugLine="If (item.childto * 10 = item.id) Then";
if ((_item.childto /*int*/ *10==_item.id /*int*/ )) { 
 //BA.debugLineNum = 177;BA.debugLine="sABC = item.Sort";
mostCurrent._sabc = _item.Sort /*String*/ ;
 //BA.debugLineNum = 178;BA.debugLine="Main.aSchueler = Helper.FillSchuelerArray(sABC)";
mostCurrent._main._aschueler /*String[][]*/  = mostCurrent._helper._fillschuelerarray /*String[][]*/ (mostCurrent.activityBA,mostCurrent._sabc);
 //BA.debugLineNum = 179;BA.debugLine="iAnzahl = 0";
_ianzahl = (int) (0);
 };
 //BA.debugLineNum = 181;BA.debugLine="If Main.aSchueler.Length <> 0 Then";
if (mostCurrent._main._aschueler /*String[][]*/ .length!=0) { 
 //BA.debugLineNum = 182;BA.debugLine="l.Text = Main.aSchueler(iAnzahl, 0)";
_l.setText(BA.ObjectToCharSequence(mostCurrent._main._aschueler /*String[][]*/ [_ianzahl][(int) (0)]));
 //BA.debugLineNum = 183;BA.debugLine="item.Schueler = Main.aSchueler(iAnzahl, 0)";
_item.Schueler /*String*/  = mostCurrent._main._aschueler /*String[][]*/ [_ianzahl][(int) (0)];
 //BA.debugLineNum = 184;BA.debugLine="item.SchuelerID = Main.aSchueler(iAnzahl, 1)";
_item.SchuelerID /*int*/  = (int)(Double.parseDouble(mostCurrent._main._aschueler /*String[][]*/ [_ianzahl][(int) (1)]));
 //BA.debugLineNum = 185;BA.debugLine="iAnzahl = iAnzahl + 1";
_ianzahl = (int) (_ianzahl+1);
 };
 };
 //BA.debugLineNum = 190;BA.debugLine="If item.IsParent = True And item.Expanded = True";
if (_item.IsParent /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True && _item.Expanded /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 191;BA.debugLine="img.Bitmap = LoadBitmap(File.DirAssets, \"ic_menu";
_img.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_menu_more.png").getObject()));
 }else if(_item.IsParent /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 193;BA.debugLine="img.Bitmap = LoadBitmap(File.DirAssets, \"ic_menu";
_img.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_menu_less.png").getObject()));
 };
 //BA.debugLineNum = 195;BA.debugLine="p.Tag = item";
_p.setTag((Object)(_item));
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _drawlistview() throws Exception{
int _i = 0;
int _itemheight = 0;
int _childoffset = 0;
int _pheight = 0;
fadata.mobil.schueler._explistviewitem _item = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
int _j = 0;
fadata.mobil.schueler._explistviewitem _childitem = null;
 //BA.debugLineNum = 94;BA.debugLine="Sub DrawListView";
 //BA.debugLineNum = 96;BA.debugLine="For i = SV.Panel.NumberOfViews - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (mostCurrent._sv.getPanel().getNumberOfViews()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 97;BA.debugLine="SV.Panel.RemoveViewAt(i)";
mostCurrent._sv.getPanel().RemoveViewAt(_i);
 }
};
 //BA.debugLineNum = 100;BA.debugLine="Dim ItemHeight As Int";
_itemheight = 0;
 //BA.debugLineNum = 101;BA.debugLine="ItemHeight = 40dip";
_itemheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 102;BA.debugLine="Dim ChildOffset As Int";
_childoffset = 0;
 //BA.debugLineNum = 103;BA.debugLine="ChildOffset = 30dip";
_childoffset = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 105;BA.debugLine="Dim pHeight As Int";
_pheight = 0;
 //BA.debugLineNum = 106;BA.debugLine="pHeight = 0";
_pheight = (int) (0);
 //BA.debugLineNum = 108;BA.debugLine="For i = 0 To ExpandableList.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (mostCurrent._expandablelist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 109;BA.debugLine="Dim item As ExpListViewItem";
_item = new fadata.mobil.schueler._explistviewitem();
 //BA.debugLineNum = 110;BA.debugLine="item = ExpandableList.Get(i)";
_item = (fadata.mobil.schueler._explistviewitem)(mostCurrent._expandablelist.Get(_i));
 //BA.debugLineNum = 111;BA.debugLine="If item.IsParent = True Then";
if (_item.IsParent /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 112;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 113;BA.debugLine="p.Initialize(\"pnl\")";
_p.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 114;BA.debugLine="SV.Panel.AddView(p, 0, pHeight, 100%x, ItemHeig";
mostCurrent._sv.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_pheight,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_itemheight);
 //BA.debugLineNum = 115;BA.debugLine="ConstructPanel(item, p)";
_constructpanel(_item,_p);
 //BA.debugLineNum = 116;BA.debugLine="pHeight = pHeight + ItemHeight";
_pheight = (int) (_pheight+_itemheight);
 };
 //BA.debugLineNum = 118;BA.debugLine="If item.Expanded = True Then";
if (_item.Expanded /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 119;BA.debugLine="For j = 0 To ExpandableList.Size - 1";
{
final int step21 = 1;
final int limit21 = (int) (mostCurrent._expandablelist.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit21 ;_j = _j + step21 ) {
 //BA.debugLineNum = 120;BA.debugLine="Dim childitem As ExpListViewItem";
_childitem = new fadata.mobil.schueler._explistviewitem();
 //BA.debugLineNum = 121;BA.debugLine="childitem = ExpandableList.Get(j)";
_childitem = (fadata.mobil.schueler._explistviewitem)(mostCurrent._expandablelist.Get(_j));
 //BA.debugLineNum = 122;BA.debugLine="If childitem.childto = item.id Then";
if (_childitem.childto /*int*/ ==_item.id /*int*/ ) { 
 //BA.debugLineNum = 123;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 124;BA.debugLine="p.Initialize(\"pnl\")";
_p.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 125;BA.debugLine="SV.Panel.AddView(p, ChildOffset, pHeight, 100";
mostCurrent._sv.getPanel().AddView((android.view.View)(_p.getObject()),_childoffset,_pheight,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-_childoffset),_itemheight);
 //BA.debugLineNum = 126;BA.debugLine="ConstructPanel(childitem, p)";
_constructpanel(_childitem,_p);
 //BA.debugLineNum = 127;BA.debugLine="pHeight = pHeight + ItemHeight";
_pheight = (int) (_pheight+_itemheight);
 };
 }
};
 };
 }
};
 //BA.debugLineNum = 132;BA.debugLine="SV.Panel.Height = pHeight";
mostCurrent._sv.getPanel().setHeight(_pheight);
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim lstSchueler As ListView";
mostCurrent._lstschueler = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Type ExpListViewItem(IsParent As Boolean, Expande";
;
 //BA.debugLineNum = 21;BA.debugLine="Dim ExpandableList As List";
mostCurrent._expandablelist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 22;BA.debugLine="Dim SV As ScrollView";
mostCurrent._sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim sABC As String";
mostCurrent._sabc = "";
 //BA.debugLineNum = 24;BA.debugLine="Dim iAnzahl As Int";
_ianzahl = 0;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _lstschueler_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub lstSchueler_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 88;BA.debugLine="SchuelerSelected = Position";
_schuelerselected = _position;
 //BA.debugLineNum = 89;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 90;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _pnl_click() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
fadata.mobil.schueler._explistviewitem _item = null;
 //BA.debugLineNum = 136;BA.debugLine="Sub pnl_Click";
 //BA.debugLineNum = 137;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 138;BA.debugLine="v = Sender";
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 139;BA.debugLine="Dim item As ExpListViewItem";
_item = new fadata.mobil.schueler._explistviewitem();
 //BA.debugLineNum = 140;BA.debugLine="item = v.Tag";
_item = (fadata.mobil.schueler._explistviewitem)(_v.getTag());
 //BA.debugLineNum = 142;BA.debugLine="If item.IsParent = True Then";
if (_item.IsParent /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 143;BA.debugLine="item.Expanded = Not(item.Expanded)";
_item.Expanded /*boolean*/  = anywheresoftware.b4a.keywords.Common.Not(_item.Expanded /*boolean*/ );
 //BA.debugLineNum = 144;BA.debugLine="DrawListView";
_drawlistview();
 }else {
 //BA.debugLineNum = 146;BA.debugLine="Main.iAusgewaehlterSchuelrID = item.SchuelerID";
mostCurrent._main._iausgewaehlterschuelrid /*int*/  = _item.SchuelerID /*int*/ ;
 //BA.debugLineNum = 147;BA.debugLine="Main.sAusgewaehlterSchueler = item.Schueler";
mostCurrent._main._sausgewaehlterschueler /*String*/  = _item.Schueler /*String*/ ;
 //BA.debugLineNum = 148;BA.debugLine="SchuelerSelected = item.SchuelerID";
_schuelerselected = _item.SchuelerID /*int*/ ;
 //BA.debugLineNum = 150;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 151;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim SchuelerSelected As Int			'Rückgabe an das Ac";
_schuelerselected = 0;
 //BA.debugLineNum = 12;BA.debugLine="SchuelerSelected = 0";
_schuelerselected = (int) (0);
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
