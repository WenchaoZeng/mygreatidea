package name.zwc.mygreatidea.android.adapters;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.AdapterBase;
import name.zwc.mygreatidea.android.common.Helpers;
import name.zwc.mygreatidea.android.entities.Idea;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class IdeaListAdapter extends AdapterBase
{
	public IdeaListAdapter(Activity context)
	{
		super(context, R.layout.idea_item);
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = super.getView(position, convertView, parent);
		Idea idea = (Idea)datasource[position];
		Helpers.setIdeaView(convertView, idea);
		return convertView;
	}

}
