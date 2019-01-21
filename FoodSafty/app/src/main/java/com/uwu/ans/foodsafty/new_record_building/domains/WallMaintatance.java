
package com.uwu.ans.foodsafty.new_record_building.domains;



@SuppressWarnings("unused")
public class WallMaintatance {


    private String mClean;
    private String mNoAccumilation;
    private String mNoContamination;
    private String mSuitable;
    private String mMarks;
    private String mRemarks;

    public WallMaintatance(String mClean, String mNoAccumilation, String mNoContamination, String mSuitable, String mMarks, String mRemarks) {
        this.mClean = mClean;
        this.mNoAccumilation = mNoAccumilation;
        this.mNoContamination = mNoContamination;
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

    public String getClean() {
        return mClean;
    }

    public void setClean(String clean) {
        mClean = clean;
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

    public String getSuitable() {
        return mSuitable;
    }

    public void setSuitable(String suitable) {
        mSuitable = suitable;
    }

}
