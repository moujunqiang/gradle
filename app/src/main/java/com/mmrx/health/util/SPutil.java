package com.mmrx.health.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mmrx.health.R;
import com.mmrx.health.bean.User;
import com.mmrx.health.bean.WeightBean;

/**
* 用户基本信息存储工具类
* */
public class SPutil {

	public static final String USER = "user";
	public static final String WEIGHTBEAN = "weightbean";
	public static final String DATE = "date";
	public static final String WATER = "water";
	public static final String LWATER = "lwater";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String WEIGHT = "weight";
	public static final String TARGETWEIGHT = "targetweight";
	public static final String GENDER = "gender";
	public static final String HEIGHT = "tall";
	public static final String BMI = "bmi";
	private static final String ISALARM = "isalarm";
    private static final String ACC_ICON_PATH = "account_icon_path";
    private static final String LOGIN = "login";
    //健身计划fragment是否需要需要更新
    private static final String BUILD_NEED_UPDATE = "buildNeedUpdate";

    private Context mContext;
	SharedPreferences preferences;

	public SPutil(Context context) {
		if (null == preferences) {
            mContext = context;
			preferences = context.getSharedPreferences(USER,
					Context.MODE_PRIVATE);

		}
	}

    public void WriteBuildUpdate(boolean isUpdate) {
        preferences.edit().putBoolean(BUILD_NEED_UPDATE, isUpdate).commit();
    }

    public boolean ReadBuildUpdate() {
        return preferences.getBoolean(BUILD_NEED_UPDATE, true);
    }

    public void WriteLogin(boolean isLogin) {
        preferences.edit().putBoolean(LOGIN, isLogin).commit();
    }

    public boolean ReadLogin() {
        return preferences.getBoolean(LOGIN, false);
    }


	public void WriteAlarm(boolean isAlarm) {
		preferences.edit().putBoolean(ISALARM, isAlarm).commit();
	}

	public boolean ReadAlarm() {
		return preferences.getBoolean(ISALARM, false);
	}
	public void WriteName(String name) {
		preferences.edit().putString(NAME, name).commit();
	}

	public String ReadName() {
		return preferences.getString(NAME, "无");
	}
	public void WriteBMI(String bmi) {
		preferences.edit().putString(BMI, bmi).commit();
	}

	public String ReadBMI() {
		return preferences.getString(BMI, "unsetBMI");
	}

	public void WriteAge(int age) {
		preferences.edit().putInt(AGE, age).commit();

	}

	public int ReadAge() {
		return preferences.getInt(AGE, -1);

	}
	public void WriteWater(int water) {
		preferences.edit().putInt(WATER, water).commit();

	}

	public int ReadWater() {
		return preferences.getInt(WATER, 0);

	}
	public void WriteLWater(int lwater) {
		preferences.edit().putInt(LWATER, lwater).commit();

	}

	public int ReadLWater() {
		return preferences.getInt(LWATER, 0);

	}

	public void WriteWeight(int weight) {
		preferences.edit().putInt(WEIGHT, weight).commit();

	}
	public void WriteTargetWeight(int targetWeight) {
		preferences.edit().putInt(TARGETWEIGHT, targetWeight).commit();
	}

	public int ReadTargetWeight() {
		return preferences.getInt(TARGETWEIGHT,0);
	}




	public int ReadWeight() {
		return preferences.getInt(WEIGHT, 0);

	}

	public void WriteHeight(int hight) {
		preferences.edit().putInt(HEIGHT, hight).commit();

	}


	public int ReadHeight() {
		return preferences.getInt(HEIGHT, 0);

	}

	public void WriteGender(String gender) {
		preferences.edit().putString(GENDER, gender).commit();

	}

	public String ReadGender() {
		return preferences.getString(GENDER,mContext.getResources().getString(R.string.male));

	}

	public void WriteUser(User u) {
		preferences.edit().putString(GENDER, u.getGender())
				.putInt(HEIGHT, u.getTall()).putInt(WEIGHT, u.getWeight())
				.putString(NAME, u.getName()).putInt(AGE, u.getAge())
				.putString(GENDER, u.getGender()).commit();

	}

	public void WriteWeightBean(WeightBean weightBean) {
        preferences.edit().putString(DATE, weightBean.getCtime())
				.putInt(WEIGHT,weightBean.getWeight()).commit();

    }

    public String ReadAccPath() {
        return preferences.getString(ACC_ICON_PATH, "...");

    }

	public void Clear(){
		preferences.edit().clear();
	}
}
