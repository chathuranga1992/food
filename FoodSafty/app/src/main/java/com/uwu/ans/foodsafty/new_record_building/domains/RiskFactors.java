
package com.uwu.ans.foodsafty.new_record_building.domains;


@SuppressWarnings("unused")
public class RiskFactors {


    private String mHazzards;

    private String mUnsafeStructures;
    private String mMarks;
    private String mRemarks;

    public RiskFactors(String mHazzards, String mUnsafeStructures, String mMarks, String mRemarks) {
        this.mHazzards = mHazzards;
        this.mUnsafeStructures = mUnsafeStructures;
        this.mMarks = mMarks;
        this.mRemarks = mRemarks;
    }

    public String getmMarks() {
        return mMarks;
    }

    public void setmMarks(String mMarks) {
        this.mMarks = mMarks;
    }

    public String getmRemarks() {
        return mRemarks;
    }

    public void setmRemarks(String mRemarks) {
        this.mRemarks = mRemarks;
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
