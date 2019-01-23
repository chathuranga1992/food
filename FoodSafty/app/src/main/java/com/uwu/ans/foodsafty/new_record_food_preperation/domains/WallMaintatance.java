
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class WallMaintatance {


    private String mClean;
    private String mNoAccumilation;
    private String mNoContamination;
    private String mSuitable;
    private String mMarks;
    private String mRemark;

    public WallMaintatance(String mClean, String mNoAccumilation, String mNoContamination, String mSuitable, String mMarks, String mRemark) {
        this.mClean = mClean;
        this.mNoAccumilation = mNoAccumilation;
        this.mNoContamination = mNoContamination;
        this.mSuitable = mSuitable;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getClean() {
        return mClean;
    }

    public void setClean(String clean) {
        mClean = clean;
    }

    public String getMarks() {
        return mMarks;
    }

    public void setMarks(String marks) {
        mMarks = marks;
    }

    public String getNoAccumilation() {
        return mNoAccumilation;
    }

    public void setNoAccumilation(String noAccumilation) {
        mNoAccumilation = noAccumilation;
    }

    public String getNoContamination() {
        return mNoContamination;
    }

    public void setNoContamination(String noContamination) {
        mNoContamination = noContamination;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public String getSuitable() {
        return mSuitable;
    }

    public void setSuitable(String suitable) {
        mSuitable = suitable;
    }

}
