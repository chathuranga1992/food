
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class RiskFactors {

    private String mHazzards;
    private String mUnsafeStructures;
    private String mMarks;
    private String mRemark;

    public RiskFactors(String mHazzards, String mUnsafeStructures, String mMarks, String mRemark) {
        this.mHazzards = mHazzards;
        this.mUnsafeStructures = mUnsafeStructures;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getmMarks() {
        return mMarks;
    }

    public void setmMarks(String mMarks) {
        this.mMarks = mMarks;
    }

    public String getmRemark() {
        return mRemark;
    }

    public void setmRemark(String mRemark) {
        this.mRemark = mRemark;
    }

    public String getHazzards() {
        return mHazzards;
    }

    public void setHazzards(String hazzards) {
        mHazzards = hazzards;
    }

    public String getUnsafeStructures() {
        return mUnsafeStructures;
    }

    public void setUnsafeStructures(String unsafeStructures) {
        mUnsafeStructures = unsafeStructures;
    }

}
