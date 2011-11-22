package name.zwc.mygreatidea.android;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class AdapterBase extends BaseAdapter
{
	protected LayoutInflater inflater;
	public AdapterBase(Activity context)
	{
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
}
