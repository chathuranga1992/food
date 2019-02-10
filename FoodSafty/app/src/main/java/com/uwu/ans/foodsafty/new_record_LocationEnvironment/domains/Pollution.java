
package com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains;


@SuppressWarnings("unused")
public class Pollution {


    private String mNoAccumulation;
    private String mNoDust;
    private String mNoSmoke;
    private String mNoAdverseEffects;
    private String mMarks;
    private String mRemarks;

    public Pollution(String mNoAccumulation, String mNoDust, String mNoSmoke, String mNoAdverseEffects, String mMarks, String mRemarks) {
        this.mNoAccumulation = mNoAccumulation;
        this.mNoDust = mNoDust;
        this.mNoSmoke = mNoSmoke;
        this.mNoAdverseEffects = mNoAdverseEffects;
        this.mMarks = mMarks;
        this.mRemarks = mRemarks;
    }

    public String getmNoAccumulation() {
        return mNoAccumulation;
    }

    public void setmNoAccumulation(String mNoAccumulation) {
        this.mNoAccumulation = mNoAccumulation;
    }

    public String getmNoDust() {
        return mNoDust;
    }

    public void setmNoDust(String mNoDust) {
        this.mNoDust = mNoDust;
    }

    public String getmNoSmoke() {
        return mNoSmoke;
    }

    public void setmNoSmoke(String mNoSmoke) {
        this.mNoSmoke = mNoSmoke;
    }

    public String getmNoAdverseEffects() {
        return mNoAdverseEffects;
    }

    public void setmNoAdverseEffects(String mNoAdverseEffects) {
        this.mNoAdverseEffects = mNoAdverseEffects;
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
}
