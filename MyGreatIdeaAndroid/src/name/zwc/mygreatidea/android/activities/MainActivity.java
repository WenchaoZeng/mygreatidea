package name.zwc.mygreatidea.android.activities;

import name.zwc.mygreatidea.android.R;
import name.zwc.mygreatidea.android.adapters.IdeaListAdapter;
import name.zwc.mygreatidea.android.common.ActivityBase;
import name.zwc.mygreatidea.android.common.Helpers;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 发布想法按钮
        linkButtonToActivity(R.id.buttonSubmitIdea, "SubmitIdeaActivity");
        
        // 设置按钮
        linkButtonToActivity(R.id.buttonSetting, "SettingActivity");
        
        // 刷新按钮
        setBackgroundTask(R.id.buttonRefresh, new Runnable()
		{
			public void run()
			{
				// 显示列表
				IdeaListAdapter adapter = new IdeaListAdapter(context);
				ideas = service.getIdeas(Integer.MAX_VALUE);
				adapter.datasource = ideas;
				setAdapter(R.id.listViewIdeas, adapter);
				
				// 点击事件
				ListView listView = (ListView)findViewById(R.id.listViewIdeas);
				listView.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> parent, View view, int position, long id)
					{
						Helpers.startActivity(context, "IdeaDetailActivity", position);
					}
				});
			}
		});
        button(R.id.buttonRefresh).performClick();
    }
}
