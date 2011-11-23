package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import android.os.Bundle;

public class MyGreatIdeaAndroidActivity extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	
    	new Thread(new Runnable()
		{
			public void run()
			{
				Helpers.startActivity(context, "MainActivity");
				finish();
			}
		}).start();
    }
}