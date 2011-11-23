package name.zwc.mygreatidea.android;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import name.zwc.mygreatidea.android.entities.Comment;

public class CommentListAdapter extends AdapterBase
{
	public CommentListAdapter(Activity context)
	{
		super(context, R.layout.comment_item);
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = super.getView(position, convertView, parent);
		Comment comment = (Comment)datasource[position];
		setCommentView(convertView, comment);
		return convertView;
	}
	protected void setCommentView(View context, Comment comment)
	{
		((TextView)context.findViewById(R.id.textViewUserName)).setText(comment.UserName);
		((TextView)context.findViewById(R.id.textViewDateTime)).setText(String.valueOf(comment.PostTime));
		((TextView)context.findViewById(R.id.textViewContent)).setText(comment.Content);
	}
}