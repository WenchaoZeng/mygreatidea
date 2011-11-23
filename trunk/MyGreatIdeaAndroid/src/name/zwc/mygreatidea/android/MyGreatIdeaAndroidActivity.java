package name.zwc.mygreatidea.android;

import name.zwc.mygreatidea.android.util.Helpers;
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