package com.m.base;

import com.m.car2.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class SingleCheckView extends RelativeLayout implements OnClickListener {
	public SingleCheckView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public SingleCheckView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public SingleCheckView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		View.inflate(context, R.layout.single_check_view, this);
		setGravity(Gravity.CENTER);
		check = (CheckBox) findViewById(R.id.check_check);
		
		text = (TextView) findViewById(R.id.check_text);
		
		check.setOnClickListener(this);
		text.setOnClickListener(this);
	}
	
	
	public void setData(String name){
		text.setText(name);
	}
	
	
	public void setChecked(){
		check.setChecked(false);
	}
	
	
	private OnSingleOnClickListener onSingleOnClickListener ;
	private CheckBox check;
	private TextView text; 
	public void setOnSingleClickListener(OnSingleOnClickListener  onSingleOnClickListener){
		this.onSingleOnClickListener = onSingleOnClickListener ;
	}
	
	@Override
	public void onClick(View arg0) {
		if (this.onSingleOnClickListener != null) {
			check.setChecked(!check.isChecked());
			this.onSingleOnClickListener.onClick();
		}
	}
	
	
	public boolean isChecked(){
		return check.isChecked();
	}


	public interface OnSingleOnClickListener{
		public void onClick();
	}

}
