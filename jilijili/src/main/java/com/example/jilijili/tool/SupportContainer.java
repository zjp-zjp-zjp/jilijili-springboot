package com.example.jilijili.tool;

public class SupportContainer {
    private Long typeOfSupport;
    private Long targetId;

    public SupportContainer() {
    }

    public SupportContainer(Long typeOfSupport, Long targetId) {
        this.typeOfSupport = typeOfSupport;
        this.targetId = targetId;
    }

    public Long getTypeOfSupport() {
        return typeOfSupport;
    }

    public void setTypeOfSupport(Long typeOfSupport) {
        this.typeOfSupport = typeOfSupport;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "SupportContainer{" +
                "typeOfSupport=" + typeOfSupport +
                ", targetId=" + targetId +
                '}';
    }
}
