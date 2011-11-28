package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.adapters.CommentListAdapter;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import name.zwc.mygreatidea.android.entities.Idea;
import android.os.Bundle;

public class IdeaDetailActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.idea_detail);
		
		// Show idea content
		int index = Helpers.getValue(getIntent());
		Idea idea = ideas[index];
		Helpers.setIdeaView(context.findViewById(R.id.include_idea), idea);
		
		// Show the comment list.
		CommentListAdapter adapter = new CommentListAdapter(context);
		adapter.datasource = service.getComments(idea.Id);
		setAdapter(R.id.listViewComments, adapter);
		
		setReturnButton(R.id.buttonReturn);
		linkButtonToActivity(R.id.buttonSubmitComment, "SubmitCommentActivity", index);
	}
}
