package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.entities.Idea;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IdeaListAdapter extends AdapterBase
{
	public Idea[] ideas = null;
	
	public IdeaListAdapter(Activity context)
	{
		super(context);
	}

	public int getCount()
	{
		return ideas.length;
	}

	public Object getItem(int position)
	{
		return ideas[position];
	}

	public long getItemId(int position)
	{
		return ideas[position].Id;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = inflater.inflate(R.layout.idea_item, null);
		Idea idea = ideas[position];
		
		((TextView)convertView.findViewById(R.id.textViewTitle)).setText(idea.Title);
		((TextView)convertView.findViewById(R.id.textViewDateTime)).setText(idea.PostTime);
		((TextView)convertView.findViewById(R.id.textViewCommentCount)).setText(idea.CommentCount);
		((TextView)convertView.findViewById(R.id.textViewContent)).setText(idea.Content);
		
		return convertView;
	}

}
