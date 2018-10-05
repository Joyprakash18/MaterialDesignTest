package com.sayan.sample.materialdesigntest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RunningProcessActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_proccess);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            try {
                List<ProcessModel> packages = fetchAllCurrentlyRunningProcessBeforeLollipop();
                AsyncTask.execute(new MyProcessKillRunnable(packages, this));
            } catch (PackageNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private List<ProcessModel> fetchCurrentUserStatisticsFromLollipop() throws PackageNotFoundException {
        List<ProcessModel> processModels = new ArrayList<>();
        Calendar beginCal = Calendar.getInstance();
//        beginCal.set(Calendar.DATE, 1);
//        beginCal.set(Calendar.MONTH, 10);
//        beginCal.set(Calendar.YEAR, 2018);
        Calendar calendar = Calendar.getInstance();
        beginCal.setTimeInMillis(calendar.getTimeInMillis() - 3600000);
//        Calendar endCal = Calendar.getInstance();
//        endCal.set(Calendar.DATE, 3);
//        endCal.set(Calendar.MONTH, 10);
//        endCal.set(Calendar.YEAR, 2018);
        long endTime = calendar.getTimeInMillis();
        final UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        if (usageStatsManager != null) {
            final List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, beginCal.getTimeInMillis(), endTime);
            Log.d("Executed app", "Application executed : " + queryUsageStats.get(0).getPackageName());
            if (queryUsageStats != null) {
                if (!queryUsageStats.isEmpty()) {
                    for (UsageStats usageStats :
                            queryUsageStats) {
                        processModels.add(new ProcessModel(usageStats.getPackageName(), -1));
                    }
                } else {
                    throw new PackageNotFoundException("Unable to find any running process currently, queryUsageStats empty");
                }
            } else {
                throw new PackageNotFoundException("Unable to find any running process currently, queryUsageStats null");
            }
        } else {
            throw new PackageNotFoundException("Unable to fetch system UsageStatsManager, usageStatsManager null");
        }
        return processModels;
    }

    private List<ProcessModel> fetchAllCurrentlyRunningProcessBeforeLollipop() throws PackageNotFoundException {
        List<ProcessModel> processModels = new ArrayList<>();
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> recentTasks;
        if (activityManager != null) {
            recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
            if (recentTasks != null) {
                if (!recentTasks.isEmpty()) {
                    for (ActivityManager.RunningTaskInfo runningTaskInfo :
                            recentTasks) {
                        processModels.add(new ProcessModel(runningTaskInfo.baseActivity.getPackageName(), runningTaskInfo.id));
                    }
                } else {
                    throw new PackageNotFoundException("Unable to find any running process currently, recentTasks empty");
                }
            } else {
                throw new PackageNotFoundException("Unable to find any running process currently, recentTasks null");
            }
        } else {
            throw new PackageNotFoundException("Unable to fetch activityManager, activityManager null");
        }
        return processModels;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            try {
                List<ProcessModel> processModels = fetchCurrentUserStatisticsFromLollipop();
                AsyncTask.execute(new MyProcessKillRunnable(processModels, this));
            } catch (PackageNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyProcessKillRunnable implements Runnable {

        private List<ProcessModel> processModelList;
        private Activity activity;

        MyProcessKillRunnable(List<ProcessModel> processModelList, Activity activity) {
            this.processModelList = processModelList;
            this.activity = activity;
        }

        @Override
        public void run() {
            for (ProcessModel processModel :
                    processModelList) {
                killAppByPackage(processModel);
                break;
            }
        }

        private void killAppByPackage(ProcessModel packageToKill) {
//            List<ApplicationInfo> processModelList;
//            PackageManager pm;
//            pm = activity.getPackageManager();
//            //get a list of installed apps.
//            processModelList = pm.getInstalledApplications(0);
//            ActivityManager mActivityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
//             String myPackage = activity.getApplicationContext().getPackageName();
//            for (ApplicationInfo packageInfo : processModelList) {
//                if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1) {
//                    continue;
//                }
//                if(packageInfo.packageName.equals(myPackage)) {
//                    continue;
//                }
//                if(packageInfo.packageName.equals(packageToKill)) {
//                    if (mActivityManager != null) {
//                        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
//                    }
//                }
//            }
            ActivityManager mActivityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
//            mActivityManager.killBackgroundProcesses(packageToKill.getPackageName());
            android.os.Process.killProcess(packageToKill.getpId());
        }
    }
}
