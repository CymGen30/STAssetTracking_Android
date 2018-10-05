/*
 *  Copyright (c) 2019  STMicroelectronics – All rights reserved
 *  The STMicroelectronics corporate logo is a trademark of STMicroelectronics
 *
 *  Redistribution and use in source and binary forms, with or without modification,
 *  are permitted provided that the following conditions are met:
 *  - Redistributions of source code must retain the above copyright notice, this list of conditions
 *    and the following disclaimer.
 *
 *  - Redistributions in binary form must reproduce the above copyright notice, this list of
 *    conditions and the following disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *
 *  - Neither the name nor trademarks of STMicroelectronics International N.V. nor any other
 *    STMicroelectronics company nor the names of its contributors may be used to endorse or
 *    promote products derived from this software without specific prior written permission.
 *
 *  - All of the icons, pictures, logos and other images that are provided with the source code
 *    in a directory whose title begins with st_images may only be used for internal purposes and
 *    shall not be redistributed to any third party or modified in any way.
 *
 *  - Any redistributions in binary form shall not include the capability to display any of the
 *    icons, pictures, logos and other images that are provided with the source code in a directory
 *    whose title begins with st_images.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *  AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
 *  OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 *  OF SUCH DAMAGE.
 */

package com.st.assetTracking.sigfox.view

import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.st.assetTracking.sigfox.R
import com.st.assetTracking.sigfox.adapter.OrientationSelectorAdapter
import com.st.assetTracking.sigfox.model.Orientation
import com.st.assetTracking.sigfox.model.SensorThreshold
import com.st.assetTracking.sigfox.viewModel.NavigationViewModel
import com.st.assetTracking.sigfox.viewModel.SampleSettingsViewModel
import com.st.assetTracking.sigfox.viewModel.ShowSampleSettings

internal class OrientationSelectorFragment : AppCompatDialogFragment() {

    companion object {
        val SELECTABLE_ORIENTATION = listOf(
                Orientation.TOP_LEFT,
                Orientation.BOTTOM_LEFT,
                Orientation.BOTTOM_RIGHT,
                Orientation.TOP_RIGHT,
                Orientation.UP,
                Orientation.DOWN)
    }

    private val mNavigator by lazy { ViewModelProviders.of(requireActivity()).get(NavigationViewModel::class.java) }
    private val mSettingsViewModel by lazy { ViewModelProviders.of(requireActivity()).get(SampleSettingsViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_orientation_selector, container, false)

        rootView.findViewById<RecyclerView>(R.id.sensorTypeSelector_list).adapter =
                OrientationSelectorAdapter(SELECTABLE_ORIENTATION,object : OrientationSelectorAdapter.OnOrientationSelectedCallback{
                    override fun onOrientationSelected(selectedOrientation: Orientation) {
                        mSettingsViewModel.addSensorThreshold(SensorThreshold.orientationThreshold(selectedOrientation))
                        mNavigator.moveTo(ShowSampleSettings)
                        dismiss()
                    }
                })

        return rootView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle(R.string.orientationSelector_title)
        return dialog
    }

}
