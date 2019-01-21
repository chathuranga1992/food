
package com.uwu.ans.foodsafty.new_record_building.domains;


@SuppressWarnings("unused")
public class BuildingModel {


    private CeilingStructure mCeilingStructure;

    private String mComment;

    private FloorStructure mFloorStructure;

    private String mId;

    private LightNVentilation mLightNVentilation;

    private String mRecordId;

    private RiskFactors mRiskFactors;

    private Space mSpace;

    private Structure mStructure;

    private WallMaintatance mWallMaintatance;

    public BuildingModel(CeilingStructure mCeilingStructure, String mComment,
                         FloorStructure mFloorStructure, String mId,
                         LightNVentilation mLightNVentilation, String mRecordId,
                         RiskFactors mRiskFactors, Space mSpace, Structure mStructure,
                         WallMaintatance mWallMaintatance) {
        this.mCeilingStructure = mCeilingStructure;
        this.mComment = mComment;
        this.mFloorStructure = mFloorStructure;
        this.mId = mId;
        this.mLightNVentilation = mLightNVentilation;
        this.mRecordId = mRecordId;
        this.mRiskFactors = mRiskFactors;
        this.mSpace = mSpace;
        this.mStructure = mStructure;
        this.mWallMaintatance = mWallMaintatance;
    }

    public CeilingStructure getCeilingStructure() {
        return mCeilingStructure;
    }

    public void setCeilingStructure(CeilingStructure ceilingStructure) {
        mCeilingStructure = ceilingStructure;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public FloorStructure getFloorStructure() {
        return mFloorStructure;
    }

    public void setFloorStructure(FloorStructure floorStructure) {
        mFloorStructure = floorStructure;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public LightNVentilation getLightNVentilation() {
        return mLightNVentilation;
    }

    public void setLightNVentilation(LightNVentilation lightNVentilation) {
        mLightNVentilation = lightNVentilation;
    }

    public String getRecordId() {
        return mRecordId;
    }

    public void setRecordId(String recordId) {
        mRecordId = recordId;
    }

    public RiskFactors getRiskFactors() {
        return mRiskFactors;
    }

    public void setRiskFactors(RiskFactors riskFactors) {
        mRiskFactors = riskFactors;
    }

    public Space getSpace() {
        return mSpace;
    }

    public void setSpace(Space space) {
        mSpace = space;
    }

    public Structure getStructure() {
        return mStructure;
    }

    public void setStructure(Structure structure) {
        mStructure = structure;
    }

    public WallMaintatance getWallMaintatance() {
        return mWallMaintatance;
    }

    public void setWallMaintatance(WallMaintatance wallMaintatance) {
        mWallMaintatance = wallMaintatance;
    }

}
