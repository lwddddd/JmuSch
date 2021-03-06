package com.example.jmusch;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import obj.Course;
import obj.CourseAdapter;
import obj.CourseList;

import static com.example.jmusch.R.string.course_address;

/**
 * Created by admin on 2017/3/11.
 */

public class Course_add {
    Context mContext;
    View mView;
    int mWeekDay;
    int mClassNum;
    CourseList courseList=new CourseList();
    private EditText course_name;;
    private EditText course_address;
    private EditText course_teacher;
//    private EditText course_week;
//    private EditText course_count;

    public Course_add(){

    }
    //添加课程的窗体
    public Course_add(Context context, final int weekDay, final int classNum, final CourseAdapter adapter, final int where){

        mWeekDay=weekDay;
        mClassNum=classNum;
        LayoutInflater inflater= LayoutInflater.from(context);
        mContext=context;
        View view = inflater.inflate(R.layout.course_add,null);
        mView=view;
        findWidgetes();
        final AlertDialog.Builder ad =new AlertDialog.Builder(context);
        ad.setView(view);
        TextView tagName=(TextView)view.findViewById(R.id.course_add_tag);
        tagName.setText("新建课程");
        ad.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Course cou=new Course();
                cou.setWeekDay(String.valueOf(mWeekDay));
                cou.setClassNum(String.valueOf(mClassNum));
                if(!(course_name.getText().toString()).equals(""))
                    cou.setCourseName(course_name.getText().toString());
                if(!(course_teacher.getText().toString()).equals(""))
                    cou.setTeacher(course_teacher.getText().toString());
                if(!(course_address.getText().toString()).equals(""))
                    cou.setLoca(course_address.getText().toString());
                cou.saveAdb();
                switch (mWeekDay) {
                    case 1:
                        courseList.getCourseList1().set(where, cou);
                        break;
                    case 2:
                        courseList.getCourseList2().set(where, cou);
                        break;
                    case 3:
                        courseList.getCourseList3().set(where, cou);
                        break;
                    case 4:
                        courseList.getCourseList4().set(where, cou);
                        break;
                    case 5:
                        courseList.getCourseList5().set(where, cou);
                        break;
                    case 6:
                        courseList.getCourseList6().set(where, cou);
                        break;
                    case 7:
                        courseList.getCourseList7().set(where, cou);
                        break;
                    default:
                }
                adapter.notifyDataSetChanged();
            }
        });
        ad.show();
    }

    //删除课程
    public void delete(Context context, final int weekDay, final int classNum, final CourseAdapter adapter,int where){
        Course course=new Course();
        course.setClassNum(String.valueOf(classNum));
        course.setWeekDay(String.valueOf(weekDay));
        course.reSet();
        course.saveAdb();
        switch (weekDay) {
            case 1:
                courseList.getCourseList1().set(where, course);
                break;
            case 2:
                courseList.getCourseList2().set(where, course);
                break;
            case 3:
                courseList.getCourseList3().set(where, course);
                break;
            case 4:
                courseList.getCourseList4().set(where, course);
                break;
            case 5:
                courseList.getCourseList5().set(where, course);
                break;
            case 6:
                courseList.getCourseList6().set(where, course);
                break;
            case 7:
                courseList.getCourseList7().set(where, course);
                break;
            default:
        }
        adapter.notifyDataSetChanged();//删除信息
        Log.d("Main","delete");

    }

    //修改课程的窗体
    public void edit(Context context, final int weekDay, final int classNum, final CourseAdapter adapter, final int where){
        mWeekDay=weekDay;
        mClassNum=classNum;
        LayoutInflater inflater= LayoutInflater.from(context);
        mContext=context;
        View view = inflater.inflate(R.layout.course_add,null);
        mView=view;
        findWidgetes();
        final AlertDialog.Builder ad =new AlertDialog.Builder(context);
        ad.setView(view);
        TextView tagName=(TextView)view.findViewById(R.id.course_add_tag);
        tagName.setText("修改课程信息");
        List<Course> list=DataSupport.where("weekDay = ? and classNum=?",String.valueOf(weekDay),String.valueOf(classNum)).find(Course.class);
        final Course course=list.get(0);
        course_name.setText(course.getCourseName());
        course_teacher.setText(course.getTeacher());
        course_address.setText(course.getLoca());
        ad.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Course cou=course;
                if(!(course_name.getText().toString()).equals(""))
                    cou.setCourseName(course_name.getText().toString());
                if(!(course_teacher.getText().toString()).equals(""))
                    cou.setTeacher(course_teacher.getText().toString());
                if(!(course_address.getText().toString()).equals(""))
                    cou.setLoca(course_address.getText().toString());
                cou.saveAdb();
                switch (mWeekDay) {
                    case 1:
                        courseList.getCourseList1().set(where, cou);
                        break;
                    case 2:
                        courseList.getCourseList2().set(where, cou);
                        break;
                    case 3:
                        courseList.getCourseList3().set(where, cou);
                        break;
                    case 4:
                        courseList.getCourseList4().set(where, cou);
                        break;
                    case 5:
                        courseList.getCourseList5().set(where, cou);
                        break;
                    case 6:
                        courseList.getCourseList6().set(where, cou);
                        break;
                    case 7:
                        courseList.getCourseList7().set(where, cou);
                        break;
                    default:
                }
                adapter.notifyDataSetChanged();
            }
        });
        ad.show();
        Log.d("Main","edit");
    }

    //删除课程的对话框
    private void confirm_delete()
    {
        new AlertDialog.Builder(mContext).setTitle("系统提示");
    }

    private void findWidgetes() {
        course_name=(EditText)mView.findViewById(R.id.course_add_EditText_courseName);
        course_address=(EditText)mView.findViewById(R.id.course_add_EditText_loca);
        course_teacher=(EditText)mView.findViewById(R.id.course_add_EditText_teacher);

    }

}
