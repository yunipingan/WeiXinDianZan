package cn.pingweb.weixindianzan;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.List;

public class MyService extends AccessibilityService {

    List<AccessibilityNodeInfo> nodeInfos;
    AccessibilityNodeInfo rootNodeInfo;
    String preString = "com.tencent.mm:id/";

    public MyService() {
    }

    @Override
    protected boolean onGesture(int gestureId) {
        return super.onGesture(gestureId);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    @Override
    public List<AccessibilityWindowInfo> getWindows() {
        return super.getWindows();
    }

    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        return super.getRootInActiveWindow();
    }

    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        return super.findFocus(focus);
    }

    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    @Override
    public synchronized void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.v("Accessibility", "微信点赞服务");
        rootNodeInfo = accessibilityEvent.getSource();
        if(rootNodeInfo!=null)
        {
            List<AccessibilityNodeInfo> cdmNodes = rootNodeInfo.findAccessibilityNodeInfosByViewId(preString+"cdm");
            {
                if (cdmNodes.size()>0) {
                    Log.v("Accessibility", "cdmSize" + cdmNodes.size());
                    for (AccessibilityNodeInfo cdm : cdmNodes)
                    {
                    cdm.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        List<AccessibilityNodeInfo>cdiNodes = rootNodeInfo.findAccessibilityNodeInfosByViewId(preString+"cdi");
                        if(cdiNodes.size()>0){
                            if(cdiNodes.get(0).getText().equals("赞")){
                                List<AccessibilityNodeInfo> cdgNodes = rootNodeInfo.findAccessibilityNodeInfosByViewId(preString + "cdg");
                                if (cdgNodes.size() > 0) {
                                    Log.v("Accessibility", "点击成功");
                                    cdgNodes.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

}
//cdm 0 点开
//cdg 0 （cdi 1 是赞/取消的文字）
//cdj 2 评论