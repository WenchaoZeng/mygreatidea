package name.zwc.mygreatidea.android.common;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Listen to on click event and jump to an target activity.
 * @author ZengWenchao
 */
public class ActivityLinkerOnClickListener implements OnClickListener
{
	public Activity context;
	public String targetActivityName;
	public int value;
	
	public ActivityLinkerOnClickListener(Activity context, String targetActivityName, int value)
	{
		this.context = context;
		this.targetActivityName = targetActivityName;
		this.value = value;
	}
	public void onClick(View v)
	{
		Helpers.startActivity(context, targetActivityName, value);
	}
}
