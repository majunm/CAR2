package com.m.car2;

import android.os.Parcel;
import android.os.Parcelable;

public class CarInfo implements Parcelable {
	public int resId;
	public String text;

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CarInfo() {
		super();
	}

	public CarInfo(int resId, String text) {
		super();
		this.resId = resId;
		this.text = text;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeInt(resId);
		parcel.writeString(text);
	}

	/**
	 * 序列化实体类
	 */
	public static final Parcelable.Creator<CarInfo> CREATOR = new Creator<CarInfo>() {
		public CarInfo createFromParcel(Parcel source) {
			CarInfo personPar = new CarInfo();
			personPar.resId = source.readInt();
			personPar.text = source.readString();
			return personPar;
		}

		public CarInfo[] newArray(int size) {
			return new CarInfo[size];
		}
	};

}
