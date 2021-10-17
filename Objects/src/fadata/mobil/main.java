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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "fadata.mobil", "fadata.mobil.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "fadata.mobil", "fadata.mobil.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "fadata.mobil.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static String _versionsnummer = "";
public static anywheresoftware.b4a.phone.PackageManagerWrapper _pm = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public static String _sourcefolder = "";
public static anywheresoftware.b4a.keywords.LayoutValues _lv = null;
public static String _sausgewaehlterschueler = "";
public static String[][] _aschueler = null;
public static String[][] _afahrtbezeichnung = null;
public static String[][] _asonstigetaetigkeiten = null;
public static String[][] _atreffpunkt = null;
public static String[][] _akfz = null;
public static int _iausgewaehlterschuelrid = 0;
public static boolean _bdatenvorhanden = false;
public static boolean _btreffpunktauswahl = false;
public static boolean _bfirsttime = false;
public static boolean _bzahlbearbeiten = false;
public static boolean _bzahldatum = false;
public static boolean[] _bisselected = null;
public static boolean _bsonsttaetigkeit = false;
public static boolean _balleklassen = false;
public static boolean _bkfzmanuel = false;
public static int _izahlungsposition = 0;
public static int _iausbildungbereich = 0;
public static String _sfahrlehrer = "";
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _termin = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _fahrstunden = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _stammdaten = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _zahlungen = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _lernkontrolle = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _config = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _info = null;
public anywheresoftware.b4a.objects.TabHostWrapper _thstart = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btkalender = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bttagzurueck = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bttagvor = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfahrlehrer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblschueler = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlfahrlehrer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlschueler = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfahrstd = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblanzeigefahrstd = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgesamtstd = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblanzeigegesamtstd = null;
public static int _iterminid = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _lstschueler = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstfahrlehrer = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstkfz = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lsttermine = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstschuelerklasse = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstklassen = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstfahrtbezeichnung = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lsttreffpunkt = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstbegleitfahrzeug = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungfuer = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstzahlungen = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstsonstigetaetigkeiten = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblabfahrt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasse = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblkfz = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfahrtbezeichnung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlklasse = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlklassealle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlkfz = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlfahrbezeichnung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltreffpunkt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstdplus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstdminus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblminplus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblminminus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmin1plus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmin1minus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheitplus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheitminus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbegleitfahrzeug = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblauswahlbegleitfahrzeug = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheit5plus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheit5minus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheit1plus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheit1minus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblanzschueler = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblanzdatum = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblabfahrtanzeige = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinheitanzeige = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpfeil = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsonder = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpruefungsergebnisse = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtauswahltreffpunkt = null;
public anywheresoftware.b4a.objects.PanelWrapper _panstart = null;
public anywheresoftware.b4a.objects.PanelWrapper _panunterschrift = null;
public anywheresoftware.b4a.objects.PanelWrapper _panpruefung = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btvormerken = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btabbruch = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btspeichern = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btunterschrift = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btpruefergebnisok = null;
public static String _sfahrtenbezeichnung = "";
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbnichterfassen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbnichtbestanden = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rbbestanden = null;
public anywheresoftware.b4a.objects.PanelWrapper _panstammdaten1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panstammdaten2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panklassen = null;
public anywheresoftware.b4a.objects.PanelWrapper _panklasse1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panklasse2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panklasse3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panklasse4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdaten1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdaten2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasse1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasse2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasse3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasse4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenort = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenanschrift = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatentelefon = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammtelefon = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenhandy = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammhandy = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenarbeitsstelle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammarbeitsstelle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatentelefonarbeit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammtelefonarbeit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenklassen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammklassen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenhatklasse = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammhatklasse = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenemail = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammemail = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenkontostand = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenantrageingereicht = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenantragzurueck = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatengeburtstag = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatennationalitaet = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenschulefiliale = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammdatenanmeldung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammkontostand = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammantrageingereicht = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammantragzurueck = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammgeburtstag = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammnationalitaet = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammanmeldung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstammschulefiliale = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnameklasse = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassegrundstoff = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassedoppelstunde = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassenbezeichnung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassespezifisch = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseuebungsfahrt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseuebunggrund = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseueberland = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseautobahn = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassenachtfaht = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseunterweisungfhrzg = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassetheorpruefung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassepraktischepruefung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseausbildungsstand = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigegrundstoff = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigedoppelstunde = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigebezeichnung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigespezifisch = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigeuebungsfahrt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigeuebunggrund = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigeueberland = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigeautobahn = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigenachtfaht = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigeunterweisungfhrzg = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigetheorpruefung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklasseanzeigepraktischepruefung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblklassedoppelstundetext = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstdje45min = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahldatum = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahltag = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlalle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlschueler = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlgesamtbetrag = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlzahlungfuertext = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlzahlungstext = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlauswahlzahlungenfuer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlzeilebearbeiten = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzahlspeichern = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtzahlbetrag = null;
public anywheresoftware.b4a.objects.PanelWrapper _panauswahl = null;
public anywheresoftware.b4a.objects.PanelWrapper _panzahlliste = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungschueler = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildung6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungplus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungminus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahl6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlx = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlminus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlplus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahlausrufe = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungzahldel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezeile10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildunglistezusatz10 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panausbildung = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernthemen = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstausbildunglernpunkte = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblschuelerbvf = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgrundstufe = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblausbildungbvf6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldaemmerung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblchecklistefahrvorb = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblheizunglueftung = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbetriebsverkehrssich = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderheinsteigen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkrad = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbspiegel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkopfstuetze = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsitz = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkradhaltung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpedale = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgurt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltwaehlhebel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzuendschloss = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoranlassen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahranhalte = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschaltuebg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch1_2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch2_3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhoch3_4 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter2_1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter4_1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrunter3_1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkuebung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbumkehren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenlaengs = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertsrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertslinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblrueckwaertsrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblvorwaertslinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaertsfahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinparkenquer = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertsrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertslinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqrueckwaertsrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbqvorwaertslinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrbremsung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrollenschalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsschalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsuebung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdegressiv = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzielbremsung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefahrsituation = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgefaelle = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanhalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanfahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckwaerts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsichern = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanhalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstanfahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrueckwaerts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstsichern = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstschalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtastgeschw = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienkontroll = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboertlichbesonder = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrbahnbenutzung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinordnen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmarkierungen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrstreifenwechsel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifueberholen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabbiegen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabrechts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmehrspurig = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradweg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsonderstreifen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstrassenbahn = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrasse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorfahrt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtsvorlinks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeil = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpolizeibeamte = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgruenpfeilschild = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwabstand = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsituationverkehrstn = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerueberweg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cboeffentlverkehrsm = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaelterebehinderte = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinbahnstrradfahrer = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkinder = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschulbus = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbradfahrermofa = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrsberuhigt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwierigeverkehrsf = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbengpass = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreisverkehr = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergangwarte = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkritischeverkehrss = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhauptverkehrszt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpartnerverhalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschwungnutzen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaengerschutzb = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbangepasstegeschw = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabstand = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulvorne = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulhinten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulseitlich = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeobachtspiegel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverkehrszeichen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkreuzungeinmuend = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkurven = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsteigungen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbulgefaelle = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cballeen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbueberholen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderesituat = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbliegenblsichern = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrenortsch = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfussgaenger = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwildtiere = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondereanford = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsgrenze = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cborientierung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbablenkung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahrtplanung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinfahrtab = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabfahrbahnwechsel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbgeschwindigkeit = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbababstand = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabvorne = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabhinten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabseitlich = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabueberholen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschilder = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorbeifahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrastparktank = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverhunfall = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdichterverkehr = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesondersituat = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbesonderanford = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbableistungsgrenze = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkonfliktsitua = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabablenkung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolle = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstell = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbenutzung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfernlicht = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverlassenbab = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeleuchtstrasse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbeleuchtstrasse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbparken = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesondersituat = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlechtewitterung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtiere = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbahnuebergaenge = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbunbelverkehrtn = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbdubesonderanfor = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblendung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbduorientierung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabschlussbesp = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbselbstfahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbinnerorts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbausserorts = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverantwfahren = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtestfpruef = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfakt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbandere = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwiederholung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbleistungsbew = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbreifen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinausschalten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktionpruefen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbstandlicht = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbnebelschluss = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbblinker = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbabblendlicht = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarnblicke = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhupe = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbsfernlicht = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbschlussleuchte = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremslicht = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkontrolllbenenn = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrueckstrahler = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbvorhandensein = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbeschaedigung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblenkschlentriegeln = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbprueflenkspiel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfunktbremse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbetriebsbremse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfeststellbremse = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbanlegengurt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrichtigsitz = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinstellrueckspiegel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinkopfstuetze = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeinlenkrad = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbedienenagg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheizung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbheckheizung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbehsonderaus = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cblueftung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbklimaanlage = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbenergienutzung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkeineunnverbr = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbrechtztabsch = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotorraum = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmotoroel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkuehlmittel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwaschm = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbtanken = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbremsen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbsicherungsmittel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwarndreieck = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbordwerkzeug = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbzusaetzlichaus = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbverbandskasten = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbaussenkontrolle = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbscheibenwischer = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkennzeichen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckspiegel = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbcheckbeleuchtung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbladungssicherung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbkenntlichmachung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbfahreschlwitt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittlueftung = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittscheiben = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbregen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwasserlachen = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwindsturm = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbmatchschnee = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbeis = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbwittbeleuchtung = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan0 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan5 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan8 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan9 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan10 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan11 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan12 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan13 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan14 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan15 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan16 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan17 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan18 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan19 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pan20 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etnotizen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstfahrlehrer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstanzeigefahrlehrer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstkfz = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstanzeigekfz = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstfahreinheit = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbegleitfahrzeugerfassen = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpruefungergebnis = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbleinstelltreffpunkt = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbvfkontrolle = null;
public anywheresoftware.b4a.objects.PanelWrapper _paneinstellauswahl = null;
public anywheresoftware.b4a.objects.AutoCompleteEditTextWrapper _txtneuertreffpunkt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txteinstanzeigefahreinheit = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bteinstellaktuelleliste = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bteinstellspeichern = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btappbeenden = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbegleitfahrzueg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbpruefungsergebnis = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbbvfkontrolle = null;
public static boolean[] _akonfig = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btunterschrfitspeichern = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btunterschriftloeschen = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btunterschriftabbruch = null;
public anywheresoftware.b4a.objects.PanelWrapper _panunterschriftfeld = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1 = null;
public fadata.mobil.signaturecapture._signaturedata _sigd = null;
public fadata.mobil.helper _helper = null;
public fadata.mobil.dbutils _dbutils = null;
public fadata.mobil.signaturecapture _signaturecapture = null;
public fadata.mobil.readwriteini _readwriteini = null;
public fadata.mobil.sonstigetaetigkeiten _sonstigetaetigkeiten = null;
public fadata.mobil.schueler _schueler = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (sonstigetaetigkeiten.mostCurrent != null);
vis = vis | (schueler.mostCurrent != null);
vis = vis | (begleitfahrzeuge.mostCurrent != null);
vis = vis | (fahrlehrer.mostCurrent != null);
vis = vis | (fahrtbezeichnung.mostCurrent != null);
vis = vis | (kalender.mostCurrent != null);
vis = vis | (kfz.mostCurrent != null);
vis = vis | (klassen.mostCurrent != null);
vis = vis | (pruefergebnis.mostCurrent != null);
vis = vis | (schuelerklasse.mostCurrent != null);
vis = vis | (treffpunkt.mostCurrent != null);
vis = vis | (zahlungenfuer.mostCurrent != null);
return vis;}
public static class _listviewdata{
public boolean IsInitialized;
public String FirstRow;
public String SecondRow;
public void Initialize() {
IsInitialized = true;
FirstRow = "";
SecondRow = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(fadata.mobil.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
fadata.mobil.main parent;
boolean _firsttime;
anywheresoftware.b4a.phone.Phone _p = null;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 165;BA.debugLine="If (FirstTime) Then";
if (true) break;

case 1:
//if
this.state = 26;
if ((_firsttime)) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 167;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 168;BA.debugLine="ToastMessageShow(\"SDK Version: = \" & p.SdkVersio";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("SDK Version: = "+BA.NumberToString(_p.getSdkVersion())),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 170;BA.debugLine="If p.SdkVersion < 23 Then";
if (true) break;

case 4:
//if
this.state = 9;
if (_p.getSdkVersion()<23) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 171;BA.debugLine="SourceFolder = File.DirDefaultExternal";
parent._sourcefolder = anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 173;BA.debugLine="SourceFolder = rp.GetSafeDirDefaultExternal(\"\")";
parent._sourcefolder = parent._rp.GetSafeDirDefaultExternal("");
 if (true) break;
;
 //BA.debugLineNum = 176;BA.debugLine="Try";

case 9:
//try
this.state = 25;
this.catchState = 20;
this.state = 11;
if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 20;
 //BA.debugLineNum = 177;BA.debugLine="If File.Exists(SourceFolder, \"FaData2012.db\") =";
if (true) break;

case 12:
//if
this.state = 15;
if (anywheresoftware.b4a.keywords.Common.File.Exists(parent._sourcefolder,"FaData2012.db")==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 14;
}if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 178;BA.debugLine="File.Copy(File.DirAssets, \"FaData2012.db\", Sou";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"FaData2012.db",parent._sourcefolder,"FaData2012.db");
 if (true) break;
;
 //BA.debugLineNum = 180;BA.debugLine="If SQL1.IsInitialized = False Then";

case 15:
//if
this.state = 18;
if (parent._sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 181;BA.debugLine="SQL1.Initialize(SourceFolder, \"FaData2012.db\",";
parent._sql1.Initialize(parent._sourcefolder,"FaData2012.db",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="SQL1.ExecQuery(\"PRAGMA journal_mode=DELETE\")";
parent._sql1.ExecQuery("PRAGMA journal_mode=DELETE");
 if (true) break;

case 18:
//C
this.state = 25;
;
 if (true) break;

case 20:
//C
this.state = 21;
this.catchState = 0;
 //BA.debugLineNum = 185;BA.debugLine="Msgbox2Async(\"Keine Datenbank vorhanden\", \"Fehl";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Keine Datenbank vorhanden"),BA.ObjectToCharSequence("Fehler - keine Datenbank gefunden"),"OK","","",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 186;BA.debugLine="Wait For Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 53;
return;
case 53:
//C
this.state = 21;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 188;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 21:
//if
this.state = 24;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 23;
}if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 189;BA.debugLine="Activity.Finish()";
parent.mostCurrent._activity.Finish();
 //BA.debugLineNum = 190;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 if (true) break;

case 24:
//C
this.state = 25;
;
 if (true) break;
if (true) break;

case 25:
//C
this.state = 26;
this.catchState = 0;
;
 if (true) break;
;
 //BA.debugLineNum = 195;BA.debugLine="If (FirstTime = False) Then";

case 26:
//if
this.state = 29;
if ((_firsttime==anywheresoftware.b4a.keywords.Common.False)) { 
this.state = 28;
}if (true) break;

case 28:
//C
this.state = 29;
 //BA.debugLineNum = 196;BA.debugLine="Helper.DBinBenutzung(True)";
parent.mostCurrent._helper._dbinbenutzung /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 if (true) break;
;
 //BA.debugLineNum = 200;BA.debugLine="If (Helper.CheckVersion()) Then";

case 29:
//if
this.state = 52;
if ((parent.mostCurrent._helper._checkversion /*boolean*/ (mostCurrent.activityBA))) { 
this.state = 31;
}else {
this.state = 47;
}if (true) break;

case 31:
//C
this.state = 32;
 //BA.debugLineNum = 202;BA.debugLine="If (Helper.CheckDatenInDB()) Then";
if (true) break;

case 32:
//if
this.state = 45;
if ((parent.mostCurrent._helper._checkdatenindb /*boolean*/ (mostCurrent.activityBA))) { 
this.state = 34;
}else {
this.state = 40;
}if (true) break;

case 34:
//C
this.state = 35;
 //BA.debugLineNum = 204;BA.debugLine="Helper.DBinBenutzung(True)";
parent.mostCurrent._helper._dbinbenutzung /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 205;BA.debugLine="Helper.FehlendeZuordnungenFuellenNachTransport(";
parent.mostCurrent._helper._fehlendezuordnungenfuellennachtransport /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 206;BA.debugLine="Helper.KorrigierenDerVorhandenenFahrdaten()			'";
parent.mostCurrent._helper._korrigierendervorhandenenfahrdaten /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 207;BA.debugLine="Helper.KorrigierenDerFehlerhaftenMatchCodes()";
parent.mostCurrent._helper._korrigierenderfehlerhaftenmatchcodes /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 208;BA.debugLine="Helper.LadenFixerTreffpunkte()";
parent.mostCurrent._helper._ladenfixertreffpunkte /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 211;BA.debugLine="Helper.SetFixenFahrlehrerFahrzeug";
parent.mostCurrent._helper._setfixenfahrlehrerfahrzeug /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 213;BA.debugLine="lv = GetDeviceLayoutValues";
parent._lv = anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA);
 //BA.debugLineNum = 214;BA.debugLine="If (lv.Scale = 1.3312500715255737) Then";
if (true) break;

case 35:
//if
this.state = 38;
if ((parent._lv.Scale==1.3312500715255737)) { 
this.state = 37;
}if (true) break;

case 37:
//C
this.state = 38;
 //BA.debugLineNum = 215;BA.debugLine="lv.Scale = 1.0";
parent._lv.Scale = (float) (1.0);
 if (true) break;

case 38:
//C
this.state = 45;
;
 //BA.debugLineNum = 218;BA.debugLine="Activity.LoadLayout(\"Startpage\")";
parent.mostCurrent._activity.LoadLayout("Startpage",mostCurrent.activityBA);
 //BA.debugLineNum = 219;BA.debugLine="Activity.Title = Activity.Title & TAB & \" (\" &";
parent.mostCurrent._activity.setTitle(BA.ObjectToCharSequence(BA.ObjectToString(parent.mostCurrent._activity.getTitle())+anywheresoftware.b4a.keywords.Common.TAB+" ("+parent._pm.GetVersionName("fadata.mobil")+")"));
 //BA.debugLineNum = 221;BA.debugLine="Log(\"Größenlayout Höhe: \" & lv.Height)";
anywheresoftware.b4a.keywords.Common.LogImpl("0196666","Größenlayout Höhe: "+BA.NumberToString(parent._lv.Height),0);
 //BA.debugLineNum = 222;BA.debugLine="Log(\"           Breite: \" & lv.Width)";
anywheresoftware.b4a.keywords.Common.LogImpl("0196667","           Breite: "+BA.NumberToString(parent._lv.Width),0);
 //BA.debugLineNum = 223;BA.debugLine="Log(\"            Scale: \" & lv.Scale)";
anywheresoftware.b4a.keywords.Common.LogImpl("0196668","            Scale: "+BA.NumberToString(parent._lv.Scale),0);
 //BA.debugLineNum = 224;BA.debugLine="Log(\"       ScreenSize: \" & lv.toString)";
anywheresoftware.b4a.keywords.Common.LogImpl("0196669","       ScreenSize: "+parent._lv.toString(),0);
 //BA.debugLineNum = 227;BA.debugLine="thStart.Height = lv.Height";
parent.mostCurrent._thstart.setHeight(parent._lv.Height);
 //BA.debugLineNum = 228;BA.debugLine="thStart.Width = lv.Width";
parent.mostCurrent._thstart.setWidth(parent._lv.Width);
 //BA.debugLineNum = 229;BA.debugLine="thStart.Color = Colors.Transparent";
parent.mostCurrent._thstart.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 232;BA.debugLine="bDatenVorhanden = False";
parent._bdatenvorhanden = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 233;BA.debugLine="bTreffpunktAuswahl = False";
parent._btreffpunktauswahl = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 234;BA.debugLine="bZahlBearbeiten = False";
parent._bzahlbearbeiten = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 235;BA.debugLine="bZahlDatum = False";
parent._bzahldatum = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 236;BA.debugLine="bAlleKlassen = False";
parent._balleklassen = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 237;BA.debugLine="bKFZmanuel = False";
parent._bkfzmanuel = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 240;BA.debugLine="Helper.ArraybIsSelected(False)";
parent.mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 241;BA.debugLine="iAusbildungBereich = 1";
parent._iausbildungbereich = (int) (1);
 //BA.debugLineNum = 244;BA.debugLine="aKonfig = Helper.KonfigdatenLaden";
parent._akonfig = parent.mostCurrent._helper._konfigdatenladen /*boolean[]*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 247;BA.debugLine="ListInitialisieren";
_listinitialisieren();
 //BA.debugLineNum = 249;BA.debugLine="AddTabs";
_addtabs();
 //BA.debugLineNum = 251;BA.debugLine="Beschriftungen";
_beschriftungen();
 //BA.debugLineNum = 253;BA.debugLine="cbBegleitfahrzueg.Checked = aKonfig(0)";
parent.mostCurrent._cbbegleitfahrzueg.setChecked(parent._akonfig[(int) (0)]);
 //BA.debugLineNum = 254;BA.debugLine="cbPruefungsErgebnis.Checked = aKonfig(1)";
parent.mostCurrent._cbpruefungsergebnis.setChecked(parent._akonfig[(int) (1)]);
 //BA.debugLineNum = 255;BA.debugLine="cbBVFKontrolle.Checked = aKonfig(2)";
parent.mostCurrent._cbbvfkontrolle.setChecked(parent._akonfig[(int) (2)]);
 //BA.debugLineNum = 258;BA.debugLine="txtEinstAnzeigeFahrEinheit.Text = Helper.Konfig";
parent.mostCurrent._txteinstanzeigefahreinheit.setText(BA.ObjectToCharSequence(parent.mostCurrent._helper._konfigdatenfahreinheit /*String*/ (mostCurrent.activityBA)));
 //BA.debugLineNum = 261;BA.debugLine="Helper.InitialisierungSignature(canvas1, panUnt";
parent.mostCurrent._helper._initialisierungsignature /*String*/ (mostCurrent.activityBA,parent.mostCurrent._canvas1,parent.mostCurrent._panunterschriftfeld,parent.mostCurrent._sigd);
 if (true) break;

case 40:
//C
this.state = 41;
 //BA.debugLineNum = 263;BA.debugLine="Msgbox2Async(\"Keine Daten in der Datenbank vorh";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Keine Daten in der Datenbank vorhanden"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Bitte zuerst Datenübertragung aus dem FaData-Hauptprogramm starten"),BA.ObjectToCharSequence("Fehler - keine Daten vorhanden"),"OK","","",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 264;BA.debugLine="Wait For Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 54;
return;
case 54:
//C
this.state = 41;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 266;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 41:
//if
this.state = 44;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 43;
}if (true) break;

case 43:
//C
this.state = 44;
 //BA.debugLineNum = 267;BA.debugLine="Activity.Finish()";
parent.mostCurrent._activity.Finish();
 //BA.debugLineNum = 268;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 if (true) break;

case 44:
//C
this.state = 45;
;
 if (true) break;

case 45:
//C
this.state = 52;
;
 if (true) break;

case 47:
//C
this.state = 48;
 //BA.debugLineNum = 272;BA.debugLine="Msgbox2Async(\"Falsche Version vorhanden\" & CRLF";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Falsche Version vorhanden"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Bitte wenden Sie sich an den Service von FaData"),BA.ObjectToCharSequence("Fehler - falsche Version"),"OK","","",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 273;BA.debugLine="Wait for Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 55;
return;
case 55:
//C
this.state = 48;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 274;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 48:
//if
this.state = 51;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 50;
}if (true) break;

case 50:
//C
this.state = 51;
 //BA.debugLineNum = 275;BA.debugLine="Activity.Finish()";
parent.mostCurrent._activity.Finish();
 //BA.debugLineNum = 276;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 if (true) break;

case 51:
//C
this.state = 52;
;
 if (true) break;

case 52:
//C
this.state = -1;
;
 //BA.debugLineNum = 279;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _msgbox_result(int _iresult) throws Exception{
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 389;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 390;BA.debugLine="Log(\"Beenden: \" & UserClosed)";
anywheresoftware.b4a.keywords.Common.LogImpl("0327681","Beenden: "+BA.ObjectToString(_userclosed),0);
 //BA.debugLineNum = 391;BA.debugLine="Helper.DBinBenutzung(False)";
mostCurrent._helper._dbinbenutzung /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 392;BA.debugLine="If (UserClosed) Then";
if ((_userclosed)) { 
 //BA.debugLineNum = 393;BA.debugLine="If SQL1.IsInitialized() Then";
if (_sql1.IsInitialized()) { 
 //BA.debugLineNum = 394;BA.debugLine="SQL1.Close()";
_sql1.Close();
 };
 };
 //BA.debugLineNum = 406;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _ipos = 0;
 //BA.debugLineNum = 281;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 283;BA.debugLine="DateTime.DateFormat = \"dd.MM.yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("dd.MM.yyyy");
 //BA.debugLineNum = 284;BA.debugLine="If Kalender.DaySelected > 0 Then";
if (mostCurrent._kalender._dayselected /*long*/ >0) { 
 //BA.debugLineNum = 285;BA.debugLine="btKalender.Text = DateTime.Date(Kalender.DaySele";
mostCurrent._btkalender.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(mostCurrent._kalender._dayselected /*long*/ )));
 }else {
 //BA.debugLineNum = 287;BA.debugLine="If bDatenVorhanden = False And bFirstTime = Fals";
if (_bdatenvorhanden==anywheresoftware.b4a.keywords.Common.False && _bfirsttime==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 288;BA.debugLine="btKalender.Text = DateTime.Date(DateTime.Now)	'";
mostCurrent._btkalender.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 };
 };
 //BA.debugLineNum = 293;BA.debugLine="If Schueler.SchuelerSelected <> 0 Then";
if (mostCurrent._schueler._schuelerselected /*int*/ !=0) { 
 //BA.debugLineNum = 294;BA.debugLine="lblAuswahlSchueler.Text = sAusgewaehlterSchueler";
mostCurrent._lblauswahlschueler.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 }else {
 //BA.debugLineNum = 302;BA.debugLine="If bDatenVorhanden = False Then";
if (_bdatenvorhanden==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 303;BA.debugLine="lblAuswahlSchueler.Text = aSchueler(0, 0)";
mostCurrent._lblauswahlschueler.setText(BA.ObjectToCharSequence(_aschueler[(int) (0)][(int) (0)]));
 //BA.debugLineNum = 304;BA.debugLine="sAusgewaehlterSchueler = lblAuswahlSchueler.Tex";
_sausgewaehlterschueler = mostCurrent._lblauswahlschueler.getText();
 //BA.debugLineNum = 305;BA.debugLine="iAusgewaehlterSchuelrID = aSchueler(0, 1)";
_iausgewaehlterschuelrid = (int)(Double.parseDouble(_aschueler[(int) (0)][(int) (1)]));
 };
 };
 //BA.debugLineNum = 308;BA.debugLine="Helper.FillSchuelerKlasseListView(lstSchuelerKlas";
mostCurrent._helper._fillschuelerklasselistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstschuelerklasse);
 //BA.debugLineNum = 311;BA.debugLine="If Fahrlehrer.FahrlehrerSelected > 0 Then";
if (mostCurrent._fahrlehrer._fahrlehrerselected /*int*/ >0) { 
 //BA.debugLineNum = 314;BA.debugLine="lblEinstAnzeigeFahrlehrer.Text = sFahrlehrer";
mostCurrent._lbleinstanzeigefahrlehrer.setText(BA.ObjectToCharSequence(_sfahrlehrer));
 //BA.debugLineNum = 315;BA.debugLine="lblAuswahlFahrlehrer.Text = sFahrlehrer";
mostCurrent._lblauswahlfahrlehrer.setText(BA.ObjectToCharSequence(_sfahrlehrer));
 }else {
 //BA.debugLineNum = 317;BA.debugLine="If bDatenVorhanden = False Then";
if (_bdatenvorhanden==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 318;BA.debugLine="lblEinstAnzeigeFahrlehrer.Text = lstFahrlehrer.";
mostCurrent._lbleinstanzeigefahrlehrer.setText(BA.ObjectToCharSequence(mostCurrent._lstfahrlehrer.GetItem((int) (0))));
 //BA.debugLineNum = 319;BA.debugLine="lblAuswahlFahrlehrer.Text = lstFahrlehrer.GetIt";
mostCurrent._lblauswahlfahrlehrer.setText(BA.ObjectToCharSequence(mostCurrent._lstfahrlehrer.GetItem((int) (0))));
 };
 };
 //BA.debugLineNum = 324;BA.debugLine="lblEinstAnzeigeKfz.Text = lstKfz.GetItem(KFZ.KfzS";
mostCurrent._lbleinstanzeigekfz.setText(BA.ObjectToCharSequence(mostCurrent._lstkfz.GetItem(mostCurrent._kfz._kfzselected /*int*/ )));
 //BA.debugLineNum = 325;BA.debugLine="lblAuswahlKfz.Text = lstKfz.GetItem(KFZ.KfzSelect";
mostCurrent._lblauswahlkfz.setText(BA.ObjectToCharSequence(mostCurrent._lstkfz.GetItem(mostCurrent._kfz._kfzselected /*int*/ )));
 //BA.debugLineNum = 328;BA.debugLine="If bAlleKlassen Then";
if (_balleklassen) { 
 //BA.debugLineNum = 329;BA.debugLine="lblAuswahlKlasse.Text = lstKlassen.GetItem(Klass";
mostCurrent._lblauswahlklasse.setText(BA.ObjectToCharSequence(mostCurrent._lstklassen.GetItem(mostCurrent._klassen._klassenselected /*int*/ )));
 }else {
 //BA.debugLineNum = 332;BA.debugLine="lblAuswahlKlasse.Text = lstSchuelerKlasse.GetIte";
mostCurrent._lblauswahlklasse.setText(BA.ObjectToCharSequence(mostCurrent._lstschuelerklasse.GetItem(mostCurrent._schuelerklasse._klasseselected /*int*/ )));
 };
 //BA.debugLineNum = 336;BA.debugLine="Dim iPos As Int";
_ipos = 0;
 //BA.debugLineNum = 338;BA.debugLine="If bSonstTaetigkeit Then";
if (_bsonsttaetigkeit) { 
 //BA.debugLineNum = 339;BA.debugLine="sFahrtenbezeichnung = lstSonstigeTaetigkeiten.Ge";
mostCurrent._sfahrtenbezeichnung = BA.ObjectToString(mostCurrent._lstsonstigetaetigkeiten.GetItem(mostCurrent._sonstigetaetigkeiten._sonsttaetigkeitselected /*int*/ ));
 //BA.debugLineNum = 340;BA.debugLine="iPos = sFahrtenbezeichnung.IndexOf(\" - \")";
_ipos = mostCurrent._sfahrtenbezeichnung.indexOf(" - ");
 //BA.debugLineNum = 341;BA.debugLine="lblAuswahlFahrbezeichnung.Text = sFahrtenbezeich";
mostCurrent._lblauswahlfahrbezeichnung.setText(BA.ObjectToCharSequence(mostCurrent._sfahrtenbezeichnung.substring((int) (0),_ipos)));
 //BA.debugLineNum = 343;BA.debugLine="If lblAuswahlFahrbezeichnung.Text = \"Unt\" Then";
if ((mostCurrent._lblauswahlfahrbezeichnung.getText()).equals("Unt")) { 
 //BA.debugLineNum = 344;BA.debugLine="btUnterschrift.Visible = True";
mostCurrent._btunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 345;BA.debugLine="btSpeichern.Visible = False";
mostCurrent._btspeichern.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 347;BA.debugLine="btUnterschrift.Visible = False";
mostCurrent._btunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 348;BA.debugLine="btSpeichern.Visible = True";
mostCurrent._btspeichern.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 351;BA.debugLine="sFahrtenbezeichnung = lstFahrtbezeichnung.GetIte";
mostCurrent._sfahrtenbezeichnung = BA.ObjectToString(mostCurrent._lstfahrtbezeichnung.GetItem(mostCurrent._fahrtbezeichnung._fahrtselected /*int*/ ));
 //BA.debugLineNum = 352;BA.debugLine="iPos = sFahrtenbezeichnung.IndexOf(\" - \")";
_ipos = mostCurrent._sfahrtenbezeichnung.indexOf(" - ");
 //BA.debugLineNum = 353;BA.debugLine="lblAuswahlFahrbezeichnung.Text = sFahrtenbezeich";
mostCurrent._lblauswahlfahrbezeichnung.setText(BA.ObjectToCharSequence(mostCurrent._sfahrtenbezeichnung.substring((int) (0),_ipos)));
 };
 //BA.debugLineNum = 357;BA.debugLine="txtAuswahlTreffpunkt.Text = lstTreffpunkt.GetItem";
mostCurrent._txtauswahltreffpunkt.setText(BA.ObjectToCharSequence(mostCurrent._lsttreffpunkt.GetItem(mostCurrent._treffpunkt._treffpunktselected /*int*/ )));
 //BA.debugLineNum = 360;BA.debugLine="If Helper.CheckBegleitfahrzeug(lblAuswahlKlasse.T";
if (mostCurrent._helper._checkbegleitfahrzeug /*boolean*/ (mostCurrent.activityBA,mostCurrent._lblauswahlklasse.getText())) { 
 //BA.debugLineNum = 361;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.White";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 362;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = True";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 363;BA.debugLine="If Begleitfahrzeuge.BegleitfahrzeugSelected > 0";
if (mostCurrent._begleitfahrzeuge._begleitfahrzeugselected /*int*/ >0) { 
 //BA.debugLineNum = 364;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = lstBegleitfahr";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(mostCurrent._lstbegleitfahrzeug.GetItem(mostCurrent._begleitfahrzeuge._begleitfahrzeugselected /*int*/ )));
 }else {
 //BA.debugLineNum = 367;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = lstBegleitfah";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(mostCurrent._lstbegleitfahrzeug.GetItem((int) (0))));
 };
 }else {
 //BA.debugLineNum = 371;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.LightGr";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 372;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = \"\"";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 373;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = False";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 377;BA.debugLine="If ZahlungenFuer.ZahlungFuerSelected > 0 Then";
if (mostCurrent._zahlungenfuer._zahlungfuerselected /*int*/ >0) { 
 //BA.debugLineNum = 378;BA.debugLine="lblZahlAuswahlZahlungenFuer.Text = lstZahlungFue";
mostCurrent._lblzahlauswahlzahlungenfuer.setText(BA.ObjectToCharSequence(mostCurrent._lstzahlungfuer.GetItem(mostCurrent._zahlungenfuer._zahlungfuerselected /*int*/ )));
 }else {
 //BA.debugLineNum = 380;BA.debugLine="lblZahlAuswahlZahlungenFuer.Text = lstZahlungFue";
mostCurrent._lblzahlauswahlzahlungenfuer.setText(BA.ObjectToCharSequence(mostCurrent._lstzahlungfuer.GetItem((int) (0))));
 };
 //BA.debugLineNum = 384;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(mostCurrent._btkalender.getText());
 //BA.debugLineNum = 386;BA.debugLine="bFirstTime = True";
_bfirsttime = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 387;BA.debugLine="End Sub";
return "";
}
public static String  _addtabs() throws Exception{
 //BA.debugLineNum = 1202;BA.debugLine="Sub AddTabs";
 //BA.debugLineNum = 1203;BA.debugLine="thStart.AddTabWithIcon(\"\", termin, termin ,\"Start";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._termin.getObject()),(android.graphics.Bitmap)(mostCurrent._termin.getObject()),"Start_Tab");
 //BA.debugLineNum = 1204;BA.debugLine="thStart.AddTabWithIcon(\"\", fahrstunden, fahrstund";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._fahrstunden.getObject()),(android.graphics.Bitmap)(mostCurrent._fahrstunden.getObject()),"Fahrdaten_Tab");
 //BA.debugLineNum = 1205;BA.debugLine="thStart.AddTabWithIcon (\"\", stammdaten, stammdate";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._stammdaten.getObject()),(android.graphics.Bitmap)(mostCurrent._stammdaten.getObject()),"Stammdaten_Tab");
 //BA.debugLineNum = 1206;BA.debugLine="thStart.AddTabWithIcon(\"\", zahlungen, zahlungen,";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._zahlungen.getObject()),(android.graphics.Bitmap)(mostCurrent._zahlungen.getObject()),"Zahlungen_tab");
 //BA.debugLineNum = 1207;BA.debugLine="If aKonfig(2) Then";
if (_akonfig[(int) (2)]) { 
 //BA.debugLineNum = 1208;BA.debugLine="thStart.AddTabWithIcon(\"\", lernkontrolle, lernko";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._lernkontrolle.getObject()),(android.graphics.Bitmap)(mostCurrent._lernkontrolle.getObject()),"Ausbildungbvf_Tab");
 }else {
 //BA.debugLineNum = 1210;BA.debugLine="thStart.AddTabWithIcon(\"\", lernkontrolle, lernko";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._lernkontrolle.getObject()),(android.graphics.Bitmap)(mostCurrent._lernkontrolle.getObject()),"Ausbildungskontrolle_Tab");
 };
 //BA.debugLineNum = 1212;BA.debugLine="thStart.AddTabWithIcon(\"\", config, config, \"Einst";
mostCurrent._thstart.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._config.getObject()),(android.graphics.Bitmap)(mostCurrent._config.getObject()),"Einstellung_Tab");
 //BA.debugLineNum = 1213;BA.debugLine="End Sub";
return "";
}
public static String  _beschriftungen() throws Exception{
 //BA.debugLineNum = 1255;BA.debugLine="Sub Beschriftungen";
 //BA.debugLineNum = 1256;BA.debugLine="Helper.StartBeschriftung(btKalender, btTagVor, bt";
mostCurrent._helper._startbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._btkalender,mostCurrent._bttagvor,mostCurrent._bttagzurueck,mostCurrent._lblfahrlehrer,mostCurrent._lblschueler,mostCurrent._lblfahrstd,mostCurrent._lblgesamtstd);
 //BA.debugLineNum = 1257;BA.debugLine="Helper.EinstellungBeschriftung(lblEinstFahrlehrer";
mostCurrent._helper._einstellungbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lbleinstfahrlehrer,mostCurrent._lbleinstkfz,mostCurrent._lbleinstfahreinheit,mostCurrent._lbleinstelltreffpunkt,mostCurrent._bteinstellaktuelleliste,mostCurrent._bteinstellspeichern,mostCurrent._lblbegleitfahrzeugerfassen,mostCurrent._lblpruefungergebnis,mostCurrent._btappbeenden,(anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(mostCurrent._txtneuertreffpunkt.getObject())),mostCurrent._txteinstanzeigefahreinheit,mostCurrent._lblbvfkontrolle);
 //BA.debugLineNum = 1259;BA.debugLine="Helper.FahrdatenBeschriftung(lblAbfahrt, lblEinhe";
mostCurrent._helper._fahrdatenbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lblabfahrt,mostCurrent._lbleinheit,mostCurrent._lblklasse,mostCurrent._lblauswahlklassealle,mostCurrent._lblkfz,mostCurrent._lblfahrtbezeichnung,mostCurrent._lblstdplus,mostCurrent._lblstdminus,mostCurrent._lblminplus,mostCurrent._lblminminus,mostCurrent._lblmin1plus,mostCurrent._lblmin1minus,mostCurrent._lbleinheitplus,mostCurrent._lbleinheitminus,mostCurrent._lblabfahrtanzeige,mostCurrent._lbleinheitanzeige,mostCurrent._lbltreffpunkt,mostCurrent._lblbegleitfahrzeug,mostCurrent._btvormerken,mostCurrent._btabbruch,mostCurrent._btspeichern,mostCurrent._btunterschrift,mostCurrent._lblpfeil,mostCurrent._lbleinheit5plus,mostCurrent._lbleinheit5minus,mostCurrent._lbleinheit1plus,mostCurrent._lbleinheit1minus,mostCurrent._lblsonder,mostCurrent._lblpruefungsergebnisse,mostCurrent._rbnichterfassen,mostCurrent._rbnichtbestanden,mostCurrent._rbbestanden,mostCurrent._btpruefergebnisok,mostCurrent._txtauswahltreffpunkt);
 //BA.debugLineNum = 1264;BA.debugLine="Helper.UnterschriftBeschriftung(btUnterschrfitSpe";
mostCurrent._helper._unterschriftbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._btunterschrfitspeichern,mostCurrent._btunterschriftabbruch,mostCurrent._btunterschriftloeschen);
 //BA.debugLineNum = 1265;BA.debugLine="Helper.StammdatenBeschriftung(lblStammdatenTelefo";
mostCurrent._helper._stammdatenbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lblstammdatentelefon,mostCurrent._lblstammdatenhandy,mostCurrent._lblstammdatenarbeitsstelle,mostCurrent._lblstammdatentelefonarbeit,mostCurrent._lblstammdaten1,mostCurrent._lblstammdaten2,mostCurrent._lblklasse1,mostCurrent._lblklasse2,mostCurrent._lblklasse3,mostCurrent._lblklasse4,mostCurrent._lblstammdatenklassen,mostCurrent._lblstammdatenhatklasse,mostCurrent._lblstammdatenemail);
 //BA.debugLineNum = 1268;BA.debugLine="Helper.StammdatenBeschriftung1(lblStammdatenKonto";
mostCurrent._helper._stammdatenbeschriftung1 /*String*/ (mostCurrent.activityBA,mostCurrent._lblstammdatenkontostand,mostCurrent._lblstammdatenantrageingereicht,mostCurrent._lblstammdatenantragzurueck,mostCurrent._lblstammdatengeburtstag,mostCurrent._lblstammdatennationalitaet,mostCurrent._lblstammdatenanmeldung,mostCurrent._lblstammdatenschulefiliale);
 //BA.debugLineNum = 1270;BA.debugLine="Helper.StammdatenBeschriftungKlasse(lblKlasseGrun";
mostCurrent._helper._stammdatenbeschriftungklasse /*String*/ (mostCurrent.activityBA,mostCurrent._lblklassegrundstoff,mostCurrent._lblklassedoppelstunde,mostCurrent._lblklassespezifisch,mostCurrent._lblklasseuebungsfahrt,mostCurrent._lblklasseuebunggrund,mostCurrent._lblklasseueberland,mostCurrent._lblklasseautobahn,mostCurrent._lblklassenachtfaht,mostCurrent._lblklasseunterweisungfhrzg,mostCurrent._lblklassetheorpruefung,mostCurrent._lblklassepraktischepruefung,mostCurrent._lblklassedoppelstundetext,mostCurrent._lblstdje45min);
 //BA.debugLineNum = 1273;BA.debugLine="Helper.ZahlungenBeschriftung(lblZahlTag, lblZahlA";
mostCurrent._helper._zahlungenbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lblzahltag,mostCurrent._lblzahlalle,mostCurrent._lblzahlzahlungfuertext,mostCurrent._lblzahlzahlungstext,mostCurrent._lblzahlzeilebearbeiten,mostCurrent._lblzahlspeichern,mostCurrent._txtzahlbetrag);
 //BA.debugLineNum = 1274;BA.debugLine="If aKonfig(2) Then";
if (_akonfig[(int) (2)]) { 
 //BA.debugLineNum = 1275;BA.debugLine="Helper.AusbildungBVFBeschriftung(lblGrundstufe,";
mostCurrent._helper._ausbildungbvfbeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lblgrundstufe,mostCurrent._lblausbildungbvf0,mostCurrent._lblausbildungbvf1,mostCurrent._lblausbildungbvf2,mostCurrent._lblausbildungbvf3,mostCurrent._lblausbildungbvf4,mostCurrent._lblausbildungbvf5,mostCurrent._lblausbildungbvf6);
 //BA.debugLineNum = 1276;BA.debugLine="Helper.AusbildungBVFPan0(cbBesonderhEinsteigen,";
mostCurrent._helper._ausbildungbvfpan0 /*String*/ (mostCurrent.activityBA,mostCurrent._cbbesonderheinsteigen,mostCurrent._cbeinstellen,mostCurrent._cblenkrad,mostCurrent._cbspiegel,mostCurrent._cbkopfstuetze,mostCurrent._cbsitz,mostCurrent._cblenkradhaltung,mostCurrent._cbpedale,mostCurrent._cbgurt,mostCurrent._cbschaltwaehlhebel,mostCurrent._cbzuendschloss,mostCurrent._cbmotoranlassen,mostCurrent._cbanfahranhalte);
 //BA.debugLineNum = 1278;BA.debugLine="Helper.AusbildungBVFPan1(cbSchaltuebg, cbHoch1_2";
mostCurrent._helper._ausbildungbvfpan1 /*String*/ (mostCurrent.activityBA,mostCurrent._cbschaltuebg,mostCurrent._cbhoch1_2,mostCurrent._cbhoch2_3,mostCurrent._cbhoch3_4,mostCurrent._cbrunter4_3,mostCurrent._cbrunter3_2,mostCurrent._cbrunter2_1,mostCurrent._cbrunter4_2,mostCurrent._cbrunter4_1,mostCurrent._cbrunter3_1,mostCurrent._cblenkuebung);
 //BA.debugLineNum = 1279;BA.debugLine="Helper.AusbildungBVFPan2(cbUmkehren, cbEinparken";
mostCurrent._helper._ausbildungbvfpan2 /*String*/ (mostCurrent.activityBA,mostCurrent._cbumkehren,mostCurrent._cbeinparkenlaengs,mostCurrent._cblvorwaertsrechts,mostCurrent._cblrueckwaertslinks,mostCurrent._cblrueckwaertsrechts,mostCurrent._cblvorwaertslinks,mostCurrent._cbrueckwaertsfahren,mostCurrent._cbeinparkenquer,mostCurrent._cbqvorwaertsrechts,mostCurrent._cbqrueckwaertslinks,mostCurrent._cbqrueckwaertsrechts,mostCurrent._cbqvorwaertslinks,mostCurrent._cbgefahrbremsung);
 //BA.debugLineNum = 1281;BA.debugLine="Helper.AusbildungBVFPan3(cbRollenSchalten, cbBre";
mostCurrent._helper._ausbildungbvfpan3 /*String*/ (mostCurrent.activityBA,mostCurrent._cbrollenschalten,mostCurrent._cbbremsschalten,mostCurrent._cbbremsuebung,mostCurrent._cbdegressiv,mostCurrent._cbzielbremsung,mostCurrent._cbgefahrsituation,mostCurrent._cbgefaelle,mostCurrent._cbanhalten,mostCurrent._cbanfahren,mostCurrent._cbrueckwaerts,mostCurrent._cbsichern,mostCurrent._cbschalten);
 //BA.debugLineNum = 1283;BA.debugLine="Helper.AusbildungBVFPan4(cbSteigung, cbStAnhalte";
mostCurrent._helper._ausbildungbvfpan4 /*String*/ (mostCurrent.activityBA,mostCurrent._cbsteigung,mostCurrent._cbstanhalten,mostCurrent._cbstanfahren,mostCurrent._cbstrueckwaerts,mostCurrent._cbstsichern,mostCurrent._cbstschalten,mostCurrent._cbtastgeschw,mostCurrent._cbbedienkontroll,mostCurrent._cboertlichbesonder);
 //BA.debugLineNum = 1284;BA.debugLine="Helper.AusbildungBVFPan5(cbFahrbahnbenutzung, cb";
mostCurrent._helper._ausbildungbvfpan5 /*String*/ (mostCurrent.activityBA,mostCurrent._cbfahrbahnbenutzung,mostCurrent._cbeinordnen,mostCurrent._cbmarkierungen,mostCurrent._cbfahrstreifenwechsel,mostCurrent._cblinks,mostCurrent._cbrechts,mostCurrent._cbvorbeifueberholen,mostCurrent._cbabbiegen,mostCurrent._cbabrechts,mostCurrent._cbablinks,mostCurrent._cbmehrspurig,mostCurrent._cbradweg,mostCurrent._cbsonderstreifen);
 //BA.debugLineNum = 1286;BA.debugLine="Helper.AusbildungBVFPan6(cbStrassenbahn, cbEinba";
mostCurrent._helper._ausbildungbvfpan6 /*String*/ (mostCurrent.activityBA,mostCurrent._cbstrassenbahn,mostCurrent._cbeinbahnstrasse,mostCurrent._cbvorfahrt,mostCurrent._cbrechtsvorlinks,mostCurrent._cbgruenpfeil,mostCurrent._cbpolizeibeamte,mostCurrent._cbgruenpfeilschild,mostCurrent._cbgeschwabstand,mostCurrent._cbsituationverkehrstn,mostCurrent._cbfussgaengerueberweg,mostCurrent._cboeffentlverkehrsm,mostCurrent._cbaelterebehinderte,mostCurrent._cbeinbahnstrradfahrer);
 //BA.debugLineNum = 1288;BA.debugLine="Helper.AusbildungBVFPan7(cbKinder, cbSchulbus, c";
mostCurrent._helper._ausbildungbvfpan7 /*String*/ (mostCurrent.activityBA,mostCurrent._cbkinder,mostCurrent._cbschulbus,mostCurrent._cbradfahrermofa,mostCurrent._cbverkehrsberuhigt,mostCurrent._cbschwierigeverkehrsf,mostCurrent._cbengpass,mostCurrent._cbkreisverkehr,mostCurrent._cbbahnuebergangwarte,mostCurrent._cbkritischeverkehrss,mostCurrent._cbhauptverkehrszt,mostCurrent._cbpartnerverhalten,mostCurrent._cbschwungnutzen,mostCurrent._cbfussgaengerschutzb);
 //BA.debugLineNum = 1290;BA.debugLine="Helper.AusbildungBVFPan8(cbAngepassteGeschw, cbA";
mostCurrent._helper._ausbildungbvfpan8 /*String*/ (mostCurrent.activityBA,mostCurrent._cbangepasstegeschw,mostCurrent._cbabstand,mostCurrent._cbulvorne,mostCurrent._cbulhinten,mostCurrent._cbulseitlich,mostCurrent._cbbeobachtspiegel,mostCurrent._cbverkehrszeichen,mostCurrent._cbkreuzungeinmuend,mostCurrent._cbkurven,mostCurrent._cbsteigungen,mostCurrent._cbulgefaelle,mostCurrent._cballeen,mostCurrent._cbueberholen);
 //BA.debugLineNum = 1292;BA.debugLine="Helper.AusbildungBVFPan9(cbBesondereSituat, cbLi";
mostCurrent._helper._ausbildungbvfpan9 /*String*/ (mostCurrent.activityBA,mostCurrent._cbbesonderesituat,mostCurrent._cbliegenblsichern,mostCurrent._cbeinfahrenortsch,mostCurrent._cbfussgaenger,mostCurrent._cbwildtiere,mostCurrent._cbbesondereanford,mostCurrent._cbleistungsgrenze,mostCurrent._cborientierung,mostCurrent._cbablenkung);
 //BA.debugLineNum = 1293;BA.debugLine="Helper.AusbildungBVFPan10(cbFahrtplanung, cbEinf";
mostCurrent._helper._ausbildungbvfpan10 /*String*/ (mostCurrent.activityBA,mostCurrent._cbfahrtplanung,mostCurrent._cbeinfahrtab,mostCurrent._cbabfahrbahnwechsel,mostCurrent._cbgeschwindigkeit,mostCurrent._cbababstand,mostCurrent._cbabvorne,mostCurrent._cbabhinten,mostCurrent._cbabseitlich,mostCurrent._cbabueberholen,mostCurrent._cbschilder,mostCurrent._cbvorbeifahren,mostCurrent._cbrastparktank,mostCurrent._cbverhunfall);
 //BA.debugLineNum = 1295;BA.debugLine="Helper.AusbildungBVFPan11(cbDichterVerkehr, cbBe";
mostCurrent._helper._ausbildungbvfpan11 /*String*/ (mostCurrent.activityBA,mostCurrent._cbdichterverkehr,mostCurrent._cbbesondersituat,mostCurrent._cbbesonderanford,mostCurrent._cbableistungsgrenze,mostCurrent._cbkonfliktsitua,mostCurrent._cbabablenkung,mostCurrent._lbldaemmerung,mostCurrent._cbbeleuchtung,mostCurrent._cbkontrolle,mostCurrent._cbeinstell,mostCurrent._cbbenutzung,mostCurrent._cbfernlicht,mostCurrent._cbverlassenbab);
 //BA.debugLineNum = 1297;BA.debugLine="Helper.AusbildungBVFPan12(cbBeleuchtStrasse, cbU";
mostCurrent._helper._ausbildungbvfpan12 /*String*/ (mostCurrent.activityBA,mostCurrent._cbbeleuchtstrasse,mostCurrent._cbunbeleuchtstrasse,mostCurrent._cbparken,mostCurrent._cbdubesondersituat,mostCurrent._cbschlechtewitterung,mostCurrent._cbtiere,mostCurrent._cbbahnuebergaenge,mostCurrent._cbunbelverkehrtn,mostCurrent._cbdubesonderanfor,mostCurrent._cbblendung,mostCurrent._cbduorientierung,mostCurrent._cbabschlussbesp);
 //BA.debugLineNum = 1299;BA.debugLine="Helper.AusbildungBVFPan13(cbSelbstFahren, cbInne";
mostCurrent._helper._ausbildungbvfpan13 /*String*/ (mostCurrent.activityBA,mostCurrent._cbselbstfahren,mostCurrent._cbinnerorts,mostCurrent._cbausserorts,mostCurrent._cbverantwfahren,mostCurrent._cbtestfpruef,mostCurrent._cbfakt,mostCurrent._cbandere,mostCurrent._cbwiederholung,mostCurrent._cbleistungsbew);
 //BA.debugLineNum = 1300;BA.debugLine="Helper.AusbildungBVFPan14(lblChecklisteFahrvorb,";
mostCurrent._helper._ausbildungbvfpan14 /*String*/ (mostCurrent.activityBA,mostCurrent._lblchecklistefahrvorb,mostCurrent._cbreifen,mostCurrent._cbeinausschalten,mostCurrent._cbfunktionpruefen,mostCurrent._cbstandlicht,mostCurrent._cbnebelschluss,mostCurrent._cbblinker,mostCurrent._cbabblendlicht,mostCurrent._cbwarnblicke,mostCurrent._cbhupe,mostCurrent._cbbsfernlicht,mostCurrent._cbschlussleuchte,mostCurrent._cbbremslicht);
 //BA.debugLineNum = 1302;BA.debugLine="Helper.AusbildungBVFPan15(cbKontrollLBenenn, cbR";
mostCurrent._helper._ausbildungbvfpan15 /*String*/ (mostCurrent.activityBA,mostCurrent._cbkontrolllbenenn,mostCurrent._cbrueckstrahler,mostCurrent._cbvorhandensein,mostCurrent._cbbeschaedigung,mostCurrent._cblenkung,mostCurrent._cblenkschlentriegeln,mostCurrent._cbprueflenkspiel,mostCurrent._cbfunktbremse,mostCurrent._cbbetriebsbremse,mostCurrent._cbfeststellbremse,mostCurrent._cbanlegengurt);
 //BA.debugLineNum = 1304;BA.debugLine="Helper.AusbildungBVFPan16(cbRichtigSitz, cbEinst";
mostCurrent._helper._ausbildungbvfpan16 /*String*/ (mostCurrent.activityBA,mostCurrent._cbrichtigsitz,mostCurrent._cbeinstellrueckspiegel,mostCurrent._cbeinkopfstuetze,mostCurrent._cbeinlenkrad,mostCurrent._lblheizunglueftung,mostCurrent._cbbedienenagg,mostCurrent._cbheizung,mostCurrent._cbheckheizung,mostCurrent._cbbehsonderaus,mostCurrent._cblueftung,mostCurrent._cbklimaanlage);
 //BA.debugLineNum = 1306;BA.debugLine="Helper.AusbildungBVFPan17(cbEnergieNutzung, cbKe";
mostCurrent._helper._ausbildungbvfpan17 /*String*/ (mostCurrent.activityBA,mostCurrent._cbenergienutzung,mostCurrent._cbkeineunnverbr,mostCurrent._cbrechtztabsch,mostCurrent._lblbetriebsverkehrssich,mostCurrent._cbmotorraum,mostCurrent._cbmotoroel,mostCurrent._cbkuehlmittel,mostCurrent._cbscheibenwaschm,mostCurrent._cbtanken,mostCurrent._cbbremsen);
 //BA.debugLineNum = 1307;BA.debugLine="Helper.AusbildungBVFPan18(cbSicherungsmittel, cb";
mostCurrent._helper._ausbildungbvfpan18 /*String*/ (mostCurrent.activityBA,mostCurrent._cbsicherungsmittel,mostCurrent._cbwarndreieck,mostCurrent._cbbordwerkzeug,mostCurrent._cbzusaetzlichaus,mostCurrent._cbverbandskasten,mostCurrent._cbaussenkontrolle,mostCurrent._cbscheibenwischer,mostCurrent._cbkennzeichen,mostCurrent._cbcheckspiegel,mostCurrent._cbcheckbeleuchtung,mostCurrent._cbladung,mostCurrent._cbladungssicherung,mostCurrent._cbkenntlichmachung);
 //BA.debugLineNum = 1309;BA.debugLine="Helper.AusbildungBVFPan19(cbFahreSchlWitt, cbWit";
mostCurrent._helper._ausbildungbvfpan19 /*String*/ (mostCurrent.activityBA,mostCurrent._cbfahreschlwitt,mostCurrent._cbwittlueftung,mostCurrent._cbwittscheiben,mostCurrent._cbregen,mostCurrent._cbwasserlachen,mostCurrent._cbwindsturm,mostCurrent._cbmatchschnee,mostCurrent._cbeis,mostCurrent._cbwittbeleuchtung);
 }else {
 //BA.debugLineNum = 1311;BA.debugLine="Helper.AusbildungskontrolleBeschriftung(lblAusbi";
mostCurrent._helper._ausbildungskontrollebeschriftung /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildung0,mostCurrent._lblausbildung1,mostCurrent._lblausbildung2,mostCurrent._lblausbildung3,mostCurrent._lblausbildung4,mostCurrent._lblausbildung5,mostCurrent._lblausbildung6,mostCurrent._lblausbildungplus,mostCurrent._lblausbildungminus,mostCurrent._lblausbildungzahl0,mostCurrent._lblausbildungzahl1,mostCurrent._lblausbildungzahl2,mostCurrent._lblausbildungzahl3,mostCurrent._lblausbildungzahl4,mostCurrent._lblausbildungzahl5,mostCurrent._lblausbildungzahl6,mostCurrent._lblausbildungzahlx,mostCurrent._lblausbildungzahlminus,mostCurrent._lblausbildungzahlplus,mostCurrent._lblausbildungzahlausrufe,mostCurrent._lblausbildungzahldel);
 };
 //BA.debugLineNum = 1316;BA.debugLine="End Sub";
return "";
}
public static String  _btabbruch_click() throws Exception{
 //BA.debugLineNum = 691;BA.debugLine="Sub btAbbruch_Click";
 //BA.debugLineNum = 692;BA.debugLine="thStart.CurrentTab = 0";
mostCurrent._thstart.setCurrentTab((int) (0));
 //BA.debugLineNum = 693;BA.debugLine="SonstigeTaetigkeitenAktiv(True)";
_sonstigetaetigkeitenaktiv(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 694;BA.debugLine="sAusgewaehlterSchueler = lblAuswahlSchueler.Text";
_sausgewaehlterschueler = mostCurrent._lblauswahlschueler.getText();
 //BA.debugLineNum = 695;BA.debugLine="iAusgewaehlterSchuelrID = Helper.GetSchuelerID(sA";
_iausgewaehlterschuelrid = mostCurrent._helper._getschuelerid /*int*/ (mostCurrent.activityBA,_sausgewaehlterschueler);
 //BA.debugLineNum = 696;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.LightGra";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 697;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = \"\";";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 698;BA.debugLine="End Sub";
return "";
}
public static String  _btappbeenden_click() throws Exception{
 //BA.debugLineNum = 1020;BA.debugLine="Sub btAppBeenden_Click";
 //BA.debugLineNum = 1022;BA.debugLine="If Helper.SaveConfigDataToINI(lblEinstAnzeigeFahr";
if (mostCurrent._helper._saveconfigdatatoini /*boolean*/ (mostCurrent.activityBA,mostCurrent._lbleinstanzeigefahrlehrer.getText(),mostCurrent._lbleinstanzeigekfz.getText(),mostCurrent._txteinstanzeigefahreinheit.getText(),mostCurrent._cbbegleitfahrzueg.getChecked(),mostCurrent._cbpruefungsergebnis.getChecked(),mostCurrent._cbbvfkontrolle.getChecked())) { 
 //BA.debugLineNum = 1024;BA.debugLine="If Helper.SaveTreffpunkteToINI Then";
if (mostCurrent._helper._savetreffpunktetoini /*boolean*/ (mostCurrent.activityBA)) { 
 //BA.debugLineNum = 1025;BA.debugLine="Activity.Finish()";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 1026;BA.debugLine="ExitApplication()";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else {
 //BA.debugLineNum = 1028;BA.debugLine="ToastMessageShow(\"Fehler beim speichern der Tre";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim speichern der Treffpunkte!"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 1031;BA.debugLine="ToastMessageShow(\"Fehler beim speichern der Konf";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim speichern der Konfig-Daten in INI Datei!"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 1034;BA.debugLine="If SQL1.IsInitialized() Then";
if (_sql1.IsInitialized()) { 
 //BA.debugLineNum = 1035;BA.debugLine="Helper.DBinBenutzung(False)";
mostCurrent._helper._dbinbenutzung /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1036;BA.debugLine="SQL1.Close()";
_sql1.Close();
 };
 //BA.debugLineNum = 1038;BA.debugLine="End Sub";
return "";
}
public static String  _bteinstellaktuelleliste_click() throws Exception{
 //BA.debugLineNum = 995;BA.debugLine="Sub btEinstellAktuelleListe_Click";
 //BA.debugLineNum = 996;BA.debugLine="bTreffpunktAuswahl = True";
_btreffpunktauswahl = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 997;BA.debugLine="StartActivity(Treffpunkt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._treffpunkt.getObject()));
 //BA.debugLineNum = 999;BA.debugLine="lstTreffpunkt.Clear";
mostCurrent._lsttreffpunkt.Clear();
 //BA.debugLineNum = 1000;BA.debugLine="Helper.FillTreffpunktListView(lstTreffpunkt)";
mostCurrent._helper._filltreffpunktlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lsttreffpunkt);
 //BA.debugLineNum = 1001;BA.debugLine="End Sub";
return "";
}
public static String  _bteinstellspeichern_click() throws Exception{
String _sneuertreffpunkt = "";
 //BA.debugLineNum = 1004;BA.debugLine="Sub btEinstellSpeichern_Click";
 //BA.debugLineNum = 1005;BA.debugLine="Dim sNeuerTreffpunkt As String";
_sneuertreffpunkt = "";
 //BA.debugLineNum = 1007;BA.debugLine="sNeuerTreffpunkt = txtNeuerTreffpunkt.Text.Trim";
_sneuertreffpunkt = mostCurrent._txtneuertreffpunkt.getText().trim();
 //BA.debugLineNum = 1009;BA.debugLine="If Helper.InsertNeuenTreffpunkt(sNeuerTreffpunkt)";
if (mostCurrent._helper._insertneuentreffpunkt /*boolean*/ (mostCurrent.activityBA,_sneuertreffpunkt)) { 
 //BA.debugLineNum = 1010;BA.debugLine="lstTreffpunkt.Clear";
mostCurrent._lsttreffpunkt.Clear();
 //BA.debugLineNum = 1011;BA.debugLine="Helper.FillTreffpunktListView(lstTreffpunkt)";
mostCurrent._helper._filltreffpunktlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lsttreffpunkt);
 //BA.debugLineNum = 1012;BA.debugLine="txtNeuerTreffpunkt.Text = \"\"";
mostCurrent._txtneuertreffpunkt.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1013;BA.debugLine="ToastMessageShow(\"Neuer Treffpunkt hinzugefügt!\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Neuer Treffpunkt hinzugefügt!"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1015;BA.debugLine="ToastMessageShow(\"Fehler beim anlegen des neues";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim anlegen des neues Treffpunkts! Maximale Anzahl erreicht!"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 1017;BA.debugLine="End Sub";
return "";
}
public static String  _btkalender_click() throws Exception{
 //BA.debugLineNum = 627;BA.debugLine="Sub btKalender_Click";
 //BA.debugLineNum = 629;BA.debugLine="StartActivity(Kalender)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._kalender.getObject()));
 //BA.debugLineNum = 630;BA.debugLine="End Sub";
return "";
}
public static String  _btkalender_longclick() throws Exception{
 //BA.debugLineNum = 1041;BA.debugLine="Sub btKalender_LongClick";
 //BA.debugLineNum = 1042;BA.debugLine="ToastMessageShow(\"Laygröße: \" & lv.Height & \" \" &";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Laygröße: "+BA.NumberToString(_lv.Height)+" "+BA.NumberToString(_lv.Width)+" "+BA.NumberToString(_lv.Scale)),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1043;BA.debugLine="End Sub";
return "";
}
public static void  _btpruefergebnisok_click() throws Exception{
ResumableSub_btPruefErgebnisOK_Click rsub = new ResumableSub_btPruefErgebnisOK_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_btPruefErgebnisOK_Click extends BA.ResumableSub {
public ResumableSub_btPruefErgebnisOK_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
boolean _checkzeit = false;
boolean _checkspeichern = false;
boolean _checksignature = false;
int _iresult = 0;
String _spruefergebnis = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 766;BA.debugLine="Dim checkZeit, checkSpeichern, checkSignature As";
_checkzeit = false;
_checkspeichern = false;
_checksignature = false;
 //BA.debugLineNum = 769;BA.debugLine="checkZeit = True";
_checkzeit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 771;BA.debugLine="If lblAuswahlFahrbezeichnung.Text <> \"Unt\" Then";
if (true) break;

case 1:
//if
this.state = 4;
if ((parent.mostCurrent._lblauswahlfahrbezeichnung.getText()).equals("Unt") == false) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 773;BA.debugLine="checkZeit = Helper.StartzeitPuefen(iTerminID, bt";
_checkzeit = parent.mostCurrent._helper._startzeitpuefen /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText());
 if (true) break;
;
 //BA.debugLineNum = 777;BA.debugLine="If checkZeit = False And bDatenVorhanden Then";

case 4:
//if
this.state = 7;
if (_checkzeit==anywheresoftware.b4a.keywords.Common.False && parent._bdatenvorhanden) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 778;BA.debugLine="Msgbox2Async(\"Terminliche Überscheidung mit eine";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Terminliche Überscheidung mit einem anderem Termin."+anywheresoftware.b4a.keywords.Common.CRLF+"Soll dennoch gespeichert werden?"),BA.ObjectToCharSequence("Achtung! Überscheidung"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 779;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 74;
return;
case 74:
//C
this.state = 7;
_iresult = (Integer) result[0];
;
 if (true) break;
;
 //BA.debugLineNum = 782;BA.debugLine="If iResult = DialogResponse.POSITIVE Or checkZeit";

case 7:
//if
this.state = 73;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE || _checkzeit) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 783;BA.debugLine="If bSonstTaetigkeit Then";
if (true) break;

case 10:
//if
this.state = 65;
if (parent._bsonsttaetigkeit) { 
this.state = 12;
}else {
this.state = 35;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 784;BA.debugLine="If lblAuswahlFahrbezeichnung.Text = \"Unt\" Then";
if (true) break;

case 13:
//if
this.state = 30;
if ((parent.mostCurrent._lblauswahlfahrbezeichnung.getText()).equals("Unt")) { 
this.state = 15;
}else {
this.state = 29;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 786;BA.debugLine="checkSignature = Helper.checkSignature(iTermin";
_checksignature = parent.mostCurrent._helper._checksignature /*boolean*/ (mostCurrent.activityBA,parent._iterminid);
 //BA.debugLineNum = 789;BA.debugLine="If checkSignature Then";
if (true) break;

case 16:
//if
this.state = 27;
if (_checksignature) { 
this.state = 18;
}else {
this.state = 20;
}if (true) break;

case 18:
//C
this.state = 27;
 //BA.debugLineNum = 790;BA.debugLine="checkSpeichern = Helper.SonstigeTaetigkeitenS";
_checkspeichern = parent.mostCurrent._helper._sonstigetaetigkeitenspeichern /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText(),_checksignature);
 if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 793;BA.debugLine="Msgbox2Async(\"Keine Unterschrift vorhanden\" &";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Keine Unterschrift vorhanden"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Soll dennoch gespeichert werden?"),BA.ObjectToCharSequence("Unterschrift"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 794;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 75;
return;
case 75:
//C
this.state = 21;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 795;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 21:
//if
this.state = 26;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 23;
}else {
this.state = 25;
}if (true) break;

case 23:
//C
this.state = 26;
 //BA.debugLineNum = 796;BA.debugLine="checkSpeichern = Helper.SonstigeTaetigkeiten";
_checkspeichern = parent.mostCurrent._helper._sonstigetaetigkeitenspeichern /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText(),_checksignature);
 if (true) break;

case 25:
//C
this.state = 26;
 //BA.debugLineNum = 799;BA.debugLine="checkSpeichern = True";
_checkspeichern = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 800;BA.debugLine="ToastMessageShow(\"Daten wurden nicht gespeic";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Daten wurden nicht gespeichert"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 26:
//C
this.state = 27;
;
 if (true) break;

case 27:
//C
this.state = 30;
;
 if (true) break;

case 29:
//C
this.state = 30;
 //BA.debugLineNum = 804;BA.debugLine="checkSpeichern = Helper.SonstigeTaetigkeitenSp";
_checkspeichern = parent.mostCurrent._helper._sonstigetaetigkeitenspeichern /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText(),_checksignature);
 if (true) break;
;
 //BA.debugLineNum = 807;BA.debugLine="If checkSpeichern Then";

case 30:
//if
this.state = 33;
if (_checkspeichern) { 
this.state = 32;
}if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 808;BA.debugLine="SonstigeTaetigkeitenAktiv(True)";
_sonstigetaetigkeitenaktiv(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 33:
//C
this.state = 65;
;
 if (true) break;

case 35:
//C
this.state = 36;
 //BA.debugLineNum = 835;BA.debugLine="If cbPruefungsErgebnis.Checked Then";
if (true) break;

case 36:
//if
this.state = 47;
if (parent.mostCurrent._cbpruefungsergebnis.getChecked()) { 
this.state = 38;
}if (true) break;

case 38:
//C
this.state = 39;
 //BA.debugLineNum = 836;BA.debugLine="Dim sPruefErgebnis As String";
_spruefergebnis = "";
 //BA.debugLineNum = 837;BA.debugLine="If rbBestanden.Checked Then";
if (true) break;

case 39:
//if
this.state = 46;
if (parent.mostCurrent._rbbestanden.getChecked()) { 
this.state = 41;
}else if(parent.mostCurrent._rbnichtbestanden.getChecked()) { 
this.state = 43;
}else if(parent.mostCurrent._rbnichterfassen.getChecked()) { 
this.state = 45;
}if (true) break;

case 41:
//C
this.state = 46;
 //BA.debugLineNum = 839;BA.debugLine="sPruefErgebnis = \"2\"";
_spruefergebnis = "2";
 if (true) break;

case 43:
//C
this.state = 46;
 //BA.debugLineNum = 842;BA.debugLine="sPruefErgebnis = \"1\"";
_spruefergebnis = "1";
 if (true) break;

case 45:
//C
this.state = 46;
 //BA.debugLineNum = 845;BA.debugLine="sPruefErgebnis = \"0\"";
_spruefergebnis = "0";
 if (true) break;

case 46:
//C
this.state = 47;
;
 if (true) break;
;
 //BA.debugLineNum = 849;BA.debugLine="If iTerminID > 0 Then";

case 47:
//if
this.state = 64;
if (parent._iterminid>0) { 
this.state = 49;
}else {
this.state = 63;
}if (true) break;

case 49:
//C
this.state = 50;
 //BA.debugLineNum = 851;BA.debugLine="checkSignature = Helper.checkSignature(iTermi";
_checksignature = parent.mostCurrent._helper._checksignature /*boolean*/ (mostCurrent.activityBA,parent._iterminid);
 //BA.debugLineNum = 854;BA.debugLine="If checkSignature Or bDatenVorhanden = False";
if (true) break;

case 50:
//if
this.state = 61;
if (_checksignature || parent._bdatenvorhanden==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 52;
}else {
this.state = 54;
}if (true) break;

case 52:
//C
this.state = 61;
 //BA.debugLineNum = 855;BA.debugLine="checkSpeichern = Helper.FahrdatenSpeichernUp";
_checkspeichern = parent.mostCurrent._helper._fahrdatenspeichernupdate /*boolean*/ (mostCurrent.activityBA,parent._iterminid,_spruefergebnis,parent.mostCurrent._cbpruefungsergebnis.getChecked(),_checksignature);
 if (true) break;

case 54:
//C
this.state = 55;
 //BA.debugLineNum = 857;BA.debugLine="Msgbox2Async(\"Keine Unterschrift vorhanden\"";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Keine Unterschrift vorhanden"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"Soll dennoch gespeichert werden?"),BA.ObjectToCharSequence("Unterschrift"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 858;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 76;
return;
case 76:
//C
this.state = 55;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 859;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 55:
//if
this.state = 60;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 57;
}else {
this.state = 59;
}if (true) break;

case 57:
//C
this.state = 60;
 //BA.debugLineNum = 860;BA.debugLine="checkSpeichern = Helper.FahrdatenSpeichernU";
_checkspeichern = parent.mostCurrent._helper._fahrdatenspeichernupdate /*boolean*/ (mostCurrent.activityBA,parent._iterminid,_spruefergebnis,parent.mostCurrent._cbpruefungsergebnis.getChecked(),_checksignature);
 if (true) break;

case 59:
//C
this.state = 60;
 //BA.debugLineNum = 862;BA.debugLine="checkSpeichern = True";
_checkspeichern = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 863;BA.debugLine="ToastMessageShow(\"Daten wurden nicht gespei";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Daten wurden nicht gespeichert"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 60:
//C
this.state = 61;
;
 if (true) break;

case 61:
//C
this.state = 64;
;
 if (true) break;

case 63:
//C
this.state = 64;
 //BA.debugLineNum = 891;BA.debugLine="checkSpeichern = True";
_checkspeichern = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 64:
//C
this.state = 65;
;
 if (true) break;
;
 //BA.debugLineNum = 898;BA.debugLine="If  iTerminID > 0 And checkSpeichern Then";

case 65:
//if
this.state = 72;
if (parent._iterminid>0 && _checkspeichern) { 
this.state = 67;
}else if(_checkspeichern) { 
this.state = 69;
}else {
this.state = 71;
}if (true) break;

case 67:
//C
this.state = 72;
 //BA.debugLineNum = 899;BA.debugLine="thStart.CurrentTab = thStart.CurrentTab - 1";
parent.mostCurrent._thstart.setCurrentTab((int) (parent.mostCurrent._thstart.getCurrentTab()-1));
 //BA.debugLineNum = 900;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(parent.mostCurrent._btkalender.getText());
 if (true) break;

case 69:
//C
this.state = 72;
 //BA.debugLineNum = 903;BA.debugLine="thStart.CurrentTab = thStart.CurrentTab - 1";
parent.mostCurrent._thstart.setCurrentTab((int) (parent.mostCurrent._thstart.getCurrentTab()-1));
 //BA.debugLineNum = 904;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(parent.mostCurrent._btkalender.getText());
 if (true) break;

case 71:
//C
this.state = 72;
 //BA.debugLineNum = 906;BA.debugLine="Msgbox(\"Fehler beim Speichern der Daten\", \"Fehl";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Fehler beim Speichern der Daten"),BA.ObjectToCharSequence("Fehler"),mostCurrent.activityBA);
 //BA.debugLineNum = 907;BA.debugLine="panStart.Visible = True";
parent.mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 908;BA.debugLine="panUnterschrift.Visible = False";
parent.mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 909;BA.debugLine="panPruefung.Visible = False";
parent.mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 72:
//C
this.state = 73;
;
 if (true) break;

case 73:
//C
this.state = -1;
;
 //BA.debugLineNum = 913;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _btspeichern_click() throws Exception{
ResumableSub_btSpeichern_Click rsub = new ResumableSub_btSpeichern_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_btSpeichern_Click extends BA.ResumableSub {
public ResumableSub_btSpeichern_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 728;BA.debugLine="If iTerminID = 0 And bSonstTaetigkeit = False The";
if (true) break;

case 1:
//if
this.state = 16;
if (parent._iterminid==0 && parent._bsonsttaetigkeit==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 729;BA.debugLine="If bSonstTaetigkeit Then";
if (true) break;

case 4:
//if
this.state = 15;
if (parent._bsonsttaetigkeit) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 15;
 //BA.debugLineNum = 730;BA.debugLine="btVormerken_Click";
_btvormerken_click();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 733;BA.debugLine="Msgbox2Async(\"Sollen Daten direkt gespeichern w";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Sollen Daten direkt gespeichern werden?"),BA.ObjectToCharSequence("Hinweis"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 734;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 28;
return;
case 28:
//C
this.state = 9;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 735;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 9:
//if
this.state = 14;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 11;
}else {
this.state = 13;
}if (true) break;

case 11:
//C
this.state = 14;
 //BA.debugLineNum = 737;BA.debugLine="btVormerken_Click";
_btvormerken_click();
 //BA.debugLineNum = 740;BA.debugLine="iTerminID = Helper.TerminIDHolen(btKalender.Te";
parent._iterminid = parent.mostCurrent._helper._terminidholen /*int*/ (mostCurrent.activityBA,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlklasse.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._txtauswahltreffpunkt.getText());
 //BA.debugLineNum = 742;BA.debugLine="bDatenVorhanden = True";
parent._bdatenvorhanden = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 744;BA.debugLine="bDatenVorhanden = False";
parent._bdatenvorhanden = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 745;BA.debugLine="ToastMessageShow(\"Daten wurden nicht gespeiche";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Daten wurden nicht gespeichert"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 14:
//C
this.state = 15;
;
 if (true) break;

case 15:
//C
this.state = 16;
;
 if (true) break;
;
 //BA.debugLineNum = 751;BA.debugLine="If cbPruefungsErgebnis.Checked And bSonstTaetigke";

case 16:
//if
this.state = 27;
if (parent.mostCurrent._cbpruefungsergebnis.getChecked() && parent._bsonsttaetigkeit==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 18;
}else {
this.state = 26;
}if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 752;BA.debugLine="If Helper.IsPruefung(lblAuswahlFahrbezeichnung.T";
if (true) break;

case 19:
//if
this.state = 24;
if (parent.mostCurrent._helper._ispruefung /*boolean*/ (mostCurrent.activityBA,parent.mostCurrent._lblauswahlfahrbezeichnung.getText())) { 
this.state = 21;
}else {
this.state = 23;
}if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 753;BA.debugLine="panStart.Visible = False";
parent.mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 754;BA.debugLine="panUnterschrift.Visible = False";
parent.mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 755;BA.debugLine="panPruefung.Visible = True";
parent.mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 757;BA.debugLine="btPruefErgebnisOK_Click";
_btpruefergebnisok_click();
 if (true) break;

case 24:
//C
this.state = 27;
;
 if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 760;BA.debugLine="btPruefErgebnisOK_Click";
_btpruefergebnisok_click();
 if (true) break;

case 27:
//C
this.state = -1;
;
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _bttagvor_click() throws Exception{
long _ldate = 0L;
 //BA.debugLineNum = 633;BA.debugLine="Sub btTagVor_Click";
 //BA.debugLineNum = 634;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 636;BA.debugLine="lDate = DateTime.DateParse(btKalender.Text)";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(mostCurrent._btkalender.getText());
 //BA.debugLineNum = 637;BA.debugLine="lDate = DateTime.Add(lDate, 0, 0, 1)";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_ldate,(int) (0),(int) (0),(int) (1));
 //BA.debugLineNum = 639;BA.debugLine="btKalender.Text = DateTime.Date(lDate)";
mostCurrent._btkalender.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate)));
 //BA.debugLineNum = 641;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(mostCurrent._btkalender.getText());
 //BA.debugLineNum = 642;BA.debugLine="End Sub";
return "";
}
public static String  _bttagzurueck_click() throws Exception{
long _ldate = 0L;
 //BA.debugLineNum = 645;BA.debugLine="Sub btTagZurueck_Click";
 //BA.debugLineNum = 646;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 648;BA.debugLine="lDate = DateTime.DateParse(btKalender.Text)";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(mostCurrent._btkalender.getText());
 //BA.debugLineNum = 649;BA.debugLine="lDate = DateTime.Add(lDate, 0, 0, -1)";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.Add(_ldate,(int) (0),(int) (0),(int) (-1));
 //BA.debugLineNum = 651;BA.debugLine="btKalender.Text = DateTime.Date(lDate)";
mostCurrent._btkalender.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate)));
 //BA.debugLineNum = 653;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(mostCurrent._btkalender.getText());
 //BA.debugLineNum = 654;BA.debugLine="End Sub";
return "";
}
public static void  _btunterschrfitspeichern_click() throws Exception{
ResumableSub_btUnterschrfitSpeichern_Click rsub = new ResumableSub_btUnterschrfitSpeichern_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_btUnterschrfitSpeichern_Click extends BA.ResumableSub {
public ResumableSub_btUnterschrfitSpeichern_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
boolean _checkzeit = false;
boolean _checkspeichern = false;
int _iresult = 0;
boolean _bverschluesselt = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 929;BA.debugLine="Dim checkZeit, checkSpeichern As Boolean";
_checkzeit = false;
_checkspeichern = false;
 //BA.debugLineNum = 932;BA.debugLine="checkZeit = True";
_checkzeit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 935;BA.debugLine="If lblAuswahlFahrbezeichnung.Text = \"Unt\" Then";
if (true) break;

case 1:
//if
this.state = 6;
if ((parent.mostCurrent._lblauswahlfahrbezeichnung.getText()).equals("Unt")) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 936;BA.debugLine="lblAuswahlKlasse.Text = \"\"";
parent.mostCurrent._lblauswahlklasse.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 937;BA.debugLine="txtAuswahlTreffpunkt.Text = \"\"";
parent.mostCurrent._txtauswahltreffpunkt.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 938;BA.debugLine="lblEinheitAnzeige.Text = \"0\"";
parent.mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence("0"));
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 941;BA.debugLine="checkZeit = Helper.StartzeitPuefen(iTerminID, bt";
_checkzeit = parent.mostCurrent._helper._startzeitpuefen /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText());
 if (true) break;
;
 //BA.debugLineNum = 944;BA.debugLine="If checkZeit = False Then";

case 6:
//if
this.state = 9;
if (_checkzeit==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 8;
}if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 945;BA.debugLine="Msgbox2Async(\"Terminliche Überscheidung mit eine";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Terminliche Überscheidung mit einem anderem Termin."+anywheresoftware.b4a.keywords.Common.CRLF+"Soll dennoch gespeichert werden?"),BA.ObjectToCharSequence("Achtung! Überscheidung"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 946;BA.debugLine="Wait For Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 25;
return;
case 25:
//C
this.state = 9;
_iresult = (Integer) result[0];
;
 if (true) break;
;
 //BA.debugLineNum = 949;BA.debugLine="If iResult = DialogResponse.POSITIVE Or checkZeit";

case 9:
//if
this.state = 24;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE || _checkzeit) { 
this.state = 11;
}if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 950;BA.debugLine="checkSpeichern = Helper.DatenVormerken(iTerminID";
_checkspeichern = parent.mostCurrent._helper._datenvormerken /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlklasse.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._txtauswahltreffpunkt.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText(),parent.mostCurrent._lblauswahlkfz.getText(),parent.mostCurrent._lblauswahlbegleitfahrzeug.getText());
 //BA.debugLineNum = 953;BA.debugLine="If checkSpeichern Then";
if (true) break;

case 12:
//if
this.state = 23;
if (_checkspeichern) { 
this.state = 14;
}else {
this.state = 22;
}if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 955;BA.debugLine="iTerminID = Helper.TerminIDHolen(btKalender.Tex";
parent._iterminid = parent.mostCurrent._helper._terminidholen /*int*/ (mostCurrent.activityBA,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlklasse.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._txtauswahltreffpunkt.getText());
 //BA.debugLineNum = 957;BA.debugLine="Dim bVerschluesselt As Boolean";
_bverschluesselt = false;
 //BA.debugLineNum = 958;BA.debugLine="bVerschluesselt = Helper.UnterschriftSpeichernN";
_bverschluesselt = parent.mostCurrent._helper._unterschriftspeichernneu /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._lblauswahlklasse.getText(),parent.mostCurrent._lblauswahlkfz.getText(),parent.mostCurrent._lblauswahlbegleitfahrzeug.getText(),parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText());
 //BA.debugLineNum = 961;BA.debugLine="If bVerschluesselt Then";
if (true) break;

case 15:
//if
this.state = 20;
if (_bverschluesselt) { 
this.state = 17;
}else {
this.state = 19;
}if (true) break;

case 17:
//C
this.state = 20;
 //BA.debugLineNum = 962;BA.debugLine="bDatenVorhanden = True";
parent._bdatenvorhanden = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 963;BA.debugLine="btSpeichern_Click";
_btspeichern_click();
 if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 965;BA.debugLine="MsgboxAsync(\"Fehler beim speichern der Untersc";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Fehler beim speichern der Unterschrift in DB"),BA.ObjectToCharSequence("Fehler"),processBA);
 //BA.debugLineNum = 966;BA.debugLine="Wait For Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 26;
return;
case 26:
//C
this.state = 20;
_iresult = (Integer) result[0];
;
 if (true) break;

case 20:
//C
this.state = 23;
;
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 971;BA.debugLine="MsgboxAsync(\"Fehler beim Speichern der Daten\",";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Fehler beim Speichern der Daten"),BA.ObjectToCharSequence("Fehler"),processBA);
 //BA.debugLineNum = 972;BA.debugLine="Wait For Msgbox_Result (iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 27;
return;
case 27:
//C
this.state = 23;
_iresult = (Integer) result[0];
;
 if (true) break;

case 23:
//C
this.state = 24;
;
 if (true) break;

case 24:
//C
this.state = -1;
;
 //BA.debugLineNum = 975;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _btunterschrift_click() throws Exception{
int _laenge = 0;
int _hoehe = 0;
 //BA.debugLineNum = 701;BA.debugLine="Sub btUnterschrift_Click";
 //BA.debugLineNum = 703;BA.debugLine="panStart.Visible = False";
mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 704;BA.debugLine="panUnterschrift.Visible = True";
mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 705;BA.debugLine="panPruefung.Visible = False";
mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 706;BA.debugLine="SignatureCapture.sPath = \"\"";
mostCurrent._signaturecapture._spath /*String*/  = "";
 //BA.debugLineNum = 711;BA.debugLine="Dim laenge, hoehe As Int";
_laenge = 0;
_hoehe = 0;
 //BA.debugLineNum = 712;BA.debugLine="laenge = panUnterschriftFeld.Width";
_laenge = mostCurrent._panunterschriftfeld.getWidth();
 //BA.debugLineNum = 713;BA.debugLine="hoehe = panUnterschriftFeld.Height";
_hoehe = mostCurrent._panunterschriftfeld.getHeight();
 //BA.debugLineNum = 714;BA.debugLine="Log(\"Feld Unterschrift Laenge: \" & laenge)";
anywheresoftware.b4a.keywords.Common.LogImpl("02424845","Feld Unterschrift Laenge: "+BA.NumberToString(_laenge),0);
 //BA.debugLineNum = 715;BA.debugLine="Log(\"                  Hoehe : \" & hoehe)";
anywheresoftware.b4a.keywords.Common.LogImpl("02424846","                  Hoehe : "+BA.NumberToString(_hoehe),0);
 //BA.debugLineNum = 716;BA.debugLine="End Sub";
return "";
}
public static String  _btunterschriftabbruch_click() throws Exception{
 //BA.debugLineNum = 984;BA.debugLine="Sub btUnterschriftAbbruch_Click";
 //BA.debugLineNum = 985;BA.debugLine="panStart.Visible = True";
mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 986;BA.debugLine="panUnterschrift.Visible = False";
mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 987;BA.debugLine="panPruefung.Visible = False";
mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 990;BA.debugLine="SignatureCapture.sPath = \"\"";
mostCurrent._signaturecapture._spath /*String*/  = "";
 //BA.debugLineNum = 991;BA.debugLine="btUnterschriftLoeschen_Click";
_btunterschriftloeschen_click();
 //BA.debugLineNum = 992;BA.debugLine="End Sub";
return "";
}
public static String  _btunterschriftloeschen_click() throws Exception{
 //BA.debugLineNum = 978;BA.debugLine="Sub btUnterschriftLoeschen_Click";
 //BA.debugLineNum = 979;BA.debugLine="SignatureCapture.sPath = \"\"";
mostCurrent._signaturecapture._spath /*String*/  = "";
 //BA.debugLineNum = 980;BA.debugLine="SignatureCapture.Clear(sigD)";
mostCurrent._signaturecapture._clear /*String*/ (mostCurrent.activityBA,mostCurrent._sigd);
 //BA.debugLineNum = 981;BA.debugLine="End Sub";
return "";
}
public static void  _btvormerken_click() throws Exception{
ResumableSub_btVormerken_Click rsub = new ResumableSub_btVormerken_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_btVormerken_Click extends BA.ResumableSub {
public ResumableSub_btVormerken_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
boolean _checkzeit = false;
boolean _checkspeichern = false;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 664;BA.debugLine="Dim checkZeit, checkSpeichern As Boolean";
_checkzeit = false;
_checkspeichern = false;
 //BA.debugLineNum = 667;BA.debugLine="checkZeit = True";
_checkzeit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 670;BA.debugLine="checkZeit = Helper.StartzeitPuefen(iTerminID, btK";
_checkzeit = parent.mostCurrent._helper._startzeitpuefen /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText());
 //BA.debugLineNum = 672;BA.debugLine="If checkZeit = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_checkzeit==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 673;BA.debugLine="Msgbox2Async(\"Terminliche Überscheidung mit eine";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Terminliche Überscheidung mit einem anderem Termin."+anywheresoftware.b4a.keywords.Common.CRLF+"Soll dennoch gespeichert werden?"),BA.ObjectToCharSequence("Achtung! Überscheidung"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 674;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 14;
return;
case 14:
//C
this.state = 4;
_iresult = (Integer) result[0];
;
 if (true) break;
;
 //BA.debugLineNum = 677;BA.debugLine="If iResult = DialogResponse.POSITIVE Or checkZeit";

case 4:
//if
this.state = 13;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE || _checkzeit) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 678;BA.debugLine="checkSpeichern = Helper.DatenVormerken(iTerminID";
_checkspeichern = parent.mostCurrent._helper._datenvormerken /*boolean*/ (mostCurrent.activityBA,parent._iterminid,parent.mostCurrent._btkalender.getText(),parent.mostCurrent._lblabfahrtanzeige.getText(),parent.mostCurrent._lbleinheitanzeige.getText(),parent.mostCurrent._lblauswahlklasse.getText(),parent.mostCurrent._lblauswahlfahrbezeichnung.getText(),parent.mostCurrent._txtauswahltreffpunkt.getText(),parent.mostCurrent._lblauswahlfahrlehrer.getText(),parent.mostCurrent._lblauswahlkfz.getText(),parent.mostCurrent._lblauswahlbegleitfahrzeug.getText());
 //BA.debugLineNum = 681;BA.debugLine="If checkSpeichern Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_checkspeichern) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 682;BA.debugLine="thStart.CurrentTab = thStart.CurrentTab - 1";
parent.mostCurrent._thstart.setCurrentTab((int) (parent.mostCurrent._thstart.getCurrentTab()-1));
 //BA.debugLineNum = 683;BA.debugLine="TermineHolen(btKalender.Text)";
_termineholen(parent.mostCurrent._btkalender.getText());
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 685;BA.debugLine="Msgbox(\"Fehler beim Speichern der Daten\", \"Fehl";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Fehler beim Speichern der Daten"),BA.ObjectToCharSequence("Fehler"),mostCurrent.activityBA);
 if (true) break;

case 12:
//C
this.state = 13;
;
 if (true) break;

case 13:
//C
this.state = -1;
;
 //BA.debugLineNum = 688;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _cbbegleitfahrzueg_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 1833;BA.debugLine="Sub cbBegleitfahrzueg_CheckedChange(Checked As Boo";
 //BA.debugLineNum = 1835;BA.debugLine="Helper.SetKonfig(Checked, \"cbBegleitfahrzueg\")";
mostCurrent._helper._setkonfig /*String*/ (mostCurrent.activityBA,_checked,"cbBegleitfahrzueg");
 //BA.debugLineNum = 1836;BA.debugLine="End Sub";
return "";
}
public static String  _cbpruefungsergebnis_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 1839;BA.debugLine="Sub cbPruefungsErgebnis_CheckedChange(Checked As B";
 //BA.debugLineNum = 1840;BA.debugLine="Helper.SetKonfig(Checked, \"cbPruefungsErgebnis\")";
mostCurrent._helper._setkonfig /*String*/ (mostCurrent.activityBA,_checked,"cbPruefungsErgebnis");
 //BA.debugLineNum = 1842;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 44;BA.debugLine="DateTime.DateFormat = \"dd.MM.yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("dd.MM.yyyy");
 //BA.debugLineNum = 45;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 48;BA.debugLine="Dim termin, fahrstunden, stammdaten, zahlungen, l";
mostCurrent._termin = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._fahrstunden = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._stammdaten = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._zahlungen = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._lernkontrolle = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._config = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
mostCurrent._info = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 49;BA.debugLine="termin = LoadBitmap(File.DirAssets, \"ic_tab_termi";
mostCurrent._termin = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_termine.png");
 //BA.debugLineNum = 50;BA.debugLine="fahrstunden = LoadBitmap(File.DirAssets, \"ic_tab_";
mostCurrent._fahrstunden = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_fahrstunden.png");
 //BA.debugLineNum = 51;BA.debugLine="stammdaten = LoadBitmap(File.DirAssets, \"ic_tab_s";
mostCurrent._stammdaten = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_stammdaten.png");
 //BA.debugLineNum = 52;BA.debugLine="zahlungen = LoadBitmap(File.DirAssets, \"ic_tab_za";
mostCurrent._zahlungen = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_zahlungen.png");
 //BA.debugLineNum = 53;BA.debugLine="lernkontrolle = LoadBitmap(File.DirAssets, \"ic_ta";
mostCurrent._lernkontrolle = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_kontrolle.png");
 //BA.debugLineNum = 54;BA.debugLine="config = LoadBitmap(File.DirAssets, \"ic_tab_einst";
mostCurrent._config = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_tab_einstellungen.png");
 //BA.debugLineNum = 55;BA.debugLine="info = LoadBitmap(File.DirAssets, \"ic_dialog_info";
mostCurrent._info = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ic_dialog_information.ico");
 //BA.debugLineNum = 58;BA.debugLine="Dim thStart As TabHost";
mostCurrent._thstart = new anywheresoftware.b4a.objects.TabHostWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim btKalender, btTagZurueck, btTagVor As Button";
mostCurrent._btkalender = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._bttagzurueck = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._bttagvor = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Dim lblFahrlehrer, lblSchueler, lblAuswahlFahrleh";
mostCurrent._lblfahrlehrer = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblschueler = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlfahrlehrer = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlschueler = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblfahrstd = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblanzeigefahrstd = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblgesamtstd = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblanzeigegesamtstd = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim iTerminID As Int";
_iterminid = 0;
 //BA.debugLineNum = 67;BA.debugLine="Dim lstSchueler, lstFahrlehrer, lstKfz, lstTermin";
mostCurrent._lstschueler = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstfahrlehrer = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstkfz = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lsttermine = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstschuelerklasse = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstklassen = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstfahrtbezeichnung = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lsttreffpunkt = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstbegleitfahrzeug = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstzahlungfuer = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstzahlungen = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstsonstigetaetigkeiten = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Dim lblAbfahrt, lblEinheit, lblKlasse, lblKfz, lb";
mostCurrent._lblabfahrt = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheit = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasse = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblkfz = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblfahrtbezeichnung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlklasse = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlklassealle = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlkfz = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlfahrbezeichnung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbltreffpunkt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Dim lblStdPlus, lblStdMinus, lblMinPlus, lblMinMi";
mostCurrent._lblstdplus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstdminus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblminplus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblminminus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblmin1plus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblmin1minus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheitplus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheitminus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblbegleitfahrzeug = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblauswahlbegleitfahrzeug = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheit5plus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheit5minus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheit1plus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheit1minus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblanzschueler = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblanzdatum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Dim lblAbfahrtAnzeige, lblEinheitAnzeige, lblPfei";
mostCurrent._lblabfahrtanzeige = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinheitanzeige = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblpfeil = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblsonder = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblpruefungsergebnisse = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Dim txtAuswahlTreffpunkt As EditText";
mostCurrent._txtauswahltreffpunkt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Dim panStart, panUnterschrift, panPruefung As Pan";
mostCurrent._panstart = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panunterschrift = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panpruefung = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Dim btVormerken, btAbbruch, btSpeichern, btUnters";
mostCurrent._btvormerken = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btabbruch = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btspeichern = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btunterschrift = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btpruefergebnisok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Dim sFahrtenbezeichnung As String";
mostCurrent._sfahrtenbezeichnung = "";
 //BA.debugLineNum = 79;BA.debugLine="Dim rbNichtErfassen, rbNichtBestanden, rbBestande";
mostCurrent._rbnichterfassen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
mostCurrent._rbnichtbestanden = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
mostCurrent._rbbestanden = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Dim panStammdaten1, panStammdaten2, panKlassen, p";
mostCurrent._panstammdaten1 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panstammdaten2 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panklassen = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panklasse1 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panklasse2 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panklasse3 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panklasse4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Dim lblStammdaten1, lblStammdaten2, lblKlasse1, l";
mostCurrent._lblstammdaten1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdaten2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasse1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasse2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasse3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasse4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Dim lblStammdatenName, lblStammdatenOrt, lblStamm";
mostCurrent._lblstammdatenname = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenort = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenanschrift = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatentelefon = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammtelefon = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenhandy = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammhandy = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenarbeitsstelle = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammarbeitsstelle = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatentelefonarbeit = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammtelefonarbeit = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenklassen = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammklassen = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenhatklasse = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammhatklasse = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenemail = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammemail = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 88;BA.debugLine="Dim lblStammdatenKontostand, lblStammdatenAntragE";
mostCurrent._lblstammdatenkontostand = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenantrageingereicht = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenantragzurueck = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatengeburtstag = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatennationalitaet = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenschulefiliale = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammdatenanmeldung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammkontostand = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammantrageingereicht = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammantragzurueck = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammgeburtstag = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammnationalitaet = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammanmeldung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstammschulefiliale = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 92;BA.debugLine="Dim lblNameKlasse, lblKlasseGrundstoff, lblKlasse";
mostCurrent._lblnameklasse = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassegrundstoff = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassedoppelstunde = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassenbezeichnung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassespezifisch = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseuebungsfahrt = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseuebunggrund = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseueberland = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseautobahn = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassenachtfaht = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseunterweisungfhrzg = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassetheorpruefung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassepraktischepruefung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseausbildungsstand = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 95;BA.debugLine="Dim	lblKlasseAnzeigeGrundstoff, lblKlasseAnzeigeD";
mostCurrent._lblklasseanzeigegrundstoff = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigedoppelstunde = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigebezeichnung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigespezifisch = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigeuebungsfahrt = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigeuebunggrund = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigeueberland = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigeautobahn = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigenachtfaht = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigeunterweisungfhrzg = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigetheorpruefung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklasseanzeigepraktischepruefung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblklassedoppelstundetext = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblstdje45min = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Dim lblZahlDatum, lblZahlTag, lblZahlAlle, lblZah";
mostCurrent._lblzahldatum = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahltag = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlalle = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlschueler = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlgesamtbetrag = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlzahlungfuertext = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlzahlungstext = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlauswahlzahlungenfuer = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlzeilebearbeiten = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblzahlspeichern = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 102;BA.debugLine="Dim txtZahlBetrag As EditText";
mostCurrent._txtzahlbetrag = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 103;BA.debugLine="Dim panAuswahl, panZahlListe As Panel";
mostCurrent._panauswahl = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panzahlliste = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 106;BA.debugLine="Dim lblAusbildungSchueler, lblAusbildung0, lblAus";
mostCurrent._lblausbildungschueler = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung0 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildung6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungplus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungminus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl0 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahl6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahlx = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahlminus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahlplus = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahlausrufe = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungzahldel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 110;BA.debugLine="Dim lblAusbildungListeZeile1, lblAusbildungListeZ";
mostCurrent._lblausbildunglistezeile1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile7 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile8 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile9 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezeile10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 112;BA.debugLine="Dim lblAusbildungListeZusatz1, lblAusbildungListe";
mostCurrent._lblausbildunglistezusatz1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz7 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz8 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz9 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildunglistezusatz10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 114;BA.debugLine="Dim panAusbildung As Panel";
mostCurrent._panausbildung = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 115;BA.debugLine="Dim lstAusbildungLernThemen, lstAusbildungLernPun";
mostCurrent._lstausbildunglernthemen = new anywheresoftware.b4a.objects.ListViewWrapper();
mostCurrent._lstausbildunglernpunkte = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 118;BA.debugLine="Dim lblSchuelerBVF, lblGrundstufe, lblAusbildungB";
mostCurrent._lblschuelerbvf = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblgrundstufe = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf0 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblausbildungbvf6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbldaemmerung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblchecklistefahrvorb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblheizunglueftung = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblbetriebsverkehrssich = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 120;BA.debugLine="Dim cbBesonderhEinsteigen, cbEinstellen, cbLenkra";
mostCurrent._cbbesonderheinsteigen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinstellen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblenkrad = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbspiegel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkopfstuetze = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsitz = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblenkradhaltung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbpedale = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgurt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschaltwaehlhebel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbzuendschloss = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmotoranlassen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbanfahranhalte = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschaltuebg = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbhoch1_2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbhoch2_3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbhoch3_4 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter4_3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter3_2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter2_1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter4_2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter4_1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrunter3_1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblenkuebung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbumkehren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinparkenlaengs = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblvorwaertsrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblrueckwaertslinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblrueckwaertsrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblvorwaertslinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrueckwaertsfahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinparkenquer = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbqvorwaertsrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbqrueckwaertslinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbqrueckwaertsrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbqvorwaertslinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgefahrbremsung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrollenschalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbremsschalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbremsuebung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbdegressiv = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbzielbremsung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgefahrsituation = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgefaelle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbanhalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbanfahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrueckwaerts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsichern = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsteigung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstanhalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstanfahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstrueckwaerts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstsichern = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstschalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbtastgeschw = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbedienkontroll = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cboertlichbesonder = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfahrbahnbenutzung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinordnen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmarkierungen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfahrstreifenwechsel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbvorbeifueberholen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabbiegen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabrechts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbablinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmehrspurig = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbradweg = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsonderstreifen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstrassenbahn = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinbahnstrasse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbvorfahrt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrechtsvorlinks = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgruenpfeil = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbpolizeibeamte = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgruenpfeilschild = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgeschwabstand = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsituationverkehrstn = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfussgaengerueberweg = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cboeffentlverkehrsm = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbaelterebehinderte = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinbahnstrradfahrer = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkinder = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschulbus = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbradfahrermofa = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverkehrsberuhigt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschwierigeverkehrsf = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbengpass = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkreisverkehr = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbahnuebergangwarte = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkritischeverkehrss = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbhauptverkehrszt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbpartnerverhalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschwungnutzen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfussgaengerschutzb = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbangepasstegeschw = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabstand = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbulvorne = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbulhinten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbulseitlich = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbeobachtspiegel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverkehrszeichen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkreuzungeinmuend = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkurven = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsteigungen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbulgefaelle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cballeen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbueberholen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbesonderesituat = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbliegenblsichern = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinfahrenortsch = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfussgaenger = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwildtiere = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbesondereanford = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbleistungsgrenze = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cborientierung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbablenkung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfahrtplanung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinfahrtab = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabfahrbahnwechsel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbgeschwindigkeit = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbababstand = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabvorne = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabhinten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabseitlich = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabueberholen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschilder = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbvorbeifahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrastparktank = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverhunfall = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbdichterverkehr = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbesondersituat = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbesonderanford = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbableistungsgrenze = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkonfliktsitua = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabablenkung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbeleuchtung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkontrolle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinstell = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbenutzung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfernlicht = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverlassenbab = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbeleuchtstrasse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbunbeleuchtstrasse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbparken = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbdubesondersituat = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschlechtewitterung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbtiere = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbahnuebergaenge = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbunbelverkehrtn = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbdubesonderanfor = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbblendung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbduorientierung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabschlussbesp = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbselbstfahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbinnerorts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbausserorts = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverantwfahren = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbtestfpruef = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfakt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbandere = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwiederholung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbleistungsbew = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbreifen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinausschalten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfunktionpruefen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbstandlicht = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbnebelschluss = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbblinker = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbabblendlicht = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwarnblicke = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbhupe = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbsfernlicht = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbschlussleuchte = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbremslicht = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkontrolllbenenn = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrueckstrahler = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbvorhandensein = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbeschaedigung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblenkung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblenkschlentriegeln = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbprueflenkspiel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfunktbremse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbetriebsbremse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfeststellbremse = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbanlegengurt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrichtigsitz = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinstellrueckspiegel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinkopfstuetze = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeinlenkrad = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbedienenagg = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbheizung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbheckheizung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbehsonderaus = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cblueftung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbklimaanlage = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbenergienutzung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkeineunnverbr = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbrechtztabsch = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmotorraum = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmotoroel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkuehlmittel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbscheibenwaschm = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbtanken = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbremsen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbsicherungsmittel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwarndreieck = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbordwerkzeug = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbzusaetzlichaus = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbverbandskasten = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbaussenkontrolle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbscheibenwischer = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkennzeichen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbcheckspiegel = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbcheckbeleuchtung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbladung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbladungssicherung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbkenntlichmachung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbfahreschlwitt = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwittlueftung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwittscheiben = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbregen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwasserlachen = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwindsturm = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbmatchschnee = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbeis = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbwittbeleuchtung = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 140;BA.debugLine="Dim pan0, pan1, pan2, pan3, pan4, pan5, pan6, pan";
mostCurrent._pan0 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan1 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan2 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan3 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan4 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan5 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan6 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan7 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan8 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan9 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan10 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan11 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan12 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan13 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan14 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan15 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan16 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan17 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan18 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan19 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._pan20 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 141;BA.debugLine="Dim etNotizen As EditText";
mostCurrent._etnotizen = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 145;BA.debugLine="Dim lblEinstFahrlehrer, lblEinstAnzeigeFahrlehrer";
mostCurrent._lbleinstfahrlehrer = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinstanzeigefahrlehrer = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinstkfz = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinstanzeigekfz = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinstfahreinheit = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblbegleitfahrzeugerfassen = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblpruefungergebnis = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lbleinstelltreffpunkt = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblbvfkontrolle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 147;BA.debugLine="Dim panEinstellAuswahl As Panel";
mostCurrent._paneinstellauswahl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 148;BA.debugLine="Dim txtNeuerTreffpunkt As AutoCompleteEditText";
mostCurrent._txtneuertreffpunkt = new anywheresoftware.b4a.objects.AutoCompleteEditTextWrapper();
 //BA.debugLineNum = 149;BA.debugLine="Dim txtEinstAnzeigeFahrEinheit As EditText";
mostCurrent._txteinstanzeigefahreinheit = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 150;BA.debugLine="Dim btEinstellAktuelleListe, btEinstellSpeichern,";
mostCurrent._bteinstellaktuelleliste = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._bteinstellspeichern = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btappbeenden = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 151;BA.debugLine="Dim cbBegleitfahrzueg, cbPruefungsErgebnis, cbBVF";
mostCurrent._cbbegleitfahrzueg = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbpruefungsergebnis = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
mostCurrent._cbbvfkontrolle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 152;BA.debugLine="Dim aKonfig() As Boolean";
_akonfig = new boolean[(int) (0)];
;
 //BA.debugLineNum = 155;BA.debugLine="Dim btUnterschrfitSpeichern, btUnterschriftLoesch";
mostCurrent._btunterschrfitspeichern = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btunterschriftloeschen = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btunterschriftabbruch = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 156;BA.debugLine="Dim panUnterschriftFeld As Panel";
mostCurrent._panunterschriftfeld = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 157;BA.debugLine="Dim canvas1 As Canvas";
mostCurrent._canvas1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 158;BA.debugLine="Dim sigD As SignatureData";
mostCurrent._sigd = new fadata.mobil.signaturecapture._signaturedata();
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _klassenfelderfuellen(String[] _aklassendaten) throws Exception{
 //BA.debugLineNum = 1805;BA.debugLine="Sub KlassenFelderFuellen(aKlassendaten() As String";
 //BA.debugLineNum = 1807;BA.debugLine="lblKlasseAnzeigeGrundstoff.Text = lblStammKlassen";
mostCurrent._lblklasseanzeigegrundstoff.setText(BA.ObjectToCharSequence(mostCurrent._lblstammklassen.getText()));
 //BA.debugLineNum = 1808;BA.debugLine="lblKlasseAnzeigeDoppelstunde.Text = aKlassendaten";
mostCurrent._lblklasseanzeigedoppelstunde.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (0)]));
 //BA.debugLineNum = 1809;BA.debugLine="lblKlasseAnzeigeSpezifisch.Text = aKlassendaten(1";
mostCurrent._lblklasseanzeigespezifisch.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (1)]));
 //BA.debugLineNum = 1810;BA.debugLine="lblKlasseAnzeigeBezeichnung.Text = aKlassendaten(";
mostCurrent._lblklasseanzeigebezeichnung.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (2)]));
 //BA.debugLineNum = 1813;BA.debugLine="If lblKlasseAnzeigeBezeichnung.Text = \"\" Then";
if ((mostCurrent._lblklasseanzeigebezeichnung.getText()).equals("")) { 
 //BA.debugLineNum = 1814;BA.debugLine="lblKlasseAusbildungsstand.Visible = False";
mostCurrent._lblklasseausbildungsstand.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1816;BA.debugLine="lblKlasseAusbildungsstand.Visible = True";
mostCurrent._lblklasseausbildungsstand.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1817;BA.debugLine="lblKlasseAusbildungsstand.Text = aKlassendaten(3";
mostCurrent._lblklasseausbildungsstand.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (3)]));
 };
 //BA.debugLineNum = 1820;BA.debugLine="lblKlasseAnzeigeUebungsfahrt.Text = aKlassendaten";
mostCurrent._lblklasseanzeigeuebungsfahrt.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (4)]));
 //BA.debugLineNum = 1821;BA.debugLine="lblKlasseAnzeigeUebungGrund.Text = aKlassendaten(";
mostCurrent._lblklasseanzeigeuebunggrund.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (5)]));
 //BA.debugLineNum = 1822;BA.debugLine="lblKlasseAnzeigeUeberland.Text = aKlassendaten(6)";
mostCurrent._lblklasseanzeigeueberland.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (6)]));
 //BA.debugLineNum = 1823;BA.debugLine="lblKlasseAnzeigeAutobahn.Text = aKlassendaten(7)";
mostCurrent._lblklasseanzeigeautobahn.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (7)]));
 //BA.debugLineNum = 1824;BA.debugLine="lblKlasseAnzeigeNachtfaht.Text = aKlassendaten(8)";
mostCurrent._lblklasseanzeigenachtfaht.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (8)]));
 //BA.debugLineNum = 1825;BA.debugLine="lblKlasseAnzeigeUnterweisungFhrzg.Text = aKlassen";
mostCurrent._lblklasseanzeigeunterweisungfhrzg.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (9)]));
 //BA.debugLineNum = 1826;BA.debugLine="lblKlasseAnzeigeTheorPruefung.Text = aKlassendate";
mostCurrent._lblklasseanzeigetheorpruefung.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (10)]));
 //BA.debugLineNum = 1827;BA.debugLine="lblKlasseAnzeigePraktischePruefung.Text = aKlasse";
mostCurrent._lblklasseanzeigepraktischepruefung.setText(BA.ObjectToCharSequence(_aklassendaten[(int) (11)]));
 //BA.debugLineNum = 1828;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung0_click() throws Exception{
 //BA.debugLineNum = 2248;BA.debugLine="Sub lblAusbildung0_Click";
 //BA.debugLineNum = 2249;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2257;BA.debugLine="iAusbildungBereich = 1";
_iausbildungbereich = (int) (1);
 //BA.debugLineNum = 2259;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2260;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2261;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung1_click() throws Exception{
 //BA.debugLineNum = 2264;BA.debugLine="Sub lblAusbildung1_Click";
 //BA.debugLineNum = 2265;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2273;BA.debugLine="iAusbildungBereich = 2";
_iausbildungbereich = (int) (2);
 //BA.debugLineNum = 2275;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2276;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2277;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung2_click() throws Exception{
 //BA.debugLineNum = 2280;BA.debugLine="Sub lblAusbildung2_Click";
 //BA.debugLineNum = 2281;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2289;BA.debugLine="iAusbildungBereich = 3";
_iausbildungbereich = (int) (3);
 //BA.debugLineNum = 2291;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2292;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2293;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung3_click() throws Exception{
 //BA.debugLineNum = 2296;BA.debugLine="Sub lblAusbildung3_Click";
 //BA.debugLineNum = 2297;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2305;BA.debugLine="iAusbildungBereich = 4";
_iausbildungbereich = (int) (4);
 //BA.debugLineNum = 2307;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2308;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2309;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung4_click() throws Exception{
 //BA.debugLineNum = 2312;BA.debugLine="Sub lblAusbildung4_Click";
 //BA.debugLineNum = 2313;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2322;BA.debugLine="iAusbildungBereich = 5";
_iausbildungbereich = (int) (5);
 //BA.debugLineNum = 2324;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2325;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2326;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung5_click() throws Exception{
 //BA.debugLineNum = 2329;BA.debugLine="Sub lblAusbildung5_Click";
 //BA.debugLineNum = 2330;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2338;BA.debugLine="iAusbildungBereich = 6";
_iausbildungbereich = (int) (6);
 //BA.debugLineNum = 2340;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2341;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2342;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildung6_click() throws Exception{
 //BA.debugLineNum = 2345;BA.debugLine="Sub lblAusbildung6_Click";
 //BA.debugLineNum = 2346;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2354;BA.debugLine="iAusbildungBereich = 7";
_iausbildungbereich = (int) (7);
 //BA.debugLineNum = 2356;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusbil";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,_iausbildungbereich);
 //BA.debugLineNum = 2357;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusbil";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,_iausbildungbereich);
 //BA.debugLineNum = 2358;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungbvf0_click() throws Exception{
 //BA.debugLineNum = 2763;BA.debugLine="Private Sub lblAusbildungBVF0_Click";
 //BA.debugLineNum = 2764;BA.debugLine="iAusbildungBereich = iAusbildungBereich + 1";
_iausbildungbereich = (int) (_iausbildungbereich+1);
 //BA.debugLineNum = 2766;BA.debugLine="If iAusbildungBereich > 20 Then";
if (_iausbildungbereich>20) { 
 //BA.debugLineNum = 2767;BA.debugLine="iAusbildungBereich = 1";
_iausbildungbereich = (int) (1);
 }else if(_iausbildungbereich<1) { 
 //BA.debugLineNum = 2769;BA.debugLine="iAusbildungBereich = 20";
_iausbildungbereich = (int) (20);
 };
 //BA.debugLineNum = 2772;BA.debugLine="ShowBVFTabs";
_showbvftabs();
 //BA.debugLineNum = 2773;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungbvf1_click() throws Exception{
 //BA.debugLineNum = 2775;BA.debugLine="Private Sub lblAusbildungBVF1_Click";
 //BA.debugLineNum = 2776;BA.debugLine="iAusbildungBereich = iAusbildungBereich - 1";
_iausbildungbereich = (int) (_iausbildungbereich-1);
 //BA.debugLineNum = 2778;BA.debugLine="If iAusbildungBereich > 20 Then";
if (_iausbildungbereich>20) { 
 //BA.debugLineNum = 2779;BA.debugLine="iAusbildungBereich = 1";
_iausbildungbereich = (int) (1);
 }else if(_iausbildungbereich<1) { 
 //BA.debugLineNum = 2781;BA.debugLine="iAusbildungBereich = 20";
_iausbildungbereich = (int) (20);
 };
 //BA.debugLineNum = 2784;BA.debugLine="ShowBVFTabs";
_showbvftabs();
 //BA.debugLineNum = 2785;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungbvf2_click() throws Exception{
 //BA.debugLineNum = 3233;BA.debugLine="Private Sub lblAusbildungBVF2_Click";
 //BA.debugLineNum = 3234;BA.debugLine="lblGrundstufe.Text = \"Notizen\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Notizen"));
 //BA.debugLineNum = 3235;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3236;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3237;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3238;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3239;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3240;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3241;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3242;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3243;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3244;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3245;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3246;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3247;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3248;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3249;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3250;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3251;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3252;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3253;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3254;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3256;BA.debugLine="pan20.Visible = True";
mostCurrent._pan20.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3257;BA.debugLine="End Sub";
return "";
}
public static void  _lblausbildungbvf3_click() throws Exception{
ResumableSub_lblAusbildungBVF3_Click rsub = new ResumableSub_lblAusbildungBVF3_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lblAusbildungBVF3_Click extends BA.ResumableSub {
public ResumableSub_lblAusbildungBVF3_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 3260;BA.debugLine="Msgbox2Async(\"Möchten Sie die Daten zum '\" & btKa";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Möchten Sie die Daten zum '"+parent.mostCurrent._btkalender.getText()+"' speichern?"),BA.ObjectToCharSequence("BVF Daten speichern"),"Ja","","Nein",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3261;BA.debugLine="Wait For msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 1;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 3262;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 3263;BA.debugLine="If Helper.InsertUpdateBVFData(btKalender.Text, s";
if (true) break;

case 4:
//if
this.state = 9;
if (parent.mostCurrent._helper._insertupdatebvfdata /*boolean*/ (mostCurrent.activityBA,parent.mostCurrent._btkalender.getText(),parent._sausgewaehlterschueler,parent.mostCurrent._cbbesonderheinsteigen.getChecked(),parent.mostCurrent._cbeinstellen.getChecked(),parent.mostCurrent._cblenkrad.getChecked(),parent.mostCurrent._cbspiegel.getChecked(),parent.mostCurrent._cbkopfstuetze.getChecked(),parent.mostCurrent._cbsitz.getChecked(),parent.mostCurrent._cblenkradhaltung.getChecked(),parent.mostCurrent._cbpedale.getChecked(),parent.mostCurrent._cbgurt.getChecked(),parent.mostCurrent._cbschaltwaehlhebel.getChecked(),parent.mostCurrent._cbzuendschloss.getChecked(),parent.mostCurrent._cbmotoranlassen.getChecked(),parent.mostCurrent._cbanfahranhalte.getChecked(),parent.mostCurrent._cbschaltuebg.getChecked(),parent.mostCurrent._cbhoch1_2.getChecked(),parent.mostCurrent._cbhoch2_3.getChecked(),parent.mostCurrent._cbhoch3_4.getChecked(),parent.mostCurrent._cbrunter4_3.getChecked(),parent.mostCurrent._cbrunter3_2.getChecked(),parent.mostCurrent._cbrunter2_1.getChecked(),parent.mostCurrent._cbrunter4_2.getChecked(),parent.mostCurrent._cbrunter4_1.getChecked(),parent.mostCurrent._cbrunter3_1.getChecked(),parent.mostCurrent._cblenkuebung.getChecked(),parent.mostCurrent._cbumkehren.getChecked(),parent.mostCurrent._cbeinparkenlaengs.getChecked(),parent.mostCurrent._cblvorwaertsrechts.getChecked(),parent.mostCurrent._cblrueckwaertslinks.getChecked(),parent.mostCurrent._cblrueckwaertsrechts.getChecked(),parent.mostCurrent._cblvorwaertslinks.getChecked(),parent.mostCurrent._cbrueckwaertsfahren.getChecked(),parent.mostCurrent._cbeinparkenquer.getChecked(),parent.mostCurrent._cbqvorwaertsrechts.getChecked(),parent.mostCurrent._cbqrueckwaertslinks.getChecked(),parent.mostCurrent._cbqrueckwaertsrechts.getChecked(),parent.mostCurrent._cbqvorwaertslinks.getChecked(),parent.mostCurrent._cbgefahrbremsung.getChecked(),parent.mostCurrent._cbrollenschalten.getChecked(),parent.mostCurrent._cbbremsschalten.getChecked(),parent.mostCurrent._cbbremsuebung.getChecked(),parent.mostCurrent._cbdegressiv.getChecked(),parent.mostCurrent._cbzielbremsung.getChecked(),parent.mostCurrent._cbgefahrsituation.getChecked(),parent.mostCurrent._cbgefaelle.getChecked(),parent.mostCurrent._cbanhalten.getChecked(),parent.mostCurrent._cbanfahren.getChecked(),parent.mostCurrent._cbrueckwaerts.getChecked(),parent.mostCurrent._cbsichern.getChecked(),parent.mostCurrent._cbschalten.getChecked(),parent.mostCurrent._cbsteigung.getChecked(),parent.mostCurrent._cbstanhalten.getChecked(),parent.mostCurrent._cbstanfahren.getChecked(),parent.mostCurrent._cbstrueckwaerts.getChecked(),parent.mostCurrent._cbstsichern.getChecked(),parent.mostCurrent._cbstschalten.getChecked(),parent.mostCurrent._cbtastgeschw.getChecked(),parent.mostCurrent._cbbedienkontroll.getChecked(),parent.mostCurrent._cboertlichbesonder.getChecked(),parent.mostCurrent._cbfahrbahnbenutzung.getChecked(),parent.mostCurrent._cbeinordnen.getChecked(),parent.mostCurrent._cbmarkierungen.getChecked(),parent.mostCurrent._cbfahrstreifenwechsel.getChecked(),parent.mostCurrent._cblinks.getChecked(),parent.mostCurrent._cbrechts.getChecked(),parent.mostCurrent._cbvorbeifueberholen.getChecked(),parent.mostCurrent._cbabbiegen.getChecked(),parent.mostCurrent._cbabrechts.getChecked(),parent.mostCurrent._cbablinks.getChecked(),parent.mostCurrent._cbmehrspurig.getChecked(),parent.mostCurrent._cbradweg.getChecked(),parent.mostCurrent._cbsonderstreifen.getChecked(),parent.mostCurrent._cbstrassenbahn.getChecked(),parent.mostCurrent._cbeinbahnstrasse.getChecked(),parent.mostCurrent._cbvorfahrt.getChecked(),parent.mostCurrent._cbrechtsvorlinks.getChecked(),parent.mostCurrent._cbgruenpfeil.getChecked(),parent.mostCurrent._cbpolizeibeamte.getChecked(),parent.mostCurrent._cbgruenpfeilschild.getChecked(),parent.mostCurrent._cbgeschwabstand.getChecked(),parent.mostCurrent._cbsituationverkehrstn.getChecked(),parent.mostCurrent._cbfussgaengerueberweg.getChecked(),parent.mostCurrent._cboeffentlverkehrsm.getChecked(),parent.mostCurrent._cbaelterebehinderte.getChecked(),parent.mostCurrent._cbeinbahnstrradfahrer.getChecked(),parent.mostCurrent._cbkinder.getChecked(),parent.mostCurrent._cbschulbus.getChecked(),parent.mostCurrent._cbradfahrermofa.getChecked(),parent.mostCurrent._cbverkehrsberuhigt.getChecked(),parent.mostCurrent._cbschwierigeverkehrsf.getChecked(),parent.mostCurrent._cbengpass.getChecked(),parent.mostCurrent._cbkreisverkehr.getChecked(),parent.mostCurrent._cbbahnuebergangwarte.getChecked(),parent.mostCurrent._cbkritischeverkehrss.getChecked(),parent.mostCurrent._cbhauptverkehrszt.getChecked(),parent.mostCurrent._cbpartnerverhalten.getChecked(),parent.mostCurrent._cbschwungnutzen.getChecked(),parent.mostCurrent._cbfussgaengerschutzb.getChecked(),parent.mostCurrent._cbangepasstegeschw.getChecked(),parent.mostCurrent._cbabstand.getChecked(),parent.mostCurrent._cbulvorne.getChecked(),parent.mostCurrent._cbulhinten.getChecked(),parent.mostCurrent._cbulseitlich.getChecked(),parent.mostCurrent._cbbeobachtspiegel.getChecked(),parent.mostCurrent._cbverkehrszeichen.getChecked(),parent.mostCurrent._cbkreuzungeinmuend.getChecked(),parent.mostCurrent._cbkurven.getChecked(),parent.mostCurrent._cbsteigungen.getChecked(),parent.mostCurrent._cbulgefaelle.getChecked(),parent.mostCurrent._cballeen.getChecked(),parent.mostCurrent._cbueberholen.getChecked(),parent.mostCurrent._cbbesonderesituat.getChecked(),parent.mostCurrent._cbliegenblsichern.getChecked(),parent.mostCurrent._cbeinfahrenortsch.getChecked(),parent.mostCurrent._cbfussgaenger.getChecked(),parent.mostCurrent._cbwildtiere.getChecked(),parent.mostCurrent._cbbesondereanford.getChecked(),parent.mostCurrent._cbleistungsgrenze.getChecked(),parent.mostCurrent._cborientierung.getChecked(),parent.mostCurrent._cbablenkung.getChecked(),parent.mostCurrent._cbfahrtplanung.getChecked(),parent.mostCurrent._cbeinfahrtab.getChecked(),parent.mostCurrent._cbabfahrbahnwechsel.getChecked(),parent.mostCurrent._cbgeschwindigkeit.getChecked(),parent.mostCurrent._cbababstand.getChecked(),parent.mostCurrent._cbabvorne.getChecked(),parent.mostCurrent._cbabhinten.getChecked(),parent.mostCurrent._cbabseitlich.getChecked(),parent.mostCurrent._cbabueberholen.getChecked(),parent.mostCurrent._cbschilder.getChecked(),parent.mostCurrent._cbvorbeifahren.getChecked(),parent.mostCurrent._cbrastparktank.getChecked(),parent.mostCurrent._cbverhunfall.getChecked(),parent.mostCurrent._cbdichterverkehr.getChecked(),parent.mostCurrent._cbbesondersituat.getChecked(),parent.mostCurrent._cbbesonderanford.getChecked(),parent.mostCurrent._cbableistungsgrenze.getChecked(),parent.mostCurrent._cbkonfliktsitua.getChecked(),parent.mostCurrent._cbabablenkung.getChecked(),parent.mostCurrent._cbbeleuchtung.getChecked(),parent.mostCurrent._cbkontrolle.getChecked(),parent.mostCurrent._cbeinstell.getChecked(),parent.mostCurrent._cbbenutzung.getChecked(),parent.mostCurrent._cbfernlicht.getChecked(),parent.mostCurrent._cbverlassenbab.getChecked(),parent.mostCurrent._cbbeleuchtstrasse.getChecked(),parent.mostCurrent._cbunbeleuchtstrasse.getChecked(),parent.mostCurrent._cbparken.getChecked(),parent.mostCurrent._cbdubesondersituat.getChecked(),parent.mostCurrent._cbschlechtewitterung.getChecked(),parent.mostCurrent._cbtiere.getChecked(),parent.mostCurrent._cbbahnuebergaenge.getChecked(),parent.mostCurrent._cbunbelverkehrtn.getChecked(),parent.mostCurrent._cbdubesonderanfor.getChecked(),parent.mostCurrent._cbblendung.getChecked(),parent.mostCurrent._cbduorientierung.getChecked(),parent.mostCurrent._cbabschlussbesp.getChecked(),parent.mostCurrent._cbselbstfahren.getChecked(),parent.mostCurrent._cbinnerorts.getChecked(),parent.mostCurrent._cbausserorts.getChecked(),parent.mostCurrent._cbverantwfahren.getChecked(),parent.mostCurrent._cbtestfpruef.getChecked(),parent.mostCurrent._cbfakt.getChecked(),parent.mostCurrent._cbandere.getChecked(),parent.mostCurrent._cbwiederholung.getChecked(),parent.mostCurrent._cbleistungsbew.getChecked(),parent.mostCurrent._cbreifen.getChecked(),parent.mostCurrent._cbeinausschalten.getChecked(),parent.mostCurrent._cbfunktionpruefen.getChecked(),parent.mostCurrent._cbstandlicht.getChecked(),parent.mostCurrent._cbnebelschluss.getChecked(),parent.mostCurrent._cbblinker.getChecked(),parent.mostCurrent._cbabblendlicht.getChecked(),parent.mostCurrent._cbwarnblicke.getChecked(),parent.mostCurrent._cbhupe.getChecked(),parent.mostCurrent._cbbsfernlicht.getChecked(),parent.mostCurrent._cbschlussleuchte.getChecked(),parent.mostCurrent._cbbremslicht.getChecked(),parent.mostCurrent._cbkontrolllbenenn.getChecked(),parent.mostCurrent._cbrueckstrahler.getChecked(),parent.mostCurrent._cbvorhandensein.getChecked(),parent.mostCurrent._cbbeschaedigung.getChecked(),parent.mostCurrent._cblenkung.getChecked(),parent.mostCurrent._cblenkschlentriegeln.getChecked(),parent.mostCurrent._cbprueflenkspiel.getChecked(),parent.mostCurrent._cbfunktbremse.getChecked(),parent.mostCurrent._cbbetriebsbremse.getChecked(),parent.mostCurrent._cbfeststellbremse.getChecked(),parent.mostCurrent._cbanlegengurt.getChecked(),parent.mostCurrent._cbrichtigsitz.getChecked(),parent.mostCurrent._cbeinstellrueckspiegel.getChecked(),parent.mostCurrent._cbeinkopfstuetze.getChecked(),parent.mostCurrent._cbeinlenkrad.getChecked(),parent.mostCurrent._cbbedienenagg.getChecked(),parent.mostCurrent._cbheizung.getChecked(),parent.mostCurrent._cbheckheizung.getChecked(),parent.mostCurrent._cbbehsonderaus.getChecked(),parent.mostCurrent._cblueftung.getChecked(),parent.mostCurrent._cbklimaanlage.getChecked(),parent.mostCurrent._cbenergienutzung.getChecked(),parent.mostCurrent._cbkeineunnverbr.getChecked(),parent.mostCurrent._cbrechtztabsch.getChecked(),parent.mostCurrent._cbmotorraum.getChecked(),parent.mostCurrent._cbmotoroel.getChecked(),parent.mostCurrent._cbkuehlmittel.getChecked(),parent.mostCurrent._cbscheibenwaschm.getChecked(),parent.mostCurrent._cbtanken.getChecked(),parent.mostCurrent._cbbremsen.getChecked(),parent.mostCurrent._cbsicherungsmittel.getChecked(),parent.mostCurrent._cbwarndreieck.getChecked(),parent.mostCurrent._cbbordwerkzeug.getChecked(),parent.mostCurrent._cbzusaetzlichaus.getChecked(),parent.mostCurrent._cbverbandskasten.getChecked(),parent.mostCurrent._cbaussenkontrolle.getChecked(),parent.mostCurrent._cbscheibenwischer.getChecked(),parent.mostCurrent._cbkennzeichen.getChecked(),parent.mostCurrent._cbcheckspiegel.getChecked(),parent.mostCurrent._cbcheckbeleuchtung.getChecked(),parent.mostCurrent._cbladung.getChecked(),parent.mostCurrent._cbladungssicherung.getChecked(),parent.mostCurrent._cbkenntlichmachung.getChecked(),parent.mostCurrent._cbfahreschlwitt.getChecked(),parent.mostCurrent._cbwittlueftung.getChecked(),parent.mostCurrent._cbwittscheiben.getChecked(),parent.mostCurrent._cbregen.getChecked(),parent.mostCurrent._cbwasserlachen.getChecked(),parent.mostCurrent._cbwindsturm.getChecked(),parent.mostCurrent._cbmatchschnee.getChecked(),parent.mostCurrent._cbeis.getChecked(),parent.mostCurrent._cbwittbeleuchtung.getChecked(),parent.mostCurrent._etnotizen.getText())) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 3283;BA.debugLine="ToastMessageShow(\"Daten wurden gespeichert!\", F";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Daten wurden gespeichert!"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 3285;BA.debugLine="ToastMessageShow(\"Fehler beim speichern der Dat";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim speichern der Daten!"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 9:
//C
this.state = 10;
;
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 3288;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lblausbildungbvf4_click() throws Exception{
 //BA.debugLineNum = 3290;BA.debugLine="Private Sub lblAusbildungBVF4_Click";
 //BA.debugLineNum = 3292;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungbvf5_click() throws Exception{
 //BA.debugLineNum = 3294;BA.debugLine="Private Sub lblAusbildungBVF5_Click";
 //BA.debugLineNum = 3296;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungbvf6_click() throws Exception{
 //BA.debugLineNum = 3298;BA.debugLine="Private Sub lblAusbildungBVF6_Click";
 //BA.debugLineNum = 3300;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile1_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 1848;BA.debugLine="Sub lblAusbildungListeZeile1_Click()";
 //BA.debugLineNum = 1850;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 1857;BA.debugLine="If bIsSelected(0) Then";
if (_bisselected[(int) (0)]) { 
 //BA.debugLineNum = 1858;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1859;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1860;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1861;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1863;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1865;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile1,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1);
 //BA.debugLineNum = 1867;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 1869;BA.debugLine="lstAusbildungLernPunkte.SetSelection(0)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (0));
 //BA.debugLineNum = 1871;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(0)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (0)));
 //BA.debugLineNum = 1872;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(0)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (0)));
 //BA.debugLineNum = 1874;BA.debugLine="lblAusbildungListeZeile1.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 1875;BA.debugLine="lblAusbildungListeZusatz1.text = sEintrag";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 1877;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1878;BA.debugLine="bIsSelected(0) = True";
_bisselected[(int) (0)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1880;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile10_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2208;BA.debugLine="Sub lblAusbildungListeZeile10_Click";
 //BA.debugLineNum = 2210;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2217;BA.debugLine="If bIsSelected(9) Then";
if (_bisselected[(int) (9)]) { 
 //BA.debugLineNum = 2218;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2219;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2220;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2221;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2223;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2225;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile10,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2227;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2229;BA.debugLine="lstAusbildungLernPunkte.SetSelection(9)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (9));
 //BA.debugLineNum = 2231;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(9)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (9)));
 //BA.debugLineNum = 2232;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(9)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (9)));
 //BA.debugLineNum = 2234;BA.debugLine="lblAusbildungListeZeile10.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile10.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2235;BA.debugLine="lblAusbildungListeZusatz10.text = sEintrag";
mostCurrent._lblausbildunglistezusatz10.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2237;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2238;BA.debugLine="bIsSelected(9) = True";
_bisselected[(int) (9)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2240;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile2_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 1888;BA.debugLine="Sub lblAusbildungListeZeile2_Click";
 //BA.debugLineNum = 1890;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 1897;BA.debugLine="If bIsSelected(1) Then";
if (_bisselected[(int) (1)]) { 
 //BA.debugLineNum = 1898;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1899;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1900;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1901;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1903;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1905;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile2,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2);
 //BA.debugLineNum = 1907;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 1909;BA.debugLine="lstAusbildungLernPunkte.SetSelection(0)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (0));
 //BA.debugLineNum = 1911;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(1)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (1)));
 //BA.debugLineNum = 1912;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(1)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (1)));
 //BA.debugLineNum = 1914;BA.debugLine="lblAusbildungListeZeile2.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile2.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 1915;BA.debugLine="lblAusbildungListeZusatz2.text = sEintrag";
mostCurrent._lblausbildunglistezusatz2.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 1917;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1918;BA.debugLine="bIsSelected(1) = True";
_bisselected[(int) (1)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1920;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile3_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 1928;BA.debugLine="Sub lblAusbildungListeZeile3_Click";
 //BA.debugLineNum = 1930;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 1937;BA.debugLine="If bIsSelected(2) Then";
if (_bisselected[(int) (2)]) { 
 //BA.debugLineNum = 1938;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1939;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1940;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1941;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1943;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1945;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile3,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3);
 //BA.debugLineNum = 1947;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 1949;BA.debugLine="lstAusbildungLernPunkte.SetSelection(2)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (2));
 //BA.debugLineNum = 1951;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(2)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (2)));
 //BA.debugLineNum = 1952;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(2)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (2)));
 //BA.debugLineNum = 1954;BA.debugLine="lblAusbildungListeZeile3.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile3.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 1955;BA.debugLine="lblAusbildungListeZusatz3.text = sEintrag";
mostCurrent._lblausbildunglistezusatz3.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 1957;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1958;BA.debugLine="bIsSelected(2) = True";
_bisselected[(int) (2)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1960;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile4_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 1968;BA.debugLine="Sub lblAusbildungListeZeile4_Click";
 //BA.debugLineNum = 1970;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 1977;BA.debugLine="If bIsSelected(3) Then";
if (_bisselected[(int) (3)]) { 
 //BA.debugLineNum = 1978;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1979;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1980;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1981;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1983;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 1985;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile4,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4);
 //BA.debugLineNum = 1987;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 1989;BA.debugLine="lstAusbildungLernPunkte.SetSelection(3)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (3));
 //BA.debugLineNum = 1991;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(3)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (3)));
 //BA.debugLineNum = 1992;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(3)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (3)));
 //BA.debugLineNum = 1994;BA.debugLine="lblAusbildungListeZeile4.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile4.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 1995;BA.debugLine="lblAusbildungListeZusatz4.text = sEintrag";
mostCurrent._lblausbildunglistezusatz4.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 1997;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1998;BA.debugLine="bIsSelected(3) = True";
_bisselected[(int) (3)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2000;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile5_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2008;BA.debugLine="Sub lblAusbildungListeZeile5_Click";
 //BA.debugLineNum = 2010;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2017;BA.debugLine="If bIsSelected(4) Then";
if (_bisselected[(int) (4)]) { 
 //BA.debugLineNum = 2018;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2019;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2020;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2021;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2023;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2025;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile5,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5);
 //BA.debugLineNum = 2027;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2029;BA.debugLine="lstAusbildungLernPunkte.SetSelection(4)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (4));
 //BA.debugLineNum = 2031;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(4)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (4)));
 //BA.debugLineNum = 2032;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(4)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (4)));
 //BA.debugLineNum = 2034;BA.debugLine="lblAusbildungListeZeile5.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile5.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2035;BA.debugLine="lblAusbildungListeZusatz5.text = sEintrag";
mostCurrent._lblausbildunglistezusatz5.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2037;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2038;BA.debugLine="bIsSelected(4) = True";
_bisselected[(int) (4)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2040;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile6_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2048;BA.debugLine="Sub lblAusbildungListeZeile6_Click";
 //BA.debugLineNum = 2050;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2057;BA.debugLine="If bIsSelected(5) Then";
if (_bisselected[(int) (5)]) { 
 //BA.debugLineNum = 2058;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2059;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2060;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2061;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2063;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2065;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile6,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6);
 //BA.debugLineNum = 2067;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2069;BA.debugLine="lstAusbildungLernPunkte.SetSelection(5)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (5));
 //BA.debugLineNum = 2071;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(5)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (5)));
 //BA.debugLineNum = 2072;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(5)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (5)));
 //BA.debugLineNum = 2074;BA.debugLine="lblAusbildungListeZeile6.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile6.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2075;BA.debugLine="lblAusbildungListeZusatz6.text = sEintrag";
mostCurrent._lblausbildunglistezusatz6.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2077;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2078;BA.debugLine="bIsSelected(5) = True";
_bisselected[(int) (5)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2080;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile7_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2088;BA.debugLine="Sub lblAusbildungListeZeile7_Click";
 //BA.debugLineNum = 2090;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2097;BA.debugLine="If bIsSelected(6) Then";
if (_bisselected[(int) (6)]) { 
 //BA.debugLineNum = 2098;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2099;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2100;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2101;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2103;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2105;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile7,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7);
 //BA.debugLineNum = 2107;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2109;BA.debugLine="lstAusbildungLernPunkte.SetSelection(5)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (5));
 //BA.debugLineNum = 2111;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(6)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (6)));
 //BA.debugLineNum = 2112;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(6)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (6)));
 //BA.debugLineNum = 2114;BA.debugLine="lblAusbildungListeZeile7.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile7.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2115;BA.debugLine="lblAusbildungListeZusatz7.text = sEintrag";
mostCurrent._lblausbildunglistezusatz7.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2117;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2118;BA.debugLine="bIsSelected(6) = True";
_bisselected[(int) (6)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2120;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile8_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2128;BA.debugLine="Sub lblAusbildungListeZeile8_Click";
 //BA.debugLineNum = 2130;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2137;BA.debugLine="If bIsSelected(7) Then";
if (_bisselected[(int) (7)]) { 
 //BA.debugLineNum = 2138;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2139;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2140;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2141;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2143;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2145;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile8,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8);
 //BA.debugLineNum = 2147;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2149;BA.debugLine="lstAusbildungLernPunkte.SetSelection(7)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (7));
 //BA.debugLineNum = 2151;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(7)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (7)));
 //BA.debugLineNum = 2152;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(7)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (7)));
 //BA.debugLineNum = 2154;BA.debugLine="lblAusbildungListeZeile8.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile8.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2155;BA.debugLine="lblAusbildungListeZusatz8.text = sEintrag";
mostCurrent._lblausbildunglistezusatz8.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2157;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2158;BA.debugLine="bIsSelected(7) = True";
_bisselected[(int) (7)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2160;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezeile9_click() throws Exception{
String _sanzeige = "";
String _seintrag = "";
 //BA.debugLineNum = 2168;BA.debugLine="Sub lblAusbildungListeZeile9_Click";
 //BA.debugLineNum = 2170;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildungL";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 2177;BA.debugLine="If bIsSelected(8) Then";
if (_bisselected[(int) (8)]) { 
 //BA.debugLineNum = 2178;BA.debugLine="lblAusbildungListeZeile1.Text = \"\"";
mostCurrent._lblausbildunglistezeile1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2179;BA.debugLine="lblAusbildungListeZeile1.Color = Colors.Transpar";
mostCurrent._lblausbildunglistezeile1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2180;BA.debugLine="lblAusbildungListeZusatz1.Text = \"\"";
mostCurrent._lblausbildunglistezusatz1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 2181;BA.debugLine="lblAusbildungListeZusatz1.Color = Colors.Transpa";
mostCurrent._lblausbildunglistezusatz1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 2183;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2185;BA.debugLine="Helper.SetLabelLayout(lblAusbildungListeZeile9,";
mostCurrent._helper._setlabellayout /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9);
 //BA.debugLineNum = 2187;BA.debugLine="Dim sAnzeige, sEintrag As String";
_sanzeige = "";
_seintrag = "";
 //BA.debugLineNum = 2189;BA.debugLine="lstAusbildungLernPunkte.SetSelection(8)";
mostCurrent._lstausbildunglernpunkte.SetSelection((int) (8));
 //BA.debugLineNum = 2191;BA.debugLine="sAnzeige = lstAusbildungLernThemen.GetItem(8)";
_sanzeige = BA.ObjectToString(mostCurrent._lstausbildunglernthemen.GetItem((int) (8)));
 //BA.debugLineNum = 2192;BA.debugLine="sEintrag = lstAusbildungLernPunkte.GetItem(8)";
_seintrag = BA.ObjectToString(mostCurrent._lstausbildunglernpunkte.GetItem((int) (8)));
 //BA.debugLineNum = 2194;BA.debugLine="lblAusbildungListeZeile9.Text = sAnzeige";
mostCurrent._lblausbildunglistezeile9.setText(BA.ObjectToCharSequence(_sanzeige));
 //BA.debugLineNum = 2195;BA.debugLine="lblAusbildungListeZusatz9.text = sEintrag";
mostCurrent._lblausbildunglistezusatz9.setText(BA.ObjectToCharSequence(_seintrag));
 //BA.debugLineNum = 2197;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2198;BA.debugLine="bIsSelected(8) = True";
_bisselected[(int) (8)] = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 2200;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz1_click() throws Exception{
 //BA.debugLineNum = 1883;BA.debugLine="Sub lblAusbildungListeZusatz1_Click";
 //BA.debugLineNum = 1884;BA.debugLine="lblAusbildungListeZeile1_Click";
_lblausbildunglistezeile1_click();
 //BA.debugLineNum = 1885;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz10_click() throws Exception{
 //BA.debugLineNum = 2243;BA.debugLine="Sub lblAusbildungListeZusatz10_Click";
 //BA.debugLineNum = 2244;BA.debugLine="lblAusbildungListeZeile10_Click";
_lblausbildunglistezeile10_click();
 //BA.debugLineNum = 2245;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz2_click() throws Exception{
 //BA.debugLineNum = 1923;BA.debugLine="Sub lblAusbildungListeZusatz2_Click";
 //BA.debugLineNum = 1924;BA.debugLine="lblAusbildungListeZeile2_Click";
_lblausbildunglistezeile2_click();
 //BA.debugLineNum = 1925;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz3_click() throws Exception{
 //BA.debugLineNum = 1963;BA.debugLine="Sub lblAusbildungListeZusatz3_Click";
 //BA.debugLineNum = 1964;BA.debugLine="lblAusbildungListeZeile3_Click";
_lblausbildunglistezeile3_click();
 //BA.debugLineNum = 1965;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz4_click() throws Exception{
 //BA.debugLineNum = 2003;BA.debugLine="Sub lblAusbildungListeZusatz4_Click";
 //BA.debugLineNum = 2004;BA.debugLine="lblAusbildungListeZeile4_Click";
_lblausbildunglistezeile4_click();
 //BA.debugLineNum = 2005;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz5_click() throws Exception{
 //BA.debugLineNum = 2043;BA.debugLine="Sub lblAusbildungListeZusatz5_Click";
 //BA.debugLineNum = 2044;BA.debugLine="lblAusbildungListeZeile5_Click";
_lblausbildunglistezeile5_click();
 //BA.debugLineNum = 2045;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz6_click() throws Exception{
 //BA.debugLineNum = 2083;BA.debugLine="Sub lblAusbildungListeZusatz6_Click";
 //BA.debugLineNum = 2084;BA.debugLine="lblAusbildungListeZeile6_Click";
_lblausbildunglistezeile6_click();
 //BA.debugLineNum = 2085;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz7_click() throws Exception{
 //BA.debugLineNum = 2123;BA.debugLine="Sub lblAusbildungListeZusatz7_Click";
 //BA.debugLineNum = 2124;BA.debugLine="lblAusbildungListeZeile7_Click";
_lblausbildunglistezeile7_click();
 //BA.debugLineNum = 2125;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz8_click() throws Exception{
 //BA.debugLineNum = 2163;BA.debugLine="Sub lblAusbildungListeZusatz8_Click";
 //BA.debugLineNum = 2164;BA.debugLine="lblAusbildungListeZeile8_Click";
_lblausbildunglistezeile8_click();
 //BA.debugLineNum = 2165;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildunglistezusatz9_click() throws Exception{
 //BA.debugLineNum = 2203;BA.debugLine="Sub lblAusbildungListeZusatz9_Click";
 //BA.debugLineNum = 2204;BA.debugLine="lblAusbildungListeZeile9_Click";
_lblausbildunglistezeile9_click();
 //BA.debugLineNum = 2205;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungminus_click() throws Exception{
 //BA.debugLineNum = 2381;BA.debugLine="Sub lblAusbildungMinus_Click";
 //BA.debugLineNum = 2382;BA.debugLine="Select  iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2384;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
case 2: {
 //BA.debugLineNum = 2386;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 3: {
 //BA.debugLineNum = 2388;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 4: {
 //BA.debugLineNum = 2390;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 5: {
 //BA.debugLineNum = 2392;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 6: {
 //BA.debugLineNum = 2394;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 7: {
 //BA.debugLineNum = 2396;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
}
;
 //BA.debugLineNum = 2398;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungplus_click() throws Exception{
 //BA.debugLineNum = 2361;BA.debugLine="Sub lblAusbildungPlus_Click";
 //BA.debugLineNum = 2362;BA.debugLine="Select  iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2364;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 2: {
 //BA.debugLineNum = 2366;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 3: {
 //BA.debugLineNum = 2368;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 4: {
 //BA.debugLineNum = 2370;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 5: {
 //BA.debugLineNum = 2372;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 6: {
 //BA.debugLineNum = 2374;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
case 7: {
 //BA.debugLineNum = 2376;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
}
;
 //BA.debugLineNum = 2378;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl0_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2551;BA.debugLine="Sub lblAusbildungZahl0_Click";
 //BA.debugLineNum = 2552;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2553;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2554;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl0.getText()));
 //BA.debugLineNum = 2561;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2563;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2565;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2567;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2569;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2571;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2573;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2575;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2579;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl1_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2581;BA.debugLine="Sub lblAusbildungZahl1_Click";
 //BA.debugLineNum = 2582;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2583;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2584;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl1.getText()));
 //BA.debugLineNum = 2591;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2593;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2595;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2597;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2599;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2601;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2603;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2605;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2609;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl2_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2611;BA.debugLine="Sub lblAusbildungZahl2_Click";
 //BA.debugLineNum = 2612;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2613;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2614;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl2.getText()));
 //BA.debugLineNum = 2621;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2623;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2625;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2627;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2629;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2631;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2633;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2635;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2639;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl3_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2641;BA.debugLine="Sub lblAusbildungZahl3_Click";
 //BA.debugLineNum = 2642;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2643;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2644;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl3.getText()));
 //BA.debugLineNum = 2651;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2653;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2655;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2657;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2659;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2661;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2663;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2665;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2669;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl4_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2671;BA.debugLine="Sub lblAusbildungZahl4_Click";
 //BA.debugLineNum = 2672;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2673;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2674;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl4.getText()));
 //BA.debugLineNum = 2681;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2683;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2685;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2687;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2689;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2691;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2693;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2695;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2699;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl5_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2701;BA.debugLine="Sub lblAusbildungZahl5_Click";
 //BA.debugLineNum = 2702;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2703;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2704;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl5.getText()));
 //BA.debugLineNum = 2711;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2713;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2715;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2717;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2719;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2721;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2723;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2725;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2729;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahl6_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2731;BA.debugLine="Sub lblAusbildungZahl6_Click";
 //BA.debugLineNum = 2732;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2733;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2734;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahl6.getText()));
 //BA.debugLineNum = 2741;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2743;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2745;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2747;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2749;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2751;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2753;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2755;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2759;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahlausrufe_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2431;BA.debugLine="Sub lblAusbildungZahlAusrufe_Click";
 //BA.debugLineNum = 2432;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2433;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2434;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahlausrufe.getText()));
 //BA.debugLineNum = 2441;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2443;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2445;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2447;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2449;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2451;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2453;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2455;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2459;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahldel_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2521;BA.debugLine="Sub lblAusbildungZahlDel_Click";
 //BA.debugLineNum = 2522;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2523;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2524;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahldel.getText()));
 //BA.debugLineNum = 2531;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2533;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2535;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2537;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2539;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2541;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2543;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2545;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2549;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahlminus_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2461;BA.debugLine="Sub lblAusbildungZahlMinus_Click";
 //BA.debugLineNum = 2462;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2463;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2464;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahlminus.getText()));
 //BA.debugLineNum = 2471;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2473;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2475;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2477;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2479;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2481;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2483;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2485;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2489;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahlplus_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2491;BA.debugLine="Sub lblAusbildungZahlPlus_Click";
 //BA.debugLineNum = 2492;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2493;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2494;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahlplus.getText()));
 //BA.debugLineNum = 2501;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2503;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2505;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2507;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2509;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2511;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2513;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2515;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2519;BA.debugLine="End Sub";
return "";
}
public static String  _lblausbildungzahlx_click() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2401;BA.debugLine="Sub lblAusbildungZahlX_Click";
 //BA.debugLineNum = 2402;BA.debugLine="For i = 0 To bIsSelected.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (_bisselected.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 2403;BA.debugLine="If bIsSelected(i) Then";
if (_bisselected[_i]) { 
 //BA.debugLineNum = 2404;BA.debugLine="Helper.AusbildungZustandAendern(lblAusbildungLi";
mostCurrent._helper._ausbildungzustandaendern /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10,BA.ObjectToChar(mostCurrent._lblausbildungzahlx.getText()));
 //BA.debugLineNum = 2411;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2413;BA.debugLine="lblAusbildung0_Click";
_lblausbildung0_click();
 break; }
case 2: {
 //BA.debugLineNum = 2415;BA.debugLine="lblAusbildung1_Click";
_lblausbildung1_click();
 break; }
case 3: {
 //BA.debugLineNum = 2417;BA.debugLine="lblAusbildung2_Click";
_lblausbildung2_click();
 break; }
case 4: {
 //BA.debugLineNum = 2419;BA.debugLine="lblAusbildung3_Click";
_lblausbildung3_click();
 break; }
case 5: {
 //BA.debugLineNum = 2421;BA.debugLine="lblAusbildung4_Click";
_lblausbildung4_click();
 break; }
case 6: {
 //BA.debugLineNum = 2423;BA.debugLine="lblAusbildung5_Click";
_lblausbildung5_click();
 break; }
case 7: {
 //BA.debugLineNum = 2425;BA.debugLine="lblAusbildung6_Click";
_lblausbildung6_click();
 break; }
}
;
 };
 }
};
 //BA.debugLineNum = 2429;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlbegleitfahrzeug_click() throws Exception{
 //BA.debugLineNum = 454;BA.debugLine="Sub lblAuswahlBegleitfahrzeug_Click";
 //BA.debugLineNum = 455;BA.debugLine="If Helper.CheckBegleitfahrzeug(lblAuswahlKlasse.T";
if (mostCurrent._helper._checkbegleitfahrzeug /*boolean*/ (mostCurrent.activityBA,mostCurrent._lblauswahlklasse.getText())) { 
 //BA.debugLineNum = 456;BA.debugLine="StartActivity(Begleitfahrzeuge)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._begleitfahrzeuge.getObject()));
 };
 //BA.debugLineNum = 458;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlfahrbezeichnung_click() throws Exception{
 //BA.debugLineNum = 435;BA.debugLine="Sub lblAuswahlFahrbezeichnung_Click";
 //BA.debugLineNum = 436;BA.debugLine="If bSonstTaetigkeit Then";
if (_bsonsttaetigkeit) { 
 //BA.debugLineNum = 437;BA.debugLine="StartActivity(SonstigeTaetigkeiten)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sonstigetaetigkeiten.getObject()));
 }else {
 //BA.debugLineNum = 439;BA.debugLine="StartActivity(Fahrtbezeichnung)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._fahrtbezeichnung.getObject()));
 };
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlkfz_click() throws Exception{
 //BA.debugLineNum = 548;BA.debugLine="Sub lblAuswahlKFZ_Click";
 //BA.debugLineNum = 549;BA.debugLine="bKFZmanuel = True";
_bkfzmanuel = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 550;BA.debugLine="StartActivity(KFZ)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._kfz.getObject()));
 //BA.debugLineNum = 551;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlklasse_click() throws Exception{
 //BA.debugLineNum = 423;BA.debugLine="Sub lblAuswahlKlasse_Click";
 //BA.debugLineNum = 425;BA.debugLine="StartActivity(SchuelerKlasse)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._schuelerklasse.getObject()));
 //BA.debugLineNum = 426;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlklassealle_click() throws Exception{
 //BA.debugLineNum = 429;BA.debugLine="Sub lblAuswahlKlasseAlle_Click";
 //BA.debugLineNum = 431;BA.debugLine="StartActivity(Klassen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._klassen.getObject()));
 //BA.debugLineNum = 432;BA.debugLine="End Sub";
return "";
}
public static String  _lblauswahlschueler_click() throws Exception{
 //BA.debugLineNum = 657;BA.debugLine="Sub lblAuswahlSchueler_Click";
 //BA.debugLineNum = 658;BA.debugLine="StartActivity(Schueler)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._schueler.getObject()));
 //BA.debugLineNum = 659;BA.debugLine="SchuelerKlasse.KlasseSelected = 0";
mostCurrent._schuelerklasse._klasseselected /*int*/  = (int) (0);
 //BA.debugLineNum = 660;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheit1minus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 507;BA.debugLine="Sub lblEinheit1Minus_Click";
 //BA.debugLineNum = 508;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 510;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 512;BA.debugLine="If iEinheit > 15 Then";
if (_ieinheit>15) { 
 //BA.debugLineNum = 513;BA.debugLine="lblEinheitAnzeige.Text = iEinheit - 1";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit-1));
 };
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheit1plus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 499;BA.debugLine="Sub lblEinheit1Plus_Click";
 //BA.debugLineNum = 500;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 502;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 503;BA.debugLine="lblEinheitAnzeige.Text = iEinheit + 1";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit+1));
 //BA.debugLineNum = 504;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheit5minus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 488;BA.debugLine="Sub lblEinheit5Minus_Click";
 //BA.debugLineNum = 489;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 491;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 493;BA.debugLine="If iEinheit > 15 Then";
if (_ieinheit>15) { 
 //BA.debugLineNum = 494;BA.debugLine="lblEinheitAnzeige.Text = iEinheit - 5";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit-5));
 };
 //BA.debugLineNum = 496;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheit5plus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 480;BA.debugLine="Sub lblEinheit5Plus_Click";
 //BA.debugLineNum = 481;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 483;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 484;BA.debugLine="lblEinheitAnzeige.Text = iEinheit + 5";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit+5));
 //BA.debugLineNum = 485;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheitminus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 469;BA.debugLine="Sub lblEinheitMinus_Click";
 //BA.debugLineNum = 470;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 472;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 474;BA.debugLine="If iEinheit > 15 Then";
if (_ieinheit>15) { 
 //BA.debugLineNum = 475;BA.debugLine="lblEinheitAnzeige.Text = iEinheit - 15";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit-15));
 };
 //BA.debugLineNum = 477;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinheitplus_click() throws Exception{
int _ieinheit = 0;
 //BA.debugLineNum = 461;BA.debugLine="Sub lblEinheitPlus_Click";
 //BA.debugLineNum = 462;BA.debugLine="Dim iEinheit As Int";
_ieinheit = 0;
 //BA.debugLineNum = 464;BA.debugLine="iEinheit = lblEinheitAnzeige.Text";
_ieinheit = (int)(Double.parseDouble(mostCurrent._lbleinheitanzeige.getText()));
 //BA.debugLineNum = 465;BA.debugLine="lblEinheitAnzeige.Text = iEinheit + 15";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_ieinheit+15));
 //BA.debugLineNum = 466;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinstanzeigefahrlehrer_click() throws Exception{
 //BA.debugLineNum = 413;BA.debugLine="Sub lblEinstAnzeigeFahrlehrer_Click";
 //BA.debugLineNum = 414;BA.debugLine="StartActivity(Fahrlehrer)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._fahrlehrer.getObject()));
 //BA.debugLineNum = 415;BA.debugLine="End Sub";
return "";
}
public static String  _lbleinstanzeigekfz_click() throws Exception{
 //BA.debugLineNum = 418;BA.debugLine="Sub lblEinstAnzeigeKfz_Click";
 //BA.debugLineNum = 419;BA.debugLine="StartActivity(KFZ)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._kfz.getObject()));
 //BA.debugLineNum = 420;BA.debugLine="End Sub";
return "";
}
public static String  _lblklasse1_click() throws Exception{
String[] _aklassendaten = null;
 //BA.debugLineNum = 1753;BA.debugLine="Sub lblKlasse1_Click";
 //BA.debugLineNum = 1754;BA.debugLine="panStammdaten1.Visible = False";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1755;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1757;BA.debugLine="Helper.ZaehlerKlassenAnzeige(1, lblKlassenbezeich";
mostCurrent._helper._zaehlerklassenanzeige /*String*/ (mostCurrent.activityBA,(int) (1),mostCurrent._lblklassenbezeichnung);
 //BA.debugLineNum = 1758;BA.debugLine="panKlassen.Visible = True";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1760;BA.debugLine="Dim aKlassendaten() As String";
_aklassendaten = new String[(int) (0)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 1761;BA.debugLine="aKlassendaten = Helper.StammdatenKlassendatenHole";
_aklassendaten = mostCurrent._helper._stammdatenklassendatenholen /*String[]*/ (mostCurrent.activityBA,(int) (1));
 //BA.debugLineNum = 1763;BA.debugLine="KlassenFelderFuellen(aKlassendaten)";
_klassenfelderfuellen(_aklassendaten);
 //BA.debugLineNum = 1764;BA.debugLine="End Sub";
return "";
}
public static String  _lblklasse2_click() throws Exception{
String[] _aklassendaten = null;
 //BA.debugLineNum = 1766;BA.debugLine="Sub lblKlasse2_Click";
 //BA.debugLineNum = 1767;BA.debugLine="panStammdaten1.Visible = False";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1768;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1770;BA.debugLine="Helper.ZaehlerKlassenAnzeige(2, lblKlassenbezeich";
mostCurrent._helper._zaehlerklassenanzeige /*String*/ (mostCurrent.activityBA,(int) (2),mostCurrent._lblklassenbezeichnung);
 //BA.debugLineNum = 1771;BA.debugLine="panKlassen.Visible = True";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1773;BA.debugLine="Dim aKlassendaten() As String";
_aklassendaten = new String[(int) (0)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 1774;BA.debugLine="aKlassendaten = Helper.StammdatenKlassendatenHole";
_aklassendaten = mostCurrent._helper._stammdatenklassendatenholen /*String[]*/ (mostCurrent.activityBA,(int) (2));
 //BA.debugLineNum = 1776;BA.debugLine="KlassenFelderFuellen(aKlassendaten)";
_klassenfelderfuellen(_aklassendaten);
 //BA.debugLineNum = 1777;BA.debugLine="End Sub";
return "";
}
public static String  _lblklasse3_click() throws Exception{
String[] _aklassendaten = null;
 //BA.debugLineNum = 1779;BA.debugLine="Sub lblKlasse3_Click";
 //BA.debugLineNum = 1780;BA.debugLine="panStammdaten1.Visible = False";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1781;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1783;BA.debugLine="Helper.ZaehlerKlassenAnzeige(3, lblKlassenbezeich";
mostCurrent._helper._zaehlerklassenanzeige /*String*/ (mostCurrent.activityBA,(int) (3),mostCurrent._lblklassenbezeichnung);
 //BA.debugLineNum = 1784;BA.debugLine="panKlassen.Visible = True";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1786;BA.debugLine="Dim aKlassendaten() As String";
_aklassendaten = new String[(int) (0)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 1787;BA.debugLine="aKlassendaten = Helper.StammdatenKlassendatenHole";
_aklassendaten = mostCurrent._helper._stammdatenklassendatenholen /*String[]*/ (mostCurrent.activityBA,(int) (3));
 //BA.debugLineNum = 1789;BA.debugLine="KlassenFelderFuellen(aKlassendaten)";
_klassenfelderfuellen(_aklassendaten);
 //BA.debugLineNum = 1790;BA.debugLine="End Sub";
return "";
}
public static String  _lblklasse4_click() throws Exception{
String[] _aklassendaten = null;
 //BA.debugLineNum = 1792;BA.debugLine="Sub lblKlasse4_Click";
 //BA.debugLineNum = 1793;BA.debugLine="panStammdaten1.Visible = False";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1794;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1796;BA.debugLine="Helper.ZaehlerKlassenAnzeige(4, lblKlassenbezeich";
mostCurrent._helper._zaehlerklassenanzeige /*String*/ (mostCurrent.activityBA,(int) (4),mostCurrent._lblklassenbezeichnung);
 //BA.debugLineNum = 1797;BA.debugLine="panKlassen.Visible = True";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1799;BA.debugLine="Dim aKlassendaten() As String";
_aklassendaten = new String[(int) (0)];
java.util.Arrays.fill(_aklassendaten,"");
 //BA.debugLineNum = 1800;BA.debugLine="aKlassendaten = Helper.StammdatenKlassendatenHole";
_aklassendaten = mostCurrent._helper._stammdatenklassendatenholen /*String[]*/ (mostCurrent.activityBA,(int) (4));
 //BA.debugLineNum = 1802;BA.debugLine="KlassenFelderFuellen(aKlassendaten)";
_klassenfelderfuellen(_aklassendaten);
 //BA.debugLineNum = 1803;BA.debugLine="End Sub";
return "";
}
public static String  _lblmin1minus_click() throws Exception{
 //BA.debugLineNum = 543;BA.debugLine="Sub lblMin1Minus_Click";
 //BA.debugLineNum = 544;BA.debugLine="UhrzeitSubstrahieren(0, 1)";
_uhrzeitsubstrahieren((int) (0),(int) (1));
 //BA.debugLineNum = 545;BA.debugLine="End Sub";
return "";
}
public static String  _lblmin1plus_click() throws Exception{
 //BA.debugLineNum = 538;BA.debugLine="Sub lblMin1Plus_Click";
 //BA.debugLineNum = 539;BA.debugLine="UhrzeitAddieren(0, 1)";
_uhrzeitaddieren((int) (0),(int) (1));
 //BA.debugLineNum = 540;BA.debugLine="End Sub";
return "";
}
public static String  _lblminminus_click() throws Exception{
 //BA.debugLineNum = 533;BA.debugLine="Sub lblMinMinus_Click";
 //BA.debugLineNum = 534;BA.debugLine="UhrzeitSubstrahieren(0, 5)";
_uhrzeitsubstrahieren((int) (0),(int) (5));
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return "";
}
public static String  _lblminplus_click() throws Exception{
 //BA.debugLineNum = 528;BA.debugLine="Sub lblMinPlus_Click";
 //BA.debugLineNum = 529;BA.debugLine="UhrzeitAddieren(0, 5)";
_uhrzeitaddieren((int) (0),(int) (5));
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return "";
}
public static String  _lblpfeil_click() throws Exception{
 //BA.debugLineNum = 449;BA.debugLine="Sub lblPfeil_Click";
 //BA.debugLineNum = 450;BA.debugLine="StartActivity(Treffpunkt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._treffpunkt.getObject()));
 //BA.debugLineNum = 451;BA.debugLine="End Sub";
return "";
}
public static String  _lblsonder_click() throws Exception{
 //BA.debugLineNum = 3404;BA.debugLine="Sub lblSonder_Click";
 //BA.debugLineNum = 3405;BA.debugLine="SonstigeTaetigkeitenAktiv(bSonstTaetigkeit)";
_sonstigetaetigkeitenaktiv(_bsonsttaetigkeit);
 //BA.debugLineNum = 3406;BA.debugLine="End Sub";
return "";
}
public static String  _lblstammdaten1_click() throws Exception{
 //BA.debugLineNum = 1741;BA.debugLine="Sub lblStammdaten1_Click";
 //BA.debugLineNum = 1742;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1743;BA.debugLine="panKlassen.Visible = False";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1744;BA.debugLine="panStammdaten1.Visible = True";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1745;BA.debugLine="End Sub";
return "";
}
public static String  _lblstammdaten2_click() throws Exception{
 //BA.debugLineNum = 1747;BA.debugLine="Sub lblStammdaten2_Click";
 //BA.debugLineNum = 1748;BA.debugLine="panStammdaten1.Visible = False";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1749;BA.debugLine="panKlassen.Visible = False";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1750;BA.debugLine="panStammdaten2.Visible = True";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1751;BA.debugLine="End Sub";
return "";
}
public static void  _lblstammemail_click() throws Exception{
ResumableSub_lblStammEmail_Click rsub = new ResumableSub_lblStammEmail_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lblStammEmail_Click extends BA.ResumableSub {
public ResumableSub_lblStammEmail_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
anywheresoftware.b4a.phone.Phone.Email _message = null;
String _mail = "";
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 3382;BA.debugLine="Dim message As Email";
_message = new anywheresoftware.b4a.phone.Phone.Email();
 //BA.debugLineNum = 3383;BA.debugLine="Dim mail As String";
_mail = "";
 //BA.debugLineNum = 3385;BA.debugLine="mail = lblStammEmail.Text";
_mail = parent.mostCurrent._lblstammemail.getText();
 //BA.debugLineNum = 3386;BA.debugLine="If mail <> \"\" Then";
if (true) break;

case 1:
//if
this.state = 8;
if ((_mail).equals("") == false) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 3387;BA.debugLine="Msgbox2Async(\"Möchten Sie eine Email an den Schü";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Möchten Sie eine Email an den Schüler '"+parent._sausgewaehlterschueler+"' senden?"),BA.ObjectToCharSequence("eMail versenden"),"Ja","","Nein",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3388;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 9;
return;
case 9:
//C
this.state = 4;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 3389;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 3390;BA.debugLine="message.To.Add(mail)";
_message.To.Add((Object)(_mail));
 //BA.debugLineNum = 3391;BA.debugLine="StartActivity(message.GetIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_message.GetIntent()));
 if (true) break;

case 7:
//C
this.state = 8;
;
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 3394;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _lblstammhandy_click() throws Exception{
ResumableSub_lblStammHandy_Click rsub = new ResumableSub_lblStammHandy_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lblStammHandy_Click extends BA.ResumableSub {
public ResumableSub_lblStammHandy_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
anywheresoftware.b4a.phone.Phone.PhoneCalls _calls = null;
String _phone = "";
String _permission = "";
boolean _result = false;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 3331;BA.debugLine="Dim calls As PhoneCalls";
_calls = new anywheresoftware.b4a.phone.Phone.PhoneCalls();
 //BA.debugLineNum = 3332;BA.debugLine="Dim phone As String";
_phone = "";
 //BA.debugLineNum = 3334;BA.debugLine="phone = lblStammHandy.Text";
_phone = parent.mostCurrent._lblstammhandy.getText();
 //BA.debugLineNum = 3335;BA.debugLine="If phone <> \"\" Then";
if (true) break;

case 1:
//if
this.state = 20;
if ((_phone).equals("") == false) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 3336;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_CALL_PHONE)";
parent._rp.CheckAndRequest(processBA,parent._rp.PERMISSION_CALL_PHONE);
 //BA.debugLineNum = 3337;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 21;
return;
case 21:
//C
this.state = 4;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 3338;BA.debugLine="If Result Then";
if (true) break;

case 4:
//if
this.state = 19;
if (_result) { 
this.state = 6;
}else {
this.state = 18;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 3339;BA.debugLine="Msgbox2Async(\"Möchten Sie den Schüler '\" & sAus";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Möchten Sie den Schüler '"+parent._sausgewaehlterschueler+"' anrufen?"),BA.ObjectToCharSequence("Anruf"),"Ja","","Nein",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3340;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 22;
return;
case 22:
//C
this.state = 7;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 3342;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 16;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 3343;BA.debugLine="Try";
if (true) break;

case 10:
//try
this.state = 15;
this.catchState = 14;
this.state = 12;
if (true) break;

case 12:
//C
this.state = 15;
this.catchState = 14;
 //BA.debugLineNum = 3344;BA.debugLine="StartActivity(calls.Call(phone))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_calls.Call(_phone)));
 if (true) break;

case 14:
//C
this.state = 15;
this.catchState = 0;
 //BA.debugLineNum = 3346;BA.debugLine="Log(\"Anruf klappt nicht\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07798800","Anruf klappt nicht",0);
 if (true) break;
if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
;
 if (true) break;

case 16:
//C
this.state = 19;
;
 if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 3350;BA.debugLine="Log(\"Keine Berechtigung zum Telefonieren\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07798804","Keine Berechtigung zum Telefonieren",0);
 if (true) break;

case 19:
//C
this.state = 20;
;
 if (true) break;

case 20:
//C
this.state = -1;
;
 //BA.debugLineNum = 3353;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static void  _lblstammtelefon_click() throws Exception{
ResumableSub_lblStammTelefon_Click rsub = new ResumableSub_lblStammTelefon_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lblStammTelefon_Click extends BA.ResumableSub {
public ResumableSub_lblStammTelefon_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
anywheresoftware.b4a.phone.Phone.PhoneCalls _calls = null;
String _phone = "";
String _permission = "";
boolean _result = false;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 3306;BA.debugLine="Dim calls As PhoneCalls";
_calls = new anywheresoftware.b4a.phone.Phone.PhoneCalls();
 //BA.debugLineNum = 3307;BA.debugLine="Dim phone As String";
_phone = "";
 //BA.debugLineNum = 3309;BA.debugLine="phone = lblStammTelefon.Text";
_phone = parent.mostCurrent._lblstammtelefon.getText();
 //BA.debugLineNum = 3310;BA.debugLine="If phone <> \"\" Then";
if (true) break;

case 1:
//if
this.state = 20;
if ((_phone).equals("") == false) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 3311;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_CALL_PHONE)";
parent._rp.CheckAndRequest(processBA,parent._rp.PERMISSION_CALL_PHONE);
 //BA.debugLineNum = 3312;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 21;
return;
case 21:
//C
this.state = 4;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 3313;BA.debugLine="If Result Then";
if (true) break;

case 4:
//if
this.state = 19;
if (_result) { 
this.state = 6;
}else {
this.state = 18;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 3314;BA.debugLine="Msgbox2Async(\"Möchten Sie den Schüler '\" & sAus";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Möchten Sie den Schüler '"+parent._sausgewaehlterschueler+"' anrufen?"),BA.ObjectToCharSequence("Anruf"),"Ja","","Nein",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3315;BA.debugLine="Wait For msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 22;
return;
case 22:
//C
this.state = 7;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 3316;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 16;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 3317;BA.debugLine="Try";
if (true) break;

case 10:
//try
this.state = 15;
this.catchState = 14;
this.state = 12;
if (true) break;

case 12:
//C
this.state = 15;
this.catchState = 14;
 //BA.debugLineNum = 3318;BA.debugLine="StartActivity(calls.Call(phone))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_calls.Call(_phone)));
 if (true) break;

case 14:
//C
this.state = 15;
this.catchState = 0;
 //BA.debugLineNum = 3320;BA.debugLine="Log(\"Anruf klappt nicht\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07733263","Anruf klappt nicht",0);
 if (true) break;
if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
;
 if (true) break;

case 16:
//C
this.state = 19;
;
 if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 3324;BA.debugLine="Log(\"Keine Berechtigung zum Telefonieren\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07733267","Keine Berechtigung zum Telefonieren",0);
 if (true) break;

case 19:
//C
this.state = 20;
;
 if (true) break;

case 20:
//C
this.state = -1;
;
 //BA.debugLineNum = 3327;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _lblstammtelefonarbeit_click() throws Exception{
ResumableSub_lblStammTelefonArbeit_Click rsub = new ResumableSub_lblStammTelefonArbeit_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lblStammTelefonArbeit_Click extends BA.ResumableSub {
public ResumableSub_lblStammTelefonArbeit_Click(fadata.mobil.main parent) {
this.parent = parent;
}
fadata.mobil.main parent;
anywheresoftware.b4a.phone.Phone.PhoneCalls _calls = null;
String _phone = "";
String _permission = "";
boolean _result = false;
int _iresult = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 3357;BA.debugLine="Dim calls As PhoneCalls";
_calls = new anywheresoftware.b4a.phone.Phone.PhoneCalls();
 //BA.debugLineNum = 3358;BA.debugLine="Dim phone As String";
_phone = "";
 //BA.debugLineNum = 3360;BA.debugLine="phone = lblStammTelefonArbeit.Text";
_phone = parent.mostCurrent._lblstammtelefonarbeit.getText();
 //BA.debugLineNum = 3361;BA.debugLine="If phone <> \"\" Then";
if (true) break;

case 1:
//if
this.state = 20;
if ((_phone).equals("") == false) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 3362;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_CALL_PHONE)";
parent._rp.CheckAndRequest(processBA,parent._rp.PERMISSION_CALL_PHONE);
 //BA.debugLineNum = 3363;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 21;
return;
case 21:
//C
this.state = 4;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 3364;BA.debugLine="If Result Then";
if (true) break;

case 4:
//if
this.state = 19;
if (_result) { 
this.state = 6;
}else {
this.state = 18;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 3365;BA.debugLine="Msgbox2Async(\"Möchten Sie den Schüler '\" & sAus";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Möchten Sie den Schüler '"+parent._sausgewaehlterschueler+"' anrufen?"),BA.ObjectToCharSequence("Anruf"),"Ja","","Nein",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3366;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 22;
return;
case 22:
//C
this.state = 7;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 3367;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 7:
//if
this.state = 16;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 3368;BA.debugLine="Try";
if (true) break;

case 10:
//try
this.state = 15;
this.catchState = 14;
this.state = 12;
if (true) break;

case 12:
//C
this.state = 15;
this.catchState = 14;
 //BA.debugLineNum = 3369;BA.debugLine="StartActivity(calls.Call(phone))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_calls.Call(_phone)));
 if (true) break;

case 14:
//C
this.state = 15;
this.catchState = 0;
 //BA.debugLineNum = 3371;BA.debugLine="Log(\"Anruf klappt nicht\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07864335","Anruf klappt nicht",0);
 if (true) break;
if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
;
 if (true) break;

case 16:
//C
this.state = 19;
;
 if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 3375;BA.debugLine="Log(\"Keine Berechtigung zum Telefonieren\")";
anywheresoftware.b4a.keywords.Common.LogImpl("07864339","Keine Berechtigung zum Telefonieren",0);
 if (true) break;

case 19:
//C
this.state = 20;
;
 if (true) break;

case 20:
//C
this.state = -1;
;
 //BA.debugLineNum = 3378;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _lblstdminus_click() throws Exception{
 //BA.debugLineNum = 523;BA.debugLine="Sub lblStdMinus_Click";
 //BA.debugLineNum = 524;BA.debugLine="UhrzeitSubstrahieren(1, 0)";
_uhrzeitsubstrahieren((int) (1),(int) (0));
 //BA.debugLineNum = 525;BA.debugLine="End Sub";
return "";
}
public static String  _lblstdplus_click() throws Exception{
 //BA.debugLineNum = 518;BA.debugLine="Sub lblStdPlus_Click";
 //BA.debugLineNum = 519;BA.debugLine="UhrzeitAddieren(1, 0)";
_uhrzeitaddieren((int) (1),(int) (0));
 //BA.debugLineNum = 520;BA.debugLine="End Sub";
return "";
}
public static String  _lblzahlalle_click() throws Exception{
 //BA.debugLineNum = 564;BA.debugLine="Sub lblZahlAlle_Click";
 //BA.debugLineNum = 565;BA.debugLine="bZahlDatum = True";
_bzahldatum = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 567;BA.debugLine="lblZahlAlle.Color = Colors.ARGB(255, 144, 238, 14";
mostCurrent._lblzahlalle.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (144),(int) (238),(int) (144)));
 //BA.debugLineNum = 568;BA.debugLine="lblZahlTag.Color = Colors.ARGB(255, 211, 211, 211";
mostCurrent._lblzahltag.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 //BA.debugLineNum = 570;BA.debugLine="ZahlungenHolen(True)";
_zahlungenholen(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 571;BA.debugLine="End Sub";
return "";
}
public static String  _lblzahlauswahlzahlungenfuer_click() throws Exception{
 //BA.debugLineNum = 444;BA.debugLine="Sub lblZahlAuswahlZahlungenFuer_Click";
 //BA.debugLineNum = 445;BA.debugLine="StartActivity(ZahlungenFuer)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._zahlungenfuer.getObject()));
 //BA.debugLineNum = 446;BA.debugLine="End Sub";
return "";
}
public static String  _lblzahlspeichern_click() throws Exception{
String _sbetrag = "";
int _igesamt = 0;
int _ipos = 0;
 //BA.debugLineNum = 586;BA.debugLine="Sub lblZahlSpeichern_Click";
 //BA.debugLineNum = 587;BA.debugLine="Dim sBetrag As String";
_sbetrag = "";
 //BA.debugLineNum = 590;BA.debugLine="If txtZahlBetrag.Text <> \"\" Then";
if ((mostCurrent._txtzahlbetrag.getText()).equals("") == false) { 
 //BA.debugLineNum = 591;BA.debugLine="If txtZahlBetrag.Text.IndexOf(\".\") = -1 And txtZ";
if (mostCurrent._txtzahlbetrag.getText().indexOf(".")==-1 && mostCurrent._txtzahlbetrag.getText().indexOf(",")==-1) { 
 //BA.debugLineNum = 592;BA.debugLine="sBetrag = txtZahlBetrag.Text & \".00\"";
_sbetrag = mostCurrent._txtzahlbetrag.getText()+".00";
 }else {
 //BA.debugLineNum = 594;BA.debugLine="Dim iGesamt, iPos As Int";
_igesamt = 0;
_ipos = 0;
 //BA.debugLineNum = 597;BA.debugLine="If txtZahlBetrag.Text.IndexOf(\".\") > -1 Then";
if (mostCurrent._txtzahlbetrag.getText().indexOf(".")>-1) { 
 //BA.debugLineNum = 598;BA.debugLine="iGesamt = txtZahlBetrag.Text.Length";
_igesamt = mostCurrent._txtzahlbetrag.getText().length();
 //BA.debugLineNum = 599;BA.debugLine="iPos = txtZahlBetrag.Text.LastIndexOf(\".\")";
_ipos = mostCurrent._txtzahlbetrag.getText().lastIndexOf(".");
 }else {
 //BA.debugLineNum = 601;BA.debugLine="iGesamt = txtZahlBetrag.Text.Length";
_igesamt = mostCurrent._txtzahlbetrag.getText().length();
 //BA.debugLineNum = 602;BA.debugLine="iPos = txtZahlBetrag.Text.LastIndexOf(\",\")";
_ipos = mostCurrent._txtzahlbetrag.getText().lastIndexOf(",");
 };
 //BA.debugLineNum = 605;BA.debugLine="If (iGesamt - (iPos + 1)) > 2 Then";
if ((_igesamt-(_ipos+1))>2) { 
 //BA.debugLineNum = 606;BA.debugLine="txtZahlBetrag.Text = txtZahlBetrag.Text.SubStr";
mostCurrent._txtzahlbetrag.setText(BA.ObjectToCharSequence(mostCurrent._txtzahlbetrag.getText().substring((int) (0),(int) (_ipos+3))));
 }else if((_igesamt-(_ipos+1))==1) { 
 //BA.debugLineNum = 608;BA.debugLine="txtZahlBetrag.Text = txtZahlBetrag.Text & \"0\"";
mostCurrent._txtzahlbetrag.setText(BA.ObjectToCharSequence(mostCurrent._txtzahlbetrag.getText()+"0"));
 };
 //BA.debugLineNum = 611;BA.debugLine="sBetrag = txtZahlBetrag.Text.Replace(\",\", \".\")";
_sbetrag = mostCurrent._txtzahlbetrag.getText().replace(",",".");
 };
 //BA.debugLineNum = 614;BA.debugLine="If Helper.ZahlungenSpeichern(sBetrag, lblZahlDat";
if (mostCurrent._helper._zahlungenspeichern /*boolean*/ (mostCurrent.activityBA,_sbetrag,mostCurrent._lblzahldatum.getText(),mostCurrent._lblzahlauswahlzahlungenfuer.getText())) { 
 //BA.debugLineNum = 615;BA.debugLine="ZahlungenHolen(bZahlDatum)";
_zahlungenholen(_bzahldatum);
 //BA.debugLineNum = 616;BA.debugLine="txtZahlBetrag.Text = \"\"";
mostCurrent._txtzahlbetrag.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 617;BA.debugLine="bZahlBearbeiten = False";
_bzahlbearbeiten = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 618;BA.debugLine="lblZahlZeileBearbeiten.Color = Colors.ARGB(255,";
mostCurrent._lblzahlzeilebearbeiten.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 };
 };
 //BA.debugLineNum = 622;BA.debugLine="End Sub";
return "";
}
public static String  _lblzahltag_click() throws Exception{
 //BA.debugLineNum = 554;BA.debugLine="Sub lblZahlTag_Click";
 //BA.debugLineNum = 555;BA.debugLine="bZahlDatum = False";
_bzahldatum = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 557;BA.debugLine="lblZahlTag.Color = Colors.ARGB(255, 144, 238, 144";
mostCurrent._lblzahltag.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (144),(int) (238),(int) (144)));
 //BA.debugLineNum = 558;BA.debugLine="lblZahlAlle.Color = Colors.ARGB(255, 211, 211, 21";
mostCurrent._lblzahlalle.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 //BA.debugLineNum = 560;BA.debugLine="ZahlungenHolen(False)";
_zahlungenholen(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 561;BA.debugLine="End Sub";
return "";
}
public static String  _lblzahlzeilebearbeiten_click() throws Exception{
 //BA.debugLineNum = 574;BA.debugLine="Sub lblZahlZeileBearbeiten_Click";
 //BA.debugLineNum = 575;BA.debugLine="If bZahlBearbeiten = False Then";
if (_bzahlbearbeiten==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 576;BA.debugLine="bZahlBearbeiten = True";
_bzahlbearbeiten = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 577;BA.debugLine="lblZahlZeileBearbeiten.Color = Colors.Red";
mostCurrent._lblzahlzeilebearbeiten.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 }else {
 //BA.debugLineNum = 579;BA.debugLine="bZahlBearbeiten = False";
_bzahlbearbeiten = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 580;BA.debugLine="lblZahlZeileBearbeiten.Color = Colors.ARGB(255,";
mostCurrent._lblzahlzeilebearbeiten.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 //BA.debugLineNum = 581;BA.debugLine="txtZahlBetrag.Text = \"\"";
mostCurrent._txtzahlbetrag.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 583;BA.debugLine="End Sub";
return "";
}
public static String  _listinitialisieren() throws Exception{
 //BA.debugLineNum = 1216;BA.debugLine="Sub ListInitialisieren";
 //BA.debugLineNum = 1217;BA.debugLine="lstSchueler.Initialize(\"LstSchueler\")";
mostCurrent._lstschueler.Initialize(mostCurrent.activityBA,"LstSchueler");
 //BA.debugLineNum = 1218;BA.debugLine="Helper.FillSchuelerListView(lstSchueler)";
mostCurrent._helper._fillschuelerlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstschueler);
 //BA.debugLineNum = 1220;BA.debugLine="lstFahrlehrer.Initialize(\"LstFahrlehrer\")";
mostCurrent._lstfahrlehrer.Initialize(mostCurrent.activityBA,"LstFahrlehrer");
 //BA.debugLineNum = 1221;BA.debugLine="Helper.FillFahrlehrerListView(lstFahrlehrer)";
mostCurrent._helper._fillfahrlehrerlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstfahrlehrer);
 //BA.debugLineNum = 1223;BA.debugLine="lstKfz.Initialize(\"LstKFZ\")";
mostCurrent._lstkfz.Initialize(mostCurrent.activityBA,"LstKFZ");
 //BA.debugLineNum = 1224;BA.debugLine="Helper.FillKfzListView(lstKfz)";
mostCurrent._helper._fillkfzlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstkfz);
 //BA.debugLineNum = 1226;BA.debugLine="lstZahlungFuer.Initialize(\"LstZahlungFuer\")";
mostCurrent._lstzahlungfuer.Initialize(mostCurrent.activityBA,"LstZahlungFuer");
 //BA.debugLineNum = 1227;BA.debugLine="Helper.FillZahlungenFuerListView(lstZahlungFuer)";
mostCurrent._helper._fillzahlungenfuerlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstzahlungfuer);
 //BA.debugLineNum = 1229;BA.debugLine="lstSchuelerKlasse.Initialize(\"LstSchuelerKlasse\")";
mostCurrent._lstschuelerklasse.Initialize(mostCurrent.activityBA,"LstSchuelerKlasse");
 //BA.debugLineNum = 1230;BA.debugLine="Helper.FillSchuelerKlasseListView(lstSchuelerKlas";
mostCurrent._helper._fillschuelerklasselistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstschuelerklasse);
 //BA.debugLineNum = 1232;BA.debugLine="lstKlassen.Initialize(\"LstKlassen\")";
mostCurrent._lstklassen.Initialize(mostCurrent.activityBA,"LstKlassen");
 //BA.debugLineNum = 1233;BA.debugLine="Helper.FillKlassenListView(lstKlassen)";
mostCurrent._helper._fillklassenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstklassen);
 //BA.debugLineNum = 1235;BA.debugLine="lstFahrtbezeichnung.Initialize(\"LstFahrtbezeichnu";
mostCurrent._lstfahrtbezeichnung.Initialize(mostCurrent.activityBA,"LstFahrtbezeichnung");
 //BA.debugLineNum = 1236;BA.debugLine="Helper.FillFahrtenbezeichnungListView(lstFahrtbez";
mostCurrent._helper._fillfahrtenbezeichnunglistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstfahrtbezeichnung);
 //BA.debugLineNum = 1238;BA.debugLine="lstSonstigeTaetigkeiten.Initialize(\"LstSonstigeTa";
mostCurrent._lstsonstigetaetigkeiten.Initialize(mostCurrent.activityBA,"LstSonstigeTaetigkeiten");
 //BA.debugLineNum = 1239;BA.debugLine="Helper.FillSonstigeTaetigkeitenListView(lstSonsti";
mostCurrent._helper._fillsonstigetaetigkeitenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstsonstigetaetigkeiten);
 //BA.debugLineNum = 1241;BA.debugLine="lstTreffpunkt.Initialize(\"LstTreffpunkt\")";
mostCurrent._lsttreffpunkt.Initialize(mostCurrent.activityBA,"LstTreffpunkt");
 //BA.debugLineNum = 1242;BA.debugLine="Helper.FillTreffpunktListView(lstTreffpunkt)";
mostCurrent._helper._filltreffpunktlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lsttreffpunkt);
 //BA.debugLineNum = 1244;BA.debugLine="lstBegleitfahrzeug.Initialize(\"LstBegleitfahrzeug";
mostCurrent._lstbegleitfahrzeug.Initialize(mostCurrent.activityBA,"LstBegleitfahrzeug");
 //BA.debugLineNum = 1245;BA.debugLine="Helper.FillBegleitfahrzeugListView(lstBegleitfahr";
mostCurrent._helper._fillbegleitfahrzeuglistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstbegleitfahrzeug);
 //BA.debugLineNum = 1248;BA.debugLine="lstTermine.Initialize(\"LstTermine\")";
mostCurrent._lsttermine.Initialize(mostCurrent.activityBA,"LstTermine");
 //BA.debugLineNum = 1249;BA.debugLine="lstAusbildungLernThemen.Initialize(\"LstAusbildung";
mostCurrent._lstausbildunglernthemen.Initialize(mostCurrent.activityBA,"LstAusbildungLernThemen");
 //BA.debugLineNum = 1250;BA.debugLine="lstAusbildungLernPunkte.Initialize(\"LstAusbildung";
mostCurrent._lstausbildunglernpunkte.Initialize(mostCurrent.activityBA,"LstAusbildungLernPunkte");
 //BA.debugLineNum = 1252;BA.debugLine="End Sub";
return "";
}
public static String  _lsttermine_itemclick(int _position,Object _value) throws Exception{
String _serstezeile = "";
String _szweitezeile = "";
String _sstartzeit = "";
String _sdauer = "";
String _sklasse = "";
String _sfahrtbezeichnung = "";
String _sunterschrift = "";
String _srest = "";
String _streffpunkt = "";
String _stermin = "";
int _pos = 0;
fadata.mobil.main._listviewdata _lvdtermine = null;
 //BA.debugLineNum = 1348;BA.debugLine="Sub lstTermine_ItemClick (Position As Int, Value A";
 //BA.debugLineNum = 1349;BA.debugLine="Dim sErsteZeile, sZweiteZeile As String";
_serstezeile = "";
_szweitezeile = "";
 //BA.debugLineNum = 1351;BA.debugLine="Dim sStartzeit, sDauer, sKlasse, sFahrtbezeichnun";
_sstartzeit = "";
_sdauer = "";
_sklasse = "";
_sfahrtbezeichnung = "";
_sunterschrift = "";
_srest = "";
_streffpunkt = "";
_stermin = "";
 //BA.debugLineNum = 1352;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 1355;BA.debugLine="bDatenVorhanden = True";
_bdatenvorhanden = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1357;BA.debugLine="Dim lvdTermine As ListViewData";
_lvdtermine = new fadata.mobil.main._listviewdata();
 //BA.debugLineNum = 1358;BA.debugLine="lvdTermine = Value";
_lvdtermine = (fadata.mobil.main._listviewdata)(_value);
 //BA.debugLineNum = 1360;BA.debugLine="sErsteZeile = lvdTermine.FirstRow";
_serstezeile = _lvdtermine.FirstRow /*String*/ ;
 //BA.debugLineNum = 1361;BA.debugLine="sZweiteZeile = lvdTermine.SecondRow";
_szweitezeile = _lvdtermine.SecondRow /*String*/ ;
 //BA.debugLineNum = 1364;BA.debugLine="pos = sErsteZeile.IndexOf(\" \")";
_pos = _serstezeile.indexOf(" ");
 //BA.debugLineNum = 1365;BA.debugLine="sStartzeit = sErsteZeile.SubString2(0, pos).Trim";
_sstartzeit = _serstezeile.substring((int) (0),_pos).trim();
 //BA.debugLineNum = 1372;BA.debugLine="lblAnzDatum.Text = btKalender.Text";
mostCurrent._lblanzdatum.setText(BA.ObjectToCharSequence(mostCurrent._btkalender.getText()));
 //BA.debugLineNum = 1375;BA.debugLine="pos = sZweiteZeile.IndexOf(TAB & TAB)";
_pos = _szweitezeile.indexOf(anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB);
 //BA.debugLineNum = 1376;BA.debugLine="sDauer = sZweiteZeile.SubString2(0, pos).Trim";
_sdauer = _szweitezeile.substring((int) (0),_pos).trim();
 //BA.debugLineNum = 1377;BA.debugLine="sRest = sZweiteZeile.SubString(pos + 2)";
_srest = _szweitezeile.substring((int) (_pos+2));
 //BA.debugLineNum = 1379;BA.debugLine="pos = sRest.IndexOf(TAB & TAB)";
_pos = _srest.indexOf(anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB);
 //BA.debugLineNum = 1380;BA.debugLine="sKlasse = sRest.SubString2(0, pos).Trim";
_sklasse = _srest.substring((int) (0),_pos).trim();
 //BA.debugLineNum = 1381;BA.debugLine="sRest = sRest.SubString(pos + 2)";
_srest = _srest.substring((int) (_pos+2));
 //BA.debugLineNum = 1383;BA.debugLine="pos = sRest.IndexOf(TAB & TAB)";
_pos = _srest.indexOf(anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB);
 //BA.debugLineNum = 1384;BA.debugLine="sFahrtbezeichnung = sRest.SubString2(0, pos).Trim";
_sfahrtbezeichnung = _srest.substring((int) (0),_pos).trim();
 //BA.debugLineNum = 1385;BA.debugLine="sRest = sRest.SubString(pos + 2)";
_srest = _srest.substring((int) (_pos+2));
 //BA.debugLineNum = 1387;BA.debugLine="pos = sRest.IndexOf(TAB & TAB)";
_pos = _srest.indexOf(anywheresoftware.b4a.keywords.Common.TAB+anywheresoftware.b4a.keywords.Common.TAB);
 //BA.debugLineNum = 1388;BA.debugLine="sUnterschrift = sRest.SubString2(0, pos).Trim";
_sunterschrift = _srest.substring((int) (0),_pos).trim();
 //BA.debugLineNum = 1389;BA.debugLine="sTreffpunkt = sRest.SubString(pos + 2).Trim";
_streffpunkt = _srest.substring((int) (_pos+2)).trim();
 //BA.debugLineNum = 1393;BA.debugLine="lblAbfahrtAnzeige.Text = sStartzeit";
mostCurrent._lblabfahrtanzeige.setText(BA.ObjectToCharSequence(_sstartzeit));
 //BA.debugLineNum = 1394;BA.debugLine="lblEinheitAnzeige.Text = sDauer";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(_sdauer));
 //BA.debugLineNum = 1395;BA.debugLine="sTermin = btKalender.Text";
_stermin = mostCurrent._btkalender.getText();
 //BA.debugLineNum = 1397;BA.debugLine="lblAuswahlKlasse.Text = sKlasse";
mostCurrent._lblauswahlklasse.setText(BA.ObjectToCharSequence(_sklasse));
 //BA.debugLineNum = 1398;BA.debugLine="lblAuswahlFahrbezeichnung.Text = sFahrtbezeichnun";
mostCurrent._lblauswahlfahrbezeichnung.setText(BA.ObjectToCharSequence(_sfahrtbezeichnung));
 //BA.debugLineNum = 1399;BA.debugLine="txtAuswahlTreffpunkt.Text = sTreffpunkt";
mostCurrent._txtauswahltreffpunkt.setText(BA.ObjectToCharSequence(_streffpunkt));
 //BA.debugLineNum = 1402;BA.debugLine="iTerminID = Helper.TerminIDHolen(sTermin, sStartz";
_iterminid = mostCurrent._helper._terminidholen /*int*/ (mostCurrent.activityBA,_stermin,_sstartzeit,_sdauer,_sklasse,_sfahrtbezeichnung,_streffpunkt);
 //BA.debugLineNum = 1403;BA.debugLine="lblAnzSchueler.Text = sAusgewaehlterSchueler";
mostCurrent._lblanzschueler.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1406;BA.debugLine="lblAuswahlKfz.Text = Helper.GetKFZ(iTerminID)";
mostCurrent._lblauswahlkfz.setText(BA.ObjectToCharSequence(mostCurrent._helper._getkfz /*String*/ (mostCurrent.activityBA,_iterminid)));
 //BA.debugLineNum = 1409;BA.debugLine="If Helper.CheckBegleitfahrzeug(sKlasse) Then";
if (mostCurrent._helper._checkbegleitfahrzeug /*boolean*/ (mostCurrent.activityBA,_sklasse)) { 
 //BA.debugLineNum = 1410;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = Helper.Begleitf";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(mostCurrent._helper._begleitfahrzeugholen /*String*/ (mostCurrent.activityBA,_iterminid)));
 //BA.debugLineNum = 1411;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = True";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1412;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.White";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 1414;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = \"\"";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1415;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = False";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1416;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.LightGr";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 };
 //BA.debugLineNum = 1420;BA.debugLine="panStart.Visible = True";
mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1421;BA.debugLine="panUnterschrift.Visible = False";
mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1422;BA.debugLine="panPruefung.Visible = False";
mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1425;BA.debugLine="thStart.CurrentTab = thStart.CurrentTab + 1";
mostCurrent._thstart.setCurrentTab((int) (mostCurrent._thstart.getCurrentTab()+1));
 //BA.debugLineNum = 1426;BA.debugLine="End Sub";
return "";
}
public static void  _lsttermine_itemlongclick(int _position,Object _value) throws Exception{
ResumableSub_lstTermine_ItemLongClick rsub = new ResumableSub_lstTermine_ItemLongClick(null,_position,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_lstTermine_ItemLongClick extends BA.ResumableSub {
public ResumableSub_lstTermine_ItemLongClick(fadata.mobil.main parent,int _position,Object _value) {
this.parent = parent;
this._position = _position;
this._value = _value;
}
fadata.mobil.main parent;
int _position;
Object _value;
int _iresult = 0;
String _sdatum = "";
String _sfirstline = "";
fadata.mobil.main._listviewdata _lvdtermine = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 1430;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 1431;BA.debugLine="Dim sDatum, sFirstline As String";
_sdatum = "";
_sfirstline = "";
 //BA.debugLineNum = 1432;BA.debugLine="Dim lvdTermine As ListViewData";
_lvdtermine = new fadata.mobil.main._listviewdata();
 //BA.debugLineNum = 1434;BA.debugLine="sDatum = btKalender.Text";
_sdatum = parent.mostCurrent._btkalender.getText();
 //BA.debugLineNum = 1435;BA.debugLine="lvdTermine = Value";
_lvdtermine = (fadata.mobil.main._listviewdata)(_value);
 //BA.debugLineNum = 1436;BA.debugLine="sFirstline = lvdTermine.FirstRow";
_sfirstline = _lvdtermine.FirstRow /*String*/ ;
 //BA.debugLineNum = 1438;BA.debugLine="Msgbox2Async(\"Soll der Termineintrag '\" & sFirstl";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Soll der Termineintrag '"+_sfirstline+"' wirklich gelöscht werden?"),BA.ObjectToCharSequence("Sicherheitsabfrage"),"Ja","","Nein",parent.mostCurrent._info,processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1439;BA.debugLine="Wait For Msgbox_Result(iResult As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 1;
_iresult = (Integer) result[0];
;
 //BA.debugLineNum = 1441;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 1443;BA.debugLine="If (Helper.TerminLoeschen(Position, sDatum)) The";
if (true) break;

case 4:
//if
this.state = 9;
if ((parent.mostCurrent._helper._terminloeschen /*boolean*/ (mostCurrent.activityBA,_position,_sdatum))) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 1444;BA.debugLine="ToastMessageShow(\"Termin erfolgreich gelöscht\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Termin erfolgreich gelöscht"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 1446;BA.debugLine="ToastMessageShow(\"Fehler beim Löschen des Termi";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Fehler beim Löschen des Termins"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1447;BA.debugLine="Log(\"Fehler beim Löschen des Termins: \" & sFirs";
anywheresoftware.b4a.keywords.Common.LogImpl("03538962","Fehler beim Löschen des Termins: "+_sfirstline+" "+_sdatum,0);
 if (true) break;

case 9:
//C
this.state = 10;
;
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 1451;BA.debugLine="TermineHolen(sDatum)";
_termineholen(_sdatum);
 //BA.debugLineNum = 1452;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lstzahlungen_itemclick(int _position,Object _value) throws Exception{
String[] _azahldaten = null;
 //BA.debugLineNum = 1457;BA.debugLine="Sub lstZahlungen_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 1458;BA.debugLine="Dim aZahlDaten() As String";
_azahldaten = new String[(int) (0)];
java.util.Arrays.fill(_azahldaten,"");
 //BA.debugLineNum = 1461;BA.debugLine="iZahlungsPosition = Position";
_izahlungsposition = _position;
 //BA.debugLineNum = 1463;BA.debugLine="If bZahlBearbeiten Then";
if (_bzahlbearbeiten) { 
 //BA.debugLineNum = 1464;BA.debugLine="If bZahlDatum = False Then";
if (_bzahldatum==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1466;BA.debugLine="aZahlDaten = Helper.ZahlungHolen(Position, Valu";
_azahldaten = mostCurrent._helper._zahlungholen /*String[]*/ (mostCurrent.activityBA,_position,_value,mostCurrent._lblzahldatum.getText());
 }else {
 //BA.debugLineNum = 1468;BA.debugLine="aZahlDaten = Helper.ZahlungHolen(Position, Valu";
_azahldaten = mostCurrent._helper._zahlungholen /*String[]*/ (mostCurrent.activityBA,_position,_value,"Alle");
 };
 //BA.debugLineNum = 1471;BA.debugLine="txtZahlBetrag.Text = aZahlDaten(1)";
mostCurrent._txtzahlbetrag.setText(BA.ObjectToCharSequence(_azahldaten[(int) (1)]));
 //BA.debugLineNum = 1472;BA.debugLine="lblZahlAuswahlZahlungenFuer.Text = aZahlDaten(2)";
mostCurrent._lblzahlauswahlzahlungenfuer.setText(BA.ObjectToCharSequence(_azahldaten[(int) (2)]));
 };
 //BA.debugLineNum = 1474;BA.debugLine="End Sub";
return "";
}
public static String  _lstzahlungen_itemlongclick(int _position,Object _value) throws Exception{
int _iresult = 0;
 //BA.debugLineNum = 1477;BA.debugLine="Sub lstZahlungen_ItemLongClick (Position As Int, V";
 //BA.debugLineNum = 1478;BA.debugLine="Dim iResult As Int";
_iresult = 0;
 //BA.debugLineNum = 1480;BA.debugLine="If bZahlBearbeiten Then";
if (_bzahlbearbeiten) { 
 //BA.debugLineNum = 1481;BA.debugLine="iResult = Msgbox2(\"Soll der Zahlungeintrag '\" &";
_iresult = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Soll der Zahlungeintrag '"+BA.ObjectToString(_value)+"' wirklich gelöscht werden?"),BA.ObjectToCharSequence("Sicherheitsabfrage"),"Ja","","Nein",(android.graphics.Bitmap)(mostCurrent._info.getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 1483;BA.debugLine="If iResult = DialogResponse.POSITIVE Then";
if (_iresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 1484;BA.debugLine="If Helper.ZahlungLoeschen(Position, lblZahlDatu";
if (mostCurrent._helper._zahlungloeschen /*boolean*/ (mostCurrent.activityBA,_position,mostCurrent._lblzahldatum.getText())) { 
 //BA.debugLineNum = 1485;BA.debugLine="ToastMessageShow(\"Zahlungeintrag gelöscht\", Fa";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Zahlungeintrag gelöscht"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1486;BA.debugLine="lblZahlZeileBearbeiten_Click";
_lblzahlzeilebearbeiten_click();
 //BA.debugLineNum = 1487;BA.debugLine="ZahlungenHolen(bZahlDatum)";
_zahlungenholen(_bzahldatum);
 };
 };
 };
 //BA.debugLineNum = 1491;BA.debugLine="End Sub";
return "";
}
public static String  _panunterschriftfeld_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 3398;BA.debugLine="Sub panUnterschriftFeld_Touch (Action As Int, X As";
 //BA.debugLineNum = 3399;BA.debugLine="SignatureCapture.Panel1_Touch(sigD, X, Y, Action)";
mostCurrent._signaturecapture._panel1_touch /*String*/ (mostCurrent.activityBA,mostCurrent._sigd,_x,_y,_action);
 //BA.debugLineNum = 3400;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
helper._process_globals();
dbutils._process_globals();
signaturecapture._process_globals();
readwriteini._process_globals();
sonstigetaetigkeiten._process_globals();
schueler._process_globals();
begleitfahrzeuge._process_globals();
fahrlehrer._process_globals();
fahrtbezeichnung._process_globals();
kalender._process_globals();
kfz._process_globals();
klassen._process_globals();
pruefergebnis._process_globals();
schuelerklasse._process_globals();
treffpunkt._process_globals();
zahlungenfuer._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Public VersionsNummer As String";
_versionsnummer = "";
 //BA.debugLineNum = 16;BA.debugLine="VersionsNummer = \"V1.0\"";
_versionsnummer = "V1.0";
 //BA.debugLineNum = 18;BA.debugLine="Public pm As PackageManager";
_pm = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Public rp As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 21;BA.debugLine="Public SourceFolder As String";
_sourcefolder = "";
 //BA.debugLineNum = 24;BA.debugLine="Public lv As LayoutValues 															'Layoutw";
_lv = new anywheresoftware.b4a.keywords.LayoutValues();
 //BA.debugLineNum = 25;BA.debugLine="Public sAusgewaehlterSchueler, aSchueler(0,0), aF";
_sausgewaehlterschueler = "";
_aschueler = new String[(int) (0)][];
{
int d0 = _aschueler.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_aschueler[i0] = new String[d1];
java.util.Arrays.fill(_aschueler[i0],"");
}
}
;
_afahrtbezeichnung = new String[(int) (0)][];
{
int d0 = _afahrtbezeichnung.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_afahrtbezeichnung[i0] = new String[d1];
java.util.Arrays.fill(_afahrtbezeichnung[i0],"");
}
}
;
_asonstigetaetigkeiten = new String[(int) (0)][];
{
int d0 = _asonstigetaetigkeiten.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_asonstigetaetigkeiten[i0] = new String[d1];
java.util.Arrays.fill(_asonstigetaetigkeiten[i0],"");
}
}
;
_atreffpunkt = new String[(int) (0)][];
{
int d0 = _atreffpunkt.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_atreffpunkt[i0] = new String[d1];
java.util.Arrays.fill(_atreffpunkt[i0],"");
}
}
;
_akfz = new String[(int) (0)][];
{
int d0 = _akfz.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_akfz[i0] = new String[d1];
java.util.Arrays.fill(_akfz[i0],"");
}
}
;
 //BA.debugLineNum = 27;BA.debugLine="Public iAusgewaehlterSchuelrID As Int";
_iausgewaehlterschuelrid = 0;
 //BA.debugLineNum = 29;BA.debugLine="Public bDatenVorhanden, bTreffpunktAuswahl, bFirs";
_bdatenvorhanden = false;
_btreffpunktauswahl = false;
_bfirsttime = false;
_bzahlbearbeiten = false;
_bzahldatum = false;
_bisselected = new boolean[(int) (10)];
;
_bsonsttaetigkeit = false;
_balleklassen = false;
_bkfzmanuel = false;
 //BA.debugLineNum = 31;BA.debugLine="Public iZahlungsPosition, iAusbildungBereich As I";
_izahlungsposition = 0;
_iausbildungbereich = 0;
 //BA.debugLineNum = 33;BA.debugLine="Public sFahrlehrer As String";
_sfahrlehrer = "";
 //BA.debugLineNum = 35;BA.debugLine="Public SQL1 As SQL															'zum Speichern d";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 38;BA.debugLine="Type ListViewData (FirstRow As String, SecondRow";
;
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _showbvftabs() throws Exception{
 //BA.debugLineNum = 2787;BA.debugLine="Sub ShowBVFTabs()";
 //BA.debugLineNum = 2788;BA.debugLine="pan20.Visible = False";
mostCurrent._pan20.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2789;BA.debugLine="Select iAusbildungBereich";
switch (_iausbildungbereich) {
case 1: {
 //BA.debugLineNum = 2791;BA.debugLine="lblGrundstufe.Text = \"Grundstufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Grundstufe"));
 //BA.debugLineNum = 2792;BA.debugLine="pan0.Visible = True";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2793;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2794;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2795;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2796;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2797;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2798;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2799;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2800;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2801;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2802;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2803;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2804;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2805;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2806;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2807;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2808;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2809;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2810;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2811;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 2: {
 //BA.debugLineNum = 2813;BA.debugLine="lblGrundstufe.Text = \"Grundstufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Grundstufe"));
 //BA.debugLineNum = 2814;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2815;BA.debugLine="pan1.Visible = True";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2816;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2817;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2818;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2819;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2820;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2821;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2822;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2823;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2824;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2825;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2826;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2827;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2828;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2829;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2830;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2831;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2832;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2833;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 3: {
 //BA.debugLineNum = 2835;BA.debugLine="lblGrundstufe.Text = \"Grundfahraufgaben\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Grundfahraufgaben"));
 //BA.debugLineNum = 2836;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2837;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2838;BA.debugLine="pan2.Visible = True";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2839;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2840;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2841;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2842;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2843;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2844;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2845;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2846;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2847;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2848;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2849;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2850;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2851;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2852;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2853;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2854;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2855;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 4: {
 //BA.debugLineNum = 2857;BA.debugLine="lblGrundstufe.Text = \"Aufbaustufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Aufbaustufe"));
 //BA.debugLineNum = 2858;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2859;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2860;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2861;BA.debugLine="pan3.Visible = True";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2862;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2863;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2864;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2865;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2866;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2867;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2868;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2869;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2870;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2871;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2872;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2873;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2874;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2875;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2876;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2877;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 5: {
 //BA.debugLineNum = 2879;BA.debugLine="lblGrundstufe.Text = \"Aufbaustufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Aufbaustufe"));
 //BA.debugLineNum = 2880;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2881;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2882;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2883;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2884;BA.debugLine="pan4.Visible = True";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2885;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2886;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2887;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2888;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2889;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2890;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2891;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2892;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2893;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2894;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2895;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2896;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2897;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2898;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2899;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 6: {
 //BA.debugLineNum = 2901;BA.debugLine="lblGrundstufe.Text = \"Leistungsstufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Leistungsstufe"));
 //BA.debugLineNum = 2902;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2903;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2904;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2905;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2906;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2907;BA.debugLine="pan5.Visible = True";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2908;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2909;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2910;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2911;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2912;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2913;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2914;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2915;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2916;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2917;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2918;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2919;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2920;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2921;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 7: {
 //BA.debugLineNum = 2923;BA.debugLine="lblGrundstufe.Text = \"Leistungsstufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Leistungsstufe"));
 //BA.debugLineNum = 2924;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2925;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2926;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2927;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2928;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2929;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2930;BA.debugLine="pan6.Visible = True";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2931;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2932;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2933;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2934;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2935;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2936;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2937;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2938;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2939;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2940;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2941;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2942;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2943;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 8: {
 //BA.debugLineNum = 2945;BA.debugLine="lblGrundstufe.Text = \"Leistungsstufe\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Leistungsstufe"));
 //BA.debugLineNum = 2946;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2947;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2948;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2949;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2950;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2951;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2952;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2953;BA.debugLine="pan7.Visible = True";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2954;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2955;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2956;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2957;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2958;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2959;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2960;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2961;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2962;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2963;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2964;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2965;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 9: {
 //BA.debugLineNum = 2967;BA.debugLine="lblGrundstufe.Text = \"Überland\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Überland"));
 //BA.debugLineNum = 2968;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2969;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2970;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2971;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2972;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2973;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2974;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2975;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2976;BA.debugLine="pan8.Visible = True";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2977;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2978;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2979;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2980;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2981;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2982;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2983;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2984;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2985;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2986;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2987;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 10: {
 //BA.debugLineNum = 2989;BA.debugLine="lblGrundstufe.Text = \"Überland\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Überland"));
 //BA.debugLineNum = 2990;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2991;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2992;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2993;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2994;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2995;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2996;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2997;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2998;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2999;BA.debugLine="pan9.Visible = True";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3000;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3001;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3002;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3003;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3004;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3005;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3006;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3007;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3008;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3009;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 11: {
 //BA.debugLineNum = 3011;BA.debugLine="lblGrundstufe.Text = \"Autobahn\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Autobahn"));
 //BA.debugLineNum = 3012;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3013;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3014;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3015;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3016;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3017;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3018;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3019;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3020;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3021;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3022;BA.debugLine="pan10.Visible = True";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3023;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3024;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3025;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3026;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3027;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3028;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3029;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3030;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3031;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 12: {
 //BA.debugLineNum = 3033;BA.debugLine="lblGrundstufe.Text = \"Autobahn\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Autobahn"));
 //BA.debugLineNum = 3034;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3035;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3036;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3037;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3038;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3039;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3040;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3041;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3042;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3043;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3044;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3045;BA.debugLine="pan11.Visible = True";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3046;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3047;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3048;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3049;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3050;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3051;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3052;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3053;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 13: {
 //BA.debugLineNum = 3055;BA.debugLine="lblGrundstufe.Text = \"Dämmerung/Dunkelheit\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Dämmerung/Dunkelheit"));
 //BA.debugLineNum = 3056;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3057;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3058;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3059;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3060;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3061;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3062;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3063;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3064;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3065;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3066;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3067;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3068;BA.debugLine="pan12.Visible = True";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3069;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3070;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3071;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3072;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3073;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3074;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3075;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 14: {
 //BA.debugLineNum = 3077;BA.debugLine="lblGrundstufe.Text = \"Reife- und Testprüfung\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Reife- und Testprüfung"));
 //BA.debugLineNum = 3078;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3079;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3080;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3081;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3082;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3083;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3084;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3085;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3086;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3087;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3088;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3089;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3090;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3091;BA.debugLine="pan13.Visible = True";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3092;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3093;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3094;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3095;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3096;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3097;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 15: {
 //BA.debugLineNum = 3099;BA.debugLine="lblGrundstufe.Text = \"Situative Bausteine\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Situative Bausteine"));
 //BA.debugLineNum = 3100;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3101;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3102;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3103;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3104;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3105;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3106;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3107;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3108;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3109;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3110;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3111;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3112;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3113;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3114;BA.debugLine="pan14.Visible = True";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3115;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3116;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3117;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3118;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3119;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 16: {
 //BA.debugLineNum = 3121;BA.debugLine="lblGrundstufe.Text = \"Situative Bausteine\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Situative Bausteine"));
 //BA.debugLineNum = 3122;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3123;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3124;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3125;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3126;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3127;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3128;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3129;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3130;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3131;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3132;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3133;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3134;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3135;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3136;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3137;BA.debugLine="pan15.Visible = True";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3138;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3139;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3140;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3141;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 17: {
 //BA.debugLineNum = 3143;BA.debugLine="lblGrundstufe.Text = \"Checkliste zur fahrtechni";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Checkliste zur fahrtechnischen Vorbereitung"));
 //BA.debugLineNum = 3144;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3145;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3146;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3147;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3148;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3149;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3150;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3151;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3152;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3153;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3154;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3155;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3156;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3157;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3158;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3159;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3160;BA.debugLine="pan16.Visible = True";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3161;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3162;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3163;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 18: {
 //BA.debugLineNum = 3165;BA.debugLine="lblGrundstufe.Text = \"Heizung und Lüftung\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Heizung und Lüftung"));
 //BA.debugLineNum = 3166;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3167;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3168;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3169;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3170;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3171;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3172;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3173;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3174;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3175;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3176;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3177;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3178;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3179;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3180;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3181;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3182;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3183;BA.debugLine="pan17.Visible = True";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3184;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3185;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 19: {
 //BA.debugLineNum = 3187;BA.debugLine="lblGrundstufe.Text = \"Betriebs- und Verkehrssic";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Betriebs- und Verkehrssicherheit"));
 //BA.debugLineNum = 3188;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3189;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3190;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3191;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3192;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3193;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3194;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3195;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3196;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3197;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3198;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3199;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3200;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3201;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3202;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3203;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3204;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3205;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3206;BA.debugLine="pan18.Visible = True";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3207;BA.debugLine="pan19.Visible = False";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break; }
case 20: {
 //BA.debugLineNum = 3209;BA.debugLine="lblGrundstufe.Text = \"Witterung\"";
mostCurrent._lblgrundstufe.setText(BA.ObjectToCharSequence("Witterung"));
 //BA.debugLineNum = 3210;BA.debugLine="pan0.Visible = False";
mostCurrent._pan0.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3211;BA.debugLine="pan1.Visible = False";
mostCurrent._pan1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3212;BA.debugLine="pan2.Visible = False";
mostCurrent._pan2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3213;BA.debugLine="pan3.Visible = False";
mostCurrent._pan3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3214;BA.debugLine="pan4.Visible = False";
mostCurrent._pan4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3215;BA.debugLine="pan5.Visible = False";
mostCurrent._pan5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3216;BA.debugLine="pan6.Visible = False";
mostCurrent._pan6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3217;BA.debugLine="pan7.Visible = False";
mostCurrent._pan7.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3218;BA.debugLine="pan8.Visible = False";
mostCurrent._pan8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3219;BA.debugLine="pan9.Visible = False";
mostCurrent._pan9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3220;BA.debugLine="pan10.Visible = False";
mostCurrent._pan10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3221;BA.debugLine="pan11.Visible = False";
mostCurrent._pan11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3222;BA.debugLine="pan12.Visible = False";
mostCurrent._pan12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3223;BA.debugLine="pan13.Visible = False";
mostCurrent._pan13.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3224;BA.debugLine="pan14.Visible = False";
mostCurrent._pan14.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3225;BA.debugLine="pan15.Visible = False";
mostCurrent._pan15.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3226;BA.debugLine="pan16.Visible = False";
mostCurrent._pan16.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3227;BA.debugLine="pan17.Visible = False";
mostCurrent._pan17.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3228;BA.debugLine="pan18.Visible = False";
mostCurrent._pan18.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3229;BA.debugLine="pan19.Visible = True";
mostCurrent._pan19.setVisible(anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 3231;BA.debugLine="End Sub";
return "";
}
public static String  _sonstigetaetigkeitenaktiv(boolean _btrue) throws Exception{
int _ipos = 0;
 //BA.debugLineNum = 3409;BA.debugLine="Sub SonstigeTaetigkeitenAktiv(bTrue As Boolean)";
 //BA.debugLineNum = 3410;BA.debugLine="Dim iPos As Int";
_ipos = 0;
 //BA.debugLineNum = 3412;BA.debugLine="If bTrue = False Then";
if (_btrue==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 3413;BA.debugLine="bSonstTaetigkeit = True";
_bsonsttaetigkeit = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 3414;BA.debugLine="lblSonder.Color = Colors.Red";
mostCurrent._lblsonder.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 3417;BA.debugLine="lblKlasse.Visible = False";
mostCurrent._lblklasse.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3418;BA.debugLine="lblAuswahlKlasse.Visible = False";
mostCurrent._lblauswahlklasse.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3419;BA.debugLine="lblAuswahlKlasseAlle.Visible = False";
mostCurrent._lblauswahlklassealle.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3420;BA.debugLine="lblKfz.Visible = False";
mostCurrent._lblkfz.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3421;BA.debugLine="lblAuswahlKfz.Visible = False";
mostCurrent._lblauswahlkfz.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3422;BA.debugLine="lblBegleitfahrzeug.Visible = False";
mostCurrent._lblbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3423;BA.debugLine="lblAuswahlBegleitfahrzeug.Visible = False";
mostCurrent._lblauswahlbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3424;BA.debugLine="lblTreffpunkt.Visible = False";
mostCurrent._lbltreffpunkt.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3425;BA.debugLine="txtAuswahlTreffpunkt.Visible = False";
mostCurrent._txtauswahltreffpunkt.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3426;BA.debugLine="lblPfeil.Visible = False";
mostCurrent._lblpfeil.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3427;BA.debugLine="btVormerken.Visible = False";
mostCurrent._btvormerken.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3429;BA.debugLine="sFahrtenbezeichnung = lstSonstigeTaetigkeiten.Ge";
mostCurrent._sfahrtenbezeichnung = BA.ObjectToString(mostCurrent._lstsonstigetaetigkeiten.GetItem(mostCurrent._sonstigetaetigkeiten._sonsttaetigkeitselected /*int*/ ));
 //BA.debugLineNum = 3430;BA.debugLine="iPos = sFahrtenbezeichnung.IndexOf(\" - \")";
_ipos = mostCurrent._sfahrtenbezeichnung.indexOf(" - ");
 //BA.debugLineNum = 3431;BA.debugLine="lblAuswahlFahrbezeichnung.Text = sFahrtenbezeich";
mostCurrent._lblauswahlfahrbezeichnung.setText(BA.ObjectToCharSequence(mostCurrent._sfahrtenbezeichnung.substring((int) (0),_ipos)));
 //BA.debugLineNum = 3433;BA.debugLine="If lblAuswahlFahrbezeichnung.Text = \"Unt\" Then";
if ((mostCurrent._lblauswahlfahrbezeichnung.getText()).equals("Unt")) { 
 //BA.debugLineNum = 3434;BA.debugLine="btUnterschrift.Visible = True";
mostCurrent._btunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3435;BA.debugLine="btSpeichern.Visible = False";
mostCurrent._btspeichern.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 3437;BA.debugLine="btUnterschrift.Visible = False";
mostCurrent._btunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3438;BA.debugLine="btSpeichern.Visible = True";
mostCurrent._btspeichern.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 3442;BA.debugLine="bSonstTaetigkeit = False";
_bsonsttaetigkeit = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3443;BA.debugLine="lblSonder.Color = Colors.ARGB(255, 211, 211, 211";
mostCurrent._lblsonder.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 //BA.debugLineNum = 3446;BA.debugLine="lblKlasse.Visible = True";
mostCurrent._lblklasse.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3447;BA.debugLine="lblAuswahlKlasse.Visible = True";
mostCurrent._lblauswahlklasse.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3448;BA.debugLine="lblAuswahlKlasseAlle.Visible = True";
mostCurrent._lblauswahlklassealle.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3449;BA.debugLine="lblKfz.Visible = True";
mostCurrent._lblkfz.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3450;BA.debugLine="lblAuswahlKfz.Visible = True";
mostCurrent._lblauswahlkfz.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3451;BA.debugLine="lblBegleitfahrzeug.Visible = True";
mostCurrent._lblbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3452;BA.debugLine="lblAuswahlBegleitfahrzeug.Visible = True";
mostCurrent._lblauswahlbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3453;BA.debugLine="lblTreffpunkt.Visible = True";
mostCurrent._lbltreffpunkt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3454;BA.debugLine="txtAuswahlTreffpunkt.Visible = True";
mostCurrent._txtauswahltreffpunkt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3455;BA.debugLine="lblPfeil.Visible = True";
mostCurrent._lblpfeil.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3456;BA.debugLine="btVormerken.Visible = True";
mostCurrent._btvormerken.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3457;BA.debugLine="btUnterschrift.Visible = True";
mostCurrent._btunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3459;BA.debugLine="sFahrtenbezeichnung = lstFahrtbezeichnung.GetIte";
mostCurrent._sfahrtenbezeichnung = BA.ObjectToString(mostCurrent._lstfahrtbezeichnung.GetItem(mostCurrent._fahrtbezeichnung._fahrtselected /*int*/ ));
 //BA.debugLineNum = 3460;BA.debugLine="iPos = sFahrtenbezeichnung.IndexOf(\" - \")";
_ipos = mostCurrent._sfahrtenbezeichnung.indexOf(" - ");
 //BA.debugLineNum = 3461;BA.debugLine="lblAuswahlFahrbezeichnung.Text = sFahrtenbezeich";
mostCurrent._lblauswahlfahrbezeichnung.setText(BA.ObjectToCharSequence(mostCurrent._sfahrtenbezeichnung.substring((int) (0),_ipos)));
 //BA.debugLineNum = 3463;BA.debugLine="btUnterschriftAbbruch_Click";
_btunterschriftabbruch_click();
 };
 //BA.debugLineNum = 3465;BA.debugLine="End Sub";
return "";
}
public static String  _stammdatenholen() throws Exception{
String[] _astammdaten = null;
 //BA.debugLineNum = 1713;BA.debugLine="Sub StammdatenHolen";
 //BA.debugLineNum = 1714;BA.debugLine="Dim aStammdaten() As String";
_astammdaten = new String[(int) (0)];
java.util.Arrays.fill(_astammdaten,"");
 //BA.debugLineNum = 1715;BA.debugLine="aStammdaten = Helper.StammdatenHolen";
_astammdaten = mostCurrent._helper._stammdatenholen /*String[]*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 1718;BA.debugLine="lblStammdatenName.Text = sAusgewaehlterSchueler";
mostCurrent._lblstammdatenname.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1719;BA.debugLine="lblStammdatenAnschrift.Text = aStammdaten(0) & \"";
mostCurrent._lblstammdatenanschrift.setText(BA.ObjectToCharSequence(_astammdaten[(int) (0)]+" "+_astammdaten[(int) (1)]));
 //BA.debugLineNum = 1720;BA.debugLine="lblStammdatenOrt.Text = aStammdaten(2) & \" \" & aS";
mostCurrent._lblstammdatenort.setText(BA.ObjectToCharSequence(_astammdaten[(int) (2)]+" "+_astammdaten[(int) (3)]));
 //BA.debugLineNum = 1721;BA.debugLine="lblStammTelefon.Text = aStammdaten(4)";
mostCurrent._lblstammtelefon.setText(BA.ObjectToCharSequence(_astammdaten[(int) (4)]));
 //BA.debugLineNum = 1722;BA.debugLine="lblStammHandy.Text = aStammdaten(5)";
mostCurrent._lblstammhandy.setText(BA.ObjectToCharSequence(_astammdaten[(int) (5)]));
 //BA.debugLineNum = 1723;BA.debugLine="lblStammEmail.Text = aStammdaten(6)";
mostCurrent._lblstammemail.setText(BA.ObjectToCharSequence(_astammdaten[(int) (6)]));
 //BA.debugLineNum = 1724;BA.debugLine="lblStammArbeitsstelle.Text = aStammdaten(7)";
mostCurrent._lblstammarbeitsstelle.setText(BA.ObjectToCharSequence(_astammdaten[(int) (7)]));
 //BA.debugLineNum = 1725;BA.debugLine="lblStammTelefonArbeit.Text = aStammdaten(8)";
mostCurrent._lblstammtelefonarbeit.setText(BA.ObjectToCharSequence(_astammdaten[(int) (8)]));
 //BA.debugLineNum = 1726;BA.debugLine="lblStammKlassen.Text = aStammdaten(9)";
mostCurrent._lblstammklassen.setText(BA.ObjectToCharSequence(_astammdaten[(int) (9)]));
 //BA.debugLineNum = 1727;BA.debugLine="lblStammHatKlasse.Text = aStammdaten(10)";
mostCurrent._lblstammhatklasse.setText(BA.ObjectToCharSequence(_astammdaten[(int) (10)]));
 //BA.debugLineNum = 1730;BA.debugLine="lblStammKontostand.Text = aStammdaten(11)";
mostCurrent._lblstammkontostand.setText(BA.ObjectToCharSequence(_astammdaten[(int) (11)]));
 //BA.debugLineNum = 1731;BA.debugLine="lblStammAntragEingereicht.Text = aStammdaten(12)";
mostCurrent._lblstammantrageingereicht.setText(BA.ObjectToCharSequence(_astammdaten[(int) (12)]));
 //BA.debugLineNum = 1732;BA.debugLine="lblStammAntragZurueck.Text = aStammdaten(13)";
mostCurrent._lblstammantragzurueck.setText(BA.ObjectToCharSequence(_astammdaten[(int) (13)]));
 //BA.debugLineNum = 1733;BA.debugLine="lblStammGeburtstag.Text = aStammdaten(14)";
mostCurrent._lblstammgeburtstag.setText(BA.ObjectToCharSequence(_astammdaten[(int) (14)]));
 //BA.debugLineNum = 1734;BA.debugLine="lblStammNationalitaet.Text = aStammdaten(15)";
mostCurrent._lblstammnationalitaet.setText(BA.ObjectToCharSequence(_astammdaten[(int) (15)]));
 //BA.debugLineNum = 1735;BA.debugLine="lblStammAnmeldung.Text = aStammdaten(16)";
mostCurrent._lblstammanmeldung.setText(BA.ObjectToCharSequence(_astammdaten[(int) (16)]));
 //BA.debugLineNum = 1736;BA.debugLine="lblStammSchuleFiliale.Text = aStammdaten(17)";
mostCurrent._lblstammschulefiliale.setText(BA.ObjectToCharSequence(_astammdaten[(int) (17)]));
 //BA.debugLineNum = 1738;BA.debugLine="End Sub";
return "";
}
public static String  _termineholen(String _sdate) throws Exception{
int[] _istunden = null;
anywheresoftware.b4a.objects.LabelWrapper _lbllinie = null;
 //BA.debugLineNum = 1321;BA.debugLine="Sub TermineHolen(sDate As String)";
 //BA.debugLineNum = 1322;BA.debugLine="Dim iStunden(2) As Int";
_istunden = new int[(int) (2)];
;
 //BA.debugLineNum = 1324;BA.debugLine="lstTermine.Clear";
mostCurrent._lsttermine.Clear();
 //BA.debugLineNum = 1326;BA.debugLine="iStunden = Helper.FahrlehrerTermineHolen(sDate, l";
_istunden = mostCurrent._helper._fahrlehrertermineholen /*int[]*/ (mostCurrent.activityBA,_sdate,mostCurrent._lsttermine);
 //BA.debugLineNum = 1328;BA.debugLine="lblAnzeigeGesamtStd.Text = iStunden(0)";
mostCurrent._lblanzeigegesamtstd.setText(BA.ObjectToCharSequence(_istunden[(int) (0)]));
 //BA.debugLineNum = 1329;BA.debugLine="lblAnzeigeFahrStd.Text = iStunden(1)";
mostCurrent._lblanzeigefahrstd.setText(BA.ObjectToCharSequence(_istunden[(int) (1)]));
 //BA.debugLineNum = 1331;BA.debugLine="lstTermine.ScrollingBackgroundColor = Colors.Tran";
mostCurrent._lsttermine.setScrollingBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1334;BA.debugLine="Dim lblLinie As Label";
_lbllinie = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 1335;BA.debugLine="lblLinie = lstTermine.TwoLinesLayout.SecondLabel";
_lbllinie = mostCurrent._lsttermine.getTwoLinesLayout().SecondLabel;
 //BA.debugLineNum = 1336;BA.debugLine="lstTermine.TwoLinesLayout.ItemHeight = 65dip";
mostCurrent._lsttermine.getTwoLinesLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65)));
 //BA.debugLineNum = 1337;BA.debugLine="If lv.Height = 1280 And lv.Width = 800 And lv.Sca";
if (_lv.Height==1280 && _lv.Width==800 && _lv.Scale==1) { 
 //BA.debugLineNum = 1338;BA.debugLine="lstTermine.SingleLineLayout.Label.TextSize = 19";
mostCurrent._lsttermine.getSingleLineLayout().Label.setTextSize((float) (19));
 //BA.debugLineNum = 1339;BA.debugLine="lblLinie.TextSize = 19";
_lbllinie.setTextSize((float) (19));
 }else {
 //BA.debugLineNum = 1342;BA.debugLine="lblLinie.TextSize = 14";
_lbllinie.setTextSize((float) (14));
 };
 //BA.debugLineNum = 1344;BA.debugLine="lblLinie.TextColor = Colors.Black";
_lbllinie.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1345;BA.debugLine="End Sub";
return "";
}
public static String  _thstart_tabchanged() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lblliniethemen = null;
anywheresoftware.b4a.objects.LabelWrapper _lblliniepunkte = null;
 //BA.debugLineNum = 1548;BA.debugLine="Sub thStart_TabChanged";
 //BA.debugLineNum = 1549;BA.debugLine="If thStart.CurrentTab = 0 Then SonstigeTaetigkeit";
if (mostCurrent._thstart.getCurrentTab()==0) { 
_sonstigetaetigkeitenaktiv(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 1552;BA.debugLine="If thStart.CurrentTab = 1 And bDatenVorhanden = F";
if (mostCurrent._thstart.getCurrentTab()==1 && _bdatenvorhanden==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1553;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.LightGr";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 1554;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = \"\";";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1556;BA.debugLine="lblAuswahlKlasse.Text = Helper.GetSchuelerKlasse";
mostCurrent._lblauswahlklasse.setText(BA.ObjectToCharSequence(mostCurrent._helper._getschuelerklasse /*String*/ (mostCurrent.activityBA,_iausgewaehlterschuelrid)));
 //BA.debugLineNum = 1558;BA.debugLine="lblAuswahlKfz.Text = Helper.GetSelectedKFZ";
mostCurrent._lblauswahlkfz.setText(BA.ObjectToCharSequence(mostCurrent._helper._getselectedkfz /*String*/ (mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 1562;BA.debugLine="If thStart.CurrentTab = 1 And bDatenVorhanden And";
if (mostCurrent._thstart.getCurrentTab()==1 && _bdatenvorhanden && _bsonsttaetigkeit) { 
 //BA.debugLineNum = 1564;BA.debugLine="SonstigeTaetigkeitenAktiv(False)";
_sonstigetaetigkeitenaktiv(anywheresoftware.b4a.keywords.Common.False);
 }else if(mostCurrent._thstart.getCurrentTab()==1 && _bdatenvorhanden) { 
 }else if(mostCurrent._thstart.getCurrentTab()!=1) { 
 //BA.debugLineNum = 1568;BA.debugLine="iTerminID = 0";
_iterminid = (int) (0);
 //BA.debugLineNum = 1569;BA.debugLine="bDatenVorhanden = False";
_bdatenvorhanden = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1570;BA.debugLine="btUnterschriftLoeschen_Click";
_btunterschriftloeschen_click();
 }else {
 //BA.debugLineNum = 1573;BA.debugLine="SonstigeTaetigkeitenAktiv(True)";
_sonstigetaetigkeitenaktiv(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1576;BA.debugLine="lblAbfahrtAnzeige.Text = DateTime.Time(DateTime.";
mostCurrent._lblabfahrtanzeige.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 1577;BA.debugLine="lblEinheitAnzeige.Text = txtEinstAnzeigeFahrEinh";
mostCurrent._lbleinheitanzeige.setText(BA.ObjectToCharSequence(mostCurrent._txteinstanzeigefahreinheit.getText()));
 //BA.debugLineNum = 1580;BA.debugLine="panStart.Visible = True";
mostCurrent._panstart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1581;BA.debugLine="panUnterschrift.Visible = False";
mostCurrent._panunterschrift.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1582;BA.debugLine="panPruefung.Visible = False";
mostCurrent._panpruefung.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1584;BA.debugLine="btUnterschriftLoeschen_Click";
_btunterschriftloeschen_click();
 //BA.debugLineNum = 1586;BA.debugLine="If cbBegleitfahrzueg.Checked Then";
if (mostCurrent._cbbegleitfahrzueg.getChecked()) { 
 //BA.debugLineNum = 1588;BA.debugLine="lblBegleitfahrzeug.Visible = True";
mostCurrent._lblbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1589;BA.debugLine="lblAuswahlBegleitfahrzeug.Visible = True";
mostCurrent._lblauswahlbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1591;BA.debugLine="If lblAuswahlBegleitfahrzeug.Text <> \"keine Aus";
if ((mostCurrent._lblauswahlbegleitfahrzeug.getText()).equals("keine Auswahl") == false) { 
 //BA.debugLineNum = 1592;BA.debugLine="If Helper.CheckBegleitfahrzeug(lblAuswahlKlass";
if (mostCurrent._helper._checkbegleitfahrzeug /*boolean*/ (mostCurrent.activityBA,mostCurrent._lblauswahlklasse.getText())) { 
 //BA.debugLineNum = 1593;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = lstBegleitfa";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(mostCurrent._lstbegleitfahrzeug.GetItem((int) (0))));
 //BA.debugLineNum = 1594;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = True";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1595;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.whit";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 }else {
 //BA.debugLineNum = 1598;BA.debugLine="lblAuswahlBegleitfahrzeug.Text = \"\"";
mostCurrent._lblauswahlbegleitfahrzeug.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1599;BA.debugLine="lblAuswahlBegleitfahrzeug.Enabled = False";
mostCurrent._lblauswahlbegleitfahrzeug.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1600;BA.debugLine="lblAuswahlBegleitfahrzeug.Color = Colors.Light";
mostCurrent._lblauswahlbegleitfahrzeug.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 };
 }else {
 //BA.debugLineNum = 1603;BA.debugLine="lblBegleitfahrzeug.Visible = False";
mostCurrent._lblbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1604;BA.debugLine="lblAuswahlBegleitfahrzeug.Visible = False";
mostCurrent._lblauswahlbegleitfahrzeug.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1608;BA.debugLine="Helper.AusgewaehltenSchuelerSetzen";
mostCurrent._helper._ausgewaehltenschuelersetzen /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 1609;BA.debugLine="lblAnzSchueler.Text = sAusgewaehlterSchueler";
mostCurrent._lblanzschueler.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1610;BA.debugLine="lblAnzDatum.Text = btKalender.Text";
mostCurrent._lblanzdatum.setText(BA.ObjectToCharSequence(mostCurrent._btkalender.getText()));
 //BA.debugLineNum = 1611;BA.debugLine="iTerminID = 0";
_iterminid = (int) (0);
 //BA.debugLineNum = 1612;BA.debugLine="bDatenVorhanden = False";
_bdatenvorhanden = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1616;BA.debugLine="If thStart.CurrentTab = 0 Then";
if (mostCurrent._thstart.getCurrentTab()==0) { 
 //BA.debugLineNum = 1617;BA.debugLine="sAusgewaehlterSchueler = lblAuswahlSchueler.Text";
_sausgewaehlterschueler = mostCurrent._lblauswahlschueler.getText();
 };
 //BA.debugLineNum = 1621;BA.debugLine="If thStart.CurrentTab = 2 Then";
if (mostCurrent._thstart.getCurrentTab()==2) { 
 //BA.debugLineNum = 1622;BA.debugLine="StammdatenHolen";
_stammdatenholen();
 //BA.debugLineNum = 1623;BA.debugLine="panKlassen.Visible = False";
mostCurrent._panklassen.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1624;BA.debugLine="panStammdaten2.Visible = False";
mostCurrent._panstammdaten2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1625;BA.debugLine="panStammdaten1.Visible = True";
mostCurrent._panstammdaten1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 1629;BA.debugLine="If thStart.CurrentTab = 3 Then";
if (mostCurrent._thstart.getCurrentTab()==3) { 
 //BA.debugLineNum = 1630;BA.debugLine="lblZahlDatum.Text = btKalender.Text";
mostCurrent._lblzahldatum.setText(BA.ObjectToCharSequence(mostCurrent._btkalender.getText()));
 //BA.debugLineNum = 1631;BA.debugLine="lblZahlSchueler.Text = sAusgewaehlterSchueler";
mostCurrent._lblzahlschueler.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1633;BA.debugLine="lblZahlGesamtBetrag.Color = Colors.ARGB(255, 144";
mostCurrent._lblzahlgesamtbetrag.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (144),(int) (238),(int) (144)));
 //BA.debugLineNum = 1634;BA.debugLine="lblZahlTag.Color = Colors.ARGB(255, 144, 238, 14";
mostCurrent._lblzahltag.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (144),(int) (238),(int) (144)));
 //BA.debugLineNum = 1635;BA.debugLine="lblZahlAlle.Color = Colors.ARGB(255, 211, 211, 2";
mostCurrent._lblzahlalle.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (211),(int) (211),(int) (211)));
 //BA.debugLineNum = 1637;BA.debugLine="ZahlungenHolen(False)";
_zahlungenholen(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1641;BA.debugLine="If thStart.CurrentTab = 4 Then";
if (mostCurrent._thstart.getCurrentTab()==4) { 
 //BA.debugLineNum = 1643;BA.debugLine="Helper.ArraybIsSelected(False)";
mostCurrent._helper._arraybisselected /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1644;BA.debugLine="If aKonfig(2) Then";
if (_akonfig[(int) (2)]) { 
 //BA.debugLineNum = 1646;BA.debugLine="lblSchuelerBVF.Text = sAusgewaehlterSchueler";
mostCurrent._lblschuelerbvf.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1647;BA.debugLine="Helper.SetBVFFelder(btKalender.Text, sAusgewaeh";
mostCurrent._helper._setbvffelder /*String*/ (mostCurrent.activityBA,mostCurrent._btkalender.getText(),_sausgewaehlterschueler,mostCurrent._cbbesonderheinsteigen,mostCurrent._cbeinstellen,mostCurrent._cblenkrad,mostCurrent._cbspiegel,mostCurrent._cbkopfstuetze,mostCurrent._cbsitz,mostCurrent._cblenkradhaltung,mostCurrent._cbpedale,mostCurrent._cbgurt,mostCurrent._cbschaltwaehlhebel,mostCurrent._cbzuendschloss,mostCurrent._cbmotoranlassen,mostCurrent._cbanfahranhalte,mostCurrent._cbschaltuebg,mostCurrent._cbhoch1_2,mostCurrent._cbhoch2_3,mostCurrent._cbhoch3_4,mostCurrent._cbrunter4_3,mostCurrent._cbrunter3_2,mostCurrent._cbrunter2_1,mostCurrent._cbrunter4_2,mostCurrent._cbrunter4_1,mostCurrent._cbrunter3_1,mostCurrent._cblenkuebung,mostCurrent._cbumkehren,mostCurrent._cbeinparkenlaengs,mostCurrent._cblvorwaertsrechts,mostCurrent._cblrueckwaertslinks,mostCurrent._cblrueckwaertsrechts,mostCurrent._cblvorwaertslinks,mostCurrent._cbrueckwaertsfahren,mostCurrent._cbeinparkenquer,mostCurrent._cbqvorwaertsrechts,mostCurrent._cbqrueckwaertslinks,mostCurrent._cbqrueckwaertsrechts,mostCurrent._cbqvorwaertslinks,mostCurrent._cbgefahrbremsung,mostCurrent._cbrollenschalten,mostCurrent._cbbremsschalten,mostCurrent._cbbremsuebung,mostCurrent._cbdegressiv,mostCurrent._cbzielbremsung,mostCurrent._cbgefahrsituation,mostCurrent._cbgefaelle,mostCurrent._cbanhalten,mostCurrent._cbanfahren,mostCurrent._cbrueckwaerts,mostCurrent._cbsichern,mostCurrent._cbschalten,mostCurrent._cbsteigung,mostCurrent._cbstanhalten,mostCurrent._cbstanfahren,mostCurrent._cbstrueckwaerts,mostCurrent._cbstsichern,mostCurrent._cbstschalten,mostCurrent._cbtastgeschw,mostCurrent._cbbedienkontroll,mostCurrent._cboertlichbesonder,mostCurrent._cbfahrbahnbenutzung,mostCurrent._cbeinordnen,mostCurrent._cbmarkierungen,mostCurrent._cbfahrstreifenwechsel,mostCurrent._cblinks,mostCurrent._cbrechts,mostCurrent._cbvorbeifueberholen,mostCurrent._cbabbiegen,mostCurrent._cbabrechts,mostCurrent._cbablinks,mostCurrent._cbmehrspurig,mostCurrent._cbradweg,mostCurrent._cbsonderstreifen,mostCurrent._cbstrassenbahn,mostCurrent._cbeinbahnstrasse,mostCurrent._cbvorfahrt,mostCurrent._cbrechtsvorlinks,mostCurrent._cbgruenpfeil,mostCurrent._cbpolizeibeamte,mostCurrent._cbgruenpfeilschild,mostCurrent._cbgeschwabstand,mostCurrent._cbsituationverkehrstn,mostCurrent._cbfussgaengerueberweg,mostCurrent._cboeffentlverkehrsm,mostCurrent._cbaelterebehinderte,mostCurrent._cbeinbahnstrradfahrer,mostCurrent._cbkinder,mostCurrent._cbschulbus,mostCurrent._cbradfahrermofa,mostCurrent._cbverkehrsberuhigt,mostCurrent._cbschwierigeverkehrsf,mostCurrent._cbengpass,mostCurrent._cbkreisverkehr,mostCurrent._cbbahnuebergangwarte,mostCurrent._cbkritischeverkehrss,mostCurrent._cbhauptverkehrszt,mostCurrent._cbpartnerverhalten,mostCurrent._cbschwungnutzen,mostCurrent._cbfussgaengerschutzb,mostCurrent._cbangepasstegeschw,mostCurrent._cbabstand,mostCurrent._cbulvorne,mostCurrent._cbulhinten,mostCurrent._cbulseitlich,mostCurrent._cbbeobachtspiegel,mostCurrent._cbverkehrszeichen,mostCurrent._cbkreuzungeinmuend,mostCurrent._cbkurven,mostCurrent._cbsteigungen,mostCurrent._cbulgefaelle,mostCurrent._cballeen,mostCurrent._cbueberholen,mostCurrent._cbbesonderesituat,mostCurrent._cbliegenblsichern,mostCurrent._cbeinfahrenortsch,mostCurrent._cbfussgaenger,mostCurrent._cbwildtiere,mostCurrent._cbbesondereanford,mostCurrent._cbleistungsgrenze,mostCurrent._cborientierung,mostCurrent._cbablenkung,mostCurrent._cbfahrtplanung,mostCurrent._cbeinfahrtab,mostCurrent._cbabfahrbahnwechsel,mostCurrent._cbgeschwindigkeit,mostCurrent._cbababstand,mostCurrent._cbabvorne,mostCurrent._cbabhinten,mostCurrent._cbabseitlich,mostCurrent._cbabueberholen,mostCurrent._cbschilder,mostCurrent._cbvorbeifahren,mostCurrent._cbrastparktank,mostCurrent._cbverhunfall,mostCurrent._cbdichterverkehr,mostCurrent._cbbesondersituat,mostCurrent._cbbesonderanford,mostCurrent._cbableistungsgrenze,mostCurrent._cbkonfliktsitua,mostCurrent._cbabablenkung,mostCurrent._cbbeleuchtung,mostCurrent._cbkontrolle,mostCurrent._cbeinstell,mostCurrent._cbbenutzung,mostCurrent._cbfernlicht,mostCurrent._cbverlassenbab,mostCurrent._cbbeleuchtstrasse,mostCurrent._cbunbeleuchtstrasse,mostCurrent._cbparken,mostCurrent._cbdubesondersituat,mostCurrent._cbschlechtewitterung,mostCurrent._cbtiere,mostCurrent._cbbahnuebergaenge,mostCurrent._cbunbelverkehrtn,mostCurrent._cbdubesonderanfor,mostCurrent._cbblendung,mostCurrent._cbduorientierung,mostCurrent._cbabschlussbesp,mostCurrent._cbselbstfahren,mostCurrent._cbinnerorts,mostCurrent._cbausserorts,mostCurrent._cbverantwfahren,mostCurrent._cbtestfpruef,mostCurrent._cbfakt,mostCurrent._cbandere,mostCurrent._cbwiederholung,mostCurrent._cbleistungsbew,mostCurrent._cbreifen,mostCurrent._cbeinausschalten,mostCurrent._cbfunktionpruefen,mostCurrent._cbstandlicht,mostCurrent._cbnebelschluss,mostCurrent._cbblinker,mostCurrent._cbabblendlicht,mostCurrent._cbwarnblicke,mostCurrent._cbhupe,mostCurrent._cbbsfernlicht,mostCurrent._cbschlussleuchte,mostCurrent._cbbremslicht,mostCurrent._cbkontrolllbenenn,mostCurrent._cbrueckstrahler,mostCurrent._cbvorhandensein,mostCurrent._cbbeschaedigung,mostCurrent._cblenkung,mostCurrent._cblenkschlentriegeln,mostCurrent._cbprueflenkspiel,mostCurrent._cbfunktbremse,mostCurrent._cbbetriebsbremse,mostCurrent._cbfeststellbremse,mostCurrent._cbanlegengurt,mostCurrent._cbrichtigsitz,mostCurrent._cbeinstellrueckspiegel,mostCurrent._cbeinkopfstuetze,mostCurrent._cbeinlenkrad,mostCurrent._cbbedienenagg,mostCurrent._cbheizung,mostCurrent._cbheckheizung,mostCurrent._cbbehsonderaus,mostCurrent._cblueftung,mostCurrent._cbklimaanlage,mostCurrent._cbenergienutzung,mostCurrent._cbkeineunnverbr,mostCurrent._cbrechtztabsch,mostCurrent._cbmotorraum,mostCurrent._cbmotoroel,mostCurrent._cbkuehlmittel,mostCurrent._cbscheibenwaschm,mostCurrent._cbtanken,mostCurrent._cbbremsen,mostCurrent._cbsicherungsmittel,mostCurrent._cbwarndreieck,mostCurrent._cbbordwerkzeug,mostCurrent._cbzusaetzlichaus,mostCurrent._cbverbandskasten,mostCurrent._cbaussenkontrolle,mostCurrent._cbscheibenwischer,mostCurrent._cbkennzeichen,mostCurrent._cbcheckspiegel,mostCurrent._cbcheckbeleuchtung,mostCurrent._cbladung,mostCurrent._cbladungssicherung,mostCurrent._cbkenntlichmachung,mostCurrent._cbfahreschlwitt,mostCurrent._cbwittlueftung,mostCurrent._cbwittscheiben,mostCurrent._cbregen,mostCurrent._cbwasserlachen,mostCurrent._cbwindsturm,mostCurrent._cbmatchschnee,mostCurrent._cbeis,mostCurrent._cbwittbeleuchtung,mostCurrent._etnotizen);
 }else {
 //BA.debugLineNum = 1668;BA.debugLine="Helper.AusbildungsAnzeigenLoeschen(lblAusbildun";
mostCurrent._helper._ausbildungsanzeigenloeschen /*String*/ (mostCurrent.activityBA,mostCurrent._lblausbildunglistezeile1,mostCurrent._lblausbildunglistezusatz1,mostCurrent._lblausbildunglistezeile2,mostCurrent._lblausbildunglistezusatz2,mostCurrent._lblausbildunglistezeile3,mostCurrent._lblausbildunglistezusatz3,mostCurrent._lblausbildunglistezeile4,mostCurrent._lblausbildunglistezusatz4,mostCurrent._lblausbildunglistezeile5,mostCurrent._lblausbildunglistezusatz5,mostCurrent._lblausbildunglistezeile6,mostCurrent._lblausbildunglistezusatz6,mostCurrent._lblausbildunglistezeile7,mostCurrent._lblausbildunglistezusatz7,mostCurrent._lblausbildunglistezeile8,mostCurrent._lblausbildunglistezusatz8,mostCurrent._lblausbildunglistezeile9,mostCurrent._lblausbildunglistezusatz9,mostCurrent._lblausbildunglistezeile10,mostCurrent._lblausbildunglistezusatz10);
 //BA.debugLineNum = 1675;BA.debugLine="lblAusbildungSchueler.Text = sAusgewaehlterSchu";
mostCurrent._lblausbildungschueler.setText(BA.ObjectToCharSequence(_sausgewaehlterschueler));
 //BA.debugLineNum = 1678;BA.debugLine="Dim lblLinieThemen, lblLiniePunkte As Label";
_lblliniethemen = new anywheresoftware.b4a.objects.LabelWrapper();
_lblliniepunkte = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 1680;BA.debugLine="lblLinieThemen = lstAusbildungLernThemen.Single";
_lblliniethemen = mostCurrent._lstausbildunglernthemen.getSingleLineLayout().Label;
 //BA.debugLineNum = 1681;BA.debugLine="lblLiniePunkte = lstAusbildungLernPunkte.Single";
_lblliniepunkte = mostCurrent._lstausbildunglernpunkte.getSingleLineLayout().Label;
 //BA.debugLineNum = 1684;BA.debugLine="If lv.Width > 320 And lv.Height > 480 Then";
if (_lv.Width>320 && _lv.Height>480) { 
 //BA.debugLineNum = 1685;BA.debugLine="lblLinieThemen.TextSize = 16";
_lblliniethemen.setTextSize((float) (16));
 //BA.debugLineNum = 1686;BA.debugLine="lblLiniePunkte.TextSize = 16";
_lblliniepunkte.setTextSize((float) (16));
 //BA.debugLineNum = 1687;BA.debugLine="lstAusbildungLernThemen.SingleLineLayout.ItemH";
mostCurrent._lstausbildunglernthemen.getSingleLineLayout().setItemHeight((int) (mostCurrent._panausbildung.getHeight()/(double)10));
 //BA.debugLineNum = 1688;BA.debugLine="lstAusbildungLernPunkte.SingleLineLayout.ItemH";
mostCurrent._lstausbildunglernpunkte.getSingleLineLayout().setItemHeight((int) (mostCurrent._panausbildung.getHeight()/(double)10));
 }else {
 //BA.debugLineNum = 1690;BA.debugLine="lblLinieThemen.TextSize = 15";
_lblliniethemen.setTextSize((float) (15));
 //BA.debugLineNum = 1691;BA.debugLine="lblLiniePunkte.TextSize = 15";
_lblliniepunkte.setTextSize((float) (15));
 //BA.debugLineNum = 1692;BA.debugLine="lstAusbildungLernThemen.SingleLineLayout.ItemH";
mostCurrent._lstausbildunglernthemen.getSingleLineLayout().setItemHeight((int) (22));
 //BA.debugLineNum = 1693;BA.debugLine="lstAusbildungLernPunkte.SingleLineLayout.ItemH";
mostCurrent._lstausbildunglernpunkte.getSingleLineLayout().setItemHeight((int) (22));
 };
 //BA.debugLineNum = 1696;BA.debugLine="lblLinieThemen.TextColor = Colors.Black";
_lblliniethemen.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1697;BA.debugLine="lblLiniePunkte.TextColor = Colors.Black";
_lblliniepunkte.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1699;BA.debugLine="Helper.FillAusbildungLernThemenListView(lstAusb";
mostCurrent._helper._fillausbildunglernthemenlistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernthemen,(int) (1));
 //BA.debugLineNum = 1700;BA.debugLine="Helper.FillAusbildungLernPunkteListView(lstAusb";
mostCurrent._helper._fillausbildunglernpunktelistview /*String*/ (mostCurrent.activityBA,mostCurrent._lstausbildunglernpunkte,_sausgewaehlterschueler,(int) (1));
 };
 };
 //BA.debugLineNum = 1705;BA.debugLine="If thStart.CurrentTab = 5 Then";
if (mostCurrent._thstart.getCurrentTab()==5) { 
 //BA.debugLineNum = 1706;BA.debugLine="lblEinstAnzeigeKfz.Text = Helper.GetSelectedKFZ";
mostCurrent._lbleinstanzeigekfz.setText(BA.ObjectToCharSequence(mostCurrent._helper._getselectedkfz /*String*/ (mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 1708;BA.debugLine="End Sub";
return "";
}
public static String  _uhrzeitaddieren(int _istd,int _imin) throws Exception{
String _shour = "";
String _sminute = "";
int _iindex = 0;
int _rest = 0;
int _i = 0;
 //BA.debugLineNum = 1050;BA.debugLine="Sub UhrzeitAddieren(iStd As Int, iMin As Int)";
 //BA.debugLineNum = 1051;BA.debugLine="Dim sHour, sMinute As String";
_shour = "";
_sminute = "";
 //BA.debugLineNum = 1052;BA.debugLine="Dim iIndex As Int";
_iindex = 0;
 //BA.debugLineNum = 1053;BA.debugLine="Dim rest As Int";
_rest = 0;
 //BA.debugLineNum = 1055;BA.debugLine="iIndex = lblAbfahrtAnzeige.Text.IndexOf(\":\")";
_iindex = mostCurrent._lblabfahrtanzeige.getText().indexOf(":");
 //BA.debugLineNum = 1056;BA.debugLine="sHour = lblAbfahrtAnzeige.Text.SubString2(0, iInd";
_shour = mostCurrent._lblabfahrtanzeige.getText().substring((int) (0),_iindex);
 //BA.debugLineNum = 1057;BA.debugLine="sMinute = lblAbfahrtAnzeige.Text.SubString(iIndex";
_sminute = mostCurrent._lblabfahrtanzeige.getText().substring((int) (_iindex+1));
 //BA.debugLineNum = 1059;BA.debugLine="If iMin > 1 Then";
if (_imin>1) { 
 //BA.debugLineNum = 1061;BA.debugLine="rest = sMinute Mod 5";
_rest = (int) ((double)(Double.parseDouble(_sminute))%5);
 //BA.debugLineNum = 1062;BA.debugLine="If rest > 0 Then";
if (_rest>0) { 
 //BA.debugLineNum = 1063;BA.debugLine="For i = rest To 4";
{
final int step10 = 1;
final int limit10 = (int) (4);
_i = _rest ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1064;BA.debugLine="sMinute = sMinute + 1";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))+1);
 }
};
 }else {
 //BA.debugLineNum = 1067;BA.debugLine="sMinute = sMinute + 5";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))+5);
 };
 //BA.debugLineNum = 1070;BA.debugLine="If sMinute > 55 Then";
if ((double)(Double.parseDouble(_sminute))>55) { 
 //BA.debugLineNum = 1071;BA.debugLine="sMinute = 0";
_sminute = BA.NumberToString(0);
 //BA.debugLineNum = 1072;BA.debugLine="If sHour = 23 Then";
if ((_shour).equals(BA.NumberToString(23))) { 
 //BA.debugLineNum = 1073;BA.debugLine="sHour = 0";
_shour = BA.NumberToString(0);
 }else {
 //BA.debugLineNum = 1075;BA.debugLine="sHour = sHour + 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))+1);
 };
 //BA.debugLineNum = 1078;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1079;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1084;BA.debugLine="If sMinute >= 0 And sMinute < 10 Then";
if ((double)(Double.parseDouble(_sminute))>=0 && (double)(Double.parseDouble(_sminute))<10) { 
 //BA.debugLineNum = 1085;BA.debugLine="sMinute = \"0\" & sMinute";
_sminute = "0"+_sminute;
 };
 }else if(_imin>0) { 
 //BA.debugLineNum = 1089;BA.debugLine="sMinute = sMinute + 1";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))+1);
 //BA.debugLineNum = 1090;BA.debugLine="If sMinute > 59 Then";
if ((double)(Double.parseDouble(_sminute))>59) { 
 //BA.debugLineNum = 1091;BA.debugLine="sMinute = 0";
_sminute = BA.NumberToString(0);
 //BA.debugLineNum = 1092;BA.debugLine="If sHour = 23 Then";
if ((_shour).equals(BA.NumberToString(23))) { 
 //BA.debugLineNum = 1093;BA.debugLine="sHour = 0";
_shour = BA.NumberToString(0);
 }else {
 //BA.debugLineNum = 1095;BA.debugLine="sHour = sHour + 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))+1);
 };
 //BA.debugLineNum = 1098;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1099;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1104;BA.debugLine="If sMinute >= 0 And sMinute < 10 Then";
if ((double)(Double.parseDouble(_sminute))>=0 && (double)(Double.parseDouble(_sminute))<10) { 
 //BA.debugLineNum = 1105;BA.debugLine="sMinute = \"0\" & sMinute";
_sminute = "0"+_sminute;
 };
 };
 //BA.debugLineNum = 1109;BA.debugLine="If iStd = 1 Then";
if (_istd==1) { 
 //BA.debugLineNum = 1110;BA.debugLine="If sHour >= 23 Then";
if ((double)(Double.parseDouble(_shour))>=23) { 
 //BA.debugLineNum = 1111;BA.debugLine="sHour = 0";
_shour = BA.NumberToString(0);
 }else {
 //BA.debugLineNum = 1113;BA.debugLine="sHour = sHour + 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))+1);
 };
 //BA.debugLineNum = 1116;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1117;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1121;BA.debugLine="lblAbfahrtAnzeige.Text = sHour & \":\" & sMinute";
mostCurrent._lblabfahrtanzeige.setText(BA.ObjectToCharSequence(_shour+":"+_sminute));
 //BA.debugLineNum = 1122;BA.debugLine="End Sub";
return "";
}
public static String  _uhrzeitsubstrahieren(int _istd,int _imin) throws Exception{
String _shour = "";
String _sminute = "";
int _iindex = 0;
int _rest = 0;
int _i = 0;
 //BA.debugLineNum = 1125;BA.debugLine="Sub UhrzeitSubstrahieren(iStd As Int, iMin As Int)";
 //BA.debugLineNum = 1126;BA.debugLine="Dim sHour, sMinute As String";
_shour = "";
_sminute = "";
 //BA.debugLineNum = 1127;BA.debugLine="Dim iIndex As Int";
_iindex = 0;
 //BA.debugLineNum = 1128;BA.debugLine="Dim rest As Int";
_rest = 0;
 //BA.debugLineNum = 1130;BA.debugLine="iIndex = lblAbfahrtAnzeige.Text.IndexOf(\":\")";
_iindex = mostCurrent._lblabfahrtanzeige.getText().indexOf(":");
 //BA.debugLineNum = 1131;BA.debugLine="sHour = lblAbfahrtAnzeige.Text.SubString2(0, iInd";
_shour = mostCurrent._lblabfahrtanzeige.getText().substring((int) (0),_iindex);
 //BA.debugLineNum = 1132;BA.debugLine="sMinute = lblAbfahrtAnzeige.Text.SubString(iIndex";
_sminute = mostCurrent._lblabfahrtanzeige.getText().substring((int) (_iindex+1));
 //BA.debugLineNum = 1134;BA.debugLine="If iMin > 1 Then";
if (_imin>1) { 
 //BA.debugLineNum = 1136;BA.debugLine="rest = sMinute Mod 5";
_rest = (int) ((double)(Double.parseDouble(_sminute))%5);
 //BA.debugLineNum = 1137;BA.debugLine="If rest > 0 Then";
if (_rest>0) { 
 //BA.debugLineNum = 1138;BA.debugLine="For i = 0 To rest - 1";
{
final int step10 = 1;
final int limit10 = (int) (_rest-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1139;BA.debugLine="sMinute = sMinute - 1";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))-1);
 }
};
 }else {
 //BA.debugLineNum = 1142;BA.debugLine="sMinute = sMinute - 5";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))-5);
 };
 //BA.debugLineNum = 1145;BA.debugLine="If sMinute < 0 Then";
if ((double)(Double.parseDouble(_sminute))<0) { 
 //BA.debugLineNum = 1146;BA.debugLine="sMinute = 55";
_sminute = BA.NumberToString(55);
 //BA.debugLineNum = 1147;BA.debugLine="If sHour = \"00\" Then";
if ((_shour).equals("00")) { 
 //BA.debugLineNum = 1148;BA.debugLine="sHour = 23";
_shour = BA.NumberToString(23);
 }else {
 //BA.debugLineNum = 1150;BA.debugLine="sHour = sHour - 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))-1);
 };
 //BA.debugLineNum = 1153;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1154;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1159;BA.debugLine="If sMinute >= 0 And sMinute < 10 Then";
if ((double)(Double.parseDouble(_sminute))>=0 && (double)(Double.parseDouble(_sminute))<10) { 
 //BA.debugLineNum = 1160;BA.debugLine="sMinute = \"0\" & sMinute";
_sminute = "0"+_sminute;
 };
 }else if(_imin>0) { 
 //BA.debugLineNum = 1164;BA.debugLine="sMinute = sMinute - 1";
_sminute = BA.NumberToString((double)(Double.parseDouble(_sminute))-1);
 //BA.debugLineNum = 1165;BA.debugLine="If sMinute < 0 Then";
if ((double)(Double.parseDouble(_sminute))<0) { 
 //BA.debugLineNum = 1166;BA.debugLine="sMinute = 59";
_sminute = BA.NumberToString(59);
 //BA.debugLineNum = 1167;BA.debugLine="If sHour = \"00\" Then";
if ((_shour).equals("00")) { 
 //BA.debugLineNum = 1168;BA.debugLine="sHour = 23";
_shour = BA.NumberToString(23);
 }else {
 //BA.debugLineNum = 1170;BA.debugLine="sHour = sHour - 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))-1);
 };
 //BA.debugLineNum = 1173;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1174;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1179;BA.debugLine="If sMinute >= 0 And sMinute < 10 Then";
if ((double)(Double.parseDouble(_sminute))>=0 && (double)(Double.parseDouble(_sminute))<10) { 
 //BA.debugLineNum = 1180;BA.debugLine="sMinute = \"0\" & sMinute";
_sminute = "0"+_sminute;
 };
 };
 //BA.debugLineNum = 1184;BA.debugLine="If iStd = 1 Then";
if (_istd==1) { 
 //BA.debugLineNum = 1185;BA.debugLine="If sHour <= 0 Then";
if ((double)(Double.parseDouble(_shour))<=0) { 
 //BA.debugLineNum = 1186;BA.debugLine="sHour = 23";
_shour = BA.NumberToString(23);
 }else {
 //BA.debugLineNum = 1188;BA.debugLine="sHour = sHour - 1";
_shour = BA.NumberToString((double)(Double.parseDouble(_shour))-1);
 };
 //BA.debugLineNum = 1191;BA.debugLine="If sHour >= 0 And sHour < 10 Then";
if ((double)(Double.parseDouble(_shour))>=0 && (double)(Double.parseDouble(_shour))<10) { 
 //BA.debugLineNum = 1192;BA.debugLine="sHour = \"0\" & sHour";
_shour = "0"+_shour;
 };
 };
 //BA.debugLineNum = 1196;BA.debugLine="lblAbfahrtAnzeige.Text = sHour & \":\" & sMinute";
mostCurrent._lblabfahrtanzeige.setText(BA.ObjectToCharSequence(_shour+":"+_sminute));
 //BA.debugLineNum = 1197;BA.debugLine="End Sub";
return "";
}
public static String  _zahlungenholen(boolean _ball) throws Exception{
String _ssumme = "";
anywheresoftware.b4a.objects.LabelWrapper _lbllinie = null;
int _igesamt = 0;
int _ipos = 0;
 //BA.debugLineNum = 1494;BA.debugLine="Sub ZahlungenHolen(bAll As Boolean)";
 //BA.debugLineNum = 1495;BA.debugLine="Dim sSumme As String";
_ssumme = "";
 //BA.debugLineNum = 1496;BA.debugLine="Dim lblLinie As Label";
_lbllinie = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 1498;BA.debugLine="lv = GetDeviceLayoutValues";
_lv = anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA);
 //BA.debugLineNum = 1500;BA.debugLine="lstZahlungen.ScrollingBackgroundColor = Colors.Tr";
mostCurrent._lstzahlungen.setScrollingBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 1503;BA.debugLine="lblLinie = lstZahlungen.SingleLineLayout.Label";
_lbllinie = mostCurrent._lstzahlungen.getSingleLineLayout().Label;
 //BA.debugLineNum = 1504;BA.debugLine="lblLinie.TextColor = Colors.Black";
_lbllinie.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1510;BA.debugLine="lstZahlungen.SingleLineLayout.ItemHeight = 20dip";
mostCurrent._lstzahlungen.getSingleLineLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 1511;BA.debugLine="lblLinie.TextSize = 14";
_lbllinie.setTextSize((float) (14));
 //BA.debugLineNum = 1515;BA.debugLine="lstZahlungen.Clear";
mostCurrent._lstzahlungen.Clear();
 //BA.debugLineNum = 1516;BA.debugLine="If bAll Then";
if (_ball) { 
 //BA.debugLineNum = 1517;BA.debugLine="sSumme = Helper.ErfassteZahlungenHolen(\"Alle\", l";
_ssumme = mostCurrent._helper._erfasstezahlungenholen /*String*/ (mostCurrent.activityBA,"Alle",mostCurrent._lstzahlungen);
 }else {
 //BA.debugLineNum = 1519;BA.debugLine="sSumme = Helper.ErfassteZahlungenHolen(lblZahlDa";
_ssumme = mostCurrent._helper._erfasstezahlungenholen /*String*/ (mostCurrent.activityBA,mostCurrent._lblzahldatum.getText(),mostCurrent._lstzahlungen);
 };
 //BA.debugLineNum = 1522;BA.debugLine="If sSumme = Null Then";
if (_ssumme== null) { 
 //BA.debugLineNum = 1523;BA.debugLine="lblZahlGesamtBetrag.Text = \"0.00\"";
mostCurrent._lblzahlgesamtbetrag.setText(BA.ObjectToCharSequence("0.00"));
 }else {
 //BA.debugLineNum = 1525;BA.debugLine="Dim iGesamt, iPos As Int";
_igesamt = 0;
_ipos = 0;
 //BA.debugLineNum = 1528;BA.debugLine="iGesamt = sSumme.Length";
_igesamt = _ssumme.length();
 //BA.debugLineNum = 1529;BA.debugLine="iPos = sSumme.LastIndexOf(\".\")";
_ipos = _ssumme.lastIndexOf(".");
 //BA.debugLineNum = 1531;BA.debugLine="If iPos = -1 Then";
if (_ipos==-1) { 
 //BA.debugLineNum = 1532;BA.debugLine="lblZahlGesamtBetrag.Text = sSumme & \".00\"";
mostCurrent._lblzahlgesamtbetrag.setText(BA.ObjectToCharSequence(_ssumme+".00"));
 }else {
 //BA.debugLineNum = 1534;BA.debugLine="If (iGesamt - (iPos + 1)) > 2 Then";
if ((_igesamt-(_ipos+1))>2) { 
 //BA.debugLineNum = 1535;BA.debugLine="lblZahlGesamtBetrag.Text = sSumme.SubString2(";
mostCurrent._lblzahlgesamtbetrag.setText(BA.ObjectToCharSequence(_ssumme.substring((int) (0),(int) (_ipos+3))));
 }else if((_igesamt-(_ipos+1))==1) { 
 //BA.debugLineNum = 1537;BA.debugLine="lblZahlGesamtBetrag.Text = sSumme & \"0\"";
mostCurrent._lblzahlgesamtbetrag.setText(BA.ObjectToCharSequence(_ssumme+"0"));
 }else {
 //BA.debugLineNum = 1539;BA.debugLine="lblZahlGesamtBetrag.Text = sSumme";
mostCurrent._lblzahlgesamtbetrag.setText(BA.ObjectToCharSequence(_ssumme));
 };
 };
 };
 //BA.debugLineNum = 1543;BA.debugLine="End Sub";
return "";
}
}
