package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.entities.Idea;
import android.os.Bundle;

public class SubmitIdeaActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_idea);

		// The submit button.
		setBackgroundTask(R.id.buttonSubmit, new Runnable()
		{
			public void run()
			{
				// Ensure the user has set its name and contact.
				if (!ensureSetting())
				{
					return;
				}
				
				// Submit the idea.
				Idea idea = new Idea();

				idea.Title = getInputText(R.id.editTextTitle);
				if (idea.Title.equals(""))
				{
					showInformation(R.string.please_input_title);
					return;
				}
				
				idea.Content = getInputText(R.id.editTextContent);
				if (idea.Content.equals(""))
				{
					showInformation(R.string.please_input_content);
					return;
				}
				
				idea.UserName = getUserName();
				idea.UserContact = getUserContact();
				
				if (service.submitIdea(idea))
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
		
		// The behavior of the back button
		setReturnButton(R.id.buttonReturn);
	}
}
