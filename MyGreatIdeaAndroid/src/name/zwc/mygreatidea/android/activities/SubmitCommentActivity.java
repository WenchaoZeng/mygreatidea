package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import android.os.Bundle;

public class SubmitCommentActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_comment);
		
		int index = Helpers.getValue(getIntent());
		final int ideaID = ideas[index].Id;
		
		setReturnButton(R.id.buttonReturn);
		
		setBackgroundTask(R.id.buttonSubmit, new Runnable()
		{
			public void run()
			{
				try
				{
					showInformation(String.valueOf(ideaID));
					Thread.sleep(2000);
				}
				catch (InterruptedException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				showInformation("提交成功");
				context.finish();
			}
		});
	}
}