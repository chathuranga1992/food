
package com.uwu.ans.foodsafty.new_record_building.domains;


@SuppressWarnings("unused")
public class Space {


    private String mAdequate;

    private String mAppropriate;
    private String mMarks;
    private String mRemarks;

    public Space(String mAdequate, String mAppropriate, String mMarks, String mRemarks) {
        this.mAdequate = mAdequate;
        this.mAppropriate = mAppropriate;
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

    public String getAppropriate() {
        return mAppropriate;
    }

    public void setAppropriate(String appropriate) {
        mAppropriate = appropriate;
    }

}
