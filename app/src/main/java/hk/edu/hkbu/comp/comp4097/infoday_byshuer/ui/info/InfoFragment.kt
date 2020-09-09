package hk.edu.hkbu.comp.comp4097.infoday_byshuer.ui.info

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hk.edu.hkbu.comp.comp4097.infoday_byshuer.R

class InfoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}