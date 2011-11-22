package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.entities.Idea;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SubmitIdeaActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_idea);

		// 提交按钮
		button(R.id.buttonSubmit).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// 保证用户已经设置了用户名和联系方式
				if (!ensureSetting())
				{
					return;
				}
				
				((Button)v).setEnabled(false);
				new Thread(new Runnable()
				{
					public void run()
					{
						// 发布想法
						Idea idea = new Idea();
						idea.UserName = getUserName();
						idea.UserContact = getUserContact();
						idea.Title = editText(R.id.editTextTitle).getText().toString().trim();
						idea.Content = editText(R.id.editTextContent).getText().toString().trim();
						
						if (service.submitIdea(idea))
						{
							showInformation("发布成功");
							context.finish();
						}
						else
						{
							showInformation("发布失败");
						}
					}
				}).start();
			}
		});
		
		// 返回按钮
		button(R.id.buttonReturn).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				context.finish();
			}
		});
	}
}
