
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;




@SuppressWarnings("unused")
public class Structure {


    private String mAdequate;
    private String mPermanant;
    private String mSafe;
    private String mSuitable;
    private String mMarks;
    private String mRemark;

    public Structure(String mAdequate, String mPermanant, String mSafe, String mSuitable, String mMarks, String mRemark) {
        this.mAdequate = mAdequate;
        this.mPermanant = mPermanant;
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

    public String getPermanant() {
        return mPermanant;
    }

    public void setPermanant(String permanant) {
        mPermanant = permanant;
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
