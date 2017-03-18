package obj;


import android.content.Context;
import android.util.Log;
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
			viewHolder.id2=(TextView)view.findViewById(R.id.number2);
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
		int id2=Integer.valueOf(course.getClassNum()).intValue()+1;
		Log.d("num",String.valueOf(id2));
		viewHolder.id2.setText(String.valueOf(id2));
		if(!course.getCourseName().equals(""))
		{
			viewHolder.ltext0.setText(course.getCourseName());
			viewHolder.ltext1.setText(course.getLoca());
			viewHolder.ltext6.setText(course.getTeacher());
			viewHolder.ltext7.setText(course.getZhoushu());
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
		TextView id2;
		TextView ltext0;
		TextView ltext1;
		TextView ltext6;
		TextView ltext7;


	}

}
