
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class Drainage {


    private String mAdequate;
    private String mNoAccumulation;
    private String mSafe;
    private String mSuitable;
    private String mMarks;
    private String mRemark;

    public Drainage(String mAdequate, String mNoAccumulation, String mSafe, String mSuitable, String mMarks, String mRemark) {
        this.mAdequate = mAdequate;
        this.mNoAccumulation = mNoAccumulation;
        this.mSafe = mSafe;
        this.mSuitable = mSuitable;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getAdequate() {
        return mAdequate;
    }

    public void setAdequate(String adequate) {
        mAdequate = adequate;
    }

    public String getMarks() {
        return mMarks;
    }

    public void setMarks(String marks) {
        mMarks = marks;
    }

    public String getNoAccumulation() {
        return mNoAccumulation;
    }

    public void setNoAccumulation(String noAccumulation) {
        mNoAccumulation = noAccumulation;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public String getSafe() {
        return mSafe;
    }

    public void setSafe(String safe) {
        mSafe = safe;
    }

    public String getSuitable() {
        return mSuitable;
    }

    public void setSuitable(String suitable) {
        mSuitable = suitable;
    }

}
