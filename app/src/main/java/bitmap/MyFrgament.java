package bitmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by admin on 2017/12/21.
 */

public class MyFrgament extends Fragment {
    public static MyFrgament newInstance() {
        
        Bundle args = new Bundle();
        
        MyFrgament fragment = new MyFrgament();
        fragment.setArguments(args);
        return fragment;
    }
}
