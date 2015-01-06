package com.m.base;

import java.util.ArrayList;

import com.m.base.SingleCheckView.OnSingleOnClickListener;
import com.m.car2.R;
import com.m.domain.CarInfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CheckView extends RelativeLayout {

	private SingleCheckView checkOne;
	private SingleCheckView checkTwo;
	private SingleCheckView checkThree;
	private SingleCheckView checkFour;

	public CheckView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public CheckView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public CheckView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		View.inflate(context, R.layout.check_view, this);
		checkOne = (SingleCheckView) findViewById(R.id.check_one);
		checkTwo = (SingleCheckView) findViewById(R.id.check_two);
		checkThree = (SingleCheckView) findViewById(R.id.check_three);
		checkFour = (SingleCheckView) findViewById(R.id.check_four);
		
		checkOne.setOnSingleClickListener(new OnSingleOnClickListener() {
			@Override
			public void onClick() {
				goListener(0);
			}
		});
		checkFour.setOnSingleClickListener(new OnSingleOnClickListener() {
			
			@Override
			public void onClick() {
				goListener(3);
			}
		});
		checkTwo.setOnSingleClickListener(new OnSingleOnClickListener() {
			
			@Override
			public void onClick() {
				goListener(1);
			}
		});
		checkThree.setOnSingleClickListener(new OnSingleOnClickListener() {
			@Override
			public void onClick() {
				goListener(2);
			}
		});
	}
	
	public void clear(int position){
//		switch (position) {
//		case 0:
//			checkOne.setChecked();
//			break;
//		case 1:
//			checkTwo.setChecked();
//			break;
//		case 2:
//			checkThree.setChecked();
//			break;
//		case 3:
//			checkFour.setChecked();
//			break;
//		}
		checkFour.setChecked();
		checkOne.setChecked();
		checkThree.setChecked();
		checkTwo.setChecked();
	}
	
	
	private ArrayList<CarInfo> carInfos ; 
	public void setData(ArrayList<CarInfo>  carInfos){
		if (this.carInfos == null) {
			this.carInfos = new ArrayList<CarInfo>();
		}
		this.carInfos.clear();
		this.carInfos.addAll(carInfos );
		checkOne.setData(this.carInfos.get(0).getChinaName());
		checkTwo.setData(this.carInfos.get(1).getChinaName());
		checkThree.setData(this.carInfos.get(2).getChinaName());
		checkFour.setData(this.carInfos.get(3).getChinaName());
		
	}
	
	
	
	private void goListener(int position){
		if (this.checkListener != null) {
			checkListener.onClivk(position);
		}
	}
	
	private CheckListener checkListener ;
	public void setCheckListener(CheckListener checkListener){
		this.checkListener = checkListener ;
	}
	
	public interface  CheckListener{
		public void onClivk(int position);
	}

}
