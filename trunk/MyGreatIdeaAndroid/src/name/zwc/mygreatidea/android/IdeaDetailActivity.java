package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.entities.Idea;
import name.zwc.mygreatidea.android.util.Helpers;
import android.os.Bundle;

public class IdeaDetailActivity extends ActivityBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.idea_detail);
		
		// 显示想法内容
		int index = Helpers.getValue(getIntent());
		Idea idea = ideas[index];
		Helpers.setIdeaView(context.findViewById(R.id.include_idea), idea);
		
		// 显示评论列表
		CommentListAdapter adapter = new CommentListAdapter(context);
		adapter.datasource = service.getComments(idea.Id);
		setAdapter(R.id.listViewComments, adapter);
		
		setReturnButton(R.id.buttonReturn);
		linkButtonToActivity(R.id.buttonSubmitComment, "SubmitCommentActivity", index);
	}
}
