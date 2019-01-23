
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class PestControl {


    private String mNoPests;
    private String mNoRodents;
    private String mMarks;
    private String mRemark;

    public PestControl(String mNoPests, String mNoRodents, String mMarks, String mRemark) {
        this.mNoPests = mNoPests;
        this.mNoRodents = mNoRodents;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getMarks() {
        return mMarks;
    }

    public void setMarks(String marks) {
        mMarks = marks;
    }

    public String getNoPests() {
        return mNoPests;
    }

    public void setNoPests(String noPests) {
        mNoPests = noPests;
    }

    public String getNoRodents() {
        return mNoRodents;
    }

    public void setNoRodents(String noRodents) {
        mNoRodents = noRodents;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

}
