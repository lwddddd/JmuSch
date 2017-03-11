package obj;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jmusch.R;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
	private int resourceId;

	public CourseAdapter(Context context, int textViewResourceId, List<Course> objects) {
		super(context, textViewResourceId,objects);
		resourceId=textViewResourceId;
	}
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Course course=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null)
		{
			view= LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.id=(TextView)view.findViewById(R.id.number);
			viewHolder.ltext0=(TextView)view.findViewById(R.id.ltext0);
			viewHolder.ltext1=(TextView)view.findViewById(R.id.ltext1);
			viewHolder.ltext6=(TextView)view.findViewById(R.id.ltext6);
			viewHolder.ltext7=(TextView)view.findViewById(R.id.ltext7);
			view.setTag(viewHolder);
		}
		else
		{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.id.setText(course.getClassNum());
		if(!course.getCourseName().equals(""))
		{
			viewHolder.ltext0.setText("课程名："+course.getCourseName());
			viewHolder.ltext1.setText("上课地点："+course.getLoca());
			viewHolder.ltext6.setText("授课老师："+course.getTeacher());
			viewHolder.ltext7.setText("上课周数："+course.getZhoushu());
		}
		else
		{
			viewHolder.ltext0.setText(course.getCourseName());
			viewHolder.ltext1.setText(course.getLoca());
			viewHolder.ltext6.setText(course.getTeacher());
			viewHolder.ltext7.setText(course.getZhoushu());

		}
		return view;

	}
	class ViewHolder{
		TextView id;
		TextView ltext0;
		TextView ltext1;
		TextView ltext6;
		TextView ltext7;


	}

}
