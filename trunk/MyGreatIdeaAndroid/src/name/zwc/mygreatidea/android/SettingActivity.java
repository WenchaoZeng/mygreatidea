package name.zwc.mygreatidea.android;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		button(R.id.buttonSubmit).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				((Button)v).setEnabled(false);

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
