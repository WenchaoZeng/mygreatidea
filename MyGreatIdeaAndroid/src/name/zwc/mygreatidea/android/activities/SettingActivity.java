package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import android.os.Bundle;

public class SettingActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		// 读取原先数据
		editText(R.id.editTextUserName).setText(getUserName());
		editText(R.id.editTextUserContact).setText(getUserContact());
		
		// 提交按钮
		setBackgroundTask(R.id.buttonSubmit, new Runnable()
		{
			public void run()
			{
				// 保存数据
				String userName = editText(R.id.editTextUserName).getText().toString().trim();
				setUserName(userName);
				String userContact = editText(R.id.editTextUserContact).getText().toString().trim();
				setUserContact(userContact);
				
				showInformation("设置成功");
				context.finish();
			}
		});
	}
}
