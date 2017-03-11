package Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jmusch.Course_add;
import com.example.jmusch.MainActivity;
import com.example.jmusch.R;

import obj.Course;
import obj.CourseAdapter;
import obj.CourseList;

/**
 * Created by Administrator on 2017-03-04.
 */

public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private CourseList courseList=new CourseList();
    CourseAdapter adapter;
    private String[]data={"apple","banana","orange",
            "pear","grape","pineapple","123","12351","13513","3463463","12451245"
    };

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_page,container,false);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,data);
//        ListView listView=(ListView)view.findViewById(R.id.list1);
//        listView.setAdapter(adapter);
        final View view = inflater.inflate(R.layout.fragment_page,container,false);
        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList1());
        switch (mPage) {
									case 1:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList1());
										break;
									case 2:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList2());
										break;
									case 3:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList3());
										break;
									case 4:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList4());
										break;
									case 5:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList5());
										break;
									case 6:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList6());
										break;
									case 7:
                                        adapter=new CourseAdapter(view.getContext(),R.layout.course_item,courseList.getCourseList7());
										break;
									default:
								}
        ListView listView=(ListView)view.findViewById(R.id.list1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    final int id, long arg3) {
                final int currentDay=mPage;
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setTitle("选择");
                TextView tv=(TextView)arg1.findViewById(R.id.ltext0);
                Log.i("Test",(tv.getText().toString().equals(""))+"");
                //如果课程栏目为空就启动添加对话框
                if((tv.getText()).toString().equals("")){
                    //通过数组资源为对话框中的列表添加选项内容，这里只有一个选项
                    dialog.setItems(R.array.edit_options1, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Course_add add=new Course_add();
                            add.add(view.getContext(),currentDay,id+1,adapter);
                        }
                    });
                    dialog.create().show();
                }
                else{
                    dialog.setItems(R.array.edit_options2, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==1)
                            {
                                Course_add add=new Course_add();
                                add.delete(view.getContext(),currentDay,id+1,adapter);
                            }
                            else
                            {
                                Course_add add=new Course_add();
                                add.edit(view.getContext(),currentDay,id+1,adapter);
                            }
                        }
                    });
                    dialog.create().show();

                }

            }
        });
        return view;
    }
}