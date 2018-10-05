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

package com.st.assetTracking.sigfox.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.st.assetTracking.sigfox.R
import com.st.assetTracking.sigfox.model.ThresholdSensorType
import com.st.assetTracking.sigfox.viewModel.resourceImage
import com.st.assetTracking.sigfox.viewModel.resourceString

internal class SensorTypeSelectorAdapter(private val sensorList:List<ThresholdSensorType> = emptyList(),
    private val onSensorSelectedCallback:OnSensorSelectedCallback?=null) :
    RecyclerView.Adapter<SensorTypeSelectorAdapter.SensorTypeViewHolder>() {


    interface OnSensorSelectedCallback{
        fun onSensorSelected( item : ThresholdSensorType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorTypeViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor_type,
                parent,false)
        return SensorTypeViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return sensorList.size
    }

    override fun onBindViewHolder(viewHolder: SensorTypeViewHolder, itemIndex: Int) {
        val sensor = sensorList[itemIndex]
        viewHolder.icon.setImageResource(sensor.resourceImage)
        viewHolder.name.setText(sensor.resourceString)
        viewHolder.itemView.setOnClickListener {
            onSensorSelectedCallback?.onSensorSelected(sensor)
        }
    }


    class SensorTypeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val icon:ImageView = view.findViewById(R.id.sensorTypeSelector_image)
        val name:TextView = view.findViewById(R.id.sensorTypeSelector_text)
    }
}