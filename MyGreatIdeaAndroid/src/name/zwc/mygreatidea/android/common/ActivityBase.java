package name.zwc.mygreatidea.android.common;

import name.zwc.mygreatidea.android.entities.Idea;
import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityBase extends Activity
{
	protected static Idea[] ideas;
	
	protected Activity context;
	protected WebService service = new WebService();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		context = this;
	}
	
	//
	// The user's name and its contact.
	//
	protected static String _userName = null;
	protected String getUserName()
	{
		if (_userName == null)
		{
			_userName = getPreference("_userName");
		}
		return _userName;
	}
	protected void setUserName(String value)
	{
		setPreference("_userName", value);
		_userName = value;
	}
	protected static String _userContact = null;
	protected String getUserContact()
	{
		if (_userContact == null)
		{
			_userContact = getPreference("_userContact");
		}
		return _userContact;
	}
	protected void setUserContact(String value)
	{
		setPreference("_userContact", value);
		_userContact = value;
	}
	protected boolean ensureSetting()
	{
		String userName = getUserName();
		String userContact = getUserContact();
		
		if (userName == null 
				|| userName.equals("")
				|| userContact == null
				|| userContact.equals(""))
		{
			Helpers.startActivity(context, "SettingActivity");
			return false;
		}
		return true;
	}
	
	//
	// Retrieve and store user settings.
	//
	protected void setPreference(String key, String value)
	{
		SharedPreferences preferences = getSharedPreferences("user", Application.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	protected String getPreference(String key)
	{
		SharedPreferences preferences = getSharedPreferences("user", Application.MODE_PRIVATE);
		return preferences.getString(key, null);
	}

	protected Handler handler = new Handler()
	{
		public void handleMessage(Message msg) 
		{
			switch (msg.what)
			{
			case 1: // showInformation method
				Toast.makeText(context, (String)msg.obj, Toast.LENGTH_SHORT).show();
				break;
			case 2: // setAdapter method
				ListView listView = (ListView)findViewById(msg.arg1);
				listView.setAdapter((BaseAdapter)msg.obj);
				break;
			default:
				break;
			}
		};
	};
	protected Button button(int viewID)
	{
		return (Button) findViewById(viewID);
	}
	protected EditText editText(int viewID)
	{
		return (EditText) findViewById(viewID);
	}
	protected String getInputText(int viewID)
	{
		EditText editText  = (EditText) findViewById(viewID);
		return editText.getText().toString().trim();
	}
	protected void setReturnButton(int viewID)
	{
		button(viewID).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				v.setEnabled(false);
				context.finish();
			}
		});
	}
	
	protected void showInformation(String content)
	{
		Message message = new Message();
		message.what = 1;
		message.obj = content;
		handler.sendMessage(message);
	}
	protected void showInformation(int resourceID)
	{
		String content = getResources().getString(resourceID);
		showInformation(content);
	}
	protected void linkButtonToActivity(int buttonViewID, String activityName)
	{
		linkButtonToActivity(buttonViewID, activityName, 0);
	}
	protected void linkButtonToActivity(int buttonViewID, String activityName, int value)
	{
		ActivityLinkerOnClickListener onClickListener = new ActivityLinkerOnClickListener(this, activityName, value);
		button(buttonViewID).setOnClickListener(onClickListener);
	}
	protected void setBackgroundTask(int buttonViewID, Runnable task)
	{
		BackgroundTaskOnClickListener onClickListener = new BackgroundTaskOnClickListener(context, buttonViewID, task);
		button(buttonViewID).setOnClickListener(onClickListener);
	}
	protected void setAdapter(int viewID, BaseAdapter adapter)
	{
		Message message = new Message();
		message.what = 2;
		message.arg1 = viewID;
		message.obj = adapter;
		handler.sendMessage(message);
	}
}
