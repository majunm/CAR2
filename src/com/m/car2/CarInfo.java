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

	/** 
	 *  排名不分先后:
	 *  0 = 劳斯莱斯    = a0
	 *  1 = 宾利            = a1
	 *  2 = 保时捷        = a2
	 *  3 = 法拉利        = a3
	 *  4 = 玛莎拉蒂    = a4
	 *  5 = 兰博基尼    = a5
	 *  6 = 奔驰            = a6
	 *  7 = 宝马            = a7
	 *  8 = 迈巴赫        = a7
	 *  9 = 路斯特        = a9
	 *  10 = 奥迪   = b0
	 *  11 = 悍马    = b1
	 *  12 = 路虎   = b2
	 *  13 = 莲花    = b3
	 *  14 = 阿尔法·罗密欧    = b4
	 *  15 = 吉利    = b5
	 *  16 = 英菲尼迪    = b6
	 *  17 = 铃木    = b7
	 *  18 = 雪铁龙    = b8
	 *  19 = 长安    = b9
	 *  20 = 长安商用    = c0
	 *  21 = 名爵    = c1
	 *  22 = 开瑞   = c2
	 *  23 = 海马    = c3
	 *  24 = 东风    = c4
	 *  25 = 吉利全球鹰    = c5
	 *  26 = 日产    = c6
	 *  27 = 丰田  = c7
	 *  28 = 欧宝    = c8
	 *  29 = 捷豹    = c9
	 *  
	 *  30 = 福特野马    = d0
	 *  31 = 别克    = d1
	 *  32 = 沃尔沃  = d2
	 *  33 = 斯柯达    = d3
	 *  34 = 荣威    = d4
	 *  35 = 吉利帝豪    = d5
	 *  36 = 英伦    = d6
	 *  37 = 瑞麒  = d7
	 *  38 = 斯巴鲁    = d8
	 *  39 = 威麟    = d9
	 *  
	 *  40 = 标致    = e0
	 *  41 = 启辰    = e1
	 *  42 = 本田   = e2
	 *  43 = GMC  = e3
	 *  44 = 宝马Mini = e4
	 *  45 = 马自达    = e5
	 *  46 = 长城   = e6
	 *  47 = 理念  = e7
	 *  48 = 大众    = e8
	 *  49 = 陆风   = e9
	 *  
	 *  50 = 雪佛兰    = f0
	 *  51 = 福特    = f1
	 *  52 = 起亚   = f2
	 *  53 = 萨博  = f3
	 *  54 = 华普 = f4
	 *  55 = 中华    = f5
	 *  56 = 奇瑞  = f6
	 *  57 = 阿斯顿·马丁  = f7
	 *  58 = 夏利    = f8
	 *  59 = 雷克萨斯   = f9
	 *  
	 *  60 = 现代    = g0
	 *  61 = 吉奥    = g1
	 *  62 = 菲亚特   = g2
	 *  63 = 凯迪拉克 = g3
	 *  64 = 北汽威旺 = g4
	 *  65 = 帕加尼    = g5
	 *  66 = 西雅特  = g6
	 *  67 = 现代劳恩斯  = g7
	 *  68 = 众泰   = g8
	 *  69 = 克莱斯勒  = g9
	 *  
	 *  70 = 江淮    = h0
	 *  71 = 雷诺    = h1
	 *  72 = 科尼赛克   = h2
	 *  73 = 巴博斯 = h3
	 *  74 = 宝骏 = h4
	 *  75 = 北京汽车    = h5
	 *  76 = 北汽制造 = h6
	 *  77 = 奔腾  = h7
	 *  78 = 比亚迪   = h8
	 *  79 = 布加迪  = h9
	 *  
	 *  80 = 昌河    = i0
	 *  81 = 川汽野马    = i1
	 *  82 = DS   = i2
	 *  83 = 大发 = i3
	 *  84 = 道奇 = i4
	 *  85 = 东南    = i5
	 *  86 = 福迪 = i6
	 *  87 = 福田  = i7
	 *  88 = 观致   = i8
	 *  89 = 广汽传祺  = i9
	 * 
	 *  90 = 哈飞    = j0
	 *  91 = 海格    = j1
	 *  92 = 红旗   = j2
	 *  93 = 华泰 = j3
	 *  94 = 黄海 = j4
	 *  95 = 吉普    = j5
	 *  96 = 江铃 = j6
	 *  97 = 金杯  = j7
	 *  98 = AC Schnitzer = j8
	 *  99 = 中兴  = j9
	 *  
	 *  100 = 威兹曼    = k0
	 *  101 = 双环    = k1
	 *  102 = 世爵   = k2
	 *  103 = 三菱 = k3
	 *  104 = 力帆 = k4
	 *  105 = 卡尔森    = k5
	 *  106 = 讴歌 = k6
	 *  107 = 思铭  = k7
	 *  108 = 猎豹   = k8
	 *  109 = 双龙  = k9
	 *  
	 *  110 = Gumpert = l0
	 *  111 = 光冈    = l1
	 *  112 = 凯佰赫   = l2
	 *  113 = 纳智捷 = l3
	 *  114 = 林肯 = l4
	 *  115 = 迈凯轮    = l5
	 *  116 = 永源 = l6
	 *  117 = 腾势  = l7
	 *  118 = KTM   = l8
	 *  119 = 奔驰Smart = l9
	 *  
	 *  120 = 赛恩    = m0
	 *  121 = 特斯拉    = m1
	 *  122 = 一汽   = m2
	 *  123 = 欧朗 = m3
	 *  124 = 哈弗 = m4
	 *  125 = 达西亚    = m5
	 *  126 = Fisker = m6
	 *  127 = 之诺 = m7
	 *  128 = Noble  = m8
	 *  129 = 摩根汽车  = m9
	 *  
	 *  130 = 九龙汽车    = n0
	 *  131 = 华骐    = n1
	 *  132 = 恒天汽车   = n2
	 *  133 = Hennessey = n3
	 *  134 = 福汽新龙马 = n4
	 *  135 = 沃克斯豪尔    = n5
	 *  136 = 斯派朗 = n6
	 *  137 = ----  = n7
	 *  138 = ----   = n8
	 *  139 = ----  = n9
	 *  */
	private void init(int type) {
		switch (type % 140) {
		case 0:
			this.resId = R.drawable.a0;
			this.text = "劳斯莱斯";
			this.chinaName = "劳斯莱斯";
			this.englishName = "Rolls-Royce";
			this.description = "劳斯莱斯以一个“贵族化”的汽车公司享誉全球，同时也是目前世界三大航空发动机生产商之一。";
			break;
		case 1:
			this.resId = R.drawable.a1;
			this.text = "宾利";
			this.chinaName = "宾利";
			this.englishName = "Bentley";
			this.description = "";
			break;
		case 2:
			this.resId = R.drawable.a2;
			this.text = "保时捷";
			this.chinaName = "保时捷";
			this.englishName = "Porsche";
			this.description = "";
			break;
		case 3:
			this.resId = R.drawable.a3;
			this.text = "法拉利";
			this.chinaName = "法拉利";
			this.englishName = "Ferrari";
			this.description = "";
			break;
		case 4:
			this.resId = R.drawable.a4;
			this.text = "玛莎拉蒂";
			this.chinaName = "玛莎拉蒂";
			this.englishName = "Maserati";
			this.description = "";
			break;
		case 5:
			this.resId = R.drawable.a5;
			this.text = "兰博基尼";
			this.chinaName = "兰博基尼";
			this.englishName = "Lamborghini";
			this.description = "";
			break;
		case 6:
			this.resId = R.drawable.a6;
			this.text = "奔驰";
			this.chinaName = "奔驰";
			this.englishName = "Benz";
			this.description = "";
			break;
		case 7:
			this.resId = R.drawable.a7;
			this.text = "宝马";
			this.chinaName = "宝马";
			this.englishName = "BMW";
			this.description = "";
			break;
		case 8:
			this.resId = R.drawable.a8;
			this.text = "迈巴赫";
			this.chinaName = "迈巴赫";
			this.englishName = "Maybach";
			this.description = "";
			break;
		case 9:
			this.resId = R.drawable.a9;
			this.text = "路斯特";
			this.chinaName = "路斯特";
			this.englishName = "Lotusnyo";
			this.description = "";
			break;
		case 10:
			this.resId = R.drawable.b0;
			this.text = "奥迪";
			this.chinaName = "奥迪";
			this.englishName = "Audi";
			this.description = "";
			break;
		case 11:
			this.resId = R.drawable.b1;
			this.text = "悍马";
			this.chinaName = "悍马";
			this.englishName = "HUMMER";
			this.description = "";
			break;
		/** --------------------- */
		case 12:
			this.resId = R.drawable.b2;
			this.text = "路虎";
			this.chinaName = "路虎";
			this.englishName = "Land Rover";
			this.description = "";
			break;
		case 13:
			this.resId = R.drawable.b3;
			this.text = "莲花";
			this.chinaName = "莲花";
			this.englishName = "LianHua";
			this.description = "";
			break;
		case 14:
			this.resId = R.drawable.b4;
			this.text = "阿尔法·罗密欧";
			this.chinaName = "阿尔法·罗密欧";
			this.englishName = "Alfa Romeo";
			this.description = "";
			break;
		case 15:
			this.resId = R.drawable.b5;
			this.text = "吉利";
			this.chinaName = "吉利";
			this.englishName = "GEELY";
			this.description = "";
			break;
		case 16:
			this.resId = R.drawable.b6;
			this.text = "英菲尼迪";
			this.chinaName = "英菲尼迪";
			this.englishName = "INFINITI";
			this.description = "";
			break;
		case 17:
			this.resId = R.drawable.b7;
			this.text = "铃木";
			this.chinaName = "铃木";
			this.englishName = "SUZUKI";
			this.description = "";
			break;
		case 18:
			this.resId = R.drawable.b8;
			this.text = "雪铁龙";
			this.chinaName = "雪铁龙";
			this.englishName = "CITROEN";
			this.description = "";
			break;
		case 19:
			this.resId = R.drawable.b9;
			this.text = "长安";
			this.chinaName = "长安";
			this.englishName = "CHANGAN";
			this.description = "";
			break;
		/** --------------------- */
		case 20:
			this.resId = R.drawable.c0;
			this.text = "长安商用";
			this.chinaName = "长安商用";
			this.englishName = "Chang'an_for_Business";
			this.description = "";
			break;
		case 21:
			this.resId = R.drawable.c1;
			this.text = "名爵";
			this.chinaName = "名爵";
			this.englishName = "MG";
			this.description = "";
			break;
		case 22:
			this.resId = R.drawable.c2;
			this.text = "开瑞";
			this.chinaName = "开瑞";
			this.englishName = "Karry";
			this.description = "";
			break;
		case 23:
			this.resId = R.drawable.c3;
			this.text = "海马";
			this.chinaName = "海马";
			this.englishName = "Haima";
			this.description = "";
			break;
		case 24:
			this.resId = R.drawable.c4;
			this.text = "东风";
			this.chinaName = "东风";
			this.englishName = "DongFeng";
			this.description = "";
			break;
		case 25:
			this.resId = R.drawable.c5;
			this.text = "吉利全球鹰";
			this.chinaName = "吉利全球鹰";
			this.englishName = "GLEAGLE";
			this.description = "";
			break;
		case 26:
			this.resId = R.drawable.c6;
			this.text = "日产";
			this.chinaName = "日产";
			this.englishName = "NISSAN";
			this.description = "";
			break;
		case 27:
			this.resId = R.drawable.c7;
			this.text = "丰田";
			this.chinaName = "丰田";
			this.englishName = "TOYOTA";
			this.description = "";
			break;
		case 28:
			this.resId = R.drawable.c8;
			this.text = "欧宝";
			this.chinaName = "欧宝";
			this.englishName = "OPEL";
			this.description = "";
			break;
		case 29:
			this.resId = R.drawable.c9;
			this.text = "捷豹";
			this.chinaName = "捷豹";
			this.englishName = "Jaguar";
			this.description = "";
			break;
		/** --------------------- */
		case 30:
			this.resId = R.drawable.d0;
			this.text = "福特野马";
			this.chinaName = "福特野马";
			this.englishName = "MUSTANG";
			this.description = "";
			break;
		case 31:
			this.resId = R.drawable.d1;
			this.text = "别克";
			this.chinaName = "别克";
			this.englishName = "Buick";
			this.description = "";
			break;
		case 32:
			this.resId = R.drawable.d2;
			this.text = "沃尔沃";
			this.chinaName = "沃尔沃";
			this.englishName = "Volvo";
			this.description = "";
			break;
		case 33:
			this.resId = R.drawable.d3;
			this.text = "斯柯达";
			this.chinaName = "斯柯达";
			this.englishName = "SKODA";
			this.description = "";
			break;
		case 34:
			this.resId = R.drawable.d4;
			this.text = "荣威";
			this.chinaName = "荣威";
			this.englishName = "Roewe";
			this.description = "";
			break;
		case 35:
			this.resId = R.drawable.d5;
			this.text = "吉利帝豪";
			this.chinaName = "吉利帝豪";
			this.englishName = "EMGRAND";
			this.description = "";
			break;
		case 36:
			this.resId = R.drawable.d6;
			this.text = "英伦";
			this.chinaName = "英伦";
			this.englishName = "Engloncar";
			this.description = "";
			break;
		case 37:
			this.resId = R.drawable.d7;
			this.text = "瑞麒";
			this.chinaName = "瑞麒";
			this.englishName = "RIICH";
			this.description = "";
			break;
		case 38:
			this.resId = R.drawable.d8;
			this.text = "斯巴鲁";
			this.chinaName = "斯巴鲁";
			this.englishName = "SUBARU";
			this.description = "";
			break;
		case 39:
			this.resId = R.drawable.d9;
			this.text = "威麟";
			this.chinaName = "威麟";
			this.englishName = "RELY";
			this.description = "";
			break;
		/** --------------------- */
		case 40:
			this.resId = R.drawable.e0;
			this.text = "标致";
			this.chinaName = "标致";
			this.englishName = "PEUGEOT";
			this.description = "";
			break;
		case 41:
			this.resId = R.drawable.e1;
			this.text = "启辰";
			this.chinaName = "启辰";
			this.englishName = "Venucia";
			this.description = "";
			break;
		case 42:
			this.resId = R.drawable.e2;
			this.text = "本田";
			this.chinaName = "本田";
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
			this.text = "宝马Mini";
			this.chinaName = "宝马Mini";
			this.englishName = "BMW_MINI";
			this.description = "";
			break;
		case 45:
			this.resId = R.drawable.e5;
			this.text = "马自达";
			this.chinaName = "马自达";
			this.englishName = "Mazda";
			this.description = "";
			break;
		case 46:
			this.resId = R.drawable.e6;
			this.text = "长城";
			this.chinaName = "长城";
			this.englishName = "Great Wall";
			this.description = "";
			break;
		case 47:
			this.resId = R.drawable.e7;
			this.text = "理念";
			this.chinaName = "理念";
			this.englishName = "linian";
			this.description = "";
			break;
		case 48:
			this.resId = R.drawable.e8;
			this.text = "大众";
			this.chinaName = "大众";
			this.englishName = "Volkswagen";
			this.description = "";
			break;
		case 49:
			this.resId = R.drawable.e9;
			this.text = "陆风";
			this.chinaName = "陆风";
			this.englishName = "landwind";
			this.description = "";
			break;
		/** --------------------- */
		case 50:
			this.resId = R.drawable.f0;
			this.text = "雪佛兰";
			this.chinaName = "雪佛兰";
			this.englishName = "Chevrolet";
			this.description = "";
			break;
		case 51:
			this.resId = R.drawable.f1;
			this.text = "福特";
			this.chinaName = "福特";
			this.englishName = "Ford";
			this.description = "";
			break;
		case 52:
			this.resId = R.drawable.f2;
			this.text = "起亚";
			this.chinaName = "起亚";
			this.englishName = "KIA";
			this.description = "";
			break;
		case 53:
			this.resId = R.drawable.f3;
			this.text = "萨博";
			this.chinaName = "萨博";
			this.englishName = "SAAB";
			this.description = "";
			break;
		case 54:
			this.resId = R.drawable.f4;
			this.text = "华普";
			this.chinaName = "华普";
			this.englishName = "HuaPu";
			this.description = "";
			break;
		case 55:
			this.resId = R.drawable.f5;
			this.text = "中华";
			this.chinaName = "中华";
			this.englishName = "ZhongHua";
			this.description = "";
			break;
		case 56:
			this.resId = R.drawable.f6;
			this.text = "奇瑞";
			this.chinaName = "奇瑞";
			this.englishName = "chery";
			this.description = "";
			break;
		case 57:
			this.resId = R.drawable.f7;
			this.text = "阿斯顿·马丁";
			this.chinaName = "阿斯顿·马丁";
			this.englishName = "AstonMartin";
			this.description = "";
			break;
		case 58:
			this.resId = R.drawable.f8;
			this.text = "夏利";
			this.chinaName = "夏利";
			this.englishName = "Xiali";
			this.description = "";
			break;
		case 59:
			this.resId = R.drawable.f9;
			this.text = "雷克萨斯";
			this.chinaName = "雷克萨斯";
			this.englishName = "Lexus";
			this.description = "";
			break;
		/** --------------------- */
		case 60:
			this.resId = R.drawable.g0;
			this.text = "现代";
			this.chinaName = "现代";
			this.englishName = "HYUNDAI";
			this.description = "";
			break;
		case 61:
			this.resId = R.drawable.g1;
			this.text = "吉奥";
			this.chinaName = "吉奥";
			this.englishName = "Ji-Ao";
			this.description = "";
			break;
		case 62:
			this.resId = R.drawable.g2;
			this.text = "菲亚特";
			this.chinaName = "菲亚特";
			this.englishName = "FIAT";
			this.description = "";
			break;
		case 63:
			this.resId = R.drawable.g3;
			this.text = "凯迪拉克";
			this.chinaName = "凯迪拉克";
			this.englishName = "Cadillac";
			this.description = "美国通用公司";
			break;
		case 64:
			this.resId = R.drawable.g4;
			this.text = "北汽威旺";
			this.chinaName = "北汽威旺";
			this.englishName = "Wei-Wang";
			this.description = "";
			break;
		case 65:
			this.resId = R.drawable.g5;
			this.text = "帕加尼";
			this.chinaName = "帕加尼";
			this.englishName = "PAGANI";
			this.description = "产地:意大利·摩德纳";
			break;
		case 66:
			this.resId = R.drawable.g6;
			this.text = "西雅特";
			this.chinaName = "西雅特";
			this.englishName = "Seat";
			this.description = "德国大众汽车公司";
			break;
		case 67:
			this.resId = R.drawable.g7;
			this.text = "现代劳恩斯";
			this.chinaName = "现代劳恩斯";
			this.englishName = "POHENS";
			this.description = "韩车现代汽车车公司";
			break;
		case 68:
			this.resId = R.drawable.g8;
			this.text = "众泰";
			this.chinaName = "众泰";
			this.englishName = "Z-Tai";
			this.description = "中国内地-隶属公司：众泰控股集团";
			break;
		case 69:
			this.resId = R.drawable.g9;
			this.text = "克莱斯勒";
			this.chinaName = "克莱斯勒";
			this.englishName = "Chrysler";
			this.description = "产　　地：美国·密歇根州海兰德帕克";
			break;
		/** --------------------- */
		case 70:
			this.resId = R.drawable.h0;
			this.text = "江淮";
			this.chinaName = "江淮";
			this.englishName = "JAC";
			this.description = "产　　地：中国·安徽合肥\n隶属公司：安徽江淮汽车股份有限公司";
			break;
		case 71:
			this.resId = R.drawable.h1;
			this.text = "雷诺";
			this.chinaName = "雷诺";
			this.englishName = "RENAULT";
			this.description = "产　　地：法国·布洛涅-比扬古\n隶属公司：雷诺汽车公司";
			break;
		case 72:
			this.resId = R.drawable.h2;
			this.text = "科尼赛克";
			this.chinaName = "科尼赛克";
			this.englishName = "Koenigsegg";
			this.description = "产　　地：瑞典";
			break;
		case 73:
			this.resId = R.drawable.h3;
			this.text = "巴博斯";
			this.chinaName = "巴博斯";
			this.englishName = "BRABUS";
			this.description = "产　　地：德国\n隶属公司：巴博斯 S级,巴博斯 SLS级";
			break;
		case 74:
			this.resId = R.drawable.h4;
			this.text = "宝骏";
			this.chinaName = "宝骏";
			this.englishName = "Bao-Jun";
			this.description = "隶属公司：上海通用";
			break;
		case 75:
			this.resId = R.drawable.h5;
			this.text = "北京汽车";
			this.chinaName = "北京汽车";
			this.englishName = "Beijing-Car";
			this.description = "产　　地：中国·北京\n隶属公司：北汽集团";
			break;
		case 76:
			this.resId = R.drawable.h6;
			this.text = "北汽制造";
			this.chinaName = "北汽制造";
			this.englishName = "Beijing-Builder";
			this.description = "产　　地：中国·北京\n隶属公司：北京汽车制造厂";
			break;
		case 77:
			this.resId = R.drawable.h7;
			this.text = "奔腾";
			this.chinaName = "奔腾";
			this.englishName = "BESTURN";
			this.description = "隶属公司：一汽集团";
			break;
		case 78:
			this.resId = R.drawable.h8;
			this.text = "比亚迪";
			this.chinaName = "比亚迪";
			this.englishName = "BYD";
			this.description = "产　　地：深圳市\n隶属公司：比亚迪股份有限公司";
			break;
		case 79:
			this.resId = R.drawable.h9;
			this.text = "布加迪";
			this.chinaName = "布加迪";
			this.englishName = "BUGATTI";
			this.description = "产　　地：法国·阿尔萨斯\n隶属公司：大众集团";
			break;
		/** --------------------- */
		case 80:
			this.resId = R.drawable.i0;
			this.text = "昌河";
			this.chinaName = "昌河";
			this.englishName = "ChangHe";
			this.description = "产　　地：中国·江西省景德镇市\n隶属公司：中国长安汽车集团股份有限公司";
			break;
		case 81:
			this.resId = R.drawable.i1;
			this.text = "川汽野马";
			this.chinaName = "川汽野马";
			this.englishName = "----";
			this.description = "产　　地：中国·四川\n隶属公司：川汽集团";
			break;
		case 82:
			this.resId = R.drawable.i2;
			this.text = "DS";
			this.chinaName = "DS";
			this.englishName = "DS";
			this.description = "产　　地：法国·巴黎\n隶属公司：标致雪铁龙集团";
			break;
		case 83:
			this.resId = R.drawable.i3;
			this.text = "大发";
			this.chinaName = "大发";
			this.englishName = "----";
			this.description = "产　　地：日本\n隶属公司：大发工业株式会社";
			break;
		case 84:
			this.resId = R.drawable.i4;
			this.text = "道奇";
			this.chinaName = "道奇";
			this.englishName = "DODGE";
			this.description = "产　　地：美国\n隶属公司：克莱斯勒集团";
			break;
		case 85:
			this.resId = R.drawable.i5;
			this.text = "东南";
			this.chinaName = "东南";
			this.englishName = "Beijing-Car";
			this.description = "产　　地：中国·福建省福州市\n隶属公司：东南(福建)汽车工业有限公司";
			break;
		case 86:
			this.resId = R.drawable.i6;
			this.text = "福迪";
			this.chinaName = "福迪";
			this.englishName = "FODAYAUTO";
			this.description = "产　　地：中国·广东\n隶属公司：广东福迪汽车有限公司";
			break;
		case 87:
			this.resId = R.drawable.i7;
			this.text = "福田";
			this.chinaName = "福田";
			this.englishName = "FOTON";
			this.description = "产　　地：中国·北京市昌平区\n隶属公司：北汽福田汽车股份有限公司";
			break;
		case 88:
			this.resId = R.drawable.i8;
			this.text = "观致";
			this.chinaName = "观致";
			this.englishName = "QOROS";
			this.description = "产　　地：中国·上海\n隶属公司：奇瑞汽车有限公司";
			break;
		case 89:
			this.resId = R.drawable.i9;
			this.text = "广汽传祺";
			this.chinaName = "广汽传祺";
			this.englishName = "----";
			this.description = "产　　地：中国·广州\n隶属公司：广汽集团";
			break;
		/** --------------------- */
		case 90:
			this.resId = R.drawable.j0;
			this.text = "哈飞";
			this.chinaName = "哈飞";
			this.englishName = "----";
			this.description = "产　　地：中国·黑龙江省哈尔滨\n隶属公司：哈尔滨哈飞汽车工业集团有限公司";
			break;
		case 91:
			this.resId = R.drawable.j1;
			this.text = "海格";
			this.chinaName = "海格";
			this.englishName = "HIGER";
			this.description = "产　　地：中国·江苏苏州\n隶属公司：金龙联合汽车工业(苏州)有限公司";
			break;
		case 92:
			this.resId = R.drawable.j2;
			this.text = "红旗";
			this.chinaName = "红旗";
			this.englishName = "----";
			this.description = "产　　地：中国·吉林省长春市\n隶属公司：一汽集团";
			break;
		case 93:
			this.resId = R.drawable.j3;
			this.text = "华泰";
			this.chinaName = "华泰";
			this.englishName = "----";
			this.description = "产　　地：中国\n隶属公司：华泰汽车集团";
			break;
		case 94:
			this.resId = R.drawable.j4;
			this.text = "黄海";
			this.chinaName = "黄海";
			this.englishName = "----";
			this.description = "产　　地：中国·辽宁沈阳丹东市\n隶属公司：辽宁曙光汽车集团股份有限公司";
			break;
		case 95:
			this.resId = R.drawable.j5;
			this.text = "吉普";
			this.chinaName = "吉普";
			this.englishName = "Jeep";
			this.description = "产　　地：美国\n隶属公司：戴姆勒克莱斯勒";
			break;
		case 96:
			this.resId = R.drawable.j6;
			this.text = "江铃";
			this.chinaName = "江铃";
			this.englishName = "JMC";
			this.description = "产　　地：中国·江西省南昌市\n隶属公司：江铃汽车股份有限公司";
			break;
		case 97:
			this.resId = R.drawable.j7;
			this.text = "金杯";
			this.chinaName = "金杯";
			this.englishName = "FOTON";
			this.description = "产　　地：中国·沈阳市\n隶属公司：华晨汽车集团";
			break;
		case 98:
			this.resId = R.drawable.j8;
			this.text = "AC Schnitzer";
			this.chinaName = "AC Schnitzer";
			this.englishName = "AC Schnitzer";
			this.description = "产　　地：德国\n隶属公司：Kohl Automobile Gmbh和Schnitzer";
			break;
		case 99:
			this.resId = R.drawable.j9;
			this.text = "中兴";
			this.chinaName = "中兴";
			this.englishName = "----";
			this.description = "产　　地：中国·河北\n隶属公司：河北中兴汽车制造有限公司";
			break;
		/** --------------------- */
		case 100:
			this.resId = R.drawable.k0;
			this.text = "威兹曼";
			this.chinaName = "威兹曼";
			this.englishName = "Wiesmann";
			this.description = "产　　地：德国Dulman\n隶属公司：----";
			break;
		case 101:
			this.resId = R.drawable.k1;
			this.text = "双环";
			this.chinaName = "双环";
			this.englishName = "----";
			this.description = "产　　地：中国·河北·石家庄\n隶属公司：石家庄双环汽车股份有限公司";
			break;
		case 102:
			this.resId = R.drawable.k2;
			this.text = "世爵";
			this.chinaName = "世爵";
			this.englishName = "SPYKER";
			this.description = "产　　地：荷兰\n隶属公司：----";
			break;
		case 103:
			this.resId = R.drawable.k3;
			this.text = "三菱";
			this.chinaName = "三菱";
			this.englishName = "----";
			this.description = "产　　地：日本\n隶属公司：三菱集团";
			break;
		case 104:
			this.resId = R.drawable.k4;
			this.text = "力帆";
			this.chinaName = "力帆";
			this.englishName = "LIFAN";
			this.description = "产　　地：中国·重庆\n隶属公司：重庆力帆实业(集团)公司";
			break;
		case 105:
			this.resId = R.drawable.k5;
			this.text = "卡尔森";
			this.chinaName = "卡尔森";
			this.englishName = "carlsson";
			this.description = "产　　地：德国\n隶属公司：----";
			break;
		case 106:
			this.resId = R.drawable.k6;
			this.text = "讴歌";
			this.chinaName = "讴歌";
			this.englishName = "Acura";
			this.description = "产　　地：美国\n隶属公司：日本本田汽车公司";
			break;
		case 107:
			this.resId = R.drawable.k7;
			this.text = "思铭";
			this.chinaName = "思铭";
			this.englishName = "CIIMO";
			this.description = "产　　地：中国\n隶属公司：东风本田";
			break;
		case 108:
			this.resId = R.drawable.k8;
			this.text = "猎豹";
			this.chinaName = "猎豹";
			this.englishName = "----";
			this.description = "产　　地：中国·湖南\n隶属公司：广汽长丰汽车股份有限公司";
			break;
		case 109:
			this.resId = R.drawable.k9;
			this.text = "双龙";
			this.chinaName = "双龙";
			this.englishName = "SSANGYONG";
			this.description = "产　　地：韩国\n隶属公司：韩国双龙集团";
			break;
		/** --------------------- */
		case 110:
			this.resId = R.drawable.l0;
			this.text = "Gumpert";
			this.chinaName = "Gumpert";
			this.englishName = "Gumpert";
			this.description = "产　　地：----\n隶属公司：MTM集团";
			break;
		case 111:
			this.resId = R.drawable.l1;
			this.text = "光冈";
			this.chinaName = "光冈";
			this.englishName = "MITSUOKA";
			this.description = "产　　地：日本\n隶属公司：----";
			break;
		case 112:
			this.resId = R.drawable.l2;
			this.text = "凯佰赫";
			this.chinaName = "凯佰赫";
			this.englishName = "KOMBAT";
			this.description = "产　　地：俄罗斯\n隶属公司：Dartz集团";
			break;
		case 113:
			this.resId = R.drawable.l3;
			this.text = "纳智捷";
			this.chinaName = "纳智捷";
			this.englishName = "Luxgen";
			this.description = "产　　地：中国·浙江·杭州\n隶属公司：台湾裕隆集团";
			break;
		case 114:
			this.resId = R.drawable.l4;
			this.text = "林肯";
			this.chinaName = "林肯";
			this.englishName = "lincoln";
			this.description = "产　　地：美国\n隶属公司：美国福特汽车";
			break;
		case 115:
			this.resId = R.drawable.l5;
			this.text = "迈凯轮";
			this.chinaName = "迈凯轮";
			this.englishName = "McLaren";
			this.description = "产　　地：----\n隶属公司：梅赛德斯-奔驰";
			break;
		case 116:
			this.resId = R.drawable.l6;
			this.text = "永源";
			this.chinaName = "永源";
			this.englishName = "jonway";
			this.description = "产　　地：中国·浙江\n隶属公司：浙江永源集团";
			break;
		case 117:
			this.resId = R.drawable.l7;
			this.text = "腾势";
			this.chinaName = "腾势";
			this.englishName = "DENZA";
			this.description = "产　　地：中国·深圳\n隶属公司：比亚迪·戴姆勒";
			break;
		case 118:
			this.resId = R.drawable.l8;
			this.text = "KTM";
			this.chinaName = "KTM";
			this.englishName = "KTM";
			this.description = "产　　地：奥地利·Mattighofen\n隶属公司：----";
			break;
		case 119:
			this.resId = R.drawable.l9;
			this.text = "奔驰Smart";
			this.chinaName = "奔驰Smart";
			this.englishName = "Smart";
			this.description = "产　　地：----\n隶属公司：梅赛德斯-奔驰和Swatch公司";
			break;
		/** --------------------- */
		case 120:
			this.resId = R.drawable.m0;
			this.text = "赛恩";
			this.chinaName = "赛恩";
			this.englishName = "Scion";
			this.description = "产　　地：美国\n隶属公司：丰田汽车";
			break;
		case 121:
			this.resId = R.drawable.m1;
			this.text = "特斯拉";
			this.chinaName = "特斯拉";
			this.englishName = "TESLA";
			this.description = "产　　地：美国\n隶属公司：----";
			break;
		case 122:
			this.resId = R.drawable.m2;
			this.text = "一汽";
			this.chinaName = "一汽";
			this.englishName = "KOMBAT";
			this.description = "产　　地：中国\n隶属公司：中国第一汽车集团公司";
			break;
		case 123:
			this.resId = R.drawable.m3;
			this.text = "欧朗";
			this.chinaName = "欧朗";
			this.englishName = "Oley";
			this.description = "产　　地：中国·吉林·长春\n隶属公司：一汽集团";
			break;
		case 124:
			this.resId = R.drawable.m4;
			this.text = "哈弗";
			this.chinaName = "哈弗";
			this.englishName = "HAVAL";
			this.description = "产　　地：中国·河北省·保定市\n隶属公司：长城汽车";
			break;
		case 125:
			this.resId = R.drawable.m5;
			this.text = "达西亚";
			this.chinaName = "达西亚";
			this.englishName = "Dacia";
			this.description = "产　　地：----\n隶属公司：雷诺汽车";
			break;
		case 126:
			this.resId = R.drawable.m6;
			this.text = "Fisker";
			this.chinaName = "Fisker汽车";
			this.englishName = "Fisker";
			this.description = "产　　地：美国\n隶属公司：----";
			break;
		case 127:
			this.resId = R.drawable.m7;
			this.text = "之诺";
			this.chinaName = "之诺";
			this.englishName = "ZINORO";
			this.description = "产　　地：中国·北京市\n隶属公司：华晨宝马汽车";
			break;
		case 128:
			this.resId = R.drawable.m8;
			this.text = "Noble";
			this.chinaName = "Noble汽车";
			this.englishName = "Noble";
			this.description = "产　　地：英国\n隶属公司：----";
			break;
		case 129:
			this.resId = R.drawable.m9;
			this.text = "摩根汽车";
			this.chinaName = "摩根汽车";
			this.englishName = "morgan";
			this.description = "产　　地：英国\n隶属公司：摩根汽车公司";
			break;
		/** --------------------- */
		case 130:
			this.resId = R.drawable.n0;
			this.text = "九龙汽车";
			this.chinaName = "joylong九龙商务车";
			this.englishName = "joylong";
			this.description = "产　　地：中国·江苏省·扬州市\n隶属公司：江苏九龙汽车制造有限公司";
			break;
		case 131:
			this.resId = R.drawable.n1;
			this.text = "华骐";
			this.chinaName = "华骐";
			this.englishName = "HORKI";
			this.description = "产　　地：----\n隶属公司：东风悦达起亚";
			break;
		case 132:
			this.resId = R.drawable.n2;
			this.text = "恒天汽车";
			this.chinaName = "CHTC恒天汽车";
			this.englishName = "CHTC";
			this.description = "产　　地：中国·河北·保定\n隶属公司：中国第一汽车集团公司";
			break;
		case 133:
			this.resId = R.drawable.n3;
			this.text = "Hennessey";
			this.chinaName = "Hennessey";
			this.englishName = "Hennessey";
			this.description = "产　　地：美国\n隶属公司：----";
			break;
		case 134:
			this.resId = R.drawable.n4;
			this.text = "福汽新龙马";
			this.chinaName = "福汽新龙马";
			this.englishName = "NLM MOTOR";
			this.description = "产　　地：中国·福建省\n隶属公司：福建省汽车工业集团控股公司";
			break;
		case 135:
			this.resId = R.drawable.n5;
			this.text = "沃克斯豪尔";
			this.chinaName = "沃克斯豪尔";
			this.englishName = "VAUXHALL";
			this.description = "产　　地：英国·沃克斯豪尔\n隶属公司：通用汽车";
			break;
		case 136:
			this.resId = R.drawable.n6;
			this.text = "斯派朗";
			this.chinaName = "斯派朗";
			this.englishName = "Spirra";
			this.description = "产　　地：韩国\n隶属公司：----";
			break;
		case 137:
			this.resId = R.drawable.n7;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "产　　地：----\n隶属公司：----";
			break;
		case 138:
			this.resId = R.drawable.n8;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "产　　地：----\n隶属公司：----";
			break;
		case 139:
			this.resId = R.drawable.n9;
			this.text = "----";
			this.chinaName = "----";
			this.englishName = "----";
			this.description = "产　　地：----\n隶属公司：----";
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
	 *  排名不分先后:
	 *  0 = 劳斯莱斯    = a0
	 *  1 = 宾利            = a1
	 *  2 = 保时捷        = a2
	 *  3 = 法拉利        = a3
	 *  4 = 玛莎拉蒂    = a4
	 *  5 = 兰博基尼    = a5
	 *  6 = 奔驰            = a6
	 *  7 = 宝马            = a7
	 *  8 = 迈巴赫        = a7
	 *  9 = 路斯特        = a9
	 *  10 = 奥迪   = b0
	 *  11 = 悍马    = b1
	 *  12 = 路虎   = b2
	 *  13 = 莲花    = b3
	 *  14 = 阿尔法·罗密欧    = b4
	 *  15 = 吉利    = b5
	 *  16 = 英菲尼迪    = b6
	 *  17 = 铃木    = b7
	 *  18 = 雪铁龙    = b8
	 *  19 = 长安    = b9
	 *  20 = 长安商用    = c0
	 *  21 = 名爵    = c1
	 *  22 = 开瑞   = c2
	 *  23 = 海马    = c3
	 *  24 = 东风    = c4
	 *  25 = 吉利全球鹰    = c5
	 *  26 = 日产    = c6
	 *  27 = 丰田  = c7
	 *  28 = 欧宝    = c8
	 *  29 = 捷豹    = c9
	 *  
	 *  30 = 福特野马    = d0
	 *  31 = 别克    = d1
	 *  32 = 沃尔沃  = d2
	 *  33 = 斯柯达    = d3
	 *  34 = 荣威    = d4
	 *  35 = 吉利帝豪    = d5
	 *  36 = 英伦    = d6
	 *  37 = 瑞麒  = d7
	 *  38 = 斯巴鲁    = d8
	 *  39 = 威麟    = d9
	 *  
	 *  40 = 标致    = e0
	 *  41 = 启辰    = e1
	 *  42 = 本田   = e2
	 *  43 = GMC  = e3
	 *  44 = 宝马Mini = e4
	 *  45 = 马自达    = e5
	 *  46 = 长城   = e6
	 *  47 = 理念  = e7
	 *  48 = 大众    = e8
	 *  49 = 陆风   = e9
	 *  
	 *  50 = 雪佛兰    = f0
	 *  51 = 福特    = f1
	 *  52 = 起亚   = f2
	 *  53 = 萨博  = f3
	 *  54 = 华普 = f4
	 *  55 = 中华    = f5
	 *  56 = 奇瑞  = f6
	 *  57 = 阿斯顿·马丁  = f7
	 *  58 = 夏利    = f8
	 *  59 = 雷克萨斯   = f9
	 *  
	 *  60 = 现代    = g0
	 *  61 = 吉奥    = g1
	 *  62 = 菲亚特   = g2
	 *  63 = 凯迪拉克 = g3
	 *  64 = 北汽威旺 = g4
	 *  65 = 帕加尼    = g5
	 *  66 = 西雅特  = g6
	 *  67 = 现代劳恩斯  = g7
	 *  68 = 众泰   = g8
	 *  69 = 克莱斯勒  = g9
	 *  
	 *  70 = 江淮    = h0
	 *  71 = 雷诺    = h1
	 *  72 = 科尼赛克   = h2
	 *  73 = 巴博斯 = h3
	 *  74 = 宝骏 = h4
	 *  75 = 北京汽车    = h5
	 *  76 = 北汽制造 = h6
	 *  77 = 奔腾  = h7
	 *  78 = 比亚迪   = h8
	 *  79 = 布加迪  = h9
	 *  
	 *  80 = 昌河    = i0
	 *  81 = 川汽野马    = i1
	 *  82 = DS   = i2
	 *  83 = 大发 = i3
	 *  84 = 道奇 = i4
	 *  85 = 东南    = i5
	 *  86 = 福迪 = i6
	 *  87 = 福田  = i7
	 *  88 = 观致   = i8
	 *  89 = 广汽传祺  = i9
	 * 
	 *  90 = 哈飞    = j0
	 *  91 = 海格    = j1
	 *  92 = 红旗   = j2
	 *  93 = 华泰 = j3
	 *  94 = 黄海 = j4
	 *  95 = 吉普    = j5
	 *  96 = 江铃 = j6
	 *  97 = 金杯  = j7
	 *  98 = AC Schnitzer = j8
	 *  99 = 中兴  = j9
	 *  
	 *  100 = 威兹曼    = k0
	 *  101 = 双环    = k1
	 *  102 = 世爵   = k2
	 *  103 = 三菱 = k3
	 *  104 = 力帆 = k4
	 *  105 = 卡尔森    = k5
	 *  106 = 讴歌 = k6
	 *  107 = 思铭  = k7
	 *  108 = 猎豹   = k8
	 *  109 = 双龙  = k9
	 *  
	 *  110 = Gumpert = l0
	 *  111 = 光冈    = l1
	 *  112 = 凯佰赫   = l2
	 *  113 = 纳智捷 = l3
	 *  114 = 林肯 = l4
	 *  115 = 迈凯轮    = l5
	 *  116 = 永源 = l6
	 *  117 = 腾势  = l7
	 *  118 = KTM   = l8
	 *  119 = 奔驰Smart = l9
	 *  
	 *  120 = 赛恩    = m0
	 *  121 = 特斯拉    = m1
	 *  122 = 一汽   = m2
	 *  123 = 欧朗 = m3
	 *  124 = 哈弗 = m4
	 *  125 = 达西亚    = m5
	 *  126 = Fisker = m6
	 *  127 = 之诺 = m7
	 *  128 = Noble  = m8
	 *  129 = 摩根汽车  = m9
	 *  
	 *  130 = 九龙汽车    = n0
	 *  131 = 华骐    = n1
	 *  132 = 恒天汽车   = n2
	 *  133 = Hennessey = n3
	 *  134 = 福汽新龙马 = n4
	 *  135 = 沃克斯豪尔    = n5
	 *  136 = 斯派朗 = n6
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
