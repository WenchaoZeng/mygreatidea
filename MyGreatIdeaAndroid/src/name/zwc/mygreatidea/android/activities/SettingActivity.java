package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import android.content.res.Resources;
import android.os.Bundle;

public class SettingActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		// Read the existing values.
		editText(R.id.editTextUserName).setText(getUserName());
		editText(R.id.editTextUserContact).setText(getUserContact());
		
		// The submit button.
		setBackgroundTask(R.id.buttonSubmit, new Runnable()
		{
			public void run()
			{
				// Saving values.
				String userName = getInputText(R.id.editTextUserName);
				setUserName(userName);
				
				String userContact = getInputText(R.id.editTextUserContact);
				setUserContact(userContact);
				
				showInformation(R.string.set_success);
				context.finish();
			}
		});
	}
}
