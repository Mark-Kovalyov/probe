package mayton.probe.ignite;

import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

public class MyLifecycleBean implements LifecycleBean {

    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        System.out.printf(":: onLifecycleEvent : %s\n", evt.name());
    }
}
