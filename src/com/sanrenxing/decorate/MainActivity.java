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
    protected String[] mStrings = {"�ҵ���ҳ", "�����Ƽ�", "�ҵ��ղ�", "��������"};

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
				System.out.println("ѡ���˵�"+position);
			}
		});
    }
    
    public void onClickContentButton(View v) {
        mLayout.toggleSidebar();
        System.out.println("111111");
    }

    //���ؼ�
    @Override
    public void onBackPressed() {
    	//���sidebar������ʱ�򣬹ر�sidebar������ر����
        if (mLayout.isOpening()) {
            mLayout.closeSidebar();
        } else {
            finish();
        }
    }

    /* ��sidebar����ʱ�����*/
    @Override
    public void onSidebarOpened() {
        Log.d(TAG, "opened");

        System.out.println("44444");
    }

    /* ���ر�sidebarʱ����ô˷��� */
    @Override
    public void onSidebarClosed() {
        Log.d(TAG, "opened");

        System.out.println("5555");
    }

    /*��sidebar���������ҵ����ҳ������sidebar��ťʱ����ô˷���*/
    @Override
    public boolean onContentTouchedWhenOpening() {
        // the content area is touched when sidebar opening, close sidebar
        Log.d(TAG, "going to close sidebar");
        mLayout.closeSidebar();

        System.out.println("66666");
        return true;
    }
}
