package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.entities.Idea;
import android.os.Bundle;

public class SubmitIdeaActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_idea);

		// 提交按钮
		setBackgroundTask(R.id.buttonSubmit, new Runnable()
		{
			public void run()
			{
				// 保证用户已经设置了用户名和联系方式
				if (!ensureSetting())
				{
					return;
				}
				
				// 发布想法
				Idea idea = new Idea();

				idea.Title = editText(R.id.editTextTitle).getText().toString().trim();
				if (idea.Title.equals(""))
				{
					showInformation("请填写标题");
					return;
				}
				
				idea.Content = editText(R.id.editTextContent).getText().toString().trim();
				if (idea.Content.equals(""))
				{
					showInformation("请填写内容");
					return;
				}
				
				idea.UserName = getUserName();
				idea.UserContact = getUserContact();
				
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
		});
		
		// The behavior of the back button
		setReturnButton(R.id.buttonReturn);
	}
}
