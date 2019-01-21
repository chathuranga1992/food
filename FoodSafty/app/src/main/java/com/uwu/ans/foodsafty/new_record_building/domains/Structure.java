
package com.uwu.ans.foodsafty.new_record_building.domains;


@SuppressWarnings("unused")
public class Structure {


    private String mAdequate;
    private String mPermanant;
    private String mSafe;
    private String mSuitable;
    private String mMarks;
    private String mRemarks;

    public Structure(String mAdequate, String mPermanant, String mSafe, String mSuitable, String mMarks, String mRemarks) {
        this.mAdequate = mAdequate;
        this.mPermanant = mPermanant;
        this.mSafe = mSafe;
        this.mSuitable = mSuitable;
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

    public String getAdequate() {
        return mAdequate;
    }

    public void setAdequate(String adequate) {
        mAdequate = adequate;
    }

    public String getPermanant() {
        return mPermanant;
    }

    public void setPermanant(String permanant) {
        mPermanant = permanant;
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
