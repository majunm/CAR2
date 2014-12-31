package com.m.car2;

import android.os.Parcel;
import android.os.Parcelable;

public class CarInfo implements Parcelable {
	public int resId;
	public String text;
	public String story;
	public String englishName;
	public String chinaName;
	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

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

	/** 200������Ʒ�� */
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
	 * ���л�ʵ����
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

	/** 
	 *  ���������Ⱥ�:
	 *  0 = ��˹��˹    = a0
	 *  1 = ����            = a1
	 *  2 = ��ʱ��        = a2
	 *  3 = ������        = a3
	 *  4 = ��ɯ����    = a4
	 *  5 = ��������    = a5
	 *  6 = ����            = a6
	 *  7 = ����            = a7
	 *  8 = ���ͺ�        = a7
	 *  9 = ·˹��        = a9
	 *  10 = �µ�   = b0
	 *  11 = ����    = b1
	 *  12 = ·��   = b2
	 *  13 = ����    = b3
	 *  14 = ������������ŷ    = b4
	 *  15 = ����    = b5
	 *  16 = Ӣ�����    = b6
	 *  17 = ��ľ    = b7
	 *  18 = ѩ����    = b8
	 *  19 = ����    = b9
	 *  20 = ��������    = c0
	 *  21 = ����    = c1
	 *  22 = ����   = c2
	 *  23 = ����    = c3
	 *  24 = ����    = c4
	 *  25 = ����ȫ��ӥ    = c5
	 *  26 = �ղ�    = c6
	 *  27 = ����  = c7
	 *  28 = ŷ��    = c8
	 *  29 = �ݱ�    = c9
	 *  
	 *  30 = ����Ұ��    = d0
	 *  31 = ���    = d1
	 *  32 = �ֶ���  = d2
	 *  33 = ˹�´�    = d3
	 *  34 = ����    = d4
	 *  35 = �����ۺ�    = d5
	 *  36 = Ӣ��    = d6
	 *  37 = ����  = d7
	 *  38 = ˹��³    = d8
	 *  39 = ����    = d9
	 *  
	 *  40 = ����    = e0
	 *  41 = ����    = e1
	 *  42 = ����   = e2
	 *  43 = GMC  = e3
	 *  44 = ����Mini = e4
	 *  45 = ���Դ�    = e5
	 *  46 = ����   = e6
	 *  47 = ����  = e7
	 *  48 = ����    = e8
	 *  49 = ½��   = e9
	 *  
	 *  50 = ѩ����    = f0
	 *  51 = ����    = f1
	 *  52 = ����   = f2
	 *  53 = ����  = f3
	 *  54 = ���� = f4
	 *  55 = �л�    = f5
	 *  56 = ����  = f6
	 *  57 = ��˹�١���  = f7
	 *  58 = ����    = f8
	 *  59 = �׿���˹   = f9
	 *  
	 *  60 = �ִ�    = g0
	 *  61 = ����    = g1
	 *  62 = ������   = g2
	 *  63 = �������� = g3
	 *  64 = �������� = g4
	 *  65 = ������    = g5
	 *  66 = ������  = g6
	 *  67 = �ִ��Ͷ�˹  = g7
	 *  68 = ��̩   = g8
	 *  69 = ����˹��  = g9
	 *  
	 *  70 = ����    = h0
	 *  71 = ��ŵ    = h1
	 *  72 = ��������   = h2
	 *  73 = �Ͳ�˹ = h3
	 *  74 = ���� = h4
	 *  75 = ��������    = h5
	 *  76 = �������� = h6
	 *  77 = ����  = h7
	 *  78 = ���ǵ�   = h8
	 *  79 = ���ӵ�  = h9
	 *  
	 *  80 = ����    = i0
	 *  81 = ����Ұ��    = i1
	 *  82 = DS   = i2
	 *  83 = �� = i3
	 *  84 = ���� = i4
	 *  85 = ����    = i5
	 *  86 = ���� = i6
	 *  87 = ����  = i7
	 *  88 = ����   = i8
	 *  89 = ��������  = i9
	 * 
	 *  90 = ����    = j0
	 *  91 = ����    = j1
	 *  92 = ����   = j2
	 *  93 = ��̩ = j3
	 *  94 = �ƺ� = j4
	 *  95 = ����    = j5
	 *  96 = ���� = j6
	 *  97 = ��  = j7
	 *  98 = AC Schnitzer = j8
	 *  99 = ����  = j9
	 *  
	 *  100 = ������    = k0
	 *  101 = ˫��    = k1
	 *  102 = ����   = k2
	 *  103 = ���� = k3
	 *  104 = ���� = k4
	 *  105 = ����ɭ    = k5
	 *  106 = ک�� = k6
	 *  107 = ˼��  = k7
	 *  108 = �Ա�   = k8
	 *  109 = ˫��  = k9
	 *  
	 *  110 = Gumpert = l0
	 *  111 = ���    = l1
	 *  112 = ���ۺ�   = l2
	 *  113 = ���ǽ� = l3
	 *  114 = �ֿ� = l4
	 *  115 = ������    = l5
	 *  116 = ��Դ = l6
	 *  117 = ����  = l7
	 *  118 = KTM   = l8
	 *  119 = ����Smart = l9
	 *  
	 *  120 = ����    = m0
	 *  121 = ��˹��    = m1
	 *  122 = һ��   = m2
	 *  123 = ŷ�� = m3
	 *  124 = ���� = m4
	 *  125 = ������    = m5
	 *  126 = Fisker = m6
	 *  127 = ֮ŵ = m7
	 *  128 = Noble  = m8
	 *  129 = Ħ������  = m9
	 *  
	 *  130 = ��������    = n0
	 *  131 = ����    = n1
	 *  132 = ��������   = n2
	 *  133 = Hennessey = n3
	 *  134 = ���������� = n4
	 *  135 = �ֿ�˹����    = n5
	 *  136 = ˹���� = n6
	 *  137 = ----  = n7
	 *  138 = ----   = n8
	 *  139 = ----  = n9
	 *  */
	private void init(int type) {
		switch (type % 140) {
		case 0:
			this.resId = R.drawable.a0;
			this.text = "��˹��˹";
			this.chinaName = "��˹��˹";
			this.englishName = "Rolls-Royce";
			this.description = "��˹��˹��һ�������廯����������˾����ȫ��ͬʱҲ��Ŀǰ�������󺽿շ�����������֮һ��";
			break;
		case 1:
			this.resId = R.drawable.a1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Bentley";
			this.description = "";
			break;
		case 2:
			this.resId = R.drawable.a2;
			this.text = "��ʱ��";
			this.chinaName = "��ʱ��";
			this.englishName = "Porsche";
			this.description = "";
			break;
		case 3:
			this.resId = R.drawable.a3;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "Ferrari";
			this.description = "";
			break;
		case 4:
			this.resId = R.drawable.a4;
			this.text = "��ɯ����";
			this.chinaName = "��ɯ����";
			this.englishName = "Maserati";
			this.description = "";
			break;
		case 5:
			this.resId = R.drawable.a5;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Lamborghini";
			this.description = "";
			break;
		case 6:
			this.resId = R.drawable.a6;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Benz";
			this.description = "";
			break;
		case 7:
			this.resId = R.drawable.a7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "BMW";
			this.description = "";
			break;
		case 8:
			this.resId = R.drawable.a8;
			this.text = "���ͺ�";
			this.chinaName = "���ͺ�";
			this.englishName = "Maybach";
			this.description = "";
			break;
		case 9:
			this.resId = R.drawable.a9;
			this.text = "·˹��";
			this.chinaName = "·˹��";
			this.englishName = "Lotusnyo";
			this.description = "";
			break;
		case 10:
			this.resId = R.drawable.b0;
			this.text = "�µ�";
			this.chinaName = "�µ�";
			this.englishName = "Audi";
			this.description = "";
			break;
		case 11:
			this.resId = R.drawable.b1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "HUMMER";
			this.description = "";
			break;
		/** --------------------- */
		case 12:
			this.resId = R.drawable.b2;
			this.text = "·��";
			this.chinaName = "·��";
			this.englishName = "Land Rover";
			this.description = "";
			break;
		case 13:
			this.resId = R.drawable.b3;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "LianHua";
			this.description = "";
			break;
		case 14:
			this.resId = R.drawable.b4;
			this.text = "������������ŷ";
			this.chinaName = "������������ŷ";
			this.englishName = "Alfa Romeo";
			this.description = "";
			break;
		case 15:
			this.resId = R.drawable.b5;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "GEELY";
			this.description = "";
			break;
		case 16:
			this.resId = R.drawable.b6;
			this.text = "Ӣ�����";
			this.chinaName = "Ӣ�����";
			this.englishName = "INFINITI";
			this.description = "";
			break;
		case 17:
			this.resId = R.drawable.b7;
			this.text = "��ľ";
			this.chinaName = "��ľ";
			this.englishName = "SUZUKI";
			this.description = "";
			break;
		case 18:
			this.resId = R.drawable.b8;
			this.text = "ѩ����";
			this.chinaName = "ѩ����";
			this.englishName = "CITROEN";
			this.description = "";
			break;
		case 19:
			this.resId = R.drawable.b9;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "CHANGAN";
			this.description = "";
			break;
		/** --------------------- */
		case 20:
			this.resId = R.drawable.c0;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Chang'an_for_Business";
			this.description = "";
			break;
		case 21:
			this.resId = R.drawable.c1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "MG";
			this.description = "";
			break;
		case 22:
			this.resId = R.drawable.c2;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Karry";
			this.description = "";
			break;
		case 23:
			this.resId = R.drawable.c3;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Haima";
			this.description = "";
			break;
		case 24:
			this.resId = R.drawable.c4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "DongFeng";
			this.description = "";
			break;
		case 25:
			this.resId = R.drawable.c5;
			this.text = "����ȫ��ӥ";
			this.chinaName = "����ȫ��ӥ";
			this.englishName = "GLEAGLE";
			this.description = "";
			break;
		case 26:
			this.resId = R.drawable.c6;
			this.text = "�ղ�";
			this.chinaName = "�ղ�";
			this.englishName = "NISSAN";
			this.description = "";
			break;
		case 27:
			this.resId = R.drawable.c7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "TOYOTA";
			this.description = "";
			break;
		case 28:
			this.resId = R.drawable.c8;
			this.text = "ŷ��";
			this.chinaName = "ŷ��";
			this.englishName = "OPEL";
			this.description = "";
			break;
		case 29:
			this.resId = R.drawable.c9;
			this.text = "�ݱ�";
			this.chinaName = "�ݱ�";
			this.englishName = "Jaguar";
			this.description = "";
			break;
		/** --------------------- */
		case 30:
			this.resId = R.drawable.d0;
			this.text = "����Ұ��";
			this.chinaName = "����Ұ��";
			this.englishName = "MUSTANG";
			this.description = "";
			break;
		case 31:
			this.resId = R.drawable.d1;
			this.text = "���";
			this.chinaName = "���";
			this.englishName = "Buick";
			this.description = "";
			break;
		case 32:
			this.resId = R.drawable.d2;
			this.text = "�ֶ���";
			this.chinaName = "�ֶ���";
			this.englishName = "Volvo";
			this.description = "";
			break;
		case 33:
			this.resId = R.drawable.d3;
			this.text = "˹�´�";
			this.chinaName = "˹�´�";
			this.englishName = "SKODA";
			this.description = "";
			break;
		case 34:
			this.resId = R.drawable.d4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Roewe";
			this.description = "";
			break;
		case 35:
			this.resId = R.drawable.d5;
			this.text = "�����ۺ�";
			this.chinaName = "�����ۺ�";
			this.englishName = "EMGRAND";
			this.description = "";
			break;
		case 36:
			this.resId = R.drawable.d6;
			this.text = "Ӣ��";
			this.chinaName = "Ӣ��";
			this.englishName = "Engloncar";
			this.description = "";
			break;
		case 37:
			this.resId = R.drawable.d7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "RIICH";
			this.description = "";
			break;
		case 38:
			this.resId = R.drawable.d8;
			this.text = "˹��³";
			this.chinaName = "˹��³";
			this.englishName = "SUBARU";
			this.description = "";
			break;
		case 39:
			this.resId = R.drawable.d9;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "RELY";
			this.description = "";
			break;
		/** --------------------- */
		case 40:
			this.resId = R.drawable.e0;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "PEUGEOT";
			this.description = "";
			break;
		case 41:
			this.resId = R.drawable.e1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Venucia";
			this.description = "";
			break;
		case 42:
			this.resId = R.drawable.e2;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Volvo";
			this.description = "";
			break;
		case 43:
			this.resId = R.drawable.e3;
			this.text = "GMC";
			this.chinaName = "GMC";
			this.englishName = "GMC";
			this.description = "";
			break;
		case 44:
			this.resId = R.drawable.e4;
			this.text = "����Mini";
			this.chinaName = "����Mini";
			this.englishName = "BMW_MINI";
			this.description = "";
			break;
		case 45:
			this.resId = R.drawable.e5;
			this.text = "���Դ�";
			this.chinaName = "���Դ�";
			this.englishName = "Mazda";
			this.description = "";
			break;
		case 46:
			this.resId = R.drawable.e6;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Great Wall";
			this.description = "";
			break;
		case 47:
			this.resId = R.drawable.e7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "linian";
			this.description = "";
			break;
		case 48:
			this.resId = R.drawable.e8;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Volkswagen";
			this.description = "";
			break;
		case 49:
			this.resId = R.drawable.e9;
			this.text = "½��";
			this.chinaName = "½��";
			this.englishName = "landwind";
			this.description = "";
			break;
		/** --------------------- */
		case 50:
			this.resId = R.drawable.f0;
			this.text = "ѩ����";
			this.chinaName = "ѩ����";
			this.englishName = "Chevrolet";
			this.description = "";
			break;
		case 51:
			this.resId = R.drawable.f1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Ford";
			this.description = "";
			break;
		case 52:
			this.resId = R.drawable.f2;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "KIA";
			this.description = "";
			break;
		case 53:
			this.resId = R.drawable.f3;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "SAAB";
			this.description = "";
			break;
		case 54:
			this.resId = R.drawable.f4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "HuaPu";
			this.description = "";
			break;
		case 55:
			this.resId = R.drawable.f5;
			this.text = "�л�";
			this.chinaName = "�л�";
			this.englishName = "ZhongHua";
			this.description = "";
			break;
		case 56:
			this.resId = R.drawable.f6;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "chery";
			this.description = "";
			break;
		case 57:
			this.resId = R.drawable.f7;
			this.text = "��˹�١���";
			this.chinaName = "��˹�١���";
			this.englishName = "AstonMartin";
			this.description = "";
			break;
		case 58:
			this.resId = R.drawable.f8;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Xiali";
			this.description = "";
			break;
		case 59:
			this.resId = R.drawable.f9;
			this.text = "�׿���˹";
			this.chinaName = "�׿���˹";
			this.englishName = "Lexus";
			this.description = "";
			break;
		/** --------------------- */
		case 60:
			this.resId = R.drawable.g0;
			this.text = "�ִ�";
			this.chinaName = "�ִ�";
			this.englishName = "HYUNDAI";
			this.description = "";
			break;
		case 61:
			this.resId = R.drawable.g1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Ji-Ao";
			this.description = "";
			break;
		case 62:
			this.resId = R.drawable.g2;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "FIAT";
			this.description = "";
			break;
		case 63:
			this.resId = R.drawable.g3;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Cadillac";
			this.description = "����ͨ�ù�˾";
			break;
		case 64:
			this.resId = R.drawable.g4;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Wei-Wang";
			this.description = "";
			break;
		case 65:
			this.resId = R.drawable.g5;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "PAGANI";
			this.description = "����:�������Ħ����";
			break;
		case 66:
			this.resId = R.drawable.g6;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "Seat";
			this.description = "�¹�����������˾";
			break;
		case 67:
			this.resId = R.drawable.g7;
			this.text = "�ִ��Ͷ�˹";
			this.chinaName = "�ִ��Ͷ�˹";
			this.englishName = "POHENS";
			this.description = "�����ִ���������˾";
			break;
		case 68:
			this.resId = R.drawable.g8;
			this.text = "��̩";
			this.chinaName = "��̩";
			this.englishName = "Z-Tai";
			this.description = "�й��ڵ�-������˾����̩�عɼ���";
			break;
		case 69:
			this.resId = R.drawable.g9;
			this.text = "����˹��";
			this.chinaName = "����˹��";
			this.englishName = "Chrysler";
			this.description = "�������أ���������Ъ���ݺ���������";
			break;
		/** --------------------- */
		case 70:
			this.resId = R.drawable.h0;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "JAC";
			this.description = "�������أ��й������պϷ�\n������˾�����ս��������ɷ����޹�˾";
			break;
		case 71:
			this.resId = R.drawable.h1;
			this.text = "��ŵ";
			this.chinaName = "��ŵ";
			this.englishName = "RENAULT";
			this.description = "�������أ�������������-�����\n������˾����ŵ������˾";
			break;
		case 72:
			this.resId = R.drawable.h2;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Koenigsegg";
			this.description = "�������أ����";
			break;
		case 73:
			this.resId = R.drawable.h3;
			this.text = "�Ͳ�˹";
			this.chinaName = "�Ͳ�˹";
			this.englishName = "BRABUS";
			this.description = "�������أ��¹�\n������˾���Ͳ�˹ S��,�Ͳ�˹ SLS��";
			break;
		case 74:
			this.resId = R.drawable.h4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Bao-Jun";
			this.description = "������˾���Ϻ�ͨ��";
			break;
		case 75:
			this.resId = R.drawable.h5;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Beijing-Car";
			this.description = "�������أ��й�������\n������˾����������";
			break;
		case 76:
			this.resId = R.drawable.h6;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "Beijing-Builder";
			this.description = "�������أ��й�������\n������˾�������������쳧";
			break;
		case 77:
			this.resId = R.drawable.h7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "BESTURN";
			this.description = "������˾��һ������";
			break;
		case 78:
			this.resId = R.drawable.h8;
			this.text = "���ǵ�";
			this.chinaName = "���ǵ�";
			this.englishName = "BYD";
			this.description = "�������أ�������\n������˾�����ǵϹɷ����޹�˾";
			break;
		case 79:
			this.resId = R.drawable.h9;
			this.text = "���ӵ�";
			this.chinaName = "���ӵ�";
			this.englishName = "BUGATTI";
			this.description = "�������أ�������������˹\n������˾�����ڼ���";
			break;
		/** --------------------- */
		case 80:
			this.resId = R.drawable.i0;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "ChangHe";
			this.description = "�������أ��й�������ʡ��������\n������˾���й������������Źɷ����޹�˾";
			break;
		case 81:
			this.resId = R.drawable.i1;
			this.text = "����Ұ��";
			this.chinaName = "����Ұ��";
			this.englishName = "----";
			this.description = "�������أ��й����Ĵ�\n������˾����������";
			break;
		case 82:
			this.resId = R.drawable.i2;
			this.text = "DS";
			this.chinaName = "DS";
			this.englishName = "DS";
			this.description = "�������أ�����������\n������˾������ѩ��������";
			break;
		case 83:
			this.resId = R.drawable.i3;
			this.text = "��";
			this.chinaName = "��";
			this.englishName = "----";
			this.description = "�������أ��ձ�\n������˾���󷢹�ҵ��ʽ����";
			break;
		case 84:
			this.resId = R.drawable.i4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "DODGE";
			this.description = "�������أ�����\n������˾������˹�ռ���";
			break;
		case 85:
			this.resId = R.drawable.i5;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Beijing-Car";
			this.description = "�������أ��й�������ʡ������\n������˾������(����)������ҵ���޹�˾";
			break;
		case 86:
			this.resId = R.drawable.i6;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "FODAYAUTO";
			this.description = "�������أ��й����㶫\n������˾���㶫�����������޹�˾";
			break;
		case 87:
			this.resId = R.drawable.i7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "FOTON";
			this.description = "�������أ��й��������в�ƽ��\n������˾���������������ɷ����޹�˾";
			break;
		case 88:
			this.resId = R.drawable.i8;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "QOROS";
			this.description = "�������أ��й����Ϻ�\n������˾�������������޹�˾";
			break;
		case 89:
			this.resId = R.drawable.i9;
			this.text = "��������";
			this.chinaName = "��������";
			this.englishName = "----";
			this.description = "�������أ��й�������\n������˾����������";
			break;
		/** --------------------- */
		case 90:
			this.resId = R.drawable.j0;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "----";
			this.description = "�������أ��й���������ʡ������\n������˾������������������ҵ�������޹�˾";
			break;
		case 91:
			this.resId = R.drawable.j1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "HIGER";
			this.description = "�������أ��й�����������\n������˾����������������ҵ(����)���޹�˾";
			break;
		case 92:
			this.resId = R.drawable.j2;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "----";
			this.description = "�������أ��й�������ʡ������\n������˾��һ������";
			break;
		case 93:
			this.resId = R.drawable.j3;
			this.text = "��̩";
			this.chinaName = "��̩";
			this.englishName = "----";
			this.description = "�������أ��й�\n������˾����̩��������";
			break;
		case 94:
			this.resId = R.drawable.j4;
			this.text = "�ƺ�";
			this.chinaName = "�ƺ�";
			this.englishName = "----";
			this.description = "�������أ��й�����������������\n������˾����������������Źɷ����޹�˾";
			break;
		case 95:
			this.resId = R.drawable.j5;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Jeep";
			this.description = "�������أ�����\n������˾����ķ�տ���˹��";
			break;
		case 96:
			this.resId = R.drawable.j6;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "JMC";
			this.description = "�������أ��й�������ʡ�ϲ���\n������˾�����������ɷ����޹�˾";
			break;
		case 97:
			this.resId = R.drawable.j7;
			this.text = "��";
			this.chinaName = "��";
			this.englishName = "FOTON";
			this.description = "�������أ��й���������\n������˾��������������";
			break;
		case 98:
			this.resId = R.drawable.j8;
			this.text = "AC Schnitzer";
			this.chinaName = "AC Schnitzer";
			this.englishName = "AC Schnitzer";
			this.description = "�������أ��¹�\n������˾��Kohl Automobile Gmbh��Schnitzer";
			break;
		case 99:
			this.resId = R.drawable.j9;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "----";
			this.description = "�������أ��й����ӱ�\n������˾���ӱ����������������޹�˾";
			break;
		/** --------------------- */
		case 100:
			this.resId = R.drawable.k0;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "Wiesmann";
			this.description = "�������أ��¹�Dulman\n������˾��----";
			break;
		case 101:
			this.resId = R.drawable.k1;
			this.text = "˫��";
			this.chinaName = "˫��";
			this.englishName = "----";
			this.description = "�������أ��й����ӱ���ʯ��ׯ\n������˾��ʯ��ׯ˫�������ɷ����޹�˾";
			break;
		case 102:
			this.resId = R.drawable.k2;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "SPYKER";
			this.description = "�������أ�����\n������˾��----";
			break;
		case 103:
			this.resId = R.drawable.k3;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "----";
			this.description = "�������أ��ձ�\n������˾�����⼯��";
			break;
		case 104:
			this.resId = R.drawable.k4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "LIFAN";
			this.description = "�������أ��й�������\n������˾����������ʵҵ(����)��˾";
			break;
		case 105:
			this.resId = R.drawable.k5;
			this.text = "����ɭ";
			this.chinaName = "����ɭ";
			this.englishName = "carlsson";
			this.description = "�������أ��¹�\n������˾��----";
			break;
		case 106:
			this.resId = R.drawable.k6;
			this.text = "ک��";
			this.chinaName = "ک��";
			this.englishName = "Acura";
			this.description = "�������أ�����\n������˾���ձ�����������˾";
			break;
		case 107:
			this.resId = R.drawable.k7;
			this.text = "˼��";
			this.chinaName = "˼��";
			this.englishName = "CIIMO";
			this.description = "�������أ��й�\n������˾�����籾��";
			break;
		case 108:
			this.resId = R.drawable.k8;
			this.text = "�Ա�";
			this.chinaName = "�Ա�";
			this.englishName = "----";
			this.description = "�������أ��й�������\n������˾���������������ɷ����޹�˾";
			break;
		case 109:
			this.resId = R.drawable.k9;
			this.text = "˫��";
			this.chinaName = "˫��";
			this.englishName = "SSANGYONG";
			this.description = "�������أ�����\n������˾������˫������";
			break;
		/** --------------------- */
		case 110:
			this.resId = R.drawable.l0;
			this.text = "Gumpert";
			this.chinaName = "Gumpert";
			this.englishName = "Gumpert";
			this.description = "�������أ�----\n������˾��MTM����";
			break;
		case 111:
			this.resId = R.drawable.l1;
			this.text = "���";
			this.chinaName = "���";
			this.englishName = "MITSUOKA";
			this.description = "�������أ��ձ�\n������˾��----";
			break;
		case 112:
			this.resId = R.drawable.l2;
			this.text = "���ۺ�";
			this.chinaName = "���ۺ�";
			this.englishName = "KOMBAT";
			this.description = "�������أ�����˹\n������˾��Dartz����";
			break;
		case 113:
			this.resId = R.drawable.l3;
			this.text = "���ǽ�";
			this.chinaName = "���ǽ�";
			this.englishName = "Luxgen";
			this.description = "�������أ��й����㽭������\n������˾��̨��ԣ¡����";
			break;
		case 114:
			this.resId = R.drawable.l4;
			this.text = "�ֿ�";
			this.chinaName = "�ֿ�";
			this.englishName = "lincoln";
			this.description = "�������أ�����\n������˾��������������";
			break;
		case 115:
			this.resId = R.drawable.l5;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "McLaren";
			this.description = "�������أ�----\n������˾��÷����˹-����";
			break;
		case 116:
			this.resId = R.drawable.l6;
			this.text = "��Դ";
			this.chinaName = "��Դ";
			this.englishName = "jonway";
			this.description = "�������أ��й����㽭\n������˾���㽭��Դ����";
			break;
		case 117:
			this.resId = R.drawable.l7;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "DENZA";
			this.description = "�������أ��й�������\n������˾�����ǵϡ���ķ��";
			break;
		case 118:
			this.resId = R.drawable.l8;
			this.text = "KTM";
			this.chinaName = "KTM";
			this.englishName = "KTM";
			this.description = "�������أ��µ�����Mattighofen\n������˾��----";
			break;
		case 119:
			this.resId = R.drawable.l9;
			this.text = "����Smart";
			this.chinaName = "����Smart";
			this.englishName = "Smart";
			this.description = "�������أ�----\n������˾��÷����˹-���ۺ�Swatch��˾";
			break;
		/** --------------------- */
		case 120:
			this.resId = R.drawable.m0;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "Scion";
			this.description = "�������أ�����\n������˾����������";
			break;
		case 121:
			this.resId = R.drawable.m1;
			this.text = "��˹��";
			this.chinaName = "��˹��";
			this.englishName = "TESLA";
			this.description = "�������أ�����\n������˾��----";
			break;
		case 122:
			this.resId = R.drawable.m2;
			this.text = "һ��";
			this.chinaName = "һ��";
			this.englishName = "KOMBAT";
			this.description = "�������أ��й�\n������˾���й���һ�������Ź�˾";
			break;
		case 123:
			this.resId = R.drawable.m3;
			this.text = "ŷ��";
			this.chinaName = "ŷ��";
			this.englishName = "Oley";
			this.description = "�������أ��й������֡�����\n������˾��һ������";
			break;
		case 124:
			this.resId = R.drawable.m4;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "HAVAL";
			this.description = "�������أ��й����ӱ�ʡ��������\n������˾����������";
			break;
		case 125:
			this.resId = R.drawable.m5;
			this.text = "������";
			this.chinaName = "������";
			this.englishName = "Dacia";
			this.description = "�������أ�----\n������˾����ŵ����";
			break;
		case 126:
			this.resId = R.drawable.m6;
			this.text = "Fisker";
			this.chinaName = "Fisker����";
			this.englishName = "Fisker";
			this.description = "�������أ�����\n������˾��----";
			break;
		case 127:
			this.resId = R.drawable.m7;
			this.text = "֮ŵ";
			this.chinaName = "֮ŵ";
			this.englishName = "ZINORO";
			this.description = "�������أ��й���������\n������˾��������������";
			break;
		case 128:
			this.resId = R.drawable.m8;
			this.text = "Noble";
			this.chinaName = "Noble����";
			this.englishName = "Noble";
			this.description = "�������أ�Ӣ��\n������˾��----";
			break;
		case 129:
			this.resId = R.drawable.m9;
			this.text = "Ħ������";
			this.chinaName = "Ħ������";
			this.englishName = "morgan";
			this.description = "�������أ�Ӣ��\n������˾��Ħ��������˾";
			break;
		/** --------------------- */
		case 130:
			this.resId = R.drawable.n0;
			this.text = "��������";
			this.chinaName = "joylong��������";
			this.englishName = "joylong";
			this.description = "�������أ��й�������ʡ��������\n������˾�����վ��������������޹�˾";
			break;
		case 131:
			this.resId = R.drawable.n1;
			this.text = "����";
			this.chinaName = "����";
			this.englishName = "HORKI";
			this.description = "�������أ�----\n������˾�������ô�����";
			break;
		case 132:
			this.resId = R.drawable.n2;
			this.text = "��������";
			this.chinaName = "CHTC��������";
			this.englishName = "CHTC";
			this.description = "�������أ��й����ӱ�������\n������˾���й���һ�������Ź�˾";
			break;
		case 133:
			this.resId = R.drawable.n3;
			this.text = "Hennessey";
			this.chinaName = "Hennessey";
			this.englishName = "Hennessey";
			this.description = "�������أ�����\n������˾��----";
			break;
		case 134:
			this.resId = R.drawable.n4;
			this.text = "����������";
			this.chinaName = "����������";
			this.englishName = "NLM MOTOR";
			this.description = "�������أ��й�������ʡ\n������˾������ʡ������ҵ���ſعɹ�˾";
			break;
		case 135:
			this.resId = R.drawable.n5;
			this.text = "�ֿ�˹����";
			this.chinaName = "�ֿ�˹����";
			this.englishName = "VAUXHALL";
			this.description = "�������أ�Ӣ�����ֿ�˹����\n������˾��ͨ������";
			break;
		case 136:
			this.resId = R.drawable.n6;
			this.text = "˹����";
			this.chinaName = "˹����";
			this.englishName = "Spirra";
			this.description = "�������أ�����\n������˾��----";
			break;
		case 137:
			this.resId = R.drawable.n7;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "�������أ�----\n������˾��----";
			break;
		case 138:
			this.resId = R.drawable.n8;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "�������أ�----\n������˾��----";
			break;
		case 139:
			this.resId = R.drawable.n9;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "�������أ�----\n������˾��----";
			break;
		/** --------------------- */

		}
	}

	public CarInfo(int resId, String text, String englishName, String chinaName) {
		super();
		this.resId = resId;
		this.text = text;
		this.englishName = englishName;
		this.chinaName = chinaName;
	}

	/** 
	 *  ���������Ⱥ�:
	 *  0 = ��˹��˹    = a0
	 *  1 = ����            = a1
	 *  2 = ��ʱ��        = a2
	 *  3 = ������        = a3
	 *  4 = ��ɯ����    = a4
	 *  5 = ��������    = a5
	 *  6 = ����            = a6
	 *  7 = ����            = a7
	 *  8 = ���ͺ�        = a7
	 *  9 = ·˹��        = a9
	 *  10 = �µ�   = b0
	 *  11 = ����    = b1
	 *  12 = ·��   = b2
	 *  13 = ����    = b3
	 *  14 = ������������ŷ    = b4
	 *  15 = ����    = b5
	 *  16 = Ӣ�����    = b6
	 *  17 = ��ľ    = b7
	 *  18 = ѩ����    = b8
	 *  19 = ����    = b9
	 *  20 = ��������    = c0
	 *  21 = ����    = c1
	 *  22 = ����   = c2
	 *  23 = ����    = c3
	 *  24 = ����    = c4
	 *  25 = ����ȫ��ӥ    = c5
	 *  26 = �ղ�    = c6
	 *  27 = ����  = c7
	 *  28 = ŷ��    = c8
	 *  29 = �ݱ�    = c9
	 *  
	 *  30 = ����Ұ��    = d0
	 *  31 = ���    = d1
	 *  32 = �ֶ���  = d2
	 *  33 = ˹�´�    = d3
	 *  34 = ����    = d4
	 *  35 = �����ۺ�    = d5
	 *  36 = Ӣ��    = d6
	 *  37 = ����  = d7
	 *  38 = ˹��³    = d8
	 *  39 = ����    = d9
	 *  
	 *  40 = ����    = e0
	 *  41 = ����    = e1
	 *  42 = ����   = e2
	 *  43 = GMC  = e3
	 *  44 = ����Mini = e4
	 *  45 = ���Դ�    = e5
	 *  46 = ����   = e6
	 *  47 = ����  = e7
	 *  48 = ����    = e8
	 *  49 = ½��   = e9
	 *  
	 *  50 = ѩ����    = f0
	 *  51 = ����    = f1
	 *  52 = ����   = f2
	 *  53 = ����  = f3
	 *  54 = ���� = f4
	 *  55 = �л�    = f5
	 *  56 = ����  = f6
	 *  57 = ��˹�١���  = f7
	 *  58 = ����    = f8
	 *  59 = �׿���˹   = f9
	 *  
	 *  60 = �ִ�    = g0
	 *  61 = ����    = g1
	 *  62 = ������   = g2
	 *  63 = �������� = g3
	 *  64 = �������� = g4
	 *  65 = ������    = g5
	 *  66 = ������  = g6
	 *  67 = �ִ��Ͷ�˹  = g7
	 *  68 = ��̩   = g8
	 *  69 = ����˹��  = g9
	 *  
	 *  70 = ����    = h0
	 *  71 = ��ŵ    = h1
	 *  72 = ��������   = h2
	 *  73 = �Ͳ�˹ = h3
	 *  74 = ���� = h4
	 *  75 = ��������    = h5
	 *  76 = �������� = h6
	 *  77 = ����  = h7
	 *  78 = ���ǵ�   = h8
	 *  79 = ���ӵ�  = h9
	 *  
	 *  80 = ����    = i0
	 *  81 = ����Ұ��    = i1
	 *  82 = DS   = i2
	 *  83 = �� = i3
	 *  84 = ���� = i4
	 *  85 = ����    = i5
	 *  86 = ���� = i6
	 *  87 = ����  = i7
	 *  88 = ����   = i8
	 *  89 = ��������  = i9
	 * 
	 *  90 = ����    = j0
	 *  91 = ����    = j1
	 *  92 = ����   = j2
	 *  93 = ��̩ = j3
	 *  94 = �ƺ� = j4
	 *  95 = ����    = j5
	 *  96 = ���� = j6
	 *  97 = ��  = j7
	 *  98 = AC Schnitzer = j8
	 *  99 = ����  = j9
	 *  
	 *  100 = ������    = k0
	 *  101 = ˫��    = k1
	 *  102 = ����   = k2
	 *  103 = ���� = k3
	 *  104 = ���� = k4
	 *  105 = ����ɭ    = k5
	 *  106 = ک�� = k6
	 *  107 = ˼��  = k7
	 *  108 = �Ա�   = k8
	 *  109 = ˫��  = k9
	 *  
	 *  110 = Gumpert = l0
	 *  111 = ���    = l1
	 *  112 = ���ۺ�   = l2
	 *  113 = ���ǽ� = l3
	 *  114 = �ֿ� = l4
	 *  115 = ������    = l5
	 *  116 = ��Դ = l6
	 *  117 = ����  = l7
	 *  118 = KTM   = l8
	 *  119 = ����Smart = l9
	 *  
	 *  120 = ����    = m0
	 *  121 = ��˹��    = m1
	 *  122 = һ��   = m2
	 *  123 = ŷ�� = m3
	 *  124 = ���� = m4
	 *  125 = ������    = m5
	 *  126 = Fisker = m6
	 *  127 = ֮ŵ = m7
	 *  128 = Noble  = m8
	 *  129 = Ħ������  = m9
	 *  
	 *  130 = ��������    = n0
	 *  131 = ����    = n1
	 *  132 = ��������   = n2
	 *  133 = Hennessey = n3
	 *  134 = ���������� = n4
	 *  135 = �ֿ�˹����    = n5
	 *  136 = ˹���� = n6
	 *  137 = ----  = n7
	 *  138 = ----   = n8
	 *  139 = ----  = n9
	 *  */
	public CarInfo(int resId, String text, String englishName, String chinaName,
			String description) {
		super();
		this.resId = resId;
		this.text = text;
		this.englishName = englishName;
		this.chinaName = chinaName;
		this.description = description;
	}

}
