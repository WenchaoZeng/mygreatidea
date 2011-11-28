package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import name.zwc.mygreatidea.android.entities.Comment;
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
				// Ensure the user has set its name and contact.
				if (!ensureSetting())
				{
					return;
				}
				
				Comment comment = new Comment();
				comment.IdeaId = ideaID;
				comment.Content = getInputText(R.id.editTextContent);
				
				comment.UserName = getUserName();
				comment.UserContact = getUserContact();
				
				if (service.submitComment(comment))
				{
					showInformation(R.string.submit_success);
					context.finish();
				}
				else
				{
					showInformation(R.string.submit_fail);
				}
			}
		});
	}
}