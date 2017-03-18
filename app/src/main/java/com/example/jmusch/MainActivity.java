package com.example.jmusch;

import com.wx.wheelview.widget.WheelViewDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import Fragment.MyFragmentPagerAdapter;
import obj.Course;
import obj.CourseAdapter;
import obj.CourseList;
import tool.MyApplication;
import tool.ShareMethod;

public class MainActivity extends AppCompatActivity {
	public ListView list[] = new ListView[7];
	private TabHost tabs   = null;
	private TextView exitButton = null;
	private TextView setButton = null;
	CourseList courseList = new CourseList();
	CourseAdapter []adapter=new CourseAdapter[7];
	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private ListView mListView;
	private ArrayAdapter mAdapter;
    private LinearLayout search_layout;
	private LinearLayout search_layout2;
	private SearchView msearchview;
	private Button bt1;
	private Button bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//判断程序是否第一次启动
		SharedPreferences pref=getSharedPreferences("SHARE_APP_TAG",MODE_PRIVATE);
		SharedPreferences.Editor editor=getSharedPreferences("SHARE_APP_TAG",MODE_PRIVATE).edit();
		Boolean user_first=pref.getBoolean("FIRST",true);
		//创建数据库
		LitePal.getDatabase();
		//第一次
		if(user_first){
			editor.putBoolean("FIRST",false).commit();
			editor.putInt("weekNum",1).commit();
			CourseList.setWeekNum(1);
			//第一次创建对数据库对其赋初值
			for(int i=1;i<=7;i++) {
				for (int j = 1; j <=12; j++) {
					if(j%2!=0)
					{
						Course course = new Course();
						course.setWeekDay(String.valueOf(i));
						course.setClassNum(String.valueOf(j));
						course.save();
					}
				}
				adbSuit();
			}
		}
		else{
			int weekNum=pref.getInt("weekNum",1);
			CourseList.setWeekNum(weekNum);
			adbSuit();
		}
        //设置侧滑菜单和标题栏
		mToolbar = (Toolbar) findViewById(R.id.tool_bar);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationView=(NavigationView) findViewById(R.id.id_nv_menu);
		mNavigationView.setItemIconTintList(null);
//		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//			@Override public boolean onNavigationItemSelected(MenuItem menuItem) {
//				switch (menuItem.getItemId())
//				{
//					case R.id.nav_alarmclock:{
//						AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//						//    设置Title的图标
//						builder.setIcon(R.drawable.ic_launcher);
//						//    设置Title的内容
//						builder.setTitle("弹出警告框");
//						//    设置Content来显示一个信息
//						builder.setMessage("确定删除吗？");
//						//    设置一个PositiveButton
//						builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//						{
//							@Override
//							public void onClick(DialogInterface dialog, int which)
//							{
//								Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
//							}
//						});
////						menuItem.setChecked(true);
////						Intent in1=new Intent(MainActivity.this,SettingActivity.class);
////						startActivity(in1);
//						break;
//					}
//					default:break;
//				}
//				return true;
//			}
//		});
		mToolbar.setTitle("第"+CourseList.getWeekNum()+"周");
		setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setupDrawerContent(mNavigationView);
//侧滑菜单打开关闭监听
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close){
			@Override
			public void onDrawerOpened(View drawerView) {
				//Toast.makeText(MyActivity.this,"Open",Toast.LENGTH_SHORT).show();
				super.onDrawerOpened(drawerView);
			}
			@Override
			public void onDrawerClosed(View drawerView) {
				//Toast.makeText(MyActivity.this,"Close",Toast.LENGTH_SHORT).show();
				super.onDrawerClosed(drawerView);
			}
		};
		mActionBarDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

		//设置选项卡
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
				this);
		viewPager.setAdapter(adapter);
		//TabLayout
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		adapter.notifyDataSetChanged();

		//搜索菜单
		bt1=(Button)findViewById(R.id.bt1);
		bt2=(Button)findViewById(R.id.bt2);
		search_layout2=(LinearLayout)findViewById(R.id.search_action);
		msearchview=(SearchView)findViewById(R.id.searchview);
		//int id=msearchview.getContext().getResources()."android:id/searchview",null,null);
		bt1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(v.getId()==R.id.bt1)
				{
					int i=0;
					i=search_layout2.getVisibility();
					if(i==8)
					{search_layout2.setVisibility(View.VISIBLE);
					    msearchview.setQueryHint("如:航海1511");}
					else
						msearchview.setQueryHint("如:航海1511");

				}

			}
		});
		bt2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(v.getId()==R.id.bt2)
				{
					int i=0;
					i=search_layout2.getVisibility();
					if(i==8)
					{search_layout2.setVisibility(View.VISIBLE);
					msearchview.setQueryHint("滚动菜单");}
					else
					msearchview.setQueryHint("滚动菜单");
				}

			}
		});
}

	//设置侧滑菜单点击事件
	private void setupDrawerContent(NavigationView navigationView)
	{
		navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener()
				{
					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						int i=menuItem.getItemId();
						switch (i)
						{
							case R.id.nav_setting:{
								Intent intent=new Intent(MainActivity.this,SettingActivity.class);
								startActivity(intent);
								break;
							}
							default:
								break;
						}
						mDrawerLayout.closeDrawers();
						return true;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.drawerlayout_meau,menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		List<String> PLANETS = new ArrayList<String>();
		for(int i=1;i<=20;i++)
			PLANETS.add(String.valueOf(i));

		switch(id) {
			case R.id.action_search:{
				//Intent intent=new Intent(MainActivity.this,SearchActivity.class);
				//startActivity(intent);
				search_layout=(LinearLayout)findViewById(R.id.search_layout);
				int i=0;
				i=search_layout.getVisibility();
				if(i==8)
					search_layout.setVisibility(View.VISIBLE);
				else
				{ search_layout.setVisibility(View.GONE);
				search_layout2.setVisibility(View.GONE);}
				break;
			}
			case R.id.change_weekNum:{
//				Intent in1=new Intent(MainActivity.this,SettingActivity.class);
//                	startActivity(in1);
				final WheelViewDialog dialog = new WheelViewDialog(this);
				dialog.setTitle("更改周数").setItems(createArrays()).setButtonText("确定").setDialogStyle(Color
						.parseColor("#6699ff")).setCount(5);
				dialog.setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
					@Override
					public void onItemClick(int position, String s) {
						int num=position+1;
						CourseList.setWeekNum(num);
						mToolbar.setTitle("第"+CourseList.getWeekNum()+"周");
						SharedPreferences.Editor editor = getSharedPreferences("SHARE_APP_TAG", MODE_PRIVATE).edit();
						editor.putInt("weekNum",num).commit();
					}
				});

				dialog.show();
				break;
			}
			default:break;
		}
		return super.onOptionsItemSelected(item);
	}
	private ArrayList<String> createArrays() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i <=20; i++) {
			list.add("第"+i+"周");
		}
		return list;
	}
	//使CourseList中的数据成为数据库的数据
	public void adbSuit(){
		List<Course> adbCourseList = DataSupport.where("weekDay = ?","1").order("id").find(Course.class);
		courseList.setCourseList1(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","2").order("id").find(Course.class);
		courseList.setCourseList2(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","3").order("id").find(Course.class);
		courseList.setCourseList3(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","4").order("id").find(Course.class);
		courseList.setCourseList4(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","5").order("id").find(Course.class);
		courseList.setCourseList5(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","6").order("id").find(Course.class);
		courseList.setCourseList6(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","7").order("id").find(Course.class);
		courseList.setCourseList7(adbCourseList);

	}
}
