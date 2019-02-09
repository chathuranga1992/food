
package com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains;


public class Suitability {


    private String mAmenity;
    private String mAccessibility;
    private String mActivity;
    private String mVisibility;
    private String mMarks;
    private String mRemarks;

    public Suitability(String mAmenity, String mAccessibility, String mActivity, String mVisibility, String mMarks, String mRemarks) {
        this.mAmenity = mAmenity;
        this.mAccessibility = mAccessibility;
        this.mActivity = mActivity;
        this.mVisibility = mVisibility;
        this.mMarks = mMarks;
        this.mRemarks = mRemarks;
    }

    public String getmAmenity() {
        return mAmenity;
    }

    public void setmAmenity(String mAmenity) {
        this.mAmenity = mAmenity;
    }

    public String getmAccessibility() {
        return mAccessibility;
    }

    public void setmAccessibility(String mAccessibility) {
        this.mAccessibility = mAccessibility;
    }

    public String getmActivity() {
        return mActivity;
    }

    public void setmActivity(String mActivity) {
        this.mActivity = mActivity;
    }

    public String getmVisibility() {
        return mVisibility;
    }

    public void setmVisibility(String mVisibility) {
        this.mVisibility = mVisibility;
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
