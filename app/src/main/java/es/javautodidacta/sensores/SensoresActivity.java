package es.javautodidacta.sensores;

import androidx.fragment.app.Fragment;

public class SensoresActivity extends SimpleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new SensoresFragment();
    }
}
