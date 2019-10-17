package com.st.assetTracking.comunication

import com.st.assetTracking.sigfox.model.ThresholdComparison
import com.st.assetTracking.sigfox.model.ThresholdSensorType
import com.st.assetTracking.sigfox.comunication.toSamplingSettings
import org.junit.Assert
import org.junit.Test

class CloudTrackerNucleoConsoleKtTest{

    @Test
    fun decode0Th(){
        val data = byteArrayOf(0x01,0x02,0x00)
        val parsed = data.toSamplingSettings()
        Assert.assertNotNull(parsed)
        Assert.assertEquals(2.toShort(),parsed!!.cloudSyncInterval)
        Assert.assertEquals(1.toShort(),parsed.samplingInterval)
        Assert.assertEquals(0,parsed.threshold.size)
    }

    @Test
    fun decode1Th(){
        val data = byteArrayOf(0x01,0x02,0x01,0x01,0x01,0x00,0x00)
        val parsed = data.toSamplingSettings()
        Assert.assertNotNull(parsed)
        Assert.assertEquals(1,parsed!!.threshold.size)
        Assert.assertEquals(com.st.assetTracking.sigfox.model.ThresholdSensorType.Temperature,parsed.threshold[0].sensor)
        Assert.assertEquals(com.st.assetTracking.sigfox.model.ThresholdComparison.BiggerOrEqual,parsed.threshold[0].comparison)
        Assert.assertEquals(0.0.toFloat(),parsed.threshold[0].threshold)
    }

    @Test
    fun decode1ThMin(){
        //val data = byteArrayOf(0x01,0x02,0x01,0x01,0xFF.toByte(),0x00,0x00)
        val data = byteArrayOf(1,15, 1, 1, 0, 0xE8.toByte(), 3)
        val parsed = data.toSamplingSettings()
        Assert.assertNotNull(parsed)
        Assert.assertEquals(1,parsed!!.threshold.size)
        Assert.assertEquals(com.st.assetTracking.sigfox.model.ThresholdSensorType.Temperature,parsed.threshold[0].sensor)
        Assert.assertEquals(com.st.assetTracking.sigfox.model.ThresholdComparison.BiggerOrEqual,parsed.threshold[0].comparison)
        Assert.assertEquals(0.0.toFloat(),parsed.threshold[0].threshold)
    }

}