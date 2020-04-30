package com.solplatform.constants;

public enum BuildStatus {
    WAITFOREXCUTE ("wait_for_excute"), EXCUTING ("excuting"), PASS ("pass"), PARTIALPASS ("partial_pass"), FAILED ("failed"),
    USERSTOP ("user_stop"), NOCASE ("no_case"), EXCEPTION ("exception"), FINISH ("finish");

    // 定义一个 private 修饰的实例变量
    private String date;

    // 定义一个带参数的构造器，枚举类的构造器只能使用 private 修饰
    private BuildStatus(String date) {
        this.date = date;
    }

    // 定义 get set 方法
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // 重写 toString() 方法
    @Override
    public String toString() {
        return date;
    }
}
