package name.zwc.mygreatidea.android.common;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.entities.Idea;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class Helpers
{
	public static void startActivity(Activity context, String activityName)
	{
		startActivity(context, activityName, 0);
	}
	public static void startActivity(Activity context, String activityName, int value)
	{
		Intent intent = new Intent("name.zwc.mygreatidea." + activityName);
		intent.putExtra("value", value);
		context.startActivity(intent);
	}
	public static int getValue(Intent intent)
	{
		return intent.getIntExtra("value", 0);
	}
	public static void setIdeaView(View context, Idea idea)
	{
		((TextView)context.findViewById(R.id.textViewTitle)).setText(idea.Title);
		((TextView)context.findViewById(R.id.textViewDateTime)).setText(String.valueOf(idea.PostTime));
		((TextView)context.findViewById(R.id.textViewCommentCount)).setText(String.valueOf(idea.CommentCount));
		((TextView)context.findViewById(R.id.textViewContent)).setText(idea.Content);
	}
}
