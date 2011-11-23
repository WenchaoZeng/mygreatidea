package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.util.Helpers;
import android.os.Bundle;

public class SubmitCommentActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_comment);
		
		final int ideaID = Helpers.getValue(getIntent());
		
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