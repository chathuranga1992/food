
package com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains;


@SuppressWarnings("unused")
public class Appearance {


    private String mAttractive;
    private String mBeautiful;
    private String mMarks;
    private String mRemarks;

    public Appearance(String mAttractive, String mBeautiful, String mMarks, String mRemarks) {
        this.mAttractive = mAttractive;
        this.mBeautiful = mBeautiful;
        this.mMarks = mMarks;
        this.mRemarks = mRemarks;
    }

    public String getmAttractive() {
        return mAttractive;
    }

    public void setmAttractive(String mAttractive) {
        this.mAttractive = mAttractive;
    }

    public String getmBeautiful() {
        return mBeautiful;
    }

    public void setmBeautiful(String mBeautiful) {
        this.mBeautiful = mBeautiful;
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
