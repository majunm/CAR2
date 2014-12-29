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

	/** 200个汽车品牌 */
	public CarInfo(int type) {
		super();
		init(type);
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

	private void init(int type) {
		switch (type) {
		case 0:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 1:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 2:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 3:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 4:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 5:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 6:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 7:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 8:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 9:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 10:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 11:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 12:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 13:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 14:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 15:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 16:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 17:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 18:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 19:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 20:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 21:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 22:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 23:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 24:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 25:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 26:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 27:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 28:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 29:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 30:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 31:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 32:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 33:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 34:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 35:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 36:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 37:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 38:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 39:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 40:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 41:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 42:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 43:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 44:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 45:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 46:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 47:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 48:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 49:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 50:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 51:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 52:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 53:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 54:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 55:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 56:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 57:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 58:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 59:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 60:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 61:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 62:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 63:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 64:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 65:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 66:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 67:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 68:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 69:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 70:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 71:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 72:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 73:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 74:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 75:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 76:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 77:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 78:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 79:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 80:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 81:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 82:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 83:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 84:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 85:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 86:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 87:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 88:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 89:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 90:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 91:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 92:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 93:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 94:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 95:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 96:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 97:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 98:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 99:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 100:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 101:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 102:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 103:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 104:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 105:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 106:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 107:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 108:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 109:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 110:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 111:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 112:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 113:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 114:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 115:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 116:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 117:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 118:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 119:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 120:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 121:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 122:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 123:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 124:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 125:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 126:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 127:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 128:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 129:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 130:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 131:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 132:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 133:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 134:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 135:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 136:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 137:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 138:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 139:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 140:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 141:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 142:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 143:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 144:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 145:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 146:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 147:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 148:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 149:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 150:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 151:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 152:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 153:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 154:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 155:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 156:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 157:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 158:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 159:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 160:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 161:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 162:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 163:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 164:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 165:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 166:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 167:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 168:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 169:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 170:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 171:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 172:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 173:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 174:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 175:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 176:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 177:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 178:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 179:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 180:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 181:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 182:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 183:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 184:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 185:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 186:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 187:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 188:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 189:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		/** --------------------- */
		case 190:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 191:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 192:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 193:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 194:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 195:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 196:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 197:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 198:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		case 199:
			this.resId = R.drawable.ferrari;
			this.text = "法拉利";
			break;
		}
	}

}
