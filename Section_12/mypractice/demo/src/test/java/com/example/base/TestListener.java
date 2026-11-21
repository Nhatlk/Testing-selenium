package com.example.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
  @Override
  public void onTestStart(ITestResult result) {
    System.out.println("==================================================");
    System.out.println("[START] Bắt đầu chạy test: " + result.getName());
  }
  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("[FAIL] THẤT BẠI: " + result.getName());
    System.out.println("[ERROR REASON] " + result.getThrowable().getMessage());
    System.out.println("==================================================");
  }
  @Override
  public void onStart(ITestContext context) {
    System.out.println("&gt;&gt;&gt; KHỞI ĐỘNG SUITE: " + context.getName());
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.println("&gt;&gt;&gt; HOÀN TẤT SUITE: " + context.getName());
  }

}
