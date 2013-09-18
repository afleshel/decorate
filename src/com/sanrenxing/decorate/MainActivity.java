package com.sanrenxing.decorate;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sanrenxing.decorate.custom.AnimationLayout;

public class MainActivity extends Activity implements AnimationLayout.Listener{
    public final static String TAG = "MainActivity";

    protected ListView lv_sidebar;//lidebar listview
    protected AnimationLayout mLayout;
    protected String[] mStrings = {"我的主页", "排行推荐", "我的收藏", "关于我们"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
        mLayout.setListener(this);

        lv_sidebar   = (ListView) findViewById(R.id.lv_sidebar);
        lv_sidebar.setAdapter(new ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice, mStrings));
        lv_sidebar.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        lv_sidebar.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mLayout.toggleSidebar();
				System.out.println("选择了第"+position);
			}
		});
    }
    
    public void onClickContentButton(View v) {
        mLayout.toggleSidebar();
        System.out.println("111111");
    }

    //返回键
    @Override
    public void onBackPressed() {
    	//如果sidebar开启的时候，关闭sidebar，否则关闭软件
        if (mLayout.isOpening()) {
            mLayout.closeSidebar();
        } else {
            finish();
        }
    }

    /* 当sidebar开启时候调用*/
    @Override
    public void onSidebarOpened() {
        Log.d(TAG, "opened");

        System.out.println("44444");
    }

    /* 当关闭sidebar时候调用此方法 */
    @Override
    public void onSidebarClosed() {
        Log.d(TAG, "opened");

        System.out.println("5555");
    }

    /*当sidebar开启，并且点击首页上收起sidebar按钮时候调用此方法*/
    @Override
    public boolean onContentTouchedWhenOpening() {
        // the content area is touched when sidebar opening, close sidebar
        Log.d(TAG, "going to close sidebar");
        mLayout.closeSidebar();

        System.out.println("66666");
        return true;
    }
}
