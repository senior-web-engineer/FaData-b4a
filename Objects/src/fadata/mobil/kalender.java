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

public class kalender extends Activity implements B4AActivity{
	public static kalender mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "fadata.mobil", "fadata.mobil.kalender");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (kalender).");
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
		activityBA = new BA(this, layout, processBA, "fadata.mobil", "fadata.mobil.kalender");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "fadata.mobil.kalender", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (kalender) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (kalender) Resume **");
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
		return kalender.class;
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
            BA.LogInfo("** Activity (kalender) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (kalender) Pause event (activity is not paused). **");
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
            kalender mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (kalender) Resume **");
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
public static long _zeiteinheit = 0L;
public static long _firstdayofmonth = 0L;
public static long _lastdayofmonth = 0L;
public static long _dayselected = 0L;
public static String[] _day = null;
public static String[] _month = null;
public static int _firstdayofweek = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pankalender = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label10 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _nextmonth = null;
public anywheresoftware.b4a.objects.ButtonWrapper _prevmonth = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btabbruch = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btaktuellermonat = null;
public fadata.mobil.main _main = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.dbutils _dbutils = null;
public fadata.mobil.signaturecapture _signaturecapture = null;
public fadata.mobil.readwriteini _readwriteini = null;
public fadata.mobil.sonstigetaetigkeiten _sonstigetaetigkeiten = null;
public fadata.mobil.schueler _schueler = null;
public fadata.mobil.begleitfahrzeuge _begleitfahrzeuge = null;
public fadata.mobil.fahrlehrer _fahrlehrer = null;
public fadata.mobil.fahrtbezeichnung _fahrtbezeichnung = null;
public fadata.mobil.kfz _kfz = null;
public fadata.mobil.klassen _klassen = null;
public fadata.mobil.pruefergebnis _pruefergebnis = null;
public fadata.mobil.schuelerklasse _schuelerklasse = null;
public fadata.mobil.treffpunkt _treffpunkt = null;
public fadata.mobil.zahlungenfuer _zahlungenfuer = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 36;BA.debugLine="FirstDayOfWeek = 1";
_firstdayofweek = (int) (1);
 //BA.debugLineNum = 38;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 40;BA.debugLine="Day(0) = \"So\"";
_day[(int) (0)] = "So";
 //BA.debugLineNum = 41;BA.debugLine="Day(1) = \"Mo\"";
_day[(int) (1)] = "Mo";
 //BA.debugLineNum = 42;BA.debugLine="Day(2) = \"Di\"";
_day[(int) (2)] = "Di";
 //BA.debugLineNum = 43;BA.debugLine="Day(3) = \"Mi\"";
_day[(int) (3)] = "Mi";
 //BA.debugLineNum = 44;BA.debugLine="Day(4) = \"Do\"";
_day[(int) (4)] = "Do";
 //BA.debugLineNum = 45;BA.debugLine="Day(5) = \"Fr\"";
_day[(int) (5)] = "Fr";
 //BA.debugLineNum = 46;BA.debugLine="Day(6) = \"Sa\"";
_day[(int) (6)] = "Sa";
 //BA.debugLineNum = 47;BA.debugLine="Month(0) = \"Januar\"";
_month[(int) (0)] = "Januar";
 //BA.debugLineNum = 48;BA.debugLine="Month(1) = \"Februar\"";
_month[(int) (1)] = "Februar";
 //BA.debugLineNum = 49;BA.debugLine="Month(2) = \"März\"";
_month[(int) (2)] = "März";
 //BA.debugLineNum = 50;BA.debugLine="Month(3) = \"April\"";
_month[(int) (3)] = "April";
 //BA.debugLineNum = 51;BA.debugLine="Month(4) = \"Mai\"";
_month[(int) (4)] = "Mai";
 //BA.debugLineNum = 52;BA.debugLine="Month(5) = \"Juni\"";
_month[(int) (5)] = "Juni";
 //BA.debugLineNum = 53;BA.debugLine="Month(6) = \"Juli\"";
_month[(int) (6)] = "Juli";
 //BA.debugLineNum = 54;BA.debugLine="Month(7) = \"August\"";
_month[(int) (7)] = "August";
 //BA.debugLineNum = 55;BA.debugLine="Month(8) = \"September\"";
_month[(int) (8)] = "September";
 //BA.debugLineNum = 56;BA.debugLine="Month(9) = \"Oktober\"";
_month[(int) (9)] = "Oktober";
 //BA.debugLineNum = 57;BA.debugLine="Month(10) = \"November\"";
_month[(int) (10)] = "November";
 //BA.debugLineNum = 58;BA.debugLine="Month(11) = \"Dezember\"";
_month[(int) (11)] = "Dezember";
 };
 //BA.debugLineNum = 66;BA.debugLine="DaySelected = 0";
_dayselected = (long) (0);
 //BA.debugLineNum = 67;BA.debugLine="ZeitEinheit = DateTime.Now";
_zeiteinheit = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 68;BA.debugLine="Activity.LoadLayout(\"Kalender\")";
mostCurrent._activity.LoadLayout("Kalender",mostCurrent.activityBA);
 //BA.debugLineNum = 69;BA.debugLine="Activity.Title = \"Tagesauswahl\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("Tagesauswahl"));
 //BA.debugLineNum = 73;BA.debugLine="If FirstDayOfWeek = 1 Then";
if (_firstdayofweek==1) { 
 //BA.debugLineNum = 74;BA.debugLine="Label4.Text = Day(1)";
mostCurrent._label4.setText(BA.ObjectToCharSequence(_day[(int) (1)]));
 //BA.debugLineNum = 75;BA.debugLine="Label5.Text = Day(2)";
mostCurrent._label5.setText(BA.ObjectToCharSequence(_day[(int) (2)]));
 //BA.debugLineNum = 76;BA.debugLine="Label6.Text = Day(3)";
mostCurrent._label6.setText(BA.ObjectToCharSequence(_day[(int) (3)]));
 //BA.debugLineNum = 77;BA.debugLine="Label7.Text = Day(4)";
mostCurrent._label7.setText(BA.ObjectToCharSequence(_day[(int) (4)]));
 //BA.debugLineNum = 78;BA.debugLine="Label8.Text = Day(5)";
mostCurrent._label8.setText(BA.ObjectToCharSequence(_day[(int) (5)]));
 //BA.debugLineNum = 79;BA.debugLine="Label9.Text = Day(6)";
mostCurrent._label9.setText(BA.ObjectToCharSequence(_day[(int) (6)]));
 //BA.debugLineNum = 80;BA.debugLine="Label10.Text = Day(0)";
mostCurrent._label10.setText(BA.ObjectToCharSequence(_day[(int) (0)]));
 }else {
 //BA.debugLineNum = 82;BA.debugLine="Label4.Text = Day(0)";
mostCurrent._label4.setText(BA.ObjectToCharSequence(_day[(int) (0)]));
 //BA.debugLineNum = 83;BA.debugLine="Label5.Text = Day(1)";
mostCurrent._label5.setText(BA.ObjectToCharSequence(_day[(int) (1)]));
 //BA.debugLineNum = 84;BA.debugLine="Label6.Text = Day(2)";
mostCurrent._label6.setText(BA.ObjectToCharSequence(_day[(int) (2)]));
 //BA.debugLineNum = 85;BA.debugLine="Label7.Text = Day(3)";
mostCurrent._label7.setText(BA.ObjectToCharSequence(_day[(int) (3)]));
 //BA.debugLineNum = 86;BA.debugLine="Label8.Text = Day(4)";
mostCurrent._label8.setText(BA.ObjectToCharSequence(_day[(int) (4)]));
 //BA.debugLineNum = 87;BA.debugLine="Label9.Text = Day(5)";
mostCurrent._label9.setText(BA.ObjectToCharSequence(_day[(int) (5)]));
 //BA.debugLineNum = 88;BA.debugLine="Label10.Text = Day(6)";
mostCurrent._label10.setText(BA.ObjectToCharSequence(_day[(int) (6)]));
 };
 //BA.debugLineNum = 118;BA.debugLine="CreateCalendar";
_createcalendar();
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 125;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _btabbruch_click() throws Exception{
 //BA.debugLineNum = 265;BA.debugLine="Sub btAbbruch_Click";
 //BA.debugLineNum = 267;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 268;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 269;BA.debugLine="End Sub";
return "";
}
public static String  _btaktuellermonat_click() throws Exception{
 //BA.debugLineNum = 258;BA.debugLine="Sub btAktuellerMonat_Click";
 //BA.debugLineNum = 260;BA.debugLine="RemovePanelViews";
_removepanelviews();
 //BA.debugLineNum = 261;BA.debugLine="ZeitEinheit = DateTime.Now";
_zeiteinheit = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 262;BA.debugLine="CreateCalendar";
_createcalendar();
 //BA.debugLineNum = 263;BA.debugLine="End Sub";
return "";
}
public static String  _buttonpress_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 235;BA.debugLine="Sub ButtonPress_Click";
 //BA.debugLineNum = 237;BA.debugLine="Dim b As Button";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 238;BA.debugLine="b = Sender";
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 239;BA.debugLine="DaySelected = b.Tag";
_dayselected = BA.ObjectToLongNumber(_b.getTag());
 //BA.debugLineNum = 240;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 241;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static String  _createcalendar() throws Exception{
int _i = 0;
int _offsetx = 0;
int _offsety = 0;
long _tempday = 0L;
long _startdate = 0L;
long _enddate = 0L;
long _bday = 0L;
long _today = 0L;
anywheresoftware.b4a.keywords.LayoutValues _lv = null;
anywheresoftware.b4a.objects.ButtonWrapper _bn = null;
 //BA.debugLineNum = 129;BA.debugLine="Sub CreateCalendar";
 //BA.debugLineNum = 130;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 131;BA.debugLine="Dim OffsetX As Int";
_offsetx = 0;
 //BA.debugLineNum = 132;BA.debugLine="Dim OffsetY As Int";
_offsety = 0;
 //BA.debugLineNum = 133;BA.debugLine="Dim TempDay As Long";
_tempday = 0L;
 //BA.debugLineNum = 134;BA.debugLine="Dim StartDate As Long";
_startdate = 0L;
 //BA.debugLineNum = 135;BA.debugLine="Dim EndDate As Long";
_enddate = 0L;
 //BA.debugLineNum = 136;BA.debugLine="Dim bDay As Long";
_bday = 0L;
 //BA.debugLineNum = 137;BA.debugLine="Dim Today As Long";
_today = 0L;
 //BA.debugLineNum = 138;BA.debugLine="Dim lv As LayoutValues";
_lv = new anywheresoftware.b4a.keywords.LayoutValues();
 //BA.debugLineNum = 139;BA.debugLine="lv = GetDeviceLayoutValues";
_lv = anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA);
 //BA.debugLineNum = 141;BA.debugLine="Today = DateTime.Now";
_today = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 142;BA.debugLine="DateTime.DateFormat=\"ddMMyyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("ddMMyyyy");
 //BA.debugLineNum = 144;BA.debugLine="FirstDayOfMonth = DateTime.DateParse(\"01\" & DateT";
_firstdayofmonth = anywheresoftware.b4a.keywords.Common.DateTime.DateParse("01"+anywheresoftware.b4a.keywords.Common.DateTime.Date(_zeiteinheit).substring((int) (2)));
 //BA.debugLineNum = 146;BA.debugLine="TempDay = DateTime.Add(ZeitEinheit,0,1,0)";
_tempday = anywheresoftware.b4a.keywords.Common.DateTime.Add(_zeiteinheit,(int) (0),(int) (1),(int) (0));
 //BA.debugLineNum = 147;BA.debugLine="TempDay = DateTime.DateParse(\"01\" & DateTime.Date";
_tempday = anywheresoftware.b4a.keywords.Common.DateTime.DateParse("01"+anywheresoftware.b4a.keywords.Common.DateTime.Date(_tempday).substring((int) (2)));
 //BA.debugLineNum = 148;BA.debugLine="LastDayOfMonth = DateTime.Add(TempDay,0,0,-1)";
_lastdayofmonth = anywheresoftware.b4a.keywords.Common.DateTime.Add(_tempday,(int) (0),(int) (0),(int) (-1));
 //BA.debugLineNum = 150;BA.debugLine="Label1.Text = Month(DateTime.GetMonth(ZeitEinheit";
mostCurrent._label1.setText(BA.ObjectToCharSequence(_month[(int) (anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_zeiteinheit)-1)]+"  "+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetYear(_zeiteinheit))));
 //BA.debugLineNum = 151;BA.debugLine="OffsetX = panKalender.Left";
_offsetx = mostCurrent._pankalender.getLeft();
 //BA.debugLineNum = 152;BA.debugLine="OffsetY = Label4.Top + Label4.Height + 5";
_offsety = (int) (mostCurrent._label4.getTop()+mostCurrent._label4.getHeight()+5);
 //BA.debugLineNum = 155;BA.debugLine="If FirstDayOfWeek = 1 Then";
if (_firstdayofweek==1) { 
 //BA.debugLineNum = 156;BA.debugLine="If (DateTime.GetDayOfWeek(FirstDayOfMonth) - 1)";
if ((anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_firstdayofmonth)-1)==0) { 
 //BA.debugLineNum = 157;BA.debugLine="StartDate = DateTime.Add(FirstDayOfMonth,0,0,-6";
_startdate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_firstdayofmonth,(int) (0),(int) (0),(int) (-6));
 }else {
 //BA.debugLineNum = 159;BA.debugLine="StartDate = DateTime.Add(FirstDayOfMonth,0,0, 0";
_startdate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_firstdayofmonth,(int) (0),(int) (0),(int) (0-(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_firstdayofmonth)-2)));
 };
 }else {
 //BA.debugLineNum = 162;BA.debugLine="StartDate = DateTime.Add(FirstDayOfMonth,0,0, 0-";
_startdate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_firstdayofmonth,(int) (0),(int) (0),(int) (0-(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_firstdayofmonth)-1)));
 };
 //BA.debugLineNum = 165;BA.debugLine="bDay = StartDate";
_bday = _startdate;
 //BA.debugLineNum = 168;BA.debugLine="If FirstDayOfWeek = 1 Then";
if (_firstdayofweek==1) { 
 //BA.debugLineNum = 169;BA.debugLine="If (DateTime.GetDayOfWeek(LastDayOfMonth) - 1) =";
if ((anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_lastdayofmonth)-1)==0) { 
 //BA.debugLineNum = 170;BA.debugLine="EndDate = LastDayOfMonth";
_enddate = _lastdayofmonth;
 }else {
 //BA.debugLineNum = 172;BA.debugLine="EndDate = DateTime.Add(LastDayOfMonth,0,0, 8 -";
_enddate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_lastdayofmonth,(int) (0),(int) (0),(int) (8-anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_lastdayofmonth)));
 };
 }else {
 //BA.debugLineNum = 175;BA.debugLine="EndDate = DateTime.Add(LastDayOfMonth,0,0, 7 - D";
_enddate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_lastdayofmonth,(int) (0),(int) (0),(int) (7-anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_lastdayofmonth)));
 };
 //BA.debugLineNum = 181;BA.debugLine="For i = 1 To 42";
{
final int step39 = 1;
final int limit39 = (int) (42);
_i = (int) (1) ;
for (;_i <= limit39 ;_i = _i + step39 ) {
 //BA.debugLineNum = 182;BA.debugLine="If bDay <= EndDate Then";
if (_bday<=_enddate) { 
 //BA.debugLineNum = 183;BA.debugLine="Dim bn As Button";
_bn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 184;BA.debugLine="bn.Initialize(\"ButtonPress\")";
_bn.Initialize(mostCurrent.activityBA,"ButtonPress");
 //BA.debugLineNum = 185;BA.debugLine="bn.Text = DateTime.GetDayOfMonth(bDay)";
_bn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(_bday)));
 //BA.debugLineNum = 186;BA.debugLine="bn.Color = Colors.LightGray";
_bn.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 187;BA.debugLine="bn.TextColor = Colors.Black";
_bn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 188;BA.debugLine="bn.TextSize = 11";
_bn.setTextSize((float) (11));
 //BA.debugLineNum = 190;BA.debugLine="If DateTime.GetMonth(bDay) <> DateTime.GetMonth";
if (anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_bday)!=anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_zeiteinheit)) { 
 //BA.debugLineNum = 191;BA.debugLine="bn.Enabled = False";
_bn.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 192;BA.debugLine="bn.Tag = 0";
_bn.setTag((Object)(0));
 }else {
 //BA.debugLineNum = 194;BA.debugLine="bn.Enabled = True";
_bn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 195;BA.debugLine="bn.Tag = bDay";
_bn.setTag((Object)(_bday));
 };
 //BA.debugLineNum = 198;BA.debugLine="If DateTime.Date(bDay) = DateTime.Date(Today)";
if ((anywheresoftware.b4a.keywords.Common.DateTime.Date(_bday)).equals(anywheresoftware.b4a.keywords.Common.DateTime.Date(_today))) { 
 //BA.debugLineNum = 199;BA.debugLine="bn.TextColor = Colors.Red";
_bn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 200;BA.debugLine="bn.Typeface = Typeface.DEFAULT_BOLD";
_bn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 };
 //BA.debugLineNum = 204;BA.debugLine="panKalender.AddView(bn, OffsetX, OffsetY, Label";
mostCurrent._pankalender.AddView((android.view.View)(_bn.getObject()),_offsetx,_offsety,mostCurrent._label4.getWidth(),mostCurrent._label4.getHeight());
 //BA.debugLineNum = 205;BA.debugLine="OffsetX = (OffsetX + Label4.Width) + ((Label10.";
_offsetx = (int) ((_offsetx+mostCurrent._label4.getWidth())+((mostCurrent._label10.getLeft()-mostCurrent._label4.getLeft()-(6*mostCurrent._label4.getWidth()))/(double)6));
 //BA.debugLineNum = 207;BA.debugLine="If OffsetX > (panKalender.Width - Label4.Width)";
if (_offsetx>(mostCurrent._pankalender.getWidth()-mostCurrent._label4.getWidth())) { 
 //BA.debugLineNum = 208;BA.debugLine="OffsetX = panKalender.Left";
_offsetx = mostCurrent._pankalender.getLeft();
 //BA.debugLineNum = 209;BA.debugLine="OffsetY = OffsetY + Label4.Height + ((Label10.";
_offsety = (int) (_offsety+mostCurrent._label4.getHeight()+((mostCurrent._label10.getLeft()-mostCurrent._label4.getLeft()-(6*mostCurrent._label4.getWidth()))/(double)6));
 };
 //BA.debugLineNum = 211;BA.debugLine="bDay = DateTime.Add(bDay,0,0,1)";
_bday = anywheresoftware.b4a.keywords.Common.DateTime.Add(_bday,(int) (0),(int) (0),(int) (1));
 };
 }
};
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim panKalender As Panel";
mostCurrent._pankalender = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim Label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim NextMonth,PrevMonth, btAbbruch, btAktuellerMo";
mostCurrent._nextmonth = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._prevmonth = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btabbruch = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btaktuellermonat = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _nextmonth_click() throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub NextMonth_Click";
 //BA.debugLineNum = 253;BA.debugLine="RemovePanelViews";
_removepanelviews();
 //BA.debugLineNum = 254;BA.debugLine="ZeitEinheit = DateTime.Add(FirstDayOfMonth,0,1,0)";
_zeiteinheit = anywheresoftware.b4a.keywords.Common.DateTime.Add(_firstdayofmonth,(int) (0),(int) (1),(int) (0));
 //BA.debugLineNum = 255;BA.debugLine="CreateCalendar";
_createcalendar();
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _prevmonth_click() throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Sub PrevMonth_Click";
 //BA.debugLineNum = 246;BA.debugLine="RemovePanelViews";
_removepanelviews();
 //BA.debugLineNum = 247;BA.debugLine="ZeitEinheit = DateTime.Add(FirstDayOfMonth,0,-1,0";
_zeiteinheit = anywheresoftware.b4a.keywords.Common.DateTime.Add(_firstdayofmonth,(int) (0),(int) (-1),(int) (0));
 //BA.debugLineNum = 248;BA.debugLine="CreateCalendar";
_createcalendar();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim ZeitEinheit As Long";
_zeiteinheit = 0L;
 //BA.debugLineNum = 10;BA.debugLine="Dim FirstDayOfMonth, LastDayOfMonth, DaySelected";
_firstdayofmonth = 0L;
_lastdayofmonth = 0L;
_dayselected = 0L;
 //BA.debugLineNum = 11;BA.debugLine="Dim Day(7) As String";
_day = new String[(int) (7)];
java.util.Arrays.fill(_day,"");
 //BA.debugLineNum = 12;BA.debugLine="Dim Month(12) As String";
_month = new String[(int) (12)];
java.util.Arrays.fill(_month,"");
 //BA.debugLineNum = 13;BA.debugLine="Dim FirstDayOfWeek As Int";
_firstdayofweek = 0;
 //BA.debugLineNum = 15;BA.debugLine="DaySelected = 0";
_dayselected = (long) (0);
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _removepanelviews() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
long _ii = 0L;
 //BA.debugLineNum = 217;BA.debugLine="Sub RemovePanelViews";
 //BA.debugLineNum = 218;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 219;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 220;BA.debugLine="Dim ii As Long";
_ii = 0L;
 //BA.debugLineNum = 222;BA.debugLine="For i=panKalender.NumberOfViews-1 To 0 Step -1";
{
final int step4 = -1;
final int limit4 = (int) (0);
_i = (int) (mostCurrent._pankalender.getNumberOfViews()-1) ;
for (;_i >= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 223;BA.debugLine="v = panKalender.GetView(i)";
_v = mostCurrent._pankalender.GetView(_i);
 //BA.debugLineNum = 224;BA.debugLine="Try";
try { //BA.debugLineNum = 225;BA.debugLine="ii = v.Tag";
_ii = BA.ObjectToLongNumber(_v.getTag());
 //BA.debugLineNum = 226;BA.debugLine="If ii >= 0 Then";
if (_ii>=0) { 
 //BA.debugLineNum = 227;BA.debugLine="panKalender.RemoveViewAt(i)";
mostCurrent._pankalender.RemoveViewAt(_i);
 };
 } 
       catch (Exception e12) {
			processBA.setLastException(e12); //BA.debugLineNum = 230;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("023986189",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 }
};
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
}
