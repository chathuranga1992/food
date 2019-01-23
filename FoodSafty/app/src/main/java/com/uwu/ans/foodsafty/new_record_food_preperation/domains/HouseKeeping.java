
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class HouseKeeping {


    private String mAdequate;
    private String mAppropriate;
    private String mMarks;
    private String mRemark;

    public HouseKeeping(String mAdequate, String mAppropriate, String mMarks, String mRemark) {
        this.mAdequate = mAdequate;
        this.mAppropriate = mAppropriate;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getAdequate() {
        return mAdequate;
    }

    public void setAdequate(String adequate) {
        mAdequate = adequate;
    }

    public String getAppropriate() {
        return mAppropriate;
    }

    public void setAppropriate(String appropriate) {
        mAppropriate = appropriate;
    }

    public String getMarks() {
        return mMarks;
    }

    public void setMarks(String marks) {
        mMarks = marks;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

}
